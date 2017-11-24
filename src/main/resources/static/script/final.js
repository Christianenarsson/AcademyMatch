var backBtn = document.getElementById("backbtn");
var sendBtn = document.getElementById("sendbtn");
var txt = document.getElementById("txt");
var namesElement = document.getElementById("names");
var httpRequest;

backBtn.addEventListener("click", back);
sendBtn.addEventListener("click", send);

window.onload = init;

function back() {
    document.location.href = "/swipe";
}

function send() {
    txt.innerHTML = "Sent";
}

function init() {
    httpRequest = new XMLHttpRequest();
    if (!httpRequest) {
        console.log('There was a problem making the request.');
    }
    httpRequest.onreadystatechange = listController;
    httpRequest.open('GET', '/finalGetList', true);
    httpRequest.send(null);
}

function listController() {
    if (httpRequest.readyState === XMLHttpRequest.DONE) {
        if (httpRequest.status === 200) {
            var response = httpRequest.responseText;
            var rArray = response.split(":");
            for (var sass in response) {
                sArray = sass.split(";");
                var a = document.createElement('a');
                a.setAttribute("href", sArray[1]);
                a.innerHTML = sArray[0];
                var button = document.createElement("button");
                button.innerHTML = 'X';
                namesElement.appendChild(a);
                namesElement.appendChild(button);
            }
        }
    } else {
        console.log('There was a problem receiving the request.');
    }
}
}