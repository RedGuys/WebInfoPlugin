package ru.redguy.webinfo.common.utils;

import org.jetbrains.annotations.NotNull;

public class Logger {

    private static ILogger logger;

    public static void InjectLogger(ILogger logger) {
        Logger.logger = logger;
    }

    public static void info(@NotNull LoggerType logFrom, String message) {
        logger.info("[" + logFrom.getName() + "] " + message);
    }

    public static void warn(@NotNull LoggerType logFrom, String message) {
        logger.warn("[" + logFrom.getName() + "] " + message);
    }

    public static void error(@NotNull LoggerType logFrom, String message) {
        logger.error("[" + logFrom.getName() + "] " + message);
    }
}
