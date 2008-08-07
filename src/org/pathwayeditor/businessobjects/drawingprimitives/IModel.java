/**
 * 
 */
package org.pathwayeditor.businessobjects.drawingprimitives;

import java.util.Iterator;

import org.pathwayeditor.businessobjects.contectadapter.IContextAdapterServiceProvider;
import org.pathwayeditor.businessobjects.typedefn.ILinkObjectType;

/**
 * @author smoodie
 *
 */
public interface IModel {
	
	IContextAdapterServiceProvider getContextAdapterServiceProvider();
	
	IModel createCopy(ICanvas newCanvas);
	
	boolean canCreateLink(ILinkObjectType linkObjectType,
							IShapeAttribute srcShape, IShapeAttribute tgtShape);
	
	ILinkAttribute createLink(ILinkObjectType linkObjectType,
			IShapeAttribute srcShape, IShapeAttribute tgtShape);
	
	IModelState getCurrentState();
	
	void restoreToState(IModelState stateToRestore);
	
	void removeSubgraph(ICanvasObjectSelection selection);
	
	IRootObjectNode getRootObject();
	
	/**
	 * Iterates over the link edges that are in the model.
	 * @return
	 */
	Iterator<ILinkAttribute> linkIterator();

	/**
	 * Iterators over all the shapes in the model.
	 * @return
	 */
	Iterator<IShapeAttribute> shapeIterator();
	
	/**
	 * Iterators over all the shapes in the model.
	 * @return
	 */
	Iterator<ILabelAttribute> labelIterator();
	
	int getNumLinks();
	
	int getNumLabels();
	
	int getNumShapes();
}
