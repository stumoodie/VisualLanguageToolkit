/**
 * 
 */
package org.pathwayeditor.businessobjects.hibernate.helpers;

/**
 * 
 * The application notation subsystem is inconsistent with the persisted notation definition. 
 * @author smoodie
 *
 */
public class InconsistentNotationDefinitionException extends Exception {
	private static final long serialVersionUID = 4100750464005284577L;

	/**
	 * 
	 */
	public InconsistentNotationDefinitionException() {
	}

	/**
	 * @param message
	 */
	public InconsistentNotationDefinitionException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public InconsistentNotationDefinitionException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public InconsistentNotationDefinitionException(String message, Throwable cause) {
		super(message, cause);
	}

}
