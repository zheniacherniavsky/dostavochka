const STAGE_PLP = "PLP";
const STAGE_CART = "CART";

let totalPrice = 0;

const changeTotalPrice = (number) => {
    totalPrice += number;
    document.querySelector("#cartTotalPrice").innerHTML = totalPrice.toFixed(2);
}

function loadCart(stage) {
    const token = window.localStorage.getItem("token");
    const accountId = window.localStorage.getItem("accountId");
    totalPrice = 0;

    if(token && accountId) {
        fetch(`/api/v1/order/cart?accountId=${accountId}`, {
            headers: {'Authorization': 'Bearer ' + token},
        }).then(async (response) => {
            if(response.status === 403) {
                window.localStorage.clear();
            }
            else {
                const data = await response.json();
                const cartProductsContainer = document.querySelector("#orders");
                if(cartProductsContainer) cartProductsContainer.innerHTML = "";

                if (data && data.length) {
                    document.querySelector("#ordersCount").innerHTML = data.length;
                    data.forEach(order => {
                        const catalogProductId = `product_${order.product.id}`;

                        if(stage === STAGE_PLP) {
                            // disable add to cart button on PLP
                            const productButtonOnPage = document.querySelector(`#products #${catalogProductId} .addToCartButton`);
                            if(productButtonOnPage) {
                                productButtonOnPage.innerHTML = "Added to cart";
                                productButtonOnPage.disabled = true;
                            }
                        }

                        if(stage === STAGE_CART) {
                            cartProductsContainer.appendChild(cartProductComponent(order));
                        }
                    });
                }
            }
        })
    } else {
        document.querySelector("#ordersCount").innerHTML = '0';
    }
}

function cartProductComponent(order) {
    const {product, id: orderId, quantity} = order;
    const productId = `product-cart_${orderId}`;
    let actualPrice = parseFloat(product.price * quantity).toFixed(2);
    changeTotalPrice(+actualPrice);

    const productCartContainer = document.createElement("div");
    productCartContainer.classList.add("product-cart");
    productCartContainer.id = productId;

    const productHeader = document.createElement("h3");
    productHeader.innerHTML = product.displayName;

    const productQuantity = document.createElement("input");
    productQuantity.id = `product-quantity_${orderId}`;
    productQuantity.type = "number";
    productQuantity.value = quantity;
    productQuantity.min = "1";
    productQuantity.onchange = (event) => {
        const {value} = event.target;
        if(parseInt(value) <= 0) {
            document.querySelector(`#product-quantity_${orderId}`).value = quantity;
        } else {
            const token = window.localStorage.getItem("token");
            changeTotalPrice(-actualPrice)
            actualPrice = parseFloat(product.price * value).toFixed(2);
            changeTotalPrice(+actualPrice)
            document.querySelector(`#product-actual-price_${orderId}`).innerHTML = actualPrice;
            fetch(`/api/v1/order/updateQuantity?cartOrderId=${orderId}&quantity=${value}`, {
                method: "PATCH",
                headers: {'Authorization': 'Bearer ' + token},
            })
        }
    }

    const productQuantityContainer = document.createElement("div");
    productQuantityContainer.classList.add('product-quantity-container');
    productQuantityContainer.appendChild((() => {
        const quantityText = document.createElement('p');
        quantityText.innerHTML = "Quantity: "
        return quantityText;
    })())
    productQuantityContainer.appendChild(productQuantity)

    const productPrice = document.createElement("p");
    productPrice.classList.add("price");
    productPrice.innerHTML = product.price;

    const productActualPrice = document.createElement("p");
    productActualPrice.id = `product-actual-price_${orderId}`;
    productActualPrice.classList.add("price");
    productActualPrice.innerHTML = actualPrice;

    const productActualPriceContainer = document.createElement("div");
    productActualPriceContainer.classList.add("product-actual-price-container");
    productActualPriceContainer.appendChild((() => {
        const productActualPriceText = document.createElement("p");
        productActualPriceText.innerHTML = "Actual Price: ";
        return productActualPriceText
    })());
    productActualPriceContainer.appendChild(productActualPrice);

    const removeFromCartButton = document.createElement("button");
    removeFromCartButton.innerHTML = "Remove";
    removeFromCartButton.className="removeFromCartButton btn btn-primary"
    removeFromCartButton.onclick = () => {
        const token = window.localStorage.getItem("token");
        const accountId = window.localStorage.getItem("accountId");
        const errorMessage = document.querySelector(`#cartErrorMessage`);

        if(!token || !accountId) {
            errorMessage.innerHTML = "Something went wrong! Try to reload the page.";
            return;
        } else errorMessage.innerHTML = "";

        fetch(`/api/v1/order/delete?cartOrderId=${orderId}`, {
            method: "DELETE",
            headers: {'Authorization': 'Bearer ' + token},
        }).then(async (response) => {
            if(response.status === 403) {
                errorMessage.innerHTML = "Please Sign In before adding to cart!";
            } else {
                loadCart(STAGE_CART);
                const ordersCount = document.querySelector("#ordersCount");
                ordersCount.innerHTML = parseInt(ordersCount.innerHTML) - 1;
            }
        })
    }

    const div1 = document.createElement('div');
    div1.appendChild(productHeader);
    div1.appendChild(productPrice);

    const div2 = document.createElement('div');
    div2.appendChild(productQuantityContainer);

    const div3 = document.createElement('div');
    div3.appendChild(productActualPriceContainer);
    div3.appendChild(removeFromCartButton);

    div2.appendChild(div3);

    productCartContainer.appendChild(div1);
    productCartContainer.appendChild(div2);

    return productCartContainer;
}