/**
 * 
 */
package org.pathwayeditor.businessobjects.drawingprimitives.listeners;

/**
 * @author smoodie
 *
 */
public interface IModelChangeListener {

	void nodeStructureChange(IModelNodeChangeEvent event);
	
	void edgeStructureChange(IModelEdgeChangeEvent event);
	
}
