package com.letskodeit.overview;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggingDemo {

    private static final Logger log = LogManager.getLogger(LoggingDemo.class.getName());

    public static void main(String[] args) {
        log.trace("Trace Message printed");
        log.debug("Debug Message printed");
        log.info("Info Message printed");
        log.error("Error Message printed");
        log.fatal("Fatal Message printed");
    }
}
