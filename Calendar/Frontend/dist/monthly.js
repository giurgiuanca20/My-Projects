// Obține elementele din document
let squares = document.getElementsByClassName("square");
let currentMonth = document.getElementById("currentMonth");

// Setează numele lunii curente
let date = new Date();
let monthNames = [
    "Ianuarie",
    "Februarie",
    "Martie",
    "Aprilie",
    "Mai",
    "Iunie",
    "Iulie",
    "August",
    "Septembrie",
    "Octombrie",
    "Noiembrie",
    "Decembrie",
];
currentMonth.textContent = monthNames[date.getMonth()] + " " + date.getFullYear();

let squaresData = {};


function getCookie(name) {
    let nameEQ = name + "=";
    let ca = document.cookie.split(';');
    for (let i = 0; i < ca.length; i++) {
        let c = ca[i];
        while (c.charAt(0) === ' ') c = c.substring(1, c.length);
        if (c.indexOf(nameEQ) === 0) {
            return c.substring(nameEQ.length, c.length);
        }
    }
    return null;
}
function sendSquareNumber(number) {
    fetch('http://localhost:8088/api/monthlys/sendNrMonthly', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(number)
    })
        .then(response => {
            if (response.ok) {
                console.log('Data a fost trimisă către server.');
                window.location.href = "/addMonthly.html";
            } else {
                console.error('Eroare la trimiterea datei către server.');
            }
        })
        .catch(error => {
            console.error('Eroare:', error);
        });
}

window.onpageshow = function(event) {
    if (event.persisted) {
        window.location.reload();
    }
};
window.onload = function() {
    const userIdString = getCookie('userId'); // Obtain the value of the userId cookie as a string
    const userId = parseInt(userIdString);
    fetch(`http://localhost:8088/api/colors/getColor?userId=${userId}`)
        .then(response => response.json())
        .then(data => {
            data.forEach(color => {
                let square = document.querySelector(`.square:nth-child(${color.nrDay})`);
                if (square) {
                    square.style.borderTopColor = color.color;
                }
            });
        })
        .catch(error => {
            console.error('Eroare:', error);
        });
}
