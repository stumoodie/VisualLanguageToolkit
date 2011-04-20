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

package org.pathwayeditor.businessobjects.drawingprimitives.properties;

/**
 * IAnnotationPropertyVisitor is an interface that is used as part of the Visitor Design Pattern. It 
 * should be implemented by classes that wish to provide sub-type specific implementations of sub-types of
 * {@link IAnnotationProperty}. It provides a safer alternative to an if ... else ... if block. A method is only called
 * by an instance of the subtype matching the methods argument. So, for example, an implementation of <code>IIntegerAnnotationProperty</code>
 * will only ever invoke the method <code>visitIntegerAnnotationProperty()</code>.  
 * 
 * @author Stuart Moodie
 *
 */
public interface IAnnotationPropertyVisitor {

	/**
	 * An integer property implementation.
	 * @param prop the integer property invoking this method, which will not be null.
	 */
	void visitIntegerAnnotationProperty(IIntegerAnnotationProperty prop);
	
	/**
	 * An boolean property implementation.
	 * @param prop the boolean property invoking this method, which will not be null.
	 */
	void visitBooleanAnnotationProperty(IBooleanAnnotationProperty prop);
	
	/**
	 * An plain text property implementation.
	 * @param prop the plain text property invoking this method, which will not be null.
	 */
	void visitPlainTextAnnotationProperty(IPlainTextAnnotationProperty prop);
	
	/**
	 * An number property implementation.
	 * @param prop the number property invoking this method, which will not be null.
	 */
	void visitNumberAnnotationProperty(INumberAnnotationProperty prop);
	
	/**
	 * An list property implementation.
	 * @param prop the list property invoking this method, which will not be null.
	 */
	void visitListAnnotationProperty(IListAnnotationProperty prop);
	
}
