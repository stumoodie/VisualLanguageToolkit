/*
Copyright 2009, Court of the University of Edinburgh

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License. 
*/
/**
 * 
 */
package org.pathwayeditor.businessobjects.drawingprimitives;

import java.util.Iterator;

import org.pathwayeditor.businessobjects.notationsubsystem.INotationSubsystem;

import uk.ac.ed.inf.graph.compound.ICompoundEdge;
import uk.ac.ed.inf.graph.compound.ICompoundGraph;
import uk.ac.ed.inf.graph.compound.ICompoundNode;


/**
 * @author smoodie
 *
 */
public interface IModel {

	/**
	 * Gets the name of the canvas, which cannot be null.
	 * @return the name of the canvas, which cannot be null or an empty string.
	 */
	String getName();
	
	/**
	 * Tests if the name can be used as a valid canvas name.
	 * @param name the name to be tested, can be null.
	 * @return true if the name is valid, false otherwise.
	 */
	boolean isValidName(String name);
	
	/**
	 * Sets the canvas name.
	 * @param name the new name for the canvas, which must be valid.
	 * @throws IllegalArgumentException if <code>isValidName(name) == false</code>
	 */
	void setName(String name);
	
	IShapeAttributeFactory shapeAttributeFactory();
	
	ILinkAttributeFactory linkAttributeFactory();

	ILabelAttributeFactory labelAttributeFactory();

	IRootAttribute getRootAttribute();

	/**
	 * Number of canvas attributes stored by this canvas.
	 * @return the number of canvas attributes.
	 */
	int numCanvasAttributes();
	
	void addCanvasAttribute(ICanvasElementAttribute attribute);

	ICanvasAttributeSequence getCreationSerialCounter();

	/**
	 * Returns all the drawing nodes in this model.
	 * @return the drawing node iterator.
	 */
	Iterator<ICompoundNode> drawingNodeIterator();
	
	/**
	 * Returns a shape node iterator for all the shape nodes held in this model.
	 * @return the shape node iterator, which cannot be null.
	 */
	Iterator<ICompoundNode> shapeNodeIterator();
	
	
	/**
	 * Returns an iterator for all the label nodes held in this model.
	 * @return the label node iterator, which cannot be null.
	 */
	Iterator<ICompoundNode> labelNodeIterator();
	
	/**
	 * Returns an iterator for all the link edges in this model
	 * @return the link edge iterator.
	 */
	Iterator<ICompoundEdge> linkEdgeIterator();
	
	/**
	 * Provides the number of drawing elements in this model.
	 * @return the number of drawing elements.
	 */
	int numDrawingElements();
	
	/**
	 * Provides the number of drawing nodes in this model.
	 * @return the number of drawing nodes.
	 */
	int numDrawingNodes();
	
	/**
	 * Provides the number of shape nodes in this model.
	 * @return the number of shape nodes.
	 */
	int numShapeNodes();
	
	/**
	 * Provides the number of label nodes in this model.
	 * @return the number of label nodes.
	 */
	int numLabelNodes();
	
	
	/**
	 * Provides the number of link edges in this model.
	 * @return the number of link edges.
	 */
	int numLinkEdges();

	ICompoundGraph getGraph();
	
	/**
	 * Find an attribute that matches the serial number.
	 * @param attributeSerial the serial number. 
	 * @return The canvas attribute with the serial number matching <code>attributeSerial</code> or null if no matching attribute serial can be found. 
	 */
	ICanvasElementAttribute findAttribute(int attributeSerial);

	/**
	 * Tests if the canvas contains a link attribute matching the serial number.
	 * @param attributeSerial the serial number.
	 * @return true if it contains the attribute, false otherwise.
	 */
	boolean containsLinkAttribute(int attributeSerial);
	
	/**
	 * Gets the link attribute matching the serial number.
	 * @param attributeSerial the serial number that uniquely identifies the link attribute.
	 * @return the link attribute, which cannot be null.
	 * @throws IllegalArgumentException if <code>containsLinkAttribute(attributeSerial) == false</code>.
	 */
	ILinkAttribute getLinkAttribute(int attributeSerial);

	/**
	 * Tests if the canvas contains a shape attribute matching the serial number.
	 * @param attributeSerial the serial number.
	 * @return true if it contains the attribute, false otherwise.
	 */
	boolean containsShapeAttribute(int attributeSerial);
	
	/**
	 * Gets the shape attribute matching the serial number.
	 * @param attributeSerial the serial number that uniquely identifies the link attribute.
	 * @return the shape attribute, which cannot be null.
	 * @throws IllegalArgumentException if <code>containsShapeAttribute(attributeSerial) == false</code>.
	 */
	IShapeAttribute getShapeAttribute(int attributeSerial);

	/**
	 * Tests if the canvas contains a label attribute matching the serial number.
	 * @param attributeSerial the serial number.
	 * @return true if it contains the attribute, false otherwise.
	 */
	boolean containsLabelAttribute(int attributeSerial);
	
	/**
	 * Gets the label attribute matching the serial number.
	 * @param attributeSerial the serial number that uniquely identifies the link attribute.
	 * @return the label attribute, which cannot be null.
	 * @throws IllegalArgumentException if <code>containsLabelAttribute(attributeSerial) == false</code>.
	 */
	ILabelAttribute getLabelAttribute(int attributeSerial);
	
	Iterator<ICanvasElementAttribute> canvasAttributeIterator();
	
	Iterator<IShapeAttribute> shapeAttributeIterator();
	
	int numShapeAttributes();
	
	Iterator<ILabelAttribute> labelAttributeIterator();
	
	int numLabelAttributes();
	
	Iterator<ILinkAttribute> linkAttributeIterator();
	
	int numLinkAttributes();

	INotationSubsystem getNotationSubsystem();
}
