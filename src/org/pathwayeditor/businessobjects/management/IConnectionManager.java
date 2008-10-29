/**
 * 
 */
package org.pathwayeditor.businessobjects.management;

/**
 * @author smoodie
 *
 */
public interface IConnectionManager {

	/**
	 * Initialises and opens connections to the database. Clients should test <code>isOpen()</code>
	 * to see if open succeeded. 
	 * @throws IllegalStateException if <code>isOpen()</code> is true.
	 */
	void open();
	
	/**
	 * Tests if connections are already open. 
	 * @return true if they are, false otherwise.
	 */
	boolean isOpen();
	
	/**
	 * Closes connections to the database. This will fail silently, but
	 * clients can check the <code>isOpen()</code> method to see if it succeeded.
	 */
	void close();
	
}
