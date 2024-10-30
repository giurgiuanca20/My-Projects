const API_URL = "http://localhost:8081/device";

async function createMapping(userId,description) {
  const response = await fetch(API_URL+"/create/mapping",{
    method: "POST",
    body: JSON.stringify({
      userId: userId,
      description: description,
    }),
      headers: {
        "Content-type": "application/json; charset=UTF-8",
      },
  });
  
  const text = await response.text();
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
        "Content-type": "application/json; charset=UTF-8",
      },
  });
  
  const text = await response.text();
  return text;
}

  export {
   
    createMapping,
    deleteMapping,
  };