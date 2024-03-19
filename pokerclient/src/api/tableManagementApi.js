import { apiClient } from "./ApiClient";

export function sitTheTable({tableId, pos, username}){
    return apiClient.put(`/tables/${tableId}/add/player/${pos}/client/${username}`);
}

export function addBot({tableId, pos, strategy}){
    return apiClient.put(`/tables/${tableId}/add/player/${pos}/bot/${strategy}`);
}

export function removePlayer({tableId, pos}){
    return apiClient.put(`/tables/${tableId}/remove/player/${pos}`);
}

export function addAction({tableId, action}){
    return apiClient.put(`/tables/${tableId}/add/action`, action);
}

export function startGame({tableId}){
    return apiClient.put(`/tables/${tableId}/start`);
}

export function pauseGame({tableId}){
    return apiClient.put(`/tables/${tableId}/pause`);
}

export function continueGame({tableId}){
    return apiClient.put(`/tables/${tableId}/continue`);
}