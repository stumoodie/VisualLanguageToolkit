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
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.Version;
import org.pathwayeditor.testutils.PojoTester;

/**
 * @author ntsorman
 *
 */
public class DbContextTest extends PojoTester{
	
	public static final String CONTEXT_ID = "context id" ;
	public static final String CONTEXT_NAME = "new context name" ;
	public static final int MAJOR_VERSION = 1 ;
	public static final int MINOR_VERSION = 2 ;
	public static final int PATCH_VERSION = 3 ;
	public static final String CONTEXT_DESCRIPTION = "context description" ;
	public static final String OBJECT_TYPE_NAME = "objectTypeName" ;
	public static final String OBJECT_TYPE_DESCRIPTION = "objectTypeDescription" ;
	
	private static final String ADDED_CONTEXT_DATA = "integrationTest/DbContextTestData/DbAddedContextRefData.xml";
	private static final String ADDED_OBJECT_TYPE_DATA = "integrationTest/DbContextTestData/AddedObjectTypeRefData.xml";
	private static final String DELETED_CONTEXT_DATA = "integrationTest/DbContextTestData/DeletedContextRefData.xml";
	
	@Test
	public void testAddContext () throws Exception 
	{	
		doSetup () ;
		Version version = new Version(MAJOR_VERSION, MINOR_VERSION, PATCH_VERSION);
		HibNotation contextToAdd = new HibNotation ( CONTEXT_ID, CONTEXT_NAME, CONTEXT_DESCRIPTION, version) ;
		
		getSession().save(contextToAdd) ;
		getSession().getTransaction().commit() ;
		
		IDataSet expectedDeltas = new XmlDataSet(new FileInputStream(
				ADDED_CONTEXT_DATA));
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
	public void testaddObjectTypeToContext () throws Exception 
	{
		doSetup () ;
		Query retreivedContext = getSession().createQuery("from HibContext where id = '100001'" ) ;
		HibNotation dbContext = (HibNotation) retreivedContext.uniqueResult() ;
		
		HibObjectType newObjectType = new HibObjectType (dbContext, OBJECT_TYPE_NAME, OBJECT_TYPE_DESCRIPTION) ;
		dbContext.addObjectType(newObjectType) ;
		
		getSession().saveOrUpdate(dbContext) ;
		getSession().getTransaction().commit() ;
		
		IDataSet expectedDeltas = new XmlDataSet(new FileInputStream(
				ADDED_OBJECT_TYPE_DATA));
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
	public void testDeleteContext () throws Exception 
	{
		doSetup () ;
		Query retreivedContext = getSession().createQuery("from HibContext where id = '100002'" ) ;
		HibNotation dbContext = (HibNotation) retreivedContext.uniqueResult() ;
		
		getSession().delete(dbContext) ;
		getSession().getTransaction().commit() ;
		
		IDataSet expectedDeltas = new XmlDataSet(new FileInputStream(
				DELETED_CONTEXT_DATA));
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
		return "integrationTest/DbSourceData/DbSourceDataRefData.xml";
	}

}
