/**
 * 
 */
package org.pathwayeditor.businessobjects.pojos;

import org.pathwayeditor.businessobjects.drawingprimitives.ICanvas;
import org.pathwayeditor.businessobjects.drawingprimitives.IModel;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibCanvas;
import org.pathwayeditor.businessobjects.notationservice.IContext;
import org.pathwayeditor.businessobjects.repository.IMap;

/**
 * @author smoodie
 *
 */
public class Canvas implements ICanvas {
	private final HibCanvas hibCanvas;
	private final IContext context;
	private final IModel model;
	
	public Canvas(IMap map, IContext context, IModel model){
		this.hibCanvas = new HibCanvas();
		this.context = context;
		this.model = model;
	}
	
	public Canvas(HibCanvas canvas, IContext context, IModel model){
		this.hibCanvas = canvas;
		this.context = context;
		this.model = model;
	}
	
	public IContext getContext(){
		return this.context;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICanvas#getBackgroundColour()
	 */
	public RGB getBackgroundColour() {
		RGB retVal = new RGB(this.hibCanvas.getBackgroundRed(), this.hibCanvas.getBackgroundGreen(),
					this.hibCanvas.getBackgroundBlue());
		return retVal;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICanvas#getMap()
	 */
	public IMap getMap() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICanvas#getModel()
	 */
	public IModel getModel() {
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
