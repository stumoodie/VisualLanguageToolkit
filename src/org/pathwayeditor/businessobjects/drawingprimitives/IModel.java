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
				IShape srcShape, IShape tgtShape);
	
	ILink createLink(ILinkObjectType linkObjectType,
			IShape srcShape, IShape tgtShape);
	
	/**
	 * Creates a new submodel factory that can be used to create a submodel of
	 * this one.
	 * @return
	 */
	ISelectionSubgraphFactory selectionSubgraphFactory();
	
	void removedSubgraph(ISelectionSubgraphFactory subgraph);
	
	IRootObject getRootObject();
	
	/**
	 * Iterates over the link edges that are in the model.
	 * @return
	 */
	Iterator<ILink> linkIterator();

	/**
	 * Iterators over all the shapes in the model.
	 * @return
	 */
	Iterator<IShape> shapeIterator();
	
	/**
	 * Iterators over all the shapes in the model.
	 * @return
	 */
	Iterator<ILabel> labelIterator();
	
	int getNumLinks();
	
	int getNumLabels();
	
	int getNumShapes();
}
