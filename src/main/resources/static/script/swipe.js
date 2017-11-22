var noBtn = document.getElementById("nobtn");
var yesBtn = document.getElementById("yesbtn");
var menuBtn = document.getElementById("menubtn");
var image = document.getElementById("image");
var title = document.getElementById("namn");
var brodtxt = document.getElementById("brodtxt");
var chosenTxt = document.getElementById("chosen");
var klassTxt = document.getElementById("klass");
var audio = new Audio('/sounds/waterdrop.wav');
var canSwipe = true;
var img;
var name;
var age;
var text;
var klass;
var httpRequest;

noBtn.addEventListener("click", swipeNo);
yesBtn.addEventListener("click", swipeYes);
menuBtn.addEventListener("click", menu);

function menu() {
    document.location.href = "/final";
}

function swipeNo() {
    if (canSwipe) {
        canSwipe = false;
        timeoutID = window.setTimeout(swipable, 400);
        httpRequest = new XMLHttpRequest();
        if (!httpRequest) {
            console.log('There was a problem making the request.');
        }
        httpRequest.onreadystatechange = personController;
        httpRequest.open('GET', '/swipeNo', true);
        httpRequest.send(null);
    }
}

function swipeYes() {
    if (canSwipe) {
        audio.play();
        canSwipe = false;
        timeoutID = window.setTimeout(swipable, 400);
        httpRequest = new XMLHttpRequest();
        if (!httpRequest) {
            console.log('There was a problem making the request.');
        }
        httpRequest.onreadystatechange = personController;
        httpRequest.open('GET', '/swipeYes', true);
        httpRequest.send(null);
    }
}

function personController() {
    if (httpRequest.readyState === XMLHttpRequest.DONE) {
        if (httpRequest.status === 200) {
            var response = httpRequest.responseText;
            if (response == "final") {
                document.location.href = "/final";
            } else {
                var json = JSON.parse(response);
                name = json.name;
                age = json.age;
                klass = json.klass;
                text = json.text;
                img = json.img;
                chosen = json.chosen;
                title.innerHTML = name + " " + age;
                image.src = img;
                klassTxt.innerHTML = klass;
                brodtxt.innerHTML = text;
                chosenTxt.innerHTML = chosen;
            }
        } else {
            console.log('There was a problem receiving the request.');
        }
    }
}

function swipable() {
    canSwipe = true;
}
