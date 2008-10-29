/**
 * 
 */
package org.pathwayeditor.businessobjects.drawingprimitives;

import org.pathwayeditor.businessobjects.typedefn.IObjectType;



/**
 * @author smoodie
 *
 */
public interface ICanvasAttribute {
	
	/**
	 * Get the Canvas related with this attribute. Cannot be null.
	 * @return the Canvas. Cannot be null.
	 */	
	ICanvas getCanvas();

	/**
	 * Get the Creation serial of this attribute. Cannot be null.
	 * @return the serial number, greater that 0.
	 */	
	int getCreationSerial();	
	
	IObjectType getObjectType();
	
	/**
	 * The business key is the combination of canvas and creation serial.
	 * @param other
	 * @return true if equal, false otherwise.
	 */
	boolean equals(Object other); 

	/**
	 * See <code>equals</code> for definition of identity.
	 * @return the hash code.
	 */
	int hashCode();
}
