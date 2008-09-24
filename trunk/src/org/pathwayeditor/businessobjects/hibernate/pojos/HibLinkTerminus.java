package org.pathwayeditor.businessobjects.hibernate.pojos;

// Generated 07-May-2008 22:43:44 by Hibernate Tools 3.2.1.GA

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.pathwayeditor.businessobjects.drawingprimitives.ILinkAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkTerminus;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LinkEndDecoratorShape;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LinkTermType;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.PrimitiveShapeType;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.Size;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationProperty;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyBuilder;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyDefinition;
import org.pathwayeditor.businessobjects.hibernate.helpers.PropertyBuilder;
import org.pathwayeditor.businessobjects.hibernate.pojos.graph.IterationCaster;
import org.pathwayeditor.businessobjects.typedefn.ILinkTerminusDefaults;
import org.pathwayeditor.businessobjects.typedefn.ILinkTerminusDefinition;

/**
 * LinkTerminus generated by hbm2java
 */
public class HibLinkTerminus implements ILinkTerminus, Serializable {
	private final static int DEF_TERM_DEC_WIDTH = 0;
	private final static int DEF_TERM_DEC_HEIGHT = 0;
	private final static int DEF_END_DEC_WIDTH = 0;
	private final static int DEF_END_DEC_HEIGHT = 0;
	private final static int DEFAULT_OFFSET = 0;

	private Long id = null;
	private HibLinkAttribute linkAttribute = null;
	private LinkTermType linkTermType = null;
	private short offset = DEFAULT_OFFSET;
	private LinkEndDecoratorShape endDecoratorType = null;
	private Size endDecSize = new Size(DEF_END_DEC_WIDTH, DEF_END_DEC_HEIGHT);
    private PrimitiveShapeType termShapeType = null;
    private RGB terminusColour;
    private Size terminusSize = new Size(DEF_TERM_DEC_WIDTH, DEF_TERM_DEC_HEIGHT);
    private IPropertyBuilder propBuilder;
	private Map<String, HibProperty> hibProperties = new HashMap<String, HibProperty>(0);

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
		this.propBuilder = new PropertyBuilder(hibLinkAttribute.getCanvas());
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
		this.propBuilder = new PropertyBuilder(hibLinkAttribute.getCanvas());
		this.endDecoratorType = other.getEndDecoratorType();
		this.endDecSize = other.getEndSize();
		this.offset = other.getOffset();
		this.terminusColour = other.getTerminusColor();
		this.terminusSize = other.getTerminusSize();
		this.termShapeType = other.getTerminusDecoratorType();
		for(HibProperty otherHibProp : other.getProperties().values()){
			HibProperty copiedHibProp = (HibProperty)otherHibProp.getDefinition().copyProperty(propBuilder);
			this.hibProperties.put(copiedHibProp.getDefinition().getName(), copiedHibProp);
		}
	}

	/**
	 * @param linkTerminusDefaults
	 */
	private void setDefaults(ILinkTerminusDefaults linkTerminusDefaults) {
		this.endDecoratorType = linkTerminusDefaults.getEndDecoratorType();
		this.endDecSize = linkTerminusDefaults.getEndSize();
		this.offset = linkTerminusDefaults.getGap();
		this.termShapeType = linkTerminusDefaults.getTermDecoratorType();
		this.terminusColour = linkTerminusDefaults.getTermColour();
		this.terminusSize = linkTerminusDefaults.getTermSize();
		for(IPropertyDefinition propDefn : linkTerminusDefaults.getPropertiesFilter().getAllProperties()){
			this.hibProperties.put(propDefn.getName(), (HibProperty)propDefn.createProperty(propBuilder));
		}
	}

	public Long getId() {
		return this.id;
	}

	@SuppressWarnings("unused")
	private void setId(Long id) {
		this.id = id;
	}

	public HibLinkAttribute getAttribute() {
		return this.linkAttribute;
	}

	void setAttribute(HibLinkAttribute hibLinkAttribute) {
		this.linkAttribute = hibLinkAttribute;
		this.propBuilder = new PropertyBuilder(hibLinkAttribute.getCanvas());
	}

	public LinkTermType getLinkTermType() {
		return this.linkTermType;
	}

	public void setLinkTermType(LinkTermType linkTermType) {
		this.linkTermType = linkTermType;
	}

	public short getOffset() {
		return this.offset;
	}

	public void setOffset(short offset) {
		this.offset = offset;
	}

	public LinkEndDecoratorShape getEndDecoratorType() {
		return this.endDecoratorType;
	}

	public void setEndDecoratorType(LinkEndDecoratorShape decoratorType) {
		if (decoratorType == null)
			throw new IllegalArgumentException("Decoratot type cannot be null.");
		this.endDecoratorType = decoratorType;
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

		return ((this.getAttribute() == castOther.getAttribute()) || (this.getAttribute() != null
				&& castOther.getAttribute() != null && this.getAttribute().equals(
				castOther.getAttribute())))
				&& (this.getLinkTermType() == castOther.getLinkTermType());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getAttribute() == null ? 0 : this.getAttribute().hashCode());
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
			throw new IllegalArgumentException();

		this.offset = (short) newGap;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.pathwayeditor.businessobjects.drawingprimitives.ILinkTerminus#
	 * getOwningLink()
	 */
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
	public Size getEndSize() {
		return this.endDecSize;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.pathwayeditor.businessobjects.drawingprimitives.ILinkTerminus#
	 * getTerminusColor()
	 */
	public RGB getTerminusColor() {
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.pathwayeditor.businessobjects.drawingprimitives.ILinkTerminus#setEndSize
	 * (org.pathwayeditor.businessobjects.drawingprimitives.attributes.Size)
	 */
	public void setEndSize(Size size) {
		this.endDecSize = size;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.pathwayeditor.businessobjects.drawingprimitives.ILinkTerminus#
	 * setTerminusColor
	 * (org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB)
	 */
	public void setTerminusColor(RGB newColour) {
		this.terminusColour = newColour;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.pathwayeditor.businessobjects.drawingprimitives.ILinkTerminus#
	 * setTerminusDecoratorType
	 * (org.pathwayeditor.businessobjects.drawingprimitives
	 * .attributes.IPrimitiveShape)
	 */
	public void setTerminusDecoratorType(PrimitiveShapeType shapeObjectType) {
		this.termShapeType = shapeObjectType;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.pathwayeditor.businessobjects.drawingprimitives.ILinkTerminus#
	 * setTerminusSize
	 * (org.pathwayeditor.businessobjects.drawingprimitives.attributes.Size)
	 */
	public void setTerminusSize(Size newSize) {
		this.terminusSize = newSize;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.pathwayeditor.businessobjects.drawingprimitives.ILinkTerminus#
	 * getDefinition()
	 */
	public ILinkTerminusDefinition getDefinition() {
		ILinkTerminusDefinition retVal = this.getAttribute().getObjectType()
				.getTargetTerminusDefinition();
		if (this.getLinkTermType() == LinkTermType.SOURCE) {
			retVal = this.getAttribute().getObjectType()
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

}
