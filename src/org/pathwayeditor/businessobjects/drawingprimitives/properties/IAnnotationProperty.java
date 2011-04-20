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

package org.pathwayeditor.businessobjects.drawingprimitives.properties;

import org.pathwayeditor.businessobjects.drawingprimitives.listeners.IAnnotationPropertyChangeListenee;

/**
 * 
 * IAnnotationProperty is an interface defining an annotation property. An annotation property is a configurable piece of information
 * that is associated with most canvas attributes. Its purpose is to store notation specific information that may be displayed or used
 * by a notation subsystem when manipulation a given model. Each property is specified by a <code>IPropertyDefinition</code>, which in
 * turn is defined within the NotationSubsystem.
 *
 * @author Stuart Moodie
 *
 */
public interface IAnnotationProperty extends IAnnotationPropertyChangeListenee {
	/**
	 * Get the definition associated with this PlainTextProperty.
	 * @return The property definition instance. Cannot be null.
	 */
	IPropertyDefinition getDefinition();
	
	/**
	 * Gets the owner of this property
	 * @return the owner of this property, which cannot be null. 
	 */
	IAnnotatedObject getOwner();
	
	/**
	 * Get the an object representation of the value of this property
	 * @return An object representing the value of the property. Cannot be null. The value must satisfy
	 * <code>this.isValid(this.getValue))</code>
	 */
	Object getValue();

	/**
	 * Visit the annotation property. This method applies the Visitor design pattern and provides a mechanisms for
	 * applying different operations to subclasses of this interface without the usual if/else clause.    
	 * @param visitor an implementation of the IAnnotationPropertyVisitor interface that provides different operations for
	 * the subclasses of this interface.
	 */
	void visit(IAnnotationPropertyVisitor visitor);
}
