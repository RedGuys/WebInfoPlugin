package ru.redguy.webinfo.common.utils;


public class APIResponse {

    public static APIResponse OK(Object response) {
        return new APIResponse(1, "OK", response);
    }


    public static APIResponse TheVariableIsNotPassed(String variableName) {
        return new APIResponse(7, "The '" + variableName + "' variable is not passed");
    }


    public static APIResponse UnsupportedOperation() {
        return new APIResponse(10, "Unsupported operation");
    }


    public static APIResponse InternalError() {
        return new APIResponse(9, "Internal error");
    }


    public static APIResponse VariableIncorrect(String variableName) {
        return new APIResponse(18, "The '" + variableName + "' variable incorrect");
    }


    public static APIResponse NotFound() {
        return new APIResponse(19, "Not Found");
    }


    public static APIResponse MethodNotFound() {
        return new APIResponse(26, "Method not found");
    }

    private final int code;
    private final String comment;

    private Object response;

    protected APIResponse(int code, String comment) {
        this.code = code;
        this.comment = comment;
    }

    protected APIResponse(int code, String comment, Object response) {
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
