/**
 * 
 */
package org.pathwayeditor.bussinessobjects.drawingprimitives;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.pathwayeditor.businessobjects.drawingprimitives.ICanvas;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.Size;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibCanvas;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibContext;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibFolder;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibMapDiagram;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibRootFolder;

/**
 * @author ntsorman
 *
 */
@RunWith(JMock.class)
public class ICanvasTest {
	
	private Mockery mockery = new JUnit4Mockery() {{
		 setImposteriser(ClassImposteriser.INSTANCE);
	}};
	
	private ICanvas canvas ;
	private HibMapDiagram mapDiagram ;
	private HibContext context ;
	private HibFolder folder ;
	
	private static final String MAP_DIAGRAM_NAME = "mapDiagramName" ;
	private static final int GRID_WIDTH = 10;
	private static final int GRID_HEIGHT = 20;
	private static final Size GRID_SIZE = new Size(GRID_WIDTH, GRID_HEIGHT) ;
	private static final int NEW_GRID_SIZE = 20 ;
	private static final boolean GRID_ENABLED = true ;
	private static final boolean SNAP_TO_GRID_ENABLED = true ;
	private static final boolean GRID_NOT_ENABLED = false ;
	private static final boolean SNAP_TO_GRID_NOT_ENABLED = false ;
	private static final int BACKGROUND_COLOR = 100 ;
	private static final int CANVAS_SIZE = 500 ;
	private static final int NEW_CANVAS_SIZE = 600 ;
	private static final int NEW_BACKGROUND_COLOR = 100 ;
	private static final int LESS_THAN_ZERO_SIZE = -10 ;
	private static final int OTHER_GRID_SIZE = 15 ;
	
	@Before
	public void setUp() throws Exception {
		folder = new HibRootFolder () ;
		
		mapDiagram = new HibMapDiagram (folder, MAP_DIAGRAM_NAME) ;
		context = new HibContext () ;
		
		canvas = new HibCanvas (  mapDiagram ,  context, GRID_SIZE , GRID_SIZE , GRID_ENABLED , 
				SNAP_TO_GRID_ENABLED , BACKGROUND_COLOR , BACKGROUND_COLOR , BACKGROUND_COLOR ,
				CANVAS_SIZE , CANVAS_SIZE) ;
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testCanvasCreated () throws Exception 
	{
		assertEquals ( "Map diagram " , mapDiagram , canvas.getOwningMap()) ;
		assertEquals ( "context" , context , canvas.getContext()) ;
		assertEquals ( "grid" , GRID_SIZE , canvas.getGridSize()) ;
		assertEquals ( "backgroundColor" , new RGB (BACKGROUND_COLOR , BACKGROUND_COLOR , BACKGROUND_COLOR) , canvas.getBackgroundColour()) ;
		assertTrue ( "created " , canvas.getCreated() != null ) ;
		assertTrue ( "modified " , canvas.getModified() != null ) ;
		assertEquals ( "map size" , new Size ( CANVAS_SIZE ,CANVAS_SIZE) , canvas.getCanvasSize()) ;
		
	}
	
	@Test
	public void testChangeCanvasSize () throws Exception 
	{
		canvas.setCanvasSize(new Size (NEW_CANVAS_SIZE , NEW_CANVAS_SIZE)) ;
		
		assertEquals ( "height" , NEW_CANVAS_SIZE , ((HibCanvas)canvas).getCanvasHeight() ) ;
		assertEquals ( "width" , NEW_CANVAS_SIZE , ((HibCanvas)canvas).getCanvasWidth() ) ;
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testChangeCanvasSizeToNull () throws Exception
	{
		canvas.setCanvasSize(null) ;
		assertEquals ( "height" , CANVAS_SIZE , ((HibCanvas)canvas).getCanvasHeight() ) ;
		assertEquals ( "width" , CANVAS_SIZE , ((HibCanvas)canvas).getCanvasWidth() ) ;
		
	}
	
	@Test
	public void testChangeBackgroundColor () throws Exception
	{
		canvas.setBackgroundColour(new RGB(NEW_BACKGROUND_COLOR,NEW_BACKGROUND_COLOR,NEW_BACKGROUND_COLOR)) ;
		
		assertEquals ( "red" , NEW_BACKGROUND_COLOR , ((HibCanvas)canvas).getBackgroundRed() ) ;
		assertEquals ( "blue" , NEW_BACKGROUND_COLOR , ((HibCanvas)canvas).getBackgroundBlue() ) ;
		assertEquals ( "green" , NEW_BACKGROUND_COLOR , ((HibCanvas)canvas).getBackgroundGreen() ) ;
	}
	
	@Test(expected=IllegalArgumentException.class)
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
