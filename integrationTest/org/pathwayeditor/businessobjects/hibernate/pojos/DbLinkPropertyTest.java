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
//	private static final String REMOVED_PROPERTY_DATA = "integrationTest/DbLinkPropertyTestData/RemovedPropertyRefData.xml";
	private static final String DELETED_LINK_AND_PROPERTY_DATA = "integrationTest/DbLinkPropertyTestData/DbDeletedLinkAndPropertyRefData.xml";
	

	
	@Test
	public void testLoadShapeProperty () throws Exception 
	{
		Session sess = getHibFactory().getCurrentSession();
		sess.beginTransaction();
		Query retreivedLinkProperty = sess.createQuery("from HibTextProperty where id='100006'" ) ;
		HibTextProperty dbLinkProperty = (HibTextProperty) retreivedLinkProperty.uniqueResult() ;
		sess.getTransaction().commit();
		
		assertEquals ( "property value" , RETREIVED_TEXT_PROPERTY_VALUE , dbLinkProperty.getValue()) ;
		
	}
	
	@Ignore @Test
	public void testAddNewNumberProperty () throws Exception 
	{
		Session sess = getHibFactory().getCurrentSession();
		sess.beginTransaction();
		Query retreivedHibLink = sess.createQuery("from HibLinkAttribute where id='100001'") ;
		HibLinkAttribute dbLink = (HibLinkAttribute) retreivedHibLink.uniqueResult() ;
		INumberPropertyDefinition defn = null;
		
		HibNumberProperty numberProperty = new HibNumberProperty ( (HibCanvas)dbLink.getCanvas() , CREATION_SERIAL , defn) ;
		
		sess.save(numberProperty) ;
		sess.saveOrUpdate(dbLink) ;
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
	public void testAddNewTextProperty () throws Exception 
	{
		Session sess = getHibFactory().getCurrentSession();
		sess.beginTransaction();
		Query retreivedHibLink = sess.createQuery("from HibLinkAttribute where id='100001'") ;
		HibLinkAttribute dbLink = (HibLinkAttribute) retreivedHibLink.uniqueResult() ;
		IPlainTextPropertyDefinition defn = null;
		
		HibTextProperty textProperty = new HibTextProperty ( (HibCanvas)dbLink.getCanvas() , CREATION_SERIAL , defn) ;
		
		sess.save(textProperty) ;
		sess.saveOrUpdate(dbLink) ;
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
	public void testAddNewRichTextProperty () throws Exception 
	{
		Session sess = getHibFactory().getCurrentSession();
		sess.beginTransaction();
		Query retreivedHibLink = sess.createQuery("from HibLinkAttribute where id='100001'") ;
		HibLinkAttribute dbLink = (HibLinkAttribute) retreivedHibLink.uniqueResult() ;
		IHtmlPropertyDefinition defn = null;
		
		HibRichTextProperty richTextProperty = new HibRichTextProperty ( (HibCanvas)dbLink.getCanvas() , CREATION_SERIAL , defn) ;
		
		sess.save(richTextProperty) ;
		sess.saveOrUpdate(dbLink) ;
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
	public void testAddNewListProperty () throws Exception 
	{
		Session sess = getHibFactory().getCurrentSession();
		sess.beginTransaction();
		Query retreivedHibLink = sess.createQuery("from HibLinkAttribute where id='100001'") ;
		HibLinkAttribute dbLink = (HibLinkAttribute) retreivedHibLink.uniqueResult() ;
		IListPropertyDefinition defn = null;
		
		List <String> valuesToEnter = new ArrayList<String> (2) ; 
		valuesToEnter.add(VALUE_TO_ENTER_1);
		valuesToEnter.add(VALUE_TO_ENTER_2);
		
		HibListProperty listProperty = new HibListProperty ((HibCanvas)dbLink.getCanvas() , CREATION_SERIAL , defn) ;
		
//		listProperty.setValues(valuesToEnter) ;
//		listProperty.setCreationSerial(CREATION_SERIAL);
//		numberProperty.setCanvas() ;
		
		sess.save(listProperty) ;
		sess.saveOrUpdate(dbLink) ;
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
	public void testDeletePropertyWithLink () throws Exception 
	{
		Session sess = getHibFactory().getCurrentSession();
		sess.beginTransaction();
		Query retreivedLink = sess.createQuery( "From HibLinkAttribute where id='100001'") ;
		HibLinkAttribute dbLink = (HibLinkAttribute) retreivedLink.uniqueResult() ;
		
		sess.delete(dbLink) ;
		sess.getTransaction().commit() ;
		
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
