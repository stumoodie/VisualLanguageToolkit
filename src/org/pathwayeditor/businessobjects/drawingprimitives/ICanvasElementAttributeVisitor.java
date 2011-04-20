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
 * ICanvasElementAttributeVisitor a instance that defines a visitor to be used as part of a Visitor Pattern.
 * Classes should implement this interface and then provide implementations for each of the subtypes of ICanvasElementAttribute
 * specified in the methods. 
 * 
 * @see ICanvasElementAttribute
 * 
 * @author Stuart Moodie
 *
 */
public interface ICanvasElementAttributeVisitor {
	
	/**
	 * Provides and implementation for the IRootAttribute and will be called by classes implementing
	 * IRootAttribute.
	 * 
	 * @param attribute the rootAttribute, which should not be null.
	 */
	void visitRoot(IRootAttribute attribute);
	
	/**
	 * Provides and implementation for the IShapeAttribute and will be called by classes implementing
	 * IShapeAttribute.
	 * 
	 * @param attribute the shapeAttribute, which should not be null.
	 */
	void visitShape(IShapeAttribute attribute);
	
	/**
	 * Provides and implementation for the ILinkAttribute and will be called by classes implementing
	 * IRootAttribute.
	 * 
	 * @param attribute the linkAttribute, which should not be null.
	 */
	void visitLink(ILinkAttribute attribute);
	
	/**
	 * Provides and implementation for the ILabelAttribute and will be called by classes implementing
	 * ILabelAttribute.
	 * 
	 * @param attribute the labelAttribute, which should not be null.
	 */
	void visitLabel(ILabelAttribute attribute);
	
}
