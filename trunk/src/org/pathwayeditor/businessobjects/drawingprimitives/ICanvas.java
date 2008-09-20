package org.pathwayeditor.businessobjects.drawingprimitives;

import java.util.Date;

import org.pathwayeditor.businessobjects.contextadapter.INotationSubsystem;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.Size;
import org.pathwayeditor.businessobjects.repository.IMap;


public interface ICanvas {
	
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
	 * @return
	 */
	IModel getModel();

	
	/**
	 * Create a copy of this canvas and add it to the given map.
	 * @param map the map that will own the copied map. This cannot be the same map that owns this model. 
	 * @return The newly created model.
	 * @throws IllegalArgumentException if <code>getOwningMap().equals(map)</code>.
	 */
	ICanvas createCopy(IMap map);
}
