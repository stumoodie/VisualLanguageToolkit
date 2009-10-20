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
package org.pathwayeditor.businessobjects.database.management;

import java.sql.SQLException;

import org.pathwayeditor.businessobjects.management.IConnectionInfo;


/**
 * Provides services to startup and shutdown a database.
 * @author smoodie
 *
 */
public interface IDatabaseManager {

	/**
	 * Startup or attempt to startup a database. Not all implementations
	 * will be able to do this, especially if this is connecting to a database server
	 * over a network.
	 * @throws UnsupportedOperationException if <code>isStartUpImplemented() == false</code>.
	 * @throws IllegalStateException if <code>isAlive()</code>. 
	 */
	void startup() throws SQLException;
	
	/**
	 * Tests if the startup operation is provided by this database manager implementation.
	 * @return true is it is provided, false otherwise.
	 */
	boolean isStartupImplemented();
	
	/**
	 * Tests if the database is alive, i.e. that the database is running and this manager
	 * can connect to it.
	 * @return true if the database can be contacted and it is running, false otherwise.  
	 */
	boolean isAlive();
	
	/**
	 * Obtains the information required to connect to this database, via a JDBC connection.
	 * @return the connection information instance, which cannot be null.
	 */
	IConnectionInfo getConnectionInfo();
	
	IDbConnectionManager getConnectionManager();
	
	/**
	 * Shuts down the database typically by issuing a shutdown command to the database.
	 * This is not guaranteed to be successful as the database behaviour is very much 
	 * implementation specific.
	 * @throws IllegalStateException if <code>isAlive() == false</code>.  
	 */
	void shutdown() throws SQLException;
	
}
