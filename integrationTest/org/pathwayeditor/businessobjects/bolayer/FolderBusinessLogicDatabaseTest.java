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
import org.pathwayeditor.businessobjects.repository.IFolder;
import org.pathwayeditor.businessobjects.repository.IFolderContentChangeEvent;
import org.pathwayeditor.businessobjects.repository.IMap;
import org.pathwayeditor.businessobjects.repository.IRepository;
import org.pathwayeditor.businessobjects.repository.IRepositoryItemChangeListener;
import org.pathwayeditor.businessobjects.repository.IRepositoryPropertyChangeEvent;
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
	private static final int TEST_ROOT_INODE = 1;
	private static final int EXPECTED_NUM_ROOT_SUBFOLDERS = 2;
	private IRepository rep;
	private boolean changeDetected;

	@Test
	public void testRootFolderHasRepoSet(){
		IRepository actualRepo = rep;
		assertEquals("expected name", TEST_REPO_NAME, actualRepo.getName());
		IRootFolder actualRoot = actualRepo.getRootFolder();
		assertEquals("root id", TEST_ROOT_INODE, actualRoot.getINode());
		assertNotNull("has repository set", actualRoot.getRepository());
		assertEquals("has correct repository", actualRepo, actualRoot.getRepository());
		assertEquals("num subfolders", EXPECTED_NUM_ROOT_SUBFOLDERS, actualRoot.numSubFolders());
	}
	
	@Test
	public void testSyncroniseAfterAddingSubfolderToRoot() {
		{
			IRootFolder actualRootFolder = rep.getRootFolder();
			actualRootFolder.createSubfolder(JIMMY_KRANKIE);
			this.getRepositoryPersistenceManager().synchronise();
		}
		{
			IRepository actualRepo = rep;
			assertEquals("expected name", TEST_REPO_NAME, actualRepo.getName());
			IRootFolder actualRoot = actualRepo.getRootFolder();
			assertEquals("root id", TEST_ROOT_INODE, actualRoot.getINode());
			assertNotNull("has repository set", actualRoot.getRepository());
			assertEquals("has correct repository", actualRepo, actualRoot.getRepository());
			assertEquals("num subfolders", EXPECTED_NUM_ROOT_SUBFOLDERS+1, actualRoot.numSubFolders());
			assertTrue("has new subfolder", actualRepo.pathExists("/" + JIMMY_KRANKIE + "/"));
		}
	}
	
	@Test
	public void testAddSubfolderToRootFolderListener() {
		IRootFolder actualRootFolder = rep.getRootFolder();
		this.changeDetected = false;
		actualRootFolder.addChangeListener(new IRepositoryItemChangeListener() {

			public void descendentChange(IFolderContentChangeEvent e) {
				changeDetected = true;
			}

			public void propertyChange(IRepositoryPropertyChangeEvent e) {
			}

		});
		actualRootFolder.createSubfolder(JIMMY_KRANKIE);
		assertTrue("listener called", this.changeDetected);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testCreateSubFolderSetsName() {
		List test = runQuery("from HibSubFolder f where f.name='two'");
		assertEquals(0, test.size());
		IRootFolder f = rep.getRootFolder();
		f.createSubfolder("two");
		this.getRepositoryPersistenceManager().synchronise();
		test = runQuery(
				"from HibSubFolder f where f.name='two'");
		assertEquals(1, test.size());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testMoveSubFolderRemovesSubFolderFromOldParent() {
		IRootFolder r = rep.getRootFolder();
		IFolder sub = (ISubFolder)rep.getFolderByPath("/subfolder2/subfolder4/"); 
		r.moveSubfolder((ISubFolder) sub);
		this.getRepositoryPersistenceManager().synchronise();
		List test =runQuery(
						"from HibSubFolder sub where sub.id = '100005' and sub.parentFolder.id='100003'");
		assertEquals(0, test.size());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testMoveSubFolderAddsSubFolderToNewParent() {
		IRootFolder r = rep.getRootFolder();
		IFolder sub = rep.getFolderByPath("/subfolder2/subfolder4/");
		r.moveSubfolder((ISubFolder) sub);
		this.getRepositoryPersistenceManager().synchronise();
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
		ISubFolder sub2 = sub1.subFolderIterator().next();
		assertFalse(r.canMoveSubfolder(sub1));
		assertTrue(r.canMoveSubfolder(sub2));
	}

	private ISubFolder getSubFolderThatHasChildren(IRootFolder r,
			ISubFolder sub1) {
		Iterator<? extends ISubFolder> it = r.subFolderIterator();
		while (it.hasNext()) {
			sub1 = it.next();
			if (sub1.subFolderIterator().hasNext())
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
	public void copySubFolderMakesFolderWithSameNameTest() {
		//rep = this.getBusinessObjectFactory().getRepository("repo name");
		IRootFolder r = rep.getRootFolder();
		ISubFolder sub1 = (ISubFolder) rep.getFolderByPath("/subfolder2/subfolder4/");
		assertFalse(subFolderExistsCalled(r, sub1.getName()));
		r.createCopyOfSubfolder(sub1);
		this.getRepositoryPersistenceManager().synchronise();
		assertTrue(subFolderExistsCalled(r, sub1.getName()));
		Session sess = getSessionFactory().getCurrentSession();
		sess.beginTransaction();
		ISubFolder sub = (ISubFolder)sess.createQuery(
			"from HibSubFolder s where s.parentFolder =:parent and s.name = :name")
			.setEntity("parent", r).setString("name", sub1.getName())
			.uniqueResult();
		assertNotNull(sub);
		sess.getTransaction().commit();
	}

	@Test
	public void removeSubFolderTest() {
		IRootFolder r = rep.getRootFolder();
		ISubFolder sub1 =  (ISubFolder)rep.getFolderByPath("/subfolder1/");
		assertTrue(r.containsSubfolder(sub1));
		r.removeSubfolder(sub1);
		this.getRepositoryPersistenceManager().synchronise();
		assertFalse(r.containsSubfolder(sub1));
		assertNull(runUniqueQuery("from HibSubFolder r where r.id = '100002'"));
	}
	
	@Test
	public void removeMapWhenMapFromDatabaseQueryTest(){
		ISubFolder sub1 =  (ISubFolder)rep.getFolderByPath("/subfolder2/subfolder4/");
//		IMap map = (IMap) runQuery("from HibMap m where m.id = '100001'").uniqueResult();
//		assertEquals(1,sub1.getNumMaps());
		IMap map = sub1.mapIterator().next();
		int iNode = map.getINode();
		sub1.removeMap(map);
		assertEquals(0,sub1.numMaps());
		this.getRepositoryPersistenceManager().synchronise();
		Session sess = getSessionFactory().getCurrentSession();
		sess.beginTransaction();
		IMap mapResult = (IMap)sess.createQuery("from HibMap m where m.INode = ?").setInteger(0, iNode).uniqueResult();
		sess.getTransaction().commit();
		assertNull(mapResult);
	}
	
	@Test
	public void removeMapWhenMapFromFolderLookupTest(){
		ISubFolder sub1 =  (ISubFolder)rep.getFolderByPath("/subfolder2/subfolder4/");
		IMap map=null;
		Iterator<IMap> mapIter = sub1.mapIterator();
		while(mapIter.hasNext()){
			IMap search = mapIter.next();
			if(search.getName().equals("Diagram name"))
				map=search;
		}
		assertEquals(1,sub1.numMaps());
		sub1.removeMap(map);
		assertEquals(0,sub1.numMaps());
		this.getRepositoryPersistenceManager().synchronise();
		assertNull(runUniqueQuery("from HibMap m where m.id = '100001'"));
	}
	
	@Test
	public void removeMapWhenMapBoSynchroniseCalledTwiceTest(){
		ISubFolder sub1 =  (ISubFolder)rep.getFolderByPath("/subfolder2/subfolder4/");
		IMap map=null;
		Iterator<IMap> mapIter = sub1.mapIterator();
		while(mapIter.hasNext()){
			IMap search = mapIter.next();
			if(search.getName().equals("Diagram name"))
				map=search;
		}
		this.getRepositoryPersistenceManager().synchronise();
		assertEquals(1,sub1.numMaps());
		sub1.removeMap(map);
		assertEquals(0,sub1.numMaps());
		this.getRepositoryPersistenceManager().synchronise();
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
	public void renameSubFolderTest() {
		IRootFolder r = rep.getRootFolder();
		ISubFolder sub1 =  (ISubFolder)rep.getFolderByPath("/subfolder1/");
		assertFalse(subFolderExistsCalled(r, JIMMY_KRANKIE));
		r.renameSubfolder(sub1, JIMMY_KRANKIE);
		this.getRepositoryPersistenceManager().synchronise();
		assertTrue(subFolderExistsCalled(r, JIMMY_KRANKIE));
	}

	@Test
	public void testNumMaps() {
		IRootFolder r = rep.getRootFolder();
		ISubFolder sub1 =  (ISubFolder)rep.getFolderByPath("/subfolder2/subfolder4/");
		assertEquals(0, r.numMaps());
		assertEquals(1, sub1.numMaps());
	}

	@Test
	public void testNumSubFolders() {
		IRootFolder r = rep.getRootFolder();
		assertEquals(2, r.numSubFolders());
	}

	@Test
	public void testGetMapIteratorCannotBeNull() {
		IRootFolder r = rep.getRootFolder();
		assertNotNull(r.mapIterator());
	}

	@Test
	public void testGetMapIteratorIteratesOverMaps() {
		ISubFolder sub1 =  (ISubFolder)rep.getFolderByPath("/subfolder2/subfolder4/");
		Iterator<? extends IMap> it = sub1.mapIterator();
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
	public void testRootFolderCreateMapHappyCase() {
		IRootFolder r = rep.getRootFolder();
		assertFalse(mapExistsCalled( r, JIMMY_KRANKIE));
		r.createMap(JIMMY_KRANKIE);
		this.getRepositoryPersistenceManager().synchronise();
		assertTrue(mapExistsCalled( r, JIMMY_KRANKIE));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCreateMapNameAlreadyInUseThrowsIllegalArgument()
			throws SQLException {
		IRootFolder r = rep.getRootFolder();
		assertFalse(mapExistsCalled( r, JIMMY_KRANKIE));
		r.createMap(JIMMY_KRANKIE);
		this.getRepositoryPersistenceManager().synchronise();
		r.createMap(JIMMY_KRANKIE);
	}

	@Test
	public void testContainsMapHappyCase() {
		ISubFolder sub1 =  (ISubFolder)rep.getFolderByPath("/subfolder2/subfolder4/");
		IMap map = sub1.mapIterator().next();
		assertTrue(sub1.containsMap(map));
	}

	@Test
	public void testContainsMapFalse() {
		ISubFolder sub1 =  (ISubFolder)rep.getFolderByPath("/subfolder2/subfolder4/");
		IRootFolder r = rep.getRootFolder();
		IMap map = sub1.mapIterator().next();
		assertTrue(sub1.containsMap(map));
		assertFalse(r.containsMap(map));
	}

	@Test
	public void testCreateCopyOfMapHappyCase() {
		ISubFolder sub1 = (ISubFolder) rep
				.getFolderByPath("/subfolder2/subfolder4/");
		IRootFolder r = rep.getRootFolder();
		IMap map = sub1.mapIterator().next();
		assertFalse(mapExistsCalled( r, map.getName()));
		{
			Session sess = getSessionFactory().getCurrentSession();
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
			this.getRepositoryPersistenceManager().synchronise();
			assertTrue(mapExistsCalled(r, map.getName()));
			Session sess = getSessionFactory().getCurrentSession();
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
	public void testMoveMapHappyCaseAddsMapToNewParent() {
		ISubFolder sub1 =  (ISubFolder)rep.getFolderByPath("/subfolder2/subfolder4/");
		IRootFolder r = rep.getRootFolder();
		IMap map = sub1.mapIterator().next();
		assertFalse(mapExistsCalled(r, map.getName()));
		assertTrue(mapExistsCalled(sub1, map.getName()));
		r.moveMap(map);
		this.getRepositoryPersistenceManager().synchronise();
		assertTrue(mapExistsCalled(r, map.getName()));
		assertFalse(mapExistsCalled(sub1, map.getName()));
		{
			Session sess = getSessionFactory().getCurrentSession();
			sess.beginTransaction();
			int actualSize = sess.createQuery(
				"from HibMap m where m.folder =:parent and m.name = :name")
				.setEntity("parent", r)
				.setString("name", map.getName()).list().size();
			sess.getTransaction().commit();
			assertEquals(1, actualSize);
		}
		{
			Session sess = getSessionFactory().getCurrentSession();
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
	public void testMoveMapHappyCaseRemovesMapFromOldParent() {
		ISubFolder sub1 =  (ISubFolder)rep.getFolderByPath("/subfolder2/subfolder4/");
		IRootFolder r = rep.getRootFolder();
		IMap map = sub1.mapIterator().next();
		assertFalse(mapExistsCalled( r, map.getName()));
		{
			Session sess = getSessionFactory().getCurrentSession();
			sess.beginTransaction();
			int actualSize = sess.createQuery(
				"from HibMap m where m.folder =:parent and m.name = :name")
				.setEntity("parent", sub1).setString("name",
						map.getName()).list().size();
			sess.getTransaction().commit();
			assertEquals(1, actualSize);
		}
		r.moveMap(map);
		this.getRepositoryPersistenceManager().synchronise();
		{
			Session sess = getSessionFactory().getCurrentSession();
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
		IMap map = sub1.mapIterator().next();
		assertTrue(sub1.canRenameMap(map, JIMMY_KRANKIE));
		assertFalse(sub1.canRenameMap(map, map.getName()));
	}

	@Test
	public void testRenameMapHappyCase() {
		ISubFolder sub1 =  (ISubFolder)rep.getFolderByPath("/subfolder2/subfolder4/");
		IMap map = sub1.mapIterator().next();
		assertFalse(mapExistsCalled(sub1, JIMMY_KRANKIE));
		{
			Session sess = getSessionFactory().getCurrentSession();
			sess.beginTransaction();
			int actualSize = sess.createQuery(
				"from HibMap m where m.folder =:parent and m.name = :name")
				.setEntity("parent", sub1).setString("name",
					JIMMY_KRANKIE).list().size();
			sess.getTransaction().commit();
			assertEquals(0, actualSize);
		}
		sub1.renameMap(map, JIMMY_KRANKIE);
		this.getRepositoryPersistenceManager().synchronise();
		assertTrue(mapExistsCalled(sub1, JIMMY_KRANKIE));
		{
			Session sess = getSessionFactory().getCurrentSession();
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
		Iterator<? extends ISubFolder> it = r.subFolderIterator();
		assertTrue(it.next().getName().equals("subfolder1") ? it.next()
				.getName().equals("subfolder2") : it.next().getName().equals(
				"subfolder1"));
	}

	private boolean subFolderExistsCalled(IRootFolder r, String name) {
		Iterator<ISubFolder> iter = r.subFolderIterator();
		while (iter.hasNext()) {
			ISubFolder sub = iter.next();
			if (sub.getName().equals(name))
				return true;
		}
		return false;
	}

	private boolean mapExistsCalled(IFolder r, String name) {
		Iterator<IMap> mapIterator = r.mapIterator();
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
		rep = this.getRepositoryPersistenceManager().getRepository();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.testutils.GenericTester#doAdditionalTearDown()
	 */
	@Override
	protected void doAdditionalTearDown() {
		rep = null;
	}
}
