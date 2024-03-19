import { useState } from "react";

export default function Filter({filterSet, sendRequest}){

    /*
        the main property is filterSet
        it consists of object filterPoint
        filterPoint has a form:
        {
            type (multiple-select, multiple-input, single-select, single-input)
            label
            allValues
            chosenValues
            setValues
        }
    */

    const [multipleSingle, setMultipleSingle] = useState("");


    return (
        <div>

            {sendRequest && <button className="btn btn-primary" onClick={sendRequest}>Apply</button>}

            {filterSet.map((filterPoint, index) => (
                <div key={`filterPoint_${index}`} className="m-1">
                    {filterPoint.label}
                    {filterPoint.type == "single-input" ? 
                        <div>
                            <input value={filterPoint.chosenValues} onChange={event => filterPoint.setValues(event.target.value)}/>
                        </div>
                    : filterPoint.type == "single-select" ?
                        <div>
                            <select value={filterPoint.chosenValues} onChange={event => filterPoint.setValues(event.target.value)}>
                                <option value={null}>No value</option>
                                {filterPoint.allValues.map((val, index) => (
                                    <option key={`option_${index}`} value={val}>{val}</option>
                                ))}
                            </select>
                        </div>
                    : filterPoint.type == "multiple-input" ? 
                        <div>
                            <div>
                                {filterPoint.chosenValues.map((value, index) => (
                                    <span key={`chosenValue_${index}`}> 
                                        {value}
                                        <button className="btn btn-danger" onClick={e => 
                                            filterPoint.setValues([...filterPoint.chosenValues].filter(val => val != value))
                                        }>X</button>
                                    </span>
                                ))}
                            </div>
                            <input value={multipleSingle} onChange={event => setMultipleSingle(event.target.value)}/>
                            <br/>
                            <button className="btn btn-primary" onClick={e => {
                                filterPoint.setValues([...filterPoint.chosenValues, multipleSingle])
                            }}>Add</button>
                        </div>
                    : filterPoint.type == "multiple-select" ?
                        <div>
                            <div>
                            <select multiple value={filterPoint.chosenValues} onChange={event => filterPoint.setValues(event.target.value)}>
                                {filterPoint.allValues.map(val => (
                                    <option key={`option_${index}`} value={val}>{val}</option>
                                ))}
                            </select>
                        </div>
                        </div>
                    : null
                    }
                </div>
            ))}

        </div>
    );
}