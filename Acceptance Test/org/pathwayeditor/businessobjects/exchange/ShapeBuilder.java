/*
Copyright 2009-2011, Court of the University of Edinburgh
*/

package org.pathwayeditor.businessobjects.exchange;

import org.pathwayeditor.businessobjects.drawingprimitives.ICanvasElementAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeAttributeFactory;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPlainTextAnnotationProperty;
import org.pathwayeditor.businessobjects.typedefn.IShapeObjectType;

import uk.ac.ed.inf.graph.compound.ICompoundGraphElement;
import uk.ac.ed.inf.graph.compound.ICompoundNode;
import uk.ac.ed.inf.graph.compound.ICompoundNodeFactory;

/**
 * @author Stuart Moodie
 *
 */
public class ShapeBuilder {
	private static final String NAME_PROP_NAME = "shapeTypeAName";
	private ICompoundGraphElement parent;
	private IShapeObjectType objectType;
	private ICompoundNode compoundNode;
	private String name;

	public ShapeBuilder(ICompoundGraphElement parent, IShapeObjectType objectType){
		this.parent = parent;
		this.objectType = objectType;
	}

	public ShapeBuilder(){
		this(null, null);
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public void build(){
		ICanvasElementAttribute canvasAtt = (ICanvasElementAttribute)this.parent.getAttribute();
		IShapeAttributeFactory shapeAttFact = canvasAtt.getModel().shapeAttributeFactory();
		shapeAttFact.setObjectType(objectType);
		ICompoundNodeFactory nodeFactory = parent.getChildCompoundGraph().nodeFactory();
		nodeFactory.setAttributeFactory(shapeAttFact);
		this.compoundNode = nodeFactory.createNode();
		IShapeAttribute shapeAtt = (IShapeAttribute)this.compoundNode.getAttribute();
		IPlainTextAnnotationProperty prop = (IPlainTextAnnotationProperty)shapeAtt.getProperty(NAME_PROP_NAME);
		prop.setValue(name);
	}
	
	
	public ICompoundNode getNode(){
		return this.compoundNode;
	}


	public void setParent(ICompoundNode parent) {
		this.parent = parent;
	}

	
	public ICompoundNode getParent(){
		return this.compoundNode;
	}


	public void setObjectType(IShapeObjectType objectType) {
		this.objectType = objectType;
	}

	
	public IShapeObjectType getObjectType(){
		return this.objectType;
	}
}
