/**
 * 
 */
package org.pathwayeditor.businessobjects.pojos;

import org.pathwayeditor.businessobjects.contectadapter.IContext;
import org.pathwayeditor.businessobjects.drawingprimitives.ICanvas;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibCanvas;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibMapDiagram;
import org.pathwayeditor.businessobjects.repository.IMap;

/**
 * @author smoodie
 *
 */
public class Canvas implements ICanvas {
	private final HibCanvas hibCanvas;
	private final IContext context;
	private Model model;
	private static int creationCntr = 0;
	private ISyntaxMappingFactory mappingFactory;
	
	/**
	 * Constructs a canvas that blongs to a map.
	 * @param mappingFactory
	 * @param map
	 * @param context
	 */
	public Canvas(ISyntaxMappingFactory mappingFactory, HibMapDiagram map, IContext context){
		this.context = context;
		this.hibCanvas = new HibCanvas(this, map, mappingFactory.createHibContext(context));
		this.mappingFactory = mappingFactory;
	}
	
//	/**
//	 * Contructs a canvas that is not associated with a map and is therefore transient.
//	 * @param mappingFactory
//	 * @param context
//	 */
//	public Canvas(ISyntaxMappingFactory mappingFactory, IContext context){
//		this.hibCanvas = new HibCanvas(this, map.getHibObject(), mappingFactory.createHibContext(context));
//		this.context = context;
//		this.mappingFactory = mappingFactory;
//	}
	
	/**
	 * Constructs a canvas from the equivalent hibernate object. 
	 * @param mappingFactory A mapping factory used to map between hibernate and CA classes. 
	 * @param canvas the equivalent hibernate canvas class that this can will be a facade for. 
	 */
	public Canvas(IContext context, Model model, HibCanvas canvas){
		this.hibCanvas = canvas;
		this.context = context;
		this.model = model;
	}
	
	public Canvas(HibMapDiagram newMap, Canvas other){
		this.hibCanvas = new HibCanvas(newMap, other.hibCanvas);
		this.context = other.context;
		this.model = new Model(this);
	}
	
	public Canvas(Canvas other){
		this.hibCanvas = new HibCanvas(other.hibCanvas);
		this.context = other.context;
	}
	
	/**
	 * Get the context used by this canvas. 
	 */
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
		return this.hibCanvas.getMapDiagram();
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

	ISyntaxMappingFactory getSyntaxMappingFactory(){
		return this.mappingFactory;
	}
	
	/**
	 * @return
	 */
	int nextCreationSerial() {
		return ++creationCntr;
	}

	/**
	 * @return
	 */
	HibCanvas getHibObject() {
		return this.hibCanvas;
	}
}
