/**
 * 
 */
package org.pathwayeditor.businessobjects.hibernate.pojos;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.Version;

/**
 * @author ntsorman
 *
 */
//@RunWith(JMock.class)
public class HibNotationTest {

//	private Mockery mockery = new JUnit4Mockery() {{
//		 setImposteriser(ClassImposteriser.INSTANCE);
//	}};
	
	private HibNotation notation ;
	
	private static final String QUALIFIED_NAME_ID = "globalId" ;
	private static final String DISPLAY_NAME = "name" ;
	private static final int MAJOR_VERSION = 2 ;
	private static final int MINOR_VERSION = 1 ;
	private static final int PATCH_VERSION = 0 ;
	private static final String NOTATION_DESCRIPTION = "description" ;
	
//	private static final String VERSION_STRING = "2.1.0" ;
	
	@Before
	public void setUp() throws Exception {
		Version vers = new Version(MAJOR_VERSION,MINOR_VERSION, PATCH_VERSION); 
		notation = new HibNotation (QUALIFIED_NAME_ID, DISPLAY_NAME, NOTATION_DESCRIPTION, vers) ;
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testCreatedNotation () throws Exception 
	{
		assertEquals ( "name" , DISPLAY_NAME , notation.getDisplayName() ) ;
		assertEquals ( "description" , NOTATION_DESCRIPTION , notation.getDescription() ) ;
		assertEquals ( "major version" , MAJOR_VERSION , notation.getMajorVersion()) ;
		assertEquals ( "minor version" , MINOR_VERSION , notation.getMinorVersion()) ;
		assertEquals ( "patch version" , PATCH_VERSION , notation.getPatchVersion()) ;
		assertEquals ( "global id" , QUALIFIED_NAME_ID , notation.getQualifiedName()) ;
//		assertEquals ( "version string" , VERSION_STRING , context.getVersionString()) ;
//		assertEquals ( "display name" , CONTEXT_NAME , context.getDisplayName() ) ;
	}
}
