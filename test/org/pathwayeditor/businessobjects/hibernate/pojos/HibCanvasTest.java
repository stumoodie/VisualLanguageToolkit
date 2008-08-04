/**
 * 
 */
package org.pathwayeditor.businessobjects.hibernate.pojos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

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
	private HibCanvas canvas2 ;
	private final static int NUMERIC_VALUE_TWO = 2;
	private final static int NUMERIC_VALUE_ZERO = 0;
	private final static String ANOTHER_OBJECT = "another object" ;
	
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
		
		canvas = new HibCanvas ( mockMapDiagram , mockContext ) ;
		
		canvas.changeMapDiagram(mockMapDiagram) ;
		
		assertEquals ( "map Diagram" , mockMapDiagram , canvas.getMapDiagram()) ;
	}
	
	@Test
	public void testAddAndRemoveShapeToCanvas () throws Exception
	{
		final HibMapDiagram mockMapDiagram = mockery.mock(HibMapDiagram.class , "mockMapDiagram") ;
		final HibContext mockContext = mockery.mock(HibContext.class , "mockContext") ;
		final HibShape mockShape = mockery.mock(HibShape.class , "mockShape" ) ;
		final HibShape mockShape2 = mockery.mock(HibShape.class, "mockShape2") ;
		
		canvas = new HibCanvas ( mockMapDiagram , mockContext ) ;
		
		mockery.checking( new Expectations () {{
			one(mockShape).getCanvas() ; will(returnValue(null));
			one(mockShape).getCanvas() ; will(returnValue(canvas));
			one(mockShape).setCanvas(canvas) ;
			one(mockShape).setCanvas(null) ;
			
			one(mockShape2).getCanvas() ; will(returnValue(null));
			one(mockShape2).getCanvas() ; will(returnValue(canvas));
			one(mockShape2).setCanvas(canvas) ;
			one(mockShape2).setCanvas(null) ;
		}});
		
		
		canvas.addShape(mockShape) ;
		canvas.addShape(mockShape2) ;
		
		assertEquals ( "shapes in canvas" , NUMERIC_VALUE_TWO , canvas.getShapes().size() ) ;
		
		canvas.removeShape(mockShape) ;
		canvas.removeShape(mockShape2) ;
		
		assertEquals ( "no shapes in canvas" , NUMERIC_VALUE_ZERO , canvas.getShapes().size() ) ;
	}
	
	@Test
	public void testAddLabelToCanvas () throws Exception
	{
		final HibMapDiagram mockMapDiagram = mockery.mock(HibMapDiagram.class , "mockMapDiagram") ;
		final HibContext mockContext = mockery.mock(HibContext.class , "mockContext") ;
		final HibLabel mockLabel = mockery.mock(HibLabel.class , "mockLabel") ;
		final HibLabel mockLabel2 = mockery.mock(HibLabel.class , "mockLabel2") ;
		
		canvas = new HibCanvas ( mockMapDiagram , mockContext ) ;
		
		mockery.checking( new Expectations () {{
			one(mockLabel).getCanvas() ; will(returnValue(null));
			one(mockLabel).getCanvas() ; will(returnValue(canvas));
			one(mockLabel).setCanvas(canvas) ;
			one(mockLabel).setCanvas(null) ;
			
			one(mockLabel2).getCanvas() ; will(returnValue(null));
			one(mockLabel2).getCanvas() ; will(returnValue(canvas));
			one(mockLabel2).setCanvas(canvas) ;
			one(mockLabel2).setCanvas(null) ;
		}});
		
		canvas.addLabel(mockLabel) ;
		canvas.addLabel(mockLabel2) ;
		
		assertEquals ( "labels in Canvas" , NUMERIC_VALUE_TWO , canvas.getLabels().size()) ;
		
		canvas.removeLabel(mockLabel) ;
		canvas.removeLabel(mockLabel2) ;
		
		assertEquals ( "no labels in Canvas" , NUMERIC_VALUE_ZERO , canvas.getLabels().size()) ;
	}
	
	@Test
	public void testAddLinksToCanvas () throws Exception
	{
		final HibMapDiagram mockMapDiagram = mockery.mock(HibMapDiagram.class , "mockMapDiagram") ;
		final HibContext mockContext = mockery.mock(HibContext.class , "mockContext") ;
		final HibLink mockLink = mockery.mock(HibLink.class , "mockLink") ;
		final HibLink mockLink2 = mockery.mock(HibLink.class , "mockLink2") ;
		
		canvas = new HibCanvas ( mockMapDiagram , mockContext ) ;
		
		mockery.checking( new Expectations () {{
			one(mockLink).getCanvas() ; will(returnValue(null));
			one(mockLink).getCanvas() ; will(returnValue(canvas));
			one(mockLink).setCanvas(canvas) ;
			one(mockLink).setCanvas(null) ;
			
			one(mockLink2).getCanvas() ; will(returnValue(null));
			one(mockLink2).getCanvas() ; will(returnValue(canvas));
			one(mockLink2).setCanvas(canvas) ;
			one(mockLink2).setCanvas(null) ;
		}});
		
		canvas.addLink(mockLink) ;
		canvas.addLink(mockLink2) ;
		
		assertEquals ( "links in Canvas" , NUMERIC_VALUE_TWO , canvas.getLinks().size()) ;
		
		canvas.removeLink(mockLink) ;
		canvas.removeLink(mockLink2) ;
		
		assertEquals ( "no links in Canvas" , NUMERIC_VALUE_ZERO , canvas.getLinks().size()) ;
	}
	
	@Test
	public void testAddPropertiesToCanvas () throws Exception
	{
		final HibMapDiagram mockMapDiagram = mockery.mock(HibMapDiagram.class , "mockMapDiagram") ;
		final HibContext mockContext = mockery.mock(HibContext.class , "mockContext") ;
		final HibProperty mockProperty = mockery.mock(HibProperty.class , "mockProperty") ;
		final HibProperty mockProperty2 = mockery.mock(HibProperty.class , "mockProperty2") ;
		
		canvas = new HibCanvas ( mockMapDiagram , mockContext ) ;
		
		mockery.checking( new Expectations () {{
			one(mockProperty).getCanvas() ; will(returnValue(null));
			one(mockProperty).getCanvas() ; will(returnValue(canvas));
			one(mockProperty).setCanvas(canvas) ;
			one(mockProperty).setCanvas(null) ;
			
			one(mockProperty2).getCanvas() ; will(returnValue(null));
			one(mockProperty2).getCanvas() ; will(returnValue(canvas));
			one(mockProperty2).setCanvas(canvas) ;
			one(mockProperty2).setCanvas(null) ;
		}});
		
		canvas.addProperty(mockProperty) ;
		canvas.addProperty(mockProperty2) ;
		
		assertEquals ( "Properties in Canvas" , NUMERIC_VALUE_TWO , canvas.getProperties().size()) ;
		
		canvas.removeProperty(mockProperty) ;
		canvas.removeProperty(mockProperty2) ;
		
		assertEquals ( "no properties in Canvas" , NUMERIC_VALUE_ZERO , canvas.getProperties().size()) ;
	}
	
	@Test
	public void testEquals () throws Exception 
	{
		final HibMapDiagram mockMapDiagram = mockery.mock(HibMapDiagram.class , "mockMapDiagram") ;
		final HibMapDiagram mockMapDiagram2 = mockery.mock(HibMapDiagram.class , "mockMapDiagram2") ;
		final HibContext mockContext = mockery.mock(HibContext.class , "mockContext") ;
		
		canvas = new HibCanvas ( mockMapDiagram , mockContext ) ;
		canvas2 = new HibCanvas ( mockMapDiagram2 , mockContext ) ;
		
		assertTrue ( canvas.equals(canvas)) ;
		assertFalse ( canvas.equals(null)) ;
		assertFalse ( canvas.equals(canvas2)) ;
		assertFalse (canvas.equals(ANOTHER_OBJECT)) ;
		
		canvas2.changeMapDiagram(mockMapDiagram) ;
		assertTrue (canvas.equals(canvas2)) ;
	}
	
	@Test
	public void testHashCode() throws Exception
	{
		final HibMapDiagram mockMapDiagram = mockery.mock(HibMapDiagram.class , "mockMapDiagram") ;
		final HibMapDiagram mockMapDiagram2 = mockery.mock(HibMapDiagram.class , "mockMapDiagram2") ;
		final HibContext mockContext = mockery.mock(HibContext.class , "mockContext") ;
		
		canvas = new HibCanvas ( mockMapDiagram , mockContext ) ;
		canvas2 = new HibCanvas ( mockMapDiagram2 , mockContext ) ;
		
		assertEquals ( "same object" , canvas.hashCode() , canvas.hashCode()) ;
		assertFalse ( "other object" , canvas.hashCode() == canvas2.hashCode()) ;
		
		canvas2.setMapDiagram(mockMapDiagram) ;
		
		assertEquals ( "other object same Mapdiagram" , canvas.hashCode() , canvas2.hashCode()) ;
	}
}
