/**
 * 
 */
package org.pathwayeditor.businessobjects;

import java.util.Iterator;

import org.pathwayeditor.businessobjects.typedefn.ILinkObjectType;

/**
 * @author smoodie
 *
 */
public interface IModel {
	
//	IContextAdapterServiceProvider getContextAdapterServiceProvider();
	
	
	IModel createCopy();
	
	boolean canCreateLink(ILinkObjectType linkObjectType,
				IShape srcShape, IShape tgtShape);
	
	ILink createLink(ILinkObjectType linkObjectType,
			IShape srcShape, IShape tgtShape);
	
	IShape getOwningShape();
		
	IModelState getCurrentState();
	
	void restoreToState(IModelState stateToRestore);
	
	/**
	 * Creates a new submodel factory that can be used to create a submodel of
	 * this one.
	 * @return
	 */
	ISelectionSubgraphFactory selectionSubgraphFactory();
	
	void removedSubgraph(ISelectionSubgraphFactory subgraph);
	
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
	
	int getNumLinks();
	
	int getNumShapes();
}
