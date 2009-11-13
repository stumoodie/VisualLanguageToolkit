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
package org.pathwayeditor.businessobjects.hibernate.helpers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.pathwayeditor.businessobjects.drawingprimitives.ICanvas;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB;
import org.pathwayeditor.businessobjects.management.ICanvasPersistenceHandler;
import org.pathwayeditor.businessobjects.repository.IMap;
import org.pathwayeditor.businessobjects.repository.IRepository;
import org.pathwayeditor.bussinessobjects.stubs.notationsubsystem.StubNotationSubSystem;
import org.pathwayeditor.bussinessobjects.stubs.notationsubsystem.StubNotationSubsystemPool;
import org.pathwayeditor.testutils.PojoXlsTester;

/**
 * @author smoodie
 *
 */
@RunWith(JMock.class)
public class HibCanvasPersistenceHandlerTest extends PojoXlsTester {
	private static final String EXPECTED_MAP = "Test Map";
	private static final int EXPECTED_INODE = 4;
	private static final String EXPECTED_REPOSITORY_NAME = "repo name";
	private static final int EXPECTED_NUM_DRAWING_ELEMENTS = 26;
	private static final String SOURCE_DATA_FILE_NAME = "Acceptance Test/DBConsistencyTestSourceData/DBSourceData.xls";
	private static final String EXPECTED_CANVAS_DATA_FILE_NAME = "Acceptance Test/org/pathwayeditor/businessobjects/hibernate/helpers/PostSynchroniseExpectedData.xls";
	private static final RGB EXPECTED_COLOUR = new RGB(100, 100, 100);
	private static final String EXPECTED_NEW_MAP = "Diagram name2";
	private static final int EXPECTED_NEW_INODE = 5;
	private static final String NEW_CANVAS_DATA_FILE_NAME = "Acceptance Test/org/pathwayeditor/businessobjects/hibernate/helpers/NewCanvasExpectedData.xls";
	private static final String DELETED_CANVAS_DATA_FILE_NAME = "Acceptance Test/org/pathwayeditor/businessobjects/hibernate/helpers/DeletedCanvasExpectedData.xls";
	private ICanvasPersistenceHandler testInstance;
	private ICanvasPersistenceHandler testEmptyInstance;
	private IMap mockMap;
	private IMap mockNewMap;
	private IRepository mockRepository;
	private Mockery mockery = new JUnit4Mockery();
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.testutils.PojoTester#getDbUnitDataFilePath()
	 */
	@Override
	protected String getDbUnitDataFilePath() {
		return SOURCE_DATA_FILE_NAME;
	}

	@Override
	protected void additionalSetup(){
		this.mockMap = this.mockery.mock(IMap.class, "mockMap");
		this.mockNewMap = this.mockery.mock(IMap.class, "mockNewMap");
		this.mockRepository = this.mockery.mock(IRepository.class, "mockRepostory");
		
		this.mockery.checking(new Expectations(){{
			allowing(mockRepository).getName(); will(returnValue(EXPECTED_REPOSITORY_NAME));
			
			allowing(mockMap).getName(); will(returnValue(EXPECTED_MAP));
			allowing(mockMap).getINode(); will(returnValue(EXPECTED_INODE));
			allowing(mockMap).getRepository(); will(returnValue(mockRepository));

			allowing(mockNewMap).getName(); will(returnValue(EXPECTED_NEW_MAP));
			allowing(mockNewMap).getINode(); will(returnValue(EXPECTED_NEW_INODE));
			allowing(mockNewMap).getRepository(); will(returnValue(mockRepository));
		}});
		
		this.testInstance = new HibCanvasPersistenceHandler(this.getHibFactory(), new StubNotationSubsystemPool(), this.mockMap);
		this.testEmptyInstance = new HibCanvasPersistenceHandler(this.getHibFactory(), new StubNotationSubsystemPool(), this.mockNewMap);
	}
	
	@Override
	protected void additionalTeardown(){
		this.testInstance = null;
		this.testEmptyInstance = null;
	}
	
	@Test
	public void testLoadedCanvasIsValid() {
		this.testInstance.loadCanvas();
		ICanvas canvas = this.testInstance.getLoadedCanvas();
		assertTrue("modelValid", canvas.getModel().isValid());
	}
	
	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.hibernate.helpers.HibCanvasPersistenceHandler#loadCanvas()}.
	 */
	@Test
	public void testLoadCanvas() {
		this.testInstance.loadCanvas();
		ICanvas expectedCanvas = this.testInstance.getLoadedCanvas();
		assertNotNull("canvas loaded", expectedCanvas);
		assertEquals("expected elements loaded", EXPECTED_NUM_DRAWING_ELEMENTS, expectedCanvas.getModel().numDrawingElements());
		assertTrue("valid model", expectedCanvas.getModel().isValid());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.hibernate.helpers.HibCanvasPersistenceHandler#getLoadedCanvas()}.
	 */
	@Test
	public void testGetLoadedCanvasWhenNotLoaded() {
		assertNull("not loaded", this.testInstance.getLoadedCanvas());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.hibernate.helpers.HibCanvasPersistenceHandler#reset()}.
	 */
	@Test
	public void testReset() {
		this.testInstance.loadCanvas();
		assertNotNull("Canvas set", this.testInstance.getLoadedCanvas());
		this.testInstance.reset();
		assertNull("No canvas set", this.testInstance.getLoadedCanvas());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.hibernate.helpers.HibCanvasPersistenceHandler#synchroniseCanvas()}.
	 * @throws Exception 
	 */
	@Test
	public void testSynchroniseCanvas() throws Exception {
		this.testInstance.loadCanvas();
		ICanvas actualCanvas = this.testInstance.getLoadedCanvas();
		assertEquals("expected background", EXPECTED_COLOUR, actualCanvas.getBackgroundColour());
		actualCanvas.setBackgroundColour(RGB.BLUE);
		this.testInstance.synchroniseCanvas();
		this.compareDatabase(EXPECTED_CANVAS_DATA_FILE_NAME);
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.hibernate.helpers.HibCanvasPersistenceHandler#createCanvas(org.pathwayeditor.businessobjects.notationsubsystem.INotationSubsystem)}.
	 * @throws Exception 
	 */
	@Test
	public void testCreateCanvas() throws Exception {
		this.testEmptyInstance.createCanvas(new StubNotationSubSystem());
		ICanvas actualCanvas = this.testEmptyInstance.getLoadedCanvas();
		assertNotNull("Canvas created", actualCanvas);
		assertEquals("expected inode", EXPECTED_NEW_INODE, actualCanvas.getINode());
		assertEquals("expected repo name", EXPECTED_REPOSITORY_NAME, actualCanvas.getRepositoryName());
		this.testEmptyInstance.synchroniseCanvas();
		this.compareDatabase(SOURCE_DATA_FILE_NAME, NEW_CANVAS_DATA_FILE_NAME);
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.hibernate.helpers.HibCanvasPersistenceHandler#doesCanvasExist()}.
	 */
	@Test
	public void testDoesCanvasExist() {
		assertTrue("canvas exists", this.testInstance.doesCanvasExist());
		assertFalse("canvas missing", this.testEmptyInstance.doesCanvasExist());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.hibernate.helpers.HibCanvasPersistenceHandler#deleteCanvas()}.
	 * @throws Exception 
	 */
	@Test
	public void testDeleteCanvas() throws Exception {
		this.testInstance.loadCanvas();
		this.testInstance.deleteCanvas();
		this.compareDatabase(DELETED_CANVAS_DATA_FILE_NAME);
	}

}
