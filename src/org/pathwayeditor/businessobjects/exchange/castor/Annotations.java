/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3</a>, using an XML
 * Schema.
 * $Id$
 */

package org.pathwayeditor.businessobjects.exchange.castor;

/**
 * Class Annotations.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public class Annotations implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Internal choice value storage
     */
    private java.lang.Object _choiceValue;

    /**
     * Field _textAnnotationPropertyList.
     */
    private java.util.List<org.pathwayeditor.businessobjects.exchange.castor.TextAnnotationProperty> _textAnnotationPropertyList;

    /**
     * Field _numberAnnotationPropertyList.
     */
    private java.util.List<org.pathwayeditor.businessobjects.exchange.castor.NumberAnnotationProperty> _numberAnnotationPropertyList;

    /**
     * Field _booleanAnnotationPropertyList.
     */
    private java.util.List<org.pathwayeditor.businessobjects.exchange.castor.BooleanAnnotationProperty> _booleanAnnotationPropertyList;

    /**
     * Field _integerAnnotationPropertyList.
     */
    private java.util.List<org.pathwayeditor.businessobjects.exchange.castor.IntegerAnnotationProperty> _integerAnnotationPropertyList;


      //----------------/
     //- Constructors -/
    //----------------/

    public Annotations() {
        super();
        this._textAnnotationPropertyList = new java.util.ArrayList<org.pathwayeditor.businessobjects.exchange.castor.TextAnnotationProperty>();
        this._numberAnnotationPropertyList = new java.util.ArrayList<org.pathwayeditor.businessobjects.exchange.castor.NumberAnnotationProperty>();
        this._booleanAnnotationPropertyList = new java.util.ArrayList<org.pathwayeditor.businessobjects.exchange.castor.BooleanAnnotationProperty>();
        this._integerAnnotationPropertyList = new java.util.ArrayList<org.pathwayeditor.businessobjects.exchange.castor.IntegerAnnotationProperty>();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * 
     * 
     * @param vBooleanAnnotationProperty
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addBooleanAnnotationProperty(
            final org.pathwayeditor.businessobjects.exchange.castor.BooleanAnnotationProperty vBooleanAnnotationProperty)
    throws java.lang.IndexOutOfBoundsException {
        this._booleanAnnotationPropertyList.add(vBooleanAnnotationProperty);
    }

    /**
     * 
     * 
     * @param index
     * @param vBooleanAnnotationProperty
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addBooleanAnnotationProperty(
            final int index,
            final org.pathwayeditor.businessobjects.exchange.castor.BooleanAnnotationProperty vBooleanAnnotationProperty)
    throws java.lang.IndexOutOfBoundsException {
        this._booleanAnnotationPropertyList.add(index, vBooleanAnnotationProperty);
    }

    /**
     * 
     * 
     * @param vIntegerAnnotationProperty
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addIntegerAnnotationProperty(
            final org.pathwayeditor.businessobjects.exchange.castor.IntegerAnnotationProperty vIntegerAnnotationProperty)
    throws java.lang.IndexOutOfBoundsException {
        this._integerAnnotationPropertyList.add(vIntegerAnnotationProperty);
    }

    /**
     * 
     * 
     * @param index
     * @param vIntegerAnnotationProperty
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addIntegerAnnotationProperty(
            final int index,
            final org.pathwayeditor.businessobjects.exchange.castor.IntegerAnnotationProperty vIntegerAnnotationProperty)
    throws java.lang.IndexOutOfBoundsException {
        this._integerAnnotationPropertyList.add(index, vIntegerAnnotationProperty);
    }

    /**
     * 
     * 
     * @param vNumberAnnotationProperty
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addNumberAnnotationProperty(
            final org.pathwayeditor.businessobjects.exchange.castor.NumberAnnotationProperty vNumberAnnotationProperty)
    throws java.lang.IndexOutOfBoundsException {
        this._numberAnnotationPropertyList.add(vNumberAnnotationProperty);
    }

    /**
     * 
     * 
     * @param index
     * @param vNumberAnnotationProperty
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addNumberAnnotationProperty(
            final int index,
            final org.pathwayeditor.businessobjects.exchange.castor.NumberAnnotationProperty vNumberAnnotationProperty)
    throws java.lang.IndexOutOfBoundsException {
        this._numberAnnotationPropertyList.add(index, vNumberAnnotationProperty);
    }

    /**
     * 
     * 
     * @param vTextAnnotationProperty
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addTextAnnotationProperty(
            final org.pathwayeditor.businessobjects.exchange.castor.TextAnnotationProperty vTextAnnotationProperty)
    throws java.lang.IndexOutOfBoundsException {
        this._textAnnotationPropertyList.add(vTextAnnotationProperty);
    }

    /**
     * 
     * 
     * @param index
     * @param vTextAnnotationProperty
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addTextAnnotationProperty(
            final int index,
            final org.pathwayeditor.businessobjects.exchange.castor.TextAnnotationProperty vTextAnnotationProperty)
    throws java.lang.IndexOutOfBoundsException {
        this._textAnnotationPropertyList.add(index, vTextAnnotationProperty);
    }

    /**
     * Method enumerateBooleanAnnotationProperty.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration<? extends org.pathwayeditor.businessobjects.exchange.castor.BooleanAnnotationProperty> enumerateBooleanAnnotationProperty(
    ) {
        return java.util.Collections.enumeration(this._booleanAnnotationPropertyList);
    }

    /**
     * Method enumerateIntegerAnnotationProperty.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration<? extends org.pathwayeditor.businessobjects.exchange.castor.IntegerAnnotationProperty> enumerateIntegerAnnotationProperty(
    ) {
        return java.util.Collections.enumeration(this._integerAnnotationPropertyList);
    }

    /**
     * Method enumerateNumberAnnotationProperty.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration<? extends org.pathwayeditor.businessobjects.exchange.castor.NumberAnnotationProperty> enumerateNumberAnnotationProperty(
    ) {
        return java.util.Collections.enumeration(this._numberAnnotationPropertyList);
    }

    /**
     * Method enumerateTextAnnotationProperty.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration<? extends org.pathwayeditor.businessobjects.exchange.castor.TextAnnotationProperty> enumerateTextAnnotationProperty(
    ) {
        return java.util.Collections.enumeration(this._textAnnotationPropertyList);
    }

    /**
     * Method getBooleanAnnotationProperty.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the
     * org.pathwayeditor.businessobjects.exchange.castor.BooleanAnnotationProperty
     * at the given index
     */
    public org.pathwayeditor.businessobjects.exchange.castor.BooleanAnnotationProperty getBooleanAnnotationProperty(
            final int index)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._booleanAnnotationPropertyList.size()) {
            throw new IndexOutOfBoundsException("getBooleanAnnotationProperty: Index value '" + index + "' not in range [0.." + (this._booleanAnnotationPropertyList.size() - 1) + "]");
        }

        return (org.pathwayeditor.businessobjects.exchange.castor.BooleanAnnotationProperty) _booleanAnnotationPropertyList.get(index);
    }

    /**
     * Method getBooleanAnnotationProperty.Returns the contents of
     * the collection in an Array.  <p>Note:  Just in case the
     * collection contents are changing in another thread, we pass
     * a 0-length Array of the correct type into the API call. 
     * This way we <i>know</i> that the Array returned is of
     * exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public org.pathwayeditor.businessobjects.exchange.castor.BooleanAnnotationProperty[] getBooleanAnnotationProperty(
    ) {
        org.pathwayeditor.businessobjects.exchange.castor.BooleanAnnotationProperty[] array = new org.pathwayeditor.businessobjects.exchange.castor.BooleanAnnotationProperty[0];
        return (org.pathwayeditor.businessobjects.exchange.castor.BooleanAnnotationProperty[]) this._booleanAnnotationPropertyList.toArray(array);
    }

    /**
     * Method getBooleanAnnotationPropertyCount.
     * 
     * @return the size of this collection
     */
    public int getBooleanAnnotationPropertyCount(
    ) {
        return this._booleanAnnotationPropertyList.size();
    }

    /**
     * Returns the value of field 'choiceValue'. The field
     * 'choiceValue' has the following description: Internal choice
     * value storage
     * 
     * @return the value of field 'ChoiceValue'.
     */
    public java.lang.Object getChoiceValue(
    ) {
        return this._choiceValue;
    }

    /**
     * Method getIntegerAnnotationProperty.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the
     * org.pathwayeditor.businessobjects.exchange.castor.IntegerAnnotationProperty
     * at the given index
     */
    public org.pathwayeditor.businessobjects.exchange.castor.IntegerAnnotationProperty getIntegerAnnotationProperty(
            final int index)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._integerAnnotationPropertyList.size()) {
            throw new IndexOutOfBoundsException("getIntegerAnnotationProperty: Index value '" + index + "' not in range [0.." + (this._integerAnnotationPropertyList.size() - 1) + "]");
        }

        return (org.pathwayeditor.businessobjects.exchange.castor.IntegerAnnotationProperty) _integerAnnotationPropertyList.get(index);
    }

    /**
     * Method getIntegerAnnotationProperty.Returns the contents of
     * the collection in an Array.  <p>Note:  Just in case the
     * collection contents are changing in another thread, we pass
     * a 0-length Array of the correct type into the API call. 
     * This way we <i>know</i> that the Array returned is of
     * exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public org.pathwayeditor.businessobjects.exchange.castor.IntegerAnnotationProperty[] getIntegerAnnotationProperty(
    ) {
        org.pathwayeditor.businessobjects.exchange.castor.IntegerAnnotationProperty[] array = new org.pathwayeditor.businessobjects.exchange.castor.IntegerAnnotationProperty[0];
        return (org.pathwayeditor.businessobjects.exchange.castor.IntegerAnnotationProperty[]) this._integerAnnotationPropertyList.toArray(array);
    }

    /**
     * Method getIntegerAnnotationPropertyCount.
     * 
     * @return the size of this collection
     */
    public int getIntegerAnnotationPropertyCount(
    ) {
        return this._integerAnnotationPropertyList.size();
    }

    /**
     * Method getNumberAnnotationProperty.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the
     * org.pathwayeditor.businessobjects.exchange.castor.NumberAnnotationProperty
     * at the given index
     */
    public org.pathwayeditor.businessobjects.exchange.castor.NumberAnnotationProperty getNumberAnnotationProperty(
            final int index)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._numberAnnotationPropertyList.size()) {
            throw new IndexOutOfBoundsException("getNumberAnnotationProperty: Index value '" + index + "' not in range [0.." + (this._numberAnnotationPropertyList.size() - 1) + "]");
        }

        return (org.pathwayeditor.businessobjects.exchange.castor.NumberAnnotationProperty) _numberAnnotationPropertyList.get(index);
    }

    /**
     * Method getNumberAnnotationProperty.Returns the contents of
     * the collection in an Array.  <p>Note:  Just in case the
     * collection contents are changing in another thread, we pass
     * a 0-length Array of the correct type into the API call. 
     * This way we <i>know</i> that the Array returned is of
     * exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public org.pathwayeditor.businessobjects.exchange.castor.NumberAnnotationProperty[] getNumberAnnotationProperty(
    ) {
        org.pathwayeditor.businessobjects.exchange.castor.NumberAnnotationProperty[] array = new org.pathwayeditor.businessobjects.exchange.castor.NumberAnnotationProperty[0];
        return (org.pathwayeditor.businessobjects.exchange.castor.NumberAnnotationProperty[]) this._numberAnnotationPropertyList.toArray(array);
    }

    /**
     * Method getNumberAnnotationPropertyCount.
     * 
     * @return the size of this collection
     */
    public int getNumberAnnotationPropertyCount(
    ) {
        return this._numberAnnotationPropertyList.size();
    }

    /**
     * Method getTextAnnotationProperty.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the
     * org.pathwayeditor.businessobjects.exchange.castor.TextAnnotationProperty
     * at the given index
     */
    public org.pathwayeditor.businessobjects.exchange.castor.TextAnnotationProperty getTextAnnotationProperty(
            final int index)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._textAnnotationPropertyList.size()) {
            throw new IndexOutOfBoundsException("getTextAnnotationProperty: Index value '" + index + "' not in range [0.." + (this._textAnnotationPropertyList.size() - 1) + "]");
        }

        return (org.pathwayeditor.businessobjects.exchange.castor.TextAnnotationProperty) _textAnnotationPropertyList.get(index);
    }

    /**
     * Method getTextAnnotationProperty.Returns the contents of the
     * collection in an Array.  <p>Note:  Just in case the
     * collection contents are changing in another thread, we pass
     * a 0-length Array of the correct type into the API call. 
     * This way we <i>know</i> that the Array returned is of
     * exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public org.pathwayeditor.businessobjects.exchange.castor.TextAnnotationProperty[] getTextAnnotationProperty(
    ) {
        org.pathwayeditor.businessobjects.exchange.castor.TextAnnotationProperty[] array = new org.pathwayeditor.businessobjects.exchange.castor.TextAnnotationProperty[0];
        return (org.pathwayeditor.businessobjects.exchange.castor.TextAnnotationProperty[]) this._textAnnotationPropertyList.toArray(array);
    }

    /**
     * Method getTextAnnotationPropertyCount.
     * 
     * @return the size of this collection
     */
    public int getTextAnnotationPropertyCount(
    ) {
        return this._textAnnotationPropertyList.size();
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
     * Method iterateBooleanAnnotationProperty.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator<? extends org.pathwayeditor.businessobjects.exchange.castor.BooleanAnnotationProperty> iterateBooleanAnnotationProperty(
    ) {
        return this._booleanAnnotationPropertyList.iterator();
    }

    /**
     * Method iterateIntegerAnnotationProperty.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator<? extends org.pathwayeditor.businessobjects.exchange.castor.IntegerAnnotationProperty> iterateIntegerAnnotationProperty(
    ) {
        return this._integerAnnotationPropertyList.iterator();
    }

    /**
     * Method iterateNumberAnnotationProperty.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator<? extends org.pathwayeditor.businessobjects.exchange.castor.NumberAnnotationProperty> iterateNumberAnnotationProperty(
    ) {
        return this._numberAnnotationPropertyList.iterator();
    }

    /**
     * Method iterateTextAnnotationProperty.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator<? extends org.pathwayeditor.businessobjects.exchange.castor.TextAnnotationProperty> iterateTextAnnotationProperty(
    ) {
        return this._textAnnotationPropertyList.iterator();
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
    public void removeAllBooleanAnnotationProperty(
    ) {
        this._booleanAnnotationPropertyList.clear();
    }

    /**
     */
    public void removeAllIntegerAnnotationProperty(
    ) {
        this._integerAnnotationPropertyList.clear();
    }

    /**
     */
    public void removeAllNumberAnnotationProperty(
    ) {
        this._numberAnnotationPropertyList.clear();
    }

    /**
     */
    public void removeAllTextAnnotationProperty(
    ) {
        this._textAnnotationPropertyList.clear();
    }

    /**
     * Method removeBooleanAnnotationProperty.
     * 
     * @param vBooleanAnnotationProperty
     * @return true if the object was removed from the collection.
     */
    public boolean removeBooleanAnnotationProperty(
            final org.pathwayeditor.businessobjects.exchange.castor.BooleanAnnotationProperty vBooleanAnnotationProperty) {
        boolean removed = _booleanAnnotationPropertyList.remove(vBooleanAnnotationProperty);
        return removed;
    }

    /**
     * Method removeBooleanAnnotationPropertyAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public org.pathwayeditor.businessobjects.exchange.castor.BooleanAnnotationProperty removeBooleanAnnotationPropertyAt(
            final int index) {
        java.lang.Object obj = this._booleanAnnotationPropertyList.remove(index);
        return (org.pathwayeditor.businessobjects.exchange.castor.BooleanAnnotationProperty) obj;
    }

    /**
     * Method removeIntegerAnnotationProperty.
     * 
     * @param vIntegerAnnotationProperty
     * @return true if the object was removed from the collection.
     */
    public boolean removeIntegerAnnotationProperty(
            final org.pathwayeditor.businessobjects.exchange.castor.IntegerAnnotationProperty vIntegerAnnotationProperty) {
        boolean removed = _integerAnnotationPropertyList.remove(vIntegerAnnotationProperty);
        return removed;
    }

    /**
     * Method removeIntegerAnnotationPropertyAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public org.pathwayeditor.businessobjects.exchange.castor.IntegerAnnotationProperty removeIntegerAnnotationPropertyAt(
            final int index) {
        java.lang.Object obj = this._integerAnnotationPropertyList.remove(index);
        return (org.pathwayeditor.businessobjects.exchange.castor.IntegerAnnotationProperty) obj;
    }

    /**
     * Method removeNumberAnnotationProperty.
     * 
     * @param vNumberAnnotationProperty
     * @return true if the object was removed from the collection.
     */
    public boolean removeNumberAnnotationProperty(
            final org.pathwayeditor.businessobjects.exchange.castor.NumberAnnotationProperty vNumberAnnotationProperty) {
        boolean removed = _numberAnnotationPropertyList.remove(vNumberAnnotationProperty);
        return removed;
    }

    /**
     * Method removeNumberAnnotationPropertyAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public org.pathwayeditor.businessobjects.exchange.castor.NumberAnnotationProperty removeNumberAnnotationPropertyAt(
            final int index) {
        java.lang.Object obj = this._numberAnnotationPropertyList.remove(index);
        return (org.pathwayeditor.businessobjects.exchange.castor.NumberAnnotationProperty) obj;
    }

    /**
     * Method removeTextAnnotationProperty.
     * 
     * @param vTextAnnotationProperty
     * @return true if the object was removed from the collection.
     */
    public boolean removeTextAnnotationProperty(
            final org.pathwayeditor.businessobjects.exchange.castor.TextAnnotationProperty vTextAnnotationProperty) {
        boolean removed = _textAnnotationPropertyList.remove(vTextAnnotationProperty);
        return removed;
    }

    /**
     * Method removeTextAnnotationPropertyAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public org.pathwayeditor.businessobjects.exchange.castor.TextAnnotationProperty removeTextAnnotationPropertyAt(
            final int index) {
        java.lang.Object obj = this._textAnnotationPropertyList.remove(index);
        return (org.pathwayeditor.businessobjects.exchange.castor.TextAnnotationProperty) obj;
    }

    /**
     * 
     * 
     * @param index
     * @param vBooleanAnnotationProperty
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setBooleanAnnotationProperty(
            final int index,
            final org.pathwayeditor.businessobjects.exchange.castor.BooleanAnnotationProperty vBooleanAnnotationProperty)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._booleanAnnotationPropertyList.size()) {
            throw new IndexOutOfBoundsException("setBooleanAnnotationProperty: Index value '" + index + "' not in range [0.." + (this._booleanAnnotationPropertyList.size() - 1) + "]");
        }

        this._booleanAnnotationPropertyList.set(index, vBooleanAnnotationProperty);
    }

    /**
     * 
     * 
     * @param vBooleanAnnotationPropertyArray
     */
    public void setBooleanAnnotationProperty(
            final org.pathwayeditor.businessobjects.exchange.castor.BooleanAnnotationProperty[] vBooleanAnnotationPropertyArray) {
        //-- copy array
        _booleanAnnotationPropertyList.clear();

        for (int i = 0; i < vBooleanAnnotationPropertyArray.length; i++) {
                this._booleanAnnotationPropertyList.add(vBooleanAnnotationPropertyArray[i]);
        }
    }

    /**
     * 
     * 
     * @param index
     * @param vIntegerAnnotationProperty
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setIntegerAnnotationProperty(
            final int index,
            final org.pathwayeditor.businessobjects.exchange.castor.IntegerAnnotationProperty vIntegerAnnotationProperty)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._integerAnnotationPropertyList.size()) {
            throw new IndexOutOfBoundsException("setIntegerAnnotationProperty: Index value '" + index + "' not in range [0.." + (this._integerAnnotationPropertyList.size() - 1) + "]");
        }

        this._integerAnnotationPropertyList.set(index, vIntegerAnnotationProperty);
    }

    /**
     * 
     * 
     * @param vIntegerAnnotationPropertyArray
     */
    public void setIntegerAnnotationProperty(
            final org.pathwayeditor.businessobjects.exchange.castor.IntegerAnnotationProperty[] vIntegerAnnotationPropertyArray) {
        //-- copy array
        _integerAnnotationPropertyList.clear();

        for (int i = 0; i < vIntegerAnnotationPropertyArray.length; i++) {
                this._integerAnnotationPropertyList.add(vIntegerAnnotationPropertyArray[i]);
        }
    }

    /**
     * 
     * 
     * @param index
     * @param vNumberAnnotationProperty
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setNumberAnnotationProperty(
            final int index,
            final org.pathwayeditor.businessobjects.exchange.castor.NumberAnnotationProperty vNumberAnnotationProperty)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._numberAnnotationPropertyList.size()) {
            throw new IndexOutOfBoundsException("setNumberAnnotationProperty: Index value '" + index + "' not in range [0.." + (this._numberAnnotationPropertyList.size() - 1) + "]");
        }

        this._numberAnnotationPropertyList.set(index, vNumberAnnotationProperty);
    }

    /**
     * 
     * 
     * @param vNumberAnnotationPropertyArray
     */
    public void setNumberAnnotationProperty(
            final org.pathwayeditor.businessobjects.exchange.castor.NumberAnnotationProperty[] vNumberAnnotationPropertyArray) {
        //-- copy array
        _numberAnnotationPropertyList.clear();

        for (int i = 0; i < vNumberAnnotationPropertyArray.length; i++) {
                this._numberAnnotationPropertyList.add(vNumberAnnotationPropertyArray[i]);
        }
    }

    /**
     * 
     * 
     * @param index
     * @param vTextAnnotationProperty
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setTextAnnotationProperty(
            final int index,
            final org.pathwayeditor.businessobjects.exchange.castor.TextAnnotationProperty vTextAnnotationProperty)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._textAnnotationPropertyList.size()) {
            throw new IndexOutOfBoundsException("setTextAnnotationProperty: Index value '" + index + "' not in range [0.." + (this._textAnnotationPropertyList.size() - 1) + "]");
        }

        this._textAnnotationPropertyList.set(index, vTextAnnotationProperty);
    }

    /**
     * 
     * 
     * @param vTextAnnotationPropertyArray
     */
    public void setTextAnnotationProperty(
            final org.pathwayeditor.businessobjects.exchange.castor.TextAnnotationProperty[] vTextAnnotationPropertyArray) {
        //-- copy array
        _textAnnotationPropertyList.clear();

        for (int i = 0; i < vTextAnnotationPropertyArray.length; i++) {
                this._textAnnotationPropertyList.add(vTextAnnotationPropertyArray[i]);
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
     * org.pathwayeditor.businessobjects.exchange.castor.Annotations
     */
    public static org.pathwayeditor.businessobjects.exchange.castor.Annotations unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (org.pathwayeditor.businessobjects.exchange.castor.Annotations) org.exolab.castor.xml.Unmarshaller.unmarshal(org.pathwayeditor.businessobjects.exchange.castor.Annotations.class, reader);
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
