/**
 * 
 */
package org.pathwayeditor.businessobjects.hibernate.pojos;

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
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LinkTermType;

/**
 * @author ntsorman
 *
 */
@RunWith(JMock.class)
public class HibLinkTerminusTest {
	
	private Mockery mockery = new JUnit4Mockery() {{
		 setImposteriser(ClassImposteriser.INSTANCE);
	}};
	
	private static final LinkTermType LINK_END_TYPE = LinkTermType.SOURCE;
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
		Map<String,HibProperty> properties = new HashMap <String,HibProperty> (0) ;
			
		HibLinkTerminus linkTerminus = new HibLinkTerminus ( mockHibLink, LINK_END_TYPE) ;
		
		assertEquals ("link end type" , LINK_END_TYPE , linkTerminus.getLinkTermType()) ;
		assertEquals ("offset" , OFFSET , linkTerminus.getOffset()) ;
		assertEquals( "properties" , properties , linkTerminus.getProperties()) ;
		
	}
	
	@Test
	public void testAddProperty () throws Exception
	{
		final HibLinkAttribute mockHibLink = mockery.mock(HibLinkAttribute.class , "mockHibLink") ;
//		final Map<String,HibProperty> properties = new HashMap <String,HibProperty> (0) ;
			
		final HibLinkTerminus linkTerminus = new HibLinkTerminus ( mockHibLink, LINK_END_TYPE) ;
		
		final HibTextProperty mockTextProperty = mockery.mock(HibTextProperty.class , "mockTextProperty") ;
		
		linkTerminus.addProperty("property", mockTextProperty) ;
		
		assertEquals ( "property in" , 1 , linkTerminus.getProperties().size()) ;
		assertEquals ( "same property" , mockTextProperty , linkTerminus.getProperties().get("property")) ;
	}
	
	@Test
	public void testRemoveProperty () throws Exception
	{
		final HibLinkAttribute mockHibLink = mockery.mock(HibLinkAttribute.class , "mockHibLink") ;
//		final Map<String,HibProperty> properties = new HashMap <String,HibProperty> (0) ;
			
		final HibLinkTerminus linkTerminus = new HibLinkTerminus ( mockHibLink, LINK_END_TYPE) ;
		
		final HibTextProperty mockTextProperty = mockery.mock(HibTextProperty.class , "mockTextProperty") ;
		
		linkTerminus.addProperty("property", mockTextProperty) ;
		
		linkTerminus.removeProperty("property") ;
		
		assertEquals ( "no properties" , 0 , linkTerminus.getProperties().size()) ;

	}
	

}
