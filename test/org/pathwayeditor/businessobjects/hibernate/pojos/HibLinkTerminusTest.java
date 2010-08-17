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
package org.pathwayeditor.businessobjects.hibernate.pojos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkTerminus;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LinkEndDecoratorShape;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LinkTermType;
import org.pathwayeditor.businessobjects.typedefn.ILinkTerminusDefaults;
import org.pathwayeditor.businessobjects.typedefn.ILinkTerminusDefinition;
import org.pathwayeditor.figure.geometry.Dimension;

import uk.ac.ed.inf.graph.util.IndexCounter;

/**
 * @author ntsorman
 *
 */
@RunWith(JMock.class)
public class HibLinkTerminusTest {
	private Mockery mockery = new JUnit4Mockery() {{
		 setImposteriser(ClassImposteriser.INSTANCE);
	}};
	
	private static final LinkTermType LINK_END_TYPE = LinkTermType.SOURCE;
	private static final double EXPECTED_OFFSET = 1.0 ;
	private static final Dimension EXPECTED_END_SIZE = new Dimension(10, 20);
	private static final LinkEndDecoratorShape EXPECTED_END_DEC = LinkEndDecoratorShape.ARROW;
	private static final int EXPECTED_CREATION_SERIAL = 2;
	private static final IndexCounter EXPECTED_CREATION_SERIAL_CNTR = new IndexCounter(EXPECTED_CREATION_SERIAL);

	private static final double DOUBLE_DELTA = 0.001; 

	private Canvas mockCanvas;
	private HibLinkAttribute mockHibLink;
	private ILinkTerminusDefinition mockTermDefn;
	private ILinkTerminus linkTerminus;
	private ILinkTerminusDefaults mockDefaults;
	private Set<HibCanvasAttribute> attributes;
	
	@Before
	public void setUp() throws Exception {
		mockHibLink = mockery.mock(HibLinkAttribute.class , "mockHibLink") ;
		mockTermDefn = mockery.mock(ILinkTerminusDefinition.class, "mockTermDefn");
		mockDefaults = mockery.mock(ILinkTerminusDefaults.class, "mockDefaults");
		mockCanvas = mockery.mock(Canvas.class, "mockCanvas");
		
		this.attributes = new HashSet<HibCanvasAttribute>();
		
		this.mockery.checking(new Expectations(){{
			allowing(mockCanvas).getCreationSerialCounter(); will(returnValue(EXPECTED_CREATION_SERIAL_CNTR));
			allowing(mockCanvas).getCanvasAttributes().add(with(any(HibCanvasAttribute.class))); will(returnValue(attributes));
			
			allowing(mockHibLink).getCanvas(); will(returnValue(mockCanvas));
			allowing(mockHibLink).getLinkTermini(); will(returnValue(new ArrayList<HibLinkTerminus>()));
			
			allowing(mockTermDefn).getDefaultAttributes(); will(returnValue(mockDefaults));
			
			allowing(mockDefaults).getEndDecoratorType(); will(returnValue(EXPECTED_END_DEC));
			allowing(mockDefaults).getEndSize(); will(returnValue(EXPECTED_END_SIZE));
			allowing(mockDefaults).getGap(); will(returnValue(EXPECTED_OFFSET));
			allowing(mockDefaults).propertyDefinitionIterator(); will(returnIterator());
		}});
		
		linkTerminus = new HibLinkTerminus ( mockCanvas, EXPECTED_CREATION_SERIAL, LINK_END_TYPE, mockTermDefn) ;
		((HibLinkTerminus)linkTerminus).changeOwningLink(mockHibLink);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testLinkTerminusInitialisedCorrectly () throws Exception 
	{
		
		assertEquals ("expected link end type" , LINK_END_TYPE , linkTerminus.getLinkTermType()) ;
		assertEquals ("expected offset" , EXPECTED_OFFSET , linkTerminus.getGap(), DOUBLE_DELTA) ;
		assertEquals ("expected end size" , EXPECTED_END_SIZE , linkTerminus.getEndSize()) ;
		assertEquals ("expected offset" , EXPECTED_END_DEC , linkTerminus.getEndDecoratorType()) ;
		assertFalse( "expected properties" , linkTerminus.propertyIterator().hasNext()) ;
		
	}
}
