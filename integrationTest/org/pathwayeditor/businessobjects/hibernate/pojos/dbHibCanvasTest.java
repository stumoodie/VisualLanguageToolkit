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
import org.dbunit.operation.DatabaseOperation;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.pathwayeditor.testutils.HibernateDbTester;

/**
 * @author ntsorman
 *
 */
public class dbHibCanvasTest {

	private static HibernateDbTester dbTester = null;
	private SessionFactory hibFactory; 
	private Session session ;

	private static final String HIB_CONFIG_FILE = "test_hibernate.cfg.xml";
	private static final String REF_DATA = "integrationTest/DbCanvasTestData/CanvasDbRefData.xml";
	private static final String DELETED_MAPDIAGRAM_REF_DATA = "integrationTest/DbCanvasTestData/DeletedMapDiagramRefData.xml";
	private static final String CHANGED_MAPDIAGRAM_REF_DATA = "integrationTest/DbCanvasTestData/ChangedMapDiagramCanvasRefData.xml";
	private static final String CLONED_MAPDIAGRAM_REF_DATA = "integrationTest/DbCanvasTestData/ClonedCanvasRedData.xml";
	
	
	private static final int COLOR_VALUE = 100 ;
	private static final int GRID_VALUE = 10 ;
	private static final boolean GRID_ENABLEMENT = true ;
	private static final int CANVAS_SIZE = 10 ;
	
	private static final String CHECK_DATE_STRING = "1970-01-01 00:00:00.0" ;
	
	
	
	
	
	@BeforeClass
	public static void initSchema() throws Exception{
		dbTester = new HibernateDbTester(HIB_CONFIG_FILE);
		dbTester.createSchema();
	}
	
	@AfterClass
	public static void dropSchema() throws Exception{
		dbTester.dropSchema() ;
	}

	
	@Before
	public void setUp() throws Exception {
		this.hibFactory = dbTester.getHibernateSessionFactory();
		dbTester.setSetUpOperation(DatabaseOperation.CLEAN_INSERT);
		dbTester.setTearDownOperation(DatabaseOperation.DELETE_ALL);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		dbTester.onTearDown();
		if(this.hibFactory != null && !this.hibFactory.isClosed()){
			this.hibFactory.close();
		}
		this.hibFactory = null;
	}
	
	@Test
	public void testLoadedCanvas () throws Exception
	{
		
		dbTester.setDataSet(new XmlDataSet(new FileInputStream(REF_DATA)));
		dbTester.onSetup();
		
		session = hibFactory.getCurrentSession() ;
		session.beginTransaction() ;
		
		Query retreivedCanvas = session.createQuery( "from HibCanvas where id ='100001'" ) ;
		Query retreivedMapDiagram = session.createQuery( "from HibMapDiagram where id ='100001'" ) ;
		
		HibMapDiagram parentMapDiagram = (HibMapDiagram) retreivedMapDiagram.uniqueResult() ;
		HibCanvas dbCanvas = (HibCanvas) retreivedCanvas.uniqueResult() ;
		
		assertEquals ( "MapDiagram id" , parentMapDiagram , dbCanvas.getMapDiagram() ) ;
		
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
	public void deleteMapDiagramAndCanvas () throws Exception 
	{
		dbTester.setDataSet(new XmlDataSet(new FileInputStream(REF_DATA)));
		dbTester.onSetup();
		
		session = hibFactory.getCurrentSession() ;
		session.beginTransaction() ;
		
		Query retreivedMapDiagram = session.createQuery( "from HibMapDiagram where id ='100001'" ) ;
		HibMapDiagram parentMapDiagram = (HibMapDiagram) retreivedMapDiagram.uniqueResult() ;
		
		Query retreivedCanvas = session.createQuery( "from HibCanvas where id ='100001'" ) ;
		HibCanvas dbCanvas = (HibCanvas) retreivedCanvas.uniqueResult() ;
		
		
		session.delete(dbCanvas) ;
		session.delete(parentMapDiagram) ;
		session.getTransaction().commit();
		
		IDataSet expectedDeltas = new XmlDataSet(new FileInputStream(
				DELETED_MAPDIAGRAM_REF_DATA));
		String testTables[] = expectedDeltas.getTableNames();
		IDataSet actualChanges = dbTester.getConnection().createDataSet(testTables);
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
	public void testChangeMapDiagram () throws Exception
	{
		dbTester.setDataSet(new XmlDataSet(new FileInputStream(REF_DATA)));
		dbTester.onSetup();
		
		session = hibFactory.getCurrentSession() ;
		session.beginTransaction() ;
		
		Query retreivedMapDiagram = session.createQuery( "from HibMapDiagram where id ='100002'" ) ;
		HibMapDiagram parentMapDiagram = (HibMapDiagram) retreivedMapDiagram.uniqueResult() ;
		
		Query retreivedCanvas = session.createQuery( "from HibCanvas where id ='100001'" ) ;
		HibCanvas dbCanvas = (HibCanvas) retreivedCanvas.uniqueResult() ;
		
		dbCanvas.changeMapDiagram(parentMapDiagram) ;
		
		session.saveOrUpdate(dbCanvas) ;
		session.getTransaction().commit() ;
		
		IDataSet expectedDeltas = new XmlDataSet(new FileInputStream(
				CHANGED_MAPDIAGRAM_REF_DATA));
		String testTables[] = expectedDeltas.getTableNames();
		IDataSet actualChanges = dbTester.getConnection().createDataSet(testTables);
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
		dbTester.setDataSet(new XmlDataSet(new FileInputStream(REF_DATA)));
		dbTester.onSetup();
		
		session = hibFactory.getCurrentSession() ;
		session.beginTransaction() ;
		
		Query retreivedMapDiagram = session.createQuery( "from HibMapDiagram where id ='100002'" ) ;
		HibMapDiagram parentMapDiagram = (HibMapDiagram) retreivedMapDiagram.uniqueResult() ;
		
		Query retreivedCanvas = session.createQuery( "from HibCanvas where id ='100001'" ) ;
		HibCanvas dbCanvas = (HibCanvas) retreivedCanvas.uniqueResult() ;
		
		HibCanvas toWrite= new HibCanvas (parentMapDiagram, dbCanvas) ;
		
//		toWrite.changeMapDiagram(parentMapDiagram) ; 
		
		session.save(toWrite) ;
		session.getTransaction().commit() ;
		
		IDataSet expectedDeltas = new XmlDataSet(new FileInputStream(
				CLONED_MAPDIAGRAM_REF_DATA));
		String testTables[] = expectedDeltas.getTableNames();
		IDataSet actualChanges = dbTester.getConnection().createDataSet(testTables);
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
	
	

}
