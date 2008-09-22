/**
 * 
 */
package org.pathwayeditor.businessobjects.hibernate.pojos;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.INumberPropertyDefinition;

/**
 * @author ntsorman
 *
 */
@RunWith(JMock.class)
public class HibNumberPropertyTest {
	
	private Mockery mockery = new JUnit4Mockery() {{
		 setImposteriser(ClassImposteriser.INSTANCE);
	}};
	
	private static final BigDecimal PROPERTY_VALUE 	= new BigDecimal ( 10000000 ) ;
	private static final int 		CREATION_SERIAL = 12345 ;
	
	private static final BigDecimal NEW_PROPERTY_VALUE 	= new BigDecimal ( 20000000 ) ;
	private static final int 		NEW_CREATION_SERIAL = 54321 ;
	
	private HibNumberProperty numberProperty ;
	private HibCanvas mockCanvas ;
	private INumberPropertyDefinition mockTermDefn;
	
	@Before
	public void setUp() throws Exception {
		mockCanvas = mockery.mock(HibCanvas.class , "HibCanvas") ;
		mockTermDefn = mockery.mock(INumberPropertyDefinition.class, "mocktermDefn");
		
		this.mockery.checking(new Expectations(){{}});
		
		numberProperty = new HibNumberProperty ( mockCanvas, CREATION_SERIAL, mockTermDefn) ;
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testCheckInitialiasedCorrectly() throws Exception
	{
		assertEquals ( "check value" , PROPERTY_VALUE , numberProperty.getValue()) ;
		assertEquals ( "check serial" , CREATION_SERIAL , numberProperty.getCreationSerial() ) ;
		assertEquals ( "check canvas" , mockCanvas , numberProperty.getCanvas() ) ;
		
	}

	@Test
	public void testAlterPropertyValues () throws Exception 
	{
		numberProperty.setNumberValue(NEW_PROPERTY_VALUE) ;
		
		assertEquals ( "check value" , NEW_PROPERTY_VALUE , numberProperty.getValue()) ;
	}
	
	@Test
	public void testCopyNumberProperty () throws Exception 
	{
		
		HibNumberProperty copyOfNumberProperty = new HibNumberProperty(mockCanvas, NEW_CREATION_SERIAL, numberProperty) ;
		
		assertEquals ( "copy of" , numberProperty , copyOfNumberProperty ) ;
	}
}
