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

import java.util.Iterator;

import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LineStyle;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.IBendPointChangeListenee;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.ICanvasAttributePropertyChangeListenee;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.ISuppressableChangeListenee;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotatedObject;
import org.pathwayeditor.businessobjects.typedefn.ILinkObjectType;
import org.pathwayeditor.figure.geometry.Point;

public interface ILinkAttribute extends IZOrderedObject, ICanvasAttribute, IAnnotatedObject, ICanvasAttributePropertyChangeListenee, IBendPointChangeListenee, ISuppressableChangeListenee {
	/**
	 * Gets the Object type related to this Link.
	 * @return the object type.
	 */
	ILinkObjectType getObjectType();
	
	
	/**
	 * Get the Link Decorator for the source of the link. Cannot be null.
	 * @return the source link Decorator.
	 */		
	ILinkTerminus getSourceTerminus();

	/**
	 * Get the Link Decorator for the target of the link. Cannot be null.
	 * @return the target link Decorator.
	 */		
	ILinkTerminus getTargetTerminus();

	/**
	 * Gets an iterator that contains all the bendpoints of this Link. Cannot be null.
	 * @return the bendpoint iterator.
	 */
	Iterator<IBendPoint> bendPointIterator();
	
	/**
	 * Gets the number of bendpoints this link has. Cannot be null.
	 * @return the numeric value of the bendpoints.
	 */
	int numBendPoints();
	
	/**
	 *  Makes a set of bendpoints which draw a dog-leg link
	 */
	void makeSelfBendPoints(Point src, Point tgt);

	
	/**
	 * Checks if the bendpoint in question belongs to this Link.
	 * @return true if the bendpoint is part of the Link false otherwise.
	 */
	boolean containsBendPoint(IBendPoint bendPoint);
	
	/**
	 * Test if this attribute contains a bendpoint at the given position in the
	 * bendpoint list.  
	 * @param index the list poistion number.
	 * @return true if a bendpoint exists at this position, false otherwise.
	 */
	boolean containsBendPoint(int index);
	
	/**
	 * Get the bendpoint at the given index position.
	 * @param index the index position.
	 * @return the bendpoint at the given position.
	 * @throws IllegalArgumentException if <code>containsBendPoint(index) == false</code>.
	 */
	IBendPoint getBendPoint(int index);
	
	/**
	 * Creates a new bendpoint and adds it to the end of the bendpoint list in this atrtribute.
	 * @param location the new location of the bendpoint.
	 * @param firstRelativeDim the position relative to the first anchor point of the link.
	 * @param secondRelativeDim the position relative to second anchor point of the link.
	 * @return the newly created bendpoint which cannot be null.
	 * throws IllegalArgumentException if any parameter is null. 
	 */
	IBendPoint createNewBendPoint(Point location);
	
	/**
	 * As {@link #createNewBendPoint(Point)}, but also defines the index position
	 * that the new bendpoint should be inserted into. This allows an index position that will result in an
	 * append to the list, i.e. <code>position == numBendPoints()</code>.
	 * @param position the index position which must be valid for the list of bendpoints
	 * @param location the new location of the bendpoint
	 * @param firstRelativeDim the new relative dimension to the first anchor point
	 * @param secondRelativeDim the new relative dimension to the second anchor point of the link.
	 * @return the newly created bendpoint
	 * @throws IllegalArgumentException if any parameter is null.
	 * @throws IndexOutOfBoundsException if <code>indexPos &lt; 0 || indexPos &gt; this.numBendPoints()</code>.
	 */
	IBendPoint createNewBendPoint(int position, Point location);
	
	/**
	 * Adds a bendpoint to the list of bendpoints at the given position.
	 * @param newBendPoint the new bendpoint
	 * @param indexPos the new bendpoint position
	 * @throws IllegalArgumentException if <code>containsBendPoint(bendpoint)==false</code>.
	 * @throws IndexOutOfBoundsException if <code>indexPos < 0 || indexPos >= this.numBendPoints()</code>. 
	 */
	void changeBendPointPosition(IBendPoint newBendPoint, int indexPos);
	
	/**
	 * Removes the given bendpoint from this Link.
	 * @param bendPoint the bendpoint to remove which must be held by this attribute and cannot be null.
	 * @throws IllegalArgumentException if <code>containsBendPoint(bendPoint) == false</code>.
	 */
	void removeBendPoint(IBendPoint bendPoint);
	
	/**
	 * Removed a bendpoint at the given index position.
	 * @param index the index position.
	 * @throws IllegalArgumentException if <code>containsBendPoint(index) == false</code>. 
	 */
	
	void removeBendPoint(int index);

	/**
	 * Gets the RGB representation of the color of the link. Cannot be null.
	 * @return the RGB color.
	 */
	RGB getLineColour () ;
	
	/**
	 * Sets a new Color for this link.
	 * @param newColor the new colour for the line, which cannot be null.
	 * @throws IllegalArgumentException if newColor is null.
	 */
	void setLineColour ( RGB newColor ) ;
	
	/**
	 * Gets the line style of this link.
	 * @return the line style. Cannot be null.
	 */	
	LineStyle getLineStyle () ;
	
	/**
	 * Sets a new line style for this link.
	 * @param style the new line style, which cannot be null.
	 * @throws IllegalArgumentException if style is null.
	 */
	void setLineStyle ( LineStyle style );
	
	/**
	 * Returns the width of the line.
	 * @return the width.
	 */
	double getLineWidth() ;
	
	/**
	 * Sets a new width for the line of this link.
	 * @param lineWidth the new line width, which must be a positive integer greater than zero.
	 * @throws IllegalArgumentException if the line width is invalid.
	 */
	void setLineWidth ( double lineWidth) ;
	
	
	/**
	 * Gets the link edge associated with the attribute.
	 * @return the link edge, which cannot be null.
	 */
	ILinkEdge getCurrentDrawingElement();
}
