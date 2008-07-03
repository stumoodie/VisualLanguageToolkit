package org.pathwayeditor.businessobjects.hibernate.pojos;

import static org.junit.Assert.assertEquals;

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
public class SubFolderTest {
	
	private Mockery mockery = new JUnit4Mockery() {{
		 setImposteriser(ClassImposteriser.INSTANCE);
	}};
	
	
	private HibSubFolder subfolder1 = new HibSubFolder () ;
	private HibSubFolder subfolder2 = new HibSubFolder () ;
	private HibSubFolder subfolder3 = new HibSubFolder () ;
	
	private static final String SUBFOLDER_NAME = "subfolder1" ;
	private static final String SUBFOLDER_NAME_2 = "subfolder2" ;
	
	
	@Before
	public void setUp() throws Exception {
	}
	
	@After
	public void tearDown() throws Exception {
		
	}
	
	@Test
	public void testCopyConstructor () throws Exception 
	{
		final HibFolder mockParentFolder = mockery.mock( HibFolder.class , "mockParentFolder" ) ;
		mockery.checking( new Expectations () {{
			
		}} ) ;
		
		subfolder2 = new HibSubFolder ( mockParentFolder , SUBFOLDER_NAME_2 ) ;
		
		subfolder3 = new HibSubFolder (mockParentFolder , subfolder2 );
		
		assertEquals ( SUBFOLDER_NAME_2 , subfolder3.getName()  ) ;
		assertEquals ( mockParentFolder , subfolder3.getParentFolder()  ) ;
	}
	
	@Test 
	public void testAddSubFolder () throws Exception 
	{
		
		final HibFolder mockParentFolder = mockery.mock( HibFolder.class , "mockParentFolder" ) ;
		mockery.checking( new Expectations () {{
			
		}} ) ;
		
		subfolder1 = new HibSubFolder ( mockParentFolder , SUBFOLDER_NAME ) ;
		
		subfolder1.addSubFolder(subfolder2) ;
		subfolder1.addSubFolder(subfolder3) ;
		
		assertEquals ( 2 , subfolder1.getSubFolders().size()) ;
	}
	
	@Test
	public void removeSubFolder () throws Exception 
	{
		final HibFolder mockParentFolder = mockery.mock( HibFolder.class , "mockParentFolder" ) ;
		mockery.checking( new Expectations () {{
			
		}} ) ;
		
		subfolder1 = new HibSubFolder ( mockParentFolder , SUBFOLDER_NAME ) ;
		
		subfolder1.addSubFolder(subfolder2) ;
		
		assertEquals( 1 , subfolder1.getSubFolders().size()) ;
		assertEquals ( subfolder2.getParentFolder() , subfolder1 ) ;		
		
		subfolder1.removeSubFolder(subfolder2) ;
		
		assertEquals( 0 , subfolder1.getSubFolders().size()) ;
		assertEquals ( null , subfolder2.getParentFolder()) ;
	}
	
	@Test
	public void testGetParentFolder () throws Exception 
	{
		final HibFolder mockParentFolder = mockery.mock( HibFolder.class , "mockParentFolder" ) ;
		mockery.checking( new Expectations () {{
			
		}} ) ;
		
		subfolder1 = new HibSubFolder ( mockParentFolder , SUBFOLDER_NAME ) ;
		
		subfolder1.addSubFolder(subfolder2) ;
		
		assertEquals ( mockParentFolder , subfolder1.getParentFolder()) ;
		assertEquals ( subfolder1 , subfolder2.getParentFolder()) ;
	}
	
	@Test
	public void testChangeParentFolder () throws Exception 
	{
		final HibFolder mockParentFolder = mockery.mock( HibFolder.class , "mockParentFolder" ) ;
		mockery.checking( new Expectations () {{
			
		}} ) ;
		
		subfolder1 = new HibSubFolder ( mockParentFolder , SUBFOLDER_NAME ) ;
		
		
		subfolder1.addSubFolder(subfolder2) ;
		assertEquals ( subfolder1 , subfolder2.getParentFolder()) ;
		
		subfolder3.addSubFolder(subfolder2) ;
		assertEquals ( subfolder3 , subfolder2.getParentFolder()) ;
		
	}
	
	@SuppressWarnings("unchecked")
	@Test 
	public void testAddMapDiagram () throws Exception 
	{
		final HibFolder mockParentFolder = mockery.mock( HibFolder.class , "mockParentFolder" ) ;
		final HibMapDiagram mockMapDiagram = mockery.mock( HibMapDiagram.class , "mockMapDiagram") ; 
		final Set<HibMapDiagram> mockMapDiagramSet = mockery.mock( Set.class , "mockMapDiagramSet") ; 
		mockery.checking( new Expectations () {{
		}});
			
		subfolder1 = new HibSubFolder ( mockParentFolder , SUBFOLDER_NAME ) ;
		
		mockery.checking( new Expectations () {{
			atLeast(1).of(mockMapDiagram).getOwner() ; will(returnValue(mockParentFolder)) ;
			atLeast(1).of(mockMapDiagram).setOwner(subfolder1) ;

			atLeast(1).of(mockParentFolder).getMapDiagrams() ; will(returnValue(mockMapDiagramSet));
			
			atLeast(1).of(mockMapDiagramSet).remove(mockMapDiagram); will(returnValue(true));
		}} ) ;
		
		
		subfolder1.addMapDiagram(mockMapDiagram) ;
		
		assertEquals ("expected num diagrams", 1 , subfolder1.getMapDiagrams().size()) ;
		assertEquals ("expected diagram", mockMapDiagram , subfolder1.getMapDiagrams().iterator().next() ) ;
	}
	
	@Test
	public void testRemoveDiagram () throws Exception
	{
		final HibFolder mockParentFolder = mockery.mock( HibFolder.class , "mockParentFolder" ) ;
		final HibMapDiagram mockMapDiagram = mockery.mock( HibMapDiagram.class , "mockMapDiagram") ; 
		final Set<HibMapDiagram> mockMapDiagramSet = mockery.mock( Set.class , "mockMapDiagramSet") ; 

		mockery.checking( new Expectations () {{
		}});
			
		subfolder1 = new HibSubFolder ( mockParentFolder , SUBFOLDER_NAME ) ;
		
		mockery.checking( new Expectations () {{
			atLeast(1).of(mockMapDiagram).getOwner() ; will(returnValue(subfolder1)) ;
			one(mockMapDiagram).setOwner(subfolder1) ;
			one(mockMapDiagram).setOwner(null) ;

		}} ) ;
		
		subfolder1.addMapDiagram(mockMapDiagram) ;
		assertEquals ( 1 , subfolder1.getMapDiagrams().size()) ;
		
		
		subfolder1.removeMapDiagram(mockMapDiagram) ;
		assertEquals ("expected num diagrams", 0, subfolder1.getMapDiagrams().size()) ;
	}
}
