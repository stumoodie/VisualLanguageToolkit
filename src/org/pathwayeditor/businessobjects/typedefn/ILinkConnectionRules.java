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
 * ILinkConnectionRules is an interface that is used to query the syntax rules for a link. The idea is that an instance of the interface is associated
 *  with a Link and is queried used by the link and its clients to ensure it is syntactically correct.
 *    
 * @author Stuart Moodie
 *
 */
public interface ILinkConnectionRules {
	
	/**
	 * Test if the source type is a valid source to the link.
	 * @param source A non null source object type.
	 * @return True if the it is a valid source, false otherwise.
	 * @throws IllegalArgumentException if source is null.
	 */
	boolean isValidSource(INodeObjectType source);
	
	/**
	 * Test if the combination of source and target can be joined by a link.
	 * @param source A non-null source object type.
	 * @param target A non-null target object type.
	 * @return True if both can for a link.
	 * @throws IllegalArgumentException if source and target are null.
	 */
	boolean isValidTarget(INodeObjectType source, INodeObjectType target);
	
	/**
	 * Get the link object type that these rule are applicable for.
	 * @return Returns a valid instance of <code>ILinkObjectType</code>. Cannot be null.
	 */
	ILinkObjectType getLinkObjectType(); 
}
