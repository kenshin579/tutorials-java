package com.java.booleanchange;

import java.awt.*;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.EventObject;
import java.util.List;

/**
 * This class uses the EventQueue to process its events, but you should only
 * really do this if the changes you make have an impact on part of a GUI
 * eg. adding a button to a JFrame.
 * <p/>
 * Otherwise, you should create your own event dispatch thread that can handle
 * change events
 */
public class BooleanChangeTest implements BooleanChangeDispatcher {

    public static void main(String[] args) {

        BooleanChangeListener listener = new BooleanChangeListener() {
            public void stateChanged(BooleanChangeEvent event) {
                System.out.println("Detected change to: "
                        + event.getDispatcher().getFlag()
                        + " -- event: " + event);
            }
        };

        BooleanChangeTest test = new BooleanChangeTest(false);
        test.addBooleanChangeListener(listener);

        test.setFlag(false); // no change, no event dispatch
        test.setFlag(true); // changed to true -- event dispatched

    }

    private boolean flag;
    private List<BooleanChangeListener> listeners;

    public BooleanChangeTest(boolean initialFlagState) {
        flag = initialFlagState;
        listeners = new ArrayList<BooleanChangeListener>();
    }

    public void addBooleanChangeListener(BooleanChangeListener listener) {
        listeners.add(listener);
    }

    public void setFlag(boolean flag) {
        if (this.flag != flag) {
            this.flag = flag;
            dispatchEvent();
        }
    }

    public boolean getFlag() {
        return flag;
    }

    private void dispatchEvent() {
        final BooleanChangeEvent event = new BooleanChangeEvent(this);
        for (BooleanChangeListener l : listeners) {
            dispatchRunnableOnEventQueue(l, event);
        }
    }

    private void dispatchRunnableOnEventQueue(
            final BooleanChangeListener listener,
            final BooleanChangeEvent event) {

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                listener.stateChanged(event);
            }
        });
    }

}

interface BooleanChangeDispatcher {

    public void addBooleanChangeListener(BooleanChangeListener listener);

    public boolean getFlag();

    public void setFlag(boolean flag);

}

/**
 * Listener interface for classes interested in knowing about a boolean
 * flag change.
 */
interface BooleanChangeListener extends EventListener {

    public void stateChanged(BooleanChangeEvent event);

}

/**
 * This class lets the listener know when the change occured and what
 * object was changed.
 */
class BooleanChangeEvent extends EventObject {

    private final BooleanChangeDispatcher dispatcher;

    public BooleanChangeEvent(BooleanChangeDispatcher dispatcher) {
        super(dispatcher);
        this.dispatcher = dispatcher;
    }

    // type safe way to get source (as opposed to getSource of EventObject
    public BooleanChangeDispatcher getDispatcher() {
        return dispatcher;
    }
}