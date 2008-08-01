/**
 * 
 */
package org.pathwayeditor.businessobjects.drawingprimitives;

/**
 * @author smoodie
 *
 */
public interface ICanvasObject {

	ICanvas getCanvas();
	
	int getCreationSerial();
	
	boolean equals(Object other); 
}
