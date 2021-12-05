function loadAccount() {
    const token = window.localStorage.getItem("token");
    const accountId = window.localStorage.getItem("accountId");

    if(token && accountId) {
        fetch(`/api/v1/buyer/info?accountId=${accountId}`, {
            headers: {'Authorization': 'Bearer ' + token},
        }).then(response => {
            if(response.status === 403) {
                window.localStorage.clear();
            }
        })
    }
}