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

import java.util.Iterator;

import org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationProperty;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationPropertyVisitor;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IBooleanAnnotationProperty;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IIntegerAnnotationProperty;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IListAnnotationProperty;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.INumberAnnotationProperty;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPlainTextAnnotationProperty;
import org.pathwayeditor.businessobjects.exchange.castor.BooleanAnnotationProperty;
import org.pathwayeditor.businessobjects.exchange.castor.IntegerAnnotationProperty;
import org.pathwayeditor.businessobjects.exchange.castor.ListAnnotationProperty;
import org.pathwayeditor.businessobjects.exchange.castor.ListItem;
import org.pathwayeditor.businessobjects.exchange.castor.NumberAnnotationProperty;
import org.pathwayeditor.businessobjects.exchange.castor.PropertyList;
import org.pathwayeditor.businessobjects.exchange.castor.TextAnnotationProperty;

/**
 * @author Stuart Moodie
 *
 */
public class AnnotationsBuilder implements IAnnotationPropertyVisitor {
	private final PropertyList annotations;
	private int lastPropId = 0;
	
	public AnnotationsBuilder(){
		this.annotations = new PropertyList();
	}
	
	public int createProperty(IAnnotationProperty prop){
		prop.visit(this);
		return lastPropId;
	}
	
	public PropertyList getAnnotations(){
		return this.annotations;
	}
	
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationPropertyVisitor#visitBooleanProperty(org.pathwayeditor.businessobjects.drawingprimitives.properties.IBooleanAnnotationProperty)
	 */
	@Override
	public void visitBooleanAnnotationProperty(IBooleanAnnotationProperty property) {
		BooleanAnnotationProperty prop = new BooleanAnnotationProperty();
		prop.setId(++this.lastPropId);
		prop.setName(property.getDefinition().getName());
		prop.setDisplayName(property.getDefinition().getDisplayName());
		prop.setValue(property.getValue());
		this.annotations.addBooleanAnnotationProperty(prop);
	}

//	/* (non-Javadoc)
//	 * @see org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationPropertyVisitor#visitHtmlProperty(org.pathwayeditor.businessobjects.drawingprimitives.properties.IHtmlAnnotationProperty)
//	 */
//	public void visitHtmlProperty(IHtmlAnnotationProperty property) {
//	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationPropertyVisitor#visitIntegerProperty(org.pathwayeditor.businessobjects.drawingprimitives.properties.IIntegerAnnotationProperty)
	 */
	@Override
	public void visitIntegerAnnotationProperty(IIntegerAnnotationProperty property) {
		IntegerAnnotationProperty prop = new IntegerAnnotationProperty();
		prop.setId(++this.lastPropId);
		prop.setName(property.getDefinition().getName());
		prop.setDisplayName(property.getDefinition().getDisplayName());
		prop.setValue(property.getValue());
		this.annotations.addIntegerAnnotationProperty(prop);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationPropertyVisitor#visitListProperty(org.pathwayeditor.businessobjects.drawingprimitives.properties.IListAnnotationProperty)
	 */
	@Override
	public void visitListAnnotationProperty(IListAnnotationProperty property) {
		ListAnnotationProperty prop = new ListAnnotationProperty();
		prop.setId(++this.lastPropId);
		prop.setName(property.getDefinition().getName());
		prop.setDisplayName(property.getDefinition().getDisplayName());
		Iterator<String> iter = property.getValueIterator();
		int idx = 0;
		while(iter.hasNext()){
			ListItem item = new ListItem();
			item.setIdx(idx++);
			item.setValue(iter.next());
			prop.addListItem(item);
		}
		this.annotations.addListAnnotationProperty(prop);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationPropertyVisitor#visitNumberProperty(org.pathwayeditor.businessobjects.drawingprimitives.properties.INumberAnnotationProperty)
	 */
	@Override
	public void visitNumberAnnotationProperty(INumberAnnotationProperty property) {
		NumberAnnotationProperty prop = new NumberAnnotationProperty();
		prop.setId(++this.lastPropId);
		prop.setName(property.getDefinition().getName());
		prop.setDisplayName(property.getDefinition().getDisplayName());
		prop.setValue(property.getValue().toString());
		this.annotations.addNumberAnnotationProperty(prop);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationPropertyVisitor#visitPlainTextProperty(org.pathwayeditor.businessobjects.drawingprimitives.properties.IPlainTextAnnotationProperty)
	 */
	@Override
	public void visitPlainTextAnnotationProperty(IPlainTextAnnotationProperty property) {
		TextAnnotationProperty prop = new TextAnnotationProperty();
		prop.setId(++this.lastPropId);
		prop.setName(property.getDefinition().getName());
		prop.setDisplayName(property.getDefinition().getDisplayName());
		prop.setContent(property.getValue());
		this.annotations.addTextAnnotationProperty(prop);
	}
}
