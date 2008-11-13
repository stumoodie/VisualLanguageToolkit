package org.pathwayeditor.businessobjects.drawingprimitives;

import java.util.Iterator;

import org.pathwayeditor.businessobjects.drawingprimitives.attributes.ConnectionRouter;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.IBendPoint;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LineStyle;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotatedObject;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationProperty;
import org.pathwayeditor.businessobjects.typedefn.ILinkObjectType;

public interface ILinkAttribute extends IZOrderedObject, ICanvasAttribute, IAnnotatedObject {
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
	ConnectionRouter getRouter();

	/**
	 * Sets the Connector router of the link.
	 * @throws IllegalArgumentException if router is null.
	 */
	void setRouter(ConnectionRouter router);
	
	
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
	 * Adds a new bendpoint on this Link.
	 * @throws IllegalArgumentException if bendpoint is null
	 */
	void addBendPoint(IBendPoint newBendPoint);
	
	/**
	 * Checks if the bendpoint in question belongs to this Link.
	 * @return true if the bendpoint is part of the Link false otherwise.
	 */
	boolean containsBendPoint(IBendPoint bendPoint);
	
	/**
	 * Removes the given bendpoint from this Link.
	 * @param bendPoint
	 * @throws IllegalArgumentException if bendpoint is null.
	 */
	void removeBendPoint(IBendPoint bendPoint);
	
	/**
	 * Gets the RGB representation of the color of the link. Cannot be null.
	 * @return the RGB color.
	 */
	RGB getLineColor () ;
	
	/**
	 * Sets a new Color for this link.
	 * @param newColor
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
	 * @param style
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
	 * @param url
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
	 * @param newName the new Name
	 */
	void setName ( String newName) ;
	
	/**
	 * Gets the Name that is related with this link.
	 * @return the description of the link.
	 */	
	String getDescription () ;
	
	/**
	 * Sets the Description that is related with this link.
	 * @param newDescription the new Description
	 */
	void setDescription ( String newDescription) ;	
	
	/**
	 * Gets the DetailedDescription that is related with this link.
	 * @return the DetailedDescription of the link.
	 */	
	String getDetailedDescription () ;
	
	/**
	 * Sets the DetailedDescription that is related with this link.
	 * @param DetailedDescription the new DetailedDescription
	 */
	void setDetailedDescription ( String DetailedDescription) ;	
	
	/**
	 * Returns the width of the line.
	 * @return the width.
	 */
	int getLineWidth() ;
	
	/**
	 * Sets a new width for the line of this link.
	 * @param lineWidth the new line width.
	 */
	void setLineWidth ( int lineWidth) ;
	
	
//	ILinkEdge getLinkEdge();

//	Iterator<IAnnotationProperty> propertyIterator();
}
