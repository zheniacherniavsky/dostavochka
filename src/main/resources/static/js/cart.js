function loadCart() {
    const token = window.localStorage.getItem("token");
    const accountId = window.localStorage.getItem("accountId");

    if(token && accountId) {
        fetch(`/api/v1/order/cart?accountId=${accountId}`, {
            headers: {'Authorization': 'Bearer ' + token},
        }).then(async (response) => {
            if(response.status === 403) {
                window.localStorage.clear();
            }
            else {
                const data = await response.json();
                if (data && data.length) {
                    document.querySelector("#ordersCount").innerHTML = data.length;
                    data.forEach(order => {
                        const catalogProductId = `product_${order.product.id}`;

                        // disable add to cart button on PLP
                        const productButtonOnPage = document.querySelector(`#products #${catalogProductId} .addToCartButton`);
                        if(productButtonOnPage) {
                            productButtonOnPage.innerHTML = "Added to cart";
                            productButtonOnPage.disabled = true;
                        }
                    });
                }
            }
        })
    }
}