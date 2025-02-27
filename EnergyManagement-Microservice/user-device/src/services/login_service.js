

function setCookie(value){
  try {
    sessionStorage.setItem('sessionUser', value);
  } catch (error) {
    console.error("Error setting session storage:", error);
  }
  }
  
  
  function getCookie() {
    try {
      const user = sessionStorage.getItem('sessionUser');
      return user ? JSON.parse(user) : null;
    } catch (error) {
      console.error("Error parsing user from session storage:", error);
      return null;
    }
  }


  export {
    getCookie,
    setCookie,
  };
