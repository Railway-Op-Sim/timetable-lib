package net.danielgill.ros.service.event;

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
}
