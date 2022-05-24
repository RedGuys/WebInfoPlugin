package ru.redguy.webinfo.spigot.utils;

import ru.redguy.webinfo.common.utils.ILogger;

import java.util.logging.Logger;

public class SpigotLogger implements ILogger {

    private final Logger logger;

    public SpigotLogger(Logger logger) {
        this.logger = logger;
    }

    public void info(String message) {
        logger.info(message);
    }

    public void warn(String message) {
        logger.warning(message);
    }

    public void error(String message) {
        System.err.println(message);
    }
}
