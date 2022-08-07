package ru.redguy.webinfo.common.utils;

import ru.redguy.miniwebserver.utils.arguments.QueryArgumentType;
import ru.redguy.webinfo.common.controllers.Controllers;

public class WorldArgument extends QueryArgumentType {
    @Override
    public boolean isCorrect(String arg) {
        return Controllers.getWorldsController().isWorldExist(arg);
    }

    @Override
    public Object parseArgument(String arg) {
        return arg;
    }
}
