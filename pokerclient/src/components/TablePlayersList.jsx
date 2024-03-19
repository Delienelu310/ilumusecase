import { useEffect, useState } from "react";
import { addBot, sitTheTable, removePlayer } from "../api/tableManagementApi";
import { useAuth } from "../authentication/AuthContext";

import { retrieveStrategies } from "../api/tableApi";

export default function TablePlayersList({table, refresh}){

    const {username, isAuthorised} = useAuth();

    const [strategyChosen ,setStrategyChosen] = useState();
    const [strategies, setStrategies] = useState();


    function sitTheTableAction(position){
        sitTheTable({tableId: table.id, username, position})
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

    useEffect(() => {
        retrieveStrategies({categories: [table.category.category], pageNumber: 0, pageSize: 10})
            .then(response => {
                setStrategies(response.data);
            }).catch(e => console.log(e));
    }, []);

    return (
        <div>
            {table && <div>
                {table.admin && table.admin.username == username && <div>
                    <input value={strategyChosen} onChange={event => setStrategyChosen(event.target.value)}/>
                    <select value={strategyChosen} onChange={event => setStrategyChosen(event.target.value)}>
                        {strategies.map((val, i) => (
                            <option key={`option_${i}`} value={val}>{val}</option>
                        ))}
                    </select> 
                </div>}

                {new Array(table.category.maxPlayers).map((el, index) => (
                    <div key={"player_" + index}>
                        {table.players[index] ? 
                            <div>
                                {table.players[index].client && <div>
                                    Player: {table.players[index].client.username}
                                </div>}
                                {table.players[index].botStrategy && <div>
                                    Bot {table.players[index].id} - {table.players[index].botStrategy.strategy}    
                                </div>}
                            </div> 
                            : 
                            <div>
                                {isAuthorised && <button className="btn btn-success" onClick={() => sitTheTableAction(index)}>Sit here</button>}
                                {table.admin && table.admin.username == username && <button className="btn btn-primary" onClick={() => addBotAction(index)}>Add bot</button>}
                            </div>
                        }
                        {table.admin.username == username && 
                            <button className="btn btn-danger" onClick={() => removePlayer({tableId: table.id, pos:index})}>X</button>}
                    </div>
                ))}
            </div>}
        </div>
    );
}