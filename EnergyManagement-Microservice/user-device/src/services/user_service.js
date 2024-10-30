const API_URL = "http://localhost:8080/user";
const AUTH_URL = "http://localhost:8080/auth";

async function getUsers() {
  const response = await fetch(API_URL+"/findAll",{
    method: "GET",
    // mode: "cors",
    // cache: "no-cache",
    // credentials: "include",
  });
  const json = await response.json();
  return json;
}

async function getUserId(username) {
  const response = await fetch(API_URL+"/findId?username=" + encodeURIComponent(username),{
    method: "GET",
    // mode: "cors",
    // cache: "no-cache",
    // credentials: "include",
  });
  const text = await response.text();
  return text;
}


async function deleteUser(username) {
  const response = await fetch(API_URL + "/delete?username=" + encodeURIComponent(username), {
      method: "DELETE",
      headers: {
        "Content-type": "application/json; charset=UTF-8",   //nu cred ca am nevoie de utf
      },
    });

    const text = await response.text();
    return text;
  }
  
  async function updateUser(username, name, email, password, role) {
    const response = await fetch(API_URL + "/update", {
      method: "PUT",
      // mode: "cors",
      // cache: "no-cache",
      // credentials: "include",
      body: JSON.stringify({
        username: username,
        name: name,
        email: email,
        password: password,
        role:role,
      }),
      headers: {
        "Content-type": "application/json; charset=UTF-8",
      },
    });
      
    const text = await response.text();
    return text;
  }
  


async function postUser(username, name,email,password,role) {
    const response = await fetch(AUTH_URL + "/sign_up", {
      method: "POST",
    //   mode: "cors",
    //   cache: "no-cache",
    //   credentials: "include",
      body: JSON.stringify({
        username: username,
        name: name,
        email: email,
        password: password,
        role:role,
      }),
      headers: {
        "Content-type": "application/json; charset=UTF-8",
      },
    });
  
    const text = await response.text();
    return text;
  }


  async function login(username, password) {
    const response = await fetch(AUTH_URL + "/sign_in", {
      method: "POST",
    //   mode: "cors",
    //   cache: "no-cache",
    //   credentials: "include",
      body: JSON.stringify({
        username: username,
        password: password,
      }),
      headers: {
        "Content-type": "application/json; charset=UTF-8",
      },
    });
  
    const json = await response.json();
    return json;
  }


  export {
   
    postUser,
    deleteUser,
    getUsers,
    updateUser,
    getUserId,
    login,

  };