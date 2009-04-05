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

import org.hibernate.collection.PersistentSet;
import org.hibernate.engine.SessionImplementor;

import uk.ed.inf.graph.basic.IBasicNode;
import uk.ed.inf.graph.util.INodeSet;

@SuppressWarnings("unchecked")
public class PersistentNodeSet extends PersistentSet implements INodeSet {

	private static final long serialVersionUID = 8815020068010247244L;

	/**
	 * Empty constructor.
	 * <p/>
	 * Note: this form is not ever ever ever used by Hibernate; it is, however,
	 * needed for SOAP libraries and other such marshalling code.
	 */
	public PersistentNodeSet() {
		super();
	}

	/**
	 * Constructor matching super.  Instantiates a lazy set (the underlying
	 * set is un-initialized).
	 *
	 * @param session The session to which this set will belong.
	 */
	public PersistentNodeSet(SessionImplementor session) {
		super( session );
	}

	/**
	 * Instantiates a non-lazy set (the underlying set is constructed
	 * from the incoming set reference).
	 *
	 * @param session The session to which this set will belong.
	 * @param set The underlying set data.
	 */
	public PersistentNodeSet(SessionImplementor session, INodeSet set) {
		super(session, set);
	}

	public boolean contains(int nodeIdx) {
		INodeSet nodeSet = (INodeSet)set; 
		return nodeSet.contains(nodeIdx);
	}

	public IBasicNode get(int nodeIdx) {
		INodeSet nodeSet = (INodeSet)set; 
		return nodeSet.get(nodeIdx);
	}

}
