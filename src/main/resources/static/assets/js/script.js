
// change classname on mouse hover
function changeClassName(element, oldClass, newClass) {
    if (element.className == oldClass) {
        element.className = newClass;
    } else {
        element.className = oldClass;
    }
}


// delete message after 5 seconds
function functionToDisappearInnerHTML(elementPos) {
    setTimeout(() => {
        document.querySelectorAll(elementPos).forEach(function (el) {
            el.innerHTML = ''; //Clears the innerHTML
        });
    }, 5000);
}
functionToDisappearInnerHTML('.message');

// check if password and retype match
function checkPasswordMatch(confirmPass) {
    var password = document.getElementById("password");
    var submit = document.getElementById("submit");
    if (confirmPass.value != password.value) {
        confirmPass.setCustomValidity("Passwords do not match.");
        submit.disabled = true;
    } else {
        confirmPass.setCustomValidity('');
        submit.disabled = false;
    }
}

const ShowPasswordToggle = document.querySelector("[type='password']");
ShowPasswordToggle.onclick = function () {
    document.querySelector("[type='password']").classList.add("input-password");
    document.getElementById("toggle-password").classList.remove("d-none");
    const passwordInput = document.querySelector("[type='password']");
    const togglePasswordButton = document.getElementById("toggle-password");
    togglePasswordButton.addEventListener("click", togglePassword);
    function togglePassword() {
        if (passwordInput.type === "password") {
            passwordInput.type = "text";
            togglePasswordButton.setAttribute("aria-label", "Hide password.");
        } else {
            passwordInput.type = "password";
            togglePasswordButton.setAttribute("aria-label", "Show password as plain text. " + "Warning: this will display your password on the screen.");
        }
    }
};

// dark mode
const classToggle = (el, ...args) => args.map(e => el.classList.toggle(e));
function toggleDarkmode() {
    if (document.readyState === 'complete') {
        document.body.classList.toggle("dark-mode");
        document.querySelectorAll("thead").forEach((e) => e.classList.toggle("dark-mode"));
        document.querySelectorAll("tbody").forEach((e) => e.classList.toggle("dark-mode"));
        document.querySelectorAll("a").forEach((e) => e.classList.toggle("dark-mode"));
        document.querySelectorAll("button").forEach((e) => e.classList.toggle("dark-mode"));
        // document.querySelectorAll(".navbar").forEach((e) => e.classList.toggle("dark-mode"));
        document.querySelectorAll(".form-control").forEach((e) => e.classList.toggle("dark-mode"));
        document.querySelectorAll(".card").forEach((e) => e.classList.toggle("dark-mode"));
        document.querySelectorAll(".bg-light").forEach((e) => e.classList.toggle("dark-mode"));
        classToggle(document.querySelector("footer"), "bg-light", "bg-dark");
        classToggle(document.querySelector("#dark-mode-icon"), "fa-moon", "fa-sun");

    }
}

// Example starter JavaScript for disabling form submissions if there are invalid fields
(() => {
    'use strict';

    // Fetch all the forms we want to apply custom Bootstrap validation styles to
    const forms = document.querySelectorAll('.needs-validation');

    // Loop over them and prevent submission
    Array.prototype.slice.call(forms).forEach((form) => {
        form.addEventListener('submit', (event) => {
            if (!form.checkValidity()) {
                event.preventDefault();
                event.stopPropagation();
            }
            form.classList.add('was-validated');
        }, false);
    });
})();