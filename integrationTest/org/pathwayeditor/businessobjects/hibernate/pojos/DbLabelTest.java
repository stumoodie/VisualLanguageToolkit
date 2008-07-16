/**
 * 
 */
package org.pathwayeditor.businessobjects.hibernate.pojos;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;
import org.pathwayeditor.testutils.PojoTester;

/**
 * @author nhanlon
 *
 */
public class DbLabelTest extends PojoTester {
	
	@Test
	public void testSetTextProperty() throws Exception{
		doSetup();
		HibLabel label= (HibLabel) getSession().createQuery ( "From HibLabel label join fetch label.visualisableProperty where label.id = 1").uniqueResult();
		HibTextProperty property = (HibTextProperty) label.getVisualisableProperty();
		assertEquals("chips",property.getTextValue());
		property.setTextValue("fish and chips");
		saveAndCommit(label);	
		startNewTransaction();
		label= (HibLabel) getSession().createQuery ( "From HibLabel label join fetch label.visualisableProperty where label.id=1").uniqueResult();
		property = (HibTextProperty) label.getVisualisableProperty();
		assertEquals("fish and chips",property.getTextValue());
	}

	@Test
	public void testSetRichTextProperty() throws Exception{
		doSetup();
		HibLabel label= (HibLabel) getSession().createQuery ( "From HibLabel label join fetch label.visualisableProperty where label.id = 4").uniqueResult();
		HibRichTextProperty property = (HibRichTextProperty) label.getVisualisableProperty();
		assertEquals("chips",property.getRichTextValue());
		property.setRichTextValue("fish and chips");
		saveAndCommit(label);	
		startNewTransaction();
		label= (HibLabel) getSession().createQuery ( "From HibLabel label join fetch label.visualisableProperty where label.id=4").uniqueResult();
		property = (HibRichTextProperty) label.getVisualisableProperty();
		assertEquals("fish and chips",property.getRichTextValue());
	}
	
	@Test
	public void testSetNumberProperty() throws Exception{
		doSetup();
		HibLabel label= (HibLabel) getSession().createQuery ( "From HibLabel label join fetch label.visualisableProperty where label.id = 2").uniqueResult();
		HibNumberProperty property = (HibNumberProperty) label.getVisualisableProperty();
		assertEquals(BigDecimal.ONE,property.getNumberValue());
		property.setNumberValue(new BigDecimal(2));
		saveAndCommit(label);	
		startNewTransaction();
		label= (HibLabel) getSession().createQuery ( "From HibLabel label join fetch label.visualisableProperty where label.id=2").uniqueResult();
		property = (HibNumberProperty) label.getVisualisableProperty();
		assertEquals(new BigDecimal(2),property.getNumberValue());
	}
	
	@Test
	public void testSetListProperty() throws Exception{
		doSetup();
		HibLabel label= (HibLabel) getSession().createQuery ( "From HibLabel label join fetch label.visualisableProperty where label.id = 3").uniqueResult();
		HibListProperty property = (HibListProperty) label.getVisualisableProperty();
		assertEquals("first in list",property.getValues().get(0));
		assertEquals("second in list",property.getValues().get(1));
		property.setValues(new ArrayList(Arrays.asList(new String [] {"two","one"})));
		saveAndCommit(label);	
		startNewTransaction();
		label= (HibLabel) getSession().createQuery ( "From HibLabel label join fetch label.visualisableProperty where label.id=3").uniqueResult();
		property = (HibListProperty) label.getVisualisableProperty();
		assertEquals("two",property.getValues().get(0));
		assertEquals("one",property.getValues().get(1));
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.testutils.PojoTester#getDbUnitDataFilePath()
	 */
	@Override
	protected String getDbUnitDataFilePath() {
		return "integrationTest/DbMapObjectTestData/LabelData.xml";
	}
}
