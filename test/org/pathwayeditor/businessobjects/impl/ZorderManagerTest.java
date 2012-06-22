/*
Copyright 2009-2011, Court of the University of Edinburgh
*/

package org.pathwayeditor.businessobjects.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
public class ZorderManagerTest {
	private static final int EXPECTED_SIZE = 4;
	private Mockery mockery;
	private IZOrderManager testInstance;
	private ICanvasElementAttribute mockOwner;
	private ICanvasElementAttribute mockAtt1;
	private ICanvasElementAttribute mockAtt2;
	private ICanvasElementAttribute mockAtt3;
	private ICanvasElementAttribute mockAtt4;
	
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		this.mockery = new JUnit4Mockery();
		this.mockOwner = this.mockery.mock(ICanvasElementAttribute.class, "mockOwner");
		this.mockAtt1 = this.mockery.mock(ICanvasElementAttribute.class, "mockAtt1");
		this.mockAtt2 = this.mockery.mock(ICanvasElementAttribute.class, "mockAtt2");
		this.mockAtt3 = this.mockery.mock(ICanvasElementAttribute.class, "mockAtt3");
		this.mockAtt4 = this.mockery.mock(ICanvasElementAttribute.class, "mockAtt4");
		this.testInstance = new ZOrderManager(mockOwner);
		this.testInstance.addToFront(mockAtt1);
		this.testInstance.addToFront(mockAtt2);
		this.testInstance.addToFront(mockAtt3);
		this.testInstance.addToFront(mockAtt4);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		this.mockery = null;
		this.mockAtt1 = null;
		this.mockAtt2 = null;
		this.mockAtt3 = null;
		this.mockAtt4 = null;
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
		assertCorrectOrdering(this.testInstance, this.mockAtt2, this.mockAtt3, this.mockAtt4, this.mockAtt1);
		this.testInstance.toFront(mockAtt3);
		assertCorrectOrdering(this.testInstance, this.mockAtt2, this.mockAtt4, this.mockAtt1, this.mockAtt3);
		this.testInstance.toFront(mockAtt3);
		assertCorrectOrdering(this.testInstance, this.mockAtt2, this.mockAtt4, this.mockAtt1, this.mockAtt3);
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.ZOrderManager#toBack(org.pathwayeditor.businessobjects.drawingprimitives.ICanvasElementAttribute)}.
	 */
	@Test
	public void testToBack() {
		this.testInstance.toBack(mockAtt4);
		assertCorrectOrdering(this.testInstance, this.mockAtt4, this.mockAtt1, this.mockAtt2, this.mockAtt3);
		this.testInstance.toBack(mockAtt2);
		assertCorrectOrdering(this.testInstance, this.mockAtt2, this.mockAtt4, this.mockAtt1, this.mockAtt3);
		this.testInstance.toBack(mockAtt2);
		assertCorrectOrdering(this.testInstance, this.mockAtt2, this.mockAtt4, this.mockAtt1, this.mockAtt3);
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.ZOrderManager#moveForwardOne(org.pathwayeditor.businessobjects.drawingprimitives.ICanvasElementAttribute)}.
	 */
	@Test
	public void testMoveForwardOne() {
		this.testInstance.moveForwardOne(mockAtt2);
		assertCorrectOrdering(this.testInstance, this.mockAtt1, this.mockAtt3, this.mockAtt2, this.mockAtt4);
		this.testInstance.moveForwardOne(mockAtt1);
		assertCorrectOrdering(this.testInstance, this.mockAtt3, this.mockAtt1, this.mockAtt2, this.mockAtt4);
		this.testInstance.moveForwardOne(mockAtt4);
		assertCorrectOrdering(this.testInstance, this.mockAtt3, this.mockAtt1, this.mockAtt2, this.mockAtt4);
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.ZOrderManager#moveBackOne(org.pathwayeditor.businessobjects.drawingprimitives.ICanvasElementAttribute)}.
	 */
	@Test
	public void testMoveBackOne() {
		this.testInstance.moveBackOne(mockAtt2);
		assertCorrectOrdering(this.testInstance, this.mockAtt2, this.mockAtt1, this.mockAtt3, this.mockAtt4);
		this.testInstance.moveBackOne(mockAtt4);
		assertCorrectOrdering(this.testInstance, this.mockAtt2, this.mockAtt1, this.mockAtt4, this.mockAtt3);
		this.testInstance.moveBackOne(mockAtt2);
		assertCorrectOrdering(this.testInstance, this.mockAtt2, this.mockAtt1, this.mockAtt4, this.mockAtt3);
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.ZOrderManager#orderedIterator()}.
	 */
	@Test
	public void testOrderedIterator() {
		assertCorrectOrdering(this.testInstance, this.mockAtt1, this.mockAtt2, this.mockAtt3, this.mockAtt4);
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
		assertCorrectOrdering(this.testInstance, this.mockAtt1, this.mockAtt2, this.mockAtt3, this.mockAtt4, mockAtt5);
		this.testInstance.moveBackOne(mockAtt5);
		assertCorrectOrdering(this.testInstance, this.mockAtt1, this.mockAtt2, this.mockAtt3, mockAtt5, this.mockAtt4);
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.ZOrderManager#canMoveBack(org.pathwayeditor.businessobjects.drawingprimitives.ICanvasElementAttribute)}.
	 */
	@Test
	public void testCanMoveBack() {
		assertFalse("cannot move back", this.testInstance.canMoveBack(mockAtt1));
		assertTrue("can move back", this.testInstance.canMoveBack(mockAtt2));
		assertTrue("can move back", this.testInstance.canMoveBack(mockAtt3));
		assertTrue("can move back", this.testInstance.canMoveBack(mockAtt4));
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.ZOrderManager#canMoveForward(org.pathwayeditor.businessobjects.drawingprimitives.ICanvasElementAttribute)}.
	 */
	@Test
	public void testCanMoveForward() {
		assertTrue("can move forward", this.testInstance.canMoveForward(mockAtt1));
		assertTrue("can move forward", this.testInstance.canMoveForward(mockAtt2));
		assertTrue("can move forward", this.testInstance.canMoveForward(mockAtt3));
		assertFalse("can move forward", this.testInstance.canMoveForward(mockAtt4));
	}

}
