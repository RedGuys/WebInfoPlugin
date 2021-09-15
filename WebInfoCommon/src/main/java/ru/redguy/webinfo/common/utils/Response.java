package ru.redguy.webinfo.common.utils;

import com.google.gson.JsonObject;

public class Response {

    public static Response OK(JsonObject response) {
        return new Response(1, "OK",response);
    }
    public static Response TheVariableIsNotPassed(String variableName) {
        return new Response(7,"The '"+variableName+"' variable is not passed");
    }
    public static Response InternalError() {
        return new Response(9,"Internal error");
    }
    public static Response NotFound() {
        return new Response(19,"Not Found");
    }
    public static Response MethodNotFound() {
        return new Response(26,"Method not found");
    }

    private final int code;
    private final String comment;

    private JsonObject response;

    protected Response(int code, String comment) {
        this.code = code;
        this.comment = comment;
    }

    protected Response(int code, String comment, JsonObject response) {
        this.code = code;
        this.comment = comment;
        this.response = response;
    }

    public int getCode() {
        return code;
    }

    public String getComment() {
        return comment;
    }

    public JsonObject getResponse() {
        return response;
    }
}
