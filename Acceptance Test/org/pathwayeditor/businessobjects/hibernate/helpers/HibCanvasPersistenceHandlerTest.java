/**
 * 
 */
package org.pathwayeditor.businessobjects.hibernate.helpers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.pathwayeditor.businessobjects.drawingprimitives.ICanvas;
import org.pathwayeditor.businessobjects.management.ICanvasPersistenceHandler;
import org.pathwayeditor.businessobjects.repository.IMap;
import org.pathwayeditor.businessobjects.repository.IRepository;
import org.pathwayeditor.bussinessobjects.stubs.notationsubsystem.StubNotationSubsystemPool;
import org.pathwayeditor.testutils.PojoTester;

/**
 * @author smoodie
 *
 */
@RunWith(JMock.class)
public class HibCanvasPersistenceHandlerTest extends PojoTester {
	private static final String EXPECTED_MAP = "Test Map";
	private static final int EXPECTED_INODE = 4;
	private static final String EXPECTED_REPOSITORY_NAME = "repo name";
	private static final int EXPECTED_NUM_DRAWING_ELEMENTS = 26;
	private static final String SOURCE_DATA_FILE_NAME = "Acceptance Test/DBConsistencyTestSourceData/DBSourceData.xml";
	private ICanvasPersistenceHandler testInstance;
	private IMap mockMap;
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
		this.mockRepository = this.mockery.mock(IRepository.class, "mockRepostory");
		
		this.mockery.checking(new Expectations(){{
			allowing(mockRepository).getName(); will(returnValue(EXPECTED_REPOSITORY_NAME));
			
			allowing(mockMap).getName(); will(returnValue(EXPECTED_MAP));
			allowing(mockMap).getINode(); will(returnValue(EXPECTED_INODE));
			allowing(mockMap).getRepository(); will(returnValue(mockRepository));
		}});
		
		this.testInstance = new HibCanvasPersistenceHandler(this.getHibFactory(), new StubNotationSubsystemPool(), this.mockMap);
	}
	
	@Override
	protected void additionalTeardown(){
		this.testInstance = null;
	}
	
	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.hibernate.helpers.HibCanvasPersistenceHandler#getOwningMap()}.
	 */
	@Test
	public void testGetOwningMap() {
		assertEquals("expected map", this.mockMap, this.testInstance.getOwningMap());
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
	 */
	@Ignore @Test
	public void testSynchroniseCanvas() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.hibernate.helpers.HibCanvasPersistenceHandler#createCanvas(org.pathwayeditor.businessobjects.notationsubsystem.INotationSubsystem)}.
	 */
	@Ignore @Test
	public void testCreateCanvas() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.hibernate.helpers.HibCanvasPersistenceHandler#doesCanvasExist()}.
	 */
	@Ignore @Test
	public void testDoesCanvasExist() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.hibernate.helpers.HibCanvasPersistenceHandler#deleteCanvas()}.
	 */
	@Ignore @Test
	public void testDeleteCanvas() {
		fail("Not yet implemented");
	}

}
