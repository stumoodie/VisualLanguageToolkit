/*
Copyright 2009-2011, Court of the University of Edinburgh
*/

package org.pathwayeditor.businessobjects.exchange;

import org.pathwayeditor.businessobjects.drawingprimitives.IAnchorNodeAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.IAnchorNodeAttributeFactory;
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkAttribute;
import org.pathwayeditor.businessobjects.typedefn.IAnchorNodeObjectType;

import uk.ac.ed.inf.graph.compound.ICompoundNode;
import uk.ac.ed.inf.graph.compound.ICompoundNodeFactory;

/**
 * AnchorNodeBuilder
 *
 * @author Stuart Moodie
 *
 */
public class AnchorNodeBuilder {
	private ILinkAttribute parentLink;
	private IAnchorNodeObjectType objectType;
	
	public AnchorNodeBuilder(ILinkAttribute link, IAnchorNodeObjectType objectType) {
		this.parentLink = link;
		this.objectType = objectType;
	}

	public ILinkAttribute getParentLink() {
		return this.parentLink;
	}

	public void setParentLink(ILinkAttribute parentLink) {
		this.parentLink = parentLink;
	}

	public IAnchorNodeObjectType getObjectType() {
		return this.objectType;
	}

	public void setObjectType(IAnchorNodeObjectType objectType) {
		this.objectType = objectType;
	}

	public IAnchorNodeAttribute create(){
		ICompoundNodeFactory nodeFact = parentLink.getCurrentElement().getChildCompoundGraph().nodeFactory();
		IAnchorNodeAttributeFactory attFact = parentLink.getModel().anchorNodeAttributeFactory();
		attFact.setObjectType(objectType);
		nodeFact.setAttributeFactory(attFact);
		ICompoundNode node = nodeFact.createNode();
		return (IAnchorNodeAttribute)node.getAttribute();
	}
	
}
