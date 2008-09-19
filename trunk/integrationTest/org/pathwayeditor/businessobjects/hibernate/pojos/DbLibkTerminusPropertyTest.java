/**
 * 
 */
package org.pathwayeditor.businessobjects.hibernate.pojos;

import static org.junit.Assert.assertEquals;

import java.io.FileInputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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
public class DbLibkTerminusPropertyTest extends PojoTester{

	public static final String LOADED_TEXT_PROPERTY_VALUE = "LinkTerminusProperty";
	public static final String RICH_TEXT_PROPERTY_VALUE = "richtextvalue";
	public static final BigDecimal NUMBER_VALUE_TEN = new BigDecimal ( 10 ) ;
	public static final String NUMBER_PROPERTY_NAME = "numberProperty" ;
	public static final String TEXT_PROPERTY_NAME = "textProperty" ;
	public static final String RICH_TEXT_PROPERTY_NAME = "richTextProperty" ;
	public static final String LIST_PROPERTY_NAME = "listProperty" ;
	public static final int CREATION_SERIAL = 7;
	public static final String TEXT_PROPERTY_VALUE = "text property value" ;
	public static final String VALUE_TO_ENTER_1 = "value1" ;
	public static final String VALUE_TO_ENTER_2 = "value2" ;
	
	private static final String ADDED_NUMBER_PROPERTY_DATA = "integrationTest/DbLinkTerminusPropertyTestData/DbAddedNumberProperyRefData.xml";
	private static final String ADDED_TEXT_PROPERTY_DATA = "integrationTest/DbLinkTerminusPropertyTestData/AddedTextPropertyRefData.xml";
	private static final String ADDED_RICH_TEXT_PROPERTY_DATA = "integrationTest/DbLinkTerminusPropertyTestData/AddedRichTextPropertyRefData.xml";
	private static final String ADDED_LIST_PROPERTY_DATA = "integrationTest/DbLinkTerminusPropertyTestData/AddedListPropertyRefData.xml";
	private static final String REMOVED_PROPERTY_DATA = "integrationTest/DbLinkTerminusPropertyTestData/RemovedPropertyRefData.xml";
	
	
	@Test
	public void testLoadLinkProperty () throws Exception 
	{
		doSetup() ;
		
		Query retreivedLinkTerminusProperty = getSession().createQuery("from HibTextProperty where id='100007'" ) ;
		HibTextProperty dbLinkTerminusProperty = (HibTextProperty) retreivedLinkTerminusProperty.uniqueResult() ;
		
		assertEquals ( "property value" , LOADED_TEXT_PROPERTY_VALUE , dbLinkTerminusProperty.getTextValue()) ;
		
	}
	
	@Test
	public void testAddTextProperty () throws Exception
	{
		doSetup() ;
		
		Query retreivedLinkTerminus = getSession().createQuery( "From HibLinkTerminus where id='100001'") ;
		HibLinkTerminus dbLinkTerminus = (HibLinkTerminus) retreivedLinkTerminus.uniqueResult() ;
		
		HibTextProperty textProperty = new HibTextProperty ( (HibCanvas)dbLinkTerminus.getLink().getCanvas() , CREATION_SERIAL , TEXT_PROPERTY_VALUE) ;
		
		dbLinkTerminus.addProperty(TEXT_PROPERTY_NAME , textProperty ) ;
		
		getSession().save(textProperty) ;
		getSession().saveOrUpdate(dbLinkTerminus) ;
		getSession().getTransaction().commit() ;
		
		IDataSet expectedDeltas = new XmlDataSet(new FileInputStream(
				ADDED_TEXT_PROPERTY_DATA));
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
	public void testAddRichTextProperty () throws Exception
	{
		doSetup() ;
		
		Query retreivedLinkTerminus = getSession().createQuery( "From HibLinkTerminus where id='100001'") ;
		HibLinkTerminus dbLinkTerminus = (HibLinkTerminus) retreivedLinkTerminus.uniqueResult() ;
		
		HibRichTextProperty textProperty = new HibRichTextProperty ((HibCanvas)dbLinkTerminus.getLink().getCanvas() , CREATION_SERIAL , RICH_TEXT_PROPERTY_VALUE ) ;
		
		dbLinkTerminus.addProperty(RICH_TEXT_PROPERTY_NAME , textProperty ) ;
		
		getSession().save(textProperty) ;
		getSession().saveOrUpdate(dbLinkTerminus) ;
		getSession().getTransaction().commit() ;
		
		IDataSet expectedDeltas = new XmlDataSet(new FileInputStream(
				ADDED_RICH_TEXT_PROPERTY_DATA));
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
	public void testAddNumberProperty () throws Exception
	{
		doSetup() ;
		
		Query retreivedLinkTerminus = getSession().createQuery( "From HibLinkTerminus where id='100001'") ;
		HibLinkTerminus dbLinkTerminus = (HibLinkTerminus) retreivedLinkTerminus.uniqueResult() ;
		
		HibNumberProperty textProperty = new HibNumberProperty ( (HibCanvas) dbLinkTerminus.getLink().getCanvas() , CREATION_SERIAL , NUMBER_VALUE_TEN ) ;
		
		dbLinkTerminus.addProperty(NUMBER_PROPERTY_NAME , textProperty ) ;
		
		getSession().save(textProperty) ;
		getSession().saveOrUpdate(dbLinkTerminus) ;
		getSession().getTransaction().commit() ;
		
		IDataSet expectedDeltas = new XmlDataSet(new FileInputStream(
				ADDED_NUMBER_PROPERTY_DATA));
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
	public void testAddNewListProperty () throws Exception 
	{
		doSetup() ;
		Query retreivedLinkTerminus = getSession().createQuery( "From HibLinkTerminus where id='100001'") ;
		HibLinkTerminus dbLinkTerminus = (HibLinkTerminus) retreivedLinkTerminus.uniqueResult() ;
		
		List <String> valuesToEnter = new ArrayList<String> (2) ; 
		valuesToEnter.add(VALUE_TO_ENTER_1);
		valuesToEnter.add(VALUE_TO_ENTER_2);
		
		HibListProperty listProperty = new HibListProperty ( (HibCanvas) dbLinkTerminus.getLink().getCanvas() , CREATION_SERIAL ,valuesToEnter ) ;

		
		dbLinkTerminus.addProperty(LIST_PROPERTY_NAME , listProperty ) ;
		
		getSession().save(listProperty) ;
		getSession().saveOrUpdate(dbLinkTerminus) ;
		getSession().getTransaction().commit() ;
		
		
		IDataSet expectedDeltas = new XmlDataSet(new FileInputStream(
				ADDED_LIST_PROPERTY_DATA));
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
	public void removePropertyFromShape () throws Exception 
	{
		doSetup() ;
		Query retreivedLinkTerminus = getSession().createQuery( "From HibLinkTerminus where id='100001'") ;
		HibLinkTerminus dbLinkTerminus = (HibLinkTerminus) retreivedLinkTerminus.uniqueResult() ;
		
		dbLinkTerminus.removeProperty("LinkTerminusPropertyName") ;
		
		getSession().saveOrUpdate(dbLinkTerminus) ;
		getSession().getTransaction().commit();
		
		IDataSet expectedDeltas = new XmlDataSet(new FileInputStream(
				REMOVED_PROPERTY_DATA));
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
