var btnLogin = document.querySelector("#btnLogin");
btnLogin.addEventListener("click", function(e) {
    e.preventDefault();
    
    var form = document.querySelector("#form");
    form.submit();
});

var btnSignup = document.querySelector("#btnSignup");
btnSignup.addEventListener("click", function(e) {
    e.preventDefault();

    window.location.href = "/signup";
});