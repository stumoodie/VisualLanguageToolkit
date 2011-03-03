/*
 * Copyright 2009-2011, Court of the University of Edinburgh
 */
package org.pathwayeditor.figure.definition;

/**
 * 
 * IInterpreterErrorHandler is an interface that defines an error handler that is called by the figure definition
 * interpreter. It enables implementation of error handling to be hidden from the interpreter. 
 *
 * @author Stuart Moodie
 *
 */
public interface IInterpreterErrorHandler {
	public enum ErrorCode {	UNEXPECTED_TYPE  };
	
	
	/**
	 * Report that an error has occurred.
	 * @param msg the error message describing the error. 
	 */
	void reportError(String msg);

	/**
	 * Report that an error has occurred together with an error code.
	 * @param errorCode the error code. 
	 * @param value the value associated with the error code.
	 */
	void reportError(ErrorCode errorCode, Value value);
	
}
