package com.javaexamples;

public class MyListenerImpl implements IEventListener {
    public void send(String msg) {
        System.out.println("sending " + msg);
    }
}
