/**
 * 
 */
package org.pathwayeditor.bussinessobjects.drawingprimitives;

import static org.junit.Assert.assertEquals;

import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.pathwayeditor.businessobjects.drawingprimitives.ILabelAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.Location;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.Size;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPlainTextPropertyDefinition;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibCanvas;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibLabelAttribute;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibProperty;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibTextProperty;
import org.pathwayeditor.businessobjects.typedefn.ILabelAttributeDefaults;

/**
 * @author ntsorman
 *
 */
@RunWith(JMock.class)
public class ILabelAttributeTest {

	private Mockery mockery = new JUnit4Mockery() {{
		 setImposteriser(ClassImposteriser.INSTANCE);
	}};
	
	private ILabelAttribute labelAttribute ;
	private HibCanvas mockCanvas ;
	private HibProperty property ;
	
	private static final String PROPERTY_VALUE = "value" ;
	
	private static final int CREATION_SERIAL = 12345 ;
	private static final int POSITION_X = 50 ;
	private static final int POSITION_Y = 50 ;
	private static final Location POSITION_VALUE = new Location(POSITION_X, POSITION_Y);
	private static final int SIZE_WIDTH = 20 ;
	private static final int SIZE_HEIGHT = 30 ;
	private static final Size SIZE_VALUE = new Size(SIZE_WIDTH, SIZE_HEIGHT);
//	private static final boolean IS_DISPLAYED = true ;
	private static final int BACKGROUND_RED = 100 ;
	private static final int BACKGROUND_GREEN = 100 ;
	private static final int BACKGROUND_BLUE = 100 ;
	private static final RGB BACKGROUND_COLOR = new RGB(BACKGROUND_RED, BACKGROUND_GREEN, BACKGROUND_BLUE);
//	private static final boolean FILL_SET = true ;
	
	private static final int NEW_POSITION_VALUE = 150 ;
	private static final int NEW_SIZE_VALUE = 40 ;
	private static final int NEW_BACKGROUND_COLOR = 200 ;

	
	@Before
	public void setUp() throws Exception {
		mockCanvas = mockery.mock(HibCanvas.class , "mockCanvas") ;
		final ILabelAttributeDefaults mockDefaults = mockery.mock(ILabelAttributeDefaults.class, "mockDefaults");
		IPlainTextPropertyDefinition defn = null;
		
		property = new HibTextProperty ( null , CREATION_SERIAL , defn) ;
		
		labelAttribute = new HibLabelAttribute ( mockCanvas, CREATION_SERIAL, property, mockDefaults);
		labelAttribute.setLocation(POSITION_VALUE);
		labelAttribute.setSize(SIZE_VALUE);
		labelAttribute.setBackgroundColor(BACKGROUND_COLOR);
	}
	
	@After
	public void tearDown() throws Exception
	{
		this.labelAttribute = null;
	}
	
	@Ignore @Test
	public void testCheckCreated () throws Exception
	{
		assertEquals ( "canvas" , mockCanvas , labelAttribute.getCanvas() );
		assertEquals ( "color" , BACKGROUND_COLOR, labelAttribute.getBackgroundColor() );
		assertEquals ("creation serial" , CREATION_SERIAL , labelAttribute.getCreationSerial() );
		assertEquals ("size" , SIZE_VALUE, labelAttribute.getSize() );
		assertEquals ("location" , POSITION_VALUE, labelAttribute.getLocation() );
		assertEquals ( "property" , property , labelAttribute.getProperty()) ;
	}
	
	@Ignore @Test
	public void testChangeLocation () throws Exception
	{
		labelAttribute.setLocation( new Location ( NEW_POSITION_VALUE , NEW_POSITION_VALUE )) ;
		
		assertEquals ("location X" , NEW_POSITION_VALUE , ((HibLabelAttribute) labelAttribute).getXPosition() ) ;
		assertEquals ("location Y" , NEW_POSITION_VALUE , ((HibLabelAttribute) labelAttribute).getYPosition() ) ;
	}

	@Ignore @Test(expected=IllegalArgumentException.class)
	public void testChangeLocationToNull () throws Exception
	{
		labelAttribute.setLocation( null) ;
		
		assertEquals ("location X" , POSITION_VALUE , ((HibLabelAttribute) labelAttribute).getXPosition() ) ;
		assertEquals ("location Y" , POSITION_VALUE , ((HibLabelAttribute) labelAttribute).getYPosition() ) ;
	}	
	
	@Ignore @Test
	public void testChangeSize () throws Exception 
	{
		labelAttribute.setSize( new Size (NEW_SIZE_VALUE , NEW_SIZE_VALUE ) ) ;

		assertEquals ("height" , NEW_SIZE_VALUE , ((HibLabelAttribute) labelAttribute).getHeight() ) ;
		assertEquals ("width" , NEW_SIZE_VALUE , ((HibLabelAttribute) labelAttribute).getWidth() ) ;
	}
	
	@Ignore @Test(expected=IllegalArgumentException.class)
	public void testChangeSizeToNull () throws Exception 
	{
		labelAttribute.setSize( null) ;

		assertEquals ("height" , SIZE_VALUE , ((HibLabelAttribute) labelAttribute).getHeight() ) ;
		assertEquals ("width" , SIZE_VALUE , ((HibLabelAttribute) labelAttribute).getWidth() ) ;
	}
	
	@Ignore @Test
	public void testChangeBackgroundColor () throws Exception
	{
		labelAttribute.setBackgroundColor( new RGB ( NEW_BACKGROUND_COLOR , NEW_BACKGROUND_COLOR ,NEW_BACKGROUND_COLOR) ) ;
		
		assertEquals ("red" , NEW_BACKGROUND_COLOR , ((HibLabelAttribute) labelAttribute).getBackgroundRed() ) ;
		assertEquals ("green" , NEW_BACKGROUND_COLOR , ((HibLabelAttribute) labelAttribute).getBackgroundGreen() ) ;
		assertEquals ("blue" , NEW_BACKGROUND_COLOR , ((HibLabelAttribute) labelAttribute).getBackgroundBlue() ) ;
	}
	
	@Ignore @Test(expected=IllegalArgumentException.class)
	public void testChangeBackgroundColorToNull () throws Exception
	{
		labelAttribute.setBackgroundColor( null ) ;
		
		assertEquals ("red" , BACKGROUND_COLOR , ((HibLabelAttribute) labelAttribute).getBackgroundRed() ) ;
		assertEquals ("green" , BACKGROUND_COLOR , ((HibLabelAttribute) labelAttribute).getBackgroundGreen() ) ;
		assertEquals ("blue" , BACKGROUND_COLOR , ((HibLabelAttribute) labelAttribute).getBackgroundBlue() ) ;
	}
}
