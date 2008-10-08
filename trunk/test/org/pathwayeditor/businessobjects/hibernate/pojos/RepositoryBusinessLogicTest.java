/**
 * 
 */
package org.pathwayeditor.businessobjects.hibernate.pojos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.pathwayeditor.businessobjects.repository.IRepository;
import org.pathwayeditor.businessobjects.repository.IRootFolder;
import org.pathwayeditor.businessobjects.repository.ISubFolder;

/**
 * @author nhanlon tests for the HibRepository class' implementation of the IRepository interface
 *
 */
public class RepositoryBusinessLogicTest {
	private static final String CHILD_ONE_NAME = "one";
	private static final String CHILD_TWO_NAME = "two";
	private static final String CHILD_THREE_NAME = "three";
	private static final String CHILD_FOUR_NAME = "four";
	private static final String REPOSITORY_NAME1 = "JimmyKrankie";
	private static final String REPOSITORY_DESCRIPTION1 = "fandabidosi";
	private static final String REPOSITORY_NAME2 ="testName2" ;
	private static final String REPOSITORY_DESCRIPTION2 ="testDescription2" ;
	private static final int EXPECTED_BUILD_NUM = 9999;
	private static final int EXPECTED_ALT_BUILD_NUM = 222;
	private IRepository testInstance1;
	private IRepository testInstance2;
	private ISubFolder childOne;
	private ISubFolder childTwo;
	private ISubFolder childThree;
	private ISubFolder childFour;
	private IRootFolder root;
	
	@Before
	public void setUp(){
		this.testInstance1 = new HibRepository(REPOSITORY_NAME1, REPOSITORY_DESCRIPTION1, EXPECTED_BUILD_NUM);
		root = testInstance1.getRootFolder();
		childOne = root.createSubfolder(CHILD_ONE_NAME);
		childTwo = childOne.createSubfolder(CHILD_TWO_NAME);
		childThree = childTwo.createSubfolder(CHILD_THREE_NAME);
		childFour = childThree.createSubfolder(CHILD_FOUR_NAME);
		this.testInstance2 = new HibRepository(REPOSITORY_NAME2, REPOSITORY_DESCRIPTION2, EXPECTED_BUILD_NUM);
	}
	
	@Test
	public void testPublicConstructorEnsureNameNeverNullOrEmptyString(){
		try{@SuppressWarnings("unused")
		IRepository rep = new HibRepository("",REPOSITORY_DESCRIPTION1,1);fail("empty name not allowed");}catch(IllegalArgumentException e) {;}
		try{@SuppressWarnings("unused")
		IRepository rep = new HibRepository(null,REPOSITORY_DESCRIPTION1,1);fail("empty name not allowed");}catch(IllegalArgumentException e) {;}
	}
	
	@Test
	public void testPublicConstructorEnsureDescriptionNeverNullButEmptyStringAllowed(){
		try{
			new HibRepository(REPOSITORY_NAME1,null,1);
			fail("empty name not allowed");
		}catch(IllegalArgumentException e) {;}
		new HibRepository(REPOSITORY_NAME1,"",1);
	}
	
	@Test
	public void testGetNameHappyCase(){
		assertEquals(REPOSITORY_NAME1,testInstance1.getName());
	}
	
	@Test
	public void testGetDesriptionHappyCase(){
		assertEquals(REPOSITORY_DESCRIPTION1,testInstance1.getDescription());
	}
	
	@Test
	public void testNewRepositoryHasARootFolder(){
		assertNotNull(testInstance1.getRootFolder());
	}
	
	@Test
	public void testGetSchemaBuildNumHappyCase(){
		assertEquals(EXPECTED_BUILD_NUM,testInstance1.getSchemaBuildNum());
	}
	
	@Test
	public void testEqualsWorksOnName(){
		IRepository rep = new HibRepository(REPOSITORY_NAME1, REPOSITORY_NAME1, EXPECTED_ALT_BUILD_NUM);
		IRepository rep2 = new HibRepository(REPOSITORY_DESCRIPTION1, REPOSITORY_DESCRIPTION1, EXPECTED_BUILD_NUM);
		assertFalse(rep.equals(rep2));
		assertFalse(testInstance1.equals(rep2));
		assertTrue(testInstance1.equals(rep));
	}
	
	@Test
	public final void testHashCode() {
		IRepository rep2 = new HibRepository(REPOSITORY_NAME1, REPOSITORY_DESCRIPTION1, EXPECTED_BUILD_NUM);
		assertFalse ( "Hash code not equals." , testInstance1.hashCode() == testInstance2.hashCode() ) ;
		assertTrue ( "Hash code equals." , testInstance1.hashCode() == rep2.hashCode() ) ;
	}

	@Test
	public void testGetFoldersByNameOneFolderFoundHappyCase(){
		List <ISubFolder>folders = testInstance1.getFoldersByName(childFour.getName());
		assertEquals(1, folders.size());
		ISubFolder f = folders.iterator().next();
		assertEquals(f,childFour);
	}
	
	@Test
	public void testGetFoldersByNameTwoFoldersFoundHappyCase(){
		List <ISubFolder>folders = testInstance1.getFoldersByName(childFour.getName());
		assertEquals(1, folders.size());
		childTwo.createCopyOfSubfolder(childFour);
		folders = testInstance1.getFoldersByName(childFour.getName());
		assertEquals(2, folders.size());
		ISubFolder f = folders.iterator().next();
		assertEquals(f.getName(),childFour.getName());
		f = folders.iterator().next();
		assertEquals(f.getName(),childFour.getName());
	}
	
	@Test
	public void testGetFoldersByNameWhenNameNotFound(){
		List <ISubFolder>folders = testInstance1.getFoldersByName("eggs");
		assertEquals(0,folders.size());
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testGetFoldersByNameWhenNameNull(){
		testInstance1.getFoldersByName(null);
	}
	@Test (expected=IllegalArgumentException.class)
	public void testGetFoldersByNameWhenNameEmptyString(){
		testInstance1.getFoldersByName("");
	}
	
	@Test
	public void testPathExistsHappyCaseRootFolder(){
		assertTrue(testInstance1.pathExists("/"));
	}
	
	@Ignore @Test
	public void testPathExistsHappyCaseSubFolder(){
		assertTrue(testInstance1.pathExists("/one"));
		assertTrue(testInstance1.pathExists("/one/two"));
	}
	
	@Test
	public void testGetFolderByPathHappyCaseRootFolder(){
		assertEquals(root,testInstance1.getFolderByPath("/"));
	}
	
	@Ignore @Test
	public void testGetFolderByPathHappyCaseSubFolder(){
		assertEquals(childOne,testInstance1.getFolderByPath("/one"));
		assertEquals(childTwo,testInstance1.getFolderByPath("/one/two"));
		assertEquals(childFour,testInstance1.getFolderByPath("/one/two/three/four"));
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testGetFolderByPathWhenNotFound(){
	       testInstance1.getFolderByPath("Russel Harty");
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testGetFolderByPathWhenPathisNull(){
	       testInstance1.getFolderByPath(null);
	}
	@Test (expected=IllegalArgumentException.class)
	public void testGetFolderByPathWhenPathisEmpty(){
	       testInstance1.getFolderByPath("");
	}

}
