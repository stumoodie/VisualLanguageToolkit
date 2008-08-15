/**
 * 
 */
package org.pathwaueditor.bussinessobjects.drawingprimitives.properties;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.INumberAnnotationProperty;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibCanvas;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibNumberProperty;

/**
 * @author ntsorman
 *
 */
public class INumberAnnotationPropertyTest {
	private Mockery mockery = new JUnit4Mockery() {{
		 setImposteriser(ClassImposteriser.INSTANCE);
	}};
	
	private INumberAnnotationProperty numberProperty ;
	
	private static final int CREATION_SERIAL = 100 ;
	private static final BigDecimal NUMBER_VALUE = new BigDecimal(10000000) ;
	
	private static final BigDecimal OTHER_NUMBER_VALUE = new BigDecimal ( 1222121 ) ;
	
	@Before
	public void setUp() throws Exception {
		HibCanvas mockCanvas = mockery.mock(HibCanvas.class , "mockCanvas") ;
		
		numberProperty = new HibNumberProperty ( mockCanvas, CREATION_SERIAL, NUMBER_VALUE) ;
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testCreatedPlainTextProperty () throws Exception
	{
		assertEquals ( "creation serial" , CREATION_SERIAL , numberProperty.getPropertySerial()) ;
		assertEquals ( "text value" , NUMBER_VALUE , numberProperty.getNumberValue() ) ;
		assertTrue ( "object value" , numberProperty.getValue() instanceof BigDecimal ) ;
	}
	
	@Test
	public void testChangeTextPropertyValue () throws Exception
	{
		numberProperty.setNumberValue(OTHER_NUMBER_VALUE) ;
		assertEquals ( "text value" , OTHER_NUMBER_VALUE , numberProperty.getNumberValue() ) ;
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testChangeTextPropertyValueToNull () throws Exception
	{
		numberProperty.setNumberValue(null) ;
		assertEquals ( "text value" , NUMBER_VALUE , numberProperty.getNumberValue() ) ;
	}
	
	@Test(expected=UnsupportedOperationException.class)
	public void testGetOwningObject () throws Exception 
	{
		numberProperty.getOwningObject() ;
	}
}
