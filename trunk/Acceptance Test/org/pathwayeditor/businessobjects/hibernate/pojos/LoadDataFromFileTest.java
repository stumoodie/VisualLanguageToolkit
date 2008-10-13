/**
 * 
 */
package org.pathwayeditor.businessobjects.hibernate.pojos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;

import org.junit.Test;
import org.pathwayeditor.businessobjects.drawingprimitives.ICanvas;
import org.pathwayeditor.businessobjects.drawingprimitives.IModel;
import org.pathwayeditor.businessobjects.drawingprimitives.IRootNode;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeNode;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LineStyle;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.Location;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.Size;
import org.pathwayeditor.businessobjects.management.IMapContentPersistenceManager;
import org.pathwayeditor.businessobjects.management.PersistenceManagerNotOpenException;
import org.pathwayeditor.businessobjects.repository.IMap;
import org.pathwayeditor.businessobjects.repository.IRepository;
import org.pathwayeditor.businessobjects.repository.IRootFolder;
import org.pathwayeditor.businessobjects.repository.ISubFolder;
import org.pathwayeditor.testutils.GenericTester;

/**
 * @author ntsorman
 *
 */
public class LoadDataFromFileTest extends GenericTester{
	
	private IRepository repository;
	private IRootFolder rootFolder ;
	private ISubFolder subFolder1 ;
	private ISubFolder subFolder2 ;
	private IMap mapDiagram1 ;
	private IMap mapDiagram2 ;
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
	
	private IShapeAttribute shapeAttribute1 ;
	private IShapeAttribute shapeAttribute2 ;
	private IShapeAttribute shapeAttribute3 ;
	private IShapeAttribute shapeAttribute4 ;
	private IShapeAttribute shapeAttribute5 ;
	private IShapeAttribute shapeAttribute6 ;
	private IShapeAttribute shapeAttribute7 ;
	private IShapeAttribute shapeAttribute8 ;
	
	
	
	private static final String REPOSITORY_NAME ="repo name" ;
	private static final String ROOT_FOLDER_PATH = "/" ;
	private static final String NAME_OF_SUBFOLDER1 = "subfolder1" ;
	private static final String NAME_OF_SUBFOLDER2 = "subfolder2" ;
	private static final String SUBFOLDER1_DESCRIPTION = "description1" ;
	private static final String SUBFOLDER2_DESCRIPTION = "description2" ;
	private static final String SUBFOLDER1_PATH = "/subfolder1/" ;
	private static final String SUBFOLDER2_PATH = "/subfolder2/" ;
	private static final String MAP_DIAGRAM1_NAME = "Diagram name" ;
	private static final String MAP_DIAGRAM2_NAME = "Diagram name2" ;
	private static final String MAP_DIAGRAM1_DESCRIPTION = "Diagram Description" ;
	private static final String MAP_DIAGRAM2_DESCRIPTION = "Diagram Description2" ;
	private static final String SHAPE_ATTR1_DESCR = "descr1" ;
	private static final String SHAPE_ATTR1_DETAIL_DESCR = "detailed descr1" ;
	private static final String SHAPE_ATTR1_URL = "http://www.shape1URL.org" ;
	private static final String SHAPE_ATTR2_DESCR = "descr2" ;
	private static final String SHAPE_ATTR2_DETAIL_DESCR = "detailed descr2" ;
	private static final String SHAPE_ATTR2_URL = "http://www.shape2URL.org" ;
	private static final String SHAPE_ATTR3_DESCR = "descr3" ;
	private static final String SHAPE_ATTR3_DETAIL_DESCR = "detailed descr3" ;
	private static final String SHAPE_ATTR3_URL = "http://www.shape3URL.org" ;
	private static final String SHAPE_ATTR4_DESCR = "descr4" ;
	private static final String SHAPE_ATTR4_DETAIL_DESCR = "detailed descr4" ;
	private static final String SHAPE_ATTR4_URL = "http://www.shape4URL.org" ;
	private static final String SHAPE_ATTR5_DESCR = "descr5" ;
	private static final String SHAPE_ATTR5_DETAIL_DESCR = "detailed descr5" ;
	private static final String SHAPE_ATTR5_URL = "http://www.shape5URL.org" ;
	private static final String SHAPE_ATTR6_DESCR = "descr6" ;
	private static final String SHAPE_ATTR6_DETAIL_DESCR = "detailed descr6" ;
	private static final String SHAPE_ATTR6_URL = "http://www.shape6URL.org" ;
	private static final String SHAPE_ATTR7_DESCR = "descr7" ;
	private static final String SHAPE_ATTR7_DETAIL_DESCR = "detailed descr7" ;
	private static final String SHAPE_ATTR7_URL = "http://www.shape7URL.org" ;
	private static final String SHAPE_ATTR8_DESCR = "descr8" ;
	private static final String SHAPE_ATTR8_DETAIL_DESCR = "detailed descr8" ;
	private static final String SHAPE_ATTR8_URL = "http://www.shape8URL.org" ;
	
	private static final int NUMBER_OF_SUBFOLDERS_IN_ROOT = 2 ;
	private static final int NUMBER_OF_MAPS_IN_ROOT = 0 ;
	private static final int ROOT_FOLDER_INODE = 1 ;
	private static final int NUMBER_OF_MAPS_IN_SUBFOLDERS = 1 ;
	private static final int NUMBER_OF_SUBFOLDERS_IN_SUBFOLDER = 0 ;
	private static final int SUBFOLDER1_INODE = 2 ;
	private static final int SUBFOLDER2_INODE = 3 ;
	private static final int MAP_DIAGRAM1_INODE = 4 ;
	private static final int MAP_DIAGRAM2_INODE = 5 ;
	private static final int DATE_CHECK =1 ;
	private static final int MONTH_CHECK =0 ;
	private static final int YEAR_CHECK =70 ;
	private static final int ROOT_NODE_INDEX = 0 ;
	private static final int NUM_OF_ROOTNODE_SHAPE_CHILDREN = 2 ;
	private static final int NUM_OF_ROOTNODE_LABEL_CHILDREN = 0 ;
	private static final int NUM_OF_ROOTNODE_LINK_CHILDREN = 0 ;
	private static final int SHAPENODE1_INDEX = 1 ;
	private static final int SHAPENODE2_INDEX = 2 ;
	private static final int SHAPENODE3_INDEX = 3 ;
	private static final int SHAPENODE4_INDEX = 4 ;
	private static final int SHAPENODE5_INDEX = 5 ;
	private static final int SHAPENODE6_INDEX = 6 ;
	private static final int SHAPENODE7_INDEX = 7 ;
	private static final int SHAPENODE8_INDEX = 8 ;
	private static final int NUM_OF_SHAPENODE1_SHAPE_CHILDREN = 2 ;
	private static final int NUM_OF_SHAPENODE1_LABEL_CHILDREN = 0 ;
	private static final int NUM_OF_SHAPENODE1_LINK_CHILDREN = 0 ;
	private static final int SHAPE_ATTRIBUTE1_CREATION_SERIAL = 1 ;
	private static final int SHAPE_ATTRIBUTE1_LINE_WIDTH = 1 ;
	private static final int SHAPE_ATTRIBUTE1_LINE_PADDING = 1 ;
	private static final int NUM_OF_SHAPENODE2_SHAPE_CHILDREN = 2 ;
	private static final int NUM_OF_SHAPENODE2_LABEL_CHILDREN = 0 ;
	private static final int NUM_OF_SHAPENODE2_LINK_CHILDREN = 0 ;
	private static final int SHAPE_ATTRIBUTE2_CREATION_SERIAL = 2 ;
	private static final int SHAPE_ATTRIBUTE2_LINE_WIDTH = 2 ;
	private static final int SHAPE_ATTRIBUTE2_LINE_PADDING = 2 ;
	private static final int NUM_OF_SHAPENODE3_SHAPE_CHILDREN = 0 ;
	private static final int NUM_OF_SHAPENODE3_LABEL_CHILDREN = 0 ;
	private static final int NUM_OF_SHAPENODE3_LINK_CHILDREN = 0 ;
	private static final int SHAPE_ATTRIBUTE3_CREATION_SERIAL = 3 ;
	private static final int SHAPE_ATTRIBUTE3_LINE_WIDTH = 3 ;
	private static final int SHAPE_ATTRIBUTE3_LINE_PADDING = 3 ;
	private static final int NUM_OF_SHAPENODE4_SHAPE_CHILDREN = 0 ;
	private static final int NUM_OF_SHAPENODE4_LABEL_CHILDREN = 0 ;
	private static final int NUM_OF_SHAPENODE4_LINK_CHILDREN = 0 ;
	private static final int SHAPE_ATTRIBUTE4_CREATION_SERIAL = 4 ;
	private static final int SHAPE_ATTRIBUTE4_LINE_WIDTH = 4 ;
	private static final int SHAPE_ATTRIBUTE4_LINE_PADDING = 4 ;
	private static final int NUM_OF_SHAPENODE5_SHAPE_CHILDREN = 0 ;
	private static final int NUM_OF_SHAPENODE5_LABEL_CHILDREN = 0 ;
	private static final int NUM_OF_SHAPENODE5_LINK_CHILDREN = 0 ;
	private static final int SHAPE_ATTRIBUTE5_CREATION_SERIAL = 5 ;
	private static final int SHAPE_ATTRIBUTE5_LINE_WIDTH = 5 ;
	private static final int SHAPE_ATTRIBUTE5_LINE_PADDING = 5 ;
	private static final int NUM_OF_SHAPENODE6_SHAPE_CHILDREN = 1 ;
	private static final int NUM_OF_SHAPENODE6_LABEL_CHILDREN = 0 ;
	private static final int NUM_OF_SHAPENODE6_LINK_CHILDREN = 0 ;
	private static final int SHAPE_ATTRIBUTE6_CREATION_SERIAL = 6 ;
	private static final int SHAPE_ATTRIBUTE6_LINE_WIDTH = 6 ;
	private static final int SHAPE_ATTRIBUTE6_LINE_PADDING = 6 ;
	private static final int NUM_OF_SHAPENODE7_SHAPE_CHILDREN = 1 ;
	private static final int NUM_OF_SHAPENODE7_LABEL_CHILDREN = 0 ;
	private static final int NUM_OF_SHAPENODE7_LINK_CHILDREN = 0 ;
	private static final int SHAPE_ATTRIBUTE7_CREATION_SERIAL = 7 ;
	private static final int SHAPE_ATTRIBUTE7_LINE_WIDTH = 7 ;
	private static final int SHAPE_ATTRIBUTE7_LINE_PADDING = 7 ;
	private static final int NUM_OF_SHAPENODE8_SHAPE_CHILDREN = 0 ;
	private static final int NUM_OF_SHAPENODE8_LABEL_CHILDREN = 0 ;
	private static final int NUM_OF_SHAPENODE8_LINK_CHILDREN = 0 ;
	private static final int SHAPE_ATTRIBUTE8_CREATION_SERIAL = 8 ;
	private static final int SHAPE_ATTRIBUTE8_LINE_WIDTH = 8 ;
	private static final int SHAPE_ATTRIBUTE8_LINE_PADDING = 8 ;
	
	private static final RGB RGB_100 = new RGB ( 100 , 100 , 100 ) ;
	private static final RGB RGB_101 = new RGB ( 101 , 101 , 101 ) ;
	private static final RGB RGB_102 = new RGB ( 102 , 102 , 102 ) ;
	private static final RGB RGB_103 = new RGB ( 103 , 103 , 103 ) ;
	private static final RGB RGB_104 = new RGB ( 104 , 104 , 104 ) ;
	private static final RGB RGB_105 = new RGB ( 105 , 105 , 105 ) ;
	private static final RGB RGB_106 = new RGB ( 106 , 106 , 106 ) ;
	private static final RGB RGB_107 = new RGB ( 107 , 107 , 107 ) ;
	private static final RGB RGB_108 = new RGB ( 108 , 108 	, 108 ) ;
	
	private static final Size SIZE_10 = new Size ( 10 , 10 ) ;
	private static final Size SIZE_51 = new Size ( 51 , 51 ) ;
	private static final Size SIZE_52 = new Size ( 52 , 52 ) ;
	private static final Size SIZE_53 = new Size ( 53 , 53 ) ;
	private static final Size SIZE_54 = new Size ( 54 , 54 ) ;
	private static final Size SIZE_55 = new Size ( 55 , 55 ) ;
	private static final Size SIZE_56 = new Size ( 56 , 56 ) ;
	private static final Size SIZE_57 = new Size ( 57 , 57 ) ;
	private static final Size SIZE_58 = new Size ( 58 , 58 ) ;
	
	private static final Location LOCATION_51 = new Location ( 51, 51) ;
	private static final Location LOCATION_52 = new Location ( 52, 52) ;
	private static final Location LOCATION_53 = new Location ( 53, 53) ;
	private static final Location LOCATION_54 = new Location ( 54, 54) ;
	private static final Location LOCATION_55 = new Location ( 55, 55) ;
	private static final Location LOCATION_56 = new Location ( 56, 56) ;
	private static final Location LOCATION_57 = new Location ( 57, 57) ;
	private static final Location LOCATION_58 = new Location ( 58, 58) ;

	/* (non-Javadoc)
	 * @see org.pathwayeditor.testutils.GenericTester#doAdditionalSetUp()
	 */
	@Override
	protected void doAdditionalSetUp() {
		try {
			repository = this.getBusinessObjectFactory().getRepository();
		} catch (PersistenceManagerNotOpenException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	private void loadObjects () throws Exception
	{
		
		rootFolder = repository.getRootFolder() ;
		
		Iterator<ISubFolder> subFolders = repository.getRootFolder().getSubFolderIterator() ;
		
		subFolder1 = (ISubFolder)repository.getFolderByPath( SUBFOLDER1_PATH) ;
		subFolder2 = (ISubFolder)repository.getFolderByPath( SUBFOLDER2_PATH) ;
		
		mapDiagram1 = subFolder1.getMapIterator().next() ;
		mapDiagram2 = subFolder2.getMapIterator().next() ;
		
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
				shapeAttribute1 = tempShapeNode.getAttribute() ;
			}
			else
			{
				shapeNode2 = tempShapeNode ;
				shapeAttribute2 = tempShapeNode.getAttribute() ;
			}
			
		}
		
		Iterator<IShapeNode> shape1ChildIterator = shapeNode1.getSubCanvas().shapeIterator() ;
		while ( shape1ChildIterator.hasNext())
		{
			IShapeNode tempShapeNode = shape1ChildIterator.next();
			
			if ( tempShapeNode.getIndex() ==3 )
			{
				shapeNode3 = tempShapeNode ;
				shapeAttribute3 = tempShapeNode.getAttribute() ;
			}
			else
			if  ( tempShapeNode.getIndex() ==4 )
			{
				shapeNode4 = tempShapeNode ;
				shapeAttribute4 = tempShapeNode.getAttribute() ;
			}
			
		}
		
		Iterator<IShapeNode> shape2ChildIterator = shapeNode2.getSubCanvas().shapeIterator() ;
		while ( shape2ChildIterator.hasNext())
		{
			IShapeNode tempShapeNode = shape2ChildIterator.next();
			
			if ( tempShapeNode.getIndex() ==5 )
			{
				shapeNode5 = tempShapeNode ;
				shapeAttribute5 = tempShapeNode.getAttribute() ;
			}
			else
			if  ( tempShapeNode.getIndex() ==6 )
			{
				shapeNode6 = tempShapeNode ;
				shapeAttribute6  = tempShapeNode.getAttribute() ;
			}
			
		}
		
		shapeNode7 = shapeNode6.getSubCanvas().shapeIterator().next() ;
		shapeAttribute7 = shapeNode7.getAttribute() ;
		
		shapeNode8 = shapeNode7.getSubCanvas().shapeIterator().next() ;
		shapeAttribute8 = shapeNode8.getAttribute() ;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.testutils.GenericTester#doAdditionalTearDown()
	 */
	@Override
	protected void doAdditionalTearDown() {
		repository = null;
		rootFolder = null ;
		subFolder1 = null ;
		subFolder2 = null ;
		mapDiagram1 = null ;
		mapDiagram2 = null ;
		dbCanvas = null ;
		dbModel = null ;
		dbRootNode = null ;
		
		shapeNode1 = null ;
		shapeNode2 = null ;
		shapeNode3 = null ;
		shapeNode4 = null ;
		shapeNode5 = null ;
		shapeNode6 = null ;
		shapeNode7 = null ;
		shapeNode8 = null ;
		
		shapeAttribute1 = null ;
		shapeAttribute2 = null ;
		shapeAttribute3 = null ;
		shapeAttribute4 = null ;
		shapeAttribute5 = null ;
		shapeAttribute6 = null ;
		shapeAttribute7 = null ;
		shapeAttribute8 = null ;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.testutils.GenericTester#getDbUnitDataFilePath()
	 */
	@Override
	protected String getDbUnitDataFilePath() {
		return "Acceptance Test/DBConsistencyTestSourceData/DBSourceData.xml";
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.testutils.GenericTester#getTestRepositoryName()
	 */
	@Override
	protected String getTestRepositoryName() {
		return REPOSITORY_NAME ;
	}
	
	@Test
	public void testRepositoryIsLoadedProperly () throws Exception 
	{
		loadObjects() ;
		assertEquals ( "correct Repository name" , REPOSITORY_NAME , repository.getName()) ;
	}
	
	@Test
	public void testCheckRootFolderLoadedProperly () throws Exception 
	{
		loadObjects() ;
		assertEquals ( "belongs to repository" , repository , rootFolder.getRepository()) ;
		assertEquals ( "No maps" , NUMBER_OF_MAPS_IN_ROOT , rootFolder.getNumMaps() ) ;
		assertEquals ( "two subfolders" , NUMBER_OF_SUBFOLDERS_IN_ROOT , rootFolder.getNumSubFolders() ) ;
		assertEquals ( "path of root" , ROOT_FOLDER_PATH , rootFolder.getPath()) ;
		assertEquals ( "root inode" , ROOT_FOLDER_INODE , rootFolder.getINode()) ;
	}
	
	@Test
	public void testCheckSubFoldersLoadedProperly () throws Exception
	{
		loadObjects() ;
		assertNotNull ( "subfolder1 initialized" , subFolder1 ) ;
		assertNotNull ( "subfolder2 initialized" , subFolder2 ) ;
		
		assertEquals ( "subfolder1 name" , NAME_OF_SUBFOLDER1 , subFolder1.getName()) ;
		assertEquals ( "one map inside" , NUMBER_OF_MAPS_IN_SUBFOLDERS , subFolder1.getNumMaps()) ;
		assertEquals ( "no subfolders" , NUMBER_OF_SUBFOLDERS_IN_SUBFOLDER , subFolder1.getNumSubFolders()) ;
		assertEquals ( "description check" , SUBFOLDER1_DESCRIPTION , subFolder1.getDescription()) ;
		assertEquals ( "subfolder1 inode" , SUBFOLDER1_INODE , subFolder1.getINode()) ;
		assertEquals ( "parent check" , rootFolder , subFolder1.getParent()) ;
		assertEquals ( "subfolder1 path" , SUBFOLDER1_PATH , subFolder1.getPath()) ;
		assertEquals ( "repository check" , repository , subFolder1.getRepository() );
		
		assertEquals ( "subfolder2 name" , NAME_OF_SUBFOLDER2 , subFolder2.getName()) ;
		assertEquals ( "one map inside" , NUMBER_OF_MAPS_IN_SUBFOLDERS , subFolder2.getNumMaps()) ;
		assertEquals ( "no subfolders" , NUMBER_OF_SUBFOLDERS_IN_SUBFOLDER , subFolder2.getNumSubFolders()) ;
		assertEquals ( "description check" , SUBFOLDER2_DESCRIPTION , subFolder2.getDescription()) ;
		assertEquals ( "subfolder2 inode" , SUBFOLDER2_INODE , subFolder2.getINode()) ;
		assertEquals ( "parent check" , rootFolder , subFolder2.getParent()) ;
		assertEquals ( "subfolder2 path" , SUBFOLDER2_PATH , subFolder2.getPath()) ;
		assertEquals ( "repository check" , repository , subFolder2.getRepository() );
	}
	
	@Test
	public void testCheckMapDiagramsLoadedProperly () throws Exception 
	{
		loadObjects() ;
		assertNotNull ( "mapDiagram1 is null" , mapDiagram1) ;
		assertNotNull ( "mapDiagram2 loaded" , mapDiagram2 ) ;
		
		assertEquals ( "map Name" , MAP_DIAGRAM1_NAME , mapDiagram1.getName() ) ;
		assertEquals ( "map description" , MAP_DIAGRAM1_DESCRIPTION , mapDiagram1.getDescription() );
		assertEquals ( "map diagram1 inode" , MAP_DIAGRAM1_INODE , mapDiagram1.getINode()) ;
		assertEquals ( "parent folder" , subFolder1 , mapDiagram1.getOwner()) ;
		assertEquals ( "repository check" , repository , mapDiagram1.getRepository() ) ;
		
		assertEquals ( "map Name" , MAP_DIAGRAM2_NAME , mapDiagram2.getName() ) ;
		assertEquals ( "map description" , MAP_DIAGRAM2_DESCRIPTION , mapDiagram2.getDescription() );
		assertEquals ( "map diagram1 inode" , MAP_DIAGRAM2_INODE , mapDiagram2.getINode()) ;
		assertEquals ( "parent folder" , subFolder2 , mapDiagram2.getOwner()) ;
		assertEquals ( "repository check" , repository , mapDiagram2.getRepository() ) ;
		
	}
	
	@Test
	public void testCheckIntegrityOfCanvas () throws Exception 
	{
		
		loadObjects() ;
		assertNotNull ( "canvas is not null" , this.dbCanvas ) ;
		
		assertEquals ( "check owning map" , mapDiagram1 , dbCanvas.getOwningMap()) ;
		assertEquals ( "canvas size" , SIZE_10 , dbCanvas.getCanvasSize() ) ;
		assertEquals ( "grid size" , SIZE_10 , dbCanvas.getGridSize() ) ;
		assertEquals ( "created check day" , DATE_CHECK  , dbCanvas.getCreated().getDate() );
		assertEquals ( "created check month" , MONTH_CHECK  , dbCanvas.getCreated().getMonth() );
		assertEquals ( "created check year" , YEAR_CHECK  , dbCanvas.getCreated().getYear() );
		assertEquals ( "modified check day" , DATE_CHECK  , dbCanvas.getModified().getDate() );
		assertEquals ( "modified check month" , MONTH_CHECK  , dbCanvas.getModified().getMonth() );
		assertEquals ( "modified check year" , YEAR_CHECK  , dbCanvas.getModified().getYear() );
		assertEquals ( "check color" , RGB_100 , dbCanvas.getBackgroundColour()) ;
		assertTrue ( "grid enabled" , dbCanvas.isGridEnabled()) ;
		assertTrue ( "snap grid enabled" , dbCanvas.isSnapToGridOn()) ;
	}
	
	@Test
	public void testCheckModelIsCorrect () throws Exception
	{
		loadObjects() ;
		assertNotNull ( "model is not null" , dbModel) ;
		
		assertEquals ( "belongs to Canvas" , dbCanvas , dbModel.getCanvas()) ;
		assertNotNull ( "has root node" , dbModel.getRootNode()) ;
	}
	
	@Test
	public void testCheckIntegrityOfRootNode () throws Exception
	{
		loadObjects() ;
		assertNotNull ( "is not null" , dbRootNode) ;
		assertNotNull ("rootNode has SubModel" , dbRootNode.getSubCanvas()) ;
		
		assertEquals ( "root node index" , ROOT_NODE_INDEX , dbRootNode.getIndex() ) ;
		assertEquals ( "model check" , dbModel , dbRootNode.getModel()) ;
		assertNull ( "no parent for rootNode" , dbRootNode.getParent() ) ;
		assertEquals ( "rootnode has 2 shapes", NUM_OF_ROOTNODE_SHAPE_CHILDREN , dbRootNode.getSubCanvas().getNumShapes()) ;
//		assertEquals ( "rootnode has no labels", NUM_OF_ROOTNODE_LABEL_CHILDREN , dbRootNode.getSubCanvas().getNumLabels()) ;
		assertEquals ( "rootnode has no links", NUM_OF_ROOTNODE_LINK_CHILDREN , dbRootNode.getSubCanvas().getNumLinks()) ;
		
		int numOfShapeNodes = 0 ;
		
		Iterator <IShapeNode> shapeIterator = dbRootNode.getSubCanvas().shapeIterator() ;
		
		while ( shapeIterator.hasNext())
		{
			shapeIterator.next();
			numOfShapeNodes ++ ;
		}
		
		assertEquals ( "2 shapeNodes" , 2 , numOfShapeNodes) ;
	}
	
	@Test
	public void testCheckIntegrityOfShapeNode1andShapeAttribute1 () throws Exception
	{
		loadObjects () ;
		assertNotNull ( "is not null" , shapeNode1) ;
		assertNotNull ("shapeNode1 has SubModel" , shapeNode1.getSubCanvas()) ;
		assertNotNull ( "is linkedtoAttribute" , shapeNode1.getAttribute() ) ;
		
		assertEquals ( "shapenode1 index" , SHAPENODE1_INDEX , shapeNode1.getIndex() ) ;
		assertEquals ( "model check" , dbModel , shapeNode1.getModel()) ;
		assertEquals ( "parent is rootNode" , dbRootNode.getIndex() , shapeNode1.getParent().getIndex() ) ;
		assertEquals ( "shapeNode1 has 2 shapes", NUM_OF_SHAPENODE1_SHAPE_CHILDREN , shapeNode1.getSubCanvas().getNumShapes()) ;
//		assertEquals ( "shapeNode1 has no labels", NUM_OF_SHAPENODE1_LABEL_CHILDREN , shapeNode1.getSubCanvas().getNumLabels()) ;
		assertEquals ( "shapeNode1 has no links", NUM_OF_SHAPENODE1_LINK_CHILDREN , shapeNode1.getSubCanvas().getNumLinks()) ;
		
		assertEquals ( "shapeAttribute1 creation serial" , SHAPE_ATTRIBUTE1_CREATION_SERIAL , shapeAttribute1.getCreationSerial()  ) ;
		assertEquals ( "shapeAttribute1 description" , SHAPE_ATTR1_DESCR , shapeAttribute1.getDescription() );
		assertEquals ( "shapeAttribute1 detail description" , SHAPE_ATTR1_DETAIL_DESCR , shapeAttribute1.getDetailedDescription() );
		assertEquals ( "shapeAttribute1 url" , SHAPE_ATTR1_URL , shapeAttribute1.getUrl() );
		assertEquals ( "shapeAttribute1 fill color" , RGB_101 , shapeAttribute1.getFillColour()) ;
		assertEquals ( "shapeAttribute1 line color" , RGB_101 , shapeAttribute1.getLineColour()) ;
		assertEquals ( "shapeAttribute1 line style" , LineStyle.SOLID ,shapeAttribute1.getLineStyle() ) ;
		assertEquals ( "shapeAttribute1 line width" , SHAPE_ATTRIBUTE1_LINE_WIDTH ,shapeAttribute1.getLineWidth() ) ;
		assertEquals ( "shapeAttribute1 line padding" , SHAPE_ATTRIBUTE1_LINE_PADDING , shapeAttribute1.getPadding() ) ;
		assertEquals ( "shapeAttribute1 location" , LOCATION_51, shapeAttribute1.getLocation() ) ;
		assertEquals ( "shapeAttribute1 line padding" , SIZE_51 , shapeAttribute1.getSize()) ;
		
		int numOfShapeNodes = 0 ;
		
		Iterator <IShapeNode> shapeIterator = shapeNode1.getSubCanvas().shapeIterator() ;
		
		while ( shapeIterator.hasNext())
		{
			shapeIterator.next();
			numOfShapeNodes ++ ;
		}
		
		assertEquals ( "2 shapeNodes" , 2 , numOfShapeNodes) ;
				
	}
	
	@Test
	public void testCheckIntegrityOfShapeNode2AndShapeAttribute2 () throws Exception
	{
		loadObjects () ;
		assertNotNull ( "is not null" , shapeNode2) ;
		assertNotNull ("shapeNode2 has SubModel" , shapeNode2.getSubCanvas()) ;
		assertNotNull ( "is linkedtoAttribute" , shapeNode2.getAttribute() ) ;
		
		assertEquals ( "shapenode2 index" , SHAPENODE2_INDEX , shapeNode2.getIndex() ) ;
		assertEquals ( "model check" , dbModel , shapeNode2.getModel()) ;
		assertEquals ( "parent is rootNode" , dbRootNode.getIndex() , shapeNode2.getParent().getIndex() ) ;
		assertEquals ( "shapeNode1 has 2 shapes", NUM_OF_SHAPENODE2_SHAPE_CHILDREN , shapeNode2.getSubCanvas().getNumShapes()) ;
//		assertEquals ( "shapeNode1 has no labels", NUM_OF_SHAPENODE2_LABEL_CHILDREN , shapeNode2.getSubCanvas().getNumLabels()) ;
		assertEquals ( "shapeNode1 has no links", NUM_OF_SHAPENODE2_LINK_CHILDREN , shapeNode2.getSubCanvas().getNumLinks()) ;
		
		assertEquals ( "shapeAttribute2 creation serial" , SHAPE_ATTRIBUTE2_CREATION_SERIAL , shapeAttribute2.getCreationSerial()  ) ;
		assertEquals ( "shapeAttribute2 description" , SHAPE_ATTR2_DESCR , shapeAttribute2.getDescription() );
		assertEquals ( "shapeAttribute2 detail description" , SHAPE_ATTR2_DETAIL_DESCR , shapeAttribute2.getDetailedDescription() );
		assertEquals ( "shapeAttribute2 url" , SHAPE_ATTR2_URL , shapeAttribute2.getUrl() );
		assertEquals ( "shapeAttribute2 fill color" , RGB_102 , shapeAttribute2.getFillColour()) ;
		assertEquals ( "shapeAttribute2 line color" , RGB_102 , shapeAttribute2.getLineColour()) ;
		assertEquals ( "shapeAttribute2 line style" , LineStyle.DASHED ,shapeAttribute2.getLineStyle() ) ;
		assertEquals ( "shapeAttribute2 line width" , SHAPE_ATTRIBUTE2_LINE_WIDTH ,shapeAttribute2.getLineWidth() ) ;
		assertEquals ( "shapeAttribute2 line padding" , SHAPE_ATTRIBUTE2_LINE_PADDING , shapeAttribute2.getPadding() ) ;
		assertEquals ( "shapeAttribute2 location" , LOCATION_52, shapeAttribute2.getLocation() ) ;
		assertEquals ( "shapeAttribute2 line padding" , SIZE_52 , shapeAttribute2.getSize()) ;
		
		int numOfShapeNodes = 0 ;
		
		Iterator <IShapeNode> shapeIterator = shapeNode2.getSubCanvas().shapeIterator() ;
		
		while ( shapeIterator.hasNext())
		{
			shapeIterator.next();
			numOfShapeNodes ++ ;
		}
		
		assertEquals ( "2 shapeNodes" , 2 , numOfShapeNodes) ;
				
	}
	
	@Test
	public void testCheckIntegrityOfShapeNode3AndShapeAttribute3 () throws Exception
	{
		loadObjects () ;
		assertNotNull ( "is not null" , shapeNode3) ;
		assertNotNull ("shapeNode3 has SubModel" , shapeNode3.getSubCanvas()) ;
		assertNotNull ( "is linkedtoAttribute" , shapeNode3.getAttribute() ) ;
		
		assertEquals ( "shapenode3 index" , SHAPENODE3_INDEX , shapeNode3.getIndex() ) ;
		assertEquals ( "model check" , dbModel , shapeNode3.getModel()) ;
		assertEquals ( "parent is shapeNode1" , shapeNode1.getIndex() , shapeNode3.getParent().getIndex() ) ;
		assertEquals ( "shapeNode3 has no shapes", NUM_OF_SHAPENODE3_SHAPE_CHILDREN , shapeNode3.getSubCanvas().getNumShapes()) ;
		assertEquals ( "shapeNode3 has no labels", NUM_OF_SHAPENODE3_LABEL_CHILDREN , shapeNode3.getSubCanvas().getNumLabels()) ;
		assertEquals ( "shapeNode3 has no links", NUM_OF_SHAPENODE3_LINK_CHILDREN , shapeNode3.getSubCanvas().getNumLinks()) ;
		
		assertEquals ( "shapeAttribute3 creation serial" , SHAPE_ATTRIBUTE3_CREATION_SERIAL , shapeAttribute3.getCreationSerial()  ) ;
		assertEquals ( "shapeAttribute3 description" , SHAPE_ATTR3_DESCR , shapeAttribute3.getDescription() );
		assertEquals ( "shapeAttribute3 detail description" , SHAPE_ATTR3_DETAIL_DESCR , shapeAttribute3.getDetailedDescription() );
		assertEquals ( "shapeAttribute3 url" , SHAPE_ATTR3_URL , shapeAttribute3.getUrl() );
		assertEquals ( "shapeAttribute3 fill color" , RGB_103 , shapeAttribute3.getFillColour()) ;
		assertEquals ( "shapeAttribute3 line color" , RGB_103 , shapeAttribute3.getLineColour()) ;
		assertEquals ( "shapeAttribute3 line style" , LineStyle.DASH_DOT ,shapeAttribute3.getLineStyle() ) ;
		assertEquals ( "shapeAttribute3 line width" , SHAPE_ATTRIBUTE3_LINE_WIDTH ,shapeAttribute3.getLineWidth() ) ;
		assertEquals ( "shapeAttribute3 line padding" , SHAPE_ATTRIBUTE3_LINE_PADDING , shapeAttribute3.getPadding() ) ;
		assertEquals ( "shapeAttribute3 location" , LOCATION_53, shapeAttribute3.getLocation() ) ;
		assertEquals ( "shapeAttribute3 line padding" , SIZE_53 , shapeAttribute3.getSize()) ;
		
		int numOfShapeNodes = 0 ;
		
		Iterator <IShapeNode> shapeIterator = shapeNode3.getSubCanvas().shapeIterator() ;
		
		while ( shapeIterator.hasNext())
		{
			shapeIterator.next();
			numOfShapeNodes ++ ;
		}
		
		assertEquals ( "0 shapeNodes" , 0 , numOfShapeNodes) ;
				
	}
	
	@Test
	public void testCheckIntegrityOfShapeNode4AndShapeAttribute4 () throws Exception
	{
		loadObjects () ;
		assertNotNull ( "is not null" , shapeNode4) ;
		assertNotNull ("shapeNode4 has SubModel" , shapeNode4.getSubCanvas()) ;
		assertNotNull ( "is linkedtoAttribute" , shapeNode4.getAttribute() ) ;
		
		assertEquals ( "shapenode4 index" , SHAPENODE4_INDEX , shapeNode4.getIndex() ) ;
		assertEquals ( "model check" , dbModel , shapeNode4.getModel()) ;
		assertEquals ( "parent is shapeNode1" , shapeNode1.getIndex() , shapeNode4.getParent().getIndex() ) ;
		assertEquals ( "shapeNode4 has no shapes", NUM_OF_SHAPENODE4_SHAPE_CHILDREN , shapeNode4.getSubCanvas().getNumShapes()) ;
		assertEquals ( "shapeNode4 has no labels", NUM_OF_SHAPENODE4_LABEL_CHILDREN , shapeNode4.getSubCanvas().getNumLabels()) ;
		assertEquals ( "shapeNode4 has no links", NUM_OF_SHAPENODE4_LINK_CHILDREN , shapeNode4.getSubCanvas().getNumLinks()) ;
		
		assertEquals ( "shapeAttribute4 creation serial" , SHAPE_ATTRIBUTE4_CREATION_SERIAL , shapeAttribute4.getCreationSerial()  ) ;
		assertEquals ( "shapeAttribute4 description" , SHAPE_ATTR4_DESCR , shapeAttribute4.getDescription() );
		assertEquals ( "shapeAttribute4 detail description" , SHAPE_ATTR4_DETAIL_DESCR , shapeAttribute4.getDetailedDescription() );
		assertEquals ( "shapeAttribute4 url" , SHAPE_ATTR4_URL , shapeAttribute4.getUrl() );
		assertEquals ( "shapeAttribute4 fill color" , RGB_104 , shapeAttribute4.getFillColour()) ;
		assertEquals ( "shapeAttribute4 line color" , RGB_104 , shapeAttribute4.getLineColour()) ;
		assertEquals ( "shapeAttribute4 line style" , LineStyle.DASH_DOT_DOT ,shapeAttribute4.getLineStyle() ) ;
		assertEquals ( "shapeAttribute4 line width" , SHAPE_ATTRIBUTE4_LINE_WIDTH ,shapeAttribute4.getLineWidth() ) ;
		assertEquals ( "shapeAttribute4 line padding" , SHAPE_ATTRIBUTE4_LINE_PADDING , shapeAttribute4.getPadding() ) ;
		assertEquals ( "shapeAttribute4 location" , LOCATION_54, shapeAttribute4.getLocation() ) ;
		assertEquals ( "shapeAttribute4 line padding" , SIZE_54 , shapeAttribute4.getSize()) ;
		
		int numOfShapeNodes = 0 ;
		
		Iterator <IShapeNode> shapeIterator = shapeNode4.getSubCanvas().shapeIterator() ;
		
		while ( shapeIterator.hasNext())
		{
			shapeIterator.next();
			numOfShapeNodes ++ ;
		}
		
		assertEquals ( "0 shapeNodes" , 0 , numOfShapeNodes) ;
				
	}
	
	@Test
	public void testCheckIntegrityOfShapeNode5AndShapeAttribute5 () throws Exception
	{
		loadObjects () ;
		assertNotNull ( "is not null" , shapeNode5) ;
		assertNotNull ("shapeNode5 has SubModel" , shapeNode5.getSubCanvas()) ;
		assertNotNull ( "is linkedtoAttribute" , shapeNode5.getAttribute() ) ;
		
		assertEquals ( "shapenode5 index" , SHAPENODE5_INDEX , shapeNode5.getIndex() ) ;
		assertEquals ( "model check" , dbModel , shapeNode5.getModel()) ;
		assertEquals ( "parent is shapeNode2" , shapeNode2.getIndex() , shapeNode5.getParent().getIndex() ) ;
		assertEquals ( "shapeNode5 has no shapes", NUM_OF_SHAPENODE5_SHAPE_CHILDREN , shapeNode5.getSubCanvas().getNumShapes()) ;
		assertEquals ( "shapeNode5 has no labels", NUM_OF_SHAPENODE5_LABEL_CHILDREN , shapeNode5.getSubCanvas().getNumLabels()) ;
		assertEquals ( "shapeNode5 has no links", NUM_OF_SHAPENODE5_LINK_CHILDREN , shapeNode5.getSubCanvas().getNumLinks()) ;
		
		assertEquals ( "shapeAttribute5 creation serial" , SHAPE_ATTRIBUTE5_CREATION_SERIAL , shapeAttribute5.getCreationSerial()  ) ;
		assertEquals ( "shapeAttribute5 description" , SHAPE_ATTR5_DESCR , shapeAttribute5.getDescription() );
		assertEquals ( "shapeAttribute5 detail description" , SHAPE_ATTR5_DETAIL_DESCR , shapeAttribute5.getDetailedDescription() );
		assertEquals ( "shapeAttribute5 url" , SHAPE_ATTR5_URL , shapeAttribute5.getUrl() );
		assertEquals ( "shapeAttribute5 fill color" , RGB_105 , shapeAttribute5.getFillColour()) ;
		assertEquals ( "shapeAttribute5 line color" , RGB_105 , shapeAttribute5.getLineColour()) ;
		assertEquals ( "shapeAttribute5 line style" , LineStyle.DOT ,shapeAttribute5.getLineStyle() ) ;
		assertEquals ( "shapeAttribute5 line width" , SHAPE_ATTRIBUTE5_LINE_WIDTH ,shapeAttribute5.getLineWidth() ) ;
		assertEquals ( "shapeAttribute5 line padding" , SHAPE_ATTRIBUTE5_LINE_PADDING , shapeAttribute5.getPadding() ) ;
		assertEquals ( "shapeAttribute5 location" , LOCATION_55, shapeAttribute5.getLocation() ) ;
		assertEquals ( "shapeAttribute5 line padding" , SIZE_55 , shapeAttribute5.getSize()) ;
		
		int numOfShapeNodes = 0 ;
		
		Iterator <IShapeNode> shapeIterator = shapeNode5.getSubCanvas().shapeIterator() ;
		
		while ( shapeIterator.hasNext())
		{
			shapeIterator.next();
			numOfShapeNodes ++ ;
		}
		
		assertEquals ( "0 shapeNodes" , 0 , numOfShapeNodes) ;
				
	}
	
	@Test
	public void testCheckIntegrityOfShapeNode6AndShapeAttribute6 () throws Exception
	{
		loadObjects () ;
		assertNotNull ( "is not null" , shapeNode6) ;
		assertNotNull ("shapeNode6 has SubModel" , shapeNode6.getSubCanvas()) ;
		assertNotNull ( "is linkedtoAttribute" , shapeNode6.getAttribute() ) ;
		
		assertEquals ( "shapenode6 index" , SHAPENODE6_INDEX , shapeNode6.getIndex() ) ;
		assertEquals ( "model check" , dbModel , shapeNode6.getModel()) ;
		assertEquals ( "parent is shapeNode2" , shapeNode2.getIndex() , shapeNode6.getParent().getIndex() ) ;
		assertEquals ( "shapeNode6 one shapes", NUM_OF_SHAPENODE6_SHAPE_CHILDREN , shapeNode6.getSubCanvas().getNumShapes()) ;
//		assertEquals ( "shapeNode6 has no labels", NUM_OF_SHAPENODE6_LABEL_CHILDREN , shapeNode6.getSubCanvas().getNumLabels()) ;
		assertEquals ( "shapeNode6 has no links", NUM_OF_SHAPENODE6_LINK_CHILDREN , shapeNode6.getSubCanvas().getNumLinks()) ;
		
		assertEquals ( "shapeAttribute6 creation serial" , SHAPE_ATTRIBUTE6_CREATION_SERIAL , shapeAttribute6.getCreationSerial()  ) ;
		assertEquals ( "shapeAttribute6 description" , SHAPE_ATTR6_DESCR , shapeAttribute6.getDescription() );
		assertEquals ( "shapeAttribute6 detail description" , SHAPE_ATTR6_DETAIL_DESCR , shapeAttribute6.getDetailedDescription() );
		assertEquals ( "shapeAttribute6 url" , SHAPE_ATTR6_URL , shapeAttribute6.getUrl() );
		assertEquals ( "shapeAttribute6 fill color" , RGB_106 , shapeAttribute6.getFillColour()) ;
		assertEquals ( "shapeAttribute6 line color" , RGB_106 , shapeAttribute6.getLineColour()) ;
		assertEquals ( "shapeAttribute6 line style" , LineStyle.SOLID ,shapeAttribute6.getLineStyle() ) ;
		assertEquals ( "shapeAttribute6 line width" , SHAPE_ATTRIBUTE6_LINE_WIDTH ,shapeAttribute6.getLineWidth() ) ;
		assertEquals ( "shapeAttribute6 line padding" , SHAPE_ATTRIBUTE6_LINE_PADDING , shapeAttribute6.getPadding() ) ;
		assertEquals ( "shapeAttribute6 location" , LOCATION_56, shapeAttribute6.getLocation() ) ;
		assertEquals ( "shapeAttribute6 line padding" , SIZE_56 , shapeAttribute6.getSize()) ;
		
		int numOfShapeNodes = 0 ;
		
		Iterator <IShapeNode> shapeIterator = shapeNode6.getSubCanvas().shapeIterator() ;
		
		while ( shapeIterator.hasNext())
		{
			shapeIterator.next();
			numOfShapeNodes ++ ;
		}
		
		assertEquals ( "1 shapeNodes" , 1 , numOfShapeNodes) ;
				
	}
	
	@Test
	public void testCheckIntegrityOfShapeNode7AndShapeAttribute7 () throws Exception
	{
		loadObjects () ;
		assertNotNull ( "is not null" , shapeNode7) ;
		assertNotNull ("shapeNode7 has SubModel" , shapeNode7.getSubCanvas()) ;
		assertNotNull ( "is linkedtoAttribute" , shapeNode7.getAttribute() ) ;
		
		assertEquals ( "shapenode7 index" , SHAPENODE7_INDEX , shapeNode7.getIndex() ) ;
		assertEquals ( "model check" , dbModel , shapeNode7.getModel()) ;
		assertEquals ( "parent is shapeNode6" , shapeNode6.getIndex() , shapeNode7.getParent().getIndex() ) ;
		assertEquals ( "shapeNode7 has one shapes", NUM_OF_SHAPENODE7_SHAPE_CHILDREN , shapeNode7.getSubCanvas().getNumShapes()) ;
//		assertEquals ( "shapeNode7 has no labels", NUM_OF_SHAPENODE7_LABEL_CHILDREN , shapeNode7.getSubCanvas().getNumLabels()) ;
		assertEquals ( "shapeNode7 has no links", NUM_OF_SHAPENODE7_LINK_CHILDREN , shapeNode7.getSubCanvas().getNumLinks()) ;
		
		assertEquals ( "shapeAttribute7 creation serial" , SHAPE_ATTRIBUTE7_CREATION_SERIAL , shapeAttribute7.getCreationSerial()  ) ;
		assertEquals ( "shapeAttribute7 description" , SHAPE_ATTR7_DESCR , shapeAttribute7.getDescription() );
		assertEquals ( "shapeAttribute7 detail description" , SHAPE_ATTR7_DETAIL_DESCR , shapeAttribute7.getDetailedDescription() );
		assertEquals ( "shapeAttribute7 url" , SHAPE_ATTR7_URL , shapeAttribute7.getUrl() );
		assertEquals ( "shapeAttribute7 fill color" , RGB_107 , shapeAttribute7.getFillColour()) ;
		assertEquals ( "shapeAttribute7 line color" , RGB_107 , shapeAttribute7.getLineColour()) ;
		assertEquals ( "shapeAttribute7 line style" , LineStyle.DASHED ,shapeAttribute7.getLineStyle() ) ;
		assertEquals ( "shapeAttribute7 line width" , SHAPE_ATTRIBUTE7_LINE_WIDTH ,shapeAttribute7.getLineWidth() ) ;
		assertEquals ( "shapeAttribute7 line padding" , SHAPE_ATTRIBUTE7_LINE_PADDING , shapeAttribute7.getPadding() ) ;
		assertEquals ( "shapeAttribute7 location" , LOCATION_57, shapeAttribute7.getLocation() ) ;
		assertEquals ( "shapeAttribute7 line padding" , SIZE_57 , shapeAttribute7.getSize()) ;
		
		int numOfShapeNodes = 0 ;
		
		Iterator <IShapeNode> shapeIterator = shapeNode7.getSubCanvas().shapeIterator() ;
		
		while ( shapeIterator.hasNext())
		{
			shapeIterator.next();
			numOfShapeNodes ++ ;
		}
		
		assertEquals ( "1 shapeNodes" , 1 , numOfShapeNodes) ;
				
	}
	
	@Test
	public void testCheckIntegrityOfShapeNode8AndShapeAttribute8 () throws Exception
	{
		loadObjects () ;
		assertNotNull ( "is not null" , shapeNode8) ;
		assertNotNull ("shapeNode8 has SubModel" , shapeNode8.getSubCanvas()) ;
		assertNotNull ( "is linkedtoAttribute" , shapeNode8.getAttribute() ) ;
		
		assertEquals ( "shapenode8 index" , SHAPENODE8_INDEX , shapeNode8.getIndex() ) ;
		assertEquals ( "model check" , dbModel , shapeNode8.getModel()) ;
		assertEquals ( "parent is shapeNode7" , shapeNode7.getIndex() , shapeNode8.getParent().getIndex() ) ;
		assertEquals ( "shapeNode8 has one shapes", NUM_OF_SHAPENODE8_SHAPE_CHILDREN , shapeNode8.getSubCanvas().getNumShapes()) ;
		assertEquals ( "shapeNode8 has no labels", NUM_OF_SHAPENODE8_LABEL_CHILDREN , shapeNode8.getSubCanvas().getNumLabels()) ;
		assertEquals ( "shapeNode8 has no links", NUM_OF_SHAPENODE8_LINK_CHILDREN , shapeNode8.getSubCanvas().getNumLinks()) ;
		
		assertEquals ( "shapeAttribute8 creation serial" , SHAPE_ATTRIBUTE8_CREATION_SERIAL , shapeAttribute8.getCreationSerial()  ) ;
		assertEquals ( "shapeAttribute8 description" , SHAPE_ATTR8_DESCR , shapeAttribute8.getDescription() );
		assertEquals ( "shapeAttribute8 detail description" , SHAPE_ATTR8_DETAIL_DESCR , shapeAttribute8.getDetailedDescription() );
		assertEquals ( "shapeAttribute8 url" , SHAPE_ATTR8_URL , shapeAttribute8.getUrl() );
		assertEquals ( "shapeAttribute8 fill color" , RGB_108 , shapeAttribute8.getFillColour()) ;
		assertEquals ( "shapeAttribute8 line color" , RGB_108 , shapeAttribute8.getLineColour()) ;
		assertEquals ( "shapeAttribute8 line style" , LineStyle.DASH_DOT ,shapeAttribute8.getLineStyle() ) ;
		assertEquals ( "shapeAttribute8 line width" , SHAPE_ATTRIBUTE8_LINE_WIDTH ,shapeAttribute8.getLineWidth() ) ;
		assertEquals ( "shapeAttribute8 line padding" , SHAPE_ATTRIBUTE8_LINE_PADDING , shapeAttribute8.getPadding() ) ;
		assertEquals ( "shapeAttribute8 location" , LOCATION_58, shapeAttribute8.getLocation() ) ;
		assertEquals ( "shapeAttribute8 line padding" , SIZE_58 , shapeAttribute8.getSize()) ;
		
		int numOfShapeNodes = 0 ;
		
		Iterator <IShapeNode> shapeIterator = shapeNode8.getSubCanvas().shapeIterator() ;
		
		while ( shapeIterator.hasNext())
		{
			shapeIterator.next();
			numOfShapeNodes ++ ;
		}
		
		assertEquals ( "0 shapeNodes" , 0 , numOfShapeNodes) ;
				
	}
}
	