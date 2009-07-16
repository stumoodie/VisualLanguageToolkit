/**
 * 
 */
package org.pathwayeditor.figure.geometry;

import java.util.SortedSet;

import org.pathwayeditor.businessobjects.drawingprimitives.IDrawingNode;
import org.pathwayeditor.businessobjects.drawingprimitives.IModel;

/**
 * @author smoodie
 *
 */
public interface INodeIntersectionCalculator {

	IModel getModel();

	void setFilter(IIntersectionCalcnFilter filter);

	SortedSet<IDrawingNode> findIntersectingNodes(IConvexHull queryHull);

	SortedSet<IDrawingNode> findNodesAt(Point p);
	
}