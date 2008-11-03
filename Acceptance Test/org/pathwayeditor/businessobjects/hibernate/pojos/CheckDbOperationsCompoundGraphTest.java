/**
 * 
 */
package org.pathwayeditor.businessobjects.hibernate.pojos;

import static org.junit.Assert.assertTrue;

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
import org.pathwayeditor.businessobjects.drawingprimitives.ICanvasObjectSelection;
import org.pathwayeditor.businessobjects.drawingprimitives.ILabelNode;
import org.pathwayeditor.businessobjects.drawingprimitives.ILabelNodeFactory;
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkEdge;
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkEdgeFactory;
import org.pathwayeditor.businessobjects.drawingprimitives.IModel;
import org.pathwayeditor.businessobjects.drawingprimitives.IRootNode;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeNode;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeNodeFactory;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.Location;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.PrimitiveShapeType;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.Size;
import org.pathwayeditor.businessobjects.management.IMapContentPersistenceManager;
import org.pathwayeditor.businessobjects.management.PersistenceManagerNotOpenException;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationSubsystem;
import org.pathwayeditor.businessobjects.repository.IMap;
import org.pathwayeditor.businessobjects.repository.IRepository;
import org.pathwayeditor.businessobjects.repository.IRootFolder;
import org.pathwayeditor.businessobjects.repository.ISubFolder;
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
	private final static String CREATED_NODE_VALIDATION = "Acceptance Test/DBConsistencyTestValidationData/CreatedShapeNode.xml" ;
	private final static String CREATED_LABEL_VALIDATION = "Acceptance Test/DBConsistencyTestValidationData/CreatedLabelNode.xml" ;
	private final static String DELETED_NODE_VALIDATION = "Acceptance Test/DBConsistencyTestValidationData/DeleteShapeNode.xml" ;
	private final static String MOVED_NODE_VALIDATION = "Acceptance Test/DBConsistencyTestValidationData/MovedNodeValidation.xml" ;
	private final static String COPIED_NODE_VALIDATION = "Acceptance Test/DBConsistencyTestValidationData/copiedShapeNode.xml" ;
	private final static String DELETED_EDGE_VALIDATION = "Acceptance Test/DBConsistencyTestValidationData/deletedEdge.xml" ;
	private final static String DELETED_TWO_SHAPES_VALIDATION = "Acceptance Test/DBConsistencyTestValidationData/deletedTwoShapes.xml" ;
	

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
		
		Iterator<ISubFolder> subFolders = repository.getRootFolder().getSubFolderIterator() ;
		
		subFolder1 = (ISubFolder)repository.getFolderByPath( SUBFOLDER1_PATH) ;
		
		mapDiagram1 = subFolder1.getMapIterator().next() ;
		
		map1Manager = this.getRepositoryPersistenceManager().openMap(mapDiagram1) ;
		map1Manager.loadContent() ;
		dbCanvas = map1Manager.getCanvas() ;
		
		dbModel = dbCanvas.getModel() ;
		dbRootNode = dbModel.getRootNode() ;
		
		dbNotationSubSystem = dbCanvas.getNotationSubsystem() ;
		
		
		Iterator<IShapeNode> rootNodeChildrenIterator = dbRootNode.getSubCanvas().shapeIterator() ;
		
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
		
		Iterator<IShapeNode> shape1ChildIterator = shapeNode1.getSubCanvas().shapeIterator() ;
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
		
		Iterator<IShapeNode> shape2ChildIterator = shapeNode2.getSubCanvas().shapeIterator() ;
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
		
		shapeNode7 = shapeNode6.getSubCanvas().shapeIterator().next() ;
		
		shapeNode8 = shapeNode7.getSubCanvas().shapeIterator().next() ;
		
		
		Iterator<ILinkEdge> rootNodeEdges = dbRootNode.getSubCanvas().linkIterator() ;	
		
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
	
	@Ignore
	@Test
	public void testCreateNewShapeNode () throws Exception 
	{
		loadData () ;
		IShapeNodeFactory nodeFactory = dbRootNode.getSubCanvas().shapeNodeFactory() ;
//		nodeFactory.setObjectType(new StubShapeObjectType () ) ;
		nodeFactory.setObjectType(shapeNode1.getAttribute().getObjectType()) ;
//		assertTrue ( "can create node" , nodeFactory.canCreateShapeNode()) ;
		newNode = nodeFactory.createShapeNode() ;
		newNode.getAttribute().setLocation(NEW_NODE_LOCATION) ;
		newNode.getAttribute().setSize(NEW_NODE_SIZE) ;
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
	
	@Ignore
	@Test
	public void testCreateNewLabelNode () throws Exception 
	{
		loadData () ;
		ILabelNodeFactory labelFactory = shapeNode1.getSubCanvas().labelNodeFactory() ;
		newLabel = labelFactory.createLabel() ;
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
	
	@Ignore
	@Test
	public void testCreateNewEdgeLink () throws Exception 
	{
		loadData () ;
		ILinkEdgeFactory linkFactory = dbRootNode.getSubCanvas().linkEdgeFactory() ;
		linkFactory.setShapeNodePair(shapeNode5, shapeNode8) ;
		assertTrue ( "can create link" , linkFactory.canCreateLink() ) ;
		newLinkEdge = linkFactory.createLinkEdge() ;
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
			Assertion.assertEquals(new SortedTable(expectedTable),
					new SortedTable(actualTable, expectedTable
							.getTableMetaData()));
		}
	}
	
	@Ignore
	@Test
	public void testDeleteNode () throws Exception
	{
		loadData ();
		
		ICanvasObjectSelection objectSelection = this.dbModel.newCanvasObjectSelection() ;
		
		objectSelection.addShape(this.shapeNode8);
		this.dbModel.removeSubgraph(objectSelection) ;
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
	
	@Ignore
	@Test
	public void testMoveNode () throws Exception
	{
		loadData() ;
		
		ICanvasObjectSelection objectSelection = this.dbModel.newCanvasObjectSelection() ;
		objectSelection.addShape(shapeNode8) ;
		
		dbRootNode.getSubCanvas().moveHere(objectSelection) ;
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
	
	@Ignore
	@Test
	public void testCopyNode () throws Exception
	{
		loadData() ;
		
		ICanvasObjectSelection objectSelection = this.dbModel.newCanvasObjectSelection() ;
		objectSelection.addShape(shapeNode8) ;
		
		dbRootNode.getSubCanvas().copyHere(objectSelection) ;
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
	public void testDeleteEdge () throws Exception 
	{
		loadData () ;
		
		ICanvasObjectSelection objectSelection = this.dbModel.newCanvasObjectSelection() ;
		objectSelection.addLink(linkEdge1) ;
		
		this.dbModel.removeSubgraph(objectSelection) ;
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
	
	@Ignore
	@Test
	public void testDeleteTwoConnectedShapes () throws Exception
	{
		loadData() ;
		
		ICanvasObjectSelection objectSelection = this.dbModel.newCanvasObjectSelection() ;
		objectSelection.addShape(shapeNode3) ;
		objectSelection.addShape(shapeNode7) ;
		
		this.dbModel.removeSubgraph(objectSelection) ;
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
	
	@Ignore
	@Test
	public void testDeleteAll () throws Exception 
	{
		loadData() ;
		
		ICanvasObjectSelection objectSelection = this.dbModel.newCanvasObjectSelection() ;
		objectSelection.addShape(shapeNode1) ;
		objectSelection.addShape(shapeNode2) ;
		this.dbModel.removeSubgraph(objectSelection);
		map1Manager.synchronise() ;
	}

}
