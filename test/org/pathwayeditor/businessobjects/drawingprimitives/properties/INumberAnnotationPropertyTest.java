/*
Copyright 2009, Court of the University of Edinburgh

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License. 
*/
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
import org.pathwayeditor.businessobjects.impl.AnnotatedCanvasAttribute;
import org.pathwayeditor.businessobjects.impl.NumberProperty;

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
	private AnnotatedCanvasAttribute mockCanvasAttribute;
	private static final String PROP_NAME = "propName";
	private static final BigDecimal NUMBER_VALUE = new BigDecimal(10000000) ;
	private static final BigDecimal OTHER_NUMBER_VALUE = new BigDecimal ( 1222121 ) ;
	
	@Before
	public void setUp() throws Exception {
		mockPropDefn = this.mockery.mock(INumberPropertyDefinition.class, "mockpropDefn");
		
		this.mockery.checking(new Expectations(){{
			allowing(mockPropDefn).getName(); will(returnValue(PROP_NAME));
			allowing(mockPropDefn).getDefaultValue(); will(returnValue(NUMBER_VALUE));
			allowing(mockPropDefn).getDisplayName(); will(returnValue(PROP_NAME));
		}});
		
		numberProperty = new NumberProperty (mockCanvasAttribute, mockPropDefn) ;
	}

	@After
	public void tearDown() throws Exception {
		this.mockCanvasAttribute = null;
		this.mockPropDefn = null;
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
