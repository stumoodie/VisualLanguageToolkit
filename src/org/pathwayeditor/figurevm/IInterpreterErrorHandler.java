package org.pathwayeditor.figurevm;

public interface IInterpreterErrorHandler {
	public enum ErrorCode {	UNEXPECTED_TYPE  };
	
	
	void reportError(String msg);


	void reportError(ErrorCode unexpected_type, Value value);
	
}
