package net.danielgill.ros.service.event;

/**
 * An interface for any event that has a time in it, so that it can be incremented for templates.
 * @author Daniel Gill
 */
public interface TimedEvent {
    /**
     * Increments the time in the event by a given number of minutes.
     * @param minutes The minutes to be added to the time on the event.
     */
    public abstract void incrementTime(int minutes);
}
