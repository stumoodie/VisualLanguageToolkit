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
import org.junit.Test;
import org.junit.runner.RunWith;
import org.pathwayeditor.businessobjects.drawingprimitives.ILabelAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.Location;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.Size;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibCanvas;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibLabelAttribute;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibProperty;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibTextProperty;

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
	private static final int POSITION_VALUE = 50 ;
	private static final int SIZE_VALUE = 20 ;
	private static final boolean IS_DISPLAYED = true ;
	private static final int BACKGROUND_COLOR = 100 ;
	private static final boolean FILL_SET = true ;
	
	private static final int NEW_POSITION_VALUE = 150 ;
	private static final int NEW_SIZE_VALUE = 40 ;
	private static final int NEW_BACKGROUND_COLOR = 200 ;
	private static final boolean IS_NOT_DISPLAYED = false ;
	private static final boolean NO_FILL_SET = false ;

	
	@Before
	public void setUp() throws Exception {
		mockCanvas = mockery.mock(HibCanvas.class , "mockCanvas") ;
		
		property = new HibTextProperty ( null , CREATION_SERIAL , PROPERTY_VALUE) ;
		
		labelAttribute = new HibLabelAttribute ( mockCanvas, CREATION_SERIAL, POSITION_VALUE, POSITION_VALUE,
				SIZE_VALUE, SIZE_VALUE, property, IS_DISPLAYED, BACKGROUND_COLOR, BACKGROUND_COLOR, BACKGROUND_COLOR, FILL_SET) ;
	}
	
	@After
	public void tearDown() throws Exception
	{
		
	}
	
	@Test
	public void testCheckCreated () throws Exception
	{
		assertEquals ( "canvas" , mockCanvas , labelAttribute.getCanvas() );
		assertEquals ( "color" , new RGB ( BACKGROUND_COLOR ,BACKGROUND_COLOR ,BACKGROUND_COLOR  ) , labelAttribute.getBackgroundColor() );
		assertEquals ("creation serial" , CREATION_SERIAL , labelAttribute.getCreationSerial() );
		assertEquals ("is displayed" , IS_DISPLAYED , labelAttribute.isDisplayed() );
		assertEquals ("is fillset" , FILL_SET , labelAttribute.isFillSet() );
		assertEquals ("size" , new Size (SIZE_VALUE , SIZE_VALUE) , labelAttribute.getSize() );
		assertEquals ("location" , new Location (POSITION_VALUE , POSITION_VALUE) , labelAttribute.getLocation() );
		assertEquals ( "property" , property , labelAttribute.getProperty()) ;
	}
	
	@Test
	public void testChangeLocation () throws Exception
	{
		labelAttribute.setLocation( new Location ( NEW_POSITION_VALUE , NEW_POSITION_VALUE )) ;
		
		assertEquals ("location X" , NEW_POSITION_VALUE , ((HibLabelAttribute) labelAttribute).getXPosition() ) ;
		assertEquals ("location Y" , NEW_POSITION_VALUE , ((HibLabelAttribute) labelAttribute).getYPosition() ) ;
	}

	@Test(expected=IllegalArgumentException.class)
	public void testChangeLocationToNull () throws Exception
	{
		labelAttribute.setLocation( null) ;
		
		assertEquals ("location X" , POSITION_VALUE , ((HibLabelAttribute) labelAttribute).getXPosition() ) ;
		assertEquals ("location Y" , POSITION_VALUE , ((HibLabelAttribute) labelAttribute).getYPosition() ) ;
	}	
	
	@Test
	public void testChangeSize () throws Exception 
	{
		labelAttribute.setSize( new Size (NEW_SIZE_VALUE , NEW_SIZE_VALUE ) ) ;

		assertEquals ("height" , NEW_SIZE_VALUE , ((HibLabelAttribute) labelAttribute).getHeight() ) ;
		assertEquals ("width" , NEW_SIZE_VALUE , ((HibLabelAttribute) labelAttribute).getWidth() ) ;
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testChangeSizeToNull () throws Exception 
	{
		labelAttribute.setSize( null) ;

		assertEquals ("height" , SIZE_VALUE , ((HibLabelAttribute) labelAttribute).getHeight() ) ;
		assertEquals ("width" , SIZE_VALUE , ((HibLabelAttribute) labelAttribute).getWidth() ) ;
	}
	
	@Test
	public void testChangeBackgroundColor () throws Exception
	{
		labelAttribute.setBackgroundColor( new RGB ( NEW_BACKGROUND_COLOR , NEW_BACKGROUND_COLOR ,NEW_BACKGROUND_COLOR) ) ;
		
		assertEquals ("red" , NEW_BACKGROUND_COLOR , ((HibLabelAttribute) labelAttribute).getBackgroundRed() ) ;
		assertEquals ("green" , NEW_BACKGROUND_COLOR , ((HibLabelAttribute) labelAttribute).getBackgroundGreen() ) ;
		assertEquals ("blue" , NEW_BACKGROUND_COLOR , ((HibLabelAttribute) labelAttribute).getBackgroundBlue() ) ;
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testChangeBackgroundColorToNull () throws Exception
	{
		labelAttribute.setBackgroundColor( null ) ;
		
		assertEquals ("red" , BACKGROUND_COLOR , ((HibLabelAttribute) labelAttribute).getBackgroundRed() ) ;
		assertEquals ("green" , BACKGROUND_COLOR , ((HibLabelAttribute) labelAttribute).getBackgroundGreen() ) ;
		assertEquals ("blue" , BACKGROUND_COLOR , ((HibLabelAttribute) labelAttribute).getBackgroundBlue() ) ;
	}
	
	@Test
	public void testChangeIsDisplayed () throws Exception
	{
		labelAttribute.setDisplayed(IS_NOT_DISPLAYED) ;
		
		assertEquals ( "displayed" , IS_NOT_DISPLAYED , labelAttribute.isDisplayed() ) ;
	}
	
	@Test
	public void testChangeFillSet () throws Exception
	{
		labelAttribute.setFillSet(NO_FILL_SET) ;
		
		assertEquals ( "fill set" , NO_FILL_SET , labelAttribute.isFillSet() ) ;
	}

}
