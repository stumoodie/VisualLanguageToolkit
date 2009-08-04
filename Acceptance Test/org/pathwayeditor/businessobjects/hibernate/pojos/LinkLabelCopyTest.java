package org.pathwayeditor.businessobjects.hibernate.pojos;	

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.pathwayeditor.businessobjects.drawingprimitives.ICanvas;
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkEdge;
import org.pathwayeditor.businessobjects.drawingprimitives.ISelectionFactory;
import org.pathwayeditor.businessobjects.management.IMapPersistenceManager;
import org.pathwayeditor.businessobjects.management.IRepositoryPersistenceManager;
import org.pathwayeditor.testutils.GenericXlsTester;


public class LinkLabelCopyTest extends GenericXlsTester {
	private static final String SOURCE_DATA_FILE = "Acceptance Test/DBConsistencyTestSourceData/DBSourceData.xls";
	private static final String REPOSITORY_NAME ="repo name";
	private static final int TEST_INODE = 4;
	private static final int EXPECTED_LINK_ATTRIBUTE = 9;
//	private static final String EXPECTED_MOVED_LABEL = "Acceptance Test/org/pathwayeditor/businessobjects/hibernate/pojos/ExpectedLabelCopy.xml";
	private static final int EXPECTED_NUM_LINKS = 18;
	private static final int EXPECTED_NUM_SHAPES = 16;
	private static final int EXPECTED_NUM_LABELS = 16;
	private static final int EXPECTED_NUM_ROOT_LINKS = 8;
	private static final int EXPECTED_NUM_ROOT_SHAPES = 4;
	private static final int EXPECTED_NUM_ROOT_LABELS = 8;
	private static final int EXPECTED_NUM_ATTRIBUTES = 87;

	private ILinkAttribute testLink;
	private IMapPersistenceManager mapManager;

	@Override
	protected void doAdditionalSetUp() {
		IRepositoryPersistenceManager repoManager = this.getRepositoryPersistenceManager();
		this.mapManager = repoManager.getMapPersistenceManager(TEST_INODE);
		mapManager.open();
		ICanvas canvas = mapManager.getCanvas();
		testLink = canvas.getLinkAttribute(EXPECTED_LINK_ATTRIBUTE);
	}

	
	@Test
	public void testLinkCopied() throws Exception{
		ILinkEdge link = this.testLink.getCurrentDrawingElement();
		ISelectionFactory fact = link.getModel().newSelectionFactory();
		fact.addDrawingNode(link.getSourceShape());
		fact.addDrawingNode(link.getTargetShape());
		fact.addLink(link);
		link.getModel().getRootNode().getSubModel().copyHere(fact.createGeneralSelection());
		assertTrue("map valid", link.getModel().isValid());
		assertEquals("expected num links", EXPECTED_NUM_LINKS, link.getModel().numLinkEdges());
		assertEquals("expected num shapes", EXPECTED_NUM_SHAPES, link.getModel().numShapeNodes());
		assertEquals("expected num labels", EXPECTED_NUM_LABELS, link.getModel().numLabelNodes());
		assertEquals("expected num root links", EXPECTED_NUM_ROOT_LINKS, link.getModel().getRootNode().getSubModel().numLinkEdges());
		assertEquals("expected num root shapes", EXPECTED_NUM_ROOT_SHAPES, link.getModel().getRootNode().getSubModel().numShapeNodes());
		assertEquals("expected num root labels", EXPECTED_NUM_ROOT_LABELS, link.getModel().getRootNode().getSubModel().numLabelNodes());
		ICanvas canvas = link.getModel().getCanvas();
		assertEquals("expected num attribs", EXPECTED_NUM_ATTRIBUTES, canvas.numCanvasAttributes());
		mapManager.close(true);
	}
	
	
	
	@Override
	protected void doAdditionalTearDown() {
		this.testLink = null;
	}

	@Override
	protected String getDbUnitDataFilePath() {
		return SOURCE_DATA_FILE;
	}

	@Override
	protected String getTestRepositoryName() {
		return REPOSITORY_NAME ;
	}

}
