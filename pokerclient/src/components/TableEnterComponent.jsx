import { useState } from "react";
import { useNavigate } from "react-router-dom";

export default function TableEnterComponent(){

    const [id, setId] = useState(null);

    const navigate = useNavigate();

    function enterTable(){
        navigate(`/tables/${id}`);
    }

    return (
        <div>
            <input value={id} onChange={event => setId(event.target.value)}/>
            <button className="btn btn-primary" onClick={e => enterTable()}>Enter</button>
        </div>
    );
}