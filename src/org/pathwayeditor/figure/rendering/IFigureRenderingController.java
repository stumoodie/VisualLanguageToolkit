/*
  Licensed to the Court of the University of Edinburgh (UofE) under one
  or more contributor license agreements.  See the NOTICE file
  distributed with this work for additional information
  regarding copyright ownership.  The UofE licenses this file
  to you under the Apache License, Version 2.0 (the
  "License"); you may not use this file except in compliance
  with the License.  You may obtain a copy of the License at

  http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing,
  software distributed under the License is distributed on an
  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
  KIND, either express or implied.  See the License for the
  specific language governing permissions and limitations
  under the License.
*/


package org.pathwayeditor.figure.rendering;

import java.util.List;
import java.util.Set;

import org.pathwayeditor.businessobjects.drawingprimitives.attributes.Colour;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LineStyle;
import org.pathwayeditor.figure.geometry.Envelope;
import org.pathwayeditor.figure.geometry.IConvexHull;

/**
 * 
 * IFigureController is an interface that defines how a figure definition
 * can be generated and manipulated. Classes implementing this interface will be responsible
 * for interpreting a compiled figure definition and enabling modification and querying of
 * the attributes of the figure definition and associated bind variables. In typical usage scenario
 * a client will set the appropriate attributes for the figure and then generate a figure definition.
 * Changes to an attribute do not automatically trigger a regeneration of the figure definition, this must
 * be done explicitly with a call to <code>generateFigureDefinition()</code>. A listener is provided
 * and a client may wish to listen for changes to attributed and use the listener to trigger regeneration.  
 *
 * @author Stuart Moodie
 *
 */
public interface IFigureRenderingController {

	void setEnvelope(Envelope newEnvelope);
	
	/**
	 * Sets the requested bounding box for the figure definition. Note that this may not correspond
	 * to the bounding box of the shape drawn by the figure definition (this is obtained using <code>getEnvelope()</code>.
	 * @param newEnvelope the new bounding box to use for this figure definition.
	 * @deprecated use setEnvelope() instead  
	 */
	@Deprecated
	void setRequestedEnvelope(Envelope newEnvelope);
	
	/**
	 * Get the bounding box that was last requested. 
	 * @return the last requested bounding box.
	 * @deprecated use getEnvelope() instead
	 */
	@Deprecated
	Envelope getRequestedEnvelope();
	
	/**
	 * Get the bounding box of the figure what was actually defined by the last
	 * generation of the figure definition. 
	 * @return the last actual bounding box, or null if the figure definition has not been generated. 
	 */
	Envelope getEnvelope();
	
	/**
	 * Get the last convex hull to be calculated.
	 * @return the last calculated convex hull, or null if the figure definition has not been generated.
	 */
	IConvexHull getConvexHull();

	/**
	 * Sets the fill colour of the figure.
	 * @param newFillColour the new fill colour, which should not be null.
	 */
	void setFillColour(Colour newFillColour);
	
	/**
	 * Get the fill colour set for the figure. 
	 * @return the fill colour.
	 */
	Colour getFillColour();
	
	/**
	 * Sets the font colour of the figure.
	 * @param newFontColour the new font colour, which should not be null.
	 */
	void setFontColour(Colour newFontColour);
	
	/**
	 * Get the font colour set for the figure. 
	 * @return the font colour.
	 */
	Colour getFontColour();
	
	void setFont(GenericFont font);
	
	GenericFont getFont();
	
	/**
	 * Sets the line colour to be used by the figure,
	 * @param newLineColour the new line colour, which should not be null.
	 */
	void setLineColour(Colour newLineColour);
	
	/**
	 * Get the line colour set for the figure definition.
	 * @return the line colour.
	 */
	Colour getLineColour();
	
	
	/**
	 * Get the line style set for the figure definition.
	 * @return the line style.
	 */
	LineStyle getLineStyle();
	
	/**
	 * Sets the line style to be used by the figure,
	 * @param newLineStyle the new line colour, which should not be null.
	 */
	void setLineStyle(LineStyle newLineStyle);

	/**
	 * Get the line width for the figure definition.
	 * @return the line width.
	 */
	double getLineWidth();
	
	/**
	 * Sets the line width to be used by the figure,
	 * @param lineWidth the new line colour, which should not be null.
	 */
	void setLineWidth(double lineWidth);

	/**
	 * Binds boolean value to a bind variable in the figure definition. 
	 * @param name the bind variable name.
	 * @param value the value to assigned to the bind variable.
	 */
	void setBindBoolean(String name, Boolean value);

	/**
	 * Binds String value to a bind variable in the figure definition. 
	 * @param name the bind variable name.
	 * @param value the value to assigned to the bind variable.
	 */
	void setBindString(String name, String value);

	/**
	 * Binds Integer value to a bind variable in the figure definition. 
	 * @param name the bind variable name.
	 * @param value the value to assigned to the bind variable.
	 */
	void setBindInteger(String name, int value);

	/**
	 * Binds Double value to a bind variable in the figure definition. 
	 * @param name the bind variable name.
	 * @param value the value to assigned to the bind variable.
	 */
	void setBindDouble(String name, double value);

	/**
	 * Gets the binds variable names used in the figure definition used by this controller.
	 * @return the set of variable names, which may be empty, but will not be null.
	 */
	Set<String> getBindVariableNames();
	
	/**
	 * Tells the controller to (re)generate the figure definition. 
	 */
	void generateFigureDefinition();
	
	/**
	 * Gets the compiled figure definition used by this controller.
	 * @return the figure definition, which cannot be null.
	 */
	GraphicsInstructionList getFigureDefinition();
	
	/**
	 * Gets an anchor locator factor used to create an anchor locator defined by this figure definition.
	 * @return the anchor locator factory or null if no anchor locator was specified in the figure definition.
	 */
	IAnchorLocatorFactory getAnchorLocatorFactory();
	
	/**
	 * Adds a figure change listener to this controller.
	 * @param listener the listener, which should not be null.
	 */
	void addListener(IFigureRenderingControllerListener listener);
	
	/**
	 * Remove the figure change listener from this controller.
	 * @param listener the listener, which should not be null.
	 */
	void removeListener(IFigureRenderingControllerListener listener);

	/**
	 * Get the listeners associated this this controller.
	 * @return a list of listeners that is safe to modify and can be empty, but will not be null.
	 */
	List<IFigureRenderingControllerListener> listenerIterator();
}
