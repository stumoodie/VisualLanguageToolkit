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


package org.pathwayeditor.businessobjects.drawingprimitives;

import org.pathwayeditor.businessobjects.drawingprimitives.listeners.ICanvasAttributeChangeListenee;
import org.pathwayeditor.businessobjects.typedefn.IObjectType;
import org.pathwayeditor.figure.geometry.Point;

import uk.ac.ed.inf.graph.compound.IElementAttribute;


/**
 * ICanvasElementAttribute is an interface that provides the basic attributes and behaviours of a Canvas Attribute.
 * No that an attribute is regarded as removed, if its associated Compound Graph Element is marked as removed. 
 * 
 * @author Stuart Moodie
 *
 */
public interface ICanvasElementAttribute extends IElementAttribute, ICanvasAttributeChangeListenee, Comparable<ICanvasElementAttribute> {
	
	/**
	 * Gets the IModel to which this canvas attribute belongs.
	 * @return the model instance which cannot be null.
	 */
	IModel getModel();
	
	/**
	 * Gets the object type of the attribute.
	 * @return the object type which cannot be null.
	 */
	IObjectType getObjectType();
	
	/**
	 * Translate the attribute by the given displacement.
	 * @param delta the displacement to translate this attribute by, which cannot be null.
	 */
	void translate(Point delta);
	
	/**
	 * Get the Creation serial of this attribute. Cannot be null.
	 * @return the serial number, greater that 0.
	 */	
	int getCreationSerial();	
	
	
	/**
	 * Tests if the attribute has been removed. It is removed is the CompoundGraphElement it is
	 * associated with is removed. 
	 * @return true if it has been removed, false otherwise.
	 */
	boolean isRemoved();
	
	/**
	 * Applies a visitor to this instance. See the Visitor Pattern for more information on
	 * how this works.
	 *    
	 * @param visitor the visitor interface, which cannot be null.
	 */
	void visit(ICanvasElementAttributeVisitor visitor);
	
	IZOrderManager getZorderManager();
}
