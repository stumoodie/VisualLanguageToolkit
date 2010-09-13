/**
 * 
 */
package org.pathwayeditor.businessobjects.exchange;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.FileReader;
import java.io.Reader;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.pathwayeditor.businessobjects.drawingprimitives.ILabelAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.IRootAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeAttribute;
import org.pathwayeditor.businessobjects.management.INotationSubsystemPool;
import org.pathwayeditor.bussinessobjects.stubs.notationsubsystem.StubNotationSubsystemPool;

import uk.ac.ed.inf.graph.compound.IChildCompoundGraph;
import uk.ac.ed.inf.graph.compound.ICompoundEdge;
import uk.ac.ed.inf.graph.compound.ICompoundGraph;
import uk.ac.ed.inf.graph.compound.ICompoundNode;
import uk.ac.ed.inf.graph.compound.IRootChildCompoundGraph;


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
	private static final String TEST_FILE_NAME = "Acceptance Test/org/pathwayeditor/businessobjects/exchange/testFixedEdges.xml";
//	private static final String SOURCE_FILE = "Acceptance Test/org/pathwayeditor/businessobjects/exchange/DBSourceData.xls";
//	private static final String DELTA_FILE = "Acceptance Test/org/pathwayeditor/businessobjects/exchange/UnmarshalledExpected.xls";
//	private static final String REPOSITORY_NAME ="repo name" ;
//	private static final int MAP_INODE = 5;
//	private IRepository repository;
	private INotationSubsystemPool stubNotationPool;
	private ModelUnmarshaller builder;
	private ICompoundGraph model;

	@Before
	public void setUp() throws Exception{
		Reader in = null;
		try {
			this.stubNotationPool = new StubNotationSubsystemPool();
			builder = new ModelUnmarshaller(this.stubNotationPool);
			in = new FileReader(TEST_FILE_NAME);
			builder.read(in);
			builder.build();
			this.model = builder.getModel();
		} finally {
			if (in != null) {
				in.close();
			}
		}
	}
	
	@After
	public void tearDown(){
		this.stubNotationPool = null;
		this.builder = null;
	}
	
	
	@Test
	public void expectedModelStatistics(){
		IRootAttribute rootAtt = (IRootAttribute)model.getRoot().getAttribute();
		assertEquals("expected num canvas attribs", 23, rootAtt.numCanvasAttributes());
		assertEquals("expected num shape attribs", 8, rootAtt.numShapeAttributes());
		assertEquals("expected num label attribs", 6, rootAtt.numLabelAttributes());
		assertEquals("expected num link attribs", 9, rootAtt.numLinkAttributes());
		assertEquals("expected num elements", 24, model.numElements());
		assertEquals("expected num nodes", 15, model.numNodes());
		assertEquals("expected num edges", 9, model.numEdges());
	}
	
	
	@Test
	public void expectedRootChildGraphStats(){
		assertNotNull("att exists", this.model.getRoot().getAttribute());
		assertNotNull("node exists", this.model.getRoot().getAttribute().getCurrentElement());
		IRootChildCompoundGraph rootChild =this.model.getRoot().getChildCompoundGraph();
		assertEquals("expected num elements", 6, rootChild.numElements());
		assertEquals("expected num nodes", 2, rootChild.numNodes());
		assertEquals("expected num edges", 4, rootChild.numEdges());
	}
	
	
	@Test
	public void expectedShapeAttribute1ChildGraphStats(){
		IRootAttribute rootAtt = (IRootAttribute)model.getRoot().getAttribute();
		IShapeAttribute shape = (IShapeAttribute)rootAtt.findAttribute(1);
		assertNotNull("att exists", shape);
		ICompoundNode node = shape.getCurrentElement();
		assertNotNull("node exists", node);
		assertEquals("expected parent idx", 0, node.getParent().getIndex());
		IChildCompoundGraph childGraph = node.getChildCompoundGraph();
		assertEquals("expected num elements", 5, childGraph.numElements());
		assertEquals("expected num nodes", 3, childGraph.numNodes());
		assertEquals("expected num edges", 2, childGraph.numEdges());
	}
	
	
	@Test
	public void expectedShapeAttribute2ChildGraphStats(){
		IRootAttribute rootAtt = (IRootAttribute)model.getRoot().getAttribute();
		IShapeAttribute shape = (IShapeAttribute)rootAtt.findAttribute(2);
		assertNotNull("att exists", shape);
		ICompoundNode node = shape.getCurrentElement();
		assertNotNull("node exists", node);
		assertEquals("expected parent idx", 0, node.getParent().getIndex());
		IChildCompoundGraph childGraph = node.getChildCompoundGraph();
		assertEquals("expected num elements", 4, childGraph.numElements());
		assertEquals("expected num nodes", 3, childGraph.numNodes());
		assertEquals("expected num edges", 1, childGraph.numEdges());
	}
	
	
	@Test
	public void expectedLinkAttribute9ChildGraphStats(){
		IRootAttribute rootAtt = (IRootAttribute)model.getRoot().getAttribute();
		ILinkAttribute shape = (ILinkAttribute)rootAtt.findAttribute(9);
		assertNotNull("att exists", shape);
		ICompoundEdge node = shape.getCurrentElement();
		assertNotNull("node exists", node);
		assertEquals("expected parent idx", 0, node.getParent().getIndex());
		IChildCompoundGraph childGraph = node.getChildCompoundGraph();
		assertEquals("expected num elements", 1, childGraph.numElements());
		assertEquals("expected num nodes", 1, childGraph.numNodes());
		assertEquals("expected num edges", 0, childGraph.numEdges());
	}
	
	
	@Test
	public void expectedLabelAttribute20ChildGraphStats(){
		IRootAttribute rootAtt = (IRootAttribute)model.getRoot().getAttribute();
		ILabelAttribute shape = (ILabelAttribute)rootAtt.findAttribute(20);
		assertNotNull("att exists", shape);
		ICompoundNode node = shape.getCurrentElement();
		assertNotNull("node exists", node);
		assertEquals("expected parent idx", 14, node.getParent().getIndex());
		IChildCompoundGraph childGraph = node.getChildCompoundGraph();
		assertEquals("expected num elements", 0, childGraph.numElements());
		assertEquals("expected num nodes", 0, childGraph.numNodes());
		assertEquals("expected num edges", 0, childGraph.numEdges());
	}
	
	
//	@Test
//	public void testUnMarshalling() throws Exception{
//		Reader in = null;
//		try {
//			ICompoundGraph model = builder.getModel();
//			IRootAttribute canvas = (IRootAttribute)model.getRoot().getAttribute();
//			assertEquals("expected num canvas attribs", 22, canvas.numCanvasAttributes());
//			assertEquals("expected num elements", 23, model.numElements());
//			assertEquals("expected num nodes", 14, model.numNodes());
//			assertEquals("expected num edges", 9, model.numEdges());
//			assertNotNull("Canvas defined", canvas);
////			ILinkTerminus att = (ILinkTerminus)canvas.findAttribute(29);
////			INumberAnnotationProperty prop = (INumberAnnotationProperty)att.getProperty("NumberProperty");
////			BigDecimal actualNumPropVal = prop.getValue();
////			BigDecimal expectedNumPropVal = new BigDecimal(EXPECTED_PRECISE_NUMBER);
////			assertEquals("Number Precision OK", expectedNumPropVal, actualNumPropVal);
////			this.map2Manager.getCanvas().copyHere(canvas);
////			this.map2Manager.synchronise();
////			compareDatabase(SOURCE_FILE, DELTA_FILE);
//		} finally {
//			if (in != null) {
//				in.close();
//			}
//		}
//	}
	
	
}
