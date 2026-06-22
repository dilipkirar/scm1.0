console.log("Scripts Loading");
//change theme work
let currentTheme = getTheme();
console.log(currentTheme);
//initial
document.addEventListener("DOMContentLoaded",() => {
    changeTheme();
})

function changeTheme() {
    //set to web page
    changePageTheme(currentTheme, currentTheme);

    //set the listener to change theme button
    const changeThemeButton = document.querySelector('#theme_change_button');

    changeThemeButton.addEventListener("click", (event) => {
        let oldTheme = currentTheme;
        console.log("changed theme button clicked");
        //theme ko light
        if (currentTheme === "dark") {
            currentTheme = "light";
        } else {
            currentTheme = "dark";
        }
        changePageTheme(currentTheme, oldTheme);
    });
}

//set theme to localStorage
function setTheme(theme) {
    localStorage.setItem("theme", theme);
}

//get theme from localStorage
function getTheme() {
    let theme = localStorage.getItem("theme");
    return theme ? theme : "light";
}

function changePageTheme(theme, oldTheme) {
    //local storage main update
    setTheme(theme);
    //remove the current theme
    document.querySelector('html').classList.remove(oldTheme);
    //set the current theme
    document.querySelector('html').classList.add(theme);
    //change the text of button
    document.querySelector("#theme_change_button").querySelector("span").textContent =
        theme == "light" ? "Dark" : "Light";
}

//End of change theme page