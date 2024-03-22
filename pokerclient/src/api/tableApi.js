import { apiClient } from "./ApiClient";

export function retrieveCategories(){
    return apiClient.get("/categories");
}

export function retrieveStrategies({query, authors, categories, pageNumber, pageSize}){
    let request = "/strategies?"
    let params = [];
    if(query) params.push(`query=${query}`);
    if(categories && categories.length > 0) params.push(`categories=${categories.join(",")}`);
    if(authors && authors.length > 0) params.push(`authors=${authors.join(",")}`);
    if(pageNumber || pageNumber == 0)params.push(`pageNumber=${pageNumber}`);
    if(pageSize && pageSize > 0)params.push(`pageSize=${pageSize}`);

    let paramsStr = params.join("&");

    if(params.length > 0) request += "?" + paramsStr;
    
    return apiClient.get(request);
}


export function retrieveTables({query, categories, authorUsernames, pageNumber, pageSize}){

    let request = "/tables"
    let params = [];
    if(query) params.push(`query=${query}`);
    if(categories && categories.length > 0) params.push(`categories=${categories.join(",")}`);
    if(authorUsernames && authorUsernames.length > 0) params.push(`authorUsernames=${authorUsernames.join(",")}`);
    if(pageNumber || pageNumber == 0) params.push(`pageNumber=${pageNumber}`);
    if(pageSize && pageSize > 0) params.push(`pageSize=${pageSize}`);

    let paramsStr = params.join("&");

    if(params.length > 0) request += "?" + paramsStr;

    console.log(request);

    return apiClient.get(request);
}

export function retrieveTablesCount({query, categories, authorUsernames}){
    let request = "/tables/count";

    let params = [];
    if(query) params.push(`query=${query}`);
    if(categories && categories.length > 0) params.push(`categories=${categories.join(",")}`);
    if(authorUsernames && authorUsernames.length > 0) params.push(`authorUsernames=${authorUsernames.join(",")}`);

    let paramsStr = params.join("&");

    if(params.length > 0) request += "?" + paramsStr;
    return apiClient.get(request);

}

export function retrieveTableById({tableId}){
    return apiClient.get(`/tables/${tableId}`);
}

export function createTable({name, category, adminUsername, blindSize}){
    return apiClient.post(`/tables`, {name, category, adminUsername, blindSize});
}