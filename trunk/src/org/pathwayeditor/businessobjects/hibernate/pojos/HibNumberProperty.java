package org.pathwayeditor.businessobjects.hibernate.pojos;
// Generated 07-May-2008 22:43:44 by Hibernate Tools 3.2.1.GA


import java.io.Serializable;
import java.math.BigDecimal;

import org.pathwayeditor.businessobjects.drawingprimitives.properties.INumberAnnotationProperty;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.INumberPropertyDefinition;

/**
 * NumberProperty generated by hbm2java
 */
public class HibNumberProperty extends HibProperty implements Serializable , INumberAnnotationProperty  {
	private static final long serialVersionUID = 1951406655193239331L;

     private BigDecimal numberValue;
     private INumberPropertyDefinition propDefn;

     /**
	 * Constructor should only be used by hiberate.
	 * @deprecated Application code should not use this constructor. Use one of the other constructors instead.
	 */
     HibNumberProperty() {
    }

    public HibNumberProperty(HibCanvas hibCanvas, int creationSerial, INumberPropertyDefinition propDefn) {
        super(hibCanvas, creationSerial);        
        this.propDefn = propDefn;
       this.numberValue = propDefn.getDefaultValue();
    }
   
    public HibNumberProperty(HibCanvas newCanvas, int newCreationSerial, HibNumberProperty other){
    	super(newCanvas, newCreationSerial, other);
    	numberValue = other.numberValue;
    	this.propDefn = other.propDefn;
    }
    
    public void setValue(BigDecimal numberValue) {
    	if ( numberValue == null)
    		throw new IllegalArgumentException ("Number value should not be null.") ; 
        this.numberValue = numberValue;
    }

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.properties.INumberAnnotationProperty#getDefinition()
	 */
	public INumberPropertyDefinition getDefinition() {
		return this.propDefn;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationProperty#getValue()
	 */
	public BigDecimal getValue() {
		return this.numberValue;
	}

}

