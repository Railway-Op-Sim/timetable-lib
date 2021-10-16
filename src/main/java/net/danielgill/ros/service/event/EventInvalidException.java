package net.danielgill.ros.service.event;

public class EventInvalidException extends Exception {
	String eventString;

	public EventInvalidException(String eventString) {
		this.eventString = eventString;
	}

	public String getEventString() {
		return eventString;
	}
}
