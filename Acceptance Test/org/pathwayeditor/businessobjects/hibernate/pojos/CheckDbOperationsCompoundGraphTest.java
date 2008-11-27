/**
 * 
 */
package org.pathwayeditor.businessobjects.hibernate.pojos;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;

import org.junit.Ignore;
import org.junit.Test;
import org.pathwayeditor.businessobjects.drawingprimitives.ICanvas;
import org.pathwayeditor.businessobjects.drawingprimitives.IGraphMomento;
import org.pathwayeditor.businessobjects.drawingprimitives.ILabelAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.ILabelNode;
import org.pathwayeditor.businessobjects.drawingprimitives.ILabelNodeFactory;
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkEdge;
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkEdgeFactory;
import org.pathwayeditor.businessobjects.drawingprimitives.IModel;
import org.pathwayeditor.businessobjects.drawingprimitives.IRootNode;
import org.pathwayeditor.businessobjects.drawingprimitives.ISelectionFactory;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeNode;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeNodeFactory;
import org.pathwayeditor.businessobjects.drawingprimitives.ISubModel;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.ConnectionRouter;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LineStyle;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.Location;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.PrimitiveShapeType;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.Size;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationProperty;
import org.pathwayeditor.businessobjects.management.IMapContentPersistenceManager;
import org.pathwayeditor.businessobjects.management.PersistenceManagerException;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationSubsystem;
import org.pathwayeditor.businessobjects.repository.IMap;
import org.pathwayeditor.businessobjects.repository.IRepository;
import org.pathwayeditor.businessobjects.repository.IRootFolder;
import org.pathwayeditor.businessobjects.repository.ISubFolder;
import org.pathwayeditor.bussinessobjects.stubs.notationsubsystem.StubLinkBObjectType;
import org.pathwayeditor.bussinessobjects.stubs.notationsubsystem.StubShapeAObjectType;
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
	
	private IMapContentPersistenceManager map1Manager ;
//	private IMapContentPersistenceManager map2Manager ;
	
	private IRepository repository;
	private IRootFolder rootFolder ;
	private ISubFolder subFolder1 ;
	private ISubFolder subFolder2 ;
	private IMap mapDiagram1 ;
	private ICanvas dbCanvas ;
	private IModel dbModel ;
	private IRootNode dbRootNode ;
	
	private IShapeNode shapeNode1 ;
	private IShapeNode shapeNode2 ;
	private IShapeNode shapeNode3 ;
	private IShapeNode shapeNode4 ;
	private IShapeNode shapeNode5 ;
	private IShapeNode shapeNode6 ;
	private IShapeNode shapeNode7 ;
	private IShapeNode shapeNode8 ;
	
	private ILinkEdge linkEdge1 ;
	private ILinkEdge linkEdge2 ;
	private ILinkEdge linkEdge3;
	private ILinkEdge linkEdge4 ;
	private ILinkEdge linkEdge5 ;
	private ILinkEdge linkEdge6 ;
	private ILinkEdge linkEdge7 ;
	private ILinkEdge linkEdge8 ;
	private ILinkEdge linkEdge9 ;
	
	private IShapeNode newNode ;
	private ILabelNode newLabel ;
	private ILinkEdge newLinkEdge ;
	
	private INotationSubsystem dbNotationSubSystem;
	
	private static final String REPOSITORY_NAME ="repo name" ;
	private static final String SUBFOLDER1_PATH = "/subfolder1/" ;
	private static final String SUBFOLDER2_PATH = "/subfolder2/" ;
	private static final String MAP2_PATH = "/subfolder2/Diagram name2" ;
	
	private static final Location NEW_NODE_LOCATION = new Location ( 75 , 75 ) ;
	private static final Size NEW_NODE_SIZE = new Size (25 , 25) ;
	

	private final static String CREATED_LINK_VALIDATION = "Acceptance Test/DBConsistencyTestValidationData/CreatedLinkEdge.xml" ;
	private final static String CREATED_TWO_NODE_VALIDATION = "Acceptance Test/DBConsistencyTestValidationData/CreatedTwoShapeNode.xml" ;
	private final static String CREATED_NODE_VALIDATION = "Acceptance Test/DBConsistencyTestValidationData/CreatedShapeNode.xml" ;
	private final static String CREATED_LABEL_VALIDATION = "Acceptance Test/DBConsistencyTestValidationData/CreatedLabelNode.xml" ;
	private final static String DELETED_NODE_VALIDATION = "Acceptance Test/DBConsistencyTestValidationData/DeleteShapeNode.xml" ;
	private final static String DELETED_LABEL_VALIDATION = "Acceptance Test/DBConsistencyTestValidationData/DeletedLabelNodeData.xml" ;
	private final static String MOVED_NODE_VALIDATION = "Acceptance Test/DBConsistencyTestValidationData/MovedNodeValidation.xml" ;
	private final static String COPIED_NODE_VALIDATION = "Acceptance Test/DBConsistencyTestValidationData/CopiedShapeNode.xml" ;
	private final static String COPY_WHOLE_VALIDATION = "Acceptance Test/DBConsistencyTestValidationData/CopyWholeGraphData.xml" ;
	private final static String DELETED_EDGE_VALIDATION = "Acceptance Test/DBConsistencyTestValidationData/DeletedEdge.xml" ;
	private final static String DELETED_TWO_SHAPES_VALIDATION = "Acceptance Test/DBConsistencyTestValidationData/DeletedTwoShapes.xml" ;
	private final static String DELETED_ALL_VALIDATION = "Acceptance Test/DBConsistencyTestValidationData/DeleteAll.xml" ;
	private final static String CHANGED_SHAPE_DATA_VALIDATION = "Acceptance Test/DBConsistencyTestValidationData/ChangedShapeData.xml" ;
	private final static String CHANGED_LABEL_DATA_VALIDATION = "Acceptance Test/DBConsistencyTestValidationData/ChangedLabelData.xml" ;
	private final static String CHANGED_LINK_DATA_VALIDATION = "Acceptance Test/DBConsistencyTestValidationData/ChangedLinkData.xml" ;
	private final static String CHANGED_CANVAS_DATA_VALIDATION = "Acceptance Test/DBConsistencyTestValidationData/ChangedCanvasData.xml" ;
	private final static String RESTORED_FROM_DELETED_SHAPE_NODE = "Acceptance Test/DBConsistencyTestValidationData/RestoredDeletedShapeData.xml" ;
	private final static String RESTORED_FROM_ADDED_SHAPE_NODE = "Acceptance Test/DBConsistencyTestValidationData/RestoredSavedShapeData.xml" ;
	private final static String RESTORED_FROM_MOVED_SHAPE_NODE = "Acceptance Test/DBConsistencyTestValidationData/RestoredMovedShapeData.xml" ;
	private final static String RESTORED_FROM_COPIED_SHAPE_NODE = "Acceptance Test/DBConsistencyTestValidationData/RestoredCopiedShapeData.xml" ;
	private final static String RESTORED_FROM_DELETED_LABEL_NODE = "Acceptance Test/DBConsistencyTestValidationData/RestoredDeletedLabelData.xml" ;
	private final static String RESTORED_FROM_DELETED_LINK_EDGE = "Acceptance Test/DBConsistencyTestValidationData/RestoredDeletedLinkData.xml" ;
	private final static String RESTORED_FROM_ADDED_LINK_EDGE = "Acceptance Test/DBConsistencyTestValidationData/RestoredAddedLinkData.xml" ;
	private final static String RESTORED_FROM_ADDED_LABEL_NODE = "Acceptance Test/DBConsistencyTestValidationData/RestoredAddedLabelData.xml" ;
	
	
	private final static String CHANGED_SHAPE_NAME = "new shape name" ;
	private final static String CHANGED_SHAPE_DESCRIPTION = "new shape description" ;
	private final static String CHANGED_SHAPE_DETAILED_DESCRIPTION = "new shape detailed description" ;
	private final static RGB CHANGED_SHAPE_FILL_COLOR = new RGB ( 1 ,1 ,1 ) ;
	private final static RGB CHANGED_SHAPE_LINE_COLOR = new RGB ( 2 ,2 ,2 ) ;
	private final static LineStyle CHANGED_SHAPE_LINE_STYLE = LineStyle.SOLID ;
	private final static int CHANGED_SHAPE_LINE_WIDTH = 100 ; 
	private final static int CHANGED_SHAPE_PADDING = 150 ;
	private final static PrimitiveShapeType CHANGED_SHAPE_PRIMITIVE_TYRE = PrimitiveShapeType.UP_CHEVRON ;
	private final static Location CHANGED_SHAPE_LOCATION = new Location ( 500 , 500 ) ;
	private final static Size CHANGED_SHAPE_SIZE = new Size ( 400 ,400 ) ;
	private final static String CHANGED_SHAPE_URL = "http://www.something.co.uk" ;
	
	private final static RGB CHANGED_LABEL_BACKGROUND_COLOR = new RGB ( 200 , 200 , 200 ) ;
	private final static Location CHANGED_LABEL_LOCATION = new Location ( 222 , 222 ) ;
	private final static Size CHANGED_LABEL_SIZE = new Size ( 122 , 122 ) ;
	
	private final static String CHANGED_LINK_NAME = "changed link name" ;
	private final static String CHANGED_LINK_DESCRIPTION = "changed link description" ;
	private final static String CHANGED_LINK_DETAILED_DESCRIPTION = "changed link detailed description" ;
	private final static RGB CHANGED_LINK_LINE_COLOUR = new RGB ( 111 , 111 ,111 ) ;
	private final static LineStyle CHANGED_LINK_LINE_STYLE = LineStyle.SOLID ;
	private final static int CHANGED_LINK_LINE_WIDTH = 222 ; 
	private final static ConnectionRouter CHANGED_LINK_CONNECTION_ROUTER = ConnectionRouter.SHORTEST_PATH ;
	private final static String CHANGED_LINK_URL = "http://www.changed.co.uk" ;
	
	private static final Size CHANGED_CANVAS_GRID_SIZE = new Size ( 100 , 100) ;
	private static final boolean CHANGED_CANVAS_GRID_ENABLED = false ;
	private static final boolean CHANGED_CANVAS_SNAP_ENABLED = false ;
	private static final RGB CHANGED_CANVAS_BACKGROUND_COLOUR =  new RGB ( 123 , 123 ,123 ) ;
	private static final Size CHANGED_CANVAS_SIZE = new Size ( 121 , 121 ) ;
	private static final String SOURCE_DATA_FILE = "Acceptance Test/DBConsistencyTestSourceData/DBSourceData.xml";
	private static final int NUM_ROOT_NODE_SHAPES = 2;
	private static final int NUM_ROOT_NODE_LABELS = 4;
	private static final int NUM_MODEL_SHAPES = 8;
	private static final int NUM_MODEL_LABELS = 8;
	private static final int NUM_MODEL_EDGES = 9;
	private static final int NUM_ROOT_NODE_EDGES = 4;
	private static final int EXPECTED_NUM_PROPS_PER_SHAPE = 1;
	
	
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.integrationtestutils.GenericTester#doAdditionalSetUp()
	 */
	@Override
	protected void doAdditionalSetUp() {
		try {
			repository = this.getRepositoryPersistenceManager().getRepository();
			loadData();
		} catch (PersistenceManagerException e) {
			throw new RuntimeException(e);
		}
	}
	
	private void loadData () throws PersistenceManagerException 
	{
		rootFolder = repository.getRootFolder() ;
		
		subFolder1 = (ISubFolder)repository.getFolderByPath( SUBFOLDER1_PATH) ;
		
		mapDiagram1 = subFolder1.getMapIterator().next() ;
		
		
		
		map1Manager = this.getRepositoryPersistenceManager().openMap(mapDiagram1) ;
		map1Manager.loadContent() ;
		dbCanvas = map1Manager.getCanvas() ;
		
		
		
		dbModel = dbCanvas.getModel() ;
		dbRootNode = dbModel.getRootNode() ;
		
		dbNotationSubSystem = dbCanvas.getNotationSubsystem() ;
		
		
		Iterator<IShapeNode> rootNodeChildrenIterator = dbRootNode.getSubModel().shapeIterator() ;
		
		while ( rootNodeChildrenIterator.hasNext())
		{
			IShapeNode tempShapeNode = rootNodeChildrenIterator.next();
			
			if ( tempShapeNode.getIndex() ==1 )
			{
				shapeNode1 = tempShapeNode ;
			}
			else
			{
				shapeNode2 = tempShapeNode ;
			}
			
		}
		
		Iterator<IShapeNode> shape1ChildIterator = shapeNode1.getSubModel().shapeIterator() ;
		while ( shape1ChildIterator.hasNext())
		{
			IShapeNode tempShapeNode = shape1ChildIterator.next();
			
			if ( tempShapeNode.getIndex() ==3 )
			{
				shapeNode3 = tempShapeNode ;
			}
			else
			if  ( tempShapeNode.getIndex() ==4 )
			{
				shapeNode4 = tempShapeNode ;
			}
			
		}
		
		Iterator<IShapeNode> shape2ChildIterator = shapeNode2.getSubModel().shapeIterator() ;
		while ( shape2ChildIterator.hasNext())
		{
			IShapeNode tempShapeNode = shape2ChildIterator.next();
			
			if ( tempShapeNode.getIndex() ==5 )
			{
				shapeNode5 = tempShapeNode ;
			}
			else
			if  ( tempShapeNode.getIndex() ==6 )
			{
				shapeNode6 = tempShapeNode ;
			}
			
		}
		
		shapeNode7 = shapeNode6.getSubModel().shapeIterator().next() ;
		
		shapeNode8 = shapeNode7.getSubModel().shapeIterator().next() ;
		
		
		Iterator<ILinkEdge> rootNodeEdges = dbRootNode.getSubModel().linkIterator() ;	
		
		while ( rootNodeEdges.hasNext())
		{
			ILinkEdge tempLinkEdge = rootNodeEdges.next() ;
			
			if ( tempLinkEdge.getIndex() == 0 )
			{
				linkEdge1 = tempLinkEdge ;
			}
			if ( tempLinkEdge.getIndex() == 1 )
			{
				linkEdge2 = tempLinkEdge ;
			}
			if ( tempLinkEdge.getIndex() == 2 )
			{
				linkEdge3 = tempLinkEdge ;
			}
			if ( tempLinkEdge.getIndex() == 3 )
			{
				linkEdge4 = tempLinkEdge ;
			}
			if ( tempLinkEdge.getIndex() == 6 )
			{
				linkEdge7 = tempLinkEdge ;
			}
		}
		
		
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.integrationtestutils.GenericTester#doAdditionalTearDown()
	 */
	@Override
	protected void doAdditionalTearDown() {
		repository = null ;
		rootFolder = null;
		subFolder1 = null;
		subFolder2 = null;
		mapDiagram1 = null;
		dbCanvas = null;
		dbModel = null;
		dbRootNode = null;
		
		shapeNode1 =null;
		shapeNode2 = null;
		shapeNode3 = null;
		shapeNode4 =null;
		shapeNode5 =null;
		shapeNode6 = null;
		shapeNode7 = null;
		shapeNode8 = null;
		
		linkEdge1 = null;
		linkEdge2 = null;
		linkEdge3 = null;
		linkEdge4 = null;
		linkEdge5 = null;
		linkEdge6 = null;
		linkEdge7 = null;
		linkEdge8 = null;
		linkEdge9 = null;
		
		newNode = null;
		newLabel = null;
		newLinkEdge = null;
		
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
	public void testCreateNewShapeNode () throws Exception
	{
		
		IShapeNodeFactory nodeFactory = dbRootNode.getSubModel().shapeNodeFactory() ;
		nodeFactory.setObjectType(this.dbNotationSubSystem.getSyntaxService().getShapeObjectType(StubShapeAObjectType.UNIQUE_ID)  ) ;
		
		newNode = nodeFactory.createShapeNode() ;
		newNode.getAttribute().setLocation(NEW_NODE_LOCATION) ;
		newNode.getAttribute().setPrimitiveShape(PrimitiveShapeType.ARC) ;
		map1Manager.synchronise() ;
		super.compareDatabase(SOURCE_DATA_FILE, CREATED_NODE_VALIDATION);
	}
	
	@Test
	public void testCreateTwoShapeNodesOneChildOfAnother () throws Exception
	{
		
		IShapeNodeFactory nodeFactory = dbRootNode.getSubModel().shapeNodeFactory() ;
		nodeFactory.setObjectType(this.dbNotationSubSystem.getSyntaxService().getShapeObjectType(StubShapeAObjectType.UNIQUE_ID)  ) ;
		
		newNode = nodeFactory.createShapeNode() ;
		newNode.getAttribute().setLocation(NEW_NODE_LOCATION) ;
		newNode.getAttribute().setPrimitiveShape(PrimitiveShapeType.ARC) ;
		
		IShapeNodeFactory newNodeFactory = newNode.getSubModel().shapeNodeFactory() ;
		newNodeFactory.setObjectType(this.dbNotationSubSystem.getSyntaxService().getShapeObjectType(StubShapeAObjectType.UNIQUE_ID)  ) ;
		IShapeNode newNode2 = newNodeFactory.createShapeNode() ;
		newNode2.getAttribute().setLocation(NEW_NODE_LOCATION) ;
		newNode2.getAttribute().setPrimitiveShape(PrimitiveShapeType.ARC) ;
		
		map1Manager.synchronise() ;
		this.compareDatabase(CREATED_TWO_NODE_VALIDATION);
	}
	
	@Test
	public void testCreateNewLabelNode () throws Exception 
	{
		
		
		IShapeNodeFactory nodeFactory = dbRootNode.getSubModel().shapeNodeFactory() ;
		nodeFactory.setObjectType(this.dbNotationSubSystem.getSyntaxService().getShapeObjectType(StubShapeAObjectType.UNIQUE_ID)  ) ;
		
		newNode = nodeFactory.createShapeNode() ;
		
		IAnnotationProperty aProperty = newNode.getAttribute().propertyIterator().next() ;
		
		assertNotNull ( "property is not null" , aProperty) ;
		
		ILabelNodeFactory labelFactory = newNode.getSubModel().labelNodeFactory() ;

		labelFactory.setProperty(aProperty) ;
		newLabel = labelFactory.createLabel() ;
		
		map1Manager.synchronise() ;
		super.compareDatabase(SOURCE_DATA_FILE, CREATED_LABEL_VALIDATION);
	}

	@Test
	public void testCreateNewEdgeLink () throws Exception 
	{
		
		ILinkEdgeFactory linkFactory = shapeNode5.getSubModel().linkEdgeFactory() ;
		linkFactory.setObjectType(this.dbNotationSubSystem.getSyntaxService().getLinkObjectType(StubLinkBObjectType.UNIQUE_ID)) ;
		linkFactory.setShapeNodePair(shapeNode5, shapeNode5) ;

		newLinkEdge = linkFactory.createLinkEdge() ;
		
		
		map1Manager.synchronise() ;
		super.compareDatabase(SOURCE_DATA_FILE, CREATED_LINK_VALIDATION);
	}
	
	@Test
	public void testDeleteShapeNode () throws Exception
	{
		ISelectionFactory objectSelection = this.dbModel.newSelectionFactory() ;
		
		objectSelection.addDrawingNode(this.shapeNode8);
		this.dbModel.removeSubgraph(objectSelection.createSelection()) ;
		assertTrue("model is valid after removal", this.dbModel.isValid());
		map1Manager.synchronise() ;
		this.compareDatabase(DELETED_NODE_VALIDATION);
	}
	
	@Test
	public void testDeleteLabelNode () throws Exception
	{
		ILabelNode labelNode1 = shapeNode1.getSubModel().labelIterator().next() ;
		
		ISelectionFactory objectSelection = this.dbModel.newSelectionFactory() ;
		
		objectSelection.addDrawingNode(labelNode1);
		
		this.dbModel.removeSubgraph(objectSelection.createSelection()) ;
		assertTrue("model is valid after removal", this.dbModel.isValid());
		map1Manager.synchronise() ;
		this.compareDatabase(DELETED_LABEL_VALIDATION);
	}
	
	@Test
	public void testMoveNode () throws Exception
	{
		
		
		ISelectionFactory objectSelection = this.dbModel.newSelectionFactory() ;
		objectSelection.addDrawingNode(shapeNode8) ;
		
		dbRootNode.getSubModel().moveHere(objectSelection.createSelection()) ;
		assertTrue("model is valid after move", dbRootNode.getModel().isValid());
		map1Manager.synchronise() ;
		this.compareDatabase(MOVED_NODE_VALIDATION);
	}
	
	@Test
	public void testCopyNode () throws Exception
	{
		
		
		assertTrue("model is valid before copy", dbRootNode.getModel().isValid());
		assertEquals("root has correct shapes", NUM_ROOT_NODE_SHAPES, this.dbModel.getRootNode().getSubModel().numShapeNodes());
		assertEquals("root has an same labels", NUM_ROOT_NODE_LABELS, this.dbModel.getRootNode().getSubModel().numLabelNodes());
		assertEquals("model correct num shapes", NUM_MODEL_SHAPES, this.dbModel.numShapeNodes());
		assertEquals("model has same labels", NUM_MODEL_LABELS, this.dbModel.numLabelNodes());
		assertEquals("model has same edges", NUM_MODEL_EDGES, this.dbModel.numLinkEdges());

		ISelectionFactory objectSelection = this.dbModel.newSelectionFactory() ;
		objectSelection.addDrawingNode(shapeNode8) ;
		
		dbRootNode.getSubModel().copyHere(objectSelection.createSelection()) ;
		assertTrue("model is valid after copy", dbRootNode.getModel().isValid());
		assertEquals("root has an extra shape", NUM_ROOT_NODE_SHAPES + 1, this.dbModel.getRootNode().getSubModel().numShapeNodes());
		assertEquals("root has an same labels", NUM_ROOT_NODE_LABELS, this.dbModel.getRootNode().getSubModel().numLabelNodes());
		assertEquals("model extra shape", NUM_MODEL_SHAPES + 1, this.dbModel.numShapeNodes());
		assertEquals("model has same labels", NUM_MODEL_LABELS, this.dbModel.numLabelNodes());
		assertEquals("model has same edges", NUM_MODEL_EDGES, this.dbModel.numLinkEdges());
		map1Manager.synchronise() ;
		this.compareDatabase(SOURCE_DATA_FILE, COPIED_NODE_VALIDATION);
	}
	
	
	@Test
	public void testCopyEntireGraphBySelectingTheTopNodes () throws Exception {
		ISelectionFactory objectSelection = this.dbModel.newSelectionFactory() ;
		objectSelection.addDrawingNode(shapeNode1) ;
		objectSelection.addDrawingNode(shapeNode2) ;
		dbRootNode.getSubModel().copyHere(objectSelection.createSelection()) ;
		assertTrue("model is valid after copy", dbRootNode.getModel().isValid());
		IModel model = this.dbCanvas.getModel();
		ISubModel rootModel = model.getRootNode().getSubModel();
		assertEquals("model has additional nodes", NUM_MODEL_SHAPES + 8, model.numShapeNodes());
		assertEquals("model has additional labels", NUM_MODEL_LABELS + 8, model.numLabelNodes());
		assertEquals("model has additional edges", NUM_MODEL_EDGES + 9, model.numLinkEdges());
		assertEquals("root has additional nodes", NUM_ROOT_NODE_SHAPES + 2, rootModel.numShapeNodes());
		assertEquals("root has additional labels", NUM_ROOT_NODE_LABELS + 4, rootModel.numLabelNodes());
		assertEquals("root has additional edges", NUM_ROOT_NODE_EDGES + 4, rootModel.numLinkEdges());
		
		map1Manager.synchronise() ;
		this.compareDatabase(SOURCE_DATA_FILE, COPY_WHOLE_VALIDATION);
	}
	
	@Test
	public void testDeleteEdge () throws Exception 
	{
		
		
		ISelectionFactory objectSelection = this.dbModel.newSelectionFactory() ;
		objectSelection.addLink(linkEdge1) ;
		
		this.dbModel.removeSubgraph(objectSelection.createSelection()) ;
		assertTrue("model is valid after delete", dbRootNode.getModel().isValid());
		map1Manager.synchronise() ;
		this.compareDatabase(DELETED_EDGE_VALIDATION);
	}
	
	
	@Test
	public void testDeleteTwoConnectedShapes () throws Exception
	{
		
		
		ISelectionFactory objectSelection = this.dbModel.newSelectionFactory() ;
		objectSelection.addDrawingNode(shapeNode3) ;
		objectSelection.addDrawingNode(shapeNode7) ;
		
		this.dbModel.removeSubgraph(objectSelection.createSelection()) ;
		map1Manager.synchronise() ;
		this.compareDatabase(DELETED_TWO_SHAPES_VALIDATION);
	}
	
	
	@Test
	public void testDeleteAll () throws Exception 
	{
		
		
		ISelectionFactory objectSelection = this.dbModel.newSelectionFactory() ;
		objectSelection.addDrawingNode(shapeNode1) ;
		objectSelection.addDrawingNode(shapeNode2) ;
		this.dbModel.removeSubgraph(objectSelection.createSelection());
		map1Manager.synchronise() ;
		this.compareDatabase(DELETED_ALL_VALIDATION);
	}
	
	@Test
	public void testAlterShapeAttributeValues () throws Exception
	{
		
		
		IShapeAttribute shapeAttribute1 = shapeNode1.getAttribute() ;
		
		shapeAttribute1.setName(CHANGED_SHAPE_NAME) ;
		shapeAttribute1.setDescription(CHANGED_SHAPE_DESCRIPTION) ;
		shapeAttribute1.setDetailedDescription(CHANGED_SHAPE_DETAILED_DESCRIPTION) ;
		shapeAttribute1.setFillColour(CHANGED_SHAPE_FILL_COLOR) ;
		shapeAttribute1.setLineColour(CHANGED_SHAPE_LINE_COLOR) ;
		shapeAttribute1.setLineStyle(CHANGED_SHAPE_LINE_STYLE) ;
		shapeAttribute1.setLineWidth(CHANGED_SHAPE_LINE_WIDTH);
		shapeAttribute1.setPadding(CHANGED_SHAPE_PADDING) ;
		shapeAttribute1.setLocation(CHANGED_SHAPE_LOCATION) ;
		shapeAttribute1.setSize(CHANGED_SHAPE_SIZE);
		shapeAttribute1.setUrl(CHANGED_SHAPE_URL) ;
		shapeAttribute1.setPrimitiveShape(CHANGED_SHAPE_PRIMITIVE_TYRE) ;
		
		map1Manager.synchronise() ;
		
		this.compareDatabase(CHANGED_SHAPE_DATA_VALIDATION);
	}
	
	@Test
	public void testAlterLabelAttributeValues () throws Exception {
		
		
		ILabelAttribute labelAttribute1 = shapeNode1.getSubModel().labelIterator().next().getAttribute() ;
		
		labelAttribute1.setBackgroundColor(CHANGED_LABEL_BACKGROUND_COLOR) ;
		labelAttribute1.setLocation(CHANGED_LABEL_LOCATION) ;
		labelAttribute1.setSize(CHANGED_LABEL_SIZE) ;
		
		map1Manager.synchronise() ;
		
		this.compareDatabase(CHANGED_LABEL_DATA_VALIDATION);
	}
	
	@Test
	public void testAlterLinkAttributeValues () throws Exception
	{
		
		ILinkAttribute linkAttribute1 = linkEdge1.getAttribute() ;
		
		linkAttribute1.setName(CHANGED_LINK_NAME) ;
		linkAttribute1.setDescription(CHANGED_LINK_DESCRIPTION) ;
		linkAttribute1.setDetailedDescription(CHANGED_LINK_DETAILED_DESCRIPTION) ;
		linkAttribute1.setLineColor(CHANGED_LINK_LINE_COLOUR);
		linkAttribute1.setLineStyle(CHANGED_LINK_LINE_STYLE) ;
		linkAttribute1.setRouter(CHANGED_LINK_CONNECTION_ROUTER) ;
		linkAttribute1.setUrl(CHANGED_LINK_URL) ;
		linkAttribute1.setLineWidth(CHANGED_LINK_LINE_WIDTH) ;
		
		map1Manager.synchronise();
		
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
		
		map1Manager.synchronise();
		this.compareDatabase(CHANGED_CANVAS_DATA_VALIDATION);
	}
	
	@Test
	public void testSaveAndRestoreStateDeletingNodes () throws Exception 
	{
		 
		
		IGraphMomento savedOriginalState = dbCanvas.getModel().getCurrentState() ;
		
		ISelectionFactory objectSelection = this.dbModel.newSelectionFactory() ;
		objectSelection.addDrawingNode(shapeNode1) ;
		
		this.dbModel.removeSubgraph(objectSelection.createSelection()) ;
		
		map1Manager.synchronise() ;
		
		dbCanvas.getModel().restoreToState(savedOriginalState) ;
		
		map1Manager.synchronise();
		this.compareDatabase(RESTORED_FROM_DELETED_SHAPE_NODE);
	}
	
	@Test
	public void testSaveAndRestoreStateAddingShape () throws Exception 
	{
		
		
		IGraphMomento savedOriginalState = dbCanvas.getModel().getCurrentState() ;
		
		IShapeNodeFactory nodeFactory = dbRootNode.getSubModel().shapeNodeFactory() ;
		nodeFactory.setObjectType(this.dbNotationSubSystem.getSyntaxService().getShapeObjectType(StubShapeAObjectType.UNIQUE_ID)  ) ;
		
		newNode = nodeFactory.createShapeNode() ;
		newNode.getAttribute().setLocation(NEW_NODE_LOCATION) ;
		newNode.getAttribute().setPrimitiveShape(PrimitiveShapeType.ARC) ;
		map1Manager.synchronise() ;
		
		map1Manager.synchronise() ;
		
		dbCanvas.getModel().restoreToState(savedOriginalState) ;
		
		map1Manager.synchronise();
		this.compareDatabase(RESTORED_FROM_ADDED_SHAPE_NODE);
	}
	
	
	@Test
	public void testSaveAndRestoreStateMovingShape () throws Exception
	{
		
		
		IGraphMomento savedOriginalState = dbCanvas.getModel().getCurrentState() ;
		
		ISelectionFactory objectSelection = this.dbModel.newSelectionFactory() ;
		objectSelection.addDrawingNode(shapeNode8) ;
		
		dbRootNode.getSubModel().moveHere(objectSelection.createSelection()) ;
		map1Manager.synchronise() ;
		
		dbCanvas.getModel().restoreToState(savedOriginalState) ;
		
		map1Manager.synchronise() ;
		this.compareDatabase(RESTORED_FROM_MOVED_SHAPE_NODE);
	}
	
	@Test
	public void testSaveAndRestoreStateCopyingShape () throws Exception 
	{
		
		
		IGraphMomento savedOriginalState = dbCanvas.getModel().getCurrentState() ;
		
		ISelectionFactory objectSelection = this.dbModel.newSelectionFactory() ;
		objectSelection.addDrawingNode(shapeNode8) ;
		
		dbRootNode.getSubModel().copyHere(objectSelection.createSelection()) ;
		map1Manager.synchronise() ;
		
		dbCanvas.getModel().restoreToState(savedOriginalState) ;
		
		map1Manager.synchronise() ;
		this.compareDatabase(RESTORED_FROM_COPIED_SHAPE_NODE);
	}
	
	@Test
	public void testSaveAndRestoreDeleteLabel () throws Exception
	{
		
		IGraphMomento savedOriginalState = dbCanvas.getModel().getCurrentState() ;
		
		ILabelNode labelNode1 = shapeNode1.getSubModel().labelIterator().next() ;
		
		ISelectionFactory objectSelection = this.dbModel.newSelectionFactory() ;
		objectSelection.addDrawingNode(labelNode1) ;
		
		this.dbModel.removeSubgraph(objectSelection.createSelection()) ;
		
		map1Manager.synchronise() ;
		
		dbCanvas.getModel().restoreToState(savedOriginalState) ;
		
		map1Manager.synchronise();
		this.compareDatabase(RESTORED_FROM_DELETED_LABEL_NODE);
	}
	
	@Test
	public void testSaveAndRestoreDeletedLink () throws Exception
	{
		
		IGraphMomento savedOriginalState = dbCanvas.getModel().getCurrentState() ;
		ISelectionFactory objectSelection = this.dbModel.newSelectionFactory() ;
		objectSelection.addLink(linkEdge1) ;
		
		this.dbModel.removeSubgraph(objectSelection.createSelection()) ;
		map1Manager.synchronise() ;
		
		this.dbCanvas.getModel().restoreToState(savedOriginalState) ;
		map1Manager.synchronise() ;
		this.compareDatabase(RESTORED_FROM_DELETED_LINK_EDGE);
	}
	
	@Test
	public void testSaveAndRestoreAddLink () throws Exception
	{
		
		IGraphMomento savedOriginalState = dbCanvas.getModel().getCurrentState() ;
		
		ILinkEdgeFactory linkFactory = shapeNode5.getSubModel().linkEdgeFactory() ;
		linkFactory.setObjectType(this.dbNotationSubSystem.getSyntaxService().getLinkObjectType(StubLinkBObjectType.UNIQUE_ID)) ;
		linkFactory.setShapeNodePair(shapeNode5, shapeNode5) ;

		newLinkEdge = linkFactory.createLinkEdge() ;
		
		map1Manager.synchronise() ;
		
		dbCanvas.getModel().restoreToState(savedOriginalState) ;
		map1Manager.synchronise() ;
		this.compareDatabase(RESTORED_FROM_ADDED_LINK_EDGE);
	}
	
	@Test
	public void testSaveAndRestoreStateAddLabel () throws Exception 
	{
		
		IGraphMomento savedOriginalState = dbCanvas.getModel().getCurrentState() ;
		
		IShapeNodeFactory nodeFactory = dbRootNode.getSubModel().shapeNodeFactory() ;
		nodeFactory.setObjectType(this.dbNotationSubSystem.getSyntaxService().getShapeObjectType(StubShapeAObjectType.UNIQUE_ID)  ) ;
		
		newNode = nodeFactory.createShapeNode() ;
		
		IAnnotationProperty aProperty = newNode.getAttribute().propertyIterator().next() ;
		
		assertNotNull ( "property is not null" , aProperty) ;
		
		ILabelNodeFactory labelFactory = newNode.getSubModel().labelNodeFactory() ;

		labelFactory.setProperty(aProperty) ;
		newLabel = labelFactory.createLabel() ;
		
		map1Manager.synchronise() ;
		
		dbCanvas.getModel().restoreToState(savedOriginalState) ;
		
		map1Manager.synchronise() ;
		this.compareDatabase(RESTORED_FROM_ADDED_LABEL_NODE);
	}
	
	@Ignore
	@Test
	public void testMakeCopyOfGraphViaTheModelAndToAnotherMapDIagram () throws PersistenceManagerException {
		IMap mapDiagram2 = (IMap)this.repository.findRepositoryItemByPath(MAP2_PATH);
		IMapContentPersistenceManager map2Manager = this.getRepositoryPersistenceManager().openMap(mapDiagram2) ;
		map2Manager.loadContent();
		map2Manager.createCanvas(dbNotationSubSystem) ;
		ICanvas canvas2 = map2Manager.getCanvas() ;
		assertFalse("cannot copy to yourself", canvas2.canCopyHere(canvas2));
		assertFalse("cannot copy from null", canvas2.canCopyHere(null));
		assertTrue("can copy here", canvas2.canCopyHere(dbCanvas));
		canvas2.copyHere(dbCanvas);
		//TODO add a data comparison here too.
	}
}
