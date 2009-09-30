/**
 * 
 */
package org.pathwayeditor.businessobjects.hyperlink;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.net.URI;

import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.pathwayeditor.businessobjects.drawingprimitives.ICanvas;
import org.pathwayeditor.businessobjects.drawingprimitives.ICanvasAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.IDrawingElement;
import org.pathwayeditor.businessobjects.management.IMapPersistenceManager;
import org.pathwayeditor.businessobjects.management.IRepositoryPersistenceManager;
import org.pathwayeditor.businessobjects.repository.IMap;
import org.pathwayeditor.businessobjects.repository.IRepository;

/**
 * @author smoodie
 *
 */
public class RepositoryLookupServiceTest {
	private static final String CANVAS_URI = "pwe:/Default%20Repo/first/second/canvas";
	private static final String WRONG_SCHEME_URI = "http:/Default%20Repo/first/second/canvas";
	private static final String CANVAS_LOCAL_SERVER_URI = "pwe://localhost/Default%20Repo/first/second/canvas";
	private static final String CANVAS_LOCAL_INET_SERVER_URI = "pwe://127.0.0.1/Default%20Repo/first/second/canvas";
	private static final String CANVAS_REMOTE_SERVER_URI = "pwe://ftp.demon.net/Default%20Repo/first/second/canvas";
	private static final String CANVAS_OBJECT_URI = "pwe:/Default%20Repo/first/second/canvas#12";
	private static final String EXPECTED_REPO_NAME = "Default Repo";
	private static final String EXPECTED_CANVAS_URI = "pwe:/Default%20Repo/first/second/three";
	private static final String EXPECTED_CANVAS_OBJECT_URI = "pwe:/Default%20Repo/first/second/three#24222";
	private IRepositoryLookupService testInstance;
	private URI wrongSchemeUri;
	private URI canvasUri;
	private URI canvasLocalServerUri;
	private URI canvasRemoteServerUri;
	private URI canvasLocalInetServerUri;
	private URI canvasObjectUri;
	private IRepositoryPersistenceManager mockRepoManager;
	private IRepository mockRepo;
	private ICanvas mockCanvas;
	private ICanvasAttribute mockAttrib;
	private IDrawingElement mockDrawingElement;
	private IMap mockMap;
	private IMapPersistenceManager mockMapManager;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		mockRepoManager = EasyMock.createMock("mockRepoManager", IRepositoryPersistenceManager.class);
		mockRepo = EasyMock.createMock("mockRepo", IRepository.class);
		mockCanvas = EasyMock.createMock("mockCanvas", ICanvas.class);
		mockAttrib = EasyMock.createMock("mockAttrib", ICanvasAttribute.class);
		mockDrawingElement = EasyMock.createMock("mockDrawingElement", IDrawingElement.class);
		mockMap = EasyMock.createMock("mockMap", IMap.class);
		mockMapManager = EasyMock.createMock("mockMapManager", IMapPersistenceManager.class);

		EasyMock.expect(mockRepoManager.getRepository()).andStubReturn(mockRepo);
		mockRepoManager.open();
		EasyMock.expectLastCall().asStub();
		EasyMock.expect(mockRepoManager.isOpen()).andStubReturn(true);
		EasyMock.expect(mockRepoManager.getMapPersistenceManager(mockMap)).andStubReturn(mockMapManager);
		
		EasyMock.expect(mockRepo.getName()).andStubReturn(EXPECTED_REPO_NAME);
		EasyMock.expect(mockRepo.findRepositoryItemByPath("/first/second/canvas")).andStubReturn(mockMap);

		EasyMock.expect(mockMapManager.isOpen()).andStubReturn(true);
		mockMapManager.open();
		EasyMock.expectLastCall().asStub();
		EasyMock.expect(mockMapManager.doesCanvasExist()).andStubReturn(true);
		EasyMock.expect(mockMapManager.getCanvas()).andStubReturn(mockCanvas);
		EasyMock.expect(mockMapManager.getMap()).andStubReturn(mockMap);
		
		EasyMock.expect(mockMap.getRepository()).andStubReturn(mockRepo);
		EasyMock.expect(mockMap.getPath()).andStubReturn("/first/second/three");

		EasyMock.expect(mockCanvas.findAttribute(12)).andStubReturn(mockAttrib);

		EasyMock.expect(mockAttrib.getCurrentDrawingElement()).andStubReturn(mockDrawingElement);
		EasyMock.expect(mockAttrib.getCreationSerial()).andStubReturn(24222);
		
		EasyMock.expect(mockDrawingElement.getAttribute()).andStubReturn(mockAttrib);
		
		EasyMock.replay(mockRepoManager);
		EasyMock.replay(mockRepo);
		EasyMock.replay(mockMap);
		EasyMock.replay(mockMapManager);
		EasyMock.replay(mockCanvas);
		EasyMock.replay(mockAttrib);
		EasyMock.replay(mockDrawingElement);
		
		this.testInstance = new RepositoryLookupService(new IRepositoryServiceCallback(){
			public IRepositoryPersistenceManager getRepositoryByName(String name) {
				return mockRepoManager;
			}
			public void registerRepository(IRepositoryPersistenceManager repoManager) {
			}
		});
		
		this.canvasUri = new URI(CANVAS_URI);
		this.wrongSchemeUri = new URI(WRONG_SCHEME_URI);
		this.canvasLocalServerUri = new URI(CANVAS_LOCAL_SERVER_URI);
		this.canvasRemoteServerUri = new URI(CANVAS_REMOTE_SERVER_URI);
		this.canvasLocalInetServerUri = new URI(CANVAS_LOCAL_INET_SERVER_URI);
		this.canvasObjectUri = new URI(CANVAS_OBJECT_URI);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		EasyMock.verify(this.mockAttrib);
		EasyMock.verify(this.mockCanvas);
		EasyMock.verify(this.mockDrawingElement);
		EasyMock.verify(this.mockMap);
		EasyMock.verify(this.mockMapManager);
		EasyMock.verify(this.mockRepo);
		EasyMock.verify(this.mockRepoManager);

		this.testInstance = null;
	}

	@Test(expected=IllegalArgumentException.class)
	public void testRepositoryLookupServiceWithNullCallback() {
		this.testInstance = new RepositoryLookupService(null); 
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.hyperlink.RepositoryLookupService#findCanvas(java.net.URI)}.
	 */
	@Test
	public void testFindCanvas() {
		ICanvas actualReturn = this.testInstance.findCanvas(this.canvasUri);
		assertNotNull("canvas exist", actualReturn);
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.hyperlink.RepositoryLookupService#findCanvas(java.net.URI)}.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testFindCanvasWrongScheme() {
		this.testInstance.findCanvas(this.wrongSchemeUri);
	}

	@Test
	public void testIsValidUri(){
		assertFalse("InValid", this.testInstance.isValidUri(null));
		assertTrue("Valid", this.testInstance.isValidUri(this.canvasUri));
		assertFalse("InValid", this.testInstance.isValidUri(this.wrongSchemeUri));
	}
	
	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.hyperlink.RepositoryLookupService#findCanvas(java.net.URI)}.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testFindCanvasObjectWrongScheme() {
		this.testInstance.findCanvasObject(this.wrongSchemeUri);
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.hyperlink.RepositoryLookupService#findCanvas(java.net.URI)}.
	 */
	@Test
	public void testFindCanvasWithLocalServerUri() {
		ICanvas actualReturn = this.testInstance.findCanvas(this.canvasLocalServerUri);
		assertNotNull("canvas exist", actualReturn);
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.hyperlink.RepositoryLookupService#findCanvas(java.net.URI)}.
	 */
	@Test
	public void testFindCanvasWithLocalInetServerUri() {
		ICanvas actualReturn = this.testInstance.findCanvas(this.canvasLocalInetServerUri);
		assertNotNull("canvas exist", actualReturn);
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.hyperlink.RepositoryLookupService#findCanvas(java.net.URI)}.
	 */
	@Test
	public void testFindCanvasWithRemoteServerUri() {
		ICanvas actualReturn = this.testInstance.findCanvas(this.canvasRemoteServerUri);
		assertNull("canvas not found", actualReturn);
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.hyperlink.RepositoryLookupService#findCanvas(java.net.URI)}.
	 */
	@Test
	public void testFindCanvasWithCanvasObjectUri() {
		ICanvas actualReturn = this.testInstance.findCanvas(this.canvasObjectUri);
		assertNotNull("canvas obj exists", actualReturn);
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.hyperlink.RepositoryLookupService#findCanvasObject(java.net.URI)}.
	 */
	@Test
	public void testFindCanvasObjectWithCanvasUri() {
		IDrawingElement actualReturn = this.testInstance.findCanvasObject(this.canvasUri);
		assertNull("canvas obj not found", actualReturn);
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.hyperlink.RepositoryLookupService#findCanvasObject(java.net.URI)}.
	 */
	@Test
	public void testFindCanvasObject() {
		IDrawingElement actualReturn = this.testInstance.findCanvasObject(this.canvasObjectUri);
		assertNotNull("canvas obj exists", actualReturn);
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.hyperlink.RepositoryLookupService#createAbsoluteUri}.
	 */
	@Test
	public void testCreateAbsoluteUriMapManager() {
		URI actualReturn = this.testInstance.createAbsoluteUri(this.mockMapManager);
		assertEquals("absolute URI", EXPECTED_CANVAS_URI, actualReturn.toString());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.hyperlink.RepositoryLookupService#createAbsoluteUri}.
	 */
	@Test
	public void testCreateAbsoluteUriManManagerDrawingElement() {
		URI actualResult = this.testInstance.createAbsoluteUri(this.mockMapManager, this.mockDrawingElement);
		assertEquals("absolute URI", EXPECTED_CANVAS_OBJECT_URI, actualResult.toString());
	}

}
