/**
 * 
 */
package org.pathwayeditor.businessobjects.hibernate.pojos;

import java.util.Iterator;

import org.pathwayeditor.businessobjects.drawingprimitives.ILinkEdge;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeNode;
import org.pathwayeditor.businessobjects.hibernate.pojos.graph.IterationCaster;
import org.pathwayeditor.businessobjects.typedefn.IShapeObjectType;

import uk.ed.inf.graph.compound.base.BaseCompoundEdge;

/**
 * @author nhanlon
 *
 */
public class HibShapeNode extends HibCompoundNode implements IShapeNode {
	private HibShapeAttribute shapeAttribute ;
	
	/**
	 * Constructor should only be used by hiberate.
	 * @deprecated Application code should not use this constructor. Use one of the other constructors instead.
	 */
	HibShapeNode(){
		super();
	}
	
	/**
	 * Constructor to be used by application code.
	 * @param parentNode
	 * @param nodeIndex
	 * @param shapeAttribute
	 */
	public HibShapeNode(HibCompoundNode parentNode, int nodeIndex, HibShapeAttribute shapeAttribute){
		super(parentNode.getGraph(), parentNode, nodeIndex);
		this.changeAttribute(shapeAttribute);
	}
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShapeNode#getChildModel()
	 */
	public HibSubModel getSubCanvas() {
		return this.getChildCompoundGraph();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShapeNode#getAttribute()
	 */
	public HibShapeAttribute getAttribute() {
		return this.shapeAttribute;
	}

	void setAttribute(HibShapeAttribute shapeAttribute) {
		this.shapeAttribute = shapeAttribute;
	}
	
	public void changeAttribute(HibShapeAttribute newShapeAttribute){
		if(this.shapeAttribute != null){
			this.shapeAttribute.setShapeNode(null);
		}
		if(newShapeAttribute != null){
			newShapeAttribute.setShapeNode(this);
		}
		this.shapeAttribute = newShapeAttribute;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IDrawingNode#getObjectType()
	 */
	public IShapeObjectType getObjectType() {
		return this.shapeAttribute.getObjectType();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShapeNode#getNumSourceLinks()
	 */
	public int getNumSourceLinks() {
		return this.getOutDegree();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShapeNode#getNumTargetLinks()
	 */
	public int getNumTargetLinks() {
		return this.getInDegree();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShapeNode#sourceLinkIterator()
	 */
	public Iterator<ILinkEdge> sourceLinkIterator() {
		return new IterationCaster<ILinkEdge, BaseCompoundEdge>(this.getOutEdgeIterator());
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShapeNode#targetLinkIterator()
	 */
	public Iterator<ILinkEdge> targetLinkIterator() {
		return new IterationCaster<ILinkEdge, BaseCompoundEdge>(this.getInEdgeIterator());
	}
}
