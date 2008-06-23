package org.pathwayeditor.businessobjects;


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
