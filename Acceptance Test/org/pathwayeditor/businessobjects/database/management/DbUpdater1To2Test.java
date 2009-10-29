
package org.pathwayeditor.businessobjects.database.management;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Test;
import org.pathwayeditor.businessobjects.management.IConnectionInfo;
import org.pathwayeditor.businessobjects.management.IDbUpgrader;
import org.pathwayeditor.testutils.PojoXlsTester;

/**
 * @author smoodie
 *
 */
public class DbUpdater1To2Test extends PojoXlsTester {
	private static final int TGT_VERSION = 2;
	private DbUpdater1To2 testInstance;
	
	
	@Override
	protected void additionalSetup(){
		try {
			IDbConnectionManager stubManager = new StubDbManager();
			this.testInstance = new DbUpdater1To2();
			this.testInstance.setConnectionManager(stubManager);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	protected void additionalTeardown(){
		this.testInstance = null;
	}
	
	@Test
	public void testDoUpgrade() throws SQLException, Exception {
		this.testInstance.setRequestedTargetVersion(TGT_VERSION);
		assertTrue("Cannot do upgrade", this.testInstance.canDoUpgrade());
		assertTrue("Cannot do upgrade", this.testInstance.isUpgradeRequired());
		this.testInstance.doUpgrade();
		assertFalse("Cannot do upgrade", this.testInstance.isUpgradeRequired());
		assertFalse("Cannot do upgrade", this.testInstance.canDoUpgrade());
		compareDatabase("Acceptance Test/org/pathwayeditor/businessobjects/database/management/DBSchemaBuild1ExpectedResults.xls");
	}

	@Test
	public void testCanDoUpgradeWhenNoVersionSet() throws Exception {
		assertFalse("Can't upgrade", this.testInstance.canDoUpgrade());
	}

	@Test
	public void testCanDoUpgradeWhenTgtVersionSet() throws Exception {
		this.testInstance.setRequestedTargetVersion(TGT_VERSION);
		assertTrue("Can't upgrade", this.testInstance.canDoUpgrade());
	}

	@Test
	public void testSetTargetVersion() {
		this.testInstance.setRequestedTargetVersion(TGT_VERSION);
		assertEquals("tgt version not set", TGT_VERSION, this.testInstance.getRequestedTargetVersion());
	}

	@Test
	public void testGetTargetVersion() {
		assertEquals("tgt version not set", IDbUpgrader.VERSION_NOT_SET, this.testInstance.getRequestedTargetVersion());
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
				return DbUpdater1To2Test.this.getConnection().getConnection();
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
