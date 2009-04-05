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
