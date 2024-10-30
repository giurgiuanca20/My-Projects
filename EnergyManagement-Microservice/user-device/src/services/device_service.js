const API_URL = "http://localhost:8081/device";

async function getDevices() {
  const response = await fetch(API_URL+"/findAll",{
    method: "GET",
    // mode: "cors",
    // cache: "no-cache",
    // credentials: "include",
  });
  const json = await response.json();
  return json;
}

async function postDevice(description, address, maxHourlyConsumption) {
    const response = await fetch(API_URL + "/create", {
      method: "POST",
    //   mode: "cors",
    //   cache: "no-cache",
    //   credentials: "include",
      body: JSON.stringify({
        description: description,
        address: address,
        maxHourlyConsumption: maxHourlyConsumption,
      }),
      headers: {
        "Content-type": "application/json; charset=UTF-8",
      },
    });
  
    const text = await response.text();
    return text;
  }


  async function deleteDevice(description,address,maxHourlyConsumption) {
    const response = await fetch(API_URL + "/delete", {
      method: "DELETE",
    //   mode: "cors",
    //   cache: "no-cache",
    //   credentials: "include",
      body: JSON.stringify({
        description: description,
        address:address,
        maxHourlyConsumption:maxHourlyConsumption,
      }),
      headers: {
        "Content-type": "application/json; charset=UTF-8",   //nu cred ca am nevoie de utf
      },
    });
    const text = await response.text();
    return text;
  }
  
  async function updateDevice(description,address,maxHourlyConsumption) {
    const response = await fetch(API_URL + "/update", {
      method: "PUT",
    //   mode: "cors",
    //   cache: "no-cache",
    //   credentials: "include",
      body: JSON.stringify({
        description: description,
        address:address,
        maxHourlyConsumption:maxHourlyConsumption,
      }),
      headers: {
        "Content-type": "application/json; charset=UTF-8",
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

  };