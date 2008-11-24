/**
 * 
 */
package org.pathwayeditor.businessobjects.drawingprimitives.listeners;

/**
 * @author smoodie
 *
 */
public interface INodeChangeListener {

	void sourceNodeStructureChange(INodeChangeEvent event);
	
	void targetNodeStructureChange(INodeChangeEvent event);
	
}
