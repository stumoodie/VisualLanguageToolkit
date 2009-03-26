/**
 * 
 */
package org.pathwayeditor.businessobjects.drawingprimitives.properties;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

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
import org.pathwayeditor.businessobjects.hibernate.pojos.HibNumberProperty;

/**
 * @author ntsorman
 *
 */
@RunWith(JMock.class)
public class INumberAnnotationPropertyTest {
	private Mockery mockery = new JUnit4Mockery() {{
		 setImposteriser(ClassImposteriser.INSTANCE);
	}};
	
	private INumberAnnotationProperty numberProperty ;
	private INumberPropertyDefinition mockPropDefn;
	private HibAnnotatedCanvasAttribute mockCanvasAttribute;
	private static final String PROP_NAME = "propName";
	private static final BigDecimal NUMBER_VALUE = new BigDecimal(10000000) ;
	private static final BigDecimal OTHER_NUMBER_VALUE = new BigDecimal ( 1222121 ) ;
	
	@Before
	public void setUp() throws Exception {
		mockPropDefn = this.mockery.mock(INumberPropertyDefinition.class, "mockpropDefn");
		
		this.mockery.checking(new Expectations(){{
			allowing(mockPropDefn).getName(); will(returnValue(PROP_NAME));
			allowing(mockPropDefn).getDefaultValue(); will(returnValue(NUMBER_VALUE));
		}});
		
		numberProperty = new HibNumberProperty (mockCanvasAttribute, mockPropDefn) ;
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testCreatedPlainTextProperty () {
		assertEquals("expected definition", this.mockPropDefn, numberProperty.getDefinition());
		assertEquals ( "text value" , NUMBER_VALUE , numberProperty.getValue() ) ;
		assertTrue ( "object value" , numberProperty.getValue() instanceof Number ) ;
	}
	
	@Test
	public void testChangeTextPropertyValue () throws Exception
	{
		numberProperty.setValue(OTHER_NUMBER_VALUE) ;
		assertEquals ( "expected value" , OTHER_NUMBER_VALUE , numberProperty.getValue() ) ;
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testChangeTextPropertyValueToNull () throws Exception
	{
		numberProperty.setValue(null) ;
		assertEquals ( "expected value" , OTHER_NUMBER_VALUE , numberProperty.getValue() ) ;
	}
	
}
