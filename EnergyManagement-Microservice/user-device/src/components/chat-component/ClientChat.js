import '../../styles/ChatBox.css';
import React, { useState, useEffect } from 'react';
import { findAdmins } from '../../services/user_service';
import ChatBox from './ChatBox';

function ClientChat() {
  const [admins, setAdmins] = useState([]);
  const [selectedAdmin, setSelectedAdmin] = useState('');
  const [showChatBox, setShowChatBox] = useState(false);

  useEffect(() => {
    const fetchAdmins = async () => {
      try {
        const data = await findAdmins();
        setAdmins(data);
      } catch (error) {
        console.error('Error fetching admins:', error);
      }
    };

    fetchAdmins();
  }, []);

  const handleAdminChange = (e) => {
    setSelectedAdmin(e.target.value);
  };

  const startChat = () => {
    if (selectedAdmin && selectedAdmin !== '--choose--') {
      setShowChatBox(true);
    } else {
      alert('Please select an admin to chat with.');
    }
  };

  const getSelectedAdmin = () => {
    // Comparăm tipurile corect
    const admin = admins.find((admin) => String(admin.id) === selectedAdmin);
    return admin;
  };


  return (
    <div>
      <h2>Chat with:</h2>
      <select id="adminSelector" value={selectedAdmin} onChange={handleAdminChange}>
        <option value="--choose--">--choose--</option>
        {admins.map((admin, index) => (
          <option key={index} value={String(admin.id)}>
            {admin.name}
          </option>
        ))}
      </select>
      <button type="submit" onClick={startChat}>Start to chat</button>
<div className='chat-box-client'>
      {showChatBox && (
        <ChatBox user={getSelectedAdmin()} onClose={() => setShowChatBox(false)}/>

      )}
      </div>
    </div>
  );
}

export default ClientChat;

