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

import java.util.EnumSet;

/**
 * 
 * ILinkObjectType is an interface that defines an object type for a link. In addition to
 * to providing parent/child syntax it also defines what nodes can be connect by a link the the notation
 * and so is the final link in the syntax definition as provided by the notation subsystem's syntax service.
 * See {@link IObjectType} for more information about the purpose of an object type. 
 *
 * @author Stuart Moodie
 *
 */
public interface ILinkObjectType extends IObjectType {
	public static enum LinkEditableAttributes{ COLOUR, LINE_STYLE, LINE_WIDTH };
	
	/**
	 * Returns the unique identifier for the link object type, which must be a positive integer.
	 * @return the unique id, which must comply with the postcondition: <code>getUniqueId() > 0</code>.
	 */
	@Override
	int getUniqueId();
	
	/**
	 * Gets the connection rules for this link.
	 * @return A non-null instance of the connection rules.
	 */
	ILinkConnectionRules getLinkConnectionRules();

	/**
	 * Gets the default attributes for the link.
	 * @return the default attributes class, which cannot be null.
	 */
	ILinkAttributeDefaults getDefaultAttributes();
	
	/**
	 * Get the source terminus definition.
	 * @return the definition, which cannot be null.
	 */
	ILinkTerminusDefinition getSourceTerminusDefinition();
	
	/**
	 * Get the target terminus definition.
	 * @return the definition, which cannot be null.
	 */
	ILinkTerminusDefinition getTargetTerminusDefinition();
	
	/**
	 * Get the editable attributes for this type.
	 * @return the set of editable attributes, which cannot be null, but can be empty.
	 */
	EnumSet<LinkEditableAttributes> getEditableAttributes();
}
