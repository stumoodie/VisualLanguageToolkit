/**
 * 
 */
package org.pathwayeditor.businessobjects.hibernate.pojos;

import java.util.Iterator;

import org.pathwayeditor.businessobjects.drawingprimitives.ILinkEdge;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeNode;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.INodeChangeListener;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.ListenableNodeStructureChangeItem;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.ModelStructureChangeType;
import org.pathwayeditor.businessobjects.hibernate.pojos.graph.IterationCaster;
import org.pathwayeditor.businessobjects.typedefn.IShapeObjectType;

import uk.ed.inf.graph.compound.base.BaseCompoundEdge;

/**
 * @author nhanlon
 *
 */
public class HibShapeNode extends HibCompoundNode implements IShapeNode {
	private HibShapeAttribute shapeAttribute ;
	private ListenableNodeStructureChangeItem listenable = new ListenableNodeStructureChangeItem(this); 
	
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
		this.shapeAttribute = shapeAttribute;
		this.shapeAttribute.setShapeNode(this);
	}
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShapeNode#getChildModel()
	 */
	public HibSubModel getSubModel() {
		return this.getChildCompoundGraph();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShapeNode#getAttribute()
	 */
	public HibShapeAttribute getAttribute() {
		return this.shapeAttribute;
	}

	public void setAttribute(HibShapeAttribute shapeAttribute) {
		this.shapeAttribute = shapeAttribute;
		this.shapeAttribute.setShapeNode(this);
	}
	
//	public void changeAttribute(HibShapeAttribute newShapeAttribute){
//		if(this.shapeAttribute != null){
//			this.shapeAttribute.setShapeNode(null);
//		}
//		if(newShapeAttribute != null){
//			HibShapeNode oldShape = newShapeAttribute.getShapeNode(); 
//			if(oldShape != null){
//				oldShape.setAttribute(null);
//			}
//			newShapeAttribute.setShapeNode(this);
//		}
//		this.shapeAttribute = newShapeAttribute;
//	}

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
	
	@Override
	public String toString(){
		StringBuilder builder = new StringBuilder(this.getClass().getSimpleName());
		builder.append("(model=");
		builder.append(this.getModel());
		builder.append(", index=");
		builder.append(this.getIndex());
		builder.append(", removed=");
		builder.append(this.isRemoved());
		builder.append(", attribute=");
		builder.append(this.getAttribute());
		builder.append(")");
		return builder.toString();
	}

	/* (non-Javadoc)
	 * @see uk.ed.inf.graph.compound.base.BaseCompoundNode#removalAction()
	 */
	@Override
	protected void removalAction(boolean removed) {
		ModelStructureChangeType type;
		if(removed){
			type = ModelStructureChangeType.DELETED;
		}
		else{
			type = ModelStructureChangeType.ADDED;
			this.shapeAttribute.setShapeNode(this);
		}
		this.getModel().notifyNodeStructureChange(type, this);
		this.getParentNode().getSubModel().notifyNodeStructureChange(type, this);
	}

	public void notifySourceEdgeChange(ModelStructureChangeType type, HibLinkEdge hibLinkEdge) {
		listenable.notifySourceNodeStructureChange(type, hibLinkEdge);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.listeners.INodeChangeListenee#addNodeChangeListener(org.pathwayeditor.businessobjects.drawingprimitives.listeners.INodeChangeListener)
	 */
	public void addNodeChangeListener(INodeChangeListener listener) {
		this.listenable.addNodeChangeListener(listener);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.listeners.INodeChangeListenee#nodeChangeListenerIterator()
	 */
	public Iterator<INodeChangeListener> nodeChangeListenerIterator() {
		return this.listenable.nodeChangeListenerIterator();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.listeners.INodeChangeListenee#removeNodeChangeListener(org.pathwayeditor.businessobjects.drawingprimitives.listeners.INodeChangeListener)
	 */
	public void removeNodeChangeListener(INodeChangeListener listener) {
		this.listenable.removeNodeChangeListener(listener);
	}

	/**
	 * @param type
	 * @param hibLinkEdge
	 */
	public void notifyTargetEdgeChange(ModelStructureChangeType type, HibLinkEdge hibLinkEdge) {
		listenable.notifyTargetNodeStructureChange(type, hibLinkEdge);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.hibernate.pojos.HibCompoundNode#isValid()
	 */
	@Override
	public boolean isValid() {
		return this.shapeAttribute != null && this.shapeAttribute.isValid();
	}
	
	@Override
	public HibCompoundNode getParentNode() {
		return this.getHibParentNode();
	}

}
