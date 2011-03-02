/*
Copyright 2009-2011, Court of the University of Edinburgh
*/

package org.pathwayeditor.businessobjects.exchange;

import java.io.IOException;
import java.io.InputStream;

import org.pathwayeditor.businessobjects.drawingprimitives.IModel;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationSubsystem;

/**
 * IXmlPersistenceManager is an interface that defines how a model is marshalled and
 * unmarshalled to a stream. It can also create a new model. This was originally designed
 * to comply with Eclipse's persistence model, but could be redesigned to be more intuitive.  
 * 
 * @author Stuart Moodie
 *
 */
public interface IXmlPersistenceManager {

	/**
	 * Gets the current canvas, which may be <code>null</code> if no canvas has been created or read.
	 * @return The current canvas.
	 */
	IModel getCurrentModel();
	
	/**
	 * Creates a new canvas replacing any canvas that is currently set.
	 * @param notationSubsystem the notation subsystem to use to create the newc canvas. 
	 * @param modelName the name of the canvas
	 */
	void createNewModelStream(INotationSubsystem notationSubsystem, String modelName);

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
