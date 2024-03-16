
export default function SearchBar({query, setQuery}){
    return (
        <div className="form-control m-2">
            <input placeholder="search..." value={query} onChange={event => setQuery(event.target.value)}/>
        </div>
    );
}