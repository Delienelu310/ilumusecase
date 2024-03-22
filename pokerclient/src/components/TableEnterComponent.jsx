import { useState } from "react";
import { useNavigate } from "react-router-dom";

export default function TableEnterComponent(){

    const [id, setId] = useState();

    const navigate = useNavigate();

    function enterTable(){
        navigate(`/tables/${id}`);
    }

    return (
        <div style={{
            width: "50%"
        }}>
            <h4>Enter table using id:</h4>
            <input style={{display: "inline-block", width: "50%"}} className="m-2 form-control" value={id} onChange={event => setId(event.target.value)}/>
            <button className="btn btn-primary" onClick={e => enterTable()}>Enter</button>
        </div>
    );
}