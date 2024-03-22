import { apiClient } from "../api/ApiClient";

export function register({username, password}, {setUsername, setAuthorised, logout}){

    return apiClient.post(`/register`, {username, password})
        .then(response => {
            if(response.status != 200){
                logout();
                return false;
            }
            return login({username, password}, {setUsername, setAuthorised, logout});
        })
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
        });
}