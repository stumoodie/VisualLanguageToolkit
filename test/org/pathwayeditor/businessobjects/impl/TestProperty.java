/*
Copyright 2009-2011, Court of the University of Edinburgh
*/

package org.pathwayeditor.businessobjects.impl;

import java.util.Iterator;

import org.pathwayeditor.businessobjects.drawingprimitives.listeners.IAnnotationPropertyChangeListener;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotatedObject;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationProperty;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationPropertyVisitor;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyDefinition;

/**
 * @author Stuart Moodie
 *
 */
public class TestProperty implements IAnnotationProperty {
	private final IAnnotatedObject owner;
	private final Object value;
	private final IPropertyDefinition defn;

	public TestProperty(IAnnotatedObject owner, IPropertyDefinition defn){
		this.owner = owner;
		this.defn = defn;
		this.value = defn.getDefaultValue();
	}
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.listeners.IAnnotationPropertyChangeListenee#addChangeListener(org.pathwayeditor.businessobjects.drawingprimitives.listeners.IAnnotationPropertyChangeListener)
	 */
	@Override
	public void addChangeListener(IAnnotationPropertyChangeListener listener) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.listeners.IAnnotationPropertyChangeListenee#removeChangeListener(org.pathwayeditor.businessobjects.drawingprimitives.listeners.IAnnotationPropertyChangeListener)
	 */
	@Override
	public void removeChangeListener(IAnnotationPropertyChangeListener listener) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.listeners.IAnnotationPropertyChangeListenee#listenerIterator()
	 */
	@Override
	public Iterator<IAnnotationPropertyChangeListener> listenerIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationProperty#getDefinition()
	 */
	@Override
	public IPropertyDefinition getDefinition() {
		return this.defn;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationProperty#getOwner()
	 */
	@Override
	public IAnnotatedObject getOwner() {
		return this.owner;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationProperty#getValue()
	 */
	@Override
	public Object getValue() {
		return value;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationProperty#visit(org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationPropertyVisitor)
	 */
	@Override
	public void visit(IAnnotationPropertyVisitor visitor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.defn == null) ? 0 : this.defn.hashCode());
		result = prime * result + ((this.owner == null) ? 0 : this.owner.hashCode());
		result = prime * result + ((this.value == null) ? 0 : this.value.hashCode());
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
		if (this.defn == null) {
			if (other.getDefinition() != null) {
				return false;
			}
		} else if (!this.defn.equals(other.getDefinition())) {
			return false;
		}
		if (this.owner == null) {
			if (other.getOwner() != null) {
				return false;
			}
		} else if (!this.owner.equals(other.getOwner())) {
			return false;
		}
		if (this.value == null) {
			if (other.getValue() != null) {
				return false;
			}
		} else if (!this.value.equals(other.getValue())) {
			return false;
		}
		return true;
	}

}
