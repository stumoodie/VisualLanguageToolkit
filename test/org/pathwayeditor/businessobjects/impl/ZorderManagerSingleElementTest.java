/*
Copyright 2009-2011, Court of the University of Edinburgh
*/

package org.pathwayeditor.businessobjects.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.Iterator;

import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.pathwayeditor.businessobjects.drawingprimitives.ICanvasElementAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.IZOrderManager;

/**
 * ZorderManagerTest
 *
 * @author Stuart Moodie
 *
 */
@RunWith(JMock.class)
public class ZorderManagerSingleElementTest {
	private static final int EXPECTED_SIZE = 1;
	private Mockery mockery;
	private IZOrderManager testInstance;
	private ICanvasElementAttribute mockOwner;
	private ICanvasElementAttribute mockAtt1;
	
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		this.mockery = new JUnit4Mockery();
		this.mockOwner = this.mockery.mock(ICanvasElementAttribute.class, "mockOwner");
		this.mockAtt1 = this.mockery.mock(ICanvasElementAttribute.class, "mockAtt1");
		this.testInstance = new ZOrderManager(mockOwner);
		this.testInstance.addToFront(mockAtt1);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		this.mockery = null;
		this.mockAtt1 = null;
	}
	
	
	private static void assertCorrectOrdering(IZOrderManager testOrderManager, ICanvasElementAttribute ... orderedAtts){
		assertEquals("Equal numbers", testOrderManager.numAttributes(), orderedAtts.length);
		int i = 0;
		Iterator<ICanvasElementAttribute> iter = testOrderManager.orderedIterator();
		while(iter.hasNext()){
			assertEquals("same element", iter.next(), orderedAtts[i++]);
		}
	}
	

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.ZOrderManager#toFront(org.pathwayeditor.businessobjects.drawingprimitives.ICanvasElementAttribute)}.
	 */
	@Test
	public void testToFront() {
		this.testInstance.toFront(mockAtt1);
		assertCorrectOrdering(this.testInstance, this.mockAtt1);
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.ZOrderManager#toBack(org.pathwayeditor.businessobjects.drawingprimitives.ICanvasElementAttribute)}.
	 */
	@Test
	public void testToBack() {
		this.testInstance.toBack(mockAtt1);
		assertCorrectOrdering(this.testInstance, this.mockAtt1);
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.ZOrderManager#moveForwardOne(org.pathwayeditor.businessobjects.drawingprimitives.ICanvasElementAttribute)}.
	 */
	@Test
	public void testMoveForwardOne() {
		this.testInstance.moveForwardOne(mockAtt1);
		assertCorrectOrdering(this.testInstance, this.mockAtt1);
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.ZOrderManager#moveBackwardOne(org.pathwayeditor.businessobjects.drawingprimitives.ICanvasElementAttribute)}.
	 */
	@Test
	public void testMoveBackOne() {
		this.testInstance.moveBackwardOne(mockAtt1);
		assertCorrectOrdering(this.testInstance, this.mockAtt1);
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.ZOrderManager#orderedIterator()}.
	 */
	@Test
	public void testOrderedIterator() {
		assertCorrectOrdering(this.testInstance, this.mockAtt1);
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.ZOrderManager#getOwningAttribute()}.
	 */
	@Test
	public void testGetOwningAttribute() {
		assertEquals("owning attribute", this.mockOwner, this.testInstance.getOwningAttribute());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.ZOrderManager#addToFront(org.pathwayeditor.businessobjects.drawingprimitives.ICanvasElementAttribute)}.
	 */
	@Test
	public void testAddToFront() {
		ICanvasElementAttribute mockAtt5 = this.mockery.mock(ICanvasElementAttribute.class, "mockAtt5");
		this.testInstance.addToFront(mockAtt5);
		assertEquals("expectedSize", EXPECTED_SIZE+1, this.testInstance.numAttributes());
		assertCorrectOrdering(this.testInstance, this.mockAtt1, mockAtt5);
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.ZOrderManager#canMoveBack(org.pathwayeditor.businessobjects.drawingprimitives.ICanvasElementAttribute)}.
	 */
	@Test
	public void testCanMoveBack() {
		assertFalse("cannot move back", this.testInstance.canMoveBack(mockAtt1));
	}

	@Test
	public void testCanMoveBackNull() {
		assertFalse("cannot move back", this.testInstance.canMoveBack(null));
	}
	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.ZOrderManager#canMoveForward(org.pathwayeditor.businessobjects.drawingprimitives.ICanvasElementAttribute)}.
	 */
	@Test
	public void testCanMoveForward() {
		assertFalse("cannot move forward", this.testInstance.canMoveForward(mockAtt1));
	}
	
	@Test
	public void testCanMoveForwardNull() {
		assertFalse("cannot move forward", this.testInstance.canMoveForward(null));
	}
	@Test
	public void testNumElements(){
		assertEquals("num elements", EXPECTED_SIZE, this.testInstance.numAttributes());
	}

}
