/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3</a>, using an XML
 * Schema.
 * $Id$
 */

package org.pathwayeditor.businessobjects.exchange.castor;

/**
 * Class LinkAttributeType.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public abstract class LinkAttributeType extends org.pathwayeditor.businessobjects.exchange.castor.CanvasAttributeType 
implements java.io.Serializable
{


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

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
     * Field _objectType.
     */
    private org.pathwayeditor.businessobjects.exchange.castor.ObjectType _objectType;

    /**
     * Field _lineColour.
     */
    private org.pathwayeditor.businessobjects.exchange.castor.LineColour _lineColour;

    /**
     * Field _annotations.
     */
    private org.pathwayeditor.businessobjects.exchange.castor.Annotations _annotations;


      //----------------/
     //- Constructors -/
    //----------------/

    public LinkAttributeType() {
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
     * Returns the value of field 'lineColour'.
     * 
     * @return the value of field 'LineColour'.
     */
    public org.pathwayeditor.businessobjects.exchange.castor.LineColour getLineColour(
    ) {
        return this._lineColour;
    }

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
     * Returns the value of field 'srcTermSerial'.
     * 
     * @return the value of field 'SrcTermSerial'.
     */
    public long getSrcTermSerial(
    ) {
        return this._srcTermSerial;
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
     * Sets the value of field 'lineColour'.
     * 
     * @param lineColour the value of field 'lineColour'.
     */
    public void setLineColour(
            final org.pathwayeditor.businessobjects.exchange.castor.LineColour lineColour) {
        this._lineColour = lineColour;
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
