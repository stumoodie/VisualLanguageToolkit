/*
  Licensed to the Court of the University of Edinburgh (UofE) under one
  or more contributor license agreements.  See the NOTICE file
  distributed with this work for additional information
  regarding copyright ownership.  The UofE licenses this file
  to you under the Apache License, Version 2.0 (the
  "License"); you may not use this file except in compliance
  with the License.  You may obtain a copy of the License at

  http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing,
  software distributed under the License is distributed on an
  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
  KIND, either express or implied.  See the License for the
  specific language governing permissions and limitations
  under the License.
*/

package org.pathwayeditor.businessobjects.exchange;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.FileReader;
import java.io.Reader;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.pathwayeditor.businessobjects.drawingprimitives.ICanvasElementAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.ILabelAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.IModel;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeAttribute;
import org.pathwayeditor.businessobjects.management.INotationSubsystemPool;
import org.pathwayeditor.bussinessobjects.stubs.notationsubsystem.StubNotationSubsystemPool;

import uk.ac.ed.inf.graph.compound.IChildCompoundGraph;
import uk.ac.ed.inf.graph.compound.ICompoundEdge;
import uk.ac.ed.inf.graph.compound.ICompoundNode;
import uk.ac.ed.inf.graph.compound.IRootChildCompoundGraph;


/**
 * @author Stuart Moodie
 *
 */
public class XMLUnMarshallingTest {
	private static final String TEST_FILE_NAME = "Acceptance Test/org/pathwayeditor/businessobjects/exchange/testFixedEdges.xml";
	private INotationSubsystemPool stubNotationPool;
	private ModelUnmarshaller builder;
	private IModel model;

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
		assertEquals("expected num canvas attribs", 26, model.numCanvasAttributes());
		assertEquals("expected num shape attribs", 8, model.numShapeAttributes());
		assertEquals("expected num label attribs", 8, model.numLabelAttributes());
		assertEquals("expected num link attribs", 9, model.numLinkAttributes());
		assertEquals("expected num link attribs", 0, model.numAnchorNodeAttributes());
		assertEquals("expected num edges", 9, model.numLinkEdges());
		assertEquals("expected num nodes", 17, model.numDrawingNodes());
		assertEquals("expected num elements", 26, model.numDrawingElements());
	}
	
	
	@Test
	public void expectedRootChildGraphStats(){
		assertNotNull("att exists", this.model.getRootAttribute());
		assertNotNull("node exists", this.model.getRootAttribute().getCurrentElement());
		IRootChildCompoundGraph rootChild =this.model.getGraph().getRoot().getChildCompoundGraph();
		assertEquals("expected num elements", 6, rootChild.numElements());
		assertEquals("expected num nodes", 2, rootChild.numNodes());
		assertEquals("expected num edges", 4, rootChild.numEdges());
	}
	
	
	@Test
	public void expectedShapeAttribute1ChildGraphStats(){
		IModel rootAtt = model;
		IShapeAttribute shape = (IShapeAttribute)rootAtt.findAttribute(1);
		assertNotNull("att exists", shape);
		ICompoundNode node = shape.getCurrentElement();
		assertNotNull("node exists", node);
		assertEquals("expected parent idx", 0, node.getParent().getIndex());
		IChildCompoundGraph childGraph = node.getChildCompoundGraph();
		assertEquals("expected num elements", 4, childGraph.numElements());
		assertEquals("expected num nodes", 2, childGraph.numNodes());
		assertEquals("expected num edges", 2, childGraph.numEdges());
	}
	
	
	@Test
	public void expectedShapeAttribute2ChildGraphStats(){
		IModel rootAtt = model;
		IShapeAttribute shape = (IShapeAttribute)rootAtt.findAttribute(2);
		assertNotNull("att exists", shape);
		ICompoundNode node = shape.getCurrentElement();
		assertNotNull("node exists", node);
		assertEquals("expected parent idx", 0, node.getParent().getIndex());
		IChildCompoundGraph childGraph = node.getChildCompoundGraph();
		assertEquals("expected num elements", 3, childGraph.numElements());
		assertEquals("expected num nodes", 2, childGraph.numNodes());
		assertEquals("expected num edges", 1, childGraph.numEdges());
	}
	
	
	@Test
	public void expectedLinkAttribute9ChildGraphStats(){
		IModel rootAtt = model;
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
		IModel rootAtt = model;
		ILabelAttribute shape = (ILabelAttribute)rootAtt.findAttribute(20);
		assertNotNull("att exists", shape);
		ICompoundNode node = shape.getCurrentElement();
		assertNotNull("node exists", node);
		assertEquals("expected parent idx", 12, ((ICanvasElementAttribute)node.getParent().getAttribute()).getCreationSerial());
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
