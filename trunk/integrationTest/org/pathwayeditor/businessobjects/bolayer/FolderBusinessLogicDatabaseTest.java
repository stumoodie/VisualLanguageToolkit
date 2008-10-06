/**
 * 
 */
package org.pathwayeditor.businessobjects.bolayer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.junit.Test;
import org.pathwayeditor.businessobjects.management.PersistenceManagerException;
import org.pathwayeditor.businessobjects.management.PersistenceManagerNotOpenException;
import org.pathwayeditor.businessobjects.repository.IFolder;
import org.pathwayeditor.businessobjects.repository.IMap;
import org.pathwayeditor.businessobjects.repository.IRepository;
import org.pathwayeditor.businessobjects.repository.IRootFolder;
import org.pathwayeditor.businessobjects.repository.ISubFolder;
import org.pathwayeditor.testutils.GenericTester;

/**
 * @author nhanlon Tests that the business logic operations tested by
 *         FolderBusinessLogicTest result in database changes
 */
public class FolderBusinessLogicDatabaseTest extends GenericTester {

	private static final String JIMMY_KRANKIE = "JimmyKrankie";
	private static final String TEST_REPO_NAME = "repo name";
	private IRepository rep;

	@SuppressWarnings("unchecked")
	@Test
	public void testCreateSubFolderSetsName() throws PersistenceManagerException {
		List test = runQuery("from HibSubFolder f where f.name='two'");
		assertEquals(0, test.size());
		IRootFolder f = rep.getRootFolder();
		f.createSubfolder("two");
		this.getBusinessObjectFactory().synchroniseRepository();
		test = runQuery(
				"from HibSubFolder f where f.name='two'");
		assertEquals(1, test.size());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testMoveSubFolderRemovesSubFolderFromOldParent() throws PersistenceManagerException {
		IRootFolder r = rep.getRootFolder();
		IFolder sub = (ISubFolder)rep.getFolderByPath("/subfolder2/subfolder4/"); 
		r.moveSubfolder((ISubFolder) sub);
		this.getBusinessObjectFactory().synchroniseRepository();
		List test =runQuery(
						"from HibSubFolder sub where sub.id = '100005' and sub.parentFolder.id='100003'");
		assertEquals(0, test.size());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testMoveSubFolderAddsSubFolderToNewParent() throws PersistenceManagerException {
		IRootFolder r = rep.getRootFolder();
		IFolder sub = rep.getFolderByPath("/subfolder2/subfolder4/");
		r.moveSubfolder((ISubFolder) sub);
		this.getBusinessObjectFactory().synchroniseRepository();
		assertTrue(subFolderExistsCalled(r, "subfolder4"));
		List test = runQuery(
						"from HibSubFolder sub where sub.name = 'subfolder4' and sub.parentFolder.id='100001'");
		assertEquals(1, test.size());
	}

	@Test
	public void testCanMoveSubFolder() {
		IRootFolder r = rep.getRootFolder();
		ISubFolder sub1 = null;
		sub1 = getSubFolderThatHasChildren(r, sub1);
		ISubFolder sub2 = sub1.getSubFolderIterator().next();
		assertFalse(r.canMoveSubfolder(sub1));
		assertTrue(r.canMoveSubfolder(sub2));
	}

	private ISubFolder getSubFolderThatHasChildren(IRootFolder r,
			ISubFolder sub1) {
		Iterator<? extends ISubFolder> it = r.getSubFolderIterator();
		while (it.hasNext()) {
			sub1 = it.next();
			if (sub1.getSubFolderIterator().hasNext())
				break;
		}
		return sub1;
	}

	@Test
	public void testCanUseSubFolderName() {
		IRootFolder r = rep.getRootFolder();
		assertFalse(r.canUseSubfolderName("subfolder1"));
		assertTrue(r.canUseSubfolderName(JIMMY_KRANKIE));
	}

	@Test
	public void folderContainsSubFolderTest() {
		IRootFolder r = rep.getRootFolder();
		ISubFolder sub1 =  (ISubFolder)rep.getFolderByPath("/subfolder1/");
		ISubFolder sub2 =  (ISubFolder)rep.getFolderByPath("/subfolder2/subfolder4/");
		assertTrue(r.containsSubfolder(sub1));
		assertFalse(r.containsSubfolder(sub2));
	}

	@Test
	public void copySubFolderMakesFolderWithSameNameTest() throws PersistenceManagerException {
		//rep = this.getBusinessObjectFactory().getRepository("repo name");
		IRootFolder r = rep.getRootFolder();
		ISubFolder sub1 = (ISubFolder) rep.getFolderByPath("/subfolder2/subfolder4/");
		assertFalse(subFolderExistsCalled(r, sub1.getName()));
		r.createCopyOfSubfolder(sub1);
		this.getBusinessObjectFactory().synchroniseRepository();
		assertTrue(subFolderExistsCalled(r, sub1.getName()));
		Session sess = getSession();
		sess.beginTransaction();
		ISubFolder sub = (ISubFolder)sess.createQuery(
			"from HibSubFolder s where s.parentFolder =:parent and s.name = :name")
			.setEntity("parent", r).setString("name", sub1.getName())
			.uniqueResult();
		assertNotNull(sub);
	}

	@Test
	public void removeSubFolderTest() throws PersistenceManagerException {
		IRootFolder r = rep.getRootFolder();
		ISubFolder sub1 =  (ISubFolder)rep.getFolderByPath("/subfolder1/");
		assertTrue(r.containsSubfolder(sub1));
		r.removeSubfolder(sub1);
		this.getBusinessObjectFactory().synchroniseRepository();
		assertFalse(r.containsSubfolder(sub1));
		assertNull(runUniqueQuery("from HibSubFolder r where r.id = '100002'"));
	}
	
	@Test
	public void removeMapWhenMapFromDatabaseQueryTest() throws PersistenceManagerException{
		ISubFolder sub1 =  (ISubFolder)rep.getFolderByPath("/subfolder2/subfolder4/");
//		IMap map = (IMap) runQuery("from HibMap m where m.id = '100001'").uniqueResult();
//		assertEquals(1,sub1.getNumMaps());
		IMap map = sub1.getMapIterator().next();
		int iNode = map.getINode();
		sub1.removeMap(map);
		assertEquals(0,sub1.getNumMaps());
		this.getBusinessObjectFactory().synchroniseRepository();
		Session sess = getSession();
		sess.beginTransaction();
		IMap mapResult = (IMap)sess.createQuery("from HibMap m where m.iNode = ?").setInteger(0, iNode).uniqueResult();
		sess.getTransaction().commit();
		assertNull(mapResult);
	}
	
	@Test
	public void removeMapWhenMapFromFolderLookupTest() throws PersistenceManagerException{
		ISubFolder sub1 =  (ISubFolder)rep.getFolderByPath("/subfolder2/subfolder4/");
		IMap map=null;
		Iterator<IMap> mapIter = sub1.getMapIterator();
		while(mapIter.hasNext()){
			IMap search = mapIter.next();
			if(search.getName().equals("Diagram name"))
				map=search;
		}
		assertEquals(1,sub1.getNumMaps());
		sub1.removeMap(map);
		assertEquals(0,sub1.getNumMaps());
		this.getBusinessObjectFactory().synchroniseRepository();
		assertNull(runUniqueQuery("from HibMap m where m.id = '100001'"));
	}
	
	@Test
	public void removeMapWhenMapBoSynchroniseCalledTwiceTest() throws PersistenceManagerException{
		ISubFolder sub1 =  (ISubFolder)rep.getFolderByPath("/subfolder2/subfolder4/");
		IMap map=null;
		Iterator<IMap> mapIter = sub1.getMapIterator();
		while(mapIter.hasNext()){
			IMap search = mapIter.next();
			if(search.getName().equals("Diagram name"))
				map=search;
		}
		this.getBusinessObjectFactory().synchroniseRepository();
		assertEquals(1,sub1.getNumMaps());
		sub1.removeMap(map);
		assertEquals(0,sub1.getNumMaps());
		this.getBusinessObjectFactory().synchroniseRepository();
		assertNull(runUniqueQuery(
		"from HibMap m where m.id = '100001'"));
	}
	
	@Test
	public void canRenameSubFolderTest() {
		IRootFolder r = rep.getRootFolder();
		ISubFolder sub1 =  (ISubFolder)rep.getFolderByPath("/subfolder1/");
		assertFalse(subFolderExistsCalled(r, JIMMY_KRANKIE));
		assertTrue(r.canRenameSubfolder(sub1, JIMMY_KRANKIE));
	}

	@Test
	public void renameSubFolderTest() throws PersistenceManagerException {
		IRootFolder r = rep.getRootFolder();
		ISubFolder sub1 =  (ISubFolder)rep.getFolderByPath("/subfolder1/");
		assertFalse(subFolderExistsCalled(r, JIMMY_KRANKIE));
		r.renameSubfolder(sub1, JIMMY_KRANKIE);
		this.getBusinessObjectFactory().synchroniseRepository();
		assertTrue(subFolderExistsCalled(r, JIMMY_KRANKIE));
	}

	@Test
	public void testNumMaps() {
		IRootFolder r = rep.getRootFolder();
		ISubFolder sub1 =  (ISubFolder)rep.getFolderByPath("/subfolder2/subfolder4/");
		assertEquals(0, r.getNumMaps());
		assertEquals(1, sub1.getNumMaps());
	}

	@Test
	public void testNumSubFolders() {
		IRootFolder r = rep.getRootFolder();
		assertEquals(2, r.numSubFolders());
	}

	@Test
	public void testGetMapIteratorCannotBeNull() {
		IRootFolder r = rep.getRootFolder();
		assertNotNull(r.getMapIterator());
	}

	@Test
	public void testGetMapIteratorIteratesOverMaps() {
		ISubFolder sub1 =  (ISubFolder)rep.getFolderByPath("/subfolder2/subfolder4/");
		Iterator<? extends IMap> it = sub1.getMapIterator();
		String name = it.next().getName();
		assertTrue(name.equals("Diagram name")||name.equals("Diagram name2"));
	}

	@Test
	public void testCanUseMapNameHappyCase() {
		ISubFolder sub1 =  (ISubFolder)rep.getFolderByPath("/subfolder2/subfolder4/");
		assertTrue(sub1.canUseMapName(JIMMY_KRANKIE));
	}

	@Test
	public void testCanUseMapNameNotAllowed() {
		ISubFolder sub1 =  (ISubFolder)rep.getFolderByPath("/subfolder2/subfolder4/");
		assertTrue(sub1.canUseMapName(JIMMY_KRANKIE));
		assertFalse(sub1.canUseMapName("Diagram name"));
	}

	@Test
	public void testRootFolderCreateMapHappyCase() throws PersistenceManagerException {
		IRootFolder r = rep.getRootFolder();
		assertFalse(mapExistsCalled( r, JIMMY_KRANKIE));
		r.createMap(JIMMY_KRANKIE);
		this.getBusinessObjectFactory().synchroniseRepository();
		assertTrue(mapExistsCalled( r, JIMMY_KRANKIE));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCreateMapNameAlreadyInUseThrowsIllegalArgument()
			throws SQLException, PersistenceManagerException {
		IRootFolder r = rep.getRootFolder();
		assertFalse(mapExistsCalled( r, JIMMY_KRANKIE));
		r.createMap(JIMMY_KRANKIE);
		this.getBusinessObjectFactory().synchroniseRepository();
		r.createMap(JIMMY_KRANKIE);
	}

	@Test
	public void testContainsMapHappyCase() {
		ISubFolder sub1 =  (ISubFolder)rep.getFolderByPath("/subfolder2/subfolder4/");
		IMap map = sub1.getMapIterator().next();
		assertTrue(sub1.containsMap(map));
	}

	@Test
	public void testContainsMapFalse() {
		ISubFolder sub1 =  (ISubFolder)rep.getFolderByPath("/subfolder2/subfolder4/");
		IRootFolder r = rep.getRootFolder();
		IMap map = sub1.getMapIterator().next();
		assertTrue(sub1.containsMap(map));
		assertFalse(r.containsMap(map));
	}

	@Test
	public void testCreateCopyOfMapHappyCase() throws PersistenceManagerException {
		ISubFolder sub1 = (ISubFolder) rep
				.getFolderByPath("/subfolder2/subfolder4/");
		IRootFolder r = rep.getRootFolder();
		IMap map = sub1.getMapIterator().next();
		assertFalse(mapExistsCalled( r, map.getName()));
		{
			Session sess = getSession();
			sess.beginTransaction();
			int actualSize = sess.createQuery(
					"from HibMap m where m.folder =:parent and m.name = :name")
					.setEntity("parent", r).setString("name", map.getName())
					.list().size();
			sess.getTransaction().commit();
			assertEquals(0, actualSize);
		}
		r.createCopyOfMap(map);
		{
			this.getBusinessObjectFactory().synchroniseRepository();
			assertTrue(mapExistsCalled(r, map.getName()));
			Session sess = getSession();
			sess.beginTransaction();
			int actualSize = sess.createQuery(
					"from HibMap m where m.folder =:parent and m.name = :name")
					.setEntity("parent", r).setString("name", map.getName())
					.list().size();
			sess.getTransaction().commit();
			assertEquals(1, actualSize);
		}
	}

	@Test
	public void testMoveMapHappyCaseAddsMapToNewParent() throws PersistenceManagerException {
		ISubFolder sub1 =  (ISubFolder)rep.getFolderByPath("/subfolder2/subfolder4/");
		IRootFolder r = rep.getRootFolder();
		IMap map = sub1.getMapIterator().next();
		assertFalse(mapExistsCalled(r, map.getName()));
		assertTrue(mapExistsCalled(sub1, map.getName()));
		r.moveMap(map);
		this.getBusinessObjectFactory().synchroniseRepository();
		assertTrue(mapExistsCalled(r, map.getName()));
		assertFalse(mapExistsCalled(sub1, map.getName()));
		{
			Session sess = getSession();
			sess.beginTransaction();
			int actualSize = sess.createQuery(
				"from HibMap m where m.folder =:parent and m.name = :name")
				.setEntity("parent", r)
				.setString("name", map.getName()).list().size();
			sess.getTransaction().commit();
			assertEquals(1, actualSize);
		}
		{
			Session sess = getSession();
			sess.beginTransaction();
			int actualSize = sess.createQuery(
				"from HibMap m where m.folder =:parent and m.name = :name")
				.setEntity("parent", sub1)
				.setString("name", map.getName()).list().size();
			sess.getTransaction().commit();
			assertEquals(0, actualSize);
		}
	}

	@Test
	public void testMoveMapHappyCaseRemovesMapFromOldParent() throws PersistenceManagerException {
		ISubFolder sub1 =  (ISubFolder)rep.getFolderByPath("/subfolder2/subfolder4/");
		IRootFolder r = rep.getRootFolder();
		IMap map = sub1.getMapIterator().next();
		assertFalse(mapExistsCalled( r, map.getName()));
		{
			Session sess = getSession();
			sess.beginTransaction();
			int actualSize = sess.createQuery(
				"from HibMap m where m.folder =:parent and m.name = :name")
				.setEntity("parent", sub1).setString("name",
						map.getName()).list().size();
			sess.getTransaction().commit();
			assertEquals(1, actualSize);
		}
		r.moveMap(map);
		this.getBusinessObjectFactory().synchroniseRepository();
		{
			Session sess = getSession();
			sess.beginTransaction();
			int actualSize = sess.createQuery(
				"from HibMap m where m.folder =:parent and m.name = :name")
				.setEntity("parent", sub1).setString("name",
						map.getName()).list().size();
				sess.getTransaction().commit();
			assertEquals(0, actualSize);
		}
	}

	@Test
	public void testCanRenameMapHappyCase() {
		ISubFolder sub1 =  (ISubFolder)rep.getFolderByPath("/subfolder2/subfolder4/");
		IMap map = sub1.getMapIterator().next();
		assertTrue(sub1.canRenameMap(map, JIMMY_KRANKIE));
		assertFalse(sub1.canRenameMap(map, map.getName()));
	}

	@Test
	public void testRenameMapHappyCase() throws PersistenceManagerException {
		ISubFolder sub1 =  (ISubFolder)rep.getFolderByPath("/subfolder2/subfolder4/");
		IMap map = sub1.getMapIterator().next();
		assertFalse(mapExistsCalled(sub1, JIMMY_KRANKIE));
		{
			Session sess = getSession();
			sess.beginTransaction();
			int actualSize = sess.createQuery(
				"from HibMap m where m.folder =:parent and m.name = :name")
				.setEntity("parent", sub1).setString("name",
					JIMMY_KRANKIE).list().size();
			sess.getTransaction().commit();
			assertEquals(0, actualSize);
		}
		sub1.renameMap(map, JIMMY_KRANKIE);
		this.getBusinessObjectFactory().synchroniseRepository();
		assertTrue(mapExistsCalled(sub1, JIMMY_KRANKIE));
		{
			Session sess = getSession();
			sess.beginTransaction();
			int actualSize = sess.createQuery(
				"from HibMap m where m.folder =:parent and m.name = :name")
				.setEntity("parent", sub1).setString("name",
						JIMMY_KRANKIE).list().size();
			sess.getTransaction().commit();
			assertEquals(1, actualSize);
		}
	}

	@Test
	public void testSubFolderIterator() {
		IRootFolder r = rep.getRootFolder();
		Iterator<? extends ISubFolder> it = r.getSubFolderIterator();
		assertTrue(it.next().getName().equals("subfolder1") ? it.next()
				.getName().equals("subfolder2") : it.next().getName().equals(
				"subfolder1"));
	}

	private boolean subFolderExistsCalled(IRootFolder r, String name) {
		Iterator<ISubFolder> iter = r.getSubFolderIterator();
		while (iter.hasNext()) {
			ISubFolder sub = iter.next();
			if (sub.getName().equals(name))
				return true;
		}
		return false;
	}

	private boolean mapExistsCalled(IFolder r, String name) {
		Iterator<IMap> mapIterator = r.getMapIterator();
		while(mapIterator.hasNext()){
			IMap map = mapIterator.next();
			if (map.getName().equals(name))
				return true;
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pathwayeditor.testutils.PojoTester#getDbUnitDataFilePath()
	 */
	@Override
	protected String getDbUnitDataFilePath() {
		return "integrationTest/DbSourceData/DbSourceRepositoryRefData.xml";
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.testutils.GenericTester#getTestRepositoryName()
	 */
	@Override
	protected String getTestRepositoryName() {
		return TEST_REPO_NAME;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.testutils.GenericTester#doAdditionalSetUp()
	 */
	@Override
	protected void doAdditionalSetUp() {
		try {
			rep = this.getBusinessObjectFactory().getRepository();
		} catch (PersistenceManagerNotOpenException e) {
			throw new RuntimeException(e);
		}
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.testutils.GenericTester#doAdditionalTearDown()
	 */
	@Override
	protected void doAdditionalTearDown() {
		rep = null;
	}
}
