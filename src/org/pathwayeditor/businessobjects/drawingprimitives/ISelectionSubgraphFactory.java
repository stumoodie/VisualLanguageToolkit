/**
 * 
 */
package org.pathwayeditor.businessobjects.drawingprimitives;




/**
 * @author smoodie
 *
 */
public interface ISelectionSubgraphFactory {

	IModel getModel();
	
	void addShape(IShape selectedShape);
	
	void addLink(ILink selectedLink);
	
	ISelectionSubgraph createSelectionSubgraph();
}
