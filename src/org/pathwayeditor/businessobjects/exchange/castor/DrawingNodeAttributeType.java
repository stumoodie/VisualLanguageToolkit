/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3</a>, using an XML
 * Schema.
 * $Id$
 */

package org.pathwayeditor.businessobjects.exchange.castor;

/**
 * Class DrawingNodeAttributeType.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public abstract class DrawingNodeAttributeType extends org.pathwayeditor.businessobjects.exchange.castor.CanvasAttributeType 
implements java.io.Serializable
{


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _drawingNodeLocation.
     */
    private org.pathwayeditor.businessobjects.exchange.castor.DrawingNodeLocation _drawingNodeLocation;

    /**
     * Field _size.
     */
    private org.pathwayeditor.businessobjects.exchange.castor.Size _size;

    /**
     * Field _annotations.
     */
    private org.pathwayeditor.businessobjects.exchange.castor.Annotations _annotations;


      //----------------/
     //- Constructors -/
    //----------------/

    public DrawingNodeAttributeType() {
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
     * Returns the value of field 'drawingNodeLocation'.
     * 
     * @return the value of field 'DrawingNodeLocation'.
     */
    public org.pathwayeditor.businessobjects.exchange.castor.DrawingNodeLocation getDrawingNodeLocation(
    ) {
        return this._drawingNodeLocation;
    }

    /**
     * Returns the value of field 'size'.
     * 
     * @return the value of field 'Size'.
     */
    public org.pathwayeditor.businessobjects.exchange.castor.Size getSize(
    ) {
        return this._size;
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
     * Sets the value of field 'drawingNodeLocation'.
     * 
     * @param drawingNodeLocation the value of field
     * 'drawingNodeLocation'.
     */
    public void setDrawingNodeLocation(
            final org.pathwayeditor.businessobjects.exchange.castor.DrawingNodeLocation drawingNodeLocation) {
        this._drawingNodeLocation = drawingNodeLocation;
    }

    /**
     * Sets the value of field 'size'.
     * 
     * @param size the value of field 'size'.
     */
    public void setSize(
            final org.pathwayeditor.businessobjects.exchange.castor.Size size) {
        this._size = size;
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
