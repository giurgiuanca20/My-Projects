function createRectangle(event) {
    const rectContainer = document.getElementById('rectContainer');

    // Remove existing rectangle if any
    while (rectContainer.firstChild) {
        rectContainer.removeChild(rectContainer.firstChild);
    }

    const rectDiv = document.createElement('div');
    rectDiv.classList.add('rectSection');

    const rectContent = document.createElement('div');
    rectContent.classList.add('rectContent');

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
}

function save() {
    const rectangle = document.querySelector('.rectSection'); // Select the single rectangle
    const dataToSend = {};

    if (rectangle) {
        const text = rectangle.querySelector('textarea').value;
        const hour = rectangle.querySelector('input').value;
        dataToSend.text = text;
        dataToSend.hour = hour;
    } else {
        console.error('No rectangle found.');
        return;
    }

    fetch(`http://localhost:8088/api/google/addGoogle`, {
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
