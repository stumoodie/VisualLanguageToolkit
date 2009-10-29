/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3</a>, using an XML
 * Schema.
 * $Id$
 */

package org.pathwayeditor.businessobjects.exchange.castor;

/**
 * Class LinkTerminusType.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public abstract class LinkTerminusType extends org.pathwayeditor.businessobjects.exchange.castor.CanvasAttributeType 
implements java.io.Serializable
{


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _location.
     */
    private org.pathwayeditor.businessobjects.exchange.castor.Location _location;

    /**
     * Field _annotations.
     */
    private org.pathwayeditor.businessobjects.exchange.castor.Annotations _annotations;


      //----------------/
     //- Constructors -/
    //----------------/

    public LinkTerminusType() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'annotations'.
     * 
     * @return the value of field 'Annotations'.
     */
    public org.pathwayeditor.businessobjects.exchange.castor.Annotations getAnnotations(
    ) {
        return this._annotations;
    }

    /**
     * Returns the value of field 'location'.
     * 
     * @return the value of field 'Location'.
     */
    public org.pathwayeditor.businessobjects.exchange.castor.Location getLocation(
    ) {
        return this._location;
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
     * Sets the value of field 'annotations'.
     * 
     * @param annotations the value of field 'annotations'.
     */
    public void setAnnotations(
            final org.pathwayeditor.businessobjects.exchange.castor.Annotations annotations) {
        this._annotations = annotations;
    }

    /**
     * Sets the value of field 'location'.
     * 
     * @param location the value of field 'location'.
     */
    public void setLocation(
            final org.pathwayeditor.businessobjects.exchange.castor.Location location) {
        this._location = location;
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
