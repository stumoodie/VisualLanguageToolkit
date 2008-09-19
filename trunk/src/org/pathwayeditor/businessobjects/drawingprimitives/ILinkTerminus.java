package org.pathwayeditor.businessobjects.drawingprimitives;

import org.pathwayeditor.businessobjects.typedefn.ILinkEndDefinition.LinkEndType;

public interface ILinkTerminus {

	/**
	 * Gets the gap between the Link terminus and the targert.
	 * @return the gap.
	 */
	short getGap();
	
	/**
	 * Sets the gap between the terminus and the target.
	 * @param newGap the gap. Cannot be less than zero.
	 */
	void setGap(short newGap);
	
	/**
	 * Returns the link that linkterminus is connected to.
	 * @return the link. Cannot be null.
	 */
	ILinkAttribute getOwningLink() ;
	
	/**
	 * Gets the type of this Link Terminus.
	 * @return the end type. Cannot be null.
	 */
	LinkEndType getLinkEndType () ;

}
