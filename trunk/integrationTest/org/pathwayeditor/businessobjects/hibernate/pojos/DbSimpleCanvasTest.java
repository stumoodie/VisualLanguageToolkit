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
import org.junit.Ignore;
import org.junit.Test;
import org.pathwayeditor.testutils.PojoTester;

/**
 * @author ntsorman
 *
 */
public class DbSimpleCanvasTest extends PojoTester{
	
	private static final String DELETED_CANVAS_DATA = "integrationTest/DbSimpleCanvasDeleteData/DbSimpleCanvasDeleteRefData.xml" ;
	
	@Ignore
	@Test
	public void testDeleteSimpleCanvasAndShape () throws Exception
	{
		doSetup () ;

		startNewTransaction();
		Query retreivedCanvas = getSession().createQuery("from HibCanvas where id = '100001'"  ) ;
		HibCanvas dbCanvas = (HibCanvas) retreivedCanvas.uniqueResult() ;
		

//		List<HibLinkAttribute> links = new ArrayList<HibLinkAttribute> ( dbCanvas.getHibLinkAttributes()) ;
		
		getSession().delete(dbCanvas) ;
		getSession().getTransaction().commit() ;
	
		IDataSet expectedDeltas = new XmlDataSet(new FileInputStream(
				DELETED_CANVAS_DATA));
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
		return "integrationTest/DbSourceData/DbSourceSimpleCanvasRefData.xml";
	}

}
