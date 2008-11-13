package org.pathwayeditor.businessobjects.drawingprimitives;

import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LinkEndDecoratorShape;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LinkTermType;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.PrimitiveShapeType;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.Size;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotatedObject;
import org.pathwayeditor.businessobjects.typedefn.ILinkTerminusDefinition;

public interface ILinkTerminus extends IAnnotatedObject {

	ILinkAttribute getOwningLink() ;
	
	LinkTermType getLinkTermType () ;
	
	ILinkTerminusDefinition getDefinition();
	
	short getGap();
	
	void setGap(short newGap);
	
	RGB getTerminusColor () ;
	void setTerminusColor ( RGB newColor ) ;
	
	Size getTerminusSize () ;
	void setTerminusSize ( Size newSize) ;
	
	PrimitiveShapeType getTerminusDecoratorType();
	void setTerminusDecoratorType(PrimitiveShapeType shapeObjectType);
	
	Size getEndSize () ;
	void setEndSize ( Size size ) ;
	
	LinkEndDecoratorShape getEndDecoratorType () ;

	void setEndDecoratorType ( LinkEndDecoratorShape linkEndDecoratorShape  );

	/**
	 * Identity based on the owningLink and the link end type.
	 * @param other the other object to test.
	 * @return true of the objects are equal based on the business key, false otherwise.
	 */
	boolean equals(Object other);

	int hashCode();
}
