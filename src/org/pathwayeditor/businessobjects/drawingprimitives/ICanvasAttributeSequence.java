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

/**
 * ICanvasAttributeSequence is an interface that provides access to a unique index sequence. It provides a sequence of integers
 * starting at the current index and incrementing by 1. 
 * 
 * @author Stuart Moodie
 *
 */
public interface ICanvasAttributeSequence {

	/**
	 * Gets the current index number
	 * @return the index number.
	 */
	int getCurrent();
	
	/**
	 * Gets the next index number in the sequence. Incrementing the current index by 1.
	 * @return the next index number that was just created.
	 */
	int next();
	
}
