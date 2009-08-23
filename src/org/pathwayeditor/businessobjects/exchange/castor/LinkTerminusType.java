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
     * Field _terminusType.
     */
    private org.pathwayeditor.businessobjects.exchange.castor.types.LinkTerminusTypeTerminusTypeType _terminusType;

    /**
     * Field _srcTermSerial.
     */
    private long _srcTermSerial;

    /**
     * keeps track of state for field: _srcTermSerial
     */
    private boolean _has_srcTermSerial;

    /**
     * Field _tgtTermSerial.
     */
    private long _tgtTermSerial;

    /**
     * keeps track of state for field: _tgtTermSerial
     */
    private boolean _has_tgtTermSerial;

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
     */
    public void deleteSrcTermSerial(
    ) {
        this._has_srcTermSerial= false;
    }

    /**
     */
    public void deleteTgtTermSerial(
    ) {
        this._has_tgtTermSerial= false;
    }

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
     * Returns the value of field 'srcTermSerial'.
     * 
     * @return the value of field 'SrcTermSerial'.
     */
    public long getSrcTermSerial(
    ) {
        return this._srcTermSerial;
    }

    /**
     * Returns the value of field 'terminusType'.
     * 
     * @return the value of field 'TerminusType'.
     */
    public org.pathwayeditor.businessobjects.exchange.castor.types.LinkTerminusTypeTerminusTypeType getTerminusType(
    ) {
        return this._terminusType;
    }

    /**
     * Returns the value of field 'tgtTermSerial'.
     * 
     * @return the value of field 'TgtTermSerial'.
     */
    public long getTgtTermSerial(
    ) {
        return this._tgtTermSerial;
    }

    /**
     * Method hasSrcTermSerial.
     * 
     * @return true if at least one SrcTermSerial has been added
     */
    public boolean hasSrcTermSerial(
    ) {
        return this._has_srcTermSerial;
    }

    /**
     * Method hasTgtTermSerial.
     * 
     * @return true if at least one TgtTermSerial has been added
     */
    public boolean hasTgtTermSerial(
    ) {
        return this._has_tgtTermSerial;
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
     * Sets the value of field 'srcTermSerial'.
     * 
     * @param srcTermSerial the value of field 'srcTermSerial'.
     */
    public void setSrcTermSerial(
            final long srcTermSerial) {
        this._srcTermSerial = srcTermSerial;
        this._has_srcTermSerial = true;
    }

    /**
     * Sets the value of field 'terminusType'.
     * 
     * @param terminusType the value of field 'terminusType'.
     */
    public void setTerminusType(
            final org.pathwayeditor.businessobjects.exchange.castor.types.LinkTerminusTypeTerminusTypeType terminusType) {
        this._terminusType = terminusType;
    }

    /**
     * Sets the value of field 'tgtTermSerial'.
     * 
     * @param tgtTermSerial the value of field 'tgtTermSerial'.
     */
    public void setTgtTermSerial(
            final long tgtTermSerial) {
        this._tgtTermSerial = tgtTermSerial;
        this._has_tgtTermSerial = true;
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
