package net.danielgill.ros.service.event;

/**
 * An event class that represents any event which has a reference.
 * @author Daniel Gill
 */
public abstract class ReferenceEvent extends TimedEvent {
    public ReferenceEvent(String type) {
        super(type);
    }

    /**
     * Increments the references in the event by a given number of digits.
     * @param increment The number to be added to the event.
     */
    public abstract void incrementRef(int increment);
}
