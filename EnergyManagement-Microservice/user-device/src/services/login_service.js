

function setCookie(value){
    document.cookie=`cookie=${value}`
  }
  
  
  function getCookie() {
    try {
      const userAux = document.cookie.split('; ').find(cookie => cookie.startsWith('cookie='));
      if (userAux) {
        const parsedUser = JSON.parse(decodeURIComponent(userAux.split('=')[1]));
        return parsedUser;
      } else {
        return null;
      }
    } catch (error) {
      console.error("Error parsing user from cookies:", error);
      return null;
    }
  }


  export {
   
    getCookie,
    setCookie,
  };