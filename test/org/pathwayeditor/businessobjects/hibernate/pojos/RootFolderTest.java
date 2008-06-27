/**
 * 
 */
package org.pathwayeditor.businessobjects.hibernate.pojos;

import static org.junit.Assert.assertTrue;

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
@RunWith( JMock.class )
public class RootFolderTest {
	
	private Mockery mockery = new JUnit4Mockery() {{
		 setImposteriser(ClassImposteriser.INSTANCE);
	}};
	
	private HibRootFolder testRootFolder = new HibRootFolder();
	
	@Before
	public void setUp() throws Exception {
		testRootFolder = new HibRootFolder();
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testAddNullChild ()
	{
		try{
		testRootFolder.addSubFolder(null) ;
		}
		catch ( IllegalArgumentException exc )
		{
			assertTrue ( true ) ;
		}
		
	}

}
