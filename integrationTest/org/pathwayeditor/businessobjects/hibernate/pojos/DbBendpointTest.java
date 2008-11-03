/**
 * 
 */
package org.pathwayeditor.businessobjects.hibernate.pojos;

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
import org.junit.Test;
import org.pathwayeditor.testutils.PojoTester;

/**
 * @author ntsorman
 *
 */
public class DbBendpointTest extends PojoTester{
	
	private static final int INDEX = 2 ;
	private static final int POSITION = 45 ;
	
	private static final String ADDED_BENDPOINT_DATA = "integrationTest/DbBendpointTestData/DbAddedBendPointRefData.xml" ;
	private static final String DELETED_BENDPOINT_DATA = "integrationTest/DbBendpointTestData/DbDeletedBendPointRefData.xml" ;
	
	@Test
	public void testAddBendPoint () throws Exception 
	{
		Session sess = getHibFactory().getCurrentSession();
		sess.beginTransaction();
		Query retreivedContext = sess.createQuery("from HibLinkAttribute where id = '100001'" ) ;
		HibLinkAttribute dbLinkAttribute = (HibLinkAttribute) retreivedContext.uniqueResult() ;
		
		HibBendPoint newBendPoint = new HibBendPoint (dbLinkAttribute, INDEX, POSITION ,  POSITION) ;
		
		dbLinkAttribute.addBendPoint(newBendPoint) ;
		
		sess.saveOrUpdate(dbLinkAttribute) ;
		sess.getTransaction().commit() ;
		
		IDataSet expectedDeltas = new XmlDataSet(new FileInputStream(
				ADDED_BENDPOINT_DATA));
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
	public void testDeleteBendPoint () throws Exception
	{
		Session sess = getHibFactory().getCurrentSession();
		sess.beginTransaction();
		Query retreivedContext = sess.createQuery("from HibBendPoint where id = '100001'" ) ;
		HibBendPoint dbHibBendPoint = (HibBendPoint) retreivedContext.uniqueResult() ;
		
		sess.delete(dbHibBendPoint) ;
		sess.getTransaction().commit() ;
		
		IDataSet expectedDeltas = new XmlDataSet(new FileInputStream(
				DELETED_BENDPOINT_DATA));
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
		return "integrationTest/DbSourceData/DbSourceDataRefData.xml";
	}
}
