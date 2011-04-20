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

package org.pathwayeditor.businessobjects.typedefn;

import org.pathwayeditor.businessobjects.notationsubsystem.INotationSyntaxService;

/**
 * IObjectType is an interface that defines an object type. The object type is the basic unit of syntax defintion
 * in a Notation Subsystem. It defines the appearance and initial attribute and property values of
 * the shapes, links and labels in the notation. It also creates the basic syntax of the notation by
 * by defining which object types can have parent child relationships.  
 * 
 * An object type is equal if it belongs to the same notation and has the same UniqueId().
 * Otherwise it should comply with the standard equals contract.

 * @author Stuart Moodie
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
	 * Get the parenting rules for this node object type, which must exist.
	 * @return the parenting rules, which cannot be null.
	 */
	IObjectTypeParentingRules getParentingRules();
}
