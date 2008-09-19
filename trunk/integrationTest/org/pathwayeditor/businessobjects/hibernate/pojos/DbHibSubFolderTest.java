package org.pathwayeditor.businessobjects.hibernate.pojos;


import static org.junit.Assert.assertEquals;

import java.io.FileInputStream;
import java.util.ArrayList;

import org.dbunit.Assertion;
import org.dbunit.dataset.CompositeDataSet;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.SortedTable;
import org.dbunit.dataset.filter.DefaultColumnFilter;
import org.dbunit.dataset.xml.XmlDataSet;
import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Test;
import org.pathwayeditor.testutils.PojoTester;

/**
 * @author ntsorman
 *
 */
public class DbHibSubFolderTest extends PojoTester{
	
	private static final String REPOSITORY_NAME = "new repo name" ;
	private static final String REPOSITORY_DESCRIPTION = "new repository description" ;
	private static final int REPOSITORY_VERSION = 2534333;
	private static final String DELETED_REF_DATA = "integrationTest/DbRepositoryTestData/RepositoryNoSubFoldersRefData.xml";
	private static final String DELETED_PARENT_WITH_CHILDREN = "integrationTest/DbRepositoryTestData/DeletedSubFolderWithChildrenRefData.xml";
	private static final String CLONED_SUBFOLDER_REF_DATA =  "integrationTest/DbRepositoryTestData/CloneSubFolderRefData.xml";
	
	private static final int TWO_ENTRIES_TABLE = 2 ;
	private static final int THREE_ENTRIES_TABLE = 3 ;
	private static final int SEVEN_ENTRIES_TABLE = 7 ;
	
	private static final String FOLDER_NAME_ONE= "new Folder one" ;
	private static final String FOLDER_NAME_TWO= "new Folder two" ;
	private static final String FOLDER_NAME_THREE= "new Folder thee" ;
	
	private static final String DIAGRAM_NAME_ONE= "new Diagram one" ;
	private static final String DIAGRAM_NAME_TWO= "new Diagram two" ;
	
	private static final String SUBFOLDER_TWO_NAME = "subfolder2" ;
	
	
	@Test
	public void testWriteSubFolderToDB () throws Exception 
	{	
		doSetup () ;
		
		HibRepository aRepository = new HibRepository (REPOSITORY_NAME , REPOSITORY_DESCRIPTION , REPOSITORY_VERSION) ;
		
		HibRootFolder aRootFolder = new HibRootFolder () ;
		aRootFolder.setRepository(aRepository);
		aRootFolder.setOwningRepository(aRepository);
		
		HibSubFolder aFolder = new HibSubFolder (aRootFolder , FOLDER_NAME_ONE) ;
		HibSubFolder bFolder = new HibSubFolder (aFolder , FOLDER_NAME_TWO) ;
		HibSubFolder cFolder = new HibSubFolder (aFolder , FOLDER_NAME_THREE) ;
		
		HibMapDiagram aMapDiagram = new HibMapDiagram () ;
		HibMapDiagram bMapDiagram = new HibMapDiagram () ;
		
		aMapDiagram.setName(DIAGRAM_NAME_ONE) ;
		bMapDiagram.setName(DIAGRAM_NAME_TWO) ;
		aMapDiagram.setRepository(aRepository);
		bMapDiagram.setRepository(aRepository);
		
		aRepository.changeRootFolder(aRootFolder) ;
		
		aRootFolder.addSubFolder(aFolder);

		aFolder.addSubFolder(bFolder) ;
		aFolder.addSubFolder(cFolder) ;
		
		aRootFolder.addMapDiagram(aMapDiagram) ;
		aFolder.addMapDiagram(bMapDiagram) ;
		
		getSession().save(aRepository) ;
		
		getSession().getTransaction().commit() ;
		
		Session session = getHibFactory().getCurrentSession() ;
		session.beginTransaction();
		
		Query retrievedRootFolder = session.createQuery( "from HibSubFolder") ;
		assertEquals ( "one rootFolder" , SEVEN_ENTRIES_TABLE ,retrievedRootFolder.list().size() ) ;
		
		Query retrievedSubFolder = session.createQuery( "from HibMapDiagram") ;
		assertEquals ( "one subFolder" , THREE_ENTRIES_TABLE ,retrievedSubFolder.list().size() ) ;
		
		session.close() ;
		
		
	}
	
	@Test
	public void testCloneFolderSubFolderAndMapDiagramsAndMoveAllBetweenRepositories () throws Exception
	{
		doSetup ();
		
		Query retrievedSubFolder = getSession().createQuery( "From HibSubFolder where id='100003'") ;
		HibSubFolder dbSourceFolder = (HibSubFolder) retrievedSubFolder.uniqueResult() ;
		
		Query retrievedRootFolder = getSession().createQuery( "From HibRootFolder where id='100006'") ;
		HibRootFolder dbRootFolder = (HibRootFolder) retrievedRootFolder.uniqueResult() ;
		HibSubFolder copyOfSubFolder = new HibSubFolder ( dbRootFolder , dbSourceFolder ) ;
		copyOfSubFolder.setRepository(dbRootFolder.getRepository());
		dbRootFolder.addSubFolder(copyOfSubFolder) ;
		
		getSession().saveOrUpdate(dbRootFolder);
		
		getSession().getTransaction().commit();
		
		IDataSet expectedDeltas = new XmlDataSet(new FileInputStream(
				CLONED_SUBFOLDER_REF_DATA));
		String testTables[] = expectedDeltas.getTableNames();
		IDataSet actualChanges = getConnection().createDataSet(testTables);
		IDataSet expectedChanges = new CompositeDataSet(expectedDeltas);
		
		for (String t : testTables) {
			ITable expectedTable = DefaultColumnFilter
					.includedColumnsTable(expectedChanges.getTable(t),
							expectedDeltas.getTable(t).getTableMetaData()
									.getColumns());
			ITable actualTable = DefaultColumnFilter.includedColumnsTable(
					actualChanges.getTable(t), expectedDeltas.getTable(t)
							.getTableMetaData().getColumns());
			Assertion.assertEquals(new SortedTable(expectedTable),
					new SortedTable(actualTable, expectedTable
							.getTableMetaData()));
		}
		
	}
	
	@Test
	public void testDeleteSubFolders () throws Exception
	{
		doSetup ();
		
		Query retrievedSubFolder = getSession().createQuery( "From HibSubFolder where id='100003'") ;
		
		HibSubFolder dbParentFolder = (HibSubFolder) retrievedSubFolder.uniqueResult() ;
		
		assertEquals ( "Number of subfolders" , TWO_ENTRIES_TABLE , dbParentFolder.getSubFolders().size()) ;
		
		assertEquals ( "SubFolder name" , SUBFOLDER_TWO_NAME , dbParentFolder.getName() ) ;
		assertEquals ( "Number of Subfolders" , TWO_ENTRIES_TABLE , dbParentFolder.getSubFolders().size()) ;
		
		HibSubFolder subFolder1 , subFolder2 ;
		
		ArrayList <HibSubFolder> subFoldersList = new ArrayList <HibSubFolder> ( dbParentFolder.getSubFolders() ) ; 
		
		for (HibSubFolder subFolder : subFoldersList)
		{
			dbParentFolder.removeHibSubFolder(subFolder) ;
		}
		
		getSession().saveOrUpdate(dbParentFolder) ;
		getSession().getTransaction().commit() ;
		
		IDataSet expectedDeltas = new XmlDataSet(new FileInputStream(
				DELETED_REF_DATA));
		String testTables[] = expectedDeltas.getTableNames();
		IDataSet actualChanges = getConnection().createDataSet(testTables);
		IDataSet expectedChanges = new CompositeDataSet(expectedDeltas);
		
		for (String t : testTables) {
			ITable expectedTable = DefaultColumnFilter
					.includedColumnsTable(expectedChanges.getTable(t),
							expectedDeltas.getTable(t).getTableMetaData()
									.getColumns());
			ITable actualTable = DefaultColumnFilter.includedColumnsTable(
					actualChanges.getTable(t), expectedDeltas.getTable(t)
							.getTableMetaData().getColumns());
			Assertion.assertEquals(new SortedTable(expectedTable),
					new SortedTable(actualTable, expectedTable
							.getTableMetaData()));
		}
		
		
	}
	
	@Test
	public void testDeleteParentFolderAndSubFolders () throws Exception 
	{	
		doSetup() ;

		Query retrievedSubFolder = getSession().createQuery( "From HibSubFolder where id='100003'") ;
		HibSubFolder dbParentFolder = (HibSubFolder) retrievedSubFolder.uniqueResult() ;
		assertEquals ( "Number of subfolders" , TWO_ENTRIES_TABLE , dbParentFolder.getSubFolders().size()) ;
		assertEquals ( "SubFolder name" , SUBFOLDER_TWO_NAME , dbParentFolder.getName() ) ;
		getSession().delete(dbParentFolder) ;
		getSession().getTransaction().commit() ;

		IDataSet expectedDeltas = new XmlDataSet(new FileInputStream(
				DELETED_PARENT_WITH_CHILDREN));
		String testTables[] = expectedDeltas.getTableNames();
		IDataSet actualChanges = getConnection().createDataSet(testTables);
		IDataSet expectedChanges = new CompositeDataSet(expectedDeltas);
		for (String t : testTables) {
			ITable expectedTable = DefaultColumnFilter
					.includedColumnsTable(expectedChanges.getTable(t),
							expectedDeltas.getTable(t).getTableMetaData()
									.getColumns());
			ITable actualTable = DefaultColumnFilter.includedColumnsTable(
					actualChanges.getTable(t), expectedDeltas.getTable(t)
							.getTableMetaData().getColumns());
			Assertion.assertEquals(new SortedTable(expectedTable),
					new SortedTable(actualTable, expectedTable
							.getTableMetaData()));
		}
	}

	@Override
	protected String getDbUnitDataFilePath() {
		return "integrationTest/DbSourceData/DbSourceRepositoryRefData.xml";
	}
}
