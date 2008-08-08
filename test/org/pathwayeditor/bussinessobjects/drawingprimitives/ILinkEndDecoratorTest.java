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
	
	private static final short DECORATOR_TYPE = 1 ;
	private static final int WIDTH = 100 ;
	private static final int HEIGHT = 100 ;
	
	@Before
	public void setUp() throws Exception {

	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testGet () throws Exception
	{
		final HibLinkTerminus mockLinkTerminus = mockery.mock(HibLinkTerminus.class , "mockLinkTerminus" ) ;
		
		ILinkEndDecorator linkEndDecorator = new HibLinkEndDecorator (  mockLinkTerminus, 
				DECORATOR_TYPE, WIDTH, HEIGHT ) ;
		
		assertEquals ( "get LinkTerminus" , mockLinkTerminus , linkEndDecorator.getLinkTerminus() ) ;
		assertTrue  ("instance of ILinkTerminus" , linkEndDecorator.getLinkTerminus() instanceof ILinkTerminus) ;
	}
}
