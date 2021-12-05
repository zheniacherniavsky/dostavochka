const BURGER = "burger";
const PIZZA = "pizza";
const SUSHI = "sushi";
const DRINKS = "drinks";

const menu = {};

menu[BURGER] = document.querySelector("#burger");
menu[PIZZA] = document.querySelector("#pizza");
menu[SUSHI] = document.querySelector("#sushi");
menu[DRINKS] = document.querySelector("#drinks");

let selectedMenuOption = BURGER;

function selectBurger() {
    if(selectedMenuOption !== BURGER) {

        menu[selectedMenuOption].classList.remove('selected');
        menu[BURGER].classList.add('selected');
        selectedMenuOption = BURGER;
        loadProducts(BURGER);
        loadCart();
    }
}

function selectPizza() {
    if(selectedMenuOption !== PIZZA) {
        menu[selectedMenuOption].classList.remove('selected');
        menu[PIZZA].classList.add('selected');
        selectedMenuOption = PIZZA;
        loadProducts(PIZZA);
        loadCart();
    }
}

function selectSushi() {
    if(selectedMenuOption !== SUSHI) {
        menu[selectedMenuOption].classList.remove('selected');
        menu[SUSHI].classList.add('selected');
        selectedMenuOption = SUSHI;
        loadProducts(SUSHI);
        loadCart();
    }
}

function selectDrinks() {
    if(selectedMenuOption !== DRINKS) {
        menu[selectedMenuOption].classList.remove('selected');
        menu[DRINKS].classList.add('selected');
        selectedMenuOption = DRINKS;
        loadProducts(DRINKS);
        loadCart();
    }
}