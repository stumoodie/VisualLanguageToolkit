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

package org.pathwayeditor.businessobjects.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.pathwayeditor.businessobjects.drawingprimitives.ILabelAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.ILabelAttributeFactory;
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.IModel;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationProperty;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationSyntaxService;
import org.pathwayeditor.businessobjects.typedefn.ILabelObjectType;
import org.pathwayeditor.testfixture.CanvasTestFixture;
import org.pathwayeditor.testfixture.NotationSubsystemFixture;

import uk.ac.ed.inf.graph.util.IndexCounter;

/**
 * @author Stuart Moodie
 *
 */
@RunWith(JMock.class)
public class LabelAttributeFactoryCanCreateTest {
	private Mockery mockery;
	private NotationSubsystemFixture notationFixture;
	private CanvasTestFixture testFixture;
	private ILabelAttributeFactory testInstance;
	private IShapeAttribute shapeAtt;
	private IAnnotationProperty prop;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		this.mockery = new JUnit4Mockery();
		this.notationFixture = new NotationSubsystemFixture(mockery);
		this.notationFixture.buildFixture();
		this.testFixture = new CanvasTestFixture(mockery, "", notationFixture.getNotationSubsystem());
		this.testFixture.buildFixture();
		this.testInstance = new LabelAttributeFactory(new IndexCounter(100));
		shapeAtt = this.testFixture.getObject(CanvasTestFixture.SHAPE1_ATT_ID);
		prop = shapeAtt.getProperty(NotationSubsystemFixture.SHAPE_TYPE_A_PROP_NAME);
		this.testInstance.setProperty(prop);
		this.testInstance.setDestinationAttribute(shapeAtt);
		INotationSyntaxService syntaxService = this.notationFixture.getNotationSubsystem().getSyntaxService();
		ILabelObjectType labelObjectType = syntaxService.getLabelObjectType(NotationSubsystemFixture.LABEL_TYPE_ID);
		this.testInstance.setLabelObjectType(labelObjectType);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		this.mockery = null;
		this.testFixture = null;
		this.notationFixture = null;
		this.testInstance = null;
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.LabelAttributeFactory#canCreateAttribute()}.
	 */
	@Test
	public void testCanCreateAttribute() {
		assertTrue("can create", this.testInstance.canCreateAttribute());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.LabelAttributeFactory#getDestinationAttribute()}.
	 */
	@Test
	public void testGetDestinationAttribute() {
		assertEquals("expected value", shapeAtt, this.testInstance.getDestinationAttribute());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.LabelAttributeFactory#getDestinationAttribute()}.
	 */
	@Test
	public void testGetProperty() {
		assertEquals("expected value", prop, this.testInstance.getProperty());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.LabelAttributeFactory#canCreateAttribute()}.
	 */
	@Test
	public void testCreateAttribute() {
		final IModel rootAtt = this.testFixture.getModel();
		this.mockery.checking(new Expectations(){{
			allowing(rootAtt).addCanvasAttribute(with(any(ILinkAttribute.class)));
		}});
		ILabelAttribute labelAtt = this.testInstance.createAttribute();
		assertNotNull("exists", labelAtt);
		
	}

}
