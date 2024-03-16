import { useState } from "react";


export default function PageSwitcher({pageSize, setPageSize, itemsNumber, setPageNumber, pageNumber, sendRequest}){
    
    return (
        <div>
            {pageSize && setPageSize && <div className="m-2">
                <span>Set page size: </span>
                <input className="form-control" value={pageSize} onChange={event => setPageSize(event.target.value)}/>
            </div>}
            <div className="m-2">
                {new Array(Math.ceil(itemsNumber / pageSize)).map((item, index) => (
                    <button className={`btn ${index == pageNumber ? "btn-primary" : "btn-info"} m-1`} onClick={event => {
                        setPageNumber(index);
                        sendRequest();
                    }}>{index + 1}</button>
                ))}
            </div>
            
        </div>
    );
}