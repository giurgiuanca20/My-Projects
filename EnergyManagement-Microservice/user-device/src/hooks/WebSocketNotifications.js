import '../styles/SimulatorComponet.css';
import React, { useEffect, useState } from 'react';
import SockJS from 'sockjs-client';
import Stomp from 'stompjs';

function WebSocketNotifications({selectedDevice}) {
  const [messages, setMessages] = useState([]);
  const [stompClient, setStompClient] = useState(null);

   useEffect(() => {  
      const socket = new SockJS('http://localhost:8088/socket');
      const client = Stomp.over(socket);
  
      client.connect({}, () => {
        setStompClient(client); 
        client.subscribe(`/topic/notifications/${selectedDevice}`, (message) => {
          const receivedMessage = message.body;
          const numberMatch = receivedMessage.match(/(\d+)$/);
        const number = numberMatch ? parseInt(numberMatch[1], 10) : null;

        if (number==selectedDevice) {

            setMessages((prevMessages) => {
              if (prevMessages[receivedMessage]) {
                return {
                  ...prevMessages,
                  [receivedMessage]: prevMessages[receivedMessage] + 1,
                };
              } else {
                return { ...prevMessages, [receivedMessage]: 1 };
              }
            });
          }
        });
      }, []);

   }, []);

  return (

      <div className='simulator-componet'>
        <h2>Mesaje primite prin WebSocket:</h2>
        {Object.entries(messages).map(([msg, count], index) => (
          <div key={index}>
            {msg} {count > 1 && <span className="badge">{count}</span>}
          </div>
        ))}
      </div>

  );
}

export default WebSocketNotifications;
