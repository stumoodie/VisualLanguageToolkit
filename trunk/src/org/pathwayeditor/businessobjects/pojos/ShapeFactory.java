/**
 * 
 */
package org.pathwayeditor.businessobjects.pojos;

import org.pathwayeditor.businessobjects.typedefn.IShapeObjectType;

import uk.ed.inf.graph.compound.archetypal.ArchetypalCompoundNode;
import uk.ed.inf.graph.compound.base.BaseCompoundNode;
import uk.ed.inf.graph.compound.base.BaseCompoundNodeFactory;

/**
 * @author smoodie
 *
 */
public class ShapeFactory extends BaseCompoundNodeFactory {
	private IShapeObjectType objectType;
	
	/**
	 * @param parent
	 */
	public ShapeFactory(CommonModelNode parent) {
		super(parent);
	}

	public void setObjectType(IShapeObjectType objectType){
		this.objectType = objectType;
	}
	
	/* (non-Javadoc)
	 * @see uk.ed.inf.graph.compound.impl.ArchetypalCompoundNodeFactory#newNode(uk.ed.inf.graph.compound.impl.ArchetypalCompoundNode, int)
	 */
	@Override
	protected ArchetypalCompoundNode newNode(BaseCompoundNode parent,	int nodeIndex) {
		return new Shape(this.objectType, (CommonModelNode)parent, nodeIndex);
	}

	/**
	 * @param srcNode
	 * @return
	 */
	public Shape createShapeCopy(Shape srcNode) {
		return null;
	}

}
