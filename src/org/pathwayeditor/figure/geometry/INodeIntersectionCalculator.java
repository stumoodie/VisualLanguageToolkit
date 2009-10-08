/**
 * 
 */
package org.pathwayeditor.figure.geometry;

import java.util.Comparator;
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

	void setComparator(Comparator<IDrawingNode> comparator);
	
	SortedSet<IDrawingNode> findIntersectingNodes(IConvexHull queryHull);

	SortedSet<IDrawingNode> findNodesAt(Point p);
	
}