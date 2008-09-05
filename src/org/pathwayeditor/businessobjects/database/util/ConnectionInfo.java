/**
 * 
 */
package org.pathwayeditor.businessobjects.database.util;

/**
 * @author nhanlon
 * The default settings for ConnectionInfo are for the Hsql Database
 */
public class ConnectionInfo implements IConnectionInfo{
	private String userName="sa";
	private String password="";
	private String url=MakeNewDatabaseDropOld.CONNECTION_URL;
	private String repositoryName;
	private String driverName="org.hsqldb.jdbcDriver";
	
	public ConnectionInfo(){
		
	}

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
		return REPOSITORY_DEFAULT_NAME;
	}
	public String getDriverName() {
		return this.driverName;
	}

}
