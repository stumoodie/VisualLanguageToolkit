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
import org.pathwayeditor.businessobjects.drawingprimitives.IDrawingElement;
import org.pathwayeditor.businessobjects.drawingprimitives.ILabelSubModel;
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkTerminus;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LinkEndDecoratorShape;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LinkTermType;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.Location;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.PrimitiveShapeType;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.Size;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.IPropertyChangeListener;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.ListenablePropertyChangeItem;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.PropertyChange;
import org.pathwayeditor.businessobjects.graphics.ILabelLocationPolicy;
import org.pathwayeditor.businessobjects.graphics.TerminatorLabelLocationPolicy;
import org.pathwayeditor.businessobjects.hibernate.helpers.InconsistentNotationDefinitionException;
import org.pathwayeditor.businessobjects.typedefn.ILinkTerminusDefaults;
import org.pathwayeditor.businessobjects.typedefn.ILinkTerminusDefinition;
import org.pathwayeditor.businessobjects.typedefn.IObjectType;

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
	private short offset = DEFAULT_OFFSET;
	private LinkEndDecoratorShape endDecoratorType = null;
	private transient Size endDecSize = new Size(DEF_END_DEC_WIDTH, DEF_END_DEC_HEIGHT);
    private PrimitiveShapeType termShapeType = null;
    private transient RGB terminusColour = DEFAULT_TERM_COLOUR;
    private transient Size terminusSize = new Size(DEF_TERM_DEC_WIDTH, DEF_TERM_DEC_HEIGHT);
    private transient ILinkTerminusDefinition terminusDefn = null;
    private transient Location location = Location.ORIGIN;
	private final transient ListenablePropertyChangeItem eventHandler = new ListenablePropertyChangeItem();
	private transient ILabelLocationPolicy labelLocationPolicy; 

	/**
	 * Default constructor to only be used by hibernate.
	 * @deprecated use another constructor to initialise this class in application code.
	 */
	HibLinkTerminus() {
		super();
		this.labelLocationPolicy = new TerminatorLabelLocationPolicy(this);
	}

	public HibLinkTerminus(HibCanvas canvas, int creationSerial, HibLinkAttribute hibLinkAttribute, LinkTermType linkTermType, ILinkTerminusDefinition terminusDefn) {
		super(canvas, creationSerial, terminusDefn.getDefaultAttributes());
		this.linkAttribute = hibLinkAttribute;
		this.linkTermType = linkTermType;
		this.terminusDefn = terminusDefn;
		this.labelLocationPolicy = new TerminatorLabelLocationPolicy(this);
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
		this.termShapeType = other.getTerminusDecoratorType();
		this.location = other.getLocation();
		this.labelLocationPolicy = new TerminatorLabelLocationPolicy(this);
	}

	/**
	 * @param linkTerminusDefaults
	 */
	private void setDefaults(ILinkTerminusDefaults linkTerminusDefaults) {
		this.setEndDecoratorType(linkTerminusDefaults.getEndDecoratorType());
		this.setEndSize(linkTerminusDefaults.getEndSize());
		this.setGap(linkTerminusDefaults.getGap());
		this.setTerminusDecoratorType(linkTerminusDefaults.getTermDecoratorType());
		this.setTerminusColour(linkTerminusDefaults.getTermColour());
		this.setTerminusSize(linkTerminusDefaults.getTermSize());
	}

	public void injectLinkTerminusDefaults(ILinkTerminusDefinition terminusDefn) throws InconsistentNotationDefinitionException {
		super.injectPropertyDefinitions(terminusDefn.getDefaultAttributes());
		if(terminusDefn != null && (!terminusDefn.getOwningObjectType().equals(linkAttribute.getObjectType())
				|| !terminusDefn.getLinkEndCode().equals(this.linkTermType))){
			throw new IllegalArgumentException("terminusDefn must belong to the same object type as the link owning this terminus and be for the correct link terminus type.");
		}
		this.terminusDefn = terminusDefn;
	}
	
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
		this.eventHandler.notifyPropertyChange(PropertyChange.END_DECORATOR_TYPE, oldValue, this.endDecoratorType);
	}

	public int getEndDecWidth() {
		return this.endDecSize.getWidth();
	}

	public void setEndDecWidth(int width) {
		this.endDecSize = this.endDecSize.newWidth(width);
	}

	public int getEndDecHeight() {
		return this.endDecSize.getHeight();
	}

	public void setEndDecHeight(int height) {
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
    
    
    public int getTermDecWidth() {
        return this.terminusSize.getWidth();
    }
    
    public void setTermDecWidth(int width) {
        this.terminusSize = this.terminusSize.newWidth(width);
    }
    
    public int getTermDecHeight() {
        return this.terminusSize.getHeight();
    }
    
    public void setTermDecHeight(int height) {
        this.terminusSize = this.terminusSize.newHeight(height);
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
	public short getGap() {
		return this.offset;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.pathwayeditor.businessobjects.drawingprimitives.ILinkTerminus#setGap
	 * (int)
	 */
	public void setGap(short newGap) {
		if (newGap < 0)
			throw new IllegalArgumentException("newGap cannot be negative");

		short oldValue = this.offset;
		this.offset = (short) newGap;
		this.eventHandler.notifyPropertyChange(PropertyChange.TERMINUS_GAP, oldValue, this.offset);
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
	public Size getEndSize() {
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
	 * getTerminusDecoratorType()
	 */
	public PrimitiveShapeType getTerminusDecoratorType() {
		return this.termShapeType;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.pathwayeditor.businessobjects.drawingprimitives.ILinkTerminus#
	 * getTerminusSize()
	 */
	public Size getTerminusSize() {
		return this.terminusSize;
	}

	public void setEndSize(Size size) {
		if(size == null) throw new IllegalArgumentException("the end size cannot be null");
		
		Size oldValue = this.endDecSize;
		this.endDecSize = size;
		this.eventHandler.notifyPropertyChange(PropertyChange.END_DECORATOR_SIZE, oldValue, this.endDecSize);
	}

	public void setTerminusColour(RGB newColour) {
		if(newColour == null) throw new IllegalArgumentException("newColour cannot be null");
		
		RGB oldValue = this.terminusColour;
		this.terminusColour = newColour;
		this.eventHandler.notifyPropertyChange(PropertyChange.TERMINUS_DEC_COLOUR, oldValue, this.terminusColour);
	}

	public void setTerminusDecoratorType(PrimitiveShapeType shapeObjectType) {
		if(shapeObjectType == null) throw new IllegalArgumentException("shapeObjectType cannot be null");
		
		PrimitiveShapeType oldValue = this.termShapeType;
		this.termShapeType = shapeObjectType;
		this.eventHandler.notifyPropertyChange(PropertyChange.TERMINUS_DECORATOR_TYPE, oldValue, this.termShapeType);
	}

	public void setTerminusSize(Size newSize) {
		if(newSize == null) throw new IllegalArgumentException("newSize cannot be null");
		
		Size oldValue = this.terminusSize;
		this.terminusSize = newSize;
		this.eventHandler.notifyPropertyChange(PropertyChange.TERMINUS_SIZE, oldValue, this.terminusSize);
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
	public void addChangeListener(IPropertyChangeListener listener) {
		this.eventHandler.addChangeListener(listener);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.listeners.IPropertyChangeListenee#listenerIterator()
	 */
	public Iterator<IPropertyChangeListener> listenerIterator() {
		return this.eventHandler.listenerIterator();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.listeners.IPropertyChangeListenee#removeChangeListener(org.pathwayeditor.businessobjects.drawingprimitives.listeners.IPropertyChangeListener)
	 */
	public void removeChangeListener(IPropertyChangeListener listener) {
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

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.hibernate.pojos.HibCanvasAttribute#injectObjectType(org.pathwayeditor.businessobjects.typedefn.IObjectType)
	 */
	@Override
	public void injectObjectType(IObjectType objectType) throws InconsistentNotationDefinitionException {
		// do nothing
	}
	
	
	int getXPosition(){
		return this.location.getX();
	}
	
	void setXPosition(int newX){
		this.location = this.location.newX(newX);
	}
	
	int getYPosition(){
		return this.location.getY();
	}
	
	void setYPosition(int newY){
		this.location = this.location.newY(newY);
	}
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILinkTerminus#getLocation()
	 */
	public Location getLocation() {
		return this.location;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILinkTerminus#setLocation(org.pathwayeditor.businessobjects.drawingprimitives.attributes.Location)
	 */
	public void setLocation(Location newLocation) {
		if(!this.location.equals(newLocation)){
			Location oldLocation = this.location;
			this.location = newLocation;
			this.eventHandler.notifyPropertyChange(PropertyChange.LOCATION, oldLocation, this.location);
		}
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IAnnotatedCanvasAttribute#getLabelLocationPolicy()
	 */
	public ILabelLocationPolicy getLabelLocationPolicy() {
		return this.labelLocationPolicy;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IAnnotatedCanvasAttribute#setLabelLocationPolicy(org.pathwayeditor.businessobjects.graphics.ILabelLocationPolicy)
	 */
	public void setLabelLocationPolicy(ILabelLocationPolicy labelLocationPolicy) {
		this.labelLocationPolicy = labelLocationPolicy;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotatedObject#getLabelSubModel()
	 */
	public ILabelSubModel getLabelSubModel() {
		return this.getOwningLink().getLabelSubModel();
	}
}
