import { useState, useEffect } from "react";
import { useAuth } from "../authentication/AuthContext";
import { createTable, retrieveCategories } from "../api/tableApi";
import { useNavigate } from "react-router-dom";


export default function CreateTablePage(){

    const {username, isAuthorised} = useAuth();

    const [name, setName] = useState();
    const [category, setCategory] = useState();
    const [blindSize, setBlindSize] = useState();

    const [categories, setCategories] = useState([]);
    const navigate = useNavigate();

    function sendRequest(){
        createTable({category, name, adminUsername: username, blindSize})
            .then(response => {
                navigate(`/tables/${response.data.id}`);
            })
            .catch(e => console.log(e));
    }


    useEffect(() => {
        if(!isAuthorised){
            navigate("/login");
            return;
        }
        retrieveCategories()
            .then(response => {
                setCategories(response.data);
            }).catch(e => console.log(e));
    }, []);

    return (
        <div>
            {isAuthorised && <>
                <span>Name your table:</span>
                <input className="form-control m-2" style={{width: "75%"}}
                    value={name} onChange={event => setName(event.target.value)}
                />
                <br/>

                <span>Choose category:</span>
                <select className="m-2 form-control" style={{width: "75%"}}
                    value={category} onChange={event => setCategory(event.target.value)}
                >
                    <option value={null}>No value</option>
                    {categories.map((val, index) => (
                        <option key={`option_${index}`} value={val.category}>{val.category}</option>
                    ))}
                </select>
                <br/>

                <span>Blind size:</span>
                <input className="m-2 form-control" style={{width: "75%"}}
                    value={blindSize} onChange={e => setBlindSize(e.target.value)}
                />

                <button className="m-2 btn btn-success" onClick={e => sendRequest()}>Create</button>
                <button className="m-2 btn btn-danger" onClick={e => navigate("/")}>Cancel</button>
            </>}
        </div>
    );
}