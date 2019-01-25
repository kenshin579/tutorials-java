package com.javaexamples;

/**
 * 다른 곳에서 interface 구현체를 넘겨주면 여기서 실행함
 *
 * @author kenshin579
 */
public class EventAction {
    IEventListener myEventListener;

    public void setEventListener(IEventListener eventListener) {
        myEventListener = eventListener;
    }

    public void performAction() {
        if (myEventListener != null) {
            myEventListener.send("Hello!!");
        }
    }

    public static void main(String[] args) {
        EventAction eventAction = new EventAction();

        //1. method 인터페이스를 따로 구현한 경우
        eventAction.setEventListener(new MyListenerImpl());
        eventAction.performAction();

        //2.method anonymous class으로..
        eventAction.setEventListener(new IEventListener() {

            public void send(String msg) {
                System.out.println("Sending " + msg + " from anonymous class");
            }
        });
        eventAction.performAction();
    }
}
