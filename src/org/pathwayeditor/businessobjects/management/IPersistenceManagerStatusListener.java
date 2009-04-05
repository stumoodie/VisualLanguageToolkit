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

/**
 * @author smoodie
 *
 */
public interface IPersistenceManagerStatusListener {
	enum StateChange { OPENED, CLOSED, CANVAS_CREATED, CANVAS_DESTROYED }
	
	/**
	 * Notify clients that the state of this <code>changedManager</code> has changed to <code>stateChange</code>. Note that
	 * the change has occurred when this event is fired.
	 * @param stateChange the change in state that has occurred
	 * @param changedManager the changed manager, which cannot be null.
	 */
	void stateChanged(StateChange stateChange, IPersistenceManager changedManager);
	
	/**
	 * Notify clients that this state change is about to happen and give them the opportunity to request that this
	 * operation change is aborted. Note that it is up to the persistence manager whether it respects this request,
	 * and clients should read the documentation of the persistence manager they are listening to.
	 * @param stateChange the change that will be made, which cannot be null. 
	 * @param changeManager the change that is about to make this change.
	 * @return true if the client has requested that the change be cancelled, false otherwise.
	 */
	boolean requestCancelStateChange(StateChange stateChange, IPersistenceManager changeManager);
	
}
