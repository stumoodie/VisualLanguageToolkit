/**
 * 
 */
package org.pathwayeditor.bussinessobjects.drawingprimitives;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.pathwayeditor.businessobjects.drawingprimitives.ICanvas;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.Size;
import org.pathwayeditor.businessobjects.hibernate.helpers.IHibNotationFactory;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibCanvas;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibFolder;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibMap;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibRepository;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibRootFolder;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationSubsystem;

/**
 * @author ntsorman
 *
 */
@RunWith(JMock.class)
public class ICanvasTest {
	
	private Mockery mockery = new JUnit4Mockery();
	
	private ICanvas canvas ;
	private HibMap mapDiagram ;
	private INotationSubsystem mockContext ;
	private HibFolder folder ;
	
	private static final String MAP_DIAGRAM_NAME = "mapDiagramName" ;
	private static final int GRID_WIDTH = 10;
	private static final int GRID_HEIGHT = 20;
	private static final Size GRID_SIZE = new Size(GRID_WIDTH, GRID_HEIGHT) ;
//	private static final int NEW_GRID_SIZE = 20 ;
	private static final boolean GRID_ENABLED = true ;
	private static final boolean SNAP_TO_GRID_ENABLED = true ;
//	private static final boolean GRID_NOT_ENABLED = false ;
//	private static final boolean SNAP_TO_GRID_NOT_ENABLED = false ;
	private static final int BACKGROUND_RED = 100 ;
	private static final int BACKGROUND_BLUE = 200 ;
	private static final int BACKGROUND_GREEN = 150 ;
	private static final RGB BACKGROUND_COLOR = new RGB(BACKGROUND_RED, BACKGROUND_GREEN, BACKGROUND_BLUE) ;
	private static final int CANVAS_WIDTH = 500;
	private static final int CANVAS_HEIGHT = 600;
	private static final Size CANVAS_SIZE = new Size(CANVAS_WIDTH, CANVAS_HEIGHT);
	private static final int NEW_CANVAS_SIZE = 600 ;
	private static final int NEW_BACKGROUND_COLOR = 100 ;
//	private static final int LESS_THAN_ZERO_SIZE = -10 ;
//	private static final int OTHER_GRID_SIZE = 15 ;
	private static final int EXPECTED_BUILD_NUM = 0;
	private static final String EXPECTED_REPO_NAME = "testRepo";
	private static final String EXPECTED_REPO_DESCN = "test descn";
	
	@Before
	public void setUp() throws Exception {
		mockContext = this.mockery.mock(INotationSubsystem.class, "mockContext");
		this.mockery.checking(new Expectations(){{
			
		}});
		HibRepository repository = new HibRepository(EXPECTED_REPO_NAME, EXPECTED_REPO_DESCN, EXPECTED_BUILD_NUM); 
		folder = new HibRootFolder (repository) ;
		mapDiagram = new HibMap (folder, MAP_DIAGRAM_NAME) ;
		IHibNotationFactory mockNotationSubsystem = this.mockery.mock(IHibNotationFactory.class, "HibNotation"); 
		canvas = new HibCanvas (mapDiagram.getRepository().getName(), mapDiagram.getINode(), mockNotationSubsystem, mockContext);
		this.canvas.setGridSize(GRID_SIZE);
		this.canvas.setGridEnabled(GRID_ENABLED);
		this.canvas.setSnapToGrid(SNAP_TO_GRID_ENABLED);
		this.canvas.setBackgroundColour(BACKGROUND_COLOR);
		this.canvas.setCanvasSize(CANVAS_SIZE);
		this.mockery.assertIsSatisfied();
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Ignore @Test
	public void testCanvasCreated () throws Exception 
	{
		assertEquals ( "Map diagram " , mapDiagram.getRepository().getName() , canvas.getRepositoryName()) ;
		assertEquals ( "Map diagram " , mapDiagram.getINode() , canvas.getINode()) ;
		assertEquals ( "context" , mockContext , canvas.getNotationSubsystem()) ;
		assertEquals ( "grid" , GRID_SIZE , canvas.getGridSize()) ;
		assertEquals ( "backgroundColor" , BACKGROUND_COLOR, canvas.getBackgroundColour()) ;
		assertTrue ( "created " , canvas.getCreated() != null ) ;
		assertTrue ( "modified " , canvas.getModified() != null ) ;
		assertEquals ( "map size" , CANVAS_SIZE , canvas.getCanvasSize()) ;
		
	}
	
	@Ignore @Test
	public void testChangeCanvasSize () throws Exception 
	{
		canvas.setCanvasSize(new Size (NEW_CANVAS_SIZE , NEW_CANVAS_SIZE)) ;
		
		assertEquals ( "height" , NEW_CANVAS_SIZE , ((HibCanvas)canvas).getCanvasHeight() ) ;
		assertEquals ( "width" , NEW_CANVAS_SIZE , ((HibCanvas)canvas).getCanvasWidth() ) ;
	}
	
	@Ignore @Test(expected=IllegalArgumentException.class)
	public void testChangeCanvasSizeToNull () throws Exception
	{
		canvas.setCanvasSize(null) ;
		assertEquals ( "height" , CANVAS_SIZE , ((HibCanvas)canvas).getCanvasHeight() ) ;
		assertEquals ( "width" , CANVAS_SIZE , ((HibCanvas)canvas).getCanvasWidth() ) ;
		
	}
	
	@Ignore @Test
	public void testChangeBackgroundColor () throws Exception
	{
		canvas.setBackgroundColour(new RGB(NEW_BACKGROUND_COLOR,NEW_BACKGROUND_COLOR,NEW_BACKGROUND_COLOR)) ;
		
		assertEquals ( "red" , NEW_BACKGROUND_COLOR , ((HibCanvas)canvas).getBackgroundRed() ) ;
		assertEquals ( "blue" , NEW_BACKGROUND_COLOR , ((HibCanvas)canvas).getBackgroundBlue() ) ;
		assertEquals ( "green" , NEW_BACKGROUND_COLOR , ((HibCanvas)canvas).getBackgroundGreen() ) ;
	}
	
	@Ignore @Test(expected=IllegalArgumentException.class)
	public void testChangeBackgroundColorToNull () throws Exception
	{
		canvas.setBackgroundColour(null) ;
		
		assertEquals ( "red" , BACKGROUND_COLOR , ((HibCanvas)canvas).getBackgroundRed() ) ;
		assertEquals ( "blue" , BACKGROUND_COLOR , ((HibCanvas)canvas).getBackgroundBlue() ) ;
		assertEquals ( "green" , BACKGROUND_COLOR , ((HibCanvas)canvas).getBackgroundGreen() ) ;
	}
	
//	@Test
//	public void testChangeGridSize () throws Exception
//	{
//		canvas.setGrid(NEW_GRID_SIZE, NEW_GRID_SIZE) ;
//		assertEquals ( "height" , NEW_GRID_SIZE , ((HibCanvas)canvas).getGridX() ) ;
//		assertEquals ( "width" , NEW_GRID_SIZE , ((HibCanvas)canvas).getGridY() ) ;
//		
//	}
//	
//	@Test(expected=IllegalArgumentException.class)
//	public void testChangeGridSizeToLessThanZero () throws Exception
//	{
//		canvas.setGrid(LESS_THAN_ZERO_SIZE, LESS_THAN_ZERO_SIZE) ;
//		assertEquals ( "height" , GRID_SIZE , ((HibCanvas)canvas).getGridX() ) ;
//		assertEquals ( "width" , GRID_SIZE , ((HibCanvas)canvas).getGridY() ) ;
//		
//		canvas.setGrid(LESS_THAN_ZERO_SIZE, OTHER_GRID_SIZE) ;
//		assertEquals ( "height" , GRID_SIZE , ((HibCanvas)canvas).getGridX() ) ;
//		assertEquals ( "width" , GRID_SIZE , ((HibCanvas)canvas).getGridY() ) ;
//		
//		canvas.setGrid(OTHER_GRID_SIZE, LESS_THAN_ZERO_SIZE) ;
//		assertEquals ( "height" , GRID_SIZE , ((HibCanvas)canvas).getGridX() ) ;
//		assertEquals ( "width" , GRID_SIZE , ((HibCanvas)canvas).getGridY() ) ;
//	}
//	
//	@Test
//	public void testChangeIsGridEnabled () throws Exception
//	{
//		canvas.setGridOn(GRID_NOT_ENABLED) ;
//		
//		assertFalse ( "grid not Enabled" , canvas.isGridEnabled()) ;
//	}
//	
//	@Test
//	public void testChangeIsSnapToGridEnabled () throws Exception
//	{
//		canvas.setSnapToGrid(SNAP_TO_GRID_NOT_ENABLED) ;
//		
//		assertFalse ( "snap to grid not Enabled" , canvas.isSnapToGridOn()) ;
//	}
}
