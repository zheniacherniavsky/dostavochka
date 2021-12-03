function loadProducts() {
    fetch("/api/v1/product/list")
        .then(response => response.json())
        .then(data => console.log(data));
}