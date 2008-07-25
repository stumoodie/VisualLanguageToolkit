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
public class DbShapePropertiesTest extends PojoTester {
	
	
	public static final String LOADED_TEXT_PROPERTY_VALUE = "ShapeProperty";
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
	
	private static final String ADDED_NUMBER_PROPERTY_DATA = "integrationTest/DbShapePropertyTestData/DbAddedNumberProperyRefData.xml";
	private static final String ADDED_TEXT_PROPERTY_DATA = "integrationTest/DbShapePropertyTestData/AddedTextPropertyRefData.xml";
	private static final String ADDED_RICH_TEXT_PROPERTY_DATA = "integrationTest/DbShapePropertyTestData/AddedRichTextPropertyRefData.xml";
	private static final String ADDED_LIST_PROPERTY_DATA = "integrationTest/DbShapePropertyTestData/AddedListPropertyRefData.xml";
	private static final String REMOVED_PROPERTY_DATA = "integrationTest/DbShapePropertyTestData/RemovedPropertyRefData.xml";
	private static final String DELETED_SHAPE_AND_PROPERTY_DATA = "integrationTest/DbShapePropertyTestData/DbDeletedShapeAndPropertyRefData.xml";

	
	@Test
	public void testLoadLinkProperty () throws Exception 
	{
		doSetup() ;
		
		Query retreivedShapeProperty = getSession().createQuery("from HibTextProperty where id='100006'" ) ;
		HibTextProperty dbLinkProperty = (HibTextProperty) retreivedShapeProperty.uniqueResult() ;
		
		assertEquals ( "property value" , LOADED_TEXT_PROPERTY_VALUE , dbLinkProperty.getTextValue()) ;
		
	}
	
	@Test
	public void testAddTextProperty () throws Exception
	{
		doSetup() ;
		
		Query retreivedShape = getSession().createQuery( "From HibShape where id='100001'") ;
		HibShape dbShape = (HibShape) retreivedShape.uniqueResult() ;
		
		HibTextProperty textProperty = new HibTextProperty ( ) ;
		textProperty.setTextValue(TEXT_PROPERTY_VALUE) ;
		textProperty.setCreationSerial(CREATION_SERIAL);
		textProperty.setCanvas(dbShape.getCanvas()) ;
		
		dbShape.addProperty(TEXT_PROPERTY_NAME , textProperty ) ;
		
		getSession().save(textProperty) ;
		getSession().saveOrUpdate(dbShape) ;
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
		
		Query retreivedShape = getSession().createQuery( "From HibShape where id='100001'") ;
		HibShape dbShape = (HibShape) retreivedShape.uniqueResult() ;
		
		HibRichTextProperty textProperty = new HibRichTextProperty ( ) ;
		textProperty.setRichTextValue(RICH_TEXT_PROPERTY_VALUE) ;
		textProperty.setCreationSerial(CREATION_SERIAL);
		textProperty.setCanvas(dbShape.getCanvas()) ;
		
		dbShape.addProperty(RICH_TEXT_PROPERTY_NAME , textProperty ) ;
		
		getSession().save(textProperty) ;
		getSession().saveOrUpdate(dbShape) ;
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
		
		Query retreivedShape = getSession().createQuery( "From HibShape where id='100001'") ;
		HibShape dbShape = (HibShape) retreivedShape.uniqueResult() ;
		
		HibNumberProperty textProperty = new HibNumberProperty ( ) ;
		textProperty.setNumberValue(NUMBER_VALUE_TEN) ;
		textProperty.setCreationSerial(CREATION_SERIAL);
		textProperty.setCanvas(dbShape.getCanvas()) ;
		
		dbShape.addProperty(NUMBER_PROPERTY_NAME , textProperty ) ;
		
		getSession().save(textProperty) ;
		getSession().saveOrUpdate(dbShape) ;
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
		Query retreivedShape = getSession().createQuery( "From HibShape where id='100001'") ;
		HibShape dbShape = (HibShape) retreivedShape.uniqueResult() ;
		
		HibListProperty numberProperty = new HibListProperty ( ) ;
		
		List <String> valuesToEnter = new ArrayList<String> (2) ; 
		valuesToEnter.add(VALUE_TO_ENTER_1);
		valuesToEnter.add(VALUE_TO_ENTER_2);
		
		
		numberProperty.setValues(valuesToEnter) ;
		numberProperty.setCreationSerial(CREATION_SERIAL);
		numberProperty.setCanvas(dbShape.getCanvas()) ;
		
		dbShape.addProperty(LIST_PROPERTY_NAME , numberProperty ) ;
		
		getSession().save(numberProperty) ;
		getSession().saveOrUpdate(dbShape) ;
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
		Query retreivedShape = getSession().createQuery( "From HibShape where id='100001'") ;
		HibShape dbShape = (HibShape) retreivedShape.uniqueResult() ;
		
		dbShape.removeProperty("ShapePropertyName") ;
		
		getSession().saveOrUpdate(dbShape) ;
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
	public void testDeletePropertyWithShape () throws Exception 
	{
		doSetup() ;
		Query retreivedShape = getSession().createQuery( "From HibShape where id='100001'") ;
		HibShape dbShape = (HibShape) retreivedShape.uniqueResult() ;
		
		getSession().delete(dbShape) ;
		getSession().getTransaction().commit() ;
		
		IDataSet expectedDeltas = new XmlDataSet(new FileInputStream(
				DELETED_SHAPE_AND_PROPERTY_DATA));
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
