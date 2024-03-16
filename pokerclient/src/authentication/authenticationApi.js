

export function register({username, password}, {logout}){

    return apiClient.post(`/register`, {username, password})
        .then(response => {
            if(response.status != 200){
                logout();
                return false;
            }
            return login({username, password});
        }).catch(error => {
            console.log(error);
            logout();
            return false;
        });
}

export function login({username, password}, {setUsername, setAuthorised, logout}){
    return apiClient.post(`/login`, {username, password})
        .then(response => {
            if(response.status != 200){
                logout();
                return false;
            };

            setUsername(username);
            setAuthorised(true);

            return true;
        }).catch(error => {
            logout();
            return false;
        });
}