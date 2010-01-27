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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Iterator;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.pathwayeditor.businessobjects.repository.IMap;
import org.pathwayeditor.businessobjects.repository.IRepository;
import org.pathwayeditor.businessobjects.repository.IRepositoryItem;
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
	private static final String CHILD_FIVE_NAME = "childFive";
	private static final String CHILD_SIX_NAME = "childSix";
	private static final String MAP_ONE_NAME = "map1";
	private static final String MAP_TWO_NAME = "map2";
	private static final String MAP_THREE_NAME = "map3";
	private static final int MISSING_INODE = 999999;
	private IRepository testInstance1;
	private IRepository testInstance2;
	private ISubFolder childOne;
	private ISubFolder childTwo;
	private ISubFolder childThree;
	private ISubFolder childFour;
	private ISubFolder childFive;
	private ISubFolder childSix;
	private IMap mapOne;
	private IMap mapTwo;
	private IMap mapThree;
	private IRootFolder root;
	
	@Before
	public void setUp(){
		this.testInstance1 = new HibRepository(REPOSITORY_NAME1, REPOSITORY_DESCRIPTION1, EXPECTED_BUILD_NUM);
		root = testInstance1.getRootFolder();
		childOne = root.createSubfolder(CHILD_ONE_NAME);
		childTwo = childOne.createSubfolder(CHILD_TWO_NAME);
		childThree = childTwo.createSubfolder(CHILD_THREE_NAME);
		childFour = childThree.createSubfolder(CHILD_FOUR_NAME);
		this.childFive = root.createSubfolder(CHILD_FIVE_NAME);
		this.childSix = childOne.createSubfolder(CHILD_SIX_NAME);
		this.mapOne = root.createMap(MAP_ONE_NAME);
		this.mapTwo = childThree.createMap(MAP_TWO_NAME);
		this.mapThree = childThree.createMap(MAP_THREE_NAME);
		
		this.testInstance2 = new HibRepository(REPOSITORY_NAME2, REPOSITORY_DESCRIPTION2, EXPECTED_BUILD_NUM);
	}
	
	@Test
	public void testIteratorFromRoot(){
		Iterator<IRepositoryItem> iterRepoItem = this.testInstance1.getRootFolder().levelOrderIterator();
		IRepositoryItem expectedArr[] = new IRepositoryItem[] { root, childFive, childOne, mapOne, childTwo, childSix,
								childThree, childFour, mapTwo, mapThree };
		for(IRepositoryItem expectedItem : expectedArr){
			assertTrue("next item available for:" + expectedItem, iterRepoItem.hasNext());
			assertEquals("next item expected", expectedItem, iterRepoItem.next());
		}
	}
	
	@Test
	public void testIteratorFromMap(){
		Iterator<IRepositoryItem> iterRepoItem = this.mapOne.levelOrderIterator();
		IRepositoryItem expectedArr[] = new IRepositoryItem[] { mapOne };
		for(IRepositoryItem expectedItem : expectedArr){
			assertTrue("next item available for:" + expectedItem, iterRepoItem.hasNext());
			assertEquals("next item expected", expectedItem, iterRepoItem.next());
		}
	}
	
	@Test
	public void testIteratorFromPopulatedSubDir(){
		Iterator<IRepositoryItem> iterRepoItem = this.childThree.levelOrderIterator();
		IRepositoryItem expectedArr[] = new IRepositoryItem[] { childThree, childFour, mapTwo, mapThree };
		for(IRepositoryItem expectedItem : expectedArr){
			assertTrue("next item available for:" + expectedItem, iterRepoItem.hasNext());
			assertEquals("next item expected", expectedItem, iterRepoItem.next());
		}
	}
	
	@Test
	public void testIteratorFromEmptySubDir(){
		Iterator<IRepositoryItem> iterRepoItem = this.childFive.levelOrderIterator();
		IRepositoryItem expectedArr[] = new IRepositoryItem[] { childFive };
		for(IRepositoryItem expectedItem : expectedArr){
			assertTrue("next item available for:" + expectedItem, iterRepoItem.hasNext());
			assertEquals("next item expected", expectedItem, iterRepoItem.next());
		}
	}
	
	@Test
	public void findMapFromMapINode(){
		IRepositoryItem actualItem = this.testInstance1.findRepositoryItemByINode(this.mapTwo.getINode());
		assertEquals("expected rpo item", this.mapTwo, actualItem);
	}
	
	@Test
	public void findMapFromMissingINode(){
		IRepositoryItem actualItem = this.testInstance1.findRepositoryItemByINode(MISSING_INODE);
		assertNull("no rpo item", actualItem);
	}
	
	@Test
	public void findMapFromFolderINode(){
		IRepositoryItem actualItem = this.testInstance1.findRepositoryItemByINode(this.childSix.getINode());
		assertEquals("expected rpo item", this.childSix, actualItem);
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
	
	@Test
	public void testPathExistsHappyCaseSubFolder(){
		assertTrue(testInstance1.pathExists("/one/"));
		assertTrue(testInstance1.pathExists("/one/two/"));
	}
	
	@Test
	public void testGetFolderByPathHappyCaseRootFolder(){
		assertEquals(root,testInstance1.getFolderByPath("/"));
	}
	
	@Test
	public void testGetFolderByPathHappyCaseSubFolder(){
		assertEquals(childOne,testInstance1.getFolderByPath("/one/"));
		assertEquals(childTwo,testInstance1.getFolderByPath("/one/two/"));
		assertEquals(childFour,testInstance1.getFolderByPath("/one/two/three/four/"));
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
