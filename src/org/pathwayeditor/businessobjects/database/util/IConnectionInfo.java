/**
 * 
 */
package org.pathwayeditor.businessobjects.database.util;

/**
 * @author nhanlon
 *
 */
public interface IConnectionInfo {
	public static final String REPOSITORY_DEFAULT_NAME = "local";
	public static final String DATABASE_DEFAULT_URL = "jdbc:hsqldb:hsql://localhost/epeDev";
	public String getUserName();
	public String getPassword();
	public String getUrl();
	public String getRepositoryName();
	public String getDriverName();
}
