var countBtn = document.getElementById("countbtn");
var backBtn = document.getElementById("backbtn");

countBtn.addEventListener("click", menu);
backBtn.addEventListener("click", menu);

function menu() {
    document.location.href = "/final";
}