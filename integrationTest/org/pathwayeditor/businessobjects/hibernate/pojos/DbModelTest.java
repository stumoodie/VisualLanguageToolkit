/**
 * 
 */
package org.pathwayeditor.businessobjects.hibernate.pojos;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.pathwayeditor.testutils.GenericTester;

/**
 * @author nhanlon Unit tests for HibCompoundGraph class
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
		HibCompoundGraph graph= (HibCompoundGraph) getSession().createQuery ( "From HibCompoundGraph graph").uniqueResult();
		HibRootObjectNode node = new HibRootObjectNode();
		graph.setRootNode(node);
		saveAndCommit(graph);
		graph= (HibCompoundGraph) getSession().createQuery ( "From HibCompoundGraph graph ").uniqueResult();
		assertEquals(node,graph.getRootNode());
	}

}
