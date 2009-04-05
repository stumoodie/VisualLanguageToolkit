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
import static org.junit.Assert.assertNotNull;

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
import org.junit.Ignore;
import org.junit.Test;
import org.pathwayeditor.testutils.PojoTester;



/**
 * @author ntsorman
 *
 */
public class DbHibRootFolderTest  extends PojoTester {
	
//	private static final String HIB_CONFIG_FILE = "test_hibernate.cfg.xml";
//	private static final String REF_DATA = "integrationTest/DbRepositoryTestData/RepositoryRefData.xml";
	private static final String ADDED_SUBFOLDER_REF_DATA = "integrationTest/DbRepositoryTestData/AddedSubFolderToRootFolderRefData.xml";
//	private static final String DELETED_ROOT_REF_DATA = "integrationTest/DbRepositoryTestData/DeletedRootFolderRefData.xml";
	private static final String CLONED_ROOTFOLDER_REF_DATA = "integrationTest/DbRepositoryTestData/ClonedRootFolderRefData.xml";
	private static final String SUB_FOLDER_NAME = "subfolder5" ;
	private static final Long EXPECTED_REPO_ID = 100001L;
	
	
	
	@Test
	public void testLoadRootFolder () throws Exception 
	{
		Session sess = getHibFactory().getCurrentSession();
		sess.beginTransaction();
		Query rootFolderGetter = sess.createQuery ( "From HibRootFolder where id='100001'") ;
		
		HibRootFolder dbRootFolder = (HibRootFolder) rootFolderGetter.uniqueResult() ;
		HibRepository actualRepository = dbRootFolder.getRepository();
		Long actualRepoId = actualRepository.getId(); 
		sess.getTransaction().commit() ;
		
		assertNotNull("has repository", actualRepository);
		assertEquals("expected repository", EXPECTED_REPO_ID, actualRepoId);
		
	}

	@Test
	public void testAddFoldersToRootFolder () throws Exception 
	{
		Session sess = getHibFactory().getCurrentSession();
		sess.beginTransaction();
		Query rootFolderGetter = sess.createQuery ( "From HibRootFolder where id='100006'") ;
		HibRootFolder dbRootFolder = (HibRootFolder) rootFolderGetter.uniqueResult() ;
		HibSubFolder subFolderToAdd = new HibSubFolder ( dbRootFolder , SUB_FOLDER_NAME ) ;
		dbRootFolder.addSubFolder(subFolderToAdd) ;
		sess.saveOrUpdate(dbRootFolder) ;
		sess.getTransaction().commit() ;
		this.compareDatabase(ADDED_SUBFOLDER_REF_DATA);
//		IDataSet expectedDeltas = new XmlDataSet(new FileInputStream(
//				ADDED_SUBFOLDER_REF_DATA));
//		String testTables[] = expectedDeltas.getTableNames();
//		IDataSet actualChanges = getConnection().createDataSet(testTables);
//		IDataSet expectedChanges = new CompositeDataSet(expectedDeltas);
//		
//		for (String t : testTables) {
//			ITable expectedTable = DefaultColumnFilter
//					.includedColumnsTable(expectedChanges.getTable(t),
//							expectedDeltas.getTable(t).getTableMetaData()
//									.getColumns());
//			ITable actualTable = DefaultColumnFilter.includedColumnsTable(
//					actualChanges.getTable(t), expectedDeltas.getTable(t)
//							.getTableMetaData().getColumns());
//			Assertion.assertEquals(new SortedTable(expectedTable),
//					new SortedTable(actualTable, expectedTable
//							.getTableMetaData()));
//		}
	}
	
//	@Test
//	public void testDeleteRootFolder () throws Exception 
//	{
//		doSetup() ;
//		sess.beginTransaction();
//		Query rootFolderGetter = sess.createQuery ( "From HibRootFolder where id='100001'") ;
//		
//		HibRootFolder dbRootFolder = (HibRootFolder) rootFolderGetter.uniqueResult() ;
//		HibRepository hibRepo = dbRootFolder.getRepository();
//		dbRootFolder.changeRepository(null);
//		sess.saveOrUpdate(hibRepo) ;
//		sess.getTransaction().commit() ;
//		
//		IDataSet expectedDeltas = new XmlDataSet(new FileInputStream(
//				DELETED_ROOT_REF_DATA));
//		String testTables[] = expectedDeltas.getTableNames();
//		IDataSet actualChanges = getConnection().createDataSet(testTables);
//		IDataSet expectedChanges = new CompositeDataSet(expectedDeltas);
//		
//		for (String t : testTables) {
//			ITable expectedTable = DefaultColumnFilter
//					.includedColumnsTable(expectedChanges.getTable(t),
//							expectedDeltas.getTable(t).getTableMetaData()
//									.getColumns());
//			ITable actualTable = DefaultColumnFilter.includedColumnsTable(
//					actualChanges.getTable(t), expectedDeltas.getTable(t)
//							.getTableMetaData().getColumns());
//			Assertion.assertEquals(new SortedTable(expectedTable),
//					new SortedTable(actualTable, expectedTable
//							.getTableMetaData()));
//		}
//	}
	
	@Ignore @Test
	public void testCloneRootFolder () throws Exception
	{
		Session sess = getHibFactory().getCurrentSession();
		sess.beginTransaction();
		Query repositoryGetter = sess.createQuery ( "From HibRepository where id='100002'") ;
		HibRepository dbRepository = (HibRepository) repositoryGetter.uniqueResult() ;
		HibRootFolder oldRootFolder = dbRepository.getRootFolder();
		HibRootFolder cloneOfRootFolder = new HibRootFolder ( dbRepository , oldRootFolder ) ;
		dbRepository.changeRootFolder(null);
		sess.delete(oldRootFolder) ;
		sess.flush();
		dbRepository.changeRootFolder(cloneOfRootFolder) ;
		sess.saveOrUpdate(dbRepository) ;
		sess.getTransaction().commit() ;
		
		IDataSet expectedDeltas = new XmlDataSet(new FileInputStream(
				CLONED_ROOTFOLDER_REF_DATA));
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
