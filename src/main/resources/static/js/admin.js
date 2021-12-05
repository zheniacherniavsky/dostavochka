function addProduct() {
    console.log(123);
}

function loadOrders() {
    const token = window.localStorage.getItem("token");
    const ordersContainer = document.querySelector("#orders");
    ordersContainer.innerHTML = "";
    fetch('/api/v1/admin/orders', {
        headers: {
            "Authorization": `Bearer ${token}`
        }
    }).then(response => response.json())
        .then(orders => {
            if(orders && orders.length) {
                const [ordersInfo, cartsInfo] = orders;
                ordersInfo.forEach(orderInfo => {
                    const products = cartsInfo.filter(cart => cart.delivery.id === orderInfo.id);
                    ordersContainer.appendChild(createOrderComponent(orderInfo, products));
                })
            } else {
                ordersContainer.appendChild((() => {
                    const notFoundText = document.createElement('h2');
                    notFoundText.innerHTML = "No orders...";
                    return notFoundText;
                })())
            }
        });
}

function createOrderComponent(order, cartInfo) {
    let totalPriceValue = 0;

    const orderComponent = document.createElement('div');
    orderComponent.classList.add("order");

    const div = document.createElement("div");
    div.className = 'info';
    div.appendChild((() => {
        const div = document.createElement("div");
        div.appendChild((() => {
            const header = document.createElement("h4");
            header.innerHTML = "order information:";
            return header;
        })())
        div.appendChild((() => {
            const orderInformation = document.createElement("div");
            orderInformation.innerHTML = Object.entries(order).map(([name, value]) => {
                return `${name}: ${value}`;
            }).join("</br>");
            return orderInformation;
        })())

        return div;
    })());

    div.appendChild((() => {
        const div = document.createElement("div");

        div.appendChild((() => {
            const header = document.createElement("h4");
            header.innerHTML = "products information:";
            return header;
        })())
        cartInfo.forEach(cartItem => {
            const {product, quantity} = cartItem;
            div.appendChild((() => {
                const productInformation = document.createElement("div");
                productInformation.className = "product-info";
                const data = Object.entries(product).map(([name, value]) => {
                    if (name === 'price') totalPriceValue += parseFloat(value) * quantity;
                    return `${name}: ${value}`;
                });
                data.push(`quantity: ${quantity}`);
                productInformation.innerHTML = data.join("</br>");
                return productInformation;
            })())
        });

        return div;
    })());

    orderComponent.appendChild(div);
    orderComponent.appendChild((() => {
        const div = document.createElement("div");
        div.className = "total";

        div.appendChild((() => {
            const totalPrice = document.createElement("h4");
            totalPrice.innerHTML = `total price: $${totalPriceValue}`;
            return totalPrice;
        })());
        div.appendChild((() => {
            const acceptButton = document.createElement("button");
            acceptButton.className = 'btn btn-primary';
            acceptButton.innerHTML = "acept order";
            return acceptButton;
        })());

        return div;
    })());

    return orderComponent;
}