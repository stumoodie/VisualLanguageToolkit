/**
 * 
 */
package org.pathwayeditor.businessobjects.hibernate.pojos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;
import java.util.List;

import org.junit.Test;
import org.pathwayeditor.businessobjects.drawingprimitives.ICanvas;
import org.pathwayeditor.businessobjects.drawingprimitives.ILabelAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.ILabelNode;
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkEdge;
import org.pathwayeditor.businessobjects.drawingprimitives.IModel;
import org.pathwayeditor.businessobjects.drawingprimitives.IRootNode;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeNode;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.ConnectionRouter;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.IBendPoint;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LineStyle;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.Location;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.Size;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationProperty;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IHtmlAnnotationProperty;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IListAnnotationProperty;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.INumberAnnotationProperty;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPlainTextAnnotationProperty;
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
	
	private ILabelNode labelNode1 ;
	private ILabelNode labelNode2 ;
	private ILabelNode labelNode3 ;
	private ILabelNode labelNode4 ;
	private ILabelNode labelNode5 ;
	private ILabelNode labelNode6 ;
	private ILabelNode labelNode7 ;
	private ILabelNode labelNode8 ;
	
	private ILabelAttribute labelAttribute1 ;
	private ILabelAttribute labelAttribute2 ;
	private ILabelAttribute labelAttribute3 ;
	private ILabelAttribute labelAttribute4 ;
	private ILabelAttribute labelAttribute5 ;
	private ILabelAttribute labelAttribute6 ;
	private ILabelAttribute labelAttribute7 ;
	private ILabelAttribute labelAttribute8 ;
	
	private ILinkEdge linkEdge1 ;
	private ILinkEdge linkEdge2 ;
	private ILinkEdge linkEdge3;
	private ILinkEdge linkEdge4 ;
	private ILinkEdge linkEdge5 ;
	private ILinkEdge linkEdge6 ;
	private ILinkEdge linkEdge7 ;
	private ILinkEdge linkEdge8 ;
	private ILinkEdge linkEdge9 ;
	
	private ILinkAttribute linkAttribute1 ;
	private ILinkAttribute linkAttribute2 ;
	private ILinkAttribute linkAttribute3 ;
	private ILinkAttribute linkAttribute4 ;
	private ILinkAttribute linkAttribute5 ;
	private ILinkAttribute linkAttribute6 ;
	private ILinkAttribute linkAttribute7 ;
	private ILinkAttribute linkAttribute8 ;
	private ILinkAttribute linkAttribute9 ;
	
	private IAnnotationProperty property1 ;
	private IAnnotationProperty property2 ;
	private IAnnotationProperty property3 ;
	private IAnnotationProperty property4 ;
	private IAnnotationProperty property5 ;
	private IAnnotationProperty property6 ;
	private IAnnotationProperty property7 ;
	private IAnnotationProperty property8 ;
	
	private IBendPoint bendpoint1 ;
	private IBendPoint bendpoint2 ;
	
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
	
	private static final String LINK_ATTR1_URL = "http://www.HibLink1.org" ;
	private static final String LINK_ATTR2_URL = "http://www.HibLink2.org" ;
	private static final String LINK_ATTR3_URL = "http://www.HibLink3.org" ;
	private static final String LINK_ATTR4_URL = "http://www.HibLink4.org" ;
	private static final String LINK_ATTR5_URL = "http://www.HibLink5.org" ;
	private static final String LINK_ATTR6_URL = "http://www.HibLink6.org" ;
	private static final String LINK_ATTR7_URL = "http://www.HibLink7.org" ;
	private static final String LINK_ATTR8_URL = "http://www.HibLink8.org" ;
	private static final String LINK_ATTR9_URL = "http://www.HibLink9.org" ;
	
	private static final String LINK_ATTR1_NAME = "link_name1" ;
	private static final String LINK_ATTR2_NAME = "link_name2" ;
	private static final String LINK_ATTR3_NAME = "link_name3" ;
	private static final String LINK_ATTR4_NAME = "link_name4" ;
	private static final String LINK_ATTR5_NAME = "link_name5" ;
	private static final String LINK_ATTR6_NAME = "link_name6" ;
	private static final String LINK_ATTR7_NAME = "link_name7" ;
	private static final String LINK_ATTR8_NAME = "link_name8" ;
	private static final String LINK_ATTR9_NAME = "link_name9" ;
	
	private static final String LINK_ATTR1_DESCR = "link_descr1" ;
	private static final String LINK_ATTR2_DESCR = "link_descr2" ;
	private static final String LINK_ATTR3_DESCR = "link_descr3" ;
	private static final String LINK_ATTR4_DESCR = "link_descr4" ;
	private static final String LINK_ATTR5_DESCR = "link_descr5" ;
	private static final String LINK_ATTR6_DESCR = "link_descr6" ;
	private static final String LINK_ATTR7_DESCR = "link_descr7" ;
	private static final String LINK_ATTR8_DESCR = "link_descr8" ;
	private static final String LINK_ATTR9_DESCR = "link_descr9" ;
	
	private static final String LINK_ATTR1_DET_DESCR = "link_detailed_descr1" ;
	private static final String LINK_ATTR2_DET_DESCR = "link_detailed_descr2" ;
	private static final String LINK_ATTR3_DET_DESCR = "link_detailed_descr3" ;
	private static final String LINK_ATTR4_DET_DESCR = "link_detailed_descr4" ;
	private static final String LINK_ATTR5_DET_DESCR = "link_detailed_descr5" ;
	private static final String LINK_ATTR6_DET_DESCR = "link_detailed_descr6" ;
	private static final String LINK_ATTR7_DET_DESCR = "link_detailed_descr7" ;
	private static final String LINK_ATTR8_DET_DESCR = "link_detailed_descr8" ;
	private static final String LINK_ATTR9_DET_DESCR = "link_detailed_descr9" ;
	
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
	
	private static final int NUM_OF_ROOTNODE_LABEL_CHILDREN = 3 ;
	private static final int NUM_OF_SHAPENODE1_LABEL_CHILDREN = 1 ;
	private static final int NUM_OF_SHAPENODE2_LABEL_CHILDREN = 2 ;
	private static final int NUM_OF_SHAPENODE3_LABEL_CHILDREN = 0 ;
	private static final int NUM_OF_SHAPENODE4_LABEL_CHILDREN = 0 ;
	private static final int NUM_OF_SHAPENODE5_LABEL_CHILDREN = 0 ;
	private static final int NUM_OF_SHAPENODE6_LABEL_CHILDREN = 1 ;
	private static final int NUM_OF_SHAPENODE7_LABEL_CHILDREN = 1 ;
	private static final int NUM_OF_SHAPENODE8_LABEL_CHILDREN = 0 ;
	
	private static final int NUM_OF_ROOTNODE_LINK_CHILDREN = 5  ;
	private static final int NUM_OF_SHAPENODE1_LINK_CHILDREN = 2 ;
	private static final int NUM_OF_SHAPENODE2_LINK_CHILDREN = 2 ;
	private static final int NUM_OF_SHAPENODE3_LINK_CHILDREN = 0 ;
	private static final int NUM_OF_SHAPENODE4_LINK_CHILDREN = 0 ;
	private static final int NUM_OF_SHAPENODE5_LINK_CHILDREN = 0 ;
	private static final int NUM_OF_SHAPENODE6_LINK_CHILDREN = 0 ;
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
	
	private static final int SHAPE_ATTRIBUTE1_LINE_WIDTH = 1 ;
	private static final int SHAPE_ATTRIBUTE2_LINE_WIDTH = 2 ;
	private static final int SHAPE_ATTRIBUTE3_LINE_WIDTH = 3 ;
	private static final int SHAPE_ATTRIBUTE4_LINE_WIDTH = 4 ;
	private static final int SHAPE_ATTRIBUTE5_LINE_WIDTH = 5 ;
	private static final int SHAPE_ATTRIBUTE6_LINE_WIDTH = 6 ;
	private static final int SHAPE_ATTRIBUTE7_LINE_WIDTH = 7 ;
	private static final int SHAPE_ATTRIBUTE8_LINE_WIDTH = 8 ;
	
	private static final int SHAPE_ATTRIBUTE1_LINE_PADDING = 1 ;
	private static final int SHAPE_ATTRIBUTE2_LINE_PADDING = 2 ;
	private static final int SHAPE_ATTRIBUTE3_LINE_PADDING = 3 ;
	private static final int SHAPE_ATTRIBUTE4_LINE_PADDING = 4 ;
	private static final int SHAPE_ATTRIBUTE5_LINE_PADDING = 5 ;
	private static final int SHAPE_ATTRIBUTE6_LINE_PADDING = 6 ;
	private static final int SHAPE_ATTRIBUTE7_LINE_PADDING = 7 ;
	private static final int SHAPE_ATTRIBUTE8_LINE_PADDING = 8 ;
	
	private static final int LINK_EDGE1_INDEX = 0 ;
	private static final int LINK_EDGE2_INDEX = 1 ;
	private static final int LINK_EDGE3_INDEX = 2 ;
	private static final int LINK_EDGE4_INDEX = 3 ;
	private static final int LINK_EDGE5_INDEX = 4 ;
	private static final int LINK_EDGE6_INDEX = 5 ;
	private static final int LINK_EDGE7_INDEX = 6 ;
	private static final int LINK_EDGE8_INDEX = 7 ;
	private static final int LINK_EDGE9_INDEX = 8 ;
	
	private static final int LINK_ATTRIBUTE1_SERIAL = 1 ; 
	private static final int LINK_ATTRIBUTE2_SERIAL = 2 ; 
	private static final int LINK_ATTRIBUTE3_SERIAL = 3 ; 
	private static final int LINK_ATTRIBUTE4_SERIAL = 4 ; 
	private static final int LINK_ATTRIBUTE5_SERIAL = 5 ; 
	private static final int LINK_ATTRIBUTE6_SERIAL = 6 ; 
	private static final int LINK_ATTRIBUTE7_SERIAL = 7 ; 
	private static final int LINK_ATTRIBUTE8_SERIAL = 8 ; 
	private static final int LINK_ATTRIBUTE9_SERIAL = 9 ; 
	
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
	
	private static final int PROPERTY1_CREATION_SERIAL = 1 ; 
	private static final int PROPERTY2_CREATION_SERIAL = 2 ; 
	private static final int PROPERTY3_CREATION_SERIAL = 3 ; 
	private static final int PROPERTY4_CREATION_SERIAL = 4 ; 
	private static final int PROPERTY5_CREATION_SERIAL = 5 ; 
	private static final int PROPERTY6_CREATION_SERIAL = 6 ; 
	private static final int PROPERTY7_CREATION_SERIAL = 7 ; 
	private static final int PROPERTY8_CREATION_SERIAL = 8 ; 
	
	private static final String PROPERTY1_VALUE = "textPropertyValue" ;
	private static final String PROPERTY2_VALUE = "textPropertyValue2" ;
	private static final String PROPERTY3_VALUE = "richtextvalue" ;
	private static final String PROPERTY4_VALUE = "richtextvalue2" ;
	private static final int PROPERTY5_VALUE = 1 ;
	private static final int PROPERTY6_VALUE = 2 ;
	private static final String PROPERTY7_LIST_VALUE1 = "first in list 1" ;
	private static final String PROPERTY7_LIST_VALUE2 = "second in list 1" ;
	private static final String PROPERTY8_LIST_VALUE1 = "first in list 2" ;
	private static final String PROPERTY8_LIST_VALUE2 = "second in list 2" ;
	
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.testutils.GenericTester#doAdditionalSetUp()
	 */
	@Override
	protected void doAdditionalSetUp() {
		try {
			repository = this.getRepositoryPersistenceManager().getRepository();
		} catch (PersistenceManagerNotOpenException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	private void loadObjects () throws Exception
	{
		
		rootFolder = repository.getRootFolder() ;
		
		subFolder1 = (ISubFolder)repository.getFolderByPath( SUBFOLDER1_PATH) ;
		subFolder2 = (ISubFolder)repository.getFolderByPath( SUBFOLDER2_PATH) ;
		
		mapDiagram1 = subFolder1.getMapIterator().next() ;
		mapDiagram2 = subFolder2.getMapIterator().next() ;
		
		IMapContentPersistenceManager map1Manager = this.getRepositoryPersistenceManager().openMap(mapDiagram1) ;
		map1Manager.loadContent() ;
		dbCanvas = map1Manager.getCanvas() ;
		
		dbModel = dbCanvas.getModel() ;
		dbRootNode = dbModel.getRootNode() ;
		
		Iterator<IShapeNode> rootNodeChildrenIterator = dbRootNode.getSubModel().shapeIterator() ;
		
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
		
		Iterator<IShapeNode> shape1ChildIterator = shapeNode1.getSubModel().shapeIterator() ;
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
		
		Iterator<IShapeNode> shape2ChildIterator = shapeNode2.getSubModel().shapeIterator() ;
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
		
		shapeNode7 = shapeNode6.getSubModel().shapeIterator().next() ;
		shapeAttribute7 = shapeNode7.getAttribute() ;
		
		shapeNode8 = shapeNode7.getSubModel().shapeIterator().next() ;
		shapeAttribute8 = shapeNode8.getAttribute() ;
		
		Iterator<ILinkEdge> rootNodeEdges = dbRootNode.getSubModel().linkIterator() ;	
		
		while ( rootNodeEdges.hasNext())
		{
			ILinkEdge tempLinkEdge = rootNodeEdges.next() ;
			
			if ( tempLinkEdge.getIndex() == 0 )
			{
				linkEdge1 = tempLinkEdge ;
				linkAttribute1 = tempLinkEdge.getAttribute() ;
				bendpoint1 = linkAttribute1.bendPointIterator().next() ;
			}
			if ( tempLinkEdge.getIndex() == 1 )
			{
				linkEdge2 = tempLinkEdge ;
				linkAttribute2 = tempLinkEdge.getAttribute() ;
				bendpoint2 = linkAttribute2.bendPointIterator().next() ;
			}
			if ( tempLinkEdge.getIndex() == 2 )
			{
				linkEdge3 = tempLinkEdge ;
				linkAttribute3 = tempLinkEdge.getAttribute() ;
			}
			if ( tempLinkEdge.getIndex() == 3 )
			{
				linkEdge4 = tempLinkEdge ;
				linkAttribute4 = tempLinkEdge.getAttribute() ;
			}
			if ( tempLinkEdge.getIndex() == 6 )
			{
				linkEdge7 = tempLinkEdge ;
				linkAttribute7 = tempLinkEdge.getAttribute() ;
			}
		}
		
		Iterator<ILinkEdge> shapeNode1Edges = shapeNode1.getSubModel().linkIterator() ;
		while ( shapeNode1Edges.hasNext())
		{
			ILinkEdge tempLinkEdge = shapeNode1Edges.next() ;
			
			if ( tempLinkEdge.getIndex() == 7 )
			{
				linkEdge8 = tempLinkEdge ;
				linkAttribute8 = tempLinkEdge.getAttribute() ;
			}
			if ( tempLinkEdge.getIndex() == 8 )
			{
				linkEdge9 = tempLinkEdge ;
				linkAttribute9 = tempLinkEdge.getAttribute() ;
			}
		}
		
		Iterator<ILinkEdge> shapeNode2Edges = shapeNode2.getSubModel().linkIterator() ;
		while ( shapeNode2Edges.hasNext())
		{
			ILinkEdge tempLinkEdge = shapeNode2Edges.next() ;
			
			if ( tempLinkEdge.getIndex() == 4 )
			{
				linkEdge5 = tempLinkEdge ;
				linkAttribute5 = tempLinkEdge.getAttribute() ;
			}
			if ( tempLinkEdge.getIndex() == 5 )
			{
				linkEdge6 = tempLinkEdge ;
				linkAttribute6 = tempLinkEdge.getAttribute() ;
			}
		}
		
		Iterator<ILabelNode> rootNodeLabels = dbRootNode.getSubModel().labelIterator() ;
		while ( rootNodeLabels.hasNext() )
		{
			ILabelNode tempLabelNode = rootNodeLabels.next() ;
			
			if ( tempLabelNode.getIndex() == 10 )
			{
				labelNode2 =  tempLabelNode ;
				labelAttribute2 = tempLabelNode.getAttribute() ;
				property2 = labelAttribute2.getProperty() ;
			}
			if ( tempLabelNode.getIndex() == 11 )
			{
				labelNode3 =  tempLabelNode ;
				labelAttribute3 = tempLabelNode.getAttribute() ;
				property3 = labelAttribute3.getProperty() ;
			}
			if ( tempLabelNode.getIndex() == 13 )
			{
				labelNode5 =  tempLabelNode ;
				labelAttribute5 = tempLabelNode.getAttribute() ;
				property5 = labelAttribute5.getProperty() ;
			}
		}
		
		labelNode1 = shapeNode1.getSubModel().labelIterator().next() ;
		labelAttribute1 = labelNode1.getAttribute() ;
		property1 = labelAttribute1.getProperty() ;
		
		Iterator<ILabelNode> shapeNode2Labels = shapeNode2.getSubModel().labelIterator() ;
		while ( shapeNode2Labels.hasNext() )
		{
			ILabelNode tempLabelNode = shapeNode2Labels.next() ;
			
			if ( tempLabelNode.getIndex() == 12 )
			{
				labelNode4 =  tempLabelNode ;
				labelAttribute4 = tempLabelNode.getAttribute() ;
				property4 = labelAttribute4.getProperty() ;
			}
			if ( tempLabelNode.getIndex() == 15 )
			{
				labelNode7 =  tempLabelNode ;
				labelAttribute7 = tempLabelNode.getAttribute() ;
				property7 = labelAttribute7.getProperty() ;
			}
		}
		
		labelNode6 = shapeNode6.getSubModel().labelIterator().next() ;
		labelAttribute6 = labelNode6.getAttribute() ;
		property6 = labelAttribute6.getProperty() ;
		
		labelNode8 = shapeNode7.getSubModel().labelIterator().next() ;
		labelAttribute8 = labelNode8.getAttribute() ;
		property8 = labelAttribute8.getProperty() ;
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
		
		labelNode1 = null;
		labelNode2 = null;
		labelNode3 = null;
		labelNode4 = null;
		labelNode5 = null;
		labelNode6 = null;
		labelNode7 = null;
		labelNode8 = null;
		
		labelAttribute1 = null;
		labelAttribute2 = null;
		labelAttribute3 = null;
		labelAttribute4 = null;
		labelAttribute5 = null;
		labelAttribute6 = null;
		labelAttribute7 = null;
		labelAttribute8 = null;
		
		linkEdge1 = null;
		linkEdge2 = null;
		linkEdge3 = null;
		linkEdge4 = null;
		linkEdge5 = null;
		linkEdge6 = null;
		linkEdge7 = null;
		linkEdge8 = null;
		linkEdge9 = null;
		
		linkAttribute1 = null;
		linkAttribute2 = null;
		linkAttribute3 = null;
		linkAttribute4 = null;
		linkAttribute5 = null;
		linkAttribute6 = null;
		linkAttribute7 = null;
		linkAttribute8 = null;
		linkAttribute9 = null;
		
		property1 = null;
		property2 = null;
		property3 = null;
		property4 = null;
		property5 = null;
		property6 = null;
		property7 = null;
		property8 = null;
		
		bendpoint1 = null;
		bendpoint2 = null;
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
		assertNotNull ("rootNode has SubModel" , dbRootNode.getSubModel()) ;
		
		assertEquals ( "root node index" , ROOT_NODE_INDEX , dbRootNode.getIndex() ) ;
		assertEquals ( "model check" , dbModel , dbRootNode.getModel()) ;
		assertNull ( "no parent for rootNode" , dbRootNode.getParent() ) ;
		assertEquals ( "rootnode has 2 shapes", NUM_OF_ROOTNODE_SHAPE_CHILDREN , dbRootNode.getSubModel().getNumShapes()) ;
		assertEquals ( "rootnode has no labels", NUM_OF_ROOTNODE_LABEL_CHILDREN , dbRootNode.getSubModel().getNumLabels()) ;
		assertEquals ( "rootnode has 5 links", NUM_OF_ROOTNODE_LINK_CHILDREN , dbRootNode.getSubModel().getNumLinks()) ;
		
		int numOfShapeNodes = 0 ;
		
		Iterator <IShapeNode> shapeIterator = dbRootNode.getSubModel().shapeIterator() ;
		
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
		assertNotNull ("shapeNode1 has SubModel" , shapeNode1.getSubModel()) ;
		assertNotNull ( "is linkedtoAttribute" , shapeNode1.getAttribute() ) ;
		
		assertEquals ( "shapenode1 index" , SHAPENODE1_INDEX , shapeNode1.getIndex() ) ;
		assertEquals ( "model check" , dbModel , shapeNode1.getModel()) ;
		assertEquals ( "parent is rootNode" , dbRootNode.getIndex() , shapeNode1.getParent().getIndex() ) ;
		assertEquals ( "shapeNode1 has 2 shapes", NUM_OF_SHAPENODE1_SHAPE_CHILDREN , shapeNode1.getSubModel().getNumShapes()) ;
		assertEquals ( "shapeNode1 has no labels", NUM_OF_SHAPENODE1_LABEL_CHILDREN , shapeNode1.getSubModel().getNumLabels()) ;
		assertEquals ( "shapeNode1 has no links", NUM_OF_SHAPENODE1_LINK_CHILDREN , shapeNode1.getSubModel().getNumLinks()) ;
		
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
		
		Iterator <IShapeNode> shapeIterator = shapeNode1.getSubModel().shapeIterator() ;
		
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
		assertNotNull ("shapeNode2 has SubModel" , shapeNode2.getSubModel()) ;
		assertNotNull ( "is linkedtoAttribute" , shapeNode2.getAttribute() ) ;
		
		assertEquals ( "shapenode2 index" , SHAPENODE2_INDEX , shapeNode2.getIndex() ) ;
		assertEquals ( "model check" , dbModel , shapeNode2.getModel()) ;
		assertEquals ( "parent is rootNode" , dbRootNode.getIndex() , shapeNode2.getParent().getIndex() ) ;
		assertEquals ( "shapeNode1 has 2 shapes", NUM_OF_SHAPENODE2_SHAPE_CHILDREN , shapeNode2.getSubModel().getNumShapes()) ;
		assertEquals ( "shapeNode1 has no labels", NUM_OF_SHAPENODE2_LABEL_CHILDREN , shapeNode2.getSubModel().getNumLabels()) ;
		assertEquals ( "shapeNode1 has no links", NUM_OF_SHAPENODE2_LINK_CHILDREN , shapeNode2.getSubModel().getNumLinks()) ;
		
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
		
		Iterator <IShapeNode> shapeIterator = shapeNode2.getSubModel().shapeIterator() ;
		
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
		assertNotNull ("shapeNode3 has SubModel" , shapeNode3.getSubModel()) ;
		assertNotNull ( "is linkedtoAttribute" , shapeNode3.getAttribute() ) ;
		
		assertEquals ( "shapenode3 index" , SHAPENODE3_INDEX , shapeNode3.getIndex() ) ;
		assertEquals ( "model check" , dbModel , shapeNode3.getModel()) ;
		assertEquals ( "parent is shapeNode1" , shapeNode1.getIndex() , shapeNode3.getParent().getIndex() ) ;
		assertEquals ( "shapeNode3 has no shapes", NUM_OF_SHAPENODE3_SHAPE_CHILDREN , shapeNode3.getSubModel().getNumShapes()) ;
		assertEquals ( "shapeNode3 has no labels", NUM_OF_SHAPENODE3_LABEL_CHILDREN , shapeNode3.getSubModel().getNumLabels()) ;
		assertEquals ( "shapeNode3 has no links", NUM_OF_SHAPENODE3_LINK_CHILDREN , shapeNode3.getSubModel().getNumLinks()) ;
		
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
		
		Iterator <IShapeNode> shapeIterator = shapeNode3.getSubModel().shapeIterator() ;
		
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
		assertNotNull ("shapeNode4 has SubModel" , shapeNode4.getSubModel()) ;
		assertNotNull ( "is linkedtoAttribute" , shapeNode4.getAttribute() ) ;
		
		assertEquals ( "shapenode4 index" , SHAPENODE4_INDEX , shapeNode4.getIndex() ) ;
		assertEquals ( "model check" , dbModel , shapeNode4.getModel()) ;
		assertEquals ( "parent is shapeNode1" , shapeNode1.getIndex() , shapeNode4.getParent().getIndex() ) ;
		assertEquals ( "shapeNode4 has no shapes", NUM_OF_SHAPENODE4_SHAPE_CHILDREN , shapeNode4.getSubModel().getNumShapes()) ;
		assertEquals ( "shapeNode4 has no labels", NUM_OF_SHAPENODE4_LABEL_CHILDREN , shapeNode4.getSubModel().getNumLabels()) ;
		assertEquals ( "shapeNode4 has no links", NUM_OF_SHAPENODE4_LINK_CHILDREN , shapeNode4.getSubModel().getNumLinks()) ;
		
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
		
		Iterator <IShapeNode> shapeIterator = shapeNode4.getSubModel().shapeIterator() ;
		
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
		assertNotNull ("shapeNode5 has SubModel" , shapeNode5.getSubModel()) ;
		assertNotNull ( "is linkedtoAttribute" , shapeNode5.getAttribute() ) ;
		
		assertEquals ( "shapenode5 index" , SHAPENODE5_INDEX , shapeNode5.getIndex() ) ;
		assertEquals ( "model check" , dbModel , shapeNode5.getModel()) ;
		assertEquals ( "parent is shapeNode2" , shapeNode2.getIndex() , shapeNode5.getParent().getIndex() ) ;
		assertEquals ( "shapeNode5 has no shapes", NUM_OF_SHAPENODE5_SHAPE_CHILDREN , shapeNode5.getSubModel().getNumShapes()) ;
		assertEquals ( "shapeNode5 has no labels", NUM_OF_SHAPENODE5_LABEL_CHILDREN , shapeNode5.getSubModel().getNumLabels()) ;
		assertEquals ( "shapeNode5 has no links", NUM_OF_SHAPENODE5_LINK_CHILDREN , shapeNode5.getSubModel().getNumLinks()) ;
		
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
		
		Iterator <IShapeNode> shapeIterator = shapeNode5.getSubModel().shapeIterator() ;
		
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
		assertNotNull ("shapeNode6 has SubModel" , shapeNode6.getSubModel()) ;
		assertNotNull ( "is linkedtoAttribute" , shapeNode6.getAttribute() ) ;
		
		assertEquals ( "shapenode6 index" , SHAPENODE6_INDEX , shapeNode6.getIndex() ) ;
		assertEquals ( "model check" , dbModel , shapeNode6.getModel()) ;
		assertEquals ( "parent is shapeNode2" , shapeNode2.getIndex() , shapeNode6.getParent().getIndex() ) ;
		assertEquals ( "shapeNode6 one shapes", NUM_OF_SHAPENODE6_SHAPE_CHILDREN , shapeNode6.getSubModel().getNumShapes()) ;
		assertEquals ( "shapeNode6 has no labels", NUM_OF_SHAPENODE6_LABEL_CHILDREN , shapeNode6.getSubModel().getNumLabels()) ;
		assertEquals ( "shapeNode6 has no links", NUM_OF_SHAPENODE6_LINK_CHILDREN , shapeNode6.getSubModel().getNumLinks()) ;
		
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
		
		Iterator <IShapeNode> shapeIterator = shapeNode6.getSubModel().shapeIterator() ;
		
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
		assertNotNull ("shapeNode7 has SubModel" , shapeNode7.getSubModel()) ;
		assertNotNull ( "is linkedtoAttribute" , shapeNode7.getAttribute() ) ;
		
		assertEquals ( "shapenode7 index" , SHAPENODE7_INDEX , shapeNode7.getIndex() ) ;
		assertEquals ( "model check" , dbModel , shapeNode7.getModel()) ;
		assertEquals ( "parent is shapeNode6" , shapeNode6.getIndex() , shapeNode7.getParent().getIndex() ) ;
		assertEquals ( "shapeNode7 has one shapes", NUM_OF_SHAPENODE7_SHAPE_CHILDREN , shapeNode7.getSubModel().getNumShapes()) ;
		assertEquals ( "shapeNode7 has no labels", NUM_OF_SHAPENODE7_LABEL_CHILDREN , shapeNode7.getSubModel().getNumLabels()) ;
		assertEquals ( "shapeNode7 has no links", NUM_OF_SHAPENODE7_LINK_CHILDREN , shapeNode7.getSubModel().getNumLinks()) ;
		
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
		
		Iterator <IShapeNode> shapeIterator = shapeNode7.getSubModel().shapeIterator() ;
		
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
		assertNotNull ("shapeNode8 has SubModel" , shapeNode8.getSubModel()) ;
		assertNotNull ( "is linkedtoAttribute" , shapeNode8.getAttribute() ) ;
		
		assertEquals ( "shapenode8 index" , SHAPENODE8_INDEX , shapeNode8.getIndex() ) ;
		assertEquals ( "model check" , dbModel , shapeNode8.getModel()) ;
		assertEquals ( "parent is shapeNode7" , shapeNode7.getIndex() , shapeNode8.getParent().getIndex() ) ;
		assertEquals ( "shapeNode8 has one shapes", NUM_OF_SHAPENODE8_SHAPE_CHILDREN , shapeNode8.getSubModel().getNumShapes()) ;
		assertEquals ( "shapeNode8 has no labels", NUM_OF_SHAPENODE8_LABEL_CHILDREN , shapeNode8.getSubModel().getNumLabels()) ;
		assertEquals ( "shapeNode8 has no links", NUM_OF_SHAPENODE8_LINK_CHILDREN , shapeNode8.getSubModel().getNumLinks()) ;
		
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
		
		Iterator <IShapeNode> shapeIterator = shapeNode8.getSubModel().shapeIterator() ;
		
		while ( shapeIterator.hasNext())
		{
			shapeIterator.next();
			numOfShapeNodes ++ ;
		}
		
		assertEquals ( "0 shapeNodes" , 0 , numOfShapeNodes) ;
				
	}
	
	@Test
	public void testCheckIntegrityOfLabelNode1AndLabelAttribute1 () throws Exception 
	{
		loadObjects() ;
		assertNotNull ( "is not Null" , labelNode1) ;
		assertNotNull ("labelNode1 has SubModel" , labelNode1.getSubModel()) ;
		assertNotNull ( "has attribute" , labelNode1.getAttribute()) ;
		
		assertEquals ( "labelNode1 index" , LABELNODE1_INDEX , labelNode1.getIndex() ) ;
		assertEquals ( "model check" , dbModel , labelNode1.getModel()) ;
		assertEquals ( "parent is shapeNode1" , shapeNode1.getIndex() , labelNode1.getParent().getIndex() ) ;
		assertEquals ( "labelNode1 has one shapes", NUM_OF_ANY_LABELNODE_SHAPE_CHILDREN , labelNode1.getSubModel().getNumShapes()) ;
		assertEquals ( "labelNode1 has no labels", NUM_OF_ANY_LABELNODE_LABEL_CHILDREN , labelNode1.getSubModel().getNumLabels()) ;
		assertEquals ( "labelNode1 has no links", NUM_OF_ANY_LABELNODE_LINK_CHILDREN , labelNode1.getSubModel().getNumLinks()) ;
		
		assertEquals ( "labelAttribute1 color" , RGB_101 , labelAttribute1.getBackgroundColor() ) ;
		assertEquals ( "labelAttribute1 position" , LOCATION_51 , labelAttribute1.getLocation()) ;
		assertEquals ( "labelAttribute1 size" , SIZE_51 , labelAttribute1.getSize() ) ;
	}
	
	@Test
	public void testCheckIntegrityOfLabelNode2AndLabelAttribute2 () throws Exception 
	{
		loadObjects() ;
		assertNotNull ( "is not Null" , labelNode2) ;
		assertNotNull ("labelNode1 has SubModel" , labelNode2.getSubModel()) ;
		assertNotNull ( "has attribute" , labelNode2.getAttribute()) ;
		
		assertEquals ( "labelNode2 index" , LABELNODE2_INDEX , labelNode2.getIndex() ) ;
		assertEquals ( "model check" , dbModel , labelNode2.getModel()) ;
		assertEquals ( "parent is rootnode" , dbRootNode.getIndex() , labelNode2.getParent().getIndex() ) ;
		assertEquals ( "labelNode2 has one shapes", NUM_OF_ANY_LABELNODE_SHAPE_CHILDREN , labelNode2.getSubModel().getNumShapes()) ;
		assertEquals ( "labelNode2 has no labels", NUM_OF_ANY_LABELNODE_LABEL_CHILDREN , labelNode2.getSubModel().getNumLabels()) ;
		assertEquals ( "labelNode2 has no links", NUM_OF_ANY_LABELNODE_LINK_CHILDREN , labelNode2.getSubModel().getNumLinks()) ;
		
		assertEquals ( "labelAttribute2 color" , RGB_102 , labelAttribute2.getBackgroundColor() ) ;
		assertEquals ( "labelAttribute2 position" , LOCATION_52 , labelAttribute2.getLocation()) ;
		assertEquals ( "labelAttribute2 size" , SIZE_52 , labelAttribute2.getSize() ) ;
	}
	
	@Test
	public void testCheckIntegrityOfLabelNode3AndLabelAttribute3 () throws Exception 
	{
		loadObjects() ;
		assertNotNull ( "is not Null" , labelNode3) ;
		assertNotNull ("labelNode3 has SubModel" , labelNode3.getSubModel()) ;
		assertNotNull ( "has attribute" , labelNode3.getAttribute()) ;
		
		assertEquals ( "labelNode3 index" , LABELNODE3_INDEX , labelNode3.getIndex() ) ;
		assertEquals ( "model check" , dbModel , labelNode3.getModel()) ;
		assertEquals ( "parent is rootnode" , dbRootNode.getIndex() , labelNode3.getParent().getIndex() ) ;
		assertEquals ( "labelNode2 has one shapes", NUM_OF_ANY_LABELNODE_SHAPE_CHILDREN , labelNode3.getSubModel().getNumShapes()) ;
		assertEquals ( "labelNode2 has no labels", NUM_OF_ANY_LABELNODE_LABEL_CHILDREN , labelNode3.getSubModel().getNumLabels()) ;
		assertEquals ( "labelNode2 has no links", NUM_OF_ANY_LABELNODE_LINK_CHILDREN , labelNode3.getSubModel().getNumLinks()) ;
		
		assertEquals ( "labelAttribute2 color" , RGB_103 , labelAttribute3.getBackgroundColor() ) ;
		assertEquals ( "labelAttribute2 position" , LOCATION_53 , labelAttribute3.getLocation()) ;
		assertEquals ( "labelAttribute2 size" , SIZE_53 , labelAttribute3.getSize() ) ;
	}
	
	@Test
	public void testCheckIntegrityOfLabelNode4AndLabelAttribute4 () throws Exception 
	{
		loadObjects() ;
		assertNotNull ( "is not Null" , labelNode4) ;
		assertNotNull ("labelNode4 has SubModel" , labelNode4.getSubModel()) ;
		assertNotNull ( "has attribute" , labelNode4.getAttribute()) ;
		
		assertEquals ( "labelNode4 index" , LABELNODE4_INDEX , labelNode4.getIndex() ) ;
		assertEquals ( "model check" , dbModel , labelNode4.getModel()) ;
		assertEquals ( "parent is shapeNode2" , shapeNode2.getIndex() , labelNode4.getParent().getIndex() ) ;
		assertEquals ( "labelNode4 has one shapes", NUM_OF_ANY_LABELNODE_SHAPE_CHILDREN , labelNode4.getSubModel().getNumShapes()) ;
		assertEquals ( "labelNode4 has no labels", NUM_OF_ANY_LABELNODE_LABEL_CHILDREN , labelNode4.getSubModel().getNumLabels()) ;
		assertEquals ( "labelNode4 has no links", NUM_OF_ANY_LABELNODE_LINK_CHILDREN , labelNode4.getSubModel().getNumLinks()) ;
		
		assertEquals ( "labelAttribute4 color" , RGB_104 , labelAttribute4.getBackgroundColor() ) ;
		assertEquals ( "labelAttribute4 position" , LOCATION_54 , labelAttribute4.getLocation()) ;
		assertEquals ( "labelAttribute4 size" , SIZE_54 , labelAttribute4.getSize() ) ;
	}
	
	@Test
	public void testCheckIntegrityOfLabelNode5AndLabelAttribute5 () throws Exception 
	{
		loadObjects() ;
		assertNotNull ( "is not Null" , labelNode5) ;
		assertNotNull ("labelNode5 has SubModel" , labelNode5.getSubModel()) ;
		assertNotNull ( "has attribute" , labelNode5.getAttribute()) ;
		
		assertEquals ( "labelNode5 index" , LABELNODE5_INDEX , labelNode5.getIndex() ) ;
		assertEquals ( "model check" , dbModel , labelNode5.getModel()) ;
		assertEquals ( "parent is rootnode" , dbRootNode.getIndex() , labelNode5.getParent().getIndex() ) ;
		assertEquals ( "labelNode5 has one shapes", NUM_OF_ANY_LABELNODE_SHAPE_CHILDREN , labelNode5.getSubModel().getNumShapes()) ;
		assertEquals ( "labelNode5 has no labels", NUM_OF_ANY_LABELNODE_LABEL_CHILDREN , labelNode5.getSubModel().getNumLabels()) ;
		assertEquals ( "labelNode5 has no links", NUM_OF_ANY_LABELNODE_LINK_CHILDREN , labelNode5.getSubModel().getNumLinks()) ;
		
		assertEquals ( "labelAttribute5 color" , RGB_105 , labelAttribute5.getBackgroundColor() ) ;
		assertEquals ( "labelAttribute5 position" , LOCATION_55 , labelAttribute5.getLocation()) ;
		assertEquals ( "labelAttribute5 size" , SIZE_55 , labelAttribute5.getSize() ) ;
	}
	
	@Test
	public void testCheckIntegrityOfLabelNode6AndLabelAttribute6 () throws Exception 
	{
		loadObjects() ;
		assertNotNull ( "is not Null" , labelNode6) ;
		assertNotNull ("labelNode6 has SubModel" , labelNode6.getSubModel()) ;
		assertNotNull ( "has attribute" , labelNode6.getAttribute()) ;
		
		assertEquals ( "labelNode6 index" , LABELNODE6_INDEX , labelNode6.getIndex() ) ;
		assertEquals ( "model check" , dbModel , labelNode6.getModel()) ;
		assertEquals ( "parent is shapeNode6" , shapeNode6.getIndex() , labelNode6.getParent().getIndex() ) ;
		assertEquals ( "labelNode6 has one shapes", NUM_OF_ANY_LABELNODE_SHAPE_CHILDREN , labelNode6.getSubModel().getNumShapes()) ;
		assertEquals ( "labelNode6 has no labels", NUM_OF_ANY_LABELNODE_LABEL_CHILDREN , labelNode6.getSubModel().getNumLabels()) ;
		assertEquals ( "labelNode6 has no links", NUM_OF_ANY_LABELNODE_LINK_CHILDREN , labelNode6.getSubModel().getNumLinks()) ;
		
		assertEquals ( "labelAttribute6 color" , RGB_106 , labelAttribute6.getBackgroundColor() ) ;
		assertEquals ( "labelAttribute6 position" , LOCATION_56 , labelAttribute6.getLocation()) ;
		assertEquals ( "labelAttribute6 size" , SIZE_56 , labelAttribute6.getSize() ) ;
	}
	
	@Test
	public void testCheckIntegrityOfLabelNode7AndLabelAttribute7 () throws Exception 
	{
		loadObjects() ;
		assertNotNull ( "is not Null" , labelNode7) ;
		assertNotNull ("labelNode7 has SubModel" , labelNode7.getSubModel()) ;
		assertNotNull ( "has attribute" , labelNode7.getAttribute()) ;
		
		assertEquals ( "labelNode7 index" , LABELNODE7_INDEX , labelNode7.getIndex() ) ;
		assertEquals ( "model check" , dbModel , labelNode7.getModel()) ;
		assertEquals ( "parent is shapeNode2" , shapeNode2.getIndex() , labelNode7.getParent().getIndex() ) ;
		assertEquals ( "labelNode7 has one shapes", NUM_OF_ANY_LABELNODE_SHAPE_CHILDREN , labelNode7.getSubModel().getNumShapes()) ;
		assertEquals ( "labelNode7 has no labels", NUM_OF_ANY_LABELNODE_LABEL_CHILDREN , labelNode7.getSubModel().getNumLabels()) ;
		assertEquals ( "labelNode7 has no links", NUM_OF_ANY_LABELNODE_LINK_CHILDREN , labelNode7.getSubModel().getNumLinks()) ;
		
		assertEquals ( "labelAttribute7 color" , RGB_107 , labelAttribute7.getBackgroundColor() ) ;
		assertEquals ( "labelAttribute7 position" , LOCATION_57 , labelAttribute7.getLocation()) ;
		assertEquals ( "labelAttribute7 size" , SIZE_57 , labelAttribute7.getSize() ) ;
	}
	
	@Test
	public void testCheckIntegrityOfLabelNode8AndLabelAttribute8 () throws Exception 
	{
		loadObjects() ;
		assertNotNull ( "is not Null" , labelNode8) ;
		assertNotNull ("labelNode3 has SubModel" , labelNode8.getSubModel()) ;
		assertNotNull ( "has attribute" , labelNode8.getAttribute()) ;
		
		assertEquals ( "labelNode8 index" , LABELNODE8_INDEX , labelNode8.getIndex() ) ;
		assertEquals ( "model check" , dbModel , labelNode8.getModel()) ;
		assertEquals ( "parent is shapeNode7" , shapeNode7.getIndex() , labelNode8.getParent().getIndex() ) ;
		assertEquals ( "labelNode8 has one shapes", NUM_OF_ANY_LABELNODE_SHAPE_CHILDREN , labelNode8.getSubModel().getNumShapes()) ;
		assertEquals ( "labelNode8 has no labels", NUM_OF_ANY_LABELNODE_LABEL_CHILDREN , labelNode8.getSubModel().getNumLabels()) ;
		assertEquals ( "labelNode8 has no links", NUM_OF_ANY_LABELNODE_LINK_CHILDREN , labelNode8.getSubModel().getNumLinks()) ;
		
		assertEquals ( "labelAttribute8 color" , RGB_108 , labelAttribute8.getBackgroundColor() ) ;
		assertEquals ( "labelAttribute8 position" , LOCATION_58 , labelAttribute8.getLocation()) ;
		assertEquals ( "labelAttribute8 size" , SIZE_58 , labelAttribute8.getSize() ) ;
	}
	
	@Test
	public void testCheckIntegrityOfLinkEdge1AndLinkAttribute1 () throws Exception
	{
		loadObjects() ;
		assertNotNull ( "is not Null" , linkEdge1) ;
		assertNotNull ( "has attribute" , linkEdge1.getAttribute()) ;
		
		assertEquals ( "link edge index" , LINK_EDGE1_INDEX , linkEdge1.getIndex()) ;
		assertEquals ( "model check" , dbModel , linkEdge1.getModel()) ;
		assertEquals ( "owning subModel" , dbRootNode.getSubModel() , linkEdge1.getOwningSubModel() ) ;
		assertEquals ( "source shape" , shapeNode1 , linkEdge1.getSourceShape() ) ;
		assertEquals ( "target shape" , shapeNode2 , linkEdge1.getTargetShape() ) ;
		
		assertEquals ( "check Canvas" , dbCanvas , linkAttribute1.getCanvas() );
		assertEquals ( "check creation serial" , LINK_ATTRIBUTE1_SERIAL , linkAttribute1.getCreationSerial()) ;
		assertEquals ( "check line color" , RGB_101 , linkAttribute1.getLineColor() ) ;
		assertEquals ( "check linestyle" , LineStyle.SOLID , linkAttribute1.getLineStyle() ) ;
		assertEquals ( "check routertype" , ConnectionRouter.SHORTEST_PATH , linkAttribute1.getRouter() ) ;
		assertEquals ( "check URL" , LINK_ATTR1_URL , linkAttribute1.getUrl()) ;
		assertEquals ( "check name" , LINK_ATTR1_NAME , linkAttribute1.getName()) ;
		assertEquals ( "check desc" , LINK_ATTR1_DESCR , linkAttribute1.getDescription()) ;
		assertEquals ( "check detail desc" , LINK_ATTR1_DET_DESCR , linkAttribute1.getDetailedDescription()) ;
		assertEquals ( "check no of bends" , NUM_OF_BENDPOINTS_IN_LINK1 , linkAttribute1.numBendPoints()) ;
		
	}
	
	@Test
	public void testCheckIntegrityOfLinkEdge2AndLinkAttribute2 () throws Exception
	{
		loadObjects() ;
		assertNotNull ( "is not Null" , linkEdge2) ;
		assertNotNull ( "has attribute" , linkEdge2.getAttribute()) ;
		
		assertEquals ( "link edge index" , LINK_EDGE2_INDEX , linkEdge2.getIndex()) ;
		assertEquals ( "model check" , dbModel , linkEdge2.getModel()) ;
		assertEquals ( "owning subModel" , dbRootNode.getSubModel() , linkEdge2.getOwningSubModel() ) ;
		assertEquals ( "source shape" , shapeNode2 , linkEdge2.getSourceShape() ) ;
		assertEquals ( "target shape" , shapeNode1 , linkEdge2.getTargetShape() ) ;
		
		assertEquals ( "check Canvas" , dbCanvas , linkAttribute2.getCanvas() );
		assertEquals ( "check creation serial" , LINK_ATTRIBUTE2_SERIAL , linkAttribute2.getCreationSerial()) ;
		assertEquals ( "check line color" , RGB_102 , linkAttribute2.getLineColor() ) ;
		assertEquals ( "check linestyle" , LineStyle.DASHED , linkAttribute2.getLineStyle() ) ;
		assertEquals ( "check routertype" , ConnectionRouter.FAN , linkAttribute2.getRouter() ) ;
		assertEquals ( "check URL" , LINK_ATTR2_URL , linkAttribute2.getUrl()) ;
		assertEquals ( "check name" , LINK_ATTR2_NAME , linkAttribute2.getName()) ;
		assertEquals ( "check desc" , LINK_ATTR2_DESCR , linkAttribute2.getDescription()) ;
		assertEquals ( "check detail desc" , LINK_ATTR2_DET_DESCR , linkAttribute2.getDetailedDescription()) ;
		assertEquals ( "check no of bends" , NUM_OF_BENDPOINTS_IN_LINK2 , linkAttribute2.numBendPoints()) ;
	}
	
	@Test
	public void testCheckIntegrityOfLinkEdge3AndLinkAttribute3 () throws Exception
	{
		loadObjects() ;
		assertNotNull ( "is not Null" , linkEdge3) ;
		assertNotNull ( "has attribute" , linkEdge3.getAttribute()) ;
		
		assertEquals ( "link edge index" , LINK_EDGE3_INDEX , linkEdge3.getIndex()) ;
		assertEquals ( "model check" , dbModel , linkEdge3.getModel()) ;
		assertEquals ( "owning subModel" , dbRootNode.getSubModel() , linkEdge3.getOwningSubModel() ) ;
		assertEquals ( "source shape" , shapeNode2 , linkEdge3.getSourceShape() ) ;
		assertEquals ( "target shape" , shapeNode6 , linkEdge3.getTargetShape() ) ;
		
		assertEquals ( "check Canvas" , dbCanvas , linkAttribute3.getCanvas() );
		assertEquals ( "check creation serial" , LINK_ATTRIBUTE3_SERIAL , linkAttribute3.getCreationSerial()) ;
		assertEquals ( "check line color" , RGB_103 , linkAttribute3.getLineColor() ) ;
		assertEquals ( "check linestyle" , LineStyle.DASH_DOT , linkAttribute3.getLineStyle() ) ;
		assertEquals ( "check routertype" , ConnectionRouter.SHORTEST_PATH , linkAttribute3.getRouter() ) ;
		assertEquals ( "check URL" , LINK_ATTR3_URL , linkAttribute3.getUrl()) ;
		assertEquals ( "check name" , LINK_ATTR3_NAME , linkAttribute3.getName()) ;
		assertEquals ( "check desc" , LINK_ATTR3_DESCR , linkAttribute3.getDescription()) ;
		assertEquals ( "check detail desc" , LINK_ATTR3_DET_DESCR , linkAttribute3.getDetailedDescription()) ;
		assertEquals ( "check no of bends" , NUM_OF_BENDPOINTS_IN_LINK3 , linkAttribute3.numBendPoints()) ;
	}
	
	@Test
	public void testCheckIntegrityOfLinkEdge4AndLinkAttribute4 () throws Exception
	{
		loadObjects() ;
		assertNotNull ( "is not Null" , linkEdge4) ;
		assertNotNull ( "has attribute" , linkEdge4.getAttribute()) ;
		
		assertEquals ( "link edge index" , LINK_EDGE4_INDEX , linkEdge4.getIndex()) ;
		assertEquals ( "model check" , dbModel , linkEdge4.getModel()) ;
		assertEquals ( "owning subModel" , dbRootNode.getSubModel() , linkEdge4.getOwningSubModel() ) ;
		assertEquals ( "source shape" , shapeNode2 , linkEdge4.getSourceShape() ) ;
		assertEquals ( "target shape" , shapeNode4 , linkEdge4.getTargetShape() ) ;
		
		assertEquals ( "check Canvas" , dbCanvas , linkAttribute4.getCanvas() );
		assertEquals ( "check creation serial" , LINK_ATTRIBUTE4_SERIAL , linkAttribute4.getCreationSerial()) ;
		assertEquals ( "check line color" , RGB_104 , linkAttribute4.getLineColor() ) ;
		assertEquals ( "check linestyle" , LineStyle.DASH_DOT_DOT , linkAttribute4.getLineStyle() ) ;
		assertEquals ( "check routertype" , ConnectionRouter.FAN , linkAttribute4.getRouter() ) ;
		assertEquals ( "check URL" , LINK_ATTR4_URL , linkAttribute4.getUrl()) ;
		assertEquals ( "check name" , LINK_ATTR4_NAME , linkAttribute4.getName()) ;
		assertEquals ( "check desc" , LINK_ATTR4_DESCR , linkAttribute4.getDescription()) ;
		assertEquals ( "check detail desc" , LINK_ATTR4_DET_DESCR , linkAttribute4.getDetailedDescription()) ;
		assertEquals ( "check no of bends" , NUM_OF_BENDPOINTS_IN_LINK4 , linkAttribute3.numBendPoints()) ;
	}
	
	@Test
	public void testCheckIntegrityOfLinkEdge5AndLinkAttribute5 () throws Exception
	{
		loadObjects() ;
		assertNotNull ( "is not Null" , linkEdge5) ;
		assertNotNull ( "has attribute" , linkEdge5.getAttribute()) ;
		
		assertEquals ( "link edge index" , LINK_EDGE5_INDEX , linkEdge5.getIndex()) ;
		assertEquals ( "model check" , dbModel , linkEdge5.getModel()) ;
		assertEquals ( "owning subModel" , shapeNode2.getSubModel() , linkEdge5.getOwningSubModel() ) ;
		assertEquals ( "source shape" , shapeNode7 , linkEdge5.getSourceShape() ) ;
		assertEquals ( "target shape" , shapeNode6 , linkEdge5.getTargetShape() ) ;
		
		assertEquals ( "check Canvas" , dbCanvas , linkAttribute5.getCanvas() );
		assertEquals ( "check creation serial" , LINK_ATTRIBUTE5_SERIAL , linkAttribute5.getCreationSerial()) ;
		assertEquals ( "check line color" , RGB_105 , linkAttribute5.getLineColor() ) ;
		assertEquals ( "check linestyle" , LineStyle.DOT , linkAttribute5.getLineStyle() ) ;
		assertEquals ( "check routertype" , ConnectionRouter.SHORTEST_PATH , linkAttribute5.getRouter() ) ;
		assertEquals ( "check URL" , LINK_ATTR5_URL , linkAttribute5.getUrl()) ;
		assertEquals ( "check name" , LINK_ATTR5_NAME , linkAttribute5.getName()) ;
		assertEquals ( "check desc" , LINK_ATTR5_DESCR , linkAttribute5.getDescription()) ;
		assertEquals ( "check detail desc" , LINK_ATTR5_DET_DESCR , linkAttribute5.getDetailedDescription()) ;
		assertEquals ( "check no of bends" , NUM_OF_BENDPOINTS_IN_LINK5 , linkAttribute5.numBendPoints()) ;
		
	}
	
	@Test
	public void testCheckIntegrityOfLinkEdge6AndLinkAttribute6 () throws Exception
	{
		loadObjects() ;
		assertNotNull ( "is not Null" , linkEdge6) ;
		assertNotNull ( "has attribute" , linkEdge6.getAttribute()) ;
		
		assertEquals ( "link edge index" , LINK_EDGE6_INDEX , linkEdge6.getIndex()) ;
		assertEquals ( "model check" , dbModel , linkEdge6.getModel()) ;
		assertEquals ( "owning subModel" , shapeNode2.getSubModel() , linkEdge6.getOwningSubModel() ) ;
		assertEquals ( "source shape" , shapeNode6 , linkEdge6.getSourceShape() ) ;
		assertEquals ( "target shape" , shapeNode7 , linkEdge6.getTargetShape() ) ;
		
		assertEquals ( "check Canvas" , dbCanvas , linkAttribute6.getCanvas() );
		assertEquals ( "check creation serial" , LINK_ATTRIBUTE6_SERIAL , linkAttribute6.getCreationSerial()) ;
		assertEquals ( "check line color" , RGB_106 , linkAttribute6.getLineColor() ) ;
		assertEquals ( "check linestyle" , LineStyle.SOLID , linkAttribute6.getLineStyle() ) ;
		assertEquals ( "check routertype" , ConnectionRouter.FAN , linkAttribute6.getRouter() ) ;
		assertEquals ( "check URL" , LINK_ATTR6_URL , linkAttribute6.getUrl()) ;
		assertEquals ( "check name" , LINK_ATTR6_NAME , linkAttribute6.getName()) ;
		assertEquals ( "check desc" , LINK_ATTR6_DESCR , linkAttribute6.getDescription()) ;
		assertEquals ( "check detail desc" , LINK_ATTR6_DET_DESCR , linkAttribute6.getDetailedDescription()) ;
		assertEquals ( "check no of bends" , NUM_OF_BENDPOINTS_IN_LINK6 , linkAttribute6.numBendPoints()) ;
		
	}
	
	@Test
	public void testCheckIntegrityOfLinkEdge7AndLinkAttribute7 () throws Exception
	{
		loadObjects() ;
		assertNotNull ( "is not Null" , linkEdge7) ;
		assertNotNull ( "has attribute" , linkEdge7.getAttribute()) ;
		
		assertEquals ( "link edge index" , LINK_EDGE7_INDEX , linkEdge7.getIndex()) ;
		assertEquals ( "model check" , dbModel , linkEdge7.getModel()) ;
		assertEquals ( "owning subModel" , dbRootNode.getSubModel() , linkEdge7.getOwningSubModel() ) ;
		assertEquals ( "source shape" , shapeNode7 , linkEdge7.getSourceShape() ) ;
		assertEquals ( "target shape" , shapeNode3 , linkEdge7.getTargetShape() ) ;
		
		assertEquals ( "check Canvas" , dbCanvas , linkAttribute7.getCanvas() );
		assertEquals ( "check creation serial" , LINK_ATTRIBUTE7_SERIAL , linkAttribute7.getCreationSerial()) ;
		assertEquals ( "check line color" , RGB_107 , linkAttribute7.getLineColor() ) ;
		assertEquals ( "check linestyle" , LineStyle.DASHED , linkAttribute7.getLineStyle() ) ;
		assertEquals ( "check routertype" , ConnectionRouter.SHORTEST_PATH , linkAttribute7.getRouter() ) ;
		assertEquals ( "check URL" , LINK_ATTR7_URL , linkAttribute7.getUrl()) ;
		assertEquals ( "check name" , LINK_ATTR7_NAME , linkAttribute7.getName()) ;
		assertEquals ( "check desc" , LINK_ATTR7_DESCR , linkAttribute7.getDescription()) ;
		assertEquals ( "check detail desc" , LINK_ATTR7_DET_DESCR , linkAttribute7.getDetailedDescription()) ;
		assertEquals ( "check no of bends" , NUM_OF_BENDPOINTS_IN_LINK7 , linkAttribute7.numBendPoints()) ;
		
	}
	
	@Test
	public void testCheckIntegrityOfLinkEdge8AndLinkAttribute8 () throws Exception
	{
		loadObjects() ;
		assertNotNull ( "is not Null" , linkEdge8) ;
		assertNotNull ( "has attribute" , linkEdge8.getAttribute()) ;
		
		assertEquals ( "link edge index" , LINK_EDGE8_INDEX , linkEdge8.getIndex()) ;
		assertEquals ( "model check" , dbModel , linkEdge8.getModel()) ;
		assertEquals ( "owning subModel" , shapeNode1.getSubModel() , linkEdge8.getOwningSubModel() ) ;
		assertEquals ( "source shape" , shapeNode1 , linkEdge8.getSourceShape() ) ;
		assertEquals ( "target shape" , shapeNode1 , linkEdge8.getTargetShape() ) ;
		
		assertEquals ( "check Canvas" , dbCanvas , linkAttribute8.getCanvas() );
		assertEquals ( "check creation serial" , LINK_ATTRIBUTE8_SERIAL , linkAttribute8.getCreationSerial()) ;
		assertEquals ( "check line color" , RGB_108 , linkAttribute8.getLineColor() ) ;
		assertEquals ( "check linestyle" , LineStyle.DASH_DOT , linkAttribute8.getLineStyle() ) ;
		assertEquals ( "check routertype" , ConnectionRouter.FAN , linkAttribute8.getRouter() ) ;
		assertEquals ( "check URL" , LINK_ATTR8_URL , linkAttribute8.getUrl()) ;
		assertEquals ( "check name" , LINK_ATTR8_NAME , linkAttribute8.getName()) ;
		assertEquals ( "check desc" , LINK_ATTR8_DESCR , linkAttribute8.getDescription()) ;
		assertEquals ( "check detail desc" , LINK_ATTR8_DET_DESCR , linkAttribute8.getDetailedDescription()) ;
		assertEquals ( "check no of bends" , NUM_OF_BENDPOINTS_IN_LINK8 , linkAttribute8.numBendPoints()) ;
		
	}
	
	@Test
	public void testCheckIntegrityOfLinkEdge9AndLinkAttribute9 () throws Exception
	{
		loadObjects() ;
		assertNotNull ( "is not Null" , linkEdge9) ;
		assertNotNull ( "has attribute" , linkEdge9.getAttribute()) ;
		
		assertEquals ( "link edge index" , LINK_EDGE9_INDEX , linkEdge9.getIndex()) ;
		assertEquals ( "model check" , dbModel , linkEdge9.getModel()) ;
		assertEquals ( "owning subModel" , shapeNode1.getSubModel() , linkEdge9.getOwningSubModel() ) ;
		assertEquals ( "source shape" , shapeNode1 , linkEdge9.getSourceShape() ) ;
		assertEquals ( "target shape" , shapeNode1 , linkEdge9.getTargetShape() ) ;
		
		assertEquals ( "check Canvas" , dbCanvas , linkAttribute9.getCanvas() );
		assertEquals ( "check creation serial" , LINK_ATTRIBUTE9_SERIAL , linkAttribute9.getCreationSerial()) ;
		assertEquals ( "check line color" , RGB_109 , linkAttribute9.getLineColor() ) ;
		assertEquals ( "check linestyle" , LineStyle.DASH_DOT_DOT , linkAttribute9.getLineStyle() ) ;
		assertEquals ( "check routertype" , ConnectionRouter.SHORTEST_PATH , linkAttribute9.getRouter() ) ;
		assertEquals ( "check URL" , LINK_ATTR9_URL , linkAttribute9.getUrl()) ;
		assertEquals ( "check name" , LINK_ATTR9_NAME , linkAttribute9.getName()) ;
		assertEquals ( "check desc" , LINK_ATTR9_DESCR , linkAttribute9.getDescription()) ;
		assertEquals ( "check detail desc" , LINK_ATTR9_DET_DESCR , linkAttribute9.getDetailedDescription()) ;
		assertEquals ( "check no of bends" , NUM_OF_BENDPOINTS_IN_LINK9 , linkAttribute9.numBendPoints()) ;
		
	}
	
	@Test
	public void testLoadedProperty1 () throws Exception 
	{
		loadObjects() ;
		assertNotNull ( "is not null " , property1 ) ;
		assertEquals ( "rigth canvas " , dbCanvas , property1.getCanvas() ) ;
		
		assertTrue ( "is text property" , property1 instanceof IPlainTextAnnotationProperty ) ;
		IPlainTextAnnotationProperty theProperty = (IPlainTextAnnotationProperty) property1 ;
		assertEquals ( "value" , PROPERTY1_VALUE, theProperty.getValue()) ;
		assertEquals ( "creation serial" , PROPERTY1_CREATION_SERIAL , theProperty.getCreationSerial()) ;
	}
	
	@Test
	public void testLoadedProperty2 () throws Exception 
	{
		loadObjects() ;
		assertNotNull ( "is not null " , property2 ) ;
		assertEquals ( "rigth canvas " , dbCanvas , property2.getCanvas() ) ;
		
		assertTrue ( "is text property" , property2 instanceof IPlainTextAnnotationProperty ) ;
		IPlainTextAnnotationProperty theProperty = (IPlainTextAnnotationProperty) property2 ;
		assertEquals ( "value" , PROPERTY2_VALUE, theProperty.getValue()) ;
		assertEquals ( "creation serial" , PROPERTY2_CREATION_SERIAL , theProperty.getCreationSerial()) ;
	}
	
	@Test
	public void testLoadedProperty3 () throws Exception 
	{
		loadObjects() ;
		assertNotNull ( "is not null " , property3 ) ;
		assertEquals ( "rigth canvas " , dbCanvas , property3.getCanvas() ) ;
		
		assertTrue ( "is text property" , property3 instanceof IHtmlAnnotationProperty ) ;
		IHtmlAnnotationProperty theProperty = (IHtmlAnnotationProperty) property3 ;
		assertEquals ( "value" , PROPERTY3_VALUE, theProperty.getValue()) ;
		assertEquals ( "creation serial" , PROPERTY3_CREATION_SERIAL , theProperty.getCreationSerial()) ;
	}
	
	@Test
	public void testLoadedProperty4 () throws Exception 
	{
		loadObjects() ;
		assertNotNull ( "is not null " , property4 ) ;
		assertEquals ( "rigth canvas " , dbCanvas , property4.getCanvas() ) ;
		
		assertTrue ( "is text property" , property4 instanceof IHtmlAnnotationProperty ) ;
		IHtmlAnnotationProperty theProperty = (IHtmlAnnotationProperty) property4 ;
		assertEquals ( "value" , PROPERTY4_VALUE, theProperty.getValue()) ;
		assertEquals ( "creation serial" , PROPERTY4_CREATION_SERIAL , theProperty.getCreationSerial()) ;
	}
	
	@Test
	public void testLoadedProperty5 () throws Exception 
	{
		loadObjects() ;
		assertNotNull ( "is not null " , property5 ) ;
		assertEquals ( "rigth canvas " , dbCanvas , property5.getCanvas() ) ;
		
		assertTrue ( "is text property" , property5 instanceof INumberAnnotationProperty ) ;
		INumberAnnotationProperty theProperty = (INumberAnnotationProperty) property5 ;
		assertEquals ( "value" , PROPERTY5_VALUE, theProperty.getValue().intValue()) ;
		assertEquals ( "creation serial" , PROPERTY5_CREATION_SERIAL , theProperty.getCreationSerial()) ;
	}
	
	@Test
	public void testLoadedProperty6 () throws Exception 
	{
		loadObjects() ;
		assertNotNull ( "is not null " , property6 ) ;
		assertEquals ( "rigth canvas " , dbCanvas , property6.getCanvas() ) ;
		
		assertTrue ( "is text property" , property6 instanceof INumberAnnotationProperty ) ;
		INumberAnnotationProperty theProperty = (INumberAnnotationProperty) property6 ;
		assertEquals ( "value" , PROPERTY6_VALUE, theProperty.getValue().intValue()) ;
		assertEquals ( "creation serial" , PROPERTY6_CREATION_SERIAL , property6 .getCreationSerial()) ;
	}
	
	@Test
	public void testLoadedProperty7 () throws Exception 
	{
		loadObjects() ;
		assertNotNull ( "is not null " , property7 ) ;
		assertEquals ( "rigth canvas " , dbCanvas , property7.getCanvas() ) ;
		
		assertTrue ( "is text property" , property7 instanceof IListAnnotationProperty ) ;
		IListAnnotationProperty theProperty = (IListAnnotationProperty) property7 ;
		List <String> listValues = theProperty.getValue() ;
		assertEquals ( "first element" , PROPERTY7_LIST_VALUE1 , listValues.get(0)) ;
		assertEquals ( "second element" , PROPERTY7_LIST_VALUE2 , listValues.get(1)) ;
		assertEquals ( "creation serial" , PROPERTY7_CREATION_SERIAL , property7 .getCreationSerial()) ;
	}
	
	@Test
	public void testLoadedProperty8 () throws Exception 
	{
		loadObjects() ;
		assertNotNull ( "is not null " , property8 ) ;
		assertEquals ( "rigth canvas " , dbCanvas , property8.getCanvas() ) ;
		
		assertTrue ( "is text property" , property8 instanceof IListAnnotationProperty ) ;
		IListAnnotationProperty theProperty = (IListAnnotationProperty) property8 ;
		List <String> listValues =  theProperty.getValue() ;
		assertEquals ( "first element" , PROPERTY8_LIST_VALUE1 , listValues.get(0)) ;
		assertEquals ( "second element" , PROPERTY8_LIST_VALUE2 , listValues.get(1)) ;
		assertEquals ( "creation serial" , PROPERTY8_CREATION_SERIAL , property8 .getCreationSerial()) ;
	}
	
	@Test
	public void testCheckBendPoints () throws Exception
	{
		loadObjects () ;
		assertNotNull ( "is not null" , bendpoint1 ) ;
		assertEquals ( "check Location" , LOCATION_51 , bendpoint1.getLocation() ) ;
		assertEquals ( "check parent" , linkAttribute1 , bendpoint1.getOwningLink()) ;
		
		assertNotNull ( "is not null" , bendpoint2 ) ;
		assertEquals ( "check Location" , LOCATION_52 , bendpoint2.getLocation() ) ;
		assertEquals ( "check parent" , linkAttribute2 , bendpoint2.getOwningLink()) ;

	}
}
	