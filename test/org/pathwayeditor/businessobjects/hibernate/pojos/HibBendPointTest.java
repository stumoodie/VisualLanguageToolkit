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
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.pathwayeditor.figure.geometry.Point;

/**
 * @author ntsorman
 *
 */
@RunWith(JMock.class)
public class HibBendPointTest {
	
	private Mockery mockery = new JUnit4Mockery() {{
		 setImposteriser(ClassImposteriser.INSTANCE);
	}};
	
	private static final int INDEX_POSITION = 10 ;
	private static final int X_POSITION = 100 ;
	private static final int Y_POSITION = 100 ;
	private static final Point POSITION = new Point(X_POSITION, Y_POSITION);
	private static final Point FIRST_REL_DIM = new Point(101, 102);
	private static final Point SECOND_REL_DIM = new Point(103, 104);
	private static final int INDEX_POSITION_OTHER = 15 ;
	
	@Before
	public void setUp() throws Exception {

	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testCreateBendPoint () throws Exception 
	{
		final HibLinkAttribute mockLink = mockery.mock(HibLinkAttribute.class , "mockLink") ;
		
		HibBendPoint bendPoint = new HibBendPoint ( mockLink, INDEX_POSITION, POSITION, FIRST_REL_DIM, SECOND_REL_DIM) ;
		
		assertEquals ( "index position" , INDEX_POSITION , bendPoint.getCreationSerial()) ;
		assertEquals ("location" , POSITION , bendPoint.getLocation()) ;
		assertEquals ("first rel dim" , FIRST_REL_DIM , bendPoint.getFirstRelativeDimension()) ;
		assertEquals ("hibLink" , mockLink , bendPoint.getOwningLink()) ;
		assertEquals ("second rel dim" , SECOND_REL_DIM , bendPoint.getSecondRelativeDimension()) ;
	}
	
	@Test
	public void testEquals () throws Exception
	{
		final HibLinkAttribute mockLink = mockery.mock(HibLinkAttribute.class , "mockLink") ;
		
		HibBendPoint bendPoint = new HibBendPoint ( mockLink, INDEX_POSITION, POSITION, FIRST_REL_DIM, SECOND_REL_DIM) ;
		HibBendPoint otherBendPoint = new HibBendPoint ( mockLink, INDEX_POSITION_OTHER, POSITION, FIRST_REL_DIM, SECOND_REL_DIM) ;
		
		assertFalse ( "not the same" , bendPoint.equals(otherBendPoint)  ) ;
		
		otherBendPoint.setCreationSerial(INDEX_POSITION) ;
		
		assertTrue ( "same" , bendPoint.equals(otherBendPoint)  ) ;
		
	}
	
	@Test
	public void testHashCode () throws Exception
	{
		final HibLinkAttribute mockLink = mockery.mock(HibLinkAttribute.class , "mockLink") ;
	
		HibBendPoint bendPoint = new HibBendPoint ( mockLink, INDEX_POSITION, POSITION, FIRST_REL_DIM, SECOND_REL_DIM) ;
		
		assertEquals ("hashCode" , bendPoint.hashCode() , bendPoint.hashCode() ) ;
	}
	

}
