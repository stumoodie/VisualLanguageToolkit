package org.pathwayeditor.businessobjects.drawingprimitives;

import org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB;
import org.pathwayeditor.businessobjects.repository.IMap;
import org.pathwayeditor.businessobjects.repository.IRepositoryItem;


public interface ICanvas extends IRepositoryItem {

	IMap getMap();

	void setSnapToGrid(boolean snapToGridStatus);
	boolean isSnapToGridOn();

	void setGrid(int width, int height);
	int gridWidth();
	int gridHeight();

	void setUrl(String url);
	String getUrl();
	
	void setBackgroundColour(RGB backgroundColour);
	
	RGB getBackgroundColour();

	IModel getModel();
}
