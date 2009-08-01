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
package org.pathwayeditor.businessobjects.hibernate.pojos;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.pathwayeditor.businessobjects.drawingprimitives.ICanvas;
import org.pathwayeditor.businessobjects.drawingprimitives.IGraphMomento;
import org.pathwayeditor.businessobjects.drawingprimitives.ILabelAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkEdge;
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkEdgeFactory;
import org.pathwayeditor.businessobjects.drawingprimitives.IModel;
import org.pathwayeditor.businessobjects.drawingprimitives.ISelectionFactory;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeNode;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeNodeFactory;
import org.pathwayeditor.businessobjects.drawingprimitives.ISubModel;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LineStyle;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationProperty;
import org.pathwayeditor.businessobjects.management.IMapPersistenceManager;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationSubsystem;
import org.pathwayeditor.businessobjects.repository.IMap;
import org.pathwayeditor.businessobjects.repository.IRepository;
import org.pathwayeditor.businessobjects.repository.ISubFolder;
import org.pathwayeditor.bussinessobjects.stubs.notationsubsystem.StubLinkBObjectType;
import org.pathwayeditor.bussinessobjects.stubs.notationsubsystem.StubShapeAObjectType;
import org.pathwayeditor.bussinessobjects.stubs.notationsubsystem.StubShapeBObjectType;
import org.pathwayeditor.figure.geometry.Dimension;
import org.pathwayeditor.figure.geometry.Point;
import org.pathwayeditor.testutils.GenericTester;

/**
 * @author ntsorman
 *
 */
//@RunWith(JMock.class)
public class CheckDbOperationsCompoundGraphTest extends GenericTester{
	
//	private Mockery mockery = new JUnit4Mockery() {{
//		 setImposteriser(ClassImposteriser.INSTANCE);
//	}};
	
	private IMapPersistenceManager map1Manager ;
//	private IMapPersistenceManager map2Manager ;
	
	private IRepository repository;
//	private IRootFolder rootFolder ;
	private ISubFolder subFolder1 ;
//	private ISubFolder subFolder2 ;
	private IMap mapDiagram1 ;
	private ICanvas dbCanvas ;
	private IModel dbModel ;
//	private IRootNode dbRootNode ;
	
//	private IShapeNode shapeNode1 ;
//	private IShapeNode shapeNode2 ;
//	private IShapeNode shapeNode3 ;
//	private IShapeNode shapeNode4 ;
//	private IShapeNode shapeNode5 ;
//	private IShapeNode shapeNode6 ;
//	private IShapeNode shapeNode7 ;
//	private IShapeNode shapeNode8 ;
	
//	private ILinkEdge linkEdge1 ;
//	private ILinkEdge linkEdge2 ;
//	private ILinkEdge linkEdge3;
//	private ILinkEdge linkEdge4 ;
//	private ILinkEdge linkEdge5 ;
//	private ILinkEdge linkEdge6 ;
//	private ILinkEdge linkEdge7 ;
//	private ILinkEdge linkEdge8 ;
//	private ILinkEdge linkEdge9 ;
	
//	private IShapeNode newNode ;
//	private ILabelNode newLabel ;
//	private ILinkEdge newLinkEdge ;
	
	private INotationSubsystem dbNotationSubSystem;
	
	private static final String REPOSITORY_NAME ="repo name" ;
	private static final String SUBFOLDER1_PATH = "/subfolder1/" ;
//	private static final String SUBFOLDER2_PATH = "/subfolder2/" ;
	private static final String MAP1_PATH = "/subfolder1/Diagram name" ;
	private static final String MAP2_PATH = "/subfolder2/Diagram name2" ;
	
	private static final Point NEW_NODE_LOCATION = new Point ( 75 , 75 ) ;
//	private static final Size NEW_NODE_SIZE = new Size (25 , 25) ;
	

	private final static String CREATED_LINK_VALIDATION = "Acceptance Test/DBConsistencyTestValidationData/createdLinkEdge.xml" ;
	private final static String CREATED_TWO_NODE_VALIDATION = "Acceptance Test/DBConsistencyTestValidationData/CreatedTwoShapeNode.xml" ;
	private final static String CREATED_NODE_VALIDATION = "Acceptance Test/DBConsistencyTestValidationData/CreatedShapeNode.xml" ;
	private final static String CREATED_LABEL_VALIDATION = "Acceptance Test/DBConsistencyTestValidationData/CreatedLabelNode.xml" ;
	private final static String DELETED_NODE_VALIDATION = "Acceptance Test/DBConsistencyTestValidationData/DeleteShapeNode.xml" ;
	private final static String DELETED_LABEL_VALIDATION = "Acceptance Test/DBConsistencyTestValidationData/DeletedLabelNodeData.xml" ;
	private final static String MOVED_NODE_VALIDATION = "Acceptance Test/DBConsistencyTestValidationData/MovedNodeValidation.xml" ;
	private final static String COPIED_NODE_VALIDATION = "Acceptance Test/DBConsistencyTestValidationData/CopiedShapeNode.xml" ;
	private final static String COPY_WHOLE_VALIDATION = "Acceptance Test/DBConsistencyTestValidationData/CopyWholeGraphData.xml" ;
	private final static String DELETED_EDGE_VALIDATION = "Acceptance Test/DBConsistencyTestValidationData/DeletedEdge.xml" ;
	private final static String DELETED_TWO_SHAPES_VALIDATION = "Acceptance Test/DBConsistencyTestValidationData/deletedTwoShapes.xml" ;
	private final static String DELETED_ALL_VALIDATION = "Acceptance Test/DBConsistencyTestValidationData/DeleteAll.xml" ;
	private final static String CHANGED_SHAPE_DATA_VALIDATION = "Acceptance Test/DBConsistencyTestValidationData/ChangedShapeData.xml" ;
	private final static String CHANGED_LABEL_DATA_VALIDATION = "Acceptance Test/DBConsistencyTestValidationData/ChangedLabelData.xml" ;
	private final static String CHANGED_LINK_DATA_VALIDATION = "Acceptance Test/DBConsistencyTestValidationData/ChangedLinkData.xml" ;
	private final static String CHANGED_CANVAS_DATA_VALIDATION = "Acceptance Test/DBConsistencyTestValidationData/ChangedCanvasData.xml" ;
	private final static String RESTORED_FROM_DELETED_SHAPE_NODE = "Acceptance Test/DBConsistencyTestValidationData/restoredDeletedShapeData.xml" ;
	private final static String RESTORED_FROM_ADDED_SHAPE_NODE = "Acceptance Test/DBConsistencyTestValidationData/restoredSavedShapeData.xml" ;
	private final static String RESTORED_FROM_MOVED_SHAPE_NODE = "Acceptance Test/DBConsistencyTestValidationData/RestoredMovedShapeData.xml" ;
	private final static String RESTORED_FROM_COPIED_SHAPE_NODE = "Acceptance Test/DBConsistencyTestValidationData/RestoredCopiedShapeData.xml" ;
	private final static String RESTORED_FROM_DELETED_LABEL_NODE = "Acceptance Test/DBConsistencyTestValidationData/RestoredDeletedLabelData.xml" ;
	private final static String RESTORED_FROM_DELETED_LINK_EDGE = "Acceptance Test/DBConsistencyTestValidationData/RestoredDeletedLinkData.xml" ;
	private final static String RESTORED_FROM_ADDED_LINK_EDGE = "Acceptance Test/DBConsistencyTestValidationData/RestoredAddedLinkData.xml" ;
	private final static String RESTORED_FROM_ADDED_LABEL_NODE = "Acceptance Test/DBConsistencyTestValidationData/RestoredAddedLabelData.xml" ;
	private static final String CANVAS_COPIED_FILE = "Acceptance Test/DBConsistencyTestValidationData/CanvasCopiedData.xml";
	
	
	private final static RGB CHANGED_SHAPE_FILL_COLOR = new RGB ( 1 ,1 ,1 ) ;
	private final static RGB CHANGED_SHAPE_LINE_COLOR = new RGB ( 2 ,2 ,2 ) ;
	private final static LineStyle CHANGED_SHAPE_LINE_STYLE = LineStyle.SOLID ;
	private final static int CHANGED_SHAPE_LINE_WIDTH = 100 ; 
	private final static String CHANGED_SHAPE_PRIMITIVE_TYRE = "curbounds oval" ;
	private final static Point CHANGED_SHAPE_LOCATION = new Point ( 500 , 500 ) ;
	private final static Dimension CHANGED_SHAPE_SIZE = new Dimension ( 400 ,400 ) ;
	
	private final static RGB CHANGED_LABEL_BACKGROUND_COLOR = new RGB ( 200 , 200 , 200 ) ;
	private final static Point CHANGED_LABEL_LOCATION = new Point ( 222 , 222 ) ;
	private final static Dimension CHANGED_LABEL_SIZE = new Dimension ( 122 , 122 ) ;
	
	private final static RGB CHANGED_LINK_LINE_COLOUR = new RGB ( 111 , 111 ,111 ) ;
	private final static LineStyle CHANGED_LINK_LINE_STYLE = LineStyle.SOLID ;
	private final static int CHANGED_LINK_LINE_WIDTH = 222 ; 
	
	private static final Dimension CHANGED_CANVAS_GRID_SIZE = new Dimension ( 100 , 100) ;
	private static final boolean CHANGED_CANVAS_GRID_ENABLED = false ;
	private static final boolean CHANGED_CANVAS_SNAP_ENABLED = false ;
	private static final RGB CHANGED_CANVAS_BACKGROUND_COLOUR =  new RGB ( 123 , 123 ,123 ) ;
	private static final Dimension CHANGED_CANVAS_SIZE = new Dimension ( 121 , 121 ) ;
	private static final String SOURCE_DATA_FILE = "Acceptance Test/DBConsistencyTestSourceData/DBSourceData.xml";
	private static final int NUM_ROOT_NODE_SHAPES = 2;
	private static final int NUM_ROOT_NODE_LABELS = 4;
	private static final int NUM_MODEL_SHAPES = 8;
	private static final int NUM_MODEL_LABELS = 8;
	private static final int NUM_MODEL_EDGES = 9;
	private static final int NUM_ROOT_NODE_EDGES = 4;
//	private static final int EXPECTED_NUM_PROPS_PER_SHAPE = 1;

	private static final String DELETED_CANVAS_DATA_FILE = "Acceptance Test/org/pathwayeditor/businessobjects/hibernate/helpers/DeletedCanvasExpectedData.xml";
	private static final String EXPECTED_SHAPE = "curbounds 90 90 arc";
	private static final int SHAPE_ATTRIB5 = 5;
	private static final int SHAPE_ATTRIB8 = 8;
	private static final int SHAPE_ATTRIB1 = 1;
	private static final int SHAPE_ATTRIB2 = 2;
	private static final int LINK1_ATTRIB = 9;
	private static final int SHAPE_ATTRIB3 = 3;
	private static final int SHAPE_ATTRIB7 = 7;
	
	
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.integrationtestutils.GenericTester#doAdditionalSetUp()
	 */
	@Override
	protected void doAdditionalSetUp() {
		repository = this.getRepositoryPersistenceManager().getRepository();
		loadData();
	}
	
	private void loadData ()
	{
//		rootFolder = repository.getRootFolder() ;
		
		subFolder1 = (ISubFolder)repository.getFolderByPath( SUBFOLDER1_PATH) ;
		
		mapDiagram1 = subFolder1.mapIterator().next() ;
		
		
		
		map1Manager = this.getRepositoryPersistenceManager().getMapPersistenceManager(mapDiagram1) ;
		map1Manager.open() ;
		dbCanvas = map1Manager.getCanvas() ;
		
		
		
		dbModel = dbCanvas.getModel() ;
//		dbRootNode = dbModel.getRootNode() ;
		
		dbNotationSubSystem = dbCanvas.getNotationSubsystem() ;
		
		
//		Iterator<IShapeNode> rootNodeChildrenIterator = dbRootNode.getSubModel().shapeNodeIterator() ;
//		
//		while ( rootNodeChildrenIterator.hasNext())
//		{
//			IShapeNode tempShapeNode = rootNodeChildrenIterator.next();
//			
//			if ( tempShapeNode.getIndex() ==1 )
//			{
//				shapeNode1 = tempShapeNode ;
//			}
//			else
//			{
//				shapeNode2 = tempShapeNode ;
//			}
//			
//		}
//		
//		Iterator<IShapeNode> shape1ChildIterator = shapeNode1.getSubModel().shapeNodeIterator() ;
//		while ( shape1ChildIterator.hasNext())
//		{
//			IShapeNode tempShapeNode = shape1ChildIterator.next();
//			
//			if ( tempShapeNode.getIndex() ==3 )
//			{
//				shapeNode3 = tempShapeNode ;
//			}
////			else
////			if  ( tempShapeNode.getIndex() ==4 )
////			{
////				shapeNode4 = tempShapeNode ;
////			}
//			
//		}
//		
//		Iterator<IShapeNode> shape2ChildIterator = shapeNode2.getSubModel().shapeNodeIterator() ;
//		while ( shape2ChildIterator.hasNext())
//		{
//			IShapeNode tempShapeNode = shape2ChildIterator.next();
//			
//			if ( tempShapeNode.getIndex() ==5 )
//			{
//				shapeNode5 = tempShapeNode ;
//			}
//			else
//			if  ( tempShapeNode.getIndex() ==6 )
//			{
//				shapeNode6 = tempShapeNode ;
//			}
//			
//		}
		
//		shapeNode7 = shapeNode6.getSubModel().shapeNodeIterator().next() ;
//		
//		shapeNode8 = shapeNode7.getSubModel().shapeNodeIterator().next() ;
		
		
//		Iterator<ILinkEdge> rootNodeEdges = dbRootNode.getSubModel().linkIterator() ;	
//		
//		while ( rootNodeEdges.hasNext())
//		{
//			ILinkEdge tempLinkEdge = rootNodeEdges.next() ;
//			
//			if ( tempLinkEdge.getIndex() == 0 )
//			{
//				linkEdge1 = tempLinkEdge ;
//			}
//			if ( tempLinkEdge.getIndex() == 1 )
//			{
//				linkEdge2 = tempLinkEdge ;
//			}
//			if ( tempLinkEdge.getIndex() == 2 )
//			{
//				linkEdge3 = tempLinkEdge ;
//			}
//			if ( tempLinkEdge.getIndex() == 3 )
//			{
//				linkEdge4 = tempLinkEdge ;
//			}
//			if ( tempLinkEdge.getIndex() == 6 )
//			{
//				linkEdge7 = tempLinkEdge ;
//			}
//		}
		
		
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.integrationtestutils.GenericTester#doAdditionalTearDown()
	 */
	@Override
	protected void doAdditionalTearDown() {
		repository = null ;
//		rootFolder = null;
		subFolder1 = null;
//		subFolder2 = null;
		mapDiagram1 = null;
		dbCanvas = null;
		dbModel = null;
//		dbRootNode = null;
//		
//		shapeNode1 =null;
//		shapeNode2 = null;
//		shapeNode3 = null;
//		shapeNode4 =null;
//		shapeNode5 =null;
//		shapeNode6 = null;
//		shapeNode7 = null;
//		shapeNode8 = null;
		
//		linkEdge1 = null;
//		linkEdge2 = null;
//		linkEdge3 = null;
//		linkEdge4 = null;
//		linkEdge5 = null;
//		linkEdge6 = null;
//		linkEdge7 = null;
//		linkEdge8 = null;
//		linkEdge9 = null;
		
//		newNode = null;
//		newLabel = null;
//		newLinkEdge = null;
		
		dbNotationSubSystem = null;
		

	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.integrationtestutils.GenericTester#getDbUnitDataFilePath()
	 */
	@Override
	protected String getDbUnitDataFilePath() {
		return SOURCE_DATA_FILE;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.integrationtestutils.GenericTester#getTestRepositoryName()
	 */
	@Override
	protected String getTestRepositoryName() {
		return REPOSITORY_NAME ;
	}

	@Test
	public void testLoadedModelIsValid() {
		assertTrue("model valid", this.dbModel.isValid());
	}
	
	@Test
	public void testCreateNewShapeNode () throws Exception	{
		IShapeNodeFactory nodeFactory = this.dbModel.getRootNode().getSubModel().shapeNodeFactory() ;
		nodeFactory.setObjectType(this.dbNotationSubSystem.getSyntaxService().getShapeObjectType(StubShapeAObjectType.UNIQUE_ID)  ) ;
		
		IShapeNode newNode = nodeFactory.createShapeNode() ;
		newNode.getAttribute().setLocation(NEW_NODE_LOCATION) ;
		newNode.getAttribute().setShapeDefinition(EXPECTED_SHAPE) ;
		map1Manager.synchronise();map1Manager.close(true);
		super.compareDatabase(SOURCE_DATA_FILE, CREATED_NODE_VALIDATION);
	}
	
	@Test
	public void testCreateTwoShapeNodesOneChildOfAnother () throws Exception {
		IShapeNodeFactory nodeFactory = this.dbModel.getRootNode().getSubModel().shapeNodeFactory() ;
		nodeFactory.setObjectType(this.dbNotationSubSystem.getSyntaxService().getShapeObjectType(StubShapeAObjectType.UNIQUE_ID)  ) ;
		
		IShapeNode newNode = nodeFactory.createShapeNode() ;
		newNode.getAttribute().setLocation(NEW_NODE_LOCATION) ;
		newNode.getAttribute().setShapeDefinition(EXPECTED_SHAPE) ;
		
		IShapeNodeFactory newNodeFactory = newNode.getSubModel().shapeNodeFactory() ;
		newNodeFactory.setObjectType(this.dbNotationSubSystem.getSyntaxService().getShapeObjectType(StubShapeBObjectType.UNIQUE_ID)  ) ;
		IShapeNode newNode2 = newNodeFactory.createShapeNode() ;
		newNode2.getAttribute().setLocation(NEW_NODE_LOCATION) ;
		newNode2.getAttribute().setShapeDefinition(EXPECTED_SHAPE) ;
		
		map1Manager.synchronise();map1Manager.close(true);
		this.compareDatabase(CREATED_TWO_NODE_VALIDATION);
	}
	
	@Test
	public void testCreateNewLabelNode () throws Exception {
		final String propTestName = "TextProperty";
		final Point testLocation = new Point(10, 20);
		final Dimension testSize = new Dimension(30, 20);
		final Dimension testLabelSize = new Dimension(20, 10);
		final Point expectedLabelLocation = new Point(0, 0);
		IShapeNodeFactory nodeFactory = this.dbModel.getRootNode().getSubModel().shapeNodeFactory() ;
		nodeFactory.setObjectType(this.dbNotationSubSystem.getSyntaxService().getShapeObjectType(StubShapeAObjectType.UNIQUE_ID)  ) ;
		
		IShapeNode newNode = nodeFactory.createShapeNode() ;
		newNode.getAttribute().setLocation(testLocation);
		newNode.getAttribute().setSize(testSize);
		IAnnotationProperty aProperty = newNode.getAttribute().getProperty(propTestName);
		assertNotNull ( "property is not null" , aProperty) ;
		assertTrue("can visualise", aProperty.canVisualiseProperty());
		assertFalse("not displayed", aProperty.isDisplayed());
		assertNull("label not created", aProperty.getLabel());
		aProperty.setDisplayed(true);
		aProperty.getLabel().setSize(testLabelSize);
		assertNotNull("label created", aProperty.getLabel());
		assertEquals("expected label location", expectedLabelLocation, aProperty.getLabel().getLocation());
		map1Manager.synchronise();map1Manager.close(true);
		super.compareDatabase(SOURCE_DATA_FILE, CREATED_LABEL_VALIDATION);
	}

	@Test
	public void testCreateNewEdgeLink () throws Exception {
		IShapeNode shapeNode5 = this.dbCanvas.getShapeAttribute(SHAPE_ATTRIB5).getCurrentDrawingElement();
		ILinkEdgeFactory linkFactory = shapeNode5.getSubModel().linkEdgeFactory() ;
		linkFactory.setObjectType(this.dbNotationSubSystem.getSyntaxService().getLinkObjectType(StubLinkBObjectType.UNIQUE_ID)) ;
		linkFactory.setShapeNodePair(shapeNode5, shapeNode5) ;

		linkFactory.createLinkEdge() ;
		
		
		map1Manager.synchronise();map1Manager.close(true);
		super.compareDatabase(SOURCE_DATA_FILE, CREATED_LINK_VALIDATION);
	}
	
	@Test
	public void testDeleteShapeNode () throws Exception	{
		IShapeNode shapeNode8 = this.dbCanvas.getShapeAttribute(SHAPE_ATTRIB8).getCurrentDrawingElement();
		ISelectionFactory objectSelection = this.dbModel.newSelectionFactory() ;
		
		objectSelection.addDrawingNode(shapeNode8);
		this.dbModel.removeSubgraph(objectSelection.createGeneralSelection()) ;
		assertTrue("model is valid after removal", this.dbModel.isValid());
		map1Manager.synchronise();map1Manager.close(true);
		this.compareDatabase(DELETED_NODE_VALIDATION);
	}
	
	@Test
	public void testDeleteLabelNode () throws Exception	{
		final String testPropName = "TextProperty";
		IShapeNode shapeNode1 = this.dbCanvas.getShapeAttribute(SHAPE_ATTRIB1).getCurrentDrawingElement();
		IAnnotationProperty testProp = shapeNode1.getAttribute().getProperty(testPropName); 
		assertTrue("can display", testProp.canVisualiseProperty());
		assertTrue("is displayed", testProp.isDisplayed());
		testProp.setDisplayed(false);
		assertFalse("not displayed", testProp.isDisplayed());
		assertNull("no label", testProp.getLabel());
		assertTrue("model is valid after removal", this.dbModel.isValid());
		map1Manager.synchronise();map1Manager.close(true);
		this.compareDatabase(DELETED_LABEL_VALIDATION);
	}
	
	@Test
	public void testMoveNode () throws Exception {
		IShapeNode shapeNode = this.dbCanvas.getShapeAttribute(SHAPE_ATTRIB5).getCurrentDrawingElement();
		ISelectionFactory objectSelection = this.dbModel.newSelectionFactory() ;
		objectSelection.addDrawingNode(shapeNode) ;
		assertTrue("model is valid before move", this.dbModel.isValid());
		this.dbModel.getRootNode().getSubModel().moveHere(objectSelection.createGeneralSelection()) ;
		assertTrue("model is valid after move", this.dbModel.isValid());
		map1Manager.synchronise();
		map1Manager.close(true);
		this.compareDatabase(MOVED_NODE_VALIDATION);
	}
	
	@Test
	public void testCopyNode () throws Exception {
		assertTrue("model is valid before copy", this.dbModel.isValid());
		assertEquals("root has correct shapes", NUM_ROOT_NODE_SHAPES, this.dbModel.getRootNode().getSubModel().numShapeNodes());
		assertEquals("root has an same labels", NUM_ROOT_NODE_LABELS, this.dbModel.getRootNode().getSubModel().numLabelNodes());
		assertEquals("model correct num shapes", NUM_MODEL_SHAPES, this.dbModel.numShapeNodes());
		assertEquals("model has same labels", NUM_MODEL_LABELS, this.dbModel.numLabelNodes());
		assertEquals("model has same edges", NUM_MODEL_EDGES, this.dbModel.numLinkEdges());

		IShapeNode shapeNode = this.dbCanvas.getShapeAttribute(SHAPE_ATTRIB5).getCurrentDrawingElement();
		ISelectionFactory objectSelection = this.dbModel.newSelectionFactory() ;
		objectSelection.addDrawingNode(shapeNode) ;
		
		this.dbModel.getRootNode().getSubModel().copyHere(objectSelection.createGeneralSelection()) ;
		assertTrue("model is valid after copy", this.dbModel.isValid());
		assertEquals("root has an extra shape", NUM_ROOT_NODE_SHAPES + 1, this.dbModel.getRootNode().getSubModel().numShapeNodes());
		assertEquals("root has an same labels", NUM_ROOT_NODE_LABELS, this.dbModel.getRootNode().getSubModel().numLabelNodes());
		assertEquals("model extra shape", NUM_MODEL_SHAPES + 1, this.dbModel.numShapeNodes());
		assertEquals("model has same labels", NUM_MODEL_LABELS, this.dbModel.numLabelNodes());
		assertEquals("model has same edges", NUM_MODEL_EDGES, this.dbModel.numLinkEdges());
		map1Manager.synchronise();map1Manager.close(true);
		this.compareDatabase(SOURCE_DATA_FILE, COPIED_NODE_VALIDATION);
	}
	
	
	@Test
	public void testCopyEntireGraphBySelectingTheTopNodes () throws Exception {
		ISelectionFactory objectSelection = this.dbModel.newSelectionFactory() ;
		IShapeNode shapeNode1 = this.dbCanvas.getShapeAttribute(SHAPE_ATTRIB1).getCurrentDrawingElement();
		IShapeNode shapeNode2 = this.dbCanvas.getShapeAttribute(SHAPE_ATTRIB2).getCurrentDrawingElement();
		objectSelection.addDrawingNode(shapeNode1) ;
		objectSelection.addDrawingNode(shapeNode2) ;
		this.dbModel.getRootNode().getSubModel().copyHere(objectSelection.createGeneralSelection()) ;
		assertTrue("model is valid after copy", this.dbModel.isValid());
		IModel model = this.dbCanvas.getModel();
		ISubModel rootModel = model.getRootNode().getSubModel();
		assertEquals("model has additional nodes", NUM_MODEL_SHAPES + 8, model.numShapeNodes());
		assertEquals("model has additional labels", NUM_MODEL_LABELS + 8, model.numLabelNodes());
		assertEquals("model has additional edges", NUM_MODEL_EDGES + 9, model.numLinkEdges());
		assertEquals("root has additional nodes", NUM_ROOT_NODE_SHAPES + 2, rootModel.numShapeNodes());
		assertEquals("root has additional labels", NUM_ROOT_NODE_LABELS + 4, rootModel.numLabelNodes());
		assertEquals("root has additional edges", NUM_ROOT_NODE_EDGES + 4, rootModel.numLinkEdges());
		
		map1Manager.synchronise();map1Manager.close(true);
		this.compareDatabase(SOURCE_DATA_FILE, COPY_WHOLE_VALIDATION);
	}
	
	@Test
	public void testDeleteEdge () throws Exception {
		ILinkEdge linkEdge1 = this.dbCanvas.getLinkAttribute(LINK1_ATTRIB).getCurrentDrawingElement(); 
		ISelectionFactory objectSelection = this.dbModel.newSelectionFactory() ;
		objectSelection.addLink(linkEdge1) ;
		
		this.dbModel.removeSubgraph(objectSelection.createGeneralSelection()) ;
		assertTrue("model is valid after delete", this.dbModel.isValid());
		map1Manager.synchronise();map1Manager.close(true);
		this.compareDatabase(DELETED_EDGE_VALIDATION);
	}
	
	
	@Test
	public void testDeleteTwoConnectedShapes () throws Exception {
		IShapeNode shapeNode3 = this.dbCanvas.getShapeAttribute(SHAPE_ATTRIB3).getCurrentDrawingElement();
		IShapeNode shapeNode7 = this.dbCanvas.getShapeAttribute(SHAPE_ATTRIB7).getCurrentDrawingElement();
		ISelectionFactory objectSelection = this.dbModel.newSelectionFactory() ;
		objectSelection.addDrawingNode(shapeNode3) ;
		objectSelection.addDrawingNode(shapeNode7) ;
		
		this.dbModel.removeSubgraph(objectSelection.createGeneralSelection()) ;
		map1Manager.synchronise();map1Manager.close(true);
		this.compareDatabase(DELETED_TWO_SHAPES_VALIDATION);
	}
	
	
	@Test
	public void testDeleteAll () throws Exception {
		IShapeNode shapeNode1 = this.dbCanvas.getShapeAttribute(SHAPE_ATTRIB1).getCurrentDrawingElement();
		IShapeNode shapeNode2 = this.dbCanvas.getShapeAttribute(SHAPE_ATTRIB2).getCurrentDrawingElement();
		ISelectionFactory objectSelection = this.dbModel.newSelectionFactory() ;
		objectSelection.addDrawingNode(shapeNode1) ;
		objectSelection.addDrawingNode(shapeNode2) ;
		this.dbModel.removeSubgraph(objectSelection.createGeneralSelection());
		map1Manager.synchronise() ;
		map1Manager.close(true);
		this.compareDatabase(DELETED_ALL_VALIDATION);
	}
	
	@Test
	public void testAlterShapeAttributeValues () throws Exception {
		IShapeNode shapeNode1 = this.dbCanvas.getShapeAttribute(SHAPE_ATTRIB1).getCurrentDrawingElement();
		IShapeAttribute shapeAttribute1 = shapeNode1.getAttribute() ;
		
		shapeAttribute1.setFillColour(CHANGED_SHAPE_FILL_COLOR) ;
		shapeAttribute1.setLineColour(CHANGED_SHAPE_LINE_COLOR) ;
		shapeAttribute1.setLineStyle(CHANGED_SHAPE_LINE_STYLE) ;
		shapeAttribute1.setLineWidth(CHANGED_SHAPE_LINE_WIDTH);
		shapeAttribute1.setLocation(CHANGED_SHAPE_LOCATION) ;
		shapeAttribute1.setSize(CHANGED_SHAPE_SIZE);
		shapeAttribute1.setShapeDefinition(CHANGED_SHAPE_PRIMITIVE_TYRE) ;
		
		map1Manager.synchronise();map1Manager.close(true);
		
		this.compareDatabase(CHANGED_SHAPE_DATA_VALIDATION);
	}
	
	@Test
	public void testAlterLabelAttributeValues () throws Exception {
		IShapeNode shapeNode1 = this.dbCanvas.getShapeAttribute(SHAPE_ATTRIB1).getCurrentDrawingElement();
		ILabelAttribute labelAttribute1 = shapeNode1.getSubModel().labelIterator().next().getAttribute() ;
		
		labelAttribute1.setBackgroundColor(CHANGED_LABEL_BACKGROUND_COLOR) ;
		labelAttribute1.setLocation(CHANGED_LABEL_LOCATION) ;
		labelAttribute1.setSize(CHANGED_LABEL_SIZE) ;
		
		map1Manager.synchronise();map1Manager.close(true);
		
		this.compareDatabase(CHANGED_LABEL_DATA_VALIDATION);
	}
	
	@Test
	public void testAlterLinkAttributeValues () throws Exception {
		ILinkEdge linkEdge1 = this.dbCanvas.getLinkAttribute(LINK1_ATTRIB).getCurrentDrawingElement(); 
		ILinkAttribute linkAttribute1 = linkEdge1.getAttribute() ;
		
		linkAttribute1.setLineColor(CHANGED_LINK_LINE_COLOUR);
		linkAttribute1.setLineStyle(CHANGED_LINK_LINE_STYLE) ;
		linkAttribute1.setLineWidth(CHANGED_LINK_LINE_WIDTH) ;
		
		map1Manager.synchronise();map1Manager.close(true);
		
		this.compareDatabase(CHANGED_LINK_DATA_VALIDATION);
	}
	
	@Test
	public void testAlterCanvasValues () throws Exception
	{
		
		
		dbCanvas.setBackgroundColour(CHANGED_CANVAS_BACKGROUND_COLOUR) ;
		dbCanvas.setCanvasSize(CHANGED_CANVAS_SIZE) ; 
		dbCanvas.setGridEnabled(CHANGED_CANVAS_GRID_ENABLED) ;
		dbCanvas.setSnapToGrid(CHANGED_CANVAS_SNAP_ENABLED) ;
		dbCanvas.setGridSize(CHANGED_CANVAS_GRID_SIZE) ;
		
		map1Manager.synchronise();map1Manager.close(true);
		this.compareDatabase(CHANGED_CANVAS_DATA_VALIDATION);
	}
	
	@Test
	public void testSaveAndRestoreStateDeletingNodes () throws Exception {
		IShapeNode shapeNode1 = this.dbCanvas.getShapeAttribute(SHAPE_ATTRIB1).getCurrentDrawingElement();
		IGraphMomento savedOriginalState = dbCanvas.getModel().getCurrentState() ;
		
		ISelectionFactory objectSelection = this.dbModel.newSelectionFactory() ;
		objectSelection.addDrawingNode(shapeNode1) ;
		
		this.dbModel.removeSubgraph(objectSelection.createGeneralSelection()) ;
		
		map1Manager.synchronise() ;
		
		dbCanvas.getModel().restoreToState(savedOriginalState) ;
		
		map1Manager.synchronise();map1Manager.close(true);
		this.compareDatabase(RESTORED_FROM_DELETED_SHAPE_NODE);
	}
	
	@Test
	public void testSaveAndRestoreStateAddingShape () throws Exception {
		IGraphMomento savedOriginalState = dbCanvas.getModel().getCurrentState() ;
		
		IShapeNodeFactory nodeFactory = this.dbModel.getRootNode().getSubModel().shapeNodeFactory() ;
		nodeFactory.setObjectType(this.dbNotationSubSystem.getSyntaxService().getShapeObjectType(StubShapeAObjectType.UNIQUE_ID)  ) ;
		
		IShapeNode newNode = nodeFactory.createShapeNode() ;
		newNode.getAttribute().setLocation(NEW_NODE_LOCATION) ;
		newNode.getAttribute().setShapeDefinition(EXPECTED_SHAPE) ;
		map1Manager.synchronise() ;
		
		map1Manager.synchronise() ;
		
		dbCanvas.getModel().restoreToState(savedOriginalState) ;
		
		map1Manager.synchronise();map1Manager.close(true);
		this.compareDatabase(RESTORED_FROM_ADDED_SHAPE_NODE);
	}
	
	
	@Test
	public void testSaveAndRestoreStateMovingShape () throws Exception {
		IShapeNode shapeNode = this.dbCanvas.getShapeAttribute(SHAPE_ATTRIB5).getCurrentDrawingElement();
		IGraphMomento savedOriginalState = dbCanvas.getModel().getCurrentState() ;
		
		ISelectionFactory objectSelection = this.dbModel.newSelectionFactory() ;
		objectSelection.addDrawingNode(shapeNode) ;
		this.dbModel.getRootNode().getSubModel().moveHere(objectSelection.createGeneralSelection()) ;
		map1Manager.synchronise() ;
		
		dbCanvas.getModel().restoreToState(savedOriginalState) ;
		
		map1Manager.synchronise();map1Manager.close(true);
		this.compareDatabase(RESTORED_FROM_MOVED_SHAPE_NODE);
	}
	
	@Test
	public void testSaveAndRestoreStateCopyingShape () throws Exception	{
		IShapeNode shapeNode = this.dbCanvas.getShapeAttribute(SHAPE_ATTRIB5).getCurrentDrawingElement();
		IGraphMomento savedOriginalState = dbCanvas.getModel().getCurrentState() ;
		
		ISelectionFactory objectSelection = this.dbModel.newSelectionFactory() ;
		objectSelection.addDrawingNode(shapeNode) ;
		
		this.dbModel.getRootNode().getSubModel().copyHere(objectSelection.createGeneralSelection()) ;
		map1Manager.synchronise();
		
		dbCanvas.getModel().restoreToState(savedOriginalState) ;
		
		map1Manager.synchronise();map1Manager.close(true);
		this.compareDatabase(RESTORED_FROM_COPIED_SHAPE_NODE);
	}
	
	@Test
	public void testSaveAndRestoreDeleteLabel () throws Exception {
		IGraphMomento savedOriginalState = dbCanvas.getModel().getCurrentState() ;
		
		final String testPropName = "TextProperty";
		IShapeNode shapeNode1 = this.dbCanvas.getShapeAttribute(SHAPE_ATTRIB1).getCurrentDrawingElement();
		IAnnotationProperty testProp = shapeNode1.getAttribute().getProperty(testPropName); 
		assertTrue("can display", testProp.canVisualiseProperty());
		assertTrue("is displayed", testProp.isDisplayed());
		testProp.setDisplayed(false);
		assertFalse("not displayed", testProp.isDisplayed());
		assertNull("no label", testProp.getLabel());
		
		map1Manager.synchronise();
		
		dbCanvas.getModel().restoreToState(savedOriginalState) ;
		
		map1Manager.synchronise();map1Manager.close(true);
		this.compareDatabase(RESTORED_FROM_DELETED_LABEL_NODE);
	}
	
	@Test
	public void testSaveAndRestoreDeletedLink () throws Exception {
		ILinkEdge linkEdge1 = this.dbCanvas.getLinkAttribute(LINK1_ATTRIB).getCurrentDrawingElement(); 
		IGraphMomento savedOriginalState = dbCanvas.getModel().getCurrentState() ;
		ISelectionFactory objectSelection = this.dbModel.newSelectionFactory() ;
		objectSelection.addLink(linkEdge1) ;
		
		this.dbModel.removeSubgraph(objectSelection.createGeneralSelection()) ;
		map1Manager.synchronise();
		
		this.dbCanvas.getModel().restoreToState(savedOriginalState) ;
		map1Manager.synchronise();map1Manager.close(true);
		this.compareDatabase(RESTORED_FROM_DELETED_LINK_EDGE);
	}
	
	@Test
	public void testSaveAndRestoreAddLink () throws Exception {
		IShapeNode shapeNode5 = this.dbCanvas.getShapeAttribute(SHAPE_ATTRIB5).getCurrentDrawingElement();
		IGraphMomento savedOriginalState = dbCanvas.getModel().getCurrentState() ;
		
		ILinkEdgeFactory linkFactory = shapeNode5.getSubModel().linkEdgeFactory() ;
		linkFactory.setObjectType(this.dbNotationSubSystem.getSyntaxService().getLinkObjectType(StubLinkBObjectType.UNIQUE_ID)) ;
		linkFactory.setShapeNodePair(shapeNode5, shapeNode5) ;

		linkFactory.createLinkEdge() ;
		
		map1Manager.synchronise();
		
		dbCanvas.getModel().restoreToState(savedOriginalState) ;
		map1Manager.synchronise();map1Manager.close(true);
		this.compareDatabase(RESTORED_FROM_ADDED_LINK_EDGE);
	}
	
	@Test
	public void testSaveAndRestoreStateAddLabel () throws Exception	{
		
		IGraphMomento savedOriginalState = dbCanvas.getModel().getCurrentState() ;
		
		IShapeNodeFactory nodeFactory = this.dbModel.getRootNode().getSubModel().shapeNodeFactory() ;
		nodeFactory.setObjectType(this.dbNotationSubSystem.getSyntaxService().getShapeObjectType(StubShapeAObjectType.UNIQUE_ID)  ) ;
		
		IShapeNode newNode = nodeFactory.createShapeNode() ;
		
		IAnnotationProperty aProperty = newNode.getAttribute().propertyIterator().next() ;
		
		assertNotNull ( "property is not null" , aProperty) ;
		
		aProperty.setDisplayed(true);
//		newLabel = aProperty.getDisplayedLabel().getCurrentDrawingElement();
		
		map1Manager.synchronise() ;
		
		dbCanvas.getModel().restoreToState(savedOriginalState) ;
		
		map1Manager.synchronise();map1Manager.close(true);
		this.compareDatabase(RESTORED_FROM_ADDED_LABEL_NODE);
	}
	
	@Test
	public void testMakeCopyOfGraphViaTheModelAndToAnotherMapDIagram () throws Exception {
		IMap mapDiagram2 = (IMap)this.repository.findRepositoryItemByPath(MAP2_PATH);
		IMapPersistenceManager map2Manager = this.getRepositoryPersistenceManager().getMapPersistenceManager(mapDiagram2) ;
		map2Manager.open();
		map2Manager.createCanvas(dbNotationSubSystem) ;
		ICanvas canvas2 = map2Manager.getCanvas() ;
		assertFalse("cannot copy to yourself", canvas2.canCopyHere(canvas2));
		assertFalse("cannot copy from null", canvas2.canCopyHere(null));
		assertTrue("can copy here", canvas2.canCopyHere(dbCanvas));
		canvas2.copyHere(dbCanvas);
		assertTrue("model is valid", canvas2.getModel().isValid());
		assertEquals("correct num shapes", NUM_MODEL_SHAPES, canvas2.getModel().numShapeNodes());
		assertEquals("correct num labels", NUM_MODEL_LABELS, canvas2.getModel().numLabelNodes());
		assertEquals("correct num links", NUM_MODEL_EDGES, canvas2.getModel().numLinkEdges());
		map2Manager.synchronise();
		this.compareDatabase(SOURCE_DATA_FILE, CANVAS_COPIED_FILE);
	}
	
	@Test
	public void testDeleteCanvas() throws Exception {
		IMap map = (IMap)this.repository.findRepositoryItemByPath(MAP1_PATH);
		IMapPersistenceManager mapManager = this.getRepositoryPersistenceManager().getMapPersistenceManager(map);
		if(!mapManager.isOpen()) {
			mapManager.open();
		}
		assertTrue("canvas exists", mapManager.doesCanvasExist());
		mapManager.deleteCanvas();
		mapManager.close(true);
		this.compareDatabase(DELETED_CANVAS_DATA_FILE);
	}
	
	@Test
	public void testDeleteBendPoint () throws Exception {
		ILinkEdge linkEdge1 = this.dbCanvas.getLinkAttribute(LINK1_ATTRIB).getCurrentDrawingElement(); 
		assertEquals ( "has  1 bendpoint" , 1 , linkEdge1.getAttribute().numBendPoints() );
		linkEdge1.getAttribute().removeBendPoint(linkEdge1.getAttribute().getBendPoint(0));
		assertEquals ( "has 0 bendpoint" , 0 , linkEdge1.getAttribute().numBendPoints() );
		map1Manager.synchronise();map1Manager.close(true);
	}
}
