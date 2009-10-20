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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Test;
import org.pathwayeditor.businessobjects.management.IConnectionInfo;
import org.pathwayeditor.businessobjects.management.IDbCompatibilityHandler;
import org.pathwayeditor.businessobjects.management.IDbUpgrader;
import org.pathwayeditor.testutils.PojoXlsTester;

/**
 * @author smoodie
 *
 */
public class DbCompatabilityHandlerTest extends PojoXlsTester {
	private static final int TGT_VERSION = 2;

	private IDbCompatibilityHandler testInstance;
	private IDbUpgrader expectedUpgrader = null;
	
	@Override
	protected void additionalSetup() throws Exception {
		this.testInstance = new DbCompatabilityHandler(new StubDbManager(), TGT_VERSION);
		this.expectedUpgrader = new DbUpdater1To2();
	}

	@Override
	public void additionalTeardown() throws Exception {
		this.testInstance = null;
		this.expectedUpgrader = null;
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.database.management.DbCompatabilityHandler#addUpgrader(org.pathwayeditor.businessobjects.management.IDbUpgrader)}.
	 */
	@Test
	public void testAddUpgrader() {
		this.testInstance.addUpgrader(expectedUpgrader);
		assertNotNull("conn set", expectedUpgrader.getConnectionManager());
	}

	@Test
	public void testIsUpgradeRequiredNoUpgraderSet() throws SQLException {
		assertFalse("upgrade not required", this.testInstance.isUpgradeRequired());
	}

	@Test(expected=IllegalStateException.class)
	public void testUpgradeNoUpgraderSet() throws SQLException {
		this.testInstance.upgrade();
	}

	@Test
	public void testUpgradeUpgraderSet() throws Exception {
		this.testInstance.addUpgrader(expectedUpgrader);
		assertTrue("can upgrade", this.testInstance.canDoUpgrade());
		assertTrue("upgrade required", this.testInstance.isUpgradeRequired());
		this.testInstance.upgrade();
		assertFalse("cannot upgrade", this.testInstance.canDoUpgrade());
		assertFalse("upgrade not required", this.testInstance.isUpgradeRequired());
		compareDatabase("Acceptance Test/org/pathwayeditor/businessobjects/database/management/DBSchemaBuild1ExpectedResults.xls");
	}

	@Override
	protected String getDbUnitDataFilePath() {
		return "Acceptance Test/org/pathwayeditor/businessobjects/database/management/DBSchemaBuild1TestData.xls";
	}

	@Override
	protected File getSchemaCreationScript(){
		return new File("Acceptance Test/org/pathwayeditor/businessobjects/database/management/EPE Schema Create.ddl");
	}

	@Override
	protected File getSchemaDropScript(){
		return new File("Acceptance Test/org/pathwayeditor/businessobjects/database/management/EPE Schema Drop.ddl");
	}
	
	private class StubDbManager implements IDbConnectionManager{

		/* (non-Javadoc)
		 * @see org.pathwayeditor.businessobjects.database.management.IDbConnectionManager#closeConnection()
		 */
		public void closeConnection() {
		}

		/* (non-Javadoc)
		 * @see org.pathwayeditor.businessobjects.database.management.IDbConnectionManager#doesTestConnectionSucceed()
		 */
		public boolean doesTestConnectionSucceed() throws SQLException {
			return true;
		}

		/* (non-Javadoc)
		 * @see org.pathwayeditor.businessobjects.database.management.IDbConnectionManager#getConnection()
		 */
		public Connection getConnection() {
			try {
				return DbCompatabilityHandlerTest.this.getConnection().getConnection();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}

		/* (non-Javadoc)
		 * @see org.pathwayeditor.businessobjects.database.management.IDbConnectionManager#getConnectionInfo()
		 */
		public IConnectionInfo getConnectionInfo() {
			throw new UnsupportedOperationException("Not implemented");
		}

		/* (non-Javadoc)
		 * @see org.pathwayeditor.businessobjects.database.management.IDbConnectionManager#isAlive()
		 */
		public boolean isAlive() {
			return true;
		}

		/* (non-Javadoc)
		 * @see org.pathwayeditor.businessobjects.database.management.IDbConnectionManager#openConnection()
		 */
		public void openConnection() throws SQLException {
			
		}
		
	}
}
