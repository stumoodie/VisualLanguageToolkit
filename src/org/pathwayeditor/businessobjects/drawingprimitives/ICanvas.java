package org.pathwayeditor.businessobjects.drawingprimitives;

import java.util.Date;

import org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.Size;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.IPropertyChangeListenee;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationSubsystem;
import org.pathwayeditor.businessobjects.repository.IMap;


public interface ICanvas extends IPropertyChangeListenee{
	
	/**
	 * Get the map associated with this Canvas.
	 * @return The map . Cannot be null.
	 */
	IMap getOwningMap();
	
	/**
	 * Enable or disable the Snap to Grid funcionality.
	 */	
	void setSnapToGrid(boolean snapToGridStatus);
	
	/**
	 * Get Snap to Grid functionality status.
	 * @return true if Snap To Grid is on, false otherwise.
	 */
	boolean isSnapToGridOn();
	
	/**
	 * Enable or disable the Grid.
	 */	
	void setGridEnabled(boolean gridStatus);
	
	/**
	 * Get Grid status.
	 * @return true if Grid is enabled, false otherwise.
	 */
	boolean isGridEnabled();

	/**
	 * Set Grid size.
	 * @throws IllegalArgumentException if X or Y are less than zero.
	 */	
	void setGridSize(Size newSize);
	
	/**
	 * Get the current grid size.
	 * @return the grid size, which cannot be null.
	 */
	Size getGridSize();

	/**
	 * Set the background color of this Canvas.
	 * @throws IllegalArgumentException if value is null.
	 */	
	void setBackgroundColour(RGB backgroundColour);
	
	/**
	 * Get the background color of this Canvas.
	 * @return the RGB representation of the color of the Canvas.
	 */	
	RGB getBackgroundColour();
	
	/**
	 * Get the Context assosiated with this Canvas.
	 * @return the context. Cannot be null.
	 */	
	INotationSubsystem getNotationSubsystem();
	
	/**
	 * Get the date this Canvas was last modified.
	 * @return the Date of last modification. Cannot be null.
	 */	
	Date getModified () ;

	/**
	 * Get the date this Canvas was last created.
	 * @return the Date of creation. Cannot be null.
	 */	
	Date getCreated () ;
	
	/**
	 * Get the size of this map
	 * @return the size. Cannot be null.
	 */	
	Size getCanvasSize () ;
	
	/**
	 * Set the size of this map
	 * @throws IllegalArgumentException if value is null.
	 */	
	void setCanvasSize ( Size size ) ;
	
	/**
	 * Get the model for this canvas.
	 * @return the model associated with the canvas, which cannot be null. 
	 */
	IModel getModel();

	
	/**
	 * Tests if the given canvas can be copied into this one. This requires that the 
	 * canvas is not null, it is different to this one, it uses the same notation subsystem
	 * and that this canvas is empty.
	 * @param canvas the canvas to test.
	 * @return true if <code>canvas</code> can be successfully copied here, false otherwise.
	 */
	boolean canCopyHere(ICanvas canvas);
	
	/**
	 * Copy another canvas into this one. For it to succeed it must be a different
	 * canvas, they must have the same notation subsystems and this canvas must be empty.
	 * @param otherCanvas the other canvas to be copied here. 
	 * @throws IllegalArgumentException if <code>canCopyHere(otherCanvas)==false</code>.
	 */
	void copyHere(ICanvas otherCanvas);
	
	/**
	 * Find an attribute that matches the serial number.
	 * @param attributeSerial the serial number. 
	 * @return The canvas attribute with the serial number matching <code>attributeSerial</code> or null if no matching attribute serial can be found. 
	 */
	ICanvasAttribute findAttribute(int attributeSerial);

	/**
	 * Tests if the canvas contains a link attribute matching the serial number.
	 * @param attributeSerial the serial number.
	 * @return true if it contains the attribute, false otherwise.
	 */
	boolean containsLinkAttribute(int attributeSerial);
	
	/**
	 * Gets the link attribute matching the serial number.
	 * @param attributeSerial the serial number that uniquely identifies the link attribute.
	 * @return the link attribute, which cannot be null.
	 * @throws IllegalArgumentException if <code>containsLinkAttribute(attributeSerial) == false</code>.
	 */
	ILinkAttribute getLinkAttribute(int attributeSerial);

	/**
	 * Tests if the canvas contains a shape attribute matching the serial number.
	 * @param attributeSerial the serial number.
	 * @return true if it contains the attribute, false otherwise.
	 */
	boolean containsShapeAttribute(int attributeSerial);
	
	/**
	 * Gets the shape attribute matching the serial number.
	 * @param attributeSerial the serial number that uniquely identifies the link attribute.
	 * @return the shape attribute, which cannot be null.
	 * @throws IllegalArgumentException if <code>containsShapeAttribute(attributeSerial) == false</code>.
	 */
	IShapeAttribute getShapeAttribute(int attributeSerial);

	/**
	 * Tests if the canvas contains a label attribute matching the serial number.
	 * @param attributeSerial the serial number.
	 * @return true if it contains the attribute, false otherwise.
	 */
	boolean containsLabelAttribute(int attributeSerial);
	
	/**
	 * Gets the label attribute matching the serial number.
	 * @param attributeSerial the serial number that uniquely identifies the link attribute.
	 * @return the label attribute, which cannot be null.
	 * @throws IllegalArgumentException if <code>containsLabelAttribute(attributeSerial) == false</code>.
	 */
	ILabelAttribute getLabelAttribute(int attributeSerial);
}
