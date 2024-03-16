
export default function SearchBar({query, setQuery, sendRequest}){
    return (
        <div className="form-control m-2">
            <input placeholder="search..." value={query} onChange={event => setQuery(event.target.value)}/>
            <br/>
            {sendRequest && <button className="btn btn-primary" onClick={e => sendRequest()}>Search</button>}
        </div>
    );
}