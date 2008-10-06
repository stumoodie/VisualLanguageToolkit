/**
 * 
 */
package org.pathwayeditor.businessobjects.database.util;

/**
 * @author nhanlon
 * The default settings for ConnectionInfo are for the Hsql Database
 */
public class ConnectionInfo implements IConnectionInfo{
//	private final String userName="sa";
//	private final String password="";
//	private final String url="jdbc:hsqldb:hsql://localhost/epeDev";
//	private final String repositoryName;
//	private final String driverName="org.hsqldb.jdbcDriver";
	private final String userName;
	private final String password;
	private final String url;
	private final String repositoryName;
	private final String driverName;
	
//	public ConnectionInfo(){
//		
//	}
//
	public ConnectionInfo(String userName, String password, String url, String repositoryName, String driverName){
		if(userName == null || password == null || url == null || repositoryName == null || driverName == null)
			throw new IllegalArgumentException("parmeters cannot be null");
			
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
