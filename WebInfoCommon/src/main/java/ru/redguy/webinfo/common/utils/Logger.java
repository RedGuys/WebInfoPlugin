package ru.redguy.webinfo.common.utils;


public class Logger {

    private static ILogger logger;

    public static void InjectLogger(ILogger logger) {
        Logger.logger = logger;
    }

    public static void info(LoggerType logFrom, String message) {
        logger.info("[" + logFrom.getName() + "] " + message);
    }

    public static void warn(LoggerType logFrom, String message) {
        logger.warn("[" + logFrom.getName() + "] " + message);
    }

    public static void error(LoggerType logFrom, String message) {
        logger.error("[" + logFrom.getName() + "] " + message);
    }
}
