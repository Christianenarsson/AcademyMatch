var noBtn = document.getElementsByTagName("nobtn");
var yesBtn = document.getElementsByTagName("yesbtn");
//var menuBtn = document.getElementById("menubtn");
var image = document.getElementById("image");
var title = document.getElementById("namn");
var brodtxt = document.getElementById("brodtext");
var img;
var name;
var born;
var text;
var httpRequest;

noBtn.addEventListener("click", swipeNo);
yesBtn.addEventListener("click", swipeYes);
//menuBtn.addEventListener("click", menu);

function menu() {

}

function swipeNo() {
    httpRequest = new XMLHttpRequest();
    if (!httpRequest) {
        alert('There was a problem with the request.');
    }
    httpRequest.onreadystatechange = personController;
    httpRequest.open('GET', '/swipeNo');
    httpRequest.send();
}

function swipeYes() {
    httpRequest = new XMLHttpRequest();
    if (!httpRequest) {
        alert('There was a problem with the request.');
    }
    httpRequest.onreadystatechange = personController;
    httpRequest.open('GET', '/swipeYes');
    httpRequest.send();
}

function personController() {
    if (httpRequest.readyState === XMLHttpRequest.DONE) {
        if (httpRequest.status === 200) {
            var pString = httpRequest.responseText;
            var pArray = pString.split(":");
            if (pArray[0] == "final") {
                document.location.href = "/final";
            } else { }
            name = pArray[0];
            born = pArray[1];
            text = pArray[2];
            img = pArray[3];
            title.innerHTML = name + " " + age;
            image.src = img;
            brodtxt.innerHTML = text;
        }
    } else {
        alert('There was a problem with the request.');
    }
