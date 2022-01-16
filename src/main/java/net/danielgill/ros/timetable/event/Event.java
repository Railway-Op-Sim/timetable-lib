package net.danielgill.ros.timetable.event;

/**
 * An abstract class representing a single Event.
 * @author Daniel Gill
 */
public abstract class Event {
    private final String type;

    /**
     * The constructor which takes a String type.
     * @param type The type of the event as a String.
     */
    protected Event(String type) {
        this.type = type;
    }

    /**
     * Returns the event as a string. Should be overridden.
     * @return A String version of the event.
     */
    @Override
    public abstract String toString();

    /**
     * Returns a copy of the event instance.
     * @param event The event instance to be copied.
     * @return The copied event instance.
     */
    public abstract Event newInstance(Event event);

    /**
     * Validates the event and throws an exception if it is invalid.
     * @throws EventInvalidException Thrown if the event is invalid.
     */
    public abstract void validateEvent() throws EventInvalidException;

    /**
     * Returns the event as a String.
     * @return The string containing the event type.
     */
    public String getType() {
        return type;
    }
}
