/**
 * 
 */
package org.pathwayeditor.businessobjects.database.util;

/**
 * @author nhanlon
 *
 */
public class ConnectionInfo implements IConnectionInfo{
	
	private String userName;
	private String password;
	private String url;
	private String repositoryName;
	private String driverName;

	public ConnectionInfo(String userName, String password, String url, String repositoryName, String driverName){
		this.userName=userName;
		this.password=password;
		this.url=url;
		this.repositoryName=repositoryName;
		this.driverName=driverName;
	}
	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.database.util.IConnectionInfo#getPassword()
	 */
	public String getPassword() {
		return password;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.database.util.IConnectionInfo#getURL()
	 */
	public String getUrl() {
		return url;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.database.util.IConnectionInfo#getUserName()
	 */
	public String getUserName() {
		return userName;
	}
	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.database.util.IConnectionInfo#getRepositoryName()
	 */
	public String getRepositoryName() {
		return repositoryName;
	}
	public String getDriverName() {
		return this.driverName;
	}

}
