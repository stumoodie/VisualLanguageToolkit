/**
 * 
 */
package org.pathwayeditor.businessobjects.hibernate.pojos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Iterator;

import org.junit.Test;
import org.pathwayeditor.businessobjects.drawingprimitives.ICanvas;
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
//		dbCanvas = map1Manager.getCanvas() ;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.testutils.GenericTester#doAdditionalTearDown()
	 */
	@Override
	protected void doAdditionalTearDown() {
		repository = null ;
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
}
