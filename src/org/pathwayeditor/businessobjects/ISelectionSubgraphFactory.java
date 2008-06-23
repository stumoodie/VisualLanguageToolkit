/**
 * 
 */
package org.pathwayeditor.businessobjects;



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
