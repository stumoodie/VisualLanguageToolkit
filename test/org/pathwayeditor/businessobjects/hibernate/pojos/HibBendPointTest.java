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
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.Location;

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
		
		HibBendPoint bendPoint = new HibBendPoint ( mockLink, INDEX_POSITION, X_POSITION, Y_POSITION) ;
		
		assertEquals ( "index position" , INDEX_POSITION , bendPoint.getIndexPos()) ;
		assertEquals ("x position" , X_POSITION , bendPoint.getXPosition()) ;
		assertEquals ("y position" , Y_POSITION , bendPoint.getYPosition()) ;
		assertEquals ("hibLink" , mockLink , bendPoint.getOwningLink()) ;
		assertEquals ("location" , new Location(X_POSITION ,Y_POSITION) , bendPoint.getLocation()) ;
	}
	
	@Test
	public void testEquals () throws Exception
	{
		final HibLinkAttribute mockLink = mockery.mock(HibLinkAttribute.class , "mockLink") ;
		
		HibBendPoint bendPoint = new HibBendPoint ( mockLink, INDEX_POSITION, X_POSITION, Y_POSITION) ;
		HibBendPoint otherBendPoint = new HibBendPoint ( mockLink, INDEX_POSITION_OTHER, X_POSITION, Y_POSITION) ;
		
		assertFalse ( "not the same" , bendPoint.equals(otherBendPoint)  ) ;
		
		otherBendPoint.setIndexPos(INDEX_POSITION) ;
		
		assertTrue ( "same" , bendPoint.equals(otherBendPoint)  ) ;
		
	}
	
	@Test
	public void testHashCode () throws Exception
	{
		final HibLinkAttribute mockLink = mockery.mock(HibLinkAttribute.class , "mockLink") ;
	
		HibBendPoint bendPoint = new HibBendPoint ( mockLink, INDEX_POSITION, X_POSITION, Y_POSITION) ;
		
		assertEquals ("hashCode" , bendPoint.hashCode() , bendPoint.hashCode() ) ;
	}
}
