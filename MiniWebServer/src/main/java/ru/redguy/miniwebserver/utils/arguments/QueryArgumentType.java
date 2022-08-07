package ru.redguy.miniwebserver.utils.arguments;

public abstract class QueryArgumentType {
    public abstract boolean isCorrect(String arg);

    public abstract Object parseArgument(String arg);
}
