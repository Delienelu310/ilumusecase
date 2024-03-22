import { useEffect, useState } from "react";
import { addBot, sitTheTable, removePlayer, startGame } from "../api/tableManagementApi";
import { useAuth } from "../authentication/AuthContext";

import { retrieveStrategies } from "../api/tableApi";

export default function TablePlayersList({table, refresh}){

    const {username, isAuthorised} = useAuth();

    const [strategyChosen ,setStrategyChosen] = useState("");
    const [strategies, setStrategies] = useState([]);


    function sitTheTableAction(position){
        sitTheTable({tableId: table.id, username, pos: position})
            .then(response => {
                refresh();
            }).catch(error => {
                console.log(error);
            });
    }

    function addBotAction(position){
        addBot({tableId: table.id, pos: position, strategy: strategyChosen})
            .then(response => {
                refresh();
            }).catch(error => {
                console.log(error);
            });
    }

    function removePlayerAction(position){
        removePlayer({tableId: table.id, pos:position})
            .then(response => {
                refresh();
            }).catch(error => {
                console.log(error);
            });
    }

    useEffect(() => {
        retrieveStrategies({categories: [table.category.category], pageNumber: 0, pageSize: 10})
            .then(response => {
                console.log(response.data);
                setStrategies(response.data);
            }).catch(e => console.log(e));
    }, []);

    useEffect(() => {

    }, [strategies]);

    return (
        <div style={{width: "50%"}}>
            {table && <div>

                <h6>Bot:</h6>

                {table.admin && table.admin.username == username && <div>
                    <span>By name:</span>
                    <input className="m-2 form-control" style={{width: "75%"}}
                        value={strategyChosen} onChange={event => setStrategyChosen(event.target.value)}
                    />
                    <span>From list:</span>
                    <select className="m-2 form-control" style={{width: "75%"}} 
                        value={strategyChosen} onChange={event => {
                            setStrategyChosen(event.target.value);
                            console.log(event.target.value);
                        }}
                    >
                        <option value={""}>None</option>
                        {strategies && strategies.map((val, i) => (
                            <option key={`option_${i}`} value={val.strategy}>{val.strategy}</option>
                        ))}
                    </select> 
                </div>}

                {new Array(table.category.maxPlayers).fill(1).map((el, index) => (
                    <div className="m-2" key={"player_" + index}>
                        {table.players[index] ? 
                            <div>
                                {table.players[index].client && <div>
                                    {index + 1}. Player: {table.players[index].client.username}
                                </div>}
                                {table.players[index].botStrategy && <div>
                                    {index + 1}. Bot {table.players[index].id} - {table.players[index].botStrategy.strategy}    
                                </div>}
                            </div> 
                            : 
                            <div>
                                {index + 1}. Empty
                                {isAuthorised && <button className="m-2 btn btn-success" onClick={() => sitTheTableAction(index)}>Sit here</button>}
                                {table.admin && table.admin.username == username && 
                                    <button className="m-2 btn btn-primary" onClick={() => addBotAction(index)}>Add bot</button>
                                }
                            </div>
                        }
                        {table.players[index] && table.admin.username == username && <button 
                            className="m-2 btn btn-danger" onClick={() => removePlayerAction(index)}
                        >X</button>}
                        <hr/>
                    </div>
                ))}
            </div>}
        </div>
    );
}