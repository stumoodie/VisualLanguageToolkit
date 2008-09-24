/**
 * 
 */
package org.pathwayeditor.businessobjects.hibernate.pojos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Iterator;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.pathwayeditor.businessobjects.repository.IFolder;
import org.pathwayeditor.businessobjects.repository.IMap;
import org.pathwayeditor.businessobjects.repository.IRepository;
import org.pathwayeditor.businessobjects.repository.IRootFolder;
import org.pathwayeditor.businessobjects.repository.ISubFolder;

/**
 * @author nhanlon NH - this is a business logic test so no actual database
 *         activity will be tested - see corresponding tests in
 *         FolderBusinessLogicDatabaseTest
 * 
 * Tests the IFolder interface
 */
public class FolderBusinessLogicTest {

	private static final String FANDABIDOSI = "fandabidosi";
	private static final String JIMMY_KRANKIE = "JimmyKrankie";
	private static final String EXPECTED_REPO_NAME = "test repo name";
	private static final int EXPECTED_REPO_BUILD_NUM = 999;
	private static final String EXPECTED_REPO_DESCRIPTION = "test repo descn";
	private static final String CHILD_ONE_NAME = "one";
	private static final String CHILD_TWO_NAME = "two";
	private static final String CHILD_THREE_NAME = "three";
	private static final String CHILD_FOUR_NAME = "four";
	private static final String TEST_CHILD_NAME = "testChild";
	private static final String TEST_FOLDER_NAME = "testFolder";
	private static final int INITIAL_NUM_ROOT_SUBFOLDERS = 1;
	private static final int INITIAL_NUM_CHILDFOUR_SUBFOLDERS = 0;
	private static final String TEST_MAP_NAME = "testMap";

	private IRepository repo;
	private ISubFolder childOne;
	private ISubFolder childTwo;
	private ISubFolder childThree;
	private ISubFolder childFour;
	private IRootFolder root;

	@Before
	public void setUp() {
		this.repo = new HibRepository(EXPECTED_REPO_NAME, EXPECTED_REPO_DESCRIPTION, EXPECTED_REPO_BUILD_NUM);
		root = repo.getRootFolder();
		childOne = root.createSubfolder(CHILD_ONE_NAME);
		childTwo = childOne.createSubfolder(CHILD_TWO_NAME);
		childThree = childTwo.createSubfolder(CHILD_THREE_NAME);
		childFour = childThree.createSubfolder(CHILD_FOUR_NAME);
	}

	@After
	public void tearDown(){
		this.repo = null;
		this.root = null;
		this.childOne = null;
		this.childTwo = null;
		this.childThree = null;
		this.childFour = null;
	}
	
	@Test
	public void canMoveFolderUniqueNameTest() {
		ISubFolder child = this.childFour.createSubfolder(CHILD_ONE_NAME);
		assertTrue(childOne.canMoveSubfolder(child));
		assertFalse(root.canMoveSubfolder(child));
	}

	@Test
	public void canMoveFolderFalseWHenFolderNullTest() {
		ISubFolder child = this.childFour.createSubfolder(CHILD_ONE_NAME);
		assertTrue(childOne.canMoveSubfolder(child));
		child = null;
		assertFalse(childOne.canMoveSubfolder(child));
	}

	@Test
	public void canMoveFolderCircularChildSimpleTest() {
		ISubFolder child = this.root.createSubfolder(TEST_CHILD_NAME);
		assertTrue(child.canMoveSubfolder(childOne));
		assertFalse(childTwo.canMoveSubfolder(childOne));
	}

	@Test
	public void canMoveFolderCircularChildRecursionTest() {
		ISubFolder folder = this.root.createSubfolder(TEST_FOLDER_NAME);
		assertTrue(childThree.canMoveSubfolder(folder));
		assertFalse(childFour.canMoveSubfolder(childOne));
		assertTrue(folder.canMoveSubfolder(childFour));
	}

	@Test
	public void canUseSubfolderNameUniqueNameTest() {
		assertTrue(childOne.canUseSubfolderName(CHILD_ONE_NAME));
		assertFalse(root.canUseSubfolderName(CHILD_ONE_NAME));
	}

	@Test
	public void canUseSubfolderNameNotCaseSensitiveTest() {
		assertTrue(childOne.canUseSubfolderName(CHILD_ONE_NAME.toUpperCase()));
		assertFalse(root.canUseSubfolderName(CHILD_ONE_NAME.toUpperCase()));
	}

	@Test
	public void canUseSubfolderNameNullOrSlashdotThrowsIllegalArgumentExceptionTest() {
		assertTrue(childOne.canUseSubfolderName(CHILD_ONE_NAME));
		try {
			root.canUseSubfolderName(".");
			fail("should throw illegal arg for null or slashdot");
		} catch (IllegalArgumentException e) {
			;
		}
		try {
			root.canUseSubfolderName("\\");
			fail("should throw illegal arg for null or slashdot");
		} catch (IllegalArgumentException e) {
			;
		}
		try {
			root.canUseSubfolderName(null);
			fail("should throw illegal arg for null or slashdot");
		} catch (IllegalArgumentException e) {
			;
		}
	}

	@Test
	public void containsSubFolderDirectChildTest() {
		ISubFolder child = this.root.createSubfolder(TEST_CHILD_NAME);
		assertFalse(childOne.containsSubfolder(child));
		assertTrue(root.containsSubfolder(child));
	}

	@Test
	public void containsSubFolderChildTreeTest() {
		assertTrue(root.isDescendent(childFour));
	}

	@Test
	public void testCreateSubFolderSetsName() {
		childFour.createSubfolder(TEST_CHILD_NAME);
		assertTrue(childFour.getSubFolderIterator().next().getName().equals(TEST_CHILD_NAME));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCreateSubFolderChecksNameUnique() {
		ISubFolder testFolder = root.createSubfolder(CHILD_TWO_NAME);
		root.createSubfolder(CHILD_THREE_NAME);
		assertEquals("expected num folder", INITIAL_NUM_ROOT_SUBFOLDERS+2, root.getNumSubFolders());
		assertTrue("has child two subfolder", root.containsSubfolder(testFolder));
		root.createSubfolder(CHILD_TWO_NAME);
	}

	@Test
	public void testMoveSubFolderAddsSubFolder() {
		assertTrue(root.getNumSubFolders() == INITIAL_NUM_ROOT_SUBFOLDERS);
		ISubFolder child = root.createSubfolder(TEST_CHILD_NAME);
		childFour.moveSubfolder(child);
		assertTrue(childFour.getNumSubFolders() == INITIAL_NUM_CHILDFOUR_SUBFOLDERS+1);
		assertEquals(child, childFour.getSubFolderIterator().next());
	}

	@Test
	public void testMoveSubFolderAddsParentToSubFolderCopy() {
		assertTrue(root.getNumSubFolders() == INITIAL_NUM_ROOT_SUBFOLDERS);
		ISubFolder child = root.createSubfolder(TEST_CHILD_NAME);
		ISubFolder copy = childFour.moveSubfolder(child);
		assertEquals(childFour, copy.getParent());
	}

	@Test
	public void testMoveSubFolderAddsMapsToSubFolder() {
		ISubFolder child = root.createSubfolder(TEST_CHILD_NAME);
		IMap d = child.createMap(TEST_MAP_NAME);
		childFour.moveSubfolder(child);
		assertEquals(d, childFour.getSubFolderIterator().next()
				.getMapIterator().next());
	}

	@Test
	public void testCopySubFolderCopiesWholeSubTree() {
		ISubFolder folder = root.createSubfolder(TEST_FOLDER_NAME);
		folder.createCopyOfSubfolder(childOne);
		Iterator<ISubFolder> iter = folder.getSubFolderIterator(); 
		ISubFolder sub = iter.next();
		assertTrue(sub.getName().equals(CHILD_ONE_NAME));
		sub = sub.getSubFolderIterator().next();
		assertTrue(sub.getName().equals(CHILD_TWO_NAME));
		sub = sub.getSubFolderIterator().next();
		assertTrue(sub.getName().equals(CHILD_THREE_NAME));
	}

	@Test
	public void testCopySubFolderMakesCopyWhichIsNotEqualToOriginal() {
		ISubFolder folder = root.createSubfolder(TEST_FOLDER_NAME);
		folder.createCopyOfSubfolder(childOne);
		Iterator<ISubFolder> iter = folder.getSubFolderIterator(); 
		ISubFolder sub = iter.next();
		assertFalse(sub.equals(childOne));
		sub = sub.getSubFolderIterator().next();
		assertFalse(sub.equals(childTwo));
		sub = sub.getSubFolderIterator().next();
		assertFalse(sub.equals(childThree));
	}

	@Test
	public void testCopySubFolderAlsoCopiesMapsAndTheMapsAreNotEqualToOriginals() {
		ISubFolder folder = root.createSubfolder(TEST_FOLDER_NAME);
		ISubFolder child = root.createSubfolder(TEST_CHILD_NAME);
		IMap d = child.createMap(TEST_MAP_NAME);
		assertEquals(d, child.getMapIterator().next());
		folder.createCopyOfSubfolder(child);
		assertTrue(d.getDescription().equals(
				folder.getSubFolderIterator().next().getMapIterator().next().getDescription()));
		assertFalse(d.equals(folder.getSubFolderIterator().next()
				.getMapIterator().next()));
	}

	@Test
	public void testRemoveSubFolderRemovesSubFolderAsDirectChild() {
		ISubFolder folder = root.createSubfolder(TEST_FOLDER_NAME);
		ISubFolder child = folder.createSubfolder(TEST_CHILD_NAME);
		folder.removeSubfolder(child);
		assertEquals(0, folder.getNumSubFolders());
	}

	@Test(expected=IllegalArgumentException.class)
	public void testRemoveSubFolderRemovesSubFolderAsInDirectChild() {
		ISubFolder sub = childTwo.getSubFolderIterator().next();
		assertTrue(sub.getName().equals(CHILD_THREE_NAME));
		root.removeSubfolder(sub);
	}

	@Test
	public void testRemoveMapHappyCase() {
		IMap m = childOne.createMap(TEST_MAP_NAME);
		assertEquals(1,childOne.getNumMaps());
		assertTrue(childOne.containsMap(m));
		childOne.removeMap(m);
		assertEquals(0, childOne.getNumMaps());
		assertFalse(childOne.containsMap(m));
	}

	@Test (expected = IllegalArgumentException.class)
	public void testRemoveMapThrowsIllegalArgWhenMapIsNull() {
		childOne.removeMap(null);
	}

	@Test (expected = IllegalArgumentException.class)
	public void testRemoveMapThrowsIllegalArgWhenMapNotChild() {
		IMap m = root.createMap(TEST_MAP_NAME);
		childOne.removeMap(m);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCanrenameSubFolderFailsWhenSubFolderNotChild() {
		ISubFolder folder = this.root.createSubfolder(TEST_FOLDER_NAME);
		ISubFolder folder2 = this.root.createSubfolder(TEST_CHILD_NAME);
		folder.canRenameSubfolder(folder2, JIMMY_KRANKIE);
	}

	@Test
	public void testCanRenameSubFolderNameIsIllegal() {
		ISubFolder folder = this.root.createSubfolder(TEST_FOLDER_NAME);
		ISubFolder folder2 = folder.createSubfolder(TEST_CHILD_NAME);
		assertTrue(folder.canRenameSubfolder(folder2, JIMMY_KRANKIE));
		try {
			folder.canRenameSubfolder(folder2, ".");
			fail("should throw illegal arg for null or slashdot");
		} catch (IllegalArgumentException e) {
			;
		}
		try {
			folder.canRenameSubfolder(folder2, "\\");
			fail("should throw illegal arg for null or slashdot");
		} catch (IllegalArgumentException e) {
			;
		}
		try {
			folder.canRenameSubfolder(folder2, null);
			fail("should throw illegal arg for null or slashdot");
		} catch (IllegalArgumentException e) {
			;
		}
	}

	@Test
	public void testCanrenameSubFolderWhenSubFolderIsChildNewNameUnused() {
		ISubFolder folder = this.root.createSubfolder(TEST_FOLDER_NAME);
		ISubFolder folder2 = folder.createSubfolder(TEST_CHILD_NAME);
		assertTrue(folder.canRenameSubfolder(folder2, JIMMY_KRANKIE));
	}

	@Test
	public void testCanrenameSubFolderWhenSubFolderIsChildNewNameUsed() {
		ISubFolder folder = this.root.createSubfolder(TEST_FOLDER_NAME);
		ISubFolder folder2 = folder.createSubfolder(TEST_CHILD_NAME);
		assertTrue(folder.canRenameSubfolder(folder2, JIMMY_KRANKIE));
		folder.createSubfolder(JIMMY_KRANKIE);
		assertFalse(folder.canRenameSubfolder(folder2, JIMMY_KRANKIE));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testRenameSubFolderFailsWhenSubFolderNotChild() {
		ISubFolder folder = this.root.createSubfolder(TEST_FOLDER_NAME);
		ISubFolder folder2 = this.root.createSubfolder(TEST_CHILD_NAME);
		folder.renameSubfolder(folder2, JIMMY_KRANKIE);
	}

	@Test
	public void testRenameSubFolderNameIsIllegal() {
		ISubFolder folder = this.root.createSubfolder(TEST_FOLDER_NAME);
		ISubFolder folder2 = this.root.createSubfolder(TEST_CHILD_NAME);
		try {
			folder.renameSubfolder(folder2, ".");
			fail("should throw illegal arg for null or slashdot");
		} catch (IllegalArgumentException e) {
			;
		}
		try {
			folder.renameSubfolder(folder2, "\\");
			fail("should throw illegal arg for null or slashdot");
		} catch (IllegalArgumentException e) {
			;
		}
		try {
			folder.renameSubfolder(folder2, null);
			fail("should throw illegal arg for null or slashdot");
		} catch (IllegalArgumentException e) {
			;
		}
	}

	@Test
	public void testRenameSubFolderWhenSubFolderIsChildNewNameUnused() {
		ISubFolder folder = this.root.createSubfolder(TEST_FOLDER_NAME);
		ISubFolder folder2 = folder.createSubfolder(TEST_CHILD_NAME);
		folder.renameSubfolder(folder2, JIMMY_KRANKIE);
		assertTrue(folder2.getName().equals(JIMMY_KRANKIE));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testRenameSubFolderWhenSubFolderIsChildNewNameUsed() {
		ISubFolder folder = this.root.createSubfolder(TEST_FOLDER_NAME);
		ISubFolder folder2 = this.root.createSubfolder(TEST_CHILD_NAME);
		folder.createSubfolder(JIMMY_KRANKIE);
		folder.renameSubfolder(folder2, JIMMY_KRANKIE);
		assertFalse(folder2.getName().equals(JIMMY_KRANKIE));
	}

	@Test
	public void testNumMaps() {
		assertEquals(0, childOne.getNumMaps());
		childOne.createMap(TEST_MAP_NAME);
		assertEquals(1, childOne.getNumMaps());
	}

	@Test
	public void testNumSubFolders() {
		assertEquals(0, childFour.numSubFolders());
		assertEquals(1, childOne.numSubFolders());
	}

	@Test
	public void testGetMapIteratorCannotBeNull() {
		assertNotNull(childOne.getMapIterator());
	}

	@Test
	public void testGetMapIteratorIteratesOverMaps() {
		childOne.createMap(JIMMY_KRANKIE);
		childOne.createMap(FANDABIDOSI);
		Iterator<IMap> it = childOne.getMapIterator();
		{
			String nextFolderName = it.next().getName();
			assertTrue(nextFolderName.equals(JIMMY_KRANKIE) ? true : nextFolderName.equals(FANDABIDOSI));
		}
		{
			String nextFolderName = it.next().getName();
			assertTrue(nextFolderName.equals(JIMMY_KRANKIE) ? true : nextFolderName.equals(FANDABIDOSI));
		}
		assertFalse("iterator exhausted", it.hasNext());
	}

	@Test
	public void testCanUseMapNameHappyCase() {
		assertTrue(childOne.canUseMapName(JIMMY_KRANKIE));
	}

	@Test
	public void testCanUseMapNameIsNotCaseSensitive() {
		assertTrue(childOne.canUseMapName(JIMMY_KRANKIE));
		childOne.createMap(JIMMY_KRANKIE);
		assertFalse(childOne.canUseMapName(JIMMY_KRANKIE));
		assertFalse(childOne.canUseMapName(JIMMY_KRANKIE.toUpperCase()));
	}

	@Test
	public void testCanUseMapNameMalformed() {
		assertTrue(childOne.canUseMapName(JIMMY_KRANKIE));
		assertFalse(childOne.canUseMapName(null));
		assertFalse(childOne.canUseMapName("."));
		assertFalse(childOne.canUseMapName("\\"));
	}

	@Test
	public void testCreateMapHappyCase() {
		childOne.createMap(JIMMY_KRANKIE);
		assertTrue(mapExistsCalled(childOne, JIMMY_KRANKIE));
	}

	@Test
	public void testCreateMapMalformed() {
		childOne.createMap(JIMMY_KRANKIE);
		assertTrue(mapExistsCalled(childOne, JIMMY_KRANKIE));
		try {
			childOne.createMap(".");
			fail("should throw illegal arg for null or slashdot");
		} catch (IllegalArgumentException e) {
			;
		}
		try {
			childOne.createMap("\\");
			fail("should throw illegal arg for null or slashdot");
		} catch (IllegalArgumentException e) {
			;
		}
		try {
			childOne.createMap(null);
			fail("should throw illegal arg for null or slashdot");
		} catch (IllegalArgumentException e) {
			;
		}
	}

	@Test
	public void testCreateMapNameAlreadyInUseThrowsIllegalArgument() {
		childOne.createMap(JIMMY_KRANKIE);
		assertTrue(mapExistsCalled(childOne, JIMMY_KRANKIE));
		try {
			childOne.createMap(JIMMY_KRANKIE);
			fail("should throw illegal arg for map already exists with same name");
		} catch (IllegalArgumentException e) {
			;
		}
	}

	@Test
	public void testContainsMapTrue() {
		IMap newMap = childOne.createMap(JIMMY_KRANKIE);
		assertTrue(childOne.containsMap(newMap));
	}

	@Test
	public void testContainsMapFalse() {
		IMap newMap = childFour.createMap(JIMMY_KRANKIE);
		assertFalse(childOne.containsMap(newMap));
	}

	@Test
	public void testContainsMapNull() {
		HibMap newMapDiagram = null;
		assertFalse(childOne.containsMap(newMapDiagram));
	}

	@Test
	public void createCopyOfMapHappyCaseCopiesName() {
		IMap newMap = childFour.createMap(JIMMY_KRANKIE);
		assertFalse(mapExistsCalled(childOne, JIMMY_KRANKIE));
		childOne.createCopyOfMap(newMap);
		assertTrue(mapExistsCalled(childOne, JIMMY_KRANKIE));
	}

	@Test
	public void createCopyOfMapHappyCaseCopiesDescription() {
		IMap newMap = childFour.createMap(JIMMY_KRANKIE);
		newMap.setDescription(FANDABIDOSI);
		childOne.createCopyOfMap(newMap);
		assertEquals(FANDABIDOSI, getMapInFolderCalled(childOne, JIMMY_KRANKIE)
				.getDescription());
	}

	@Test
	public void createCopyOfMapHappyCaseCopyDoesNotEqualOriginal() {
		IMap newMap = childFour.createMap(JIMMY_KRANKIE);
		IMap copy = childOne.createCopyOfMap(newMap);
		assertFalse(newMap.equals(copy));
	}

	@Test
	public void testCreateCopyOfMapHappyCaseCopiesMapCanvas() {
		IMap newMap = childFour.createMap(JIMMY_KRANKIE);
		IMap c = childOne.createCopyOfMap(newMap);
		assertTrue(childOne.containsMap(c));
		assertTrue(childFour.containsMap(newMap));
		assertEquals(newMap.getName(), c.getName());
		assertFalse(newMap.getINode() == c.getINode());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCreateCopyOfMapWhenAlreadyExistsThrowsIllegalArg() {
		IMap newMap = childFour.createMap(JIMMY_KRANKIE);
		childOne.createCopyOfMap(newMap);
		childOne.createCopyOfMap(newMap);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCreateCopyOfMapWhenMapIsNullThrowsIllegalArg() {
		HibMap newHibMapDiagram = null;
		childOne.createCopyOfMap(newHibMapDiagram);
	}

	@Test
	public void testMoveMapHappyCaseAddsMapToNewFolder() {
		IMap newMap = childOne.createMap(JIMMY_KRANKIE);
		assertFalse(childFour.containsMap(newMap));
		childFour.moveMap(newMap);
		assertTrue(childFour.containsMap(newMap));
		assertTrue(mapExistsCalled(childFour, JIMMY_KRANKIE));
	}

	@Test
	public void testMoveMapHappyCaseRemovesMapFromOldFolder() {
		IMap newMap = childOne.createMap(JIMMY_KRANKIE);
		assertTrue(childOne.containsMap(newMap));
		childFour.moveMap(newMap);
		assertFalse(childOne.containsMap(newMap));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testMoveMapThrowsIllegalArgIfMapIsNull() {
		HibMap newMap = null;
		childFour.moveMap(newMap);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testMoveMapThrowsIllegalArgIfMapIsAlreadyContained() {
		IMap newMap = childOne.createMap(JIMMY_KRANKIE);
		childFour.moveMap(newMap);
		childFour.moveMap(newMap);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testMoveMapThrowsIllegalArgWhenMapWithSameNameExists(){
		IMap newMap = childOne.createMap(JIMMY_KRANKIE);
		childFour.createMap(JIMMY_KRANKIE);
		childFour.moveMap(newMap);
	}

	@Test
	public void testCanRenameMapHappyCase() {
		IMap newMap = childOne.createMap(JIMMY_KRANKIE);
		assertTrue(childOne.canRenameMap(newMap, FANDABIDOSI));
		assertFalse(childOne.canRenameMap(newMap, JIMMY_KRANKIE));
	}

	@Test
	public void testCanRenameMapFalseWhenMapIsNullOrNameIsNull() {
		IMap newMap = childOne.createMap(JIMMY_KRANKIE);
		assertTrue(childOne.canRenameMap(newMap, FANDABIDOSI));
		assertFalse(childOne.canRenameMap(null, FANDABIDOSI));
		assertFalse(childOne.canRenameMap(newMap, null));
	}

	@Test
	public void testRenameMapHappyCase() {
		IMap newMap = childOne.createMap(JIMMY_KRANKIE);
		assertFalse(mapExistsCalled(childOne, FANDABIDOSI));
		assertTrue(mapExistsCalled(childOne, JIMMY_KRANKIE));
		childOne.renameMap(newMap, FANDABIDOSI);
		assertTrue(mapExistsCalled(childOne, FANDABIDOSI));
		assertFalse(mapExistsCalled(childOne, JIMMY_KRANKIE));
	}

	@Test
	public void testRenameMapMalformedName() {
		IMap newMap = childOne.createMap(JIMMY_KRANKIE);
		try {
			childOne.renameMap(newMap, ".");
			fail("should throw illegal arg for null or slashdot");
		} catch (IllegalArgumentException e) {
			;
		}
		try {
			childOne.renameMap(newMap, "\\");
			fail("should throw illegal arg for null or slashdot");
		} catch (IllegalArgumentException e) {
			;
		}
		try {
			childOne.renameMap(newMap, null);
			fail("should throw illegal arg for null or slashdot");
		} catch (IllegalArgumentException e) {
			;
		}
	}

	@Test
	public void testGetSubFolderIteratorWhenSubFoldersDoNotExistGivesEmptyIterator() {
		Iterator<? extends ISubFolder> it = childFour.getSubFolderIterator();
		assertNotNull(it);
		assertFalse(it.hasNext());
	}

	@Test
	public void testGetSubFolderIteratorIteratesOverFolders() {
		Iterator<? extends ISubFolder> it = childOne.getSubFolderIterator();
		assertEquals(childTwo, it.next());
	}

	@Test
	public void testGetPathHappyCase() {
		String path = childFour.getPath();
		assertEquals("/one/two/three/four", path);
	}

	@Test
	public void testGetPathRootFolderHappyCase() {
		String path = root.getPath();
		assertEquals("/", path);
	}

	private IMap getMapInFolderCalled(IFolder r, String name) {
		Iterator<IMap> mapIter = r.getMapIterator();
		while(mapIter.hasNext()) {
			IMap map = mapIter.next();
			if (map.getName().equals(name))
				return map;
		}
		return null;
	}

	private boolean mapExistsCalled(IFolder r, String name) {
		Iterator<IMap> mapIter = r.getMapIterator();
		while(mapIter.hasNext()) {
			IMap map = mapIter.next();
			if (map.getName().equals(name))
				return true;
		}
		return false;
	}
}
