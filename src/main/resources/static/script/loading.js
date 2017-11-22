var timeoutID;
var httpRequest;

window.onload = init;

function init() {
    timeoutID = window.setTimeout(start, 1000);
}

function start() {
    httpRequest = new XMLHttpRequest();
    if (!httpRequest) {
        console.log('There was a problem making the request.');
    }
    httpRequest.onreadystatechange = goSwipe;
    httpRequest.open('GET', '/start', true);
    httpRequest.send(null);
}

function goSwipe() {
    if (httpRequest.readyState === XMLHttpRequest.DONE) {
        if (httpRequest.status === 200) {
            document.location.href = "/swipe";
        } else {
            console.log('There was a problem receiving the request.');
            start();
        }
    }
}