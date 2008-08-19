package org.pathwayeditor.businessobjects.drawingprimitives;


public interface ILabelNode extends IZOrderedObject {

	ICompoundGraph getGraph();
	
	int getNodeIndex();
	
	ILabelAttribute getAttribute();
	
	IShapeNode getOwningShape();
}
