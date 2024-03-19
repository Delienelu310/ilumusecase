import Stomp from "stompjs"
import SockJS from "sockjs-client"

export default function connectTableSocket(tableId, setTable){

    const socket = new SockJS("http://localhost:8080/socket/table");
    const client = Stomp.over(socket);

    client.connect({}, async () => {

        console.log('Connected');
        client.subscribe(`/tables/${tableId}`, (table) => {
            console.log(JSON.parse(table.body));
            setTable(JSON.parse(table.body));
        });

    });

    client.onWebSocketError = (error) => {
        //todo: add proper error handling
        console.error('Error with websocket', error);
    };

    client.onStompError = (frame) => {
        
        //todo: add proper error handling
        console.error('Broker reported error: ' + frame.headers['message']);
        console.error('Additional details: ' + frame.body);
    };

    const disconnect = () => {
        client.disconnect();
        console.log('Disconnected');
    }

    const refreshRoomForAll = () => {
        console.log("REFRESHING ALL");
        client.send( `/tables/${tableId}/refresh`,  {},  "{}");
    }


    return {disconnect, refreshRoomForAll};
};




