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
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IHtmlPropertyDefinition;

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
	
	private HibCanvas mockCanvas ;
	private HibRichTextProperty richTextProperty ;
	private IHtmlPropertyDefinition mockDefn;
	
	@Before
	public void setUp() throws Exception {
		mockCanvas = mockery.mock(HibCanvas.class , "HibCanvas") ; 
		mockDefn = mockery.mock(IHtmlPropertyDefinition.class, "mockDefn");

		this.mockery.checking(new Expectations(){{
			allowing(mockDefn).getDefaultValue(); will(returnValue(PROPERTY_VALUE));
		}});
		
		richTextProperty = new HibRichTextProperty ( mockCanvas, CREATION_SERIAL, mockDefn) ;
	}

	@After
	public void tearDown() throws Exception {
		this.richTextProperty = null;
	}
	
	
	@Test
	public void testCreateRichTextProperty () throws Exception 
	{
		assertEquals ( "check value" , PROPERTY_VALUE , richTextProperty.getRichTextValue()) ;
		assertEquals ( "check serial" , CREATION_SERIAL , richTextProperty.getCreationSerial() ) ;
		assertEquals ( "check canvas" , mockCanvas , richTextProperty.getCanvas() ) ;
	}
	
	@Test
	public void testAlterPropertyValues () throws Exception 
	{
		richTextProperty.setValue(NEW_PROPERTY_VALUE) ;
		
		assertEquals ( "check value" , NEW_PROPERTY_VALUE , richTextProperty.getRichTextValue()) ;
		assertEquals ( "check serial" , CREATION_SERIAL , richTextProperty.getCreationSerial() ) ;
	}
	
}
