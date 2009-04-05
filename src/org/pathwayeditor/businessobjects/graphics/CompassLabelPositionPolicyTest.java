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
package org.pathwayeditor.businessobjects.graphics;


import static org.junit.Assert.assertEquals;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.pathwayeditor.businessobjects.drawingprimitives.ILabelAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.Location;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.Size;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IHtmlAnnotationProperty;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.INumberAnnotationProperty;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPlainTextAnnotationProperty;

/**
 * @author smoodie
 *
 */
@RunWith(JMock.class)
public class CompassLabelPositionPolicyTest {
	private static final Location LABEL1_LOCATION = new Location(15, -20);
	private static final Location EXPECTED_SECOND_LABEL_LOCATION = new Location(-20, 10);
	private static final Location SHAPE_LOCATION = Location.ORIGIN;
	private static final Size SHAPE_SIZE = new Size(30, 20);
	private static final Location LABEL2_LOCATION = new Location(50, 10);
	private final Mockery mockery = new JUnit4Mockery();
	private ILabelLocationPolicy testInstance;
	private IShapeAttribute mockAttribute;
	private IHtmlAnnotationProperty mockProp1;
	private IPlainTextAnnotationProperty mockProp2;
	private INumberAnnotationProperty mockProp3;
	private ILabelAttribute mockLabel1;
	private ILabelAttribute mockLabel2;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		this.mockAttribute = this.mockery.mock(IShapeAttribute.class, "mockAttribute");
		this.mockProp1 = this.mockery.mock(IHtmlAnnotationProperty.class, "mockProp1");
		this.mockProp2 = this.mockery.mock(IPlainTextAnnotationProperty.class, "mockProp2");
		this.mockProp3 = this.mockery.mock(INumberAnnotationProperty.class, "mockProp3");
		this.mockLabel1 = this.mockery.mock(ILabelAttribute.class, "mockLabel1");
		this.mockLabel2 = this.mockery.mock(ILabelAttribute.class, "mockLabel2");
		this.mockery.checking(new Expectations(){{
			allowing(mockAttribute).propertyIterator(); will(returnIterator(mockProp1, mockProp2, mockProp3));
			allowing(mockAttribute).getLocation(); will(returnValue(SHAPE_LOCATION));
			allowing(mockAttribute).getSize(); will(returnValue(SHAPE_SIZE));
			
			allowing(mockProp1).isDisplayed(); will(returnValue(true));
			allowing(mockProp1).getDisplayedLabel(); will(returnValue(mockLabel1));

			allowing(mockProp2).isDisplayed(); will(returnValue(false));

			allowing(mockProp3).isDisplayed(); will(returnValue(true));
			allowing(mockProp3).getDisplayedLabel(); will(returnValue(mockLabel2));
			
			allowing(mockLabel1).getLocation(); will(returnValue(LABEL1_LOCATION));

			allowing(mockLabel2).getLocation(); will(returnValue(LABEL2_LOCATION));
		}});
		this.testInstance = new CompassLabelPositionPolicy(mockAttribute);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testLayoutOfOnlyLabel(){
		Location actualLocation = this.testInstance.nextLabelLocation();
		assertEquals("expect label locn", EXPECTED_SECOND_LABEL_LOCATION, actualLocation);
	}
	
}
