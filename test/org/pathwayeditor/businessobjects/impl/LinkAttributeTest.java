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
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
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
import org.pathwayeditor.businessobjects.notationsubsystem.INotationSyntaxService;
import org.pathwayeditor.businessobjects.typedefn.ILinkObjectType;
import org.pathwayeditor.testfixture.CanvasPropertyChangeEventValidator;
import org.pathwayeditor.testfixture.CanvasTestFixture;
import org.pathwayeditor.testfixture.GeneralIteratorTestUtility;
import org.pathwayeditor.testfixture.NotationSubsystemFixture;

import uk.ac.ed.inf.graph.compound.ICompoundGraphElement;

/**
 * @author smoodie
 *
 */
@RunWith(JMock.class)
public class LinkAttributeTest {
	private static final int EXPECTED_IDX = 100;
	private static final double DIFF_THRESHOLD = 0.0001;
	private static final RGB EXPECTED_LINE_COLOUR = RGB.GREEN;
	private static final LineStyle EXPECTED_LINE_STYLE = LineStyle.DASH_DOT;
	private static final double EXPECTED_LINE_WIDTH = 3.9;
	private static final int EXPECTED_NUM_LISTENERS = 1;
	private Mockery mockery;
	private ILinkAttribute testInstance;
	private NotationSubsystemFixture notationFixture;
	private CanvasTestFixture testFixture;
	private ICanvasAttributeChangeListener testListener;
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
		this.testFixture.buildFixture();
		INotationSyntaxService syntaxService = this.notationFixture.getNotationSubsystem().getSyntaxService();
		this.testInstance = new LinkAttribute(this.testFixture.getRootAttribute(), EXPECTED_IDX, syntaxService.getLinkObjectType(CanvasTestFixture.LINK1_ATT_OT));
		this.testListener = new ICanvasAttributeChangeListener() {

			@Override
			public void propertyChange(ICanvasAttributePropertyChangeEvent e) {
				propChangeEvent = e;
			}

			@Override
			public void nodeTranslated(ICanvasAttributeTranslationEvent e) {
			}

			@Override
			public void nodeResized(ICanvasAttributeResizedEvent e) {
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
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.LinkAttribute#getObjectType()}.
	 */
	@Test
	public void testGetObjectType() {
		INotationSyntaxService syntaxService = this.notationFixture.getNotationSubsystem().getSyntaxService();
		ILinkObjectType expectedObjectType = syntaxService.getLinkObjectType(CanvasTestFixture.LINK1_ATT_OT);
		assertEquals("object type", expectedObjectType, this.testInstance.getObjectType());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.LinkAttribute#getLineStyle()}.
	 */
	@Test
	public void testGetLineStyle() {
		assertEquals("expected val", EXPECTED_LINE_STYLE, this.testInstance.getLineStyle());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.LinkAttribute#setLineStyle(org.pathwayeditor.businessobjects.drawingprimitives.attributes.LineStyle)}.
	 */
	@Test
	public void testSetLineStyle() {
		LineStyle expectedVal = LineStyle.DASH_DOT_DOT;
		assertFalse("not same colour", expectedVal.equals(EXPECTED_LINE_STYLE));
		this.testInstance.setLineStyle(expectedVal);
		assertEquals("expected val", expectedVal, this.testInstance.getLineStyle());
		CanvasPropertyChangeEventValidator validator = new CanvasPropertyChangeEventValidator(this.testInstance, CanvasAttributePropertyChange.LINE_STYLE,
				EXPECTED_LINE_STYLE, expectedVal);
		validator.validateEvent(propChangeEvent);
	}
	
	public void testGetbendPointContainer(){
		assertNotNull("exists", this.testInstance.getBendPointContainer());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.LinkAttribute#getLineWidth()}.
	 */
	@Test
	public void testGetLineWidth() {
		assertEquals("expected val", EXPECTED_LINE_WIDTH, this.testInstance.getLineWidth(), DIFF_THRESHOLD);
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.LinkAttribute#setLineWidth(double)}.
	 */
	@Test
	public void testSetLineWidth() {
		double expectedValue = 1.9382;
		this.testInstance.setLineWidth(expectedValue);
		assertFalse("diff new val", EXPECTED_LINE_WIDTH == expectedValue);
		assertEquals("expected val", expectedValue, this.testInstance.getLineWidth(), DIFF_THRESHOLD);
		CanvasPropertyChangeEventValidator validator = new CanvasPropertyChangeEventValidator(this.testInstance, CanvasAttributePropertyChange.LINE_WIDTH,
				EXPECTED_LINE_WIDTH, expectedValue);
		validator.validateEvent(propChangeEvent);
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.LinkAttribute#getLineColour()}.
	 */
	@Test
	public void testGetLineColour() {
		assertEquals("line colour", EXPECTED_LINE_COLOUR, this.testInstance.getLineColour());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.LinkAttribute#setLineColour(org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB)}.
	 */
	@Test
	public void testSetLineColour() {
		RGB expectedLineColour = RGB.WHITE;
		assertFalse("not same colour", expectedLineColour.equals(EXPECTED_LINE_COLOUR));
		this.testInstance.setLineColour(expectedLineColour);
		assertEquals("line colour", expectedLineColour, this.testInstance.getLineColour());
		CanvasPropertyChangeEventValidator validator = new CanvasPropertyChangeEventValidator(this.testInstance, CanvasAttributePropertyChange.LINE_COLOUR,
				EXPECTED_LINE_COLOUR, expectedLineColour);
		validator.validateEvent(propChangeEvent);
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.LinkAttribute#getSourceTerminus()}.
	 */
	@Test
	public void testGetSourceTerminus() {
		assertNotNull("src term exists", this.testInstance.getSourceTerminus());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.LinkAttribute#getTargetTerminus()}.
	 */
	@Test
	public void testGetTargetTerminus() {
		assertNotNull("tgt term exists", this.testInstance.getTargetTerminus());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.LinkAttribute#addChangeListener(org.pathwayeditor.businessobjects.drawingprimitives.listeners.ICanvasAttributeChangeListener)}.
	 */
	@Test
	public void testAddChangeListenerICanvasAttributePropertyChangeListener() {
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

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.LinkAttribute#getChangeListeners()}.
	 */
	@Test
	public void testGetChangeListeners() {
		GeneralIteratorTestUtility<ICanvasAttributeChangeListener> testIter = new GeneralIteratorTestUtility<ICanvasAttributeChangeListener>(this.testListener);
		testIter.testIterator(this.testInstance.getChangeListeners().iterator());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.LinkAttribute#removeChangeListener(org.pathwayeditor.businessobjects.drawingprimitives.listeners.ICanvasAttributeChangeListener)}.
	 */
	@Test
	public void testRemoveChangeListenerICanvasAttributePropertyChangeListener() {
		this.testInstance.removeChangeListener(testListener);
		assertEquals("expected num listeners", EXPECTED_NUM_LISTENERS-1, this.testInstance.getChangeListeners().size());
		GeneralIteratorTestUtility<ICanvasAttributeChangeListener> testIter = new GeneralIteratorTestUtility<ICanvasAttributeChangeListener>();
		testIter.testIterator(this.testInstance.getChangeListeners().iterator());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.LinkAttribute#getRootAttribute()}.
	 */
	@Test
	public void testGetRootAttribute() {
		assertEquals("root att", this.testFixture.getRootAttribute(), this.testInstance.getRootAttribute());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.LinkAttribute#visit(org.pathwayeditor.businessobjects.drawingprimitives.ICanvasElementAttributeVisitor)}.
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
				assertEquals("expected att", testInstance, attribute);
			}
			
			@Override
			public void visitLabel(ILabelAttribute attribute) {
				fail("Should not be called");
			}
		});
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
		assertEquals("expected comparison", 0, this.testInstance.compareTo(testInstance));
		IShapeAttribute testShapeAtt = this.testFixture.getObject(CanvasTestFixture.SHAPE1_ATT_ID);
		assertEquals("expected comparison", 1, this.testInstance.compareTo(testShapeAtt));
		ILinkAttribute testLinkAtt = this.testFixture.getObject(CanvasTestFixture.LINK1_ATT_ID);
		assertEquals("expected comparison", 1, this.testInstance.compareTo(testLinkAtt));
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
		ICompoundGraphElement expectedElement = this.mockery.mock(ICompoundGraphElement.class, "expectedElement");
		this.testInstance.setCurrentElement(expectedElement);
		assertEquals("expected element", expectedElement, this.testInstance.getCurrentElement());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.CanvasAttribute#isRemoved()}.
	 */
	@Test
	public void testIsRemoved() {
		assertTrue("removed", this.testInstance.isRemoved());
	}

	@Test
	public void testIsRemovedWhenCurrElementPresent() {
		final ICompoundGraphElement expectedElement = this.mockery.mock(ICompoundGraphElement.class, "expectedElement");
		this.testInstance.setCurrentElement(expectedElement);
		this.mockery.checking(new Expectations(){{
			allowing(expectedElement).isRemoved(); will(returnValue(false));
		}});
		assertFalse("not removed", this.testInstance.isRemoved());
	}

	@Test
	public void testIsRemovedWhenCurrElementRemoved() {
		final ICompoundGraphElement expectedElement = this.mockery.mock(ICompoundGraphElement.class, "expectedElement");
		this.testInstance.setCurrentElement(expectedElement);
		this.mockery.checking(new Expectations(){{
			allowing(expectedElement).isRemoved(); will(returnValue(true));
		}});
		assertTrue("removed", this.testInstance.isRemoved());
	}
}
