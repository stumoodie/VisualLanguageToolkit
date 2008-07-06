/**
 * 
 */
package org.pathwayeditor.businessobjects.pojos;

import org.pathwayeditor.businessobjects.contectadapter.IContext;
import org.pathwayeditor.businessobjects.drawingprimitives.ICanvas;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB;
import org.pathwayeditor.businessobjects.repository.IMap;

/**
 * @author smoodie
 *
 */
public class TransientCanvas implements ICanvas {
	private static final int BACKGROUND_RED = 0;
	private static final int BACKGROUND_BLUE = 0;
	private static final int BACKGROUND_GREEN = 0;
	private final IContext context;
	private final RGB backgroundColour;
	private final Model model; 
	
	public TransientCanvas(IContext context){
		this.context = context;
		this.backgroundColour = new RGB(BACKGROUND_RED, BACKGROUND_GREEN, BACKGROUND_BLUE);
		//TODO: do this!
		this.model = null;
	}
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICanvas#getBackgroundColour()
	 */
	public RGB getBackgroundColour() {
		// TODO Auto-generated method stub
		return this.backgroundColour;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICanvas#getContext()
	 */
	public IContext getContext() {
		return this.context;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICanvas#getMap()
	 */
	public IMap getMap() {
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICanvas#getModel()
	 */
	public Model getModel() {
		return this.model;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICanvas#getUrl()
	 */
	public String getUrl() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICanvas#gridHeight()
	 */
	public int gridHeight() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICanvas#gridWidth()
	 */
	public int gridWidth() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICanvas#isSnapToGridOn()
	 */
	public boolean isSnapToGridOn() {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICanvas#setBackgroundColour(org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB)
	 */
	public void setBackgroundColour(RGB backgroundColour) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICanvas#setGrid(int, int)
	 */
	public void setGrid(int width, int height) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICanvas#setSnapToGrid(boolean)
	 */
	public void setSnapToGrid(boolean snapToGridStatus) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICanvas#setUrl(java.lang.String)
	 */
	public void setUrl(String url) {
		// TODO Auto-generated method stub

	}

}
