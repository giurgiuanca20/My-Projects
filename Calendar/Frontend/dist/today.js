
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
    const newRectangles = document.querySelectorAll('.new-rectangle');
    const dataToSend = [];

    newRectangles.forEach(rectangle => {
        const img = rectangle.querySelector('img');
        const imageName = img.alt;
        const text = rectangle.querySelector('textarea').value;
        const time = rectangle.querySelector('input').value;
        const userIdString = getCookie('userId'); // Obtain the value of the userId cookie as a string
        const userId = parseInt(userIdString);
        console.log(userId);
        dataToSend.push({ image: imageName, text: text, hour: time, userId:userId});
    });

    fetch('http://localhost:8088/api/todays/saveToday', {
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

document.addEventListener("DOMContentLoaded", () => {
    // IIFE to fetch and display today's data
    (function displayTodayData() {
        const nrDay = getDayOfMonth();
        const dayOfWeek = getDayOfWeek();
        const userId = getCookie('userId'); // Read userId from cookie
        const url = `http://localhost:8088/api/todays/getToday?userId=${userId}&dayOfWeek=${dayOfWeek}&nrDay=${nrDay}`;
        console.log(url);
        fetch(url, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json'
            }
        })
            .then(response => response.json())
            .then(data => {
                const rectContainer = document.getElementById('rectContainer');
                rectContainer.innerHTML = ''; // Clear previous content

                data.forEach(item => {
                    createRectangleFromData(item);
                });
            })
            .catch(error => {
                console.error('Error:', error);
            });
    })();

    function createRectangleFromData(item) {
        const rectContainer = document.getElementById('rectContainer');
        const rectDiv = document.createElement('div');
        rectDiv.classList.add('rectSection');

        const rectContent = document.createElement('div');
        rectContent.classList.add('rectContent');

        // Find the image by the corresponding name
        const img = document.querySelector(`.sideBar img[alt="${item.image}"]`);
        if (img) {
            const clonedImg = img.cloneNode(true);
            rectContent.appendChild(clonedImg);
        } else {
            // Replace with a default image or something else if the image is not found
            const defaultImg = document.createElement('img');
            defaultImg.src = 'path/to/default-image.jpg';
            rectContent.appendChild(defaultImg);
        }

        const textArea = document.createElement('textarea');
        textArea.classList.add('textArea');
        textArea.value = item.text;

        const timeInput = document.createElement('input');
        timeInput.type = 'time';
        timeInput.name = 'timeInterval';
        timeInput.value = item.hour;

        rectContent.appendChild(textArea);
        rectContent.appendChild(timeInput);

        rectDiv.appendChild(rectContent);
        rectContainer.appendChild(rectDiv);

        // Add an event listener for selecting/deselecting the rectangle
        rectDiv.addEventListener('click', function() {
            rectDiv.classList.toggle('selected');
        });
    }
});





function getDayOfWeek() {
    const days = ['Duminică', 'Luni', 'Marți', 'Miercuri', 'Joi', 'Vineri', 'Sâmbătă'];
    const currentDate = new Date();
    const dayOfWeek = days[currentDate.getDay()];
    return dayOfWeek;
}

function getDayOfMonth() {
    const date = new Date();
    return date.getDate(); // Returnează ziua din lună
}





function deleteSelectedRectangles() {
   const selectedRectangles = document.querySelectorAll('.rectSection.selected');
    selectedRectangles.forEach(rectangle => {
        const text = rectangle.querySelector('textarea').value;
        const url = `http://localhost:8088/api/todays/deleteToday?text=${text}`;
        const dataToDelete = [{ text: text }];

        // Trimite datele către controller pentru ștergere
        fetch(url, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(dataToDelete)
        })
            .then(response => {
                if (response.ok) {
                    console.log('Datele au fost șterse cu succes.');
                    // Elimină dreptunghiul din interfață după ștergere
                    rectangle.remove();
                } else {
                    console.error('Eroare la ștergerea datelor.');
                }
            })
            .catch(error => {
                console.error('Eroare:', error);
            });
    });
}

