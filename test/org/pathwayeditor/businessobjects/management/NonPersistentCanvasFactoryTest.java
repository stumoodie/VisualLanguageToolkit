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
package org.pathwayeditor.businessobjects.management;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.pathwayeditor.businessobjects.drawingprimitives.ICanvas;
import org.pathwayeditor.businessobjects.hibernate.helpers.IHibNotationFactory;
import org.pathwayeditor.businessobjects.hibernate.helpers.fallbacknotation.FallbackNotationSubsystem;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibNotation;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationSubsystem;
import org.pathwayeditor.bussinessobjects.stubs.StubHibNotationFactory;
import org.pathwayeditor.bussinessobjects.stubs.notationsubsystem.StubNotationSubSystem;

/**
 * @author smoodie
 *
 */
public class NonPersistentCanvasFactoryTest {
	private static final String TEST_CANVAS_NAME = "Test Canvas";
	private static final String INVALID_CANVAS_NAME = "   ";
	private NonPersistentCanvasFactory testInstance;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		// creates a new instance of the singleton
		NonPersistentCanvasFactory.newInstance();
		this.testInstance = NonPersistentCanvasFactory.getInstance();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		this.testInstance = null;
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.management.NonPersistentCanvasFactory#createNewCanvas()}.
	 */
	@Test
	public final void testCreateNewCanvas() {
		this.testInstance.setCanvasName(TEST_CANVAS_NAME);
		INotationSubsystem expectedNotationSubsystem = new StubNotationSubSystem(); 
		this.testInstance.setNotationSubsystem(expectedNotationSubsystem);
		final ICanvas newCanvas = this.testInstance.createNewCanvas();
		assertNotNull("canvas exists", newCanvas);
		assertEquals("expected repo name", NonPersistentCanvasFactory.DEFAULT_REPO_NAME, newCanvas.getRepositoryName());
		assertEquals("expected name", TEST_CANVAS_NAME, newCanvas.getName());
		assertEquals("expected notation subsystem", expectedNotationSubsystem, newCanvas.getNotationSubsystem());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.management.NonPersistentCanvasFactory#createNewCanvas()}.
	 */
	@Test
	public final void testCreateNewCanvasInodeCreation() {
		this.testInstance.setCanvasName(TEST_CANVAS_NAME);
		INotationSubsystem expectedNotationSubsystem = new StubNotationSubSystem(); 
		this.testInstance.setNotationSubsystem(expectedNotationSubsystem);
		final ICanvas newCanvas = this.testInstance.createNewCanvas();
		final int firstInode = newCanvas.getINode();
		final ICanvas nextNewCanvas = this.testInstance.createNewCanvas();
		final int nextInode = nextNewCanvas.getINode();
		assertEquals("inode incremeneted", firstInode+1, nextInode);
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.management.NonPersistentCanvasFactory#createNewCanvas()}.
	 */
	@Test(expected=IllegalStateException.class)
	public final void testCreateNewCanvasWithNoCanvasSet() {
		INotationSubsystem expectedNotationSubsystem = new StubNotationSubSystem(); 
		this.testInstance.setNotationSubsystem(expectedNotationSubsystem);
		this.testInstance.createNewCanvas();
	}

	@Test(expected=IllegalStateException.class)
	public final void testCreateNewCanvasWithNoNotSubSysSet() {
		this.testInstance.setCanvasName(TEST_CANVAS_NAME);
		this.testInstance.createNewCanvas();
	}

	@Test(expected=IllegalArgumentException.class)
	public final void testSetInvalidCanvasName() {
		this.testInstance.setCanvasName(INVALID_CANVAS_NAME);
	}

	@Test(expected=IllegalArgumentException.class)
	public final void testSetInvalidNotationSubsystem() {
		this.testInstance.setNotationSubsystem(null);
	}

	@Test(expected=IllegalArgumentException.class)
	public final void testSetFallbackNotationSubsystem() {
		IHibNotationFactory fact = new StubHibNotationFactory(); 
		HibNotation hibNotation = fact.getNotation();  
		this.testInstance.setNotationSubsystem(new FallbackNotationSubsystem(hibNotation));
	}

}
