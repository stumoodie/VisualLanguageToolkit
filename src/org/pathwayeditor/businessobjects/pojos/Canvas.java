/**
 * 
 */
package org.pathwayeditor.businessobjects.pojos;

import org.pathwayeditor.businessobjects.contectadapter.IContext;
import org.pathwayeditor.businessobjects.drawingprimitives.ICanvas;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibCanvas;
import org.pathwayeditor.businessobjects.repository.IMap;

/**
 * @author smoodie
 *
 */
public class Canvas implements ICanvas {
	private final HibCanvas hibCanvas;
	private final IContext context;
	
	public Canvas(ISyntaxMappingFactory mappingFactory, Map map, IContext context){
		this.hibCanvas = new HibCanvas();
		this.context = context;
	}
	
	public Canvas(ISyntaxMappingFactory mappingFactory, IContext context){
		this.hibCanvas = new HibCanvas();
		this.context = context;
	}
	
	public Canvas(ISyntaxMappingFactory mappingFactory, HibCanvas canvas){
		this.hibCanvas = canvas;
		this.context = mappingFactory.getContext(this.hibCanvas.getContext());
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
	public Model getModel() {
		return this.hibCanvas.getModel().getBusinessObject();
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

	/**
	 * @return
	 */
	int nextCreationSerial() {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * @return
	 */
	HibCanvas getHibObject() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @return
	 */
	ISyntaxMappingFactory getSyntaxMappingFactory() {
		// TODO Auto-generated method stub
		return null;
	}

}
