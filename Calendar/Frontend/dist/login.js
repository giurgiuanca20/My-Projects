document.addEventListener('DOMContentLoaded', function () {
    const signInButton = document.getElementById('signInButton');
    const signUpButton = document.getElementById('signUpButton');
    const usernameTag = document.querySelector('input[name="username"]');
    const passwordTag = document.querySelector('input[name="password"]');

    // Funcție pentru setarea cookie-urilor
    function setCookie(name, value, days) {
        let expires = "";
        if (days) {
            const date = new Date();
            date.setTime(date.getTime() + (days * 24 * 60 * 60 * 1000));
            expires = "; expires=" + date.toUTCString();
        }
        document.cookie = name + "=" + (value || "") + expires + "; path=/";
    }

    signInButton.addEventListener('click', function () {
        fetch('http://localhost:8088/api/auth/signIn', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ username: usernameTag.value, password: passwordTag.value })
        })
            .then(response => {
                if (response.ok) {
                    return response.json();
                }
                throw new Error('Network response was not ok.');
            })
            .then(data => {
                console.log(data);
                if (data) {

                    setCookie('userId', data.id, 7);
                    window.location.href = "/choice.html";
                } else {
                    alert("Contul nu este valid");
                }
            })
            .catch(error => {
                console.error('Eroare:', error);
            });
    });

    signUpButton.addEventListener('click', function () {
        window.location.href = "signUp.html";
    });
});
