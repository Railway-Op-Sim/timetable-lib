package net.danielgill.ros.timetable;

/**
 * An exception thrown if the timetable is invalid.
 * @author Daniel Gill
 */
public class TimetableInvalidException extends Exception {
    /**
     * Creates a new TimetableInvalidException.
     * @param message The message relating to the exception.
     */
    public TimetableInvalidException(String message) {
        super(message);
    }
}
