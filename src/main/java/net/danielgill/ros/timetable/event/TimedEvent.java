package net.danielgill.ros.timetable.event;

/**
 * An event class that represents any event which has a time value.
 * @author Daniel Gill
 */
public interface TimedEvent {
    /**
     * Increments the time in the event by a given number of minutes.
     * @param minutes The minutes to be added to the time on the event.
     */
    public abstract void incrementTime(int minutes);
}