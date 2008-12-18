package org.pathwayeditor.businessobjects.hibernate.pojos;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.log4j.Logger;
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkTerminus;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LinkEndDecoratorShape;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LinkTermType;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.PrimitiveShapeType;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.Size;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.IPropertyChangeListener;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.ListenablePropertyChangeItem;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.PropertyChange;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationProperty;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyBuilder;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyDefinition;
import org.pathwayeditor.businessobjects.hibernate.helpers.InconsistentNotationDefinitionException;
import org.pathwayeditor.businessobjects.hibernate.helpers.PropertyBuilder;
import org.pathwayeditor.businessobjects.hibernate.pojos.graph.IterationCaster;
import org.pathwayeditor.businessobjects.typedefn.ILinkTerminusDefaults;
import org.pathwayeditor.businessobjects.typedefn.ILinkTerminusDefinition;

public class HibLinkTerminus implements ILinkTerminus, Serializable {
	private transient final Logger logger = Logger.getLogger(this.getClass());
	private final static int DEF_TERM_DEC_WIDTH = 0;
	private final static int DEF_TERM_DEC_HEIGHT = 0;
	private final static int DEF_END_DEC_WIDTH = 0;
	private final static int DEF_END_DEC_HEIGHT = 0;
	private final static int DEFAULT_OFFSET = 0;
	private final static RGB DEFAULT_TERM_COLOUR = new RGB(0, 0, 0);
	
	private Long id = null;
	private HibLinkAttribute linkAttribute = null;
	private LinkTermType linkTermType = null;
	private short offset = DEFAULT_OFFSET;
	private LinkEndDecoratorShape endDecoratorType = null;
	private transient Size endDecSize = new Size(DEF_END_DEC_WIDTH, DEF_END_DEC_HEIGHT);
    private PrimitiveShapeType termShapeType = null;
    private transient RGB terminusColour = DEFAULT_TERM_COLOUR;
    private transient Size terminusSize = new Size(DEF_TERM_DEC_WIDTH, DEF_TERM_DEC_HEIGHT);
    private transient ILinkTerminusDefinition terminusDefn = null;;
	private Map<String, HibProperty> hibProperties = new HashMap<String, HibProperty>(0);
	private final ListenablePropertyChangeItem eventHandler = new ListenablePropertyChangeItem();

	/**
	 * Default constructor to only be used by hibernate.
	 * @deprecated use another constructor to initialise this class in application code.
	 */
	HibLinkTerminus() {
	}

	public HibLinkTerminus(HibLinkAttribute hibLinkAttribute, LinkTermType linkTermType, ILinkTerminusDefinition terminusDefn) {
		this();
		this.linkAttribute = hibLinkAttribute;
		this.linkTermType = linkTermType;
		this.terminusDefn = terminusDefn;
		setDefaults(terminusDefn.getLinkTerminusDefaults());
	}

	/**
	 * Constructs a new object with attributes copied from another one.
	 * @param hibLinkAttribute
	 * @param other
	 */
	public HibLinkTerminus(HibLinkAttribute hibLinkAttribute, HibLinkTerminus other) {
		this();
		this.linkAttribute = hibLinkAttribute;
		this.linkTermType = other.getLinkTermType();
		IPropertyBuilder propBuilder = new PropertyBuilder(hibLinkAttribute.getCanvas());
		this.endDecoratorType = other.getEndDecoratorType();
		this.endDecSize = other.getEndSize();
		this.offset = other.getGap();
		this.terminusColour = other.getTerminusColour();
		this.terminusSize = other.getTerminusSize();
		this.terminusDefn = other.terminusDefn;
		this.termShapeType = other.getTerminusDecoratorType();
		for(HibProperty otherHibProp : other.getProperties().values()){
			HibProperty copiedHibProp = (HibProperty)otherHibProp.getDefinition().copyProperty(propBuilder, otherHibProp);
			this.hibProperties.put(copiedHibProp.getDefinition().getName(), copiedHibProp);
		}
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
		IPropertyBuilder propBuilder = new PropertyBuilder(this.getOwningLink().getCanvas());
		Iterator<IPropertyDefinition> iter = linkTerminusDefaults.propertyDefinitionIterator();
		while(iter.hasNext()){
			IPropertyDefinition propDefn = iter.next();
			this.hibProperties.put(propDefn.getName(), (HibProperty)propDefn.createProperty(propBuilder));
		}
	}

	public void injectLinkTerminusDefaults(ILinkTerminusDefinition terminusDefn) throws InconsistentNotationDefinitionException {
		if(terminusDefn != null && (!terminusDefn.getOwningObjectType().equals(linkAttribute.getObjectType())
				|| !terminusDefn.getLinkEndCode().equals(this.linkTermType))){
			throw new IllegalArgumentException("terminusDefn must belong to the same object type as the link owning this terminus and be for the correct link terminus type.");
		}
		this.terminusDefn = terminusDefn;
		final Iterator<IPropertyDefinition> propDefnIter = terminusDefn.getLinkTerminusDefaults().propertyDefinitionIterator();
		int propCntr = 0;
		while(propDefnIter.hasNext()) {
			IPropertyDefinition definition = propDefnIter.next();
			HibProperty property = this.hibProperties.get(definition.getName());
			if(property==null) {
					throw new InconsistentNotationDefinitionException("The link terminus definition has property definitions which have no matching property in this Shape Attribute");
			}
			property.setPropertyDefinition(definition);
			propCntr++;
		}
		if(propCntr != this.hibProperties.size()) {
			throw new InconsistentNotationDefinitionException("Link terminus definition inconsistent with stored terminus properties. Cannot find definitions for some properties");
		}
	}
	
	public Long getId() {
		return this.id;
	}

	@SuppressWarnings("unused")
	private void setId(Long id) {
		this.id = id;
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
		this.eventHandler.notifyProperyChange(PropertyChange.END_DECORATOR_TYPE, oldValue, this.endDecoratorType);
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

	public Map<String, HibProperty> getProperties() {
		return this.hibProperties;
	}

	public void setProperties(Map<String, HibProperty> hibProperties) {
		this.hibProperties = hibProperties;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof HibLinkTerminus))
			return false;
		HibLinkTerminus castOther = (HibLinkTerminus) other;

		return ((this.getOwningLink() == castOther.getOwningLink()) || (this.getOwningLink() != null
				&& castOther.getOwningLink() != null && this.getOwningLink().equals(
				castOther.getOwningLink())))
				&& (this.getLinkTermType() == castOther.getLinkTermType());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (this.getOwningLink() == null ? 0 : this.getOwningLink().hashCode());
		result = 37 * result + this.getLinkTermType().hashCode();

		return result;
	}

	// The following is extra code specified in the hbm.xml files

	private static final long serialVersionUID = -4462637156010353035L;

	public void addProperty(String name, HibProperty toAdd) {
		if (toAdd == null)
			throw new IllegalArgumentException("property cannot be null");
		this.hibProperties.put(name, toAdd);
	}

	void removeProperty(String toRemove) {
		if (toRemove == null)
			throw new IllegalArgumentException("id cannot be null");
		HibProperty propertyToRemove = hibProperties.get(toRemove);
		if (propertyToRemove == null)
			throw new IllegalStateException("property cannot be null");

		this.hibProperties.remove(toRemove);
	}

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
		this.eventHandler.notifyProperyChange(PropertyChange.TERMINUS_GAP, oldValue, this.offset);
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
		this.eventHandler.notifyProperyChange(PropertyChange.END_DECORATOR_SIZE, oldValue, this.endDecSize);
	}

	public void setTerminusColour(RGB newColour) {
		if(newColour == null) throw new IllegalArgumentException("newColour cannot be null");
		
		RGB oldValue = this.terminusColour;
		this.terminusColour = newColour;
		this.eventHandler.notifyProperyChange(PropertyChange.TERMINUS_DEC_COLOUR, oldValue, this.terminusColour);
	}

	public void setTerminusDecoratorType(PrimitiveShapeType shapeObjectType) {
		if(shapeObjectType == null) throw new IllegalArgumentException("shapeObjectType cannot be null");
		
		PrimitiveShapeType oldValue = this.termShapeType;
		this.termShapeType = shapeObjectType;
		this.eventHandler.notifyProperyChange(PropertyChange.TERMINUS_DECORATOR_TYPE, oldValue, this.termShapeType);
	}

	public void setTerminusSize(Size newSize) {
		if(newSize == null) throw new IllegalArgumentException("newSize cannot be null");
		
		Size oldValue = this.terminusSize;
		this.terminusSize = newSize;
		this.eventHandler.notifyProperyChange(PropertyChange.TERMINUS_SIZE, oldValue, this.terminusSize);
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

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILinkTerminus#propertyIterator()
	 */
	public Iterator<IAnnotationProperty> propertyIterator() {
		return new IterationCaster<IAnnotationProperty, HibProperty>(this.hibProperties.values().iterator());
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotatedObject#getProperty(org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyDefinition)
	 */
	public IAnnotationProperty getProperty(IPropertyDefinition propDefn) {
		return this.getProperty(propDefn.getName());
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotatedObject#getProperty(java.lang.String)
	 */
	public IAnnotationProperty getProperty(String propName) {
		return this.hibProperties.get(propName);
	}
	
	@Override
	public String toString(){
		StringBuilder builder = new StringBuilder(this.getClass().getSimpleName());
		builder.append("(linkatt=");
		builder.append(this.linkAttribute);
		builder.append(", termType=");
		builder.append(this.termShapeType.name());
		builder.append(")");
		return builder.toString();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotatedObject#containsProperty(org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyDefinition)
	 */
	public boolean containsProperty(IPropertyDefinition propDefn) {
		boolean retVal = false;
		if(propDefn != null) {
			this.hibProperties.get(propDefn.getName());
		}
		return retVal;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotatedObject#containsProperty(java.lang.String)
	 */
	public boolean containsProperty(String propName) {
		boolean retVal = false;
		if(propName != null) {
			this.hibProperties.get(propName);
		}
		return retVal;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotatedObject#numProperties()
	 */
	public int numProperties() {
		return this.hibProperties.size();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotatedObject#containsProperty(org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationProperty)
	 */
	public boolean containsProperty(IAnnotationProperty property) {
		boolean retVal = false;
		if(property != null) {
			IAnnotationProperty foundProp = this.hibProperties.get(property.getDefinition().getName());
			if(foundProp != null) {
				retVal = foundProp.equals(property);
			}
		}
		return retVal;
	}

	public boolean isValid() {
		boolean retVal = true;
		if(this.linkAttribute != null && this.terminusDefn != null && terminusDefn.getOwningObjectType().equals(linkAttribute.getObjectType())
					&& terminusDefn.getLinkEndCode().equals(this.linkTermType)){
			Iterator<IPropertyDefinition> propDefnIter = terminusDefn.getLinkTerminusDefaults().propertyDefinitionIterator();
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

}
