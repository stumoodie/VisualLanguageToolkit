/**
 * 
 */
package org.pathwayeditor.figure.figuredefn;

import org.pathwayeditor.businessobjects.drawingprimitives.IDrawingNodeAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeAttribute;
import org.pathwayeditor.figure.geometry.IConvexHull;

/**
 * @author smoodie
 *
 */
public interface IFigureGeometryFactory {

	IFigureController getFigureController(IShapeAttribute node);
	
	IConvexHull getConvexHull(IDrawingNodeAttribute node);
}
