import { useState } from "react";
import SearchBar from "../components/SearchBar";

export default function TableListPage(){

    const [query, setQuery] = useState("");
    const [pageNumber, setPageNumber] = useState(0);

    return (
        <div>
            <div>
                <SearchBar/>
                <hr/>
            </div>
        </div>
    );
}