package org.pathwayeditor.businessobjects.notationsubsystem;

public class ExportServiceException extends Exception {
	private static final long serialVersionUID = 7000244894654454094L;

	public ExportServiceException() {
	}

	public ExportServiceException(String message) {
		super(message);
	}
	
	public ExportServiceException(Throwable cause) {
		super(cause);
	}

	public ExportServiceException(String message, Throwable cause) {
		super(message, cause);
	}
}
