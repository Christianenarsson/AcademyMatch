var startBtn = document.getElementById("startbtn");
var preferences = document.getElementById("preferences");
var httpRequest;

startBtn.addEventListener("click", start);

function start() {
    var chosenPref = preferences.options[preferences.selectedIndex].value;
    httpRequest = new XMLHttpRequest();
    if (!httpRequest) {
        console.log('There was a problem making the request.');
    }
    httpRequest.onreadystatechange = go;
    httpRequest.open('POST', '/setPref', true);
    httpRequest.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    httpRequest.send("preference=" + encodeURIComponent(chosenPref));
}

function go() {
    if (httpRequest.readyState === XMLHttpRequest.DONE) {
        if (httpRequest.status === 200) {
            document.location.href = "/loading";
        } else {
            console.log('There was a problem receiving the request.');
            start();
        }
    }
}