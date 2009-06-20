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
package org.pathwayeditor.bussinessobjects.drawingprimitives;

import static org.junit.Assert.assertEquals;

import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.pathwayeditor.businessobjects.drawingprimitives.IBendPoint;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibBendPoint;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibLinkAttribute;
import org.pathwayeditor.figure.geometry.Point;

/**
 * @author ntsorman
 *
 */
@RunWith(JMock.class)
public class IBendpointTest {
	
	private Mockery mockery = new JUnit4Mockery() {{
		 setImposteriser(ClassImposteriser.INSTANCE);
	}};
	
	private static final int INDEX_POSITION = 10 ;
	private static final int X_POSITION = 100 ;
	private static final int Y_POSITION = 100 ;
	private static final int X_POSITION_OTHER = 200 ;
	private static final int Y_POSITION_OTHER = 200 ;
	private static final Point POSITION = new Point(X_POSITION, Y_POSITION);
	private static final Point POSITION_OTHER = new Point(X_POSITION_OTHER, Y_POSITION_OTHER);
	private static final Point FIRST_REL_DIM = new Point(101, 102);
	private static final Point SECOND_REL_DIM = new Point(103, 104);
	
	@Before
	public void setUp() throws Exception {

	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testGetLocation () throws Exception 
	{
		final HibLinkAttribute mockLink = mockery.mock(HibLinkAttribute.class , "mockLink") ;
		
		IBendPoint bendPoint = new HibBendPoint ( mockLink, INDEX_POSITION, POSITION, FIRST_REL_DIM, SECOND_REL_DIM) ;
		
		assertEquals ( "location" , new Point ( X_POSITION , Y_POSITION ) , bendPoint.getLocation() ) ;
	}
	
	@Test
	public void testSetLocation () throws Exception 
	{
		final HibLinkAttribute mockLink = mockery.mock(HibLinkAttribute.class , "mockLink") ;
		
		IBendPoint bendPoint = new HibBendPoint ( mockLink, INDEX_POSITION, POSITION, FIRST_REL_DIM, SECOND_REL_DIM) ;
		
		assertEquals ( "location" , new Point ( X_POSITION , Y_POSITION ) , bendPoint.getLocation() ) ;
		
		Point newLocation = new Point ( X_POSITION_OTHER , Y_POSITION_OTHER ) ;
		
		bendPoint.setLocation(newLocation) ;
		
		assertEquals ( "x changed" , POSITION_OTHER , ((HibBendPoint)bendPoint).getLocation()) ;
		
	}
	
	@Test
	public void testGetOwningLink () throws Exception
	{
		final HibLinkAttribute mockLink = mockery.mock(HibLinkAttribute.class , "mockLink") ;
		
		IBendPoint bendPoint = new HibBendPoint ( mockLink, INDEX_POSITION, POSITION, FIRST_REL_DIM, SECOND_REL_DIM) ;
		
		assertEquals ( "location" , new Point ( X_POSITION , Y_POSITION ) , bendPoint.getLocation() ) ;
		
		assertEquals ( "get owningLink" , mockLink ,  bendPoint.getOwningLink() );
		
	}
}
