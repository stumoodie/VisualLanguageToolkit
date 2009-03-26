/**
 * 
 */
package org.pathwayeditor.bussinessobjects.drawingprimitives;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

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
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationProperty;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyDefinition;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibCanvas;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibObjectType;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibShapeAttribute;
import org.pathwayeditor.businessobjects.typedefn.ILabelAttributeDefaults;
import org.pathwayeditor.businessobjects.typedefn.IShapeAttributeDefaults;
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
//	private static final int COLOR_VALUE = 100 ;
	private static final int NEW_COLOR_VALUE = 100 ;
//	private static final int SIZE_VALUE = 50 ;
	private static final int NEW_SIZE_VALUE = 60 ;
	private static final String URL_VALUE = "http://www.shapeURL.org" ;
	private static final String NEW_URL_VALUE = "http://www.newShapeURL.org" ;
//	private static final int POSITION_VALUE = 50 ;
	private static final int NEW_POSITION_VALUE = 150 ;
	private static final String SHAPE_DESCR = "descr";
	private static final String NEW_SHAPE_DESCR = "newdescr";
	private static final String DETAILED_DESCR = "detailed descr";
	private static final String NEW_DETAILED_DESCR = "newdetailed descr";
//	private static final int NUMERIC_VALUE_ONE = 1;
//	private static final int NUMERIC_VALUE_ZERO = 0;
//	private static final String PROPERTY_ID = "property_id" ;
	private static final LineStyle LINE_STYLE  = LineStyle.DASH_DOT ;
	private static final LineStyle OTHER_LINE_STYLE  = LineStyle.DASH_DOT_DOT ;

	private static final int EXPECTED_DEFAULT_LINE_WIDTH = 1;
	private static final String EXPECTED_DEFAULT_NAME = SHAPE_NAME;
	private static final PrimitiveShapeType EXPECTED_DEFAULT_SHAPE_TYPE = PrimitiveShapeType.ELLIPSE;
	private static final Size EXPECTED_DEFAULT_SIZE = new Size(15,25);
	private static final String EXPECTED_DEFAULT_URL = URL_VALUE;
	private static final String EXPECTED_DEFAULT_DESCRIPTION = SHAPE_DESCR;
	private static final String EXPECTED_DEFAULT_DETAILED_DESCRIPTION = DETAILED_DESCR;
	private static final RGB EXPECTED_DEFAULT_FILL_COLOUR = new RGB(1,2,3);
	private static final RGB EXPECTED_DEFAULT_LINE_COLOUR = new RGB(4,5, 6);
	private static final LineStyle EXPECTED_DEFAULT_LINE_STYLE = LINE_STYLE;
	private static final Location EXPECTED_INITIAL_LOCATION = new Location(235,5543);
	private static final int NEW_LINE_WIDTH = 99;
	private static final int INVALID_LINE_WIDTH = -99;
	
	private IShapeAttribute shapeAttribute ;
//	private IAnnotationProperty mockProperty ;
	
	@SuppressWarnings("unchecked")
	@Before
	public void setUp() throws Exception {
		final HibCanvas mockCanvas = mockery.mock(HibCanvas.class , "mockCanvas") ;
		final IShapeObjectType mockObjectType = mockery.mock(IShapeObjectType.class, "mockObjectType");
		HibObjectType hibObjectType = this.mockery.mock(HibObjectType.class, "hibObjectType");
//		mockProperty = mockery.mock(IAnnotationProperty.class , "mockProperty") ;
		final IShapeAttributeDefaults mockDefaults = new DefaultsStub();
		final Set<HibShapeAttribute> mockAttributeSet = this.mockery.mock(Set.class, "mockAttributeSet");
		
		this.mockery.checking(new Expectations(){{
			allowing(mockObjectType).getDefaultAttributes(); will(returnValue(mockDefaults));
			
			allowing(mockCanvas).getCanvasAttributes(); will(returnValue(mockAttributeSet));
			
			allowing(mockAttributeSet).add(with(any(HibShapeAttribute.class)));
		}});
		
		shapeAttribute = new HibShapeAttribute ( mockCanvas , CREATION_SERIAL, mockObjectType, hibObjectType) ;
		shapeAttribute.setLocation(EXPECTED_INITIAL_LOCATION);
		this.mockery.assertIsSatisfied();
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testGetName () throws Exception 
	{
		assertEquals ( "correctName" , SHAPE_NAME + CREATION_SERIAL , shapeAttribute.getName()) ;
	}
	
	@Test
	public void testChangeName () throws Exception {
		shapeAttribute.setName(NEW_SHAPE_NAME) ;
		assertEquals ( "correct changed Name" , NEW_SHAPE_NAME , shapeAttribute.getName()) ;
	}
	
	@Test
	public void testGetDescription () throws Exception {
		assertEquals ( "correct Description" , SHAPE_DESCR , shapeAttribute.getDescription()) ;
	}
	
	@Test
	public void testChangeDescription () throws Exception {
		shapeAttribute.setDescription(NEW_SHAPE_DESCR) ;
		assertEquals ( "correct changed Description" , NEW_SHAPE_DESCR , shapeAttribute.getDescription()) ;
	}
	
	@Test
	public void testGetDetailedDescription () throws Exception {
		assertEquals ( "correct DetailedDescription" , DETAILED_DESCR , shapeAttribute.getDetailedDescription()) ;
	}
	
	@Test
	public void testChangeDetailedDescription () throws Exception {
		shapeAttribute.setDetailedDescription(NEW_DETAILED_DESCR) ;
		assertEquals ( "correct changed DetailedDescription" , NEW_DETAILED_DESCR , shapeAttribute.getDetailedDescription()) ;
	}
	
	@Test
	public void testGetURL () throws Exception {
		assertEquals ("correct URL" , URL_VALUE , shapeAttribute.getUrl()) ;
	}
	
	@Test
	public void testChangeURL () throws Exception {
		shapeAttribute.setUrl(NEW_URL_VALUE) ;
		assertEquals ("correct changed URL" , NEW_URL_VALUE , shapeAttribute.getUrl()) ;
	}
	
	@Test
	public void testGetLocation (){
		assertEquals ("location" , EXPECTED_INITIAL_LOCATION , shapeAttribute.getLocation()) ;
	}
	
	@Test
	public void testChangeLocation (){
		Location newLocation = new Location ( NEW_POSITION_VALUE , NEW_POSITION_VALUE) ;
		shapeAttribute.setLocation(newLocation) ;
		
		assertEquals ( "changed Position" , newLocation , shapeAttribute.getLocation() ) ;
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testChangeLocationToNull () 
	{
		shapeAttribute.setLocation(null) ;
	}
	
	@Test
	public void testGetSize () {
		assertEquals ( "correct Size" , EXPECTED_DEFAULT_SIZE , shapeAttribute.getSize() ) ;
	}
	
	@Test
	public void testChangeSize ()	{
		Size newSize = new Size ( NEW_SIZE_VALUE , NEW_SIZE_VALUE ) ;
		shapeAttribute.setSize(newSize) ;
		
		assertEquals ( "changed SIZE" , newSize , shapeAttribute.getSize());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testChangeSizeToNull () throws Exception
	{
		shapeAttribute.setSize(null) ;
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSetPrimitiveShape () throws Exception
	{
		shapeAttribute.setPrimitiveShape(null) ;
	}
	
	public void testGetPrimitiveShape () {
		assertEquals("expected shape type", EXPECTED_DEFAULT_SHAPE_TYPE, shapeAttribute.getPrimitiveShape());
	}
	
	@Test
	public void testGetFillColor ()	{
		assertEquals ( "correct Color" , EXPECTED_DEFAULT_FILL_COLOUR , shapeAttribute.getFillColour()) ;
	}
	
	@Test
	public void testChangeFillColor () {
		RGB newColor = new RGB (NEW_COLOR_VALUE , NEW_COLOR_VALUE , NEW_COLOR_VALUE) ;
		shapeAttribute.setFillColour(newColor) ;
		
		assertEquals ("new colour", newColor, shapeAttribute.getFillColour() ) ;
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testChangeFillColorToNull () {
		shapeAttribute.setFillColour(null) ;
	}
	
	@Test
	public void testGetLineWidth ()	{
		assertEquals ( "correct lineWidth" , EXPECTED_DEFAULT_LINE_WIDTH , shapeAttribute.getLineWidth()) ;
	}
	
	@Test
	public void testSetLineWidth () throws Exception
	{
		shapeAttribute.setLineWidth(NEW_LINE_WIDTH);
		assertEquals ( "correct changed lineWidth" , NEW_LINE_WIDTH , shapeAttribute.getLineWidth()) ;
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSetFalseLineWidth () {
		shapeAttribute.setLineWidth(INVALID_LINE_WIDTH);
	}
	
	@Test
	public void testGetLineColor () throws Exception 
	{
		assertEquals ( "correct lineColor" , EXPECTED_DEFAULT_LINE_COLOUR , shapeAttribute.getLineColour()) ;
	}
	
	@Test
	public void testChangeLineColor () {
		RGB newColor = new RGB (NEW_COLOR_VALUE , NEW_COLOR_VALUE , NEW_COLOR_VALUE) ;
		shapeAttribute.setLineColour(newColor) ;
		
		assertEquals ( "colour" , newColor , shapeAttribute.getLineColour() ) ;
	}

	@Test(expected=IllegalArgumentException.class)
	public void testChangeLineColorToNull () {
		shapeAttribute.setFillColour(null) ;
	}
	
	@Test
	public void testGetLineStyle () {
		assertEquals ( "lineStyle" , EXPECTED_DEFAULT_LINE_STYLE , shapeAttribute.getLineStyle() );
	}
	
	@Test
	public void testSetLineStyle () {
		shapeAttribute.setLineStyle(OTHER_LINE_STYLE) ;
		assertEquals ( "lineStyle" , OTHER_LINE_STYLE , shapeAttribute.getLineStyle() );
	}	
	
	@Test(expected=IllegalArgumentException.class)
	public void testSetLineStyleToNull () {
		shapeAttribute.setLineStyle(null) ;
	}	
	
	@Test
	public void testGetPropertyIterator () throws Exception 
	{
		Iterator<IAnnotationProperty> properties = shapeAttribute.propertyIterator() ;
		assertFalse ( "empty props" , properties.hasNext()) ;
	}
	
	
	private static class DefaultsStub implements IShapeAttributeDefaults {
		private final Set<IPropertyDefinition> retVal = Collections.emptySet();

		/* (non-Javadoc)
		 * @see org.pathwayeditor.businessobjects.typedefn.IShapeAttributeDefaults#getDefaultLabelAttributes()
		 */
		public ILabelAttributeDefaults getDefaultLabelAttributes() {
			throw new UnsupportedOperationException("not implemented");
		}

		/* (non-Javadoc)
		 * @see org.pathwayeditor.businessobjects.typedefn.IShapeAttributeDefaults#getDescription()
		 */
		public String getDescription() {
			return EXPECTED_DEFAULT_DESCRIPTION;
		}

		/* (non-Javadoc)
		 * @see org.pathwayeditor.businessobjects.typedefn.IShapeAttributeDefaults#getDetailedDescription()
		 */
		public String getDetailedDescription() {
			return EXPECTED_DEFAULT_DETAILED_DESCRIPTION;
		}

		/* (non-Javadoc)
		 * @see org.pathwayeditor.businessobjects.typedefn.IShapeAttributeDefaults#getFillColour()
		 */
		public RGB getFillColour() {
			return EXPECTED_DEFAULT_FILL_COLOUR;
		}

		/* (non-Javadoc)
		 * @see org.pathwayeditor.businessobjects.typedefn.IShapeAttributeDefaults#getLineColour()
		 */
		public RGB getLineColour() {
			return EXPECTED_DEFAULT_LINE_COLOUR;
		}

		/* (non-Javadoc)
		 * @see org.pathwayeditor.businessobjects.typedefn.IShapeAttributeDefaults#getLineStyle()
		 */
		public LineStyle getLineStyle() {
			return EXPECTED_DEFAULT_LINE_STYLE;
		}

		/* (non-Javadoc)
		 * @see org.pathwayeditor.businessobjects.typedefn.IShapeAttributeDefaults#getLineWidth()
		 */
		public int getLineWidth() {
			return EXPECTED_DEFAULT_LINE_WIDTH;
		}

		/* (non-Javadoc)
		 * @see org.pathwayeditor.businessobjects.typedefn.IShapeAttributeDefaults#getName()
		 */
		public String getName() {
			return EXPECTED_DEFAULT_NAME;
		}

		/* (non-Javadoc)
		 * @see org.pathwayeditor.businessobjects.typedefn.IShapeAttributeDefaults#getShapeType()
		 */
		public PrimitiveShapeType getShapeType() {
			return EXPECTED_DEFAULT_SHAPE_TYPE;
		}

		/* (non-Javadoc)
		 * @see org.pathwayeditor.businessobjects.typedefn.IShapeAttributeDefaults#getSize()
		 */
		public Size getSize() {
			return EXPECTED_DEFAULT_SIZE;
		}

		/* (non-Javadoc)
		 * @see org.pathwayeditor.businessobjects.typedefn.IShapeAttributeDefaults#getURL()
		 */
		public String getURL() {
			return EXPECTED_DEFAULT_URL;
		}

		/* (non-Javadoc)
		 * @see org.pathwayeditor.businessobjects.typedefn.IShapeAttributeDefaults#propertyIterator()
		 */
		public Iterator<IPropertyDefinition> propertyDefinitionIterator() {
			return this.retVal.iterator();
		}

		/* (non-Javadoc)
		 * @see org.pathwayeditor.businessobjects.typedefn.IPropertyDefinitionContainer#containsPropertyDefinition(java.lang.String)
		 */
		public boolean containsPropertyDefinition(String name) {
			return false;
		}

		/* (non-Javadoc)
		 * @see org.pathwayeditor.businessobjects.typedefn.IPropertyDefinitionContainer#getPropertyDefinition(java.lang.String)
		 */
		public IPropertyDefinition getPropertyDefinition(String name) {
			throw new IllegalArgumentException();
		}

		/* (non-Javadoc)
		 * @see org.pathwayeditor.businessobjects.typedefn.IPropertyDefinitionContainer#numPropertyDefinitions()
		 */
		public int numPropertyDefinitions() {
			return 0;
		}

		/* (non-Javadoc)
		 * @see org.pathwayeditor.businessobjects.typedefn.IPropertyDefinitionContainer#containsPropertyDefinition(org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyDefinition)
		 */
		public boolean containsPropertyDefinition(IPropertyDefinition name) {
			return false;
		}
		
	}
}
