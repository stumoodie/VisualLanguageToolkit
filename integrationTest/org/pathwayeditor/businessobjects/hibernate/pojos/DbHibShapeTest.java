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
public class DbHibShapeTest {
	
	private static HibernateDbTester dbTester = null;
	private SessionFactory hibFactory; 
	private Session session ;

	private static final String HIB_CONFIG_FILE = "test_hibernate.cfg.xml";
	private static final String REF_DATA = "integrationTest/DbShapeTestData/DbShapeRefData.xml";
	private static final String CREATED_SHAPE_DATA = "integrationTest/DbShapeTestData/DbCreatedShapeReData.xml";
	private static final String DELETED_SHAPE_DATA = "integrationTest/DbShapeTestData/DbDeletedShapeRefData.xml";
//	private static final String DELETED_SHAPE_THROUGH_CANVAS = "integrationTest/DbShapeTestData/DbDeletedShapeThroughCanvasRefData.xml";
//	private static final String CHANGED_ROOT_NODE = "integrationTest/DbShapeTestData/DbChangedRootNodeRefData.xml";
	
	
	
	
	private static final int CREATION_SERIAL = 123456 ;
	private static final String SHAPE_NAME = "shapeName" ;
	private static final int COLOR_VALUE = 100 ;
	private static final int SIZE_VALUE = 50 ;
	private static final String URL_VALUE = "http://www.shapeURL.org" ;
	private static final int POSITION_VALUE = 50 ;
	private static final String SHAPE_DESCR = "descr";
	private static final String DETAILED_DESCR = "detailed descr";
	private static final int NUMERIC_VALUE_ONE = 1;
	
	private static final int CREATION_SERIAL_2 = 654321 ;
	private static final String SHAPE_NAME_2 = "shapeName2" ;
	private static final int COLOR_VALUE_2 = 102 ;
	private static final int SIZE_VALUE_2 = 52 ;
	private static final String URL_VALUE_2 = "http://www.shapeURL2.org" ;
	private static final int POSITION_VALUE_2 = 52 ;
	private static final String SHAPE_DESCR_2 = "descr2";
	private static final String DETAILED_DESCR_2 = "detailed descr2";
	private static final int NUMERIC_VALUE_TWO = 2;
	private static final long EXPECTED_NODE_VALUE = 100001L;
	private static final int NODE_IDX_IDX = 110000;
	
	
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
	public void testLoadedShape () throws Exception
	{
		dbTester.setDataSet(new XmlDataSet(new FileInputStream(REF_DATA)));
		dbTester.onSetup();
		
		session = hibFactory.getCurrentSession() ;
		session.beginTransaction() ;
		
		Query retreivedShape = session.createQuery("from HibShape where id='100001'") ;
		
		HibShape dbShape = (HibShape) retreivedShape.uniqueResult() ;
		
		assertEquals ("created serial" , CREATION_SERIAL , dbShape.getCreationSerial() ) ;
		assertEquals ("name" , SHAPE_NAME , dbShape.getName() ) ;
		assertEquals ("description" , SHAPE_DESCR , dbShape.getDescription() ) ;
		assertEquals ("description" , DETAILED_DESCR , dbShape.getDetailedDescription() ) ;
		assertEquals ("fill red" , COLOR_VALUE , dbShape.getFillRed()) ;
		assertEquals ("fill blue" , COLOR_VALUE , dbShape.getFillBlue()) ;
		assertEquals ("fill green" , COLOR_VALUE , dbShape.getFillGreen()) ;
		assertEquals ("line red" , COLOR_VALUE , dbShape.getLineRed()) ;
		assertEquals ("line blue" , COLOR_VALUE , dbShape.getLineBlue()) ;
		assertEquals ("line green" , COLOR_VALUE , dbShape.getLineGreen()) ;
		assertEquals ("line style" , NUMERIC_VALUE_ONE , dbShape.getLineStyle()) ;
		assertEquals ("line width" , NUMERIC_VALUE_ONE , dbShape.getLineWidth()) ;
		assertEquals ("line style" , NUMERIC_VALUE_ONE , dbShape.getLineStyle()) ;
		assertEquals ("padding" , NUMERIC_VALUE_ONE , dbShape.getPadding()) ;
		assertEquals ("shape type" , NUMERIC_VALUE_ONE , dbShape.getShapeType()) ;
		assertEquals ("x position" , POSITION_VALUE , dbShape.getXPosition()) ;
		assertEquals ("y position" , POSITION_VALUE , dbShape.getYPosition()) ;
		assertEquals ("height" , SIZE_VALUE , dbShape.getHeight()) ;
		assertEquals ("width" , SIZE_VALUE , dbShape.getWidth()) ;
		assertEquals ("url" , URL_VALUE , dbShape.getUrl()) ;
		assertEquals ("node_id" , (Long)EXPECTED_NODE_VALUE , dbShape.getCompoundNode().getId()) ;
		
	}
	
	@Test
	public void testAddShape () throws Exception 
	{
		dbTester.setDataSet(new XmlDataSet(new FileInputStream(REF_DATA)));
		dbTester.onSetup();
		
		session = hibFactory.getCurrentSession() ;
		session.beginTransaction() ;
		
		Query retreivedCanvas = session.createQuery("from HibCanvas where id='100001'") ;
		Query retreivedObjectType = session.createQuery("from HibObjectType where id ='100001'");
		
		HibCanvas dbCanvas = (HibCanvas) retreivedCanvas.uniqueResult() ;
		HibObjectType objectType = (HibObjectType) retreivedObjectType.uniqueResult() ;

		HibCompoundNode hibNode = new HibCompoundNode(dbCanvas.getModel(), dbCanvas.getModel().getRootNode(), NODE_IDX_IDX); 
		
		
		HibShape shapeToSave = new HibShape ( dbCanvas , CREATION_SERIAL_2) ;
		shapeToSave.setCompoundNode(hibNode);
		shapeToSave.setName(SHAPE_NAME_2) ;
		shapeToSave.setDescription(SHAPE_DESCR_2) ;
		shapeToSave.setDetailedDescription(DETAILED_DESCR_2) ;
		shapeToSave.setFillBlue(COLOR_VALUE_2) ;
		shapeToSave.setFillRed(COLOR_VALUE_2) ;
		shapeToSave.setFillGreen(COLOR_VALUE_2) ;
		shapeToSave.setLineBlue(COLOR_VALUE_2) ;
		shapeToSave.setLineRed(COLOR_VALUE_2) ;
		shapeToSave.setLineGreen(COLOR_VALUE_2) ;
		shapeToSave.setLineWidth(NUMERIC_VALUE_TWO) ;
		shapeToSave.setLineStyle(NUMERIC_VALUE_TWO) ;
		shapeToSave.setPadding(NUMERIC_VALUE_TWO) ;
		shapeToSave.setShapeType((short)NUMERIC_VALUE_TWO) ;
		shapeToSave.setXPosition(POSITION_VALUE_2) ;
		shapeToSave.setYPosition(POSITION_VALUE_2) ;
		shapeToSave.setHeight(SIZE_VALUE_2) ;
		shapeToSave.setWidth(SIZE_VALUE_2) ;
		shapeToSave.setUrl(URL_VALUE_2) ;
		
		shapeToSave.changeCanvas(dbCanvas) ;
		shapeToSave.setObjectType(objectType) ;
		
		session.save(hibNode);
		session.getTransaction().commit() ;
		
		IDataSet expectedDeltas = new XmlDataSet(new FileInputStream(
				CREATED_SHAPE_DATA));
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
	public void testDeleteShape () throws Exception 
	{
		dbTester.setDataSet(new XmlDataSet(new FileInputStream(REF_DATA)));
		dbTester.onSetup();
		
		session = hibFactory.getCurrentSession() ;
		session.beginTransaction() ;
		
		Query retreivedShape = session.createQuery("from HibShape where id='100001'") ;
		HibShape dbShape = (HibShape) retreivedShape.uniqueResult() ;
		
		session.delete(dbShape) ;
		session.getTransaction().commit() ;
		
		IDataSet expectedDeltas = new XmlDataSet(new FileInputStream(
				DELETED_SHAPE_DATA));
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
	
//	@Test
//	public void testDeleteCanvasWithShape () throws Exception 
//	{
//		dbTester.setDataSet(new XmlDataSet(new FileInputStream(REF_DATA)));
//		dbTester.onSetup();
//		
//		session = hibFactory.getCurrentSession() ;
//		session.beginTransaction() ;
//		
//		Query retreivedCanvas = session.createQuery("From HibCanvas where id='100001'" ) ;
//		HibCanvas dbCanvas = (HibCanvas) retreivedCanvas.uniqueResult() ;
//		
//		session.delete(dbCanvas) ;
//		session.getTransaction().commit() ;
//		
//		IDataSet expectedDeltas = new XmlDataSet(new FileInputStream(
//				DELETED_SHAPE_THROUGH_CANVAS));
//		String testTables[] = expectedDeltas.getTableNames();
//		IDataSet actualChanges = dbTester.getConnection().createDataSet(testTables);
//		IDataSet expectedChanges = new CompositeDataSet(expectedDeltas);
//		
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
//	}

}
