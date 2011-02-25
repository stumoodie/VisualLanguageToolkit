/*
Copyright 2009-2011, Court of the University of Edinburgh
*/

package org.pathwayeditor.testfixture;

import static org.hamcrest.Matchers.equalTo;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.pathwayeditor.businessobjects.drawingprimitives.ILabelAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.IModel;
import org.pathwayeditor.businessobjects.drawingprimitives.IRootAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LineStyle;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationProperty;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPlainTextAnnotationProperty;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPlainTextPropertyDefinition;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyDefinition;
import org.pathwayeditor.businessobjects.typedefn.IShapeObjectType;
import org.pathwayeditor.figure.geometry.Dimension;
import org.pathwayeditor.figure.geometry.Envelope;
import org.pathwayeditor.figure.geometry.Point;

/**
 * @author Stuart Moodie
 *
 */
public class ShapeAttributeBuilder {
	private final Mockery mockery;
	private final String name;
	private IModel canvas;
	private int idx;
	private IShapeObjectType objectType;
	private IShapeAttribute shapeAttribute;
	private List<IAnnotationProperty> props = new LinkedList<IAnnotationProperty>();
	
	public ShapeAttributeBuilder(Mockery mockery, String name, IShapeObjectType objectType, IModel canvas, int idx){
		this.mockery = mockery;
		this.name = name;
		this.canvas = canvas;
		this.idx = idx;
		this.objectType = objectType;
	}
	
	public void build(){
		shapeAttribute = mockery.mock(IShapeAttribute.class, name);
		final Point location = new Point(24.0, 20.0);
		final Dimension size = new Dimension(21.0, 22.0);
		mockery.checking(new Expectations(){{
			allowing(shapeAttribute).getCreationSerial(); will(returnValue(idx));
			allowing(shapeAttribute).getObjectType(); will(returnValue(objectType));
			allowing(shapeAttribute).getBounds(); will(returnValue(new Envelope(location, size)));
			allowing(shapeAttribute).getModel(); will(returnValue(canvas));
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
		Iterator<IPropertyDefinition> defnIter = objectType.getDefaultAttributes().propertyDefinitionIterator();
		while(defnIter.hasNext()){
			IPropertyDefinition defn = defnIter.next();
			buildProperty((IPlainTextPropertyDefinition)defn);
		}
		mockery.checking(new Expectations(){{
			allowing(shapeAttribute).propertyIterator(); will(returnIterator(props));
		}});
	}
	
	public IShapeAttribute getAttribute(){
		return this.shapeAttribute;
	}
	
	private void buildProperty(final IPlainTextPropertyDefinition defn){
		final IPlainTextAnnotationProperty prop = mockery.mock(IPlainTextAnnotationProperty.class, createPropName(defn.getName()));
		final String propName = defn.getName();
		mockery.checking(new Expectations(){{
			allowing(prop).getDefinition(); will(returnValue(defn));
			allowing(prop).getOwner(); will(returnValue(shapeAttribute));
			allowing(shapeAttribute).getProperty(with(equalTo(propName))); will(returnValue(prop));
			allowing(shapeAttribute).getProperty(with(defn)); will(returnValue(prop));
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
		buf.append(propName, 1, propName.length());
		return buf.toString();
	}

}
