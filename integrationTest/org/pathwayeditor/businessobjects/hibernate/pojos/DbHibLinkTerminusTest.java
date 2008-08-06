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
import org.junit.Test;
import org.pathwayeditor.testutils.PojoTester;

/**
 * @author ntsorman
 *
 */
public class DbHibLinkTerminusTest extends PojoTester{
	private static final String ADDED_LINKTERMINUS_DATA = "integrationTest/DbLinkTerminusTestData/DbAddedNewLinkTerminusRefData.xml";
	private static final String DELETED_LINKTERMINUS_DATA = "integrationTest/DbLinkTerminusTestData/DbDeletedLinkTerminusRefData.xml";
	
	private static final int NUMERIC_VALUE_ONE = 1 ; 
	private static final int NUMERIC_VALUE_TEN = 10 ;
	
	@Test
	public void testLoadLinkTerminus () throws Exception
	{
		doSetup();
		
		Query retreivedLinkTerminus = getSession().createQuery( "From HibLinkTerminus where id='100001'") ;
		HibLinkTerminus dbLinkTerminus = (HibLinkTerminus) retreivedLinkTerminus.uniqueResult() ;
		
		Query retreivedLink = getSession().createQuery("from HibLinkAttribute where id='100001'" );
		HibLinkAttribute dbLink = (HibLinkAttribute) retreivedLink.uniqueResult() ;
		
		Query retreivedLinkEndDecorator = getSession().createQuery("from HibLinkEndDecorator where id ='100001'" );
		HibLinkEndDecorator dbLinkEndDecorator = (HibLinkEndDecorator) retreivedLinkEndDecorator.uniqueResult() ;
		
		Query retreivedLinkTerminusDecorator = getSession().createQuery( "from HibLinkTerminusDecorator where id='100001'") ;
		HibLinkTerminusDecorator dbLinkTerminusDecorator = (HibLinkTerminusDecorator) retreivedLinkTerminusDecorator.uniqueResult() ;
		
		assertEquals ("link term type " , NUMERIC_VALUE_ONE , dbLinkTerminus.getLinkEndType()) ;
		assertEquals ("link term offset" , NUMERIC_VALUE_TEN , dbLinkTerminus.getOffset()) ;
		assertEquals ("parent link", dbLink.hashCode(), dbLinkTerminus.getLink().hashCode()) ;
		assertEquals ("link end Decorator" , dbLinkEndDecorator.hashCode() , dbLinkTerminus.getDecorator().hashCode() ) ;
		assertEquals ("linkTerminusDecorator" , dbLinkTerminusDecorator.hashCode() , dbLinkTerminus.getDecorator().hashCode()) ;
		
	}
	
	@Test 
	public void testCreateLinkTerminus () throws Exception
	{
		doSetup();

		
		Query retreivedLink = getSession().createQuery("from HibLinkAttribute where id='100001'" );
		HibLinkAttribute dbLink = (HibLinkAttribute) retreivedLink.uniqueResult() ;
		
		HibLinkTerminus newLinkTerminus = new HibLinkTerminus ();
		newLinkTerminus.setOffset((short) 5  );
		newLinkTerminus.setLinkEndType(2) ;
		
		dbLink.addLinkTermini(newLinkTerminus) ;
		
		getSession().saveOrUpdate(dbLink) ;
		getSession().getTransaction().commit() ;
		
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
	
	@Test
	public void testDeleteLinkTerminus () throws Exception 
	{
		doSetup();
		
		Query retreivedLinkTerminus = getSession().createQuery( "From HibLinkTerminus where id='100001'") ;
		HibLinkTerminus dbLinkTerminus = (HibLinkTerminus) retreivedLinkTerminus.uniqueResult() ;
		
		getSession().delete(dbLinkTerminus) ;
		getSession().getTransaction().commit() ;
		
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
