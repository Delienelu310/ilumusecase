import { useEffect, useState } from "react";

import { retrieveTables, retrieveTablesCount } from "../api/tableApi";

import Filter from "./Filter";
import PageSwitcher from "./PageSwitcher";
import SearchBar from "./SearchBar";
import TableListElement from "./TableListElement";


export default function TableList({allCategories}){

    const [tables, setTables] = useState([]);

    const [query, setQuery] = useState();

    const [categories, setCategories] = useState([]);
    const [authorUsernames, setAuthorUsernames] = useState([]);


    const [itemsNumber, setItemsNumber] = useState(0);
    const [pageSize, setPageSize] = useState(10);
    const [pageNumber, setPageNumber] = useState(0);

    function getNumberOfTables(){
        return retrieveTablesCount({query, categories, authorUsernames})
            .then(response => {
                setItemsNumber(response.data);
            })
            .catch(e => {
                console.log(e);
            });
    }

    function sendRequest(){
        getNumberOfTables()
            .then(number => {
                return retrieveTables({query, categories, authorUsernames, pageNumber, pageSize});
            })
            .then(response => {
                console.log(response);
                setTables(response.data);
            })
            .catch(e => {
                console.log(e);
            });
    }

    useEffect(() => {
        sendRequest();
    }, [])

    return (
        <div>
            <h4>Enter table using list:</h4>
            <SearchBar query={query} setQuery={setQuery} sendRequest={sendRequest}/>
            <Filter sendRequest={sendRequest} filterSet={[
                {
                    type: "multiple-select",
                    label: "Categories",
                    allValues: allCategories,
                    chosenValues: categories,
                    setValues: setCategories
                },
                {
                    type: "multiple-input",
                    label: "Author usernames",
                    chosenValues: authorUsernames,
                    setValues: setAuthorUsernames
                }
            ]}/>

            <div>
                {tables.map((table, index) => (
                    <TableListElement key={`table_${index}`} table={table}/>
                ))}
            </div>

            <PageSwitcher
                pageSize={pageSize}
                setPageSize={setPageSize}
                pageNumber={pageNumber}
                itemsNumber={itemsNumber}
                setPageNumber={setPageNumber}
                sendRequest={sendRequest}
            />
        </div>
    );
}