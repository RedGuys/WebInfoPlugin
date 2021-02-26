package ru.redguy.webinfosponge.utils;

import org.slf4j.Logger;
import ru.redguy.webinfocommon.utils.ILogger;

public class SpongeLogger implements ILogger {

    private final Logger logger;

    public SpongeLogger(Logger logger) {
        this.logger = logger;
    }

    public void info(String message) {
        logger.info(message);
    }

    public void warn(String message) {
        logger.warn(message);
    }

    public void error(String message) {
        logger.error(message);
    }
}
