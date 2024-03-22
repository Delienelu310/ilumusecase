import { useEffect, useState } from "react";

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


    useEffect(() => {
        console.log(filterSet);
    }, [])

    return (
        <div style={{width: "40%", border: "solid 1px black", borderRadius: "10px", padding: "10px"}} >

            <h4>Filter:</h4>

            {sendRequest && <button className="btn btn-primary" onClick={sendRequest}>Apply</button>}

            {filterSet.map((filterPoint, index) => (
                <div key={`filterPoint_${index}`} className="m-1">

                    <hr/>
                    <h6>{filterPoint.label}</h6>

                    {filterPoint.type == "single-input" ? 
                        <div>
                            <input className="form-control m-2" style={{width: "75%"}}
                                value={filterPoint.chosenValues} onChange={event => filterPoint.setValues(event.target.value)}
                            />
                        </div>
                    : (filterPoint.type == "single-select" ?
                        <div>
                            <select className="m-2 form-control" style={{width:"75%"}}
                                value={filterPoint.chosenValues} onChange={event => filterPoint.setValues(event.target.value)}
                            >
                                <option value={null}>No value</option>
                                {filterPoint.allValues.map((val, index) => (
                                    <option key={`option_${index}`} value={val}>{val}</option>
                                ))}
                            </select>
                        </div>
                    : (filterPoint.type == "multiple-input" ? 
                        <div>
                            <div>
                                {filterPoint.chosenValues.map((value, index) => (
                                    <div className="m-2" key={`chosenValue_${index}`}>  
                                        <span>{index + 1}. {value}</span>
                                        <button className="m-2 btn btn-danger" onClick={e => 
                                            filterPoint.setValues([...filterPoint.chosenValues].filter(val => val != value))
                                        }>X</button>
                                    </div>
                                ))}
                            </div>
                            <input style={{width:"50%", display: "inline-block"}} className="form-control m-2" 
                                value={multipleSingle} onChange={event => setMultipleSingle(event.target.value)}
                            />
                            <button className="m-2 btn btn-primary" onClick={e => {
                                if(filterPoint.chosenValues && filterPoint.chosenValues.includes && 
                                    filterPoint.chosenValues.includes(multipleSingle)) return;

                                filterPoint.setValues([...filterPoint.chosenValues, multipleSingle])
                            }}>Add</button>
                        </div>
                    : (filterPoint.type == "multiple-select" ?
                        <div>
                            <div>
                                <select multiple className="form-control" value={filterPoint.chosenValues}  style={{width:"75%"}}
                                    onChange={event => {
                                        if(filterPoint.chosenValues && filterPoint.chosenValues.includes && 
                                            filterPoint.chosenValues.includes(event.target.value)
                                        ){
                                            filterPoint.setValues([...filterPoint.chosenValues].filter(val => val != event.target.value));
                                        }else{
                                            filterPoint.setValues([...filterPoint.chosenValues, event.target.value]);
                                        }
                                    }
                                }>
                                    {filterPoint.allValues.map((val, i) => (
                                        <option key={`option_${i}`} value={val.category}>{val.category}</option>
                                    ))}
                                </select>
                                <button className="m-2 btn btn-danger" onClick={e => filterPoint.setValues([])}>Deselect all</button>
                            </div>
                        </div>
                    : null)))
                    }
                </div>
            ))}

        </div>
    );
}