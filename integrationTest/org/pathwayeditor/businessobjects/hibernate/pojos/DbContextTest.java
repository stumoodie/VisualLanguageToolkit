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
import org.junit.Test;
import org.pathwayeditor.testutils.PojoTester;

/**
 * @author ntsorman
 *
 */
public class DbContextTest extends PojoTester{
	
	public static final String CONTEXT_ID = "context id" ;
	public static final String CONTEXT_NAME = "new context name" ;
	public static final int MAJOR_VERSION = 1 ;
	public static final int MINOR_VERSION = 2 ;
	public static final int PATCH_VERSION = 3 ;
	public static final String CONTEXT_DESCRIPTION = "context description" ;
	
	private static final String ADDED_CONTEXT_DATA = "integrationTest/DbContextTestData/DbAddedContextRefData.xml";
	
	@Test
	public void testAddContext () throws Exception 
	{	
		doSetup () ;
		HibContext contextToAdd = new HibContext ( CONTEXT_ID, CONTEXT_NAME, MAJOR_VERSION, MINOR_VERSION, PATCH_VERSION, CONTEXT_DESCRIPTION) ;
		
		getSession().save(contextToAdd) ;
		
		IDataSet expectedDeltas = new XmlDataSet(new FileInputStream(
				ADDED_CONTEXT_DATA));
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
