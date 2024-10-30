function dayToRow(day) {
    switch (day) {
        case 'Luni':
            return 1;
        case 'Marti':
            return 2;
        case 'Miercuri':
            return 3;
        case 'Joi':
            return 4;
        case 'Vineri':
            return 5;
        case 'Sambata':
            return 6;
        case 'Duminica':
            return 7;
        default:
            return 9;
    }
}

function timeToColumn(hour) {
    const hourMap = {
        '08:00-10:00': 2,
        '10:00-12:00': 3,
        '12:00-14:00': 4,
        '14:00-16:00': 5,
        '16:00-18:00': 6,
        '18:00-20:00': 7,
    };

    const hourToCheck = parseInt(hour.split(':')[0]);

    for (const key in hourMap) {
        if (hourMap.hasOwnProperty(key)) {
            const interval = key.split('-');
            const startHour = parseInt(interval[0].split(':')[0]);
            const endHour = parseInt(interval[1].split(':')[0]);

            if (hourToCheck >= startHour && hourToCheck < endHour) {
                return hourMap[key];
            }
        }
    }

    return 8; // Dacă nu se potrivește în niciun interval, se va plasa în "Altele"
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
function clearTable() {
    const tableRows = document.querySelectorAll('tbody tr');

    tableRows.forEach(row => {
        const cellsInRow = row.querySelectorAll('td');
        for (let i = 1; i < cellsInRow.length; i++) {
            cellsInRow[i].textContent = '';
        }
    });
}

function populateTable(weeklyData) {
    clearTable();
    weeklyData.forEach(item => {
        const row = dayToRow(item.day);
        const column = timeToColumn(item.hour);

        let cell;
        if (column !== 8) {
            cell = document.querySelector(`tbody tr:nth-child(${row}) td:nth-child(${column})`);
        } else {
            cell = document.querySelector(`tbody tr:nth-child(${row}) td:last-child`);
        }

        if (cell) {
            if (cell.textContent.trim() !== '') {
                cell.textContent += ' | ' + item.text + '\n'; // Adăugăm bara ca separator
            } else {
                cell.textContent += item.text + '\n';
            }
        }
    });
}


function fetchWeeklyData() {
        const userId = getCookie('userId'); // Read userId from cookie
        const url = `http://localhost:8088/api/weeklys/getWeekly?userId=${userId}`;
        fetch(url, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json'
            }
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok.');
                }
                return response.json(); // Parse the response as JSON
            })
            .then(data => {
                console.log(data);
                populateTable(data);

            })
            .catch(error => {
                console.error('There was a problem with the fetch operation:', error);
            });

    };

    document.addEventListener('DOMContentLoaded', function () {
        fetchWeeklyData();
    });


    let selectedCells = []; // Array pentru a ține evidența celulelor selectate

    function toggleSelection(cell) {
        if (!selectedCells.includes(cell)) {
            selectedCells.push(cell);
            cell.style.backgroundColor = 'green'; // Selectează celula
        } else {
            selectedCells = selectedCells.filter(selectedCell => selectedCell !== cell);
            cell.style.backgroundColor = ''; // Deselectează celula
        }
    }





function deleteSelectedCells() {
    const dataToDelete = [];
    selectedCells.forEach(cell => {
        const row = cell.parentNode.rowIndex; // Obține indexul rândului și ajustează pentru că începe de la 0
        const day = document.querySelector(`tbody tr:nth-child(${row}) td:first-child`).textContent; // Obține numele zilei

        const column = cell.cellIndex; // Obține indexul coloanei
        const headerCell = document.querySelector(`thead tr th:nth-child(${column + 1})`); // Găsește celula antetului din coloana respectivă
        const headerValue = String(headerCell.textContent); // Obține valoarea antetului

        const text = String(cell.textContent.trim());

        if (text.includes('|')) {
            const parts = text.split('|').map(part => part.trim());
            parts.forEach(part => {
                dataToDelete.push(part);
            });
        } else {
            dataToDelete.push(text);
        }
    });

    console.log(dataToDelete);

    if (dataToDelete.length > 0) {
        // Iterate over each text to delete and make individual delete requests
        dataToDelete.forEach(text => {
            const url = `http://localhost:8088/api/weeklys/deleteWeekly?text=${encodeURIComponent(text)}`;

            fetch(url, {
                method: 'DELETE',
                headers: {
                    'Content-Type': 'application/json',
                }
            })
                .then(response => {
                    if (response.ok) {
                        console.log(`Deleted: ${text}`);
                    } else {
                        throw new Error('Network response was not ok.');
                    }
                })
                .catch(error => {
                    console.error('Error:', error);
                });
        });

        // Remove text content and refresh the page
        selectedCells.forEach(cell => {
            cell.textContent = '';
        });

        selectedCells = [];
    }
}


    document.addEventListener('DOMContentLoaded', function () {
        fetchWeeklyData();

        document.querySelectorAll('td').forEach(cell => {
            cell.addEventListener('click', () => {
                toggleSelection(cell);
            });
        });



    });
