/**
 * 
 */
package org.pathwayeditor.businessobjects.drawingprimitives;

import org.pathwayeditor.businessobjects.hibernate.pojos.HibModel;

import uk.ed.inf.graph.compound.impl.CompoundGraph;

/**
 * @author smoodie
 *
 */
public interface ICompoundGraphBuilder {

	void setCompoundGraph(CompoundGraph graph);
	
	void setHibernateGraph(Object hibGraph);
	
	void buildGraph();
	
	CompoundGraph getGraph();
	
	void buildHibernateGraph();
	
	HibModel getHibCompoundGraph();
}
