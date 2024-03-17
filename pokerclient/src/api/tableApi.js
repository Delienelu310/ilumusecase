import { apiClient } from "./ApiClient";

export function retrieveCategories(){
    return apiClient.get("/categories");
}


export function retrieveTables({query, categories, authorUsernames, pageNumber, pageSize}){

    let request = "/tables?"
    let params = [];
    if(query) params.push(`query=${query}`);
    if(categories && categories.length > 0) params.push(`categories=${categories.join(",")}`);
    if(authorUsernames && authorUsernames.length > 0) params.push(`authorUsernames=${authorUsernames.join(",")}`);
    if(pageNumber || pageNumber == 0)params.push(`pageNumber=${pageNumber}`);
    if(pageSize && pageSize > 0)params.push(`pageSize=${pageSize}`);

    let paramsStr = params.join("&");

    return apiClient.get(request + paramsStr);
}

export function retrieveTablesCount({query, categories, authorUsernames}){
    let request = "/tables/count?";

    let params = [];
    if(query) params.push(`query=${query}`);
    if(categories && categories.length > 0) params.push(`categories=${categories.join(",")}`);
    if(authorUsernames && authorUsernames.length > 0) params.push(`authorUsernames=${authorUsernames.join(",")}`);

    let paramsStr = params.join("&");

    return apiClient.get(request + paramsStr);

}

export function retrieveTableById({tableId}){
    return apiClient.get(`/tables/${tableId}`);
}

export function createTable({name, category, adminUsername, blindSize}){
    return apiClient.post(`/tables`, {name, category, adminusername, blindSize});
}