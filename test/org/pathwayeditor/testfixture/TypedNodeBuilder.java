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

package org.pathwayeditor.testfixture;

import org.pathwayeditor.businessobjects.drawingprimitives.ILinkEdgeFactory;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeNodeFactory;
import org.pathwayeditor.businessobjects.drawingprimitives.ISubModel;
import org.pathwayeditor.businessobjects.drawingprimitives.ITypedDrawingNode;

import uk.ac.ed.inf.graph.compound.ICompoundNode;

/**
 * @author smoodie
 *
 */
public class TypedNodeBuilder implements ITypedNodeBuilder {
	private final String elementId;
	private ITypedNodeConstructor nodeConstructor;
	private final ITypedNodeConstructor defaultConstructor;
	private ICompoundNode graphNode;
	private ITypedDrawingNode shapeNode;
	private ISubModel subModel;
	private IShapeNodeFactory nodeFactory;
	private ILinkEdgeFactory edgeFactory;

	public TypedNodeBuilder(String elementId, ITypedNodeConstructor constructor){
		this.elementId = elementId;
		this.defaultConstructor = constructor;
	}
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.testfixture.IObjectBuilder#getElementId()
	 */
	@Override
	public String getElementId() {
		return this.elementId;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.testfixture.IObjectBuilder#create()
	 */
	@Override
	public void create() {
		if(nodeConstructor == null || (graphNode = nodeConstructor.createCompoundNode()) == null){
			graphNode = defaultConstructor.createCompoundNode();
		}
		if(nodeConstructor == null || (shapeNode = nodeConstructor.createShapeNode(graphNode)) == null){
			shapeNode = defaultConstructor.createShapeNode(graphNode);
		}
		if(nodeConstructor == null || (subModel = nodeConstructor.createSubModel(shapeNode)) == null){
			subModel = defaultConstructor.createSubModel(shapeNode);
		}
		if(nodeConstructor == null || (nodeFactory = nodeConstructor.createShapeNodeFactory(subModel)) == null){
			nodeFactory = defaultConstructor.createShapeNodeFactory(subModel);
		}
		if(nodeConstructor == null || (edgeFactory = nodeConstructor.createLinkEdgeFactory(subModel)) == null){
			edgeFactory = defaultConstructor.createLinkEdgeFactory(subModel);
		}
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.testfixture.IObjectBuilder#buildDependencies()
	 */
	@Override
	public void buildDependencies() {
		if(nodeConstructor == null || !nodeConstructor.buildNode(shapeNode)){
			defaultConstructor.buildNode(shapeNode);
		}
		if(nodeConstructor == null || !nodeConstructor.buildSubModel(subModel)){
			defaultConstructor.buildSubModel(subModel);
		}
		if(nodeConstructor == null || !nodeConstructor.buildShapeNodeFactory(nodeFactory)){
			defaultConstructor.buildShapeNodeFactory(nodeFactory);
		}
		if(nodeConstructor == null || !nodeConstructor.buildLinkFactory(edgeFactory)){
			defaultConstructor.buildLinkFactory(edgeFactory);
		}
	}

	@Override
	public ITypedNodeConstructor getNodeConstructor() {
		return this.nodeConstructor;
	}

	@Override
	public void setNodeConstructor(ITypedNodeConstructor nodeConstructor) {
		this.nodeConstructor = nodeConstructor;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.testfixture.IShapeNodeBuilder#getShapeNode()
	 */
	@Override
	public ITypedDrawingNode getNode() {
		return this.shapeNode;
	}

}
