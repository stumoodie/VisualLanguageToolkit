/**
 * 
 */
package org.pathwaueditor.bussinessobjects.drawingprimitives.properties;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IHtmlAnnotationProperty;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibCanvas;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibRichTextProperty;

/**
 * @author ntsorman
 *
 */
public class IHtmlAnnotationPropertyTest {
	private Mockery mockery = new JUnit4Mockery() {{
		 setImposteriser(ClassImposteriser.INSTANCE);
	}};
	
	private IHtmlAnnotationProperty htmlProperty ;
	
	private static final int CREATION_SERIAL = 100 ;
	private static final String TEXT_VALUE = "text value" ;
	
	private static final String OTHER_TEXT_VALUE = "other text value" ;
	
	@Before
	public void setUp() throws Exception {
		HibCanvas mockCanvas = mockery.mock(HibCanvas.class , "mockCanvas") ;
		
		htmlProperty = new HibRichTextProperty ( mockCanvas, CREATION_SERIAL, TEXT_VALUE) ;
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testCreatedPlainTextProperty () throws Exception
	{
		assertEquals ( "creation serial" , CREATION_SERIAL , htmlProperty.getPropertySerial()) ;
		assertEquals ( "text value" , TEXT_VALUE , htmlProperty.getRichTextValue() ) ;
		assertTrue ( "object value" , htmlProperty.getValue() instanceof String ) ;
	}
	
	@Test
	public void testChangeTextPropertyValue () throws Exception
	{
		htmlProperty.setRichTextValue(OTHER_TEXT_VALUE) ;
		assertEquals ( "text value" , OTHER_TEXT_VALUE , htmlProperty.getRichTextValue() ) ;
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testChangeTextPropertyValueToNull () throws Exception
	{
		htmlProperty.setRichTextValue(null) ;
		assertEquals ( "text value" , TEXT_VALUE , htmlProperty.getRichTextValue() ) ;
	}
	
}
