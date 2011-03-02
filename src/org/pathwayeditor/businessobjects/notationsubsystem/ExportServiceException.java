/*
Copyright 2009-2011, Court of the University of Edinburgh
*/
package org.pathwayeditor.businessobjects.notationsubsystem;

/**
 * 
 * ExportServiceException is a class that implements an exception thrown when there
 * is a problem with the export service.
 *
 * @author Stuart Moodie
 *
 */
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
