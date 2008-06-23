package org.pathwayeditor.businessobjects.typedefn;

/**
 * Class defines what in EPE speak is a context. Specifically it is a particular version of a graphical notation. 
 * @author smoodie
 *
 */
public interface IContextAdapter {
	
	/**
	 * A unique identifier string that should be globally unique. Used to identify a context. 
	 * @return The instance of the id, which cannot be null.
	 */
	String getGlobalId();
	
	/**
	 * A name for the context (notation) that does not including version information and which is designed to be
	 * human readable and presented in a User Interface or human readable report. 
	 * @return A string that may contain spaces, but should be plain UniCode text.
	 */
	String getDisplayName();

	/**
	 * 
	 * @return
	 */
	String getVersionString();
	
	int getMajorVersion();
	
	int getMinorVersion();
	
	int getPatchVersion();
}
