package com.javaworld;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MyEventSource {
    private List _listeners = new ArrayList();

    public synchronized void addEventListener(MyEventClassListener listener) {
        _listeners.add(listener);
    }

    public synchronized void removeEventListener(MyEventClassListener listener) {
        _listeners.remove(listener);
    }

    // call this method whenever you want to notify
    //the event listeners of the particular event
    private synchronized void fireEvent() {
        MyEventClass event = new MyEventClass(this);
        Iterator i = _listeners.iterator();
        while (i.hasNext()) {
            ((MyEventClassListener) i.next()).handleMyEventClassEvent(event);
        }
    }
}