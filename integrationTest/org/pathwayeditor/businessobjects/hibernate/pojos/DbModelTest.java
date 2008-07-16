/**
 * 
 */
package org.pathwayeditor.businessobjects.hibernate.pojos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Ignore;
import org.junit.Test;
import org.pathwayeditor.testutils.PojoTester;

/**
 * @author nhanlon Unit tests for HibModel class
 * 
 */
public class DbModelTest extends PojoTester {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pathwayeditor.testutils.PojoTester#getDbUnitDataFilePath()
	 */
	@Override
	protected String getDbUnitDataFilePath() {
		return "integrationTest/DbCompoundGraphTestData/ModelData.xml";
	}
	
	@Ignore
	@Test
	public void testSetRootNode() throws Exception{
		doSetup();
		HibModel graph= (HibModel) getSession().createQuery ( "From HibModel graph").uniqueResult();
		assertNull(graph.getRootNode());
		HibCompoundNode node = new HibCompoundNode();
		graph.setRootNode(node);
		saveAndCommit(graph);
		startNewTransaction();
		graph= (HibModel) getSession().createQuery ( "From HibModel graph ").uniqueResult();
		assertEquals(node,graph.getRootNode());
	}

}
