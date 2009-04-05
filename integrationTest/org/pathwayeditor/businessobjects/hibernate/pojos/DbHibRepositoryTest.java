/*
Copyright 2009, Court of the University of Edinburgh

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License. 
*/
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
import org.junit.Ignore;
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
		Session sess = getHibFactory().getCurrentSession();
		sess.beginTransaction();
		HibRepository testRepo = (HibRepository)sess.createCriteria(HibRepository.class).add(Restrictions.eq("name", EXPECTED_FIRST_REPO_NAME)).uniqueResult();
		assertEquals("expected repo id", EXPECTED_FIRST_REPO_ID, testRepo.getId());
		assertEquals("expected repo name", EXPECTED_FIRST_REPO_NAME, testRepo.getName());
		assertEquals("expected repo name", EXPECTED_FIRST_REPO_DESCN, testRepo.getDescription());
		assertEquals("expected repo name", EXPECTED_FIRST_REPO_BUILD_NUM, testRepo.getBuildNum());
		assertEquals("expected repo name", EXPECTED_FIRST_REPO_LAST_INODE, testRepo.getLastINode());
		assertEquals("expected repo name", EXPECTED_FIRST_REPO_NUM_MAPS, testRepo.getMaps().size());
		assertEquals("expected repo name", EXPECTED_FIRST_REPO_NUM_FOLDERS, testRepo.getFolders().size());
		sess.getTransaction().commit();
	}


	@Ignore @Test
	public void testWriteToDataBase () throws Exception
	{
		Session sess = getHibFactory().getCurrentSession();
		sess.beginTransaction();
		HibRepository repositoryToWrite = new HibRepository (ALT_REPOSITORY_NAME, ALT_REPOSITORY_DESCRIPTION, ALT_REPOSITORY_VERSION);
//		HibRootFolder rootFolderToWrite = new HibRootFolder () ;
		
		
//		repositoryToWrite.changeRootFolder(rootFolderToWrite) ;
		sess.save(repositoryToWrite) ;
		sess.getTransaction().commit() ;
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
	

	@Ignore @Test
	public void testDeleteRepositoryWithSubFolders () throws Exception 
	{
		Session sess = getHibFactory().getCurrentSession();
		sess.beginTransaction();
		Query repositoryGetter = sess.createQuery ( "From HibRepository where id='100001'") ;
		
		HibRepository dbRepository = (HibRepository) repositoryGetter.uniqueResult() ;
		
//		HibRootFolder dbRootFolder = dbRepository.getHibRootFolder() ;
		
		sess.delete(dbRepository) ;
//		getSession().delete(dbRootFolder) ;
		sess.getTransaction().commit() ;
		
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


	@Ignore @Test
	public void testDeleteRepositoryWithOnlyRootFolders () throws Exception 
	{
		Session sess = getHibFactory().getCurrentSession();
		sess.beginTransaction();
		Query repositoryGetter = sess.createQuery ( "From HibRepository where id='100002'") ;
		
		HibRepository dbRepository = (HibRepository) repositoryGetter.uniqueResult() ;
		
//		HibRootFolder dbRootFolder = dbRepository.getRootFolder() ;
		
		sess.delete(dbRepository) ;
		sess.getTransaction().commit() ;
		
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
