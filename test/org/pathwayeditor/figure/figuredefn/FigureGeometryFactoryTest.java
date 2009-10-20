/**
 * 
 */
package org.pathwayeditor.figure.figuredefn;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.EnumSet;
import java.util.LinkedList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.pathwayeditor.businessobjects.drawingprimitives.ICanvas;
import org.pathwayeditor.businessobjects.drawingprimitives.ILabelNode;
import org.pathwayeditor.businessobjects.drawingprimitives.IModel;
import org.pathwayeditor.businessobjects.drawingprimitives.IRootNode;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeNode;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeNodeFactory;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LineStyle;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationProperty;
import org.pathwayeditor.businessobjects.management.NonPersistentCanvasFactory;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationSubsystem;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationSyntaxService;
import org.pathwayeditor.bussinessobjects.stubs.notationsubsystem.StubNotationSubSystem;
import org.pathwayeditor.bussinessobjects.stubs.notationsubsystem.StubShapeAObjectType;
import org.pathwayeditor.bussinessobjects.stubs.notationsubsystem.StubTextPropertyDefinition;
import org.pathwayeditor.figure.figuredefn.GraphicsInstruction.GraphicsOpCode;
import org.pathwayeditor.figure.figuredefn.IFont.Style;
import org.pathwayeditor.figure.geometry.Envelope;

/**
 * @author smoodie
 *
 */
public class FigureGeometryFactoryTest {
	private IFigureGeometryFactory testInstance;
	private IRootNode expectedRootNode;
	private IShapeNode expectedShapeNode;
	private ILabelNode expectedLabelNode;
//	private GraphicsInstructionList expectedInstList;
	private static final RGB EXPECTED_LINE = new RGB(150, 150, 150);
	private static final RGB EXPECTED_FILL = new RGB(100, 100, 100);
	private static final Envelope EXPECTED_SHAPE_BOUNDS = new Envelope(10, 15, 36, 47);
	private static final Envelope EXPECTED_SHAPE_CHANGE_BOUNDS = new Envelope(123, 266, 21, 998);
	private static final RGB EXPECTED_CHANGED_FILL = new RGB(12, 233, 44);
	private static final RGB EXPECTED_CHANGED_LINE = new RGB(123, 20, 38);
	private static final Envelope EXPECTED_ROOT_BOUNDS = new Envelope(-10, -34, 58, 294837);
	private static final Envelope EXPECTED_LABEL_BOUNDS = new Envelope(0, 34, 58, 29);
	private static final Envelope EXPECTED_ROOT_ALT_BOUNDS = new Envelope(-10, -34, 580, 294837);
	private static final Envelope EXPECTED_LABEL_ALT_BOUNDS = new Envelope(0, 345, 58, 290);

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		NonPersistentCanvasFactory canvasFactory = NonPersistentCanvasFactory.getInstance();
		INotationSubsystem notationSubsystem = new StubNotationSubSystem();
		INotationSyntaxService syntaxService = notationSubsystem.getSyntaxService();
		canvasFactory.setCanvasName("Test");
		canvasFactory.setNotationSubsystem(notationSubsystem);
		ICanvas canvas = canvasFactory.createNewCanvas();
		IModel model = canvas.getModel();
		this.expectedRootNode = model.getRootNode();
		this.expectedRootNode.getAttribute().setBounds(EXPECTED_ROOT_BOUNDS);
		IShapeNodeFactory shapeFactory = model.getRootNode().getSubModel().shapeNodeFactory();
		shapeFactory.setObjectType(syntaxService.getShapeObjectType(StubShapeAObjectType.UNIQUE_ID));
		this.expectedShapeNode = shapeFactory.createShapeNode();
		this.expectedShapeNode.getAttribute().setBounds(EXPECTED_SHAPE_BOUNDS);
		IAnnotationProperty prop = this.expectedShapeNode.getAttribute().getProperty(StubTextPropertyDefinition.NAME);
		prop.setDisplayed(true);
		this.expectedLabelNode = prop.getLabel().getCurrentDrawingElement();
		this.expectedLabelNode.getAttribute().setBounds(EXPECTED_LABEL_BOUNDS);
		
		this.testInstance = new FigureGeometryFactory(model);
	}

	private GraphicsInstructionList createGraphicsList(RGB lineColour, RGB fillColour, Envelope bounds){
		List<Double> boundsList = new LinkedList<Double>();
		boundsList.add(bounds.getOrigin().getX());
		boundsList.add(bounds.getOrigin().getY());
		boundsList.add(bounds.getDimension().getWidth());
		boundsList.add(bounds.getDimension().getHeight());
		Object[][] defnArr = {
				{ GraphicsOpCode.LINE_WIDTH, Double.valueOf(1.0) },	
				{ GraphicsOpCode.LINE_STYLE, LineStyle.DASH_DOT },	
				{ GraphicsOpCode.FILL_COLOUR, fillColour },	
				{ GraphicsOpCode.LINE_COLOUR, lineColour },	
				{ GraphicsOpCode.FONT_SIZE, Double.valueOf(12.0) },	
				{ GraphicsOpCode.FONT_STYLE, EnumSet.of(Style.NORMAL) },	
				{ GraphicsOpCode.FILL_OVAL, boundsList },	
				{ GraphicsOpCode.DRAW_OVAL, boundsList },	
		};
		List<GraphicsInstruction> insts = new LinkedList<GraphicsInstruction>();
		for(Object[] defn : defnArr){
			insts.add(new GraphicsInstruction((GraphicsOpCode)defn[0], defn[1]));
		}
		return new GraphicsInstructionList(insts);
	}
	
	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link org.pathwayeditor.figure.figuredefn.FigureGeometryFactory#getFigureController(org.pathwayeditor.businessobjects.drawingprimitives.IShapeNode)}.
	 */
	@Test
	public void testGetFigureController() {
		IFigureController expectedController = this.testInstance.getFigureController(expectedShapeNode);
		assertNotNull("figure controller defined", expectedController);
		GraphicsInstructionList expectedInstList = createGraphicsList(EXPECTED_LINE, EXPECTED_FILL, EXPECTED_SHAPE_BOUNDS);
		expectedController.generateFigureDefinition();
		assertEquals("expected figure defn", expectedInstList, expectedController.getFigureDefinition());
		expectedController.setRequestedEnvelope(EXPECTED_SHAPE_CHANGE_BOUNDS);
		expectedController.setLineColour(EXPECTED_CHANGED_LINE);
		expectedController.setFillColour(EXPECTED_CHANGED_FILL);
		assertEquals("expected no figure regeneration", expectedInstList, expectedController.getFigureDefinition());
		expectedController.generateFigureDefinition();
		expectedInstList = createGraphicsList(EXPECTED_CHANGED_LINE, EXPECTED_CHANGED_FILL, EXPECTED_SHAPE_CHANGE_BOUNDS);
		assertEquals("expected figure defn change", expectedInstList, expectedController.getFigureDefinition());
	}

	@Test
	public void testGetFigureControllerChangeAttributes() {
		IFigureController expectedController = this.testInstance.getFigureController(expectedShapeNode);
		assertNotNull("figure controller defined", expectedController);

		GraphicsInstructionList expectedInstList = createGraphicsList(EXPECTED_LINE, EXPECTED_FILL, EXPECTED_SHAPE_BOUNDS);
		assertEquals("expected figure defn", expectedInstList, expectedController.getFigureDefinition());
		
		IShapeAttribute attribute = this.expectedShapeNode.getAttribute();
		attribute.setBounds(EXPECTED_SHAPE_CHANGE_BOUNDS);
		expectedInstList = createGraphicsList(EXPECTED_LINE, EXPECTED_FILL, EXPECTED_SHAPE_CHANGE_BOUNDS);
		assertEquals("expected figure regeneration", expectedInstList, expectedController.getFigureDefinition());
		
		attribute.setLineColour(EXPECTED_CHANGED_LINE);
		expectedInstList = createGraphicsList(EXPECTED_CHANGED_LINE, EXPECTED_FILL, EXPECTED_SHAPE_CHANGE_BOUNDS);
		assertEquals("expected figure regeneration", expectedInstList, expectedController.getFigureDefinition());

		attribute.setFillColour(EXPECTED_CHANGED_FILL);
		expectedInstList = createGraphicsList(EXPECTED_CHANGED_LINE, EXPECTED_CHANGED_FILL, EXPECTED_SHAPE_CHANGE_BOUNDS);
		assertEquals("expected figure regeneration", expectedInstList, expectedController.getFigureDefinition());
	}

	/**
	 * Test method for {@link org.pathwayeditor.figure.figuredefn.FigureGeometryFactory#getConvexHull(org.pathwayeditor.businessobjects.drawingprimitives.IDrawingNode)}.
	 */
	@Test
	public void testGetConvexHull() {
		assertEquals("expected root node", EXPECTED_ROOT_BOUNDS, this.testInstance.getConvexHull(expectedRootNode).getEnvelope());
		assertEquals("expected label node", EXPECTED_LABEL_BOUNDS, this.testInstance.getConvexHull(expectedLabelNode).getEnvelope());
		assertEquals("expected shape node", EXPECTED_SHAPE_BOUNDS, this.testInstance.getConvexHull(expectedShapeNode).getEnvelope());
		expectedRootNode.getAttribute().setBounds(EXPECTED_ROOT_ALT_BOUNDS);
		assertEquals("expected root node changed", EXPECTED_ROOT_ALT_BOUNDS, this.testInstance.getConvexHull(expectedRootNode).getEnvelope());
		expectedLabelNode.getAttribute().setBounds(EXPECTED_LABEL_ALT_BOUNDS);
		assertEquals("expected label node changed", EXPECTED_LABEL_ALT_BOUNDS, this.testInstance.getConvexHull(expectedLabelNode).getEnvelope());
		expectedShapeNode.getAttribute().setBounds(EXPECTED_SHAPE_CHANGE_BOUNDS);
		assertEquals("expected shape node changed", EXPECTED_SHAPE_CHANGE_BOUNDS, this.testInstance.getConvexHull(expectedShapeNode).getEnvelope());
	}

}
