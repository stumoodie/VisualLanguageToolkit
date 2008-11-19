/**
 * 
 */
package org.pathwayeditor.businessobjects.hibernate.pojos;


import static org.junit.Assert.assertNotNull;

import java.io.FileInputStream;
import java.util.Iterator;

import org.dbunit.Assertion;
import org.dbunit.dataset.CompositeDataSet;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.SortedTable;
import org.dbunit.dataset.filter.DefaultColumnFilter;
import org.dbunit.dataset.xml.XmlDataSet;
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
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.ConnectionRouter;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LineStyle;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.Location;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.PrimitiveShapeType;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.Size;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationProperty;
import org.pathwayeditor.businessobjects.management.IMapContentPersistenceManager;
import org.pathwayeditor.businessobjects.management.PersistenceManagerNotOpenException;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationSubsystem;
import org.pathwayeditor.businessobjects.repository.IMap;
import org.pathwayeditor.businessobjects.repository.IRepository;
import org.pathwayeditor.businessobjects.repository.IRootFolder;
import org.pathwayeditor.businessobjects.repository.ISubFolder;
import org.pathwayeditor.bussinessobjects.stubs.notationsubsystem.StubLinkBConnectsShaesCToBObjectType;
import org.pathwayeditor.bussinessobjects.stubs.notationsubsystem.StubShapeAParentOfAllObjectType;
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
	
	private static final Location NEW_NODE_LOCATION = new Location ( 75 , 75 ) ;
	private static final Size NEW_NODE_SIZE = new Size (25 , 25) ;
	

	private final static String CREATED_LINK_VALIDATION = "Acceptance Test/DBConsistencyTestValidationData/CreatedLinkEdge.xml" ;
	private final static String CREATED_TWO_NODE_VALIDATION = "Acceptance Test/DBConsistencyTestValidationData/CreatedTwoShapeNode.xml" ;
	private final static String CREATED_NODE_VALIDATION = "Acceptance Test/DBConsistencyTestValidationData/CreatedShapeNode.xml" ;
	private final static String CREATED_LABEL_VALIDATION = "Acceptance Test/DBConsistencyTestValidationData/CreatedLabelNode.xml" ;
	private final static String DELETED_NODE_VALIDATION = "Acceptance Test/DBConsistencyTestValidationData/DeleteShapeNode.xml" ;
	private final static String DELETED_LABEL_VALIDATION = "Acceptance Test/DBConsistencyTestValidationData/DeletedLabelNodeData.xml" ;
	private final static String MOVED_NODE_VALIDATION = "Acceptance Test/DBConsistencyTestValidationData/MovedNodeValidation.xml" ;
	private final static String COPIED_NODE_VALIDATION = "Acceptance Test/DBConsistencyTestValidationData/copiedShapeNode.xml" ;
	private final static String DELETED_EDGE_VALIDATION = "Acceptance Test/DBConsistencyTestValidationData/deletedEdge.xml" ;
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
	
	
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.integrationtestutils.GenericTester#doAdditionalSetUp()
	 */
	@Override
	protected void doAdditionalSetUp() {
		try {
			repository = this.getRepositoryPersistenceManager().getRepository();
		} catch (PersistenceManagerNotOpenException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	private void loadData () throws Exception 
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
		return "Acceptance Test/DBConsistencyTestSourceData/DBSourceData.xml";
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
		loadData () ;
		IShapeNodeFactory nodeFactory = dbRootNode.getSubModel().shapeNodeFactory() ;
		nodeFactory.setObjectType(this.dbNotationSubSystem.getSyntaxService().getShapeObjectType(StubShapeAParentOfAllObjectType.UNIQUE_ID)  ) ;
		
		newNode = nodeFactory.createShapeNode() ;
		newNode.getAttribute().setLocation(NEW_NODE_LOCATION) ;
		newNode.getAttribute().setPrimitiveShape(PrimitiveShapeType.ARC) ;
		map1Manager.synchronise() ;
		IDataSet expectedDeltas = new XmlDataSet(new FileInputStream(CREATED_NODE_VALIDATION));
		String testTables[] = expectedDeltas.getTableNames();
		IDataSet actualChanges = this.getDbTester().getConnection().createDataSet(testTables);
		IDataSet expectedChanges = new CompositeDataSet(expectedDeltas);
		for (String t : testTables) {
			ITable expectedTable = DefaultColumnFilter
					.includedColumnsTable(expectedChanges.getTable(t),
							expectedDeltas.getTable(t).getTableMetaData()
									.getColumns());
			ITable actualTable = DefaultColumnFilter.includedColumnsTable(
					actualChanges.getTable(t), expectedDeltas.getTable(t)
							.getTableMetaData().getColumns());
			Assertion.assertEquals(new SortedTable(expectedTable),
					new SortedTable(actualTable, expectedTable
							.getTableMetaData()));
		}
		
		
	}
	
	@Test
	public void testCreateTwoShapeNodesOneChildOfAnother () throws Exception
	{
		loadData () ;
		IShapeNodeFactory nodeFactory = dbRootNode.getSubModel().shapeNodeFactory() ;
		nodeFactory.setObjectType(this.dbNotationSubSystem.getSyntaxService().getShapeObjectType(StubShapeAParentOfAllObjectType.UNIQUE_ID)  ) ;
		
		newNode = nodeFactory.createShapeNode() ;
		newNode.getAttribute().setLocation(NEW_NODE_LOCATION) ;
		newNode.getAttribute().setPrimitiveShape(PrimitiveShapeType.ARC) ;
		
		IShapeNodeFactory newNodeFactory = newNode.getSubModel().shapeNodeFactory() ;
		newNodeFactory.setObjectType(this.dbNotationSubSystem.getSyntaxService().getShapeObjectType(StubShapeAParentOfAllObjectType.UNIQUE_ID)  ) ;
		IShapeNode newNode2 = newNodeFactory.createShapeNode() ;
		newNode2.getAttribute().setLocation(NEW_NODE_LOCATION) ;
		newNode2.getAttribute().setPrimitiveShape(PrimitiveShapeType.ARC) ;
		
		map1Manager.synchronise() ;
		
		IDataSet expectedDeltas = new XmlDataSet(new FileInputStream(CREATED_TWO_NODE_VALIDATION));
		String testTables[] = expectedDeltas.getTableNames();
		IDataSet actualChanges = this.getDbTester().getConnection().createDataSet(testTables);
		IDataSet expectedChanges = new CompositeDataSet(expectedDeltas);
		for (String t : testTables) {
			ITable expectedTable = DefaultColumnFilter
					.includedColumnsTable(expectedChanges.getTable(t),
							expectedDeltas.getTable(t).getTableMetaData()
									.getColumns());
			ITable actualTable = DefaultColumnFilter.includedColumnsTable(
					actualChanges.getTable(t), expectedDeltas.getTable(t)
							.getTableMetaData().getColumns());
			Assertion.assertEquals(new SortedTable(expectedTable),
					new SortedTable(actualTable, expectedTable
							.getTableMetaData()));
		}
		
		
	}
	
	@Test
	public void testCreateNewLabelNode () throws Exception 
	{
		loadData () ;
		
		IShapeNodeFactory nodeFactory = dbRootNode.getSubModel().shapeNodeFactory() ;
		nodeFactory.setObjectType(this.dbNotationSubSystem.getSyntaxService().getShapeObjectType(StubShapeAParentOfAllObjectType.UNIQUE_ID)  ) ;
		
		newNode = nodeFactory.createShapeNode() ;
		
		IAnnotationProperty aProperty = newNode.getAttribute().propertyIterator().next() ;
		
		assertNotNull ( "property is not null" , aProperty) ;
		
		ILabelNodeFactory labelFactory = newNode.getSubModel().labelNodeFactory() ;

		labelFactory.setProperty(aProperty) ;
		newLabel = labelFactory.createLabel() ;
		
		map1Manager.synchronise() ;
		
		IDataSet expectedDeltas = new XmlDataSet(new FileInputStream(CREATED_LABEL_VALIDATION));
		String testTables[] = expectedDeltas.getTableNames();
		IDataSet actualChanges = this.getDbTester().getConnection().createDataSet(testTables);
		IDataSet expectedChanges = new CompositeDataSet(expectedDeltas);
		for (String t : testTables) {
			ITable expectedTable = DefaultColumnFilter
					.includedColumnsTable(expectedChanges.getTable(t),
							expectedDeltas.getTable(t).getTableMetaData()
									.getColumns());
			ITable actualTable = DefaultColumnFilter.includedColumnsTable(
					actualChanges.getTable(t), expectedDeltas.getTable(t)
							.getTableMetaData().getColumns());
			Assertion.assertEquals(new SortedTable(expectedTable),
					new SortedTable(actualTable, expectedTable
							.getTableMetaData()));
		}
	}

	@Test
	public void testCreateNewEdgeLink () throws Exception 
	{
		loadData () ;
		ILinkEdgeFactory linkFactory = shapeNode5.getSubModel().linkEdgeFactory() ;
		linkFactory.setObjectType(this.dbNotationSubSystem.getSyntaxService().getLinkObjectType(StubLinkBConnectsShaesCToBObjectType.UNIQUE_ID)) ;
		linkFactory.setShapeNodePair(shapeNode5, shapeNode5) ;

		newLinkEdge = linkFactory.createLinkEdge() ;
		
		
		map1Manager.synchronise() ;
		IDataSet expectedDeltas = new XmlDataSet(new FileInputStream(CREATED_LINK_VALIDATION));
		String testTables[] = expectedDeltas.getTableNames();
		IDataSet actualChanges = this.getDbTester().getConnection().createDataSet(testTables);
		IDataSet expectedChanges = new CompositeDataSet(expectedDeltas);
		for (String t : testTables) {
			ITable expectedTable = DefaultColumnFilter
					.includedColumnsTable(expectedChanges.getTable(t),
							expectedDeltas.getTable(t).getTableMetaData()
									.getColumns());
			ITable actualTable = DefaultColumnFilter.includedColumnsTable(
					actualChanges.getTable(t), expectedDeltas.getTable(t)
							.getTableMetaData().getColumns());
			SortedTable expected = new SortedTable(expectedTable);
			SortedTable actual = new SortedTable(actualTable, expectedTable
					.getTableMetaData());
			Assertion.assertEquals(new SortedTable(expectedTable),
					new SortedTable(actualTable, expectedTable
							.getTableMetaData()));
		}
	}
	
	@Test
	public void testDeleteShapeNode () throws Exception
	{
		loadData ();
		
		ISelectionFactory objectSelection = this.dbModel.newSelectionFactory() ;
		
		objectSelection.addDrawingNode(this.shapeNode8);
		this.dbModel.removeSubgraph(objectSelection.createSelection()) ;
		map1Manager.synchronise() ;
		IDataSet expectedDeltas = new XmlDataSet(new FileInputStream(DELETED_NODE_VALIDATION));
		String testTables[] = expectedDeltas.getTableNames();
		IDataSet actualChanges = this.getDbTester().getConnection().createDataSet(testTables);
		IDataSet expectedChanges = new CompositeDataSet(expectedDeltas);
		for (String t : testTables) {
			ITable expectedTable = DefaultColumnFilter
					.includedColumnsTable(expectedChanges.getTable(t),
							expectedDeltas.getTable(t).getTableMetaData()
									.getColumns());
			ITable actualTable = DefaultColumnFilter.includedColumnsTable(
					actualChanges.getTable(t), expectedDeltas.getTable(t)
							.getTableMetaData().getColumns());
			Assertion.assertEquals(new SortedTable(expectedTable),
					new SortedTable(actualTable, expectedTable
							.getTableMetaData()));
		}
	}
	
	@Test
	public void testDeleteLabelNode () throws Exception
	{
		loadData ();
		
		ILabelNode labelNode1 = shapeNode1.getSubModel().labelIterator().next() ;
		
		ISelectionFactory objectSelection = this.dbModel.newSelectionFactory() ;
		
		objectSelection.addDrawingNode(labelNode1);
		
		this.dbModel.removeSubgraph(objectSelection.createSelection()) ;
		map1Manager.synchronise() ;
		IDataSet expectedDeltas = new XmlDataSet(new FileInputStream(DELETED_LABEL_VALIDATION));
		String testTables[] = expectedDeltas.getTableNames();
		IDataSet actualChanges = this.getDbTester().getConnection().createDataSet(testTables);
		IDataSet expectedChanges = new CompositeDataSet(expectedDeltas);
		for (String t : testTables) {
			ITable expectedTable = DefaultColumnFilter
					.includedColumnsTable(expectedChanges.getTable(t),
							expectedDeltas.getTable(t).getTableMetaData()
									.getColumns());
			ITable actualTable = DefaultColumnFilter.includedColumnsTable(
					actualChanges.getTable(t), expectedDeltas.getTable(t)
							.getTableMetaData().getColumns());
			Assertion.assertEquals(new SortedTable(expectedTable),
					new SortedTable(actualTable, expectedTable
							.getTableMetaData()));
		}
	}
	
	@Test
	public void testMoveNode () throws Exception
	{
		loadData() ;
		
		ISelectionFactory objectSelection = this.dbModel.newSelectionFactory() ;
		objectSelection.addDrawingNode(shapeNode8) ;
		
		dbRootNode.getSubModel().moveHere(objectSelection.createSelection()) ;
		map1Manager.synchronise() ;
		IDataSet expectedDeltas = new XmlDataSet(new FileInputStream(MOVED_NODE_VALIDATION));
		String testTables[] = expectedDeltas.getTableNames();
		IDataSet actualChanges = this.getDbTester().getConnection().createDataSet(testTables);
		IDataSet expectedChanges = new CompositeDataSet(expectedDeltas);
		for (String t : testTables) {
			ITable expectedTable = DefaultColumnFilter
					.includedColumnsTable(expectedChanges.getTable(t),
							expectedDeltas.getTable(t).getTableMetaData()
									.getColumns());
			ITable actualTable = DefaultColumnFilter.includedColumnsTable(
					actualChanges.getTable(t), expectedDeltas.getTable(t)
							.getTableMetaData().getColumns());
			Assertion.assertEquals(new SortedTable(expectedTable),
					new SortedTable(actualTable, expectedTable
							.getTableMetaData()));
		}
	}
	
	@Test
	public void testCopyNode () throws Exception
	{
		loadData() ;
		
		ISelectionFactory objectSelection = this.dbModel.newSelectionFactory() ;
		objectSelection.addDrawingNode(shapeNode8) ;
		
		dbRootNode.getSubModel().copyHere(objectSelection.createSelection()) ;
		map1Manager.synchronise() ;
		IDataSet expectedDeltas = new XmlDataSet(new FileInputStream(COPIED_NODE_VALIDATION));
		String testTables[] = expectedDeltas.getTableNames();
		IDataSet actualChanges = this.getDbTester().getConnection().createDataSet(testTables);
		IDataSet expectedChanges = new CompositeDataSet(expectedDeltas);
		for (String t : testTables) {
			ITable expectedTable = DefaultColumnFilter
					.includedColumnsTable(expectedChanges.getTable(t),
							expectedDeltas.getTable(t).getTableMetaData()
									.getColumns());
			ITable actualTable = DefaultColumnFilter.includedColumnsTable(
					actualChanges.getTable(t), expectedDeltas.getTable(t)
							.getTableMetaData().getColumns());
			Assertion.assertEquals(new SortedTable(expectedTable),
					new SortedTable(actualTable, expectedTable
							.getTableMetaData()));
		}
		
	}
	
	@Ignore
	@Test
	public void testCopyEntireGraph () throws Exception
	{
		loadData () ;
		
		ISelectionFactory objectSelection = this.dbModel.newSelectionFactory() ;
		objectSelection.addDrawingNode(shapeNode1) ;
		objectSelection.addDrawingNode(shapeNode2) ;
		
		
		dbRootNode.getSubModel().copyHere(objectSelection.createSelection()) ;
		map1Manager.synchronise() ;
		IDataSet expectedDeltas = new XmlDataSet(new FileInputStream(COPIED_NODE_VALIDATION));
		String testTables[] = expectedDeltas.getTableNames();
		IDataSet actualChanges = this.getDbTester().getConnection().createDataSet(testTables);
		IDataSet expectedChanges = new CompositeDataSet(expectedDeltas);
		for (String t : testTables) {
			ITable expectedTable = DefaultColumnFilter
					.includedColumnsTable(expectedChanges.getTable(t),
							expectedDeltas.getTable(t).getTableMetaData()
									.getColumns());
			ITable actualTable = DefaultColumnFilter.includedColumnsTable(
					actualChanges.getTable(t), expectedDeltas.getTable(t)
							.getTableMetaData().getColumns());
			Assertion.assertEquals(new SortedTable(expectedTable),
					new SortedTable(actualTable, expectedTable
							.getTableMetaData()));
		}

	}
	
	@Test
	public void testDeleteEdge () throws Exception 
	{
		loadData () ;
		
		ISelectionFactory objectSelection = this.dbModel.newSelectionFactory() ;
		objectSelection.addLink(linkEdge1) ;
		
		this.dbModel.removeSubgraph(objectSelection.createSelection()) ;
		map1Manager.synchronise() ;
		IDataSet expectedDeltas = new XmlDataSet(new FileInputStream(DELETED_EDGE_VALIDATION));
		String testTables[] = expectedDeltas.getTableNames();
		IDataSet actualChanges = this.getDbTester().getConnection().createDataSet(testTables);
		IDataSet expectedChanges = new CompositeDataSet(expectedDeltas);
		for (String t : testTables) {
			ITable expectedTable = DefaultColumnFilter
					.includedColumnsTable(expectedChanges.getTable(t),
							expectedDeltas.getTable(t).getTableMetaData()
									.getColumns());
			ITable actualTable = DefaultColumnFilter.includedColumnsTable(
					actualChanges.getTable(t), expectedDeltas.getTable(t)
							.getTableMetaData().getColumns());
			Assertion.assertEquals(new SortedTable(expectedTable),
					new SortedTable(actualTable, expectedTable
							.getTableMetaData()));
		}
		
	}
	
	@Test
	public void testDeleteTwoConnectedShapes () throws Exception
	{
		loadData() ;
		
		ISelectionFactory objectSelection = this.dbModel.newSelectionFactory() ;
		objectSelection.addDrawingNode(shapeNode3) ;
		objectSelection.addDrawingNode(shapeNode7) ;
		
		this.dbModel.removeSubgraph(objectSelection.createSelection()) ;
		map1Manager.synchronise() ;
		IDataSet expectedDeltas = new XmlDataSet(new FileInputStream(DELETED_TWO_SHAPES_VALIDATION));
		String testTables[] = expectedDeltas.getTableNames();
		IDataSet actualChanges = this.getDbTester().getConnection().createDataSet(testTables);
		IDataSet expectedChanges = new CompositeDataSet(expectedDeltas);
		for (String t : testTables) {
			ITable expectedTable = DefaultColumnFilter
					.includedColumnsTable(expectedChanges.getTable(t),
							expectedDeltas.getTable(t).getTableMetaData()
									.getColumns());
			ITable actualTable = DefaultColumnFilter.includedColumnsTable(
					actualChanges.getTable(t), expectedDeltas.getTable(t)
							.getTableMetaData().getColumns());
			Assertion.assertEquals(new SortedTable(expectedTable),
					new SortedTable(actualTable, expectedTable
							.getTableMetaData()));
		}

	}
	
	@Test
	public void testDeleteAll () throws Exception 
	{
		loadData() ;
		
		ISelectionFactory objectSelection = this.dbModel.newSelectionFactory() ;
		objectSelection.addDrawingNode(shapeNode1) ;
		objectSelection.addDrawingNode(shapeNode2) ;
		this.dbModel.removeSubgraph(objectSelection.createSelection());
		map1Manager.synchronise() ;
		
		IDataSet expectedDeltas = new XmlDataSet(new FileInputStream(DELETED_ALL_VALIDATION));
		String testTables[] = expectedDeltas.getTableNames();
		IDataSet actualChanges = this.getDbTester().getConnection().createDataSet(testTables);
		IDataSet expectedChanges = new CompositeDataSet(expectedDeltas);
		for (String t : testTables) {
			ITable expectedTable = DefaultColumnFilter
					.includedColumnsTable(expectedChanges.getTable(t),
							expectedDeltas.getTable(t).getTableMetaData()
									.getColumns());
			ITable actualTable = DefaultColumnFilter.includedColumnsTable(
					actualChanges.getTable(t), expectedDeltas.getTable(t)
							.getTableMetaData().getColumns());
			Assertion.assertEquals(new SortedTable(expectedTable),
					new SortedTable(actualTable, expectedTable
							.getTableMetaData()));
		}
	}
	
	@Test
	public void testAlterShapeAttributeValues () throws Exception
	{
		loadData () ;
		
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
		
		IDataSet expectedDeltas = new XmlDataSet(new FileInputStream(CHANGED_SHAPE_DATA_VALIDATION));
		String testTables[] = expectedDeltas.getTableNames();
		IDataSet actualChanges = this.getDbTester().getConnection().createDataSet(testTables);
		IDataSet expectedChanges = new CompositeDataSet(expectedDeltas);
		for (String t : testTables) {
			ITable expectedTable = DefaultColumnFilter
					.includedColumnsTable(expectedChanges.getTable(t),
							expectedDeltas.getTable(t).getTableMetaData()
									.getColumns());
			ITable actualTable = DefaultColumnFilter.includedColumnsTable(
					actualChanges.getTable(t), expectedDeltas.getTable(t)
							.getTableMetaData().getColumns());
			Assertion.assertEquals(new SortedTable(expectedTable),
					new SortedTable(actualTable, expectedTable
							.getTableMetaData()));
		}
	}
	
	@Test
	public void testAlterLabelAttributeValues () throws Exception {
		loadData () ;
		
		ILabelAttribute labelAttribute1 = shapeNode1.getSubModel().labelIterator().next().getAttribute() ;
		
		labelAttribute1.setBackgroundColor(CHANGED_LABEL_BACKGROUND_COLOR) ;
		labelAttribute1.setLocation(CHANGED_LABEL_LOCATION) ;
		labelAttribute1.setSize(CHANGED_LABEL_SIZE) ;
		
		map1Manager.synchronise() ;
		
		IDataSet expectedDeltas = new XmlDataSet(new FileInputStream(CHANGED_LABEL_DATA_VALIDATION));
		String testTables[] = expectedDeltas.getTableNames();
		IDataSet actualChanges = this.getDbTester().getConnection().createDataSet(testTables);
		IDataSet expectedChanges = new CompositeDataSet(expectedDeltas);
		for (String t : testTables) {
			ITable expectedTable = DefaultColumnFilter
					.includedColumnsTable(expectedChanges.getTable(t),
							expectedDeltas.getTable(t).getTableMetaData()
									.getColumns());
			ITable actualTable = DefaultColumnFilter.includedColumnsTable(
					actualChanges.getTable(t), expectedDeltas.getTable(t)
							.getTableMetaData().getColumns());
			Assertion.assertEquals(new SortedTable(expectedTable),
					new SortedTable(actualTable, expectedTable
							.getTableMetaData()));
		}
		
	}
	
	@Test
	public void testAlterLinkAttributeValues () throws Exception
	{
		loadData() ;
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
		
		
		IDataSet expectedDeltas = new XmlDataSet(new FileInputStream(CHANGED_LINK_DATA_VALIDATION));
		String testTables[] = expectedDeltas.getTableNames();
		IDataSet actualChanges = this.getDbTester().getConnection().createDataSet(testTables);
		IDataSet expectedChanges = new CompositeDataSet(expectedDeltas);
		for (String t : testTables) {
			ITable expectedTable = DefaultColumnFilter
					.includedColumnsTable(expectedChanges.getTable(t),
							expectedDeltas.getTable(t).getTableMetaData()
									.getColumns());
			ITable actualTable = DefaultColumnFilter.includedColumnsTable(
					actualChanges.getTable(t), expectedDeltas.getTable(t)
							.getTableMetaData().getColumns());
			Assertion.assertEquals(new SortedTable(expectedTable),
					new SortedTable(actualTable, expectedTable
							.getTableMetaData()));
		}
	}
	
	@Test
	public void testAlterCanvasValues () throws Exception
	{
		loadData() ;
		
		dbCanvas.setBackgroundColour(CHANGED_CANVAS_BACKGROUND_COLOUR) ;
		dbCanvas.setCanvasSize(CHANGED_CANVAS_SIZE) ; 
		dbCanvas.setGridEnabled(CHANGED_CANVAS_GRID_ENABLED) ;
		dbCanvas.setSnapToGrid(CHANGED_CANVAS_SNAP_ENABLED) ;
		dbCanvas.setGridSize(CHANGED_CANVAS_GRID_SIZE) ;
		
		map1Manager.synchronise();
		
		IDataSet expectedDeltas = new XmlDataSet(new FileInputStream(CHANGED_CANVAS_DATA_VALIDATION));
		String testTables[] = expectedDeltas.getTableNames();
		IDataSet actualChanges = this.getDbTester().getConnection().createDataSet(testTables);
		IDataSet expectedChanges = new CompositeDataSet(expectedDeltas);
		for (String t : testTables) {
			ITable expectedTable = DefaultColumnFilter
					.includedColumnsTable(expectedChanges.getTable(t),
							expectedDeltas.getTable(t).getTableMetaData()
									.getColumns());
			ITable actualTable = DefaultColumnFilter.includedColumnsTable(
					actualChanges.getTable(t), expectedDeltas.getTable(t)
							.getTableMetaData().getColumns());
			Assertion.assertEquals(new SortedTable(expectedTable),
					new SortedTable(actualTable, expectedTable
							.getTableMetaData()));
		}
	}
	
	@Test
	public void testSaveAndRestoreStateDeletingNodes () throws Exception 
	{
		loadData(); 
		
		IGraphMomento savedOriginalState = dbCanvas.getModel().getCurrentState() ;
		
		ISelectionFactory objectSelection = this.dbModel.newSelectionFactory() ;
		objectSelection.addDrawingNode(shapeNode1) ;
		
		this.dbModel.removeSubgraph(objectSelection.createSelection()) ;
		
		map1Manager.synchronise() ;
		
		dbCanvas.getModel().restoreToState(savedOriginalState) ;
		
		map1Manager.synchronise();
		
		IDataSet expectedDeltas = new XmlDataSet(new FileInputStream(RESTORED_FROM_DELETED_SHAPE_NODE));
		String testTables[] = expectedDeltas.getTableNames();
		IDataSet actualChanges = this.getDbTester().getConnection().createDataSet(testTables);
		IDataSet expectedChanges = new CompositeDataSet(expectedDeltas);
		for (String t : testTables) {
			ITable expectedTable = DefaultColumnFilter
					.includedColumnsTable(expectedChanges.getTable(t),
							expectedDeltas.getTable(t).getTableMetaData()
									.getColumns());
			ITable actualTable = DefaultColumnFilter.includedColumnsTable(
					actualChanges.getTable(t), expectedDeltas.getTable(t)
							.getTableMetaData().getColumns());
			Assertion.assertEquals(new SortedTable(expectedTable),
					new SortedTable(actualTable, expectedTable
							.getTableMetaData()));
		}
		
		
	}
	
	@Test
	public void testSaveAndRestoreStateAddingShape () throws Exception 
	{
		loadData() ;
		
		IGraphMomento savedOriginalState = dbCanvas.getModel().getCurrentState() ;
		
		IShapeNodeFactory nodeFactory = dbRootNode.getSubModel().shapeNodeFactory() ;
		nodeFactory.setObjectType(this.dbNotationSubSystem.getSyntaxService().getShapeObjectType(StubShapeAParentOfAllObjectType.UNIQUE_ID)  ) ;
		
		newNode = nodeFactory.createShapeNode() ;
		newNode.getAttribute().setLocation(NEW_NODE_LOCATION) ;
		newNode.getAttribute().setPrimitiveShape(PrimitiveShapeType.ARC) ;
		map1Manager.synchronise() ;
		
		map1Manager.synchronise() ;
		
		dbCanvas.getModel().restoreToState(savedOriginalState) ;
		
		map1Manager.synchronise();
		
		IDataSet expectedDeltas = new XmlDataSet(new FileInputStream(RESTORED_FROM_ADDED_SHAPE_NODE));
		String testTables[] = expectedDeltas.getTableNames();
		IDataSet actualChanges = this.getDbTester().getConnection().createDataSet(testTables);
		IDataSet expectedChanges = new CompositeDataSet(expectedDeltas);
		for (String t : testTables) {
			ITable expectedTable = DefaultColumnFilter
					.includedColumnsTable(expectedChanges.getTable(t),
							expectedDeltas.getTable(t).getTableMetaData()
									.getColumns());
			ITable actualTable = DefaultColumnFilter.includedColumnsTable(
					actualChanges.getTable(t), expectedDeltas.getTable(t)
							.getTableMetaData().getColumns());
			Assertion.assertEquals(new SortedTable(expectedTable),
					new SortedTable(actualTable, expectedTable
							.getTableMetaData()));
		}
		
	}
	
	
	@Test
	public void testSaveAndRestoreStateMovingShape () throws Exception
	{
		loadData() ;
		
		IGraphMomento savedOriginalState = dbCanvas.getModel().getCurrentState() ;
		
		ISelectionFactory objectSelection = this.dbModel.newSelectionFactory() ;
		objectSelection.addDrawingNode(shapeNode8) ;
		
		dbRootNode.getSubModel().moveHere(objectSelection.createSelection()) ;
		map1Manager.synchronise() ;
		
		dbCanvas.getModel().restoreToState(savedOriginalState) ;
		
		map1Manager.synchronise() ;
		
		IDataSet expectedDeltas = new XmlDataSet(new FileInputStream(RESTORED_FROM_MOVED_SHAPE_NODE));
		String testTables[] = expectedDeltas.getTableNames();
		IDataSet actualChanges = this.getDbTester().getConnection().createDataSet(testTables);
		IDataSet expectedChanges = new CompositeDataSet(expectedDeltas);
		for (String t : testTables) {
			ITable expectedTable = DefaultColumnFilter
					.includedColumnsTable(expectedChanges.getTable(t),
							expectedDeltas.getTable(t).getTableMetaData()
									.getColumns());
			ITable actualTable = DefaultColumnFilter.includedColumnsTable(
					actualChanges.getTable(t), expectedDeltas.getTable(t)
							.getTableMetaData().getColumns());
			Assertion.assertEquals(new SortedTable(expectedTable),
					new SortedTable(actualTable, expectedTable
							.getTableMetaData()));
		}
		
	}
	
	@Test
	public void testSaveAndRestoreStateCopyingShape () throws Exception 
	{
		loadData() ;
		
		IGraphMomento savedOriginalState = dbCanvas.getModel().getCurrentState() ;
		
		ISelectionFactory objectSelection = this.dbModel.newSelectionFactory() ;
		objectSelection.addDrawingNode(shapeNode8) ;
		
		dbRootNode.getSubModel().copyHere(objectSelection.createSelection()) ;
		map1Manager.synchronise() ;
		
		dbCanvas.getModel().restoreToState(savedOriginalState) ;
		
		map1Manager.synchronise() ;
		
		IDataSet expectedDeltas = new XmlDataSet(new FileInputStream(RESTORED_FROM_COPIED_SHAPE_NODE));
		String testTables[] = expectedDeltas.getTableNames();
		IDataSet actualChanges = this.getDbTester().getConnection().createDataSet(testTables);
		IDataSet expectedChanges = new CompositeDataSet(expectedDeltas);
		for (String t : testTables) {
			ITable expectedTable = DefaultColumnFilter
					.includedColumnsTable(expectedChanges.getTable(t),
							expectedDeltas.getTable(t).getTableMetaData()
									.getColumns());
			ITable actualTable = DefaultColumnFilter.includedColumnsTable(
					actualChanges.getTable(t), expectedDeltas.getTable(t)
							.getTableMetaData().getColumns());
			Assertion.assertEquals(new SortedTable(expectedTable),
					new SortedTable(actualTable, expectedTable
							.getTableMetaData()));
		}
	}
	
	@Test
	public void testSaveAndRestoreDeleteLabel () throws Exception
	{
		loadData () ;
		IGraphMomento savedOriginalState = dbCanvas.getModel().getCurrentState() ;
		
		ILabelNode labelNode1 = shapeNode1.getSubModel().labelIterator().next() ;
		
		ISelectionFactory objectSelection = this.dbModel.newSelectionFactory() ;
		objectSelection.addDrawingNode(labelNode1) ;
		
		this.dbModel.removeSubgraph(objectSelection.createSelection()) ;
		
		map1Manager.synchronise() ;
		
		dbCanvas.getModel().restoreToState(savedOriginalState) ;
		
		map1Manager.synchronise();
		
		IDataSet expectedDeltas = new XmlDataSet(new FileInputStream(RESTORED_FROM_DELETED_LABEL_NODE));
		String testTables[] = expectedDeltas.getTableNames();
		IDataSet actualChanges = this.getDbTester().getConnection().createDataSet(testTables);
		IDataSet expectedChanges = new CompositeDataSet(expectedDeltas);
		for (String t : testTables) {
			ITable expectedTable = DefaultColumnFilter
					.includedColumnsTable(expectedChanges.getTable(t),
							expectedDeltas.getTable(t).getTableMetaData()
									.getColumns());
			ITable actualTable = DefaultColumnFilter.includedColumnsTable(
					actualChanges.getTable(t), expectedDeltas.getTable(t)
							.getTableMetaData().getColumns());
			Assertion.assertEquals(new SortedTable(expectedTable),
					new SortedTable(actualTable, expectedTable
							.getTableMetaData()));
		}
	}
	
	@Test
	public void testSaveAndRestoreDeletedLink () throws Exception
	{
		loadData () ;
		IGraphMomento savedOriginalState = dbCanvas.getModel().getCurrentState() ;
		ISelectionFactory objectSelection = this.dbModel.newSelectionFactory() ;
		objectSelection.addLink(linkEdge1) ;
		
		this.dbModel.removeSubgraph(objectSelection.createSelection()) ;
		map1Manager.synchronise() ;
		
		this.dbCanvas.getModel().restoreToState(savedOriginalState) ;
		map1Manager.synchronise() ;
		
		IDataSet expectedDeltas = new XmlDataSet(new FileInputStream(RESTORED_FROM_DELETED_LINK_EDGE));
		String testTables[] = expectedDeltas.getTableNames();
		IDataSet actualChanges = this.getDbTester().getConnection().createDataSet(testTables);
		IDataSet expectedChanges = new CompositeDataSet(expectedDeltas);
		for (String t : testTables) {
			ITable expectedTable = DefaultColumnFilter
					.includedColumnsTable(expectedChanges.getTable(t),
							expectedDeltas.getTable(t).getTableMetaData()
									.getColumns());
			ITable actualTable = DefaultColumnFilter.includedColumnsTable(
					actualChanges.getTable(t), expectedDeltas.getTable(t)
							.getTableMetaData().getColumns());
			Assertion.assertEquals(new SortedTable(expectedTable),
					new SortedTable(actualTable, expectedTable
							.getTableMetaData()));
		}
	}
	
	@Test
	public void testSaveAndRestoreAddLink () throws Exception
	{
		loadData () ;
		IGraphMomento savedOriginalState = dbCanvas.getModel().getCurrentState() ;
		
		ILinkEdgeFactory linkFactory = shapeNode5.getSubModel().linkEdgeFactory() ;
		linkFactory.setObjectType(this.dbNotationSubSystem.getSyntaxService().getLinkObjectType(StubLinkBConnectsShaesCToBObjectType.UNIQUE_ID)) ;
		linkFactory.setShapeNodePair(shapeNode5, shapeNode5) ;

		newLinkEdge = linkFactory.createLinkEdge() ;
		
		map1Manager.synchronise() ;
		
		dbCanvas.getModel().restoreToState(savedOriginalState) ;
		map1Manager.synchronise() ;
		
		IDataSet expectedDeltas = new XmlDataSet(new FileInputStream(RESTORED_FROM_ADDED_LINK_EDGE));
		String testTables[] = expectedDeltas.getTableNames();
		IDataSet actualChanges = this.getDbTester().getConnection().createDataSet(testTables);
		IDataSet expectedChanges = new CompositeDataSet(expectedDeltas);
		for (String t : testTables) {
			ITable expectedTable = DefaultColumnFilter
					.includedColumnsTable(expectedChanges.getTable(t),
							expectedDeltas.getTable(t).getTableMetaData()
									.getColumns());
			ITable actualTable = DefaultColumnFilter.includedColumnsTable(
					actualChanges.getTable(t), expectedDeltas.getTable(t)
							.getTableMetaData().getColumns());
			Assertion.assertEquals(new SortedTable(expectedTable),
					new SortedTable(actualTable, expectedTable
							.getTableMetaData()));
		}
	}
	
	@Test
	public void testSaveAndRestoreStateAddLabel () throws Exception 
	{
		loadData () ;
		IGraphMomento savedOriginalState = dbCanvas.getModel().getCurrentState() ;
		
		IShapeNodeFactory nodeFactory = dbRootNode.getSubModel().shapeNodeFactory() ;
		nodeFactory.setObjectType(this.dbNotationSubSystem.getSyntaxService().getShapeObjectType(StubShapeAParentOfAllObjectType.UNIQUE_ID)  ) ;
		
		newNode = nodeFactory.createShapeNode() ;
		
		IAnnotationProperty aProperty = newNode.getAttribute().propertyIterator().next() ;
		
		assertNotNull ( "property is not null" , aProperty) ;
		
		ILabelNodeFactory labelFactory = newNode.getSubModel().labelNodeFactory() ;

		labelFactory.setProperty(aProperty) ;
		newLabel = labelFactory.createLabel() ;
		
		map1Manager.synchronise() ;
		
		dbCanvas.getModel().restoreToState(savedOriginalState) ;
		
		map1Manager.synchronise() ;
		
		IDataSet expectedDeltas = new XmlDataSet(new FileInputStream(RESTORED_FROM_ADDED_LABEL_NODE));
		String testTables[] = expectedDeltas.getTableNames();
		IDataSet actualChanges = this.getDbTester().getConnection().createDataSet(testTables);
		IDataSet expectedChanges = new CompositeDataSet(expectedDeltas);
		for (String t : testTables) {
			ITable expectedTable = DefaultColumnFilter
					.includedColumnsTable(expectedChanges.getTable(t),
							expectedDeltas.getTable(t).getTableMetaData()
									.getColumns());
			ITable actualTable = DefaultColumnFilter.includedColumnsTable(
					actualChanges.getTable(t), expectedDeltas.getTable(t)
							.getTableMetaData().getColumns());
			Assertion.assertEquals(new SortedTable(expectedTable),
					new SortedTable(actualTable, expectedTable
							.getTableMetaData()));
		}
	}
	
}
