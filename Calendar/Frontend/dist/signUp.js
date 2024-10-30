

function redirectLogIn() {
    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;
    const email = document.getElementById('email').value;

    const dataToSend = {
        username: username,
        password: password,
        email: email
    };

    fetch('http://localhost:8088/api/auth/signUp', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(dataToSend)
    })
        .then(response => {
            console.log('Response:', response); // Log the response object
            if (response.headers.get('content-type')?.includes('application/json')) {
                return response.json().then(data => ({ status: response.status, body: data }));
            } else {
                return response.text().then(text => ({ status: response.status, body: text }));
            }
        })
        .then(({ status, body }) => {

            console.log('Body:', body); // Log the response body
            if (status === 200) {
                window.location.href = "/index.html";
            } else {
                const errorMessage = typeof body === 'string' ? body : body.message;
                alert(`Error ${status}: ${errorMessage || 'An error occurred'}`);
            }
        })
        .catch(error => {
            console.error('Error:', error);
        });
}


