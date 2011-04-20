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


package org.pathwayeditor.businessobjects.drawingprimitives.listeners;

import org.pathwayeditor.businessobjects.drawingprimitives.ILinkTerminus;

/**
 * ILinkTerminusValueChangeEvent is an interface that defines an attribute value change event in a
 * link terminus. The attribute changed is indicated by the <code>LinkTerminusChangeType</code>. 
 * 
 * @author Stuart Moodie
 *
 */
public interface ILinkTerminusValueChangeEvent {

	/**
	 * Gets the <code>LinkTerminusChangeType</code> indicating the attribute that was changed.
	 * @return the type of the change, whic cannot be null. 
	 */
	LinkTerminusChangeType getChangeType();
	
	/**
	 * Gets the link terminus that originated this event.
	 * @return the originating link terminus.
	 */
	ILinkTerminus getChangedLinkTerminus();
	
	/**
	 * Gets the original value of the attribute. 
	 * @return the original value.
	 */
	Object getOldValue();
	
	/**
	 * Gets the new value of the attribute.
	 * @return the new value.
	 */
	Object getNewValue();
	
}
