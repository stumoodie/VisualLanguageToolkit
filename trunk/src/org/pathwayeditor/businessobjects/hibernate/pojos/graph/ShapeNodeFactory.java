/**
 * 
 */
package org.pathwayeditor.businessobjects.hibernate.pojos.graph;

import org.pathwayeditor.businessobjects.drawingprimitives.IShapeNodeFactory;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibCompoundNode;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibModel;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibShapeNode;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibSubModel;
import org.pathwayeditor.businessobjects.typedefn.IShapeObjectType;

import uk.ed.inf.graph.compound.base.BaseCompoundNode;
import uk.ed.inf.graph.compound.base.BaseCompoundNodeFactory;

/**
 * @author smoodie
 *
 */
public class ShapeNodeFactory extends BaseCompoundNodeFactory implements IShapeNodeFactory {
	private final HibCompoundNode parent;
	private IShapeObjectType shapeObjectType;
	
	/**
	 * @param parent
	 */
	public ShapeNodeFactory(HibCompoundNode parent) {
		super();
		this.parent = parent;
		this.shapeObjectType = null;
	}

	/* (non-Javadoc)
	 * @see uk.ed.inf.graph.compound.base.BaseCompoundNodeFactory#newNode(uk.ed.inf.graph.compound.base.BaseCompoundNode, int)
	 */
	@Override
	protected BaseCompoundNode newNode(BaseCompoundNode parent, int nodeIndex) {
		return new HibShapeNode((HibCompoundNode)parent, nodeIndex, shapeObjectType);
	}

	/* (non-Javadoc)
	 * @see uk.ed.inf.graph.compound.base.BaseCompoundNodeFactory#getGraph()
	 */
	@Override
	public HibModel getGraph() {
		return this.parent.getGraph();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShapeNodeFactory#createShapeNode()
	 */
	public HibShapeNode createShapeNode() {
		return (HibShapeNode)this.createNode();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShapeNodeFactory#getCurrentObjectType()
	 */
	public IShapeObjectType getCurrentObjectType() {
		return this.shapeObjectType;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShapeNodeFactory#getOwningSubCanvas()
	 */
	public HibSubModel getOwningSubCanvas() {
		return this.parent.getChildCompoundGraph();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShapeNodeFactory#setObjectType(org.pathwayeditor.businessobjects.typedefn.IShapeObjectType)
	 */
	public void setObjectType(IShapeObjectType shapeObjectType) {
		this.shapeObjectType = shapeObjectType;
	}

	/* (non-Javadoc)
	 * @see uk.ed.inf.graph.compound.base.BaseCompoundNodeFactory#getParentNode()
	 */
	@Override
	public HibCompoundNode getParentNode() {
		return this.parent;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShapeNodeFactory#canCreateShapeNode()
	 */
	public boolean canCreateShapeNode() {
		boolean retVal = false;
		if(this.shapeObjectType != null){
			retVal = this.parent.getObjectType().getParentingRules().isValidChild(shapeObjectType);
		}
		return retVal;
	}

}
