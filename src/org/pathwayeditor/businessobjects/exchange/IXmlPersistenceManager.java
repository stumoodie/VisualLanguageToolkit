/*
Copyright 2009-2011, Court of the University of Edinburgh
*/

package org.pathwayeditor.businessobjects.exchange;

import java.io.IOException;
import java.io.InputStream;

import org.pathwayeditor.businessobjects.drawingprimitives.IModel;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationSubsystem;

/**
 * @author Stuart Moodie
 *
 */
public interface IXmlPersistenceManager {

	/**
	 * Gets the current canvas, which may be <code>null</code> if no canvas has been created or read.
	 * @return The current canvas.
	 */
	IModel getCurrentCanvas();
	
	/**
	 * Creates a new canvas replacing any canvas that is currently set.
	 * @param notationSubsystem the notation subsystem to use to create the newc canvas. 
	 * @param canvasName the name of the canvas
	 */
	void createNewCanvasStream(INotationSubsystem notationSubsystem, String canvasName);

	/**
	 * Reads a canvas XMLSchema specification from the input stream and creates a new canvas
	 * based on this definition.  
	 * @param in the input stream containing the XMLSchema, which cannot be null.
	 * @throws IOException if there is an error reading the input stream.
	 */
	void readCanvasFromStream(InputStream in) throws IOException;
	
	/**
	 * Creates an input stream to an XMLSchema representation of the current canvas.
	 * @return the input stream, which cannot be null.
	 * @throws IOException if there is an error creating the input stream.
	 * @throws IllegalStateException if not canvas exists, i.e. <code>getCurrentCanvas() == null</code>. 
	 */
	InputStream writeCanvasToStream() throws IOException;
}
