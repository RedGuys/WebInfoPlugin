package ru.redguy.webinfo.common.utils;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class Response {

    @Contract(value = "_ -> new", pure = true)
    public static @NotNull Response OK(Object response) {
        return new Response(1, "OK", response);
    }

    @Contract(value = "_ -> new", pure = true)
    public static @NotNull Response TheVariableIsNotPassed(String variableName) {
        return new Response(7, "The '" + variableName + "' variable is not passed");
    }

    @Contract(value = " -> new", pure = true)
    public static @NotNull Response UnsupportedOperation() {
        return new Response(10, "Unsupported operation");
    }

    @Contract(value = " -> new", pure = true)
    public static @NotNull Response InternalError() {
        return new Response(9, "Internal error");
    }

    @Contract(value = "_ -> new", pure = true)
    public static @NotNull Response VariableIncorrect(String variableName) {
        return new Response(18, "The '" + variableName + "' variable incorrect");
    }

    @Contract(value = " -> new", pure = true)
    public static @NotNull Response NotFound() {
        return new Response(19, "Not Found");
    }

    @Contract(value = " -> new", pure = true)
    public static @NotNull Response MethodNotFound() {
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
