/*
Copyright 2009-2011, Court of the University of Edinburgh
*/

package org.pathwayeditor.businessobjects.exchange;

import org.pathwayeditor.businessobjects.drawingprimitives.ICanvasElementAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.IDrawingNodeAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkAttributeFactory;
import org.pathwayeditor.businessobjects.drawingprimitives.IRootAttribute;
import org.pathwayeditor.businessobjects.typedefn.ILinkObjectType;

import uk.ac.ed.inf.graph.compound.CompoundNodePair;
import uk.ac.ed.inf.graph.compound.ICompoundEdge;
import uk.ac.ed.inf.graph.compound.ICompoundEdgeFactory;
import uk.ac.ed.inf.graph.compound.ICompoundNode;

/**
 * LinkBuilder
 *
 * @author Stuart Moodie
 *
 */
public class LinkBuilder {
	private ICanvasElementAttribute parent;
	private ILinkObjectType linkObjectType;
	
	
	public LinkBuilder(IRootAttribute rootAttribute, ILinkObjectType linkObjectType) {
		this.linkObjectType = linkObjectType;
		this.parent = rootAttribute;
	}

	public ILinkAttribute create(IDrawingNodeAttribute srcAtt, IDrawingNodeAttribute tgtAtt){
		ILinkAttributeFactory linkAttFact = parent.getModel().linkAttributeFactory();
		linkAttFact.setObjectType(linkObjectType);
		ICompoundEdgeFactory edgeFactory = parent.getCurrentElement().getChildCompoundGraph().edgeFactory();
		edgeFactory.setPair(new CompoundNodePair((ICompoundNode)srcAtt.getCurrentElement(), (ICompoundNode)tgtAtt.getCurrentElement()));
		edgeFactory.setAttributeFactory(linkAttFact);
		ICompoundEdge compoundEdge = edgeFactory.createEdge();
		ILinkAttribute shapeAtt = (ILinkAttribute)compoundEdge.getAttribute();
		return shapeAtt;
	}
	
	
	public ICanvasElementAttribute getParent() {
		return this.parent;
	}


	public void setParent(ICanvasElementAttribute parent) {
		this.parent = parent;
	}


	public ILinkObjectType getLinkObjectType() {
		return this.linkObjectType;
	}


	public void setLinkObjectType(ILinkObjectType linkObjectType) {
		this.linkObjectType = linkObjectType;
	}

}
