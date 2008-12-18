package org.pathwayeditor.businessobjects.drawingprimitives;

import java.util.Iterator;

import org.pathwayeditor.businessobjects.drawingprimitives.attributes.ConnectionRouter;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.IBendPoint;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LineStyle;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.Location;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.Size;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.IPropertyChangeListenee;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotatedObject;
import org.pathwayeditor.businessobjects.typedefn.ILinkObjectType;

public interface ILinkAttribute extends IZOrderedObject, ICanvasAttribute, IAnnotatedObject, IPropertyChangeListenee {
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
	 * Get the Connector router of the link. Cannot be null.
	 * @return the connection router.
	 */
	ConnectionRouter getRouterType();

	/**
	 * Sets the Connector router of the link.
	 * @param router the router type to set, which cannot be null.
	 * @throws IllegalArgumentException if router is null.
	 */
	void setRouterType(ConnectionRouter router);
	
	
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
	IBendPoint createNewBendPoint(Location location, Size firstRelativeDim, Size secondRelativeDim);
	
	/**
	 * As {@link #createNewBendPoint(Location, Size, Size)}, but also defines the index position
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
	IBendPoint createNewBendPoint(int position, Location location, Size firstRelativeDim, Size secondRelativeDim);
	
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
	RGB getLineColor () ;
	
	/**
	 * Sets a new Color for this link.
	 * @param newColor the new colour for the line, which cannot be null.
	 * @throws IllegalArgumentException if newColor is null.
	 */
	void setLineColor ( RGB newColor ) ;
	
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
	 * Gets the URL that is related with this link.
	 * @return the url of the link.
	 */	
	String getUrl () ;
	
	/**
	 * Sets the URL related with this link.
	 * @param url the url, which cannot be null, but can be malformed or an empty string.
	 * @throws IllegalArgumentException if url is null.
	 */
	void setUrl ( String url) ;
	
	/**
	 * Gets the Name that is related with this link.
	 * @return the name of the link.
	 */	
	String getName () ;
	
	/**
	 * Sets the Name that is related with this link.
	 * @param newName the new Name, which cannot be null, but can be an empty string.
	 * @throws IllegalArgumentException if <code>newName</code> is null.
	 */
	void setName ( String newName) ;
	
	/**
	 * Gets the description of this link.
	 * @return the description of the link, which cannot be null.
	 */	
	String getDescription () ;
	
	/**
	 * Sets the Description that is related with this link.
	 * @param newDescription the new Description, which cannot be null.
	 * @throws IllegalArgumentException if <code>description</code> is null.
	 */
	void setDescription ( String newDescription) ;	
	
	/**
	 * Gets the DetailedDescription that is related with this link.
	 * @return the DetailedDescription of the link.
	 */	
	String getDetailedDescription () ;
	
	/**
	 * Sets the DetailedDescription that is related with this link.
	 * @param DetailedDescription the new DetailedDescription, which cannot be null.
	 * @throws IllegalArgumentException if <code>detailedDescription</code> is null.
	 */
	void setDetailedDescription ( String DetailedDescription) ;	
	
	/**
	 * Returns the width of the line.
	 * @return the width.
	 */
	int getLineWidth() ;
	
	/**
	 * Sets a new width for the line of this link.
	 * @param lineWidth the new line width, which must be a positive integer greater than zero.
	 * @throws IllegalArgumentException if the line width is invalid.
	 */
	void setLineWidth ( int lineWidth) ;
	
	
	/**
	 * Gets the link edge associated with the attribute.
	 * @return the link edge, which cannot be null.
	 */
	ILinkEdge getCurrentDrawingElement();
}
