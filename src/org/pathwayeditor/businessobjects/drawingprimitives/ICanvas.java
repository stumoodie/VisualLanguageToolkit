package org.pathwayeditor.businessobjects.drawingprimitives;

import java.util.Date;

import org.pathwayeditor.businessobjects.contextadapter.IContext;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.Size;
import org.pathwayeditor.businessobjects.repository.IMap;


public interface ICanvas {

	IMap getMap();

	void setSnapToGrid(boolean snapToGridStatus);
	boolean isSnapToGridOn();
	
	void setGridEnabled(boolean snapToGridStatus);
	boolean isGridEnabled();

	void setGrid(int width, int height);
	int gridWidth();
	int gridHeight();

	void setBackgroundColour(RGB backgroundColour);
	
	RGB getBackgroundColour();
	
	IContext getContext();

	Date getModified () ;
	
	Date getCreated () ;
	
	Size getMapSize () ;
	void setMapSize ( Size size ) ;
}
