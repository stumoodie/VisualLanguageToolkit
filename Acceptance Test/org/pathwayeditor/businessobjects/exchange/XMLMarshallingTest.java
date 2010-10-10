/**
 * 
 */
package org.pathwayeditor.businessobjects.exchange;

import static org.junit.Assert.assertTrue;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import org.exolab.castor.xml.MarshalException;
import org.exolab.castor.xml.ValidationException;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.pathwayeditor.businessobjects.drawingprimitives.IRootAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeAttributeFactory;
import org.pathwayeditor.businessobjects.exchange.castor.Canvas;
import org.pathwayeditor.businessobjects.impl.RootAttribute;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationSyntaxService;
import org.pathwayeditor.businessobjects.typedefn.IShapeObjectType;
import org.pathwayeditor.testfixture.NotationSubsystemFixture;

import uk.ac.ed.inf.graph.compound.ICompoundGraph;
import uk.ac.ed.inf.graph.compound.ICompoundNode;
import uk.ac.ed.inf.graph.compound.newimpl.CompoundGraph;


/**
 * @author smoodie
 *
 */
public class XMLMarshallingTest {
	private ICompoundGraph graph;
	private Mockery mockery;

	
	@Before
	public void setUp(){
		this.mockery = new JUnit4Mockery();
		NotationSubsystemFixture notationFixture = new NotationSubsystemFixture(mockery);
		notationFixture.buildFixture();
		INotationSyntaxService syntaxService = notationFixture.getNotationSubsystem().getSyntaxService();
		IRootAttribute rootAtt = new RootAttribute("testModel", syntaxService.getRootObjectType());
		this.graph = new CompoundGraph(rootAtt);
		IShapeObjectType shapeOt = syntaxService.getShapeObjectType(NotationSubsystemFixture.SHAPE_TYPE_A_ID);
		IShapeAttributeFactory shapeAttFact = rootAtt.shapeAttributeFactory();
		ShapeBuilder shapeBuilder = new ShapeBuilder(this.graph.getRoot(), shapeOt);
		shapeBuilder.setName("S1");
		shapeBuilder.build();
		ICompoundNode shape1Node = shapeBuilder.getNode();
		((IShapeAttribute)shape1Node.getAttribute()).getProperty(NotationSubsystemFixture.SHAPE_TYPE_A_PROP_NAME).setDisplayed(true);
		shapeBuilder.setName("S2");
		shapeBuilder.build();
		ICompoundNode shape2Node = shapeBuilder.getNode();
		shapeBuilder.setParent(shape1Node);
		shapeBuilder.setName("S3");
		shapeBuilder.build();
		ICompoundNode shape3Node = shapeBuilder.getNode();
	}
	
	
	@After
	public void tearDown(){
		
	}
	
	
	@Test
	public void testMarshalling() throws IOException, MarshalException, ValidationException{
		Writer out = null;
		try {
			CanvasMarshaller builder = new CanvasMarshaller();
			builder.setCanvas(graph);
			builder.buildCanvas();
			Canvas xmlCanvas = builder.getCanvas();
			xmlCanvas.validate();
			assertTrue("valid XML", xmlCanvas.isValid());
			out = new FileWriter("test.xml");
			builder.write(out);
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}

}
