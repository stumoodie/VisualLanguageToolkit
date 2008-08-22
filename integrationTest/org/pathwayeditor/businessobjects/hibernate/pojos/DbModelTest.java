/**
 * 
 */
package org.pathwayeditor.businessobjects.hibernate.pojos;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.pathwayeditor.testutils.PojoTester;

/**
 * @author nhanlon Unit tests for HibCompoundGraph class
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
		return "integrationTest/DbSourceData/DbSourceDataRefData.xml";
	}
	
	@Test
	public void testSetRootNode() throws Exception{
		doSetup() ;
		//TODO change method 
		HibCompoundGraph graph= (HibCompoundGraph) getSession().createQuery ( "From HibCompoundGraph where id='100001'").uniqueResult();
		HibRootObjectNode node = new HibRootObjectNode();
		HibRootObjectNode old = (HibRootObjectNode) graph.getRootNode() ;
		
		graph.changeRootNode(node);
		getSession().delete(old);
		saveAndCommit(graph);
		graph= (HibCompoundGraph) getSession().createQuery ( "From HibCompoundGraph where id='100001'").uniqueResult();
		assertEquals(node,graph.getRootNode());
	}

}
