var backBtn = document.getElementById("backbtn");
var sendBtn = document.getElementById("sendbtn");
var txt = document.getElementById("txt");
var namesElement = document.getElementById("names");
var buttons = document.getElementsByClassName("removeBtn");
var httpRequest;
var rArray;

backBtn.addEventListener("click", back);
sendBtn.addEventListener("click", send);
if (document.addEventListener) {
    document.addEventListener("click", handleClick, false);
}
else if (document.attachEvent) {
    document.attachEvent("onclick", handleClick);
}

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
            if (response != "empty") {
                console.log(response)
                rArray = response.split(";");
                for (var i = 0; i < rArray.length; i++) {
                    sArray = rArray[i].split(",");
                    var s = document.createElement("div");
                    var a = document.createElement('a');
                    a.setAttribute("href", sArray[1]);
                    a.innerHTML = sArray[0];
                    var button = document.createElement("button");
                    button.innerHTML = 'X';
                    button.classList.add("removeBtn");
                    button.id = i;
                    s.appendChild(a);
                    s.appendChild(button);
                    namesElement.appendChild(s);
                }
            }
        } else {
            console.log('There was a problem receiving the request.');
        }
    }
}

function handleClick(event) {
    event = event || window.event;
    event.target = event.target || event.srcElement;

    var element = event.target;
    while (element) {
        if (element.nodeName === "BUTTON") {
            if (element == sendBtn) {
                send();
            } else if (element == backBtn) {
                back();
            } else {
            }
            remove(element);
        }
        break;
    }

    element = element.parentNode;
}


function remove(button) {
    var r = rArray[button.id].split(",");
    var n = r[0];
    document.location.href = "/finalRemove?name=" + n;
}