/**
 * 
 */
package org.pathwayeditor.businessobjects.hibernate.pojos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.pathwayeditor.businessobjects.bolayer.BusinessObjectFactory;
import org.pathwayeditor.businessobjects.bolayer.IBusinessObjectFactory;
import org.pathwayeditor.businessobjects.database.util.ConnectionInfo;
import org.pathwayeditor.businessobjects.database.util.HibernateUtil;
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
	private IRepository rep;
	private IBusinessObjectFactory bo =new BusinessObjectFactory(new ConnectionInfo());

	@Before	
	@Override
	public void setUp() throws Exception {
		super.setUp();
		rep = ((BusinessObjectFactory) bo).getFreshRepository("repo name");
	}
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.testutils.GenericTester#tearDown()
	 */
	@Override
	public void tearDown() throws Exception {
		super.tearDown();
		HibernateUtil.commit();
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testCreateSubFolderSetsName() {
		List test = getSession().createQuery(
				"from HibSubFolder f where f.name='two'").list();
		assertEquals(0, test.size());
		IRootFolder f = rep.getRootFolder();
		f.createSubfolder("two");
		bo.synchroniseRepository();
		test = getSession().createQuery(
				"from HibSubFolder f where f.name='two'").list();
		assertEquals(1, test.size());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testMoveSubFolderRemovesSubFolderFromOldParent() {
		IRootFolder r = rep.getRootFolder();
		IFolder sub = rep.getFolderByPath("/subfolder2/subfolder4"); 
		r.moveSubfolder((ISubFolder) sub);
		bo.synchroniseRepository();
		List test = getSession()
				.createQuery(
						"from HibSubFolder sub where sub.id = '100005' and sub.parentFolder.id='100003'")
				.list();
		assertEquals(0, test.size());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testMoveSubFolderAddsSubFolderToNewParent() {
		IRootFolder r = rep.getRootFolder();
		IFolder sub = rep.getFolderByPath("/subfolder2/subfolder4");
		r.moveSubfolder((ISubFolder) sub);
		bo.synchroniseRepository();
		assertTrue(subFolderExistsCalled( (HibRootFolder) r, "subfolder4"));
		List test = getSession()
				.createQuery(
						"from HibSubFolder sub where sub.name = 'subfolder4' and sub.parentFolder.id='100001'")
				.list();
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
		HibSubFolder sub1 = (HibSubFolder) getSession().createQuery(
				"from HibSubFolder r where r.id = '100005'").uniqueResult();
		HibSubFolder sub2 = (HibSubFolder) getSession().createQuery(
				"from HibSubFolder r where r.id = '100006'").uniqueResult();
		assertTrue(r.containsSubfolder(sub1));
		assertFalse(r.containsSubfolder(sub2));
	}

	@Test
	public void copySubFolderMakesFolderWithSameNameTest() {
		//rep = bo.getRepository("repo name");
		IRootFolder r = rep.getRootFolder();
		ISubFolder sub1 = (ISubFolder) rep.getFolderByPath("/subfolder2/subfolder4");
		assertFalse(subFolderExistsCalled((HibRootFolder) r, sub1.getName()));
		r.createCopyOfSubfolder(sub1);
		bo.synchroniseRepository();
		assertTrue(subFolderExistsCalled((HibRootFolder) r, sub1.getName()));
		assertNotNull(getSession()
				.createQuery(
						"from HibSubFolder s where s.parentFolder =:parent and s.name = :name")
				.setEntity("parent", r).setString("name", sub1.getName())
				.uniqueResult());
	}

	@Test
	public void removeSubFolderTest() {
		HibRootFolder r = (HibRootFolder) rep.getRootFolder();
		HibSubFolder sub1 = (HibSubFolder) rep.getFolderByPath("/subfolder1");
		assertTrue(r.getSubFolders().contains(sub1));
		r.removeSubfolder(sub1);
		bo.synchroniseRepository();
		assertFalse(r.getSubFolders().contains(sub1));
		assertNull(getSession().createQuery(
				"from HibSubFolder r where r.id = '100002'").uniqueResult());
	}
	
	@Test
	public void removeMapTest(){
		HibSubFolder sub1 = (HibSubFolder) rep.getFolderByPath("/subfolder2/subfolder4");
		IMap map = (IMap) getSession().createQuery("from HibMapDiagram m where m.id = '100001'").uniqueResult();
		assertEquals(1,sub1.numMaps());
		sub1.removeMap(map);
		assertEquals(0,sub1.numMaps());
		bo.synchroniseRepository();
		assertNull(getSession().createQuery(
		"from HibMapDiagram m where m.id = '100001'").uniqueResult());
	}
	
	@Test
	public void canRenameSubFolderTest() {
		IRootFolder r = rep.getRootFolder();
		HibSubFolder sub1 = (HibSubFolder) rep.getFolderByPath("/subfolder1");
		assertFalse(subFolderExistsCalled((HibRootFolder) r, JIMMY_KRANKIE));
		assertTrue(r.canRenameSubfolder(sub1, JIMMY_KRANKIE));
	}

	@Test
	public void renameSubFolderTest() {
		IRootFolder r = rep.getRootFolder();
		HibSubFolder sub1 = (HibSubFolder) rep.getFolderByPath("/subfolder1");
		assertFalse(subFolderExistsCalled((HibRootFolder) r, JIMMY_KRANKIE));
		r.renameSubfolder(sub1, JIMMY_KRANKIE);
		bo.synchroniseRepository();
		assertTrue(subFolderExistsCalled((HibRootFolder) r, JIMMY_KRANKIE));
	}

	@Test
	public void testNumMaps() {
		IRootFolder r = rep.getRootFolder();
		HibSubFolder sub1 = (HibSubFolder) rep.getFolderByPath("/subfolder2/subfolder4");
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
		assertNotNull(r.getMapIterator());
	}

	@Test
	public void testGetMapIteratorIteratesOverMaps() {
		HibSubFolder sub1 = (HibSubFolder) rep.getFolderByPath("/subfolder2/subfolder4");
		Iterator<? extends IMap> it = sub1.getMapIterator();
		assertTrue(it.next().getName().equals("Diagram name"));
	}

	@Test
	public void testCanUseMapNameHappyCase() {
		HibSubFolder sub1 = (HibSubFolder) rep.getFolderByPath("/subfolder2/subfolder4");
		assertTrue(sub1.canUseMapName(JIMMY_KRANKIE));
	}

	@Test
	public void testCanUseMapNameNotAllowed() {
		HibSubFolder sub1 = (HibSubFolder) rep.getFolderByPath("/subfolder2/subfolder4");
		assertTrue(sub1.canUseMapName(JIMMY_KRANKIE));
		assertFalse(sub1.canUseMapName("Diagram name"));
	}

	@Test
	public void testRootFolderCreateMapHappyCase() {
		IRootFolder r = rep.getRootFolder();
		assertFalse(mapExistsCalled((HibFolder) r, JIMMY_KRANKIE));
		r.createMap(JIMMY_KRANKIE);
		bo.synchroniseRepository();
		r = (HibRootFolder) getSession().createQuery(
				"from HibRootFolder r where r.id = '100001'").uniqueResult();
		assertTrue(mapExistsCalled((HibFolder) r, JIMMY_KRANKIE));
		HibernateUtil.commit();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCreateMapNameAlreadyInUseThrowsIllegalArgument()
			throws SQLException {
		IRootFolder r = rep.getRootFolder();
		assertFalse(mapExistsCalled((HibFolder) r, JIMMY_KRANKIE));
		r.createMap(JIMMY_KRANKIE);
		bo.synchroniseRepository();
		r = (HibRootFolder) getSession().createQuery(
				"from HibRootFolder r where r.id = '100001'").uniqueResult();
		assertTrue(mapExistsCalled((HibFolder) r, JIMMY_KRANKIE));
		r.createMap(JIMMY_KRANKIE);
	}

	@Test
	public void testContainsMapHappyCase() {
		HibSubFolder sub1 = (HibSubFolder) rep.getFolderByPath("/subfolder2/subfolder4");
		HibMapDiagram map = sub1.getMapDiagrams().iterator().next();
		assertTrue(sub1.containsMap(map));
	}

	@Test
	public void testContainsMapFalse() {
		HibSubFolder sub1 = (HibSubFolder) rep.getFolderByPath("/subfolder2/subfolder4");
		IRootFolder r = rep.getRootFolder();
		HibMapDiagram map = sub1.getMapDiagrams().iterator().next();
		assertTrue(sub1.containsMap(map));
		assertFalse(r.containsMap(map));
	}

	@Test
	public void testCreateCopyOfMapHappyCase() {
		HibSubFolder sub1 = (HibSubFolder) rep.getFolderByPath("/subfolder2/subfolder4");
		IRootFolder r = rep.getRootFolder();
		HibMapDiagram map = sub1.getMapDiagrams().iterator().next();
		assertFalse(mapExistsCalled((HibFolder) r, map.getName()));
		assertEquals(
				0,
				getSession()
						.createQuery(
								"from HibMapDiagram m where m.folder =:parent and m.name = :name")
						.setEntity("parent", r)
						.setString("name", map.getName()).list().size());
		r.createCopyOfMap(map);
		bo.synchroniseRepository();
		assertTrue(mapExistsCalled((HibFolder) r, map.getName()));
		assertEquals(
				1,
				getSession()
						.createQuery(
								"from HibMapDiagram m where m.folder =:parent and m.name = :name")
						.setEntity("parent", r)
						.setString("name", map.getName()).list().size());
	}

	@Test
	public void testMoveMapHappyCaseAddsMapToNewParent() {
		HibSubFolder sub1 = (HibSubFolder) rep.getFolderByPath("/subfolder2/subfolder4");
		IRootFolder r = rep.getRootFolder();
		IMap map = sub1.getMapIterator().next();
		assertFalse(mapExistsCalled((HibFolder) r, map.getName()));
		assertTrue(mapExistsCalled(sub1, map.getName()));
		r.moveMap(map);
		bo.synchroniseRepository();
		assertTrue(mapExistsCalled((HibFolder) r, map.getName()));
		assertFalse(mapExistsCalled(sub1, map.getName()));
		assertEquals(
				1,
				getSession()
						.createQuery(
								"from HibMapDiagram m where m.folder =:parent and m.name = :name")
						.setEntity("parent", r)
						.setString("name", map.getName()).list().size());
		assertEquals(
				0,
				getSession()
						.createQuery(
								"from HibMapDiagram m where m.folder =:parent and m.name = :name")
						.setEntity("parent", sub1)
						.setString("name", map.getName()).list().size());
	}

	@Test
	public void testMoveMapHappyCaseRemovesMapFromOldParent() {
		HibSubFolder sub1 = (HibSubFolder) rep.getFolderByPath("/subfolder2/subfolder4");
		IRootFolder r = rep.getRootFolder();
		HibMapDiagram map = sub1.getMapDiagrams().iterator().next();
		assertFalse(mapExistsCalled((HibFolder) r, map.getName()));
		assertEquals(
				1,
				getSession()
						.createQuery(
								"from HibMapDiagram m where m.folder =:parent and m.name = :name")
						.setEntity("parent", sub1).setString("name",
								map.getName()).list().size());
		r.moveMap(map);
		bo.synchroniseRepository();
		assertEquals(
				0,
				getSession()
						.createQuery(
								"from HibMapDiagram m where m.folder =:parent and m.name = :name")
						.setEntity("parent", sub1).setString("name",
								map.getName()).list().size());
	}

	@Test
	public void testCanRenameMapHappyCase() {
		HibSubFolder sub1 = (HibSubFolder) rep.getFolderByPath("/subfolder2/subfolder4");
		HibMapDiagram map = sub1.getMapDiagrams().iterator().next();
		assertTrue(sub1.canRenameMap(map, JIMMY_KRANKIE));
		assertFalse(sub1.canRenameMap(map, map.getName()));
	}

	@Test
	public void testRenameMapHappyCase() {
		HibSubFolder sub1 = (HibSubFolder) rep.getFolderByPath("/subfolder2/subfolder4");
		HibMapDiagram map = sub1.getMapDiagrams().iterator().next();
		assertFalse(mapExistsCalled(sub1, JIMMY_KRANKIE));
		assertEquals(
				0,
				getSession()
						.createQuery(
								"from HibMapDiagram m where m.folder =:parent and m.name = :name")
						.setEntity("parent", sub1).setString("name",
								JIMMY_KRANKIE).list().size());
		sub1.renameMap(map, JIMMY_KRANKIE);
		bo.synchroniseRepository();
		assertTrue(mapExistsCalled(sub1, JIMMY_KRANKIE));
		assertEquals(
				1,
				getSession()
						.createQuery(
								"from HibMapDiagram m where m.folder =:parent and m.name = :name")
						.setEntity("parent", sub1).setString("name",
								JIMMY_KRANKIE).list().size());
	}

	@Test
	public void testSubFolderIterator() {
		IRootFolder r = rep.getRootFolder();
		Iterator<? extends ISubFolder> it = r.getSubFolderIterator();
		assertTrue(it.next().getName().equals("subfolder1") ? it.next()
				.getName().equals("subfolder2") : it.next().getName().equals(
				"subfolder1"));
	}

	private boolean subFolderExistsCalled(HibRootFolder r, String name) {
		Set<HibSubFolder> subs = r.getSubFolders();
		for (HibSubFolder sub : subs) {
			if (sub.getName().equals(name))
				return true;
		}
		return false;
	}

	private boolean mapExistsCalled(HibFolder r, String name) {
		Set<HibMapDiagram> maps = r.getMapDiagrams();
		for (HibMapDiagram map : maps) {
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
}
