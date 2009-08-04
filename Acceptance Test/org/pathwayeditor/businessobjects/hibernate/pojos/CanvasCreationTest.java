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
package org.pathwayeditor.businessobjects.hibernate.pojos;

import org.junit.Test;
import org.pathwayeditor.businessobjects.management.IMapPersistenceManager;
import org.pathwayeditor.businessobjects.management.IRepositoryPersistenceManager;
import org.pathwayeditor.businessobjects.repository.IMap;
import org.pathwayeditor.bussinessobjects.stubs.notationsubsystem.StubNotationSubSystem;
import org.pathwayeditor.testutils.GenericXlsTester;


/**
 * @author smoodie
 *
 */
public class CanvasCreationTest extends GenericXlsTester {
	private static final String REPOSITORY_NAME ="repo name" ;
	private static final String TEST_MAP_PATH = "/subfolder2/Diagram name2";
	private static final String SOURCE_DATA_FILE = "Acceptance Test/DBConsistencyTestSourceData/DBSourceData.xls";
	private static final String CANVAS_CREATION_CHANGES = "Acceptance Test/DBConsistencyTestValidationData/DBNewCanvasData.xml";
	private IMapPersistenceManager mapContentManager;
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.testutils.GenericTester#doAdditionalSetUp()
	 */
	@Override
	protected void doAdditionalSetUp() {
		IRepositoryPersistenceManager repoManager = this.getRepositoryPersistenceManager();
		IMap map;
		map = (IMap)repoManager.getRepository().findRepositoryItemByPath(TEST_MAP_PATH);
		this.mapContentManager = repoManager.getMapPersistenceManager(map);
		this.mapContentManager.open();
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
