function signup() {
    const login = document.querySelector('#login').value;
    const password = document.querySelector('#password').value;

    fetch("/api/v1/auth/signup", {
        method: "POST",
        headers: {'Content-Type': 'application/json', 'Accept': 'application/json'},
        body: JSON.stringify({
            login,
            password
        })
    }).then(async (response) => {
        if(response.status === 201) {
            console.log('Account has been created!');
        } else {
            const data = await response.json();
            console.log(data.errors[0].message);
        }
    })
}