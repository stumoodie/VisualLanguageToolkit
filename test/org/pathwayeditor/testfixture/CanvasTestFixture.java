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

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.pathwayeditor.businessobjects.drawingprimitives.ICanvas;
import org.pathwayeditor.businessobjects.drawingprimitives.ILabelAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.IRootAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationSubsystem;
import org.pathwayeditor.figure.geometry.Dimension;
import org.pathwayeditor.figure.geometry.Envelope;
import org.pathwayeditor.figure.geometry.Point;

/**
 * @author smoodie
 *
 */
public class CanvasTestFixture extends CommonTestFixture {
	public static final int NUM_ATTRIBUTES = 4;
	public static final String CANVAS_ID = "canvas";
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

	private static final String ELEMENT_CREATION_ORDER[] = { CANVAS_ID, ROOT_ATT_ID, SHAPE1_ATT_ID, LABEL9_ATT_ID, LINK1_ATT_ID };

	private INotationSubsystem notationSubsystem;
	private ICanvas canvas;
	
	private final IObjectBuilder objectBuilders[] = new IObjectBuilder[]{ 
		new GeneralBuilder<ICanvas>(CANVAS_ID, new IObjectConstructor<ICanvas>(){

			@Override
			public ICanvas create() {
				Mockery mockery = getMockery();
				canvas = mockery.mock(ICanvas.class, "canvas");
				mockery.checking(new Expectations(){{
					allowing(canvas).getName(); will(returnValue("Mock Canvas"));
					allowing(canvas).getBackgroundColour(); will(returnValue(RGB.WHITE));
					allowing(canvas).getCanvasSize(); will(returnValue(new Dimension(500, 500)));
					allowing(canvas).getGridSize(); will(returnValue(new Dimension(10,10)));
					allowing(canvas).getINode(); will(returnValue(10002L));
					allowing(canvas).getRepositoryName(); returnValue("mock repository");
					allowing(canvas).getNotationSubsystem(); will(returnValue(notationSubsystem));
					allowing(canvas).compareTo(canvas); will(returnValue(0));
					allowing(canvas).isEmpty(); will(returnValue(false));
					allowing(canvas).numCanvasAttributes(); will(returnValue(NUM_ATTRIBUTES));
				}});
				return canvas;
			}

			@Override
			public boolean build() {
				Mockery mockery = getMockery();
				mockery.checking(new Expectations(){{
				}});
				return true;
			}
		}),
		new GeneralBuilder<IRootAttribute>(ROOT_ATT_ID, new IObjectConstructor<IRootAttribute>(){

			@Override
			public IRootAttribute create() {
				Mockery mockery = getMockery();
				final IRootAttribute rootAttribute = mockery.mock(IRootAttribute.class, "rootAttribute");
				final Point location = new Point(-12.0, -10.0);
				final Dimension size = new Dimension(400.0, 200.0);
				mockery.checking(new Expectations(){{
					allowing(rootAttribute).getCreationSerial(); will(returnValue(ROOT_ATT_IDX));
					allowing(rootAttribute).getObjectType(); will(returnValue(notationSubsystem.getSyntaxService().getRootObjectType()));
					allowing(rootAttribute).getLocation(); will(returnValue(location));
					allowing(rootAttribute).getSize(); will(returnValue(size));
					allowing(rootAttribute).getBounds(); will(returnValue(new Envelope(location, size)));
					allowing(rootAttribute).getCanvas(); will(returnValue(canvas));
					allowing(rootAttribute).isRemoved(); will(returnValue(false));
					allowing(rootAttribute).compareTo(rootAttribute); will(returnValue(0));
					allowing(rootAttribute).compareTo(with(any(IShapeAttribute.class))); will(returnValue(-1));
					allowing(rootAttribute).compareTo(with(any(ILabelAttribute.class))); will(returnValue(-1));
					allowing(rootAttribute).compareTo(with(any(ILinkAttribute.class))); will(returnValue(-1));
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
				mockery.checking(new Expectations(){{
					allowing(shapeAttribute).getCreationSerial(); will(returnValue(SHAPE1_ATT_IDX));
					allowing(shapeAttribute).getObjectType(); will(returnValue(notationSubsystem.getSyntaxService().getShapeObjectType(SHAPE1_ATT_OT)));
					allowing(shapeAttribute).getLocation(); will(returnValue(location));
					allowing(shapeAttribute).getSize(); will(returnValue(size));
					allowing(shapeAttribute).getBounds(); will(returnValue(new Envelope(location, size)));
					allowing(shapeAttribute).getCanvas(); will(returnValue(canvas));
					allowing(shapeAttribute).isRemoved(); will(returnValue(false));
					allowing(shapeAttribute).compareTo(shapeAttribute); will(returnValue(0));
					allowing(shapeAttribute).compareTo(with(any(ILinkAttribute.class))); will(returnValue(-1));
					allowing(shapeAttribute).compareTo(with(any(ILabelAttribute.class))); will(returnValue(-1));
					allowing(shapeAttribute).compareTo(with(any(IRootAttribute.class))); will(returnValue(1));
				}});
				return shapeAttribute;
			}

			@Override
			public boolean build() {
//				final IShapeAttribute shapeAttribute = getObject(SHAPE1_ATT_ID);
//				final IShapeAttribute otherShapeAtts = getObjectArray(new IShapeAttribute[0], )
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
				mockery.checking(new Expectations(){{
					allowing(labelAttribute).getCreationSerial(); will(returnValue(LABEL9_ATT_IDX));
					allowing(labelAttribute).getLocation(); will(returnValue(location));
					allowing(labelAttribute).getSize(); will(returnValue(size));
					allowing(labelAttribute).getBounds(); will(returnValue(new Envelope(location, size)));
					allowing(labelAttribute).getCanvas(); will(returnValue(canvas));
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
				mockery.checking(new Expectations(){{
					allowing(linkAttribute).getCreationSerial(); will(returnValue(LINK1_ATT_IDX));
					allowing(linkAttribute).getCanvas(); will(returnValue(canvas));
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

}
