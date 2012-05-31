/*
Copyright 2009-2011, Court of the University of Edinburgh

*/

package org.pathwayeditor.businessobjects.drawingprimitives;

import java.util.Iterator;

import org.pathwayeditor.businessobjects.drawingprimitives.listeners.IBendPointChangeListenee;
import org.pathwayeditor.figure.geometry.Point;

/**
 * IBendPointContainer is an interface defining a container for bend points that is associated with a ILinkAttribute.
 * This allows the manipulation of and access to bend points.
 * Bend points are indexed from 0 to N-1 - similar to an array.
 * 
 * @author Stuart Moodie
 *
 */
public interface IBendPointContainer extends IBendPointChangeListenee  {

	/**
	 * Gets the link attribute that this bend-point container is associated with.
	 * @return the link attriobute which should not be null.
	 */
	ILinkAttribute getLinkAttribute();
	
	/**
	 * Gets an iterator that contains all the bendpoints of this Link. Cannot be null.
	 * @return the bendpoint iterator.
	 */
	Iterator<Point> bendPointIterator();
	
	/**
	 * Gets the number of bendpoints this link has. Cannot be null.
	 * @return the numeric value of the bendpoints.
	 */
	int numBendPoints();
	
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
	Point getBendPoint(int index);
	
	/**
	 * Creates a new bendpoint and adds it to the end of the bendpoint list in this atrtribute.
	 * @param location the new location of the bendpoint.
	 * throws IllegalArgumentException if any parameter is null. 
	 */
	void createNewBendPoint(Point location);
	
	/**
	 * As {@link #createNewBendPoint(Point)}, but also defines the index position
	 * that the new bendpoint should be inserted into. This allows an index position that will result in an
	 * append to the list, i.e. <code>position == numBendPoints()</code>.
	 * @param position the index position which must be valid for the list of bendpoints
	 * @param location the new location of the bendpoint
	 * @throws IllegalArgumentException if any parameter is null.
	 * @throws IndexOutOfBoundsException if <code>indexPos &lt; 0 || indexPos &gt; this.numBendPoints()</code>.
	 */
	void createNewBendPoint(int position, Point location);
	
	/**
	 * Removed a bendpoint at the given index position.
	 * @param index the index position.
	 * @throws IllegalArgumentException if <code>containsBendPoint(index) == false</code>. 
	 */
	void removeBendPoint(int index);
	
	/**
	 * Translate the bendpoint to a new point.
	 * @param idx the index of the bend-point.
	 * @param translation the translation, which should be null.
	 */
	void translateBendPoint(int idx, Point translation);

	/**
	 * Translates all bendpoint by the displacement specified by <code>delta</code>.
	 * @param delta the amount to translate all the bendpoints, which should not be null.
	 */
	void translateAll(Point delta);

}
