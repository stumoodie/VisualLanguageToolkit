/**
 * 
 */
package org.pathwayeditor.businessobjects.hibernate.pojos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Set;

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
public class HibMapDiagramTest {

	private Mockery mockery = new JUnit4Mockery() {{
		 setImposteriser(ClassImposteriser.INSTANCE);
	}};
	
	private HibMapDiagram testMapDiagram = new HibMapDiagram () ;
	private HibMapDiagram testMapDiagram2 = new HibMapDiagram () ;
	
	private static final String DIAGRAM_NAME1 = "diagram name" ;
	private static final String DIAGRAM_DESCRIPTION1= "diagram description" ;
	private static final String DIAGRAM_NAME2 = "diagram name2" ;
	private static final String DIAGRAM_DESCRIPTION2 = "diagram description2" ;
	
	@Before
	public void setUp() throws Exception {

	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testCopyConstructor () 
	{
		final HibFolder mockFolder = mockery.mock(HibFolder.class , "mockFolder") ;
		mockery.checking( new Expectations () {
			{atLeast(1).of(mockFolder).getRepository();}
			{atLeast(1).of(mockFolder).addMapDiagram(with(any(HibMapDiagram.class)));}	
		});
		testMapDiagram2 = new HibMapDiagram ( mockFolder , DIAGRAM_NAME2 ) ;
		testMapDiagram = new HibMapDiagram ( mockFolder , testMapDiagram2 ) ;
		assertEquals ( "name same with source diagram" , DIAGRAM_NAME2 ,testMapDiagram.getName() ) ;
		assertEquals ( "descr same with source diagram" , "" ,testMapDiagram.getDescription() ) ;
		assertEquals ( "folder same with source diagram" , mockFolder ,testMapDiagram.getFolder() ) ;
	}
	
	@Test
	public void testChangeFolder () throws Exception 
	{
		final HibFolder mockFolder = mockery.mock(HibFolder.class , "mockFolder") ;
		final HibFolder mockFolder2 = mockery.mock(HibFolder.class , "mockFolder2") ;
		final Set<HibMapDiagram> mockMapDiagramSet = mockery.mock( Set.class , "mockMapDiagramSet") ; 
		mockery.checking( new Expectations () {
			{atLeast(1).of(mockFolder).getRepository();}
			{atLeast(1).of(mockFolder).addMapDiagram(with(any(HibMapDiagram.class)));}		
		});
		
		mockery.checking( new Expectations () {{
		
			testMapDiagram = new HibMapDiagram ( mockFolder , DIAGRAM_NAME1 ) ;
			
		mockery.checking( new Expectations () {{
			atLeast(1).of(mockFolder).getMapDiagrams() ; will(returnValue(mockMapDiagramSet));
			
			atLeast(1).of(mockFolder2).getMapDiagrams() ; will(returnValue(mockMapDiagramSet));
			
			atLeast(1).of(mockMapDiagramSet).remove(testMapDiagram); will(returnValue(true));
			atLeast(1).of(mockMapDiagramSet).add(testMapDiagram); will(returnValue(true));
		}});
		}});
		
		
		assertEquals ( "folder is mockFolder" , mockFolder , testMapDiagram.getFolder() ) ;
		
		testMapDiagram.changeFolder(mockFolder2) ;
		assertEquals ("folder is mockFolder2" , mockFolder2 , testMapDiagram.getFolder() ) ;
	}
	
	@Test
	public void testEquals () throws Exception
	{	
		final HibFolder mockFolder = mockery.mock(HibFolder.class , "mockFolder") ;
		mockery.checking( new Expectations () {
		{atLeast(1).of(mockFolder).getRepository();}
		{atLeast(1).of(mockFolder).addMapDiagram(with(any(HibMapDiagram.class)));}	
		});
		
		testMapDiagram = new HibMapDiagram ( mockFolder , DIAGRAM_NAME1 ) ;
		testMapDiagram2 = new HibMapDiagram ( mockFolder , DIAGRAM_NAME2 ) ;
		
		assertTrue ( "Equals with self" ,testMapDiagram.equals(testMapDiagram)) ;
		assertFalse ( "Not equals with null" , testMapDiagram.equals(null)) ;
		assertFalse ( "Not equals with other" , testMapDiagram.equals(testMapDiagram2)) ;
		
		testMapDiagram2.setName(DIAGRAM_NAME1) ;
		assertFalse ("Not equals with other same name" , testMapDiagram.equals(testMapDiagram2)) ;
	}
	
	@Test
	public final void testHashCode() {
		final HibFolder mockFolder = mockery.mock(HibFolder.class , "mockFolder") ;
		mockery.checking( new Expectations () {
			{atLeast(1).of(mockFolder).getRepository();}
			{atLeast(1).of(mockFolder).addMapDiagram(with(any(HibMapDiagram.class)));}		
		});
		
		testMapDiagram = new HibMapDiagram ( mockFolder , DIAGRAM_NAME1 ) ;
		testMapDiagram2 = new HibMapDiagram ( mockFolder , DIAGRAM_NAME2 ) ;
		
		
		assertFalse ( "Hash code not equals." , testMapDiagram.hashCode() == testMapDiagram2.hashCode() ) ;
		testMapDiagram2.setName(DIAGRAM_NAME1) ;
		assertFalse ( "Hash code STILL not equals." , testMapDiagram.hashCode() == testMapDiagram2.hashCode() ) ;
	}
}
