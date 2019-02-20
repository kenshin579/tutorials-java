package com.advenoh.examples;

/**
 * http://www.jayway.com/2013/03/05/beyond-mocking-with-powermock/
 */
public class MyClass1 {

    static String hello(String arg) {
        return "Hello " + arg;
    }

    public String nonStaticHello(String arg) {
        return "NonStatic Hello " + arg;
    }

    public String goodByeWrapper(String arg) {
        return goodbye(arg);
    }

    private String goodbye(String arg) {
        return "Goodbye " + arg;
    }

}