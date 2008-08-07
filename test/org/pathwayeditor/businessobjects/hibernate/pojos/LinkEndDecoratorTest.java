/**
 * 
 */
package org.pathwayeditor.businessobjects.hibernate.pojos;

import static org.junit.Assert.assertEquals;

import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author ntsorman
 *
 */
@RunWith(JMock.class)
public class LinkEndDecoratorTest {
	
	private Mockery mockery = new JUnit4Mockery() {{
		 setImposteriser(ClassImposteriser.INSTANCE);
	}};
	
	private static final short DECORATOR_TYPE = 1 ;
	private static final int WIDTH = 100 ;
	private static final int HEIGHT = 100 ;
	private static final int GAP = 5 ;
	
	@Before
	public void setUp() throws Exception {

	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test 
	public void testCreateLinkEndDecorator () throws Exception
	{
		final HibLinkTerminus mockLinkTerminus = mockery.mock(HibLinkTerminus.class , "mockLinkTerminus" ) ;
		
		HibLinkEndDecorator linkEndDecorator = new HibLinkEndDecorator ( mockLinkTerminus, 
				DECORATOR_TYPE, WIDTH, HEIGHT , GAP) ;
		
		assertEquals ("link terminus" , mockLinkTerminus , linkEndDecorator.getLinkTerminus() ) ;
		assertEquals ("decorator type" , DECORATOR_TYPE , linkEndDecorator.getDecoratorType()) ;
		assertEquals ("width" , WIDTH , linkEndDecorator.getWidth()) ;
		assertEquals ("height" , HEIGHT , linkEndDecorator.getHeight()) ;
		assertEquals ("gap" , GAP , linkEndDecorator.getGap()) ;
		
	}
}
