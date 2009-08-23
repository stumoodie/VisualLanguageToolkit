/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3</a>, using an XML
 * Schema.
 * $Id$
 */

package org.pathwayeditor.businessobjects.exchange.castor;

/**
 * Class CompoundNodeType.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public abstract class CompoundNodeType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _nodeId.
     */
    private long _nodeId;

    /**
     * keeps track of state for field: _nodeId
     */
    private boolean _has_nodeId;

    /**
     * Field _childModel.
     */
    private org.pathwayeditor.businessobjects.exchange.castor.ChildModel _childModel;


      //----------------/
     //- Constructors -/
    //----------------/

    public CompoundNodeType() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     */
    public void deleteNodeId(
    ) {
        this._has_nodeId= false;
    }

    /**
     * Returns the value of field 'childModel'.
     * 
     * @return the value of field 'ChildModel'.
     */
    public org.pathwayeditor.businessobjects.exchange.castor.ChildModel getChildModel(
    ) {
        return this._childModel;
    }

    /**
     * Returns the value of field 'nodeId'.
     * 
     * @return the value of field 'NodeId'.
     */
    public long getNodeId(
    ) {
        return this._nodeId;
    }

    /**
     * Method hasNodeId.
     * 
     * @return true if at least one NodeId has been added
     */
    public boolean hasNodeId(
    ) {
        return this._has_nodeId;
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
     * Sets the value of field 'childModel'.
     * 
     * @param childModel the value of field 'childModel'.
     */
    public void setChildModel(
            final org.pathwayeditor.businessobjects.exchange.castor.ChildModel childModel) {
        this._childModel = childModel;
    }

    /**
     * Sets the value of field 'nodeId'.
     * 
     * @param nodeId the value of field 'nodeId'.
     */
    public void setNodeId(
            final long nodeId) {
        this._nodeId = nodeId;
        this._has_nodeId = true;
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
