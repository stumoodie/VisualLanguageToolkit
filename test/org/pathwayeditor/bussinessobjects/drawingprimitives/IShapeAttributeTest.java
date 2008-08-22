/**
 * 
 */
package org.pathwayeditor.bussinessobjects.drawingprimitives;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LineStyle;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.Location;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.Size;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationProperty;
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
	private static final LineStyle LINE_STYLE  = LineStyle.DASH_DOT ;
	private static final LineStyle OTHER_LINE_STYLE  = LineStyle.DASH_DOT_DOT ;
	
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
		tempShapeAttribute.setLineStyle(LINE_STYLE) ;
		
		
		
		mockProperty = mockery.mock(HibProperty.class , "mockProperty") ;
		
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
	
	@Test(expected=IllegalArgumentException.class)
	public void testChangeLocationToNull () throws Exception
	{
		shapeAttribute.setLocation(null) ;
		
		assertEquals ( "changed XPosition" , POSITION_VALUE , ((HibShapeAttribute)shapeAttribute).getXPosition() ) ;
		assertEquals ( "changed YPosition" , POSITION_VALUE , ((HibShapeAttribute)shapeAttribute).getYPosition() ) ;
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
	
	@Test(expected=IllegalArgumentException.class)
	public void testChangeSizeToNull () throws Exception
	{
		shapeAttribute.setSize(null) ;
		
		assertEquals ( "changed Height" , SIZE_VALUE , ((HibShapeAttribute)shapeAttribute).getHeight() ) ;
		assertEquals ( "changed Width" , SIZE_VALUE , ((HibShapeAttribute)shapeAttribute).getWidth() ) ;
	}
	
	@Test(expected=UnsupportedOperationException.class)
	public void testSetPrimitiveShape () throws Exception
	{
		// TODO 
		shapeAttribute.setPrimitiveShape(null) ;
	}
	
	@Test(expected=UnsupportedOperationException.class)
	public void testGetPrimitiveShape () throws Exception
	{
		// TODO 
		shapeAttribute.getPrimitiveShape() ;
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
		
		assertEquals ( "red" , NEW_COLOR_VALUE , ((HibShapeAttribute)shapeAttribute).getFillRed() ) ;
		assertEquals ( "blue" , NEW_COLOR_VALUE , ((HibShapeAttribute)shapeAttribute).getFillBlue() ) ;
		assertEquals ( "green" , NEW_COLOR_VALUE , ((HibShapeAttribute)shapeAttribute).getFillGreen() ) ;
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testChangeFillColorToNull () throws Exception 
	{
		shapeAttribute.setFillColour(null) ;
		
		assertEquals ( "red" , COLOR_VALUE , ((HibShapeAttribute)shapeAttribute).getFillRed() ) ;
		assertEquals ( "blue" , COLOR_VALUE , ((HibShapeAttribute)shapeAttribute).getFillBlue() ) ;
		assertEquals ( "green" , COLOR_VALUE , ((HibShapeAttribute)shapeAttribute).getFillGreen() ) ;
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
	
	@Test(expected=IllegalArgumentException.class)
	public void testSetFalseLineWidth () throws Exception
	{
		shapeAttribute.setLineWidth(-10);
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
		
		assertEquals ( "red" , NEW_COLOR_VALUE , ((HibShapeAttribute)shapeAttribute).getLineRed() ) ;
		assertEquals ( "blue" , NEW_COLOR_VALUE , ((HibShapeAttribute)shapeAttribute).getLineBlue() ) ;
		assertEquals ( "green" , NEW_COLOR_VALUE , ((HibShapeAttribute)shapeAttribute).getLineGreen() ) ;
	}

	@Test(expected=IllegalArgumentException.class)
	public void testChangeLineColorToNull () throws Exception 
	{
		shapeAttribute.setFillColour(null) ;
		
		assertEquals ( "red" , COLOR_VALUE , ((HibShapeAttribute)shapeAttribute).getLineRed() ) ;
		assertEquals ( "blue" , COLOR_VALUE , ((HibShapeAttribute)shapeAttribute).getLineBlue() ) ;
		assertEquals ( "green" , COLOR_VALUE , ((HibShapeAttribute)shapeAttribute).getLineGreen() ) ;
	}
	
	@Test
	public void testGetLineStyle () throws Exception
	{
		assertEquals ( "lineStyle" , LineStyle.DASH_DOT , shapeAttribute.getLineStyle() );
	}
	
	@Test
	public void testSetLineStyle () throws Exception
	{
		shapeAttribute.setLineStyle(OTHER_LINE_STYLE) ;
		assertEquals ( "lineStyle" , LineStyle.DASH_DOT_DOT , shapeAttribute.getLineStyle() );
	}	
	
	@Test(expected=IllegalArgumentException.class)
	public void testSetLineStyleToNull () throws Exception
	{
		shapeAttribute.setLineStyle(null) ;
		assertEquals ( "lineStyle" , LineStyle.DASH_DOT , shapeAttribute.getLineStyle() );
	}	
		
	@Test
	public void testHasProperty () throws Exception 
	{
		// TODO
		shapeAttribute.hasProperty("a property") ;
	}
	
	@Test
	public void testGetProperty () throws Exception 
	{
		assertEquals ( "get property" ,mockProperty , shapeAttribute.getProperty(PROPERTY_ID ) ) ;
	}
	
	@Test(expected=UnsupportedOperationException.class)
	public void testGetFirstObject() {
		shapeAttribute.getFirstObject() ;
	}
	
	@Test(expected=UnsupportedOperationException.class)
	public void testGetLastObject() {
		shapeAttribute.getLastObject() ;
	}
	
	@Test(expected=UnsupportedOperationException.class)
	public void testGetNextObject() {
		shapeAttribute.getNextObject() ;
	}
	
	@Test(expected=UnsupportedOperationException.class)
	public void testGetPreviousObject() {
		shapeAttribute.getPreviousObject() ;
	}
	
	@Test
	public void testGetPropertyIterator () throws Exception 
	{
		Set<IAnnotationProperty> properties = shapeAttribute.propertyIterator() ;
		assertTrue ( "containsProperty" , properties.contains(mockProperty)) ;
		assertEquals ( "number of properties" , NUMERIC_VALUE_ONE , properties.size() );
	}
}
