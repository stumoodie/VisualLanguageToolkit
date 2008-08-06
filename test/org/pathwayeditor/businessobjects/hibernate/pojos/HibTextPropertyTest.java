/**
 * 
 */
package org.pathwayeditor.businessobjects.hibernate.pojos;

import static org.junit.Assert.assertEquals;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
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
	
	@Before
	public void setUp() throws Exception {

	}

	@After
	public void tearDown() throws Exception {
	}
	
	
	@Test
	public void testCreateTextProperty () throws Exception 
	{
		final HibCanvas mockCanvas = mockery.mock(HibCanvas.class , "HibCanvas") ; 
		HibTextProperty textProperty = new HibTextProperty ( mockCanvas, CREATION_SERIAL, PROPERTY_VALUE) ;
		
		assertEquals ( "check value" , PROPERTY_VALUE , textProperty.getTextValue()) ;
		assertEquals ( "check serial" , CREATION_SERIAL , textProperty.getCreationSerial() ) ;
		assertEquals ( "check canvas" , mockCanvas , textProperty.getCanvas() ) ;
	}
	
	@Test
	public void testAlterPropertyValues () throws Exception 
	{
		final HibCanvas mockCanvas = mockery.mock(HibCanvas.class , "mockCanvas") ;
		final HibCanvas newMockCanvas = mockery.mock(HibCanvas.class , "newMockCanvas") ;
		
		HibTextProperty textProperty = new HibTextProperty ( mockCanvas, CREATION_SERIAL, PROPERTY_VALUE) ;
		
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
		final HibCanvas mockCanvas = mockery.mock(HibCanvas.class , "HibCanvas") ; 
		HibTextProperty textProperty = new HibTextProperty ( mockCanvas, CREATION_SERIAL, PROPERTY_VALUE) ;
		
		HibTextProperty copyOfTextProperty = textProperty.copy(mockCanvas) ;
		
		assertEquals ( "copy of" , textProperty , copyOfTextProperty ) ;
	}
	
	@Ignore
	@Test
	public void testGetOwner () throws Exception
	{
		final HibCanvas mockCanvas = mockery.mock(HibCanvas.class , "HibCanvas") ; 
		final HibTextProperty textProperty = new HibTextProperty ( mockCanvas, CREATION_SERIAL, PROPERTY_VALUE) ;
		
		final HibShapeAttribute mockShape = mockery.mock(HibShapeAttribute.class , "mockShape") ;
		
		mockery.checking( new Expectations () {
			{atLeast(1).of(mockShape).addProperty("TextProperty", textProperty);}
		});
		
		mockShape.addProperty("TextProperty", textProperty) ;
		
		assertEquals ( "get Owner" , mockShape , textProperty.getOwningObject()) ;
		
	}
}
