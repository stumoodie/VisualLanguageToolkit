/**
 * 
 */
package org.pathwayeditor.businessobjects.pojos;

import org.pathwayeditor.businessobjects.drawingprimitives.ICanvasObjectSelection;
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeAttribute;

import uk.ed.inf.graph.compound.base.BaseCompoundGraph;
import uk.ed.inf.graph.compound.base.BaseSubCompoundGraph;
import uk.ed.inf.graph.compound.base.BaseSubCompoundGraphBuilder;
import uk.ed.inf.graph.compound.base.BaseSubCompoundGraphFactory;
import uk.ed.inf.graph.compound.impl.SubCompoundGraph;

/**
 * @author smoodie
 *
 */
public class SelectionSubgraph extends BaseSubCompoundGraphFactory implements ICanvasObjectSelection {
	
	public SelectionSubgraph(Model model){
		super(new SelectionSubgraphBuilder(model));
	}
	
	public void addShape(IShapeAttribute selectedShape){
		Shape shape = (Shape)selectedShape;
		this.addNode(shape);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ISelectionSubgraph#addLink(org.pathwayeditor.businessobjects.drawingprimitives.ILink)
	 */
	public void addLink(ILinkAttribute selectedLink) {
		Link link = (Link)selectedLink;
		this.addEdge(link);
	}
	
	private static class SelectionSubgraphBuilder extends BaseSubCompoundGraphBuilder {
		private SubCompoundGraph subgraph;
		
		public SelectionSubgraphBuilder(Model model){
			super(model);
		}
		
		/* (non-Javadoc)
		 * @see uk.ed.inf.graph.compound.impl.BaseSubCompoundGraphBuilder#getSubgraph()
		 */
		@Override
		public BaseSubCompoundGraph getSubgraph() {
			return this.subgraph;
		}

		/* (non-Javadoc)
		 * @see uk.ed.inf.graph.compound.impl.BaseSubCompoundGraphBuilder#newSubgraph(uk.ed.inf.graph.compound.impl.BaseCompoundGraph)
		 */
		@Override
		protected void newSubgraph(BaseCompoundGraph compoundGraph) {
			this.subgraph = new SubCompoundGraph(compoundGraph);
		}
		
	}
}
