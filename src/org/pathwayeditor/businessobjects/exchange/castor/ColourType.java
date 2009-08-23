/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3</a>, using an XML
 * Schema.
 * $Id$
 */

package org.pathwayeditor.businessobjects.exchange.castor;

/**
 * Class ColourType.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public abstract class ColourType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _red.
     */
    private long _red;

    /**
     * keeps track of state for field: _red
     */
    private boolean _has_red;

    /**
     * Field _green.
     */
    private long _green;

    /**
     * keeps track of state for field: _green
     */
    private boolean _has_green;

    /**
     * Field _blue.
     */
    private long _blue;

    /**
     * keeps track of state for field: _blue
     */
    private boolean _has_blue;


      //----------------/
     //- Constructors -/
    //----------------/

    public ColourType() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     */
    public void deleteBlue(
    ) {
        this._has_blue= false;
    }

    /**
     */
    public void deleteGreen(
    ) {
        this._has_green= false;
    }

    /**
     */
    public void deleteRed(
    ) {
        this._has_red= false;
    }

    /**
     * Returns the value of field 'blue'.
     * 
     * @return the value of field 'Blue'.
     */
    public long getBlue(
    ) {
        return this._blue;
    }

    /**
     * Returns the value of field 'green'.
     * 
     * @return the value of field 'Green'.
     */
    public long getGreen(
    ) {
        return this._green;
    }

    /**
     * Returns the value of field 'red'.
     * 
     * @return the value of field 'Red'.
     */
    public long getRed(
    ) {
        return this._red;
    }

    /**
     * Method hasBlue.
     * 
     * @return true if at least one Blue has been added
     */
    public boolean hasBlue(
    ) {
        return this._has_blue;
    }

    /**
     * Method hasGreen.
     * 
     * @return true if at least one Green has been added
     */
    public boolean hasGreen(
    ) {
        return this._has_green;
    }

    /**
     * Method hasRed.
     * 
     * @return true if at least one Red has been added
     */
    public boolean hasRed(
    ) {
        return this._has_red;
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
     * Sets the value of field 'blue'.
     * 
     * @param blue the value of field 'blue'.
     */
    public void setBlue(
            final long blue) {
        this._blue = blue;
        this._has_blue = true;
    }

    /**
     * Sets the value of field 'green'.
     * 
     * @param green the value of field 'green'.
     */
    public void setGreen(
            final long green) {
        this._green = green;
        this._has_green = true;
    }

    /**
     * Sets the value of field 'red'.
     * 
     * @param red the value of field 'red'.
     */
    public void setRed(
            final long red) {
        this._red = red;
        this._has_red = true;
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
