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
public class HibTextPropertyTest {
	
	private Mockery mockery = new JUnit4Mockery() {{
		 setImposteriser(ClassImposteriser.INSTANCE);
	}};
	
	private static final String PROPERTY_VALUE 	= "Property value" ;
	private static final int 	CREATION_SERIAL = 12345 ;
	
	private static final String NEW_PROPERTY_VALUE 	= "New property value" ;
	private static final int 	NEW_CREATION_SERIAL = 54321 ;
	
	private HibTextProperty textProperty  ;
	private HibCanvas mockCanvas ;
	
	@Before
	public void setUp() throws Exception {
		mockCanvas = mockery.mock(HibCanvas.class , "HibCanvas") ; 
		textProperty = new HibTextProperty ( mockCanvas, CREATION_SERIAL, PROPERTY_VALUE) ;
	}

	@After
	public void tearDown() throws Exception {
	}
	
	
	@Test
	public void testCreateTextProperty () throws Exception 
	{
		assertEquals ( "check value" , PROPERTY_VALUE , textProperty.getTextValue()) ;
		assertEquals ( "check serial" , CREATION_SERIAL , textProperty.getCreationSerial() ) ;
		assertEquals ( "check canvas" , mockCanvas , textProperty.getCanvas() ) ;
	}
	
	@Test
	public void testAlterPropertyValues () throws Exception 
	{
		final HibCanvas newMockCanvas = mockery.mock(HibCanvas.class , "newMockCanvas") ;
		
		textProperty.setTextValue(NEW_PROPERTY_VALUE) ;
		textProperty.setCreationSerial(NEW_CREATION_SERIAL) ;
		textProperty.setCanvas(newMockCanvas) ;
		
		assertEquals ( "check value" , NEW_PROPERTY_VALUE , textProperty.getTextValue()) ;
		assertEquals ( "check serial" , NEW_CREATION_SERIAL , textProperty.getCreationSerial() ) ;
		assertEquals ( "check canvas" , newMockCanvas , textProperty.getCanvas() ) ;
	}
	
	@Test
	public void testCopyNumberProperty () throws Exception 
	{
		HibTextProperty copyOfTextProperty = textProperty.copy(mockCanvas) ;
		
		assertEquals ( "copy of" , textProperty , copyOfTextProperty ) ;
	}
	
	@Test(expected=UnsupportedOperationException.class)
	public void testGetOwner () throws Exception
	{
		textProperty.getOwningObject() ;
	}
}
