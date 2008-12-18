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
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LinkTermType;
import org.pathwayeditor.businessobjects.typedefn.ILinkTerminusDefinition;
import org.pathwayeditor.testutils.PojoTester;

/**
 * @author ntsorman
 *
 */
public class DbHibLinkTerminusTest extends PojoTester{
	private static final String ADDED_LINKTERMINUS_DATA = "integrationTest/DbLinkTerminusTestData/DbAddedNewLinkTerminusRefData.xml";
	private static final String DELETED_LINKTERMINUS_DATA = "integrationTest/DbLinkTerminusTestData/DbDeletedLinkTerminusRefData.xml";
	
	private static final LinkTermType EXPECTED_SRC_TYPE = LinkTermType.SOURCE; 
	private static final int EXPECTED_OFFSET_VALUE = 10 ;
	
	@Test
	public void testLoadLinkTerminus () throws Exception
	{
		Session sess = getHibFactory().getCurrentSession();
		sess.beginTransaction();
		Query retreivedLinkTerminus = sess.createQuery( "From HibLinkTerminus where id='100001'") ;
		HibLinkTerminus dbLinkTerminus = (HibLinkTerminus) retreivedLinkTerminus.uniqueResult() ;
		
		Query retreivedLink = sess.createQuery("from HibLinkAttribute where id='100001'" );
		HibLinkAttribute dbLink = (HibLinkAttribute) retreivedLink.uniqueResult() ;
		
		LinkTermType actualTermType = dbLinkTerminus.getLinkTermType(); 
		int actualOffset = dbLinkTerminus.getGap();
		int termHashCode = dbLinkTerminus.getOwningLink().hashCode();
		int linkHashCode = dbLink.hashCode(); 
		sess.getTransaction().commit();
		
		assertEquals ("link term type " , EXPECTED_SRC_TYPE , actualTermType) ;
		assertEquals ("link term offset" , EXPECTED_OFFSET_VALUE , actualOffset) ;
		assertEquals ("parent link", linkHashCode, termHashCode) ;
	}
	
	@Ignore @Test 
	public void testCreateLinkTerminus () throws Exception
	{
		Session sess = getHibFactory().getCurrentSession();
		sess.beginTransaction();
		Query retreivedLink = sess.createQuery("from HibLinkAttribute where id='100001'" );
		HibLinkAttribute dbLink = (HibLinkAttribute) retreivedLink.uniqueResult() ;
		ILinkTerminusDefinition defn = null;
		
		HibLinkTerminus newLinkTerminus = new HibLinkTerminus (dbLink, LinkTermType.TARGET, defn);
		newLinkTerminus.setGap((short) 5  );
		
//		dbLink.setTargetTerminus(newLinkTerminus) ;
		
		sess.saveOrUpdate(dbLink) ;
		sess.getTransaction().commit() ;
		
		IDataSet expectedDeltas = new XmlDataSet(new FileInputStream(
				ADDED_LINKTERMINUS_DATA));
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
	public void testDeleteLinkTerminus () throws Exception 
	{
		Session sess = getHibFactory().getCurrentSession();
		sess.beginTransaction();
		Query retreivedLinkTerminus = sess.createQuery( "From HibLinkTerminus where id='100001'") ;
		HibLinkTerminus dbLinkTerminus = (HibLinkTerminus) retreivedLinkTerminus.uniqueResult() ;
		
		sess.delete(dbLinkTerminus) ;
		sess.getTransaction().commit() ;
		
		IDataSet expectedDeltas = new XmlDataSet(new FileInputStream(
				DELETED_LINKTERMINUS_DATA));
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
