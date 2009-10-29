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
		mockCanvasAttribute = this.mockery.mock(HibAnnotatedCanvasAttribute.class, "mockCanvasAttribute");
		
		this.mockery.checking(new Expectations(){{
			allowing(mockPropDefn).getName(); will(returnValue(PROP_NAME));
			allowing(mockPropDefn).getDefaultValue(); will(returnValue(TEXT_VALUE));
			allowing(mockPropDefn).getDisplayName(); will(returnValue(PROP_NAME));
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
