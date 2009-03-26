/**
 * 
 */
package org.pathwayeditor.businessobjects.drawingprimitives.properties;

import static org.junit.Assert.assertEquals;

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
import org.pathwayeditor.businessobjects.hibernate.pojos.HibTextProperty;

/**
 * @author ntsorman
 *
 */
@RunWith(JMock.class)
public class IPlainTextAnnotationPropertyTest {
	
	private Mockery mockery = new JUnit4Mockery() {{
		 setImposteriser(ClassImposteriser.INSTANCE);
	}};
	
	private IPlainTextAnnotationProperty textProperty ;
	private IPlainTextPropertyDefinition mockPropDefn;
	private HibAnnotatedCanvasAttribute mockCanvasAttribute;
	
	private static final String PROP_NAME = "propName";
	private static final String TEXT_VALUE = "text value" ;
	private static final String OTHER_TEXT_VALUE = "other text value" ;
	
	@Before
	public void setUp() throws Exception {
		mockPropDefn = this.mockery.mock(IPlainTextPropertyDefinition.class, "mockpropDefn");
		
		this.mockery.checking(new Expectations(){{
			allowing(mockPropDefn).getName(); will(returnValue(PROP_NAME));
			allowing(mockPropDefn).getDefaultValue(); will(returnValue(TEXT_VALUE));
		}});
		
		textProperty = new HibTextProperty (mockCanvasAttribute, mockPropDefn) ;
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testCreatedPlainTextProperty () throws Exception {
		assertEquals("expected definition", this.mockPropDefn, textProperty.getDefinition());
		assertEquals ( "text value" , TEXT_VALUE , textProperty.getValue() ) ;
	}
	
	@Test
	public void testChangeTextPropertyValue () throws Exception	{
		textProperty.setValue(OTHER_TEXT_VALUE) ;
		assertEquals ( "text value" , OTHER_TEXT_VALUE , textProperty.getValue() ) ;
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testChangeTextPropertyValueToNull () throws Exception {
		textProperty.setValue(null) ;
	}
	
}
