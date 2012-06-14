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

/**
 * 
 */
package org.pathwayeditor.businessobjects.drawingprimitives;

import java.util.Iterator;

import org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationProperty;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationSubsystem;

import uk.ac.ed.inf.graph.compound.ICompoundEdge;
import uk.ac.ed.inf.graph.compound.ICompoundGraph;
import uk.ac.ed.inf.graph.compound.ICompoundNode;


/**
 * IModel is an interface which defines a complete model used to represent drawing
 * of a graphical notation. The model contains the compound graph representation of
 * the drawing together with the Notation Subsystem defining the graphical notation
 * used by this drawing, factories to create the attributes used to store the appearance
 * of the diagram and methods to access these attributes.    
 * 
 * @author Stuart Moodie
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
	
	/**
	 * Get a new shape attribute factory.
	 * @return the shape attribute factory, which cannot be null.
	 */
	IShapeAttributeFactory shapeAttributeFactory();
	
	/**
	 * Get a new link attribute factory.
	 * @return the link attribute factory, which cannot be null.
	 */
	ILinkAttributeFactory linkAttributeFactory();

	/**
	 * Get a new label attribute factory.
	 * @return the label attribute factory, which cannot be null.
	 */
	ILabelAttributeFactory labelAttributeFactory();

	/**
	 * Create a new anchor node attribute factory
	 * @return the node anchor att fact.
	 */
	IAnchorNodeAttributeFactory anchorNodeAttributeFactory();

	/**
	 * Get the root attribute for this model.
	 * @return the root attribute which cannot be null.
	 */
	IRootAttribute getRootAttribute();

	/**
	 * Number of canvas attributes stored by this canvas. This ignores any attributes
	 * associated with removed graph elements.
	 * @return the number of canvas attributes.
	 */
	int numCanvasAttributes();
	
	/**
	 * Adds a new attribute to be stored by this model. This is typically done by a factory when creating
	 * a new attribute and this method is typically no used by client code.
	 * @param attribute the attribute to add, which should not be null.
	 */
	void addCanvasAttribute(ICanvasElementAttribute attribute);

	/**
	 * Get the attribute serial counter associated with this model, which
	 * should not be null. 
	 * @return the sequence counter.
	 */
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

	/**
	 * Get the compound graph that defines the compound graph structure of the
	 * diagram defined by this model. 
	 * @return the compound graph, which cannot be null.
	 */
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
	
	/**
	 * Provides a new iterator for all the canvas attributes in the model - that
	 * have are not associated with removed graph elements.
	 * @return the iterator, which cannot be null.
	 */
	Iterator<ICanvasElementAttribute> canvasAttributeIterator();
	
	/**
	 * Provides a new iterator for all the shape attributes in the model - that
	 * have are not associated with removed graph elements.
	 * @return the iterator, which cannot be null.
	 */
	Iterator<IShapeAttribute> shapeAttributeIterator();
	
	/**
	 * Number of shape attributes stored by this canvas. This ignores any attributes
	 * associated with removed graph elements.
	 * @return the number of shape attributes.
	 */
	int numShapeAttributes();
	
	/**
	 * Provides a new iterator for all the label attributes in the model - that
	 * have are not associated with removed graph elements.
	 * @return the iterator, which cannot be null.
	 */
	Iterator<ILabelAttribute> labelAttributeIterator();
	
	/**
	 * Number of label attributes stored by this canvas. This ignores any attributes
	 * associated with removed graph elements.
	 * @return the number of label attributes.
	 */
	int numLabelAttributes();
	
	/**
	 * Provides a new iterator for all the link attributes in the model - that
	 * have are not associated with removed graph elements.
	 * @return the iterator, which cannot be null.
	 */
	Iterator<ILinkAttribute> linkAttributeIterator();
	
	/**
	 * Number of link attributes stored by this canvas. This ignores any attributes
	 * associated with removed graph elements.
	 * @return the number of link attributes.
	 */
	int numLinkAttributes();

	/**
	 * Gets the notation subsystem used by this model.
	 * @return the notation subsystem, which cannot be null.
	 */
	INotationSubsystem getNotationSubsystem();
	
	/**
	 * Gets the label attribute associated with the given annotation property. This
	 * ignores labels that have been removed.
	 * @param annotationProperty the annotation property, which should not be null.
	 * @return the label attribute associated with this annotation property or null
	 *  if no corresponding label can be found.
	 */
	ILabelAttribute getLabelForProperty(IAnnotationProperty annotationProperty);
	
	/**
	 * Tests if the annotation property has a corresponding label. This ignores labels that have been
	 * removed.
	 * @param annotationProperty the annotation property to test.
	 * @return true if there is an associated label, false otherwise.
	 */
	boolean hasLabelForProperty(IAnnotationProperty annotationProperty);

	Iterator<IDrawingNodeAttribute> drawingNodeAttributeIterator();
}
