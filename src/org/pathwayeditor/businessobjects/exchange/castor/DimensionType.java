/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3</a>, using an XML
 * Schema.
 * $Id$
 */

package org.pathwayeditor.businessobjects.exchange.castor;

/**
 * Class DimensionType.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public abstract class DimensionType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _width.
     */
    private double _width;

    /**
     * keeps track of state for field: _width
     */
    private boolean _has_width;

    /**
     * Field _height.
     */
    private double _height;

    /**
     * keeps track of state for field: _height
     */
    private boolean _has_height;


      //----------------/
     //- Constructors -/
    //----------------/

    public DimensionType() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     */
    public void deleteHeight(
    ) {
        this._has_height= false;
    }

    /**
     */
    public void deleteWidth(
    ) {
        this._has_width= false;
    }

    /**
     * Returns the value of field 'height'.
     * 
     * @return the value of field 'Height'.
     */
    public double getHeight(
    ) {
        return this._height;
    }

    /**
     * Returns the value of field 'width'.
     * 
     * @return the value of field 'Width'.
     */
    public double getWidth(
    ) {
        return this._width;
    }

    /**
     * Method hasHeight.
     * 
     * @return true if at least one Height has been added
     */
    public boolean hasHeight(
    ) {
        return this._has_height;
    }

    /**
     * Method hasWidth.
     * 
     * @return true if at least one Width has been added
     */
    public boolean hasWidth(
    ) {
        return this._has_width;
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
     * Sets the value of field 'height'.
     * 
     * @param height the value of field 'height'.
     */
    public void setHeight(
            final double height) {
        this._height = height;
        this._has_height = true;
    }

    /**
     * Sets the value of field 'width'.
     * 
     * @param width the value of field 'width'.
     */
    public void setWidth(
            final double width) {
        this._width = width;
        this._has_width = true;
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
