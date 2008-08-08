/**
 * 
 */
package org.pathwayeditor.bussinessobjects.drawingprimitives;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkTerminus;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibLinkAttribute;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibLinkEndDecorator;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibLinkTerminus;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibLinkTerminusDecorator;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibProperty;

/**
 * @author ntsorman
 *
 */
@RunWith(JMock.class)
public class ILinkTerminusTest {
	
	private Mockery mockery = new JUnit4Mockery() {{
		 setImposteriser(ClassImposteriser.INSTANCE);
	}};
	private static final int LINK_END_TYPE = 1 ;
	private static final short OFFSET = 1 ;
	private static final short GAP = 5 ;
	private static final short FALSE_GAP = -5 ;
	
	@Before
	public void setUp() throws Exception {

	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testGetGap () throws Exception 
	{
		final HibLinkAttribute mockHibLink = mockery.mock(HibLinkAttribute.class , "mockHibLink") ;
		final HibLinkTerminusDecorator mockTerminusDecorator = mockery.mock(HibLinkTerminusDecorator.class , "mockTerminusDecorator") ;
		final HibLinkEndDecorator mockEndDecorator = mockery.mock(HibLinkEndDecorator.class , "mockEndDecorator") ;
		final Map<String,HibProperty> properties = new HashMap <String,HibProperty> (0) ;
			
		final ILinkTerminus linkTerminus = new HibLinkTerminus ( mockHibLink, LINK_END_TYPE, OFFSET, 
												mockTerminusDecorator, mockEndDecorator, 
												properties) ;
		
		assertEquals ( "get gap" , OFFSET , linkTerminus.getGap()) ;
	}
	
	
	
	@Test(expected=IllegalArgumentException.class)
	public void testSetGap () throws Exception 
	{
		final HibLinkAttribute mockHibLink = mockery.mock(HibLinkAttribute.class , "mockHibLink") ;
		final HibLinkTerminusDecorator mockTerminusDecorator = mockery.mock(HibLinkTerminusDecorator.class , "mockTerminusDecorator") ;
		final HibLinkEndDecorator mockEndDecorator = mockery.mock(HibLinkEndDecorator.class , "mockEndDecorator") ;
		final Map<String,HibProperty> properties = new HashMap <String,HibProperty> (0) ;
			
		final ILinkTerminus linkTerminus = new HibLinkTerminus ( mockHibLink, LINK_END_TYPE, OFFSET, 
												mockTerminusDecorator, mockEndDecorator, 
												properties) ;
		
		linkTerminus.setGap(GAP) ; 
		
		assertEquals ( "new Gap " , GAP , linkTerminus.getGap() ) ;
		
		linkTerminus.setGap(FALSE_GAP) ;
		
		assertEquals ( "new Gap " , GAP , linkTerminus.getGap() ) ;
 	}
}
