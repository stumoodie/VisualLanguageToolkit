/*
Copyright 2009-2011, Court of the University of Edinburgh
*/

package org.pathwayeditor.businessobjects.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.pathwayeditor.businessobjects.drawingprimitives.IModel;
import org.pathwayeditor.businessobjects.drawingprimitives.IRootAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.CanvasAttributePropertyChange;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.CanvasPropertyChange;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.ICanvasAttributeChangeListener;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.ICanvasAttributePropertyChangeEvent;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.ICanvasAttributeResizedEvent;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.ICanvasAttributeTranslationEvent;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.ICanvasPropertyChangeEvent;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.ICanvasPropertyChangeListener;
import org.pathwayeditor.figure.geometry.Dimension;
import org.pathwayeditor.figure.geometry.Envelope;
import org.pathwayeditor.figure.geometry.Point;
import org.pathwayeditor.testfixture.CanvasAttributePropertyChangeEventValidator;
import org.pathwayeditor.testfixture.CanvasOnlyPropertyChangeEventValidator;
import org.pathwayeditor.testfixture.CanvasTestFixture;
import org.pathwayeditor.testfixture.GeneralIteratorTestUtility;
import org.pathwayeditor.testfixture.IObjectConstructor;
import org.pathwayeditor.testfixture.NotationSubsystemFixture;

/**
 * @author Stuart Moodie
 *
 */
@RunWith(JMock.class)
public class RootAttributeTest {
	private static final Envelope EXPECTED_BOUNDS = new Envelope(RootAttribute.INITIAL_POS, RootAttribute.INITIAL_SIZE);
	private static final int EXPECTED_NUM_LISTENERS = 1;
	private IRootAttribute testInstance;
	private NotationSubsystemFixture notationFixture;
	private Mockery mockery;
	private ICanvasAttributeChangeListener testListener;
	private ICanvasPropertyChangeListener testCanvasListener;
	private CanvasTestFixture testFixture;
	private ICanvasAttributeTranslationEvent translationEvent = null;
	private ICanvasAttributeResizedEvent resizedEvent = null;
	private ICanvasAttributePropertyChangeEvent propChangeEvent = null;
	private ICanvasPropertyChangeEvent canvasChangeEvent = null;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		this.mockery = new JUnit4Mockery();
		this.notationFixture = new NotationSubsystemFixture(mockery);
		this.notationFixture.buildFixture();
		this.testFixture = new CanvasTestFixture(mockery, "", this.notationFixture.getNotationSubsystem());
		this.testFixture.redefineBuilder(CanvasTestFixture.ROOT_ATT_ID, new IObjectConstructor<IRootAttribute>(){

			@Override
			public IRootAttribute create() {
				final IModel model = testFixture.getObject(CanvasTestFixture.MODEL_ID); 
				mockery.checking(new Expectations(){{
					one(model).addCanvasAttribute(with(any(IRootAttribute.class)));
				}});
				testInstance = new RootAttribute(model, CanvasTestFixture.ROOT_ATT_IDX, notationFixture.getNotationSubsystem().getSyntaxService().getRootObjectType());
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
			public void elementTranslated(ICanvasAttributeTranslationEvent e) {
				translationEvent = e;
			}

			@Override
			public void nodeResized(ICanvasAttributeResizedEvent e) {
				resizedEvent = e;
			}
			
		};
		this.testInstance.addChangeListener(this.testListener);
		this.testCanvasListener = new ICanvasPropertyChangeListener() {

			@Override
			public void propertyChange(ICanvasPropertyChangeEvent e) {
				canvasChangeEvent = e;
			}

		};
		this.testInstance.addCanvasPropertyChangeListener(testCanvasListener);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		this.mockery = null;
		this.testInstance = null;
		this.testListener = null;
		this.testFixture = null;
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.RootAttribute#getObjectType()}.
	 */
	@Test
	public void testGetObjectType() {
		assertEquals("expected ot", this.notationFixture.getNotationSubsystem().getSyntaxService().getRootObjectType(), this.testInstance.getObjectType());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.RootAttribute#getBounds()}.
	 */
	@Test
	public void testGetBounds() {
		assertEquals("expected bounds", EXPECTED_BOUNDS, this.testInstance.getBounds());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.RootAttribute#getBackgroundColour()}.
	 */
	@Test
	public void testGetBackgroundColour() {
		assertEquals("expected bg col", RootAttribute.DEFAULT_BACKGROUND_COLOUR, this.testInstance.getBackgroundColour());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.RootAttribute#setBackgroundColour(org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB)}.
	 */
	@Test
	public void testSetBackgroundColour() {
		RGB newCol = RGB.GREEN;
		assertFalse("not same as default", RootAttribute.DEFAULT_BACKGROUND_COLOUR.equals(newCol));
		this.testInstance.setBackgroundColour(newCol);
		assertEquals("expected bg col", newCol, this.testInstance.getBackgroundColour());
		CanvasOnlyPropertyChangeEventValidator validator = new CanvasOnlyPropertyChangeEventValidator(this.testInstance, CanvasPropertyChange.BACKGROUND_COLOUR,
				RootAttribute.DEFAULT_BACKGROUND_COLOUR, newCol);
		validator.validateEvent(canvasChangeEvent);
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.RootAttribute#setBounds(org.pathwayeditor.figure.geometry.Envelope)}.
	 */
	@Test
	public void testSetBounds() {
		Envelope newBounds = new Envelope(-101, -99, 103.9983, 0.999);
		assertFalse("not default", EXPECTED_BOUNDS.equals(newBounds));
		this.testInstance.setBounds(newBounds);
		assertEquals("bounds set", newBounds, this.testInstance.getBounds());
		CanvasAttributePropertyChangeEventValidator validator = new CanvasAttributePropertyChangeEventValidator(this.testInstance, CanvasAttributePropertyChange.BOUNDS,
				EXPECTED_BOUNDS, newBounds);
		validator.validateEvent(propChangeEvent);
	}

	@Test
	public void testAddChangeListener() {
		this.testInstance.addChangeListener(new ICanvasAttributeChangeListener() {
			
			@Override
			public void propertyChange(ICanvasAttributePropertyChangeEvent e) {
				
			}

			@Override
			public void elementTranslated(ICanvasAttributeTranslationEvent e) {
				
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
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.RootAttribute#resize(org.pathwayeditor.figure.geometry.Point, org.pathwayeditor.figure.geometry.Dimension)}.
	 */
	@Test
	public void testResize() {
		this.testInstance.resize(new Point(25, 10), new Dimension(9, 12));
		assertEquals("no resize", EXPECTED_BOUNDS, this.testInstance.getBounds());
		assertNull("not event set", this.resizedEvent);
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.RootAttribute#translate(org.pathwayeditor.figure.geometry.Point)}.
	 */
	@Test
	public void testTranslate() {
		this.testInstance.translate(new Point(103.0, -38762.0));
		assertEquals("no translation", EXPECTED_BOUNDS, this.testInstance.getBounds());
		assertNull("not event set", this.translationEvent);
	}


//	/**
//	 * Test method for {@link org.pathwayeditor.businessobjects.impl.RootAttribute#getCreationSerialCounter()}.
//	 */
//	@Test
//	public void testGetCreationSerialCounter() {
//		assertEquals("expected counter", this.testCounter, this.testInstance.getCreationSerialCounter());
//	}

	@Test(expected=UnsupportedOperationException.class)
	public void testElementAttributeCopyFactory(){
		this.testInstance.elementAttributeCopyFactory();
	}
	
	@Test(expected=UnsupportedOperationException.class)
	public void testElementAttributeMoveFactory(){
		this.testInstance.elementAttributeMoveFactory();
	}
	
	@Test
	public void testGetCreationSerial(){
		assertEquals("expected creation serial", RootAttribute.ROOT_IDX, this.testInstance.getCreationSerial());
	}


	@Test
	public void testCompareTo() {
		assertEquals("compare to self", 0, this.testInstance.compareTo(testInstance));
	}

	@Test
	public void testIsRemoved(){
		assertTrue("removed", this.testInstance.isRemoved());
	}
	
	@Test
	public void testGetCurrentElement(){
		assertNull("no current element", this.testInstance.getCurrentElement());
	}

}
