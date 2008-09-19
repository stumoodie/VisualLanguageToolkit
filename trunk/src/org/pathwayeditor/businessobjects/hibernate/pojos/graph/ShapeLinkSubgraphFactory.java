/**
 * 
 */
package org.pathwayeditor.businessobjects.hibernate.pojos.graph;

import org.pathwayeditor.businessobjects.drawingprimitives.ICanvasObjectSelection;
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkEdge;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeNode;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibLinkEdge;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibModel;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibShapeNode;

import uk.ed.inf.graph.compound.base.BaseSubCompoundGraphFactory;

/**
 * @author smoodie
 *
 */
public class ShapeLinkSubgraphFactory extends BaseSubCompoundGraphFactory implements ICanvasObjectSelection {

	/**
	 * @param builder
	 */
	public ShapeLinkSubgraphFactory(HibModel model) {
		super(new ShapeLinkSubgraphBuilder(model));
	}
	
	public ShapeLinkSubgraph createSubgraph(){
		return (ShapeLinkSubgraph)super.createSubgraph();
	}

	public ShapeLinkSubgraph createInducedSubgraph(){
		return (ShapeLinkSubgraph)super.createInducedSubgraph();
	}

	/**
	 * Adds a link to the factory for future selection in a subgraph.
	 * @param selectedLink the link to add to the factory
	 * @throws ClassCastException if <code>selectedLink</code> is not of type HibLinkEdge. 
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICanvasObjectSelection#addLink(ILinkEdge)
	 */
	public void addLink(ILinkEdge selectedLink) {
		this.addEdge((HibLinkEdge)selectedLink);
	}

	/**
	 * Adds a link to the factory for future selection in a subgraph.
	 * @param selectedShape the shape to add to the factory
	 * @throws ClassCastException if <code>selectedShape</code> is not of type HibShapeNode. 
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICanvasObjectSelection#addShape(IShapeNode)
	 */
	public void addShape(IShapeNode selectedShape) {
		this.addNode((HibShapeNode)selectedShape);
	}

}
