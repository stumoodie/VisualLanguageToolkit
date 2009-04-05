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

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.collection.PersistentCollection;
import org.hibernate.engine.SessionImplementor;
import org.hibernate.persister.collection.CollectionPersister;
import org.hibernate.usertype.UserCollectionType;

import uk.ed.inf.graph.compound.base.BaseCompoundEdge;
import uk.ed.inf.graph.compound.base.BaseCompoundNode;
import uk.ed.inf.graph.util.IDirectedEdgeSet;
import uk.ed.inf.graph.util.IEdgeSet;
import uk.ed.inf.graph.util.impl.DirectedEdgeSet;

public class EdgeSetType implements UserCollectionType {

	public EdgeSetType(){
		
	}
	
	
	public boolean contains(Object collection, Object obj) {
		Set<?> set = (Set<?>)collection;
		return set.contains(obj);
	}

	// could be common for all collection implementations.
	public Iterator<?> getElementsIterator(Object collection) {
		Set<?> nodeSet = (Set<?>)collection;
		return nodeSet.iterator();
	}
	
	// common for list-like collections.
	public Object indexOf(Object collection, Object obj) {
		return null;
	}
	
	// factory method for certain collection type.
	public IDirectedEdgeSet<BaseCompoundNode, BaseCompoundEdge> instantiate(int size) {
		return new DirectedEdgeSet<BaseCompoundNode, BaseCompoundEdge>();
	}
	
	// standard wrapper for collection type.
	public PersistentCollection instantiate(SessionImplementor session, CollectionPersister persister) throws HibernateException {
		// Use hibernate's built in persistent set implementation
		//wrapper
		return new PersistentEdgeSet(session);
	}
	
	// could be common implementation for all collection implementations
	@SuppressWarnings("unchecked")
	public Object replaceElements(Object collectionA, Object collectionB, CollectionPersister persister, Object owner,
									Map copyCache, SessionImplementor implementor) throws HibernateException {
		Set setA = (Set)collectionA;
		Set setB = (Set)collectionB;
		setB.clear();
		setB.addAll(setA);
		return setB;
	}
	
	// standard wrapper for collection type.
	@SuppressWarnings("unchecked")
	public PersistentCollection wrap(SessionImplementor session, Object collection) {
		// Use hibernate's built in persistent set implementation
		//wrapper.
		return new PersistentEdgeSet(session, (IEdgeSet)collection);
	}

}
