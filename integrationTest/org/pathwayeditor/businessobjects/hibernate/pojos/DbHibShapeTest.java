/**
 * 
 */
package org.pathwayeditor.businessobjects.hibernate.pojos;

import static org.junit.Assert.assertEquals;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

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
public class DbHibShapeTest extends PojoTester{
	

	private static final String CREATED_SHAPE_DATA = "integrationTest/DbShapeTestData/DbCreatedShapeReData.xml";
	private static final String DELETED_SHAPE_DATA = "integrationTest/DbShapeTestData/DbDeletedShapeRefData.xml";
	private static final String DELETED_SHAPE_THROUGH_CANVAS = "integrationTest/DbShapeTestData/DbDeletedShapeRefData.xml";
	
	
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
	private static final long EXPECTED_NODE_VALUE = 100002L;
//	private static final int NODE_IDX_IDX = 110000;
	
	
	@Test
	public void testLoadedShape () throws Exception
	{
		doSetup () ;
		
		Query retreivedShape = getSession().createQuery("from HibShapeAttribute where id='100001'") ;
		
		HibShapeAttribute dbShape = (HibShapeAttribute) retreivedShape.uniqueResult() ;
		
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
		assertEquals ("node_id" , (Long)EXPECTED_NODE_VALUE , dbShape.getShapeNode().getId()) ;
		
	}
	
	@Test
	public void testAddShape () throws Exception 
	{
		doSetup () ;
		
		Query retreivedCanvas = getSession().createQuery("from HibCanvas where id='100001'") ;
		Query retreivedObjectType = getSession().createQuery("from HibObjectType where id ='100001'");
		Query retreivedCompoundNode = getSession().createQuery("from HibCompoundNode where id='100002'") ;
		
		HibCanvas dbCanvas = (HibCanvas) retreivedCanvas.uniqueResult() ;
		HibObjectType objectType = (HibObjectType) retreivedObjectType.uniqueResult() ;
		HibShapeNode hibNode = (HibShapeNode) retreivedCompoundNode.uniqueResult() ;

		HibShapeAttribute shapeToSave = new HibShapeAttribute ( dbCanvas , CREATION_SERIAL_2) ;
		shapeToSave.setShapeNode(hibNode);
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
		
		getSession().save(hibNode);
		getSession().getTransaction().commit() ;
		
		IDataSet expectedDeltas = new XmlDataSet(new FileInputStream(
				CREATED_SHAPE_DATA));
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
	public void testDeleteShape () throws Exception 
	{
		doSetup () ;
		
		Query retreivedShape = getSession().createQuery("from HibShapeAttribute where id='100001'") ;
		HibShapeAttribute dbShape = (HibShapeAttribute) retreivedShape.uniqueResult() ;
		
		getSession().delete(dbShape) ;
		getSession().getTransaction().commit() ;
		
		IDataSet expectedDeltas = new XmlDataSet(new FileInputStream(
				DELETED_SHAPE_DATA));
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
	public void testDeleteCanvasWithShape () throws Exception 
	{
		doSetup () ;
		Query retreivedCanvas = getSession().createQuery("From HibCanvas where id='100001'" ) ;
		HibCanvas dbCanvas = (HibCanvas) retreivedCanvas.uniqueResult() ;
		
		
		List<HibLinkAttribute> links = new ArrayList<HibLinkAttribute> (dbCanvas.getLinks()) ;
		
		for ( int a = 0 ; a < links.size() ; a++ )
		{
			HibLinkAttribute tempLink = (HibLinkAttribute) links.get(a) ;
			
			dbCanvas.removeLink(tempLink) ;
			
			getSession().delete(tempLink) ;
		}
		
		List<HibLabelAttribute> labels = new ArrayList<HibLabelAttribute> (dbCanvas.getLabels()) ;
		
		for ( int a = 0 ; a < labels.size() ; a++ )
		{
			HibLabelAttribute tempLabel = (HibLabelAttribute) labels.get(a) ;
			
			dbCanvas.removeLabel(tempLabel) ;
			
			getSession().delete(tempLabel) ;
		}
		
		List<HibShapeAttribute> shapes = new ArrayList<HibShapeAttribute> (dbCanvas.getShapes()) ;
		
		for ( int a = 0 ; a < shapes.size() ; a++ )
		{
			HibShapeAttribute tempShape = (HibShapeAttribute) shapes.get(a) ;
			
			dbCanvas.removeShape(tempShape) ;
			
			getSession().delete(tempShape) ;
		}
		
		getSession().delete(dbCanvas) ;
		getSession().flush();
		getSession().getTransaction().commit() ;
		
		IDataSet expectedDeltas = new XmlDataSet(new FileInputStream(
				DELETED_SHAPE_THROUGH_CANVAS));
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
