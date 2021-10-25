package net.danielgill.ros.service.event;

/**
 * An exception which is thrown if an event is invalid.
 * @author Daniel Gill
 */
public class EventInvalidException extends Exception {
	String eventString;

	/**
	 * Creates a new instance.
	 * @param eventString The String of the broken event, which can be printed at a later stage.
	 */
	public EventInvalidException(String eventString) {
		this.eventString = eventString;
	}

	/**
	 * Returns the string of the broken event.
	 * @return The String of the broken event.
	 */
	public String getEventString() {
		return eventString;
	}
}
