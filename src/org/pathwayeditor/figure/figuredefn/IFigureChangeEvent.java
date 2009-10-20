/**
 * 
 */
package org.pathwayeditor.figure.figuredefn;

/**
 * @author smoodie
 *
 */
public interface IFigureChangeEvent {

	FigureChangeType getType();
	
	Object getOldValue();
	
	Object getNewValue();
	
}
