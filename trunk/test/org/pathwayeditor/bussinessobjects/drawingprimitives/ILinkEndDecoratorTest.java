/**
 * 
 */
package org.pathwayeditor.bussinessobjects.drawingprimitives;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkEndDecorator;
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkTerminus;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LinkEndDecoratorShape;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.Size;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibLinkEndDecorator;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibLinkTerminus;


/**
 * @author ntsorman
 *
 */
@RunWith(JMock.class)
public class ILinkEndDecoratorTest {
	
	private Mockery mockery = new JUnit4Mockery() {{
		 setImposteriser(ClassImposteriser.INSTANCE);
	}};
	
	private static final int SIZE_VALUE = 100 ;
	private static final int NEW_SIZE_VALUE = 150 ;
	
	private static final LinkEndDecoratorShape OTHER_STYLE = LinkEndDecoratorShape.ARROW ;
	
//	private ILinkEndDecorator linkEndDecorator ;
	private HibLinkTerminus mockLinkTerminus ;
	
	@Before
	public void setUp() throws Exception {
		mockLinkTerminus = mockery.mock(HibLinkTerminus.class , "mockLinkTerminus" ) ;
		linkEndDecorator  = new HibLinkEndDecorator (  mockLinkTerminus, 
				LinkEndDecoratorShape.DIAMOND, SIZE_VALUE, SIZE_VALUE ) ;
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testGetLinkTerminus () throws Exception
	{
		assertEquals ( "get LinkTerminus" , mockLinkTerminus , linkEndDecorator.getLinkTerminus() ) ;
		assertTrue  ("instance of ILinkTerminus" , linkEndDecorator.getLinkTerminus() instanceof ILinkTerminus) ;
	}
	
	@Test
	public void testSetSize () throws Exception 
	{
		linkEndDecorator.setSize( new Size ( NEW_SIZE_VALUE ,NEW_SIZE_VALUE )) ;
		assertEquals ( "new Size" , new Size (NEW_SIZE_VALUE , NEW_SIZE_VALUE ) , linkEndDecorator.getSize() ) ;
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSetSizeToNull () throws Exception 
	{
		linkEndDecorator.setSize( null) ;
		assertEquals ( "new Size" , new Size (SIZE_VALUE , SIZE_VALUE ) , linkEndDecorator.getSize() ) ;
	
	}
	
	@Test
	public void testGetArrowheadStyle () throws Exception
	{
		assertEquals ( "arrowhead style" , LinkEndDecoratorShape.DIAMOND, linkEndDecorator.getDecoratorType()) ;
	}
	
	@Test
	public void testSetArrowheadStyle () throws Exception
	{
		linkEndDecorator.setDecoratorType(OTHER_STYLE) ;
		assertEquals ( "arrowhead style" , LinkEndDecoratorShape.ARROW, linkEndDecorator.getDecoratorType()) ;
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSetArrowheadStyleToNull () throws Exception
	{
		linkEndDecorator.setDecoratorType(null) ;
		assertEquals ( "arrowhead style" , LinkEndDecoratorShape.DIAMOND, linkEndDecorator.getDecoratorType()) ;
	}	
}
