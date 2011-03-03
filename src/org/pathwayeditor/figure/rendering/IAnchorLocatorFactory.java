/*
 * Copyright 2009-2011, Court of the University of Edinburgh
 */
package org.pathwayeditor.figure.rendering;

import org.pathwayeditor.figure.geometry.Envelope;

/**
 * IAnchorLocatorFactory ia an interface that defines a factory for creating
 * new instances of anchor locators. 
 * 
 * @author Stuart Moodie
 *
 */
public interface IAnchorLocatorFactory {

	/**
	 * Creates a new link anchor locator and uses the the convex hull of the owning shape
	 * in its calculations.  
	 * @return the new anchor locator
	 */
	IAnchorLocator createAnchorLocator();
	
	/**
	 * Creates a new link anchor locator that uses the convex hull provides for its
	 * calculations.
	 * @param newBounds the to calculate from, which cannot be null.
	 * @return the new anchor locator.
	 * @throws IllegalArgumentException if <code>newHull == null</code>.
	 */
	IAnchorLocator createAnchorLocator(Envelope newBounds);
	
}
