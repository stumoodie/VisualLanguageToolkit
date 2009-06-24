/**
 * 
 */
package org.pathwayeditor.figure.figuredefn;

import org.pathwayeditor.figure.geometry.IConvexHull;

/**
 * @author smoodie
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
	 * @param newHull the to calculate from, which cannot be null.
	 * @return the new anchor locator.
	 * @throws IllegalArgumentException if <code>newHull == null</code>.
	 */
	IAnchorLocator createAnchorLocator(IConvexHull newHull);
	
}
