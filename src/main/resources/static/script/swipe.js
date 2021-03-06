var noBtn = document.getElementById("nobtn");
var yesBtn = document.getElementById("yesbtn");
var countbtn = document.getElementById("countbtn");
var logoutbtn = document.getElementById("logoutbtn");
var image = document.getElementById("image");
var title = document.getElementById("namn");
var brodtxt = document.getElementById("brodtxt");
var chosenTxt = document.getElementById("chosen");
var klassTxt = document.getElementById("klass");
var pref1txt = document.getElementById("pref1txt");
var pref2txt = document.getElementById("pref2txt");
var pref3txt = document.getElementById("pref3txt");
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
countbtn.addEventListener("click", menu);
chosenTxt.addEventListener("click", menu);
logoutbtn.addEventListener("click",logOut);

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
                title.innerHTML = json.name + " " + json.age;
                image.src = json.img;
                klassTxt.innerHTML = json.klass;
                brodtxt.innerHTML = json.text;
                chosenTxt.innerHTML = json.chosen;
                pref1txt.innerHTML=json.preference1;
                pref2txt.innerHTML=json.preference2;
                pref3txt.innerHTML=json.preference3;
            }
        } else {
            console.log('There was a problem receiving the request.');
        }
    }
}

function logOut() {
    document.location.href = "/logout";
}

function swipable() {
    canSwipe = true;
}
