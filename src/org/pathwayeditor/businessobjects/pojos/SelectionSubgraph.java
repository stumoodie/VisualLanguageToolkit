/**
 * 
 */
package org.pathwayeditor.businessobjects.pojos;

import org.pathwayeditor.businessobjects.drawingprimitives.ILinkAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.ICanvasObjectSelection;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeAttribute;

import uk.ed.inf.graph.compound.archetypal.ArchetypalCompoundGraph;
import uk.ed.inf.graph.compound.archetypal.ArchetypalSubCompoundGraph;
import uk.ed.inf.graph.compound.archetypal.ArchetypalSubCompoundGraphBuilder;
import uk.ed.inf.graph.compound.archetypal.ArchetypalSubCompoundGraphFactory;
import uk.ed.inf.graph.compound.impl.SubCompoundGraph;

/**
 * @author smoodie
 *
 */
public class SelectionSubgraph extends ArchetypalSubCompoundGraphFactory implements ICanvasObjectSelection {
	
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
	
	private static class SelectionSubgraphBuilder extends ArchetypalSubCompoundGraphBuilder {
		private SubCompoundGraph subgraph;
		
		public SelectionSubgraphBuilder(Model model){
			super(model);
		}
		
		/* (non-Javadoc)
		 * @see uk.ed.inf.graph.compound.impl.ArchetypalSubCompoundGraphBuilder#getSubgraph()
		 */
		@Override
		public ArchetypalSubCompoundGraph getSubgraph() {
			return this.subgraph;
		}

		/* (non-Javadoc)
		 * @see uk.ed.inf.graph.compound.impl.ArchetypalSubCompoundGraphBuilder#newSubgraph(uk.ed.inf.graph.compound.impl.ArchetypalCompoundGraph)
		 */
		@Override
		protected void newSubgraph(ArchetypalCompoundGraph compoundGraph) {
			this.subgraph = new SubCompoundGraph(compoundGraph);
		}
		
	}
}
