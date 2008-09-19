package org.pathwayeditor.businessobjects.hibernate.customtypes;

import java.util.SortedSet;

import org.hibernate.collection.PersistentSet;
import org.hibernate.engine.SessionImplementor;

import uk.ed.inf.graph.basic.IBasicEdge;
import uk.ed.inf.graph.basic.IBasicNode;
import uk.ed.inf.graph.directed.IDirectedNode;
import uk.ed.inf.graph.util.IDirectedEdgeSet;
import uk.ed.inf.graph.util.IEdgeSet;

@SuppressWarnings("unchecked")
public class PersistentEdgeSet extends PersistentSet implements IDirectedEdgeSet {

	private static final long serialVersionUID = 8815020068010247244L;

	/**
	 * Empty constructor.
	 * <p/>
	 * Note: this form is not ever ever ever used by Hibernate; it is, however,
	 * needed for SOAP libraries and other such marshalling code.
	 */
	public PersistentEdgeSet() {
		super();
	}

	/**
	 * Constructor matching super.  Instantiates a lazy set (the underlying
	 * set is un-initialized).
	 *
	 * @param session The session to which this set will belong.
	 */
	public PersistentEdgeSet(SessionImplementor session) {
		super( session );
	}

	/**
	 * Instantiates a non-lazy set (the underlying set is constructed
	 * from the incoming set reference).
	 *
	 * @param session The session to which this set will belong.
	 * @param set The underlying set data.
	 */
	public PersistentEdgeSet(SessionImplementor session, IEdgeSet set) {
		super(session, set);
	}

	public boolean contains(IBasicNode thisNode, IBasicNode thatNode) {
		IEdgeSet edgeSet = (IEdgeSet)set;
		return edgeSet.contains(thisNode, thatNode);
	}

	public boolean contains(int edgeIdx) {
		IEdgeSet edgeSet = (IEdgeSet)set;
		return edgeSet.contains(edgeIdx);
	}

	public SortedSet get(IBasicNode thisNode, IBasicNode thatNode) {
		IEdgeSet edgeSet = (IEdgeSet)set;
		return edgeSet.get(thisNode, thatNode);
	}

	public IBasicEdge get(int edgeIdx) {
		IEdgeSet edgeSet = (IEdgeSet)set;
		return edgeSet.get(edgeIdx);
	}

	public SortedSet getEdgesWith(IBasicNode node) {
		IEdgeSet edgeSet = (IEdgeSet)set;
		return edgeSet.getEdgesWith(node);
	}

	public boolean hasEdgesWith(IBasicNode node) {
		IEdgeSet edgeSet = (IEdgeSet)set;
		return edgeSet.hasEdgesWith(node);
	}

	public boolean contains(IDirectedNode outNode, IDirectedNode inNode) {
		IEdgeSet edgeSet = (IEdgeSet)set;
		return edgeSet.contains(outNode, inNode);
	}

	public SortedSet get(IDirectedNode outNode, IDirectedNode inNode) {
		IEdgeSet edgeSet = (IEdgeSet)set;
		return edgeSet.get(outNode, inNode);
	}

}
