package net.danielgill.ros.timetable.event;

import net.danielgill.ros.timetable.reference.Reference;

/**
 * An interface for event types that have a reference.
 * @author Daniel Gill
 */
public interface ReferenceEvent {
    /**
     * Increments the references in the event by a given number of digits.
     * @param increment The number to be added to the event.
     */
    public abstract void incrementRef(int increment);

    public abstract Reference getRef();
}
