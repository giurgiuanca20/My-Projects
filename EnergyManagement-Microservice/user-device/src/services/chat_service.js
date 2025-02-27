import { getCookie } from './../services/login_service';

let API_URL = "http://localhost:8082/chat";


const user = getCookie();

const getMessagesBetweenUsers = async (userId1, userId2) => {
console.log(user.token)
  const response = await fetch(`${API_URL}/messagesBetweenUsers?userId1=${userId1}&userId2=${userId2}`, {
    method: 'GET',
    headers: {
      "Authorization": `Bearer ${user.token}`,
      "Content-type": "application/json",
    },
  });
  
  return await response.json();

}

const getUsersForAdmin = async (adminId) => {

  const response = await fetch(`${API_URL}/usersForAdmin?adminId=${adminId}`, {
    method: 'GET',
    headers: {
      "Authorization": `Bearer ${user.token}`,
      "Content-type": "application/json",
    },
  });
  return await response.json();

}

const createMessage = async (senderId, recipientId, content, timestamp) => {
  try {
    const response = await fetch(`${API_URL}/create`, {
      method: 'POST',
      body: JSON.stringify({
        senderId: senderId,
        recipientId: recipientId,
        content: content,
        timestamp: timestamp,
        read:false
      }),
      headers: {
        "Authorization": `Bearer ${user.token}`,
        "Content-type": "application/json",
      },
    });

    if (!response.ok) {
      throw new Error('Failed to create message');
    }
    return await response.text();
  } catch (error) {
    console.error('Error creating message:', error);
    throw error;
  }
}




const readMessage = async (senderId, recipientId) => {
  try {
    const response = await fetch(`${API_URL}/read`, {
      method: 'POST',
      body: JSON.stringify({
        senderId: senderId,
        recipientId: recipientId
      }),
      headers: {
        "Authorization": `Bearer ${user.token}`,
        "Content-type": "application/json",
      },
    });

    if (!response.ok) {
      throw new Error('Failed to read message');
    }
    return await response.text();
  } catch (error) {
    console.error('Error creating message:', error);
    throw error;
  }
}




export {
  getMessagesBetweenUsers,
  createMessage,
  getUsersForAdmin,
  readMessage,
};