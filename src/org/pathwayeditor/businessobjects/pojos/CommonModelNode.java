/**
 * 
 */
package org.pathwayeditor.businessobjects.pojos;

import org.pathwayeditor.businessobjects.typedefn.INodeObjectType;

import uk.ed.inf.graph.compound.archetypal.ArchetypalCompoundNode;

/**
 * @author smoodie
 *
 */
public abstract class CommonModelNode extends ArchetypalCompoundNode {

	/**
	 * @param superGraph
	 * @param index
	 */
	public CommonModelNode(Model superGraph, int index) {
		super(superGraph, index);
	}

	/**
	 * @param parent
	 * @param index
	 */
	public CommonModelNode(CommonModelNode parent, int index) {
		super(parent, index);
	}
	
	public abstract INodeObjectType getObjectType();
	
	public Model getModel(){
		return (Model)super.getGraph();
	}
}
