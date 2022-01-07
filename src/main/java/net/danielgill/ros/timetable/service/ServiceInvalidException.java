package net.danielgill.ros.timetable.service;

import net.danielgill.ros.timetable.reference.Reference;

/**
 * An exception thrown if a service is invalid.
 * @author Daniel Gill
 * @since v1.2.0-alpha
 */
public class ServiceInvalidException extends Exception {
	private Reference ref;


	/**
	 * A constructor to be used when the service reference is unknown.
	 * @param message The message containing information about the exception.
	 */
	public ServiceInvalidException(String message) {
		super(message);
		this.ref = new Reference("UNKNOWN");
	}

	/**
	 * A constructor which includes the reference of the service.
	 * @param message The message containing information about the exception.
	 * @param ref The reference of the affected service.
	 */
	public ServiceInvalidException(String message, Reference ref) {
		super(message);
		this.ref = ref;
	}

	/**
	 * Returns the reference as a string.
	 * @return The string reference.
	 */
	public String getRef() {
		return this.ref.getRef();
	}

	@Override
	public String toString() {
		return "[" + this.ref.getRef() + "]: " + this.getMessage();
	}
}
