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
