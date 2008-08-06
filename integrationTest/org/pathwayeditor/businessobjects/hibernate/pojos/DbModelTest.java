/**
 * 
 */
package org.pathwayeditor.businessobjects.hibernate.pojos;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.pathwayeditor.testutils.GenericTester;

/**
 * @author nhanlon Unit tests for HibModel class
 * 
 */
public class DbModelTest extends GenericTester {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pathwayeditor.testutils.PojoTester#getDbUnitDataFilePath()
	 */
	@Override
	protected String getDbUnitDataFilePath() {
		return "integrationTest/DbCompoundGraphTestData/ModelData.xml";
	}
	
	@Test
	public void testSetRootNode() throws Exception{
		HibModel graph= (HibModel) getSession().createQuery ( "From HibModel graph").uniqueResult();
		HibRootObjectNode node = new HibRootObjectNode();
		graph.setRootNode(node);
		saveAndCommit(graph);
		graph= (HibModel) getSession().createQuery ( "From HibModel graph ").uniqueResult();
		assertEquals(node,graph.getRootNode());
	}

}
