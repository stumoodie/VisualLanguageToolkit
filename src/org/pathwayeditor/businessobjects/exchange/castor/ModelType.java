/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3</a>, using an XML
 * Schema.
 * $Id$
 */

package org.pathwayeditor.businessobjects.exchange.castor;

/**
 * Class ModelType.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public abstract class ModelType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _rootNode.
     */
    private org.pathwayeditor.businessobjects.exchange.castor.RootNode _rootNode;


      //----------------/
     //- Constructors -/
    //----------------/

    public ModelType() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'rootNode'.
     * 
     * @return the value of field 'RootNode'.
     */
    public org.pathwayeditor.businessobjects.exchange.castor.RootNode getRootNode(
    ) {
        return this._rootNode;
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
     * Sets the value of field 'rootNode'.
     * 
     * @param rootNode the value of field 'rootNode'.
     */
    public void setRootNode(
            final org.pathwayeditor.businessobjects.exchange.castor.RootNode rootNode) {
        this._rootNode = rootNode;
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
