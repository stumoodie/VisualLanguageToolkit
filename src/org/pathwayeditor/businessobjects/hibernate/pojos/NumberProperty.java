package org.pathwayeditor.businessobjects.hibernate.pojos;
// Generated 07-May-2008 22:43:44 by Hibernate Tools 3.2.1.GA


import java.math.BigDecimal;

/**
 * NumberProperty generated by hbm2java
 */
public class NumberProperty extends org.pathwayeditor.businessobjects.hibernate.pojos.Property implements java.io.Serializable {


     private BigDecimal numberValue;
     private PropertyVisitor visitor = PropertyVisitor.getInstance();

    public NumberProperty() {
    }

    public NumberProperty(Canvas canvas, int creationSerial, BigDecimal numberValue) {
        super(canvas, creationSerial);        
       this.numberValue = numberValue;
    }
   
    public NumberProperty(Canvas newCanvas, NumberProperty other){
    	super(newCanvas, other);
    	numberValue = other.numberValue;
    }
    
    public BigDecimal getNumberValue() {
        return this.numberValue;
    }
    
    public void setNumberValue(BigDecimal numberValue) {
        this.numberValue = numberValue;
    }

    public NumberProperty copy(Canvas newCanvas){
    	return this.visitor.copyNumberProperty(newCanvas, this);
    }


  // The following is extra code specified in the hbm.xml files

	private static final long serialVersionUID = 1951406655193239331L;
    		
  // end of extra code specified in the hbm.xml files

}


