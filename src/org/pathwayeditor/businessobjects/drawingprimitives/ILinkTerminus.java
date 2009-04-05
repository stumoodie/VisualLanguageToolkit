/*
Copyright 2009, Court of the University of Edinburgh

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License. 
*/
package org.pathwayeditor.businessobjects.drawingprimitives;

import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LinkEndDecoratorShape;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LinkTermType;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.Location;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.PrimitiveShapeType;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.Size;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.IPropertyChangeListenee;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotatedObject;
import org.pathwayeditor.businessobjects.typedefn.ILinkTerminusDefinition;

public interface ILinkTerminus extends IAnnotatedObject, ICanvasAttribute, IPropertyChangeListenee {

	/**
	 * The link attribute that owns this terminus. 
	 * @return the owning link attribute, which cannot be null.
	 */
	ILinkAttribute getOwningLink() ;
	
	/**
	 * Get the link terminus type, i.e. is it at the source or target end of the link.
	 * @return the link terminus type, which cannot be null.
	 */
	LinkTermType getLinkTermType () ;
	
	/**
	 * Get the link terminus defintion.
	 * @return the link terminus definition which cannot benull.
	 */
	ILinkTerminusDefinition getDefinition();
	
	/**
	 * Get the gap between the position of the link end terminus.
	 * @return the gap, which must be a positive integer or zero.
	 */
	short getGap();
	
	/**
	 * Sets the gap, which must be a positive integer or zero.
	 * @param newGap the new gap, which cannot be negative.
	 * @throws IllegalArgumentException if <code>newGap</code> is negative.
	 */
	void setGap(short newGap);
	
	/**
	 * Get the terminus colour. 
	 * @return the terminus colour, which cannot be null.
	 */
	RGB getTerminusColour() ;
	
	/**
	 * Set the terminus colour.
	 * @param newColour the new colour which cannot be null.
	 * @throws IllegalArgumentException if <code>newColour</code> is null. 
	 */
	void setTerminusColour(RGB newColour) ;
	
	/**
	 * Gets the size of the terminus shape, which cannot be null.
	 * @return the terminus size.
	 */
	Size getTerminusSize();
	
	/**
	 * Set the terminus size.
	 * @param newSize the new terminus size, which cannot be null.
	 * @throws IllegalArgumentException if <code>newSize</code> is null.
	 */
	void setTerminusSize(Size newSize) ;
	
	/**
	 * Get the terminus decorator type.
	 * @return the terminus decorator type, which cannot be null.
	 */
	PrimitiveShapeType getTerminusDecoratorType();
	
	/**
	 * Set the terminus decorator shape type.
	 * @param shapeObjectType the new shape type of the terminus decorator, which cannot be null.
	 * @throws IllegalArgumentException if <code>shapeObjectType</code> is null. 
	 */
	void setTerminusDecoratorType(PrimitiveShapeType shapeObjectType);
	
	/**
	 * Gets the size of the terminus end decorator.
	 * @return the size of the terminus end decorator, which cannot be null.
	 */
	Size getEndSize();
	
	/**
	 * Sets the terminus end decorator size. 
	 * @param size the end decorator size, which cannot be null.
	 * @throws IllegalArgumentException if <code>size<.code> is null.
	 */
	void setEndSize(Size size);
	
	/**
	 * Get the link end decorator shape type.
	 * @return the link end decorator shape type, which will not be null.
	 */
	LinkEndDecoratorShape getEndDecoratorType () ;

	/**
	 * Sets the link end decorator shape type.
	 * @param linkEndDecoratorShape the new shape type to set, which cannot be null.
	 * @throws IllegalArgumentException if <code>linkEndDecoratorShape == null</code>.
	 */
	void setEndDecoratorType(LinkEndDecoratorShape linkEndDecoratorShape);

	/**
	 * Gets the location of the terminus, which is the point at which the link is attached to the
	 * shape.
	 * @return the location, which cannot be null;
	 */
	Location getLocation();
	
	/**
	 * Sets the location of the terminus.
	 * @param location the location, which cannot be null.
	 * throws IllegalArgumentExtension if <code>location == null</code>.
	 */
	void setLocation(Location location);
	
	/**
	 * Identity is based on the owningLink and the link end type.
	 * @param other the other object to test.
	 * @return true of the objects are equal based on the business key, false otherwise.
	 */
	boolean equals(Object other);

	/**
	 * The hash code is based on the same identity rules as {@link #equals(Object)}.
	 * @return the hash code.
	 */
	int hashCode();
}
