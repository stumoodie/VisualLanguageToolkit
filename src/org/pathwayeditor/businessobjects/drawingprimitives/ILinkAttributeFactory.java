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

import org.pathwayeditor.businessobjects.typedefn.ILinkObjectType;

/**
 * ILinkAttributeFactory is an interface defining a factory for link attributes @{link ILinkAttribute}.
 * The factory requires an object type to create a link attribute.
 * The method {@link #canCreateAttribute()} should return false if this is not set.
 *     
 * @author Stuart Moodie
 *
 */
public interface ILinkAttributeFactory extends ICanvasAttributeFactory {

	@Override
	ILinkAttribute createAttribute();
	
	/**
	 * Sets the object type to be used when creating the next link attribute.
	 * @param objectType the object type to be used for the next link attribute to be created.
	 */
	void setObjectType(ILinkObjectType objectType);
	
	/**
	 * Gets the link object type to be used when creating the next link attribute.
	 * @return the link object type, which can be null if not set.
	 */
	ILinkObjectType getObjectType();

	@Override
	ITypedDrawingNodeAttribute getOutAttribute();

	@Override
	ITypedDrawingNodeAttribute getInAttribute();
	
}
