/**
 * 
 */
package org.pathwayeditor.businessobjects.hibernate.pojos;


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
public class DbHibRootFolderTest  extends PojoTester {
	
	private static final String HIB_CONFIG_FILE = "test_hibernate.cfg.xml";
	private static final String REF_DATA = "integrationTest/DbRepositoryTestData/RepositoryRefData.xml";
	private static final String ADDED_SUBFOLDER_REF_DATA = "integrationTest/DbRepositoryTestData/AddedSubFolderToRootFolderRefData.xml";
	private static final String DELETED_ROOT_REF_DATA = "integrationTest/DbRepositoryTestData/DeletedRootFolderRefData.xml";
	private static final String CLONED_ROOTFOLDER_REF_DATA = "integrationTest/DbRepositoryTestData/ClonedRootFolderRefData.xml";
	
	
	
	private static final String SUB_FOLDER_NAME = "subfolder5" ;
	
	
	
	@Test
	public void testAddFoldersToRootFolder () throws Exception 
	{
		doSetup() ;
		Query rootFolderGetter = getSession().createQuery ( "From HibRootFolder where id='100006'") ;
		
		HibRootFolder dbRootFolder = (HibRootFolder) rootFolderGetter.uniqueResult() ;
		
		HibSubFolder subFolderToAdd = new HibSubFolder ( dbRootFolder , SUB_FOLDER_NAME ) ;
		
		dbRootFolder.addSubFolder(subFolderToAdd) ;
		
		getSession().saveOrUpdate(dbRootFolder) ;
		
		getSession().getTransaction().commit() ;
		
		IDataSet expectedDeltas = new XmlDataSet(new FileInputStream(
				ADDED_SUBFOLDER_REF_DATA));
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
	public void testDeleteRootFolder () throws Exception 
	{
		doSetup() ;
		Query rootFolderGetter = getSession().createQuery ( "From HibRootFolder where id='100001'") ;
		
		HibRootFolder dbRootFolder = (HibRootFolder) rootFolderGetter.uniqueResult() ;
		dbRootFolder.changeRepository(null);
		getSession().delete(dbRootFolder) ;
		getSession().getTransaction().commit() ;
		
		IDataSet expectedDeltas = new XmlDataSet(new FileInputStream(
				DELETED_ROOT_REF_DATA));
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
	public void testCloneRootFolder () throws Exception
	{
		doSetup() ;
		Query repositoryGetter = getSession().createQuery ( "From HibRepository where id='100002'") ;
		HibRepository dbRepository = (HibRepository) repositoryGetter.uniqueResult() ;
		HibRootFolder oldRootFolder = dbRepository.getHibRootFolder();
		HibRootFolder cloneOfRootFolder = new HibRootFolder ( dbRepository , oldRootFolder ) ;
		dbRepository.setHibRootFolder(null);
		getSession().delete(oldRootFolder) ;
		getSession().flush();
		dbRepository.changeRootFolder(cloneOfRootFolder) ;
		getSession().saveOrUpdate(dbRepository) ;
		getSession().getTransaction().commit() ;
		
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
