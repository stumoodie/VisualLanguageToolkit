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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Test;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LinkTermType;
import org.pathwayeditor.testutils.PojoTester;

/**
 * @author ntsorman
 *
 */
public class DbHibLinkTerminusTest extends PojoTester{
	
	private static final LinkTermType EXPECTED_SRC_TYPE = LinkTermType.SOURCE; 
	private static final int EXPECTED_OFFSET_VALUE = 10 ;
	
	@Test
	public void testLoadLinkTerminus () throws Exception
	{
		Session sess = getHibFactory().getCurrentSession();
		sess.beginTransaction();
		Query retreivedLinkTerminus = sess.createQuery( "From HibLinkTerminus where id='100009'") ;
		HibLinkTerminus dbLinkTerminus = (HibLinkTerminus) retreivedLinkTerminus.uniqueResult() ;
		assertNotNull("terminus exists", dbLinkTerminus);

		LinkTermType actualTermType = dbLinkTerminus.getLinkTermType(); 
		int actualOffset = dbLinkTerminus.getGap();
		sess.getTransaction().commit();
		
		assertEquals ("link term type " , EXPECTED_SRC_TYPE , actualTermType) ;
		assertEquals ("link term offset" , EXPECTED_OFFSET_VALUE , actualOffset) ;
	}
	
	@Override
	protected String getDbUnitDataFilePath() {
		return "integrationTest/DbSourceData/DbSourceDataRefData.xml";
	}
	
}
