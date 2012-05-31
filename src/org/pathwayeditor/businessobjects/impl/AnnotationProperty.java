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

import java.util.Iterator;
import java.util.List;

import org.pathwayeditor.businessobjects.drawingprimitives.listeners.AnnotationPropertyChangeHelper;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.IAnnotationPropertyChangeEvent;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.IAnnotationPropertyChangeListener;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotatedObject;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationProperty;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyDefinition;

/**
 * Property generated by hbm2java
 */
public abstract class AnnotationProperty implements IAnnotationProperty {

//	private ILabelAttribute labelAttribute = null;
	private final IAnnotatedObject owner;
	private final AnnotationPropertyChangeHelper listenerHandler = new AnnotationPropertyChangeHelper(this);
//	private IPropertyDefinition propDefn;

	protected AnnotationProperty(IAnnotatedObject owner) {
		this.owner = owner;
	}

//
//	protected AnnotationProperty(IAnnotatedObject owner, IPropertyDefinition propDefn) {
//		this.owner = owner;
//		this.propDefn = propDefn;
//	}
//
//	protected AnnotationProperty(IAnnotatedObject newOwner, AnnotationProperty other) {
//		this.owner = newOwner;
//		this.propDefn = other.propDefn;
//	}
	
	protected abstract void setPropertyDefinition(IPropertyDefinition definition);

//	@Override
//	public boolean isDisplayed() {
//		return this.labelAttribute != null && !this.labelAttribute.isRemoved();
//	}
	
//	@Override
//	public void setDisplayed(boolean displayStatus) {
//		if(!canVisualiseProperty()) throw new IllegalStateException("This property is not visualisable");
//
//		if(!isDisplayed()) {
//			createNewLabel();
//		}
//		else {
//			removeLabel();
//		}
//	}
	
//	@Override
//	public void setLabel(ILabelAttribute label){
//		this.labelAttribute = label;
//	}
	
	protected AnnotationPropertyChangeHelper getListenerHandler(){
		return this.listenerHandler;
	}

//	private void createNewLabel() {
//		if(this.labelAttribute == null){
//			ICompoundNodeFactory fact = this.owner.getCurrentElement().getChildCompoundGraph().nodeFactory();
//			IRootAttribute rootAttribute = ((ICanvasElementAttribute)this.owner.getCurrentElement().getAttribute()).getRootAttribute();
//			ILabelAttributeFactory labelAttFact = rootAttribute.labelAttributeFactory();
//			labelAttFact.setDestinationAttribute(owner);
//			labelAttFact.setProperty(this);
//			fact.setAttributeFactory(labelAttFact);
//			ICompoundNode newLabelNode = fact.createNode();
//			this.labelAttribute = (ILabelAttribute)newLabelNode.getAttribute();
//		}
//		else {
//			this.labelAttribute.getCurrentElement().markRemoved(false);
//		}
//	}
//	
//	private void removeLabel() {
//		this.labelAttribute.getCurrentElement().markRemoved(true);
//	}
	
	@Override
	public IAnnotatedObject getOwner() {
		return this.owner;
	}
	
//	@Override
//	public ILabelAttribute getLabel() {
//		ILabelAttribute retVal = this.labelAttribute;
//		if(retVal != null && retVal.getCurrentElement().isRemoved()){
//			retVal = null;
//		}
//		return retVal;
//	}

	@Override
	public final void addChangeListener(IAnnotationPropertyChangeListener listener) {
		this.listenerHandler.addChangeListener(listener);
	}

	public final void firePropertyChange(IAnnotationPropertyChangeEvent evt) {
		this.listenerHandler.firePropertyChange(evt);
	}

	public final List<IAnnotationPropertyChangeListener> getListeners() {
		return this.listenerHandler.getListeners();
	}

	@Override
	public final Iterator<IAnnotationPropertyChangeListener> listenerIterator() {
		return this.listenerHandler.listenerIterator();
	}

	@Override
	public final void removeChangeListener(IAnnotationPropertyChangeListener listener) {
		this.listenerHandler.removeChangeListener(listener);
	}
	
	@Override
	public String toString(){
		StringBuilder builder = new StringBuilder(this.getClass().getSimpleName());
		builder.append("(");
		builder.append("owner=");
		builder.append(this.getOwner());
		builder.append("defn=");
		builder.append(this.getDefinition());
		builder.append(",value=");
		builder.append(this.getValue());
		builder.append(")");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.owner == null) ? 0 : this.owner.hashCode());
		result = prime * result + ((this.getDefinition() == null) ? 0 : this.getDefinition().hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof IAnnotationProperty)) {
			return false;
		}
		IAnnotationProperty other = (IAnnotationProperty) obj;
		if (this.owner == null) {
			if (other.getOwner() != null) {
				return false;
			}
		} else if (!this.owner.equals(other.getOwner())) {
			return false;
		}
		if (this.getDefinition() == null) {
			if (other.getDefinition() != null) {
				return false;
			}
		} else if (!this.getDefinition().equals(other.getDefinition())) {
			return false;
		}
		return true;
	}
}
