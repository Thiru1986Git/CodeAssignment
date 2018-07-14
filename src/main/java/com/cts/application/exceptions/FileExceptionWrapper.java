package com.cts.application.exceptions;

//Custom Exception class for File exception events 
public class FileExceptionWrapper extends RuntimeException {

	/**
	 * Generated serial.
	 */
	private static final long serialVersionUID = 1;

	private String message = null;

	/**
	 * Empty constructor.Calling super class constructor
	 */
	public FileExceptionWrapper() {
		super();
	}

	/**
	 * Constructor with exception message
	 * 
	 * @param message.
	 *            passing the message to super class and message to set.
	 */
	public FileExceptionWrapper(String message) {
		super(message);
		this.message = message;
	}

	/**
	 * Constructor with Throwable object
	 * 
	 * @param cause.passing
	 *            the throwable to super class.
	 */
	public FileExceptionWrapper(Throwable cause) {
		super(cause);
	}

	/**
	 * return the message
	 */
	@Override
	public String toString() {
		return message;
	}

	/**
	 * return the message
	 */
	@Override
	public String getMessage() {
		return message;
	}
}
