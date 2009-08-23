/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3</a>, using an XML
 * Schema.
 * $Id$
 */

package org.pathwayeditor.businessobjects.exchange.castor;

/**
 * Class ObjectType.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public class ObjectType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _uniqueId.
     */
    private long _uniqueId;

    /**
     * keeps track of state for field: _uniqueId
     */
    private boolean _has_uniqueId;


      //----------------/
     //- Constructors -/
    //----------------/

    public ObjectType() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     */
    public void deleteUniqueId(
    ) {
        this._has_uniqueId= false;
    }

    /**
     * Returns the value of field 'uniqueId'.
     * 
     * @return the value of field 'UniqueId'.
     */
    public long getUniqueId(
    ) {
        return this._uniqueId;
    }

    /**
     * Method hasUniqueId.
     * 
     * @return true if at least one UniqueId has been added
     */
    public boolean hasUniqueId(
    ) {
        return this._has_uniqueId;
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
     * Sets the value of field 'uniqueId'.
     * 
     * @param uniqueId the value of field 'uniqueId'.
     */
    public void setUniqueId(
            final long uniqueId) {
        this._uniqueId = uniqueId;
        this._has_uniqueId = true;
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
     * org.pathwayeditor.businessobjects.exchange.castor.ObjectType
     */
    public static org.pathwayeditor.businessobjects.exchange.castor.ObjectType unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (org.pathwayeditor.businessobjects.exchange.castor.ObjectType) org.exolab.castor.xml.Unmarshaller.unmarshal(org.pathwayeditor.businessobjects.exchange.castor.ObjectType.class, reader);
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
