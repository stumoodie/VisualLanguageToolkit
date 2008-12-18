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
import org.hibernate.Session;
import org.junit.Ignore;
import org.junit.Test;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IHtmlPropertyDefinition;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IListPropertyDefinition;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.INumberPropertyDefinition;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPlainTextPropertyDefinition;
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
		Session sess = getHibFactory().getCurrentSession();
		sess.beginTransaction();
		Query retreivedLinkTerminusProperty = sess.createQuery("from HibTextProperty where id='100007'" ) ;
		HibTextProperty dbLinkTerminusProperty = (HibTextProperty) retreivedLinkTerminusProperty.uniqueResult() ;
		assertEquals ( "property value" , LOADED_TEXT_PROPERTY_VALUE , dbLinkTerminusProperty.getValue()) ;
		sess.getTransaction().commit();
	}
	
	@Ignore @Test
	public void testAddTextProperty () throws Exception
	{
		Session sess = getHibFactory().getCurrentSession();
		sess.beginTransaction();
		Query retreivedLinkTerminus = sess.createQuery( "From HibLinkTerminus where id=100001") ;
		HibLinkTerminus dbLinkTerminus = (HibLinkTerminus) retreivedLinkTerminus.uniqueResult() ;
		IPlainTextPropertyDefinition defn = null;
		
		HibTextProperty textProperty = new HibTextProperty (dbLinkTerminus.getOwningLink().getCanvas() , CREATION_SERIAL , defn) ;
		
		dbLinkTerminus.addProperty(TEXT_PROPERTY_NAME , textProperty ) ;
		
		sess.save(textProperty) ;
		sess.saveOrUpdate(dbLinkTerminus) ;
		sess.getTransaction().commit() ;
		
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
	
	@Ignore @Test
	public void testAddRichTextProperty () throws Exception
	{
		Session sess = getHibFactory().getCurrentSession();
		sess.beginTransaction();
		Query retreivedLinkTerminus = sess.createQuery( "From HibLinkTerminus where id='100001'") ;
		HibLinkTerminus dbLinkTerminus = (HibLinkTerminus) retreivedLinkTerminus.uniqueResult() ;
		IHtmlPropertyDefinition defn = null;
		
		HibRichTextProperty textProperty = new HibRichTextProperty ((HibCanvas)dbLinkTerminus.getOwningLink().getCanvas() , CREATION_SERIAL , defn) ;
		
		dbLinkTerminus.addProperty(RICH_TEXT_PROPERTY_NAME , textProperty ) ;
		
		sess.save(textProperty) ;
		sess.saveOrUpdate(dbLinkTerminus) ;
		sess.getTransaction().commit() ;
		
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
	
	@Ignore @Test
	public void testAddNumberProperty () throws Exception
	{
		Session sess = getHibFactory().getCurrentSession();
		sess.beginTransaction();
		Query retreivedLinkTerminus = sess.createQuery( "From HibLinkTerminus where id='100001'") ;
		HibLinkTerminus dbLinkTerminus = (HibLinkTerminus) retreivedLinkTerminus.uniqueResult() ;
		INumberPropertyDefinition defn = null;
		
		HibNumberProperty textProperty = new HibNumberProperty ( (HibCanvas) dbLinkTerminus.getOwningLink().getCanvas() , CREATION_SERIAL , defn) ;
		
		dbLinkTerminus.addProperty(NUMBER_PROPERTY_NAME , textProperty ) ;
		
		sess.save(textProperty) ;
		sess.saveOrUpdate(dbLinkTerminus) ;
		sess.getTransaction().commit() ;
		
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
	
	@Ignore @Test
	public void testAddNewListProperty () throws Exception 
	{
		Session sess = getHibFactory().getCurrentSession();
		sess.beginTransaction();
		Query retreivedLinkTerminus = sess.createQuery( "From HibLinkTerminus where id='100001'") ;
		HibLinkTerminus dbLinkTerminus = (HibLinkTerminus) retreivedLinkTerminus.uniqueResult() ;
		IListPropertyDefinition defn = null;
		
		List <String> valuesToEnter = new ArrayList<String> (2) ; 
		valuesToEnter.add(VALUE_TO_ENTER_1);
		valuesToEnter.add(VALUE_TO_ENTER_2);
		
		HibListProperty listProperty = new HibListProperty ( (HibCanvas) dbLinkTerminus.getOwningLink().getCanvas() , CREATION_SERIAL , defn) ;

		
		dbLinkTerminus.addProperty(LIST_PROPERTY_NAME , listProperty ) ;
		
		sess.save(listProperty) ;
		sess.saveOrUpdate(dbLinkTerminus) ;
		sess.getTransaction().commit() ;
		
		
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
	
	@Ignore @Test
	public void removePropertyFromShape () throws Exception 
	{
		Session sess = getHibFactory().getCurrentSession();
		sess.beginTransaction();
		Query retreivedLinkTerminus = sess.createQuery( "From HibLinkTerminus where id='100001'") ;
		HibLinkTerminus dbLinkTerminus = (HibLinkTerminus) retreivedLinkTerminus.uniqueResult() ;
		
		dbLinkTerminus.removeProperty("LinkTerminusPropertyName") ;
		
		sess.saveOrUpdate(dbLinkTerminus) ;
		sess.getTransaction().commit();
		
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
