import { getCookie } from './../services/login_service';

const user = getCookie();


let API_URL = "http://device1.localhost/device";

function setApiUrl(simulationId) {
 // API_URL = `http://device${simulationId}.localhost/device`;
 API_URL = "http://device1.localhost/device";
}

async function getDevices(userId) {
  const response = await fetch(API_URL+"/findAll/mapping?userId=" + userId,{
    method: "GET",
    headers: {
      "Authorization": `Bearer ${user.token}`, 
      "Content-type": "application/json",
    },
  });
  const json = await response.json();
  return json;
}

async function findId(simulationId,description,address,maxHourlyConsumption) {
  //const response = await fetch(`http://device${simulationId}.localhost/device/findId?simulareId=${simulationId}`, {
    const response = await fetch(`http://localhost:8081/device/findId?simulareId=${simulationId}`, {  
  method: "POST",
    body: JSON.stringify({
      description: description,
      address:address,
      maxHourlyConsumption:maxHourlyConsumption,
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





  export {
    setApiUrl,
    getDevices,
    findId,

  };