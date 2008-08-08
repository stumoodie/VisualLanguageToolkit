/**
 * 
 */
package org.pathwayeditor.bussinessobjects.drawingprimitives;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.IBendPoint;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibBendPoint;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibCanvas;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibLinkAttribute;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibObjectType;

/**
 * @author ntsorman
 *
 */
@RunWith(JMock.class)
public class ILinkAttributeTest {
	
	private Mockery mockery = new JUnit4Mockery() {{
		 setImposteriser(ClassImposteriser.INSTANCE);
	}};
	
	private static final int LINK_INDEX = 1 ;
	private static final int CREATION_SERIAL = 1 ;
	private static final int INDEX_POS_1 = 1 ;
	private static final int INDEX_POS_2 = 2 ;
	private static final int INDEX_POS_3 = 3 ;
	private static final int POS_VALUE_1 = 20 ;
	private static final int POS_VALUE_2 = 30 ;
	private static final int POS_VALUE_3 = 40 ;
	
	private static final int NUMERIC_VALUE_3 = 3 ;
	private static final int NUMERIC_VALUE_2 = 2 ;
	private static final int NUMERIC_VALUE_1 = 1 ;
	
	private ILinkAttribute linkAttribute ;
	private HibCanvas mockCanvas ;
	private IBendPoint mockBendPoint1 ;
	private IBendPoint mockBendPoint2 ;
	
	@Before
	public void setUp() throws Exception {
		mockCanvas = mockery.mock(HibCanvas.class , "hibCanvas") ;
		HibObjectType mockObjectType = mockery.mock(HibObjectType.class , "mockObjectType") ;
//		mockBendPoint1 = mockery.mock(IBendPoint.class , "mockBendpoint1") ;
//		mockBendPoint2 = mockery.mock(IBendPoint.class , "mockBendpoint2") ;
//		
//		mockery.checking( new Expectations () {{
//			one(mockBendPoint1).getOwningLink() ; will(returnValue(null)) ;
//		}});
		

		
		
		HibLinkAttribute tempLinkAttribute = new HibLinkAttribute ( mockCanvas, LINK_INDEX,	mockObjectType ) ;
		
		tempLinkAttribute.setCreationSerial(CREATION_SERIAL) ;
		
		linkAttribute = tempLinkAttribute ;
		
		mockBendPoint1 = new HibBendPoint ((HibLinkAttribute)linkAttribute, INDEX_POS_1, POS_VALUE_1, POS_VALUE_1) ;
		mockBendPoint2 = new HibBendPoint ((HibLinkAttribute)linkAttribute, INDEX_POS_2, POS_VALUE_2, POS_VALUE_2) ;
		
		linkAttribute.addBendPoint(mockBendPoint1) ;
		linkAttribute.addBendPoint(mockBendPoint2) ;
		

	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testGetCanvas () throws Exception
	{
		assertEquals ( "correctCanvas" , mockCanvas , linkAttribute.getCanvas() );
	}
	
	@Test
	public void testGetCreationSerial () throws Exception 
	{
		assertEquals ( "correct creationSerial" , CREATION_SERIAL , linkAttribute.getCreationSerial()) ;
	}
	
	@Ignore
	@Test
	public void testGetObjectType () throws Exception
	{
		// TODO
		fail () ;
	}
	
	@Ignore
	@Test
	public void testGetLinkSourceDecoration() throws Exception
	{
		// TODO
		fail () ;
	}
	
	@Ignore
	@Test
	public void testGetLinkTargetDecoration() throws Exception
	{
		// TODO
		fail () ;
	}
	
	@Ignore
	@Test
	public void testGetRouter() throws Exception
	{
		// TODO
		fail () ;
	}
	
	@Ignore
	@Test
	public void testSetRouter() throws Exception
	{
		// TODO
		fail () ;
	}
	
	@Test
	public void testGetBendpointsIterator () throws Exception
	{
		Iterator<IBendPoint> iter = linkAttribute.bendPointIterator() ; 
		
		List<IBendPoint> retreivedBendPoints = new ArrayList<IBendPoint> (  );
		
		while (iter.hasNext())
			retreivedBendPoints.add(iter.next());

		assertEquals ( "correctNumber" , NUMERIC_VALUE_2 , retreivedBendPoints.size() ) ;
		assertTrue ( "bendPoint1 is there" , retreivedBendPoints.contains(mockBendPoint1) ) ;
		assertTrue ( "bendPoint2 is there" , retreivedBendPoints.contains(mockBendPoint2) ) ;
	}
	
	@Test
	public void testNumOfBendPoints () throws Exception
	{
		assertEquals ( "two bendpoints" , NUMERIC_VALUE_2 , linkAttribute.numBendPoints()) ;
	}
	
	@Test
	public void testAddBendPoins () throws Exception
	{
		IBendPoint newBendPoint = new HibBendPoint ((HibLinkAttribute)linkAttribute, INDEX_POS_3, POS_VALUE_3, POS_VALUE_3) ;
		linkAttribute.addBendPoint(newBendPoint);
		assertEquals ( "two bendpoints" , NUMERIC_VALUE_3 , linkAttribute.numBendPoints()) ;
		assertTrue ( "contains bendpoint3" , linkAttribute.containsBendPoint(newBendPoint)) ;
	}
	
	@Test
	public void testContainsBendPoints () throws Exception
	{
		assertTrue ( "contains bendpoint1" , linkAttribute.containsBendPoint(mockBendPoint1)) ;
		assertTrue ( "contains bendpoint2" , linkAttribute.containsBendPoint(mockBendPoint2)) ;
	}
	
	@Test
	public void testRemoveBendPoints () throws Exception 
	{
		linkAttribute.removeBendPoint(mockBendPoint1) ;
		assertEquals ( "one bendpoints" , NUMERIC_VALUE_1 , linkAttribute.numBendPoints()) ;
		assertFalse ( "contains bendpoint1" , linkAttribute.containsBendPoint(mockBendPoint1)) ;
	}
}	
