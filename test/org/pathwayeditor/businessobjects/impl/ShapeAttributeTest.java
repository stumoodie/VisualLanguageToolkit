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
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.pathwayeditor.businessobjects.drawingprimitives.ICanvasElementAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.ILabelAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.IRootAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LineStyle;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.CanvasAttributePropertyChange;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.ICanvasAttributeChangeListener;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.ICanvasAttributePropertyChangeEvent;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.ICanvasAttributeResizedEvent;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.ICanvasAttributeTranslationEvent;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationProperty;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPlainTextPropertyDefinition;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyBuilder;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyDefinition;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationSyntaxService;
import org.pathwayeditor.businessobjects.typedefn.IShapeObjectType;
import org.pathwayeditor.figure.geometry.Dimension;
import org.pathwayeditor.figure.geometry.Envelope;
import org.pathwayeditor.figure.geometry.Point;
import org.pathwayeditor.testfixture.CanvasPropertyChangeEventValidator;
import org.pathwayeditor.testfixture.CanvasResizeEventValidator;
import org.pathwayeditor.testfixture.CanvasTestFixture;
import org.pathwayeditor.testfixture.CanvasTranslationEventValidator;
import org.pathwayeditor.testfixture.GeneralIteratorTestUtility;
import org.pathwayeditor.testfixture.IObjectConstructor;
import org.pathwayeditor.testfixture.NotationSubsystemFixture;

import uk.ac.ed.inf.graph.compound.ICompoundGraphElement;

/**
 * @author smoodie
 *
 */
@RunWith(JMock.class)
public class ShapeAttributeTest {
	private static final double DOUBLE_EQUIVALENCE_THRESH = 0.0001;
	private static final int EXPECTED_NUM_LISTENERS = 1;
	private static final int EXPECTED_NUM_PROPS = 1;
	private CanvasTestFixture testFixture;
	private IShapeAttribute testInstance;
	private Mockery mockery;
	private NotationSubsystemFixture notationFixture;
	private IShapeObjectType expectedObjectType;
	private LineStyle expectedLineStyle;
	private double expectedLineWidth;
	private RGB expectedFillColour;
	private RGB expectedLineColour;
	private String expectedShapeDefn;
	private Dimension expectedSize;
	private Envelope expectedBounds;
	private ICanvasAttributeChangeListener testListener;
	private ICanvasAttributeTranslationEvent translationEvent = null;
	private ICanvasAttributeResizedEvent resizedEvent = null;
	private ICanvasAttributePropertyChangeEvent propChangeEvent = null;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		this.mockery = new JUnit4Mockery();
		this.notationFixture = new NotationSubsystemFixture(mockery);
		this.notationFixture.buildFixture();
		this.testFixture = new CanvasTestFixture(mockery, "", notationFixture.getNotationSubsystem());
		final INotationSyntaxService syntaxService = this.notationFixture.getNotationSubsystem().getSyntaxService();
		this.expectedObjectType = syntaxService.getShapeObjectType(CanvasTestFixture.SHAPE1_ATT_OT);
		this.expectedLineStyle = this.expectedObjectType.getDefaultAttributes().getLineStyle();
		this.expectedLineWidth = this.expectedObjectType.getDefaultAttributes().getLineWidth();
		this.expectedFillColour = this.expectedObjectType.getDefaultAttributes().getFillColour();
		this.expectedLineColour = this.expectedObjectType.getDefaultAttributes().getLineColour();
		this.expectedShapeDefn = this.expectedObjectType.getDefaultAttributes().getShapeDefinition();
		this.expectedSize = this.expectedObjectType.getDefaultAttributes().getSize();
		this.expectedBounds = new Envelope(Point.ORIGIN, expectedSize);
		final IPlainTextPropertyDefinition shapeTypeAPropDefn = this.expectedObjectType.getDefaultAttributes().getPropertyDefinition(NotationSubsystemFixture.SHAPE_TYPE_A_PROP_NAME);
		this.mockery.checking(new Expectations(){{
			exactly(1).of(shapeTypeAPropDefn).createProperty(with(any(IPropertyBuilder.class))); will(testFixture.buildTextProperty(shapeTypeAPropDefn));
//			exactly(1).of(shapeTypeBPropDefn).createProperty(with(any(IPropertyBuilder.class))); will(testFixture.buildTextProperty(shapeTypeBPropDefn));
		}});
		this.testFixture.redefineBuilder(CanvasTestFixture.SHAPE1_ATT_ID, new IObjectConstructor<IShapeAttribute>(){
			@Override
			public IShapeAttribute create() {
				testInstance = new ShapeAttribute(testFixture.getRootAttribute(), CanvasTestFixture.SHAPE1_ATT_IDX, expectedObjectType);
				return testInstance;
			}

			@Override
			public boolean build() {
				return true;
			}
			
		});
		this.testFixture.buildFixture();
		this.testListener = new ICanvasAttributeChangeListener() {

			@Override
			public void propertyChange(ICanvasAttributePropertyChangeEvent e) {
				propChangeEvent = e;
			}

			@Override
			public void nodeTranslated(ICanvasAttributeTranslationEvent e) {
				translationEvent = e;
			}

			@Override
			public void nodeResized(ICanvasAttributeResizedEvent e) {
				resizedEvent = e;
			}
			
		};
		this.testInstance.addChangeListener(this.testListener);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		this.mockery = null;
		this.notationFixture = null;
		this.testFixture = null;
		this.testInstance = null;
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.ShapeAttribute#ShapeAttribute(org.pathwayeditor.businessobjects.drawingprimitives.IRootAttribute, int, org.pathwayeditor.businessobjects.impl.ShapeAttribute)}.
	 */
	@Test(expected=NullPointerException.class)
	public void testShapeAttributeIRootAttributeIntShapeAttribute() {
		new ShapeAttribute(null, CanvasTestFixture.SHAPE1_ATT_IDX, (IShapeObjectType)null);
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.ShapeAttribute#getObjectType()}.
	 */
	@Test
	public void testGetObjectType() {
		assertEquals("expected ot", this.expectedObjectType, this.testInstance.getObjectType());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.ShapeAttribute#getLineStyle()}.
	 */
	@Test
	public void testGetLineStyle() {
		assertEquals("expected line style", expectedLineStyle, this.testInstance.getLineStyle());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.ShapeAttribute#getLineWidth()}.
	 */
	@Test
	public void testGetLineWidth() {
		assertEquals("expected line width", expectedLineWidth, this.testInstance.getLineWidth(), DOUBLE_EQUIVALENCE_THRESH);
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.ShapeAttribute#setLineWidth(double)}.
	 */
	@Test
	public void testSetLineWidth() {
		double newLineWidth = 847362.0; 
		this.testInstance.setLineWidth(newLineWidth);
		assertFalse("new value different", Math.abs(this.expectedLineWidth - newLineWidth) < DOUBLE_EQUIVALENCE_THRESH);
		assertEquals("new line width", newLineWidth, this.testInstance.getLineWidth(), DOUBLE_EQUIVALENCE_THRESH);
		CanvasPropertyChangeEventValidator validator = new CanvasPropertyChangeEventValidator(this.testInstance, CanvasAttributePropertyChange.LINE_WIDTH,
				this.expectedLineWidth, newLineWidth);
		validator.validateEvent(propChangeEvent);
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.ShapeAttribute#getFillColour()}.
	 */
	@Test
	public void testGetFillColour() {
		assertEquals("expected fill colour", expectedFillColour, this.testInstance.getFillColour());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.ShapeAttribute#getLineColour()}.
	 */
	@Test
	public void testGetLineColour() {
		assertEquals("expected line colour", expectedLineColour, this.testInstance.getLineColour());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.ShapeAttribute#setFillColour(org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB)}.
	 */
	@Test
	public void testSetFillColour() {
		RGB newColour = new RGB(1, 2, 3);
		assertFalse("new value different", this.expectedFillColour.equals(newColour));
		this.testInstance.setFillColour(newColour);
		assertEquals("expected fill colour", newColour, this.testInstance.getFillColour());
		CanvasPropertyChangeEventValidator validator = new CanvasPropertyChangeEventValidator(this.testInstance, CanvasAttributePropertyChange.FILL_COLOUR,
				this.expectedFillColour, newColour);
		validator.validateEvent(propChangeEvent);
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.ShapeAttribute#setLineColour(org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB)}.
	 */
	@Test
	public void testSetLineColour() {
		RGB newColour = new RGB(1, 2, 3);
		assertFalse("new value different", this.expectedLineColour.equals(newColour));
		this.testInstance.setLineColour(newColour);
		assertEquals("expected line colour", newColour, this.testInstance.getLineColour());
		CanvasPropertyChangeEventValidator validator = new CanvasPropertyChangeEventValidator(this.testInstance, CanvasAttributePropertyChange.LINE_COLOUR,
				this.expectedLineColour, newColour);
		validator.validateEvent(propChangeEvent);
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.ShapeAttribute#setLineStyle(org.pathwayeditor.businessobjects.drawingprimitives.attributes.LineStyle)}.
	 */
	@Test
	public void testSetLineStyle() {
		LineStyle newLineStyle = LineStyle.SOLID;
		assertFalse("new value different", this.expectedLineStyle.equals(newLineStyle));
		this.testInstance.setLineStyle(newLineStyle);
		assertEquals("expected line colour", newLineStyle, this.testInstance.getLineStyle());
		CanvasPropertyChangeEventValidator validator = new CanvasPropertyChangeEventValidator(this.testInstance, CanvasAttributePropertyChange.LINE_STYLE,
				this.expectedLineStyle, newLineStyle);
		validator.validateEvent(propChangeEvent);
	}

	@Test
	public void testAddChangeListener() {
		this.testInstance.addChangeListener(new ICanvasAttributeChangeListener() {
			
			@Override
			public void propertyChange(ICanvasAttributePropertyChangeEvent e) {
				
			}

			@Override
			public void nodeTranslated(ICanvasAttributeTranslationEvent e) {
				
			}

			@Override
			public void nodeResized(ICanvasAttributeResizedEvent e) {
				
			}
		});
		assertEquals("expected num listeners", EXPECTED_NUM_LISTENERS+1, this.testInstance.getChangeListeners().size());
	}

	@Test
	public void testGetChangeListeners() {
		GeneralIteratorTestUtility<ICanvasAttributeChangeListener> testIter = new GeneralIteratorTestUtility<ICanvasAttributeChangeListener>(this.testListener);
		testIter.testIterator(this.testInstance.getChangeListeners().iterator());
	}

	@Test
	public void testRemoveChangeListener() {
		this.testInstance.removeChangeListener(testListener);
		assertEquals("expected num listeners", EXPECTED_NUM_LISTENERS-1, this.testInstance.getChangeListeners().size());
		GeneralIteratorTestUtility<ICanvasAttributeChangeListener> testIter = new GeneralIteratorTestUtility<ICanvasAttributeChangeListener>();
		testIter.testIterator(this.testInstance.getChangeListeners().iterator());
	}
	
	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.ShapeAttribute#getBounds()}.
	 */
	@Test
	public void testGetBounds() {
		assertEquals("expected bounds", expectedBounds, this.testInstance.getBounds());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.ShapeAttribute#setBounds(org.pathwayeditor.figure.geometry.Envelope)}.
	 */
	@Test
	public void testSetBounds() {
		Envelope newBounds = new Envelope(12.0, 549.0, 3029.0, 34.1);
		this.testInstance.setBounds(newBounds);
		assertFalse("no bounds", this.expectedBounds.equals(newBounds));
		assertEquals("new bounds", newBounds, this.testInstance.getBounds());
		CanvasPropertyChangeEventValidator validator = new CanvasPropertyChangeEventValidator(this.testInstance, CanvasAttributePropertyChange.BOUNDS,
				this.expectedBounds, newBounds);
		validator.validateEvent(propChangeEvent);
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.ShapeAttribute#getShapeDefinition()}.
	 */
	@Test
	public void testGetShapeDefinition() {
		assertEquals("expected shape defn", this.expectedShapeDefn, this.testInstance.getShapeDefinition());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.ShapeAttribute#resize(org.pathwayeditor.figure.geometry.Point, org.pathwayeditor.figure.geometry.Dimension)}.
	 */
	@Test
	public void testResize() {
		Point transln = new Point(12.0, 34.0);
		Dimension sizeDelta = new Dimension(23.9, -34.0);
		Envelope resizeBounds = this.expectedBounds.resize(transln, sizeDelta);
		this.testInstance.resize(transln, sizeDelta);
		assertEquals("expected bounds", resizeBounds, this.testInstance.getBounds());
		CanvasResizeEventValidator validator = new CanvasResizeEventValidator(this.testInstance, transln, sizeDelta);
		validator.validateEvent(resizedEvent);
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.ShapeAttribute#translate(org.pathwayeditor.figure.geometry.Point)}.
	 */
	@Test
	public void testTranslate() {
		Point transln = new Point(12.0, 34.0);
		Envelope movedBounds = this.expectedBounds.translate(transln);
		this.testInstance.translate(transln);
		assertEquals("expected bounds", movedBounds, this.testInstance.getBounds());
		CanvasTranslationEventValidator validator = new CanvasTranslationEventValidator(this.testInstance, transln);
		validator.validateEvent(translationEvent);
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.ShapeAttribute#getRootAttribute()}.
	 */
	@Test
	public void testGetRootAttribute() {
		IRootAttribute rootAtt = this.testFixture.getObject(CanvasTestFixture.ROOT_ATT_ID);
		assertEquals("expected root att", rootAtt, this.testInstance.getRootAttribute());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.AnnotatedCanvasAttribute#getProperty(java.lang.String)}.
	 */
	@Test
	public void testGetPropertyString() {
		assertNotNull("property found", this.testInstance.getProperty(NotationSubsystemFixture.SHAPE_TYPE_A_PROP_NAME));
	}

	@Test(expected=IllegalArgumentException.class)
	public void testGetPropertyNullString() {
		this.testInstance.getProperty((String)null);
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.AnnotatedCanvasAttribute#getProperty(org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyDefinition)}.
	 */
	@Test
	public void testGetPropertyIPropertyDefinition() {
		IPropertyDefinition expectedProperty = expectedObjectType.getDefaultAttributes().getPropertyDefinition(NotationSubsystemFixture.SHAPE_TYPE_A_PROP_NAME); 
		assertNotNull("property found", this.testInstance.getProperty(expectedProperty));
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.AnnotatedCanvasAttribute#getProperty(org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyDefinition)}.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testGetPropertyIPropertyDefinitionNull() {
		this.testInstance.getProperty((IPropertyDefinition)null);
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.AnnotatedCanvasAttribute#propertyIterator()}.
	 */
	@Test
	public void testPropertyIterator() {
		List<IAnnotationProperty> expectedProps = new LinkedList<IAnnotationProperty>();
		Iterator<IPropertyDefinition> defnIter = this.expectedObjectType.getDefaultAttributes().propertyDefinitionIterator();
		while(defnIter.hasNext()){
			IPropertyDefinition defn = defnIter.next();
			expectedProps.add(new TestProperty(this.testInstance, defn));
		}
		GeneralIteratorTestUtility<IAnnotationProperty> testIter = new GeneralIteratorTestUtility<IAnnotationProperty>(expectedProps);
		testIter.testIterator(this.testInstance.propertyIterator());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.AnnotatedCanvasAttribute#containsProperty(java.lang.String)}.
	 */
	@Test
	public void testContainsPropertyString() {
		assertFalse("no prop", this.testInstance.containsProperty((String)null));
		assertFalse("no prop", this.testInstance.containsProperty(""));
		assertTrue("prop exists", this.testInstance.containsProperty(NotationSubsystemFixture.SHAPE_TYPE_A_PROP_NAME));
		assertFalse("no prop exists", this.testInstance.containsProperty(NotationSubsystemFixture.SHAPE_TYPE_B_PROP_NAME));
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.AnnotatedCanvasAttribute#numProperties()}.
	 */
	@Test
	public void testNumProperties() {
		assertEquals("expected num props", EXPECTED_NUM_PROPS, this.testInstance.numProperties());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.AnnotatedCanvasAttribute#containsProperty(org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationProperty)}.
	 */
	@Test
	public void testContainsPropertyIPropertyDefinition() {
		assertFalse("no prop", this.testInstance.containsProperty((IPropertyDefinition)null));
		IPropertyDefinition expectedProperty = expectedObjectType.getDefaultAttributes().getPropertyDefinition(NotationSubsystemFixture.SHAPE_TYPE_A_PROP_NAME); 
		assertTrue("prop exists", this.testInstance.containsProperty(expectedProperty));
		IShapeObjectType shapeTypeB = notationFixture.getNotationSubsystem().getSyntaxService().getShapeObjectType(NotationSubsystemFixture.SHAPE_TYPE_B_ID);
		final IPlainTextPropertyDefinition shapeTypeBPropDefn = shapeTypeB.getDefaultAttributes().getPropertyDefinition(NotationSubsystemFixture.SHAPE_TYPE_B_PROP_NAME);
		assertFalse("no prop exists", this.testInstance.containsProperty(shapeTypeBPropDefn));
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.CanvasAttribute#getCreationSerial()}.
	 */
	@Test
	public void testGetCreationSerial() {
		assertEquals("expected serial", CanvasTestFixture.SHAPE1_ATT_IDX, this.testInstance.getCreationSerial());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.CanvasAttribute#compareTo(ICanvasElementAttribute)}.
	 */
	@Test
	public void testCompareTo() {
		IRootAttribute rootAtt = this.testFixture.getObject(CanvasTestFixture.ROOT_ATT_ID);
		ILabelAttribute labelAttribute = this.testFixture.getObject(CanvasTestFixture.LABEL9_ATT_ID);
		ILinkAttribute linkAttribute = this.testFixture.getObject(CanvasTestFixture.LINK1_ATT_ID);
		assertEquals("same", 0, this.testInstance.compareTo(this.testInstance));
		assertEquals("less than", -1, this.testInstance.compareTo(labelAttribute));
		assertEquals("less than", -1, this.testInstance.compareTo(linkAttribute));
		assertEquals("greater than", 1, this.testInstance.compareTo(rootAtt));
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.CanvasAttribute#getCurrentElement()}.
	 */
	@Test
	public void testGetCurrentElement() {
		assertNull("not set", this.testInstance.getCurrentElement());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.CanvasAttribute#setCurrentElement(uk.ac.ed.inf.graph.compound.ICompoundGraphElement)}.
	 */
	@Test
	public void testSetCurrentElement() {
		ICompoundGraphElement testElement = this.mockery.mock(ICompoundGraphElement.class, "testElement");
		this.mockery.checking(new Expectations(){{
		}});
		this.testInstance.setCurrentElement(testElement);
		assertEquals("expected node", testElement, this.testInstance.getCurrentElement());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.CanvasAttribute#isRemoved()}.
	 */
	@Test
	public void testIsRemoved() {
		assertTrue("is removed", this.testInstance.isRemoved());
	}

}
