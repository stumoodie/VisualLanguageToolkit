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
 * 
 * IBendPointChangeListener is an interface defining a bend-point container listener that is invoked
 * when a change to the bend-point structure or properties is detected.  
 *
 * @author Stuart Moodie
 *
 */
public interface IBendPointContainerListener {

	/**
	 * A change to the structure of the bend-point container is detected.
	 * @param e the event that has been detected.
	 */
	void structureChange(IBendPointStructureChangeEvent e);

	/**
	 * A change to the location of a bend-point in the container has been detected.
	 * @param e the event that has been detected.
	 */
	void locationChange(IBendPointLocationChangeEvent e);

}
