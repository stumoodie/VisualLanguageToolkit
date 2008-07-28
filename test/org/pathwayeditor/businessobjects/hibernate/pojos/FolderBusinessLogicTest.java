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
import org.pathwayeditor.businessobjects.database.util.HibernateUtil;
import org.pathwayeditor.businessobjects.repository.IMap;
import org.pathwayeditor.businessobjects.repository.ISubFolder;

/**
 * @author nhanlon NH - this is a business logic test so no actual database
 *         activity will be tested - see corresponding  tests  in FolderDatabaseTest
 *         
 *         Tests the IFolder interface
 */
public class FolderBusinessLogicTest {

	/**
	 * 
	 */
	private static final String FANDABIDOSI = "fandabidosi";
	/**
	 * 
	 */
	private static final String JIMMY_KRANKIE = "JimmyKrankie";

	static {
		 HibernateUtil.setStubSessionFactoryAsDefault(); // dont use the  database 
	}
	protected HibSubFolder childOne;
	protected HibSubFolder childTwo;
	protected HibSubFolder childThree;
	protected HibSubFolder childFour;

	@Before
	public void setUp() {
		childOne = new HibSubFolder();
		childTwo = new HibSubFolder();
		childThree = new HibSubFolder();
		childFour = new HibSubFolder();
		childOne.setName("one");
		childTwo.setName("two");
		childThree.setName("three");
		childFour.setName("four");
		childOne.addSubFolder(childTwo);
		childTwo.addSubFolder(childThree);
		childThree.addSubFolder(childFour);
	}

	@Test
	public void canMoveFolderUniqueNameTest() {
		HibSubFolder folder = new HibSubFolder();
		HibSubFolder child = new HibSubFolder();
		child.setName("one");
		assertTrue(folder.canMoveSubfolder((ISubFolder) child));
		folder.addSubFolder(child);
		assertFalse(folder.canMoveSubfolder((ISubFolder) child));
	}
	
	@Test
	public void canMoveFolderFalseWHenFolderNullTest() {
		HibSubFolder folder = new HibSubFolder();
		HibSubFolder child = new HibSubFolder();
		child.setName("one");
		assertTrue(folder.canMoveSubfolder((ISubFolder) child));
		child=null;
		assertFalse(folder.canMoveSubfolder((ISubFolder) child));
	}

	@Test
	public void canMoveFolderCircularChildSimpleTest() {
		HibSubFolder folder = new HibSubFolder();
		HibSubFolder childOne = new HibSubFolder();
		childOne.setName("one");
		assertTrue(folder.canMoveSubfolder((ISubFolder) childOne));
		childOne.addSubFolder(folder);
		assertFalse(folder.canMoveSubfolder((ISubFolder) childOne));
	}

	@Test
	public void canMoveFolderCircularChildRecursionTest() {
		HibSubFolder folder = new HibSubFolder();
		assertTrue(folder.canMoveSubfolder((ISubFolder) childOne));
		childThree.addSubFolder(folder);
		assertFalse(folder.canMoveSubfolder((ISubFolder) childOne));
		assertTrue(folder.canMoveSubfolder((ISubFolder) childFour));
	}

	@Test
	public void canUseSubfolderNameUniqueNameTest() {
		HibSubFolder folder = new HibSubFolder();
		HibSubFolder child = new HibSubFolder();
		String one = "one";
		child.setName(one);
		assertTrue(folder.canUseSubfolderName(one));
		folder.addSubFolder(child);
		assertFalse(folder.canUseSubfolderName(one));
	}

	@Test
	public void canUseSubfolderNameNullOrSlashdotThrowsIllegalArgumentExceptionTest() {
		HibSubFolder folder = new HibSubFolder();
		HibSubFolder child = new HibSubFolder();
		String one = "one";
		child.setName(one);
		assertTrue(folder.canUseSubfolderName(child.getName()));
		try{folder.canUseSubfolderName(".");fail("should throw illegal arg for null or slashdot");}
		catch(IllegalArgumentException e){;}
		try{folder.canUseSubfolderName("/");fail("should throw illegal arg for null or slashdot");}
		catch(IllegalArgumentException e){;}
		try{folder.canUseSubfolderName("\\");fail("should throw illegal arg for null or slashdot");}
		catch(IllegalArgumentException e){;}
		try{folder.canUseSubfolderName(null);fail("should throw illegal arg for null or slashdot");}
		catch(IllegalArgumentException e){;}
	}

	@Test
	public void containsSubFolderDirectChildTest() {
		HibSubFolder folder = new HibSubFolder();
		HibSubFolder child = new HibSubFolder();
		child.setName("one");
		assertFalse(folder.containsSubfolder(child));
		folder.addSubFolder(child);
		assertTrue(folder.containsSubfolder(child));
	}

	@Test
	public void containsSubFolderChildTreeTest() {
		HibSubFolder folder = new HibSubFolder();
		folder.addSubFolder(childOne);
		assertTrue(folder.containsSubfolder(childFour));
	}

	@Test
	public void testCreateSubFolderSetsName() {
		HibRootFolder folder = new HibRootFolder();
		folder.createSubfolder("two");
		assertTrue(folder.getSubFolders().iterator().next().getName().equals(
				"two"));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCreateSubFolderChecksNameUnique() {
		HibSubFolder folder = new HibSubFolder();
		folder.createSubfolder("two");
		assertTrue(folder.getSubFolders().iterator().next().getName().equals(
				"two"));
		folder.createSubfolder("three");
		Iterator<HibSubFolder> it = folder.getSubFolders().iterator();
		assertTrue(it.next().getName().equals("two") ? it.next().getName()
				.equals("three") : it.next().getName().equals("two"));
		it = folder.getSubFolders().iterator();
		assertTrue(it.next().getName().equals("three") ? it.next().getName()
				.equals("two") : it.next().getName().equals("three"));
		folder.createSubfolder("two");
	}

	@Test
	public void testMoveSubFolderAddsSubFolder() {
		HibSubFolder folder = new HibSubFolder();
		assertTrue(folder.getSubFolders().size() == 0);
		HibSubFolder child = new HibSubFolder();
		child.setName("one");
		folder.moveSubfolder(child);
		assertTrue(folder.getSubFolders().size() == 1);
		assertEquals(child, folder.getSubFolders().iterator().next());
	}

	@Test
	public void testMoveSubFolderAddsParentToSubFolder() {
		HibSubFolder folder = new HibSubFolder();
		HibSubFolder child = new HibSubFolder();
		child.setName("one");
		folder.moveSubfolder(child);
		assertEquals(folder, child.getParent());
	}

	@Test
	public void testMoveSubFolderAddsMapsToSubFolder() {
		HibSubFolder folder = new HibSubFolder();
		HibSubFolder child = new HibSubFolder();
		child.setName("one");
		HibMapDiagram d = new HibMapDiagram();
		child.addMapDiagram(d);
		folder.moveSubfolder(child);
		assertEquals(d, folder.getSubFolders().iterator().next()
				.getMapDiagrams().iterator().next());
	}

	@Test
	public void testCopySubFolderCopiesWholeSubTree() {
		HibSubFolder folder = new HibSubFolder();
		folder.createCopyOfSubfolder(childOne);
		HibSubFolder sub = folder.getSubFolders().iterator().next();
		assertTrue(sub.getName().equals("one"));
		sub = sub.getSubFolders().iterator().next();
		assertTrue(sub.getName().equals("two"));
		sub = sub.getSubFolders().iterator().next();
		assertTrue(sub.getName().equals("three"));
	}

	@Test
	public void testCopySubFolderMakesCopyWhichIsNotEqualToOriginal() {
		HibSubFolder folder = new HibSubFolder();
		folder.createCopyOfSubfolder(childOne);
		HibSubFolder sub = folder.getSubFolders().iterator().next();
		assertFalse(sub.equals(childOne));
		sub = sub.getSubFolders().iterator().next();
		assertFalse(sub.equals(childTwo));
		sub = sub.getSubFolders().iterator().next();
		assertFalse(sub.equals(childThree));
	}

	@Test
	public void testCopySubFolderAlsoCopiesMapsAndTheMapsAreNotEqualToOriginals() {
		HibSubFolder folder = new HibSubFolder();
		HibSubFolder child = new HibSubFolder();
		child.setName("one");
		HibMapDiagram d = new HibMapDiagram(child, "a map");
		child.addMapDiagram(d);
		assertEquals(d, child.getMapDiagrams().iterator().next());
		folder.createCopyOfSubfolder(child);
		assertTrue(d.getDescription().equals(
				folder.getSubFolders().iterator().next().getMapDiagrams()
						.iterator().next().getDescription()));
		assertFalse(d.equals(folder.getSubFolders().iterator().next()
				.getMapDiagrams().iterator().next()));
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
	
	@Test (expected=IllegalArgumentException.class)
	public void testCanrenameSubFolderFailsWhenSubFolderNotChild(){
		HibSubFolder folder = new HibSubFolder();
		HibSubFolder folder2 = new HibSubFolder();
		folder.canRenameSubfolder(folder2, JIMMY_KRANKIE);
	}
	
	@Test
	public void testCanRenameSubFolderNameIsIllegal(){
		HibSubFolder folder = new HibSubFolder();
		HibSubFolder folder2 = new HibSubFolder();
		folder.addSubFolder(folder2);
		assertTrue(folder.canRenameSubfolder(folder2, JIMMY_KRANKIE));
		try{folder.canRenameSubfolder(folder2,".");fail("should throw illegal arg for null or slashdot");}
		catch(IllegalArgumentException e){;}
		try{folder.canRenameSubfolder(folder2,"/");fail("should throw illegal arg for null or slashdot");}
		catch(IllegalArgumentException e){;}
		try{folder.canRenameSubfolder(folder2,"\\");fail("should throw illegal arg for null or slashdot");}
		catch(IllegalArgumentException e){;}
		try{folder.canRenameSubfolder(folder2,null);fail("should throw illegal arg for null or slashdot");}
		catch(IllegalArgumentException e){;}
	}
	
	@Test
	public void testCanrenameSubFolderWhenSubFolderIsChildNewNameUnused(){
		HibSubFolder folder = new HibSubFolder();
		HibSubFolder folder2 = new HibSubFolder();
		folder.addSubFolder(folder2);
		assertTrue(folder.canRenameSubfolder(folder2, JIMMY_KRANKIE));
	}
	
	@Test
	public void testCanrenameSubFolderWhenSubFolderIsChildNewNameUsed(){
		HibSubFolder folder = new HibSubFolder();
		HibSubFolder folder2 = new HibSubFolder();
		folder.addSubFolder(folder2);
		assertTrue(folder.canRenameSubfolder(folder2, JIMMY_KRANKIE));
		folder.createSubfolder(JIMMY_KRANKIE);
		assertFalse(folder.canRenameSubfolder(folder2, JIMMY_KRANKIE));
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testRenameSubFolderFailsWhenSubFolderNotChild(){
		HibSubFolder folder = new HibSubFolder();
		HibSubFolder folder2 = new HibSubFolder();
		folder.renameSubfolder(folder2, JIMMY_KRANKIE);
	}
	
	@Test
	public void testRenameSubFolderNameIsIllegal(){
		HibSubFolder folder = new HibSubFolder();
		HibSubFolder folder2 = new HibSubFolder();
		folder.addSubFolder(folder2);
		try{folder.renameSubfolder(folder2,".");fail("should throw illegal arg for null or slashdot");}
		catch(IllegalArgumentException e){;}
		try{folder.renameSubfolder(folder2,"/");fail("should throw illegal arg for null or slashdot");}
		catch(IllegalArgumentException e){;}
		try{folder.renameSubfolder(folder2,"\\");fail("should throw illegal arg for null or slashdot");}
		catch(IllegalArgumentException e){;}
		try{folder.renameSubfolder(folder2,null);fail("should throw illegal arg for null or slashdot");}
		catch(IllegalArgumentException e){;}
	}
	
	@Test
	public void testRenameSubFolderWhenSubFolderIsChildNewNameUnused(){
		HibSubFolder folder = new HibSubFolder();
		HibSubFolder folder2 = new HibSubFolder();
		folder.addSubFolder(folder2);
		folder.renameSubfolder(folder2, JIMMY_KRANKIE);
		assertTrue(folder2.getName().equals(JIMMY_KRANKIE));
	}
	
	@Test
	public void testRenameSubFolderWhenSubFolderIsChildNewNameUsed(){
		HibSubFolder folder = new HibSubFolder();
		HibSubFolder folder2 = new HibSubFolder();
		folder.addSubFolder(folder2);
		folder.createSubfolder(JIMMY_KRANKIE);
		folder.renameSubfolder(folder2, JIMMY_KRANKIE);
		assertFalse(folder2.getName().equals(JIMMY_KRANKIE));
	}
	
	@Test
	public void testNumMaps(){
		assertEquals(0,childOne.numMaps());
		childOne.addMapDiagram(new HibMapDiagram());
		assertEquals(1,childOne.numMaps());
	}
	
	@Test
	public void testNumSubFolders(){
		assertEquals(0,childFour.numSubFolders());
		assertEquals(1,childOne.numSubFolders());
	}
	
	@Test
	public void testGetMapIteratorCannotBeNull(){
		assertNotNull(childOne.getMapIterator());
	}
	
	@Test
	public void testGetMapIteratorIteratesOverMaps(){
		new HibMapDiagram(childOne,"JIMMY");
		new HibMapDiagram(childOne,"KRANKIE");
		Iterator <? extends IMap>it = childOne.getMapIterator();
		assertTrue(it.next().getName().equals("JIMMY")?it.next().getName().equals("KRANKIE"):it.next().getName().equals("JIMMY"));
	}
	
	@Test
	public void testCanUseMapNameHappyCase(){
		assertTrue(childOne.canUseMapName(JIMMY_KRANKIE));
	}
	
	@Test
	public void testCanUseMapNameMalformed(){
		assertTrue(childOne.canUseMapName(JIMMY_KRANKIE));
		assertFalse(childOne.canUseMapName(null));
		assertFalse(childOne.canUseMapName("/"));
		assertFalse(childOne.canUseMapName("."));
		assertFalse(childOne.canUseMapName("\\"));
	}
	
	@Test
	public void testCreateMapHappyCase(){
		childOne.createMap(JIMMY_KRANKIE);
		assertTrue(mapExistsCalled(childOne, JIMMY_KRANKIE));
	}
	
	@Test
	public void testCreateMapMalformed(){
		childOne.createMap(JIMMY_KRANKIE);
		assertTrue(mapExistsCalled(childOne, JIMMY_KRANKIE));
		try{childOne.createMap(".");fail("should throw illegal arg for null or slashdot");}
		catch(IllegalArgumentException e){;}
		try{childOne.createMap("/");fail("should throw illegal arg for null or slashdot");}
		catch(IllegalArgumentException e){;}
		try{childOne.createMap("\\");fail("should throw illegal arg for null or slashdot");}
		catch(IllegalArgumentException e){;}
		try{childOne.createMap(null);fail("should throw illegal arg for null or slashdot");}
		catch(IllegalArgumentException e){;}
	}
	
	@Test
	public void testCreateMapNameAlreadyInUseThrowsIllegalArgument(){
		childOne.createMap(JIMMY_KRANKIE);
		assertTrue(mapExistsCalled(childOne, JIMMY_KRANKIE));
		try{childOne.createMap(JIMMY_KRANKIE);fail("should throw illegal arg for map already exists with same name");}
		catch(IllegalArgumentException e){;}
	}
	
	@Test
	public void testContainsMapTrue(){
		HibMapDiagram newMapDiagram = new HibMapDiagram(childOne,JIMMY_KRANKIE);
		assertTrue(childOne.containsMap(newMapDiagram));
	}
	
	@Test
	public void testContainsMapFalse(){
		HibMapDiagram newMapDiagram = new HibMapDiagram();
		assertFalse(childOne.containsMap(newMapDiagram));
	}
	
	@Test
	public void testContainsMapNull(){
		HibMapDiagram newMapDiagram = null;
		assertFalse(childOne.containsMap(newMapDiagram));
	}
	
	@Test
	public void createCopyOfMapHappyCaseCopiesName(){
		HibMapDiagram newHibMapDiagram = new HibMapDiagram(childFour,JIMMY_KRANKIE);
		assertFalse(mapExistsCalled(childOne, JIMMY_KRANKIE));
		childOne.createCopyOfMap(newHibMapDiagram);
		assertTrue(mapExistsCalled(childOne, JIMMY_KRANKIE));
	}
	
	@Test
	public void createCopyOfMapHappyCaseCopiesDescription(){
		HibMapDiagram newHibMapDiagram = new HibMapDiagram(childFour,JIMMY_KRANKIE);
		newHibMapDiagram.setDescription(FANDABIDOSI);
		childOne.createCopyOfMap(newHibMapDiagram);
		assertEquals(FANDABIDOSI,getMapInFolderCalled(childOne, JIMMY_KRANKIE).getDescription());
	}
	
	@Test
	public void createCopyOfMapHappyCaseCopyDoesNotEqualOriginal(){
		HibMapDiagram newHibMapDiagram = new HibMapDiagram();
		childOne.createCopyOfMap(newHibMapDiagram);
		assertFalse(newHibMapDiagram.equals(getMapInFolderCalled(childOne, JIMMY_KRANKIE)));
	}
	
	@Test //TODO - NH not a real test as it subs the target test class!!!!!!!!!!
	public void testCreateCopyOfMapHappyCaseCopiesMapCanvas(){
		 class CanvasCopyCalled {
			public boolean called=false;
		}
		 final CanvasCopyCalled c= new CanvasCopyCalled();
		childOne = new HibSubFolder(){
			@Override
			void copyCanvasOf(IMap origMap) {
				c.called=true;
			}
		};
		HibMapDiagram newHibMapDiagram = new HibMapDiagram(childFour,JIMMY_KRANKIE);
		childOne.createCopyOfMap(newHibMapDiagram);
		assertTrue(c.called);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testCreateCopyOfMapWhenAlreadyExistsThrowsIllegalArg(){
		HibMapDiagram newHibMapDiagram = new HibMapDiagram(childOne,JIMMY_KRANKIE);
		childOne.createCopyOfMap(newHibMapDiagram);
		childOne.createCopyOfMap(newHibMapDiagram);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testCreateCopyOfMapWhenMapIsNullThrowsIllegalArg(){
		HibMapDiagram newHibMapDiagram = null;
		childOne.createCopyOfMap(newHibMapDiagram);
	}
	
	@Test
	public void testMoveMapHappyCaseAddsMapToNewFolder(){
		childOne.createMap(JIMMY_KRANKIE);
		HibMapDiagram newMap = childOne.getMapDiagrams().iterator().next();
		assertFalse(childFour.containsMap(newMap));
		childFour.moveMap(newMap);
		assertTrue(childFour.containsMap(newMap));
		assertTrue(mapExistsCalled(childFour, JIMMY_KRANKIE));
	}
	
	@Test
	public void testMoveMapHappyCaseRemovesMapFromOldFolder(){
		childOne.createMap(JIMMY_KRANKIE);
		HibMapDiagram newMap = childOne.getMapDiagrams().iterator().next();
		assertTrue(childOne.containsMap(newMap));
		childFour.moveMap(newMap);
		assertFalse(childOne.containsMap(newMap));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testMoveMapThrowsIllegalArgIfMapIsNull(){
		HibMapDiagram newMap = null;
		childFour.moveMap(newMap);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testMoveMapThrowsIllegalArgIfMapIsAlreadyContained(){
		childOne.createMap(JIMMY_KRANKIE);
		HibMapDiagram newMap = childOne.getMapDiagrams().iterator().next();
		childFour.moveMap(newMap);
		childFour.moveMap(newMap);
	}
	
	@Test
	public void testCanRenameMapHappyCase(){
		childOne.createMap(JIMMY_KRANKIE);
		HibMapDiagram newMap = childOne.getMapDiagrams().iterator().next();
		assertTrue(childOne.canRenameMap(newMap, FANDABIDOSI));
		assertFalse(childOne.canRenameMap(newMap, JIMMY_KRANKIE));
	}
	
	@Test
	public void testCanRenameMapFalseWhenMapIsNullOrNameIsNull(){
		childOne.createMap(JIMMY_KRANKIE);
		HibMapDiagram newMap = childOne.getMapDiagrams().iterator().next();
		assertTrue(childOne.canRenameMap(newMap, FANDABIDOSI));
		assertFalse(childOne.canRenameMap(null, FANDABIDOSI));
		assertFalse(childOne.canRenameMap(newMap, null));
	}
	
	
	@Test
	public void testRenameMapHappyCase(){
		childOne.createMap(JIMMY_KRANKIE);
		HibMapDiagram newMap = childOne.getMapDiagrams().iterator().next();
		assertFalse(mapExistsCalled(childOne, FANDABIDOSI));
		assertTrue(mapExistsCalled(childOne, JIMMY_KRANKIE));
		childOne.renameMap(newMap, FANDABIDOSI);
		assertTrue(mapExistsCalled(childOne, FANDABIDOSI));
		assertFalse(mapExistsCalled(childOne, JIMMY_KRANKIE));
	}
	
	@Test
	public void testRenameMapMalformedName(){
		childOne.createMap(JIMMY_KRANKIE);
		HibMapDiagram newMap = childOne.getMapDiagrams().iterator().next();
		try{childOne.renameMap(newMap,".");fail("should throw illegal arg for null or slashdot");}
		catch(IllegalArgumentException e){;}
		try{childOne.renameMap(newMap,"/");fail("should throw illegal arg for null or slashdot");}
		catch(IllegalArgumentException e){;}
		try{childOne.renameMap(newMap,"\\");fail("should throw illegal arg for null or slashdot");}
		catch(IllegalArgumentException e){;}
		try{childOne.renameMap(newMap,null);fail("should throw illegal arg for null or slashdot");}
		catch(IllegalArgumentException e){;}
	}
	
	@Test
	public void testGetSubFolderIteratorWhenSubFoldersDoNotExistGivesEmptyIterator(){
		Iterator <? extends ISubFolder> it = childFour.getSubFolderIterator();
		assertNotNull(it);
		assertFalse(it.hasNext());
	}
	
	@Test
	public void testGetSubFolderIteratorIteratesOverFolders(){
		Iterator <? extends ISubFolder> it = childOne.getSubFolderIterator();
		assertEquals(childTwo,it.next());
	}
	
	private HibMapDiagram getMapInFolderCalled(HibFolder r, String name) {
		Set<HibMapDiagram> maps = r.getMapDiagrams();
		for (HibMapDiagram map: maps){
			if(map.getName().equals(name))
				return map;
		}
		return null;
	}
	
	private boolean mapExistsCalled(HibFolder r, String name) {
		Set<HibMapDiagram> maps = r.getMapDiagrams();
		for (HibMapDiagram map: maps){
			if(map.getName().equals(name))
				return true;
		}
		return false;
	}
}
