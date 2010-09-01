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

package org.pathwayeditor.testfixture;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Description;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.api.Action;
import org.jmock.api.Invocation;
import org.pathwayeditor.businessobjects.drawingprimitives.ICanvasElementAttributeVisitor;
import org.pathwayeditor.businessobjects.drawingprimitives.ILabelAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.IRootAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LineStyle;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPlainTextAnnotationProperty;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPlainTextPropertyDefinition;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyBuilder;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationSubsystem;
import org.pathwayeditor.businessobjects.typedefn.IShapeObjectType;
import org.pathwayeditor.figure.geometry.Dimension;
import org.pathwayeditor.figure.geometry.Envelope;
import org.pathwayeditor.figure.geometry.Point;

/**
 * @author smoodie
 *
 */
public class CanvasTestFixture extends CommonTestFixture {
	public static final int NUM_ATTRIBUTES = 4;
	public static final String ROOT_ATT_ID = "rootAttribute1";
	public static final String SHAPE1_ATT_ID = "shapeAttribute1";
	public static final String SHAPE2_ATT_ID = "shapeAttribute2";
	public static final String LABEL9_ATT_ID = "labelAttribute9";
	public static final String LINK1_ATT_ID = "link1Attribute";

	public static final int ROOT_ATT_IDX = 0;
	public static final int SHAPE1_ATT_IDX = 1;
	public static final int SHAPE2_ATT_IDX = 2;
	public static final int LABEL9_ATT_IDX = 10;
	public static final int LINK1_ATT_IDX = 17;

	public static final int SHAPE1_ATT_OT = 1;

	private static final String ELEMENT_CREATION_ORDER[] = { ROOT_ATT_ID, SHAPE1_ATT_ID, LABEL9_ATT_ID, LINK1_ATT_ID };
//	private static final int SERIAL_CTR_IDX = 30;

	private INotationSubsystem notationSubsystem;
	private IRootAttribute rootAttribute;
	
	public class CreatePropertyAction implements Action {
		private IPlainTextPropertyDefinition defn;
		
		public CreatePropertyAction(IPlainTextPropertyDefinition builder){
			this.defn = builder;
		}
		
		@Override
		public void describeTo(Description descn) {
			descn.appendText("get removal state");
		}

		@Override
		public IPlainTextAnnotationProperty invoke(Invocation invocation) throws Throwable {
			IPropertyBuilder builder = (IPropertyBuilder)invocation.getParameter(0);
			IPlainTextAnnotationProperty retVal = builder.createPlainTextProperty(this.defn);
			return retVal;
		}
		
	}
	
	public Action buildTextProperty(IPlainTextPropertyDefinition defn){
		return new CreatePropertyAction(defn);
	}

	public static class VisitShapeAttributeAction implements Action {
		
		public VisitShapeAttributeAction(){
		}
		
		@Override
		public void describeTo(Description descn) {
			descn.appendText("get removal state");
		}

		@Override
		public Object invoke(Invocation invocation) throws Throwable {
			IShapeAttribute invokedObj = (IShapeAttribute)invocation.getInvokedObject();
			ICanvasElementAttributeVisitor visitor = (ICanvasElementAttributeVisitor)invocation.getParameter(0);
			visitor.visitShape(invokedObj);
			return null;
		}
		
	}
	
	public static Action visitShapeAttribute(){
		return new VisitShapeAttributeAction();
	}

	private final IObjectBuilder objectBuilders[] = new IObjectBuilder[]{ 
		new GeneralBuilder<IRootAttribute>(ROOT_ATT_ID, new IObjectConstructor<IRootAttribute>(){

			@Override
			public IRootAttribute create() {
				Mockery mockery = getMockery();
				rootAttribute = mockery.mock(IRootAttribute.class, "rootAttribute");
				final Point location = new Point(-12.0, -10.0);
				final Dimension size = new Dimension(400.0, 200.0);
//				final IndexCounter serialCounter = new IndexCounter(SERIAL_CTR_IDX);
				mockery.checking(new Expectations(){{
					allowing(rootAttribute).getCreationSerial(); will(returnValue(ROOT_ATT_IDX));
					allowing(rootAttribute).getObjectType(); will(returnValue(notationSubsystem.getSyntaxService().getRootObjectType()));
					allowing(rootAttribute).getBounds(); will(returnValue(new Envelope(location, size)));
					allowing(rootAttribute).getRootAttribute(); will(returnValue(rootAttribute));
					allowing(rootAttribute).isRemoved(); will(returnValue(false));
					allowing(rootAttribute).compareTo(rootAttribute); will(returnValue(0));
					allowing(rootAttribute).compareTo(with(any(IShapeAttribute.class))); will(returnValue(-1));
					allowing(rootAttribute).compareTo(with(any(ILabelAttribute.class))); will(returnValue(-1));
					allowing(rootAttribute).compareTo(with(any(ILinkAttribute.class))); will(returnValue(-1));
					allowing(rootAttribute).getName(); will(returnValue("Mock Canvas"));
					allowing(rootAttribute).getBackgroundColour(); will(returnValue(RGB.WHITE));
					allowing(rootAttribute).compareTo(rootAttribute); will(returnValue(0));
					allowing(rootAttribute).numCanvasAttributes(); will(returnValue(NUM_ATTRIBUTES));
//					allowing(rootAttribute).getCreationSerialCounter(); will(returnValue(serialCounter));
				}});
				return rootAttribute;
			}

			@Override
			public boolean build() {
//				final IRootAttribute rootAttribute = getObject(ROOT_ATT_ID);
				getMockery().checking(new Expectations(){{
				}});
				return true;
			}
			
		}),
		new GeneralBuilder<IShapeAttribute>(SHAPE1_ATT_ID, new IObjectConstructor<IShapeAttribute>(){

			@Override
			public IShapeAttribute create() {
				Mockery mockery = getMockery();
				final IShapeAttribute shapeAttribute = mockery.mock(IShapeAttribute.class, createMockName(SHAPE1_ATT_ID));
				final Point location = new Point(24.0, 20.0);
				final Dimension size = new Dimension(21.0, 22.0);
				final IRootAttribute rootAttribute = getObject(ROOT_ATT_ID);
				final IShapeObjectType objectType = notationSubsystem.getSyntaxService().getShapeObjectType(SHAPE1_ATT_OT);
				mockery.checking(new Expectations(){{
					allowing(shapeAttribute).getCreationSerial(); will(returnValue(SHAPE1_ATT_IDX));
					allowing(shapeAttribute).getObjectType(); will(returnValue(objectType));
					allowing(shapeAttribute).getBounds(); will(returnValue(new Envelope(location, size)));
					allowing(shapeAttribute).getRootAttribute(); will(returnValue(rootAttribute));
					allowing(shapeAttribute).isRemoved(); will(returnValue(false));
					allowing(shapeAttribute).compareTo(shapeAttribute); will(returnValue(0));
					allowing(shapeAttribute).compareTo(with(any(ILinkAttribute.class))); will(returnValue(-1));
					allowing(shapeAttribute).compareTo(with(any(ILabelAttribute.class))); will(returnValue(-1));
					allowing(shapeAttribute).compareTo(with(any(IRootAttribute.class))); will(returnValue(1));
					allowing(shapeAttribute).propertyIterator(); will(returnIterator());
					allowing(shapeAttribute).getFillColour(); will(returnValue(RGB.WHITE));
					allowing(shapeAttribute).getLineColour(); will(returnValue(RGB.BLACK));
					allowing(shapeAttribute).getLineStyle(); will(returnValue(LineStyle.SOLID));
					allowing(shapeAttribute).getLineWidth(); will(returnValue(1.1));
					allowing(shapeAttribute).getShapeDefinition(); will(returnValue("0 0 0 0 rect"));
				}});
				return shapeAttribute;
			}

			@Override
			public boolean build() {
				getMockery().checking(new Expectations(){{
				}});
				return true;
			}
			
		}),
		new GeneralBuilder<ILabelAttribute>(LABEL9_ATT_ID, new IObjectConstructor<ILabelAttribute>(){

			@Override
			public ILabelAttribute create() {
				Mockery mockery = getMockery();
				final ILabelAttribute labelAttribute = mockery.mock(ILabelAttribute.class, createMockName(LABEL9_ATT_ID));
				final Point location = new Point(24.0, 20.0);
				final Dimension size = new Dimension(21.0, 22.0);
				final IRootAttribute rootAttribute = getObject(ROOT_ATT_ID);
				mockery.checking(new Expectations(){{
					allowing(labelAttribute).getCreationSerial(); will(returnValue(LABEL9_ATT_IDX));
					allowing(labelAttribute).getBounds(); will(returnValue(new Envelope(location, size)));
					allowing(labelAttribute).getRootAttribute(); will(returnValue(rootAttribute));
					allowing(labelAttribute).isRemoved(); will(returnValue(false));
					allowing(labelAttribute).compareTo(labelAttribute); will(returnValue(0));
					allowing(labelAttribute).compareTo(with(any(ILinkAttribute.class))); will(returnValue(-1));
					allowing(labelAttribute).compareTo(with(any(IShapeAttribute.class))); will(returnValue(1));
					allowing(labelAttribute).compareTo(with(any(IRootAttribute.class))); will(returnValue(1));
				}});
				return labelAttribute;
			}

			@Override
			public boolean build() {
//				final ILabelAttribute labelAttribute = getObject(LABEL9_ATT_ID);
//				final ILabelAttribute otherShapeAtts = getObjectArray(new ILabelAttribute[0], )
				getMockery().checking(new Expectations(){{
				}});
				return true;
			}
			
		}),
		new GeneralBuilder<ILinkAttribute>(LINK1_ATT_ID, new IObjectConstructor<ILinkAttribute>(){

			@Override
			public ILinkAttribute create() {
				Mockery mockery = getMockery();
				final ILinkAttribute linkAttribute = mockery.mock(ILinkAttribute.class, createMockName(LINK1_ATT_ID));
				final IRootAttribute rootAttribute = getObject(ROOT_ATT_ID);
				mockery.checking(new Expectations(){{
					allowing(linkAttribute).getCreationSerial(); will(returnValue(LINK1_ATT_IDX));
					allowing(linkAttribute).getRootAttribute(); will(returnValue(rootAttribute));
					allowing(linkAttribute).isRemoved(); will(returnValue(false));
					allowing(linkAttribute).compareTo(linkAttribute); will(returnValue(0));
					allowing(linkAttribute).compareTo(with(any(ILabelAttribute.class))); will(returnValue(1));
					allowing(linkAttribute).compareTo(with(any(IShapeAttribute.class))); will(returnValue(1));
					allowing(linkAttribute).compareTo(with(any(IRootAttribute.class))); will(returnValue(1));
				}});
				return linkAttribute;
			}

			@Override
			public boolean build() {
				return true;
			}
			
		})

	};
	
	public <T> T[] getObjectArray(T[] arr, String ... ids){
		List<T> retVal = new ArrayList<T>(ids.length);
		for(String id : ids){
			T obj = getObject(id);
			retVal.add(obj);
		}
		return retVal.toArray(arr);
	}
	
	public CanvasTestFixture(Mockery mockery, String prefix, INotationSubsystem notationSubsystem){
		super(mockery, prefix);
		this.notationSubsystem = notationSubsystem;
		super.initialise();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.testfixture.CommonTestFixture#getElementBuilders()
	 */
	@Override
	protected IObjectBuilder[] getElementBuilders() {
		return this.objectBuilders;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.testfixture.CommonTestFixture#getElementCreationOrder()
	 */
	@Override
	protected String[] getElementCreationOrder() {
		return ELEMENT_CREATION_ORDER;
	}

	public IRootAttribute getRootAttribute() {
		return rootAttribute;
	}

}
