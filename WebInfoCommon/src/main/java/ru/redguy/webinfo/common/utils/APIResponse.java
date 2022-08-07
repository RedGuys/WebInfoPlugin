package ru.redguy.webinfo.common.utils;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class APIResponse {

    @Contract(value = "_ -> new", pure = true)
    public static @NotNull APIResponse OK(Object response) {
        return new APIResponse(1, "OK", response);
    }

    @Contract(value = "_ -> new", pure = true)
    public static @NotNull APIResponse TheVariableIsNotPassed(String variableName) {
        return new APIResponse(7, "The '" + variableName + "' variable is not passed");
    }

    @Contract(value = " -> new", pure = true)
    public static @NotNull APIResponse UnsupportedOperation() {
        return new APIResponse(10, "Unsupported operation");
    }

    @Contract(value = " -> new", pure = true)
    public static @NotNull APIResponse InternalError() {
        return new APIResponse(9, "Internal error");
    }

    @Contract(value = "_ -> new", pure = true)
    public static @NotNull APIResponse VariableIncorrect(String variableName) {
        return new APIResponse(18, "The '" + variableName + "' variable incorrect");
    }

    @Contract(value = " -> new", pure = true)
    public static @NotNull APIResponse NotFound() {
        return new APIResponse(19, "Not Found");
    }

    @Contract(value = " -> new", pure = true)
    public static @NotNull APIResponse MethodNotFound() {
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
