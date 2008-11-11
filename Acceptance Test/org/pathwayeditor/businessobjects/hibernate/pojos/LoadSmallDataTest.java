/**
 * 
 */
package org.pathwayeditor.businessobjects.hibernate.pojos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.pathwayeditor.businessobjects.drawingprimitives.ICanvas;
import org.pathwayeditor.businessobjects.drawingprimitives.IRootNode;
import org.pathwayeditor.businessobjects.management.IMapContentPersistenceManager;
import org.pathwayeditor.businessobjects.management.PersistenceManagerNotOpenException;
import org.pathwayeditor.businessobjects.repository.IMap;
import org.pathwayeditor.businessobjects.repository.IRepository;
import org.pathwayeditor.businessobjects.repository.IRootFolder;
import org.pathwayeditor.testutils.GenericTester;

/**
 * @author ntsorman
 *
 */
public class LoadSmallDataTest extends GenericTester{
	
	private static final String REPOSITORY_NAME ="repo name" ;
	private static final String ROOT_FOLDER_PATH = "/" ;
	
	private IRepository repository;
	private IRootFolder rootFolder ;
	private IMap mapDiagram ;
	private ICanvas dbCanvas ;
	private IRootNode dbRootNode ;

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

	/* (non-Javadoc)
	 * @see org.pathwayeditor.testutils.GenericTester#doAdditionalTearDown()
	 */
	@Override
	protected void doAdditionalTearDown() {
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.testutils.GenericTester#getDbUnitDataFilePath()
	 */
	@Override
	protected String getDbUnitDataFilePath() {
		return "Acceptance Test/DBConsistencyTestSourceData/DBSourceDataSmall.xml";
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.testutils.GenericTester#getTestRepositoryName()
	 */
	@Override
	protected String getTestRepositoryName() {
		return REPOSITORY_NAME ;
	}
	
	private void loadData ( ) throws Exception
	{
		rootFolder = repository.getRootFolder() ;
		this.mapDiagram = (IMap)repository.findRepositoryItemByPath("/Diagram name");
		
		IMapContentPersistenceManager map1Manager = this.getRepositoryPersistenceManager().openMap(mapDiagram) ;
		map1Manager.loadContent() ;
		dbCanvas = map1Manager.getCanvas() ;
		
		dbRootNode = dbCanvas.getModel().getRootNode() ;
	}
	
	@Test
	public void testCheckRootNode () throws Exception 
	{
		loadData () ;
		assertNotNull ( "root node not null" , dbRootNode) ;
		
		assertEquals ( "2 shapes in" , 2 , dbRootNode.getSubModel().getNumShapes()) ;
		assertEquals ( "1 edges" , 1 , dbRootNode.getSubModel().getNumLinks()) ;
	}

}
