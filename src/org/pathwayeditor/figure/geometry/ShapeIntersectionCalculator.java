/**
 * 
 */
package org.pathwayeditor.figure.geometry;

import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

import org.apache.log4j.Logger;
import org.pathwayeditor.businessobjects.drawingprimitives.IDrawingNode;
import org.pathwayeditor.businessobjects.drawingprimitives.IModel;

/**
 * @author smoodie
 *
 */
public class ShapeIntersectionCalculator implements INodeIntersectionCalculator {
	private final Logger logger = Logger.getLogger(this.getClass());
	
	private static final IIntersectionCalcnFilter DEFAULT_FILTER = new IIntersectionCalcnFilter(){
		public boolean accept(IDrawingNode node) {
			return true;
		}
	};
	private final IModel model;
	private IIntersectionCalcnFilter filter;
	
	public ShapeIntersectionCalculator(IModel model){
		this.model = model;
		this.filter = DEFAULT_FILTER;
	}
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.figure.geometry.INodeIntersectionCalculator#getModel()
	 */
	public IModel getModel(){
		return this.model;
	}
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.figure.geometry.INodeIntersectionCalculator#setFilter(org.pathwayeditor.figure.geometry.IIntersectionCalcnFilter)
	 */
	public void setFilter(IIntersectionCalcnFilter filter){
		if(filter == null){
			this.filter = DEFAULT_FILTER;
		}
		else{
			this.filter = filter;
		}
	}
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.figure.geometry.INodeIntersectionCalculator#findIntersectingNodes(org.pathwayeditor.figure.geometry.IConvexHull)
	 */
	public SortedSet<IDrawingNode> findIntersectingNodes(IConvexHull queryHull){
		Iterator<IDrawingNode> iter = model.drawingNodeIterator();
		SortedSet<IDrawingNode> retVal = new TreeSet<IDrawingNode>();
		while(iter.hasNext()){
			IDrawingNode node = iter.next();
			if(filter.accept(node) && queryHull.hullsIntersect(node.getAttribute().getConvexHull())){
				retVal.add(node);
			}
		}
		return retVal;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.figure.geometry.INodeIntersectionCalculator#findNodesAt(org.pathwayeditor.figure.geometry.IConvexHull)
	 */
	public SortedSet<IDrawingNode> findNodesAt(Point p) {
		Iterator<IDrawingNode> iter = model.drawingNodeIterator();
		SortedSet<IDrawingNode> retVal = new TreeSet<IDrawingNode>();
		while(iter.hasNext()){
			IDrawingNode node = iter.next();
			logger.trace("Testing contains node:" + node + ", hull=" + node.getAttribute().getConvexHull() + ", point=" + p);
			if(filter.accept(node) && node.getAttribute().getConvexHull().containsPoint(p)){
				logger.trace("Found containing node");
				retVal.add(node);
			}
		}
		return retVal;
	}
}
