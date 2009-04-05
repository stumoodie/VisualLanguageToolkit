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
/**
 * 
 */
package org.pathwayeditor.businessobjects.management;

import java.util.Iterator;

/**
 * Defines a persistence manager that can be used to access objects from persistence storage. It has a status of
 * opened and closed. Once opened its contents can be synchronised with the persistent storage. Currently opening
 * the manager effectively creates an exclusive lock on the object it is managing. In future it is planned to implement
 * a more sophisticated locking scheme, with read-only and read-write locks.
 * @author smoodie
 *
 */
public interface IPersistenceManager {

	/**
	 * Tests if the manager is open so that its content can be access.
	 * @return true if the manager is open, false otherwise.
	 */
	boolean isOpen();
	
	/**
	 * Loads the content from the persistent storage and opens this manager.
	 * @throws IllegalStateException if this manager has already been opened. 
	 */
	void open();

	/**
	 * Close this manager. If the manager is already closed, then do nothing.
	 * If the close is not forced (by calling <code>close(true)</code>) then a client listening to this manager may choose to abort
	 * this action in which case the manager will still be open. Test <code>isOpen()</code>
	 * after this call to see if this has happened. If close is forced then the manager will close
	 * regardless of any clients listening to the request.
	 */
	void close(boolean force);

	/**
	 * Synchronised the content of this manager with persistent storage. Note that the manager must be open.
	 * @throws IllegalStateException if <code>isOpen() == false</code>. 
	 */
	void synchronise();

	/**
	 * Adds a new listener to this manager 
	 * @param listener the listener which cannot be null.
	 * @throws IllegalArgumentException if <code>listener == null</code>.
	 */
	void addListener(IPersistenceManagerStatusListener listener);
	
	/**
	 * Removed the listener from this manager.
	 * @param listener the listener which cannot be null.
	 * @throws IllegalArgumentException if <code>listener == null</code>.
	 */
	void removeListener(IPersistenceManagerStatusListener listener);
	
	/**
	 * Gets an iterator of  the listeners held by this manager.
	 * @return the iterator, which cannot be null.
	 */
	Iterator<IPersistenceManagerStatusListener> listenerIterator();
}
