import { useState } from "react";
import { useAuth } from "../authentication/AuthContext";
import { useNavigate } from "react-router-dom";


export default function LoginPage(){

    const {login} = useAuth();
    const navigate = useNavigate();

    const [username, setUsername] = useState();
    const [password, setPassword] = useState();

    return (
        <div>
            <span>Username:</span>
            <input className="form-control" value={username} onChange={e => setUsername(e.target.value)}/>
            <br/>
            <span>Password:</span>
            <input className="form-control" value={password} onChange={e => setPassword(e.target.value)}/>
            <br/>
            <button className="m-2 btn btn-success" onClick={e => {
                login({username, password})
                    .then(r => {
                        navigate("/");
                    }).catch(e => console.log(e));
            }}>Log in</button>
        </div>
    );
    
}
