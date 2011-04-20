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

/**
 * LinkTerminusChangeType is an enumerated type containing the attributes of a link terminus
 * that may be changed and notified as an event.
 * 
 * @author Stuart Moodie
 *
 */
public enum LinkTerminusChangeType {
	/**
	 * The location of the terminus - the anchor position. 
	 */
	LOCATION,
	/**
	 * The type of the end decorator.
	 */
	END_DECORATOR_TYPE,
	/**
	 * The size of the end decorator.
	 */
	END_DECORATOR_SIZE,
	/**
	 * The gap between the end of the link (the anchor point) and the end of the dran link. 
	 */
	TERMINUS_GAP
}
