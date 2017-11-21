var timeoutID;
window.onload = init;

function init() {
    timeoutID = window.setTimeout(start, 2000);
}

function start() {
    document.location.href = "/start";
}