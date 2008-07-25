/**
 * 
 */
package org.pathwayeditor.businessobjects.hibernate.pojos;

import static org.junit.Assert.assertEquals;

import java.io.FileInputStream;
import java.util.List;

import org.dbunit.Assertion;
import org.dbunit.dataset.CompositeDataSet;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.SortedTable;
import org.dbunit.dataset.filter.DefaultColumnFilter;
import org.dbunit.dataset.xml.XmlDataSet;
import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Test;
import org.pathwayeditor.testutils.PojoTester;

/**
 * @author ntsorman
 *
 */
public class DBHibMapDiagramTest  extends PojoTester{

	private static final String REPOSITORY_NAME = "repo name" ;
	private static final String REPOSITORY_DESCRIPTION = "repo name" ;
	private static final int REPOSITORY_VERSION = 101010;
	
	private static final String MAPDIAGRAM_NAME = "new diagram name" ;
	private static final String MAPDIAGRAM_DESCR = "new diagram description" ;
	
	private static final int ONE_ENTRY_TABLE = 1 ;
	

	private static final String ADDED_MAPDIAGRAM_REF_DATA = "integrationTest/DbRepositoryTestData/AddedMapDiagramRefData.xml";
	private static final String CLONED_MAPDIAGRAM_REF_DATA = "integrationTest/DbRepositoryTestData/ClonedMapDiagramRefData.xml";
	
	@Test
	public void testAddNewMapDiagram () throws Exception 
	{
		doSetup () ;
		Query retreivedFolder = getSession().createQuery("from HibFolder where id='100005'") ;
		
		HibFolder parentFolder = (HibFolder) retreivedFolder.uniqueResult() ;
		
		HibMapDiagram towrite = new HibMapDiagram ( parentFolder , MAPDIAGRAM_NAME ) ;
		towrite.setDescription(MAPDIAGRAM_DESCR) ;
		towrite.setRepository(parentFolder.getRepository());
		
		parentFolder.addMapDiagram(towrite) ;
		
		getSession().saveOrUpdate(parentFolder) ;
		getSession().getTransaction().commit() ;
		
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
	
	@Test
	public void testDeleteMapDiagram () throws Exception
	{
		doSetup ();
		Query retreivedMapDiagram = getSession().createQuery("from HibMapDiagram where id='100001'") ;
		HibMapDiagram toDelete = (HibMapDiagram) retreivedMapDiagram.uniqueResult() ;
		getSession().delete(toDelete) ;
		getSession().getTransaction().commit() ;
		Session session = getHibFactory().getCurrentSession() ;
		session.beginTransaction() ;
		retreivedMapDiagram = session.createQuery("from HibMapDiagram") ;
		assertEquals(0,retreivedMapDiagram.list().size());
	}
	
	@Test
	public void testCloneMapDiagram () throws Exception 
	{
		doSetup () ;
		getSession().beginTransaction() ;
		Query retreivedMapDiagram = getSession().createQuery("from HibMapDiagram where id='100001'") ;
		List <HibMapDiagram> diagrams1 = getSession().createQuery("from HibMapDiagram").list();
		HibMapDiagram toClone = (HibMapDiagram) retreivedMapDiagram.uniqueResult() ;
		Query retreivedFolder = getSession().createQuery("from HibFolder where id='100004'") ;
		HibFolder parentFolder = (HibFolder) retreivedFolder.uniqueResult() ;
		HibMapDiagram cloneDiagram = new HibMapDiagram ( parentFolder , toClone ) ;
		cloneDiagram.setRepository(parentFolder.getRepository());
		parentFolder.addMapDiagram(cloneDiagram) ;
		getSession().saveOrUpdate(parentFolder) ;
		getSession().getTransaction().commit() ;
		Session session = getHibFactory().getCurrentSession() ;
		session.beginTransaction() ;
		List <HibMapDiagram> diagrams = session.createQuery("from HibMapDiagram").list();
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
