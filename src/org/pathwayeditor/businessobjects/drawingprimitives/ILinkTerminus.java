package org.pathwayeditor.businessobjects.drawingprimitives;

import org.pathwayeditor.businessobjects.typedefn.ILinkEndDefinition.LinkEndType;

public interface ILinkTerminus {

	
	short getGap();
	
	void setGap(short newGap);
	
	ILinkAttribute getOwningLink() ;
	
	LinkEndType getLinkEndType () ;

}
