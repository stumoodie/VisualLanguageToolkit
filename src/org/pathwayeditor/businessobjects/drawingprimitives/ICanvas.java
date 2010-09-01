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
package org.pathwayeditor.businessobjects.drawingprimitives;

import org.pathwayeditor.businessobjects.drawingprimitives.listeners.ICanvasPropertyChangeListenee;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.ISuppressableChangeListenee;
import org.pathwayeditor.businessobjects.impl.IBusinessObjectGraphElementMapper;
import org.pathwayeditor.figure.geometry.Dimension;


public interface ICanvas extends ICanvasPropertyChangeListenee, ISuppressableChangeListenee, Comparable<ICanvas> {
	
	
	/**
	 * Name of repository that holds this canvas.
	 * @return the canvas name.
	 */
	String getRepositoryName();
	
	/**
	 * The unique identifier within the above repository.
	 * @return the inode.
	 */
	int getINode();
	
	/**
	 * Enable or disable the Snap to Grid functionality.
	 */	
	void setSnapToGrid(boolean snapToGridStatus);
	
	/**
	 * Get Snap to Grid functionality status.
	 * @return true if Snap To Grid is on, false otherwise.
	 */
	boolean isSnapToGridOn();
	
	/**
	 * Enable or disable the Grid.
	 */	
	void setGridEnabled(boolean gridStatus);
	
	/**
	 * Get Grid status.
	 * @return true if Grid is enabled, false otherwise.
	 */
	boolean isGridEnabled();

	/**
	 * Set Grid size.
	 * @throws IllegalArgumentException if X or Y are less than zero.
	 */	
	void setGridSize(Dimension newSize);
	
	/**
	 * Get the current grid size.
	 * @return the grid size, which cannot be null.
	 */
	Dimension getGridSize();

	
	/**
	 * Get the size of this map
	 * @return the size. Cannot be null.
	 */	
	Dimension getCanvasSize () ;
	
	/**
	 * Set the size of this map
	 * @throws IllegalArgumentException if value is null.
	 */	
	void setCanvasSize ( Dimension size ) ;
	
	IModel setModel(IModel model);
	
	/**
	 * Get the model for this canvas.
	 * @return the model associated with the canvas, which cannot be null. 
	 */
	IModel getModel();

	/**
	 * Is this canvas empty, i.e. does it contain any drawing elements?
	 * @return true if it is, false otherwise.
	 */
	boolean isEmpty();
	
	/**
	 * Tests if the given canvas can be copied into this one. This requires that the 
	 * canvas is not null, it is different to this one, it uses the same notation subsystem
	 * and that this canvas is empty.
	 * @param canvas the canvas to test.
	 * @return true if <code>canvas</code> can be successfully copied here, false otherwise.
	 */
	boolean canCopyHere(ICanvas canvas);
	
	/**
	 * Copy another canvas into this one. For it to succeed it must be a different
	 * canvas, they must have the same notation subsystems and this canvas must be empty.
	 * @param otherCanvas the other canvas to be copied here. 
	 * @throws IllegalArgumentException if <code>canCopyHere(otherCanvas)==false</code>.
	 */
	void copyHere(ICanvas otherCanvas);
	
	
	IBusinessObjectGraphElementMapper getMapper();

	IRootAttribute getRootAttribute();
}
