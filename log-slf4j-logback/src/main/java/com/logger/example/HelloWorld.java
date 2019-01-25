package com.logger.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloWorld {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("hello");
        log.info("Hello World");

        Integer intValue = new Integer(10);
        String strValue = "Hello String";

        log.info("intValue: " + intValue + " strValue: " + strValue);

        log.info("info msg");
        log.error("error msg");
        log.trace("trace msg");
        log.warn("warn msg");
        log.debug("debug msg");
    }
}
