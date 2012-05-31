/*
  Licensed to the Court of the University of Edinburgh (UofE) under one
  or more contributor license agreements.  See the NOTICE file
  distributed with this work for additional information
  regarding copyright ownership.  The UofE licenses this file
  to you under the Apache License, Version 2.0 (the
  "License"); you may not use this file except in compliance
  with the License.  You may obtain a copy of the License at

  http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing,
  software distributed under the License is distributed on an
  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
  KIND, either express or implied.  See the License for the
  specific language governing permissions and limitations
  under the License.
*/


package org.pathwayeditor.testfixture;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.hamcrest.Description;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.api.Action;
import org.jmock.api.Invocation;
import org.pathwayeditor.businessobjects.drawingprimitives.IBendPointContainer;
import org.pathwayeditor.businessobjects.drawingprimitives.ICurveSegment;
import org.pathwayeditor.businessobjects.drawingprimitives.ICurveSegmentContainer;
import org.pathwayeditor.businessobjects.drawingprimitives.ILabelAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkTerminus;
import org.pathwayeditor.businessobjects.drawingprimitives.IModel;
import org.pathwayeditor.businessobjects.drawingprimitives.IRootAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.Colour;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LineStyle;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LinkEndDecoratorShape;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LinkTermType;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationProperty;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPlainTextAnnotationProperty;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPlainTextPropertyDefinition;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyDefinition;
import org.pathwayeditor.businessobjects.typedefn.ILinkObjectType;
import org.pathwayeditor.figure.geometry.Dimension;
import org.pathwayeditor.figure.geometry.Point;

/**
 * @author Stuart Moodie
 *
 */
public class LinkAttributeBuilder {
	private static final Point SRC_END_POINT = new Point(102.0, 87.0);
	private static final Point TGT_END_POINT = new Point(132.0, 35.2);
	private final Mockery mockery;
	private final String name;
	private IModel canvas;
	private int idx;
	private ILinkObjectType objectType;
	private ILinkAttribute linkAttribute;
	private List<IAnnotationProperty> props = new LinkedList<IAnnotationProperty>();
	private List<Point> bpLocations = new LinkedList<Point>();
	private List<ICurveSegment> curveLocations = new LinkedList<ICurveSegment>();
	private static int mockCurveCntr = 0;
	
	public LinkAttributeBuilder(Mockery mockery, String name, ILinkObjectType objectType, IModel canvas, int idx){
		this.mockery = mockery;
		this.name = name;
		this.canvas = canvas;
		this.idx = idx;
		this.objectType = objectType;
	}
	
	public class GetBendPointAction implements Action {
		
		public GetBendPointAction(){
		}
		
		@Override
		public void describeTo(Description descn) {
			descn.appendText("get removal state");
		}

		@Override
		public Point invoke(Invocation invocation) throws Throwable {
			Integer idx = (Integer)invocation.getParameter(0);
			Point retVal = bpLocations.get(idx);
			return retVal;
		}
		
	}
	
	public Action getBendPoint(){
		return new GetBendPointAction();
	}

	public class TestBendPointAction implements Action {
		
		public TestBendPointAction(){
		}
		
		@Override
		public void describeTo(Description descn) {
			descn.appendText("get removal state");
		}

		@Override
		public Boolean invoke(Invocation invocation) throws Throwable {
			Integer idx = (Integer)invocation.getParameter(0);
			Boolean retVal = idx > 0 && idx < bpLocations.size();
			return retVal;
		}
		
	}
	
	public Action testBendPoint(){
		return new TestBendPointAction();
	}

	public void addBendPoint(Point bpLocn){
		this.bpLocations.add(bpLocn);
		ICurveSegment mockCurve = mockery.mock(ICurveSegment.class, "mockCurve"+ mockCurveCntr ++);
		this.curveLocations .add(mockCurve); 
	}
	
	public void build(){
		linkAttribute = mockery.mock(ILinkAttribute.class, name);
		final ILinkTerminus srcTerm = mockery.mock(ILinkTerminus.class, name + "SrcTerm");
		final ILinkTerminus tgtTerm = mockery.mock(ILinkTerminus.class, name + "TgtTerm");
		final ICurveSegmentContainer mockCurveSegContainer = mockery.mock(ICurveSegmentContainer.class, name + "mockCurveSegContainer");
		mockery.checking(new Expectations(){{
			allowing(linkAttribute).getCreationSerial(); will(returnValue(idx));
			allowing(linkAttribute).getModel(); will(returnValue(canvas));
			allowing(linkAttribute).isRemoved(); will(returnValue(false));
			allowing(linkAttribute).compareTo(linkAttribute); will(returnValue(0));
			allowing(linkAttribute).compareTo(with(any(ILabelAttribute.class))); will(returnValue(1));
			allowing(linkAttribute).compareTo(with(any(IShapeAttribute.class))); will(returnValue(1));
			allowing(linkAttribute).compareTo(with(any(IRootAttribute.class))); will(returnValue(1));
			allowing(linkAttribute).getObjectType(); will(returnValue(objectType));
			allowing(linkAttribute).isRemoved(); will(returnValue(false));
			allowing(linkAttribute).compareTo(with(any(ILinkAttribute.class))); will(returnValue(-1));
			allowing(linkAttribute).compareTo(with(any(ILabelAttribute.class))); will(returnValue(-1));
			allowing(linkAttribute).compareTo(with(any(IRootAttribute.class))); will(returnValue(1));
			allowing(linkAttribute).propertyIterator(); will(returnIterator());
			allowing(linkAttribute).getLineColour(); will(returnValue(Colour.BLACK));
			allowing(linkAttribute).getLineStyle(); will(returnValue(LineStyle.SOLID));
			allowing(linkAttribute).getLineWidth(); will(returnValue(1.1));
			allowing(linkAttribute).getSourceTerminus(); will(returnValue(srcTerm));
			allowing(linkAttribute).getTargetTerminus(); will(returnValue(tgtTerm));
			
			allowing(srcTerm).getLinkTermType(); will(returnValue(LinkTermType.TARGET));
			allowing(srcTerm).getEndDecoratorType(); will(returnValue(LinkEndDecoratorShape.ARROW));
			allowing(srcTerm).getEndSize(); will(returnValue(new Dimension(12.4, 9.03)));
			allowing(srcTerm).getGap(); will(returnValue(2.3));
			allowing(srcTerm).getLocation(); will(returnValue(TGT_END_POINT));

			allowing(tgtTerm).getLinkTermType(); will(returnValue(LinkTermType.SOURCE));
			allowing(tgtTerm).getEndDecoratorType(); will(returnValue(LinkEndDecoratorShape.NONE));
			allowing(tgtTerm).getEndSize(); will(returnValue(new Dimension(19.4, 19.01)));
			allowing(tgtTerm).getGap(); will(returnValue(1.7));
			allowing(tgtTerm).getLocation(); will(returnValue(SRC_END_POINT));
		}});
		final IBendPointContainer bpContainer = mockery.mock(IBendPointContainer.class, this.name + "BpContainer");
		this.mockery.checking(new Expectations(){{
			allowing(bpContainer).bendPointIterator(); will(returnIterator(bpLocations));
			allowing(bpContainer).getBendPoint(with(any(Integer.class))); will(getBendPoint());
			allowing(bpContainer).containsBendPoint(with(any(Integer.class))); will(testBendPoint());

			allowing(linkAttribute).getBendPointContainer(); will(returnValue(bpContainer));
			allowing(linkAttribute).getCurveSegmentContainer(); will(returnValue(mockCurveSegContainer));

			allowing(mockCurveSegContainer).curveIterator(); will(returnIterator(curveLocations));
		
		}});
		Iterator<IPropertyDefinition> defnIter = objectType.getDefaultAttributes().propertyDefinitionIterator();
		while(defnIter.hasNext()){
			IPropertyDefinition defn = defnIter.next();
			buildProperty((IPlainTextPropertyDefinition)defn);
		}
		mockery.checking(new Expectations(){{
			allowing(linkAttribute).propertyIterator(); will(returnIterator(props));
		}});
	}
	
	public ILinkAttribute getAttribute(){
		return this.linkAttribute;
	}
	
	private void buildProperty(final IPlainTextPropertyDefinition defn){
		final IPlainTextAnnotationProperty prop = mockery.mock(IPlainTextAnnotationProperty.class, createPropName(defn.getName()));
		mockery.checking(new Expectations(){{
			allowing(prop).getDefinition(); will(returnValue(defn));
			allowing(prop).getOwner(); will(returnValue(linkAttribute));
		}});
		props.add(prop);
	}

	/**
	 * @param name2
	 * @return
	 */
	private String createPropName(String propName) {
		StringBuilder buf = new StringBuilder(this.name);
		buf.append(Character.toUpperCase(propName.charAt(0)));
		buf.append(propName, 1, propName.length()-1);
		return buf.toString();
	}

}
