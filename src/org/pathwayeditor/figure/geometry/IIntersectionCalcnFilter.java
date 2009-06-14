/**
 * 
 */
package org.pathwayeditor.figure.geometry;

import org.pathwayeditor.businessobjects.drawingprimitives.IDrawingNode;

/**
 * @author smoodie
 *
 */
public interface IIntersectionCalcnFilter {

	boolean accept(IDrawingNode node);

}
