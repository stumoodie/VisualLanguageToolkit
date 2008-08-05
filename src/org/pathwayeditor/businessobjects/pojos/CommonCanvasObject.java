/**
 * 
 */
package org.pathwayeditor.businessobjects.pojos;

import org.pathwayeditor.businessobjects.drawingprimitives.ICanvasObject;
import org.pathwayeditor.businessobjects.typedefn.INodeObjectType;

import uk.ed.inf.graph.compound.archetypal.ArchetypalCompoundNode;

/**
 * @author smoodie
 *
 */
public abstract class CommonCanvasObject extends ArchetypalCompoundNode implements ICanvasObject {

	/**
	 * @param superGraph
	 * @param index
	 */
	public CommonCanvasObject(Model superGraph, int index) {
		super(superGraph, index);
	}

	/**
	 * @param parent
	 * @param index
	 */
	public CommonCanvasObject(CommonCanvasObject parent, int index) {
		super(parent, index);
	}
	
	public abstract INodeObjectType getObjectType();
}
