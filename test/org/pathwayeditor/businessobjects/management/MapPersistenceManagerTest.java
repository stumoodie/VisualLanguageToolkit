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
import org.pathwayeditor.businessobjects.management.impl.MapPersistenceManager;
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
	 * Test method for {@link org.pathwayeditor.businessobjects.management.impl.MapPersistenceManager#MapPersistenceManager(org.pathwayeditor.businessobjects.management.ICanvasPersistenceHandler)}.
	 */
	@Test(expected=IllegalArgumentException.class)
	public final void testMapPersistenceManagerNullParam() {
		new MapPersistenceManager(null);
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.management.impl.MapPersistenceManager#close(boolean)}.
	 */
	@Test
	public final void testForceCloseWhenAlreadyClosed() {
		assertFalse("closed", this.testInstance.isOpen());
		this.testInstance.close(true);
		assertFalse("closed", this.testInstance.isOpen());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.management.impl.MapPersistenceManager#close(boolean)}.
	 */
	@Test
	public final void testNonForceCloseWhenAlreadyClosed() {
		assertFalse("closed", this.testInstance.isOpen());
		this.testInstance.close(false);
		assertFalse("closed", this.testInstance.isOpen());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.management.impl.MapPersistenceManager#open()}.
	 */
	@Test
	public final void testOpen() {
		assertFalse("not open", this.testInstance.isOpen());
		this.testInstance.open();
		assertTrue("is open", this.testInstance.isOpen());
		assertEquals("canvas loaded", this.mockCanvas, this.testInstance.getCanvas());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.management.impl.MapPersistenceManager#getCanvas()}.
	 */
	@Test(expected=IllegalStateException.class)
	public final void testGetCanvasWhenClosed() {
		this.testInstance.getCanvas();
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.management.impl.MapPersistenceManager#getCanvas()}.
	 */
	@Test
	public final void testGetCanvasWhenOpenAndCanvasPresent() {
		this.testInstance.open();
		assertEquals("expected canvas", this.mockCanvas, this.testInstance.getCanvas());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.management.impl.MapPersistenceManager#getCanvas()}.
	 */
	@Test(expected=IllegalStateException.class)
	public final void testGetCanvasWhenOpenAndCanvasAbsent() {
		this.canvasExistenceState.startsAs(CANVAS_ABSENT);
		this.testInstance.open();
		this.testInstance.getCanvas();
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.management.impl.MapPersistenceManager#getMap()}.
	 */
	@Test
	public final void testGetMap() {
		assertEquals("expected map", this.mockMap, this.testInstance.getMap());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.management.impl.MapPersistenceManager#isOpen()}.
	 */
	@Test
	public final void testIsOpenWhenClosed() {
		assertFalse("not open", this.testInstance.isOpen());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.management.impl.MapPersistenceManager#isOpen()}.
	 */
	@Test
	public final void testIsOpenWhenOpen() {
		this.testInstance.open();
		assertTrue("open", this.testInstance.isOpen());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.management.impl.MapPersistenceManager#synchronise()}.
	 */
	@Test(expected=IllegalStateException.class)
	public final void testSynchroniseWhenClosed() {
		this.testInstance.synchronise();
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.management.impl.MapPersistenceManager#synchronise()}.
	 */
	@Test
	public final void testSynchroniseWhenOpenAndCanvasPresent() {
		this.testInstance.open();
		this.testInstance.synchronise();
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.management.impl.MapPersistenceManager#synchronise()}.
	 */
	@Test(expected=IllegalStateException.class)
	public final void testSynchroniseWhenOpenAndCanvasAbsent() {
		this.canvasExistenceState.startsAs(CANVAS_ABSENT);
		this.testInstance.open();
		this.testInstance.synchronise();
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.management.impl.MapPersistenceManager#addListener(org.pathwayeditor.businessobjects.management.IPersistenceManagerStatusListener)}.
	 */
	@Test(expected=IllegalArgumentException.class)
	public final void testAddListenerWithNullListener() {
		this.testInstance.addListener(null);
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.management.impl.MapPersistenceManager#createCanvas(org.pathwayeditor.businessobjects.notationsubsystem.INotationSubsystem)}.
	 */
	@Test(expected=IllegalStateException.class)
	public final void testCreateCanvasWhenNotOpen() {
		this.testInstance.createCanvas(this.mockNotationSubsystem);
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.management.impl.MapPersistenceManager#createCanvas(org.pathwayeditor.businessobjects.notationsubsystem.INotationSubsystem)}.
	 */
	@Test(expected=IllegalStateException.class)
	public final void testCreateCanvasWhenOpenAndCanvasPresent() {
		this.testInstance.open();
		this.testInstance.createCanvas(this.mockNotationSubsystem);
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.management.impl.MapPersistenceManager#createCanvas(org.pathwayeditor.businessobjects.notationsubsystem.INotationSubsystem)}.
	 */
	@Test
	public final void testCreateCanvasWhenOpenAndCanvasAbsent() {
		this.canvasExistenceState.startsAs(CANVAS_ABSENT);
		this.testInstance.open();
		this.testInstance.createCanvas(this.mockNotationSubsystem);
		assertTrue("open", this.testInstance.isOpen());
		assertEquals("expected canvas", this.mockCanvas, this.testInstance.getCanvas());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.management.impl.MapPersistenceManager#doesCanvasExist()}.
	 */
	@Test
	public final void testDoesCanvasExistWhenOpenAndCanvasPresent() {
		this.testInstance.open();
		assertTrue("canvasExists", this.testInstance.doesCanvasExist());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.management.impl.MapPersistenceManager#doesCanvasExist()}.
	 */
	@Test
	public final void testDoesCanvasExistWhenOpenAndCanvasAbsent() {
		this.canvasExistenceState.startsAs(CANVAS_ABSENT);
		this.testInstance.open();
		assertFalse("canvas does not Exist", this.testInstance.doesCanvasExist());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.management.impl.MapPersistenceManager#doesCanvasExist()}.
	 */
	@Test(expected=IllegalStateException.class)
	public final void testDoesCanvasExistWhenClosed() {
		this.testInstance.doesCanvasExist();
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.management.impl.MapPersistenceManager#deleteCanvas()}.
	 */
	@Test(expected=IllegalStateException.class)
	public final void testDeleteCanvasWhenClosed() {
		this.testInstance.deleteCanvas();
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.management.impl.MapPersistenceManager#deleteCanvas()}.
	 */
	@Test
	public final void testDeleteCanvasWhenOpenAndPresent() {
		this.testInstance.open();
		this.testInstance.deleteCanvas();
		assertFalse("canvas deleted", this.testInstance.doesCanvasExist());
		assertTrue("manager open", this.testInstance.isOpen());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.management.impl.MapPersistenceManager#deleteCanvas()}.
	 */
	@Test(expected=IllegalStateException.class)
	public final void testDeleteCanvasWhenOpenAndAbsent() {
		this.canvasExistenceState.startsAs(CANVAS_ABSENT);
		this.testInstance.open();
		this.testInstance.deleteCanvas();
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.management.impl.MapPersistenceManager#listenerIterator()}.
	 */
	@Test
	public final void testListenerIterator() {
		assertFalse("no listeners", this.testInstance.listenerIterator().hasNext());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.management.impl.MapPersistenceManager#removeListener(org.pathwayeditor.businessobjects.management.IPersistenceManagerStatusListener)}.
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
	public final void testListeningForOpenStateChange() {
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
	public final void testListeningForForcedCloseStateChange() {
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
	public final void testListeningForForcedCloseStateChangeWhenCancelRequested() {
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
	public final void testListeningForCloseStateChangeWhenCancelRequested() {
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
	public final void testForcedCloseWhenCancelRequested() {
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
	public final void testNonForcedCloseWhenCancelRequested() {
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
