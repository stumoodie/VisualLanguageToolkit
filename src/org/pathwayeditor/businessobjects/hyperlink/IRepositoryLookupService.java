/**
 * 
 */
package org.pathwayeditor.businessobjects.hyperlink;

import java.net.URI;

import org.pathwayeditor.businessobjects.drawingprimitives.ICanvas;
import org.pathwayeditor.businessobjects.drawingprimitives.IDrawingElement;
import org.pathwayeditor.businessobjects.management.IMapPersistenceManager;

/**
 * @author smoodie
 *
 */
public interface IRepositoryLookupService {

	boolean isValidUri(URI hyperlink);
	
	URI createAbsoluteUri(IMapPersistenceManager map);
	
	URI createAbsoluteUri(IMapPersistenceManager map, IDrawingElement drawingElement);
	
	ICanvas findCanvas(URI hyperlink);
	
	IDrawingElement findCanvasObject(URI hyperlink);
}
