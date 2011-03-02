/*
Copyright 2009-2011, Court of the University of Edinburgh
*/
package org.pathwayeditor.businessobjects.drawingprimitives;

import java.util.List;

import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LinkEndDecoratorShape;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LinkTermType;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.ILinkTerminusChangeListener;
import org.pathwayeditor.businessobjects.typedefn.ILinkTerminusDefinition;
import org.pathwayeditor.figure.geometry.Dimension;
import org.pathwayeditor.figure.geometry.Point;

/**
 * 
 * ILinkTerminus is an interface that defines the attributes and operations
 * on the terminus of a link. The link terminus stores the position of the
 * anchor point where the end of the link is connected to the shape as well as
 * how the end of the link is drawn. The end of the link can be decorated by various
 * arrowhead symbols of configurable size and has a configurable gap between the 
 * anchor point of the link and visible end of the link where any end decorator is
 * drawn.  
 *
 * @author Stuart Moodie
 *
 */
public interface ILinkTerminus {

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

	/**
	 * Add a link terminus change listener to this instance.
	 * @param listener the listener, which cannot be null.
	 */
	void addLinkTerminusChangeListener(ILinkTerminusChangeListener listener);
	
	/**
	 * Remove a link terminus change listener from this instance.
	 * @param listener the listener, which should not be null.
	 */
	void removeLinkTerminusChangeListener(ILinkTerminusChangeListener listener);
	
	/**
	 * Get the link terminus change listeners associated with this instance. 
	 * @return a list of listeners, which will be empty of their are none.
	 */
	List<ILinkTerminusChangeListener> getLinkTerminusChangeListeners();

	/**
	 * Translates the link by the given amount. This moves all the bend points and link
	 * end points by the displacement given by <code>delta</code>.
	 * @param delta the displacement, which cannot be null.
	 */
	void translate(Point delta);
}
