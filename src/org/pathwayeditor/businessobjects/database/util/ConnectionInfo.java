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
	private String url=IConnectionInfo.DATABASE_DEFAULT_URL;
	private String repositoryName;
	private String driverName="org.hsqldb.jdbcDriver";
	private String dialect="org.hibernate.dialect.HSQLDialect";
	private String sessionContext="thread";
	
	//TODO NH - remove this constructor when we have multiple repositories
	public ConnectionInfo(){
		
	}
	//TODO NH - there are more hibernate properties which can be specified; in future a larger constructor will probably be necessary
	public ConnectionInfo(String userName, String password, String url, String repositoryName, String driverName, String dialect, String sessionContext){
		this.userName=userName;
		this.password=password;
		this.url=url;
		this.repositoryName=repositoryName;
		this.driverName=driverName;
		this.dialect=dialect;
		this.sessionContext=sessionContext;
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
	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.database.util.IConnectionInfo#getDialect()
	 */
	public String getDialect() {
		return dialect;
	}
	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.database.util.IConnectionInfo#getSessionContext()
	 */
	public String getSessionContext() {
		return sessionContext;
	}

}
