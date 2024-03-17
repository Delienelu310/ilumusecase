import { useState } from "react";
import TableList from "../components/TableList";

export default function TableListPage(){



    return (
        <div>
            <div>
                <input/>
            </div>
            <TableList allCategories={["holdem6"]}/>
        </div>
    );
}