
export default function SearchBar({query, setQuery, sendRequest}){
    return (
        <div className="m-2">
            <input style={{width:"50%", display: "inline-block"}}className="form-control m-2" placeholder="search..." 
                value={query} onChange={event => setQuery(event.target.value)}
            />
            {sendRequest && <button className="btn btn-success" onClick={e => sendRequest()}>Search</button>}
        </div>
    );
}