package org.pathwayeditor.businessobjects.hibernate.pojos;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.pathwayeditor.businessobjects.drawingprimitives.ICanvas;
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.IModel;
import org.pathwayeditor.businessobjects.drawingprimitives.ISelectionFactory;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationProperty;
import org.pathwayeditor.businessobjects.management.IMapPersistenceManager;
import org.pathwayeditor.businessobjects.management.IRepositoryPersistenceManager;
import org.pathwayeditor.businessobjects.repository.IRepository;
import org.pathwayeditor.testutils.GenericTester;


public class LabelManipulationTest extends GenericTester {
	private static final String SOURCE_DATA_FILE = "Acceptance Test/DBConsistencyTestSourceData/DBSourceData.xml";
	private static final String REPOSITORY_NAME ="repo name";
	private static final int TEST_INODE = 4;
	private static final int EXPECTED_SHAPE_ATTRIBUTE = 1;
	private static final int EXPECTED_LINK_ATTRIBUTE = 11;
	private static final String SHAPE_PROP_NAME = "TextProperty";
	private static final String LINK_PROP_NAME = "NumberProperty";
	private static final String EXPECTED_LABEL_REMOVAL = "Acceptance Test/org/pathwayeditor/businessobjects/hibernate/pojos/ExpectedLabelRemoval.xml";
	private static final String EXPECTED_LABEL_CREATION = "Acceptance Test/org/pathwayeditor/businessobjects/hibernate/pojos/ExpectedLabelCreation.xml";
	private static final String EXPECTED_LABEL_CREATED_AND_REMOVED = "Acceptance Test/org/pathwayeditor/businessobjects/hibernate/pojos/ExpectedLabelCreationAndDeletion.xml";
	private static final String EXPECTED_LABEL_CREATED_AND_REMOVED_AND_CREATED = "Acceptance Test/org/pathwayeditor/businessobjects/hibernate/pojos/ExpectedLabelCreationAndDeletionAndCreation.xml";
	private static final int EXPECTED_PARENT_SHAPE_ATTRIBUTE = 4;
	private static final String EXPECTED_MOVED_LABEL = "Acceptance Test/org/pathwayeditor/businessobjects/hibernate/pojos/ExpectedMovedLabel.xml";

	private IRepository repository;
	private IShapeAttribute testShape;
	private IShapeAttribute testParentShape;
	private ILinkAttribute testLink;
	private IMapPersistenceManager mapManager;

	@Override
	protected void doAdditionalSetUp() {
		IRepositoryPersistenceManager repoManager = this.getRepositoryPersistenceManager();
		repository = repoManager.getRepository();
		this.mapManager = repoManager.getMapPersistenceManager(TEST_INODE);
		mapManager.open();
		ICanvas canvas = mapManager.getCanvas();
		testShape = canvas.getShapeAttribute(EXPECTED_SHAPE_ATTRIBUTE);
		testLink = canvas.getLinkAttribute(EXPECTED_LINK_ATTRIBUTE);
		testParentShape = canvas.getShapeAttribute(EXPECTED_PARENT_SHAPE_ATTRIBUTE);
	}

	
	@Test
	public void testLabelRemoved() throws Exception{
		IAnnotationProperty prop = testShape.getProperty(SHAPE_PROP_NAME);
		prop.setDisplayed(false);
		mapManager.synchronise();
		mapManager.close(true);
		this.compareDatabase(EXPECTED_LABEL_REMOVAL);
	}
	
	
	@Test
	public void testLabelCreated() throws Exception{
		IAnnotationProperty prop = testLink.getProperty(LINK_PROP_NAME);
		prop.setDisplayed(true);
		mapManager.synchronise();
		mapManager.close(true);
		this.compareDatabase(EXPECTED_LABEL_CREATION);
	}
	
	
	@Test
	public void testLabelCreatedAndRemoved() throws Exception{
		IAnnotationProperty prop = testLink.getProperty(LINK_PROP_NAME);
		prop.setDisplayed(true);
//		mapManager.synchronise();
//		mapManager.close(true);
//		mapManager.open();
		ICanvas canvas = mapManager.getCanvas();
		ILinkAttribute link = canvas.getLinkAttribute(EXPECTED_LINK_ATTRIBUTE);
		IAnnotationProperty newProp = link.getProperty(LINK_PROP_NAME);
		newProp.setDisplayed(false);
		mapManager.synchronise();
		mapManager.close(true);
		this.compareDatabase(EXPECTED_LABEL_CREATED_AND_REMOVED);
	}
	
	
	@Test
	public void testLabelCreatedAndRemovedAndCreated() throws Exception{
		{
			IAnnotationProperty prop = testLink.getProperty(LINK_PROP_NAME);
			prop.setDisplayed(true);
//			mapManager.synchronise();
//			mapManager.close(true);
		}
		{
//			mapManager.open();
			ICanvas canvas = mapManager.getCanvas();
			ILinkAttribute link = canvas.getLinkAttribute(EXPECTED_LINK_ATTRIBUTE);
			IAnnotationProperty newProp = link.getProperty(LINK_PROP_NAME);
			newProp.setDisplayed(false);
//			mapManager.synchronise();
//			mapManager.close(true);
		}
		{
//			mapManager.open();
			ICanvas canvas = mapManager.getCanvas();
			ILinkAttribute link = canvas.getLinkAttribute(EXPECTED_LINK_ATTRIBUTE);
			IAnnotationProperty newProp = link.getProperty(LINK_PROP_NAME);
			newProp.setDisplayed(true);
			mapManager.synchronise();
			mapManager.close(true);
		}
		this.compareDatabase(EXPECTED_LABEL_CREATED_AND_REMOVED_AND_CREATED);
	}
	
	@Test
	public void testLabelParentMove() throws Exception{
		IModel model = this.testShape.getCurrentDrawingElement().getModel();
		ISelectionFactory objectSelection = model.newSelectionFactory() ;
		objectSelection.addDrawingNode(this.testParentShape.getCurrentDrawingElement()) ;
		assertTrue("model is valid before move", model.isValid());
		model.getRootNode().getSubModel().moveHere(objectSelection.createGeneralSelection()) ;
		assertTrue("model is valid after move", model.isValid());
		mapManager.synchronise();
		mapManager.close(true);
		this.compareDatabase(EXPECTED_MOVED_LABEL);
	}
	
	@Override
	protected void doAdditionalTearDown() {
		this.repository = null;
		this.testLink = null;
		this.testShape = null;
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
