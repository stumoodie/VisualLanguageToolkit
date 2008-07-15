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
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.pathwayeditor.businessobjects.pojos.RootFolder;

/**
 * @author ntsorman
 *
 */
@RunWith(JMock.class)
public class HibRootFolderTest {
	
	private Mockery mockery = new JUnit4Mockery() {{
		 setImposteriser(ClassImposteriser.INSTANCE);
	}};

	
	private HibRootFolder testRootFolder1 = new HibRootFolder();
	private HibRootFolder testRootFolder2 = new HibRootFolder();
	
	private static final String REPOSITORY_NAME = "repositoryName" ;
	
	private static final int SUBFOLDERS = 1 ;
	private static final int MAP_DIAGRAMS = 1 ;
	
	@Before
	public void setUp() throws Exception {

	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testCopyConstructor () throws Exception
	{
		
		final HibRepository mockRepository = mockery.mock(HibRepository.class , "mockRepository") ;
		final HibSubFolder mockSubFolder = mockery.mock(HibSubFolder.class , "mockSubFolder") ;
		final HibMapDiagram mockMapDiagram = mockery.mock(HibMapDiagram.class , "mockMapDiagram" ) ;
		final Set<HibMapDiagram> mockMapDiagramSet = mockery.mock( Set.class , "mockMapDiagramSet") ;
		final Set<HibSubFolder> mockSubFolderSet = mockery.mock( Set.class , "mockSubFolderSet") ;
		final RootFolder mockBORootFolder = mockery.mock(RootFolder.class , "mockBORootFolder") ;
				
		mockery.checking( new Expectations () {{
		}});
		testRootFolder1 = new HibRootFolder( mockBORootFolder , mockRepository );
		mockery.checking( new Expectations () {{
			atLeast(1).of(mockSubFolder).getParentFolder(); will(returnValue(null)) ;
			atLeast(1).of(mockSubFolder).setParentFolder(testRootFolder1);
			atLeast(1).of(mockSubFolder).getMapDiagrams(); will(returnValue(mockMapDiagramSet)) ;
			atLeast(1).of(mockSubFolder).getSubFolders(); will(returnValue(mockSubFolderSet)) ;
			
			atLeast(1).of(mockMapDiagram).getFolder(); will(returnValue(null)) ;
			atLeast(1).of(mockMapDiagram).setFolder(testRootFolder1);
						

			one(mockMapDiagramSet).iterator(); will(returnIterator(mockMapDiagram)) ;
			one(mockMapDiagramSet).iterator(); will(returnIterator()) ;
			
			
			one(mockSubFolderSet).iterator(); will(returnIterator(mockSubFolder)) ;
			one(mockSubFolderSet).iterator(); will(returnIterator()) ;
			
		}} ) ;
				
		testRootFolder1.addSubFolder(mockSubFolder) ;
		testRootFolder1.addMapDiagram(mockMapDiagram) ;
		
		assertEquals ( "one subfolder" , SUBFOLDERS , testRootFolder1.getSubFolders().size() ) ;
		assertEquals ( "one map diagram" , MAP_DIAGRAMS , testRootFolder1.getMapDiagrams().size() ) ;
		
		testRootFolder2 = new HibRootFolder ( mockRepository , testRootFolder1 ) ;
		
		assertEquals ("RootFolder has the same reposirory", mockRepository ,  testRootFolder2.getRepository() ) ;
		assertEquals ( "one subfolder" , SUBFOLDERS , testRootFolder2.getSubFolders().size() ) ;
		assertEquals ( "one map diagram" , MAP_DIAGRAMS , testRootFolder2.getMapDiagrams().size() ) ;
	}

	
	@Test 
	public void addSubfoldersTest () throws Exception 
	{
	 	
		final HibRepository mockRepository = mockery.mock(HibRepository.class , "mockRepository") ;
		final HibSubFolder mockSubfolder = mockery.mock(HibSubFolder.class , "mockSubfolder") ;
		final RootFolder mockBORootFolder = mockery.mock(RootFolder.class , "mockBORootFolder") ;
		mockery.checking( new Expectations () {{
		}});
		testRootFolder1 = new HibRootFolder( mockBORootFolder ,mockRepository );
		mockery.checking( new Expectations () {{
			atLeast(1).of(mockSubfolder).getParentFolder(); will(returnValue(null)) ;
			atLeast(1).of(mockSubfolder).setParentFolder(testRootFolder1); 
						
		}} ) ;
		
		testRootFolder1.addSubFolder(mockSubfolder) ;
		
		assertEquals ( 1 , testRootFolder1.getSubFolders().size() );
	}
	
	@Test
	public void removeSubFolder () throws Exception 
	{
		final HibRepository mockRepository = mockery.mock(HibRepository.class , "mockRepository") ;
		final HibSubFolder mockSubfolder = mockery.mock(HibSubFolder.class , "mockSubfolder") ;
		final RootFolder mockBORootFolder = mockery.mock(RootFolder.class , "mockBORootFolder") ;
		mockery.checking( new Expectations () {{
		}});
		testRootFolder1 = new HibRootFolder(mockBORootFolder , mockRepository );
		mockery.checking( new Expectations () {{
			atLeast(1).of(mockSubfolder).getParentFolder(); will(returnValue(testRootFolder1)) ;
			atLeast(1).of(mockSubfolder).setParentFolder(testRootFolder1); 
			atLeast(1).of(mockSubfolder).setParentFolder(null);
						
		}} ) ;
		
		testRootFolder1.addSubFolder(mockSubfolder) ;
		assertEquals ( 1 , testRootFolder1.getSubFolders().size() );
		
		testRootFolder1.removeSubFolder(mockSubfolder) ;
		assertEquals ( 0 , testRootFolder1.getSubFolders().size() );
	}
	
	@Test
	public void testGetRepositoryFromRootFolder () throws Exception
	{
		final HibRepository mockRepository = mockery.mock(HibRepository.class , "mockRepository") ;
		final RootFolder mockBORootFolder = mockery.mock(RootFolder.class , "mockBORootFolder") ;
		
		testRootFolder1 = new HibRootFolder( mockBORootFolder ,mockRepository );
		
		assertEquals ( mockRepository , testRootFolder1.getRepository() ) ;
	}
	
	@Test 
	@Ignore
	public void testSetNewRepository () throws Exception 
	{
//		final HibRepository mockRepository = mockery.mock(HibRepository.class , "mockRepository") ;
//		final HibRepository mockRepository2 = mockery.mock(HibRepository.class , "mockRepository2") ;
//		final RootFolder mockBORootFolder = mockery.mock(RootFolder.class , "mockBORootFolder") ;
//		
//		mockery.checking( new Expectations () {{
//		}});
//		testRootFolder1 = new HibRootFolder( mockBORootFolder ,mockRepository );
//		mockery.checking( new Expectations () {{
//			atLeast(1).of(mockRepository2).setRootFolder(testRootFolder1);
//			atLeast(1).of(mockRepository).setRootFolder(null);
//		}});
//		
//		
//		testRootFolder1.changeRepository( mockRepository2) ;
//		assertEquals ( mockRepository2 , testRootFolder1.getRepository()) ;
	}


}
