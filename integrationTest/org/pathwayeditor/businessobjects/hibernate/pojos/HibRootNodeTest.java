/**
 * 
 */
package org.pathwayeditor.businessobjects.hibernate.pojos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.pathwayeditor.businessobjects.drawingprimitives.ICanvas;
import org.pathwayeditor.businessobjects.drawingprimitives.IRootNode;
import org.pathwayeditor.businessobjects.management.NonPersistentCanvasFactory;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationSyntaxService;
import org.pathwayeditor.bussinessobjects.stubs.notationsubsystem.StubNotationSubSystem;
import org.pathwayeditor.bussinessobjects.stubs.notationsubsystem.StubShapeAObjectType;
import org.pathwayeditor.bussinessobjects.stubs.notationsubsystem.StubShapeDObjectType;

/**
 * @author smoodie
 *
 */
public class HibRootNodeTest {
	private IRootNode testInstance;
	private INotationSyntaxService syntaxService;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		NonPersistentCanvasFactory canvasFact = NonPersistentCanvasFactory.getInstance();
		this.syntaxService = new StubNotationSubSystem().getSyntaxService();
		canvasFact.setCanvasName("test");
		canvasFact.setNotationSubsystem(this.syntaxService.getNotationSubsystem());
		ICanvas canvas = canvasFact.createNewCanvas();
		this.testInstance = canvas.getModel().getRootNode();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		this.syntaxService = null;
		this.testInstance = null;
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.hibernate.pojos.HibRootNode#getAttribute()}.
	 */
	@Test
	public void testGetAttribute() {
		assertNotNull("attribute set", this.testInstance.getAttribute());
		assertNotNull("current node set", this.testInstance.getAttribute().getCurrentDrawingElement());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.hibernate.pojos.HibRootNode#getParentNode()}.
	 */
	@Test
	public void testGetParentNode() {
		assertEquals("parent same as root", this.testInstance, this.testInstance.getParentNode());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.hibernate.pojos.HibRootNode#canParent(org.pathwayeditor.businessobjects.drawingprimitives.IDrawingNode)}.
	 */
	@Test
	public void testCanParentIDrawingNode() {
		assertFalse("cannot parent self", this.testInstance.canParent(this.testInstance));
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.hibernate.pojos.HibRootNode#canParent(org.pathwayeditor.businessobjects.typedefn.INodeObjectType)}.
	 */
	@Test
	public void testCanParentINodeObjectType() {
		assertTrue("can parent", this.testInstance.canParent(this.syntaxService.getShapeObjectType(StubShapeAObjectType.UNIQUE_ID)));
		assertFalse("cannot parent", this.testInstance.canParent(this.syntaxService.getShapeObjectType(StubShapeDObjectType.UNIQUE_ID)));
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.hibernate.pojos.HibRootNode#isValidChildOf(org.pathwayeditor.businessobjects.drawingprimitives.IDrawingNode)}.
	 */
	@Test
	public void testIsValidChildOfIDrawingNode() {
		assertFalse("cannot be child of itself", this.testInstance.isValidChildOf(this.testInstance));
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.hibernate.pojos.HibCompoundNode#isDescendent(org.pathwayeditor.businessobjects.drawingprimitives.IDrawingNode)}.
	 */
	@Test
	public void testIsDescendentIDrawingNode() {
		assertFalse("not descendent of itself", this.testInstance.isDescendent(this.testInstance));
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.hibernate.pojos.HibCompoundNode#getLevel()}.
	 */
	@Test
	public void testGetLevel() {
		assertEquals("expected level", 0, this.testInstance.getLevel());
	}

}
