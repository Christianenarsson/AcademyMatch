window.addEventListener('keypress', function (e) {
    var key = e.which || e.keyCode;
    if (key === 32) { 
        document.location.href = "/";
    }
});