/**
 * 
 */
package org.pathwayeditor.businessobjects.drawingprimitives.properties;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibAnnotatedCanvasAttribute;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibRichTextProperty;

/**
 * @author ntsorman
 *
 */
@RunWith(JMock.class)
public class IHtmlAnnotationPropertyTest {
	private Mockery mockery = new JUnit4Mockery(){{
        setImposteriser(ClassImposteriser.INSTANCE);
    }};
	
	private IHtmlPropertyDefinition mockPropDefn;
	private IHtmlAnnotationProperty htmlProperty;
	private HibAnnotatedCanvasAttribute mockCanvasAttribute;
	
	private static final String PROP_NAME = "propName";
	private static final String TEXT_VALUE = "text value" ;
	private static final String OTHER_TEXT_VALUE = "other text value" ;
	
	@Before
	public void setUp() throws Exception {
		mockPropDefn = this.mockery.mock(IHtmlPropertyDefinition.class, "mockpropDefn");
		mockCanvasAttribute = this.mockery.mock(HibAnnotatedCanvasAttribute.class, "mockCanvasAttribute");
		
		this.mockery.checking(new Expectations(){{
			allowing(mockPropDefn).getName(); will(returnValue(PROP_NAME));
			allowing(mockPropDefn).getDefaultValue(); will(returnValue(TEXT_VALUE));
		}});
		
		htmlProperty = new HibRichTextProperty (mockCanvasAttribute, mockPropDefn) ;
		this.mockery.assertIsSatisfied();
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testCreatedPlainTextProperty () {
		assertEquals("expected definition", this.mockPropDefn, htmlProperty.getDefinition());
		assertEquals ( "text value" , TEXT_VALUE , htmlProperty.getRichTextValue() ) ;
		assertTrue ( "object value" , htmlProperty.getValue() instanceof String ) ;
	}
	
	@Test
	public void testChangeTextPropertyValue () throws Exception
	{
		htmlProperty.setValue(OTHER_TEXT_VALUE) ;
		assertEquals ( "text value" , OTHER_TEXT_VALUE , htmlProperty.getRichTextValue() ) ;
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testChangeTextPropertyValueToNull () throws Exception
	{
		htmlProperty.setValue(null) ;
		assertEquals ( "text value" , TEXT_VALUE , htmlProperty.getRichTextValue() ) ;
	}
	
}
