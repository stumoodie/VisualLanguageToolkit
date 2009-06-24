/*
Copyright 2009, Court of the University of Edinburgh

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License. 
*/
/**
 * 
 */
package org.pathwayeditor.bussinessobjects.drawingprimitives;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.pathwayeditor.businessobjects.drawingprimitives.IBendPoint;
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LineStyle;
import org.pathwayeditor.businessobjects.hibernate.helpers.IHibNotationFactory;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibCanvas;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibLinkAttribute;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibNotation;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibObjectType;
import org.pathwayeditor.businessobjects.hibernate.pojos.ObjectTypeClassification;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationSubsystem;
import org.pathwayeditor.businessobjects.repository.IMap;
import org.pathwayeditor.businessobjects.typedefn.ILinkObjectType;
import org.pathwayeditor.bussinessobjects.stubs.StubHibNotationFactory;
import org.pathwayeditor.bussinessobjects.stubs.StubLinkObjectType;
import org.pathwayeditor.bussinessobjects.stubs.StubMap;
import org.pathwayeditor.bussinessobjects.stubs.notationsubsystem.StubNotationSubSystem;
import org.pathwayeditor.figure.geometry.Point;

/**
 * @author ntsorman
 *
 */
//@RunWith(JMock.class)
public class ILinkAttributeTest {
//	
//	private Mockery mockery = new JUnit4Mockery() {{
//		 setImposteriser(ClassImposteriser.INSTANCE);
//	}};
	
	private static final int LINK_INDEX = 1 ;
	private static final int CREATION_SERIAL = 1 ;
//	private static final int INDEX_POS_1 = 1 ;
//	private static final int INDEX_POS_2 = 2 ;
//	private static final int INDEX_POS_3 = 3 ;
	private static final int POS_VALUE_1 = 20 ;
	private static final int POS_VALUE_2 = 30 ;
	private static final int POS_VALUE_3 = 40 ;
	private static final Point BP_POSITION1 = new Point(POS_VALUE_1, POS_VALUE_1); 
	private static final Point BP_POSITION2 = new Point(POS_VALUE_2, POS_VALUE_2); 
	private static final Point BP_POSITION3 = new Point(POS_VALUE_3, POS_VALUE_3); 
	
	private static final int NUMERIC_VALUE_3 = 3 ;
	private static final int NUMERIC_VALUE_2 = 2 ;
	private static final int NUMERIC_VALUE_1 = 1 ;

	private static final LineStyle LINE_STYLE = LineStyle.DASH_DOT ;
	
	private ILinkAttribute linkAttribute ;
	private HibCanvas mockCanvas ;
	private IBendPoint mockBendPoint1 ;
	private IBendPoint mockBendPoint2 ;
	private ILinkObjectType stubObjectType;
//	private HibTextProperty textProperty ;
	
	@Before
	public void setUp() throws Exception {
//		mockCanvas = mockery.mock(HibCanvas.class , "hibCanvas") ;
//		final HibObjectType mockObjectType = mockery.mock(HibObjectType.class , "mockObjectType") ;
//		final ILinkObjectType mockLinkObjectType = mockery.mock(ILinkObjectType.class, "mockLinkObjectType");
//		final IDefaultLinkAttributes stubDefaults = new StubDefaultLinkAttributes();

//		this.mockery.checking(new Expectations(){{
//			allowing(mockLinkObjectType).getDefaultLinkAttributes(); will(returnValue(stubDefaults));
//			
//			
//		}});
		INotationSubsystem stubNotationSubSystem = new StubNotationSubSystem();
		IMap stubMap = new StubMap();
		IHibNotationFactory notationFact = new StubHibNotationFactory();
		mockCanvas = new HibCanvas(stubMap.getRepository().getName(), stubMap.getINode(), notationFact, stubNotationSubSystem, stubMap.getName());
		HibObjectType objectType = new HibObjectType(StubLinkObjectType.EXPECTED_UNIQUE_ID, StubLinkObjectType.EXPECTED_NAME, StubLinkObjectType.EXPECTED_DESCRIPTION, ObjectTypeClassification.SHAPE);
		HibNotation notation = notationFact.getNotation();
		notation.addObjectType(objectType);
		stubObjectType = new StubLinkObjectType();
		
		linkAttribute = new HibLinkAttribute ( mockCanvas, LINK_INDEX,	stubObjectType, objectType ) ;
		
//		tempLinkAttribute.setCreationSerial(CREATION_SERIAL) ;
		
		mockBendPoint1 = linkAttribute.createNewBendPoint(BP_POSITION1) ;
		mockBendPoint2 = linkAttribute.createNewBendPoint(BP_POSITION2) ;
		
//		linkAttribute.setUrl(URL) ;
		
//		textProperty = new HibTextProperty () ;

	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testGetCanvas () throws Exception
	{
		assertEquals ( "correctCanvas" , mockCanvas , linkAttribute.getCanvas() );
	}
	
	@Test
	public void testGetCreationSerial () throws Exception 
	{
		assertEquals ( "correct creationSerial" , CREATION_SERIAL , linkAttribute.getCreationSerial()) ;
	}
	
	@Test
	public void testGetObjectType () throws Exception
	{
		assertEquals("correct OT", stubObjectType, linkAttribute.getObjectType());
	}
	
	@Test
	public void testGetBendpointsIterator () throws Exception
	{
		Iterator<IBendPoint> iter = linkAttribute.bendPointIterator() ; 
		
		List<IBendPoint> retreivedBendPoints = new ArrayList<IBendPoint> (  );
		
		while (iter.hasNext())
			retreivedBendPoints.add(iter.next());

		assertEquals ( "correctNumber" , NUMERIC_VALUE_2 , retreivedBendPoints.size() ) ;
		assertTrue ( "bendPoint1 is there" , retreivedBendPoints.contains(mockBendPoint1) ) ;
		assertTrue ( "bendPoint2 is there" , retreivedBendPoints.contains(mockBendPoint2) ) ;
	}
	
	@Test
	public void testNumOfBendPoints () throws Exception
	{
		assertEquals ( "two bendpoints" , NUMERIC_VALUE_2 , linkAttribute.numBendPoints()) ;
	}
	
	@Test
	public void testAddBendPoins () throws Exception
	{
		IBendPoint newBendPoint = linkAttribute.createNewBendPoint(BP_POSITION3);
		assertEquals ( "two bendpoints" , NUMERIC_VALUE_3 , linkAttribute.numBendPoints()) ;
		assertTrue ( "contains bendpoint3" , linkAttribute.containsBendPoint(newBendPoint)) ;
	}
	
	@Test
	public void testContainsBendPoints () throws Exception
	{
		assertTrue ( "contains bendpoint1" , linkAttribute.containsBendPoint(mockBendPoint1)) ;
		assertTrue ( "contains bendpoint2" , linkAttribute.containsBendPoint(mockBendPoint2)) ;
	}
	
	@Test
	public void testRemoveBendPoints () throws Exception 
	{
		linkAttribute.removeBendPoint(mockBendPoint1) ;
		assertEquals ( "one bendpoints" , NUMERIC_VALUE_1 , linkAttribute.numBendPoints()) ;
		assertFalse ( "contains bendpoint1" , linkAttribute.containsBendPoint(mockBendPoint1)) ;
	}
	
	@Test
	public void testPropertyIterator() {
		linkAttribute.propertyIterator() ;
	}
	
	@Test
	public void testGetLineStyle() {
		linkAttribute.setLineStyle(LINE_STYLE) ;
		assertEquals ("line style" , LINE_STYLE, linkAttribute.getLineStyle() ) ;
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSetLineStyleToNull() {
		linkAttribute.setLineStyle(null) ;
	}

}	
