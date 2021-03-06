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

import uk.ac.ed.inf.graph.compound.IElementAttributeFactory;

/**
 * ICanvasAttributeFactory is an interface that defines a factory for the creation of an ICanvasAttribute.
 * 
 * 
 * @author Stuart Moodie
 *
 */
public interface ICanvasAttributeFactory extends IElementAttributeFactory {

	/**
	 * Sets the creation serial to use when creating the attribute. This value is ignored if this value is not set.
	 * @param creationSerial
	 */
	void setPreferredCreationSerial(Integer creationSerial);
	
	/**
	 * The value currently set as the preferred creation serial. 
	 * @return the value of the currently preferred creation serial, which may be null.
	 */
	Integer getPreferredCreationSerial();
	
}
