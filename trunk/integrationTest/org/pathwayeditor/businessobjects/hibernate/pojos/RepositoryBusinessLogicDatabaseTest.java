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
public class RepositoryBusinessLogicDatabaseTest extends GenericTester {

	
	@Test
	public void testGetName(){
		HibRepository rep =  (HibRepository) getSession().createQuery("from HibRepository r where r.id = '100001'").uniqueResult();
		getSession().close();
		assertEquals("repo name",rep.getName());
	}
	@Test
	public void testGetDescription(){
		HibRepository rep =  (HibRepository) getSession().createQuery("from HibRepository r where r.id = '100001'").uniqueResult();
		getSession().close();
		assertEquals("repository description",rep.getDescription());
	}
	@Test
	public void testGetRootFolder(){
		
	}
	
	@Test
	public void testNewRepositoryHasRootFolder(){
		
	}
	
	@Test
	public void testGetSchemaBuildNum(){
		
	}
	@Test
	public void testEquals(){
		
	}
	
	
	
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.testutils.GenericTester#getDbUnitDataFilePath()
	 */
	@Override
	protected String getDbUnitDataFilePath() {
		return "integrationTest/DbSourceData/DbSourceRepositoryRefData.xml";
	}

}
