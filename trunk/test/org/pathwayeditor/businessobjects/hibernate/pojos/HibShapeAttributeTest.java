/**
 * 
 */
package org.pathwayeditor.businessobjects.hibernate.pojos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LineStyle;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.PrimitiveShapeType;

/**
 * @author ntsorman
 *
 */
@RunWith(JMock.class)
public class HibShapeAttributeTest {
	
	private static final int CREATION_SERIAL = 123456 ;
	private static final String SHAPE_NAME = "shapeName" ;
	private static final int COLOR_VALUE = 100 ;
	private static final int SIZE_VALUE = 50 ;
	private static final String URL_VALUE = "http://www.shapeURL.org" ;
	private static final int POSITION_VALUE = 50 ;
	private static final String SHAPE_DESCR = "descr";
	private static final String DETAILED_DESCR = "detailed descr";
	private static final int NUMERIC_VALUE_ONE = 1;
	private static final int NUMERIC_VALUE_ZERO = 0;
	private static final String PROPERTY_ID = "property_id" ;
	private static final PrimitiveShapeType EXPECTED_SHAPE_TYPE = PrimitiveShapeType.ELLIPSE;
	
	private Mockery mockery = new JUnit4Mockery() {{
		 setImposteriser(ClassImposteriser.INSTANCE);
	}};
	
	private HibShapeAttribute shape;
	private HibShapeAttribute shape2;
	
	@Before
	public void setUp() throws Exception {

	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testAddAndRemoveProperties () throws Exception 
	{
		final HibProperty mockProperty = mockery.mock(HibProperty.class , "mockProperty") ;
		
		shape = new HibShapeAttribute () ;
		
		
		shape.addProperty( PROPERTY_ID , mockProperty) ;
		assertEquals ("one property" , NUMERIC_VALUE_ONE , shape.getProperties().size() ) ;
		
		shape.removeProperty(PROPERTY_ID) ;
		assertEquals ("no properties" , NUMERIC_VALUE_ZERO , shape.getProperties().size() ) ;		

		
	}
	
	@Test
	public void testEquals () throws Exception 
	{
		shape = new HibShapeAttribute ();
		this.populateShapeData(shape, 1) ;
		shape2 = new HibShapeAttribute () ;
		this.populateShapeData(shape2, 2) ;
		
		HibCanvas mockCanvas = mockery.mock(HibCanvas.class , "hibCanvas") ;
		HibObjectType mockObjectType = mockery.mock(HibObjectType.class , "mockObjectType") ;
		
		shape.setCanvas(mockCanvas) ;
		shape2.setCanvas(mockCanvas) ;
		
		shape.setHibObjectType(mockObjectType) ;
		shape2.setHibObjectType(mockObjectType) ;
		
		assertTrue ( shape.equals(shape)) ;
		assertFalse ( shape.equals(null)) ;
		assertFalse ( shape.equals(shape2)) ;
		assertFalse ( shape.equals(SHAPE_NAME)) ;
			
		this.populateShapeData(shape2, 1) ;
		assertTrue (shape.equals(shape2)) ;
	}
	
	@Test
	public void testHashCode () throws Exception  
	{
		shape = new HibShapeAttribute ();
		this.populateShapeData(shape, 1) ;
		shape2 = new HibShapeAttribute () ;
		this.populateShapeData(shape2, 2) ;
		
		HibCanvas mockCanvas = mockery.mock(HibCanvas.class , "hibCanvas") ;
		HibObjectType mockObjectType = mockery.mock(HibObjectType.class , "mockObjectType") ;
		
		shape.setCanvas(mockCanvas) ;
		shape2.setCanvas(mockCanvas) ;
		
		shape.setHibObjectType(mockObjectType) ;
		shape2.setHibObjectType(mockObjectType) ;
		
		assertEquals ( shape.hashCode() , shape.hashCode() ) ;
		assertFalse ( shape.hashCode() == shape2.hashCode() ) ;
		
		this.populateShapeData(shape2, 1) ;
		assertEquals ( shape.hashCode() , shape2.hashCode()) ;
	}
	
	private void populateShapeData ( HibShapeAttribute aShape , int a )
	{
		aShape.setName(SHAPE_NAME + a ) ;
		aShape.setDescription(SHAPE_DESCR + a) ;
		aShape.setCreationSerial(CREATION_SERIAL + a ) ;
		aShape.setDetailedDescription(DETAILED_DESCR + a) ;
		aShape.setFillBlue(COLOR_VALUE + a) ;
		aShape.setFillRed(COLOR_VALUE + a) ;
		aShape.setFillGreen(COLOR_VALUE + a) ;
		aShape.setLineBlue(COLOR_VALUE + a) ;
		aShape.setLineRed(COLOR_VALUE + a) ;
		aShape.setLineGreen(COLOR_VALUE + a) ;
		aShape.setLineWidth(NUMERIC_VALUE_ONE + a) ;
		aShape.setHibLineStyle(LineStyle.values()[a]) ;
		aShape.setPadding(NUMERIC_VALUE_ONE + a) ;
		aShape.setPrimitiveShape(EXPECTED_SHAPE_TYPE) ;
		aShape.setXPosition(POSITION_VALUE + a) ;
		aShape.setYPosition(POSITION_VALUE + a) ;
		aShape.setHeight(SIZE_VALUE + a) ;
		aShape.setWidth(SIZE_VALUE + a) ;
		aShape.setUrl(URL_VALUE + a) ;
	}
	
}
