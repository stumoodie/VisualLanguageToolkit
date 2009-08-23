/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3</a>, using an XML
 * Schema.
 * $Id$
 */

package org.pathwayeditor.businessobjects.exchange.castor;

/**
 * Class LabelNodeType.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public abstract class LabelNodeType extends org.pathwayeditor.businessobjects.exchange.castor.CompoundNodeType 
implements java.io.Serializable
{


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _labelAttributeSerial.
     */
    private long _labelAttributeSerial;

    /**
     * keeps track of state for field: _labelAttributeSerial
     */
    private boolean _has_labelAttributeSerial;


      //----------------/
     //- Constructors -/
    //----------------/

    public LabelNodeType() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     */
    public void deleteLabelAttributeSerial(
    ) {
        this._has_labelAttributeSerial= false;
    }

    /**
     * Returns the value of field 'labelAttributeSerial'.
     * 
     * @return the value of field 'LabelAttributeSerial'.
     */
    public long getLabelAttributeSerial(
    ) {
        return this._labelAttributeSerial;
    }

    /**
     * Method hasLabelAttributeSerial.
     * 
     * @return true if at least one LabelAttributeSerial has been
     * added
     */
    public boolean hasLabelAttributeSerial(
    ) {
        return this._has_labelAttributeSerial;
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
     * Sets the value of field 'labelAttributeSerial'.
     * 
     * @param labelAttributeSerial the value of field
     * 'labelAttributeSerial'.
     */
    public void setLabelAttributeSerial(
            final long labelAttributeSerial) {
        this._labelAttributeSerial = labelAttributeSerial;
        this._has_labelAttributeSerial = true;
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
