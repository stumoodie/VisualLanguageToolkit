package org.pathwayeditor.businessobjects.hibernate.pojos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.pathwayeditor.businessobjects.database.util.HibernateUtil;
import org.pathwayeditor.businessobjects.repository.ISubFolder;

/**
 * @author ntsorman
 *
 */
@RunWith(JMock.class)
public class HibRepositoryBusinessLogicTest  {
	private Mockery mockery = new JUnit4Mockery() {{
		 setImposteriser(ClassImposteriser.INSTANCE);
	}};
	private static final String REPOSITORY_NAME1 ="testName1" ;
	private static final String REPOSITORY_NAME2 ="testName2" ;
	
	private static final String REPOSITORY_DESCRIPTION1 ="testDescription1" ;
	private static final String REPOSITORY_DESCRIPTION2 ="testDescription2" ;
	
	private static final int VERSION_NUMBER1 = 1 ;
	private static final int VERSION_NUMBER2 = 2 ;
	
	private HibRepository testInstance1 = new HibRepository("a","",0);
	private HibRepository testInstance2 = new HibRepository("a","",0);
	static {
		 HibernateUtil.setStubSessionFactoryAsDefault(); // dont use the  database 
	}
	protected HibSubFolder childOne;
	protected HibSubFolder childTwo;
	protected HibSubFolder childThree;
	protected HibSubFolder childFour;
	HibRootFolder rootFolder = new HibRootFolder();
	

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
		this.testInstance1 = new HibRepository(REPOSITORY_NAME1 , REPOSITORY_DESCRIPTION1, VERSION_NUMBER1);
		this.testInstance2 = new HibRepository(REPOSITORY_NAME2 , REPOSITORY_DESCRIPTION2 , VERSION_NUMBER2);
		rootFolder.addSubFolder(childOne);
		testInstance1.changeRootFolder(rootFolder);
	}

	@After
	public void tearDown() throws Exception {
	}

	
	@Test
	public final void testHashCode() {
		assertFalse ( "Hash code not equals." , testInstance1.hashCode() == testInstance2.hashCode() ) ;
		this.testInstance2.setName(REPOSITORY_NAME1) ;
		assertTrue ( "Hash code equals." , testInstance1.hashCode() == testInstance2.hashCode() ) ;
	}

	@Test
	public final void testEqualsObject() {
		assertTrue ( this.testInstance1.equals(this.testInstance1)) ;
		assertFalse ( this.testInstance1.equals(null)) ;
		assertFalse ( this.testInstance1.equals(this.testInstance2)) ;
		assertFalse (this.testInstance1.equals(REPOSITORY_NAME1)) ;
		
		this.testInstance2.setName(REPOSITORY_NAME1) ;
		assertTrue (this.testInstance1.equals(this.testInstance2)) ;
	}

	@Test
	@Ignore
	public final void testChangeRootFolderNull() {
//		this.testInstance1.changeRootFolder(null);
//		assertTrue("new folder is null", this.testInstance1.getRootFolder() == null);
	}

	@Test 
	@Ignore
	public final void testChangeRootFolder() {
//		final HibRootFolder mockRootFolder1 = this.mockery.mock(HibRootFolder.class, "mockRootFolder");
//		final HibRootFolder mockRootFolder2 = this.mockery.mock(HibRootFolder.class, "mockRootFolder2");
//		this.mockery.checking(new Expectations(){{
//			atLeast(1).of(mockRootFolder1).setRepository(testInstance1);
//			atLeast(1).of(mockRootFolder2).setRepository(testInstance1);
//			atLeast(1).of(mockRootFolder2).setRepository(null);
//		}});
//		this.testInstance1.changeRootFolder(null);
//		assertTrue("new folder is null", this.testInstance1.getRootFolder() == null);
//		this.testInstance1.changeRootFolder(mockRootFolder1);
//		assertTrue("new folder is null", this.testInstance1.getRootFolder() == mockRootFolder1);
//		this.testInstance1.changeRootFolder(mockRootFolder2);
//		assertTrue("new folder is null", this.testInstance1.getRootFolder() == mockRootFolder2);
//		this.testInstance1.changeRootFolder(null);
//		assertTrue("new folder is null", this.testInstance1.getRootFolder() == null);
//		this.mockery.assertIsSatisfied();
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
		List <ISubFolder>folders = testInstance1.getFoldersByName(null);
	}
	@Test (expected=IllegalArgumentException.class)
	public void testGetFoldersByNameWhenNameEmptyString(){
		List <ISubFolder>folders = testInstance1.getFoldersByName("");
	}
	
	@Test
	public void testPathExistsHappyCaseRootFolder(){
		assertTrue(testInstance1.pathExists("/"));
	}
	
	@Test
	public void testPathExistsHappyCaseSubFolder(){
		assertTrue(testInstance1.pathExists("/one"));
		assertTrue(testInstance1.pathExists("/one/two"));
	}
	
	@Test
	public void testGetFolderByPathHappyCaseRootFolder(){
		assertEquals(rootFolder,testInstance1.getFolderByPath("/"));
	}
	
	@Test
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
