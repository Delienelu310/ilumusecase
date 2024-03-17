import { useEffect, useState } from "react";
import TableList from "../components/TableList";
import { retrieveCategories } from "../api/tableApi";

export default function TableListPage(){

    const [categories, setCategories] = useState([]);

    useEffect(() => {
        retrieveCategories()
            .then(response => {
                setCategories(response.data);
            }).catch(e => console.log(e));
    }, []);

    return (
        <div>
            <TableList allCategories={categories}/>
        </div>
    );
}