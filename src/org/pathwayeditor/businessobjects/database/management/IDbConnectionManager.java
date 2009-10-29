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

package org.pathwayeditor.businessobjects.database.management;

import java.sql.Connection;
import java.sql.SQLException;

import org.pathwayeditor.businessobjects.management.IConnectionInfo;

/**
 * @author smoodie
 *
 */
public interface IDbConnectionManager {

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.management.IDatabaseManager#getConnectionInfo()
	 */
	IConnectionInfo getConnectionInfo();

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.management.IDatabaseManager#isAlive()
	 */
	boolean isAlive();

	boolean doesTestConnectionSucceed() throws SQLException;

	void openConnection() throws SQLException;

	Connection getConnection();

	void closeConnection();

}