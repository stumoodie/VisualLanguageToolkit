/**
 * 
 */
package org.pathwaueditor.bussinessobjects.drawingprimitives.properties;

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
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPlainTextAnnotationProperty;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPlainTextPropertyDefinition;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibCanvas;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibTextProperty;

/**
 * @author ntsorman
 *
 */
@RunWith(JMock.class)
public class IPlainTextAnnotationPropertyTest {
	
	private Mockery mockery = new JUnit4Mockery() {{
		 setImposteriser(ClassImposteriser.INSTANCE);
	}};
	
	private IPlainTextAnnotationProperty textProperty ;
	
	private static final int CREATION_SERIAL = 100 ;
	private static final String TEXT_VALUE = "text value" ;
	
	private static final String OTHER_TEXT_VALUE = "other text value" ;
	
	@Before
	public void setUp() throws Exception {
		HibCanvas mockCanvas = mockery.mock(HibCanvas.class , "mockCanvas") ;
		IPlainTextPropertyDefinition mockPropDefn = this.mockery.mock(IPlainTextPropertyDefinition.class, "mockpropDefn");
		
		this.mockery.checking(new Expectations(){{}});
		
		textProperty = new HibTextProperty ( mockCanvas, CREATION_SERIAL, mockPropDefn) ;
		this.mockery.assertIsSatisfied();
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Ignore @Test
	public void testCreatedPlainTextProperty () throws Exception
	{
		assertEquals ( "creation serial" , CREATION_SERIAL , textProperty.getCreationSerial()) ;
		assertEquals ( "text value" , TEXT_VALUE , textProperty.getValue() ) ;
	}
	
	@Ignore @Test
	public void testChangeTextPropertyValue () throws Exception
	{
		textProperty.setValue(OTHER_TEXT_VALUE) ;
		assertEquals ( "text value" , OTHER_TEXT_VALUE , textProperty.getValue() ) ;
	}
	
	@Ignore @Test
	public void testChangeTextPropertyValueToNull () throws Exception
	{
		textProperty.setValue(null) ;
		assertEquals ( "text value" , TEXT_VALUE , textProperty.getValue() ) ;
	}
	

}
