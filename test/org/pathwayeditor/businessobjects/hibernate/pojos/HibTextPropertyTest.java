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
import org.junit.Test;
import org.junit.runner.RunWith;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPlainTextPropertyDefinition;

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
	
	private HibCanvas mockCanvas ;
	private HibTextProperty textProperty ;
	private IPlainTextPropertyDefinition mockDefn;
	
	@Before
	public void setUp() throws Exception {
		mockCanvas = mockery.mock(HibCanvas.class , "HibCanvas") ; 
		mockDefn = mockery.mock(IPlainTextPropertyDefinition.class, "mockDefn");

		this.mockery.checking(new Expectations(){{
			allowing(mockDefn).getDefaultValue(); will(returnValue(PROPERTY_VALUE));
		}});
		
		textProperty = new HibTextProperty ( mockCanvas, CREATION_SERIAL, mockDefn) ;
	}


	@After
	public void tearDown() throws Exception {
	}
	
	
	@Test
	public void testCreateTextProperty () throws Exception 
	{
		assertEquals ( "check value" , PROPERTY_VALUE , textProperty.getValue()) ;
		assertEquals ( "check serial" , CREATION_SERIAL , textProperty.getCreationSerial() ) ;
	}
	
	@Test
	public void testAlterPropertyValues () throws Exception 
	{
		textProperty.setValue(NEW_PROPERTY_VALUE) ;
		
		assertEquals ( "check value" , NEW_PROPERTY_VALUE , textProperty.getValue()) ;
		assertEquals ( "check serial" , CREATION_SERIAL , textProperty.getCreationSerial() ) ;
	}
}
