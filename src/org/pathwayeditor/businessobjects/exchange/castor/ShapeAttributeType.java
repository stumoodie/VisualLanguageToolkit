/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3</a>, using an XML
 * Schema.
 * $Id$
 */

package org.pathwayeditor.businessobjects.exchange.castor;

/**
 * Class ShapeAttributeType.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public abstract class ShapeAttributeType extends org.pathwayeditor.businessobjects.exchange.castor.TypedDrawingNodeAttributeType 
implements java.io.Serializable
{


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _lineWidth.
     */
    private java.math.BigDecimal _lineWidth;

    /**
     * Field _lineStyle.
     */
    private org.pathwayeditor.businessobjects.exchange.castor.types.ShapeAttributeTypeLineStyleType _lineStyle;

    /**
     * Field _fillColour.
     */
    private org.pathwayeditor.businessobjects.exchange.castor.FillColour _fillColour;

    /**
     * Field _shapeLineColour.
     */
    private org.pathwayeditor.businessobjects.exchange.castor.ShapeLineColour _shapeLineColour;


      //----------------/
     //- Constructors -/
    //----------------/

    public ShapeAttributeType() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'fillColour'.
     * 
     * @return the value of field 'FillColour'.
     */
    public org.pathwayeditor.businessobjects.exchange.castor.FillColour getFillColour(
    ) {
        return this._fillColour;
    }

    /**
     * Returns the value of field 'lineStyle'.
     * 
     * @return the value of field 'LineStyle'.
     */
    public org.pathwayeditor.businessobjects.exchange.castor.types.ShapeAttributeTypeLineStyleType getLineStyle(
    ) {
        return this._lineStyle;
    }

    /**
     * Returns the value of field 'lineWidth'.
     * 
     * @return the value of field 'LineWidth'.
     */
    public java.math.BigDecimal getLineWidth(
    ) {
        return this._lineWidth;
    }

    /**
     * Returns the value of field 'shapeLineColour'.
     * 
     * @return the value of field 'ShapeLineColour'.
     */
    public org.pathwayeditor.businessobjects.exchange.castor.ShapeLineColour getShapeLineColour(
    ) {
        return this._shapeLineColour;
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
     * Sets the value of field 'fillColour'.
     * 
     * @param fillColour the value of field 'fillColour'.
     */
    public void setFillColour(
            final org.pathwayeditor.businessobjects.exchange.castor.FillColour fillColour) {
        this._fillColour = fillColour;
    }

    /**
     * Sets the value of field 'lineStyle'.
     * 
     * @param lineStyle the value of field 'lineStyle'.
     */
    public void setLineStyle(
            final org.pathwayeditor.businessobjects.exchange.castor.types.ShapeAttributeTypeLineStyleType lineStyle) {
        this._lineStyle = lineStyle;
    }

    /**
     * Sets the value of field 'lineWidth'.
     * 
     * @param lineWidth the value of field 'lineWidth'.
     */
    public void setLineWidth(
            final java.math.BigDecimal lineWidth) {
        this._lineWidth = lineWidth;
    }

    /**
     * Sets the value of field 'shapeLineColour'.
     * 
     * @param shapeLineColour the value of field 'shapeLineColour'.
     */
    public void setShapeLineColour(
            final org.pathwayeditor.businessobjects.exchange.castor.ShapeLineColour shapeLineColour) {
        this._shapeLineColour = shapeLineColour;
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
