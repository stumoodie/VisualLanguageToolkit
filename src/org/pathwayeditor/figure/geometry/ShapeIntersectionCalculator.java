/**
 * 
 */
package org.pathwayeditor.figure.geometry;

import java.util.Comparator;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

import org.apache.log4j.Logger;
import org.pathwayeditor.businessobjects.drawingprimitives.IDrawingNode;
import org.pathwayeditor.businessobjects.drawingprimitives.IModel;
import org.pathwayeditor.businessobjects.drawingprimitives.IRootNode;
import org.pathwayeditor.figure.figuredefn.IFigureGeometryFactory;

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
	
	private static final Comparator<IDrawingNode> DEFAULT_COMPARATOR = new Comparator<IDrawingNode>(){

		public int compare(IDrawingNode o1, IDrawingNode o2) {
			int retVal = 0;
			if(o1.getLevel() < o2.getLevel()){
				retVal = 1;
			}
			else if(o1.getLevel() > o2.getLevel()){
				retVal = -1;
			}
			else{
				int o1Idx = o1.getIndex();
				int o2Idx = o2.getIndex();
				retVal = o1Idx < o2Idx ? 1 : (o1Idx > o2Idx ? -1 : 0); 
			}
			return retVal;
		}
		
	};
	
	private final IModel model;
	private IIntersectionCalcnFilter filter;
	private Comparator<IDrawingNode> comparator = DEFAULT_COMPARATOR;
	
	public ShapeIntersectionCalculator(IModel model){
		this.model = model;
		this.filter = DEFAULT_FILTER;
	}
	
	public void setComparator(Comparator<IDrawingNode> comparator){
		this.comparator = comparator;
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
	public SortedSet<IDrawingNode> findIntersectingNodes(IConvexHull queryHull, IDrawingNode queryNode){
		Iterator<IDrawingNode> iter = model.drawingNodeIterator();
		SortedSet<IDrawingNode> retVal = new TreeSet<IDrawingNode>(this.comparator);
		IFigureGeometryFactory fact = model.getFigureGeometryFactory();
		// the root node will always intersect - that's a give so add it in and exclude it from
		// intersection tests
		IRootNode rootNode = model.getRootNode();
		retVal.add(rootNode);
		while(iter.hasNext()){
			IDrawingNode node = iter.next();
			IConvexHull attributeHull = fact.getConvexHull(node);
			// ignore matches to self
			if(!node.equals(queryNode) && !node.equals(rootNode) && filter.accept(node) && queryHull.hullsIntersect(attributeHull)){
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
		SortedSet<IDrawingNode> retVal = new TreeSet<IDrawingNode>(this.comparator);
		IFigureGeometryFactory fact = model.getFigureGeometryFactory();
		while(iter.hasNext()){
			IDrawingNode node = iter.next();
			IConvexHull attributeHull = fact.getConvexHull(node);
			logger.trace("Testing contains node:" + node + ", hull=" + attributeHull + ", point=" + p);
			if(filter.accept(node) && attributeHull.containsPoint(p)){
				logger.trace("Found containing node");
				retVal.add(node);
			}
		}
		return retVal;
	}
}
