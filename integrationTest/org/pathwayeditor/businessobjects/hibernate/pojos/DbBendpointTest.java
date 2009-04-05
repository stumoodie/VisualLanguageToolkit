/*
Copyright 2009, Court of the University of Edinburgh

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License. 
*/
/**
 * 
 */
package org.pathwayeditor.businessobjects.hibernate.pojos;

import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Test;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.Location;
import org.pathwayeditor.testutils.PojoTester;

/**
 * @author ntsorman
 *
 */
public class DbBendpointTest extends PojoTester{
	
//	private static final int INDEX = 2 ;
	private static final int POSITION = 45 ;
	private static final Location BP_LOCATION = new Location(POSITION, POSITION);
	private static final Location BP_REL_DIM = new Location(POSITION, POSITION);
	
	private static final String ADDED_BENDPOINT_DATA = "integrationTest/DbBendpointTestData/DbAddedBendPointRefData.xml" ;
	private static final String DELETED_BENDPOINT_DATA = "integrationTest/DbBendpointTestData/DbDeletedBendPointRefData.xml" ;
	private static final String SOURCE_DATA_FILE = "integrationTest/DbSourceData/DbSourceDataRefData.xml";
	private static final int EXPECTED_BP_IDX = 0;
	
	@Test
	public void testAddBendPoint () throws Exception 
	{
		Session sess = getHibFactory().getCurrentSession();
		sess.beginTransaction();
		Query retreivedContext = sess.createQuery("from HibLinkAttribute where id = '100004'" ) ;
		HibLinkAttribute dbLinkAttribute = (HibLinkAttribute) retreivedContext.uniqueResult() ;
		
		dbLinkAttribute.createNewBendPoint(BP_LOCATION, BP_REL_DIM, BP_REL_DIM) ;
		
		sess.saveOrUpdate(dbLinkAttribute) ;
		sess.getTransaction().commit() ;
		
		compareDatabase(SOURCE_DATA_FILE, ADDED_BENDPOINT_DATA);
	}
	
	@Test
	public void testDeleteBendPoint () throws Exception
	{
		Session sess = getHibFactory().getCurrentSession();
		sess.beginTransaction();
		Query retreivedAtt = sess.createQuery("from HibLinkAttribute where id = '100004'" ) ;
		HibLinkAttribute dbLinkAttribute = (HibLinkAttribute) retreivedAtt.uniqueResult() ;
		dbLinkAttribute.removeBendPoint(EXPECTED_BP_IDX);
		sess.getTransaction().commit() ;
		this.compareDatabase(DELETED_BENDPOINT_DATA);
	}
	
	@Override
	protected String getDbUnitDataFilePath() {
		return SOURCE_DATA_FILE;
	}
}
