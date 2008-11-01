/**
 * 
 */
package org.pathwayeditor.businessobjects.hibernate.pojos;

import static org.junit.Assert.assertEquals;

import java.io.FileInputStream;

import org.dbunit.Assertion;
import org.dbunit.dataset.CompositeDataSet;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.SortedTable;
import org.dbunit.dataset.filter.DefaultColumnFilter;
import org.dbunit.dataset.xml.XmlDataSet;
import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Ignore;
import org.junit.Test;
import org.pathwayeditor.testutils.PojoTester;

/**
 * @author ntsorman
 *
 */
public class DBHibMapDiagramTest  extends PojoTester{

	
	private static final String MAPDIAGRAM_NAME = "new diagram name" ;
	private static final String MAPDIAGRAM_DESCR = "new diagram description" ;
	private static final String ADDED_MAPDIAGRAM_REF_DATA = "integrationTest/DbRepositoryTestData/AddedMapDiagramRefData.xml";
	private static final String CLONED_MAPDIAGRAM_REF_DATA = "integrationTest/DbRepositoryTestData/ClonedMapDiagramRefData.xml";
	
	@Ignore @Test
	public void testAddNewMapDiagram () throws Exception 
	{
		doSetup () ;
		Session sess = getHibFactory().getCurrentSession();
		sess.beginTransaction();
		Query retreivedFolder = sess.createQuery("from HibFolder where id='100005'") ;
		
		HibFolder parentFolder = (HibFolder) retreivedFolder.uniqueResult() ;
		
		HibMap towrite = new HibMap ( parentFolder , MAPDIAGRAM_NAME ) ;
		towrite.setDescription(MAPDIAGRAM_DESCR) ;
		towrite.setRepository(parentFolder.getRepository());
		
		parentFolder.addMapDiagram(towrite) ;
		
		sess.saveOrUpdate(parentFolder) ;
		sess.getTransaction().commit() ;
		
		IDataSet expectedDeltas = new XmlDataSet(new FileInputStream(
				ADDED_MAPDIAGRAM_REF_DATA));
		String testTables[] = expectedDeltas.getTableNames();
		IDataSet actualChanges = getConnection().createDataSet(testTables);
		IDataSet expectedChanges = new CompositeDataSet(expectedDeltas);
		for (String t : testTables) {
			ITable expectedTable = DefaultColumnFilter
					.includedColumnsTable(expectedChanges.getTable(t),
							expectedDeltas.getTable(t).getTableMetaData()
									.getColumns());
			ITable actualTable = DefaultColumnFilter.includedColumnsTable(
					actualChanges.getTable(t), expectedDeltas.getTable(t)
							.getTableMetaData().getColumns());
			Assertion.assertEquals(new SortedTable(expectedTable),
					new SortedTable(actualTable, expectedTable
							.getTableMetaData()));
		}
		
		
	}
	
	@Ignore @Test
	public void testDeleteMapDiagram () throws Exception
	{
		doSetup ();
		Session sess = getHibFactory().getCurrentSession();
		sess.beginTransaction();
		Query retreivedMapDiagram = sess.createQuery("from HibMapDiagram where id='100001'") ;
		HibMap toDelete = (HibMap) retreivedMapDiagram.uniqueResult() ;
		sess.delete(toDelete) ;
		sess.getTransaction().commit() ;
		Session session = getHibFactory().getCurrentSession() ;
		session.beginTransaction() ;
		retreivedMapDiagram = session.createQuery("from HibMapDiagram") ;
		assertEquals(0,retreivedMapDiagram.list().size());
	}
	
	@Ignore @Test
	public void testCloneMapDiagram () throws Exception 
	{
		doSetup () ;
		Session sess = getHibFactory().getCurrentSession();
		sess.beginTransaction();
		Query retreivedMapDiagram = sess.createQuery("from HibMapDiagram where id='100001'") ;
//		List <HibMap> diagrams1 = sess.createQuery("from HibMapDiagram").list();
		HibMap toClone = (HibMap) retreivedMapDiagram.uniqueResult() ;
		Query retreivedFolder = sess.createQuery("from HibFolder where id='100004'") ;
		HibFolder parentFolder = (HibFolder) retreivedFolder.uniqueResult() ;
		HibMap cloneDiagram = new HibMap ( parentFolder , toClone ) ;
		cloneDiagram.setRepository(parentFolder.getRepository());
		parentFolder.addMapDiagram(cloneDiagram) ;
		sess.saveOrUpdate(parentFolder) ;
		sess.getTransaction().commit() ;
		Session session = getHibFactory().getCurrentSession() ;
		session.beginTransaction() ;
//		List <HibMap> diagrams = session.createQuery("from HibMapDiagram").list();
		
		IDataSet expectedDeltas = new XmlDataSet(new FileInputStream(
				CLONED_MAPDIAGRAM_REF_DATA));
		String testTables[] = expectedDeltas.getTableNames();
		IDataSet actualChanges = getConnection().createDataSet(testTables);
		IDataSet expectedChanges = new CompositeDataSet(expectedDeltas);
		for (String t : testTables) {
			ITable expectedTable = DefaultColumnFilter
					.includedColumnsTable(expectedChanges.getTable(t),
							expectedDeltas.getTable(t).getTableMetaData()
									.getColumns());
			ITable actualTable = DefaultColumnFilter.includedColumnsTable(
					actualChanges.getTable(t), expectedDeltas.getTable(t)
							.getTableMetaData().getColumns());
			Assertion.assertEquals(new SortedTable(expectedTable),
					new SortedTable(actualTable, expectedTable
							.getTableMetaData()));
		}
	}

	@Override
	protected String getDbUnitDataFilePath() {
		return "integrationTest/DbSourceData/DbSourceRepositoryRefData.xml";

	}
}
