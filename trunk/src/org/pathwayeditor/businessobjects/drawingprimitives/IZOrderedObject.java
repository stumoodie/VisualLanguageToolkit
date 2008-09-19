/**
 * 
 */
package org.pathwayeditor.businessobjects.drawingprimitives;

/**
 * @author smoodie
 *
 */
public interface IZOrderedObject {

	IZOrderedObject getNextObject();
	
	IZOrderedObject getPreviousObject();
	
	IZOrderedObject getFirstObject();
	
	IZOrderedObject getLastObject();
	
}
