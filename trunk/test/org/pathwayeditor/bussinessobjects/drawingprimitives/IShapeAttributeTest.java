/**
 * 
 */
package org.pathwayeditor.bussinessobjects.drawingprimitives;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Collections;
import java.util.Iterator;
import java.util.Set;

import org.jmock.Expectations;
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
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.PrimitiveShapeType;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.Size;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyDefinition;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibCanvas;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibObjectType;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibShapeAttribute;
import org.pathwayeditor.businessobjects.typedefn.IDefaultLabelAttributes;
import org.pathwayeditor.businessobjects.typedefn.IDefaultShapeAttributes;
import org.pathwayeditor.businessobjects.typedefn.IObjectType;
import org.pathwayeditor.businessobjects.typedefn.IPropertyDefinitionFilter;
import org.pathwayeditor.businessobjects.typedefn.IShapeObjectType;

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

	public static final int EXPECTED_DEFAULT_LINE_WIDTH = 0;
	public static final String EXPECTED_DEFAULT_NAME = SHAPE_NAME;
	public static final PrimitiveShapeType EXPECTED_DEFAULT_SHAPE_TYPE = PrimitiveShapeType.ELLIPSE;
	public static final Size EXPECTED_DEFAULT_SIZE = new Size(15,25);
	public static final String EXPECTED_DEFAULT_URL = URL_VALUE;
	private static final String EXPECTED_DEFAULT_DESCRIPTION = SHAPE_DESCR;
	private static final String EXPECTED_DEFAULT_DETAILED_DESCRIPTION = DETAILED_DESCR;
	private static final RGB EXPECTED_DEFAULT_FILL_COLOUR = new RGB(1,2,3);
	private static final RGB EXPECTED_DEFAULT_LINE_COLOUR = new RGB(4,5, 6);
	private static final LineStyle EXPECTED_DEFAULT_LINE_STYLE = LINE_STYLE;
	
	private IShapeAttribute shapeAttribute ;
//	private IAnnotationProperty mockProperty ;
	
	@Before
	public void setUp() throws Exception {
		final HibCanvas mockCanvas = mockery.mock(HibCanvas.class , "mockCanvas") ;
		final IShapeObjectType mockObjectType = mockery.mock(IShapeObjectType.class, "mockObjectType");
		HibObjectType hibObjectType = this.mockery.mock(HibObjectType.class, "hibObjectType");
//		mockProperty = mockery.mock(IAnnotationProperty.class , "mockProperty") ;
		final IDefaultShapeAttributes mockDefaults = new DefaultsStub();;
		
		this.mockery.checking(new Expectations(){{
			allowing(mockObjectType).getDefaultAttributes(); will(returnValue(mockDefaults));
			
			
		}});
		
		shapeAttribute = new HibShapeAttribute ( mockCanvas , CREATION_SERIAL, mockObjectType, hibObjectType) ;
		this.mockery.assertIsSatisfied();
	}

	@After
	public void tearDown() throws Exception {
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
	
	@Test(expected=IllegalArgumentException.class)
	public void testSetPrimitiveShape () throws Exception
	{
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
	public void testGetPropertyIterator () throws Exception 
	{
		fail("Implement this properly");
//		Iterator<IAnnotationProperty> properties = shapeAttribute.propertyIterator() ;
//		assertTrue ( "containsProperty" , properties.contains(mockProperty)) ;
//		assertEquals ( "number of properties" , NUMERIC_VALUE_ONE , properties.size() );
	}
	
	
	private static class DefaultsStub implements IDefaultShapeAttributes {

		/* (non-Javadoc)
		 * @see org.pathwayeditor.businessobjects.typedefn.IDefaultShapeAttributes#getDefaultLabelAttributes()
		 */
		public IDefaultLabelAttributes getDefaultLabelAttributes() {
			// TODO Auto-generated method stub
			return null;
		}

		/* (non-Javadoc)
		 * @see org.pathwayeditor.businessobjects.typedefn.IDefaultShapeAttributes#getDescription()
		 */
		public String getDescription() {
			return EXPECTED_DEFAULT_DESCRIPTION;
		}

		/* (non-Javadoc)
		 * @see org.pathwayeditor.businessobjects.typedefn.IDefaultShapeAttributes#getDetailedDescription()
		 */
		public String getDetailedDescription() {
			return EXPECTED_DEFAULT_DETAILED_DESCRIPTION;
		}

		/* (non-Javadoc)
		 * @see org.pathwayeditor.businessobjects.typedefn.IDefaultShapeAttributes#getFillColour()
		 */
		public RGB getFillColour() {
			return EXPECTED_DEFAULT_FILL_COLOUR;
		}

		/* (non-Javadoc)
		 * @see org.pathwayeditor.businessobjects.typedefn.IDefaultShapeAttributes#getLineColour()
		 */
		public RGB getLineColour() {
			return EXPECTED_DEFAULT_LINE_COLOUR;
		}

		/* (non-Javadoc)
		 * @see org.pathwayeditor.businessobjects.typedefn.IDefaultShapeAttributes#getLineStyle()
		 */
		public LineStyle getLineStyle() {
			return EXPECTED_DEFAULT_LINE_STYLE;
		}

		/* (non-Javadoc)
		 * @see org.pathwayeditor.businessobjects.typedefn.IDefaultShapeAttributes#getLineWidth()
		 */
		public int getLineWidth() {
			return EXPECTED_DEFAULT_LINE_WIDTH;
		}

		/* (non-Javadoc)
		 * @see org.pathwayeditor.businessobjects.typedefn.IDefaultShapeAttributes#getName()
		 */
		public String getName() {
			return EXPECTED_DEFAULT_NAME;
		}

		/* (non-Javadoc)
		 * @see org.pathwayeditor.businessobjects.typedefn.IDefaultShapeAttributes#getPropertiesFilter()
		 */
		public IPropertyDefinitionFilter getPropertiesFilter() {
			return new StubPropDefn();
		}

		/* (non-Javadoc)
		 * @see org.pathwayeditor.businessobjects.typedefn.IDefaultShapeAttributes#getShapeType()
		 */
		public PrimitiveShapeType getShapeType() {
			return EXPECTED_DEFAULT_SHAPE_TYPE;
		}

		/* (non-Javadoc)
		 * @see org.pathwayeditor.businessobjects.typedefn.IDefaultShapeAttributes#getSize()
		 */
		public Size getSize() {
			return EXPECTED_DEFAULT_SIZE;
		}

		/* (non-Javadoc)
		 * @see org.pathwayeditor.businessobjects.typedefn.IDefaultShapeAttributes#getURL()
		 */
		public String getURL() {
			return EXPECTED_DEFAULT_URL;
		}
		
	}
	
	private static class StubPropDefn implements IPropertyDefinitionFilter {
		private final Set<IPropertyDefinition> retVal = Collections.emptySet();
		
		/* (non-Javadoc)
		 * @see org.pathwayeditor.businessobjects.typedefn.IPropertyDefinitionFilter#getAllProperties()
		 */
		public Set<IPropertyDefinition> getAllProperties() {
			return retVal;
		}

		/* (non-Javadoc)
		 * @see org.pathwayeditor.businessobjects.typedefn.IPropertyDefinitionFilter#getAllPropertiesIterator()
		 */
		public Iterator<IPropertyDefinition> getAllPropertiesIterator() {
			return retVal.iterator();
		}

		/* (non-Javadoc)
		 * @see org.pathwayeditor.businessobjects.typedefn.IPropertyDefinitionFilter#getEditableProperties()
		 */
		public Set<IPropertyDefinition> getEditableProperties() {
			return retVal;
		}

		/* (non-Javadoc)
		 * @see org.pathwayeditor.businessobjects.typedefn.IPropertyDefinitionFilter#getFormattedTextProperties()
		 */
		public Set<IPropertyDefinition> getFormattedTextProperties() {
			return retVal;
		}

		/* (non-Javadoc)
		 * @see org.pathwayeditor.businessobjects.typedefn.IPropertyDefinitionFilter#getNumberProperties()
		 */
		public Set<IPropertyDefinition> getNumberProperties() {
			return retVal;
		}

		/* (non-Javadoc)
		 * @see org.pathwayeditor.businessobjects.typedefn.IPropertyDefinitionFilter#getObjectType()
		 */
		public IObjectType getObjectType() {
			throw new UnsupportedOperationException("not implemented");
		}

		/* (non-Javadoc)
		 * @see org.pathwayeditor.businessobjects.typedefn.IPropertyDefinitionFilter#getTextProperties()
		 */
		public Set<IPropertyDefinition> getTextProperties() {
			return retVal;
		}

		/* (non-Javadoc)
		 * @see org.pathwayeditor.businessobjects.typedefn.IPropertyDefinitionFilter#getVisualisableProperties()
		 */
		public Set<IPropertyDefinition> getVisualisableProperties() {
			return retVal;
		}
		
	}
}
