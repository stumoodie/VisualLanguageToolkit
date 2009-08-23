/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3</a>, using an XML
 * Schema.
 * $Id$
 */

package org.pathwayeditor.businessobjects.exchange.castor;

/**
 * Class ShapeNodeType.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public abstract class ShapeNodeType extends org.pathwayeditor.businessobjects.exchange.castor.CompoundNodeType 
implements java.io.Serializable
{


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _shapeAttributeSerial.
     */
    private long _shapeAttributeSerial;

    /**
     * keeps track of state for field: _shapeAttributeSerial
     */
    private boolean _has_shapeAttributeSerial;


      //----------------/
     //- Constructors -/
    //----------------/

    public ShapeNodeType() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     */
    public void deleteShapeAttributeSerial(
    ) {
        this._has_shapeAttributeSerial= false;
    }

    /**
     * Returns the value of field 'shapeAttributeSerial'.
     * 
     * @return the value of field 'ShapeAttributeSerial'.
     */
    public long getShapeAttributeSerial(
    ) {
        return this._shapeAttributeSerial;
    }

    /**
     * Method hasShapeAttributeSerial.
     * 
     * @return true if at least one ShapeAttributeSerial has been
     * added
     */
    public boolean hasShapeAttributeSerial(
    ) {
        return this._has_shapeAttributeSerial;
    }

    /**
     * Method isValid.
     * 
     * @return true if this object is valid according to the schema
     */
    public boolean isValid(
    ) {
        try {
            validate();
        } catch (org.exolab.castor.xml.ValidationException vex) {
            return false;
        }
        return true;
    }

    /**
     * Sets the value of field 'shapeAttributeSerial'.
     * 
     * @param shapeAttributeSerial the value of field
     * 'shapeAttributeSerial'.
     */
    public void setShapeAttributeSerial(
            final long shapeAttributeSerial) {
        this._shapeAttributeSerial = shapeAttributeSerial;
        this._has_shapeAttributeSerial = true;
    }

    /**
     * 
     * 
     * @throws org.exolab.castor.xml.ValidationException if this
     * object is an invalid instance according to the schema
     */
    public void validate(
    )
    throws org.exolab.castor.xml.ValidationException {
        org.exolab.castor.xml.Validator validator = new org.exolab.castor.xml.Validator();
        validator.validate(this);
    }

}
