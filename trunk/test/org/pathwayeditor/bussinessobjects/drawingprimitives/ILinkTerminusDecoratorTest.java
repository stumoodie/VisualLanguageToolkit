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
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkTerminusDecorator;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LineStyle;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.Size;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibLinkTerminus;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibLinkTerminusDecorator;

/**
 * @author ntsorman
 *
 */
@RunWith(JMock.class)
public class ILinkTerminusDecoratorTest {
	
	private Mockery mockery = new JUnit4Mockery() {{
		 setImposteriser(ClassImposteriser.INSTANCE);
	}};
	
	private ILinkTerminusDecorator linkTerminusDecorator ;
	HibLinkTerminus mockLinkTerminus ;
	
	private static final int FILL_COLOR_VALUE = 100 ;
	private static final int LINE_COLOR_VALUE = 200 ;
	private static final int SIZE_VALUE = 50 ;
	private static final short SHAPE_TYPE = 1 ;
	private static final int LINE_WIDTH = 2 ;
	private static final LineStyle LINE_STYLE = LineStyle.DASH_DOT ;
	
	private static final int NEW_FILL_COLOR_VALUE = 150 ;
	private static final int NEW_LINE_COLOR_VALUE = 250 ;
	private static final int NEW_SIZE_VALUE = 55 ;
		
	@Before
	public void setUp() throws Exception {
		mockLinkTerminus = mockery.mock(HibLinkTerminus.class , "mockLinkTerminus" ) ;
		
		linkTerminusDecorator = new HibLinkTerminusDecorator (mockLinkTerminus, SHAPE_TYPE, FILL_COLOR_VALUE, FILL_COLOR_VALUE, 
				FILL_COLOR_VALUE, LINE_COLOR_VALUE, LINE_COLOR_VALUE, LINE_COLOR_VALUE, LINE_WIDTH, 
				LINE_STYLE, SIZE_VALUE, SIZE_VALUE);
	
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testCreated () throws Exception 
	{
		assertEquals ( "fill color" , new RGB (FILL_COLOR_VALUE ,FILL_COLOR_VALUE ,FILL_COLOR_VALUE) , linkTerminusDecorator.getFillColor() ) ;
		assertEquals ( "line color" , new RGB (LINE_COLOR_VALUE ,LINE_COLOR_VALUE ,LINE_COLOR_VALUE) , linkTerminusDecorator.getLineColor() ) ;
		assertEquals ( "size" , new Size ( SIZE_VALUE , SIZE_VALUE) , linkTerminusDecorator.getSize() );
		assertEquals ( "line width" , LINE_WIDTH , linkTerminusDecorator.getLineSize()) ;
		assertEquals ( "linkTerminus" , mockLinkTerminus , linkTerminusDecorator.getOwningLinkTerminus()) ;
	}
	
	@Test
	public void testChangeFillColor () throws Exception
	{
		linkTerminusDecorator.setFillColor(new RGB ( NEW_FILL_COLOR_VALUE , NEW_FILL_COLOR_VALUE , NEW_FILL_COLOR_VALUE )) ;
		assertEquals ( "red" , NEW_FILL_COLOR_VALUE , ((HibLinkTerminusDecorator)linkTerminusDecorator).getFillRed() ) ;
		assertEquals ( "blue" , NEW_FILL_COLOR_VALUE , ((HibLinkTerminusDecorator)linkTerminusDecorator).getFillBlue() ) ;
		assertEquals ( "green" , NEW_FILL_COLOR_VALUE , ((HibLinkTerminusDecorator)linkTerminusDecorator).getFillGreen() ) ;
	}
	
	@Test
	public void testChangeLineColor () throws Exception
	{
		linkTerminusDecorator.setLineColor(new RGB ( NEW_LINE_COLOR_VALUE , NEW_LINE_COLOR_VALUE , NEW_LINE_COLOR_VALUE )) ;
		assertEquals ( "red" , NEW_LINE_COLOR_VALUE , ((HibLinkTerminusDecorator)linkTerminusDecorator).getLineRed() ) ;
		assertEquals ( "blue" , NEW_LINE_COLOR_VALUE , ((HibLinkTerminusDecorator)linkTerminusDecorator).getLineBlue() ) ;
		assertEquals ( "green" , NEW_LINE_COLOR_VALUE , ((HibLinkTerminusDecorator)linkTerminusDecorator).getLineGreen() ) ;
	}
	
	@Test
	public void testChangeSize () throws Exception 
	{
		linkTerminusDecorator.setSize ( new Size ( NEW_SIZE_VALUE , NEW_SIZE_VALUE) ) ;
		assertEquals ( "height" , NEW_SIZE_VALUE ,((HibLinkTerminusDecorator)linkTerminusDecorator).getHeight() );
		assertEquals ( "width" , NEW_SIZE_VALUE ,((HibLinkTerminusDecorator)linkTerminusDecorator).getWidth() );
	}
	
	@Test
	public void testChangeLineStyle () throws Exception 
	{
		assertEquals ( "lineStyle" , LineStyle.DASH_DOT , linkTerminusDecorator.getLineStyle()) ;
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testChangeLineStyleToNull () throws Exception 
	{
		linkTerminusDecorator.setLineStyle(null) ;
		assertEquals ( "lineStyle" , LineStyle.DASH_DOT , linkTerminusDecorator.getLineStyle()) ;
	}
}
