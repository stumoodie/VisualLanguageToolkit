/**
 * 
 */
package org.pathwayeditor.businessobjects.hibernate.pojos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.pathwayeditor.testutils.GenericTester;


/**
 * @author nhanlon
 * Tests that the business logic operations tested by FolderBusinessLogicTest result in database changes
 */
public class FolderDatabaseTest extends GenericTester{


	@SuppressWarnings("unchecked")
	@Test
	public void testCreateSubFolderSetsName() {
		List test=getSession().createQuery("from HibSubFolder f where f.name='two'").list();
		assertEquals(0,test.size());
		HibFolder f = (HibFolder) getSession().createQuery("from HibRootFolder r where r.id = '100001'").uniqueResult();
		getSession().close();
		f.createSubfolder("two");
		startNewTransaction();
		test=getSession().createQuery("from HibSubFolder f where f.name='two'").list();
		assertEquals(1,test.size());
	}
	
	@SuppressWarnings("unchecked")
	@Test 
	public void testMoveSubFolderAddsSubFolderToNewParent() {
		List test = getSession().createQuery("from HibSubFolder sub where sub.id = '100005' and sub.parentFolder.id='100001'").list();
		assertEquals(0,test.size());
		HibRootFolder r =  (HibRootFolder) getSession().createQuery("from HibRootFolder r where r.id = '100001'").uniqueResult();
		HibSubFolder sub = (HibSubFolder) getSession().createQuery("from HibSubFolder r where r.id = '100005'").uniqueResult();
		getSession().close();
		r.moveSubfolder(sub);
		startNewTransaction();
		test = getSession().createQuery("from HibSubFolder sub where sub.id = '100005' and sub.parentFolder.id='100001'").list();
		assertEquals(1,test.size());
	}
	
	@SuppressWarnings("unchecked")
	@Test 
	public void testMoveSubFolderRemovesSubFolderFromOldParent() {
		List test = getSession().createQuery("from HibSubFolder sub where sub.id = '100005' and sub.parentFolder.id='100003'").list();
		assertEquals(1,test.size());
		HibRootFolder r =  (HibRootFolder) getSession().createQuery("from HibRootFolder r where r.id = '100001'").uniqueResult();
		HibSubFolder sub = (HibSubFolder) getSession().createQuery("from HibSubFolder r where r.id = '100005'").uniqueResult();
		getSession().close();
		r.moveSubfolder(sub);
		startNewTransaction();
		test = getSession().createQuery("from HibSubFolder sub where sub.id = '100005' and sub.parentFolder.id='100003'").list();
		assertEquals(0,test.size());
	}
	
	@Test
	public void testCanMoveSubFolder(){
		HibRootFolder r =  (HibRootFolder) getSession().createQuery("from HibRootFolder r where r.id = '100001'").uniqueResult();
		HibSubFolder sub1 = (HibSubFolder) getSession().createQuery("from HibSubFolder r where r.id = '100002'").uniqueResult();
		HibSubFolder sub2 = (HibSubFolder) getSession().createQuery("from HibSubFolder r where r.id = '100005'").uniqueResult();
		getSession().close();
		assertFalse(r.canMoveSubfolder(sub1));
		assertTrue(r.canMoveSubfolder(sub2));
	}
	
	@Test
	public void testCanUseSubFolderName(){
		HibRootFolder r =  (HibRootFolder) getSession().createQuery("from HibRootFolder r where r.id = '100001'").uniqueResult();
		getSession().close();
		assertFalse(r.canUseSubfolderName("subfolder1"));
		assertTrue(r.canUseSubfolderName("JimmyKrankie"));
	}
	
	@Test
	public void folderContainsSubFolderTest(){
		HibRootFolder r =  (HibRootFolder) getSession().createQuery("from HibRootFolder r where r.id = '100001'").uniqueResult();
		HibSubFolder sub1 = (HibSubFolder) getSession().createQuery("from HibSubFolder r where r.id = '100005'").uniqueResult();
		HibSubFolder sub2 = (HibSubFolder) getSession().createQuery("from HibSubFolder r where r.id = '100006'").uniqueResult();
		getSession().close();
		assertTrue(r.containsSubfolder(sub1));
		assertFalse(r.containsSubfolder(sub2));
	}
	
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.testutils.PojoTester#getDbUnitDataFilePath()
	 */
	@Override
	protected String getDbUnitDataFilePath() {
		return "integrationTest/DbRepositoryTestData/RepositoryRefData.xml";
	}
}
