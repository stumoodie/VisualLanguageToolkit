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
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
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
import org.pathwayeditor.businessobjects.management.IMapContentPersistenceManager;
import org.pathwayeditor.businessobjects.management.PersistenceManagerNotOpenException;
import org.pathwayeditor.businessobjects.repository.IMap;
import org.pathwayeditor.businessobjects.repository.IRepository;
import org.pathwayeditor.businessobjects.repository.IRootFolder;
import org.pathwayeditor.businessobjects.repository.ISubFolder;
import org.pathwayeditor.integrationtestutils.GenericTester;

/**
 * @author ntsorman
 *
 */
@RunWith(JMock.class)
public class CheckDbOperationsCompoundGraphTest extends GenericTester{
	
	private Mockery mockery = new JUnit4Mockery() {{
		 setImposteriser(ClassImposteriser.INSTANCE);
	}};
	
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
	
	private IShapeNode newNode ;
	private ILabelNode newLabel ;
	private ILinkEdge newLinkEdge ;
	
	
	private static final String REPOSITORY_NAME ="repo name" ;
	private static final String SUBFOLDER1_PATH = "/subfolder1/" ;
	private static final String SUBFOLDER2_PATH = "/subfolder2/" ;
	

	private final static String CREATED_LINK_VALIDATION = "Acceptance Test/DBConsistencyTestValidationData/CreatedLinkEdge.xml" ;
	private final static String CREATED_NODE_VALIDATION = "Acceptance Test/DBConsistencyTestValidationData/CreatedShapeNode.xml" ;
	private final static String CREATED_LABEL_VALIDATION = "Acceptance Test/DBConsistencyTestValidationData/CreatedLabelNode.xml" ;
	private final static String DELETED_NODE_VALIDATION = "Acceptance Test/DBConsistencyTestValidationData/DeletedShapeNode.xml" ;
	private final static String MOVED_NODE_VALIDATION = "Acceptance Test/DBConsistencyTestValidationData/movedShapeNode.xml" ;
	private final static String COPIED_NODE_VALIDATION = "Acceptance Test/DBConsistencyTestValidationData/copiedShapeNode.xml" ;

	/* (non-Javadoc)
	 * @see org.pathwayeditor.integrationtestutils.GenericTester#doAdditionalSetUp()
	 */
	@Override
	protected void doAdditionalSetUp() {
		try {
			repository = this.getBusinessObjectFactory().getRepository();
		} catch (PersistenceManagerNotOpenException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	private void loadData () throws Exception 
	{
		rootFolder = repository.getRootFolder() ;
		
//		Iterator<ISubFolder> subFolders = repository.getRootFolder().getSubFolderIterator() ;
		
		subFolder1 = (ISubFolder)repository.getFolderByPath( SUBFOLDER1_PATH) ;
		
		mapDiagram1 = subFolder1.getMapIterator().next() ;
		
		IMapContentPersistenceManager map1Manager = this.getBusinessObjectFactory().openMap(mapDiagram1) ;
		map1Manager.loadContent() ;
		dbCanvas = map1Manager.getCanvas() ;
		
		dbModel = dbCanvas.getModel() ;
		dbRootNode = dbModel.getRootNode() ;
		
		
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
		
		
		
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.integrationtestutils.GenericTester#doAdditionalTearDown()
	 */
	@Override
	protected void doAdditionalTearDown() {
		// TODO Auto-generated method stub
		
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
		IShapeNodeFactory nodeFactory = dbRootNode.getSubCanvas().shapeNodeFactory() ;
		assertTrue ( "can create node" , nodeFactory.canCreateShapeNode()) ;
		newNode = nodeFactory.createShapeNode() ;
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
	
	@Test
	public void testDeleteNode () throws Exception
	{
		loadData ();
		
		ICanvasObjectSelection objectSelection = this.dbModel.newCanvasObjectSelection() ;
		
		objectSelection.addShape(this.shapeNode8);
		this.dbModel.removeSubgraph(objectSelection) ;
		
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
	public void testMoveNode () throws Exception
	{
		loadData() ;
		
		ICanvasObjectSelection objectSelection = this.dbModel.newCanvasObjectSelection() ;
		objectSelection.addShape(shapeNode8) ;
		
		dbRootNode.getSubCanvas().moveHere(objectSelection) ;
		
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
		
		ICanvasObjectSelection objectSelection = this.dbModel.newCanvasObjectSelection() ;
		objectSelection.addShape(shapeNode8) ;
		
		dbRootNode.getSubCanvas().copyHere(objectSelection) ;
		
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

}
