/*
Copyright 2009, Court of the University of Edinburgh

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License. 
*/
/**
 * 
 */
package org.pathwayeditor.businessobjects.hibernate.pojos.graph;

import org.pathwayeditor.businessobjects.drawingprimitives.ILabelNode;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeNode;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibCompoundNode;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibLabelAttribute;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibLabelNode;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibLinkEdge;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibShapeAttribute;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibShapeNode;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibSubModel;

import uk.ed.inf.graph.compound.base.BaseChildCompoundGraph;
import uk.ed.inf.graph.compound.base.BaseCompoundEdge;
import uk.ed.inf.graph.compound.base.BaseCompoundNode;
import uk.ed.inf.graph.compound.base.BaseGraphMoveBuilder;

/**
 * @author smoodie
 *
 */
public class CompoundGraphMoveBuilder extends BaseGraphMoveBuilder {

	/**
	 * 
	 */
	public CompoundGraphMoveBuilder() {
			super();
	}

	/* (non-Javadoc)
	 * @see uk.ed.inf.graph.compound.base.BaseGraphCopyBuilder#createCopyOfEdge(uk.ed.inf.graph.compound.base.BaseCompoundEdge, uk.ed.inf.graph.compound.base.BaseChildCompoundGraph, uk.ed.inf.graph.compound.base.BaseCompoundNode, uk.ed.inf.graph.compound.base.BaseCompoundNode)
	 */
	@Override
	protected BaseCompoundEdge createMoveOfEdge(BaseCompoundEdge srcEdge,
			BaseChildCompoundGraph edgeOwner, BaseCompoundNode outNode,
			BaseCompoundNode inNode) {
		LinkEdgeChildFactory edgeFact = ((HibSubModel)edgeOwner).linkEdgeFactory();
		edgeFact.setAttribute(((HibLinkEdge)srcEdge).getAttribute());
		edgeFact.setPair(outNode, inNode);
		BaseCompoundEdge retVal = edgeFact.createEdge();
		return retVal;
	}

	/* (non-Javadoc)
	 * @see uk.ed.inf.graph.compound.base.BaseGraphCopyBuilder#createCopyOfNode(uk.ed.inf.graph.compound.base.BaseCompoundNode, uk.ed.inf.graph.compound.base.BaseCompoundNode)
	 */
	@Override
	protected HibCompoundNode createMoveOfNode(BaseCompoundNode srcNode, BaseCompoundNode destParentNode) {
		HibCompoundNode retVal = null;
		HibCompoundNode destHibParentNode = (HibCompoundNode)destParentNode;
		if(srcNode instanceof IShapeNode){
			HibShapeNode srcShapeNode = (HibShapeNode)srcNode;
			HibShapeAttribute srcAttribute = srcShapeNode.getAttribute();
			retVal = moveShapeNode(destHibParentNode, srcAttribute);
		}
		else if(srcNode instanceof ILabelNode){
			HibLabelNode srcLabelNode = (HibLabelNode)srcNode;
			HibLabelAttribute srcAttribute = srcLabelNode.getAttribute();
			retVal = moveLabelNode(destHibParentNode, srcAttribute);
		}
		return retVal;
	}
	
	/**
	 * @param destHibParentNode
	 * @param srcAttribute
	 * @return
	 */
	private HibCompoundNode moveLabelNode(HibCompoundNode destHibParentNode, HibLabelAttribute srcAttribute) {
		LabelNodeFactory fact = destHibParentNode.getChildCompoundGraph().labelNodeFactory();
		fact.setAttribute(srcAttribute);
		fact.setProperty(null);
		return fact.createLabel();
	}

	private HibShapeNode moveShapeNode(HibCompoundNode destHibParentNode, HibShapeAttribute otherAttribute){
		ShapeNodeFactory fact = destHibParentNode.getChildCompoundGraph().shapeNodeFactory();
		fact.setAttribute(otherAttribute);
		fact.setObjectType(null);
		HibShapeNode retVal = fact.createShapeNode();
		return retVal;
	}

	/* (non-Javadoc)
	 * @see uk.ed.inf.graph.compound.base.BaseGraphMoveBuilder#additionalInitialisation()
	 */
	@Override
	protected void additionalInitialisation() {
	}

	/* (non-Javadoc)
	 * @see uk.ed.inf.graph.compound.base.BaseGraphMoveBuilder#additionalMoveTasks()
	 */
	@Override
	protected void additionalMoveTasks() {
	}
}
