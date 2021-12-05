const productsContainer = document.querySelector("#products");

function loadProducts(category = "burger") {
    fetch(`/api/v1/product/list?category=${category}`)
        .then(response => response.json())
        .then(data => {
            productsContainer.innerHTML = '';
            const products = Object.values(data);
            if(products.length > 0) {
                products.forEach(product => {
                    productsContainer.appendChild(productComponent(product));
                })
            }
        });
}

function productComponent(product) {
    const productId = `product_${product.id}`;

    const productContainer = document.createElement("div");
    productContainer.classList.add("product");
    productContainer.id = productId;

    const productImg = document.createElement("img");
    productImg.src = product.image;

    const productHeader = document.createElement("h3");
    productHeader.innerHTML = product.displayName;

    const productDescription = document.createElement("p");
    productDescription.classList.add("description");
    if(product.description) {
        productDescription.innerHTML = product.description.length < 200 ? product.description : product.description.slice(0, 200)+'...';
    }

    const productPrice = document.createElement("p");
    productPrice.classList.add("price");
    productPrice.innerHTML = product.price;

    const addToCartButton = document.createElement("button");
    addToCartButton.innerHTML = "Add to Cart";
    addToCartButton.className="addToCartButton btn btn-success"
    addToCartButton.onclick = () => {
        const token = window.localStorage.getItem("token");
        const accountId = window.localStorage.getItem("accountId");
        const errorMessage = document.querySelector(`#${productId} .errorMessage`);

        if(!token || !accountId) {
            errorMessage.innerHTML = "Please Sign In before adding to cart!";
            return;
        } else errorMessage.innerHTML = "";

        fetch("/api/v1/order/add", {
            method: "POST",
            headers: {'Content-Type': 'application/json', 'Accept': 'application/json', 'Authorization': 'Bearer ' + token},
            body: JSON.stringify({
                quantity: 1,
                accountId,
                productId: product.id
            })
        }).then(async (response) => {
            if(response.status === 403) {
                errorMessage.innerHTML = "Please Sign In before adding to cart!";
            } else {
                const addToCartButton = document.querySelector(`#${productId} .addToCartButton`);
                addToCartButton.innerHTML = "Added to cart";
                addToCartButton.disabled = true;
                const ordersCount = document.querySelector("#ordersCount");
                ordersCount.innerHTML = parseInt(ordersCount.innerHTML) + 1;
            }
        })
    }

    const addToCartAndPriceContainer = document.createElement("div");
    addToCartAndPriceContainer.classList.add("addToCart");
    addToCartAndPriceContainer.appendChild(addToCartButton);
    addToCartAndPriceContainer.appendChild(productPrice);

    const errorMessage = document.createElement("p");
    errorMessage.classList.add("errorMessage");

    productContainer.appendChild(productImg);
    productContainer.appendChild(productHeader);
    productContainer.appendChild(productDescription);
    productContainer.appendChild(addToCartAndPriceContainer);
    productContainer.appendChild(errorMessage);

    return productContainer;
}