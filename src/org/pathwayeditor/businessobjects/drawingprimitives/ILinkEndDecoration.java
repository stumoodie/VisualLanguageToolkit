package org.pathwayeditor.businessobjects.drawingprimitives;

import org.pathwayeditor.businessobjects.typedefn.ILinkEndDefinition;

public interface ILinkEndDecoration {

//	ILinkTerminus getLinkTerminus();
	ILinkEndDefinition getLinkEndDefinition();
	ILinkEnd getLinkEnd();
	
	
	int getGap();
	
	/**
	 * Set the gap between the link terminus and link end decorators. May not
	 * be supported if <code>canSetGap()</code> is false. 
	 * @param newGap The size of the new gap. Must be <code>>=0</code>.
	 * @throws IllegalArgumentException if <code>newGap < 0</code>
	 * @throws UnsupportedOperationException if <code>canSetGap() == false</code>.
	 * @throws IllegalStateException of <code>getLinkEndDefiniton().isGapEditable() == false</code>.
	 */
	void setGap(int newGap);
}
