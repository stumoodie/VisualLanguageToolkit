/*
Copyright 2009, Court of the University of Edinburgh

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License. 
*/
/**
 * 
 */
package org.pathwayeditor.businessobjects.database.util;

import org.pathwayeditor.businessobjects.management.IConnectionInfo;

/**
 * @author nhanlon/smoodie
 * The default settings for ConnectionInfo are for the Hsql Database
 */
public class ConnectionInfo implements IConnectionInfo{
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
