package ru.redguy.webinfo.common.utils;

import ru.redguy.miniwebserver.utils.arguments.QueryArgumentType;
import ru.redguy.webinfo.common.structures.Location;

public class LocationArgument extends QueryArgumentType {
    @Override
    public boolean isCorrect(String arg) {
        try {
            new Location(arg);
            return true;
        } catch (IndexOutOfBoundsException | NumberFormatException | NullPointerException e) {
            return false;
        }
    }

    @Override
    public Object parseArgument(String arg) {
        return new Location(arg);
    }
}
