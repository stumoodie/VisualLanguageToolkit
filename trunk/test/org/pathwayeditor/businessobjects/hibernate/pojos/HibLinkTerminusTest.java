/**
 * 
 */
package org.pathwayeditor.businessobjects.hibernate.pojos;

import static org.junit.Assert.assertEquals;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LinkTermType;
import org.pathwayeditor.businessobjects.typedefn.ILinkTerminusDefinition;

/**
 * @author ntsorman
 *
 */
@RunWith(JMock.class)
public class HibLinkTerminusTest {
	private Mockery mockery = new JUnit4Mockery();
	
//	private Mockery mockery = new JUnit4Mockery() {{
//		 setImposteriser(ClassImposteriser.INSTANCE);
//	}};
	
	private static final LinkTermType LINK_END_TYPE = LinkTermType.SOURCE;
	private static final short OFFSET = 1 ;
	private HibLinkAttribute mockHibLink;
	private ILinkTerminusDefinition mockTermDefn;
	private HibLinkTerminus linkTerminus;
	
	@Before
	public void setUp() throws Exception {
		mockHibLink = mockery.mock(HibLinkAttribute.class , "mockHibLink") ;
		mockTermDefn = mockery.mock(ILinkTerminusDefinition.class, "mockTermDefn");
	
		this.mockery.checking(new Expectations(){{
			
		}});
		
		linkTerminus = new HibLinkTerminus ( mockHibLink, LINK_END_TYPE, mockTermDefn) ;
		this.mockery.assertIsSatisfied();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testLinkTerminusInitialisedCorrectly () throws Exception 
	{
		
		assertEquals ("link end type" , LINK_END_TYPE , linkTerminus.getLinkTermType()) ;
		assertEquals ("offset" , OFFSET , linkTerminus.getOffset()) ;
//		assertEquals( "properties" , properties , linkTerminus.getProperties()) ;
		
	}
}
