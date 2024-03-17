import { useState } from "react";

export default function TableEnterComponent(){

    const [id, setId] = useState(null);

    function enterTable(){

    }

    return (
        <div>
            <input value={id} onChange={event => setId(event.target.value)}/>
            <button className="btn btn-primary" onClick={e => enterTable()}>Enter</button>
        </div>
    );
}