import { useNavigate } from "react-router-dom";

export default function TableListElement({table}){

    const navigate = useNavigate();

    return (
        <div className="m-3" style={{
            border: "solid 3px black",
            borderRadius: "20px",
            width: "75%",
            padding: "20px"
        }}>
            <h3><span>{table.category.category}</span>: <span>{table.name}</span></h3>
            
            <div>
                <span>sb/bb: {Math.round(table.blindSize/2 * 100) / 100}/{table.blindSize}</span>
                <span>players: {table.players.length}</span>
            </div>
            
            <button className="btn btn-primary" onClick={event => { navigate(`/tables/${table.id}`)}}>Enter</button>
            
            <div>
                <span>Author: {table.admin.username}</span>
                <span>Id: {table.id}</span>
            </div>
        </div>
    );
}