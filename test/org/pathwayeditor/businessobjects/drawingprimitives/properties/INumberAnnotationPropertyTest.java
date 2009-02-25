/**
 * 
 */
package org.pathwayeditor.businessobjects.drawingprimitives.properties;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

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
import org.pathwayeditor.businessobjects.hibernate.pojos.HibCanvas;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibNumberProperty;

/**
 * @author ntsorman
 *
 */
@RunWith(JMock.class)
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
		INumberPropertyDefinition mockPropDefn = this.mockery.mock(INumberPropertyDefinition.class, "mockpropDefn");
		
		this.mockery.checking(new Expectations(){{}});
		
		numberProperty = new HibNumberProperty ( mockCanvas, CREATION_SERIAL, mockPropDefn) ;
		this.mockery.assertIsSatisfied();
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Ignore @Test
	public void testCreatedPlainTextProperty () throws Exception
	{
		assertEquals ( "creation serial" , CREATION_SERIAL , numberProperty.getCreationSerial()) ;
		assertEquals ( "text value" , NUMBER_VALUE , numberProperty.getValue() ) ;
	}
	
	@Ignore @Test
	public void testChangeTextPropertyValue () throws Exception
	{
		numberProperty.setValue(OTHER_NUMBER_VALUE) ;
		assertEquals ( "text value" , OTHER_NUMBER_VALUE , numberProperty.getValue() ) ;
	}
	
	@Ignore @Test(expected=IllegalArgumentException.class)
	public void testChangeTextPropertyValueToNull () throws Exception
	{
		numberProperty.setValue(null) ;
		assertEquals ( "text value" , NUMBER_VALUE , numberProperty.getValue() ) ;
	}
	
}
