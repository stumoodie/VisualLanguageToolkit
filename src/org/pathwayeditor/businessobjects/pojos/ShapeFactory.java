/**
 * 
 */
package org.pathwayeditor.businessobjects.pojos;

import org.pathwayeditor.businessobjects.typedefn.IShapeObjectType;

import uk.ed.inf.graph.compound.archetypal.ArchetypalCompoundNode;
import uk.ed.inf.graph.compound.archetypal.ArchetypalCompoundNodeFactory;

/**
 * @author smoodie
 *
 */
public class ShapeFactory extends ArchetypalCompoundNodeFactory {
	private IShapeObjectType objectType;
	
	/**
	 * @param parent
	 */
	public ShapeFactory(CommonCanvasObject parent) {
		super(parent);
	}

	public void setObjectType(IShapeObjectType objectType){
		this.objectType = objectType;
	}
	
	/* (non-Javadoc)
	 * @see uk.ed.inf.graph.compound.impl.ArchetypalCompoundNodeFactory#newNode(uk.ed.inf.graph.compound.impl.ArchetypalCompoundNode, int)
	 */
	@Override
	protected ArchetypalCompoundNode newNode(ArchetypalCompoundNode parent,	int nodeIndex) {
		return new Shape(this.objectType, (CommonCanvasObject)parent, nodeIndex);
	}

	/**
	 * @param srcNode
	 * @return
	 */
	public Shape createShapeCopy(Shape srcNode) {
		return null;
	}

}
