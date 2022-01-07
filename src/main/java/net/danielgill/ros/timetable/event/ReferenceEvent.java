package net.danielgill.ros.timetable.event;

/**
 * An event class that represents any event which has a reference.
 * @author Daniel Gill
 */
public interface ReferenceEvent {
    /**
     * Increments the references in the event by a given number of digits.
     * @param increment The number to be added to the event.
     */
    public abstract void incrementRef(int increment);
}
