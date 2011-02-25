/*
 * Copyright 2009-2011, Court of the University of Edinburgh
 */
package org.pathwayeditor.figurevm;

/**
 * 
 * IInterpreterErrorHandler
 *
 * @author Stuart Moodie
 *
 */
public interface IInterpreterErrorHandler {
	public enum ErrorCode {	UNEXPECTED_TYPE  };
	
	
	void reportError(String msg);


	void reportError(ErrorCode unexpected_type, Value value);
	
}
