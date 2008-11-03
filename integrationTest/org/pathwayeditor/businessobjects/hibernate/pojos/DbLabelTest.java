/**
 * 
 */
package org.pathwayeditor.businessobjects.hibernate.pojos;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.hibernate.Session;
import org.junit.Ignore;
import org.junit.Test;
import org.pathwayeditor.testutils.PojoTester;

/**
 * @author nhanlon
 *
 */
public class DbLabelTest extends PojoTester{
	
	@Ignore @Test
	public void testSetTextProperty() throws Exception{
		Session sess = getHibFactory().getCurrentSession();
		sess.beginTransaction();
		HibLabelAttribute label= (HibLabelAttribute) sess.createQuery ( "From HibLabelAttribute label join fetch label.visualisableProperty where label.id = 100001").uniqueResult();
		HibTextProperty property = (HibTextProperty) label.getVisualisableProperty();
		assertEquals("chips",property.getValue());
		property.setValue("fish and chips");
		sess.saveOrUpdate(label);
		sess.getTransaction().commit();
		sess = getHibFactory().getCurrentSession();
		sess.beginTransaction();
		label= (HibLabelAttribute) sess.createQuery ( "From HibLabelAttribute label join fetch label.visualisableProperty where label.id=100001").uniqueResult();
		property = (HibTextProperty) label.getVisualisableProperty();
		assertEquals("fish and chips",property.getValue());
		sess.getTransaction().commit();
	}

	@Ignore @Test
	public void testSetRichTextProperty() throws Exception{
		Session sess = getHibFactory().getCurrentSession();
		sess.beginTransaction();
		HibLabelAttribute label= (HibLabelAttribute) sess.createQuery ( "From HibLabelAttribute label join fetch label.visualisableProperty where label.id = 100004").uniqueResult();
		HibRichTextProperty property = (HibRichTextProperty) label.getVisualisableProperty();
		assertEquals("chips",property.getRichTextValue());
		property.setValue("fish and chips");
		sess.saveOrUpdate(label);	
		sess.getTransaction().commit();
		sess = getHibFactory().getCurrentSession();
		sess.beginTransaction();
		label= (HibLabelAttribute) sess.createQuery ( "From HibLabelAttribute label join fetch label.visualisableProperty where label.id=100004").uniqueResult();
		property = (HibRichTextProperty) label.getVisualisableProperty();
		assertEquals("fish and chips",property.getRichTextValue());
		sess.getTransaction().commit();
	}
	
	@Ignore @Test
	public void testSetNumberProperty() throws Exception{
		Session sess = getHibFactory().getCurrentSession();
		sess.beginTransaction();
		HibLabelAttribute label= (HibLabelAttribute) sess.createQuery ( "From HibLabelAttribute label join fetch label.visualisableProperty where label.id = 100002").uniqueResult();
		HibNumberProperty property = (HibNumberProperty) label.getVisualisableProperty();
		assertEquals(BigDecimal.ONE,property.getValue());
		property.setValue(new BigDecimal(2));
		sess.saveOrUpdate(label);
		sess.getTransaction().commit();
		sess.beginTransaction();
		label= (HibLabelAttribute) sess.createQuery ( "From HibLabelAttribute label join fetch label.visualisableProperty where label.id=100002").uniqueResult();
		property = (HibNumberProperty) label.getVisualisableProperty();
		assertEquals(new BigDecimal(2),property.getValue());
		sess.getTransaction().commit();
	}
	
	@Ignore @Test
	public void testSetListProperty() throws Exception{
		Session sess = getHibFactory().getCurrentSession();
		sess.beginTransaction();
		HibLabelAttribute label= (HibLabelAttribute) sess.createQuery ( "From HibLabelAttribute label join fetch label.visualisableProperty where label.id = 100003").uniqueResult();
		HibListProperty property = (HibListProperty) label.getVisualisableProperty();
		assertEquals("first in list",property.getValue().get(0));
		assertEquals("second in list",property.getValue().get(1));
		property.addValue("two");
		property.addValue("one");
		sess.saveOrUpdate(label);
		sess.getTransaction().commit();
		sess.beginTransaction();
		label= (HibLabelAttribute) sess.createQuery ( "From HibLabelAttribute label join fetch label.visualisableProperty where label.id=100003").uniqueResult();
		property = (HibListProperty) label.getVisualisableProperty();
		assertEquals("two",property.getValue().get(0));
		assertEquals("one",property.getValue().get(1));
		sess.getTransaction().commit();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.testutils.PojoTester#getDbUnitDataFilePath()
	 */
	@Override
	protected String getDbUnitDataFilePath() {
		return "integrationTest/DbSourceData/DbSourceDataRefData.xml";
	}
}
