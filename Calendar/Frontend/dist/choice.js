function redirectTodayPage() {
    window.location.href = "/today.html";
}

function redirectWeeklyPage() {
    window.location.href = "/weekly.html";
}

function redirectMonthlyPage() {
    window.location.href = "/monthly.html";
}

function redirectAnnualPage() {
    window.location.href = "/google.html";
}

function updateTime() {
    const timeContainer = document.getElementById('time');
    const dateContainer = document.getElementById('date');
    const now = new Date();

    const optionsTime = {
        hour: 'numeric',
        minute: 'numeric',
        second: 'numeric'
    };

    const optionsDate = {
        weekday: 'long',
        year: 'numeric',
        month: 'long',
        day: 'numeric'
    };

    const formattedTime = now.toLocaleTimeString('en-US', optionsTime);
    const formattedDate = now.toLocaleDateString('en-US', optionsDate);

    timeContainer.innerHTML = formattedTime;
    dateContainer.innerHTML = formattedDate;
}

updateTime(); // Actualizăm data și ora inițial

setInterval(updateTime, 1000); // Actualizăm data și ora în timp real
