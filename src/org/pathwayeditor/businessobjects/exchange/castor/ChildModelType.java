/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3</a>, using an XML
 * Schema.
 * $Id$
 */

package org.pathwayeditor.businessobjects.exchange.castor;

/**
 * Class ChildModelType.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public abstract class ChildModelType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _shapeNodeList.
     */
    private java.util.List<org.pathwayeditor.businessobjects.exchange.castor.ShapeNode> _shapeNodeList;

    /**
     * Field _labelNodeList.
     */
    private java.util.List<org.pathwayeditor.businessobjects.exchange.castor.LabelNode> _labelNodeList;

    /**
     * Field _linkEdgeList.
     */
    private java.util.List<org.pathwayeditor.businessobjects.exchange.castor.LinkEdge> _linkEdgeList;


      //----------------/
     //- Constructors -/
    //----------------/

    public ChildModelType() {
        super();
        this._shapeNodeList = new java.util.ArrayList<org.pathwayeditor.businessobjects.exchange.castor.ShapeNode>();
        this._labelNodeList = new java.util.ArrayList<org.pathwayeditor.businessobjects.exchange.castor.LabelNode>();
        this._linkEdgeList = new java.util.ArrayList<org.pathwayeditor.businessobjects.exchange.castor.LinkEdge>();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * 
     * 
     * @param vLabelNode
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addLabelNode(
            final org.pathwayeditor.businessobjects.exchange.castor.LabelNode vLabelNode)
    throws java.lang.IndexOutOfBoundsException {
        this._labelNodeList.add(vLabelNode);
    }

    /**
     * 
     * 
     * @param index
     * @param vLabelNode
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addLabelNode(
            final int index,
            final org.pathwayeditor.businessobjects.exchange.castor.LabelNode vLabelNode)
    throws java.lang.IndexOutOfBoundsException {
        this._labelNodeList.add(index, vLabelNode);
    }

    /**
     * 
     * 
     * @param vLinkEdge
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addLinkEdge(
            final org.pathwayeditor.businessobjects.exchange.castor.LinkEdge vLinkEdge)
    throws java.lang.IndexOutOfBoundsException {
        this._linkEdgeList.add(vLinkEdge);
    }

    /**
     * 
     * 
     * @param index
     * @param vLinkEdge
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addLinkEdge(
            final int index,
            final org.pathwayeditor.businessobjects.exchange.castor.LinkEdge vLinkEdge)
    throws java.lang.IndexOutOfBoundsException {
        this._linkEdgeList.add(index, vLinkEdge);
    }

    /**
     * 
     * 
     * @param vShapeNode
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addShapeNode(
            final org.pathwayeditor.businessobjects.exchange.castor.ShapeNode vShapeNode)
    throws java.lang.IndexOutOfBoundsException {
        this._shapeNodeList.add(vShapeNode);
    }

    /**
     * 
     * 
     * @param index
     * @param vShapeNode
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addShapeNode(
            final int index,
            final org.pathwayeditor.businessobjects.exchange.castor.ShapeNode vShapeNode)
    throws java.lang.IndexOutOfBoundsException {
        this._shapeNodeList.add(index, vShapeNode);
    }

    /**
     * Method enumerateLabelNode.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration<? extends org.pathwayeditor.businessobjects.exchange.castor.LabelNode> enumerateLabelNode(
    ) {
        return java.util.Collections.enumeration(this._labelNodeList);
    }

    /**
     * Method enumerateLinkEdge.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration<? extends org.pathwayeditor.businessobjects.exchange.castor.LinkEdge> enumerateLinkEdge(
    ) {
        return java.util.Collections.enumeration(this._linkEdgeList);
    }

    /**
     * Method enumerateShapeNode.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration<? extends org.pathwayeditor.businessobjects.exchange.castor.ShapeNode> enumerateShapeNode(
    ) {
        return java.util.Collections.enumeration(this._shapeNodeList);
    }

    /**
     * Method getLabelNode.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the
     * org.pathwayeditor.businessobjects.exchange.castor.LabelNode
     * at the given index
     */
    public org.pathwayeditor.businessobjects.exchange.castor.LabelNode getLabelNode(
            final int index)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._labelNodeList.size()) {
            throw new IndexOutOfBoundsException("getLabelNode: Index value '" + index + "' not in range [0.." + (this._labelNodeList.size() - 1) + "]");
        }

        return (org.pathwayeditor.businessobjects.exchange.castor.LabelNode) _labelNodeList.get(index);
    }

    /**
     * Method getLabelNode.Returns the contents of the collection
     * in an Array.  <p>Note:  Just in case the collection contents
     * are changing in another thread, we pass a 0-length Array of
     * the correct type into the API call.  This way we <i>know</i>
     * that the Array returned is of exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public org.pathwayeditor.businessobjects.exchange.castor.LabelNode[] getLabelNode(
    ) {
        org.pathwayeditor.businessobjects.exchange.castor.LabelNode[] array = new org.pathwayeditor.businessobjects.exchange.castor.LabelNode[0];
        return (org.pathwayeditor.businessobjects.exchange.castor.LabelNode[]) this._labelNodeList.toArray(array);
    }

    /**
     * Method getLabelNodeCount.
     * 
     * @return the size of this collection
     */
    public int getLabelNodeCount(
    ) {
        return this._labelNodeList.size();
    }

    /**
     * Method getLinkEdge.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the
     * org.pathwayeditor.businessobjects.exchange.castor.LinkEdge
     * at the given index
     */
    public org.pathwayeditor.businessobjects.exchange.castor.LinkEdge getLinkEdge(
            final int index)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._linkEdgeList.size()) {
            throw new IndexOutOfBoundsException("getLinkEdge: Index value '" + index + "' not in range [0.." + (this._linkEdgeList.size() - 1) + "]");
        }

        return (org.pathwayeditor.businessobjects.exchange.castor.LinkEdge) _linkEdgeList.get(index);
    }

    /**
     * Method getLinkEdge.Returns the contents of the collection in
     * an Array.  <p>Note:  Just in case the collection contents
     * are changing in another thread, we pass a 0-length Array of
     * the correct type into the API call.  This way we <i>know</i>
     * that the Array returned is of exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public org.pathwayeditor.businessobjects.exchange.castor.LinkEdge[] getLinkEdge(
    ) {
        org.pathwayeditor.businessobjects.exchange.castor.LinkEdge[] array = new org.pathwayeditor.businessobjects.exchange.castor.LinkEdge[0];
        return (org.pathwayeditor.businessobjects.exchange.castor.LinkEdge[]) this._linkEdgeList.toArray(array);
    }

    /**
     * Method getLinkEdgeCount.
     * 
     * @return the size of this collection
     */
    public int getLinkEdgeCount(
    ) {
        return this._linkEdgeList.size();
    }

    /**
     * Method getShapeNode.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the
     * org.pathwayeditor.businessobjects.exchange.castor.ShapeNode
     * at the given index
     */
    public org.pathwayeditor.businessobjects.exchange.castor.ShapeNode getShapeNode(
            final int index)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._shapeNodeList.size()) {
            throw new IndexOutOfBoundsException("getShapeNode: Index value '" + index + "' not in range [0.." + (this._shapeNodeList.size() - 1) + "]");
        }

        return (org.pathwayeditor.businessobjects.exchange.castor.ShapeNode) _shapeNodeList.get(index);
    }

    /**
     * Method getShapeNode.Returns the contents of the collection
     * in an Array.  <p>Note:  Just in case the collection contents
     * are changing in another thread, we pass a 0-length Array of
     * the correct type into the API call.  This way we <i>know</i>
     * that the Array returned is of exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public org.pathwayeditor.businessobjects.exchange.castor.ShapeNode[] getShapeNode(
    ) {
        org.pathwayeditor.businessobjects.exchange.castor.ShapeNode[] array = new org.pathwayeditor.businessobjects.exchange.castor.ShapeNode[0];
        return (org.pathwayeditor.businessobjects.exchange.castor.ShapeNode[]) this._shapeNodeList.toArray(array);
    }

    /**
     * Method getShapeNodeCount.
     * 
     * @return the size of this collection
     */
    public int getShapeNodeCount(
    ) {
        return this._shapeNodeList.size();
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
     * Method iterateLabelNode.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator<? extends org.pathwayeditor.businessobjects.exchange.castor.LabelNode> iterateLabelNode(
    ) {
        return this._labelNodeList.iterator();
    }

    /**
     * Method iterateLinkEdge.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator<? extends org.pathwayeditor.businessobjects.exchange.castor.LinkEdge> iterateLinkEdge(
    ) {
        return this._linkEdgeList.iterator();
    }

    /**
     * Method iterateShapeNode.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator<? extends org.pathwayeditor.businessobjects.exchange.castor.ShapeNode> iterateShapeNode(
    ) {
        return this._shapeNodeList.iterator();
    }

    /**
     */
    public void removeAllLabelNode(
    ) {
        this._labelNodeList.clear();
    }

    /**
     */
    public void removeAllLinkEdge(
    ) {
        this._linkEdgeList.clear();
    }

    /**
     */
    public void removeAllShapeNode(
    ) {
        this._shapeNodeList.clear();
    }

    /**
     * Method removeLabelNode.
     * 
     * @param vLabelNode
     * @return true if the object was removed from the collection.
     */
    public boolean removeLabelNode(
            final org.pathwayeditor.businessobjects.exchange.castor.LabelNode vLabelNode) {
        boolean removed = _labelNodeList.remove(vLabelNode);
        return removed;
    }

    /**
     * Method removeLabelNodeAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public org.pathwayeditor.businessobjects.exchange.castor.LabelNode removeLabelNodeAt(
            final int index) {
        java.lang.Object obj = this._labelNodeList.remove(index);
        return (org.pathwayeditor.businessobjects.exchange.castor.LabelNode) obj;
    }

    /**
     * Method removeLinkEdge.
     * 
     * @param vLinkEdge
     * @return true if the object was removed from the collection.
     */
    public boolean removeLinkEdge(
            final org.pathwayeditor.businessobjects.exchange.castor.LinkEdge vLinkEdge) {
        boolean removed = _linkEdgeList.remove(vLinkEdge);
        return removed;
    }

    /**
     * Method removeLinkEdgeAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public org.pathwayeditor.businessobjects.exchange.castor.LinkEdge removeLinkEdgeAt(
            final int index) {
        java.lang.Object obj = this._linkEdgeList.remove(index);
        return (org.pathwayeditor.businessobjects.exchange.castor.LinkEdge) obj;
    }

    /**
     * Method removeShapeNode.
     * 
     * @param vShapeNode
     * @return true if the object was removed from the collection.
     */
    public boolean removeShapeNode(
            final org.pathwayeditor.businessobjects.exchange.castor.ShapeNode vShapeNode) {
        boolean removed = _shapeNodeList.remove(vShapeNode);
        return removed;
    }

    /**
     * Method removeShapeNodeAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public org.pathwayeditor.businessobjects.exchange.castor.ShapeNode removeShapeNodeAt(
            final int index) {
        java.lang.Object obj = this._shapeNodeList.remove(index);
        return (org.pathwayeditor.businessobjects.exchange.castor.ShapeNode) obj;
    }

    /**
     * 
     * 
     * @param index
     * @param vLabelNode
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setLabelNode(
            final int index,
            final org.pathwayeditor.businessobjects.exchange.castor.LabelNode vLabelNode)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._labelNodeList.size()) {
            throw new IndexOutOfBoundsException("setLabelNode: Index value '" + index + "' not in range [0.." + (this._labelNodeList.size() - 1) + "]");
        }

        this._labelNodeList.set(index, vLabelNode);
    }

    /**
     * 
     * 
     * @param vLabelNodeArray
     */
    public void setLabelNode(
            final org.pathwayeditor.businessobjects.exchange.castor.LabelNode[] vLabelNodeArray) {
        //-- copy array
        _labelNodeList.clear();

        for (int i = 0; i < vLabelNodeArray.length; i++) {
                this._labelNodeList.add(vLabelNodeArray[i]);
        }
    }

    /**
     * 
     * 
     * @param index
     * @param vLinkEdge
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setLinkEdge(
            final int index,
            final org.pathwayeditor.businessobjects.exchange.castor.LinkEdge vLinkEdge)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._linkEdgeList.size()) {
            throw new IndexOutOfBoundsException("setLinkEdge: Index value '" + index + "' not in range [0.." + (this._linkEdgeList.size() - 1) + "]");
        }

        this._linkEdgeList.set(index, vLinkEdge);
    }

    /**
     * 
     * 
     * @param vLinkEdgeArray
     */
    public void setLinkEdge(
            final org.pathwayeditor.businessobjects.exchange.castor.LinkEdge[] vLinkEdgeArray) {
        //-- copy array
        _linkEdgeList.clear();

        for (int i = 0; i < vLinkEdgeArray.length; i++) {
                this._linkEdgeList.add(vLinkEdgeArray[i]);
        }
    }

    /**
     * 
     * 
     * @param index
     * @param vShapeNode
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setShapeNode(
            final int index,
            final org.pathwayeditor.businessobjects.exchange.castor.ShapeNode vShapeNode)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._shapeNodeList.size()) {
            throw new IndexOutOfBoundsException("setShapeNode: Index value '" + index + "' not in range [0.." + (this._shapeNodeList.size() - 1) + "]");
        }

        this._shapeNodeList.set(index, vShapeNode);
    }

    /**
     * 
     * 
     * @param vShapeNodeArray
     */
    public void setShapeNode(
            final org.pathwayeditor.businessobjects.exchange.castor.ShapeNode[] vShapeNodeArray) {
        //-- copy array
        _shapeNodeList.clear();

        for (int i = 0; i < vShapeNodeArray.length; i++) {
                this._shapeNodeList.add(vShapeNodeArray[i]);
        }
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
