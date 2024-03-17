import { Link, useNavigate } from "react-router-dom";
import { useAuth } from "../authentication/AuthContext";


export function Header(){

    const {logout, isAuthorised} = useAuth(); 
    const navigate = useNavigate();

    return (
        <div>
            <h1>Header</h1>
            {isAuthorised ?
                
                <>
                    <button className="m-2" onClick={e => {
                        logout();
                        navigate("/");
                    }}>Log out</button>
                    <Link className="m-2" >Account</Link>
                </>
                :
                <>
                    <Link className="m-2" to="/register">Register</Link>
                    <Link className="m-2" to="/login">Login</Link>
                </>
            }
            <hr/>
        </div>
    );
}