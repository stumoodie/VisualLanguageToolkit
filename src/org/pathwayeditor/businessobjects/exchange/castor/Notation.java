/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3</a>, using an XML
 * Schema.
 * $Id$
 */

package org.pathwayeditor.businessobjects.exchange.castor;

/**
 * Class Notation.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public class Notation implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _qualifiedName.
     */
    private java.lang.String _qualifiedName;

    /**
     * Field _majorVersion.
     */
    private long _majorVersion;

    /**
     * keeps track of state for field: _majorVersion
     */
    private boolean _has_majorVersion;

    /**
     * Field _minorVersion.
     */
    private long _minorVersion;

    /**
     * keeps track of state for field: _minorVersion
     */
    private boolean _has_minorVersion;

    /**
     * Field _patchVersion.
     */
    private long _patchVersion;

    /**
     * keeps track of state for field: _patchVersion
     */
    private boolean _has_patchVersion;


      //----------------/
     //- Constructors -/
    //----------------/

    public Notation() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     */
    public void deleteMajorVersion(
    ) {
        this._has_majorVersion= false;
    }

    /**
     */
    public void deleteMinorVersion(
    ) {
        this._has_minorVersion= false;
    }

    /**
     */
    public void deletePatchVersion(
    ) {
        this._has_patchVersion= false;
    }

    /**
     * Returns the value of field 'majorVersion'.
     * 
     * @return the value of field 'MajorVersion'.
     */
    public long getMajorVersion(
    ) {
        return this._majorVersion;
    }

    /**
     * Returns the value of field 'minorVersion'.
     * 
     * @return the value of field 'MinorVersion'.
     */
    public long getMinorVersion(
    ) {
        return this._minorVersion;
    }

    /**
     * Returns the value of field 'patchVersion'.
     * 
     * @return the value of field 'PatchVersion'.
     */
    public long getPatchVersion(
    ) {
        return this._patchVersion;
    }

    /**
     * Returns the value of field 'qualifiedName'.
     * 
     * @return the value of field 'QualifiedName'.
     */
    public java.lang.String getQualifiedName(
    ) {
        return this._qualifiedName;
    }

    /**
     * Method hasMajorVersion.
     * 
     * @return true if at least one MajorVersion has been added
     */
    public boolean hasMajorVersion(
    ) {
        return this._has_majorVersion;
    }

    /**
     * Method hasMinorVersion.
     * 
     * @return true if at least one MinorVersion has been added
     */
    public boolean hasMinorVersion(
    ) {
        return this._has_minorVersion;
    }

    /**
     * Method hasPatchVersion.
     * 
     * @return true if at least one PatchVersion has been added
     */
    public boolean hasPatchVersion(
    ) {
        return this._has_patchVersion;
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
     * 
     * 
     * @param out
     * @throws org.exolab.castor.xml.MarshalException if object is
     * null or if any SAXException is thrown during marshaling
     * @throws org.exolab.castor.xml.ValidationException if this
     * object is an invalid instance according to the schema
     */
    public void marshal(
            final java.io.Writer out)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        org.exolab.castor.xml.Marshaller.marshal(this, out);
    }

    /**
     * 
     * 
     * @param handler
     * @throws java.io.IOException if an IOException occurs during
     * marshaling
     * @throws org.exolab.castor.xml.ValidationException if this
     * object is an invalid instance according to the schema
     * @throws org.exolab.castor.xml.MarshalException if object is
     * null or if any SAXException is thrown during marshaling
     */
    public void marshal(
            final org.xml.sax.ContentHandler handler)
    throws java.io.IOException, org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        org.exolab.castor.xml.Marshaller.marshal(this, handler);
    }

    /**
     * Sets the value of field 'majorVersion'.
     * 
     * @param majorVersion the value of field 'majorVersion'.
     */
    public void setMajorVersion(
            final long majorVersion) {
        this._majorVersion = majorVersion;
        this._has_majorVersion = true;
    }

    /**
     * Sets the value of field 'minorVersion'.
     * 
     * @param minorVersion the value of field 'minorVersion'.
     */
    public void setMinorVersion(
            final long minorVersion) {
        this._minorVersion = minorVersion;
        this._has_minorVersion = true;
    }

    /**
     * Sets the value of field 'patchVersion'.
     * 
     * @param patchVersion the value of field 'patchVersion'.
     */
    public void setPatchVersion(
            final long patchVersion) {
        this._patchVersion = patchVersion;
        this._has_patchVersion = true;
    }

    /**
     * Sets the value of field 'qualifiedName'.
     * 
     * @param qualifiedName the value of field 'qualifiedName'.
     */
    public void setQualifiedName(
            final java.lang.String qualifiedName) {
        this._qualifiedName = qualifiedName;
    }

    /**
     * Method unmarshal.
     * 
     * @param reader
     * @throws org.exolab.castor.xml.MarshalException if object is
     * null or if any SAXException is thrown during marshaling
     * @throws org.exolab.castor.xml.ValidationException if this
     * object is an invalid instance according to the schema
     * @return the unmarshaled
     * org.pathwayeditor.businessobjects.exchange.castor.Notation
     */
    public static org.pathwayeditor.businessobjects.exchange.castor.Notation unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (org.pathwayeditor.businessobjects.exchange.castor.Notation) org.exolab.castor.xml.Unmarshaller.unmarshal(org.pathwayeditor.businessobjects.exchange.castor.Notation.class, reader);
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
