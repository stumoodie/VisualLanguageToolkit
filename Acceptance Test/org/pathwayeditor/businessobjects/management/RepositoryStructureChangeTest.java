/**
 * 
 */
package org.pathwayeditor.businessobjects.management;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.pathwayeditor.businessobjects.repository.IMap;
import org.pathwayeditor.businessobjects.repository.IRepository;
import org.pathwayeditor.businessobjects.repository.ISubFolder;
import org.pathwayeditor.testutils.GenericTester;

/**
 * @author ntsorman
 *
 */
public class RepositoryStructureChangeTest extends GenericTester {
	private static final String REPOSITORY_NAME = "repo name";
	private static final String SUBFOLDER1_PATH = "/subfolder1/" ;
	private static final String SUBFOLDER2_PATH = "/subfolder2/" ;
	private static final String MAP1_PATH = "/subfolder2/Diagram name2";
	private static final String NEW_MAP_NAME = "map3";
	private static final String NEW_FOLDER_NAME = "folder4";
	private IRepository repository;
	private ISubFolder subFolder2;
	private IMap map2;
	private ISubFolder subFolder1;
	

	@Test
	public void testDeleteMap () throws Exception {
		subFolder2.removeMap(map2);

		assertNull("Not attached to repo", map2.getRepository());
		assertNull("Not attached to parent folder", map2.getOwner());
		
		this.getRepositoryPersistenceManager().synchronise();
		
		this.compareDatabase("Acceptance Test/org/pathwayeditor/businessobjects/management/ExpectedDeletedMapData.xml");
	}

	@Test
	public void testDeleteMap1 () throws Exception {
		IMap map1 = (IMap)this.repository.findRepositoryItemByPath("/subfolder1/Diagram name");
		subFolder1.removeMap(map1);

		assertNull("Not attached to repo", map1.getRepository());
		assertNull("Not attached to parent folder", map1.getOwner());
		assertFalse("Missing from orginal parent", subFolder1.containsMap(map1));
		
		this.getRepositoryPersistenceManager().synchronise();
		
		this.compareDatabase("Acceptance Test/org/pathwayeditor/businessobjects/management/ExpectedDeletedMap1Data.xml");
	}

	@Test
	public void testMoveMap () throws Exception {
		subFolder1.moveMap(map2);

		assertEquals("Same repo", this.repository, map2.getRepository());
		assertFalse("Removed from folder2", this.subFolder2.containsMap(map2.getName()));
		assertEquals("Parent folder is subfolder1", this.subFolder1, map2.getOwner());
		
		this.getRepositoryPersistenceManager().synchronise();
		
		this.compareDatabase("Acceptance Test/org/pathwayeditor/businessobjects/management/ExpectedMovedMapData.xml");
	}

	@Test
	public void testCopyMap () throws Exception {
		IMap newMap = subFolder1.createCopyOfMap(map2);

		assertEquals("Same repo", this.repository, map2.getRepository());
		assertTrue("Remains in folder2", this.subFolder2.containsMap(map2));
		assertTrue("Copy in folder1", this.subFolder1.containsMap(newMap));
		
		this.getRepositoryPersistenceManager().synchronise();
		
		this.compareDatabase("Acceptance Test/org/pathwayeditor/businessobjects/management/ExpectedCopiedMapData.xml");
	}

	@Test
	public void testCreateAndDeleteMap () throws Exception {
		IMap newMap = subFolder1.createMap(NEW_MAP_NAME);
		assertEquals("Same repo", this.repository, newMap.getRepository());
		assertTrue("Contained in folder1", this.subFolder1.containsMap(newMap));
		this.getRepositoryPersistenceManager().synchronise();
		this.subFolder1.removeMap(newMap);
		assertNull("Null repo", newMap.getRepository());
		assertFalse("Contained in folder1", this.subFolder1.containsMap(newMap));
		this.getRepositoryPersistenceManager().synchronise();
		
		this.compareDatabase("Acceptance Test/org/pathwayeditor/businessobjects/management/ExpectedRecreatedMapData.xml");
	}

	@Test
	public void testCreateAndDeleteFolder () throws Exception {
		ISubFolder newSubfolder = subFolder1.createSubfolder(NEW_FOLDER_NAME);
		assertEquals("Same repo", this.repository, newSubfolder.getRepository());
		assertTrue("Contained in folder1", this.subFolder1.containsSubfolder(newSubfolder));
		this.getRepositoryPersistenceManager().synchronise();
		this.subFolder1.removeSubfolder(newSubfolder);
		assertNull("Null repo", newSubfolder.getRepository());
		assertFalse("Contained in folder1", this.subFolder1.containsSubfolder(newSubfolder));
		this.getRepositoryPersistenceManager().synchronise();
		
		this.compareDatabase("Acceptance Test/org/pathwayeditor/businessobjects/management/ExpectedRecreatedFolderData.xml");
	}

	@Test
	public void testCreateMap () throws Exception {
		IMap newMap = subFolder1.createMap(NEW_MAP_NAME);
		assertEquals("Same repo", this.repository, newMap.getRepository());
		assertTrue("Contained in folder1", this.subFolder1.containsMap(newMap));
		this.getRepositoryPersistenceManager().synchronise();
		
		this.compareDatabase("Acceptance Test/org/pathwayeditor/businessobjects/management/ExpectedCreatedMapData.xml");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pathwayeditor.integrationtestutils.GenericTester#doAdditionalSetUp()
	 */
	@Override
	protected void doAdditionalSetUp() {
		repository = this.getRepositoryPersistenceManager().getRepository();
		subFolder1 = (ISubFolder) repository.getFolderByPath(SUBFOLDER1_PATH);
		subFolder2 = (ISubFolder) repository.getFolderByPath(SUBFOLDER2_PATH);
		map2 = (IMap)repository.findRepositoryItemByPath(MAP1_PATH);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pathwayeditor.integrationtestutils.GenericTester#getDbUnitDataFilePath()
	 */
	@Override
	protected String getDbUnitDataFilePath() {
		return "Acceptance Test/org/pathwayeditor/businessobjects/management/SeedData.xml";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pathwayeditor.integrationtestutils.GenericTester#getTestRepositoryName()
	 */
	@Override
	protected String getTestRepositoryName() {
		return REPOSITORY_NAME;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pathwayeditor.testutils.GenericTester#doAdditionalTearDown()
	 */
	@Override
	protected void doAdditionalTearDown() {
		;
	}
}

 