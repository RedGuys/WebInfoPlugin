package ru.redguy.webinfo.common.utils;

public class WebSocketResponse extends WebSocketMessage {

    int id;
    int status;
    String response;

    public WebSocketResponse(int id, int status, String response) {
        this.type = "response";
        this.id = id;
        this.response = response;
        this.status = status;
    }
}
