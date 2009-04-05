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
package org.pathwayeditor.businessobjects.typedefn;

import org.pathwayeditor.businessobjects.notationsubsystem.INotationSyntaxService;

/**
 * @author smoodie
 *
 */
public interface IObjectType extends Comparable<IObjectType> {

	/**
	 * Gets the notation syntax service that this object type belongs to.
	 * @return the syntax service which cannot be null.
	 */
	INotationSyntaxService getSyntaxService();

	/**
	 * The identifier that uniquely identifies the object type within this notation.
	 * This should be a positive number, i.e. <code>getUniqueId() >= 0</code>.
	 * @return the unique object type id.
	 */
	int getUniqueId();
	
	/**
	 * The human readable name name of the object type. It should be unique within the notation. 
	 * @return the name, which cannot be null or an empty string.
	 */
	String getName();

	/**
	 * Description of the object type.
	 * @return the description that cannot be null, but can be an empty string.
	 */
	String getDescription();

	/**
	 * An object type is equal if it belongs to the same notation and has the same UniqueId().
	 * Otherwise it should comply with the standard equals contract.
	 * @param other the other object to compare.
	 * @return true if equal based on above criteria, false otherwise.
	 */
	boolean equals(Object other);
	
	/**
	 * Gets the hash code, which is based on the notation and uniqueId. 
	 * @return the hashcode.
	 */
	int hashCode();
}
