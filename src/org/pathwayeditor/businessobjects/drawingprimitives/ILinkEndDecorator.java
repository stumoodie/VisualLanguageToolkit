package org.pathwayeditor.businessobjects.drawingprimitives;

import org.pathwayeditor.businessobjects.drawingprimitives.attributes.Size;
import org.pathwayeditor.businessobjects.typedefn.ILinkEndDefinition;

public interface ILinkEndDecorator {

	ILinkTerminus getLinkTerminus();
	ILinkEndDefinition getLinkEndDefinition();
	
	Size getSize () ;
	void setSize ( Size size ) ;
	
}
