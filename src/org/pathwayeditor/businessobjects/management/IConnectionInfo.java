/**
 * 
 */
package org.pathwayeditor.businessobjects.management;

/**
 * @author nhanlon
 *
 */
public interface IConnectionInfo {
	
	String DEFAULT_REPOSITORY_NAME="Local";
	/**
	 * The database user name
	 * @return the database user name, which cannot be null.
	 */
	public String getUserName();
	
	/**
	 * The database password.
	 * @return the database password, which cannot be null, but can be an empty string of no password is set.
	 */
	public String getPassword();
	
	/**
	 * The URL for the database connection.
	 * @return the url, which cannot be null.
	 */
	public String getUrl();
	
	/**
	 * The name of the repository that is to be opened by this connection.
	 * @return the repository name, which cannot be null.
	 */
	public String getRepositoryName();
	
	/**
	 * The name of the JDBC driver class. 
	 * @return the JDBC driver class, which cannot be null.
	 */
	public String getDriverName();
}
