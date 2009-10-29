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
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.ICanvasAttributePropertyChangeListenee;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.ISuppressableChangeListenee;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotatedObject;
import org.pathwayeditor.businessobjects.typedefn.ILinkTerminusDefinition;
import org.pathwayeditor.figure.geometry.Dimension;
import org.pathwayeditor.figure.geometry.Point;

public interface ILinkTerminus extends IAnnotatedObject, ICanvasAttribute, ICanvasAttributePropertyChangeListenee, ISuppressableChangeListenee {

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
	double getGap();
	
	/**
	 * Sets the gap, which must be a positive integer or zero.
	 * @param newGap the new gap, which cannot be negative.
	 * @throws IllegalArgumentException if <code>newGap</code> is negative.
	 */
	void setGap(double newGap);
	
	/**
	 * Gets the size of the terminus end decorator.
	 * @return the size of the terminus end decorator, which cannot be null.
	 */
	Dimension getEndSize();
	
	/**
	 * Sets the terminus end decorator size. 
	 * @param size the end decorator size, which cannot be null.
	 * @throws IllegalArgumentException if <code>size<.code> is null.
	 */
	void setEndSize(Dimension size);
	
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
	Point getLocation();
	
	/**
	 * Sets the location of the terminus.
	 * @param location the location, which cannot be null.
	 * throws IllegalArgumentExtension if <code>location == null</code>.
	 */
	void setLocation(Point location);
	
	Point getReferencePoint();
	
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
