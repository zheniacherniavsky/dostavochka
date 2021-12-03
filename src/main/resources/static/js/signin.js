async function signin() {
    const login = document.querySelector('#login').value;
    const password = document.querySelector('#password').value;
    const resultMessage = document.querySelector('#resultMessage');

    const response = await fetch("/api/v1/auth/signin",
    {
        method: 'POST',
        headers: {'Content-Type': 'application/json', 'Accept': 'application/json'},
        body: JSON.stringify({
            login,
            password
        })
    });

    const data = await response.json();

    if(response.status === 200 && data.token) {
        window.localStorage.setItem("token", data.token);
        resultMessage.innerHTML = "";
    } else {
        resultMessage.className = "errorMessage";
        resultMessage.innerHTML = "Wrong credentials!";
    }
}