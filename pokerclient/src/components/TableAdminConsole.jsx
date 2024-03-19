import { useAuth } from "../authentication/AuthContext";

import { startGame, pauseGame, continueGame } from "../api/tableManagementApi";

export default function TableAdminConsole({table}){

    const {username} = useAuth();

    return (
        <div>
            {table.admin.username == username && <div>

                {!table.currentRound && 
                    <button className="btn btn-success" onClick={() => startGame({tableId: table.id})}>Start game</button>}
                {table.currentRound && table.isPaused && 
                    <button className="btn btn-success" onClick={() => continueGame({tableId: table.id})}>Continue game</button>}
                {table.currentRound && !table.isPaused && 
                    <button className="btn btn-danger" onClick={() => pauseGame({tableId: table.id})}>Pause game</button>}

            </div>}
            
        </div>
    );
}