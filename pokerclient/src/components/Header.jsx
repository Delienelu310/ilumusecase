import { Link } from "react-router-dom";


export function Header(){

    return (
        <div>
            <h1>Header</h1>
            {isAuthorised ?
                
                <>
                    <button className="m-2" onClick={logout}>Log out</button>
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