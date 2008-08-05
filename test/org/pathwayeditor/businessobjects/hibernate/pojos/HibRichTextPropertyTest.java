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
public class HibRichTextPropertyTest {
	
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
	public void testCreateRichTextProperty () throws Exception 
	{
		final HibCanvas mockCanvas = mockery.mock(HibCanvas.class , "HibCanvas") ; 
		HibRichTextProperty richTextProperty = new HibRichTextProperty ( mockCanvas, CREATION_SERIAL, PROPERTY_VALUE) ;
		
		assertEquals ( "check value" , PROPERTY_VALUE , richTextProperty.getRichTextValue()) ;
		assertEquals ( "check serial" , CREATION_SERIAL , richTextProperty.getCreationSerial() ) ;
		assertEquals ( "check canvas" , mockCanvas , richTextProperty.getCanvas() ) ;
	}
	
	@Test
	public void testAlterPropertyValues () throws Exception 
	{
		final HibCanvas mockCanvas = mockery.mock(HibCanvas.class , "mockCanvas") ;
		final HibCanvas newMockCanvas = mockery.mock(HibCanvas.class , "newMockCanvas") ;
		
		HibRichTextProperty richTextProperty = new HibRichTextProperty ( mockCanvas, CREATION_SERIAL, PROPERTY_VALUE) ;
		
		richTextProperty.setRichTextValue(NEW_PROPERTY_VALUE) ;
		richTextProperty.setCreationSerial(NEW_CREATION_SERIAL) ;
		richTextProperty.setCanvas(newMockCanvas) ;
		
		assertEquals ( "check value" , NEW_PROPERTY_VALUE , richTextProperty.getRichTextValue()) ;
		assertEquals ( "check serial" , NEW_CREATION_SERIAL , richTextProperty.getCreationSerial() ) ;
		assertEquals ( "check canvas" , newMockCanvas , richTextProperty.getCanvas() ) ;
	}
	
	@Test
	public void testCopyNumberProperty () throws Exception 
	{
		final HibCanvas mockCanvas = mockery.mock(HibCanvas.class , "HibCanvas") ; 
		HibRichTextProperty richTextProperty = new HibRichTextProperty ( mockCanvas, CREATION_SERIAL, PROPERTY_VALUE) ;
		
		HibRichTextProperty copyOfTextProperty = richTextProperty.copy(mockCanvas) ;
		
		assertEquals ( "copy of" , richTextProperty , copyOfTextProperty ) ;
	}
	
	@Ignore
	@Test
	public void testGetOwner () throws Exception
	{
		final HibCanvas mockCanvas = mockery.mock(HibCanvas.class , "HibCanvas") ; 
		final HibRichTextProperty richTextProperty = new HibRichTextProperty ( mockCanvas, CREATION_SERIAL, PROPERTY_VALUE) ;
		
		final HibShape mockShape = mockery.mock(HibShape.class , "mockShape") ;
		
		mockery.checking( new Expectations () {
			{atLeast(1).of(mockShape).addProperty("TextProperty", richTextProperty);}
		});
		
		mockShape.addProperty("TextProperty", richTextProperty) ;
		
		assertEquals ( "get Owner" , mockShape , richTextProperty.getOwningObject()) ;
		
	}
}
