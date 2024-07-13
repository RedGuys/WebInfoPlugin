package ru.redguy.webinfo.common.utils;

public class Response {

    public static Response OK(Object response) {
        return new Response(1, "OK", response);
    }

    public static Response TheVariableIsNotPassed(String variableName) {
        return new Response(7, "The '" + variableName + "' variable is not passed");
    }

    public static Response UnsupportedOperation() {
        return new Response(10, "Unsupported operation");
    }

    public static Response InternalError() {
        return new Response(9, "Internal error");
    }

    public static Response VariableIncorrect(String variableName) {
        return new Response(18, "The '" + variableName + "' variable incorrect");
    }

    public static Response NotFound() {
        return new Response(19, "Not Found");
    }

    public static Response MethodNotFound() {
        return new Response(26, "Method not found");
    }

    private final int code;
    private final String comment;

    private Object response;

    protected Response(int code, String comment) {
        this.code = code;
        this.comment = comment;
    }

    protected Response(int code, String comment, Object response) {
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

    public Object getResponse() {
        return response;
    }
}
