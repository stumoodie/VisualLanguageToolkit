/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3</a>, using an XML
 * Schema.
 * $Id$
 */

package org.pathwayeditor.businessobjects.exchange.castor;

/**
 * Class LinkEdgeType.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public abstract class LinkEdgeType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _linkIdx.
     */
    private long _linkIdx;

    /**
     * keeps track of state for field: _linkIdx
     */
    private boolean _has_linkIdx;

    /**
     * Field _srcNodeIdx.
     */
    private long _srcNodeIdx;

    /**
     * keeps track of state for field: _srcNodeIdx
     */
    private boolean _has_srcNodeIdx;

    /**
     * Field _tgtNodeIdx.
     */
    private long _tgtNodeIdx;

    /**
     * keeps track of state for field: _tgtNodeIdx
     */
    private boolean _has_tgtNodeIdx;

    /**
     * Field _linkAttributeSerial.
     */
    private long _linkAttributeSerial;

    /**
     * keeps track of state for field: _linkAttributeSerial
     */
    private boolean _has_linkAttributeSerial;


      //----------------/
     //- Constructors -/
    //----------------/

    public LinkEdgeType() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     */
    public void deleteLinkAttributeSerial(
    ) {
        this._has_linkAttributeSerial= false;
    }

    /**
     */
    public void deleteLinkIdx(
    ) {
        this._has_linkIdx= false;
    }

    /**
     */
    public void deleteSrcNodeIdx(
    ) {
        this._has_srcNodeIdx= false;
    }

    /**
     */
    public void deleteTgtNodeIdx(
    ) {
        this._has_tgtNodeIdx= false;
    }

    /**
     * Returns the value of field 'linkAttributeSerial'.
     * 
     * @return the value of field 'LinkAttributeSerial'.
     */
    public long getLinkAttributeSerial(
    ) {
        return this._linkAttributeSerial;
    }

    /**
     * Returns the value of field 'linkIdx'.
     * 
     * @return the value of field 'LinkIdx'.
     */
    public long getLinkIdx(
    ) {
        return this._linkIdx;
    }

    /**
     * Returns the value of field 'srcNodeIdx'.
     * 
     * @return the value of field 'SrcNodeIdx'.
     */
    public long getSrcNodeIdx(
    ) {
        return this._srcNodeIdx;
    }

    /**
     * Returns the value of field 'tgtNodeIdx'.
     * 
     * @return the value of field 'TgtNodeIdx'.
     */
    public long getTgtNodeIdx(
    ) {
        return this._tgtNodeIdx;
    }

    /**
     * Method hasLinkAttributeSerial.
     * 
     * @return true if at least one LinkAttributeSerial has been
     * added
     */
    public boolean hasLinkAttributeSerial(
    ) {
        return this._has_linkAttributeSerial;
    }

    /**
     * Method hasLinkIdx.
     * 
     * @return true if at least one LinkIdx has been added
     */
    public boolean hasLinkIdx(
    ) {
        return this._has_linkIdx;
    }

    /**
     * Method hasSrcNodeIdx.
     * 
     * @return true if at least one SrcNodeIdx has been added
     */
    public boolean hasSrcNodeIdx(
    ) {
        return this._has_srcNodeIdx;
    }

    /**
     * Method hasTgtNodeIdx.
     * 
     * @return true if at least one TgtNodeIdx has been added
     */
    public boolean hasTgtNodeIdx(
    ) {
        return this._has_tgtNodeIdx;
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
     * Sets the value of field 'linkAttributeSerial'.
     * 
     * @param linkAttributeSerial the value of field
     * 'linkAttributeSerial'.
     */
    public void setLinkAttributeSerial(
            final long linkAttributeSerial) {
        this._linkAttributeSerial = linkAttributeSerial;
        this._has_linkAttributeSerial = true;
    }

    /**
     * Sets the value of field 'linkIdx'.
     * 
     * @param linkIdx the value of field 'linkIdx'.
     */
    public void setLinkIdx(
            final long linkIdx) {
        this._linkIdx = linkIdx;
        this._has_linkIdx = true;
    }

    /**
     * Sets the value of field 'srcNodeIdx'.
     * 
     * @param srcNodeIdx the value of field 'srcNodeIdx'.
     */
    public void setSrcNodeIdx(
            final long srcNodeIdx) {
        this._srcNodeIdx = srcNodeIdx;
        this._has_srcNodeIdx = true;
    }

    /**
     * Sets the value of field 'tgtNodeIdx'.
     * 
     * @param tgtNodeIdx the value of field 'tgtNodeIdx'.
     */
    public void setTgtNodeIdx(
            final long tgtNodeIdx) {
        this._tgtNodeIdx = tgtNodeIdx;
        this._has_tgtNodeIdx = true;
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
