/**
 * 
 */
package org.pathwayeditor.businessobjects.hibernate.pojos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkTerminus;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LinkEndDecoratorShape;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LinkTermType;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.PrimitiveShapeType;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.Size;
import org.pathwayeditor.businessobjects.typedefn.ILinkTerminusDefaults;
import org.pathwayeditor.businessobjects.typedefn.ILinkTerminusDefinition;

/**
 * @author ntsorman
 *
 */
@RunWith(JMock.class)
public class HibLinkTerminusTest {
	private Mockery mockery = new JUnit4Mockery() {{
		 setImposteriser(ClassImposteriser.INSTANCE);
	}};
	
	private static final LinkTermType LINK_END_TYPE = LinkTermType.SOURCE;
	private static final short EXPECTED_OFFSET = 1 ;
	private static final Size EXPECTED_END_SIZE = new Size(10, 20);
	private static final Size EXPECTED_TERM_SIZE = new Size(10, 20);
	private static final RGB EXPECTED_TERM_COLOUR = new RGB(100, 200, 300);
	private static final PrimitiveShapeType EXPECTED_TERM_DEC = PrimitiveShapeType.ELLIPSE;
	private static final LinkEndDecoratorShape EXPECTED_END_DEC = LinkEndDecoratorShape.ARROW;

	private HibLinkAttribute mockHibLink;
	private ILinkTerminusDefinition mockTermDefn;
	private ILinkTerminus linkTerminus;
	private ILinkTerminusDefaults mockDefaults;
	
	@Before
	public void setUp() throws Exception {
		mockHibLink = mockery.mock(HibLinkAttribute.class , "mockHibLink") ;
		mockTermDefn = mockery.mock(ILinkTerminusDefinition.class, "mockTermDefn");
		mockDefaults = mockery.mock(ILinkTerminusDefaults.class, "mockDefaults");
		
		this.mockery.checking(new Expectations(){{
			allowing(mockHibLink).getCanvas();
			
			allowing(mockTermDefn).getLinkTerminusDefaults(); will(returnValue(mockDefaults));
			
			allowing(mockDefaults).getEndDecoratorType(); will(returnValue(EXPECTED_END_DEC));
			allowing(mockDefaults).getEndSize(); will(returnValue(EXPECTED_END_SIZE));
			allowing(mockDefaults).getGap(); will(returnValue(EXPECTED_OFFSET));
			allowing(mockDefaults).getTermColour(); will(returnValue(EXPECTED_TERM_COLOUR));
			allowing(mockDefaults).getTermDecoratorType(); will(returnValue(EXPECTED_TERM_DEC));
			allowing(mockDefaults).getTermSize(); will(returnValue(EXPECTED_TERM_SIZE));
			allowing(mockDefaults).propertyDefinitionIterator(); will(returnIterator());
		}});
		
		linkTerminus = new HibLinkTerminus ( mockHibLink, LINK_END_TYPE, mockTermDefn) ;
		this.mockery.assertIsSatisfied();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testLinkTerminusInitialisedCorrectly () throws Exception 
	{
		
		assertEquals ("expected link end type" , LINK_END_TYPE , linkTerminus.getLinkTermType()) ;
		assertEquals ("expected offset" , EXPECTED_OFFSET , linkTerminus.getGap()) ;
		assertEquals ("expected end size" , EXPECTED_END_SIZE , linkTerminus.getEndSize()) ;
		assertEquals ("expected term size" , EXPECTED_TERM_SIZE , linkTerminus.getTerminusSize()) ;
		assertEquals ("expected term dec" , EXPECTED_TERM_DEC , linkTerminus.getTerminusDecoratorType()) ;
		assertEquals ("expected offset" , EXPECTED_TERM_COLOUR , linkTerminus.getTerminusColor()) ;
		assertEquals ("expected offset" , EXPECTED_END_DEC , linkTerminus.getEndDecoratorType()) ;
		assertFalse( "expected properties" , linkTerminus.propertyIterator().hasNext()) ;
		
	}
}
