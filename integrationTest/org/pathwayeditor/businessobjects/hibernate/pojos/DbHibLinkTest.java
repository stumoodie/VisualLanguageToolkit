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
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.ConnectionRouter;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LineStyle;
import org.pathwayeditor.testutils.PojoTester;

/**
 * @author ntsorman
 *
 */
public class DbHibLinkTest extends PojoTester{
	
	private static final String DELETED_LINK_DATA = "integrationTest/DbLinkTestData/DbDeletedLinkRefData.xml";
	private static final String CREATED_LINK_DATA = "integrationTest/DbLinkTestData/DbCreatedLinkRefData.xml";
//	private static final String CHANGED_CANVAS_DATA = "integrationTest/DbLinkTestData/DbChangedCanvasRefData.xml";
	
	
	private static final int LINK_CREATION_SERIAL = 2 ;
	private static final String LINK_NAME = "link_name" ;
	private static final String LINK_DESCR = "link_descr" ;
	private static final String LINK_DETAILED_DESCR = "link_detailed_descr" ;
	private static final int LINK_COLOUR_VALUE = 255 ;
//	private static final int NUMERIC_VALUE_ONE = 1 ;
	private static final int LINK_LINE_WIDTH = 10 ;
	private static final String LINK_URL = "http://www.HibLink.org" ;
	
	private static final int LINK_CREATION_SERIAL_2 = 76543 ;
	private static final String LINK_NAME_2 = "link_name2" ;
	private static final String LINK_DESCR_2 = "link_descr2" ;
	private static final String LINK_DETAILED_DESCR_2 = "link_detailed_descr2" ;
	private static final int LINK_COLOUR_VALUE_2 = 102 ;
//	private static final int NUMERIC_VALUE_TWO = 2 ;
	private static final int LINK_LINE_WIDTH_2 = 12 ;
	private static final String LINK_URL_2 = "http://www.HibLink2.org" ;
	
	@Ignore @Test
	public void testLoadLabel () throws Exception
	{
		Session sess = getHibFactory().getCurrentSession();
		sess.beginTransaction();
		Query retreivedLink = sess.createQuery("from HibLinkAttribute where id='100001'") ;
		
		HibLinkAttribute dbLink = (HibLinkAttribute) retreivedLink.uniqueResult() ;
		assertEquals ( "creation_serial" , LINK_CREATION_SERIAL , dbLink.getCreationSerial() ) ;
		assertEquals ( "link name" , LINK_NAME , dbLink.getName() ) ;
		assertEquals ( "link descr" , LINK_DESCR , dbLink.getDescription() ) ;
		assertEquals ( "link detailed descr" , LINK_DETAILED_DESCR , dbLink.getDetailedDescription()) ;
		assertEquals ("link colour red", LINK_COLOUR_VALUE , dbLink.getLineRed()) ;
		assertEquals ("link colour blue", LINK_COLOUR_VALUE , dbLink.getLineBlue()) ;
		assertEquals ("link colour green", LINK_COLOUR_VALUE , dbLink.getLineGreen()) ;
		assertEquals ( "link line style" , LineStyle.SOLID , dbLink.getLineStyle()) ;
		assertEquals ("link line width" , LINK_LINE_WIDTH, dbLink.getLineWidth()) ;
		assertEquals ("router type" ,ConnectionRouter.SHORTEST_PATH , dbLink.getRouterType()) ;
		assertEquals ("url" , LINK_URL , dbLink.getUrl() ) ;
		
	}
	
	@Ignore @Test
	public void testDeleteLink () throws Exception
	{
		Session sess = getHibFactory().getCurrentSession();
		sess.beginTransaction();
		Query retreivedLink = sess.createQuery("from HibLinkEdge where id='100001'") ;
		
		HibLinkEdge dbLinEdge = (HibLinkEdge) retreivedLink.uniqueResult() ;
		
		sess.delete(dbLinEdge) ;
		sess.getTransaction().commit() ;
		
		IDataSet expectedDeltas = new XmlDataSet(new FileInputStream(
				DELETED_LINK_DATA));
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
	public void testAddNewLink () throws Exception 
	{
		Session sess = getHibFactory().getCurrentSession();
		sess.beginTransaction();
//		Query retreivedCompoundEdge = sess.createQuery("from HibLinkEdge where id='100001'" ) ;
		Query retreivedCanvas = sess.createQuery("from HibCanvas where id='100001'" ) ;
//		Query retreivedObjectType = sess.createQuery("from HibObjectType where id='100001'" ) ;
		
		HibCanvas dbCanvas = (HibCanvas) retreivedCanvas.uniqueResult() ;
//		HibLinkEdge dbCompoundEdge = (HibLinkEdge) retreivedCompoundEdge.uniqueResult() ;
//		HibObjectType dbObjectType = (HibObjectType) retreivedObjectType.uniqueResult() ;
		
//		HibLinkAttribute linkToWrite = new HibLinkAttribute () ;
		HibLinkAttribute linkToWrite = null;
		
		linkToWrite.setCreationSerial(LINK_CREATION_SERIAL_2) ;
		linkToWrite.setName(LINK_NAME_2) ;
		linkToWrite.setDescription(LINK_DESCR_2) ; 
		linkToWrite.setDetailedDescription(LINK_DETAILED_DESCR_2) ;
		linkToWrite.setLineRed(LINK_COLOUR_VALUE_2) ;
		linkToWrite.setLineBlue(LINK_COLOUR_VALUE_2) ;
		linkToWrite.setLineGreen(LINK_COLOUR_VALUE_2) ;
		linkToWrite.setLineStyle(LineStyle.DASHED) ;
		linkToWrite.setLineWidth(LINK_LINE_WIDTH_2) ;
		linkToWrite.setRouterType(ConnectionRouter.FAN) ;
		linkToWrite.setUrl(LINK_URL_2) ;
		
		linkToWrite.setCanvas(dbCanvas) ;
		
		sess.save(linkToWrite) ;
		sess.getTransaction().commit() ; 
		
		IDataSet expectedDeltas = new XmlDataSet(new FileInputStream(
				CREATED_LINK_DATA));
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
	
//	@Test
//	public void testChangeMapDiagram () throws Exception 
//	{
//		doSetup() ;
//		
//		startNewTransaction();
//		Query retreivedLink = sess.createQuery("from HibLinkAttribute where id='100001'") ;
//		HibLinkAttribute dbLink = (HibLinkAttribute) retreivedLink.uniqueResult() ;
//		
////		Query retreivedCanvas = sess.createQuery("from HibCanvas where id='100002'" ) ;
////		HibCanvas dbCanvas2 = (HibCanvas) retreivedCanvas.uniqueResult() ;
//				
//		sess.saveOrUpdate(dbLink) ;
//		sess.getTransaction().commit() ;
//		
//		Session session = this.getHibFactory().getCurrentSession() ;
//		session.beginTransaction() ;
//		
//		IDataSet expectedDeltas = new XmlDataSet(new FileInputStream(
//				CHANGED_CANVAS_DATA));
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
//	}

	@Override
	protected String getDbUnitDataFilePath() {
		return "integrationTest/DbSourceData/DbSourceDataRefData.xml";
	}

}
