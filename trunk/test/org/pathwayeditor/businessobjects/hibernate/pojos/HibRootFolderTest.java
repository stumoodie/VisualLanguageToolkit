package org.pathwayeditor.businessobjects.hibernate.pojos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
import org.pathwayeditor.businessobjects.repository.IFolderContentChangeEvent;
import org.pathwayeditor.businessobjects.repository.IRepositoryItemChangeListener;
import org.pathwayeditor.businessobjects.repository.IRepositoryPropertyChangeEvent;
import org.pathwayeditor.businessobjects.repository.IRootFolder;

import uk.ed.inf.graph.util.IndexCounter;

/**
 * @author ntsorman
 *
 */
@RunWith(JMock.class)
public class HibRootFolderTest {
	
	private Mockery mockery = new JUnit4Mockery() {{
		 setImposteriser(ClassImposteriser.INSTANCE);
	}};

	
	private HibRootFolder testRootFolder1;
	private HibRootFolder testRootFolder2;
	private boolean changeDetected = false;
	
	private static final String REPOSITORY_NAME = "repositoryName" ;
	private static final int SUBFOLDERS = 1 ;
	private static final int MAP_DIAGRAMS = 1 ;
	private static final int BUILD_NUM = 1000;
	private static final String REPO_DESCN = "repo desciption";
	private static final String EXPECTED_SUBFOLDER_NAME = "subfolderName";
	private HibRepository repo = null;
	
	@Before
	public void setUp() throws Exception {
		this.repo = new HibRepository(REPOSITORY_NAME, REPO_DESCN, BUILD_NUM);
		testRootFolder1 = repo.getRootFolder();
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testCreateSubfolderListener(){
		IRootFolder actualRootFolder = repo.getRootFolder();
		this.changeDetected = false;
		actualRootFolder.addChangeListener(new IRepositoryItemChangeListener() {

			public void ancestorChange(IFolderContentChangeEvent e) {
				changeDetected = true;
			}

			public void propertyChange(IRepositoryPropertyChangeEvent e) {

			}

		});
		actualRootFolder.createSubfolder(EXPECTED_SUBFOLDER_NAME);
		assertTrue("listener called", this.changeDetected);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testCopyConstructor () throws Exception
	{
		
		final HibRepository mockRepository = mockery.mock(HibRepository.class , "mockRepository") ;
		final HibSubFolder mockSubFolder = mockery.mock(HibSubFolder.class , "mockSubFolder") ;
		final HibMap mockMapDiagram = mockery.mock(HibMap.class , "mockMapDiagram" ) ;
		final Set<HibMap> mockMapDiagramSet = mockery.mock( Set.class , "mockMapDiagramSet") ;
		final Set<HibSubFolder> mockSubFolderSet = mockery.mock( Set.class , "mockSubFolderSet") ;
		final HibRootFolder mockBORootFolder = mockery.mock(HibRootFolder.class , "mockBORootFolder") ;
		final IndexCounter indexCounter = new IndexCounter();
				
		mockery.checking( new Expectations () {{
			atLeast(1).of(mockBORootFolder).getMapDiagrams();
			atLeast(1).of(mockBORootFolder).getSubFolders();

			allowing(mockRepository).getINodeCounter(); will(returnValue(indexCounter));
		}});
		testRootFolder1 = new HibRootFolder( mockRepository,mockBORootFolder );
		this.mockery.assertIsSatisfied();
		mockery.checking( new Expectations () {{
			atLeast(1).of(mockSubFolder).setRepository(mockRepository);
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
			
			allowing(mockRepository).getINodeCounter(); will(returnValue(indexCounter));
			
		}} ) ;
				
		testRootFolder1.addSubFolder(mockSubFolder) ;
		testRootFolder1.addMapDiagram(mockMapDiagram) ;
		
		assertEquals ( "one subfolder" , SUBFOLDERS , testRootFolder1.getSubFolders().size() ) ;
		assertEquals ( "one map diagram" , MAP_DIAGRAMS , testRootFolder1.getMapDiagrams().size() ) ;
		
		testRootFolder2 = new HibRootFolder ( mockRepository , testRootFolder1 ) ;
		
		assertEquals ("RootFolder has the same reposirory", mockRepository ,  testRootFolder2.getRepository() ) ;
		assertEquals ( "one subfolder" , SUBFOLDERS , testRootFolder2.getSubFolders().size() ) ;
		assertEquals ( "one map diagram" , MAP_DIAGRAMS , testRootFolder2.getMapDiagrams().size() ) ;
		this.mockery.assertIsSatisfied();
	}

	
	@Test 
	public void addSubfoldersTest () throws Exception 
	{
	 	
		final HibRepository mockRepository = mockery.mock(HibRepository.class , "mockRepository") ;
		final IndexCounter indexCounter = new IndexCounter();
		mockery.checking( new Expectations () {{
			allowing(mockRepository).getINodeCounter(); will(returnValue(indexCounter));
		}} ) ;
		HibRootFolder mockBORootFolder = new HibRootFolder(mockRepository);
		testRootFolder1 = new HibRootFolder( mockRepository,mockBORootFolder );
		final HibSubFolder mockSubfolder = mockery.mock(HibSubFolder.class , "mockSubfolder") ;
		mockery.checking( new Expectations () {{
			allowing(mockSubfolder).setRepository(mockRepository);
			allowing(mockSubfolder).getParentFolder(); will(returnValue(null)) ;
			allowing(mockSubfolder).setParentFolder(testRootFolder1); 

			allowing(mockRepository).getINodeCounter(); will(returnValue(indexCounter));
		}} ) ;
		testRootFolder1.addSubFolder(mockSubfolder) ;
		assertEquals ( 1 , testRootFolder1.getSubFolders().size() );
	}
	
	@Test
	public void removeSubFolder () throws Exception 
	{
		final HibRepository mockRepository = mockery.mock(HibRepository.class , "mockRepository") ;
		final IndexCounter indexCounter = new IndexCounter();
		mockery.checking( new Expectations () {{
			allowing(mockRepository).getINodeCounter(); will(returnValue(indexCounter));
		}} ) ;
		HibRootFolder mockBORootFolder = new HibRootFolder(mockRepository);
		testRootFolder1 = new HibRootFolder( mockRepository,mockBORootFolder );
		final HibSubFolder mockSubfolder = mockery.mock(HibSubFolder.class , "mockSubfolder") ;
		mockery.checking( new Expectations () {{
			allowing(mockSubfolder).setRepository(mockRepository);
			one(mockSubfolder).getParentFolder(); will(returnValue(null)) ;
			allowing(mockSubfolder).setParentFolder(testRootFolder1); 
			allowing(mockSubfolder).setParentFolder(null); 
			one(mockSubfolder).getParentFolder(); will(returnValue(testRootFolder1)) ;

			allowing(mockRepository).getINodeCounter(); will(returnValue(indexCounter));
		}} ) ;
		
		testRootFolder1.addSubFolder(mockSubfolder) ;
		assertEquals ( 1 , testRootFolder1.getSubFolders().size() );
		
		testRootFolder1.removeHibSubFolder(mockSubfolder) ;
		assertEquals ( 0 , testRootFolder1.getSubFolders().size() );
	}
	
	@Test
	public void testGetRepositoryFromRootFolder () throws Exception
	{
		final HibRepository mockRepository = mockery.mock(HibRepository.class , "mockRepository") ;
		final HibRootFolder mockBORootFolder = mockery.mock(HibRootFolder.class , "mockBORootFolder") ;
		final IndexCounter indexCounter = new IndexCounter();
		mockery.checking( new Expectations () {{
			atLeast(1).of(mockBORootFolder).getMapDiagrams();
			atLeast(1).of(mockBORootFolder).getSubFolders();

			allowing(mockRepository).getINodeCounter(); will(returnValue(indexCounter));
		}});
		testRootFolder1 = new HibRootFolder(mockRepository, mockBORootFolder );
		
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
