/*
  Licensed to the Court of the University of Edinburgh (UofE) under one
  or more contributor license agreements.  See the NOTICE file
  distributed with this work for additional information
  regarding copyright ownership.  The UofE licenses this file
  to you under the Apache License, Version 2.0 (the
  "License"); you may not use this file except in compliance
  with the License.  You may obtain a copy of the License at

  http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing,
  software distributed under the License is distributed on an
  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
  KIND, either express or implied.  See the License for the
  specific language governing permissions and limitations
  under the License.
*/

/**
 * 
 */
package org.pathwayeditor.businessobjects.drawingprimitives.properties;

import java.util.Iterator;
import java.util.List;

/**
 * IListAnnotationProperty is an interface the defines an annotation property containing a list of values.
 * 
 * @author Stuart Moodie
 *
 */
public interface IListAnnotationProperty extends IAnnotationProperty{
	
	/**
	 * Get the definition associated with this property.
	 * @return The property definition instance. Cannot be null.
	 */
	@Override
	IListPropertyDefinition getDefinition();
	
	/**
	 * Get the value associated with this property.
	 * @return a list of values, which cannot be null, but the list may be empty. The list should be a copy or immutable.
	 */
	@Override
	List<String> getValue();

	/**
	 * Adds a new value to the annotation list.
	 * @param newValue the new value to add to the property list.
	 */
	void addValue(String newValue);
	
	/**
	 * Gets a new iterator to all the values in this list
	 * @return a new iterator of list annotation property values.
	 */
	Iterator<String> getValueIterator();
}
