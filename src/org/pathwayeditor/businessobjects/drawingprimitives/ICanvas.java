package org.pathwayeditor.businessobjects.drawingprimitives;

import java.util.Date;

import org.pathwayeditor.businessobjects.contextadapter.IContext;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.Size;
import org.pathwayeditor.businessobjects.repository.IMap;


public interface ICanvas {
	
	/**
	 * Get the map assosiated with this Canvas.
	 * @return The map . Cannot be null.
	 */
	IMap getMap();
	
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
	void setGridEnabled(boolean snapToGridStatus);
	
	/**
	 * Get Grid status.
	 * @return true if Grid is enabled, false otherwise.
	 */
	boolean isGridEnabled();

	/**
	 * Set Grid size.
	 * @throws IllegalArgumentException if X or Y are less than zero.
	 */	
	void setGrid(int width, int height);
	
	/**
	 * Get the width of the grid.
	 * @return a numeric value representing the width of the grid.
	 */
	int gridWidth();

	/**
	 * Get the height of the grid.
	 * @return a numeric value representing the heigth of the grid.
	 */
	int gridHeight();

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
	IContext getContext();
	
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
	Size getMapSize () ;
	
	/**
	 * Set the size of this map
	 * @throws IllegalArgumentException if value is null.
	 */	
	void setMapSize ( Size size ) ;
}
