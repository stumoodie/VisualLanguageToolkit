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


package org.pathwayeditor.businessobjects.exchange;

import org.pathwayeditor.businessobjects.drawingprimitives.ICanvasElementAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeAttributeFactory;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPlainTextAnnotationProperty;
import org.pathwayeditor.businessobjects.typedefn.IShapeObjectType;

import uk.ac.ed.inf.graph.compound.ICompoundNode;
import uk.ac.ed.inf.graph.compound.ICompoundNodeFactory;

/**
 * @author Stuart Moodie
 *
 */
public class ShapeBuilder {
	private ICanvasElementAttribute parent;
	private IShapeObjectType objectType;
	private ICompoundNode compoundNode;
	private String namePropName;

	public ShapeBuilder(ICanvasElementAttribute parent, IShapeObjectType objectType){
		this.parent = parent;
		this.objectType = objectType;
	}

	
	public void setNamePropertyName(String propName){
		this.namePropName = propName;
	}
	
	public IShapeAttribute create(String name){
		IShapeAttributeFactory shapeAttFact = parent.getModel().shapeAttributeFactory();
		shapeAttFact.setObjectType(objectType);
		ICompoundNodeFactory nodeFactory = parent.getCurrentElement().getChildCompoundGraph().nodeFactory();
		nodeFactory.setAttributeFactory(shapeAttFact);
		this.compoundNode = nodeFactory.createNode();
		IShapeAttribute shapeAtt = (IShapeAttribute)this.compoundNode.getAttribute();
		IPlainTextAnnotationProperty prop = (IPlainTextAnnotationProperty)shapeAtt.getProperty(this.namePropName);
		prop.setValue(name);
		return shapeAtt;
	}
	
	
	public ICompoundNode getNode(){
		return this.compoundNode;
	}


	public void setParent(ICanvasElementAttribute parent) {
		this.parent = parent;
	}

	
	public ICompoundNode getParent(){
		return this.compoundNode;
	}


	public void setObjectType(IShapeObjectType objectType) {
		this.objectType = objectType;
	}

	
	public IShapeObjectType getObjectType(){
		return this.objectType;
	}
}
