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

import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LinkTermType;


/**
 * ILinkTerminusDefinition is an interface that defines the link end appearance and default properties. The link end has a decorator at the end of
 *  the link which is typically an arrowhead or some kind and a decorator at the terminus of the link
 *  which can be any of the shape primitives. The offset defines the gap between both of these. The line
 *  drawn for the link finished at the link end decorator (hence the name).
 *    
 * @author Stuart Moodie
 *
 */
public interface ILinkTerminusDefinition {
	public static enum LinkTermEditableAttributes{ OFFSET, END_SIZE, END_DECORATOR_TYPE };
	
	/**
	 * Get the link defaults object object that this definition belongs to.
	 * @return A non-null instance of the object type.
	 */
	ILinkObjectType getOwningObjectType();
	
	/**
	 * The type of link end, i.e. target or source.
	 * @return the terminus type, which cannot be null.
	 */
	LinkTermType getLinkEndCode();
			
	ILinkTerminusDefaults getDefaultAttributes();
	
	EnumSet<LinkTermEditableAttributes> getEditableAttributes();
}
