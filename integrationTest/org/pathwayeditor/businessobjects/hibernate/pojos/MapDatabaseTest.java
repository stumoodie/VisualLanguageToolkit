/**
 * 
 */
package org.pathwayeditor.businessobjects.hibernate.pojos;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.pathwayeditor.testutils.GenericTester;

/**
 * @author nhanlon
 *
 */
public class MapDatabaseTest extends GenericTester{
	private static final String JIMMY_KRANKIE = "JimmyKrankie";
	
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.testutils.PojoTester#getDbUnitDataFilePath()
	 */
	@Override
	protected String getDbUnitDataFilePath() {
		return "integrationTest/DbSourceData/DbSourceRepositoryRefData.xml";
	}
	
	@Test
	public void getOwnerHappyCase(){
		HibSubFolder sub1 = (HibSubFolder) getSession().createQuery("from HibSubFolder r where r.id = '100005'").uniqueResult();
		HibMapDiagram map = sub1.getMapDiagrams().iterator().next();
		getSession().close();
		assertEquals(sub1,map.getOwner());
	}
	
	@Test
	public void testGetName(){
		HibSubFolder sub1 = (HibSubFolder) getSession().createQuery("from HibSubFolder r where r.id = '100005'").uniqueResult();
		HibMapDiagram map = sub1.getMapDiagrams().iterator().next();
		getSession().close();
		assertEquals("Diagram name", map.getName());
	}
}
