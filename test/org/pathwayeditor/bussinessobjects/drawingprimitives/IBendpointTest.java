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
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.IBendPoint;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.Location;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibBendPoint;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibLinkAttribute;

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
	private static final Location POSITION = new Location(X_POSITION, Y_POSITION);
	private static final Location POSITION_OTHER = new Location(X_POSITION_OTHER, Y_POSITION_OTHER);
	private static final Location FIRST_REL_DIM = new Location(101, 102);
	private static final Location SECOND_REL_DIM = new Location(103, 104);
	
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
		
		assertEquals ( "location" , new Location ( X_POSITION , Y_POSITION ) , bendPoint.getLocation() ) ;
	}
	
	@Test
	public void testSetLocation () throws Exception 
	{
		final HibLinkAttribute mockLink = mockery.mock(HibLinkAttribute.class , "mockLink") ;
		
		IBendPoint bendPoint = new HibBendPoint ( mockLink, INDEX_POSITION, POSITION, FIRST_REL_DIM, SECOND_REL_DIM) ;
		
		assertEquals ( "location" , new Location ( X_POSITION , Y_POSITION ) , bendPoint.getLocation() ) ;
		
		Location newLocation = new Location ( X_POSITION_OTHER , Y_POSITION_OTHER ) ;
		
		bendPoint.setLocation(newLocation) ;
		
		assertEquals ( "x changed" , POSITION_OTHER , ((HibBendPoint)bendPoint).getLocation()) ;
		
	}
	
	@Test
	public void testGetOwningLink () throws Exception
	{
		final HibLinkAttribute mockLink = mockery.mock(HibLinkAttribute.class , "mockLink") ;
		
		IBendPoint bendPoint = new HibBendPoint ( mockLink, INDEX_POSITION, POSITION, FIRST_REL_DIM, SECOND_REL_DIM) ;
		
		assertEquals ( "location" , new Location ( X_POSITION , Y_POSITION ) , bendPoint.getLocation() ) ;
		
		assertEquals ( "get owningLink" , mockLink ,  bendPoint.getOwningLink() );
		
	}
}
