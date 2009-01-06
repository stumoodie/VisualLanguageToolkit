/**
 * 
 */
package org.pathwayeditor.businessobjects.hibernate.pojos;

import org.junit.Test;
import org.pathwayeditor.businessobjects.management.IMapPersistenceManager;
import org.pathwayeditor.businessobjects.management.IRepositoryPersistenceManager;
import org.pathwayeditor.businessobjects.management.PersistenceManagerException;
import org.pathwayeditor.businessobjects.repository.IMap;
import org.pathwayeditor.bussinessobjects.stubs.notationsubsystem.StubNotationSubSystem;
import org.pathwayeditor.testutils.GenericTester;


/**
 * @author smoodie
 *
 */
public class CanvasCreationTest extends GenericTester {
	private static final String REPOSITORY_NAME ="repo name" ;
	private static final String TEST_MAP_PATH = "/subfolder2/Diagram name2";
	private static final String SOURCE_DATA_FILE = "Acceptance Test/DBConsistencyTestSourceData/DBSourceData.xml";
	private static final String CANVAS_CREATION_CHANGES = "Acceptance Test/DBConsistencyTestValidationData/DBNewCanvasData.xml";
	private IMapPersistenceManager mapContentManager;
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.testutils.GenericTester#doAdditionalSetUp()
	 */
	@Override
	protected void doAdditionalSetUp() {
		try {
			IRepositoryPersistenceManager repoManager = this.getRepositoryPersistenceManager();
			IMap map;
			map = (IMap)repoManager.getRepository().findRepositoryItemByPath(TEST_MAP_PATH);
			this.mapContentManager = repoManager.getMapPersistenceManager(map);
			this.mapContentManager.open();
		} catch (PersistenceManagerException e) {
			throw new RuntimeException(e);
		}
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.testutils.GenericTester#doAdditionalTearDown()
	 */
	@Override
	protected void doAdditionalTearDown() {
		if(mapContentManager != null){
			mapContentManager.close(true);
		}
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.testutils.GenericTester#getDbUnitDataFilePath()
	 */
	@Override
	protected String getDbUnitDataFilePath() {
		return SOURCE_DATA_FILE;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.testutils.GenericTester#getTestRepositoryName()
	 */
	@Override
	protected String getTestRepositoryName() {
		return REPOSITORY_NAME;
	}

	
	@Test
	public void createCanvasTest() throws Exception{
		this.mapContentManager.createCanvas(new StubNotationSubSystem());
		super.compareDatabase(SOURCE_DATA_FILE, CANVAS_CREATION_CHANGES);
	}
}
