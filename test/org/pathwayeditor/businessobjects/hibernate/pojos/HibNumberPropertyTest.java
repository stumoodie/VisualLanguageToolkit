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
	
	
	private HibCanvas mockCanvas ;
	private HibNumberProperty numberProperty ;
	private INumberPropertyDefinition mockDefn;
	
	@Before
	public void setUp() throws Exception {
		mockCanvas = mockery.mock(HibCanvas.class , "HibCanvas") ; 
		mockDefn = mockery.mock(INumberPropertyDefinition.class, "mockDefn");

		this.mockery.checking(new Expectations(){{
			allowing(mockDefn).getDefaultValue(); will(returnValue(PROPERTY_VALUE));
		}});
		
		numberProperty = new HibNumberProperty ( mockCanvas, CREATION_SERIAL, mockDefn) ;
	}


	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testCheckInitialiasedCorrectly() throws Exception
	{
		assertEquals ( "check value" , PROPERTY_VALUE , numberProperty.getValue()) ;
		assertEquals ( "check serial" , CREATION_SERIAL , numberProperty.getCreationSerial() ) ;
	}

	@Test
	public void testAlterPropertyValues () throws Exception 
	{
		numberProperty.setValue(NEW_PROPERTY_VALUE) ;
		
		assertEquals ( "check value" , NEW_PROPERTY_VALUE , numberProperty.getValue()) ;
	}
	
}
