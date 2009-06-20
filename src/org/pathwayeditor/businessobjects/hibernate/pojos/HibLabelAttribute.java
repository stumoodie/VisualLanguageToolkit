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

import org.apache.log4j.Logger;
import org.pathwayeditor.businessobjects.drawingprimitives.ILabelAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.ILabelNode;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.Bounds;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.Location;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.Size;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.IPropertyChangeListener;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.ListenablePropertyChangeItem;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.PropertyChange;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyDefinition;
import org.pathwayeditor.businessobjects.hibernate.helpers.InconsistentNotationDefinitionException;
import org.pathwayeditor.businessobjects.typedefn.ILabelAttributeDefaults;
import org.pathwayeditor.businessobjects.typedefn.INodeObjectType;
import org.pathwayeditor.businessobjects.typedefn.IObjectType;
import org.pathwayeditor.figure.geometry.IConvexHull;

public class HibLabelAttribute extends HibCanvasAttribute implements Serializable, ILabelAttribute {
	private final Logger logger = Logger.getLogger(this.getClass()); 
	private static final long serialVersionUID = -2354270083525870259L;

	private static final int DEFAULT_X = 0;
	private static final int DEFAULT_Y = 0;
	private static final int DEFAULT_HEIGHT = 0;
	private static final int DEFAULT_WIDTH = 0;

	private Location position = new Location(DEFAULT_X, DEFAULT_Y);
	private Size size = new Size(DEFAULT_WIDTH, DEFAULT_HEIGHT);
	private HibProperty visualisableProperty;
	private RGB background;
	private HibLabelNode labelNode;
	private transient INodeObjectType objectType;
	private transient IConvexHull convexHull = null;
	private transient final ListenablePropertyChangeItem listenablePropertyChangeItem = new ListenablePropertyChangeItem();

	/**
	 * Default constructor that should only be used by hibernate.
	 * @deprecated should not be used by hibernate code, use one of the other constructors. 
	 */
	HibLabelAttribute() {
		size = new Size (0 , 0) ;
		position = new Location ( 0 , 0 ) ;
		background = new RGB ( 0 , 0 ,0 ) ;
	}

	public HibLabelAttribute(HibCanvas hibCanvas, int creationSerial, HibProperty property,	ILabelAttributeDefaults labelDefaults) {
		super(hibCanvas, creationSerial);
		this.visualisableProperty = property;
		this.visualisableProperty.setDisplayedLabel(this);
		this.objectType = new LabelObjectType(hibCanvas.getNotationSubsystem().getSyntaxService());
		populateDefaults(labelDefaults);
	}

	public HibLabelAttribute(HibCanvas hibCanvas, int creationSerial, ILabelAttribute otherAttribute, HibProperty copiedProperty) {
		super(hibCanvas, creationSerial);
		this.visualisableProperty = copiedProperty;
		this.visualisableProperty.setDisplayedLabel(this);
		this.position = otherAttribute.getLocation();
		this.size = otherAttribute.getSize();
		this.background = otherAttribute.getBackgroundColor();
		this.objectType = otherAttribute.getObjectType();
	}

	private void populateDefaults(ILabelAttributeDefaults labelDefaults) {
//		this.setSize(labelDefaults.getSize());
		this.size = new Size(0, 0);
		this.setBackgroundColor(labelDefaults.getFillColour());	}

	public Location getPosition() {
		return this.position;
	}

//	public HibLabelNode getLabelNode() {
//		return this.labelNode;
//	}

	void setCurrentDrawingElement(HibLabelNode node) {
		this.labelNode = node;
	}

	public void setXPosition(int XPosition) {
		this.position = this.position.newX(XPosition);
	}

	public int getXPosition() {
		return this.position.getX();
	}

	public int getYPosition() {
		return this.position.getY();
	}

	public void setYPosition(int YPosition) {
		this.position = this.position.newY(YPosition);
	}

	public int getWidth() {
		return this.size.getWidth();
	}

	public void setWidth(int width) {
		this.size = this.size.newWidth(width);
	}

	public int getHeight() {
		return this.size.getHeight();
	}

	public void setHeight(int height) {
		this.size = this.size.newHeight(height);
	}

	public HibProperty getVisualisableProperty() {
		return this.visualisableProperty;
	}

	public void setVisualisableProperty(HibProperty visualisableProperty) {
		this.visualisableProperty = visualisableProperty;
	}

	public int getBackgroundRed() {
		return this.background.getRed();
	}

	public void setBackgroundRed(int backgroundRed) {
		this.background = this.background.newRed(backgroundRed);
	}

	public int getBackgroundGreen() {
		return this.background.getGreen();
	}

	public void setBackgroundGreen(int backgroundGreen) {
		this.background = this.background.newGreen(backgroundGreen);
	}

	public int getBackgroundBlue() {
		return this.background.getBlue();
	}

	public void setBackgroundBlue(int backgroundBlue) {
		this.background = this.background.newBlue(backgroundBlue);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.pathwayeditor.businessobjects.drawingprimitives.ILabelAttribute#
	 * getLocation()
	 */
	public Location getLocation() {
		return this.position;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.pathwayeditor.businessobjects.drawingprimitives.ILabelAttribute#
	 * getProperty()
	 */
	public HibProperty getProperty() {
		return this.visualisableProperty;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.pathwayeditor.businessobjects.drawingprimitives.ILabelAttribute#getSize
	 * ()
	 */
	public Size getSize() {
		return this.size;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.pathwayeditor.businessobjects.drawingprimitives.ILabelAttribute#
	 * getBackgroundColor()
	 */
	public RGB getBackgroundColor() {
		return this.background;
	}

	public void setBackgroundColor(RGB color) {
		if (color == null)
			throw new IllegalArgumentException("Color cannot be null.");

		RGB oldColour = this.background;
		this.background = color;
		this.listenablePropertyChangeItem.notifyPropertyChange(PropertyChange.BACKGROUND_COLOUR, oldColour, this.background);
	}
	
	public void setObjectType ( INodeObjectType nodeObjectType)
	{
		this.objectType = nodeObjectType ;
	}

	public void setLocation(Location location) {
		if (location == null)
			throw new IllegalArgumentException("location cannot be null.");

		if(!this.position.equals(location)){
			Location oldValue = this.position;
			this.position = location;
			this.listenablePropertyChangeItem.notifyPropertyChange(PropertyChange.LOCATION, oldValue, this.position);
		}
	}

	public void setSize(Size size) {
		if (size == null)
			throw new IllegalArgumentException("size cannot be null.");

		if(!this.size.equals(size)){
			Size oldValue = this.size;
			this.size = size;
			this.listenablePropertyChangeItem.notifyPropertyChange(PropertyChange.SIZE, oldValue, this.size);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.pathwayeditor.businessobjects.drawingprimitives.ICanvasAttribute#
	 * getObjectType()
	 */
	public INodeObjectType getObjectType() {
		return this.objectType;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICanvasAttribute#hasProperty(org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyDefinition)
	 */
	public boolean hasProperty(IPropertyDefinition property) {
		return this.visualisableProperty.getDefinition().equals(property);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.listeners.IPropertyChangeListenee#addChangeListener(org.pathwayeditor.businessobjects.drawingprimitives.listeners.IPropertyChangeListener)
	 */
	public void addChangeListener(IPropertyChangeListener listener) {
		this.listenablePropertyChangeItem.addChangeListener(listener);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.listeners.IPropertyChangeListenee#listenerIterator()
	 */
	public Iterator<IPropertyChangeListener> listenerIterator() {
		return this.listenablePropertyChangeItem.listenerIterator();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.listeners.IPropertyChangeListenee#removeChangeListener(org.pathwayeditor.businessobjects.drawingprimitives.listeners.IPropertyChangeListener)
	 */
	public void removeChangeListener(IPropertyChangeListener listener) {
		this.listenablePropertyChangeItem.removeChangeListener(listener);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILabelAttribute#getCurrentDrawingElement()
	 */
	public ILabelNode getCurrentDrawingElement() {
		return this.labelNode;
	}

	public boolean isValid() {
		boolean objectTypeTest = this.getObjectType() != null;
		boolean labelNodeTest = this.getCurrentDrawingElement() != null
			&& this.getCurrentDrawingElement().getAttribute().equals(this);
		// valid to have no visualisable label set if the label node has been removed
		boolean propertySetTest = this.getVisualisableProperty() != null
			|| (this.getVisualisableProperty() == null && ((HibLabelNode)this.getCurrentDrawingElement()).isRemoved());
		if(!objectTypeTest || !labelNodeTest || !propertySetTest) {
			logger.error("attribute=" + this + " objectType set=" + objectTypeTest
					+ ", labelnode set and points to this attribute=" + labelNodeTest
					+ ", propertySet=" + propertySetTest);
		}
		return propertySetTest && labelNodeTest && propertySetTest;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.hibernate.pojos.HibCanvasAttribute#injectObjectType(org.pathwayeditor.businessobjects.typedefn.IObjectType)
	 */
	@Override
	public void injectObjectType(IObjectType objectType) throws InconsistentNotationDefinitionException {
		this.objectType = (INodeObjectType)objectType;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.hibernate.pojos.HibCanvasAttribute#getHibObjectType()
	 */
	// no persisted object type so return null
	@Override
	public HibObjectType getHibObjectType() {
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IDrawingNodeAttribute#getBounds()
	 */
	public Bounds getBounds() {
		Location topLhCorner = new Location(this.position.getX() - (this.size.getWidth()/2),
				this.position.getY() - (this.size.getHeight()/2));
		return new Bounds(topLhCorner, this.size);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IDrawingNodeAttribute#setBounds(org.pathwayeditor.businessobjects.drawingprimitives.attributes.Bounds)
	 */
	public void setBounds(Bounds newBounds) {
		Location centre = new Location(newBounds.getOrigin().getX() + (newBounds.getSize().getWidth()/2),
				newBounds.getOrigin().getY() + (newBounds.getSize().getHeight()/2));
		this.setLocation(centre);
		this.setSize(newBounds.getSize());
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IDrawingNodeAttribute#getConvexHull()
	 */
	public IConvexHull getConvexHull() {
		return this.convexHull;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IDrawingNodeAttribute#setConvexHull(org.pathwayeditor.figure.geometry.IConvexHull)
	 */
	public void setConvexHull(IConvexHull newHull) {
		this.convexHull = newHull;
	}

}
