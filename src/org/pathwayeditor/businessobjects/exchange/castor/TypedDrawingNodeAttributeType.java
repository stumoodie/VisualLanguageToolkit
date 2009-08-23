/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3</a>, using an XML
 * Schema.
 * $Id$
 */

package org.pathwayeditor.businessobjects.exchange.castor;

/**
 * Class TypedDrawingNodeAttributeType.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public abstract class TypedDrawingNodeAttributeType extends org.pathwayeditor.businessobjects.exchange.castor.DrawingNodeAttributeType 
implements java.io.Serializable
{


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _objectType.
     */
    private org.pathwayeditor.businessobjects.exchange.castor.ObjectType _objectType;


      //----------------/
     //- Constructors -/
    //----------------/

    public TypedDrawingNodeAttributeType() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'objectType'.
     * 
     * @return the value of field 'ObjectType'.
     */
    public org.pathwayeditor.businessobjects.exchange.castor.ObjectType getObjectType(
    ) {
        return this._objectType;
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
     * Sets the value of field 'objectType'.
     * 
     * @param objectType the value of field 'objectType'.
     */
    public void setObjectType(
            final org.pathwayeditor.businessobjects.exchange.castor.ObjectType objectType) {
        this._objectType = objectType;
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
