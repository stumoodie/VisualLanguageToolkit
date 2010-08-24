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

import java.util.HashMap;
import java.util.Map;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.pathwayeditor.businessobjects.drawingprimitives.IDrawingNode;
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkEdgeFactory;
import org.pathwayeditor.businessobjects.drawingprimitives.IModel;
import org.pathwayeditor.businessobjects.drawingprimitives.IRootNode;
import org.pathwayeditor.businessobjects.drawingprimitives.ISelectionFactory;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeNode;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeNodeFactory;
import org.pathwayeditor.businessobjects.drawingprimitives.ISubModel;
import org.pathwayeditor.businessobjects.drawingprimitives.ITypedDrawingNode;
import org.pathwayeditor.businessobjects.impl.IBusinessObjectGraphElementMapper;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationSubsystem;
import org.pathwayeditor.businessobjects.typedefn.ILinkObjectType;
import org.pathwayeditor.businessobjects.typedefn.IShapeObjectType;

import uk.ac.ed.inf.graph.compound.IChildCompoundGraph;
import uk.ac.ed.inf.graph.compound.ICompoundGraph;
import uk.ac.ed.inf.graph.compound.ICompoundNode;
import uk.ac.ed.inf.graph.compound.IRootChildCompoundGraph;
import uk.ac.ed.inf.graph.compound.IRootCompoundNode;

/**
 * @author smoodie
 *
 */
public class ComplexTestFixture {
	public static final int ROOT_TYPE_ID = 0;
	public static final int SHAPE_TYPE_A_ID = 1;
	public static final int SHAPE_TYPE_B_ID = 2;
	public static final int SHAPE_TYPE_C_ID = 3;
	public static final int LINK_TYPE_D_ID = 4;
	public static final int LINK_TYPE_E_ID = 5;

	public static final String MODEL_ID = "model";
	public static final String ROOT_NODE_ID = "rootNode";
	public static final String SHAPE_NODE1_ID = "shapeNode1";
	public static final String SHAPE_NODE2_ID = "shapeNode2";
	private final Mockery mockery;
	private final String prefix;
	private final Map<String, IObjectBuilder> builderMap;
	private final INotationSubsystem notationSubsystem;
	private String elementCreationOrder[] = { MODEL_ID, ROOT_NODE_ID, SHAPE_NODE1_ID, SHAPE_NODE2_ID };
	private IObjectBuilder elementBuilders[] = {
		new GraphBuilder(MODEL_ID, new IModelConstructor() {
			
			@Override
			public ISelectionFactory createSelectionFactory(IModel graph) {
				final ISelectionFactory retVal = mockery.mock(ISelectionFactory.class, createMockName("selectionFactory"));
				mockery.checking(new Expectations(){{
					allowing(retVal).getModel(); will(returnValue(retVal));
				}});
				return retVal;
			}
			
			@Override
			public IModel createModel(final ICompoundGraph graph) {
				final IModel retVal = mockery.mock(IModel.class, createMockName(MODEL_ID));
				mockery.checking(new Expectations(){{
					allowing(retVal).getCompoundGraph(); will(returnValue(graph));
				}});
				return retVal;
			}
			
			@Override
			public ILinkEdgeFactory createLinkEdgeFactory(IModel graph) {
				final ILinkEdgeFactory retVal = mockery.mock(ILinkEdgeFactory.class, createMockName("modelLinkEdgeFactory"));
				mockery.checking(new Expectations(){{
				}});
				return retVal;
			}
			
			@Override
			public ICompoundGraph createCompoundGraph() {
				final ICompoundGraph retVal = mockery.mock(ICompoundGraph.class, createMockName("graph"));
				mockery.checking(new Expectations(){{
				}});
				return retVal;
			}
			
			@Override
			public boolean buildSelectionFactory(ISelectionFactory subgraphFactory) {
				return true;
			}
			
			@Override
			public boolean buildModel(final IModel model) {
				mockery.checking(new Expectations(){{
					allowing(model).getRootNode(); will(returnValue(getRootNode()));
					allowing(model).isValid(); will(returnValue(true));
				}});
				return true;
			}
			
			@Override
			public boolean buildLinkEdgeFactory(ILinkEdgeFactory edgeFactory) {
				return true;
			}
			
			@Override
			public boolean buildCompoundGraph(final ICompoundGraph graph) {
				mockery.checking(new Expectations(){{
					allowing(graph).getRoot(); will(returnValue(getRootNode().getCompoundGraphElement()));
				}});
				return true;
			}
		}),
		new TypedNodeBuilder(ROOT_NODE_ID, new ITypedNodeConstructor(){

			@Override
			public ISubModel createSubModel(final ITypedDrawingNode node) {
				final ISubModel retVal = mockery.mock(ISubModel.class, createMockSubModelName(ROOT_NODE_ID));
				mockery.checking(new Expectations(){{
					allowing(retVal).getChildCompoundGraph(); will(returnValue(node.getCompoundGraphElement().getChildCompoundGraph()));
					allowing(retVal).getRootNode(); will(returnValue(node));
					
					allowing(node).getSubModel(); will(returnValue(retVal));
				}});
				return retVal;
			}

			@Override
			public IRootNode createShapeNode(final ICompoundNode graphNode) {
				final IRootNode retVal = mockery.mock(IRootNode.class, createMockName(ROOT_NODE_ID));
				mockery.checking(new Expectations(){{
					allowing(retVal).getCompoundGraphElement(); will(returnValue(graphNode));
				}});
				return retVal;
			}

			@Override
			public IShapeNodeFactory createShapeNodeFactory(final ISubModel subModel) {
				final IShapeNodeFactory retVal = mockery.mock(IShapeNodeFactory.class, createMockShapeNodeFactoryName(ROOT_NODE_ID));
				mockery.checking(new Expectations(){{
					allowing(retVal).getOwningSubCanvas(); will(returnValue(subModel));
				}});
				return retVal;
			}

			@Override
			public ILinkEdgeFactory createLinkEdgeFactory(ISubModel subModel) {
				final ILinkEdgeFactory retVal = mockery.mock(ILinkEdgeFactory.class, createMockLinkEdgeFactoryName(ROOT_NODE_ID));
				mockery.checking(new Expectations(){{
					allowing(retVal).getOwningSubCanvas(); will(returnValue(retVal));
				}});
				return retVal;
			}

			@Override
			public ICompoundNode createCompoundNode() {
				final IRootCompoundNode retVal = mockery.mock(IRootCompoundNode.class, createMockGraphNodeName(ROOT_NODE_ID));
				final IRootChildCompoundGraph childCompoundGraph = mockery.mock(IRootChildCompoundGraph.class, "rootNodeChildCompoundGraph"); 
				mockery.checking(new Expectations(){{
					allowing(retVal).getGraph(); will(returnValue(getModel().getCompoundGraph()));
					allowing(retVal).getParent(); will(returnValue(retVal));
					allowing(retVal).getChildCompoundGraph(); will(returnValue(childCompoundGraph));
					
					allowing(childCompoundGraph).getRoot(); will(returnValue(retVal));
				}});
				return retVal;
			}

			@Override
			public boolean buildNode(ITypedDrawingNode node) {
				return true;
			}

			@Override
			public boolean buildSubModel(ISubModel submodel) {
				return true;
			}

			@Override
			public boolean buildShapeNodeFactory(IShapeNodeFactory nodeFactory) {
				return true;
			}

			@Override
			public boolean buildLinkFactory(ILinkEdgeFactory edgeFactory) {
				return true;
			}
			
		}),
		new TypedNodeBuilder(SHAPE_NODE1_ID, new ITypedNodeConstructor(){

			@Override
			public ISubModel createSubModel(final ITypedDrawingNode node) {
				final ISubModel retVal = mockery.mock(ISubModel.class, createMockSubModelName(SHAPE_NODE1_ID));
				mockery.checking(new Expectations(){{
					allowing(retVal).getChildCompoundGraph(); will(returnValue(node.getCompoundGraphElement().getChildCompoundGraph()));
					allowing(retVal).getRootNode(); will(returnValue(node));
				}});
				return retVal;
			}

			@Override
			public IShapeNode createShapeNode(final ICompoundNode graphNode) {
				final IShapeNode retVal = mockery.mock(IShapeNode.class, createMockName(SHAPE_NODE1_ID));
				mockery.checking(new Expectations(){{
					allowing(retVal).getCompoundGraphElement(); will(returnValue(graphNode));
				}});
				return retVal;
			}

			@Override
			public IShapeNodeFactory createShapeNodeFactory(final ISubModel subModel) {
				final IShapeNodeFactory retVal = mockery.mock(IShapeNodeFactory.class, createMockShapeNodeFactoryName(SHAPE_NODE1_ID));
				mockery.checking(new Expectations(){{
					allowing(retVal).getOwningSubCanvas(); will(returnValue(subModel));
					
					allowing(subModel).shapeNodeFactory(); will(returnValue(retVal));
				}});
				return retVal;
			}

			@Override
			public ILinkEdgeFactory createLinkEdgeFactory(final ISubModel subModel) {
				final ILinkEdgeFactory retVal = mockery.mock(ILinkEdgeFactory.class, createMockLinkEdgeFactoryName(SHAPE_NODE1_ID));
				mockery.checking(new Expectations(){{
					allowing(retVal).getOwningSubCanvas(); will(returnValue(retVal));

					allowing(subModel).linkEdgeFactory(); will(returnValue(retVal));
				}});
				return retVal;
			}

			@Override
			public ICompoundNode createCompoundNode() {
				final ICompoundNode retVal = mockery.mock(ICompoundNode.class, createMockGraphNodeName(SHAPE_NODE1_ID));
				final IChildCompoundGraph childGraph = mockery.mock(IChildCompoundGraph.class, "shapeNode1ChildGraph");
				final IShapeAttribute shapeNode1Attribute = mockery.mock(IShapeAttribute.class, "shapeNode1Attribute");
				final IBusinessObjectGraphElementMapper mapper = mockery.mock(IBusinessObjectGraphElementMapper.class, "mapper");
				mockery.checking(new Expectations(){{
					allowing(retVal).getGraph(); will(returnValue(getModel().getCompoundGraph()));
					allowing(retVal).getParent(); will(returnValue(getRootNode().getCompoundGraphElement()));
					
					allowing(retVal).getChildCompoundGraph(); will(returnValue(childGraph));
					allowing(retVal).getAttribute(); will(returnValue(shapeNode1Attribute));
					
					allowing(shapeNode1Attribute).getMapper(); will(returnValue(mapper));
				}});
				return retVal;
			}

			@Override
			public boolean buildNode(ITypedDrawingNode node) {
				return true;
			}

			@Override
			public boolean buildSubModel(ISubModel submodel) {
				return true;
			}

			@Override
			public boolean buildShapeNodeFactory(IShapeNodeFactory nodeFactory) {
				return true;
			}

			@Override
			public boolean buildLinkFactory(ILinkEdgeFactory edgeFactory) {
				return true;
			}
			
		}),
		new TypedNodeBuilder(SHAPE_NODE2_ID, new ITypedNodeConstructor(){

			@Override
			public ISubModel createSubModel(final ITypedDrawingNode node) {
				final ISubModel retVal = mockery.mock(ISubModel.class, createMockSubModelName(SHAPE_NODE2_ID));
				mockery.checking(new Expectations(){{
					allowing(retVal).getChildCompoundGraph(); will(returnValue(node.getCompoundGraphElement().getChildCompoundGraph()));
					allowing(retVal).getRootNode(); will(returnValue(node));
				}});
				return retVal;
			}

			@Override
			public IShapeNode createShapeNode(final ICompoundNode graphNode) {
				final IShapeNode retVal = mockery.mock(IShapeNode.class, createMockName(SHAPE_NODE2_ID));
				mockery.checking(new Expectations(){{
					allowing(retVal).getCompoundGraphElement(); will(returnValue(graphNode));
				}});
				return retVal;
			}

			@Override
			public IShapeNodeFactory createShapeNodeFactory(final ISubModel subModel) {
				final IShapeNodeFactory retVal = mockery.mock(IShapeNodeFactory.class, createMockShapeNodeFactoryName(SHAPE_NODE2_ID));
				mockery.checking(new Expectations(){{
					allowing(retVal).getOwningSubCanvas(); will(returnValue(subModel));

					allowing(subModel).shapeNodeFactory(); will(returnValue(retVal));
				}});
				return retVal;
			}

			@Override
			public ILinkEdgeFactory createLinkEdgeFactory(final ISubModel subModel) {
				final ILinkEdgeFactory retVal = mockery.mock(ILinkEdgeFactory.class, createMockLinkEdgeFactoryName(SHAPE_NODE2_ID));
				mockery.checking(new Expectations(){{
					allowing(retVal).getOwningSubCanvas(); will(returnValue(retVal));

					allowing(subModel).linkEdgeFactory(); will(returnValue(retVal));
				}});
				return retVal;
			}

			@Override
			public ICompoundNode createCompoundNode() {
				final ICompoundNode retVal = mockery.mock(ICompoundNode.class, createMockGraphNodeName(SHAPE_NODE2_ID));
				final IChildCompoundGraph childGraph = mockery.mock(IChildCompoundGraph.class, "shapeNode2ChildGraph");
				final IShapeAttribute shapeNode2Attribute = mockery.mock(IShapeAttribute.class, "shapeNode2Attribute");
				mockery.checking(new Expectations(){{
					allowing(retVal).getGraph(); will(returnValue(getModel().getCompoundGraph()));
					allowing(retVal).getParent(); will(returnValue(getRootNode().getCompoundGraphElement()));

					allowing(retVal).getChildCompoundGraph(); will(returnValue(childGraph));
					allowing(retVal).getAttribute(); will(returnValue(shapeNode2Attribute));
				}});
				return retVal;
			}

			@Override
			public boolean buildNode(ITypedDrawingNode node) {
				return true;
			}

			@Override
			public boolean buildSubModel(ISubModel submodel) {
				return true;
			}

			@Override
			public boolean buildShapeNodeFactory(IShapeNodeFactory nodeFactory) {
				return true;
			}

			@Override
			public boolean buildLinkFactory(ILinkEdgeFactory edgeFactory) {
				return true;
			}
			
		})
	};
	
	public ComplexTestFixture(Mockery mockery, String prefix, INotationSubsystem notationSubsystem){
		this.mockery = mockery;
		this.prefix = prefix;
		this.notationSubsystem = notationSubsystem;
		this.builderMap = new HashMap<String, IObjectBuilder>();
		for(IObjectBuilder builder : this.elementBuilders){
			builderMap.put(builder.getElementId(), builder);
		}
	}
	
	
	/**
	 * @param shapeNode1Id
	 * @return
	 */
	protected String createMockGraphNodeName(String shapeNode1Id) {
		StringBuilder buf = new StringBuilder(createMockName(shapeNode1Id));
		buf.append("GraphNode");
		return buf.toString();
	}


	/**
	 * @param shapeNode1Id
	 * @return
	 */
	protected String createMockLinkEdgeFactoryName(String shapeNode1Id) {
		StringBuilder buf = new StringBuilder(createMockName(shapeNode1Id));
		buf.append("LinkEdgeFactory");
		return buf.toString();
	}


	/**
	 * @param shapeNode1Id
	 * @return
	 */
	protected String createMockShapeNodeFactoryName(String shapeNode1Id) {
		StringBuilder buf = new StringBuilder(createMockName(shapeNode1Id));
		buf.append("ShapeNodeFactory");
		return buf.toString();
	}


	protected String createMockSubModelName(String shapeNode1Id) {
		StringBuilder buf = new StringBuilder(createMockName(shapeNode1Id));
		buf.append("SubModel");
		return buf.toString();
	}

	public IModel getModel(){
		return ((IModelBuilder)this.builderMap.get(MODEL_ID)).getModel();
	}
	
	public IDrawingNode getNode(String nodeId){
		return ((IDrawingNodeBuilder)this.builderMap.get(nodeId)).getNode();
	}
	
	public IRootNode getRootNode(){
		return (IRootNode)getNode(ROOT_NODE_ID);
	}
	
	public IShapeNode getShapeNode(String nodeId){
		return (IShapeNode)getNode(nodeId);
	}
	
	private IShapeObjectType getShapeType(int uniqueId){
		return this.notationSubsystem.getSyntaxService().getShapeObjectType(uniqueId);
	}
	
	private ILinkObjectType getLinkType(int uniqueId){
		return this.notationSubsystem.getSyntaxService().getLinkObjectType(uniqueId);
	}
	
	private String createMockName(String name){
		StringBuilder buf = new StringBuilder();
		if(!prefix.isEmpty()){
			buf.append(prefix);
			buf.append(Character.toUpperCase(name.charAt(0)));
			buf.append(name, 1, name.length()-1);
		}
		else{
			buf.append(name);
		}
		return buf.toString();
	}

	public void buildFixture(){
		for(String elementId : this.elementCreationOrder){
			this.builderMap.get(elementId).create();
		}
		for(String elementId : this.elementCreationOrder){
			this.builderMap.get(elementId).buildDependencies();
		}
	}


	/**
	 * @param shapeNode1Id
	 * @param iShapeNodeConstructor
	 */
	public void redefineElement(String shapeNode1Id, ITypedNodeConstructor iShapeNodeConstructor) {
		ITypedNodeBuilder builder = (ITypedNodeBuilder)this.builderMap.get(shapeNode1Id);
		builder.setNodeConstructor(iShapeNodeConstructor);
	}
	
}
