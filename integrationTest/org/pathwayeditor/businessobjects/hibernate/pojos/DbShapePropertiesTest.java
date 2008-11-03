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
		Session sess = getHibFactory().getCurrentSession();
		sess.beginTransaction();
		Query retreivedShapeProperty = sess.createQuery("from HibTextProperty where id='100006'" ) ;
		HibTextProperty dbLinkProperty = (HibTextProperty) retreivedShapeProperty.uniqueResult() ;
		
		assertEquals ( "property value" , LOADED_TEXT_PROPERTY_VALUE , dbLinkProperty.getValue()) ;
		sess.getTransaction().commit();
	}
	
	@Ignore @Test
	public void testAddTextProperty () throws Exception
	{
		Session sess = getHibFactory().getCurrentSession();
		sess.beginTransaction();
		Query retreivedShape = sess.createQuery( "From HibShapeAttribute where id='100001'") ;
		HibShapeAttribute dbShape = (HibShapeAttribute) retreivedShape.uniqueResult() ;
		
		HibTextProperty textProperty = new HibTextProperty ( ) ;
		textProperty.setValue(TEXT_PROPERTY_VALUE) ;
		
		dbShape.addProperty(TEXT_PROPERTY_NAME , textProperty ) ;
		
		sess.save(textProperty) ;
		sess.saveOrUpdate(dbShape) ;
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
		Query retreivedShape = sess.createQuery( "From HibShapeAttribute where id='100001'") ;
		HibShapeAttribute dbShape = (HibShapeAttribute) retreivedShape.uniqueResult() ;
		
		HibRichTextProperty textProperty = new HibRichTextProperty ( ) ;
		textProperty.setValue(RICH_TEXT_PROPERTY_VALUE) ;
		
		dbShape.addProperty(RICH_TEXT_PROPERTY_NAME , textProperty ) ;
		
		sess.save(textProperty) ;
		sess.saveOrUpdate(dbShape) ;
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
		Query retreivedShape = sess.createQuery( "From HibShapeAttribute where id='100001'") ;
		HibShapeAttribute dbShape = (HibShapeAttribute) retreivedShape.uniqueResult() ;
		
		HibNumberProperty textProperty = new HibNumberProperty ( ) ;
		textProperty.setValue(NUMBER_VALUE_TEN) ;
		
		dbShape.addProperty(NUMBER_PROPERTY_NAME , textProperty ) ;
		
		sess.save(textProperty) ;
		sess.saveOrUpdate(dbShape) ;
		sess.getTransaction().commit() ;
//		
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
		Query retreivedShape = sess.createQuery( "From HibShapeAttribute where id='100001'") ;
		HibShapeAttribute dbShape = (HibShapeAttribute) retreivedShape.uniqueResult() ;
		
		HibListProperty numberProperty = new HibListProperty ( ) ;
		
		List <String> valuesToEnter = new ArrayList<String> (2) ; 
		numberProperty.addValue(VALUE_TO_ENTER_1);
		numberProperty.addValue(VALUE_TO_ENTER_2);
		
		dbShape.addProperty(LIST_PROPERTY_NAME , numberProperty ) ;
		
		sess.save(numberProperty) ;
		sess.saveOrUpdate(dbShape) ;
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
		Query retreivedShape = sess.createQuery( "From HibShapeAttribute where id='100001'") ;
		HibShapeAttribute dbShape = (HibShapeAttribute) retreivedShape.uniqueResult() ;
		
		dbShape.removeProperty("ShapePropertyName") ;
		
		sess.saveOrUpdate(dbShape) ;
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
	
	@Ignore
	@Test
	public void testDeletePropertyWithShape() throws Exception {
		Session sess = getHibFactory().getCurrentSession();
		sess.beginTransaction();
		Query retreivedShapeNode = sess
				.createQuery("From HibShapeNode where id='100002'");
		HibShapeNode dbShapeNode = (HibShapeNode) retreivedShapeNode
				.uniqueResult();

		sess.delete(dbShapeNode);
		sess.getTransaction().commit();

		sess.beginTransaction();
		Query retreivedProperty = sess
				.createQuery("From HibProperty where id='100001'");
		System.out.println(retreivedProperty.list().size());
		retreivedProperty = sess
				.createQuery("From HibProperty where id='100002'");
		System.out.println(retreivedProperty.list().size());
		retreivedProperty = sess
				.createQuery("From HibProperty where id='100003'");
		System.out.println(retreivedProperty.list().size());
		retreivedProperty = sess
				.createQuery("From HibProperty where id='100004'");
		System.out.println(retreivedProperty.list().size());
		retreivedProperty = sess
				.createQuery("From HibProperty where id='100005'");
		System.out.println(retreivedProperty.list().size());
		retreivedProperty = sess
				.createQuery("From HibProperty where id='100006'");
		System.out.println(retreivedProperty.list().size());
		retreivedProperty = sess
				.createQuery("From HibProperty where id='100007'");
		System.out.println(retreivedProperty.list().size());

		System.out.println("linkterminus");
		retreivedProperty = sess
				.createQuery("From HibLinkTerminus where id='100001'");
		System.out.println(retreivedProperty.list().size());

		System.out.println("linkAttribute");
		retreivedProperty = sess
				.createQuery("From HibLinkAttribute where id='100001'");
		System.out.println(retreivedProperty.list().size());

		sess.getTransaction().commit();
		IDataSet expectedDeltas = new XmlDataSet(new FileInputStream(
				DELETED_SHAPE_AND_PROPERTY_DATA));
		String testTables[] = expectedDeltas.getTableNames();
		IDataSet actualChanges = getConnection().createDataSet(testTables);
		IDataSet expectedChanges = new CompositeDataSet(expectedDeltas);
		for (String t : testTables) {
			ITable expectedTable = DefaultColumnFilter.includedColumnsTable(
					expectedChanges.getTable(t), expectedDeltas.getTable(t)
							.getTableMetaData().getColumns());
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
