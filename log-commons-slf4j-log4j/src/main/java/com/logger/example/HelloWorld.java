package com.logger.example;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class HelloWorld {
    public static void main(String[] args) {
        Log log = LogFactory.getLog("hello");
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
