package org.pathwayeditor.businessobjects.drawingprimitives;

import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LineStyle;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.Size;
import org.pathwayeditor.businessobjects.typedefn.IShapeObjectType;


public interface ILinkTerminusDecorator {
	
	RGB getFillColor () ;
	void setFillColor ( RGB newColor ) ;
	
	RGB getLineColor () ;
	void setLineColor ( RGB newColor ) ;
	
	Size getSize () ;
	void setSize ( Size newSize) ;
	
	int getLineSize () ;
	void setLineSize (int newLineSize) ;
	
	LineStyle getLineStyle();
	void setLineStyle(LineStyle lineStyle);
	
	int getLineWidth () ;
	void setLineWidth ( int newWidth) ;
	
	IShapeObjectType getObjectType();
	
	ILinkTerminus getOwningLinkTerminus () ;
	
	
}
