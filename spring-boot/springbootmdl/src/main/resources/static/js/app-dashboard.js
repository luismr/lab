var lnkLogout = document.querySelector("#lnkLogout");
lnkLogout.addEventListener("click", function(e) {
    e.preventDefault();

    window.location.href = "/logout";
});