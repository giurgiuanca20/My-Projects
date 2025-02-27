import '../../styles/Dashbord.css';
import React, { useState, useEffect } from 'react';
import { getCookie } from '../../services/login_service';
import { getUsersForAdmin } from '../../services/chat_service';
import { findById } from '../../services/user_service';
import ChatBox from './ChatBox';

function Dashbord() {
    const [userIds, setUserIds] = useState([]);
    const [userDetails, setUserDetails] = useState([]);
    const [activeChats, setActiveChats] = useState([]);
    const currentUser = getCookie();

    useEffect(() => {
        const fetchUsers = async () => {
            try {
                const fetchedUserIds = await getUsersForAdmin(currentUser.id);
                setUserIds(fetchedUserIds);

                // Obține detaliile utilizatorilor secvențial
                const userNames = [];
                for (const id of fetchedUserIds) {
                    const user = await findById(id);
                    userNames.push({ id, name: user.name });
                }
                setUserDetails(userNames);
            } catch (error) {
                console.error('Error fetching users:', error);
            }
        };

        fetchUsers();
    }, [currentUser.id]);

    const openChat = (user) => {
        if (!activeChats.find((chat) => chat.id === user.id)) {
            setActiveChats([...activeChats, user]);
        }
    };

    const closeChat = (userId) => {
        setActiveChats(activeChats.filter((chat) => chat.id !== userId));
    };

    return (
        <div className='dashbord'>
            <h2>Dashboard</h2>
            <div className='user-list'>
                <h3>Users</h3>
                <ul>
                    {userDetails.map((user) => (
                        <li key={user.id} onClick={() => openChat(user)} style={{ cursor: 'pointer' }}>
                            {user.name}
                        </li>
                    ))}
                </ul>
            </div>
            <div className='chat-box-admin'>
                {activeChats.map((chat) => (
                    <ChatBox
                        key={chat.id}
                        user={chat}
                        onClose={() => closeChat(chat.id)}
                    />
                ))}
            </div>
        </div>
    );
}

export default Dashbord;
