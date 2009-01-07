/**
 * 
 */
package org.pathwayeditor.businessobjects.management;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
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
public class RepositoryPersistenceManagerTest {
	private static final String REPO_UNLOADED_STATE = "closed";
	private static final String REPO_LOADED_STATE = "open";
	private static final String CANVAS_UNLOADED_STATE = "closed";
	private static final String CANVAS_LOADED_STATE = "open";
	private static final String MAP_SET_STATE = "mapSet";
	private static final String MAP_UNSET_STATE = "mapUnSet";
	private static final String EXPECTED_REPO_NAME = "Test Repo";
	private static final String OTHER_REPO_NAME = "Other Test Repo";
	private static final int EXPECTED_CANVAS_INODE = 999;
	private static final String EXPECTED_MAP_NAME = "Test Map";
	
	private Mockery mockery = new JUnit4Mockery();
	private IRepositoryPersistenceManager testInstance;
	private IRepositoryPersistenceHandler mockRepoPersistenceHandler;
	private ICanvasPersistenceHandlerFactory mockCanvasPersistenceHandlerFactory; 
	private ICanvasPersistenceHandler mockCanvasPersistenceHandler;
	private ICanvas mockCanvas;
	private IMap mockMap;
	private IRepository mockRepository;
	private boolean expectedStateChangeCalled = false;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		this.mockCanvasPersistenceHandlerFactory = this.mockery.mock(ICanvasPersistenceHandlerFactory.class, "mockCanvasPersistenceHandlerFactory");
		this.mockRepoPersistenceHandler = this.mockery.mock(IRepositoryPersistenceHandler.class, "mockRepoPersistenceHandler");
		this.mockCanvasPersistenceHandler = this.mockery.mock(ICanvasPersistenceHandler.class, "mockCanvasPersistenceHandler");
		this.mockCanvas = this.mockery.mock(ICanvas.class, "mockCanvas");
		this.mockMap = this.mockery.mock(IMap.class, "mockMap");
		this.mockRepository = this.mockery.mock(IRepository.class, "mockRepository");
		final States repoHandlerState = this.mockery.states("managerState");
		repoHandlerState.startsAs(REPO_UNLOADED_STATE);
		final States canvasHandlerFactoryState = this.mockery.states("canvasHandlerFactoryState");
		canvasHandlerFactoryState.startsAs(MAP_UNSET_STATE);
		final States canvasHandlerState = this.mockery.states("canvasHandlerState");
		canvasHandlerState.startsAs(CANVAS_UNLOADED_STATE);
		
		this.mockery.checking(new Expectations() {{
			// the handler factory will return an instance of the mockCanvasPersistenceHandler
			// since each call to createPersistenceHandler() should result in a different
			// BEWARE: the use of allowing will break the API contract if called twice. 
			allowing(mockCanvasPersistenceHandlerFactory).setMap(with(aNull(IMap.class))); then(canvasHandlerFactoryState.is(MAP_UNSET_STATE));
			allowing(mockCanvasPersistenceHandlerFactory).setMap(with(same(mockMap))); then(canvasHandlerFactoryState.is(MAP_SET_STATE));
			allowing(mockCanvasPersistenceHandlerFactory).getMap(); will(returnValue(null)); when(canvasHandlerFactoryState.is(MAP_UNSET_STATE));
			allowing(mockCanvasPersistenceHandlerFactory).getMap(); will(returnValue(mockMap)); when(canvasHandlerFactoryState.is(MAP_SET_STATE));
			allowing(mockCanvasPersistenceHandlerFactory).createPersistenceHandler(); will(returnValue(mockCanvasPersistenceHandler)); when(canvasHandlerFactoryState.is(MAP_SET_STATE));
			allowing(mockCanvasPersistenceHandlerFactory).createPersistenceHandler(); will(throwException(new IllegalStateException())); when(canvasHandlerFactoryState.is(MAP_UNSET_STATE));

			// The handler is assumed to be newly created, with a canvas that exists, but has not been loaded
			// so the loaded canvas is null.
			allowing(mockCanvasPersistenceHandler).createCanvas(with(any(INotationSubsystem.class))); will(throwException(new IllegalStateException()));
			allowing(mockCanvasPersistenceHandler).deleteCanvas(); will(throwException(new IllegalStateException())); when(canvasHandlerState.is(CANVAS_UNLOADED_STATE));
			allowing(mockCanvasPersistenceHandler).loadCanvas(); then(canvasHandlerState.is(CANVAS_LOADED_STATE));
			allowing(mockCanvasPersistenceHandler).reset(); then(canvasHandlerState.is(CANVAS_UNLOADED_STATE));
			allowing(mockCanvasPersistenceHandler).synchroniseCanvas(); will(throwException(new IllegalStateException())); when(canvasHandlerState.is(CANVAS_UNLOADED_STATE));
			allowing(mockCanvasPersistenceHandler).synchroniseCanvas(); when(canvasHandlerState.is(CANVAS_LOADED_STATE));
			allowing(mockCanvasPersistenceHandler).getOwningMap(); will(returnValue(mockMap));
			allowing(mockCanvasPersistenceHandler).doesCanvasExist(); will(returnValue(true));
			allowing(mockCanvasPersistenceHandler).getLoadedCanvas(); will(returnValue(null)); when(canvasHandlerState.is(CANVAS_UNLOADED_STATE));
			allowing(mockCanvasPersistenceHandler).getLoadedCanvas(); will(returnValue(mockCanvas)); when(canvasHandlerState.is(CANVAS_LOADED_STATE));
			
			// The repository handler is NOT loaded.
			allowing(mockRepoPersistenceHandler).loadRepository(); then(repoHandlerState.is(REPO_LOADED_STATE));
			allowing(mockRepoPersistenceHandler).synchroniseRepository(); will(throwException(new IllegalStateException())); when(repoHandlerState.is(REPO_UNLOADED_STATE));
			allowing(mockRepoPersistenceHandler).synchroniseRepository(); when(repoHandlerState.is(REPO_LOADED_STATE));
			allowing(mockRepoPersistenceHandler).getLoadedRepository(); will(returnValue(null)); when(repoHandlerState.is(REPO_UNLOADED_STATE));
			allowing(mockRepoPersistenceHandler).getLoadedRepository(); will(returnValue(mockRepository)); when(repoHandlerState.is(REPO_LOADED_STATE));
			allowing(mockRepoPersistenceHandler).getName(); will(returnValue(EXPECTED_REPO_NAME));
			allowing(mockRepoPersistenceHandler).reset(); then(repoHandlerState.is(REPO_UNLOADED_STATE));
			
			allowing(mockRepository).getName(); will(returnValue(EXPECTED_REPO_NAME));
			
			// Canvas has a repo name and inode set.
			allowing(mockCanvas).getRepositoryName(); will(returnValue(EXPECTED_REPO_NAME));
			allowing(mockCanvas).getINode(); will(returnValue(EXPECTED_CANVAS_INODE));
			
			// Map has a name, repo and inode set.
			allowing(mockMap).getRepository(); will(returnValue(mockRepository));
			allowing(mockMap).getINode(); will(returnValue(EXPECTED_CANVAS_INODE));
			allowing(mockMap).getName(); will(returnValue(EXPECTED_MAP_NAME));
		}});
		
		this.testInstance = new RepositoryPersistenceManager(this.mockRepoPersistenceHandler, this.mockCanvasPersistenceHandlerFactory);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		this.expectedStateChangeCalled = false;
		this.testInstance = null;
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.management.RepositoryPersistenceManager#RepositoryPersistenceManager(org.pathwayeditor.businessobjects.management.IRepositoryPersistenceHandler, org.pathwayeditor.businessobjects.management.ICanvasPersistenceHandlerFactory)}.
	 */
	@Test(expected=IllegalArgumentException.class)
	public final void testRepositoryPersistenceManagerWithNullRepoHandler() {
		this.testInstance = new RepositoryPersistenceManager(null, this.mockCanvasPersistenceHandlerFactory);
	}

	@Test(expected=IllegalArgumentException.class)
	public final void testRepositoryPersistenceManagerWithNullCanvasHandlerFact() {
		this.testInstance = new RepositoryPersistenceManager(this.mockRepoPersistenceHandler, null);
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.management.RepositoryPersistenceManager#getRepository()}.
	 */
	@Test(expected=IllegalStateException.class)
	public final void testGetRepository() {
		this.testInstance.getRepository();
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.management.RepositoryPersistenceManager#isOpen()}.
	 */
	@Test
	public final void testIsOpen() {
		assertFalse("manager not open", this.testInstance.isOpen());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.management.RepositoryPersistenceManager#open()}.
	 */
	@Test
	public final void testOpen() {
		this.testInstance.open();
		assertTrue("manager is open", this.testInstance.isOpen());
		assertEquals("expected repo", this.mockRepository, this.testInstance.getRepository());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.management.RepositoryPersistenceManager#close(boolean)}.
	 */
	@Test
	public final void testCloseForced() {
		this.testInstance.close(true);
		assertFalse("manager not open", this.testInstance.isOpen());
	}

	@Test
	public final void testCloseNotForced() {
		this.testInstance.close(false);
		assertFalse("manager not open", this.testInstance.isOpen());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.management.RepositoryPersistenceManager#synchronise()}.
	 */
	@Test(expected=IllegalStateException.class)
	public final void testSynchronise() {
		this.testInstance.synchronise();
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.management.RepositoryPersistenceManager#isValidMap(org.pathwayeditor.businessobjects.repository.IMap)}.
	 */
	@Test(expected=IllegalStateException.class)
	public final void testIsValidMapWhenNotOpen() {
		this.testInstance.isValidMap(this.mockMap);
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.management.RepositoryPersistenceManager#isValidMap(org.pathwayeditor.businessobjects.repository.IMap)}.
	 */
	@Test
	public final void testIsValidMapWhenOpen() {
		this.testInstance.open();
		assertTrue("valid map", this.testInstance.isValidMap(this.mockMap));
		assertFalse("valid map", this.testInstance.isValidMap(null));
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.management.RepositoryPersistenceManager#isValidMap(org.pathwayeditor.businessobjects.repository.IMap)}.
	 */
	@Test
	public final void testIsValidMapWhenOpenAndDiffRepo() {
		this.testInstance.open();
		final IRepository mockOtherRepo = this.mockery.mock(IRepository.class, "mockOtherRepo");
		final IMap mockInvalidMap = this.mockery.mock(IMap.class, "mockInvalidMap");
		this.mockery.checking(new Expectations() {{
			allowing(mockInvalidMap).getRepository(); will(returnValue(mockOtherRepo));
			
			allowing(mockOtherRepo).getName(); will(returnValue(OTHER_REPO_NAME));
		}});
		assertFalse("valid map", this.testInstance.isValidMap(mockInvalidMap));
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.management.RepositoryPersistenceManager#getMapPersistenceManager(org.pathwayeditor.businessobjects.repository.IMap)}.
	 */
	@Test(expected=IllegalStateException.class)
	public final void testGetMapPersistenceManagerWhenNotOpen() {
		this.testInstance.getMapPersistenceManager(this.mockMap);
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.management.RepositoryPersistenceManager#getMapPersistenceManager(org.pathwayeditor.businessobjects.repository.IMap)}.
	 */
	@Test
	public final void testGetMapPersistenceManagerWhenOpen() {
		this.testInstance.open();
		IMapPersistenceManager mapManager = this.testInstance.getMapPersistenceManager(this.mockMap);
		assertNotNull("mapmanager not null", mapManager);
		assertFalse("expected map manager state", mapManager.isOpen());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.management.RepositoryPersistenceManager#getMapPersistenceManager(org.pathwayeditor.businessobjects.repository.IMap)}.
	 */
	@Test(expected=IllegalArgumentException.class)
	public final void testGetMapPersistenceManagerWhenOpenAndMapNull() {
		this.testInstance.open();
		this.testInstance.getMapPersistenceManager(null);
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.management.RepositoryPersistenceManager#getMapPersistenceManager(org.pathwayeditor.businessobjects.repository.IMap)}.
	 */
	@Test(expected=IllegalArgumentException.class)
	public final void testGetMapPersistenceManagerWhenOpenAndMapFromAnotherRepo() {
		this.testInstance.open();
		final IRepository mockOtherRepo = this.mockery.mock(IRepository.class, "mockOtherRepo");
		final IMap mockInvalidMap = this.mockery.mock(IMap.class, "mockInvalidMap");
		this.mockery.checking(new Expectations() {{
			allowing(mockInvalidMap).getRepository(); will(returnValue(mockOtherRepo));
			
			allowing(mockOtherRepo).getName(); will(returnValue(OTHER_REPO_NAME));
		}});
		this.testInstance.getMapPersistenceManager(mockInvalidMap);
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
	
	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.management.RepositoryPersistenceManager#addListener(org.pathwayeditor.businessobjects.management.IPersistenceManagerStatusListener)}.
	 */
	@Test(expected=IllegalArgumentException.class)
	public final void testAddListenerNull() {
		this.testInstance.addListener(null);
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.management.RepositoryPersistenceManager#listenerIterator()}.
	 */
	@Test
	public final void testListenerIteratorWhenNone() {
		Iterator<IPersistenceManagerStatusListener> iter = this.testInstance.listenerIterator();
		assertFalse("expect empty iterator", iter.hasNext());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.management.RepositoryPersistenceManager#removeListener(org.pathwayeditor.businessobjects.management.IPersistenceManagerStatusListener)}.
	 */
	@Test(expected=IllegalArgumentException.class)
	public final void testRemoveListenerNullParam() {
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
	
}
