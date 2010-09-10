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
import static org.junit.Assert.fail;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.pathwayeditor.businessobjects.drawingprimitives.ICanvasElementAttributeVisitor;
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
import org.pathwayeditor.figure.geometry.Dimension;
import org.pathwayeditor.figure.geometry.Envelope;
import org.pathwayeditor.figure.geometry.Point;
import org.pathwayeditor.testfixture.CanvasPropertyChangeEventValidator;
import org.pathwayeditor.testfixture.CanvasResizeEventValidator;
import org.pathwayeditor.testfixture.CanvasTestFixture;
import org.pathwayeditor.testfixture.CanvasTranslationEventValidator;
import org.pathwayeditor.testfixture.GeneralIteratorTestUtility;
import org.pathwayeditor.testfixture.NotationSubsystemFixture;

import uk.ac.ed.inf.graph.compound.ICompoundNode;

/**
 * @author smoodie
 *
 */
public class LabelAttributeTest {
	private static final double DOUBLE_EQUIVALENCE_THRESH = 0.0001;
	private static final int EXPECTED_IDX = 100;
	private static final RGB EXPECTED_BG_COLOUR = RGB.RED;
	private static final Envelope EXPECTED_BOUNDS = new Envelope(0.0, 0.0, 1.0, 1.0);
	private static final RGB EXPECTED_FG_COLOUR = RGB.BLUE;
	private static final int EXPECTED_NUM_LISTENERS = 1;
	private static final LineStyle EXPECTED_LINE_STYLE = LineStyle.SOLID;
	private static final double EXPECTED_LINE_WIDTH = 2.3;
	private static final Dimension EXPECTED_MIN_SIZE = new Dimension(20.0, 23.4);
	private Mockery mockery;
	private ILabelAttribute testInstance;
	private NotationSubsystemFixture notationFixture;
	private CanvasTestFixture testFixture;
	private ICanvasAttributeChangeListener testListener;
	private ICanvasAttributeTranslationEvent translationEvent = null;
	private ICanvasAttributeResizedEvent resizedEvent = null;
	private ICanvasAttributePropertyChangeEvent propChangeEvent = null;
	private IAnnotationProperty expectedProp;

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
		IShapeAttribute shapeAtt = this.testFixture.getObject(CanvasTestFixture.SHAPE1_ATT_ID);
		expectedProp = shapeAtt.getProperty(NotationSubsystemFixture.SHAPE_TYPE_A_PROP_NAME);
		final IRootAttribute rootAtt = this.testFixture.getRootAttribute();
		this.mockery.checking(new Expectations(){{
			one(expectedProp).setLabel(with(any(ILabelAttribute.class)));
			one(rootAtt).addCanvasAttribute(with(any(ILabelAttribute.class)));
		}});
		this.testInstance = new LabelAttribute(this.testFixture.getRootAttribute(), EXPECTED_IDX, expectedProp, expectedProp.getDefinition().getLabelDefaults());
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
		this.testFixture = null;
		this.mockery = null;
		this.notationFixture = null;
		this.testInstance = null;
		this.testListener = null;
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.LabelAttribute#getBackgroundColor()}.
	 */
	@Test
	public void testGetBackgroundColor() {
		assertEquals("expected bg colour", EXPECTED_BG_COLOUR, this.testInstance.getBackgroundColor());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.LabelAttribute#setBackgroundColor(org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB)}.
	 */
	@Test
	public void testSetBackgroundColor() {
		RGB newColour = new RGB(1, 2, 3);
		assertFalse("new value different", EXPECTED_BG_COLOUR.equals(newColour));
		this.testInstance.setBackgroundColor(newColour);
		assertEquals("expected fill colour", newColour, this.testInstance.getBackgroundColor());
		CanvasPropertyChangeEventValidator validator = new CanvasPropertyChangeEventValidator(this.testInstance, CanvasAttributePropertyChange.FILL_COLOUR,
				EXPECTED_BG_COLOUR, newColour);
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
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.LabelAttribute#getBounds()}.
	 */
	@Test
	public void testGetBounds() {
		assertEquals("expected bounds", EXPECTED_BOUNDS, this.testInstance.getBounds());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.LabelAttribute#setBounds(org.pathwayeditor.figure.geometry.Envelope)}.
	 */
	@Test
	public void testSetBounds() {
		Envelope newBounds = new Envelope(12.0, 549.0, 3029.0, 34.1);
		this.testInstance.setBounds(newBounds);
		assertFalse("no bounds", EXPECTED_BOUNDS.equals(newBounds));
		assertEquals("new bounds", newBounds, this.testInstance.getBounds());
		CanvasPropertyChangeEventValidator validator = new CanvasPropertyChangeEventValidator(this.testInstance, CanvasAttributePropertyChange.BOUNDS,
				EXPECTED_BOUNDS, newBounds);
		validator.validateEvent(propChangeEvent);
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.LabelAttribute#getForegroundColor()}.
	 */
	@Test
	public void testGetForegroundColor() {
		assertEquals("expected bg colour", EXPECTED_FG_COLOUR, this.testInstance.getForegroundColor());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.LabelAttribute#setForegroundColor(org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB)}.
	 */
	@Test
	public void testSetForegroundColor() {
		RGB newColour = new RGB(1, 2, 3);
		assertFalse("new value different", EXPECTED_FG_COLOUR.equals(newColour));
		this.testInstance.setForegroundColor(newColour);
		assertEquals("expected line colour", newColour, this.testInstance.getForegroundColor());
		CanvasPropertyChangeEventValidator validator = new CanvasPropertyChangeEventValidator(this.testInstance, CanvasAttributePropertyChange.LINE_COLOUR,
				EXPECTED_FG_COLOUR, newColour);
		validator.validateEvent(propChangeEvent);
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.LabelAttribute#setNoBorder(boolean)}.
	 */
	@Test
	public void testSetNoBorder() {
		boolean newFlag = true;
		boolean oldValue = this.testInstance.hasNoBorder();
		assertFalse("new value different", oldValue == newFlag);
		this.testInstance.setNoBorder(newFlag);
		assertEquals("expected value", newFlag, this.testInstance.hasNoBorder());
		CanvasPropertyChangeEventValidator validator = new CanvasPropertyChangeEventValidator(this.testInstance, CanvasAttributePropertyChange.NO_BORDER,
				oldValue, newFlag);
		validator.validateEvent(propChangeEvent);
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.LabelAttribute#hasNoBorder()}.
	 */
	@Test
	public void testHasNoBorder() {
		assertFalse("has border", this.testInstance.hasNoBorder());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.LabelAttribute#setNoFill(boolean)}.
	 */
	@Test
	public void testSetNoFill() {
		boolean newFlag = true;
		boolean oldValue = this.testInstance.hasNoFill();
		assertFalse("new value different", oldValue == newFlag);
		this.testInstance.setNoFill(newFlag);
		assertEquals("expected value", newFlag, this.testInstance.hasNoFill());
		CanvasPropertyChangeEventValidator validator = new CanvasPropertyChangeEventValidator(this.testInstance, CanvasAttributePropertyChange.NO_FILL,
				oldValue, newFlag);
		validator.validateEvent(propChangeEvent);
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.LabelAttribute#hasNoFill()}.
	 */
	@Test
	public void testHasNoFill() {
		assertFalse("has border", this.testInstance.hasNoFill());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.LabelAttribute#getLineStyle()}.
	 */
	@Test
	public void testGetLineStyle() {
		assertEquals("expected line style", EXPECTED_LINE_STYLE, this.testInstance.getLineStyle());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.LabelAttribute#getLineWidth()}.
	 */
	@Test
	public void testGetLineWidth() {
		assertEquals("expected line width", EXPECTED_LINE_WIDTH, this.testInstance.getLineWidth(), DOUBLE_EQUIVALENCE_THRESH);
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.LabelAttribute#setLineStyle(org.pathwayeditor.businessobjects.drawingprimitives.attributes.LineStyle)}.
	 */
	@Test
	public void testSetLineStyle() {
		LineStyle newLineStyle = LineStyle.DASH_DOT_DOT;
		assertFalse("new value different", EXPECTED_LINE_STYLE.equals(newLineStyle));
		this.testInstance.setLineStyle(newLineStyle);
		assertEquals("expected line colour", newLineStyle, this.testInstance.getLineStyle());
		CanvasPropertyChangeEventValidator validator = new CanvasPropertyChangeEventValidator(this.testInstance, CanvasAttributePropertyChange.LINE_STYLE,
				EXPECTED_LINE_STYLE, newLineStyle);
		validator.validateEvent(propChangeEvent);
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.LabelAttribute#setLineWidth(double)}.
	 */
	@Test
	public void testSetLineWidth() {
		double newLineWidth = 847362.0; 
		this.testInstance.setLineWidth(newLineWidth);
		assertFalse("new value different", Math.abs(EXPECTED_LINE_WIDTH - newLineWidth) < DOUBLE_EQUIVALENCE_THRESH);
		assertEquals("new line width", newLineWidth, this.testInstance.getLineWidth(), DOUBLE_EQUIVALENCE_THRESH);
		CanvasPropertyChangeEventValidator validator = new CanvasPropertyChangeEventValidator(this.testInstance, CanvasAttributePropertyChange.LINE_WIDTH,
				EXPECTED_LINE_WIDTH, newLineWidth);
		validator.validateEvent(propChangeEvent);
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.LabelAttribute#getMinimumSize()}.
	 */
	@Test
	public void testGetMinimumSize() {
		assertEquals("min size", EXPECTED_MIN_SIZE, this.testInstance.getMinimumSize());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.LabelAttribute#resize(org.pathwayeditor.figure.geometry.Point, org.pathwayeditor.figure.geometry.Dimension)}.
	 */
	@Test
	public void testResize() {
		Point transln = new Point(12.0, 34.0);
		Dimension sizeDelta = new Dimension(23.9, -34.0);
		Envelope resizeBounds = EXPECTED_BOUNDS.resize(transln, sizeDelta);
		this.testInstance.resize(transln, sizeDelta);
		assertEquals("expected bounds", resizeBounds, this.testInstance.getBounds());
		CanvasResizeEventValidator validator = new CanvasResizeEventValidator(this.testInstance, transln, sizeDelta);
		validator.validateEvent(resizedEvent);
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.LabelAttribute#translate(org.pathwayeditor.figure.geometry.Point)}.
	 */
	@Test
	public void testTranslate() {
		Point transln = new Point(12.0, 34.0);
		Envelope movedBounds = EXPECTED_BOUNDS.translate(transln);
		this.testInstance.translate(transln);
		assertEquals("expected bounds", movedBounds, this.testInstance.getBounds());
		CanvasTranslationEventValidator validator = new CanvasTranslationEventValidator(this.testInstance, transln);
		validator.validateEvent(translationEvent);
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.LabelAttribute#getRootAttribute()}.
	 */
	@Test
	public void testGetRootAttribute() {
		IRootAttribute rootAtt = this.testFixture.getObject(CanvasTestFixture.ROOT_ATT_ID);
		assertEquals("expected root att", rootAtt, this.testInstance.getRootAttribute());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.LabelAttribute#getObjectType()}.
	 */
	@Test
	public void testGetObjectType() {
		assertNull("label object type", this.testInstance.getObjectType());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.LabelAttribute#visit(org.pathwayeditor.businessobjects.drawingprimitives.ICanvasElementAttributeVisitor)}.
	 */
	@Test
	public void testVisit() {
		this.testInstance.visit(new ICanvasElementAttributeVisitor() {
			
			@Override
			public void visitShape(IShapeAttribute attribute) {
				fail("Should not be called");
			}
			
			@Override
			public void visitRoot(IRootAttribute attribute) {
				fail("Should not be called");
			}
			
			@Override
			public void visitLink(ILinkAttribute attribute) {
				fail("Should not be called");
			}
			
			@Override
			public void visitLabel(ILabelAttribute attribute) {
				assertEquals("expected att", testInstance, attribute);
			}
		});
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.LabelAttribute#getProperty()}.
	 */
	@Test
	public void testGetProperty() {
		assertEquals("expected prop", this.expectedProp, this.testInstance.getProperty());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.LabelAttribute#elementAttributeCopyFactory()}.
	 */
	@Test
	public void testElementAttributeCopyFactory() {
		assertNotNull("copy fact set", this.testInstance.elementAttributeCopyFactory());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.LabelAttribute#elementAttributeMoveFactory()}.
	 */
	@Test
	public void testElementAttributeMoveFactory() {
		assertNotNull("move fact set", this.testInstance.elementAttributeMoveFactory());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.CanvasAttribute#getCreationSerial()}.
	 */
	@Test
	public void testGetCreationSerial() {
		assertEquals("expected idx", EXPECTED_IDX, this.testInstance.getCreationSerial());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.CanvasAttribute#compareTo(org.pathwayeditor.businessobjects.drawingprimitives.ICanvasElementAttribute)}.
	 */
	@Test
	public void testCompareTo() {
		assertEquals("equals", 0, this.testInstance.compareTo(this.testInstance));
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
		ICompoundNode testElement = this.mockery.mock(ICompoundNode.class, "testElement");
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
