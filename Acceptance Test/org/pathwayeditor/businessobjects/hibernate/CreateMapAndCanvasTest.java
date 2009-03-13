/**
 * 
 */
package org.pathwayeditor.businessobjects.hibernate;

import org.junit.Before;
import org.junit.Test;
import org.pathwayeditor.businessobjects.drawingprimitives.ICanvas;
import org.pathwayeditor.businessobjects.management.IMapPersistenceManager;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationSubsystem;
import org.pathwayeditor.businessobjects.repository.IMap;
import org.pathwayeditor.businessobjects.repository.IRepository;
import org.pathwayeditor.businessobjects.repository.ISubFolder;
import org.pathwayeditor.bussinessobjects.stubs.notationsubsystem.StubNotationSubSystem;
import org.pathwayeditor.testutils.GenericTester;

/**
 * @author ntsorman
 *
 */
public class CreateMapAndCanvasTest extends GenericTester {
	private static final String REPOSITORY_NAME = "repo name";
	private static final String SUBFOLDER1_PATH = "/subfolder1/" ;
	private IRepository repository;
	private ICanvas canvas;
	private IMapPersistenceManager map1Manager;
	

	@Before
	public void setup() {
		canvas=loadCanvasFromDB();
	}

	private ICanvas loadCanvasFromDB() {
		ISubFolder subFolder1 = (ISubFolder) repository.getFolderByPath(SUBFOLDER1_PATH);
		IMap mapDiagram1 = subFolder1.getMapIterator().next();
		map1Manager = this.getRepositoryPersistenceManager().getMapPersistenceManager(mapDiagram1);
		map1Manager.open();
		ICanvas dbCanvas = map1Manager.getCanvas();
		return dbCanvas;
	}
	
	@Test
	public void testCreateAndDeleteMapInOneGo () throws Exception
	{
		INotationSubsystem notation = new StubNotationSubSystem () ;
		ISubFolder subFolder1 = (ISubFolder) repository.getFolderByPath(SUBFOLDER1_PATH);
		IMap mapDiagram2 = subFolder1.createMap("newMap");
		map1Manager = this.getRepositoryPersistenceManager().getMapPersistenceManager(mapDiagram2);
		map1Manager.open();
		map1Manager.createCanvas(notation) ;
		map1Manager.close(true);
		this.getRepositoryPersistenceManager().synchronise();
		
		
		
		if(!map1Manager.isOpen()){
			map1Manager.open();
		}
		map1Manager.deleteCanvas();
		map1Manager.close(true);
		
		
		subFolder1.removeMap(mapDiagram2);
		
		this.getRepositoryPersistenceManager().removeMapFromRepository(mapDiagram2);
		
		this.getRepositoryPersistenceManager().synchronise();
		
		this.compareDatabase("Acceptance Test/DBConsistencyTestValidationData/CreatedAndDeletedMapDiagramAndCanvas.xml");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pathwayeditor.integrationtestutils.GenericTester#doAdditionalSetUp()
	 */
	@Override
	protected void doAdditionalSetUp() {
		repository = this.getRepositoryPersistenceManager().getRepository();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pathwayeditor.integrationtestutils.GenericTester#getDbUnitDataFilePath()
	 */
	@Override
	protected String getDbUnitDataFilePath() {
		return "Acceptance Test/DBConsistencyTestSourceData/DBSourceData.xml";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pathwayeditor.integrationtestutils.GenericTester#getTestRepositoryName()
	 */
	@Override
	protected String getTestRepositoryName() {
		return REPOSITORY_NAME;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pathwayeditor.testutils.GenericTester#doAdditionalTearDown()
	 */
	@Override
	protected void doAdditionalTearDown() {
		;
	}
}

 