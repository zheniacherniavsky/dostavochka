function addProduct() {
    const token = window.localStorage.getItem("token");
    const displayName = document.getElementById('displayName');
    const category = document.getElementById('category');
    const description = document.getElementById('description');
    const price = document.getElementById('price');
    const image = document.getElementById('image');

    fetch('/api/v1/admin/addProduct', {
        method: 'POST',
        headers: {
            "Authorization": `Bearer ${token}`,
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            displayName: displayName.value,
            category: category.value,
            description: description.value,
            price: parseFloat(price.value),
            image: image.value
        })
    }).then(async (response) => {
        if(response.ok) {
            displayName.value = '';
            category.value = '';
            description.value = '';
            price.value = '';
            image.value = '';

            alert('Product was added!')
        } else {
            const data = await response.json();
            console.error("Errors", data.errors);
            alert('Something went wrong!\nCheck console.')
        }
    })

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
    const token = window.localStorage.getItem("token");
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
                    if (name === 'image' || name === 'description') return null;
                    if (name === 'price') totalPriceValue += parseFloat(value) * quantity;
                    return `${name}: ${value}`;
                }).filter(Boolean);
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
            totalPrice.innerHTML = `total price: $${totalPriceValue.toFixed(2)}`;
            return totalPrice;
        })());
        div.appendChild((() => {
            const acceptButton = document.createElement("button");
            acceptButton.className = 'btn btn-primary';
            acceptButton.innerHTML = "acept order";
            acceptButton.onclick = () => {
                fetch(`/api/v1/admin/acceptOrder?orderId=${order.id}`, {
                    method: 'PATCH',
                    headers: {
                        "Authorization": `Bearer ${token}`
                    }
                }).then(response => {
                    if(response.ok) {
                        loadOrders()
                    }
                })
            }
            return acceptButton;
        })());

        return div;
    })());

    return orderComponent;
}