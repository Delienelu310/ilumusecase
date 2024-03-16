import { useState } from "react";
import SearchBar from "../components/SearchBar";

export default function TableListPage(){

    const [query, setQuery] = useState("");
    const [authorUsernames, setAuthorUsernames] = useState(null);
    const [categories, setCategories] = useState(null);
    const [pageNumber, setPageNumber] = useState(0);
    const [pageSize, setPageSize] = useState();

    return (
        <div>
            <div>
                <SearchBar/>
                <hr/>
            </div>
        </div>
    );
}