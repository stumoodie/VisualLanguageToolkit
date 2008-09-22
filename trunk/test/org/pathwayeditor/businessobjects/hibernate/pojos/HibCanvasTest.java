/**
 * 
 */
package org.pathwayeditor.businessobjects.hibernate.pojos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.pathwayeditor.businessobjects.contextadapter.INotationSubsystem;

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
//	private final static int NUMERIC_VALUE_TWO = 2;
//	private final static int NUMERIC_VALUE_ZERO = 0;
	private final static String ANOTHER_OBJECT = "another object" ;
	
	@Before
	public void setUp() throws Exception {

	}

	@After
	public void tearDown() throws Exception {
	}

	
	@Test
	public void testEquals () throws Exception 
	{
		final HibMap mockMapDiagram = mockery.mock(HibMap.class , "mockMapDiagram") ;
		final HibMap mockMapDiagram2 = mockery.mock(HibMap.class , "mockMapDiagram2") ;
		final INotationSubsystem mockContext = mockery.mock(INotationSubsystem.class , "mockContext") ;
		
		canvas = new HibCanvas ( mockMapDiagram , mockContext ) ;
		canvas2 = new HibCanvas ( mockMapDiagram2 , mockContext ) ;
		
		assertTrue ( canvas.equals(canvas)) ;
		assertFalse ( canvas.equals(null)) ;
		assertFalse ( canvas.equals(canvas2)) ;
		assertFalse (canvas.equals(ANOTHER_OBJECT)) ;
	}
	
	@Test
	public void testHashCode() throws Exception
	{
		final HibMap mockMapDiagram = mockery.mock(HibMap.class , "mockMapDiagram") ;
		final HibMap mockMapDiagram2 = mockery.mock(HibMap.class , "mockMapDiagram2") ;
		final INotationSubsystem mockContext = mockery.mock(INotationSubsystem.class , "mockContext") ;
		
		canvas = new HibCanvas ( mockMapDiagram , mockContext ) ;
		canvas2 = new HibCanvas ( mockMapDiagram2 , mockContext ) ;
		
		assertEquals ( "same object" , canvas.hashCode() , canvas.hashCode()) ;
		assertFalse ( "other object" , canvas.hashCode() == canvas2.hashCode()) ;
		
		canvas2.setMapDiagram(mockMapDiagram) ;
		
		assertEquals ( "other object same Mapdiagram" , canvas.hashCode() , canvas2.hashCode()) ;
	}
}
