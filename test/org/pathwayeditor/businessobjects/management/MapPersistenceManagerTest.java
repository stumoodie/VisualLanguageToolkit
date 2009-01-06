/**
 * 
 */
package org.pathwayeditor.businessobjects.management;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.States;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.pathwayeditor.businessobjects.drawingprimitives.ICanvas;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationSubsystem;
import org.pathwayeditor.businessobjects.repository.IMap;
import org.pathwayeditor.businessobjects.repository.IRepository;

/**
 * @author smoodie
 *
 */
@RunWith(JMock.class)
public class MapPersistenceManagerTest {
	private static final String CANVAS_PRESENT = "canvasPresent";
	private static final String CANVAS_ABSENT = "canvasAbsent";
	private static final String NOT_LOADED = "notLoaded";
	private static final String LOADED = "loaded";

	private static final String EXPECTED_REPO_NAME = "Test Repo";
	private static final int EXPECTED_CANVAS_INODE = 999;
	private static final String EXPECTED_MAP_NAME = "Test Map";

	private Mockery mockery = new JUnit4Mockery();
	
	private ICanvasPersistenceHandler mockCanvasPersistenceHandler;
	private IRepository mockRepository;
	private ICanvas mockCanvas;
	private IMap mockMap;
	private IMapPersistenceManager testInstance;
	private INotationSubsystem mockNotationSubsystem;
	private States canvasExistenceState;
	private boolean expectedStateChangeCalled = false;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		
		this.mockCanvasPersistenceHandler = this.mockery.mock(ICanvasPersistenceHandler.class, "mockCanvasPersistenceHandler");
		this.mockCanvas = this.mockery.mock(ICanvas.class, "mockCanvas");
		this.mockMap = this.mockery.mock(IMap.class, "mockMap");
		this.mockRepository = this.mockery.mock(IRepository.class, "mockRepository");
		this.mockNotationSubsystem = this.mockery.mock(INotationSubsystem.class, "mockNotationSubsystem");
		final States canvasHandlerState = this.mockery.states("canvasHandlerState");
		canvasHandlerState.startsAs(NOT_LOADED);
		canvasExistenceState = this.mockery.states("canvasExistenceState");
		canvasExistenceState.startsAs(CANVAS_PRESENT);
		
		this.mockery.checking(new Expectations() {{
			// The handler is assumed to be newly created, with a canvas that exists, but has not been loaded
			// so the loaded canvas is null.
			allowing(mockCanvasPersistenceHandler).createCanvas(with(any(INotationSubsystem.class))); will(throwException(new IllegalStateException())); when(canvasExistenceState.is(CANVAS_PRESENT));;
			allowing(mockCanvasPersistenceHandler).createCanvas(with(any(INotationSubsystem.class))); when(canvasExistenceState.is(CANVAS_ABSENT)); then(canvasExistenceState.is(CANVAS_PRESENT)); then(canvasHandlerState.is(LOADED));
			allowing(mockCanvasPersistenceHandler).deleteCanvas(); will(throwException(new IllegalStateException())); when(canvasHandlerState.is(NOT_LOADED));
			allowing(mockCanvasPersistenceHandler).deleteCanvas(); will(throwException(new IllegalStateException())); when(canvasExistenceState.is(CANVAS_ABSENT)); when(canvasHandlerState.is(LOADED));
			allowing(mockCanvasPersistenceHandler).deleteCanvas(); then(canvasExistenceState.is(CANVAS_ABSENT)); when(canvasExistenceState.is(CANVAS_PRESENT)); when(canvasHandlerState.is(LOADED));
			allowing(mockCanvasPersistenceHandler).loadCanvas(); then(canvasHandlerState.is(LOADED)); when(canvasExistenceState.is(CANVAS_PRESENT));
			allowing(mockCanvasPersistenceHandler).loadCanvas(); will(throwException(new IllegalStateException())); when(canvasExistenceState.is(CANVAS_ABSENT));
			allowing(mockCanvasPersistenceHandler).reset(); then(canvasHandlerState.is(NOT_LOADED));
			allowing(mockCanvasPersistenceHandler).synchroniseCanvas(); will(throwException(new IllegalStateException())); when(canvasHandlerState.is(NOT_LOADED)); when(canvasExistenceState.is(CANVAS_ABSENT));
			allowing(mockCanvasPersistenceHandler).synchroniseCanvas(); when(canvasHandlerState.is(LOADED)); when(canvasExistenceState.is(CANVAS_PRESENT));
			allowing(mockCanvasPersistenceHandler).getOwningMap(); will(returnValue(mockMap));
			allowing(mockCanvasPersistenceHandler).doesCanvasExist(); will(returnValue(true)); when(canvasExistenceState.is(CANVAS_PRESENT));
			allowing(mockCanvasPersistenceHandler).doesCanvasExist(); will(returnValue(false)); when(canvasExistenceState.is(CANVAS_ABSENT));
			allowing(mockCanvasPersistenceHandler).getLoadedCanvas(); will(returnValue(null)); when(canvasHandlerState.is(NOT_LOADED));
			allowing(mockCanvasPersistenceHandler).getLoadedCanvas(); will(returnValue(null)); when(canvasHandlerState.is(LOADED)); when(canvasExistenceState.is(CANVAS_ABSENT));
			allowing(mockCanvasPersistenceHandler).getLoadedCanvas(); will(returnValue(mockCanvas)); when(canvasHandlerState.is(LOADED)); when(canvasExistenceState.is(CANVAS_PRESENT));
			
			allowing(mockRepository).getName(); will(returnValue(EXPECTED_REPO_NAME));

			// Canvas has a repo name and inode set.
			allowing(mockCanvas).getRepositoryName(); will(returnValue(EXPECTED_REPO_NAME));
			allowing(mockCanvas).getINode(); will(returnValue(EXPECTED_CANVAS_INODE));
			
			// Map has a name, repo and inode set.
			allowing(mockMap).getRepository(); will(returnValue(mockRepository));
			allowing(mockMap).getINode(); will(returnValue(EXPECTED_CANVAS_INODE));
			allowing(mockMap).getName(); will(returnValue(EXPECTED_MAP_NAME));
		}});
		
		this.testInstance = new MapPersistenceManager(mockCanvasPersistenceHandler);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		this.expectedStateChangeCalled = false;
		this.canvasExistenceState = null;
		this.testInstance = null;
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.management.MapPersistenceManager#MapPersistenceManager(org.pathwayeditor.businessobjects.management.ICanvasPersistenceHandler)}.
	 */
	@Test(expected=IllegalArgumentException.class)
	public final void testMapPersistenceManagerNullParam() {
		new MapPersistenceManager(null);
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.management.MapPersistenceManager#close(boolean)}.
	 */
	@Test
	public final void testForceCloseWhenAlreadyClosed() {
		assertFalse("closed", this.testInstance.isOpen());
		this.testInstance.close(true);
		assertFalse("closed", this.testInstance.isOpen());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.management.MapPersistenceManager#close(boolean)}.
	 */
	@Test
	public final void testNonForceCloseWhenAlreadyClosed() {
		assertFalse("closed", this.testInstance.isOpen());
		this.testInstance.close(false);
		assertFalse("closed", this.testInstance.isOpen());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.management.MapPersistenceManager#open()}.
	 * @throws PersistenceManagerException 
	 */
	@Test
	public final void testOpen() throws PersistenceManagerException {
		assertFalse("not open", this.testInstance.isOpen());
		this.testInstance.open();
		assertTrue("is open", this.testInstance.isOpen());
		assertEquals("canvas loaded", this.mockCanvas, this.testInstance.getCanvas());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.management.MapPersistenceManager#getCanvas()}.
	 * @throws PersistenceManagerNotOpenException 
	 */
	@Test(expected=PersistenceManagerNotOpenException.class)
	public final void testGetCanvasWhenClosed() throws PersistenceManagerNotOpenException {
		this.testInstance.getCanvas();
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.management.MapPersistenceManager#getCanvas()}.
	 */
	@Test
	public final void testGetCanvasWhenOpenAndCanvasPresent() throws PersistenceManagerException {
		this.testInstance.open();
		assertEquals("expected canvas", this.mockCanvas, this.testInstance.getCanvas());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.management.MapPersistenceManager#getCanvas()}.
	 */
	@Test(expected=IllegalStateException.class)
	public final void testGetCanvasWhenOpenAndCanvasAbsent() throws PersistenceManagerException {
		this.canvasExistenceState.startsAs(CANVAS_ABSENT);
		this.testInstance.open();
		this.testInstance.getCanvas();
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.management.MapPersistenceManager#getMap()}.
	 */
	@Test
	public final void testGetMap() {
		assertEquals("expected map", this.mockMap, this.testInstance.getMap());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.management.MapPersistenceManager#isOpen()}.
	 */
	@Test
	public final void testIsOpenWhenClosed() {
		assertFalse("not open", this.testInstance.isOpen());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.management.MapPersistenceManager#isOpen()}.
	 * @throws PersistenceManagerException 
	 */
	@Test
	public final void testIsOpenWhenOpen() throws PersistenceManagerException {
		this.testInstance.open();
		assertTrue("open", this.testInstance.isOpen());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.management.MapPersistenceManager#synchronise()}.
	 * @throws PersistenceManagerNotOpenException 
	 */
	@Test(expected=PersistenceManagerNotOpenException.class)
	public final void testSynchroniseWhenClosed() throws PersistenceManagerNotOpenException {
		this.testInstance.synchronise();
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.management.MapPersistenceManager#synchronise()}.
	 * @throws PersistenceManagerException 
	 */
	@Test
	public final void testSynchroniseWhenOpenAndCanvasPresent() throws PersistenceManagerException {
		this.testInstance.open();
		this.testInstance.synchronise();
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.management.MapPersistenceManager#synchronise()}.
	 * @throws PersistenceManagerNotOpenException 
	 */
	@Test(expected=IllegalStateException.class)
	public final void testSynchroniseWhenOpenAndCanvasAbsent() throws PersistenceManagerException {
		this.canvasExistenceState.startsAs(CANVAS_ABSENT);
		this.testInstance.open();
		this.testInstance.synchronise();
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.management.MapPersistenceManager#addListener(org.pathwayeditor.businessobjects.management.IPersistenceManagerStatusListener)}.
	 */
	@Test(expected=IllegalArgumentException.class)
	public final void testAddListenerWithNullListener() {
		this.testInstance.addListener(null);
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.management.MapPersistenceManager#createCanvas(org.pathwayeditor.businessobjects.notationsubsystem.INotationSubsystem)}.
	 * @throws PersistenceManagerNotOpenException 
	 */
	@Test(expected=PersistenceManagerNotOpenException.class)
	public final void testCreateCanvasWhenNotOpen() throws PersistenceManagerNotOpenException {
		this.testInstance.createCanvas(this.mockNotationSubsystem);
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.management.MapPersistenceManager#createCanvas(org.pathwayeditor.businessobjects.notationsubsystem.INotationSubsystem)}.
	 * @throws PersistenceManagerException 
	 */
	@Test(expected=IllegalStateException.class)
	public final void testCreateCanvasWhenOpenAndCanvasPresent() throws PersistenceManagerException {
		this.testInstance.open();
		this.testInstance.createCanvas(this.mockNotationSubsystem);
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.management.MapPersistenceManager#createCanvas(org.pathwayeditor.businessobjects.notationsubsystem.INotationSubsystem)}.
	 * @throws PersistenceManagerException 
	 */
	@Test
	public final void testCreateCanvasWhenOpenAndCanvasAbsent() throws PersistenceManagerException {
		this.canvasExistenceState.startsAs(CANVAS_ABSENT);
		this.testInstance.open();
		this.testInstance.createCanvas(this.mockNotationSubsystem);
		assertTrue("open", this.testInstance.isOpen());
		assertEquals("expected canvas", this.mockCanvas, this.testInstance.getCanvas());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.management.MapPersistenceManager#doesCanvasExist()}.
	 * @throws PersistenceManagerException 
	 */
	@Test
	public final void testDoesCanvasExistWhenOpenAndCanvasPresent() throws PersistenceManagerException {
		this.testInstance.open();
		assertTrue("canvasExists", this.testInstance.doesCanvasExist());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.management.MapPersistenceManager#doesCanvasExist()}.
	 * @throws PersistenceManagerException 
	 */
	@Test
	public final void testDoesCanvasExistWhenOpenAndCanvasAbsent() throws PersistenceManagerException {
		this.canvasExistenceState.startsAs(CANVAS_ABSENT);
		this.testInstance.open();
		assertFalse("canvas does not Exist", this.testInstance.doesCanvasExist());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.management.MapPersistenceManager#doesCanvasExist()}.
	 * @throws PersistenceManagerNotOpenException 
	 */
	@Test(expected=PersistenceManagerNotOpenException.class)
	public final void testDoesCanvasExistWhenClosed() throws PersistenceManagerNotOpenException {
		this.testInstance.doesCanvasExist();
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.management.MapPersistenceManager#deleteCanvas()}.
	 * @throws PersistenceManagerNotOpenException 
	 */
	@Test(expected=PersistenceManagerNotOpenException.class)
	public final void testDeleteCanvasWhenClosed() throws PersistenceManagerNotOpenException {
		this.testInstance.deleteCanvas();
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.management.MapPersistenceManager#deleteCanvas()}.
	 * @throws PersistenceManagerNotOpenException 
	 */
	@Test
	public final void testDeleteCanvasWhenOpenAndPresent() throws PersistenceManagerException {
		this.testInstance.open();
		this.testInstance.deleteCanvas();
		assertFalse("canvas deleted", this.testInstance.doesCanvasExist());
		assertTrue("manager open", this.testInstance.isOpen());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.management.MapPersistenceManager#deleteCanvas()}.
	 * @throws PersistenceManagerNotOpenException 
	 */
	@Test(expected=IllegalStateException.class)
	public final void testDeleteCanvasWhenOpenAndAbsent() throws PersistenceManagerException {
		this.canvasExistenceState.startsAs(CANVAS_ABSENT);
		this.testInstance.open();
		this.testInstance.deleteCanvas();
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.management.MapPersistenceManager#listenerIterator()}.
	 */
	@Test
	public final void testListenerIterator() {
		assertFalse("no listeners", this.testInstance.listenerIterator().hasNext());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.management.MapPersistenceManager#removeListener(org.pathwayeditor.businessobjects.management.IPersistenceManagerStatusListener)}.
	 */
	@Test(expected=IllegalArgumentException.class)
	public final void testRemoveListenerWithNullParameter() {
		this.testInstance.removeListener(null);
	}

	@Test
	public final void testListenerAdditionAndRemoval() {
		final IPersistenceManagerStatusListener expectedListener = new IPersistenceManagerStatusListener() {
			public boolean requestCancelStateChange(StateChange stateChange, IPersistenceManager changeManager) {
				return false;
			}
			public void stateChanged(StateChange stateChange, IPersistenceManager changedManager) {	}
		};
		this.testInstance.addListener(expectedListener);
		Iterator<IPersistenceManagerStatusListener> iter = this.testInstance.listenerIterator();
		assertTrue("contains listener", iter.hasNext());
		assertEquals("contains expected", expectedListener, iter.next());
		this.testInstance.removeListener(expectedListener);
		assertFalse("listener removed", this.testInstance.listenerIterator().hasNext());
	}

	@Test
	public final void testListeningForOpenStateChange() throws PersistenceManagerException {
		this.expectedStateChangeCalled = false;
		this.testInstance.addListener(new IPersistenceManagerStatusListener() {

			public boolean requestCancelStateChange(StateChange stateChange, IPersistenceManager changeManager) {
				return false;
			}

			public void stateChanged(StateChange stateChange, IPersistenceManager changedManager) {
				if(stateChange == StateChange.OPENED) {
					expectedStateChangeCalled = true;
				}
			}
			
		});
		this.testInstance.open();
		assertTrue("status change detected", expectedStateChangeCalled);
	}
	
	@Test
	public final void testListeningForForcedCloseStateChange() throws PersistenceManagerException {
		this.expectedStateChangeCalled = false;
		this.testInstance.addListener(new IPersistenceManagerStatusListener() {

			public boolean requestCancelStateChange(StateChange stateChange, IPersistenceManager changeManager) {
				return false;
			}

			public void stateChanged(StateChange stateChange, IPersistenceManager changedManager) {
				if(stateChange == StateChange.CLOSED) {
					expectedStateChangeCalled = true;
				}
			}
			
		});
		this.testInstance.open();
		this.testInstance.close(true);
		assertTrue("status change detected", expectedStateChangeCalled);
	}
	
	@Test
	public final void testListeningForForcedCloseStateChangeWhenCancelRequested() throws PersistenceManagerException {
		this.expectedStateChangeCalled = false;
		this.testInstance.addListener(new IPersistenceManagerStatusListener() {

			public boolean requestCancelStateChange(StateChange stateChange, IPersistenceManager changeManager) {
				return true;
			}

			public void stateChanged(StateChange stateChange, IPersistenceManager changedManager) {
				if(stateChange == StateChange.CLOSED) {
					expectedStateChangeCalled = true;
				}
			}
			
		});
		this.testInstance.open();
		this.testInstance.close(true);
		assertTrue("status change detected", expectedStateChangeCalled);
	}
	
	@Test
	public final void testListeningForCloseStateChangeWhenCancelRequested() throws PersistenceManagerException {
		this.expectedStateChangeCalled = false;
		this.testInstance.addListener(new IPersistenceManagerStatusListener() {

			public boolean requestCancelStateChange(StateChange stateChange, IPersistenceManager changeManager) {
				return true;
			}

			public void stateChanged(StateChange stateChange, IPersistenceManager changedManager) {
				if(stateChange == StateChange.CLOSED) {
					expectedStateChangeCalled = true;
				}
			}
			
		});
		this.testInstance.open();
		this.testInstance.close(false);
		assertFalse("status change not detected", expectedStateChangeCalled);
	}
	
	@Test
	public final void testForcedCloseWhenCancelRequested() throws PersistenceManagerException {
		this.expectedStateChangeCalled = false;
		this.testInstance.addListener(new IPersistenceManagerStatusListener() {

			public boolean requestCancelStateChange(StateChange stateChange, IPersistenceManager changeManager) {
				return true;
			}

			public void stateChanged(StateChange stateChange, IPersistenceManager changedManager) {
				if(stateChange == StateChange.CLOSED) {
					expectedStateChangeCalled = true;
				}
			}
			
		});
		this.testInstance.open();
		this.testInstance.close(true);
		assertTrue("status change detected", expectedStateChangeCalled);
		assertFalse("still open", this.testInstance.isOpen());
	}
	
	@Test
	public final void testNonForcedCloseWhenCancelRequested() throws PersistenceManagerException {
		this.expectedStateChangeCalled = false;
		this.testInstance.addListener(new IPersistenceManagerStatusListener() {

			public boolean requestCancelStateChange(StateChange stateChange, IPersistenceManager changeManager) {
				return true;
			}

			public void stateChanged(StateChange stateChange, IPersistenceManager changedManager) {
				if(stateChange == StateChange.CLOSED) {
					expectedStateChangeCalled = true;
				}
			}
			
		});
		this.testInstance.open();
		this.testInstance.close(false);
		assertFalse("status change not detected", expectedStateChangeCalled);
		assertTrue("still open", this.testInstance.isOpen());
	}
}
