/**
 * 
 */
package org.pathwayeditor.bussinessobjects.drawingprimitives;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.Location;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.Size;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibCanvas;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibProperty;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibShapeAttribute;

/**
 * @author ntsorman
 *
 */
@RunWith(JMock.class)
public class IShapeAttributeTest {
	
	private Mockery mockery = new JUnit4Mockery() {{
		 setImposteriser(ClassImposteriser.INSTANCE);
	}};
	
	private static final int CREATION_SERIAL = 123456 ;
	private static final String SHAPE_NAME = "shapeName" ;
	private static final String NEW_SHAPE_NAME = "newShapeName" ;
	private static final int COLOR_VALUE = 100 ;
	private static final int NEW_COLOR_VALUE = 100 ;
	private static final int SIZE_VALUE = 50 ;
	private static final int NEW_SIZE_VALUE = 60 ;
	private static final String URL_VALUE = "http://www.shapeURL.org" ;
	private static final String NEW_URL_VALUE = "http://www.newShapeURL.org" ;
	private static final int POSITION_VALUE = 50 ;
	private static final int NEW_POSITION_VALUE = 150 ;
	private static final String SHAPE_DESCR = "descr";
	private static final String NEW_SHAPE_DESCR = "newdescr";
	private static final String DETAILED_DESCR = "detailed descr";
	private static final String NEW_DETAILED_DESCR = "newdetailed descr";
	private static final int NUMERIC_VALUE_ONE = 1;
	private static final int NUMERIC_VALUE_ZERO = 0;
	private static final String PROPERTY_ID = "property_id" ;
	
	private IShapeAttribute shapeAttribute ;
	private HibProperty mockProperty ;
	
	@Before
	public void setUp() throws Exception {
		final HibCanvas mockCanvas = mockery.mock(HibCanvas.class , "mockCanvas") ;
		final HibShapeAttribute tempShapeAttribute = new HibShapeAttribute ( mockCanvas , CREATION_SERIAL) ;
		
		tempShapeAttribute.setName(SHAPE_NAME) ;
		tempShapeAttribute.setFillBlue(COLOR_VALUE) ;
		tempShapeAttribute.setFillGreen(COLOR_VALUE) ;
		tempShapeAttribute.setFillRed(COLOR_VALUE) ;
		tempShapeAttribute.setXPosition(POSITION_VALUE) ;
		tempShapeAttribute.setYPosition(POSITION_VALUE) ;
		tempShapeAttribute.setDetailedDescription(DETAILED_DESCR) ;
		tempShapeAttribute.setDescription(SHAPE_DESCR) ;
		tempShapeAttribute.setHeight(SIZE_VALUE) ;
		tempShapeAttribute.setWidth(SIZE_VALUE) ;
		tempShapeAttribute.setUrl(URL_VALUE) ;
		tempShapeAttribute.setLineWidth(NUMERIC_VALUE_ONE);
		tempShapeAttribute.setLineBlue(COLOR_VALUE) ;
		tempShapeAttribute.setLineRed(COLOR_VALUE) ;
		tempShapeAttribute.setLineGreen(COLOR_VALUE) ;
		
		
		
		mockProperty = mockery.mock(HibProperty.class , "mockProperty") ;
		
		mockery.checking( new Expectations () {{
			one(mockProperty).getShape() ; will(returnValue(null)) ;
			one(mockProperty).setShape(tempShapeAttribute) ;
		}});
		
		tempShapeAttribute.addProperty(PROPERTY_ID , mockProperty) ;
		
		shapeAttribute = tempShapeAttribute ;
			
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testSomething () throws Exception
	{

	}
	
	@Test
	public void testGetName () throws Exception 
	{
		assertEquals ( "correctName" , SHAPE_NAME , shapeAttribute.getName()) ;
	}
	
	@Test
	public void testChangeName () throws Exception 
	{
		shapeAttribute.setName(NEW_SHAPE_NAME) ;
		assertEquals ( "correct changed Name" , NEW_SHAPE_NAME , shapeAttribute.getName()) ;
	}
	
	@Test
	public void testGetDescription () throws Exception 
	{
		assertEquals ( "correct Description" , SHAPE_DESCR , shapeAttribute.getDescription()) ;
	}
	
	@Test
	public void testChangeDescription () throws Exception 
	{
		shapeAttribute.setDescription(NEW_SHAPE_DESCR) ;
		assertEquals ( "correct changed Description" , NEW_SHAPE_DESCR , shapeAttribute.getDescription()) ;
	}
	
	@Test
	public void testGetDetailedDescription () throws Exception 
	{
		assertEquals ( "correct DetailedDescription" , DETAILED_DESCR , shapeAttribute.getDetailedDescription()) ;
	}
	
	@Test
	public void testChangeDetailedDescription () throws Exception 
	{
		shapeAttribute.setDetailedDescription(NEW_DETAILED_DESCR) ;
		assertEquals ( "correct changed DetailedDescription" , NEW_DETAILED_DESCR , shapeAttribute.getDetailedDescription()) ;
	}
	
	@Test
	public void testGetURL () throws Exception
	{
		assertEquals ("correct URL" , URL_VALUE , shapeAttribute.getUrl()) ;
	}
	
	@Test
	public void testChangeURL () throws Exception
	{
		shapeAttribute.setUrl(NEW_URL_VALUE) ;
		assertEquals ("correct changed URL" , NEW_URL_VALUE , shapeAttribute.getUrl()) ;
	}
	
	@Test
	public void testGetLocation () throws Exception
	{
		assertEquals ("location" , new Location(POSITION_VALUE , POSITION_VALUE) , shapeAttribute.getLocation()) ;
	}
	
	@Test
	public void testChangeLocation () throws Exception
	{
		Location newLocation = new Location ( NEW_POSITION_VALUE , NEW_POSITION_VALUE) ;
		shapeAttribute.setLocation(newLocation) ;
		
		assertEquals ( "changed XPosition" , NEW_POSITION_VALUE , ((HibShapeAttribute)shapeAttribute).getXPosition() ) ;
		assertEquals ( "changed YPosition" , NEW_POSITION_VALUE , ((HibShapeAttribute)shapeAttribute).getYPosition() ) ;
	}
	
	@Test
	public void testGetSize () throws Exception
	{
		assertEquals ( "correct Size" , new Size ( SIZE_VALUE , SIZE_VALUE ) , shapeAttribute.getSize() ) ;
	}
	
	@Test
	public void testChangeSize () throws Exception
	{
		Size newSize = new Size ( NEW_SIZE_VALUE , NEW_SIZE_VALUE ) ;
		shapeAttribute.setSize(newSize) ;
		
		assertEquals ( "changed Height" , NEW_SIZE_VALUE , ((HibShapeAttribute)shapeAttribute).getHeight() ) ;
		assertEquals ( "changed Width" , NEW_SIZE_VALUE , ((HibShapeAttribute)shapeAttribute).getWidth() ) ;
	}
	
	@Ignore
	@Test
	public void testSetPrimitiveShape () throws Exception
	{
		// TODO 
		fail() ;
	}
	
	@Ignore
	@Test
	public void testGetPrimitiveShape () throws Exception
	{
		// TODO 
		fail() ;
	}
	
	@Test
	public void testGetFillColor () throws Exception 
	{
		assertEquals ( "correct Color" , new RGB(COLOR_VALUE,COLOR_VALUE,COLOR_VALUE) , shapeAttribute.getFillColour()) ;
	}
	
	@Test
	public void testChangeFillColor () throws Exception 
	{
		RGB newColor = new RGB (NEW_COLOR_VALUE , NEW_COLOR_VALUE , NEW_COLOR_VALUE) ;
		shapeAttribute.setFillColour(newColor) ;
		
		assertEquals ( "correct changed Color" , new RGB(NEW_COLOR_VALUE,NEW_COLOR_VALUE,NEW_COLOR_VALUE) ,
						shapeAttribute.getFillColour()) ;
	}
	
	@Test
	public void testGetLineWidth () throws Exception 
	{
		assertEquals ( "correct lineWidth" , NUMERIC_VALUE_ONE , shapeAttribute.getLineWidth()) ;
	}
	
	@Test
	public void testSetLineWidth () throws Exception
	{
		shapeAttribute.setLineWidth(0);
		assertEquals ( "correct changed lineWidth" , NUMERIC_VALUE_ZERO , shapeAttribute.getLineWidth()) ;
	}
	
	@Test
	public void testGetLineColor () throws Exception 
	{
		assertEquals ( "correct lineColor" , new RGB(COLOR_VALUE,COLOR_VALUE,COLOR_VALUE) , shapeAttribute.getLineColour()) ;
	}
	
	@Test
	public void testChangeLineColor () throws Exception 
	{
		RGB newColor = new RGB (NEW_COLOR_VALUE , NEW_COLOR_VALUE , NEW_COLOR_VALUE) ;
		shapeAttribute.setFillColour(newColor) ;
		
		assertEquals ( "correct changed LineColor" , new RGB(NEW_COLOR_VALUE,NEW_COLOR_VALUE,NEW_COLOR_VALUE) ,
						shapeAttribute.getLineColour()) ;
	}
	
	@Ignore
	@Test
	public void testGetLineStyle () throws Exception
	{
		// TODO
		fail() ;
	}
	
	@Ignore
	@Test
	public void testSetLineStyle () throws Exception
	{
		// TODO
		fail() ;
	}	
	
	@Ignore
	@Test
	public void testHasProperty () throws Exception 
	{
		// TODO
		fail();
	}
	
	@Ignore
	@Test
	public void testGetProperty () throws Exception 
	{
		// TODO
		fail () ;
	}
}
