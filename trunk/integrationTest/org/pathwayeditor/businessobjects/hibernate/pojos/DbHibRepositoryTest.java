/**
 * 
 */
package org.pathwayeditor.businessobjects.hibernate.pojos;

import static org.junit.Assert.assertEquals;

import java.io.FileInputStream;

import org.dbunit.Assertion;
import org.dbunit.dataset.CompositeDataSet;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.SortedTable;
import org.dbunit.dataset.filter.DefaultColumnFilter;
import org.dbunit.dataset.xml.XmlDataSet;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;
import org.pathwayeditor.testutils.PojoTester;

/**
 * @author ntsorman
 *
 */
public class DbHibRepositoryTest extends PojoTester  {


//	private static final String HIB_CONFIG_FILE = "test_hibernate.cfg.xml";
	private static final String ALT_REPOSITORY_NAME = "repo name 3" ;
	private static final String ALT_REPOSITORY_DESCRIPTION = "repository description 3" ;
	private static final int ALT_REPOSITORY_VERSION = 25343;
	private static final String REF_DATA = "integrationTest/DbSourceData/DbSourceRepositoryRefData.xml";
	
//	private static final String CHANGE_ROOT_EXPECTED_RESULTS = "integrationTest/DbRepositoryTestData/ExpectedChangeRootRefData.xml";
	private static final String DELETED_REPOSITORY_NO_SUBFOLDERS = "integrationTest/DbRepositoryTestData/OnlyOneReposirotyNoSubFoldersRefData.xml";
	private static final String DELETED_REPOSITORY_SUBFOLDERS = "integrationTest/DbRepositoryTestData/OnlyOneRepositoryWithSubFoldersRefData.xml";
	private static final String EXPECTED_FIRST_REPO_NAME = "repo name";
	private static final String EXPECTED_FIRST_REPO_DESCN = "repository description";
	private static final int EXPECTED_FIRST_REPO_BUILD_NUM = 2534;
	private static final int EXPECTED_FIRST_REPO_LAST_INODE = 6;
	private static final int EXPECTED_FIRST_REPO_NUM_MAPS = 1;
	private static final int EXPECTED_FIRST_REPO_NUM_FOLDERS = 5;
	private static final Long EXPECTED_FIRST_REPO_ID = 100001L;
	private static final String ADDED_REPO_DATA = "integrationTest/DbRepositoryTestData/AdditionalRepositoryNoSubFoldersRefData.xml";

	@SuppressWarnings("unchecked")
	@Test
	public void testReadRepository() throws Exception {
		doSetup();
		
		Session session = this.getSession();
		HibRepository testRepo = (HibRepository)session.createCriteria(HibRepository.class).add(Restrictions.eq("name", EXPECTED_FIRST_REPO_NAME)).uniqueResult();
		assertEquals("expected repo id", EXPECTED_FIRST_REPO_ID, testRepo.getId());
		assertEquals("expected repo name", EXPECTED_FIRST_REPO_NAME, testRepo.getName());
		assertEquals("expected repo name", EXPECTED_FIRST_REPO_DESCN, testRepo.getDescription());
		assertEquals("expected repo name", EXPECTED_FIRST_REPO_BUILD_NUM, testRepo.getBuildNum());
		assertEquals("expected repo name", EXPECTED_FIRST_REPO_LAST_INODE, testRepo.getLastINode());
		assertEquals("expected repo name", EXPECTED_FIRST_REPO_NUM_MAPS, testRepo.getMaps().size());
		assertEquals("expected repo name", EXPECTED_FIRST_REPO_NUM_FOLDERS, testRepo.getFolders().size());
	}


	@Test
	public void testWriteToDataBase () throws Exception
	{
		doSetup () ;
		
		HibRepository repositoryToWrite = new HibRepository (ALT_REPOSITORY_NAME, ALT_REPOSITORY_DESCRIPTION, ALT_REPOSITORY_VERSION);
//		HibRootFolder rootFolderToWrite = new HibRootFolder () ;
		
		
//		repositoryToWrite.changeRootFolder(rootFolderToWrite) ;
		
		
		getSession().save(repositoryToWrite) ;
		
		getSession().getTransaction().commit() ;
		IDataSet expectedDeltas = new XmlDataSet(new FileInputStream(ADDED_REPO_DATA));
		String testTables[] = expectedDeltas.getTableNames();
		IDataSet actualChanges = getConnection().createDataSet(testTables);
		IDataSet expectedChanges = new CompositeDataSet(new XmlDataSet(new FileInputStream(REF_DATA)),
				expectedDeltas);
		
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
		
//		HibRootFolder dbRootFolder = dbRepository.getHibRootFolder() ;
		
		getSession().delete(dbRepository) ;
//		getSession().delete(dbRootFolder) ;
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
		
//		HibRootFolder dbRootFolder = dbRepository.getRootFolder() ;
		
		getSession().delete(dbRepository) ;
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
		return REF_DATA;
	}

}
