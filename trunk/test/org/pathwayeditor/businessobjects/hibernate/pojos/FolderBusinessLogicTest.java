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
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
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
	private static final String TEST_CHILD_NAME = "testChild";
	private static final String TEST_FOLDER_NAME = "testFolder";
	private static final int INITIAL_NUM_ROOT_SUBFOLDERS = 1;
	private static final int INITIAL_NUM_CHILDONE_SUBFOLDERS = 1;
	private static final int INITIAL_NUM_CHILDFOUR_SUBFOLDERS = 0;
	private static final String TEST_MAP_NAME = "testMap";

//	static {
//		HibernateUtil.setStubSessionFactoryAsDefault(); // dont use the database
//	}
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
		childTwo = childTwo.createSubfolder("two");
		childThree = childTwo.createSubfolder("three");
		childFour = childThree.createSubfolder("four");
	}

	@Test
	public void canMoveFolderUniqueNameTest() {
		ISubFolder child = this.childFour.createSubfolder("one");
		assertTrue(childOne.canMoveSubfolder(child));
		assertFalse(root.canMoveSubfolder(child));
	}

	@Test
	public void canMoveFolderFalseWHenFolderNullTest() {
		ISubFolder child = this.childFour.createSubfolder("one");
		assertTrue(childOne.canMoveSubfolder((ISubFolder) child));
		child = null;
		assertFalse(childOne.canMoveSubfolder((ISubFolder) child));
	}

	@Test
	public void canMoveFolderCircularChildSimpleTest() {
		ISubFolder child = this.root.createSubfolder("one");
		assertTrue(child.canMoveSubfolder((ISubFolder) childOne));
		assertFalse(childOne.canMoveSubfolder((ISubFolder) childThree));
	}

	@Test
	public void canMoveFolderCircularChildRecursionTest() {
		ISubFolder folder = this.root.createSubfolder(TEST_FOLDER_NAME);
		assertTrue(childThree.canMoveSubfolder(folder));
		assertFalse(folder.canMoveSubfolder(childOne));
		assertTrue(folder.canMoveSubfolder((ISubFolder) childFour));
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
		assertTrue(root.containsSubfolder(childFour));
	}

	@Test
	public void testCreateSubFolderSetsName() {
		childFour.createSubfolder(TEST_CHILD_NAME);
		assertTrue(childFour.getSubFolderIterator().next().getName().equals(TEST_CHILD_NAME));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCreateSubFolderChecksNameUnique() {
		root.createSubfolder("two");
		assertTrue(root.getSubFolderIterator().next().getName().equals(
				"two"));
		root.createSubfolder("three");
		Iterator<ISubFolder> it = root.getSubFolderIterator();
		assertTrue(it.next().getName().equals("two") ? it.next().getName()
				.equals("three") : it.next().getName().equals("two"));
		it = root.getSubFolderIterator();
		assertTrue(it.next().getName().equals("three") ? it.next().getName()
				.equals("two") : it.next().getName().equals("three"));
		root.createSubfolder("two");
	}

	@Test
	public void testMoveSubFolderAddsSubFolder() {
		assertTrue(root.getNumSubFolders() == INITIAL_NUM_ROOT_SUBFOLDERS);
		ISubFolder child = root.createSubfolder(TEST_CHILD_NAME);
		childFour.moveSubfolder(child);
		assertTrue(childFour.getNumSubFolders() == INITIAL_NUM_CHILDFOUR_SUBFOLDERS+1);
		assertEquals(child, childOne.getSubFolderIterator().next());
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
		childOne.moveSubfolder(child);
		assertEquals(d, childOne.getSubFolderIterator().next()
				.getMapIterator().next());
	}

	@Test
	public void testCopySubFolderCopiesWholeSubTree() {
		ISubFolder folder = root.createSubfolder(TEST_FOLDER_NAME);
		folder.createCopyOfSubfolder(childOne);
		Iterator<ISubFolder> iter = folder.getSubFolderIterator(); 
		ISubFolder sub = iter.next();
		assertTrue(sub.getName().equals("one"));
		sub = iter.next();
		assertTrue(sub.getName().equals("two"));
		sub = iter.next();
		assertTrue(sub.getName().equals("three"));
	}

	@Test
	public void testCopySubFolderMakesCopyWhichIsNotEqualToOriginal() {
		ISubFolder folder = root.createSubfolder(TEST_FOLDER_NAME);
		folder.createCopyOfSubfolder(childOne);
		Iterator<ISubFolder> iter = folder.getSubFolderIterator(); 
		ISubFolder sub = iter.next();
		assertTrue(sub.equals(childOne));
		sub = iter.next();
		assertTrue(sub.equals(childTwo));
		sub = iter.next();
		assertTrue(sub.equals(childThree));
	}

	@Test
	public void testCopySubFolderAlsoCopiesMapsAndTheMapsAreNotEqualToOriginals() {
		ISubFolder folder = root.createSubfolder(TEST_FOLDER_NAME);
		ISubFolder child = folder.createSubfolder(TEST_CHILD_NAME);
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
		HibSubFolder folder = new HibSubFolder();
		folder.addSubFolder(childOne);
		HibSubFolder sub = folder.getSubFolders().iterator().next();
		assertTrue(sub.getName().equals("one"));
		folder.removeSubfolder(sub);
		assertEquals(0, folder.getSubFolders().size());
	}

	@Test
	public void testRemoveSubFolderRemovesSubFolderAsInDirectChild() {
		HibSubFolder folder = new HibSubFolder();
		folder.addSubFolder(childOne);
		HibSubFolder sub = folder.getSubFolders().iterator().next();
		HibSubFolder one = sub;
		sub = sub.getSubFolders().iterator().next();
		HibSubFolder two = sub;
		sub = sub.getSubFolders().iterator().next();
		assertTrue(sub.getName().equals("three"));
		folder.removeSubfolder(sub);
		assertEquals(1, folder.getSubFolders().size());
		assertEquals(1, one.getSubFolders().size());
		assertEquals(0, two.getSubFolders().size());
	}

	@Test
	public void testRemoveMapHappyCase() {
		HibSubFolder folder = new HibSubFolder();
		HibMap m = new HibMap();
		folder.addMapDiagram(m);
		assertEquals(1,folder.numMaps());
		folder.removeMap(m);
		assertEquals(0,folder.numMaps());
	}

	@Test (expected = IllegalArgumentException.class)
	public void testRemoveMapThrowsIllegalArgWhenMapIsNull() {
		HibSubFolder folder = new HibSubFolder();
		folder.removeMap(null);
	}

	@Test (expected = IllegalArgumentException.class)
	public void testRemoveMapThrowsIllegalArgWhenMapFolderNull() {
		HibSubFolder folder = new HibSubFolder();
		HibMap m = new HibMap();
		folder.removeMap(m);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testRemoveMapThrowsIllegalArgWhenMapNotChild() {
		HibSubFolder folder = new HibSubFolder();
		HibSubFolder folder2 = new HibSubFolder();
		HibMap m = new HibMap();
		folder2.addMapDiagram(m);
		assertEquals(1,folder2.numMaps());
		folder.removeMap(m);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCanrenameSubFolderFailsWhenSubFolderNotChild() {
		HibSubFolder folder = new HibSubFolder();
		HibSubFolder folder2 = new HibSubFolder();
		folder.canRenameSubfolder(folder2, JIMMY_KRANKIE);
	}

	@Test
	public void testCanRenameSubFolderNameIsIllegal() {
		HibSubFolder folder = new HibSubFolder();
		HibSubFolder folder2 = new HibSubFolder();
		folder.addSubFolder(folder2);
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
		HibSubFolder folder = new HibSubFolder();
		HibSubFolder folder2 = new HibSubFolder();
		folder.addSubFolder(folder2);
		assertTrue(folder.canRenameSubfolder(folder2, JIMMY_KRANKIE));
	}

	@Test
	public void testCanrenameSubFolderWhenSubFolderIsChildNewNameUsed() {
		HibSubFolder folder = new HibSubFolder();
		HibSubFolder folder2 = new HibSubFolder();
		folder.addSubFolder(folder2);
		assertTrue(folder.canRenameSubfolder(folder2, JIMMY_KRANKIE));
		folder.createSubfolder(JIMMY_KRANKIE);
		assertFalse(folder.canRenameSubfolder(folder2, JIMMY_KRANKIE));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testRenameSubFolderFailsWhenSubFolderNotChild() {
		HibSubFolder folder = new HibSubFolder();
		HibSubFolder folder2 = new HibSubFolder();
		folder.renameSubfolder(folder2, JIMMY_KRANKIE);
	}

	@Test
	public void testRenameSubFolderNameIsIllegal() {
		HibSubFolder folder = new HibSubFolder();
		HibSubFolder folder2 = new HibSubFolder();
		folder.addSubFolder(folder2);
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
		HibSubFolder folder = new HibSubFolder();
		HibSubFolder folder2 = new HibSubFolder();
		folder.addSubFolder(folder2);
		folder.renameSubfolder(folder2, JIMMY_KRANKIE);
		assertTrue(folder2.getName().equals(JIMMY_KRANKIE));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testRenameSubFolderWhenSubFolderIsChildNewNameUsed() {
		HibSubFolder folder = new HibSubFolder();
		HibSubFolder folder2 = new HibSubFolder();
		folder.addSubFolder(folder2);
		folder.createSubfolder(JIMMY_KRANKIE);
		folder.renameSubfolder(folder2, JIMMY_KRANKIE);
		assertFalse(folder2.getName().equals(JIMMY_KRANKIE));
	}

	@Test
	public void testNumMaps() {
		assertEquals(0, childOne.numMaps());
		childOne.addMapDiagram(new HibMap());
		assertEquals(1, childOne.numMaps());
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
		new HibMap(childOne, "JIMMY");
		new HibMap(childOne, "KRANKIE");
		Iterator<? extends IMap> it = childOne.getMapIterator();
		assertTrue(it.next().getName().equals("JIMMY") ? it.next().getName()
				.equals("KRANKIE") : it.next().getName().equals("JIMMY"));
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
		HibMap newMapDiagram = new HibMap(childOne, JIMMY_KRANKIE);
		assertTrue(childOne.containsMap(newMapDiagram));
	}

	@Test
	public void testContainsMapFalse() {
		HibMap newMapDiagram = new HibMap();
		assertFalse(childOne.containsMap(newMapDiagram));
	}

	@Test
	public void testContainsMapNull() {
		HibMap newMapDiagram = null;
		assertFalse(childOne.containsMap(newMapDiagram));
	}

	@Test
	public void createCopyOfMapHappyCaseCopiesName() {
		HibMap newHibMapDiagram = new HibMap(childFour,
				JIMMY_KRANKIE);
		assertFalse(mapExistsCalled(childOne, JIMMY_KRANKIE));
		childOne.createCopyOfMap(newHibMapDiagram);
		assertTrue(mapExistsCalled(childOne, JIMMY_KRANKIE));
	}

	@Test
	public void createCopyOfMapHappyCaseCopiesDescription() {
		HibMap newHibMapDiagram = new HibMap(childFour,
				JIMMY_KRANKIE);
		newHibMapDiagram.setDescription(FANDABIDOSI);
		childOne.createCopyOfMap(newHibMapDiagram);
		assertEquals(FANDABIDOSI, getMapInFolderCalled(childOne, JIMMY_KRANKIE)
				.getDescription());
	}

	@Test
	public void createCopyOfMapHappyCaseCopyDoesNotEqualOriginal() {
		HibMap newHibMapDiagram = new HibMap();
		childOne.createCopyOfMap(newHibMapDiagram);
		assertFalse(newHibMapDiagram.equals(getMapInFolderCalled(childOne,
				JIMMY_KRANKIE)));
	}

	@Test
	// TODO - NH not a real test as it subs the target test class!!!!!!!!!!
	public void testCreateCopyOfMapHappyCaseCopiesMapCanvas() {
		class CanvasCopyCalled {
			public boolean called = false;
		}
		final CanvasCopyCalled c = new CanvasCopyCalled();
		childOne = new HibSubFolder() {
			@Override
			void copyCanvasOf(IMap origMap) {
				c.called = true;
			}
		};
		HibMap newHibMapDiagram = new HibMap(childFour,
				JIMMY_KRANKIE);
		childOne.createCopyOfMap(newHibMapDiagram);
		assertTrue(c.called);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCreateCopyOfMapWhenAlreadyExistsThrowsIllegalArg() {
		HibMap newHibMapDiagram = new HibMap(childOne,
				JIMMY_KRANKIE);
		childOne.createCopyOfMap(newHibMapDiagram);
		childOne.createCopyOfMap(newHibMapDiagram);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCreateCopyOfMapWhenMapIsNullThrowsIllegalArg() {
		HibMap newHibMapDiagram = null;
		childOne.createCopyOfMap(newHibMapDiagram);
	}

	@Test
	public void testMoveMapHappyCaseAddsMapToNewFolder() {
		childOne.createMap(JIMMY_KRANKIE);
		HibMap newMap = childOne.getMapDiagrams().iterator().next();
		assertFalse(childFour.containsMap(newMap));
		childFour.moveMap(newMap);
		assertTrue(childFour.containsMap(newMap));
		assertTrue(mapExistsCalled(childFour, JIMMY_KRANKIE));
	}

	@Test
	public void testMoveMapHappyCaseRemovesMapFromOldFolder() {
		childOne.createMap(JIMMY_KRANKIE);
		HibMap newMap = childOne.getMapDiagrams().iterator().next();
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
		childOne.createMap(JIMMY_KRANKIE);
		HibMap newMap = childOne.getMapDiagrams().iterator().next();
		childFour.moveMap(newMap);
		childFour.moveMap(newMap);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testMoveMapThrowsIllegalArgWhenMapWithSameNameExists(){
		childOne.createMap(JIMMY_KRANKIE);
		HibMap newMap = childOne.getMapDiagrams().iterator().next();
		childFour.createMap(JIMMY_KRANKIE);
		childFour.moveMap(newMap);
	}

	@Test
	public void testCanRenameMapHappyCase() {
		childOne.createMap(JIMMY_KRANKIE);
		HibMap newMap = childOne.getMapDiagrams().iterator().next();
		assertTrue(childOne.canRenameMap(newMap, FANDABIDOSI));
		assertFalse(childOne.canRenameMap(newMap, JIMMY_KRANKIE));
	}

	@Test
	public void testCanRenameMapFalseWhenMapIsNullOrNameIsNull() {
		childOne.createMap(JIMMY_KRANKIE);
		HibMap newMap = childOne.getMapDiagrams().iterator().next();
		assertTrue(childOne.canRenameMap(newMap, FANDABIDOSI));
		assertFalse(childOne.canRenameMap(null, FANDABIDOSI));
		assertFalse(childOne.canRenameMap(newMap, null));
	}

	@Test
	public void testRenameMapHappyCase() {
		childOne.createMap(JIMMY_KRANKIE);
		HibMap newMap = childOne.getMapDiagrams().iterator().next();
		assertFalse(mapExistsCalled(childOne, FANDABIDOSI));
		assertTrue(mapExistsCalled(childOne, JIMMY_KRANKIE));
		childOne.renameMap(newMap, FANDABIDOSI);
		assertTrue(mapExistsCalled(childOne, FANDABIDOSI));
		assertFalse(mapExistsCalled(childOne, JIMMY_KRANKIE));
	}

	@Test
	public void testRenameMapMalformedName() {
		childOne.createMap(JIMMY_KRANKIE);
		HibMap newMap = childOne.getMapDiagrams().iterator().next();
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

	private HibMap getMapInFolderCalled(HibFolder r, String name) {
		Set<HibMap> maps = r.getMapDiagrams();
		for (HibMap map : maps) {
			if (map.getName().equals(name))
				return map;
		}
		return null;
	}

	private boolean mapExistsCalled(HibFolder r, String name) {
		Set<HibMap> maps = r.getMapDiagrams();
		for (HibMap map : maps) {
			if (map.getName().equals(name))
				return true;
		}
		return false;
	}
}
