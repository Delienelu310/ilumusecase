import { useEffect, useState } from "react";
import { useAuth } from "../authentication/AuthContext";

import { addAction } from "../api/tableManagementApi";


export default function TablePlayerConsole({table}){

    const {username} = useAuth();
    const [size, setSize] = useState(0);

    const [currentStake, setCurrentStake] = useState(0);
    const [currentPlayer, setCurrentPlayer] = useState();


    function addActionAct(type){
        if(!currentPlayer.client || currentPlayer.client != username) return;

        let action = {
            actionType: type,
            position: table.currentPlayerPosition,
            size: 0
        };
        switch(type){
            case "BET":  
                action.size = size;
                break;
            case "FOLD":
            case "CHECK":
                break;
            case "RAISE":
                action.size = size - currentPlayer.currentBet;
                break;
            case "CALL":
                action.size = currentStake - currentPlayer.currentBet
                break;
            default:
                throw new Error();
        }

        addAction({tableId: table.id, action})
            .then(r => console.log("Action added"))
            .catch(e => console.log("Exception"));
    }


    useEffect(() => {

    
        if(table && table.currentRound){
            setCurrentPlayer(table.currentRound.players[table.currentPlayerPosition]);

            //get current biggest bet
            let max = 0;
            for(let i = 0; i < table.currentRound.players.length; i++){
                if(max < table.currentRound.players.currentBet) max = table.currentRound.players.currentBet;
            }
            setCurrentStake(max);
        }else{
            setCurrentPlayer(null);
            setSize(0);
            setCurrentStake(0);
        }
    }, [table]);
    useEffect(() => {

    }, [currentPlayer]);

    

    return (
        <div style={{width: "50%"}}>
            {table && table.currentRound && <div>
                {/* Panel with actions */}
                {currentPlayer && currentPlayer.client && currentPlayer.client.username == username && <div>
                    <input
                        className="m-2 form-control"
                        type="range"
                        min={currentStake * 2 > currentPlayer.bankroll ? currentPlayer.bankroll : currentStake * 2}
                        max={currentPlayer.bankroll}
                        value={size}
                        onChange={e => setSize(e.target.value)}
                    /> 
                    <input className="m-2 form-control" value={size} onChange={e => setSize(e.target.value)}/>
                    <button className="m-2 btn btn-success" onClick={() => addActionAct("BET")}>Bet</button>
                    <button className="m-2 btn btn-success" onClick={() => addActionAct("CALL")}>Call</button>
                    <button className="m-2 btn btn-success" onClick={() => addActionAct("CHECK")}>Check</button>
                    <button className="m-2 btn btn-primary" onClick={() => addActionAct("RAISE")}>Raise</button>
                    <button className="m-2 btn btn-danger" onClick={() => addActionAct("FOLD")}>Fold</button>
                </div>}
                    
                

            </div>}

            
        </div>
    );
}