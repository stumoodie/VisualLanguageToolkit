/**
 * 
 */
package org.pathwayeditor.businessobjects.hibernate.pojos;

import static org.junit.Assert.assertEquals;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.pathwayeditor.businessobjects.pojos.Canvas;

/**
 * @author ntsorman
 *
 */
@RunWith(JMock.class)
public class HibCanvasTest {
	
	private Mockery mockery = new JUnit4Mockery() {{
		 setImposteriser(ClassImposteriser.INSTANCE);
	}};
	
	private HibCanvas canvas ;
	private final static int NUMERIC_VALUE_TWO = 2;
	
	@Before
	public void setUp() throws Exception {

	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void addCanvasToMapDiagramTest () throws Exception 
	{
		final HibMapDiagram mockMapDiagram = mockery.mock(HibMapDiagram.class) ;
		final HibContext mockContext = mockery.mock(HibContext.class) ;
		final Canvas mockBoCanvas = mockery.mock(Canvas.class) ;
		
		canvas = new HibCanvas ( mockBoCanvas , mockMapDiagram , mockContext ) ;
		
		canvas.changeMapDiagram(mockMapDiagram) ;
		
		assertEquals ( "map Diagram" , mockMapDiagram , canvas.getMapDiagram()) ;
	}
	
	@Test
	public void addShapeToCanvasTest () throws Exception
	{
		final HibMapDiagram mockMapDiagram = mockery.mock(HibMapDiagram.class , "mockMapDiagram") ;
		final HibContext mockContext = mockery.mock(HibContext.class , "mockContext") ;
		final Canvas mockBoCanvas = mockery.mock(Canvas.class , "mockBoCanvas" ) ;
		final HibShape mockShape = mockery.mock(HibShape.class , "mockShape" ) ;
		final HibShape mockShape2 = mockery.mock(HibShape.class, "mockShape2") ;
		
		canvas = new HibCanvas ( mockBoCanvas , mockMapDiagram , mockContext ) ;
		
		mockery.checking( new Expectations () {{
			atLeast(1).of(mockShape).getCanvas() ; will(returnValue(null));
			atLeast(1).of(mockShape).setCanvas(canvas) ; 
			
			atLeast(1).of(mockShape2).getCanvas() ; will(returnValue(null));
			atLeast(1).of(mockShape2).setCanvas(canvas) ; 
		}});
		
		
		canvas.addShape(mockShape) ;
		canvas.addShape(mockShape2) ;
		
		assertEquals ( "shapes in canvas" , NUMERIC_VALUE_TWO , canvas.getShapes().size() ) ;
	}

}
