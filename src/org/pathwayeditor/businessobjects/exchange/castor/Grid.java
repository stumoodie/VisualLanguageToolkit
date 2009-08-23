/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3</a>, using an XML
 * Schema.
 * $Id$
 */

package org.pathwayeditor.businessobjects.exchange.castor;

/**
 * Class Grid.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public class Grid implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _snapToGrid.
     */
    private boolean _snapToGrid;

    /**
     * keeps track of state for field: _snapToGrid
     */
    private boolean _has_snapToGrid;

    /**
     * Field _gridOn.
     */
    private boolean _gridOn;

    /**
     * keeps track of state for field: _gridOn
     */
    private boolean _has_gridOn;

    /**
     * Field _gridSize.
     */
    private org.pathwayeditor.businessobjects.exchange.castor.GridSize _gridSize;


      //----------------/
     //- Constructors -/
    //----------------/

    public Grid() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     */
    public void deleteGridOn(
    ) {
        this._has_gridOn= false;
    }

    /**
     */
    public void deleteSnapToGrid(
    ) {
        this._has_snapToGrid= false;
    }

    /**
     * Returns the value of field 'gridOn'.
     * 
     * @return the value of field 'GridOn'.
     */
    public boolean getGridOn(
    ) {
        return this._gridOn;
    }

    /**
     * Returns the value of field 'gridSize'.
     * 
     * @return the value of field 'GridSize'.
     */
    public org.pathwayeditor.businessobjects.exchange.castor.GridSize getGridSize(
    ) {
        return this._gridSize;
    }

    /**
     * Returns the value of field 'snapToGrid'.
     * 
     * @return the value of field 'SnapToGrid'.
     */
    public boolean getSnapToGrid(
    ) {
        return this._snapToGrid;
    }

    /**
     * Method hasGridOn.
     * 
     * @return true if at least one GridOn has been added
     */
    public boolean hasGridOn(
    ) {
        return this._has_gridOn;
    }

    /**
     * Method hasSnapToGrid.
     * 
     * @return true if at least one SnapToGrid has been added
     */
    public boolean hasSnapToGrid(
    ) {
        return this._has_snapToGrid;
    }

    /**
     * Returns the value of field 'gridOn'.
     * 
     * @return the value of field 'GridOn'.
     */
    public boolean isGridOn(
    ) {
        return this._gridOn;
    }

    /**
     * Returns the value of field 'snapToGrid'.
     * 
     * @return the value of field 'SnapToGrid'.
     */
    public boolean isSnapToGrid(
    ) {
        return this._snapToGrid;
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
     * Sets the value of field 'gridOn'.
     * 
     * @param gridOn the value of field 'gridOn'.
     */
    public void setGridOn(
            final boolean gridOn) {
        this._gridOn = gridOn;
        this._has_gridOn = true;
    }

    /**
     * Sets the value of field 'gridSize'.
     * 
     * @param gridSize the value of field 'gridSize'.
     */
    public void setGridSize(
            final org.pathwayeditor.businessobjects.exchange.castor.GridSize gridSize) {
        this._gridSize = gridSize;
    }

    /**
     * Sets the value of field 'snapToGrid'.
     * 
     * @param snapToGrid the value of field 'snapToGrid'.
     */
    public void setSnapToGrid(
            final boolean snapToGrid) {
        this._snapToGrid = snapToGrid;
        this._has_snapToGrid = true;
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
     * org.pathwayeditor.businessobjects.exchange.castor.Grid
     */
    public static org.pathwayeditor.businessobjects.exchange.castor.Grid unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (org.pathwayeditor.businessobjects.exchange.castor.Grid) org.exolab.castor.xml.Unmarshaller.unmarshal(org.pathwayeditor.businessobjects.exchange.castor.Grid.class, reader);
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
