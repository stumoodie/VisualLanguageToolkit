/**
 * 
 */
package org.pathwayeditor.businessobjects.hibernate.pojos.graph;

import org.pathwayeditor.businessobjects.drawingprimitives.ICanvasAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.ILabelNode;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeNode;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibCanvas;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibCompoundNode;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibLabelAttribute;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibLabelNode;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibModel;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibShapeAttribute;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibShapeNode;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibSubModel;

import uk.ed.inf.graph.compound.base.BaseChildCompoundEdgeFactory;
import uk.ed.inf.graph.compound.base.BaseChildCompoundGraph;
import uk.ed.inf.graph.compound.base.BaseCompoundEdge;
import uk.ed.inf.graph.compound.base.BaseCompoundNode;
import uk.ed.inf.graph.compound.base.BaseGraphCopyBuilder;

/**
 * @author smoodie
 *
 */
public class CompoundGraphCopyBuilder extends BaseGraphCopyBuilder {

	/**
	 * 
	 */
	public CompoundGraphCopyBuilder() {
			super();
	}

	/* (non-Javadoc)
	 * @see uk.ed.inf.graph.compound.base.BaseGraphCopyBuilder#createCopyOfEdge(uk.ed.inf.graph.compound.base.BaseCompoundEdge, uk.ed.inf.graph.compound.base.BaseChildCompoundGraph, uk.ed.inf.graph.compound.base.BaseCompoundNode, uk.ed.inf.graph.compound.base.BaseCompoundNode)
	 */
	@Override
	protected BaseCompoundEdge createCopyOfEdge(BaseCompoundEdge srcEdge,
			BaseChildCompoundGraph edgeOwner, BaseCompoundNode outNode,
			BaseCompoundNode inNode) {
		BaseChildCompoundEdgeFactory edgeFact = edgeOwner.edgeFactory();
		edgeFact.setPair(outNode, inNode);
		return edgeFact.createEdge();
	}

	/* (non-Javadoc)
	 * @see uk.ed.inf.graph.compound.base.BaseGraphCopyBuilder#createCopyOfNode(uk.ed.inf.graph.compound.base.BaseCompoundNode, uk.ed.inf.graph.compound.base.BaseCompoundNode)
	 */
	@Override
	protected HibCompoundNode createCopyOfNode(BaseCompoundNode srcNode, BaseCompoundNode destParentNode) {
		HibCompoundNode retVal = null;
		HibCompoundNode destHibParentNode = (HibCompoundNode)destParentNode;
		if(srcNode instanceof IShapeNode){
			HibShapeNode srcShapeNode = (HibShapeNode)srcNode;
			HibShapeAttribute srcAttribute = srcShapeNode.getAttribute();
			retVal = createCopyOfShapeNode(destHibParentNode, srcAttribute);
		}
		else if(srcNode instanceof ILabelNode){
			HibLabelNode srcLabelNode = (HibLabelNode)srcNode;
			HibLabelAttribute srcAttribute = srcLabelNode.getAttribute();
			retVal = createCopyOfLabelNode(destHibParentNode, srcAttribute);
		}
		return retVal;
	}
	
	/**
	 * @param destHibParentNode
	 * @param srcAttribute
	 * @return
	 */
	private HibCompoundNode createCopyOfLabelNode(HibCompoundNode destHibParentNode, HibLabelAttribute srcAttribute) {
		
		// TODO: need to do find a way of making sure the property is copied before this label is copied
		//       and find a way to get the copied property to this label.
		return null;
	}

	private HibShapeNode createCopyOfShapeNode(HibCompoundNode destHibParentNode, HibShapeAttribute otherAttribute){
		ICanvasAttribute destParentCanvasAttribute = destHibParentNode.getAttribute();
		HibCanvas destCanvas = (HibCanvas)destParentCanvasAttribute.getCanvas();
		HibSubModel destSubModel = destHibParentNode.getChildCompoundGraph();
		HibModel destModel = destSubModel.getModel();
		HibShapeAttribute destAttribute = new HibShapeAttribute(destCanvas, destCanvas.getAttributeSerialCounter().nextIndex(), otherAttribute);
		HibShapeNode retVal = new HibShapeNode(destHibParentNode, destModel.getNodeCounter().nextIndex(), destAttribute);
		return retVal;
	}
}
