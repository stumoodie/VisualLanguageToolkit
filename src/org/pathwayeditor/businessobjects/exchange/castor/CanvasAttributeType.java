/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3</a>, using an XML
 * Schema.
 * $Id$
 */

package org.pathwayeditor.businessobjects.exchange.castor;

/**
 * Class CanvasAttributeType.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public abstract class CanvasAttributeType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _creationSerial.
     */
    private long _creationSerial;

    /**
     * keeps track of state for field: _creationSerial
     */
    private boolean _has_creationSerial;


      //----------------/
     //- Constructors -/
    //----------------/

    public CanvasAttributeType() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     */
    public void deleteCreationSerial(
    ) {
        this._has_creationSerial= false;
    }

    /**
     * Returns the value of field 'creationSerial'.
     * 
     * @return the value of field 'CreationSerial'.
     */
    public long getCreationSerial(
    ) {
        return this._creationSerial;
    }

    /**
     * Method hasCreationSerial.
     * 
     * @return true if at least one CreationSerial has been added
     */
    public boolean hasCreationSerial(
    ) {
        return this._has_creationSerial;
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
     * Sets the value of field 'creationSerial'.
     * 
     * @param creationSerial the value of field 'creationSerial'.
     */
    public void setCreationSerial(
            final long creationSerial) {
        this._creationSerial = creationSerial;
        this._has_creationSerial = true;
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
