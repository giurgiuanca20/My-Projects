import { getCookie } from './../services/login_service';

const user = getCookie();

const API_URL = "http://device1.localhost/device";

async function createMapping(userId,description) {
  const response = await fetch(API_URL+"/create/mapping",{
    method: "POST",
    body: JSON.stringify({
      userId: userId,
      description: description,
    }),
    headers: {
      "Authorization": `Bearer ${user.token}`, 
      "Content-type": "application/json",
    },
  });
  
  const text = await response.text();
  console.log(text);
  return text;
}
async function deleteMapping(userId,description) {
  const response = await fetch(API_URL+"/delete/mapping",{
    method: "PATCH",
    body: JSON.stringify({
      userId: userId,
      description: description,
    }),
    headers: {
      "Authorization": `Bearer ${user.token}`, 
      "Content-type": "application/json",
    },
  });
  
  const text = await response.text();
  return text;
}


async function existsByIdAndUserId(id, userId) {
  try {
    const response = await fetch(API_URL + `/findByDeviceAndUser?id=${id}&userId=${userId}`, {
      method: "GET",
      headers: {
        "Authorization": `Bearer ${user.token}`, 
        "Content-type": "application/json",
      },
    });

    if (!response.ok) {
      throw new Error(`HTTP error! status: ${response.status}`);
    }

    const result = response.json();
    return result;
  } catch (error) {
    console.error('Error in existsByIdAndUserId:', error);
    throw error;
  }
}


  export {
    existsByIdAndUserId,
    createMapping,
    deleteMapping,
  };