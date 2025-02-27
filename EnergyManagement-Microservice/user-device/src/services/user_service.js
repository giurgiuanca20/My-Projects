
import { getCookie } from './../services/login_service';

const user = getCookie();


const API_URL = "http://localhost:8080/user";
const AUTH_URL = "http://localhost:8080/auth";

async function getUsers() {

  if (!user || !user.token) {
    console.error("User not logged in or token not found.");
    return;
  }
  const response = await fetch(API_URL+"/findAll",{
    method: "GET",
    headers: {
      "Authorization": `Bearer ${user.token}`, 
      "Content-type": "application/json",
    },
  });
  const json = await response.json();
  return json;
}

async function getUserId(username) {
   if (!user || !user.token) {
    console.error("User not logged in or token not found.");
    return;
  }
  const response = await fetch(API_URL+"/findId?username=" + encodeURIComponent(username),{
    method: "GET",
    headers: {
      "Authorization": `Bearer ${user.token}`,
      "Content-type": "application/json", 
    },
  });
  const text = await response.text();
  return text;
}


async function deleteUser(username) {
   if (!user || !user.token) {
    console.error("User not logged in or token not found.");
    return;
  }
  const response = await fetch(API_URL + "/delete?username=" + encodeURIComponent(username), {
      method: "DELETE",
      headers: {
        "Authorization": `Bearer ${user.token}`,
        "Content-type": "application/json",
      },
    });

    const text = await response.text();
    return text;
  }
  
  async function updateUser(username, name, email, password, role) {

    if (!user || !user.token) {
      console.error("User not logged in or token not found.");
      return;
    }
    const response = await fetch(API_URL + "/update", {
      method: "PUT",
      body: JSON.stringify({
        username: username,
        name: name,
        email: email,
        password: password,
        role:role,
      }),
      headers: {
        "Authorization": `Bearer ${user.token}`,  
        "Content-type": "application/json",  
      },
    });
      
    const text = await response.text();
    return text;
  }
  


async function postUser(username, name,email,password,role) {

  if (!user || !user.token) {
    console.error("User not logged in or token not found.");
    return;
  }
    const response = await fetch(AUTH_URL + "/sign_up", {
      method: "POST",
      body: JSON.stringify({
        username: username,
        name: name,
        email: email,
        password: password,
        role:role,
      }),
      headers: {
        "Authorization": `Bearer ${user.token}`,  
        "Content-type": "application/json", 
      },
    });
  
    const text = await response.text();
    return text;
  }


  async function login(username, password) {
    const response = await fetch(AUTH_URL + "/sign_in", {
      method: "POST",
      body: JSON.stringify({
        username: username,
        password: password,
      }),
      headers: {
        "Content-type": "application/json",
      },
    });
  
    const json = await response.json();
    return json;
  }



  async function findAdmins() {
  
    if (!user || !user.token) {
      console.error("User not logged in or token not found.");
      return;
    }
    const response = await fetch(API_URL+"/findAllAdmins",{
      method: "GET",
      headers: {
        "Authorization": `Bearer ${user.token}`, 
        "Content-type": "application/json",
      },
    });
    const json = await response.json();
    return json;
  }


  async function findById(id) {
  
    if (!user || !user.token) {
      console.error("User not logged in or token not found.");
      return;
    }
    const response = await fetch(API_URL+`/find?id=${id}`,{
      method: "GET",
      headers: {
        "Authorization": `Bearer ${user.token}`, 
        "Content-type": "application/json",
      },
    });
    const json = await response.json();
    return json;
  }




  export {
    findAdmins,
    postUser,
    deleteUser,
    getUsers,
    updateUser,
    getUserId,
    login,
    findById,

  };