const API_URL = "http://localhost:8081/device";

async function getDevices(userId) {
  const response = await fetch(API_URL+"/findAll/mapping?userId=" + encodeURIComponent(userId),{
    method: "GET",
      headers: {
        "Content-type": "application/json; charset=UTF-8",
      },
  });
  const json = await response.json();
  return json;
}

  export {
   
    getDevices,

  };