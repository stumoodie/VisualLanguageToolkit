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
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

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
	
	
	@Before
	public void setUp() throws Exception {

	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testCreateNumberPropery () throws Exception
	{
		final HibCanvas mockCanvas = mockery.mock(HibCanvas.class , "HibCanvas") ; 
		HibNumberProperty numberProperty = new HibNumberProperty ( mockCanvas, CREATION_SERIAL, PROPERTY_VALUE) ;
		
		assertEquals ( "check value" , PROPERTY_VALUE , numberProperty.getNumberValue()) ;
		assertEquals ( "check serial" , CREATION_SERIAL , numberProperty.getCreationSerial() ) ;
		assertEquals ( "check canvas" , mockCanvas , numberProperty.getCanvas() ) ;
		
	}

	@Test
	public void testAlterPropertyValues () throws Exception 
	{
		final HibCanvas mockCanvas = mockery.mock(HibCanvas.class , "mockCanvas") ;
		final HibCanvas newMockCanvas = mockery.mock(HibCanvas.class , "newMockCanvas") ;
		
		HibNumberProperty numberProperty = new HibNumberProperty ( mockCanvas, CREATION_SERIAL, PROPERTY_VALUE) ;
		
		numberProperty.setNumberValue(NEW_PROPERTY_VALUE) ;
		numberProperty.setCreationSerial(NEW_CREATION_SERIAL) ;
		numberProperty.setCanvas(newMockCanvas) ;
		
		assertEquals ( "check value" , NEW_PROPERTY_VALUE , numberProperty.getNumberValue()) ;
		assertEquals ( "check serial" , NEW_CREATION_SERIAL , numberProperty.getCreationSerial() ) ;
		assertEquals ( "check canvas" , newMockCanvas , numberProperty.getCanvas() ) ;
	}
	
	@Test
	public void testCopyNumberProperty () throws Exception 
	{
		final HibCanvas mockCanvas = mockery.mock(HibCanvas.class , "HibCanvas") ; 
		HibNumberProperty numberProperty = new HibNumberProperty ( mockCanvas, CREATION_SERIAL, PROPERTY_VALUE) ;
		
		HibNumberProperty copyOfNumberProperty = numberProperty.copy(mockCanvas) ;
		
		assertEquals ( "copy of" , numberProperty , copyOfNumberProperty ) ;
	}
	
	@Ignore
	@Test
	public void testGetOwner () throws Exception
	{
		final HibCanvas mockCanvas = mockery.mock(HibCanvas.class , "HibCanvas") ; 
		final HibNumberProperty numberProperty = new HibNumberProperty ( mockCanvas, CREATION_SERIAL, PROPERTY_VALUE) ;
		
		final HibShape mockShape = mockery.mock(HibShape.class , "mockShape") ;
		
		mockery.checking( new Expectations () {
			{atLeast(1).of(mockShape).addProperty("NumberProperty", numberProperty);}
		});
		
		mockShape.addProperty("NumberProperty", numberProperty) ;
		
		assertEquals ( "get Owner" , mockShape , numberProperty.getOwningObject()) ;
		
	}
}
