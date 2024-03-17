import { useState } from "react";
import { useAuth } from "../authentication/AuthContext";


export default function CreateTablePage({categories}){

    const {username, isAuthorised} = useAuth();

    const [name, setName] = useState();
    const [category, setCategory] = useState();

    return (
        <div>
            <span>Name your table:</span>
            <input value={name} onChange={event => setName(event.target.value)} />
            <br/>

            <span>Choose category:</span>
            <select value={filterPoint.chosenValues} onChange={event => setCategory(event.target.value)}>
                <option value={null}>No value</option>
                {categories.map((val, index) => (
                    <option key={`option_${index}`} value={val}>{val}</option>
                ))}
            </select>
            <br/>

            <button className="m-2 btn btn-success">Create</button>
            <button className="m-2 btn btn-danger">Cancel</button>
        </div>
    );
}