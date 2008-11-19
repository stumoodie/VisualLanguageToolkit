/**
 * 
 */
package org.pathwayeditor.businessobjects.drawingprimitives.listeners;

/**
 * @author smoodie
 *
 */
public interface ISubModelChangeListener {

	void nodeStructureChange(ISubModelNodeChangeEvent event);
	
	void edgeStructureChange(ISubModelEdgeChangeEvent event);
	
}
