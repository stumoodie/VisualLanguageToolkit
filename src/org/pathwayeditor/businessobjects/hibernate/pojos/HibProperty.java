/*
Copyright 2009, Court of the University of Edinburgh

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License. 
*/
package org.pathwayeditor.businessobjects.hibernate.pojos;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import org.pathwayeditor.businessobjects.drawingprimitives.ILabelAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.ILabelNode;
import org.pathwayeditor.businessobjects.drawingprimitives.ILabelNodeFactory;
import org.pathwayeditor.businessobjects.drawingprimitives.IModel;
import org.pathwayeditor.businessobjects.drawingprimitives.ISelectionFactory;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.IPropertyChangeEvent;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.IPropertyChangeListener;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.ListenablePropertyChangeItem;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationProperty;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyDefinition;

/**
 * Property generated by hbm2java
 */
public abstract class HibProperty implements IAnnotationProperty, Serializable {
	private static final long serialVersionUID = -1996477907215294788L;

	private static final String DEFAULT_DISPLAY_NAME = "A Property";
	private Long id = null;
	private String name;
	private String displayName = DEFAULT_DISPLAY_NAME;
	private HibLabelAttribute labelAttribute = null;
	private HibAnnotatedCanvasAttribute owner;
	private final ListenablePropertyChangeItem listenerHandler = new ListenablePropertyChangeItem();


	/**
	 * Constructor should only be used by hiberate.
	 * @deprecated Application code should not use this constructor. Use one of the other constructors instead.
	 */
	protected HibProperty() {
	}

	protected HibProperty(HibAnnotatedCanvasAttribute owner, IPropertyDefinition propDefn) {
		this.owner = owner;
		this.name = propDefn.getName();
	}

	protected HibProperty(HibAnnotatedCanvasAttribute newOwner, HibProperty other) {
		this.owner = newOwner;
		this.name = other.name;
	}
	
	public Long getId() {
		return this.id;
	}

	@SuppressWarnings("unused")
	private void setId(Long id) {
		this.id = id;
	}

	public abstract void setPropertyDefinition(IPropertyDefinition definition);

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	public String getDisplayName() {
		return this.displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public boolean isDisplayed() {
		return this.labelAttribute != null;
	}
	
	void setLabel(HibLabelAttribute label){
		this.labelAttribute = label;
	}
	
	public void setDisplayed(boolean displayStatus) {
		if(!canVisualiseProperty()) throw new IllegalStateException("This property is not visualisable");

		if(displayStatus && this.labelAttribute == null) {
			createNewLabel();
		}
		else if(!displayStatus && this.labelAttribute != null){
			removeLabel();
		}
	}
	
	protected ListenablePropertyChangeItem getListenerHandler(){
		return this.listenerHandler;
	}

	private void createNewLabel() {
		ILabelNodeFactory fact = this.owner.getLabelSubModel().labelNodeFactory();
		fact.setProperty(this);
		ILabelNode newLabelNode = fact.createLabel();
		this.labelAttribute = (HibLabelAttribute)newLabelNode.getAttribute();
	}
	
	private void removeLabel() {
		IModel model = this.labelAttribute.getCurrentDrawingElement().getModel();
		ISelectionFactory fact = model.newSelectionFactory();
		fact.addDrawingNode(this.labelAttribute.getCurrentDrawingElement());
		model.removeSubgraph(fact.createGeneralSelection());
		setLabel(null);
	}
	
	void setOwner(HibAnnotatedCanvasAttribute owner) {
		this.owner = owner;
	}
	
	public HibAnnotatedCanvasAttribute getOwner() {
		return this.owner;
	}
	
	void setDisplayedLabel(HibLabelAttribute labelAttribute) {
		this.labelAttribute = labelAttribute;
		if(this.labelAttribute != null){
			this.labelAttribute.setVisualisableProperty(this);
		}
	}
	
	public ILabelAttribute getDisplayedLabel() {
		return this.labelAttribute;
	}

	public final void addChangeListener(IPropertyChangeListener listener) {
		this.listenerHandler.addChangeListener(listener);
	}

	public final void firePropertyChange(IPropertyChangeEvent evt) {
		this.listenerHandler.firePropertyChange(evt);
	}

	public final List<IPropertyChangeListener> getListeners() {
		return this.listenerHandler.getListeners();
	}

	public final Iterator<IPropertyChangeListener> listenerIterator() {
		return this.listenerHandler.listenerIterator();
	}

	public final void removeChangeListener(IPropertyChangeListener listener) {
		this.listenerHandler.removeChangeListener(listener);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
		result = prime * result + ((this.owner == null) ? 0 : this.owner.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof HibProperty))
			return false;
		HibProperty other = (HibProperty) obj;
		if (this.name == null) {
			if (other.name != null)
				return false;
		} else if (!this.name.equals(other.name))
			return false;
		if (this.owner == null) {
			if (other.owner != null)
				return false;
		} else if (!this.owner.equals(other.owner))
			return false;
		return true;
	}
}
