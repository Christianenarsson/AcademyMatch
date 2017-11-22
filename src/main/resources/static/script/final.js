var backBtn = document.getElementById("backbtn");
var sendBtn = document.getElementById("sendbtn");
var txt = document.getElementById("txt");

backBtn.addEventListener("click", back);
sendBtn.addEventListener("click", send);

function back() {
    document.location.href = "/swipe";
}

function send() {
    txt.innerHTML="Sent";
}