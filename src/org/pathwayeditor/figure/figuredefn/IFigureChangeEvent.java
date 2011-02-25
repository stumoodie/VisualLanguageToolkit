/**
 * 
 */
package org.pathwayeditor.figure.figuredefn;

/**
 * @author Stuart Moodie
 *
 */
public interface IFigureChangeEvent {

	FigureChangeType getType();
	
	Object getOldValue();
	
	Object getNewValue();
	
}
