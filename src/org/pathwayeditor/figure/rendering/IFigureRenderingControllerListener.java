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

package org.pathwayeditor.figure.rendering;

/**
 * IFigureChangeListener is an interface that defines a listener that is notified of 
 * attribute value changes in the {@link IFigureRenderingController}. 
 * 
 * @author Stuart Moodie
 *
 */
public interface IFigureRenderingControllerListener {

	/**
	 * Notify the listener that a change event has occurred in the figure controller. 
	 * @param event the event recording the attribute change made.
	 */
	void attributeChange(IFigureRenderingControllerAttributeChangeEvent event);
	
}
