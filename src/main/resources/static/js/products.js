const productsContainer = document.querySelector("#products");

function loadProducts() {
    fetch("/api/v1/product/list")
        .then(response => response.json())
        .then(data => {
            const products = Object.values(data);
            if(products.length > 0) {
                products.forEach(product => {
                    productsContainer.appendChild(productComponent(product));
                })
            }
        });
}

function productComponent(product) {
    const productContainer = document.createElement("div");
    productContainer.classList.add("product");

    const productImg = document.createElement("img");
    productImg.src = product.image;

    const productHeader = document.createElement("h3");
    productHeader.innerHTML = product.displayName;

    const productDescription = document.createElement("p");
    productDescription.innerHTML = product.description;

    const productPrice = document.createElement("p");
    productPrice.innerHTML = product.price;

    const addToCartButton = document.createElement("button");
    addToCartButton.innerHTML = "Add to Cart";
    addToCartButton.onclick = () => {
        const token = window.localStorage.getItem("token");
        const accountId = window.localStorage.getItem("accountId");

        fetch("/api/v1/order/add", {
            method: "POST",
            headers: {'Content-Type': 'application/json', 'Accept': 'application/json'},
            body: JSON.stringify({
                quantity: 1,
                accountId,
                productId: product.id
            })
        })
    }

    productContainer.appendChild(productImg);
    productContainer.appendChild(productHeader);
    productContainer.appendChild(productDescription);
    productContainer.appendChild(productPrice);
    productContainer.appendChild(addToCartButton);

    return productContainer;
}