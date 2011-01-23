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
package org.pathwayeditor.businessobjects.impl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.pathwayeditor.businessobjects.drawingprimitives.ILinkAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkTerminus;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LinkEndDecoratorShape;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LinkTermType;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.ILinkTerminusChangeListener;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.ILinkTerminusTranslationEvent;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.ILinkTerminusValueChangeEvent;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.LinkTerminusChangeType;
import org.pathwayeditor.businessobjects.typedefn.ILinkTerminusDefaults;
import org.pathwayeditor.businessobjects.typedefn.ILinkTerminusDefinition;
import org.pathwayeditor.figure.geometry.Dimension;
import org.pathwayeditor.figure.geometry.Point;

public class LinkTerminus implements ILinkTerminus {
	private final static int DEF_END_DEC_WIDTH = 0;
	private final static int DEF_END_DEC_HEIGHT = 0;
	private final static int DEFAULT_OFFSET = 0;
	
	private final ILinkAttribute linkAttribute;
	private final LinkTermType linkTermType;
	private double offset = DEFAULT_OFFSET;
	private LinkEndDecoratorShape endDecoratorType = null;
	private Dimension endDecSize = new Dimension(DEF_END_DEC_WIDTH, DEF_END_DEC_HEIGHT);
    private Point location = Point.ORIGIN;
    private final List<ILinkTerminusChangeListener> listeners = new LinkedList<ILinkTerminusChangeListener>(); 

	public LinkTerminus(ILinkAttribute link, LinkTermType linkTermType, ILinkTerminusDefinition terminusDefn) {
		this.linkAttribute = link;
		this.linkTermType = linkTermType;
		setDefaults(terminusDefn.getDefaultAttributes());
	}

	/**
	 * Constructs a new object with attributes copied from another one.
	 * @param hibLinkAttribute
	 * @param other
	 */
	public LinkTerminus(ILinkAttribute hibLinkAttribute, ILinkTerminus other) {
		this.linkAttribute = hibLinkAttribute;
		this.linkTermType = other.getLinkTermType();
		this.endDecoratorType = other.getEndDecoratorType();
		this.endDecSize = other.getEndSize();
		this.offset = other.getGap();
		this.location = other.getLocation();
	}

	/**
	 * @param linkTerminusDefaults
	 */
	private void setDefaults(ILinkTerminusDefaults linkTerminusDefaults) {
		this.endDecoratorType = linkTerminusDefaults.getEndDecoratorType();
		this.endDecSize = linkTerminusDefaults.getEndSize();
		this.offset = linkTerminusDefaults.getGap();
	}

	@Override
	public LinkTermType getLinkTermType() {
		return this.linkTermType;
	}

	@Override
	public LinkEndDecoratorShape getEndDecoratorType() {
		return this.endDecoratorType;
	}

	@Override
	public void setEndDecoratorType(LinkEndDecoratorShape decoratorType) {
		if (decoratorType == null)
			throw new IllegalArgumentException("Decorator type cannot be null.");
		
		LinkEndDecoratorShape oldValue = this.endDecoratorType;
		this.endDecoratorType = decoratorType;
		notifyPropertyChange(LinkTerminusChangeType.END_DECORATOR_TYPE, oldValue, this.endDecoratorType);
	}

	private void notifyPropertyChange(final LinkTerminusChangeType changeType, final Object oldValue, final Object newValue) {
		final ILinkTerminusValueChangeEvent e = new ILinkTerminusValueChangeEvent() {
			
			@Override
			public Object getOldValue() {
				return oldValue;
			}
			
			@Override
			public Object getNewValue() {
				return newValue;
			}
			
			@Override
			public ILinkTerminus getChangedLinkTerminus() {
				return LinkTerminus.this;
			}
			
			@Override
			public LinkTerminusChangeType getChangeType() {
				return changeType;
			}
		};
		for(ILinkTerminusChangeListener listener : this.listeners){
			listener.valueChangeEvent(e);
		}
	}

	public double getEndDecWidth() {
		return this.endDecSize.getWidth();
	}

	public void setEndDecWidth(double width) {
		this.endDecSize = this.endDecSize.newWidth(width);
	}

	public double getEndDecHeight() {
		return this.endDecSize.getHeight();
	}

	public void setEndDecHeight(double height) {
		this.endDecSize = this.endDecSize.newHeight(height);
	}

 	// The following is extra code specified in the hbm.xml files

	private static final long serialVersionUID = -4462637156010353035L;


	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.pathwayeditor.businessobjects.drawingprimitives.ILinkTerminus#getGap
	 * ()
	 */
	@Override
	public double getGap() {
		return this.offset;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.pathwayeditor.businessobjects.drawingprimitives.ILinkTerminus#setGap
	 * (int)
	 */
	@Override
	public void setGap(double newGap) {
		if (newGap < 0)
			throw new IllegalArgumentException("newGap cannot be negative");

		double oldValue = this.offset;
		this.offset = (short) newGap;
		notifyPropertyChange(LinkTerminusChangeType.TERMINUS_GAP, oldValue, this.offset);
	}

	@Override
	public ILinkAttribute getOwningLink() {
		return this.linkAttribute;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.pathwayeditor.businessobjects.drawingprimitives.ILinkTerminus#getEndSize
	 * ()
	 */
	@Override
	public Dimension getEndSize() {
		return this.endDecSize;
	}

	@Override
	public void setEndSize(Dimension size) {
		if(size == null) throw new IllegalArgumentException("the end size cannot be null");
		
		Dimension oldValue = this.endDecSize;
		this.endDecSize = size;
		notifyPropertyChange(LinkTerminusChangeType.END_DECORATOR_SIZE, oldValue, this.endDecSize);
	}

	@Override
	public ILinkTerminusDefinition getDefinition() {
		ILinkTerminusDefinition retVal = this.getOwningLink().getObjectType()
				.getTargetTerminusDefinition();
		if (this.getLinkTermType() == LinkTermType.SOURCE) {
			retVal = this.getOwningLink().getObjectType().getSourceTerminusDefinition();
		}
		return retVal;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILinkTerminus#getLocation()
	 */
	@Override
	public Point getLocation() {
		return this.location;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILinkTerminus#setLocation(org.pathwayeditor.businessobjects.drawingprimitives.attributes.Location)
	 */
	@Override
	public void setLocation(Point newLocation) {
		if(!this.location.equals(newLocation)){
			Point oldLocation = this.location;
			this.location = newLocation;
			notifyPropertyChange(LinkTerminusChangeType.LOCATION, oldLocation, this.location);
		}
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILinkTerminus#addLinkTerminusChangeListener(org.pathwayeditor.businessobjects.drawingprimitives.listeners.ILinkTerminusChangeListener)
	 */
	@Override
	public void addLinkTerminusChangeListener(ILinkTerminusChangeListener listener) {
		this.listeners.add(listener);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILinkTerminus#removeLinkTerminusChangeListener(org.pathwayeditor.businessobjects.drawingprimitives.listeners.ILinkTerminusChangeListener)
	 */
	@Override
	public void removeLinkTerminusChangeListener(ILinkTerminusChangeListener listener) {
		this.listeners.remove(listener);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILinkTerminus#getLinkTerminusChangeListeners()
	 */
	@Override
	public List<ILinkTerminusChangeListener> getLinkTerminusChangeListeners() {
		return new ArrayList<ILinkTerminusChangeListener>(this.listeners);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILinkTerminus#translate(org.pathwayeditor.figure.geometry.Point)
	 */
	@Override
	public void translate(Point delta) {
		if(delta.equals(Point.ORIGIN)){
			Point oldLocation = this.location.translate(delta);
			notifyTranslation(delta, oldLocation, this.location);
			notifyPropertyChange(LinkTerminusChangeType.LOCATION, oldLocation, this.location);
		}
	}

	/**
	 * 
	 * @param delta
	 * @param oldLocation
	 * @param newLocation
	 */
	private void notifyTranslation(final Point delta, final Point oldLocation, final Point newLocation) {
		final ILinkTerminusTranslationEvent e = new ILinkTerminusTranslationEvent() {
			
			@Override
			public Point getOldLocation() {
				return oldLocation;
			}
			
			@Override
			public Point getNewLocation() {
				return newLocation;
			}
			
			@Override
			public ILinkTerminus getChangedLinkTerminus() {
				return LinkTerminus.this;
			}
			
			@Override
			public Point getChangeDelta() {
				return delta;
			}
		};
		for(ILinkTerminusChangeListener listener : this.listeners){
			listener.terminusTranslated(e);
		}
	}
}
