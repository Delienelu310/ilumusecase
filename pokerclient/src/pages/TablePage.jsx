import { useNavigate, useParams } from "react-router-dom";
import { useAuth } from "../authentication/AuthContext";
import { useEffect, useState } from "react";

import connectTableSocket from "../sockets/tableSocket";


export default function TablePage(){

    const {username, isAuthorised} = useAuth();
    const {tableId} = useParams();
    const [table, setTable] = useState();

    const [refresh, setRefresh] = useState();
    const [disconnect, setDisconnect] = useState();
    const navigate = useNavigate();

    useEffect(() => {
        const data = connectTableSocket(tableId, setTable);
        setRefresh(data.refreshRoomForAll);
        setDisconnect(data.disconnect);
    }, []);

    return (
        <div>
            {table && <div>
                {/* 1. metadata about the table */}
                <div>
                    <h4>{table.name}</h4>
                    <span>Category: {table.category.category}</span>
                    <br/>
                    
                    <span>Id: {table.id}</span>
                    <span>Admin: {table.admin.username}</span>
                    <br/>
                    
                    <strong>Configurations:</strong>
                    <br/>
                    
                    <span>Big blind: {table.blindSize}</span>
                    <span>Max players{table.category.maxPlayers}</span>
                </div>
                
                {/* 2. the table - an oval or an rectange with certain number of squares on it  */}


                {/* 3. player panel */}
                {/* 4. player list panel */}
                {/* 5. admin console */}
            </div>}
            
            <button className="btn btn-danger m-3" onClick={() => {
                disconnect();
                navigate("/");
            }}>Exit</button>
        </div>
    );
}