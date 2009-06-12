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

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.log4j.Logger;
import org.pathwayeditor.businessobjects.drawingprimitives.ICanvasAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.ILabelNode;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeNode;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationProperty;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibCanvas;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibCompoundNode;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibLabelAttribute;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibLabelNode;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibLinkAttribute;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibLinkEdge;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibProperty;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibShapeAttribute;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibShapeNode;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibSubModel;

import uk.ed.inf.graph.compound.base.BaseChildCompoundGraph;
import uk.ed.inf.graph.compound.base.BaseCompoundEdge;
import uk.ed.inf.graph.compound.base.BaseCompoundNode;
import uk.ed.inf.graph.compound.base.BaseGraphCopyBuilder;

/**
 * @author smoodie
 *
 */
public class CompoundGraphCopyBuilder extends BaseGraphCopyBuilder {
	private final Logger logger = Logger.getLogger(this.getClass());
	private final Map<HibProperty, HibLabelAttribute> copiedLabelCache;
	
	/**
	 * 
	 */
	public CompoundGraphCopyBuilder() {
		super();
		this.copiedLabelCache = new HashMap<HibProperty, HibLabelAttribute>();
	}

	/* (non-Javadoc)
	 * @see uk.ed.inf.graph.compound.base.BaseGraphCopyBuilder#createCopyOfEdge(uk.ed.inf.graph.compound.base.BaseCompoundEdge, uk.ed.inf.graph.compound.base.BaseChildCompoundGraph, uk.ed.inf.graph.compound.base.BaseCompoundNode, uk.ed.inf.graph.compound.base.BaseCompoundNode)
	 */
	@Override
	protected BaseCompoundEdge createCopyOfEdge(BaseCompoundEdge srcEdge, BaseChildCompoundGraph edgeOwner,
			BaseCompoundNode outNode, BaseCompoundNode inNode) {
		HibLinkEdge retVal = createCopyOfLinkEdge  ( ((HibLinkEdge)srcEdge).getAttribute() , (HibCompoundNode) outNode,
				(HibCompoundNode) inNode , (HibSubModel) edgeOwner );
		logger.debug("created dest edge: (idx=" + retVal.getIndex() + ", att=" + retVal.getAttribute().getCreationSerial()
					+ ") from src edge: (idx=" + srcEdge.getIndex() + ", att=" + ((HibLinkEdge)srcEdge).getAttribute().getCreationSerial()
					+ ")");
		return retVal;
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
			logger.debug("created shape node: (idx=" + retVal.getIndex() + ", att=" + retVal.getAttribute().getCreationSerial()
					+ ") from src edge: (idx=" + srcNode.getIndex() + ", att=" + ((HibCompoundNode)srcNode).getAttribute().getCreationSerial()
					+ ")");
		}
		else if(srcNode instanceof ILabelNode){
			HibLabelNode srcLabelNode = (HibLabelNode)srcNode;
//			HibLabelAttribute srcAttribute = srcLabelNode.getAttribute();
			retVal = createCopyOfLabelNode(destHibParentNode, srcLabelNode);
			logger.debug("created label node: (idx=" + retVal.getIndex() + ", att=" + retVal.getAttribute().getCreationSerial()
					+ ") from src edge: (idx=" + srcNode.getIndex() + ", att=" + ((HibCompoundNode)srcNode).getAttribute().getCreationSerial()
					+ ")");
		}
		return retVal;
	}
	
	/**
	 * @param destHibParentNode
	 * @param srcAttribute
	 * @return
	 */
	private HibCompoundNode createCopyOfLabelNode(HibCompoundNode destHibParentNode, HibLabelNode srcLabelNode) {
		ICanvasAttribute destParentCanvasAttribute = destHibParentNode.getAttribute();
		HibProperty srcProperty = srcLabelNode.getAttribute().getProperty();
		// Now test if parent contains this property. If it does then the label is a label for the parent shape
		// and we can add the property from the copied parent shape as the copied property that should be
		// added to the copied label.
		HibCompoundNode parentNode = srcLabelNode.getParentNode();
		HibProperty copiedProperty = null;
		if(parentNode instanceof IShapeNode) {
			IShapeNode parentShape = (IShapeNode)parentNode;
			if(parentShape.getAttribute().containsProperty(srcProperty)) {
				// parent contains prop so label must be for a property held by this shape 
				IShapeNode copiedParentNode = (IShapeNode)super.getCopiedNode(parentNode);
				if(copiedParentNode != null) {
					copiedProperty = (HibProperty)copiedParentNode.getAttribute().getProperty(srcProperty.getDefinition());
				}
				else {
					throw new IllegalStateException("Assumption that parent shape will be copied before label is invalid");
				}
			}
		}
		else if(parentNode instanceof ILabelNode) {
			throw new IllegalStateException("parent node of a label cannot be another label");
		}
		boolean cacheCopiedLabel = false;
		if(copiedProperty == null) {
			// In this case, the property must be associated with a link which hasn't been copied yet. Therefore we will use the src
			// property to create the label and then replace it with the copied property whent he links are copied later.
			copiedProperty = srcProperty;
			// to do reassign the copied prop later we need to cache the copied label.
			cacheCopiedLabel = true;
		}
		HibCanvas destCanvas = (HibCanvas)destParentCanvasAttribute.getCanvas();
		HibLabelAttribute srcAttribute = srcLabelNode.getAttribute();
		HibLabelAttribute destAttribute = new HibLabelAttribute(destCanvas, destCanvas.getCreationSerialCounter().nextIndex(), srcAttribute, copiedProperty);
		LabelNodeFactory fact = destHibParentNode.getChildCompoundGraph().labelNodeFactory();
		fact.setAttribute(destAttribute);
		HibCompoundNode retVal = fact.createLabel();
		if(cacheCopiedLabel) {
			this.copiedLabelCache.put(srcProperty, destAttribute);
		}
		return retVal;
	}

	private HibShapeNode createCopyOfShapeNode(HibCompoundNode destHibParentNode, HibShapeAttribute otherAttribute){
		ICanvasAttribute destParentCanvasAttribute = destHibParentNode.getAttribute();
		HibCanvas destCanvas = (HibCanvas)destParentCanvasAttribute.getCanvas();
		HibShapeAttribute destAttribute = new HibShapeAttribute(destCanvas, destCanvas.getCreationSerialCounter().nextIndex(), otherAttribute);
		ShapeNodeFactory fact = destHibParentNode.getChildCompoundGraph().shapeNodeFactory();
		fact.setAttribute(destAttribute);
		return fact.createShapeNode();
	}
	
	private HibLinkEdge createCopyOfLinkEdge ( HibLinkAttribute srcAttribute , BaseCompoundNode outNode,
			BaseCompoundNode inNode , HibSubModel edgeOwner )
	{
		// FIXME: This will nee to be refactored one the labels are reorganised.
		// This should make label copying much more efficient.
		HibCanvas destCanvas = (HibCanvas)edgeOwner.getModel().getCanvas();
		HibLinkAttribute destAttribute = new HibLinkAttribute ( destCanvas , destCanvas.getCreationSerialCounter().nextIndex() , srcAttribute) ;
		LinkEdgeChildFactory edgeFact = edgeOwner.edgeFactory() ;
		edgeFact.setPair(outNode, inNode);
		edgeFact.setAttribute(destAttribute) ;
		HibLinkEdge retVal = edgeFact.createLinkEdge() ;
		// now lest look up any labels from the cache, using the properties of the src link attribute
		// (remember this was cached before the link was copied so we cannot use the destAttribute).
		Iterator<IAnnotationProperty> propIter = srcAttribute.propertyIterator();
		while(propIter.hasNext()) {
			IAnnotationProperty srcProp = propIter.next();
			HibLabelAttribute copiedLabel = this.copiedLabelCache.get(srcProp);
			if(copiedLabel!= null) {
				// now add in the copied prop from the destLinkAttribute
				HibProperty copiedProp = destAttribute.getProperty(srcProp.getDefinition());
				copiedLabel.setVisualisableProperty(copiedProp);
			}
		}
		return retVal;
	}

	/* (non-Javadoc)
	 * @see uk.ed.inf.graph.compound.base.BaseGraphCopyBuilder#additionalCopyTasks()
	 */
	@Override
	protected void additionalCopyTasks() {
		// avoid unnecessary memory usage.
		this.copiedLabelCache.clear();
	}

	/* (non-Javadoc)
	 * @see uk.ed.inf.graph.compound.base.BaseGraphCopyBuilder#additionalInitialisation()
	 */
	@Override
	protected void additionalInitialisation() {
		this.copiedLabelCache.clear();
	}
}
