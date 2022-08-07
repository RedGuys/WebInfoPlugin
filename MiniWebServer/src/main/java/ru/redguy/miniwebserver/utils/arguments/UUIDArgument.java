package ru.redguy.miniwebserver.utils.arguments;

import java.util.UUID;

public class UUIDArgument extends QueryArgumentType{
    @Override
    public boolean isCorrect(String arg) {
        try {
            UUID.fromString(arg);
            return true;
        } catch (IllegalArgumentException exception) {
            return false;
        }
    }

    @Override
    public Object parseArgument(String arg) {
        return UUID.fromString(arg);
    }
}
