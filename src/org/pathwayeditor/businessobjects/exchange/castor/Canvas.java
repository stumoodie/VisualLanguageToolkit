/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3</a>, using an XML
 * Schema.
 * $Id$
 */

package org.pathwayeditor.businessobjects.exchange.castor;

/**
 * Class Canvas.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public class Canvas implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _grid.
     */
    private org.pathwayeditor.businessobjects.exchange.castor.Grid _grid;

    /**
     * Field _notation.
     */
    private org.pathwayeditor.businessobjects.exchange.castor.Notation _notation;

    /**
     * Field _canvasSize.
     */
    private org.pathwayeditor.businessobjects.exchange.castor.CanvasSize _canvasSize;

    /**
     * Field _background.
     */
    private org.pathwayeditor.businessobjects.exchange.castor.Background _background;

    /**
     * Field _model.
     */
    private org.pathwayeditor.businessobjects.exchange.castor.Model _model;

    /**
     * Field _rootAttribute.
     */
    private org.pathwayeditor.businessobjects.exchange.castor.RootAttribute _rootAttribute;

    /**
     * Field _shapeAttributeList.
     */
    private java.util.List<org.pathwayeditor.businessobjects.exchange.castor.ShapeAttribute> _shapeAttributeList;

    /**
     * Field _labelAttributeList.
     */
    private java.util.List<org.pathwayeditor.businessobjects.exchange.castor.LabelAttribute> _labelAttributeList;

    /**
     * Field _linkAttributeList.
     */
    private java.util.List<org.pathwayeditor.businessobjects.exchange.castor.LinkAttribute> _linkAttributeList;

    /**
     * Field _linkTerminusList.
     */
    private java.util.List<org.pathwayeditor.businessobjects.exchange.castor.LinkTerminus> _linkTerminusList;


      //----------------/
     //- Constructors -/
    //----------------/

    public Canvas() {
        super();
        this._shapeAttributeList = new java.util.ArrayList<org.pathwayeditor.businessobjects.exchange.castor.ShapeAttribute>();
        this._labelAttributeList = new java.util.ArrayList<org.pathwayeditor.businessobjects.exchange.castor.LabelAttribute>();
        this._linkAttributeList = new java.util.ArrayList<org.pathwayeditor.businessobjects.exchange.castor.LinkAttribute>();
        this._linkTerminusList = new java.util.ArrayList<org.pathwayeditor.businessobjects.exchange.castor.LinkTerminus>();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * 
     * 
     * @param vLabelAttribute
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addLabelAttribute(
            final org.pathwayeditor.businessobjects.exchange.castor.LabelAttribute vLabelAttribute)
    throws java.lang.IndexOutOfBoundsException {
        this._labelAttributeList.add(vLabelAttribute);
    }

    /**
     * 
     * 
     * @param index
     * @param vLabelAttribute
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addLabelAttribute(
            final int index,
            final org.pathwayeditor.businessobjects.exchange.castor.LabelAttribute vLabelAttribute)
    throws java.lang.IndexOutOfBoundsException {
        this._labelAttributeList.add(index, vLabelAttribute);
    }

    /**
     * 
     * 
     * @param vLinkAttribute
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addLinkAttribute(
            final org.pathwayeditor.businessobjects.exchange.castor.LinkAttribute vLinkAttribute)
    throws java.lang.IndexOutOfBoundsException {
        this._linkAttributeList.add(vLinkAttribute);
    }

    /**
     * 
     * 
     * @param index
     * @param vLinkAttribute
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addLinkAttribute(
            final int index,
            final org.pathwayeditor.businessobjects.exchange.castor.LinkAttribute vLinkAttribute)
    throws java.lang.IndexOutOfBoundsException {
        this._linkAttributeList.add(index, vLinkAttribute);
    }

    /**
     * 
     * 
     * @param vLinkTerminus
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addLinkTerminus(
            final org.pathwayeditor.businessobjects.exchange.castor.LinkTerminus vLinkTerminus)
    throws java.lang.IndexOutOfBoundsException {
        this._linkTerminusList.add(vLinkTerminus);
    }

    /**
     * 
     * 
     * @param index
     * @param vLinkTerminus
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addLinkTerminus(
            final int index,
            final org.pathwayeditor.businessobjects.exchange.castor.LinkTerminus vLinkTerminus)
    throws java.lang.IndexOutOfBoundsException {
        this._linkTerminusList.add(index, vLinkTerminus);
    }

    /**
     * 
     * 
     * @param vShapeAttribute
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addShapeAttribute(
            final org.pathwayeditor.businessobjects.exchange.castor.ShapeAttribute vShapeAttribute)
    throws java.lang.IndexOutOfBoundsException {
        this._shapeAttributeList.add(vShapeAttribute);
    }

    /**
     * 
     * 
     * @param index
     * @param vShapeAttribute
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addShapeAttribute(
            final int index,
            final org.pathwayeditor.businessobjects.exchange.castor.ShapeAttribute vShapeAttribute)
    throws java.lang.IndexOutOfBoundsException {
        this._shapeAttributeList.add(index, vShapeAttribute);
    }

    /**
     * Method enumerateLabelAttribute.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration<? extends org.pathwayeditor.businessobjects.exchange.castor.LabelAttribute> enumerateLabelAttribute(
    ) {
        return java.util.Collections.enumeration(this._labelAttributeList);
    }

    /**
     * Method enumerateLinkAttribute.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration<? extends org.pathwayeditor.businessobjects.exchange.castor.LinkAttribute> enumerateLinkAttribute(
    ) {
        return java.util.Collections.enumeration(this._linkAttributeList);
    }

    /**
     * Method enumerateLinkTerminus.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration<? extends org.pathwayeditor.businessobjects.exchange.castor.LinkTerminus> enumerateLinkTerminus(
    ) {
        return java.util.Collections.enumeration(this._linkTerminusList);
    }

    /**
     * Method enumerateShapeAttribute.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration<? extends org.pathwayeditor.businessobjects.exchange.castor.ShapeAttribute> enumerateShapeAttribute(
    ) {
        return java.util.Collections.enumeration(this._shapeAttributeList);
    }

    /**
     * Returns the value of field 'background'.
     * 
     * @return the value of field 'Background'.
     */
    public org.pathwayeditor.businessobjects.exchange.castor.Background getBackground(
    ) {
        return this._background;
    }

    /**
     * Returns the value of field 'canvasSize'.
     * 
     * @return the value of field 'CanvasSize'.
     */
    public org.pathwayeditor.businessobjects.exchange.castor.CanvasSize getCanvasSize(
    ) {
        return this._canvasSize;
    }

    /**
     * Returns the value of field 'grid'.
     * 
     * @return the value of field 'Grid'.
     */
    public org.pathwayeditor.businessobjects.exchange.castor.Grid getGrid(
    ) {
        return this._grid;
    }

    /**
     * Method getLabelAttribute.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the
     * org.pathwayeditor.businessobjects.exchange.castor.LabelAttribute
     * at the given index
     */
    public org.pathwayeditor.businessobjects.exchange.castor.LabelAttribute getLabelAttribute(
            final int index)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._labelAttributeList.size()) {
            throw new IndexOutOfBoundsException("getLabelAttribute: Index value '" + index + "' not in range [0.." + (this._labelAttributeList.size() - 1) + "]");
        }

        return (org.pathwayeditor.businessobjects.exchange.castor.LabelAttribute) _labelAttributeList.get(index);
    }

    /**
     * Method getLabelAttribute.Returns the contents of the
     * collection in an Array.  <p>Note:  Just in case the
     * collection contents are changing in another thread, we pass
     * a 0-length Array of the correct type into the API call. 
     * This way we <i>know</i> that the Array returned is of
     * exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public org.pathwayeditor.businessobjects.exchange.castor.LabelAttribute[] getLabelAttribute(
    ) {
        org.pathwayeditor.businessobjects.exchange.castor.LabelAttribute[] array = new org.pathwayeditor.businessobjects.exchange.castor.LabelAttribute[0];
        return (org.pathwayeditor.businessobjects.exchange.castor.LabelAttribute[]) this._labelAttributeList.toArray(array);
    }

    /**
     * Method getLabelAttributeCount.
     * 
     * @return the size of this collection
     */
    public int getLabelAttributeCount(
    ) {
        return this._labelAttributeList.size();
    }

    /**
     * Method getLinkAttribute.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the
     * org.pathwayeditor.businessobjects.exchange.castor.LinkAttribute
     * at the given index
     */
    public org.pathwayeditor.businessobjects.exchange.castor.LinkAttribute getLinkAttribute(
            final int index)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._linkAttributeList.size()) {
            throw new IndexOutOfBoundsException("getLinkAttribute: Index value '" + index + "' not in range [0.." + (this._linkAttributeList.size() - 1) + "]");
        }

        return (org.pathwayeditor.businessobjects.exchange.castor.LinkAttribute) _linkAttributeList.get(index);
    }

    /**
     * Method getLinkAttribute.Returns the contents of the
     * collection in an Array.  <p>Note:  Just in case the
     * collection contents are changing in another thread, we pass
     * a 0-length Array of the correct type into the API call. 
     * This way we <i>know</i> that the Array returned is of
     * exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public org.pathwayeditor.businessobjects.exchange.castor.LinkAttribute[] getLinkAttribute(
    ) {
        org.pathwayeditor.businessobjects.exchange.castor.LinkAttribute[] array = new org.pathwayeditor.businessobjects.exchange.castor.LinkAttribute[0];
        return (org.pathwayeditor.businessobjects.exchange.castor.LinkAttribute[]) this._linkAttributeList.toArray(array);
    }

    /**
     * Method getLinkAttributeCount.
     * 
     * @return the size of this collection
     */
    public int getLinkAttributeCount(
    ) {
        return this._linkAttributeList.size();
    }

    /**
     * Method getLinkTerminus.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the
     * org.pathwayeditor.businessobjects.exchange.castor.LinkTerminus
     * at the given index
     */
    public org.pathwayeditor.businessobjects.exchange.castor.LinkTerminus getLinkTerminus(
            final int index)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._linkTerminusList.size()) {
            throw new IndexOutOfBoundsException("getLinkTerminus: Index value '" + index + "' not in range [0.." + (this._linkTerminusList.size() - 1) + "]");
        }

        return (org.pathwayeditor.businessobjects.exchange.castor.LinkTerminus) _linkTerminusList.get(index);
    }

    /**
     * Method getLinkTerminus.Returns the contents of the
     * collection in an Array.  <p>Note:  Just in case the
     * collection contents are changing in another thread, we pass
     * a 0-length Array of the correct type into the API call. 
     * This way we <i>know</i> that the Array returned is of
     * exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public org.pathwayeditor.businessobjects.exchange.castor.LinkTerminus[] getLinkTerminus(
    ) {
        org.pathwayeditor.businessobjects.exchange.castor.LinkTerminus[] array = new org.pathwayeditor.businessobjects.exchange.castor.LinkTerminus[0];
        return (org.pathwayeditor.businessobjects.exchange.castor.LinkTerminus[]) this._linkTerminusList.toArray(array);
    }

    /**
     * Method getLinkTerminusCount.
     * 
     * @return the size of this collection
     */
    public int getLinkTerminusCount(
    ) {
        return this._linkTerminusList.size();
    }

    /**
     * Returns the value of field 'model'.
     * 
     * @return the value of field 'Model'.
     */
    public org.pathwayeditor.businessobjects.exchange.castor.Model getModel(
    ) {
        return this._model;
    }

    /**
     * Returns the value of field 'notation'.
     * 
     * @return the value of field 'Notation'.
     */
    public org.pathwayeditor.businessobjects.exchange.castor.Notation getNotation(
    ) {
        return this._notation;
    }

    /**
     * Returns the value of field 'rootAttribute'.
     * 
     * @return the value of field 'RootAttribute'.
     */
    public org.pathwayeditor.businessobjects.exchange.castor.RootAttribute getRootAttribute(
    ) {
        return this._rootAttribute;
    }

    /**
     * Method getShapeAttribute.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the
     * org.pathwayeditor.businessobjects.exchange.castor.ShapeAttribute
     * at the given index
     */
    public org.pathwayeditor.businessobjects.exchange.castor.ShapeAttribute getShapeAttribute(
            final int index)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._shapeAttributeList.size()) {
            throw new IndexOutOfBoundsException("getShapeAttribute: Index value '" + index + "' not in range [0.." + (this._shapeAttributeList.size() - 1) + "]");
        }

        return (org.pathwayeditor.businessobjects.exchange.castor.ShapeAttribute) _shapeAttributeList.get(index);
    }

    /**
     * Method getShapeAttribute.Returns the contents of the
     * collection in an Array.  <p>Note:  Just in case the
     * collection contents are changing in another thread, we pass
     * a 0-length Array of the correct type into the API call. 
     * This way we <i>know</i> that the Array returned is of
     * exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public org.pathwayeditor.businessobjects.exchange.castor.ShapeAttribute[] getShapeAttribute(
    ) {
        org.pathwayeditor.businessobjects.exchange.castor.ShapeAttribute[] array = new org.pathwayeditor.businessobjects.exchange.castor.ShapeAttribute[0];
        return (org.pathwayeditor.businessobjects.exchange.castor.ShapeAttribute[]) this._shapeAttributeList.toArray(array);
    }

    /**
     * Method getShapeAttributeCount.
     * 
     * @return the size of this collection
     */
    public int getShapeAttributeCount(
    ) {
        return this._shapeAttributeList.size();
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
     * Method iterateLabelAttribute.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator<? extends org.pathwayeditor.businessobjects.exchange.castor.LabelAttribute> iterateLabelAttribute(
    ) {
        return this._labelAttributeList.iterator();
    }

    /**
     * Method iterateLinkAttribute.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator<? extends org.pathwayeditor.businessobjects.exchange.castor.LinkAttribute> iterateLinkAttribute(
    ) {
        return this._linkAttributeList.iterator();
    }

    /**
     * Method iterateLinkTerminus.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator<? extends org.pathwayeditor.businessobjects.exchange.castor.LinkTerminus> iterateLinkTerminus(
    ) {
        return this._linkTerminusList.iterator();
    }

    /**
     * Method iterateShapeAttribute.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator<? extends org.pathwayeditor.businessobjects.exchange.castor.ShapeAttribute> iterateShapeAttribute(
    ) {
        return this._shapeAttributeList.iterator();
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
     */
    public void removeAllLabelAttribute(
    ) {
        this._labelAttributeList.clear();
    }

    /**
     */
    public void removeAllLinkAttribute(
    ) {
        this._linkAttributeList.clear();
    }

    /**
     */
    public void removeAllLinkTerminus(
    ) {
        this._linkTerminusList.clear();
    }

    /**
     */
    public void removeAllShapeAttribute(
    ) {
        this._shapeAttributeList.clear();
    }

    /**
     * Method removeLabelAttribute.
     * 
     * @param vLabelAttribute
     * @return true if the object was removed from the collection.
     */
    public boolean removeLabelAttribute(
            final org.pathwayeditor.businessobjects.exchange.castor.LabelAttribute vLabelAttribute) {
        boolean removed = _labelAttributeList.remove(vLabelAttribute);
        return removed;
    }

    /**
     * Method removeLabelAttributeAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public org.pathwayeditor.businessobjects.exchange.castor.LabelAttribute removeLabelAttributeAt(
            final int index) {
        java.lang.Object obj = this._labelAttributeList.remove(index);
        return (org.pathwayeditor.businessobjects.exchange.castor.LabelAttribute) obj;
    }

    /**
     * Method removeLinkAttribute.
     * 
     * @param vLinkAttribute
     * @return true if the object was removed from the collection.
     */
    public boolean removeLinkAttribute(
            final org.pathwayeditor.businessobjects.exchange.castor.LinkAttribute vLinkAttribute) {
        boolean removed = _linkAttributeList.remove(vLinkAttribute);
        return removed;
    }

    /**
     * Method removeLinkAttributeAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public org.pathwayeditor.businessobjects.exchange.castor.LinkAttribute removeLinkAttributeAt(
            final int index) {
        java.lang.Object obj = this._linkAttributeList.remove(index);
        return (org.pathwayeditor.businessobjects.exchange.castor.LinkAttribute) obj;
    }

    /**
     * Method removeLinkTerminus.
     * 
     * @param vLinkTerminus
     * @return true if the object was removed from the collection.
     */
    public boolean removeLinkTerminus(
            final org.pathwayeditor.businessobjects.exchange.castor.LinkTerminus vLinkTerminus) {
        boolean removed = _linkTerminusList.remove(vLinkTerminus);
        return removed;
    }

    /**
     * Method removeLinkTerminusAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public org.pathwayeditor.businessobjects.exchange.castor.LinkTerminus removeLinkTerminusAt(
            final int index) {
        java.lang.Object obj = this._linkTerminusList.remove(index);
        return (org.pathwayeditor.businessobjects.exchange.castor.LinkTerminus) obj;
    }

    /**
     * Method removeShapeAttribute.
     * 
     * @param vShapeAttribute
     * @return true if the object was removed from the collection.
     */
    public boolean removeShapeAttribute(
            final org.pathwayeditor.businessobjects.exchange.castor.ShapeAttribute vShapeAttribute) {
        boolean removed = _shapeAttributeList.remove(vShapeAttribute);
        return removed;
    }

    /**
     * Method removeShapeAttributeAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public org.pathwayeditor.businessobjects.exchange.castor.ShapeAttribute removeShapeAttributeAt(
            final int index) {
        java.lang.Object obj = this._shapeAttributeList.remove(index);
        return (org.pathwayeditor.businessobjects.exchange.castor.ShapeAttribute) obj;
    }

    /**
     * Sets the value of field 'background'.
     * 
     * @param background the value of field 'background'.
     */
    public void setBackground(
            final org.pathwayeditor.businessobjects.exchange.castor.Background background) {
        this._background = background;
    }

    /**
     * Sets the value of field 'canvasSize'.
     * 
     * @param canvasSize the value of field 'canvasSize'.
     */
    public void setCanvasSize(
            final org.pathwayeditor.businessobjects.exchange.castor.CanvasSize canvasSize) {
        this._canvasSize = canvasSize;
    }

    /**
     * Sets the value of field 'grid'.
     * 
     * @param grid the value of field 'grid'.
     */
    public void setGrid(
            final org.pathwayeditor.businessobjects.exchange.castor.Grid grid) {
        this._grid = grid;
    }

    /**
     * 
     * 
     * @param index
     * @param vLabelAttribute
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setLabelAttribute(
            final int index,
            final org.pathwayeditor.businessobjects.exchange.castor.LabelAttribute vLabelAttribute)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._labelAttributeList.size()) {
            throw new IndexOutOfBoundsException("setLabelAttribute: Index value '" + index + "' not in range [0.." + (this._labelAttributeList.size() - 1) + "]");
        }

        this._labelAttributeList.set(index, vLabelAttribute);
    }

    /**
     * 
     * 
     * @param vLabelAttributeArray
     */
    public void setLabelAttribute(
            final org.pathwayeditor.businessobjects.exchange.castor.LabelAttribute[] vLabelAttributeArray) {
        //-- copy array
        _labelAttributeList.clear();

        for (int i = 0; i < vLabelAttributeArray.length; i++) {
                this._labelAttributeList.add(vLabelAttributeArray[i]);
        }
    }

    /**
     * 
     * 
     * @param index
     * @param vLinkAttribute
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setLinkAttribute(
            final int index,
            final org.pathwayeditor.businessobjects.exchange.castor.LinkAttribute vLinkAttribute)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._linkAttributeList.size()) {
            throw new IndexOutOfBoundsException("setLinkAttribute: Index value '" + index + "' not in range [0.." + (this._linkAttributeList.size() - 1) + "]");
        }

        this._linkAttributeList.set(index, vLinkAttribute);
    }

    /**
     * 
     * 
     * @param vLinkAttributeArray
     */
    public void setLinkAttribute(
            final org.pathwayeditor.businessobjects.exchange.castor.LinkAttribute[] vLinkAttributeArray) {
        //-- copy array
        _linkAttributeList.clear();

        for (int i = 0; i < vLinkAttributeArray.length; i++) {
                this._linkAttributeList.add(vLinkAttributeArray[i]);
        }
    }

    /**
     * 
     * 
     * @param index
     * @param vLinkTerminus
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setLinkTerminus(
            final int index,
            final org.pathwayeditor.businessobjects.exchange.castor.LinkTerminus vLinkTerminus)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._linkTerminusList.size()) {
            throw new IndexOutOfBoundsException("setLinkTerminus: Index value '" + index + "' not in range [0.." + (this._linkTerminusList.size() - 1) + "]");
        }

        this._linkTerminusList.set(index, vLinkTerminus);
    }

    /**
     * 
     * 
     * @param vLinkTerminusArray
     */
    public void setLinkTerminus(
            final org.pathwayeditor.businessobjects.exchange.castor.LinkTerminus[] vLinkTerminusArray) {
        //-- copy array
        _linkTerminusList.clear();

        for (int i = 0; i < vLinkTerminusArray.length; i++) {
                this._linkTerminusList.add(vLinkTerminusArray[i]);
        }
    }

    /**
     * Sets the value of field 'model'.
     * 
     * @param model the value of field 'model'.
     */
    public void setModel(
            final org.pathwayeditor.businessobjects.exchange.castor.Model model) {
        this._model = model;
    }

    /**
     * Sets the value of field 'notation'.
     * 
     * @param notation the value of field 'notation'.
     */
    public void setNotation(
            final org.pathwayeditor.businessobjects.exchange.castor.Notation notation) {
        this._notation = notation;
    }

    /**
     * Sets the value of field 'rootAttribute'.
     * 
     * @param rootAttribute the value of field 'rootAttribute'.
     */
    public void setRootAttribute(
            final org.pathwayeditor.businessobjects.exchange.castor.RootAttribute rootAttribute) {
        this._rootAttribute = rootAttribute;
    }

    /**
     * 
     * 
     * @param index
     * @param vShapeAttribute
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setShapeAttribute(
            final int index,
            final org.pathwayeditor.businessobjects.exchange.castor.ShapeAttribute vShapeAttribute)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._shapeAttributeList.size()) {
            throw new IndexOutOfBoundsException("setShapeAttribute: Index value '" + index + "' not in range [0.." + (this._shapeAttributeList.size() - 1) + "]");
        }

        this._shapeAttributeList.set(index, vShapeAttribute);
    }

    /**
     * 
     * 
     * @param vShapeAttributeArray
     */
    public void setShapeAttribute(
            final org.pathwayeditor.businessobjects.exchange.castor.ShapeAttribute[] vShapeAttributeArray) {
        //-- copy array
        _shapeAttributeList.clear();

        for (int i = 0; i < vShapeAttributeArray.length; i++) {
                this._shapeAttributeList.add(vShapeAttributeArray[i]);
        }
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
     * org.pathwayeditor.businessobjects.exchange.castor.Canvas
     */
    public static org.pathwayeditor.businessobjects.exchange.castor.Canvas unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (org.pathwayeditor.businessobjects.exchange.castor.Canvas) org.exolab.castor.xml.Unmarshaller.unmarshal(org.pathwayeditor.businessobjects.exchange.castor.Canvas.class, reader);
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
