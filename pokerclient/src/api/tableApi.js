import { apiClient } from "./ApiClient";

export function retrieveTables({query, categories, authorUsernames, pageNumber, pageSize}){

    let request = "/tables?"
    let params = [];
    params.push(`query=${query}`);
    params.push(`categories=${categories.join(",")}`);
    params.push(`authorUsernames=${authorUsernames.join(",")}`);
    params.push(`pageNumber=${pageNumber}`);
    params.push(`pageSize=${pageSize}`);

    let paramsStr = params.join("&");

    return apiClient.get(request + paramsStr);
}

export function retrieveTablesCount({query, categories, authorUsernames}){
    let request = "/tables/count?";

    let params = [];
    params.push(`query=${query}`);
    params.push(`categories=${categories.join(",")}`);
    params.push(`authorUsernames=${authorUsernames.join(",")}`);

    let paramsStr = params.join("&");

    return apiClient.get(request + paramsStr);

}

export function retrieveTableById({tableId}){
    return apiClient.get(`/tables/${tableId}`);
}