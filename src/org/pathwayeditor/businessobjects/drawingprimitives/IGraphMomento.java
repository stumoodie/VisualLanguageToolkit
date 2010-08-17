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
package org.pathwayeditor.businessobjects.drawingprimitives;

import java.util.Date;

/**
 * Stores the state of the canvas ready for it to be retrieved. 
 * @author smoodie
 *
 */
public interface IGraphMomento {

	/**
	 * The model that this is a momento of.
	 * @return the model, which cannot be null.
	 */
	IModel getModel();
	
	/**
	 * The time the snapshot was taken.
	 * @return the creation date which cannot be null.
	 */
	Date getCreationDate();
	
	/**
	 * A number that uniquely identifies this momento within the model. This should be unique for every momento
	 * created by the model.
	 * @return the version number, which must be an integer greater than zero.
	 */
	int getVersionNum();
	
	/**
	 * Equal if the model and the version number are the same.
	 * @param other the other object to compare.
	 * @return true is this momento and the other momento both have equal models and the same version num.
	 */
	@Override
	boolean equals(Object other);
}
