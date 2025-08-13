package org.example.CriticalSectionProblem.Utilities;

/**
 * A class that allow to share an integer by reference in order to have a shared
 * space of memory as a communication channel.
 */
public class SharableInteger {
    private int value;

    public SharableInteger(int turn) {
        this.value = turn;
    }

    public synchronized int getValue() {
        return value;
    }

    public synchronized void setValue(int turn) {
        this.value = turn;
    }
}
