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

import uk.ed.inf.graph.util.IndexCounter;

/**
 * @author ntsorman
 *
 */
@RunWith(JMock.class)
public class HibMapTest {

	private Mockery mockery = new JUnit4Mockery() {{
		 setImposteriser(ClassImposteriser.INSTANCE);
	}};
	
	private HibMap testMapDiagram = null;
	private HibMap testMapDiagram2 = null;
	private HibFolder mockFolder;
	private HibRepository mockRepository;
	private static final String DIAGRAM_NAME1 = "diagram name" ;
//	private static final String DIAGRAM_DESCRIPTION1= "diagram description" ;
	private static final String DIAGRAM_NAME2 = "diagram name2" ;
//	private static final String DIAGRAM_DESCRIPTION2 = "diagram description2" ;
	
	@Before
	public void setUp() throws Exception {
		final IndexCounter cntr = new IndexCounter();
		mockFolder = mockery.mock(HibFolder.class , "mockFolder") ;
		mockRepository = mockery.mock(HibRepository.class, "mockRepository");
		mockery.checking( new Expectations () {{
			allowing(mockFolder).getRepository(); will(returnValue(mockRepository));
			
			allowing(mockRepository).getINodeCounter(); will(returnValue(cntr));
			
		}});
		testMapDiagram2 = new HibMap ( mockFolder , DIAGRAM_NAME2 ) ;
		testMapDiagram = new HibMap ( mockFolder , testMapDiagram2 ) ;
		this.mockery.assertIsSatisfied();
	}

	@After
	public void tearDown() throws Exception {
		this.mockFolder = null;
		this.testMapDiagram = null;
		this.testMapDiagram2 = null;
	}
	
	@Test
	public void testCopyConstructor () 
	{
		assertEquals ( "name same with source diagram" , DIAGRAM_NAME2 ,testMapDiagram.getName() ) ;
		assertEquals ( "descr same with source diagram" , "" ,testMapDiagram.getDescription() ) ;
		assertEquals ( "folder same with source diagram" , mockFolder ,testMapDiagram.getFolder() ) ;
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testChangeFolder () throws Exception 
	{
		final HibFolder mockFolder2 = mockery.mock(HibFolder.class , "mockFolder2") ;
		final Set<HibMap> mockMapDiagramSet = mockery.mock( Set.class , "mockMapDiagramSet") ; 
		mockery.checking( new Expectations () {{
//			atLeast(1).of(mockFolder).removeMapDiagram(with(any(HibMap.class)));
			atLeast(1).of(mockFolder).getMapDiagrams(); will(returnValue(mockMapDiagramSet));
			atLeast(1).of(mockFolder2).getMapDiagrams() ; will(returnValue(mockMapDiagramSet));
			atLeast(1).of(mockMapDiagramSet).add(testMapDiagram); will(returnValue(true));
			atLeast(1).of(mockMapDiagramSet).remove(with(any(HibMap.class))); will(returnValue(true));
		}});
//		mockery.checking( new Expectations () {{
//			testMapDiagram = new HibMap ( mockFolder , DIAGRAM_NAME1 ) ;
//			mockery.checking( new Expectations () {{
//			atLeast(1).of(mockFolder2).getMapDiagrams() ; will(returnValue(mockMapDiagramSet));
//			atLeast(1).of(mockMapDiagramSet).add(testMapDiagram); will(returnValue(true));
//		}});
//		}});
		assertEquals ( "folder is mockFolder" , mockFolder , testMapDiagram.getFolder() ) ;
		testMapDiagram.changeFolder(mockFolder2) ;
		assertEquals ("folder is mockFolder2" , mockFolder2 , testMapDiagram.getFolder() ) ;
	}
	
	@Test
	public void testEquals () throws Exception
	{	
		assertTrue ( "Equals with self" ,testMapDiagram.equals(testMapDiagram)) ;
		assertFalse ( "Not equals with null" , testMapDiagram.equals(null)) ;
		assertFalse ( "Not equals with other" , testMapDiagram.equals(testMapDiagram2)) ;
		
		testMapDiagram2.setName(DIAGRAM_NAME1) ;
		assertFalse ("Not equals with other same name" , testMapDiagram.equals(testMapDiagram2)) ;
	}
	
	@Test
	public final void testHashCode() {
		assertFalse ( "Hash code not equals." , testMapDiagram.hashCode() == testMapDiagram2.hashCode() ) ;
		testMapDiagram2.setName(DIAGRAM_NAME1) ;
		assertFalse ( "Hash code STILL not equals." , testMapDiagram.hashCode() == testMapDiagram2.hashCode() ) ;
	}
}
