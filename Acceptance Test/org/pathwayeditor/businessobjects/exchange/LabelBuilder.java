/*
Copyright 2009-2011, Court of the University of Edinburgh
*/

package org.pathwayeditor.businessobjects.exchange;

import org.pathwayeditor.businessobjects.drawingprimitives.ICanvasElementAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.ILabelAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.ILabelAttributeFactory;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotatedObject;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationProperty;
import org.pathwayeditor.businessobjects.typedefn.ILabelObjectType;

import uk.ac.ed.inf.graph.compound.ICompoundNode;
import uk.ac.ed.inf.graph.compound.ICompoundNodeFactory;

/**
 * LabelBuilder
 *
 * @author Stuart Moodie
 *
 */
public class LabelBuilder {
	private IAnnotatedObject owningAttribute;
	private String propName;

	public LabelBuilder(IAnnotatedObject owningAttribute, String propName) {
		this.owningAttribute = owningAttribute;
		this.propName = propName;
	}
	
	public ILabelAttribute create(){
		IAnnotationProperty annotProp = owningAttribute.getProperty(propName);
		ICanvasElementAttribute att = (ICanvasElementAttribute)owningAttribute;
		ILabelAttributeFactory attFact = att.getModel().labelAttributeFactory();
		ICompoundNodeFactory labelNodeFact = att.getCurrentElement().getChildCompoundGraph().nodeFactory();
		labelNodeFact.setAttributeFactory(attFact);
		ILabelObjectType labelObjectType = att.getModel().getNotationSubsystem().getSyntaxService().getLabelObjectTypeByProperty(annotProp.getDefinition());
		attFact.setLabelObjectType(labelObjectType);
		attFact.setProperty(annotProp);
		ICompoundNode labelNode = labelNodeFact.createNode();
		ILabelAttribute retVal = (ILabelAttribute)labelNode.getAttribute();
		return retVal;
	}

	public IAnnotatedObject getOwningAttribute() {
		return this.owningAttribute;
	}

	public void setOwningAttribute(IAnnotatedObject owningAttribute) {
		this.owningAttribute = owningAttribute;
	}

	public String getPropName() {
		return this.propName;
	}

	public void setPropName(String propName) {
		this.propName = propName;
	}

}
