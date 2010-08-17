/*
Copyright 2009, Court of the University of Edinburgh

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License. 
*/
package org.pathwayeditor.businessobjects.hibernate.customtypes;

import java.util.SortedSet;

import org.hibernate.collection.PersistentSet;
import org.hibernate.engine.SessionImplementor;

import uk.ac.ed.inf.graph.compound.ICompoundEdge;
import uk.ac.ed.inf.graph.compound.ICompoundNode;
import uk.ac.ed.inf.graph.util.IDirectedEdgeSet;
import uk.ac.ed.inf.graph.util.IEdgeSet;

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

	public boolean contains(ICompoundNode thisNode, ICompoundNode thatNode) {
		IEdgeSet edgeSet = (IEdgeSet)set;
		return edgeSet.contains(thisNode, thatNode);
	}

	public boolean contains(int edgeIdx) {
		IEdgeSet edgeSet = (IEdgeSet)set;
		return edgeSet.contains(edgeIdx);
	}

	public SortedSet get(ICompoundNode thisNode, ICompoundNode thatNode) {
		IEdgeSet edgeSet = (IEdgeSet)set;
		return edgeSet.get(thisNode, thatNode);
	}

	public ICompoundEdge get(int edgeIdx) {
		IEdgeSet edgeSet = (IEdgeSet)set;
		return edgeSet.get(edgeIdx);
	}

	public SortedSet getEdgesWith(ICompoundNode thisNode, ICompoundNode node) {
		IEdgeSet edgeSet = (IEdgeSet)set;
		return edgeSet.getEdgesWith(thisNode, node);
	}

	public boolean hasEdgesWith(ICompoundNode thisNode, ICompoundNode node) {
		IEdgeSet edgeSet = (IEdgeSet)set;
		return edgeSet.hasEdgesWith(thisNode, node);
	}

//	public boolean contains(IDirectedNode outNode, IDirectedNode inNode) {
//		IEdgeSet edgeSet = (IEdgeSet)set;
//		return edgeSet.contains(outNode, inNode);
//	}
//
//	public SortedSet get(IDirectedNode outNode, IDirectedNode inNode) {
//		IEdgeSet edgeSet = (IEdgeSet)set;
//		return edgeSet.get(outNode, inNode);
//	}

}
