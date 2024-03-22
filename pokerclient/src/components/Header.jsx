import { Link, useNavigate } from "react-router-dom";
import { useAuth } from "../authentication/AuthContext";


export function Header(){

    const {logout, isAuthorised} = useAuth(); 
    const navigate = useNavigate();

    return (
        <div>
            <span>Poker</span>
            {isAuthorised ?
                
                <>
                    <Link className="m-2" to="/tables/create">Create table</Link>
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