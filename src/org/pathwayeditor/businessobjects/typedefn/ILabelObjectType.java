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

/**
 * ILabelObjectType is an interface defining an object type for a 
 * label. 
 * 
 * @author Stuart Moodie
 *
 */
public interface ILabelObjectType extends INodeObjectType {

	/**
	 * Get the default/initial values to be used when creating a <code>ILabelAttribute</code> with this object type. 
	 * @return the default values for this object type.
	 */
	ILabelAttributeDefaults getDefaultAttributes();

	/**
	 * Is the property always shown or displayed.
	 * @return true if it is always displayed, false otherwise.
	 */
	boolean isAlwaysDisplayed();
	
	@Override
	ILabelObjectTypeParentingRules getParentingRules();
}
