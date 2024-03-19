import { useNavigate, useParams } from "react-router-dom";
import { useAuth } from "../authentication/AuthContext";
import { useEffect, useState } from "react";

import connectTableSocket from "../sockets/tableSocket";
import TableAdminConsole from "../components/TableAdminConsole";
import TablePlayersList from "../components/TablePlayersList";
import TablePlayerConsole from "../components/TablePlayerConsole";
import TableCanvas from "../components/TableCanvas";


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
                <TableCanvas table={table}/>

                {/* 3. player panel */}
                {table.currentRound && <TablePlayerConsole/>}

                {/* 4. player list panel */}
                <TablePlayersList table={table}/>

                {/* 5. admin console */}
                {table.admin && table.admin.username == username && <TableAdminConsole table={table}/>}
            </div>}
            
            <button className="btn btn-danger m-3" onClick={() => {
                disconnect();
                navigate("/");
            }}>Exit</button>
        </div>
    );
}