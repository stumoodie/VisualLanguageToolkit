package org.pathwayeditor.testfixture;

import org.pathwayeditor.businessobjects.drawingprimitives.ILinkEdgeFactory;
import org.pathwayeditor.businessobjects.drawingprimitives.IModel;
import org.pathwayeditor.businessobjects.drawingprimitives.ISelectionFactory;

import uk.ac.ed.inf.graph.compound.ICompoundGraph;

public class GraphBuilder implements IModelBuilder {
	private ICompoundGraph graph;
	private IModel model;
	private ILinkEdgeFactory edgeFactory;
	private ISelectionFactory subgraphFactory;
	private final IModelConstructor defaultConstructor;
	private IModelConstructor overridingConstructor;
	private String elementId;
	
	public GraphBuilder(String elementId, IModelConstructor defaultConstructor){
		this.elementId = elementId;
		this.defaultConstructor = defaultConstructor;
		this.overridingConstructor = null;
	}
	
	public void setGraphContructor(IModelConstructor newConstructor){
		this.overridingConstructor = newConstructor;
	}
	
	public IModelConstructor getGraphContructor(){
		return this.overridingConstructor;
	}
	
	@Override
	public final void create() {
		if(overridingConstructor == null || (graph = overridingConstructor.createCompoundGraph()) == null){
			graph = defaultConstructor.createCompoundGraph();
		}
		if(overridingConstructor == null || (model = overridingConstructor.createModel(graph)) == null){
			model = defaultConstructor.createModel(graph);
		}
		if(overridingConstructor == null || (edgeFactory = overridingConstructor.createLinkEdgeFactory(model)) == null){
			edgeFactory = defaultConstructor.createLinkEdgeFactory(model);
		}
		if(overridingConstructor == null || (subgraphFactory = overridingConstructor.createSelectionFactory(model)) == null){
			subgraphFactory = defaultConstructor.createSelectionFactory(model);
		}
	}


	@Override
	public void buildDependencies() {
		if(overridingConstructor == null || !overridingConstructor.buildCompoundGraph(graph)){
			defaultConstructor.buildCompoundGraph(graph);
		}
		if(overridingConstructor == null || !overridingConstructor.buildModel(model)){
			defaultConstructor.buildModel(model);
		}
		if(overridingConstructor == null || !overridingConstructor.buildLinkEdgeFactory(edgeFactory)){
			defaultConstructor.buildLinkEdgeFactory(edgeFactory);
		}
		if(overridingConstructor == null || !overridingConstructor.buildSelectionFactory(subgraphFactory)){
			defaultConstructor.buildSelectionFactory(subgraphFactory);
		}
	}



	@Override
	public String getElementId() {
		return this.elementId;
	}

	@Override
	public IModel getModel() {
		return this.model;
	}

}
