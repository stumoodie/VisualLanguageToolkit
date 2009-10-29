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
import org.pathwayeditor.businessobjects.drawingprimitives.IBendPoint;
import org.pathwayeditor.businessobjects.drawingprimitives.IDrawingElement;
import org.pathwayeditor.businessobjects.drawingprimitives.ILabelSubModel;
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkTerminus;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LinkEndDecoratorShape;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LinkTermType;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.ICanvasAttributePropertyChangeListener;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.ListenablePropertyChangeItem;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.CanvasAttributePropertyChange;
import org.pathwayeditor.businessobjects.hibernate.helpers.InconsistentNotationDefinitionException;
import org.pathwayeditor.businessobjects.typedefn.ILinkTerminusDefaults;
import org.pathwayeditor.businessobjects.typedefn.ILinkTerminusDefinition;
import org.pathwayeditor.businessobjects.typedefn.IObjectType;
import org.pathwayeditor.figure.geometry.Dimension;
import org.pathwayeditor.figure.geometry.Point;

public class HibLinkTerminus extends HibAnnotatedCanvasAttribute implements ILinkTerminus, Serializable {
	private transient final Logger logger = Logger.getLogger(this.getClass());
	private final static int DEF_TERM_DEC_WIDTH = 0;
	private final static int DEF_TERM_DEC_HEIGHT = 0;
	private final static int DEF_END_DEC_WIDTH = 0;
	private final static int DEF_END_DEC_HEIGHT = 0;
	private final static int DEFAULT_OFFSET = 0;
	private final static RGB DEFAULT_TERM_COLOUR = new RGB(0, 0, 0);
	
	private HibLinkAttribute linkAttribute = null;
	private LinkTermType linkTermType = null;
	private double offset = DEFAULT_OFFSET;
	private LinkEndDecoratorShape endDecoratorType = null;
	private transient Dimension endDecSize = new Dimension(DEF_END_DEC_WIDTH, DEF_END_DEC_HEIGHT);
    private transient RGB terminusColour = DEFAULT_TERM_COLOUR;
    private transient Dimension terminusSize = new Dimension(DEF_TERM_DEC_WIDTH, DEF_TERM_DEC_HEIGHT);
    private transient ILinkTerminusDefinition terminusDefn = null;
    private transient Point location = Point.ORIGIN;
	private final transient ListenablePropertyChangeItem eventHandler = new ListenablePropertyChangeItem(this);

	/**
	 * Default constructor to only be used by hibernate.
	 * @deprecated use another constructor to initialise this class in application code.
	 */
	HibLinkTerminus() {
		super();
	}

	public HibLinkTerminus(HibCanvas canvas, int creationSerial, HibLinkAttribute hibLinkAttribute, LinkTermType linkTermType, ILinkTerminusDefinition terminusDefn) {
		super(canvas, creationSerial, terminusDefn.getDefaultAttributes());
		this.linkAttribute = hibLinkAttribute;
		this.linkTermType = linkTermType;
		this.terminusDefn = terminusDefn;
		setDefaults(terminusDefn.getDefaultAttributes());
	}

	/**
	 * Constructs a new object with attributes copied from another one.
	 * @param hibLinkAttribute
	 * @param other
	 */
	public HibLinkTerminus(HibCanvas canvas, int creationSerial, HibLinkAttribute hibLinkAttribute, HibLinkTerminus other) {
		super(canvas, creationSerial, other);
		this.linkAttribute = hibLinkAttribute;
		this.linkTermType = other.getLinkTermType();
		this.endDecoratorType = other.getEndDecoratorType();
		this.endDecSize = other.getEndSize();
		this.offset = other.getGap();
		this.terminusColour = other.getTerminusColour();
		this.terminusSize = other.getTerminusSize();
		this.terminusDefn = other.terminusDefn;
		this.location = other.getLocation();
	}

	/**
	 * @param linkTerminusDefaults
	 */
	private void setDefaults(ILinkTerminusDefaults linkTerminusDefaults) {
		this.setEndDecoratorType(linkTerminusDefaults.getEndDecoratorType());
		this.setEndSize(linkTerminusDefaults.getEndSize());
		this.setGap(linkTerminusDefaults.getGap());
	}

//	public void injectLinkTerminusDefaults(ILinkTerminusDefinition terminusDefn) throws InconsistentNotationDefinitionException {
//		super.injectPropertyDefinitions(terminusDefn.getDefaultAttributes());
//		if(terminusDefn != null && (!terminusDefn.getOwningObjectType().equals(linkAttribute.getObjectType())
//				|| !terminusDefn.getLinkEndCode().equals(this.linkTermType))){
//			throw new IllegalArgumentException("terminusDefn must belong to the same object type as the link owning this terminus and be for the correct link terminus type.");
//		}
//		this.terminusDefn = terminusDefn;
//	}
	
	void setOwningLink(HibLinkAttribute hibLinkAttribute) {
		this.linkAttribute = hibLinkAttribute;
	}

	public LinkTermType getLinkTermType() {
		return this.linkTermType;
	}

	public void setLinkTermType(LinkTermType linkTermType) {
		if(linkTermType == null) throw new IllegalArgumentException("linkTermType cannot be null");
		
		this.linkTermType = linkTermType;
	}

	public LinkEndDecoratorShape getEndDecoratorType() {
		return this.endDecoratorType;
	}

	public void setEndDecoratorType(LinkEndDecoratorShape decoratorType) {
		if (decoratorType == null)
			throw new IllegalArgumentException("Decorator type cannot be null.");
		
		LinkEndDecoratorShape oldValue = this.endDecoratorType;
		this.endDecoratorType = decoratorType;
		this.eventHandler.notifyPropertyChange(CanvasAttributePropertyChange.END_DECORATOR_TYPE, oldValue, this.endDecoratorType);
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

    public int getTermDecRed() {
        return this.terminusColour.getRed();
    }
    
    public void setTermDecRed(int red) {
        this.terminusColour = this.terminusColour.newRed(red);
    }
    public int getTermDecGreen() {
        return this.terminusColour.getGreen();
    }
    
    public void setTermDecGreen(int green) {
        this.terminusColour = this.terminusColour.newGreen(green);
    }
    
    public int getTermDecBlue() {
        return this.terminusColour.getBlue();
    }
    
    public void setTermDecBlue(int blue) {
        this.terminusColour = this.terminusColour.newBlue(blue);
    }
    
    
    public double getTermDecWidth() {
        return this.terminusSize.getWidth();
    }
    
    public void setTermDecWidth(double width) {
        this.terminusSize = this.terminusSize.newWidth(width);
    }
    
    public double getTermDecHeight() {
        return this.terminusSize.getHeight();
    }
    
    public void setTermDecHeight(double height) {
        this.terminusSize = this.terminusSize.newHeight(height);
    }

	// The following is extra code specified in the hbm.xml files

	private static final long serialVersionUID = -4462637156010353035L;
	private static final int FIRST_PB_IDX = 0;


	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.pathwayeditor.businessobjects.drawingprimitives.ILinkTerminus#getGap
	 * ()
	 */
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
	public void setGap(double newGap) {
		if (newGap < 0)
			throw new IllegalArgumentException("newGap cannot be negative");

		double oldValue = this.offset;
		this.offset = (short) newGap;
		this.eventHandler.notifyPropertyChange(CanvasAttributePropertyChange.TERMINUS_GAP, oldValue, this.offset);
	}

	public HibLinkAttribute getOwningLink() {
		return this.linkAttribute;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.pathwayeditor.businessobjects.drawingprimitives.ILinkTerminus#getEndSize
	 * ()
	 */
	public Dimension getEndSize() {
		return this.endDecSize;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.pathwayeditor.businessobjects.drawingprimitives.ILinkTerminus#
	 * getTerminusColor()
	 */
	public RGB getTerminusColour() {
		return this.terminusColour;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.pathwayeditor.businessobjects.drawingprimitives.ILinkTerminus#
	 * getTerminusSize()
	 */
	public Dimension getTerminusSize() {
		return this.terminusSize;
	}

	public void setEndSize(Dimension size) {
		if(size == null) throw new IllegalArgumentException("the end size cannot be null");
		
		Dimension oldValue = this.endDecSize;
		this.endDecSize = size;
		this.eventHandler.notifyPropertyChange(CanvasAttributePropertyChange.END_DECORATOR_SIZE, oldValue, this.endDecSize);
	}

	public void setTerminusColour(RGB newColour) {
		if(newColour == null) throw new IllegalArgumentException("newColour cannot be null");
		
		RGB oldValue = this.terminusColour;
		this.terminusColour = newColour;
		this.eventHandler.notifyPropertyChange(CanvasAttributePropertyChange.TERMINUS_DEC_COLOUR, oldValue, this.terminusColour);
	}


	public void setTerminusSize(Dimension newSize) {
		if(newSize == null) throw new IllegalArgumentException("newSize cannot be null");
		
		Dimension oldValue = this.terminusSize;
		this.terminusSize = newSize;
		this.eventHandler.notifyPropertyChange(CanvasAttributePropertyChange.TERMINUS_SIZE, oldValue, this.terminusSize);
	}

	public ILinkTerminusDefinition getDefinition() {
		ILinkTerminusDefinition retVal = this.getOwningLink().getObjectType()
				.getTargetTerminusDefinition();
		if (this.getLinkTermType() == LinkTermType.SOURCE) {
			retVal = this.getOwningLink().getObjectType()
					.getSourceTerminusDefinition();
		}
		return retVal;
	}

	public boolean isValid() {
		ILinkAttribute owningLink = this.getOwningLink(); 
		boolean owningLinkValid = owningLink != null;
		ILinkTerminusDefinition defn = this.getDefinition();
		LinkTermType termType = this.getLinkTermType();
		boolean definitionValid = defn != null && owningLink != null && defn.getOwningObjectType().equals(this.getOwningLink().getObjectType())
			&& defn.getLinkEndCode().equals(termType);
		boolean validProperties = this.arePropertiesValid(defn.getDefaultAttributes());
		boolean retVal = owningLinkValid && definitionValid && validProperties;
		if(!retVal) {
			logger.error("Terminus invalid: terminus=" + this + "terminusDefn is null or does not belong to the same object type as the link owning this terminus or is not of the correct link terminus type.");
		}
		return retVal;
/*	public boolean isValid() {
		boolean retVal = true;
		if(this.linkAttribute != null && this.terminusDefn != null && terminusDefn.getOwningObjectType().equals(linkAttribute.getObjectType())
					&& terminusDefn.getLinkEndCode().equals(this.linkTermType)){
			Iterator<IPropertyDefinition> propDefnIter = terminusDefn.getDefaultAttributes().propertyDefinitionIterator();
			int propCntr = 0;
			while(propDefnIter.hasNext()) {
				IPropertyDefinition definition = propDefnIter.next();
				HibProperty property = this.hibProperties.get(definition.getName());
				if(property==null) {
					logger.error("The link terminus definition has property definitions which have no matching property in this Shape Attribute");
					retVal = false;
					break;
				}
				property.setPropertyDefinition(definition);
				propCntr++;
			}
			if(retVal && propCntr != this.hibProperties.size()) {
				logger.error("Link terminus definition inconsistent with stored terminus properties. Cannot find definitions for some properties");
			}
		}
		else {
			logger.error("Terminus invalid: terminus=" + this + "terminusDefn is null or does not belong to the same object type as the link owning this terminus or is not of the correct link terminus type.");
			retVal = false;
		}
		return retVal;
	}*/

	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.listeners.IPropertyChangeListenee#addChangeListener(org.pathwayeditor.businessobjects.drawingprimitives.listeners.IPropertyChangeListener)
	 */
	public void addChangeListener(ICanvasAttributePropertyChangeListener listener) {
		this.eventHandler.addChangeListener(listener);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.listeners.IPropertyChangeListenee#listenerIterator()
	 */
	public Iterator<ICanvasAttributePropertyChangeListener> listenerIterator() {
		return this.eventHandler.listenerIterator();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.listeners.IPropertyChangeListenee#removeChangeListener(org.pathwayeditor.businessobjects.drawingprimitives.listeners.IPropertyChangeListener)
	 */
	public void removeChangeListener(ICanvasAttributePropertyChangeListener listener) {
		this.eventHandler.removeChangeListener(listener);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICanvasAttribute#getCurrentDrawingElement()
	 */
	public IDrawingElement getCurrentDrawingElement() {
		return this.linkAttribute.getCurrentDrawingElement();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICanvasAttribute#getObjectType()
	 */
	public IObjectType getObjectType() {
		return this.linkAttribute.getObjectType();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.hibernate.pojos.HibCanvasAttribute#getHibObjectType()
	 */
	@Override
	public HibObjectType getHibObjectType() {
		// no persisted object type
		return null;
	}

	double getXPosition(){
		return this.location.getX();
	}
	
	void setXPosition(double newX){
		this.location = this.location.newX(newX);
	}
	
	double getYPosition(){
		return this.location.getY();
	}
	
	void setYPosition(double newY){
		this.location = this.location.newY(newY);
	}
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILinkTerminus#getLocation()
	 */
	public Point getLocation() {
		return this.location;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILinkTerminus#setLocation(org.pathwayeditor.businessobjects.drawingprimitives.attributes.Location)
	 */
	public void setLocation(Point newLocation) {
		if(!this.location.equals(newLocation)){
			Point oldLocation = this.location;
			this.location = newLocation;
			this.eventHandler.notifyPropertyChange(CanvasAttributePropertyChange.LOCATION, oldLocation, this.location);
		}
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotatedObject#getLabelSubModel()
	 */
	public ILabelSubModel getLabelSubModel() {
		return this.getOwningLink().getLabelSubModel();
	}

	public Point getReferencePoint() {
		Point retVal = null;
		if(this.linkTermType.equals(LinkTermType.SOURCE)){
			retVal = getSrcReferencePoint();
		}
		else{
			retVal = getTgtReferencePoint();
		}
		return retVal;
	}

	private Point getSrcReferencePoint(){
		ILinkAttribute linkAtt = this.getOwningLink();
		Point retVal = null;
		if(linkAtt.numBendPoints() > 0){
			IBendPoint bp = linkAtt.getBendPoint(FIRST_PB_IDX);
			retVal = bp.getLocation();
		}
		else{
			retVal = linkAtt.getTargetTerminus().getLocation();
		}
		return retVal;
	}

	private Point getTgtReferencePoint(){
		ILinkAttribute linkAtt = this.getOwningLink();
		Point retVal = null;
		int numBendPoints = linkAtt.numBendPoints(); 
		if(numBendPoints > 0){
			IBendPoint bp = linkAtt.getBendPoint(numBendPoints - 1);
			retVal = bp.getLocation();
		}
		else{
			retVal = linkAtt.getSourceTerminus().getLocation();
		}
		return retVal;
	}

	public boolean areListenersEnabled() {
		return this.eventHandler.areListenersEnabled();
	}

	public void setListenersEnabled(boolean enabled) {
		this.eventHandler.setListenersEnabled(enabled);
	}

	public void setTerminusDefinition(ILinkTerminusDefinition terminusDefn) {
		this.terminusDefn = terminusDefn;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.hibernate.pojos.HibCanvasAttribute#injectObjectType(org.pathwayeditor.businessobjects.hibernate.pojos.IObjectTypeInjector)
	 */
	@Override
	public void injectObjectType(IObjectTypeInjector injector) throws InconsistentNotationDefinitionException {
		injector.inject(this);
	}
}
