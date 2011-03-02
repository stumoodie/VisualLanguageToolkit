/**
 * 
 */
package org.pathwayeditor.businessobjects.drawingprimitives;

import org.pathwayeditor.businessobjects.typedefn.IRootObjectType;

import uk.ac.ed.inf.graph.compound.IRootCompoundNode;

/**
 * IRootAttribute is an interface that defines the attributes of the root
 * node in the model.
 * 
 * @author Stuart Moodie
 *
 */
public interface IRootAttribute extends ITypedDrawingNodeAttribute, ICanvas {

	@Override
	IRootObjectType getObjectType();
	
	@Override
	IRootCompoundNode getCurrentElement();
}
