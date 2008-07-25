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
public class DbLinkPropertyTest extends PojoTester {

	public static final String RETREIVED_TEXT_PROPERTY_VALUE = "ShapeProperty";
	public static final BigDecimal NUMBER_VALUE_TEN = new BigDecimal ( 10 ) ;
	public static final String NUMBER_PROPERTY_NAME = "numberProperty" ;
	public static final String TEXT_PROPERTY_NAME = "textProperty" ;
	public static final String RICH_TEXT_PROPERTY_NAME = "richTextProperty" ;
	public static final String LIST_PROPERTY_NAME = "listProperty" ;
	public static final int CREATION_SERIAL = 7 ;
	public static final String TEXT_PROPERTY_VALUE = "text property value" ;
	public static final String VALUE_TO_ENTER_1 = "value1" ;
	public static final String VALUE_TO_ENTER_2 = "value2" ;
	
	private static final String ADDED_NUMBER_PROPERTY_DATA = "integrationTest/DbLinkPropertyTestData/DbAddedNumberProperyRefData.xml";
	private static final String ADDED_TEXT_PROPERTY_DATA = "integrationTest/DbLinkPropertyTestData/AddedTextPropertyRefData.xml";
	private static final String ADDED_RICH_TEXT_PROPERTY_DATA = "integrationTest/DbLinkPropertyTestData/AddedRichTextPropertyRefData.xml";
	private static final String ADDED_LIST_PROPERTY_DATA = "integrationTest/DbLinkPropertyTestData/AddedListPropertyRefData.xml";
	private static final String REMOVED_PROPERTY_DATA = "integrationTest/DbLinkPropertyTestData/RemovedPropertyRefData.xml";
	private static final String DELETED_LINK_AND_PROPERTY_DATA = "integrationTest/DbLinkPropertyTestData/DbDeletedLinkAndPropertyRefData.xml";
	

	
	@Test
	public void testLoadShapeProperty () throws Exception 
	{
		doSetup() ;
		
		Query retreivedLinkProperty = getSession().createQuery("from HibTextProperty where id='100006'" ) ;
		HibTextProperty dbLinkProperty = (HibTextProperty) retreivedLinkProperty.uniqueResult() ;
		
		assertEquals ( "property value" , RETREIVED_TEXT_PROPERTY_VALUE , dbLinkProperty.getTextValue()) ;
		
	}
	
	@Test
	public void testAddNewNumberProperty () throws Exception 
	{
		doSetup () ;
		
		Query retreivedHibLink = getSession().createQuery("from HibLink where id='100001'") ;
		HibLink dbLink = (HibLink) retreivedHibLink.uniqueResult() ;
		
		HibNumberProperty numberProperty = new HibNumberProperty ( ) ;
		numberProperty.setNumberValue(NUMBER_VALUE_TEN) ;
		numberProperty.setCreationSerial(CREATION_SERIAL);
		numberProperty.setCanvas(dbLink.getCanvas()) ;
		
		dbLink.addLinkProperty("NUMBER_PROPERTY_NAME" , numberProperty ) ;
		
		getSession().save(numberProperty) ;
		getSession().saveOrUpdate(dbLink) ;
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
	public void testAddNewTextProperty () throws Exception 
	{
		doSetup() ;
		Query retreivedHibLink = getSession().createQuery("from HibLink where id='100001'") ;
		HibLink dbLink = (HibLink) retreivedHibLink.uniqueResult() ;
		
		HibTextProperty textProperty = new HibTextProperty ( ) ;
		textProperty.setTextValue(TEXT_PROPERTY_VALUE) ;
		textProperty.setCreationSerial(CREATION_SERIAL);
		textProperty.setCanvas(dbLink.getCanvas()) ;
		
		dbLink.addLinkProperty("TEXT_PROPERTY_NAME" , textProperty ) ;
		
		getSession().save(textProperty) ;
		getSession().saveOrUpdate(dbLink) ;
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
	public void testAddNewRichTextProperty () throws Exception 
	{
		doSetup() ;
		Query retreivedHibLink = getSession().createQuery("from HibLink where id='100001'") ;
		HibLink dbLink = (HibLink) retreivedHibLink.uniqueResult() ;
		
		HibRichTextProperty richTextProperty = new HibRichTextProperty ( ) ;
		richTextProperty.setRichTextValue(TEXT_PROPERTY_VALUE) ;
		richTextProperty.setCreationSerial(CREATION_SERIAL);
		richTextProperty.setCanvas(dbLink.getCanvas()) ;
		
		dbLink.addLinkProperty("RICH_TEXT_PROPERTY_NAME" , richTextProperty ) ;
		
		getSession().save(richTextProperty) ;
		getSession().saveOrUpdate(dbLink) ;
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
	public void testAddNewListProperty () throws Exception 
	{
		doSetup() ;
		Query retreivedHibLink = getSession().createQuery("from HibLink where id='100001'") ;
		HibLink dbLink = (HibLink) retreivedHibLink.uniqueResult() ;
		
		HibListProperty numberProperty = new HibListProperty ( ) ;
		
		List <String> valuesToEnter = new ArrayList<String> (2) ; 
		valuesToEnter.add(VALUE_TO_ENTER_1);
		valuesToEnter.add(VALUE_TO_ENTER_2);
		
		
		numberProperty.setValues(valuesToEnter) ;
		numberProperty.setCreationSerial(CREATION_SERIAL);
		numberProperty.setCanvas(dbLink.getCanvas()) ;
		
		dbLink.addLinkProperty("LIST_PROPERTY_NAME" , numberProperty ) ;
		
		getSession().save(numberProperty) ;
		getSession().saveOrUpdate(dbLink) ;
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
	public void removePropertyFromLink () throws Exception 
	{
		doSetup() ;
		Query retreivedLink = getSession().createQuery( "From HibLink where id='100001'") ;
		HibLink dbLink = (HibLink) retreivedLink.uniqueResult() ;
		
		dbLink.removeLinkProperty("linkpropertyname") ;
		
		
		getSession().saveOrUpdate(dbLink) ;
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
	
	@Test
	public void testDeletePropertyWithLink () throws Exception 
	{
		doSetup() ;
		Query retreivedLink = getSession().createQuery( "From HibLink where id='100001'") ;
		HibLink dbLink = (HibLink) retreivedLink.uniqueResult() ;
		
		getSession().delete(dbLink) ;
		getSession().getTransaction().commit() ;
		
		IDataSet expectedDeltas = new XmlDataSet(new FileInputStream(
				DELETED_LINK_AND_PROPERTY_DATA));
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
