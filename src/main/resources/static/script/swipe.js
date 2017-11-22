var noBtn = document.getElementById("nobtn");
var yesBtn = document.getElementById("yesbtn");
var menuBtn = document.getElementById("menubtn");
var image = document.getElementById("image");
var title = document.getElementById("namn");
var brodtxt = document.getElementById("brodtxt");
var chosenTxt = document.getElementById("chosen");
var img;
var name;
var born;
var text;
var httpRequest;

noBtn.addEventListener("click", swipeNo);
yesBtn.addEventListener("click", swipeYes);
menuBtn.addEventListener("click", menu);

function menu() {
    document.location.href = "/final";
}

function swipeNo() {
    httpRequest = new XMLHttpRequest();
    if (!httpRequest) {
        console.log('There was a problem making the request.');
    }
    httpRequest.onreadystatechange = personController;
    httpRequest.open('GET', '/swipeNo', true);
    httpRequest.send(null);
}

function swipeYes() {
    httpRequest = new XMLHttpRequest();
    if (!httpRequest) {
        console.log('There was a problem making the request.');
    }
    httpRequest.onreadystatechange = personController;
    httpRequest.open('GET', '/swipeYes', true);
    httpRequest.send(null);
}

function personController() {
    if (httpRequest.readyState === XMLHttpRequest.DONE) {
        if (httpRequest.status === 200) {
            var pString = httpRequest.responseText;
            var pArray = pString.split(";");
            if (pArray[0] == "final") {
                document.location.href = "/final";
            } else {
                name = pArray[0];
                born = pArray[1];
                text = pArray[2];
                img = pArray[3];
                chosen = pArray[4];
                title.innerHTML = name + " " + born;
                image.src = img;
                brodtxt.innerHTML = text;
                chosenTxt.innerHTML = chosen;
            }
        } else {
            console.log('There was a problem receiving the request.');
        }
    }
}
