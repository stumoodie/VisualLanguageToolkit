/**
 * 
 */
package org.pathwayeditor.figure.figuredefn;

import org.pathwayeditor.businessobjects.drawingprimitives.IDrawingNode;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeNode;
import org.pathwayeditor.figure.geometry.IConvexHull;

/**
 * @author smoodie
 *
 */
public interface IFigureGeometryFactory {

	IFigureController getFigureController(IShapeNode node);
	
	IConvexHull getConvexHull(IDrawingNode node);
}
