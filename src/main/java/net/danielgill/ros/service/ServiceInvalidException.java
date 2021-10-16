package net.danielgill.ros.service;

import net.danielgill.ros.service.reference.Reference;

public class ServiceInvalidException extends Exception {
	private Reference ref;

	public ServiceInvalidException(String message) {
		super(message);
		this.ref = new Reference("UNKNOWN");
	}

	public ServiceInvalidException(String message, Reference ref) {
		super(message);
		this.ref = ref;
	}

	public String getRef() {
		return this.ref.getRef();
	}
}
