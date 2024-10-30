function redirectPage1() {
    window.location.href = "/showWeekly.html";
}
let selectedSquare = null;
let selectedDay = null;
function showInput(day, event) {
    const squares = document.querySelectorAll('.square');
    squares.forEach(square => square.classList.remove('selected'));
    event.target.classList.add('selected');
    selectedSquare = event.target;

    const rectContainer = document.getElementById('rectContainer');
    rectContainer.innerHTML = '';
    selectedDay = day; // Salvează ziua selectată

    showSidebar();
    createRectangle();
}

window.onload = function() {
    const sidebar = document.querySelector('.sideBar');
    sidebar.style.display = 'none'; // Asigură că bara laterală este ascunsă la început
};

function showSidebar() {
    const sidebar = document.querySelector('.sideBar');
    if (selectedDay && sidebar) {
        sidebar.style.display = 'flex'; // Afișează bara laterală doar după ce este selectată o zi
    }
}
function createRectangle(event) {
    const clickedImage = event.target;
    if (clickedImage.tagName === 'IMG') {
        const rectContainer = document.getElementById('rectContainer');
        const rectDiv = document.createElement('div');
        rectDiv.classList.add('rectSection');

        const rectContent = document.createElement('div');
        rectContent.classList.add('rectContent');

        const img = clickedImage.cloneNode(true);
        rectContent.appendChild(img);

        const textArea = document.createElement('textarea');
        textArea.classList.add('textArea');
        textArea.placeholder = 'Scrie ceva...';

        const timeInput = document.createElement('input');
        timeInput.type = 'time';
        timeInput.name = 'timeInterval';
        timeInput.min = '00:00';
        timeInput.max = '24:00';

        rectContent.appendChild(textArea);
        rectContent.appendChild(timeInput);

        rectDiv.appendChild(rectContent);
        rectContainer.appendChild(rectDiv);
        rectDiv.classList.add('new-rectangle');

        // Adaugă un event listener pentru selectarea/deselectarea dreptunghiului
        rectDiv.addEventListener('click', function() {
            rectDiv.classList.toggle('selected');
        });
    }
}
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


function save() {
    const rectangle = document.querySelector('.rectContent');
    const dataToSend = {};

    if (rectangle) {
        const img = rectangle.querySelector('img');
        const imageName = img.alt;

        const text = rectangle.querySelector('textarea').value;
        const time = rectangle.querySelector('input[type="time"]').value;
        const userIdString = getCookie('userId'); // Obtain the value of the userId cookie as a string
        const userId = parseInt(userIdString);
        if (selectedSquare) {
            const squareName = selectedSquare.textContent;


            dataToSend.day = squareName;
            dataToSend.text = text;
            dataToSend.image = imageName;
            dataToSend.hour = time;
            dataToSend.userId = userId;
        }
    }
    // Trimite obiectul `Weekly` către server folosind POST
    fetch('http://localhost:8088/api/weeklys/saveWeekly', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(dataToSend)
    })
        .then(response => {
            if (response.ok) {
                console.log('Data saved successfully.');

            } else {
                console.error('Failed to save data.');
            }
        })
        .catch(error => {
            console.error('Error:', error);
        });
}
