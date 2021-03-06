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


package org.pathwayeditor.businessobjects.impl;

import org.pathwayeditor.businessobjects.drawingprimitives.ICanvasElementAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.ILabelAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.ILabelAttributeFactory;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationProperty;
import org.pathwayeditor.businessobjects.typedefn.ILabelObjectType;

import uk.ac.ed.inf.graph.compound.IElementAttribute;
import uk.ac.ed.inf.graph.util.IndexCounter;

/**
 * @author Stuart Moodie
 *
 */
public class LabelAttributeFactory implements ILabelAttributeFactory {
	private final IndexCounter creationSerialCounter;
	private IAnnotationProperty annotationProperty;
	private ICanvasElementAttribute destination;
	private Integer preferredCreationSerial = null;
	private ILabelObjectType objectType;

	/**
	 * @param creationSerialCounter
	 */
	public LabelAttributeFactory(IndexCounter creationSerialCounter) {
		this.creationSerialCounter = creationSerialCounter;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILabelAttributeFactory#setProperty(org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationProperty)
	 */
	@Override
	public void setProperty(IAnnotationProperty annotationProperty) {
		this.annotationProperty = annotationProperty;
	}

	/* (non-Javadoc)
	 * @see uk.ac.ed.inf.graph.compound.IElementAttributeFactory#canCreateAttribute()
	 */
	@Override
	public boolean canCreateAttribute() {
		return this.destination != null && this.annotationProperty != null && this.objectType != null
			&& this.objectType.getSyntaxService().isVisualisableProperty(this.annotationProperty.getDefinition());
	}

	/* (non-Javadoc)
	 * @see uk.ac.ed.inf.graph.compound.IElementAttributeFactory#setDestinationAttribute(uk.ac.ed.inf.graph.compound.IElementAttribute)
	 */
	@Override
	public void setDestinationAttribute(IElementAttribute attribute) {
		this.destination = (ICanvasElementAttribute)attribute;
	}

	/* (non-Javadoc)
	 * @see uk.ac.ed.inf.graph.compound.IElementAttributeFactory#getDestinationAttribute()
	 */
	@Override
	public ICanvasElementAttribute getDestinationAttribute() {
		return this.destination;
	}

	/* (non-Javadoc)
	 * @see uk.ac.ed.inf.graph.compound.IElementAttributeFactory#createAttribute()
	 */
	@Override
	public ILabelAttribute createAttribute() {
		Integer currSerial = this.preferredCreationSerial;
		if(currSerial == null){
			currSerial = creationSerialCounter.nextIndex();
		}
		return new LabelAttribute(this.destination.getModel(), currSerial.intValue(), this.annotationProperty, this.objectType);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILabelAttributeFactory#getProperty()
	 */
	@Override
	public IAnnotationProperty getProperty() {
		return this.annotationProperty;
	}

	/* (non-Javadoc)
	 * @see uk.ac.ed.inf.graph.compound.IElementAttributeFactory#setOutAttribute(uk.ac.ed.inf.graph.compound.IElementAttribute)
	 */
	@Override
	public void setOutAttribute(IElementAttribute attribute) {
	}

	/* (non-Javadoc)
	 * @see uk.ac.ed.inf.graph.compound.IElementAttributeFactory#getOutAttribute()
	 */
	@Override
	public IElementAttribute getOutAttribute() {
		return null;
	}

	/* (non-Javadoc)
	 * @see uk.ac.ed.inf.graph.compound.IElementAttributeFactory#setInAttribute(uk.ac.ed.inf.graph.compound.IElementAttribute)
	 */
	@Override
	public void setInAttribute(IElementAttribute attribute) {
	}

	/* (non-Javadoc)
	 * @see uk.ac.ed.inf.graph.compound.IElementAttributeFactory#getInAttribute()
	 */
	@Override
	public IElementAttribute getInAttribute() {
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICanvasAttributeFactory#setPreferredCreationSerial(int)
	 */
	@Override
	public void setPreferredCreationSerial(Integer creationSerial) {
		this.preferredCreationSerial = creationSerial;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICanvasAttributeFactory#getPreferredCreationSerial()
	 */
	@Override
	public Integer getPreferredCreationSerial() {
		return this.preferredCreationSerial;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILabelAttributeFactory#setLabelObjectType(org.pathwayeditor.businessobjects.typedefn.ILabelObjectType)
	 */
	@Override
	public void setLabelObjectType(ILabelObjectType labelObjectType) {
		this.objectType = labelObjectType;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILabelAttributeFactory#getLabelObjectType()
	 */
	@Override
	public ILabelObjectType getLabelObjectType() {
		return this.objectType;
	}

}
