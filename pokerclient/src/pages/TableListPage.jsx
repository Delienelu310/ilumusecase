import { useEffect, useState } from "react";
import TableList from "../components/TableList";
import { retrieveCategories } from "../api/tableApi";
import TableEnterComponent from "../components/TableEnterComponent";

export default function TableListPage(){

    const [categories, setCategories] = useState([]);

    useEffect(() => {
        retrieveCategories()
            .then(response => {
                console.log(response);
                setCategories(response.data);
            }).catch(e => console.log(e));
    }, []);

    return (
        <div>
            <TableEnterComponent/>
            <hr/>
            <TableList allCategories={categories}/>
        </div>
    );
}