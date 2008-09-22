/**
 * 
 */
package org.pathwayeditor.businessobjects.hibernate.pojos;

import static org.junit.Assert.assertEquals;

import org.jmock.integration.junit4.JMock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.Version;

/**
 * @author ntsorman
 *
 */
@RunWith(JMock.class)
public class HibContextTest {

//	private Mockery mockery = new JUnit4Mockery() {{
//		 setImposteriser(ClassImposteriser.INSTANCE);
//	}};
	
	private HibNotation context ;
	
	private static final String GLOBAL_ID = "globalId" ;
	private static final String CONTEXT_NAME = "name" ;
	private static final int MAJOR_VERSION = 2 ;
	private static final int MINOR_VERSION = 1 ;
	private static final int PATCH_VERSION = 0 ;
	private static final String CONTEXT_DESCRIPTION = "description" ;
	
//	private static final String VERSION_STRING = "2.1.0" ;
	
	@Before
	public void setUp() throws Exception {
		Version vers = new Version(MAJOR_VERSION,MINOR_VERSION, PATCH_VERSION); 
		context = new HibNotation (GLOBAL_ID, CONTEXT_NAME, CONTEXT_DESCRIPTION, vers) ;
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testCreatedContext () throws Exception 
	{
		assertEquals ( "name" , CONTEXT_NAME , context.getName() ) ;
		assertEquals ( "description" , CONTEXT_DESCRIPTION , context.getDescription() ) ;
		assertEquals ( "major version" , MAJOR_VERSION , context.getMajorVersion()) ;
		assertEquals ( "minor version" , MINOR_VERSION , context.getMinorVersion()) ;
		assertEquals ( "patch version" , PATCH_VERSION , context.getPatchVersion()) ;
		assertEquals ( "global id" , GLOBAL_ID , context.getGlobalId()) ;
//		assertEquals ( "version string" , VERSION_STRING , context.getVersionString()) ;
//		assertEquals ( "display name" , CONTEXT_NAME , context.getDisplayName() ) ;
	}
}
