/**
 * 
 */
package org.pathwayeditor.businessobjects.hibernate.pojos;

import static org.junit.Assert.assertTrue;

import java.io.FileInputStream;

import org.dbunit.Assertion;
import org.dbunit.dataset.CompositeDataSet;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.SortedTable;
import org.dbunit.dataset.filter.DefaultColumnFilter;
import org.dbunit.dataset.xml.XmlDataSet;
import org.hibernate.Query;
import org.junit.Test;
import org.pathwayeditor.testutils.PojoTester;

/**
 * @author ntsorman
 *
 */
public class DbHibRepositoryTest extends PojoTester  {


	private static final String HIB_CONFIG_FILE = "test_hibernate.cfg.xml";
	private static final String ALT_REPOSITORY_NAME = "repo name 3" ;
	private static final String ALT_REPOSITORY_DESCRIPTION = "repository description 3" ;
	private static final int ALT_REPOSITORY_VERSION = 25343;
	private static final String REF_DATA = "integrationTest/DbRepositoryTestData/RepositoryRefData.xml";
	
	private static final String CHANGE_ROOT_EXPECTED_RESULTS = "integrationTest/DbRepositoryTestData/ExpectedChangeRootRefData.xml";
	private static final String DELETED_REPOSITORY_NO_SUBFOLDERS = "integrationTest/DbRepositoryTestData/OnlyOneReposirotyNoSubFoldersRefData.xml";
	private static final String DELETED_REPOSITORY_SUBFOLDERS = "integrationTest/DbRepositoryTestData/OnlyOneRepositoryWithSubFoldersRefData.xml";

	
	


	@Test
	public void testWriteToDataBase () throws Exception
	{
		doSetup () ;
		
		HibRepository repositoryToWrite = new HibRepository (ALT_REPOSITORY_NAME, ALT_REPOSITORY_DESCRIPTION, ALT_REPOSITORY_VERSION,new HibRootFolder() );
		HibRootFolder rootFolderToWrite = new HibRootFolder () ;
		
		
		repositoryToWrite.changeRootFolder(rootFolderToWrite) ;
		
		
		getSession().save(repositoryToWrite) ;
		
		getSession().getTransaction().commit() ;

		
	}
	

		@Test
		public void testChangeRootFolder () throws Exception 
		{
			doSetup () ;

			Query retrievedRepository = getSession()
					.createQuery("from HibRepository where id = '100001'");

			HibRepository loadedRepository = (HibRepository) retrievedRepository
					.uniqueResult();

			HibRootFolder oldRootFolder = loadedRepository.getHibRootFolder();

			HibRootFolder newRootFolder = new HibRootFolder();

			loadedRepository.changeRootFolder(null);
			
			assertTrue("old-subfolder unassigned", oldRootFolder
					.getOwningRepository() == null);

			getSession().delete(oldRootFolder);

			getSession().getTransaction().commit();
	
			IDataSet expectedDeltas = new XmlDataSet(new FileInputStream(
					CHANGE_ROOT_EXPECTED_RESULTS));
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
	public void testDeleteRepositoryWithSubFolders () throws Exception 
	{
		doSetup();	
		
		Query repositoryGetter = getSession().createQuery ( "From HibRepository where id='100001'") ;
		
		HibRepository dbRepository = (HibRepository) repositoryGetter.uniqueResult() ;
		
		HibRootFolder dbRootFolder = dbRepository.getHibRootFolder() ;
		
		getSession().delete(dbRepository) ;
		getSession().delete(dbRootFolder) ;
		getSession().getTransaction().commit() ;
		
		IDataSet expectedDeltas = new XmlDataSet(new FileInputStream(
				DELETED_REPOSITORY_NO_SUBFOLDERS));
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
	public void testDeleteRepositoryWithOnlyRootFolders () throws Exception 
	{
		doSetup () ;		
		
		Query repositoryGetter = getSession().createQuery ( "From HibRepository where id='100002'") ;
		
		HibRepository dbRepository = (HibRepository) repositoryGetter.uniqueResult() ;
		
		HibRootFolder dbRootFolder = dbRepository.getHibRootFolder() ;
		
		getSession().delete(dbRepository) ;
		getSession().delete(dbRootFolder) ;
		getSession().getTransaction().commit() ;
		
		IDataSet expectedDeltas = new XmlDataSet(new FileInputStream(
				DELETED_REPOSITORY_SUBFOLDERS));
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

	protected String getDbUnitDataFilePath() {
		return "integrationTest/DbSourceData/DbSourceRepositoryRefData.xml";
	}

}
