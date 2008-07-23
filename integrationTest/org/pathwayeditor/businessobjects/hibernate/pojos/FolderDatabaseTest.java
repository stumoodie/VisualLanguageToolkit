/**
 * 
 */
package org.pathwayeditor.businessobjects.hibernate.pojos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.pathwayeditor.businessobjects.repository.IMap;
import org.pathwayeditor.testutils.GenericTester;


/**
 * @author nhanlon
 * Tests that the business logic operations tested by FolderBusinessLogicTest result in database changes
 */
public class FolderDatabaseTest extends GenericTester{


	/**
	 * 
	 */
	private static final String JIMMY_KRANKIE = "JimmyKrankie";


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
		assertTrue(r.canUseSubfolderName(JIMMY_KRANKIE));
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
	
	@Test
	public void copySubFolderMakesFolderWithSameNameTest(){
		HibRootFolder r =  (HibRootFolder) getSession().createQuery("from HibRootFolder r where r.id = '100001'").uniqueResult();
		HibSubFolder sub1 = (HibSubFolder) getSession().createQuery("from HibSubFolder r where r.id = '100005'").uniqueResult();
		assertFalse(subFolderExistsCalled(r, sub1.getName()));
		getSession().close();
		r.createCopyOfSubfolder(sub1);
		startNewTransaction();
		assertTrue(subFolderExistsCalled(r, sub1.getName()));
	}
	
	@Test
	public void removeSubFolderTest(){
		HibRootFolder r =  (HibRootFolder) getSession().createQuery("from HibRootFolder r where r.id = '100001'").uniqueResult();
		HibSubFolder sub1 = (HibSubFolder) getSession().createQuery("from HibSubFolder r where r.id = '100002'").uniqueResult();
		assertTrue(r.getSubFolders().contains(sub1));
		getSession().close();
		r.removeSubfolder(sub1);
		startNewTransaction();
		assertFalse(r.getSubFolders().contains(sub1));
	}
	
	@Test
	public void canRenameSubFolderTest(){
		HibRootFolder r =  (HibRootFolder) getSession().createQuery("from HibRootFolder r where r.id = '100001'").uniqueResult();
		HibSubFolder sub1 = (HibSubFolder) getSession().createQuery("from HibSubFolder r where r.id = '100002'").uniqueResult();
		assertFalse(subFolderExistsCalled(r, JIMMY_KRANKIE));
		assertTrue(r.canRenameSubfolder(sub1, JIMMY_KRANKIE));
	}
	@Test
	public void renameSubFolderTest(){
		HibRootFolder r =  (HibRootFolder) getSession().createQuery("from HibRootFolder r where r.id = '100001'").uniqueResult();
		HibSubFolder sub1 = (HibSubFolder) getSession().createQuery("from HibSubFolder r where r.id = '100002'").uniqueResult();
		assertFalse(subFolderExistsCalled(r, JIMMY_KRANKIE));
		getSession().close();
		r.renameSubfolder(sub1, JIMMY_KRANKIE);
		startNewTransaction();
		assertTrue(subFolderExistsCalled(r, JIMMY_KRANKIE));
	}
	@Test
	public void testNumMaps(){
		HibRootFolder r =  (HibRootFolder) getSession().createQuery("from HibRootFolder r where r.id = '100001'").uniqueResult();
		HibSubFolder sub1 = (HibSubFolder) getSession().createQuery("from HibSubFolder r where r.id = '100005'").uniqueResult();
		getSession().close();
		assertEquals(0,r.numMaps());
		assertEquals(1,sub1.numMaps());
	}
	
	@Test
	public void testNumSubFolders(){
		HibRootFolder r =  (HibRootFolder) getSession().createQuery("from HibRootFolder r where r.id = '100001'").uniqueResult();
		getSession().close();
		assertEquals(2,r.numSubFolders());
	}
	
	@Test
	public void testGetMapIteratorCannotBeNull(){
		HibRootFolder r =  (HibRootFolder) getSession().createQuery("from HibRootFolder r where r.id = '100001'").uniqueResult();
		getSession().close();
		assertNotNull(r.getMapIterator());
	}
	
	@Test
	public void testGetMapIteratorIteratesOverMaps(){
		HibSubFolder sub1 = (HibSubFolder) getSession().createQuery("from HibSubFolder r where r.id = '100005'").uniqueResult();
		getSession().close();
		Iterator <? extends IMap>it = sub1.getMapIterator();
		assertTrue(it.next().getName().equals("Diagram name"));
	}
	

	private boolean subFolderExistsCalled(HibRootFolder r, String name) {
		Set<HibSubFolder> subs = r.getSubFolders();
		for (HibSubFolder sub: subs){
			if(sub.getName().equals(name))
				return true;
		}
		return false;
	}
	
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.testutils.PojoTester#getDbUnitDataFilePath()
	 */
	@Override
	protected String getDbUnitDataFilePath() {
		return "integrationTest/DbRepositoryTestData/RepositoryRefData.xml";
	}
}
