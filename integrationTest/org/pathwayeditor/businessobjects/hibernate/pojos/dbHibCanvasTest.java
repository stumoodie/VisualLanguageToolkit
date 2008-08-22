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
import org.junit.Ignore;
import org.junit.Test;
import org.pathwayeditor.testutils.PojoTester;

/**
 * @author ntsorman
 *
 */
public class dbHibCanvasTest extends  PojoTester {

	private static final String DELETED_EMPTY_MAPDIAGRAM_REF_DATA = "integrationTest/DbCanvasTestData/DbDeletedEmptyCanvasThroughMapDiagramRefData.xml";
	private static final String CHANGED_MAPDIAGRAM_REF_DATA = "integrationTest/DbCanvasTestData/ChangedMapDiagramCanvasRefData.xml";
	private static final String CLONED_MAPDIAGRAM_REF_DATA = "integrationTest/DbCanvasTestData/ClonedCanvasRedData.xml";
	private static final String DELETED_EMPTY_CANVAS_REF_DATA = "integrationTest/DbCanvasTestData/DeletedCanvasRefData.xml";
	
	
	private static final int COLOR_VALUE = 100 ;
	private static final int GRID_VALUE = 10 ;
	private static final boolean GRID_ENABLEMENT = true ;
	private static final int CANVAS_SIZE = 10 ;
	
	private static final String CHECK_DATE_STRING = "1970-01-01 00:00:00.0" ;
	
	
	@Test
	public void testLoadedCanvas () throws Exception
	{
		doSetup();
		
		Query retreivedCanvas = getSession().createQuery( "from HibCanvas where id ='100001'" ) ;
		Query retreivedMapDiagram = getSession().createQuery( "from HibMapDiagram where id ='100001'" ) ;
		
		HibMapDiagram parentMapDiagram = (HibMapDiagram) retreivedMapDiagram.uniqueResult() ;
		HibCanvas dbCanvas = (HibCanvas) retreivedCanvas.uniqueResult() ;
		
		assertEquals ("Grid X" , GRID_VALUE , dbCanvas.getGridX()) ;
		assertEquals ("Grid Y" , GRID_VALUE , dbCanvas.getGridY()) ;
		
		assertEquals ( "Grid enabled" , GRID_ENABLEMENT , dbCanvas.isGridEnabled()) ;
		assertEquals ( "Snap enabled" , GRID_ENABLEMENT , dbCanvas.isSnapToGridEnabled()) ;
		
		assertEquals ("Canvas width" , CANVAS_SIZE , dbCanvas.getCanvasWidth() ) ;
		assertEquals ("Canvas height" , CANVAS_SIZE , dbCanvas.getCanvasHeight() ) ;
		
		assertEquals ( "color green" , COLOR_VALUE , dbCanvas.getBackgroundGreen() ) ;
		assertEquals ( "color red" , COLOR_VALUE , dbCanvas.getBackgroundRed() ) ;
		assertEquals ( "color blue" , COLOR_VALUE , dbCanvas.getBackgroundBlue() ) ;
		
		assertEquals ("created date" , CHECK_DATE_STRING , dbCanvas.getCreated().toString()) ;
		assertEquals ("modified date" , CHECK_DATE_STRING , dbCanvas.getModified().toString() ) ;
	}
	
	@Test
	public void testDeleteEmptyCanvas () throws Exception 
	{
		doSetup();
		
		Query retreivedCanvas = getSession().createQuery( "from HibCanvas where id ='100003'" ) ;
		HibCanvas dbCanvas = (HibCanvas) retreivedCanvas.uniqueResult() ;
		
		dbCanvas.changeMapDiagram(null);
		getSession().delete(dbCanvas) ;
		getSession().getTransaction().commit() ;
		
		IDataSet expectedDeltas = new XmlDataSet(new FileInputStream(
				DELETED_EMPTY_CANVAS_REF_DATA));
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
	
	@Ignore
	@Test
	public void testDeleteEmptyCanvasThroughMapDiagram () throws Exception 
	{
		doSetup();
		
//		Query retreivedMapDiagram = getSession().createQuery( "from HibMapDiagram where id ='100003'" ) ;
//		HibMapDiagram parentMapDiagram = (HibMapDiagram) retreivedMapDiagram.uniqueResult() ;
		
		Query retreivedCanvas = getSession().createQuery( "from HibCanvas where id ='100003'" ) ;
		HibCanvas dbCanvas = (HibCanvas) retreivedCanvas.uniqueResult() ;
		
		HibMapDiagram parentMapDiagram = dbCanvas.getMapDiagram() ;
		
		dbCanvas.changeMapDiagram(null);
		
		getSession().delete(dbCanvas) ;
		getSession().delete(parentMapDiagram) ;
		getSession().getTransaction().commit() ;
		
//		IDataSet expectedDeltas = new XmlDataSet(new FileInputStream(
//				DELETED_EMPTY_MAPDIAGRAM_REF_DATA));
//		String testTables[] = expectedDeltas.getTableNames();
//		IDataSet actualChanges = getConnection().createDataSet(testTables);
//		IDataSet expectedChanges = new CompositeDataSet(expectedDeltas);
//		for (String t : testTables) {
//			ITable expectedTable = DefaultColumnFilter
//					.includedColumnsTable(expectedChanges.getTable(t),
//							expectedDeltas.getTable(t).getTableMetaData()
//									.getColumns());
//			ITable actualTable = DefaultColumnFilter.includedColumnsTable(
//					actualChanges.getTable(t), expectedDeltas.getTable(t)
//							.getTableMetaData().getColumns());
//			Assertion.assertEquals(new SortedTable(expectedTable),
//					new SortedTable(actualTable, expectedTable
//							.getTableMetaData()));
//		}
	}
	
	@Ignore
	@Test
	public void testChangeMapDiagram () throws Exception
	{
		// TODO this is no longer valid !! ?? !! 
		
		doSetup();
		
		Query retreivedMapDiagram = getSession().createQuery( "from HibMapDiagram where id ='100004'" ) ;
		HibMapDiagram parentMapDiagram = (HibMapDiagram) retreivedMapDiagram.uniqueResult() ;
		
		Query retreivedCanvas = getSession().createQuery( "from HibCanvas where id ='100001'" ) ;
		HibCanvas dbCanvas = (HibCanvas) retreivedCanvas.uniqueResult() ;
		
		dbCanvas.changeMapDiagram(parentMapDiagram) ;
		
		getSession().saveOrUpdate(dbCanvas) ;
		getSession().getTransaction().commit() ;
		
		IDataSet expectedDeltas = new XmlDataSet(new FileInputStream(
				CHANGED_MAPDIAGRAM_REF_DATA));
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
	public void testCloneCanvas () throws Exception
	{
		doSetup () ;
		
		Query retreivedMapDiagram = getSession().createQuery( "from HibMapDiagram where id ='100004'" ) ;
		HibMapDiagram parentMapDiagram = (HibMapDiagram) retreivedMapDiagram.uniqueResult() ;
		
		Query retreivedCanvas = getSession().createQuery( "from HibCanvas where id ='100001'" ) ;
		HibCanvas dbCanvas = (HibCanvas) retreivedCanvas.uniqueResult() ;
		
		HibCanvas toWrite= new HibCanvas (parentMapDiagram, dbCanvas) ;
		
		getSession().save(toWrite) ;
		getSession().getTransaction().commit() ;
		
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
		return "integrationTest/DbSourceData/DbSourceDataRefData.xml";
	}
	
	

}
