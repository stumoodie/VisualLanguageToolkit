package org.pathwayeditor.businessobjects.impl;

import org.pathwayeditor.businessobjects.drawingprimitives.IDrawingNode;
import org.pathwayeditor.businessobjects.drawingprimitives.IModel;
import org.pathwayeditor.businessobjects.drawingprimitives.ISubModel;

import uk.ac.ed.inf.graph.compound.ICompoundNode;

public abstract class CommonNode implements IDrawingNode {
	private ICompoundNode compoundNode;

	protected CommonNode(ICompoundNode compoundNode){
		this.compoundNode = compoundNode;
	}
	
	@Override
	public final IModel getModel() {
		return getAttribute().getCanvas().getModel();
	}

	@Override
	public final boolean isRemoved() {
		return this.compoundNode.isRemoved();
	}

	@Override
	public final void markRemoved(boolean setRemoved){
		this.compoundNode.markRemoved(setRemoved);
	}
	
	@Override
	public final long getUniqueIndex() {
		return this.compoundNode.getIndex();
	}

	@Override
	public final int getLevel() {
		return this.compoundNode.getLevel();
	}

	@Override
	public final int getIndex() {
		return this.compoundNode.getIndex();
	}

	@Override
	public final ISubModel getSubModel() {
		return this.getAttribute().getMapper().getSubModel(this.getCompoundGraphElement().getChildCompoundGraph());
	}

	@Override
	public final boolean isDescendent(IDrawingNode testNode) {
		return this.compoundNode.isDescendent(testNode.getCompoundGraphElement());
	}

	@Override
	public final ICompoundNode getCompoundGraphElement() {
		return this.compoundNode;
	}

}
