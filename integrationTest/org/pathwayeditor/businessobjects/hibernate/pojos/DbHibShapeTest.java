/**
 * 
 */
package org.pathwayeditor.businessobjects.hibernate.pojos;

import static org.junit.Assert.assertEquals;

import java.io.FileInputStream;

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
	
	private static final int CREATION_SERIAL = 123456 ;
	private static final String SHAPE_NAME = "shapeName" ;
	private static final int COLOR_VALUE = 100 ;
	private static final int SIZE_VALUE = 50 ;
	private static final String URL_VALUE = "http://www.shapeURL.org" ;
	private static final int POSITION_VALUE = 50 ;
	private static final String SHAPE_DESCR = "descr";
	private static final String DETAILED_DESCR = "detailed descr";
	private static final int NUMERIC_VALUE_ONE = 1;
	
	
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
		
	}
	
	

}
