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
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;
import java.util.List;

import org.junit.Test;
import org.pathwayeditor.businessobjects.drawingprimitives.IBendPoint;
import org.pathwayeditor.businessobjects.drawingprimitives.ICanvas;
import org.pathwayeditor.businessobjects.drawingprimitives.ILabelAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.ILabelNode;
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkEdge;
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkTerminus;
import org.pathwayeditor.businessobjects.drawingprimitives.IModel;
import org.pathwayeditor.businessobjects.drawingprimitives.IRootNode;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeNode;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LineStyle;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LinkEndDecoratorShape;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LinkTermType;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationProperty;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IHtmlAnnotationProperty;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IListAnnotationProperty;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.INumberAnnotationProperty;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPlainTextAnnotationProperty;
import org.pathwayeditor.businessobjects.management.IMapPersistenceManager;
import org.pathwayeditor.businessobjects.repository.IMap;
import org.pathwayeditor.businessobjects.repository.IRepository;
import org.pathwayeditor.businessobjects.repository.IRootFolder;
import org.pathwayeditor.businessobjects.repository.ISubFolder;
import org.pathwayeditor.figure.geometry.Dimension;
import org.pathwayeditor.figure.geometry.Point;
import org.pathwayeditor.testutils.GenericTester;

/**
 * @author ntsorman
 *
 */
public class LoadDataFromFileTest extends GenericTester{
	
	private IRepository repository;
	private ICanvas dbCanvas ;
	
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
	
	private static final int NUMBER_OF_SUBFOLDERS_IN_ROOT = 2 ;
	private static final int NUMBER_OF_MAPS_IN_ROOT = 0 ;
	private static final int ROOT_FOLDER_INODE = 1 ;
	private static final int NUMBER_OF_MAPS_IN_SUBFOLDERS = 1 ;
	private static final int NUMBER_OF_SUBFOLDERS_IN_SUBFOLDER = 0 ;
	private static final int SUBFOLDER1_INODE = 2 ;
	private static final int SUBFOLDER2_INODE = 3 ;
	private static final int MAP_DIAGRAM1_INODE = 4 ;
	private static final int MAP_DIAGRAM2_INODE = 5 ;
	
	private static final int ROOT_NODE_INDEX = 0 ;
	private static final int SHAPENODE1_INDEX = 1 ;
	private static final int SHAPENODE2_INDEX = 2 ;
	private static final int SHAPENODE3_INDEX = 3 ;
	private static final int SHAPENODE4_INDEX = 4 ;
	private static final int SHAPENODE5_INDEX = 5 ;
	private static final int SHAPENODE6_INDEX = 6 ;
	private static final int SHAPENODE7_INDEX = 7 ;
	private static final int SHAPENODE8_INDEX = 8 ;
	
	private static final int LABELNODE1_INDEX = 9 ;
	private static final int LABELNODE2_INDEX = 10 ;
	private static final int LABELNODE3_INDEX = 11 ;
	private static final int LABELNODE4_INDEX = 12 ;
	private static final int LABELNODE5_INDEX = 13 ;
	private static final int LABELNODE6_INDEX = 14 ;
	private static final int LABELNODE7_INDEX = 15 ;
	private static final int LABELNODE8_INDEX = 16 ;
	
	private static final int NUM_OF_ROOTNODE_SHAPE_CHILDREN = 2 ;
	private static final int NUM_OF_SHAPENODE1_SHAPE_CHILDREN = 2 ;
	private static final int NUM_OF_SHAPENODE2_SHAPE_CHILDREN = 2 ;
	private static final int NUM_OF_SHAPENODE3_SHAPE_CHILDREN = 0 ;
	private static final int NUM_OF_SHAPENODE4_SHAPE_CHILDREN = 0 ;
	private static final int NUM_OF_SHAPENODE5_SHAPE_CHILDREN = 0 ;
	private static final int NUM_OF_SHAPENODE6_SHAPE_CHILDREN = 1 ;
	private static final int NUM_OF_SHAPENODE7_SHAPE_CHILDREN = 1 ;
	private static final int NUM_OF_SHAPENODE8_SHAPE_CHILDREN = 0 ;
	
	private static final int NUM_OF_ROOTNODE_LABEL_CHILDREN = 4 ;
	private static final int NUM_OF_SHAPENODE1_LABEL_CHILDREN = 1 ;
	private static final int NUM_OF_SHAPENODE2_LABEL_CHILDREN = 1 ;
	private static final int NUM_OF_SHAPENODE3_LABEL_CHILDREN = 1 ;
	private static final int NUM_OF_SHAPENODE4_LABEL_CHILDREN = 1;
	private static final int NUM_OF_SHAPENODE5_LABEL_CHILDREN = 0 ;
	private static final int NUM_OF_SHAPENODE6_LABEL_CHILDREN = 0 ;
	private static final int NUM_OF_SHAPENODE7_LABEL_CHILDREN = 0 ;
	private static final int NUM_OF_SHAPENODE8_LABEL_CHILDREN = 0 ;
	
	private static final int NUM_OF_ROOTNODE_LINK_CHILDREN = 4  ;
	private static final int NUM_OF_SHAPENODE1_LINK_CHILDREN = 2 ;
	private static final int NUM_OF_SHAPENODE2_LINK_CHILDREN = 1 ;
	private static final int NUM_OF_SHAPENODE3_LINK_CHILDREN = 0 ;
	private static final int NUM_OF_SHAPENODE4_LINK_CHILDREN = 0 ;
	private static final int NUM_OF_SHAPENODE5_LINK_CHILDREN = 0 ;
	private static final int NUM_OF_SHAPENODE6_LINK_CHILDREN = 2 ;
	private static final int NUM_OF_SHAPENODE7_LINK_CHILDREN = 0 ;
	private static final int NUM_OF_SHAPENODE8_LINK_CHILDREN = 0 ;
	
	private static final int SHAPE_ATTRIBUTE1_CREATION_SERIAL = 1 ;
	private static final int SHAPE_ATTRIBUTE2_CREATION_SERIAL = 2 ;
	private static final int SHAPE_ATTRIBUTE3_CREATION_SERIAL = 3 ;
	private static final int SHAPE_ATTRIBUTE4_CREATION_SERIAL = 4 ;
	private static final int SHAPE_ATTRIBUTE5_CREATION_SERIAL = 5 ;
	private static final int SHAPE_ATTRIBUTE6_CREATION_SERIAL = 6 ;
	private static final int SHAPE_ATTRIBUTE7_CREATION_SERIAL = 7 ;
	private static final int SHAPE_ATTRIBUTE8_CREATION_SERIAL = 8 ;
	
	private static final int LINK_ATTRIBUTE1_SERIAL = 9; 
	private static final int LINK_ATTRIBUTE2_SERIAL = 10; 
	private static final int LINK_ATTRIBUTE3_SERIAL = 11; 
	private static final int LINK_ATTRIBUTE4_SERIAL = 12; 
	private static final int LINK_ATTRIBUTE5_SERIAL = 13; 
	private static final int LINK_ATTRIBUTE6_SERIAL = 14; 
	private static final int LINK_ATTRIBUTE7_SERIAL = 15; 
	private static final int LINK_ATTRIBUTE8_SERIAL = 16; 
	private static final int LINK_ATTRIBUTE9_SERIAL = 17; 

	private static final int LABEL_ATTRIBUTE1_CREATION_SERIAL = 18;
	private static final int LABEL_ATTRIBUTE2_CREATION_SERIAL = 19;
	private static final int LABEL_ATTRIBUTE3_CREATION_SERIAL = 20;
	private static final int LABEL_ATTRIBUTE4_CREATION_SERIAL = 21;
	private static final int LABEL_ATTRIBUTE5_CREATION_SERIAL = 22;
	private static final int LABEL_ATTRIBUTE6_CREATION_SERIAL = 23;
	private static final int LABEL_ATTRIBUTE7_CREATION_SERIAL = 24;
	private static final int LABEL_ATTRIBUTE8_CREATION_SERIAL = 25;

	
	private static final int SHAPE_ATTRIBUTE1_LINE_WIDTH = 1 ;
	private static final int SHAPE_ATTRIBUTE2_LINE_WIDTH = 2 ;
	private static final int SHAPE_ATTRIBUTE3_LINE_WIDTH = 3 ;
	private static final int SHAPE_ATTRIBUTE4_LINE_WIDTH = 4 ;
	private static final int SHAPE_ATTRIBUTE5_LINE_WIDTH = 5 ;
	private static final int SHAPE_ATTRIBUTE6_LINE_WIDTH = 6 ;
	private static final int SHAPE_ATTRIBUTE7_LINE_WIDTH = 7 ;
	private static final int SHAPE_ATTRIBUTE8_LINE_WIDTH = 8 ;
	
	private static final int LINK_EDGE1_INDEX = 0 ;
	private static final int LINK_EDGE2_INDEX = 1 ;
	private static final int LINK_EDGE3_INDEX = 2 ;
	private static final int LINK_EDGE4_INDEX = 3 ;
	private static final int LINK_EDGE5_INDEX = 4 ;
	private static final int LINK_EDGE6_INDEX = 5 ;
	private static final int LINK_EDGE7_INDEX = 6 ;
	private static final int LINK_EDGE8_INDEX = 7 ;
	private static final int LINK_EDGE9_INDEX = 8 ;
	
	private static final int NUM_OF_ANY_LABELNODE_SHAPE_CHILDREN = 0 ;
	private static final int NUM_OF_ANY_LABELNODE_LABEL_CHILDREN = 0 ;
	private static final int NUM_OF_ANY_LABELNODE_LINK_CHILDREN = 0 ;
	
	private static final int NUM_OF_BENDPOINTS_IN_LINK1 = 1 ;
	private static final int NUM_OF_BENDPOINTS_IN_LINK2 = 1 ;
	private static final int NUM_OF_BENDPOINTS_IN_LINK3 = 0 ;
	private static final int NUM_OF_BENDPOINTS_IN_LINK4 = 0 ;
	private static final int NUM_OF_BENDPOINTS_IN_LINK5 = 0 ;
	private static final int NUM_OF_BENDPOINTS_IN_LINK6 = 0 ;
	private static final int NUM_OF_BENDPOINTS_IN_LINK7 = 0 ;
	private static final int NUM_OF_BENDPOINTS_IN_LINK8 = 0 ;
	private static final int NUM_OF_BENDPOINTS_IN_LINK9 = 0 ;
	
	private static final int LINE_WIDTH_OF_LINKATTRIBUTE1 = 11 ;
	private static final int LINE_WIDTH_OF_LINKATTRIBUTE2 = 12 ;
	private static final int LINE_WIDTH_OF_LINKATTRIBUTE3 = 13 ;
	private static final int LINE_WIDTH_OF_LINKATTRIBUTE4 = 14 ;
	private static final int LINE_WIDTH_OF_LINKATTRIBUTE5 = 15 ;
	private static final int LINE_WIDTH_OF_LINKATTRIBUTE6 = 16 ;
	private static final int LINE_WIDTH_OF_LINKATTRIBUTE7 = 17 ;
	private static final int LINE_WIDTH_OF_LINKATTRIBUTE8 = 18 ;
	private static final int LINE_WIDTH_OF_LINKATTRIBUTE9 = 19 ;
	
	
	private static final RGB RGB_100 = new RGB ( 100 , 100 , 100 ) ;
	private static final RGB RGB_101 = new RGB ( 101 , 101 , 101 ) ;
	private static final RGB RGB_102 = new RGB ( 102 , 102 , 102 ) ;
	private static final RGB RGB_103 = new RGB ( 103 , 103 , 103 ) ;
	private static final RGB RGB_104 = new RGB ( 104 , 104 , 104 ) ;
	private static final RGB RGB_105 = new RGB ( 105 , 105 , 105 ) ;
	private static final RGB RGB_106 = new RGB ( 106 , 106 , 106 ) ;
	private static final RGB RGB_107 = new RGB ( 107 , 107 , 107 ) ;
	private static final RGB RGB_108 = new RGB ( 108 , 108 	, 108 ) ;
	private static final RGB RGB_109 = new RGB ( 109 , 109 	, 109 ) ;
	
	private static final Dimension SIZE_10 = new Dimension ( 10 , 10 ) ;
	private static final Dimension SIZE_51 = new Dimension ( 51 , 51 ) ;
	private static final Dimension SIZE_52 = new Dimension ( 52 , 52 ) ;
	private static final Dimension SIZE_53 = new Dimension ( 53 , 53 ) ;
	private static final Dimension SIZE_54 = new Dimension ( 54 , 54 ) ;
	private static final Dimension SIZE_55 = new Dimension ( 55 , 55 ) ;
	private static final Dimension SIZE_56 = new Dimension ( 56 , 56 ) ;
	private static final Dimension SIZE_57 = new Dimension ( 57 , 57 ) ;
	private static final Dimension SIZE_58 = new Dimension ( 58 , 58 ) ;
	
	private static final Point LOCATION_51 = new Point ( 51, 51) ;
	private static final Point LOCATION_52 = new Point ( 52, 52) ;
	private static final Point LOCATION_53 = new Point ( 53, 53) ;
	private static final Point LOCATION_54 = new Point ( 54, 54) ;
	private static final Point LOCATION_55 = new Point ( 55, 55) ;
	private static final Point LOCATION_56 = new Point ( 56, 56) ;
	private static final Point LOCATION_57 = new Point ( 57, 57) ;
	private static final Point LOCATION_58 = new Point ( 58, 58) ;
		
	private static final String PROPERTY1_VALUE = "textPropertyValue" ;
	private static final String PROPERTY2_VALUE = "textPropertyValue2" ;
	private static final String PROPERTY3_VALUE = "richtextvalue" ;
	private static final int PROPERTY4_VALUE = 2;
	private static final int PROPERTY5_VALUE = 1 ;
	private static final int PROPERTY6_VALUE = 2 ;
	private static final String PROPERTY7_LIST_VALUE1 = "first in list 1" ;
	private static final String PROPERTY7_LIST_VALUE2 = "second in list 1" ;
	private static final String PROPERTY8_LIST_VALUE1 = "first in list 2" ;
	private static final String PROPERTY8_LIST_VALUE2 = "second in list 2" ;
	
	private static final LinkTermType SOURCE_LINK_TERMINUS_TYPE  = LinkTermType.SOURCE ;
	private static final LinkTermType TARGET_LINK_TERMINUS_TYPE = LinkTermType.TARGET ;
	
	private static final int LINKTERM_OFFSET_VALUE1 = 1 ;
	private static final int LINKTERM_OFFSET_VALUE2 = 2 ;
	private static final int LINKTERM_OFFSET_VALUE3 = 3 ;
	private static final int LINKTERM_OFFSET_VALUE4 = 4 ;
	private static final int LINKTERM_OFFSET_VALUE5 = 5 ;
	private static final int LINKTERM_OFFSET_VALUE6 = 6 ;
	private static final int LINKTERM_OFFSET_VALUE7 = 7 ;
	private static final int LINKTERM_OFFSET_VALUE8 = 8 ;
	private static final int LINKTERM_OFFSET_VALUE9 = 9 ;
	private static final int LINKTERM_OFFSET_VALUE10 = 10 ;
	private static final int LINKTERM_OFFSET_VALUE11 = 11 ;
	private static final int LINKTERM_OFFSET_VALUE12 = 12 ;
	private static final int LINKTERM_OFFSET_VALUE13 = 13 ;
	private static final int LINKTERM_OFFSET_VALUE14 = 14 ;
	private static final int LINKTERM_OFFSET_VALUE15 = 15 ;
	private static final int LINKTERM_OFFSET_VALUE16 = 16 ;
	private static final int LINKTERM_OFFSET_VALUE17 = 17 ;
	private static final int LINKTERM_OFFSET_VALUE18 = 18 ;
	
	private static final Dimension END_DECORATOR_SIZE11 = new Dimension ( 11 , 11 ) ;
	private static final Dimension END_DECORATOR_SIZE12 = new Dimension ( 12 , 12 ) ;
	private static final Dimension END_DECORATOR_SIZE13 = new Dimension ( 13 , 13 ) ;
	private static final Dimension END_DECORATOR_SIZE14 = new Dimension ( 14 , 14 ) ;
	private static final Dimension END_DECORATOR_SIZE15 = new Dimension ( 15 , 15 ) ;
	private static final Dimension END_DECORATOR_SIZE16 = new Dimension ( 16 , 16 ) ;
	private static final Dimension END_DECORATOR_SIZE17 = new Dimension ( 17 , 17 ) ;
	private static final Dimension END_DECORATOR_SIZE18 = new Dimension ( 18 , 18 ) ;
	private static final Dimension END_DECORATOR_SIZE19 = new Dimension ( 19 , 19 ) ;
	private static final Dimension END_DECORATOR_SIZE20 = new Dimension ( 20 , 20 ) ;
	private static final Dimension END_DECORATOR_SIZE21 = new Dimension ( 21 , 21 ) ;
	private static final Dimension END_DECORATOR_SIZE22 = new Dimension ( 22 , 22 ) ;
	private static final Dimension END_DECORATOR_SIZE23 = new Dimension ( 23 , 23 ) ;
	private static final Dimension END_DECORATOR_SIZE24 = new Dimension ( 24 , 24 ) ;
	private static final Dimension END_DECORATOR_SIZE25 = new Dimension ( 25 , 25 ) ;
	private static final Dimension END_DECORATOR_SIZE26 = new Dimension ( 26 , 26 ) ;
	private static final Dimension END_DECORATOR_SIZE27 = new Dimension ( 27 , 27 ) ;
	private static final Dimension END_DECORATOR_SIZE28 = new Dimension ( 28 , 28 ) ;
	
	private static final LinkEndDecoratorShape END_DECORATOR_TYPE_SOURCE = LinkEndDecoratorShape.DIAMOND ;
	private static final LinkEndDecoratorShape END_DECORATOR_TYPE_TARGET = LinkEndDecoratorShape.ARROW ;
	private static final String MAP_PATH = "/subfolder1/Diagram name";

	private static final String MAP_DIAGRAM1_PATH = "/subfolder1/Diagram name";

	private static final String MAP_DIAGRAM2_PATH = "/subfolder2/Diagram name2";
	private static final int EXPECTED_NUM_LINK1_BENDPOINTS = 1;
	private static final int FIRST_LINK1_BENDPOINT = 0;
	private static final int FIRST_LINK2_BENDPOINT = 0;
	private static final int EXPECTED_NUM_LINK2_BENDPOINTS = 1;
	private static final int NUM_MODEL_LINK_EDGES = 9;
	private static final int NUM_MODEL_SHAPE_NODES = 8;
	private static final int NUM_MODEL_LABEL_NODES = 8;
	private static final int NUM_MODEL_DRAWING_NODES = NUM_MODEL_SHAPE_NODES + NUM_MODEL_LABEL_NODES;
	private static final double CMP_DELTA = 0.001;
	
	
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.testutils.GenericTester#doAdditionalSetUp()
	 */
	@Override
	protected void doAdditionalSetUp() {
		repository = this.getRepositoryPersistenceManager().getRepository();
	
		loadObjects();
		
	}
	
	private void loadObjects ()	{
		IMap map = (IMap)repository.findRepositoryItemByPath(MAP_PATH);
			
		IMapPersistenceManager map1Manager = this.getRepositoryPersistenceManager().getMapPersistenceManager(map);
		map1Manager.open() ;
		this.dbCanvas = map1Manager.getCanvas();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.testutils.GenericTester#doAdditionalTearDown()
	 */
	@Override
	protected void doAdditionalTearDown() {
		repository = null;
		this.dbCanvas = null;
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
	public void testValidity() {
		assertTrue("Model valid", this.dbCanvas.getModel().isValid());
	}
	
	@Test
	public void testRepositoryIsLoadedProperly () 
	{
		assertEquals ( "correct Repository name" , REPOSITORY_NAME , repository.getName()) ;
	}
	
	public void testModelStatistics() {
		IModel model = this.dbCanvas.getModel();
		assertEquals("correct num nodes", NUM_MODEL_DRAWING_NODES, model.numDrawingNodes());
		assertEquals("correct num links", NUM_MODEL_LINK_EDGES, model.numLinkEdges());
		assertEquals("correct num drawing elements", NUM_MODEL_DRAWING_NODES + NUM_MODEL_LINK_EDGES, model.numDrawingElements());
		assertEquals("correct num shape nodes", NUM_MODEL_SHAPE_NODES, model.numShapeNodes());
		assertEquals("correct num label nodes", NUM_MODEL_LABEL_NODES, model.numLabelNodes());
	}
	
	@Test
	public void testCheckRootFolderLoadedProperly (){
		IRootFolder rootFolder = this.repository.getRootFolder();
		
		assertEquals ( "belongs to repository" , repository , rootFolder.getRepository()) ;
		assertEquals ( "No maps" , NUMBER_OF_MAPS_IN_ROOT , rootFolder.numMaps() ) ;
		assertEquals ( "two subfolders" , NUMBER_OF_SUBFOLDERS_IN_ROOT , rootFolder.numSubFolders() ) ;
		assertEquals ( "path of root" , ROOT_FOLDER_PATH , rootFolder.getPath()) ;
		assertEquals ( "root inode" , ROOT_FOLDER_INODE , rootFolder.getINode()) ;
	}
	
	@Test
	public void testCheckSubFoldersLoadedProperly () {
		IRootFolder rootFolder = this.repository.getRootFolder();
		ISubFolder subFolder1 = (ISubFolder)this.repository.findRepositoryItemByPath(SUBFOLDER1_PATH);
		assertNotNull ( "subfolder1 initialized" , subFolder1 ) ;
		ISubFolder subFolder2 = (ISubFolder)this.repository.findRepositoryItemByPath(SUBFOLDER2_PATH);
		assertNotNull ( "subfolder2 initialized" , subFolder2 ) ;
		
		assertEquals ( "subfolder1 name" , NAME_OF_SUBFOLDER1 , subFolder1.getName()) ;
		assertEquals ( "one map inside" , NUMBER_OF_MAPS_IN_SUBFOLDERS , subFolder1.numMaps()) ;
		assertEquals ( "no subfolders" , NUMBER_OF_SUBFOLDERS_IN_SUBFOLDER , subFolder1.numSubFolders()) ;
		assertEquals ( "description check" , SUBFOLDER1_DESCRIPTION , subFolder1.getDescription()) ;
		assertEquals ( "subfolder1 inode" , SUBFOLDER1_INODE , subFolder1.getINode()) ;
		assertEquals ( "parent check" , rootFolder , subFolder1.getParent()) ;
		assertEquals ( "subfolder1 path" , SUBFOLDER1_PATH , subFolder1.getPath()) ;
		assertEquals ( "repository check" , repository , subFolder1.getRepository() );
		
		assertEquals ( "subfolder2 name" , NAME_OF_SUBFOLDER2 , subFolder2.getName()) ;
		assertEquals ( "one map inside" , NUMBER_OF_MAPS_IN_SUBFOLDERS , subFolder2.numMaps()) ;
		assertEquals ( "no subfolders" , NUMBER_OF_SUBFOLDERS_IN_SUBFOLDER , subFolder2.numSubFolders()) ;
		assertEquals ( "description check" , SUBFOLDER2_DESCRIPTION , subFolder2.getDescription()) ;
		assertEquals ( "subfolder2 inode" , SUBFOLDER2_INODE , subFolder2.getINode()) ;
		assertEquals ( "parent check" , rootFolder , subFolder2.getParent()) ;
		assertEquals ( "subfolder2 path" , SUBFOLDER2_PATH , subFolder2.getPath()) ;
		assertEquals ( "repository check" , repository , subFolder2.getRepository() );
	}
	
	@Test
	public void testCheckMapDiagramsLoadedProperly () {
		IMap mapDiagram1 = (IMap)this.repository.findRepositoryItemByPath(MAP_DIAGRAM1_PATH);
		IMap mapDiagram2 = (IMap)this.repository.findRepositoryItemByPath(MAP_DIAGRAM2_PATH);
		ISubFolder subFolder1 = (ISubFolder)this.repository.findRepositoryItemByPath(SUBFOLDER1_PATH);
		ISubFolder subFolder2 = (ISubFolder)this.repository.findRepositoryItemByPath(SUBFOLDER2_PATH);
		
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
	public void testCheckIntegrityOfCanvas () {
		IMap mapDiagram1 = (IMap)this.repository.findRepositoryItemByPath(MAP_DIAGRAM1_PATH);

		assertNotNull ( "canvas is not null" , this.dbCanvas ) ;
		assertEquals ( "check owning map repo name" , mapDiagram1.getRepository().getName() , dbCanvas.getRepositoryName()) ;
		assertEquals ( "check owning map inode" , mapDiagram1.getINode() , dbCanvas.getINode()) ;
		assertEquals ( "canvas size" , SIZE_10 , dbCanvas.getCanvasSize() ) ;
		assertEquals ( "grid size" , SIZE_10 , dbCanvas.getGridSize() ) ;
		assertEquals ( "check color" , RGB_100 , dbCanvas.getBackgroundColour()) ;
		assertTrue ( "grid enabled" , dbCanvas.isGridEnabled()) ;
		assertTrue ( "snap grid enabled" , dbCanvas.isSnapToGridOn()) ;
	}
	
	@Test
	public void testCheckModelIsCorrect ()
	{
		IModel dbModel = this.dbCanvas.getModel();
		assertNotNull ( "model is not null" , dbModel) ;
		
		assertEquals ( "belongs to Canvas" , dbCanvas , dbModel.getCanvas()) ;
		assertNotNull ( "has root node" , dbModel.getRootNode()) ;
	}
	
	@Test
	public void testCheckIntegrityOfRootNode ()
	{
		IModel dbModel = this.dbCanvas.getModel();
		IRootNode dbRootNode = this.dbCanvas.getModel().getRootNode();
		assertNotNull ( "is not null" , dbRootNode) ;
		assertNotNull ("rootNode has SubModel" , dbRootNode.getSubModel()) ;
		
		assertEquals ( "root node index" , ROOT_NODE_INDEX , dbRootNode.getIndex() ) ;
		assertEquals ( "model check" , dbModel , dbRootNode.getModel()) ;
		assertEquals ( "rootNode is its own parent" , dbRootNode, dbRootNode.getParentNode() ) ;
		assertEquals ( "rootnode has 2 shapes", NUM_OF_ROOTNODE_SHAPE_CHILDREN , dbRootNode.getSubModel().numShapeNodes()) ;
		assertEquals ( "rootnode has no labels", NUM_OF_ROOTNODE_LABEL_CHILDREN , dbRootNode.getSubModel().numLabelNodes()) ;
		assertEquals ( "rootnode has 5 links", NUM_OF_ROOTNODE_LINK_CHILDREN , dbRootNode.getSubModel().numLinkEdges()) ;
		
		int numOfShapeNodes = 0 ;
		
		Iterator <IShapeNode> shapeIterator = dbRootNode.getSubModel().shapeNodeIterator() ;
		
		while ( shapeIterator.hasNext())
		{
			shapeIterator.next();
			numOfShapeNodes ++ ;
		}
		
		assertEquals ( "2 shapeNodes" , 2 , numOfShapeNodes) ;
	}
	
	@Test
	public void testCheckIntegrityOfShapeNode1andShapeAttribute1 () {
		IModel dbModel = dbCanvas.getModel();
		IRootNode dbRootNode = dbModel.getRootNode();
		IShapeAttribute shapeAttribute1 = dbCanvas.getShapeAttribute(SHAPE_ATTRIBUTE1_CREATION_SERIAL);
		IShapeNode shapeNode1 = shapeAttribute1.getCurrentDrawingElement();
		
		assertNotNull ( "is not null" , shapeNode1) ;
		assertNotNull ("shapeNode1 has SubModel" , shapeNode1.getSubModel()) ;
		assertNotNull ( "is linkedtoAttribute" , shapeNode1.getAttribute() ) ;
		
		assertEquals ( "shapenode1 index" , SHAPENODE1_INDEX , shapeNode1.getIndex() ) ;
		assertEquals ( "model check" , dbModel , shapeNode1.getModel()) ;
		assertEquals ( "parent is rootNode" , dbRootNode.getIndex() , shapeNode1.getParentNode().getIndex() ) ;
		assertEquals ( "shapeNode1 has 2 shapes", NUM_OF_SHAPENODE1_SHAPE_CHILDREN , shapeNode1.getSubModel().numShapeNodes()) ;
		assertEquals ( "shapeNode1 has no labels", NUM_OF_SHAPENODE1_LABEL_CHILDREN , shapeNode1.getSubModel().numLabelNodes()) ;
		assertEquals ( "shapeNode1 has no links", NUM_OF_SHAPENODE1_LINK_CHILDREN , shapeNode1.getSubModel().numLinkEdges()) ;
		
		assertEquals ( "shapeAttribute1 creation serial" , SHAPE_ATTRIBUTE1_CREATION_SERIAL , shapeAttribute1.getCreationSerial()  ) ;
		assertEquals ( "shapeAttribute1 fill color" , RGB_101 , shapeAttribute1.getFillColour()) ;
		assertEquals ( "shapeAttribute1 line color" , RGB_101 , shapeAttribute1.getLineColour()) ;
		assertEquals ( "shapeAttribute1 line style" , LineStyle.SOLID ,shapeAttribute1.getLineStyle() ) ;
		assertEquals ( "shapeAttribute1 line width" , SHAPE_ATTRIBUTE1_LINE_WIDTH ,shapeAttribute1.getLineWidth(), CMP_DELTA ) ;
		assertEquals ( "shapeAttribute1 location" , LOCATION_51, shapeAttribute1.getLocation() ) ;
		assertEquals ( "shapeAttribute1 line padding" , SIZE_51 , shapeAttribute1.getSize()) ;
		
		assertEquals ( "2 shapeNodes" , 2 , shapeNode1.getSubModel().numShapeNodes()) ;
	}
	
	@Test
	public void testCheckIntegrityOfShapeNode2AndShapeAttribute2 () {
		IModel dbModel = dbCanvas.getModel();
		IRootNode dbRootNode = dbModel.getRootNode();
		IShapeAttribute shapeAttribute2 = dbCanvas.getShapeAttribute(SHAPE_ATTRIBUTE2_CREATION_SERIAL);
		IShapeNode shapeNode2 = shapeAttribute2.getCurrentDrawingElement();
		assertNotNull ( "is not null" , shapeNode2) ;
		assertNotNull ("shapeNode2 has SubModel" , shapeNode2.getSubModel()) ;
		assertNotNull ( "is linkedtoAttribute" , shapeNode2.getAttribute() ) ;
		
		assertEquals ( "shapenode2 index" , SHAPENODE2_INDEX , shapeNode2.getIndex() ) ;
		assertEquals ( "model check" , dbModel , shapeNode2.getModel()) ;
		assertEquals ( "parent is rootNode" , dbRootNode.getIndex() , shapeNode2.getParentNode().getIndex() ) ;
		assertEquals ( "shapeNode1 has 2 shapes", NUM_OF_SHAPENODE2_SHAPE_CHILDREN , shapeNode2.getSubModel().numShapeNodes()) ;
		assertEquals ( "shapeNode1 has no labels", NUM_OF_SHAPENODE2_LABEL_CHILDREN , shapeNode2.getSubModel().numLabelNodes()) ;
		assertEquals ( "shapeNode1 has no links", NUM_OF_SHAPENODE2_LINK_CHILDREN , shapeNode2.getSubModel().numLinkEdges()) ;
		
		assertEquals ( "shapeAttribute2 creation serial" , SHAPE_ATTRIBUTE2_CREATION_SERIAL , shapeAttribute2.getCreationSerial()  ) ;
		assertEquals ( "shapeAttribute2 fill color" , RGB_102 , shapeAttribute2.getFillColour()) ;
		assertEquals ( "shapeAttribute2 line color" , RGB_102 , shapeAttribute2.getLineColour()) ;
		assertEquals ( "shapeAttribute2 line style" , LineStyle.DASHED ,shapeAttribute2.getLineStyle() ) ;
		assertEquals ( "shapeAttribute2 line width" , SHAPE_ATTRIBUTE2_LINE_WIDTH ,shapeAttribute2.getLineWidth(), CMP_DELTA ) ;
		assertEquals ( "shapeAttribute2 location" , LOCATION_52, shapeAttribute2.getLocation() ) ;
		assertEquals ( "shapeAttribute2 line padding" , SIZE_52 , shapeAttribute2.getSize()) ;
		
		assertEquals ( "2 shapeNodes" , 2 , shapeNode2.getSubModel().numShapeNodes()) ;
		
	}
	
	@Test
	public void testCheckIntegrityOfShapeNode3AndShapeAttribute3 () {
		IShapeAttribute shapeAttribute3 = this.dbCanvas.getShapeAttribute(SHAPE_ATTRIBUTE3_CREATION_SERIAL);
		IShapeNode shapeNode3 = shapeAttribute3.getCurrentDrawingElement();
		IShapeNode shapeNode1 = this.dbCanvas.getShapeAttribute(SHAPE_ATTRIBUTE1_CREATION_SERIAL).getCurrentDrawingElement();
		IModel dbModel = dbCanvas.getModel();
		
		assertNotNull ( "is not null" , shapeNode3) ;
		assertNotNull ("shapeNode3 has SubModel" , shapeNode3.getSubModel()) ;
		assertNotNull ( "is linkedtoAttribute" , shapeNode3.getAttribute() ) ;
		
		assertEquals ( "shapenode3 index" , SHAPENODE3_INDEX , shapeNode3.getIndex() ) ;
		assertEquals ( "model check" , dbModel , shapeNode3.getModel()) ;
		assertEquals ( "parent is shapeNode1" , shapeNode1.getIndex() , shapeNode3.getParentNode().getIndex() ) ;
		assertEquals ( "shapeNode3 has no shapes", NUM_OF_SHAPENODE3_SHAPE_CHILDREN , shapeNode3.getSubModel().numShapeNodes()) ;
		assertEquals ( "shapeNode3 has no labels", NUM_OF_SHAPENODE3_LABEL_CHILDREN , shapeNode3.getSubModel().numLabelNodes()) ;
		assertEquals ( "shapeNode3 has no links", NUM_OF_SHAPENODE3_LINK_CHILDREN , shapeNode3.getSubModel().numLinkEdges()) ;
		
		assertEquals ( "shapeAttribute3 creation serial" , SHAPE_ATTRIBUTE3_CREATION_SERIAL , shapeAttribute3.getCreationSerial()  ) ;
		assertEquals ( "shapeAttribute3 fill color" , RGB_103 , shapeAttribute3.getFillColour()) ;
		assertEquals ( "shapeAttribute3 line color" , RGB_103 , shapeAttribute3.getLineColour()) ;
		assertEquals ( "shapeAttribute3 line style" , LineStyle.DASH_DOT ,shapeAttribute3.getLineStyle() ) ;
		assertEquals ( "shapeAttribute3 line width" , SHAPE_ATTRIBUTE3_LINE_WIDTH ,shapeAttribute3.getLineWidth(), CMP_DELTA ) ;
		assertEquals ( "shapeAttribute3 location" , LOCATION_53, shapeAttribute3.getLocation() ) ;
		assertEquals ( "shapeAttribute3 line padding" , SIZE_53 , shapeAttribute3.getSize()) ;
		
		assertEquals ( "0 shapeNodes" , 0 , shapeNode3.getSubModel().numShapeNodes());
				
	}
	
	@Test
	public void testCheckIntegrityOfShapeNode4AndShapeAttribute4 () {
		IShapeAttribute shapeAttribute4 = this.dbCanvas.getShapeAttribute(SHAPE_ATTRIBUTE4_CREATION_SERIAL);
		IShapeNode shapeNode4 = shapeAttribute4.getCurrentDrawingElement();
		IModel dbModel = this.dbCanvas.getModel();
		
		assertNotNull ( "is not null" , shapeNode4) ;
		assertNotNull ("shapeNode4 has SubModel" , shapeNode4.getSubModel()) ;
		assertNotNull ( "is linkedtoAttribute" , shapeNode4.getAttribute() ) ;
		
		assertEquals ( "shapenode4 index" , SHAPENODE4_INDEX , shapeNode4.getIndex() ) ;
		assertEquals ( "model check" , dbModel , shapeNode4.getModel()) ;
		assertEquals ( "parent is shapeNode1" , SHAPENODE1_INDEX, shapeNode4.getParentNode().getIndex() ) ;
		assertEquals ( "shapeNode4 has no shapes", NUM_OF_SHAPENODE4_SHAPE_CHILDREN , shapeNode4.getSubModel().numShapeNodes()) ;
		assertEquals ( "shapeNode4 has no labels", NUM_OF_SHAPENODE4_LABEL_CHILDREN , shapeNode4.getSubModel().numLabelNodes()) ;
		assertEquals ( "shapeNode4 has no links", NUM_OF_SHAPENODE4_LINK_CHILDREN , shapeNode4.getSubModel().numLinkEdges()) ;
		
		assertEquals ( "shapeAttribute4 creation serial" , SHAPE_ATTRIBUTE4_CREATION_SERIAL , shapeAttribute4.getCreationSerial()  ) ;
		assertEquals ( "shapeAttribute4 fill color" , RGB_104 , shapeAttribute4.getFillColour()) ;
		assertEquals ( "shapeAttribute4 line color" , RGB_104 , shapeAttribute4.getLineColour()) ;
		assertEquals ( "shapeAttribute4 line style" , LineStyle.DASH_DOT_DOT ,shapeAttribute4.getLineStyle() ) ;
		assertEquals ( "shapeAttribute4 line width" , SHAPE_ATTRIBUTE4_LINE_WIDTH ,shapeAttribute4.getLineWidth(), CMP_DELTA ) ;
		assertEquals ( "shapeAttribute4 location" , LOCATION_54, shapeAttribute4.getLocation() ) ;
		assertEquals ( "shapeAttribute4 line padding" , SIZE_54 , shapeAttribute4.getSize()) ;
		
		assertEquals ( "0 shapeNodes" , 0 , shapeNode4.getSubModel().numShapeNodes()) ;
				
	}
	
	@Test
	public void testCheckIntegrityOfShapeNode5AndShapeAttribute5 () {
		IShapeAttribute shapeAttribute5 = this.dbCanvas.getShapeAttribute(SHAPE_ATTRIBUTE5_CREATION_SERIAL);
		IShapeNode shapeNode5 = shapeAttribute5.getCurrentDrawingElement();
		IModel dbModel = dbCanvas.getModel();
		
		assertNotNull ( "is not null" , shapeNode5) ;
		assertNotNull ("shapeNode5 has SubModel" , shapeNode5.getSubModel()) ;
		assertNotNull ( "is linkedtoAttribute" , shapeNode5.getAttribute() ) ;
		
		assertEquals ( "shapenode5 index" , SHAPENODE5_INDEX , shapeNode5.getIndex() ) ;
		assertEquals ( "model check" , dbModel , shapeNode5.getModel()) ;
		assertEquals ( "parent is shapeNode2" , SHAPENODE2_INDEX, shapeNode5.getParentNode().getIndex() ) ;
		assertEquals ( "shapeNode5 has no shapes", NUM_OF_SHAPENODE5_SHAPE_CHILDREN , shapeNode5.getSubModel().numShapeNodes()) ;
		assertEquals ( "shapeNode5 has no labels", NUM_OF_SHAPENODE5_LABEL_CHILDREN , shapeNode5.getSubModel().numLabelNodes()) ;
		assertEquals ( "shapeNode5 has no links", NUM_OF_SHAPENODE5_LINK_CHILDREN , shapeNode5.getSubModel().numLinkEdges()) ;
		
		assertEquals ( "shapeAttribute5 creation serial" , SHAPE_ATTRIBUTE5_CREATION_SERIAL , shapeAttribute5.getCreationSerial()  ) ;
		assertEquals ( "shapeAttribute5 fill color" , RGB_105 , shapeAttribute5.getFillColour()) ;
		assertEquals ( "shapeAttribute5 line color" , RGB_105 , shapeAttribute5.getLineColour()) ;
		assertEquals ( "shapeAttribute5 line style" , LineStyle.DOT ,shapeAttribute5.getLineStyle() ) ;
		assertEquals ( "shapeAttribute5 line width" , SHAPE_ATTRIBUTE5_LINE_WIDTH ,shapeAttribute5.getLineWidth(), CMP_DELTA ) ;
		assertEquals ( "shapeAttribute5 location" , LOCATION_55, shapeAttribute5.getLocation() ) ;
		assertEquals ( "shapeAttribute5 line padding" , SIZE_55 , shapeAttribute5.getSize()) ;
		
		assertEquals ( "0 shapeNodes" , 0 , shapeNode5.getSubModel().numShapeNodes()) ;
	}
	
	@Test
	public void testCheckIntegrityOfShapeNode6AndShapeAttribute6 (){
		IShapeAttribute shapeAttribute6 = this.dbCanvas.getShapeAttribute(SHAPE_ATTRIBUTE6_CREATION_SERIAL);
		IShapeNode shapeNode6 = shapeAttribute6.getCurrentDrawingElement();
		
		assertNotNull ( "is not null" , shapeNode6) ;
		assertNotNull ("shapeNode6 has SubModel" , shapeNode6.getSubModel()) ;
		assertNotNull ( "is linkedtoAttribute" , shapeNode6.getAttribute() ) ;
		
		assertEquals ( "shapenode6 index" , SHAPENODE6_INDEX , shapeNode6.getIndex() ) ;
		assertEquals ( "parent is shapeNode2" , SHAPENODE2_INDEX , shapeNode6.getParentNode().getIndex() ) ;
		assertEquals ( "shapeNode6 one shapes", NUM_OF_SHAPENODE6_SHAPE_CHILDREN , shapeNode6.getSubModel().numShapeNodes()) ;
		assertEquals ( "shapeNode6 has no labels", NUM_OF_SHAPENODE6_LABEL_CHILDREN , shapeNode6.getSubModel().numLabelNodes()) ;
		assertEquals ( "shapeNode6 has no links", NUM_OF_SHAPENODE6_LINK_CHILDREN , shapeNode6.getSubModel().numLinkEdges()) ;
		
		assertEquals ( "shapeAttribute6 creation serial" , SHAPE_ATTRIBUTE6_CREATION_SERIAL , shapeAttribute6.getCreationSerial()  ) ;
		assertEquals ( "shapeAttribute6 fill color" , RGB_106 , shapeAttribute6.getFillColour()) ;
		assertEquals ( "shapeAttribute6 line color" , RGB_106 , shapeAttribute6.getLineColour()) ;
		assertEquals ( "shapeAttribute6 line style" , LineStyle.SOLID ,shapeAttribute6.getLineStyle() ) ;
		assertEquals ( "shapeAttribute6 line width" , SHAPE_ATTRIBUTE6_LINE_WIDTH ,shapeAttribute6.getLineWidth(), CMP_DELTA ) ;
		assertEquals ( "shapeAttribute6 location" , LOCATION_56, shapeAttribute6.getLocation() ) ;
		assertEquals ( "shapeAttribute6 line padding" , SIZE_56 , shapeAttribute6.getSize()) ;
		
		int numOfShapeNodes = 0 ;
		
		Iterator <IShapeNode> shapeIterator = shapeNode6.getSubModel().shapeNodeIterator() ;
		
		while ( shapeIterator.hasNext())
		{
			shapeIterator.next();
			numOfShapeNodes ++ ;
		}
		
		assertEquals ( "1 shapeNodes" , 1 , numOfShapeNodes) ;
				
	}
	
	@Test
	public void testCheckIntegrityOfShapeNode7AndShapeAttribute7 () {
		IShapeAttribute shapeAttribute7 = this.dbCanvas.getShapeAttribute(SHAPE_ATTRIBUTE7_CREATION_SERIAL);
		IShapeNode shapeNode7 = shapeAttribute7.getCurrentDrawingElement();
		
		assertNotNull ( "is not null" , shapeNode7) ;
		assertNotNull ("shapeNode7 has SubModel" , shapeNode7.getSubModel()) ;
		assertNotNull ( "is linkedtoAttribute" , shapeNode7.getAttribute() ) ;
		
		assertEquals ( "shapenode7 index" , SHAPENODE7_INDEX , shapeNode7.getIndex() ) ;
		assertEquals ( "parent is shapeNode6" , SHAPENODE6_INDEX , shapeNode7.getParentNode().getIndex() ) ;
		assertEquals ( "shapeNode7 has one shapes", NUM_OF_SHAPENODE7_SHAPE_CHILDREN , shapeNode7.getSubModel().numShapeNodes()) ;
		assertEquals ( "shapeNode7 has no labels", NUM_OF_SHAPENODE7_LABEL_CHILDREN , shapeNode7.getSubModel().numLabelNodes()) ;
		assertEquals ( "shapeNode7 has no links", NUM_OF_SHAPENODE7_LINK_CHILDREN , shapeNode7.getSubModel().numLinkEdges()) ;
		
		assertEquals ( "shapeAttribute7 creation serial" , SHAPE_ATTRIBUTE7_CREATION_SERIAL , shapeAttribute7.getCreationSerial()  ) ;
		assertEquals ( "shapeAttribute7 fill color" , RGB_107 , shapeAttribute7.getFillColour()) ;
		assertEquals ( "shapeAttribute7 line color" , RGB_107 , shapeAttribute7.getLineColour()) ;
		assertEquals ( "shapeAttribute7 line style" , LineStyle.DASHED ,shapeAttribute7.getLineStyle() ) ;
		assertEquals ( "shapeAttribute7 line width" , SHAPE_ATTRIBUTE7_LINE_WIDTH ,shapeAttribute7.getLineWidth(), CMP_DELTA ) ;
		assertEquals ( "shapeAttribute7 location" , LOCATION_57, shapeAttribute7.getLocation() ) ;
		assertEquals ( "shapeAttribute7 line padding" , SIZE_57 , shapeAttribute7.getSize()) ;
		
		int numOfShapeNodes = 0 ;
		
		Iterator <IShapeNode> shapeIterator = shapeNode7.getSubModel().shapeNodeIterator() ;
		
		while ( shapeIterator.hasNext())
		{
			shapeIterator.next();
			numOfShapeNodes ++ ;
		}
		
		assertEquals ( "1 shapeNodes" , 1 , numOfShapeNodes) ;
				
	}
	
	@Test
	public void testCheckIntegrityOfShapeNode8AndShapeAttribute8 () {
		IShapeAttribute shapeAttribute8 = this.dbCanvas.getShapeAttribute(SHAPE_ATTRIBUTE8_CREATION_SERIAL);
		IShapeNode shapeNode8 = shapeAttribute8.getCurrentDrawingElement(); 
		
		assertNotNull ( "is not null" , shapeNode8) ;
		assertNotNull ("shapeNode8 has SubModel" , shapeNode8.getSubModel()) ;
		assertNotNull ( "is linkedtoAttribute" , shapeNode8.getAttribute() ) ;
		
		assertEquals ( "shapenode8 index" , SHAPENODE8_INDEX , shapeNode8.getIndex() ) ;
		assertEquals ( "parent is shapeNode7" , SHAPENODE7_INDEX, shapeNode8.getParentNode().getIndex() ) ;
		assertEquals ( "shapeNode8 has one shapes", NUM_OF_SHAPENODE8_SHAPE_CHILDREN , shapeNode8.getSubModel().numShapeNodes()) ;
		assertEquals ( "shapeNode8 has no labels", NUM_OF_SHAPENODE8_LABEL_CHILDREN , shapeNode8.getSubModel().numLabelNodes()) ;
		assertEquals ( "shapeNode8 has no links", NUM_OF_SHAPENODE8_LINK_CHILDREN , shapeNode8.getSubModel().numLinkEdges()) ;
		
		assertEquals ( "shapeAttribute8 creation serial" , SHAPE_ATTRIBUTE8_CREATION_SERIAL , shapeAttribute8.getCreationSerial()  ) ;
		assertEquals ( "shapeAttribute8 fill color" , RGB_108 , shapeAttribute8.getFillColour()) ;
		assertEquals ( "shapeAttribute8 line color" , RGB_108 , shapeAttribute8.getLineColour()) ;
		assertEquals ( "shapeAttribute8 line style" , LineStyle.DASH_DOT ,shapeAttribute8.getLineStyle() ) ;
		assertEquals ( "shapeAttribute8 line width" , SHAPE_ATTRIBUTE8_LINE_WIDTH ,shapeAttribute8.getLineWidth(), CMP_DELTA ) ;
		assertEquals ( "shapeAttribute8 location" , LOCATION_58, shapeAttribute8.getLocation() ) ;
		assertEquals ( "shapeAttribute8 line padding" , SIZE_58 , shapeAttribute8.getSize()) ;
		
		int numOfShapeNodes = 0 ;
		
		Iterator <IShapeNode> shapeIterator = shapeNode8.getSubModel().shapeNodeIterator() ;
		
		while ( shapeIterator.hasNext())
		{
			shapeIterator.next();
			numOfShapeNodes ++ ;
		}
		
		assertEquals ( "0 shapeNodes" , 0 , numOfShapeNodes) ;
				
	}
	
	@Test
	public void testCheckIntegrityOfLabelNode1AndLabelAttribute1 () {
		ILabelAttribute labelAttribute1 = dbCanvas.getLabelAttribute(LABEL_ATTRIBUTE1_CREATION_SERIAL);
		ILabelNode labelNode1 = labelAttribute1.getCurrentDrawingElement();
		IAnnotationProperty property1 = labelAttribute1.getProperty() ;
		
		assertNotNull ( "is not Null" , labelNode1) ;
		assertNotNull ("labelNode1 has SubModel" , labelNode1.getSubModel()) ;
		assertNotNull ( "has attribute" , labelNode1.getAttribute()) ;
		
		assertEquals ( "labelNode1 index" , LABELNODE1_INDEX , labelNode1.getIndex() ) ;
		assertEquals ( "parent is shapeNode1" , SHAPENODE1_INDEX , labelNode1.getParentNode().getIndex() ) ;
		assertEquals ( "labelNode1 has one shapes", NUM_OF_ANY_LABELNODE_SHAPE_CHILDREN , labelNode1.getSubModel().numShapeNodes()) ;
		assertEquals ( "labelNode1 has no labels", NUM_OF_ANY_LABELNODE_LABEL_CHILDREN , labelNode1.getSubModel().numLabelNodes()) ;
		assertEquals ( "labelNode1 has no links", NUM_OF_ANY_LABELNODE_LINK_CHILDREN , labelNode1.getSubModel().numLinkEdges()) ;
		
		assertEquals ( "labelAttribute1 color" , RGB_101 , labelAttribute1.getBackgroundColor() ) ;
		assertEquals ( "labelAttribute1 position" , LOCATION_51 , labelAttribute1.getLocation()) ;
		assertEquals ( "labelAttribute1 size" , SIZE_51 , labelAttribute1.getSize() ) ;

		assertNotNull ( "is not null " , property1 ) ;
		
		assertTrue ( "is text property" , property1 instanceof IPlainTextAnnotationProperty ) ;
		IPlainTextAnnotationProperty theProperty = (IPlainTextAnnotationProperty) property1 ;
		assertEquals ( "value" , PROPERTY1_VALUE, theProperty.getValue()) ;
	}
	
	@Test
	public void testCheckIntegrityOfLabelNode2AndLabelAttribute2 () {
		ILabelAttribute labelAttribute2 = this.dbCanvas.getLabelAttribute(LABEL_ATTRIBUTE2_CREATION_SERIAL);
		ILabelNode labelNode2 = labelAttribute2.getCurrentDrawingElement();
		IAnnotationProperty property2 = labelAttribute2.getProperty();
		
		assertNotNull ( "is not Null" , labelNode2) ;
		assertNotNull ("labelNode1 has SubModel" , labelNode2.getSubModel()) ;
		assertNotNull ( "has attribute" , labelNode2.getAttribute()) ;
		
		assertEquals ( "labelNode2 index" , LABELNODE2_INDEX , labelNode2.getIndex() ) ;
		assertEquals ( "parent is rootnode" , ROOT_NODE_INDEX, labelNode2.getParentNode().getIndex() ) ;
		assertEquals ( "labelNode2 has one shapes", NUM_OF_ANY_LABELNODE_SHAPE_CHILDREN , labelNode2.getSubModel().numShapeNodes()) ;
		assertEquals ( "labelNode2 has no labels", NUM_OF_ANY_LABELNODE_LABEL_CHILDREN , labelNode2.getSubModel().numLabelNodes()) ;
		assertEquals ( "labelNode2 has no links", NUM_OF_ANY_LABELNODE_LINK_CHILDREN , labelNode2.getSubModel().numLinkEdges()) ;
		
		assertEquals ( "labelAttribute2 color" , RGB_102 , labelAttribute2.getBackgroundColor() ) ;
		assertEquals ( "labelAttribute2 position" , LOCATION_52 , labelAttribute2.getLocation()) ;
		assertEquals ( "labelAttribute2 size" , SIZE_52 , labelAttribute2.getSize() ) ;
	
		assertNotNull ( "is not null " , property2 ) ;
		
		assertTrue ( "is text property" , property2 instanceof IPlainTextAnnotationProperty ) ;
		IPlainTextAnnotationProperty theProperty = (IPlainTextAnnotationProperty) property2 ;
		assertEquals ( "value" , PROPERTY2_VALUE, theProperty.getValue()) ;
	}
	
	@Test
	public void testCheckIntegrityOfLabelNode3AndLabelAttribute3 () {
		ILabelAttribute labelAttribute3 = this.dbCanvas.getLabelAttribute(LABEL_ATTRIBUTE3_CREATION_SERIAL);
		ILabelNode labelNode3 = labelAttribute3.getCurrentDrawingElement();
		IAnnotationProperty property3 = labelAttribute3.getProperty();
		
		assertNotNull ( "is not Null" , labelNode3) ;
		assertNotNull ("labelNode3 has SubModel" , labelNode3.getSubModel()) ;
		assertNotNull ( "has attribute" , labelNode3.getAttribute()) ;
		
		assertEquals ( "labelNode3 index" , LABELNODE3_INDEX , labelNode3.getIndex() ) ;
		assertEquals ( "parent is rootnode" , ROOT_NODE_INDEX, labelNode3.getParentNode().getIndex() ) ;
		assertEquals ( "labelNode2 has one shapes", NUM_OF_ANY_LABELNODE_SHAPE_CHILDREN , labelNode3.getSubModel().numShapeNodes()) ;
		assertEquals ( "labelNode2 has no labels", NUM_OF_ANY_LABELNODE_LABEL_CHILDREN , labelNode3.getSubModel().numLabelNodes()) ;
		assertEquals ( "labelNode2 has no links", NUM_OF_ANY_LABELNODE_LINK_CHILDREN , labelNode3.getSubModel().numLinkEdges()) ;
		
		assertEquals ( "labelAttribute2 color" , RGB_103 , labelAttribute3.getBackgroundColor() ) ;
		assertEquals ( "labelAttribute2 position" , LOCATION_53 , labelAttribute3.getLocation()) ;
		assertEquals ( "labelAttribute2 size" , SIZE_53 , labelAttribute3.getSize() ) ;

		assertNotNull ( "is not null " , property3 ) ;
		
		assertTrue ( "is text property" , property3 instanceof IHtmlAnnotationProperty ) ;
		IHtmlAnnotationProperty theProperty = (IHtmlAnnotationProperty) property3 ;
		assertEquals ( "value" , PROPERTY3_VALUE, theProperty.getValue()) ;
	}
	
	@Test
	public void testCheckIntegrityOfLabelNode4AndLabelAttribute4 () {
		ILabelAttribute labelAttribute4 = this.dbCanvas.getLabelAttribute(LABEL_ATTRIBUTE4_CREATION_SERIAL);
		ILabelNode labelNode4 = labelAttribute4.getCurrentDrawingElement();
		IAnnotationProperty property4 = labelAttribute4.getProperty();
		
		assertNotNull ( "is not Null" , labelNode4) ;
		assertNotNull ("labelNode4 has SubModel" , labelNode4.getSubModel()) ;
		assertNotNull ( "has attribute" , labelNode4.getAttribute()) ;
		
		assertEquals ( "labelNode4 index" , LABELNODE4_INDEX , labelNode4.getIndex() ) ;
		assertEquals ( "parent is shapeNode2" , SHAPENODE2_INDEX, labelNode4.getParentNode().getIndex() ) ;
		assertEquals ( "labelNode4 has one shapes", NUM_OF_ANY_LABELNODE_SHAPE_CHILDREN , labelNode4.getSubModel().numShapeNodes()) ;
		assertEquals ( "labelNode4 has no labels", NUM_OF_ANY_LABELNODE_LABEL_CHILDREN , labelNode4.getSubModel().numLabelNodes()) ;
		assertEquals ( "labelNode4 has no links", NUM_OF_ANY_LABELNODE_LINK_CHILDREN , labelNode4.getSubModel().numLinkEdges()) ;
		
		assertEquals ( "labelAttribute4 color" , RGB_104 , labelAttribute4.getBackgroundColor() ) ;
		assertEquals ( "labelAttribute4 position" , LOCATION_54 , labelAttribute4.getLocation()) ;
		assertEquals ( "labelAttribute4 size" , SIZE_54 , labelAttribute4.getSize() ) ;

		assertNotNull ( "is not null " , property4 ) ;
		
		assertTrue ( "is text property" , property4 instanceof IListAnnotationProperty ) ;
		IListAnnotationProperty theProperty = (IListAnnotationProperty) property4 ;
		assertEquals ( "value" , PROPERTY4_VALUE, theProperty.getValue().size()) ;
}
	
	@Test
	public void testCheckIntegrityOfLabelNode5AndLabelAttribute5 (){
		ILabelAttribute labelAttribute5 = this.dbCanvas.getLabelAttribute(LABEL_ATTRIBUTE5_CREATION_SERIAL);
		ILabelNode labelNode5 = labelAttribute5.getCurrentDrawingElement();
		IAnnotationProperty property5 = labelAttribute5.getProperty();
		
		assertNotNull ( "is not Null" , labelNode5) ;
		assertNotNull ("labelNode5 has SubModel" , labelNode5.getSubModel()) ;
		assertNotNull ( "has attribute" , labelNode5.getAttribute()) ;
		
		assertEquals ( "labelNode5 index" , LABELNODE5_INDEX , labelNode5.getIndex() ) ;
		assertEquals ( "parent is rootnode" , ROOT_NODE_INDEX, labelNode5.getParentNode().getIndex() ) ;
		assertEquals ( "labelNode5 has one shapes", NUM_OF_ANY_LABELNODE_SHAPE_CHILDREN , labelNode5.getSubModel().numShapeNodes()) ;
		assertEquals ( "labelNode5 has no labels", NUM_OF_ANY_LABELNODE_LABEL_CHILDREN , labelNode5.getSubModel().numLabelNodes()) ;
		assertEquals ( "labelNode5 has no links", NUM_OF_ANY_LABELNODE_LINK_CHILDREN , labelNode5.getSubModel().numLinkEdges()) ;
		
		assertEquals ( "labelAttribute5 color" , RGB_105 , labelAttribute5.getBackgroundColor() ) ;
		assertEquals ( "labelAttribute5 position" , LOCATION_55 , labelAttribute5.getLocation()) ;
		assertEquals ( "labelAttribute5 size" , SIZE_55 , labelAttribute5.getSize() ) ;
	
		assertNotNull ( "is not null " , property5 ) ;
		
		assertTrue ( "is text property" , property5 instanceof INumberAnnotationProperty ) ;
		INumberAnnotationProperty theProperty = (INumberAnnotationProperty) property5 ;
		assertEquals ( "value" , PROPERTY5_VALUE, theProperty.getValue().intValue()) ;
	}
	
	@Test
	public void testCheckIntegrityOfLabelNode6AndLabelAttribute6 ()	{
		ILabelAttribute labelAttribute6 = dbCanvas.getLabelAttribute(LABEL_ATTRIBUTE6_CREATION_SERIAL);
		ILabelNode labelNode6 = labelAttribute6.getCurrentDrawingElement();
		IAnnotationProperty property6 = labelAttribute6.getProperty() ;
		
		assertNotNull ( "is not Null" , labelNode6) ;
		assertNotNull ("labelNode6 has SubModel" , labelNode6.getSubModel()) ;
		assertNotNull ( "has attribute" , labelNode6.getAttribute()) ;
		
		assertEquals ( "labelNode6 index" , LABELNODE6_INDEX , labelNode6.getIndex() ) ;
		assertEquals ( "parent is shapeNode6" , ROOT_NODE_INDEX, labelNode6.getParentNode().getIndex() ) ;
		assertEquals ( "labelNode6 has one shapes", NUM_OF_ANY_LABELNODE_SHAPE_CHILDREN , labelNode6.getSubModel().numShapeNodes()) ;
		assertEquals ( "labelNode6 has no labels", NUM_OF_ANY_LABELNODE_LABEL_CHILDREN , labelNode6.getSubModel().numLabelNodes()) ;
		assertEquals ( "labelNode6 has no links", NUM_OF_ANY_LABELNODE_LINK_CHILDREN , labelNode6.getSubModel().numLinkEdges()) ;
		
		assertEquals ( "labelAttribute6 color" , RGB_106 , labelAttribute6.getBackgroundColor() ) ;
		assertEquals ( "labelAttribute6 position" , LOCATION_56 , labelAttribute6.getLocation()) ;
		assertEquals ( "labelAttribute6 size" , SIZE_56 , labelAttribute6.getSize() ) ;

		assertNotNull ( "is not null " , property6 ) ;
		
		assertTrue ( "is text property" , property6 instanceof INumberAnnotationProperty ) ;
		INumberAnnotationProperty theProperty = (INumberAnnotationProperty) property6 ;
		assertEquals ( "value" , PROPERTY6_VALUE, theProperty.getValue().intValue()) ;
	}
	
	@Test
	public void testCheckIntegrityOfLabelNode7AndLabelAttribute7 () {
		ILabelAttribute labelAttribute7 = dbCanvas.getLabelAttribute(LABEL_ATTRIBUTE7_CREATION_SERIAL);
		ILabelNode labelNode7 = labelAttribute7.getCurrentDrawingElement();
		IAnnotationProperty property7 = labelAttribute7.getProperty() ;
		
		assertNotNull ( "is not Null" , labelNode7) ;
		assertNotNull ("labelNode7 has SubModel" , labelNode7.getSubModel()) ;
		assertNotNull ( "has attribute" , labelNode7.getAttribute()) ;
		
		assertEquals ( "labelNode7 index" , LABELNODE7_INDEX , labelNode7.getIndex() ) ;
		assertEquals ( "parent is shapeNode2" , SHAPENODE3_INDEX, labelNode7.getParentNode().getIndex() ) ;
		assertEquals ( "labelNode7 has one shapes", NUM_OF_ANY_LABELNODE_SHAPE_CHILDREN , labelNode7.getSubModel().numShapeNodes()) ;
		assertEquals ( "labelNode7 has no labels", NUM_OF_ANY_LABELNODE_LABEL_CHILDREN , labelNode7.getSubModel().numLabelNodes()) ;
		assertEquals ( "labelNode7 has no links", NUM_OF_ANY_LABELNODE_LINK_CHILDREN , labelNode7.getSubModel().numLinkEdges()) ;
		
		assertEquals ( "labelAttribute7 color" , RGB_107 , labelAttribute7.getBackgroundColor() ) ;
		assertEquals ( "labelAttribute7 position" , LOCATION_57 , labelAttribute7.getLocation()) ;
		assertEquals ( "labelAttribute7 size" , SIZE_57 , labelAttribute7.getSize() ) ;

		assertNotNull ( "is not null " , property7 ) ;
		
		assertTrue ( "is text property" , property7 instanceof IListAnnotationProperty ) ;
		IListAnnotationProperty theProperty = (IListAnnotationProperty) property7 ;
		List <String> listValues = theProperty.getValue() ;
		assertEquals ( "first element" , PROPERTY7_LIST_VALUE1 , listValues.get(0)) ;
		assertEquals ( "second element" , PROPERTY7_LIST_VALUE2 , listValues.get(1)) ;
}
	
	@Test
	public void testCheckIntegrityOfLabelNode8AndLabelAttribute8 ()	{
		ILabelAttribute labelAttribute8 = dbCanvas.getLabelAttribute(LABEL_ATTRIBUTE8_CREATION_SERIAL);
		ILabelNode labelNode8 = labelAttribute8.getCurrentDrawingElement();
		IAnnotationProperty property8 = labelAttribute8.getProperty() ;
		
		assertNotNull ( "is not Null" , labelNode8) ;
		assertNotNull ("labelNode3 has SubModel" , labelNode8.getSubModel()) ;
		assertNotNull ( "has attribute" , labelNode8.getAttribute()) ;
		
		assertEquals ( "labelNode8 index" , LABELNODE8_INDEX , labelNode8.getIndex() ) ;
		assertEquals ( "parent is shapeNode7" , SHAPENODE4_INDEX, labelNode8.getParentNode().getIndex() ) ;
		assertEquals ( "labelNode8 has one shapes", NUM_OF_ANY_LABELNODE_SHAPE_CHILDREN , labelNode8.getSubModel().numShapeNodes()) ;
		assertEquals ( "labelNode8 has no labels", NUM_OF_ANY_LABELNODE_LABEL_CHILDREN , labelNode8.getSubModel().numLabelNodes()) ;
		assertEquals ( "labelNode8 has no links", NUM_OF_ANY_LABELNODE_LINK_CHILDREN , labelNode8.getSubModel().numLinkEdges()) ;
		
		assertEquals ( "labelAttribute8 color" , RGB_108 , labelAttribute8.getBackgroundColor() ) ;
		assertEquals ( "labelAttribute8 position" , LOCATION_58 , labelAttribute8.getLocation()) ;
		assertEquals ( "labelAttribute8 size" , SIZE_58 , labelAttribute8.getSize() ) ;

		assertNotNull ( "is not null " , property8 ) ;
		
		assertTrue ( "is text property" , property8 instanceof IListAnnotationProperty ) ;
		IListAnnotationProperty theProperty = (IListAnnotationProperty) property8 ;
		List <String> listValues =  theProperty.getValue() ;
		assertEquals ( "first element" , PROPERTY8_LIST_VALUE1 , listValues.get(0)) ;
		assertEquals ( "second element" , PROPERTY8_LIST_VALUE2 , listValues.get(1)) ;
}
	
	@Test
	public void testCheckIntegrityOfLinkEdge1AndLinkAttribute1AndTermini ()	{
		ILinkAttribute linkAttribute1 = dbCanvas.getLinkAttribute(LINK_ATTRIBUTE1_SERIAL);
		ILinkEdge linkEdge1 = linkAttribute1.getCurrentDrawingElement();
		
		ILinkTerminus linkTerminus1 = linkAttribute1.getSourceTerminus() ;
		ILinkTerminus linkTerminus2 = linkAttribute1.getTargetTerminus() ;
		
		assertNotNull ( "is not Null" , linkEdge1) ;
		assertNotNull ( "has attribute" , linkEdge1.getAttribute()) ;
		
		assertEquals ( "link edge index" , LINK_EDGE1_INDEX , linkEdge1.getIndex()) ;
		assertEquals ( "owning subModel" , ROOT_NODE_INDEX, linkEdge1.getOwningSubModel().getRootNode().getIndex() ) ;
		assertEquals ( "source shape" , SHAPENODE1_INDEX, linkEdge1.getSourceShape().getIndex() ) ;
		assertEquals ( "target shape" , SHAPENODE2_INDEX, linkEdge1.getTargetShape().getIndex() ) ;
		
		assertEquals ( "check Canvas" , dbCanvas , linkAttribute1.getCanvas() );
		assertEquals ( "check creation serial" , LINK_ATTRIBUTE1_SERIAL , linkAttribute1.getCreationSerial()) ;
		assertEquals ( "check line color" , RGB_101 , linkAttribute1.getLineColor() ) ;
		assertEquals ( "check linestyle" , LineStyle.SOLID , linkAttribute1.getLineStyle() ) ;
		assertEquals ( "check no of bends" , NUM_OF_BENDPOINTS_IN_LINK1 , linkAttribute1.numBendPoints()) ;
		assertEquals ( "check line width" , LINE_WIDTH_OF_LINKATTRIBUTE1 ,linkAttribute1.getLineWidth(), CMP_DELTA) ;
		
		assertEquals ( "owning link "  , linkAttribute1 , linkTerminus1.getOwningLink()) ;
		assertEquals ( "link term type" , SOURCE_LINK_TERMINUS_TYPE ,linkTerminus1.getLinkTermType()) ;
		assertEquals ( "offsetValue" , LINKTERM_OFFSET_VALUE1 , linkTerminus1.getGap(), CMP_DELTA) ;
		assertEquals ( "end decorator type" , END_DECORATOR_TYPE_SOURCE , linkTerminus1.getEndDecoratorType()) ;
		assertEquals ( "end decorator size" , END_DECORATOR_SIZE11 , linkTerminus1.getEndSize()) ;
		
		assertEquals ( "owning link "  , linkAttribute1 , linkTerminus2.getOwningLink()) ;
		assertEquals ( "link term type" , TARGET_LINK_TERMINUS_TYPE ,linkTerminus2.getLinkTermType()) ;
		assertEquals ( "offsetValue" , LINKTERM_OFFSET_VALUE2 , linkTerminus2.getGap(), CMP_DELTA) ;
		assertEquals ( "end decorator type" , END_DECORATOR_TYPE_TARGET , linkTerminus2.getEndDecoratorType()) ;
		assertEquals ( "end decorator size" , END_DECORATOR_SIZE12 , linkTerminus2.getEndSize()) ;
	}
	
	@Test
	public void testCheckIntegrityOfLinkEdge2AndLinkAttribute2AndTermini () {
		ILinkAttribute linkAttribute2 = dbCanvas.getLinkAttribute(LINK_ATTRIBUTE2_SERIAL);
		ILinkEdge linkEdge2 = linkAttribute2.getCurrentDrawingElement();
		
		ILinkTerminus linkTerminus3 = linkAttribute2.getSourceTerminus() ;
		ILinkTerminus linkTerminus4 = linkAttribute2.getTargetTerminus() ;
		
		assertNotNull ( "is not Null" , linkEdge2) ;
		assertNotNull ( "has attribute" , linkEdge2.getAttribute()) ;
		
		assertEquals ( "link edge index" , LINK_EDGE2_INDEX , linkEdge2.getIndex()) ;
		assertEquals ( "owning subModel" , ROOT_NODE_INDEX, linkEdge2.getOwningSubModel().getRootNode().getIndex() ) ;
		assertEquals ( "source shape" , SHAPENODE2_INDEX, linkEdge2.getSourceShape().getIndex() ) ;
		assertEquals ( "target shape" , SHAPENODE1_INDEX, linkEdge2.getTargetShape().getIndex() ) ;
		
		assertEquals ( "check Canvas" , dbCanvas , linkAttribute2.getCanvas() );
		assertEquals ( "check creation serial" , LINK_ATTRIBUTE2_SERIAL , linkAttribute2.getCreationSerial()) ;
		assertEquals ( "check line color" , RGB_102 , linkAttribute2.getLineColor() ) ;
		assertEquals ( "check linestyle" , LineStyle.DASHED , linkAttribute2.getLineStyle() ) ;
		assertEquals ( "check no of bends" , NUM_OF_BENDPOINTS_IN_LINK2 , linkAttribute2.numBendPoints()) ;
		assertEquals ( "check line width" , LINE_WIDTH_OF_LINKATTRIBUTE2 , linkAttribute2.getLineWidth(), CMP_DELTA) ;
		
		assertEquals ( "owning link "  , linkAttribute2 , linkTerminus3.getOwningLink()) ;
		assertEquals ( "link term type" , SOURCE_LINK_TERMINUS_TYPE ,linkTerminus3.getLinkTermType()) ;
		assertEquals ( "offsetValue" , LINKTERM_OFFSET_VALUE3 , linkTerminus3.getGap(), CMP_DELTA) ;
		assertEquals ( "end decorator type" , END_DECORATOR_TYPE_SOURCE , linkTerminus3.getEndDecoratorType()) ;
		assertEquals ( "end decorator size" , END_DECORATOR_SIZE13 , linkTerminus3.getEndSize()) ;
		
		assertEquals ( "owning link "  , linkAttribute2 , linkTerminus4.getOwningLink()) ;
		assertEquals ( "link term type" , TARGET_LINK_TERMINUS_TYPE ,linkTerminus4.getLinkTermType()) ;
		assertEquals ( "offsetValue" , LINKTERM_OFFSET_VALUE4 , linkTerminus4.getGap(), CMP_DELTA) ;
		assertEquals ( "end decorator type" , END_DECORATOR_TYPE_TARGET , linkTerminus4.getEndDecoratorType()) ;
		assertEquals ( "end decorator size" , END_DECORATOR_SIZE14 , linkTerminus4.getEndSize()) ;
	}
	
	@Test
	public void testCheckIntegrityOfLinkEdge3AndLinkAttribute3AndTermini () {
		ILinkAttribute linkAttribute3 = this.dbCanvas.getLinkAttribute(LINK_ATTRIBUTE3_SERIAL);
		ILinkEdge linkEdge3 = linkAttribute3.getCurrentDrawingElement();
		ILinkTerminus linkTerminus5 = linkAttribute3.getSourceTerminus() ;
		ILinkTerminus linkTerminus6 = linkAttribute3.getTargetTerminus() ;
		
		assertNotNull ( "is not Null" , linkEdge3) ;
		assertNotNull ( "has attribute" , linkEdge3.getAttribute()) ;
		
		assertEquals ( "link edge index" , LINK_EDGE3_INDEX , linkEdge3.getIndex()) ;
		assertEquals ( "owning subModel" , SHAPENODE2_INDEX, linkEdge3.getOwningSubModel().getRootNode().getIndex() ) ;
		assertEquals ( "source shape" , SHAPENODE2_INDEX , linkEdge3.getSourceShape().getIndex() ) ;
		assertEquals ( "target shape" , SHAPENODE6_INDEX , linkEdge3.getTargetShape().getIndex() ) ;
		
		assertEquals ( "check Canvas" , dbCanvas , linkAttribute3.getCanvas() );
		assertEquals ( "check creation serial" , LINK_ATTRIBUTE3_SERIAL , linkAttribute3.getCreationSerial()) ;
		assertEquals ( "check line color" , RGB_103 , linkAttribute3.getLineColor() ) ;
		assertEquals ( "check linestyle" , LineStyle.DASH_DOT , linkAttribute3.getLineStyle() ) ;
		assertEquals ( "check no of bends" , NUM_OF_BENDPOINTS_IN_LINK3 , linkAttribute3.numBendPoints()) ;
		assertEquals ( "check line width" , LINE_WIDTH_OF_LINKATTRIBUTE3 , linkAttribute3.getLineWidth(), CMP_DELTA) ;
		
		assertEquals ( "owning link "  , linkAttribute3 , linkTerminus5.getOwningLink()) ;
		assertEquals ( "link term type" , SOURCE_LINK_TERMINUS_TYPE ,linkTerminus5.getLinkTermType()) ;
		assertEquals ( "offsetValue" , LINKTERM_OFFSET_VALUE5 , linkTerminus5.getGap(), CMP_DELTA) ;
		assertEquals ( "end decorator type" , END_DECORATOR_TYPE_SOURCE , linkTerminus5.getEndDecoratorType()) ;
		assertEquals ( "end decorator size" , END_DECORATOR_SIZE15 , linkTerminus5.getEndSize()) ;
		
		assertEquals ( "owning link "  , linkAttribute3 , linkTerminus6.getOwningLink()) ;
		assertEquals ( "link term type" , TARGET_LINK_TERMINUS_TYPE ,linkTerminus6.getLinkTermType()) ;
		assertEquals ( "offsetValue" , LINKTERM_OFFSET_VALUE6 , linkTerminus6.getGap(), CMP_DELTA) ;
		assertEquals ( "end decorator type" , END_DECORATOR_TYPE_TARGET , linkTerminus6.getEndDecoratorType()) ;
		assertEquals ( "end decorator size" , END_DECORATOR_SIZE16 , linkTerminus6.getEndSize()) ;
	}
	
	@Test
	public void testCheckIntegrityOfLinkEdge4AndLinkAttribute4AndTermini (){
		ILinkAttribute linkAttribute4 = this.dbCanvas.getLinkAttribute(LINK_ATTRIBUTE4_SERIAL);
		ILinkEdge linkEdge4 = linkAttribute4.getCurrentDrawingElement();
		ILinkTerminus linkTerminus7 = linkAttribute4.getSourceTerminus() ;
		ILinkTerminus linkTerminus8 = linkAttribute4.getTargetTerminus() ;
		
		assertNotNull ( "is not Null" , linkEdge4) ;
		assertNotNull ( "has attribute" , linkEdge4.getAttribute()) ;
		
		assertEquals ( "link edge index" , LINK_EDGE4_INDEX , linkEdge4.getIndex()) ;
		assertEquals ( "owning subModel" , ROOT_NODE_INDEX, linkEdge4.getOwningSubModel().getRootNode().getIndex() ) ;
		assertEquals ( "source shape" , SHAPENODE2_INDEX, linkEdge4.getSourceShape().getIndex()) ;
		assertEquals ( "target shape" , SHAPENODE4_INDEX, linkEdge4.getTargetShape().getIndex()) ;
		
		assertEquals ( "check Canvas" , dbCanvas , linkAttribute4.getCanvas() );
		assertEquals ( "check creation serial" , LINK_ATTRIBUTE4_SERIAL , linkAttribute4.getCreationSerial()) ;
		assertEquals ( "check line color" , RGB_104 , linkAttribute4.getLineColor() ) ;
		assertEquals ( "check linestyle" , LineStyle.DASH_DOT_DOT , linkAttribute4.getLineStyle() ) ;
		assertEquals ( "check no of bends" , NUM_OF_BENDPOINTS_IN_LINK4 , linkAttribute4.numBendPoints()) ;
		assertEquals ( "check line width" , LINE_WIDTH_OF_LINKATTRIBUTE4 , linkAttribute4.getLineWidth(), CMP_DELTA) ;
		
		assertEquals ( "owning link "  , linkAttribute4 , linkTerminus7.getOwningLink()) ;
		assertEquals ( "link term type" , SOURCE_LINK_TERMINUS_TYPE ,linkTerminus7.getLinkTermType()) ;
		assertEquals ( "offsetValue" , LINKTERM_OFFSET_VALUE7 , linkTerminus7.getGap(), CMP_DELTA) ;
		assertEquals ( "end decorator type" , END_DECORATOR_TYPE_SOURCE , linkTerminus7.getEndDecoratorType()) ;
		assertEquals ( "end decorator size" , END_DECORATOR_SIZE17 , linkTerminus7.getEndSize()) ;
		
		assertEquals ( "owning link "  , linkAttribute4 , linkTerminus8.getOwningLink()) ;
		assertEquals ( "link term type" , TARGET_LINK_TERMINUS_TYPE ,linkTerminus8.getLinkTermType()) ;
		assertEquals ( "offsetValue" , LINKTERM_OFFSET_VALUE8 , linkTerminus8.getGap(), CMP_DELTA) ;
		assertEquals ( "end decorator type" , END_DECORATOR_TYPE_TARGET , linkTerminus8.getEndDecoratorType()) ;
		assertEquals ( "end decorator size" , END_DECORATOR_SIZE18 , linkTerminus8.getEndSize()) ;
	}
	
	@Test
	public void testCheckIntegrityOfLinkEdge5AndLinkAttribute5AndTermini () {
		ILinkAttribute linkAttribute5 = this.dbCanvas.getLinkAttribute(LINK_ATTRIBUTE5_SERIAL);
		ILinkEdge linkEdge5 = linkAttribute5.getCurrentDrawingElement();
		ILinkTerminus linkTerminus9 = linkAttribute5.getSourceTerminus() ;
		ILinkTerminus linkTerminus10 = linkAttribute5.getTargetTerminus() ;
		
		assertNotNull ( "is not Null" , linkEdge5) ;
		assertNotNull ( "has attribute" , linkEdge5.getAttribute()) ;
		
		assertEquals ( "link edge index" , LINK_EDGE5_INDEX , linkEdge5.getIndex()) ;
		assertEquals ( "owning subModel" , SHAPENODE6_INDEX, linkEdge5.getOwningSubModel().getRootNode().getIndex() ) ;
		assertEquals ( "source shape" , SHAPENODE7_INDEX, linkEdge5.getSourceShape().getIndex() ) ;
		assertEquals ( "target shape" , SHAPENODE6_INDEX, linkEdge5.getTargetShape().getIndex() ) ;
		
		assertEquals ( "check Canvas" , dbCanvas , linkAttribute5.getCanvas() );
		assertEquals ( "check creation serial" , LINK_ATTRIBUTE5_SERIAL , linkAttribute5.getCreationSerial()) ;
		assertEquals ( "check line color" , RGB_105 , linkAttribute5.getLineColor() ) ;
		assertEquals ( "check linestyle" , LineStyle.DOT , linkAttribute5.getLineStyle() ) ;
		assertEquals ( "check no of bends" , NUM_OF_BENDPOINTS_IN_LINK5 , linkAttribute5.numBendPoints()) ;
		assertEquals ( "check line width" , LINE_WIDTH_OF_LINKATTRIBUTE5 , linkAttribute5.getLineWidth(), CMP_DELTA) ;
		
		assertEquals ( "owning link "  , linkAttribute5 , linkTerminus9.getOwningLink()) ;
		assertEquals ( "link term type" , SOURCE_LINK_TERMINUS_TYPE ,linkTerminus9.getLinkTermType()) ;
		assertEquals ( "offsetValue" , LINKTERM_OFFSET_VALUE9 , linkTerminus9.getGap(), CMP_DELTA) ;
		assertEquals ( "end decorator type" , END_DECORATOR_TYPE_SOURCE , linkTerminus9.getEndDecoratorType()) ;
		assertEquals ( "end decorator size" , END_DECORATOR_SIZE19 , linkTerminus9.getEndSize()) ;
		
		assertEquals ( "owning link "  , linkAttribute5 , linkTerminus10.getOwningLink()) ;
		assertEquals ( "link term type" , TARGET_LINK_TERMINUS_TYPE ,linkTerminus10.getLinkTermType()) ;
		assertEquals ( "offsetValue" , LINKTERM_OFFSET_VALUE10 , linkTerminus10.getGap(), CMP_DELTA) ;
		assertEquals ( "end decorator type" , END_DECORATOR_TYPE_TARGET , linkTerminus10.getEndDecoratorType()) ;
		assertEquals ( "end decorator size" , END_DECORATOR_SIZE20 , linkTerminus10.getEndSize()) ;
		
	}
	
	@Test
	public void testCheckIntegrityOfLinkEdge6AndLinkAttribute6AndTermini (){
		ILinkAttribute linkAttribute6 = this.dbCanvas.getLinkAttribute(LINK_ATTRIBUTE6_SERIAL);
		ILinkEdge linkEdge6 = linkAttribute6.getCurrentDrawingElement();
		ILinkTerminus linkTerminus11 = linkAttribute6.getSourceTerminus() ;
		ILinkTerminus linkTerminus12 = linkAttribute6.getTargetTerminus() ;
		
		assertNotNull ( "is not Null" , linkEdge6) ;
		assertNotNull ( "has attribute" , linkEdge6.getAttribute()) ;
		
		assertEquals ( "link edge index" , LINK_EDGE6_INDEX , linkEdge6.getIndex()) ;
		assertEquals ( "owning subModel" , SHAPENODE6_INDEX, linkEdge6.getOwningSubModel().getRootNode().getIndex() ) ;
		assertEquals ( "source shape" , SHAPENODE6_INDEX, linkEdge6.getSourceShape().getIndex() ) ;
		assertEquals ( "target shape" , SHAPENODE7_INDEX, linkEdge6.getTargetShape().getIndex() ) ;
		
		assertEquals ( "check Canvas" , dbCanvas , linkAttribute6.getCanvas() );
		assertEquals ( "check creation serial" , LINK_ATTRIBUTE6_SERIAL , linkAttribute6.getCreationSerial()) ;
		assertEquals ( "check line color" , RGB_106 , linkAttribute6.getLineColor() ) ;
		assertEquals ( "check linestyle" , LineStyle.SOLID , linkAttribute6.getLineStyle() ) ;
		assertEquals ( "check no of bends" , NUM_OF_BENDPOINTS_IN_LINK6 , linkAttribute6.numBendPoints()) ;
		assertEquals ( "check line width" , LINE_WIDTH_OF_LINKATTRIBUTE6 , linkAttribute6.getLineWidth(), CMP_DELTA) ;
		
		assertEquals ( "owning link "  , linkAttribute6 , linkTerminus11.getOwningLink()) ;
		assertEquals ( "link term type" , SOURCE_LINK_TERMINUS_TYPE ,linkTerminus11.getLinkTermType()) ;
		assertEquals ( "offsetValue" , LINKTERM_OFFSET_VALUE11 , linkTerminus11.getGap(), CMP_DELTA) ;
		assertEquals ( "end decorator type" , END_DECORATOR_TYPE_SOURCE , linkTerminus11.getEndDecoratorType()) ;
		assertEquals ( "end decorator size" , END_DECORATOR_SIZE21 , linkTerminus11.getEndSize()) ;
		
		assertEquals ( "owning link "  , linkAttribute6 , linkTerminus12.getOwningLink()) ;
		assertEquals ( "link term type" , TARGET_LINK_TERMINUS_TYPE ,linkTerminus12.getLinkTermType()) ;
		assertEquals ( "offsetValue" , LINKTERM_OFFSET_VALUE12 , linkTerminus12.getGap(), CMP_DELTA) ;
		assertEquals ( "end decorator type" , END_DECORATOR_TYPE_TARGET , linkTerminus12.getEndDecoratorType()) ;
		assertEquals ( "end decorator size" , END_DECORATOR_SIZE22 , linkTerminus12.getEndSize()) ;
	}
	
	@Test
	public void testCheckIntegrityOfLinkEdge7AndLinkAttribute7AndTermini (){
		ILinkAttribute linkAttribute7 = this.dbCanvas.getLinkAttribute(LINK_ATTRIBUTE7_SERIAL);
		ILinkEdge linkEdge7 = linkAttribute7.getCurrentDrawingElement();
		ILinkTerminus linkTerminus13 = linkAttribute7.getSourceTerminus() ;
		ILinkTerminus linkTerminus14 = linkAttribute7.getTargetTerminus() ;
		
		assertNotNull ( "is not Null" , linkEdge7) ;
		assertNotNull ( "has attribute" , linkEdge7.getAttribute()) ;
		
		assertEquals ( "link edge index" , LINK_EDGE7_INDEX , linkEdge7.getIndex()) ;
		assertEquals ( "owning subModel" , ROOT_NODE_INDEX, linkEdge7.getOwningSubModel().getRootNode().getIndex() ) ;
		assertEquals ( "source shape" , SHAPENODE7_INDEX, linkEdge7.getSourceShape().getIndex() ) ;
		assertEquals ( "target shape" , SHAPENODE3_INDEX, linkEdge7.getTargetShape().getIndex() ) ;
		
		assertEquals ( "check Canvas" , dbCanvas , linkAttribute7.getCanvas() );
		assertEquals ( "check creation serial" , LINK_ATTRIBUTE7_SERIAL , linkAttribute7.getCreationSerial()) ;
		assertEquals ( "check line color" , RGB_107 , linkAttribute7.getLineColor() ) ;
		assertEquals ( "check linestyle" , LineStyle.DASHED , linkAttribute7.getLineStyle() ) ;
		assertEquals ( "check no of bends" , NUM_OF_BENDPOINTS_IN_LINK7 , linkAttribute7.numBendPoints()) ;
		assertEquals ( "check line width" , LINE_WIDTH_OF_LINKATTRIBUTE7 , linkAttribute7.getLineWidth(), CMP_DELTA) ;
		
		assertEquals ( "owning link "  , linkAttribute7 , linkTerminus13.getOwningLink()) ;
		assertEquals ( "link term type" , SOURCE_LINK_TERMINUS_TYPE ,linkTerminus13.getLinkTermType()) ;
		assertEquals ( "offsetValue" , LINKTERM_OFFSET_VALUE13 , linkTerminus13.getGap(), CMP_DELTA) ;
		assertEquals ( "end decorator type" , END_DECORATOR_TYPE_SOURCE , linkTerminus13.getEndDecoratorType()) ;
		assertEquals ( "end decorator size" , END_DECORATOR_SIZE23 , linkTerminus13.getEndSize()) ;
		
		assertEquals ( "owning link "  , linkAttribute7 , linkTerminus14.getOwningLink()) ;
		assertEquals ( "link term type" , TARGET_LINK_TERMINUS_TYPE ,linkTerminus14.getLinkTermType()) ;
		assertEquals ( "offsetValue" , LINKTERM_OFFSET_VALUE14 , linkTerminus14.getGap(), CMP_DELTA) ;
		assertEquals ( "end decorator type" , END_DECORATOR_TYPE_TARGET , linkTerminus14.getEndDecoratorType()) ;
		assertEquals ( "end decorator size" , END_DECORATOR_SIZE24 , linkTerminus14.getEndSize()) ;
	}
	
	@Test
	public void testCheckIntegrityOfLinkEdge8AndLinkAttribute8AndTermini (){
		ILinkAttribute linkAttribute8 = this.dbCanvas.getLinkAttribute(LINK_ATTRIBUTE8_SERIAL);
		ILinkEdge linkEdge8 = linkAttribute8.getCurrentDrawingElement();
		ILinkTerminus linkTerminus15 = linkAttribute8.getSourceTerminus() ;
		ILinkTerminus linkTerminus16 = linkAttribute8.getTargetTerminus() ;
		
		assertNotNull ( "is not Null" , linkEdge8) ;
		assertNotNull ( "has attribute" , linkEdge8.getAttribute()) ;
		
		assertEquals ( "link edge index" , LINK_EDGE8_INDEX , linkEdge8.getIndex()) ;
		assertEquals ( "owning subModel" , SHAPENODE1_INDEX, linkEdge8.getOwningSubModel().getRootNode().getIndex() ) ;
		assertEquals ( "source shape" , SHAPENODE1_INDEX, linkEdge8.getSourceShape().getIndex() ) ;
		assertEquals ( "target shape" , SHAPENODE1_INDEX, linkEdge8.getTargetShape().getIndex() ) ;
		
		assertEquals ( "check Canvas" , dbCanvas , linkAttribute8.getCanvas() );
		assertEquals ( "check creation serial" , LINK_ATTRIBUTE8_SERIAL , linkAttribute8.getCreationSerial()) ;
		assertEquals ( "check line color" , RGB_108 , linkAttribute8.getLineColor() ) ;
		assertEquals ( "check linestyle" , LineStyle.DASH_DOT , linkAttribute8.getLineStyle() ) ;
		assertEquals ( "check no of bends" , NUM_OF_BENDPOINTS_IN_LINK8 , linkAttribute8.numBendPoints()) ;
		assertEquals ( "check line width" , LINE_WIDTH_OF_LINKATTRIBUTE8 , linkAttribute8.getLineWidth(), CMP_DELTA) ;
		
		assertEquals ( "owning link "  , linkAttribute8 , linkTerminus15.getOwningLink()) ;
		assertEquals ( "link term type" , SOURCE_LINK_TERMINUS_TYPE ,linkTerminus15.getLinkTermType()) ;
		assertEquals ( "offsetValue" , LINKTERM_OFFSET_VALUE15 , linkTerminus15.getGap(), CMP_DELTA) ;
		assertEquals ( "end decorator type" , END_DECORATOR_TYPE_SOURCE , linkTerminus15.getEndDecoratorType()) ;
		assertEquals ( "end decorator size" , END_DECORATOR_SIZE25 , linkTerminus15.getEndSize()) ;
		
		assertEquals ( "owning link "  , linkAttribute8 , linkTerminus16.getOwningLink()) ;
		assertEquals ( "link term type" , TARGET_LINK_TERMINUS_TYPE ,linkTerminus16.getLinkTermType()) ;
		assertEquals ( "offsetValue" , LINKTERM_OFFSET_VALUE16 , linkTerminus16.getGap(), CMP_DELTA) ;
		assertEquals ( "end decorator type" , END_DECORATOR_TYPE_TARGET , linkTerminus16.getEndDecoratorType()) ;
		assertEquals ( "end decorator size" , END_DECORATOR_SIZE26 , linkTerminus16.getEndSize()) ;
	}
	
	@Test
	public void testCheckIntegrityOfLinkEdge9AndLinkAttribute9AndTermini () {
		ILinkAttribute linkAttribute9 = this.dbCanvas.getLinkAttribute(LINK_ATTRIBUTE9_SERIAL);
		ILinkEdge linkEdge9 = linkAttribute9.getCurrentDrawingElement();
		ILinkTerminus linkTerminus17 = linkAttribute9.getSourceTerminus() ;
		ILinkTerminus linkTerminus18 = linkAttribute9.getTargetTerminus() ;
	
		assertNotNull ( "is not Null" , linkEdge9) ;
		assertNotNull ( "has attribute" , linkEdge9.getAttribute()) ;
		
		assertEquals ( "link edge index" , LINK_EDGE9_INDEX , linkEdge9.getIndex()) ;
		assertEquals ( "owning subModel" , SHAPENODE1_INDEX, linkEdge9.getOwningSubModel().getRootNode().getIndex() ) ;
		assertEquals ( "source shape" , SHAPENODE1_INDEX, linkEdge9.getSourceShape().getIndex() ) ;
		assertEquals ( "target shape" , SHAPENODE1_INDEX, linkEdge9.getTargetShape().getIndex() ) ;
		
		assertEquals ( "check Canvas" , dbCanvas , linkAttribute9.getCanvas() );
		assertEquals ( "check creation serial" , LINK_ATTRIBUTE9_SERIAL , linkAttribute9.getCreationSerial()) ;
		assertEquals ( "check line color" , RGB_109 , linkAttribute9.getLineColor() ) ;
		assertEquals ( "check linestyle" , LineStyle.DASH_DOT_DOT , linkAttribute9.getLineStyle() ) ;
		assertEquals ( "check no of bends" , NUM_OF_BENDPOINTS_IN_LINK9 , linkAttribute9.numBendPoints()) ;
		assertEquals ( "check line width" , LINE_WIDTH_OF_LINKATTRIBUTE9 , linkAttribute9.getLineWidth(), CMP_DELTA) ;
		
		assertEquals ( "owning link "  , linkAttribute9 , linkTerminus17.getOwningLink()) ;
		assertEquals ( "link term type" , SOURCE_LINK_TERMINUS_TYPE ,linkTerminus17.getLinkTermType()) ;
		assertEquals ( "offsetValue" , LINKTERM_OFFSET_VALUE17 , linkTerminus17.getGap(), CMP_DELTA) ;
		assertEquals ( "end decorator type" , END_DECORATOR_TYPE_SOURCE , linkTerminus17.getEndDecoratorType()) ;
		assertEquals ( "end decorator size" , END_DECORATOR_SIZE27 , linkTerminus17.getEndSize()) ;
		
		assertEquals ( "owning link "  , linkAttribute9 , linkTerminus18.getOwningLink()) ;
		assertEquals ( "link term type" , TARGET_LINK_TERMINUS_TYPE ,linkTerminus18.getLinkTermType()) ;
		assertEquals ( "offsetValue" , LINKTERM_OFFSET_VALUE18 , linkTerminus18.getGap(), CMP_DELTA) ;
		assertEquals ( "end decorator type" , END_DECORATOR_TYPE_TARGET , linkTerminus18.getEndDecoratorType()) ;
		assertEquals ( "end decorator size" , END_DECORATOR_SIZE28 , linkTerminus18.getEndSize()) ;
	}
	
	@Test
	public void testCheckLinkAt1BendPoints () {
		ILinkAttribute linkAttribute = this.dbCanvas.getLinkAttribute(LINK_ATTRIBUTE1_SERIAL);
		IBendPoint bendPoint1 = linkAttribute.getBendPoint(FIRST_LINK1_BENDPOINT);
		
		assertEquals ( "expected num bendpoints" , EXPECTED_NUM_LINK1_BENDPOINTS, linkAttribute.numBendPoints()) ;
		assertEquals ( "check Point" , LOCATION_51 , bendPoint1.getLocation() ) ;
		assertEquals ( "check parent" , linkAttribute , bendPoint1.getOwningLink()) ;
		
//		assertNotNull ( "is not null" , bendpoint2 ) ;
//		assertEquals ( "check Point" , LOCATION_52 , bendpoint2.getLocation() ) ;
//		assertEquals ( "check parent" , linkAttribute2 , bendpoint2.getOwningLink()) ;

	}

	@Test
	public void testCheckLinkAt2BendPoints () {
		ILinkAttribute linkAttribute = this.dbCanvas.getLinkAttribute(LINK_ATTRIBUTE2_SERIAL);
		IBendPoint bendPoint1 = linkAttribute.getBendPoint(FIRST_LINK2_BENDPOINT);
		
		assertEquals ( "expected num bendpoints" , EXPECTED_NUM_LINK2_BENDPOINTS, linkAttribute.numBendPoints()) ;
		assertEquals ( "check Point" , LOCATION_52 , bendPoint1.getLocation() ) ;
		assertEquals ( "check parent" , linkAttribute , bendPoint1.getOwningLink()) ;
	}
}
	