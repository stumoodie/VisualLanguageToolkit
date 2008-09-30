/**
 * 
 */
package org.pathwayeditor.bussinessobjects.drawingprimitives;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
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
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.ConnectionRouter;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.IBendPoint;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LineStyle;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.Location;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.PrimitiveShapeType;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.Size;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyDefinition;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibBendPoint;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibCanvas;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibLinkAttribute;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibObjectType;
import org.pathwayeditor.businessobjects.typedefn.IDefaultLabelAttributes;
import org.pathwayeditor.businessobjects.typedefn.IDefaultLinkAttributes;
import org.pathwayeditor.businessobjects.typedefn.ILinkObjectType;
import org.pathwayeditor.businessobjects.typedefn.ILinkTerminusDefaults;
import org.pathwayeditor.businessobjects.typedefn.IObjectType;
import org.pathwayeditor.businessobjects.typedefn.IPropertyDefinitionFilter;

/**
 * @author ntsorman
 *
 */
@RunWith(JMock.class)
public class ILinkAttributeTest {
	
	private Mockery mockery = new JUnit4Mockery() {{
		 setImposteriser(ClassImposteriser.INSTANCE);
	}};
	
	private static final int LINK_INDEX = 1 ;
	private static final int CREATION_SERIAL = 1 ;
	private static final int INDEX_POS_1 = 1 ;
	private static final int INDEX_POS_2 = 2 ;
	private static final int INDEX_POS_3 = 3 ;
	private static final int POS_VALUE_1 = 20 ;
	private static final int POS_VALUE_2 = 30 ;
	private static final int POS_VALUE_3 = 40 ;
	
	private static final int NUMERIC_VALUE_3 = 3 ;
	private static final int NUMERIC_VALUE_2 = 2 ;
	private static final int NUMERIC_VALUE_1 = 1 ;
	
	private static final String URL = "url" ;
	private static final String NEW_URL = "newurl" ;
	
	private static final int EXPECTED_DEFAULT_LINE_WIDTH = 3;
	private static final String EXPECTED_DEFAULT_NAME = "link name";
	private static final PrimitiveShapeType EXPECTED_DEFAULT_SHAPE_TYPE = PrimitiveShapeType.ELLIPSE;
	private static final Size EXPECTED_DEFAULT_SIZE = new Size(15,25);
	private static final String EXPECTED_DEFAULT_URL = "http://www.google.com";
	private static final String EXPECTED_DEFAULT_DESCRIPTION = "descn";
	private static final String EXPECTED_DEFAULT_DETAILED_DESCRIPTION = "detailed descn";
	private static final RGB EXPECTED_DEFAULT_FILL_COLOUR = new RGB(1,2,3);
	private static final RGB EXPECTED_DEFAULT_LINE_COLOUR = new RGB(4,5, 6);
	private static final LineStyle EXPECTED_DEFAULT_LINE_STYLE = LineStyle.DASH_DOT;
	private static final Location EXPECTED_INITIAL_LOCATION = new Location(235,5543);
	private static final int NEW_LINE_WIDTH = 99;
	private static final int INVALID_LINE_WIDTH = -99;
	private static final ConnectionRouter EXPECTED_DEFAULT_ROUTER = ConnectionRouter.FAN;

	
	private static final ConnectionRouter ROUTER_TYPE = ConnectionRouter.SHORTEST_PATH ;
	private static final LineStyle LINE_STYLE = LineStyle.DASH_DOT ;
	
	private ILinkAttribute linkAttribute ;
	private HibCanvas mockCanvas ;
	private IBendPoint mockBendPoint1 ;
	private IBendPoint mockBendPoint2 ;
//	private HibTextProperty textProperty ;
	
	@Before
	public void setUp() throws Exception {
		mockCanvas = mockery.mock(HibCanvas.class , "hibCanvas") ;
		final HibObjectType mockObjectType = mockery.mock(HibObjectType.class , "mockObjectType") ;
		final ILinkObjectType mockLinkObjectType = mockery.mock(ILinkObjectType.class, "mockLinkObjectType");
		final IDefaultLinkAttributes stubDefaults = new DefaultsStub();

		this.mockery.checking(new Expectations(){{
			allowing(mockLinkObjectType).getDefaultLinkAttributes(); will(returnValue(stubDefaults));
			
			
		}});
		
		linkAttribute = new HibLinkAttribute ( mockCanvas, LINK_INDEX,	mockLinkObjectType,mockObjectType ) ;
		
//		tempLinkAttribute.setCreationSerial(CREATION_SERIAL) ;
		
		mockBendPoint1 = new HibBendPoint ((HibLinkAttribute)linkAttribute, INDEX_POS_1, POS_VALUE_1, POS_VALUE_1) ;
		mockBendPoint2 = new HibBendPoint ((HibLinkAttribute)linkAttribute, INDEX_POS_2, POS_VALUE_2, POS_VALUE_2) ;
		
		linkAttribute.addBendPoint(mockBendPoint1) ;
		linkAttribute.addBendPoint(mockBendPoint2) ;
		
//		linkAttribute.setUrl(URL) ;
		
//		textProperty = new HibTextProperty () ;

	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testGetCanvas () throws Exception
	{
		assertEquals ( "correctCanvas" , mockCanvas , linkAttribute.getCanvas() );
	}
	
	@Test
	public void testGetCreationSerial () throws Exception 
	{
		assertEquals ( "correct creationSerial" , CREATION_SERIAL , linkAttribute.getCreationSerial()) ;
	}
	
	@Test(expected=UnsupportedOperationException.class)
	public void testGetObjectType () throws Exception
	{
		// TODO
		linkAttribute.getObjectType() ;
	}
	
	@Test(expected=UnsupportedOperationException.class)
	public void testGetLinkSourceDecoration() throws Exception
	{
		// TODO
		linkAttribute.getSourceTerminus() ;
	}
	
	@Test(expected=UnsupportedOperationException.class)
	public void testGetLinkTargetDecoration() throws Exception
	{
		// TODO
		linkAttribute.getTargetTerminus() ;
	}
	
	@Test
	public void testSetRouter() throws Exception
	{
		assertEquals ( "null router" , null , linkAttribute.getRouter() ) ;
		linkAttribute.setRouter(ROUTER_TYPE) ;
		assertEquals ( "router type" , ROUTER_TYPE , linkAttribute.getRouter() );
	}
	
	@Test
	public void testGetBendpointsIterator () throws Exception
	{
		Iterator<IBendPoint> iter = linkAttribute.bendPointIterator() ; 
		
		List<IBendPoint> retreivedBendPoints = new ArrayList<IBendPoint> (  );
		
		while (iter.hasNext())
			retreivedBendPoints.add(iter.next());

		assertEquals ( "correctNumber" , NUMERIC_VALUE_2 , retreivedBendPoints.size() ) ;
		assertTrue ( "bendPoint1 is there" , retreivedBendPoints.contains(mockBendPoint1) ) ;
		assertTrue ( "bendPoint2 is there" , retreivedBendPoints.contains(mockBendPoint2) ) ;
	}
	
	@Test
	public void testNumOfBendPoints () throws Exception
	{
		assertEquals ( "two bendpoints" , NUMERIC_VALUE_2 , linkAttribute.numBendPoints()) ;
	}
	
	@Test
	public void testAddBendPoins () throws Exception
	{
		IBendPoint newBendPoint = new HibBendPoint ((HibLinkAttribute)linkAttribute, INDEX_POS_3, POS_VALUE_3, POS_VALUE_3) ;
		linkAttribute.addBendPoint(newBendPoint);
		assertEquals ( "two bendpoints" , NUMERIC_VALUE_3 , linkAttribute.numBendPoints()) ;
		assertTrue ( "contains bendpoint3" , linkAttribute.containsBendPoint(newBendPoint)) ;
	}
	
	@Test
	public void testContainsBendPoints () throws Exception
	{
		assertTrue ( "contains bendpoint1" , linkAttribute.containsBendPoint(mockBendPoint1)) ;
		assertTrue ( "contains bendpoint2" , linkAttribute.containsBendPoint(mockBendPoint2)) ;
	}
	
	@Test
	public void testRemoveBendPoints () throws Exception 
	{
		linkAttribute.removeBendPoint(mockBendPoint1) ;
		assertEquals ( "one bendpoints" , NUMERIC_VALUE_1 , linkAttribute.numBendPoints()) ;
		assertFalse ( "contains bendpoint1" , linkAttribute.containsBendPoint(mockBendPoint1)) ;
	}
	
	@Test
	public void testSetUrl () throws Exception 
	{
		linkAttribute.setUrl(NEW_URL) ;
		assertEquals ( "url" , NEW_URL , linkAttribute.getUrl()) ;
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSetUrlToNull() throws Exception 
	{
		linkAttribute.setUrl(null) ;
		assertEquals ( "url" , URL , linkAttribute.getUrl()) ;
	}
	
	@Test
	public void testPropertyIterator() {
		linkAttribute.propertyIterator() ;
	}
	
	@Test
	public void testGetLineStyle() {
		linkAttribute.setLineStyle(LINE_STYLE) ;
		assertEquals ("line style" , LINE_STYLE, linkAttribute.getLineStyle() ) ;
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSetLineStyleToNull() {
		linkAttribute.setLineStyle(null) ;
	}
	
	@Test
	public void testGetPropertyIterator () {
		fail("Implement this test");
//		Iterator <IAnnotationProperty> properties = linkAttribute.propertyIterator() ;
		//FIXME:
//		assertEquals ( "property iterator contains one" , NUMERIC_VALUE_1 , properties.size() );
//		assertTrue ( "property iterator contains property" ,  properties.contains(textProperty)) ;
	}
	
	private static class DefaultsStub implements IDefaultLinkAttributes {

		/* (non-Javadoc)
		 * @see org.pathwayeditor.businessobjects.typedefn.IDefaultShapeAttributes#getDefaultLabelAttributes()
		 */
		public IDefaultLabelAttributes getDefaultLabelAttributes() {
			throw new UnsupportedOperationException("not implemented");
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
		 * @see org.pathwayeditor.businessobjects.typedefn.IDefaultShapeAttributes#getURL()
		 */
		public String getURL() {
			return EXPECTED_DEFAULT_URL;
		}

		/* (non-Javadoc)
		 * @see org.pathwayeditor.businessobjects.typedefn.IDefaultLinkAttributes#getLinkSource()
		 */
		public ILinkTerminusDefaults getLinkSource() {
			throw new UnsupportedOperationException("not implemented");
		}

		/* (non-Javadoc)
		 * @see org.pathwayeditor.businessobjects.typedefn.IDefaultLinkAttributes#getLinkTarget()
		 */
		public ILinkTerminusDefaults getLinkTarget() {
			throw new UnsupportedOperationException("not implemented");
		}

		/* (non-Javadoc)
		 * @see org.pathwayeditor.businessobjects.typedefn.IDefaultLinkAttributes#getPropertyDefinitionFilter()
		 */
		public IPropertyDefinitionFilter getPropertyDefinitionFilter() {
			return new StubPropDefn();
		}

		/* (non-Javadoc)
		 * @see org.pathwayeditor.businessobjects.typedefn.IDefaultLinkAttributes#getRouter()
		 */
		public ConnectionRouter getRouter() {
			return EXPECTED_DEFAULT_ROUTER;
		}

		/* (non-Javadoc)
		 * @see org.pathwayeditor.businessobjects.typedefn.IDefaultLinkAttributes#getUrl()
		 */
		public String getUrl() {
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
