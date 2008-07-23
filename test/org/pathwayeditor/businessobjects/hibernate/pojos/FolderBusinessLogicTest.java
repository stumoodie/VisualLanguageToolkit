/**
 * 
 */
package org.pathwayeditor.businessobjects.hibernate.pojos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;
import org.pathwayeditor.businessobjects.database.util.HibernateUtil;
import org.pathwayeditor.businessobjects.repository.ISubFolder;

/**
 * @author nhanlon NH - this is a business logic test so no actual database
 *         activity will be tested - see corresponding  tests  in FolderDatabaseTest
 */
public class FolderBusinessLogicTest {

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

}
