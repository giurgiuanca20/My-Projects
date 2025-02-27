import { getCookie } from './../services/login_service';

const user = getCookie();


let API_URL = "http://device1.localhost/device";

function setApi(simulationId) {
  //API_URL = `http://device${simulationId}.localhost/device`;
  API_URL = "http://device1.localhost/device";
}

async function getDevices() {
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

async function postDevice(description, address, maxHourlyConsumption) {
    const response = await fetch(API_URL + "/create", {
      method: "POST",
      body: JSON.stringify({
        description: description,
        address: address,
        maxHourlyConsumption: maxHourlyConsumption,
      }),
      headers: {
        "Authorization": `Bearer ${user.token}`, 
        "Content-type": "application/json",
      },
    });
  
    const text = await response.text();
    return text;
  }


  async function deleteDevice(description,address,maxHourlyConsumption) {
    const response = await fetch(API_URL + "/delete", {
      method: "DELETE",
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
    return text;
  }
  
  async function updateDevice(description,address,maxHourlyConsumption) {
    const response = await fetch(API_URL + "/update", {
      method: "PUT",
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
    return text;
  }




  export {
   
    postDevice,
    getDevices,
    deleteDevice,
    updateDevice,
    setApi,

  };