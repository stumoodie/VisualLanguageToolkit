package org.pathwayeditor.businessobjects.drawingprimitives;

import java.util.Iterator;

import org.pathwayeditor.businessobjects.drawingprimitives.attributes.ConnectionRouter;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.IBendPoint;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotatedObject;
import org.pathwayeditor.businessobjects.typedefn.ILinkObjectType;

public interface ILink extends IZOrderedObject, IAnnotatedObject {

	ICanvas getCanvas();
	
	int getCreationSerial();
	
	ILinkObjectType getObjectType();
	
	ILinkEndDecoration getLinkSourceDecoration();

	/**
	 * Gets the source shape of this link.
	 * @return the shape instance, guaranteed to be non-null.
	 */
	IShape getSourceShape();
	
	/**
	 * Tests if the source of the link can be changed the the new shape. To succeed the newShape must
	 * belong to the same map as the current source and it must have the same shape object type.    
	 * @param newShape the new shape to test. This can be null.
	 * @return true if the above criteria are met, false otherwise.
	 */
	boolean canChangeSource(IShape newShape);
	
	/**
	 * Changes the current source to a new one.
	 * @param newShape The new source to be set to.
	 * @throws IllegalArgumentException if <code>canChangeSource(newShape) == false</code>
	 */
	void changeSource(IShape newShape);
	
	ILinkEndDecoration getLinkTargetDecoration();

	ConnectionRouter getRouter();
	void setRouter(ConnectionRouter router);
	
	/**
	 * Gets the target shape of this link.
	 * @return the shape instance, guaranteed to be non-null.
	 */
	IShape getTargetShape();
	
	/**
	 * Tests if the target of the link can be changed the the new shape. To succeed the newShape must
	 * belong to the same map as the current target and it must have the same shape object type.    
	 * @param newShape the new shape to test. This can be null.
	 * @return true if the above criteria are met, false otherwise.
	 */
	boolean canChangeTarget(IShape newShape);
	
	/**
	 * Changes the current target to a new one.
	 * @param newShape The new target to be set to.
	 * @throws IllegalArgumentException if <code>canChangeTarget(newShape) == false</code>
	 */
	void changeTarget(IShape newShape);
	
	Iterator<IBendPoint> bendPointIterator();
	
	int numBendPoints();
	
	void addBendPoint(IBendPoint newBendPoint);
	
	boolean containsBendPoint(IBendPoint bendPoint);
	
	void removeBendPoint(IBendPoint bendPoint);
}
