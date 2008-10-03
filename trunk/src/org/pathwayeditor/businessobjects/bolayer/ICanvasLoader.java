/**
 * 
 */
package org.pathwayeditor.businessobjects.bolayer;

import org.pathwayeditor.businessobjects.drawingprimitives.ICanvas;
import org.pathwayeditor.businessobjects.hibernate.helpers.IHibNotationFactory;
import org.pathwayeditor.businessobjects.repository.IMap;

/**
 * @author smoodie
 *
 */
public interface ICanvasLoader {

	void setOwningMap(IMap owningMap);

	IMap getOwningMap();

	void loadCanvas();

	IHibNotationFactory getHibNotationFactory();

	void setHibNotationFactory(IHibNotationFactory hibNotationFactory);

	public ICanvas getLoadedCanvas();

	/**
	 * Sets the hibernate notation factory, the owning map and loaded canvas to null. This helps avoid memeory leaks
	 * since this object will not hold on to state object references and puts the object in a pristine state as if it
	 * were just initialised.
	 */
	void reset();

}