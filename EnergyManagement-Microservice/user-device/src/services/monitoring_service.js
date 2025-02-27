import { getCookie } from './../services/login_service';

const user = getCookie();

const API_URL = "http://localhost:8088/monitoring";

async function startSim(simulationId) {
  const response = await fetch(`http://device1.localhost/device/start?simulareId=${simulationId}`,{
    method: "GET",
    headers: {
      "Authorization": `Bearer ${user.token}`, 
      "Content-type": "application/json",
    },
  });
  const json = await response.json();
  console.log("json",json)
  return json;
}

async function chart(deviceId,formattedDate) {

  const response = await fetch(`${API_URL}/chart?deviceId=${deviceId}&date=${formattedDate}`, {
      method: "GET",
      headers: {
        "Authorization": `Bearer ${user.token}`, 
        "Content-type": "application/json",
      },
  });

  const data = await response.json();

  if (!Array.isArray(data)) {
      console.error("Chart API did not return an array:", data);
      return [];
  }

  return data;
}


async function setMaxConsumption(maxConsumption) {
   await fetch(API_URL + `/maxConsumption?maxConsumption=${maxConsumption}`, {
    method: "POST",
    headers: {
      "Authorization": `Bearer ${user.token}`, 
      "Content-type": "application/json",
    },
  });
}


  export {
   
    startSim,
    chart,
    setMaxConsumption,

  };