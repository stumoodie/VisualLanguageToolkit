/**
 * 
 */
package org.pathwayeditor.businessobjects.drawingprimitives;

/**
 * @author smoodie
 *
 */
public interface ICanvasAttribute {

	ICanvas getCanvas();
	
	int getCreationSerial();
	
	boolean equals(Object other); 
}
