package ru.redguy.miniwebserver.utils.arguments;

public class BooleanArgument extends QueryArgumentType {
    @Override
    public boolean isCorrect(String arg) {
        return arg.equalsIgnoreCase("true")|arg.equalsIgnoreCase("false");
    }

    @Override
    public Object parseArgument(String arg) {
        return Boolean.parseBoolean(arg);
    }
}
