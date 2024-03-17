import { useParams } from "react-router-dom";
import { useAuth } from "../authentication/AuthContext";
import { useEffect } from "react";


export default function TablePage(){

    const {username, isAuthorised} = useAuth();
    const {tableId} = useParams();


    useEffect(() => {

    }, []);

    return (
        <div>
            <button className="btn btn-danger m-3">Exit</button>
        </div>
    );
}