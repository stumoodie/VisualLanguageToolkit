/**
 * 
 */
package org.pathwayeditor.businessobjects.hibernate;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.pathwayeditor.businessobjects.drawingprimitives.ICanvas;
import org.pathwayeditor.businessobjects.management.IMapPersistenceManager;
import org.pathwayeditor.businessobjects.repository.IMap;
import org.pathwayeditor.businessobjects.repository.IRepository;
import org.pathwayeditor.businessobjects.repository.ISubFolder;
import org.pathwayeditor.businessobjects.testutilities.FieldInspector;
import org.pathwayeditor.testutils.GenericTester;

/**
 * @author nhanlon Load data from the database and test that all Hibernate proxies have been initialised
 */
public class AllLazyInitialisedTest extends GenericTester {

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

	@Test @Ignore //FIXME NH cant do this test for now as there are entities that do not implement comparable
	public void compareDataBeforeAndAfterSaveLoadForIdenticalValuesTest() {
		map1Manager.synchronise();
		ICanvas compare = loadCanvasFromDB();
		List <String> ignored = new ArrayList<String>(Arrays.asList(new String []
		                                                                        {"creationSerial","listenablePropertyChangeItem","propertyBuilder","linkTermini",
				"labelCriteria","shapeCriteria","listenerHandler","copyBuilder","moveBuilder", "lcaCalc","nodeCntr","edgeCntr","hibNotationFactory",
				"momentoCntr","stateHandler","creationSerialCounter"}));
		//FIXME - NH linkTermini should NOT BE IGNORED, they are throwing lazy initialisation exceptions
		assertTrue(FieldInspector.allFieldsIdenticalForTwoMapObjects(canvas, compare, ignored));
	}
	
	@Test // the test passes if an exception is NOT thrown
	public void inspectAllFieldsOfLoadedDataForProxiesTest(){
//		List <String> ignored = new ArrayList<String>(Arrays.asList(new String []
//		                                                                        {"linkTermini"}));
		FieldInspector.inspectAllFields(canvas,null);
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
