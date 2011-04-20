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
 * ICanvasAttributeChangeListener is an interface that defines a listener for changes to the Canvas attribute.
 *
 * @author Stuart Moodie
 *
 */
public interface ICanvasAttributeChangeListener {

	/**
	 * Notify that an attribute of the Canvas Attribute has changed.
	 * @param e an instance providing details of the change that was made.
	 */
	void propertyChange(ICanvasAttributePropertyChangeEvent e);

	/**
	 * Notify that the canvas attribute being listened to has been translated. 
	 * @param e the translation event providing details of the translation.
	 */
	void elementTranslated(ICanvasAttributeTranslationEvent e);

	/**
	 * Notify that the canvas attribute being listened to has been resized.
	 * @param e the resize event providing details of the translation.
	 */
	void nodeResized(ICanvasAttributeResizedEvent e);

}
