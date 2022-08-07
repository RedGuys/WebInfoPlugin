package ru.redguy.miniwebserver.utils.arguments;

public class StringArgument extends QueryArgumentType{
    @Override
    public boolean isCorrect(String arg) {
        return true;
    }

    @Override
    public Object parseArgument(String arg) {
        return arg;
    }
}
