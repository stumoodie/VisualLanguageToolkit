/**
 * 
 */
package org.pathwayeditor.businessobjects.exchange;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.FileReader;
import java.io.Reader;
import java.math.BigDecimal;

import org.junit.Test;
import org.pathwayeditor.businessobjects.drawingprimitives.ICanvas;
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkTerminus;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.INumberAnnotationProperty;
import org.pathwayeditor.businessobjects.management.IMapPersistenceManager;
import org.pathwayeditor.businessobjects.management.INotationSubsystemPool;
import org.pathwayeditor.businessobjects.repository.IMap;
import org.pathwayeditor.businessobjects.repository.IRepository;
import org.pathwayeditor.bussinessobjects.stubs.notationsubsystem.StubNotation;
import org.pathwayeditor.bussinessobjects.stubs.notationsubsystem.StubNotationSubsystemPool;
import org.pathwayeditor.testutils.GenericAllXlsTester;


/**
 * @author smoodie
 *
 */
public class XMLUnMarshallingTest extends GenericAllXlsTester {
	/**
	 * 
	 */
	private static final String EXPECTED_PRECISE_NUMBER = "0.3837248374736e-10";
	/**
	 * 
	 */
	private static final String TEST_FILE_NAME = "Acceptance Test/org/pathwayeditor/businessobjects/exchange/test.xml";
	private static final String SOURCE_FILE = "Acceptance Test/org/pathwayeditor/businessobjects/exchange/DBSourceData.xls";
	private static final String DELTA_FILE = "Acceptance Test/org/pathwayeditor/businessobjects/exchange/UnmarshalledExpected.xls";
	private static final String REPOSITORY_NAME ="repo name" ;
	private static final int MAP_INODE = 5;
	private IRepository repository;
	private IMapPersistenceManager map2Manager;
	private INotationSubsystemPool stubNotationPool = new StubNotationSubsystemPool();

	@Override
	protected String getDbUnitDataFilePath() {
		return SOURCE_FILE;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.testutils.GenericXlsTester#doAdditionalSetUp()
	 */
	@Override
	protected void doAdditionalSetUp() {
		repository = this.getRepositoryPersistenceManager().getRepository();
		IMap map = (IMap)repository.findRepositoryItemByINode(MAP_INODE);
		
		map2Manager = this.getRepositoryPersistenceManager().getMapPersistenceManager(map);
		map2Manager.open();
		map2Manager.createCanvas(this.stubNotationPool.getSubsystem(new StubNotation()));
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.testutils.GenericXlsTester#doAdditionalTearDown()
	 */
	@Override
	protected void doAdditionalTearDown() {
		this.map2Manager.close(true);
		this.map2Manager =  null;
		this.repository = null;
	}

	@Override
	protected String getTestRepositoryName() {
		return REPOSITORY_NAME ;
	}
	
	
	@Test
	public void testUnMarshalling() throws Exception{
		Reader in = null;
		try {
			CanvasUnmarshaller builder = new CanvasUnmarshaller(this.stubNotationPool, TEST_FILE_NAME, 0);
			in = new FileReader(TEST_FILE_NAME);
			builder.read(in);
			builder.buildCanvas();
			ICanvas canvas = builder.getCanvas();
			assertEquals("expected num canvas attribs", 44, canvas.numCanvasAttributes());
			assertEquals("expected num elements", 26, canvas.getModel().numDrawingElements());
			assertEquals("expected num nodes", 17, canvas.getModel().numDrawingNodes());
			assertEquals("expected num edges", 9, canvas.getModel().numLinkEdges());
			assertNotNull("Canvas defined", canvas);
			ILinkTerminus att = (ILinkTerminus)canvas.findAttribute(29);
			INumberAnnotationProperty prop = (INumberAnnotationProperty)att.getProperty("NumberProperty");
			BigDecimal actualNumPropVal = prop.getValue();
			BigDecimal expectedNumPropVal = new BigDecimal(EXPECTED_PRECISE_NUMBER);
			assertEquals("Number Precision OK", expectedNumPropVal, actualNumPropVal);
			this.map2Manager.getCanvas().copyHere(canvas);
			this.map2Manager.synchronise();
			compareDatabase(SOURCE_FILE, DELTA_FILE);
		} finally {
			if (in != null) {
				in.close();
			}
		}
	}
}
