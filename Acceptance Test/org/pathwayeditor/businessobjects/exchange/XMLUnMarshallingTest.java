/**
 * 
 */
package org.pathwayeditor.businessobjects.exchange;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.FileReader;
import java.io.Reader;

import org.junit.Test;
import org.pathwayeditor.businessobjects.drawingprimitives.IRootAttribute;
import org.pathwayeditor.businessobjects.management.INotationSubsystemPool;
import org.pathwayeditor.bussinessobjects.stubs.notationsubsystem.StubNotationSubsystemPool;

import uk.ac.ed.inf.graph.compound.ICompoundGraph;


/**
 * @author smoodie
 *
 */
public class XMLUnMarshallingTest {
//	/**
//	 * 
//	 */
//	private static final String EXPECTED_PRECISE_NUMBER = "0.3837248374736e-10";
//	/**
//	 * 
//	 */
	private static final String TEST_FILE_NAME = "Acceptance Test/org/pathwayeditor/businessobjects/exchange/sanitisedTest.xml";
//	private static final String SOURCE_FILE = "Acceptance Test/org/pathwayeditor/businessobjects/exchange/DBSourceData.xls";
//	private static final String DELTA_FILE = "Acceptance Test/org/pathwayeditor/businessobjects/exchange/UnmarshalledExpected.xls";
//	private static final String REPOSITORY_NAME ="repo name" ;
//	private static final int MAP_INODE = 5;
//	private IRepository repository;
	private INotationSubsystemPool stubNotationPool = new StubNotationSubsystemPool();

	
	@Test
	public void testUnMarshalling() throws Exception{
		Reader in = null;
		try {
			CanvasUnmarshaller builder = new CanvasUnmarshaller(this.stubNotationPool);
			in = new FileReader(TEST_FILE_NAME);
			builder.read(in);
			builder.buildCanvas();
			ICompoundGraph model = builder.getCanvas();
			IRootAttribute canvas = (IRootAttribute)model.getRoot().getAttribute();
			assertEquals("expected num canvas attribs", 22, canvas.numCanvasAttributes());
			assertEquals("expected num elements", 23, model.numElements());
			assertEquals("expected num nodes", 14, model.numNodes());
			assertEquals("expected num edges", 9, model.numEdges());
			assertNotNull("Canvas defined", canvas);
//			ILinkTerminus att = (ILinkTerminus)canvas.findAttribute(29);
//			INumberAnnotationProperty prop = (INumberAnnotationProperty)att.getProperty("NumberProperty");
//			BigDecimal actualNumPropVal = prop.getValue();
//			BigDecimal expectedNumPropVal = new BigDecimal(EXPECTED_PRECISE_NUMBER);
//			assertEquals("Number Precision OK", expectedNumPropVal, actualNumPropVal);
//			this.map2Manager.getCanvas().copyHere(canvas);
//			this.map2Manager.synchronise();
//			compareDatabase(SOURCE_FILE, DELTA_FILE);
		} finally {
			if (in != null) {
				in.close();
			}
		}
	}
}
