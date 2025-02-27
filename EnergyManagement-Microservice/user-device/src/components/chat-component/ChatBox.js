import '../../styles/ChatBox.css';
import React, { useState, useEffect } from 'react';
import { getCookie } from '../../services/login_service';
import { createMessage, getMessagesBetweenUsers,readMessage } from '../../services/chat_service';
import SockJS from 'sockjs-client';
import Stomp from 'stompjs';

function ChatBox({ user, onClose }) {


    const [isTyping, setIsTyping] = useState(false);
    const [typingTimeout, setTypingTimeout] = useState(null);
    const [userTyping, setUserTyping] = useState(false);
    const currentUser = getCookie();
    const [stompClient, setStompClient] = useState(null);
    const [newMessage, setNewMessage] = useState('');
    const [messages, setMessages] = useState([]);

    useEffect(() => {                                               //sa mearga mesajele in sus cand trimit un mesaj nou
        const messagesContainer = document.querySelector('.messages-container');
        messagesContainer.scrollTop = messagesContainer.scrollHeight;
        console.log(messages);
    }, [messages]);
  
     useEffect(() => {  
        const socket = new SockJS('http://localhost:8082/ws-chat');
        const client = Stomp.over(socket);
    
        client.connect({}, () => {
          setStompClient(client); 
          client.subscribe(`/topic/notifications/${currentUser.id}`, (message) => {     //afiseaza mesajul primit prin websocket
            const receivedMessage = JSON.parse(message.body);
            setMessages((prevMessages) => {
                const isDuplicate = prevMessages.some(
                    (message) => message.timestamp === receivedMessage.timestamp
                );
            
                return isDuplicate ? prevMessages : [...prevMessages, receivedMessage];
            });
            
            console.log(receivedMessage);
          });


          client.subscribe(`/topic/notifications/${currentUser.id}`, (message) => {
            const receivedMessage = JSON.parse(message.body);
            if (receivedMessage.type === 'TYPING') {                        //afiseaza mesajul de typing
                setUserTyping(true);
                setTimeout(() => setUserTyping(false), 3000);
            }
        });
        
        client.subscribe(`/topic/notifications/read/${currentUser.id}/${user.id}`, (message) => {
            const receivedMessage = message.body;
        
            if (receivedMessage === 'read') { 
                setMessages((prevMessages) =>
                    prevMessages.map((msg) => {
                        if (msg.senderId === currentUser.id && msg.recipientId === user.id) {
                            return { ...msg, read: true };
                        }
                        return msg;
                    })
                );
            } else {
                console.error("Unexpected message format:", receivedMessage);
            }
        });

        

        }, []);
  
     }, []);

     

    useEffect(() => {

        const fetchMessages = async () => {
            if (!currentUser || !user.id) return;               //pentru user-ul cu care s-a deschis conversatia se fechuiesc mesajele din db

            try {
                const fetchedMessages = await getMessagesBetweenUsers(currentUser.id, user.id);
                setMessages(fetchedMessages);
            } catch (error) {
                console.error('Error fetching messages:', error);
            }
        };

        fetchMessages();
    }, [user.id]);
    

    useEffect(() => {
        if (stompClient && user.id) {
            stompClient.send(
                `/app/conversation-opened/${user.id}`,
                {},
                currentUser.id.toString() // Trimite doar ID-ul utilizatorului ca string
            );
        }
    }, [stompClient, user.id]);
    
    
    useEffect(() => {
        if (!stompClient) return;
    
        const handleConversationOpened = (message) => {
            const { openerId } = JSON.parse(message.body);
            if (openerId === user.id) { // Verifici dacă notificarea vine de la utilizatorul curent al conversației
                const updatedMessages = messages.map((msg) => {
                    if (msg.senderId === openerId && !msg.read) {
                        return { ...msg, read: true };
                    }
                    return msg;
                });
                setMessages(updatedMessages);
            }
        };
    
        const subscription = stompClient.subscribe(`/topic/conversation-opened/${currentUser.id}`, handleConversationOpened);
    
        return () => subscription.unsubscribe();
    }, [stompClient, messages, user.id]);
    






    useEffect(() => {

        const markMessagesAsRead = async () => {
            if (stompClient && messages.length > 0) {
                const unreadMessages = messages.filter(
                    (message) => message.recipientId === currentUser.id && !message.read
                );
    
                unreadMessages.forEach((message) => {
                    stompClient.send(
                        `/app/read/${message.senderId}`,
                        {},
                        JSON.stringify({ messageId: message.id, recipientId: currentUser.id })
                    );
                });
            }
        };
    
        markMessagesAsRead();

    }, [stompClient]);

    const handleTyping = () => {
        if (!isTyping && stompClient) {
            setIsTyping(true);
            stompClient.send(`/app/typing/${user.id}`, {}, JSON.stringify({ senderId: currentUser.id }));
        }

        clearTimeout(typingTimeout);
        setTypingTimeout(setTimeout(() => setIsTyping(false), 2000));
    };





    
    

    const handleSendMessage = async () => {
        if (!newMessage.trim()) {
            alert('Please write a message before sending.'); 
            return;
        }

        const chatDetailsDto = {
            senderId: currentUser.id,
            recipientId: user.id,
            content: newMessage,
            timestamp: new Date().toISOString(),
            read: false,
        };

        try {
            await createMessage(currentUser.id, user.id, newMessage, new Date().toISOString()); //salvez mesajul in db
            setMessages([...messages, chatDetailsDto]); // adaug mesajul trimis la restul mesajelor(pt userul care trimite)
            setNewMessage('');              // golesc campul de mesaj
        } catch (error) {
            alert('Failed to send message.');
        }
    };









    const handleClick = () => {
        readMessage(currentUser.id, user.id);
    };

    return (
        <div className="chat-header-container" onClick={handleClick}>
            <div className="chat-header">
                <h2>{user.name}</h2>
                <button className="close-chat-btn" onClick={onClose}>
                    X
                </button>
            </div>
            <div className='messages-container'>
            {messages.length > 0 ? (
        messages
        .filter(message => 
            (message.senderId === user.id && message.recipientId === currentUser.id) ||     //afisez in acest chat doar mesajele dintre cei doi useri care au chatul
            (message.recipientId === user.id && message.senderId === currentUser.id))
        .map((message, index) => (
            <div
                key={index}
                className={`message ${message.senderId === currentUser.id ? 'sent' : 'received'}`}     //verific daca mesajul e primit de userul curent sau trimis
            >
                <p>{message.content}</p>
                <span className="timestamp">{new Date(message.timestamp).toLocaleString()}</span>
                {message.senderId === currentUser.id && message.read && (
                    <span className="read-indicator">Read</span>
                )}
            </div>
        ))
    ) : (
        <p>No messages yet. Start the conversation!</p>
    )}
            {userTyping && <p className="typing-indicator">User is typing...</p>}
            </div>

            <div className='message-send'>
                <input  onKeyPress={handleTyping} placeholder="Write your message here..." value={newMessage} onChange={(e) => setNewMessage(e.target.value)} />
                <button type='submit' id='send-b' onClick={handleSendMessage}>Send</button>
            </div>

        </div>

    );
}

export default ChatBox;
