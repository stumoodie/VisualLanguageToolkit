/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3</a>, using an XML
 * Schema.
 * $Id$
 */

package org.pathwayeditor.businessobjects.exchange.castor.types;

/**
 * Enumeration LinkTerminusTypeTerminusTypeType.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public enum LinkTerminusTypeTerminusTypeType implements java.io.Serializable {


      //------------------/
     //- Enum Constants -/
    //------------------/

    /**
     * Constant S_T
     */
    S_T("S,T");

      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field value.
     */
    private final java.lang.String value;


      //----------------/
     //- Constructors -/
    //----------------/

    private LinkTerminusTypeTerminusTypeType(final java.lang.String value) {
        this.value = value;
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method fromValue.
     * 
     * @param value
     * @return the constant for this value
     */
    public static org.pathwayeditor.businessobjects.exchange.castor.types.LinkTerminusTypeTerminusTypeType fromValue(
            final java.lang.String value) {
        for (LinkTerminusTypeTerminusTypeType c: LinkTerminusTypeTerminusTypeType.values()) {
            if (c.value.equals(value)) {
                return c;
            }
        }
        throw new IllegalArgumentException(value);
    }

    /**
     * 
     * 
     * @param value
     */
    public void setValue(
            final java.lang.String value) {
    }

    /**
     * Method toString.
     * 
     * @return the value of this constant
     */
    public java.lang.String toString(
    ) {
        return this.value;
    }

    /**
     * Method value.
     * 
     * @return the value of this constant
     */
    public java.lang.String value(
    ) {
        return this.value;
    }

}
