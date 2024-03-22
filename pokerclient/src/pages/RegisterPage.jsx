import { useState } from "react";
import { useAuth } from "../authentication/AuthContext";
import { useNavigate } from "react-router-dom";


export default function RegisterPage(){

    const {register} = useAuth();
    const navigate = useNavigate();
 
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [repeatPassword, setRepeatPassword] = useState("");

    return (
        <div>
            <span>Username:</span>
            <input className="form-control" value={username} onChange={e => setUsername(e.target.value)}/>
            <br/>
            <span>Password:</span>
            <input className="form-control" value={password} onChange={e => setPassword(e.target.value)}/>
            <br/>
            <span>Repeat password: </span>
            <input className="form-control" value={repeatPassword} onChange={e => setRepeatPassword(e.target.value)}/>
            <br/>
            <button className="m-2 btn btn-success" onClick={e => {
                if(password == repeatPassword){
                    register({username, password})
                    .then(r => navigate("/"))
                    .catch(e => console.log(e));
                }else console.log("Passwords must be equal");
            }}>Register</button>
        </div>
    );
}