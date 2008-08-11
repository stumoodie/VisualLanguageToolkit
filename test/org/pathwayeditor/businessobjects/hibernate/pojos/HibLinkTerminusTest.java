/**
 * 
 */
package org.pathwayeditor.businessobjects.hibernate.pojos;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.jmock.Expectations;
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
public class HibLinkTerminusTest {
	
	private Mockery mockery = new JUnit4Mockery() {{
		 setImposteriser(ClassImposteriser.INSTANCE);
	}};
	
	private static final int LINK_END_TYPE = 1 ;
	private static final short OFFSET = 1 ;
	
	@Before
	public void setUp() throws Exception {

	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCreateLinkTerminus () throws Exception 
	{
		final HibLinkAttribute mockHibLink = mockery.mock(HibLinkAttribute.class , "mockHibLink") ;
		final HibLinkTerminusDecorator mockTerminusDecorator = mockery.mock(HibLinkTerminusDecorator.class , "mockTerminusDecorator") ;
		final HibLinkEndDecorator mockEndDecorator = mockery.mock(HibLinkEndDecorator.class , "mockEndDecorator") ;
		Map<String,HibProperty> properties = new HashMap <String,HibProperty> (0) ;
			
		HibLinkTerminus linkTerminus = new HibLinkTerminus ( mockHibLink, LINK_END_TYPE, OFFSET, 
												mockTerminusDecorator, mockEndDecorator, 
												properties) ;
		
		assertEquals ("link end type" , LINK_END_TYPE , linkTerminus.getHibLinkEndType()) ;
		assertEquals ("offset" , OFFSET , linkTerminus.getOffset()) ;
		assertEquals ( "terminus decorator" , mockTerminusDecorator , linkTerminus.getDecorator()) ;
		assertEquals( "end decorator" , mockEndDecorator , linkTerminus.getLinkenddecorators()) ;
		assertEquals( "properties" , properties , linkTerminus.getProperties()) ;
		
	}
	
	@Test
	public void testAddProperty () throws Exception
	{
		final HibLinkAttribute mockHibLink = mockery.mock(HibLinkAttribute.class , "mockHibLink") ;
		final HibLinkTerminusDecorator mockTerminusDecorator = mockery.mock(HibLinkTerminusDecorator.class , "mockTerminusDecorator") ;
		final HibLinkEndDecorator mockEndDecorator = mockery.mock(HibLinkEndDecorator.class , "mockEndDecorator") ;
		final Map<String,HibProperty> properties = new HashMap <String,HibProperty> (0) ;
			
		final HibLinkTerminus linkTerminus = new HibLinkTerminus ( mockHibLink, LINK_END_TYPE, OFFSET, 
												mockTerminusDecorator, mockEndDecorator, 
												properties) ;
		
		final HibTextProperty mockTextProperty = mockery.mock(HibTextProperty.class , "mockTextProperty") ;
		
		mockery.checking( new Expectations () {{
			one(mockTextProperty).getLinkTerminus() ; will(returnValue(null));
			one(mockTextProperty).setLinkTerminus(linkTerminus) ;
		}});
		
		linkTerminus.addProperty("property", mockTextProperty) ;
		
		assertEquals ( "property in" , 1 , linkTerminus.getProperties().size()) ;
		assertEquals ( "same property" , mockTextProperty , linkTerminus.getProperties().get("property")) ;
	}
	
	@Test
	public void testRemoveProperty () throws Exception
	{
		final HibLinkAttribute mockHibLink = mockery.mock(HibLinkAttribute.class , "mockHibLink") ;
		final HibLinkTerminusDecorator mockTerminusDecorator = mockery.mock(HibLinkTerminusDecorator.class , "mockTerminusDecorator") ;
		final HibLinkEndDecorator mockEndDecorator = mockery.mock(HibLinkEndDecorator.class , "mockEndDecorator") ;
		final Map<String,HibProperty> properties = new HashMap <String,HibProperty> (0) ;
			
		final HibLinkTerminus linkTerminus = new HibLinkTerminus ( mockHibLink, LINK_END_TYPE, OFFSET, 
												mockTerminusDecorator, mockEndDecorator, 
												properties) ;
		
		final HibTextProperty mockTextProperty = mockery.mock(HibTextProperty.class , "mockTextProperty") ;
		
		mockery.checking( new Expectations () {{
			one(mockTextProperty).getLinkTerminus() ; will(returnValue(null));
			one(mockTextProperty).setLinkTerminus(linkTerminus) ;
			one(mockTextProperty).setLinkTerminus(null) ;
		}});
		
		linkTerminus.addProperty("property", mockTextProperty) ;
		
		linkTerminus.removeProperty("property") ;
		
		assertEquals ( "no properties" , 0 , linkTerminus.getProperties().size()) ;

	}
	

}
