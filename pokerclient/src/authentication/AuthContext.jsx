import { createContext, useContext, useState } from "react";
import { login, register } from "./authenticationApi";

const AuthContext = createContext();

export const useAuth = () => useContext(AuthContext);

export default function AuthProvider({children}){

    const [username, setUsername] = useState("");
    const [isAuthorised, setAuthorised] = useState(false);

    function logout(){
        setAuthorised(false);
        setUsername(null);
    }

    
    return (
        <AuthContext.Provider value={{username, isAuthorised, 
            register: (clientData) => register(clientData, {logout, setAuthorised, setUsername}),
            login: (clientData) => login(clientData, {logout, setAuthorised, setUsername}), 
            logout
        }}>
            {children}
        </AuthContext.Provider>
    );
}