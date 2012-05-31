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


package org.pathwayeditor.businessobjects.impl;

import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.pathwayeditor.businessobjects.drawingprimitives.IAnchorNodeAttributeFactory;
import org.pathwayeditor.businessobjects.drawingprimitives.ICanvasAttributeSequence;
import org.pathwayeditor.businessobjects.drawingprimitives.ICanvasElementAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.ILabelAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.ILabelAttributeFactory;
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkAttributeFactory;
import org.pathwayeditor.businessobjects.drawingprimitives.IModel;
import org.pathwayeditor.businessobjects.drawingprimitives.IRootAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeAttributeFactory;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationProperty;
import org.pathwayeditor.businessobjects.impl.facades.SubModelFacade;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationSubsystem;
import org.pathwayeditor.businessobjects.typedefn.IRootObjectType;

import uk.ac.ed.inf.graph.compound.ICompoundEdge;
import uk.ac.ed.inf.graph.compound.ICompoundGraph;
import uk.ac.ed.inf.graph.compound.ICompoundGraphElement;
import uk.ac.ed.inf.graph.compound.ICompoundNode;
import uk.ac.ed.inf.graph.compound.newimpl.CompoundGraph;
import uk.ac.ed.inf.graph.util.IFilterCriteria;
import uk.ac.ed.inf.graph.util.IndexCounter;
import uk.ac.ed.inf.graph.util.impl.FilteredIterator;

/**
 * @author Stuart Moodie
 *
 */
public class Model implements IModel {
	private static final int MIN_NAME_LEN = 1;
	private static final Pattern NAME_REGEXP = Pattern.compile("(\\w.*\\w)|(\\w)");
	public static final int ROOT_IDX = 0;
	
	private final IFilterCriteria<ICanvasElementAttribute> linkAttribCriteria = new IFilterCriteria<ICanvasElementAttribute>(){
		@Override
		public boolean matched(ICanvasElementAttribute testObj) {
			return testObj instanceof ILinkAttribute;
		}
	};
	private final IFilterCriteria<ICanvasElementAttribute> labelAttribCriteria = new IFilterCriteria<ICanvasElementAttribute>(){
		@Override
		public boolean matched(ICanvasElementAttribute testObj) {
			return testObj instanceof ILabelAttribute;
		}
	};
	private final IFilterCriteria<ICanvasElementAttribute> shapeAttribCriteria = new IFilterCriteria<ICanvasElementAttribute>(){
		@Override
		public boolean matched(ICanvasElementAttribute testObj) {
			return testObj instanceof IShapeAttribute;
		}
	};
	private final IFilterCriteria<ICanvasElementAttribute> attribCriteria = new IFilterCriteria<ICanvasElementAttribute>(){
		@Override
		public boolean matched(ICanvasElementAttribute testObj) {
			return true;
		}
	};

	private final INotationSubsystem notationSubsystem;
	private String name;
	private final IndexCounter creationSerialCounter;
	private final SortedSet<ICanvasElementAttribute> canvasElementAttributes = new TreeSet<ICanvasElementAttribute>();
	private final ICompoundGraph compoundGraph;
	
	public Model(String name, INotationSubsystem notationSubsystem){
		this(name, notationSubsystem, ROOT_IDX, ROOT_IDX);
	}
	
	public Model(String name, INotationSubsystem notationSubsystem, int rootIdx, int lastSequencesIndex){
		this.name = name;
		this.notationSubsystem = notationSubsystem;
		this.creationSerialCounter = new IndexCounter(lastSequencesIndex);
		IRootObjectType rootObjectType = this.notationSubsystem.getSyntaxService().getRootObjectType();
		IRootAttribute rootAttribute = new RootAttribute(this, rootIdx, rootObjectType);
		this.addCanvasAttribute(rootAttribute);
		this.compoundGraph = new CompoundGraph(rootAttribute);
	}
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IModel#getGraph()
	 */
	@Override
	public ICompoundGraph getGraph() {
		return this.compoundGraph;
	}

	@Override
	public String getName() {
		return this.name;
	}
	
	@Override
	public void setName(String name){
		if(!isValidName(name)) throw new IllegalArgumentException("Invalid name: " + name);
		
		this.name = name;
	}

	@Override
	public boolean isValidName(String name){
		return checkValidName(name);
	}
	
	public static boolean checkValidName(String name){
		boolean retVal = false;
		if(name != null && name.length() >= MIN_NAME_LEN){
			// string not null and not empty
			final Matcher matcher = NAME_REGEXP.matcher(name);
			retVal = matcher.matches();
		}
		return retVal;
	}
	
//	@Override
//	public IndexCounter getCreationSerialCounter(){
//		return this.creationSerialCounter;
//	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICanvas#containsLabelAttribute(int)
	 */
	@Override
	public boolean containsLabelAttribute(int attributeSerial) {
		return findAttribute(this.labelAttributeIterator(), attributeSerial) != null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICanvas#containsLinkAttribute(int)
	 */
	@Override
	public boolean containsLinkAttribute(int attributeSerial) {
		return findAttribute(this.linkAttributeIterator(), attributeSerial) != null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICanvas#containsShapeAttribute(int)
	 */
	@Override
	public boolean containsShapeAttribute(int attributeSerial) {
		return findAttribute(this.shapeAttributeIterator(), attributeSerial) != null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICanvas#findAttribute(int)
	 */
	@Override
	public ICanvasElementAttribute findAttribute(int attributeSerial) {
		ICanvasElementAttribute retVal = findAttribute(this.canvasAttributeIterator(), attributeSerial);
		return retVal;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICanvas#getLabelAttribute(int)
	 */
	@Override
	public ILabelAttribute getLabelAttribute(int attributeSerial) {
		ILabelAttribute retVal = findAttribute(this.labelAttributeIterator(), attributeSerial);
		if(retVal == null) {
			throw new IllegalArgumentException("attributeSerial must refer to an attribute contained by this canvas");
		}
		return retVal;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICanvas#getLinkAttribute(int)
	 */
	@Override
	public ILinkAttribute getLinkAttribute(int attributeSerial) {
		ILinkAttribute retVal = findAttribute(this.linkAttributeIterator(), attributeSerial);
		if(retVal == null) {
			throw new IllegalArgumentException("attributeSerial must refer to an attribute contained by this canvas");
		}
		return retVal;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICanvas#getShapeAttribute(int)
	 */
	@Override
	public IShapeAttribute getShapeAttribute(int attributeSerial) {
		IShapeAttribute retVal = findAttribute(this.shapeAttributeIterator(), attributeSerial);
		if(retVal == null) {
			throw new IllegalArgumentException("attributeSerial must refer to an attribute contained by this canvas");
		}
		return retVal;
	}

	private static <T extends ICanvasElementAttribute> T findAttribute(Iterator<T> searchIter, int creationSerial) {
		T retVal = null;
		while(searchIter.hasNext() && retVal == null){
			T attribute = searchIter.next();
			if(attribute.getCreationSerial() == creationSerial) {
				retVal = attribute;
			}
		}
		return retVal;  
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICanvas#numCanvasAttributes()
	 */
	@Override
	public int numCanvasAttributes() {
		int retVal = 0;
		Iterator<ICanvasElementAttribute> iter = this.canvasAttributeIterator(); 
		while(iter.hasNext()){
			iter.next();
			retVal++;
		}
		return retVal;
	}

	private <T extends ICanvasElementAttribute> Iterator<T> createAttribIter(IFilterCriteria<ICanvasElementAttribute> criteria){
		FilteredIterator<ICanvasElementAttribute> filteredIter = new FilteredIterator<ICanvasElementAttribute>(this.canvasElementAttributes.iterator(), criteria);
		return new IterationCaster<T, ICanvasElementAttribute>(filteredIter);
	}
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICanvas#canvasIterator()
	 */
	@Override
	public Iterator<ICanvasElementAttribute> canvasAttributeIterator() {
		return createAttribIter(this.attribCriteria);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICanvas#linkAttributeIterator()
	 */
	@Override
	public Iterator<ILinkAttribute> linkAttributeIterator() {
		return createAttribIter(linkAttribCriteria);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICanvas#shapeAttributeIterator()
	 */
	@Override
	public Iterator<IShapeAttribute> shapeAttributeIterator() {
		return createAttribIter(this.shapeAttribCriteria);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICanvas#labelAttributeIterator()
	 */
	@Override
	public Iterator<ILabelAttribute> labelAttributeIterator() {
		return this.createAttribIter(labelAttribCriteria);
	}

	@Override
	public void addCanvasAttribute(ICanvasElementAttribute attribute) {
		this.canvasElementAttributes.add(attribute);
	}

	private static int attributesCounter(Iterator<? extends ICanvasElementAttribute> iter){
		int retVal = 0;
		while(iter.hasNext()){
			iter.next();
			retVal++;
		}
		return retVal;
	}
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IRootAttribute#numShapeAttributes()
	 */
	@Override
	public int numShapeAttributes() {
		return attributesCounter(this.shapeAttributeIterator());
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IRootAttribute#numLabelAttributes()
	 */
	@Override
	public int numLabelAttributes() {
		return attributesCounter(this.labelAttributeIterator());
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IRootAttribute#numLinkAttributes()
	 */
	@Override
	public int numLinkAttributes() {
		return attributesCounter(this.linkAttributeIterator());
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IRootAttribute#getCreationSerialCounter()
	 */
	@Override
	public ICanvasAttributeSequence getCreationSerialCounter() {
		return new ICanvasAttributeSequence(){

			@Override
			public int getCurrent() {
				return creationSerialCounter.getLastIndex();
			}

			@Override
			public int next() {
				return creationSerialCounter.nextIndex();
			}
			
		};
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ITypedDrawingNodeAttribute#shapeAttributeFactory()
	 */
	@Override
	public IShapeAttributeFactory shapeAttributeFactory() {
		return new ShapeAttributeFactory(this.creationSerialCounter);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ITypedDrawingNodeAttribute#linkAttributeFactory()
	 */
	@Override
	public ILinkAttributeFactory linkAttributeFactory() {
		return new LinkAttributeFactory(this.creationSerialCounter);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IRootAttribute#labelAttributeFactory()
	 */
	@Override
	public ILabelAttributeFactory labelAttributeFactory() {
		return new LabelAttributeFactory(this.creationSerialCounter);
	}


	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IModel#anchorNodeAttributeFactory()
	 */
	@Override
	public IAnchorNodeAttributeFactory anchorNodeAttributeFactory() {
		return new AnchorNodeAttributeFactory(this.creationSerialCounter);
	}

	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICanvas#getNotationSubsystem()
	 */
	@Override
	public INotationSubsystem getNotationSubsystem() {
		return this.notationSubsystem;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICanvas#getRootAttribute()
	 */
	@Override
	public IRootAttribute getRootAttribute() {
		return (IRootAttribute)this.compoundGraph.getRoot().getAttribute();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IModel#shapeNodeIterator()
	 */
	@Override
	public Iterator<ICompoundNode> shapeNodeIterator() {
		FilteredIterator<ICompoundNode> iter = new FilteredIterator<ICompoundNode>(this.compoundGraph.nodeIterator(),
				new IFilterCriteria<ICompoundNode>() {
					@Override
					public boolean matched(ICompoundNode testObj) {
						return testObj.getAttribute() instanceof IShapeAttribute;
					}
				});
		return iter;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IModel#labelNodeIterator()
	 */
	@Override
	public Iterator<ICompoundNode> labelNodeIterator() {
		FilteredIterator<ICompoundNode> iter = new FilteredIterator<ICompoundNode>(this.compoundGraph.nodeIterator(),
				new IFilterCriteria<ICompoundNode>() {
					@Override
					public boolean matched(ICompoundNode testObj) {
						return testObj.getAttribute() instanceof ILabelAttribute;
					}
				});
		return iter;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IModel#linkEdgeIterator()
	 */
	@Override
	public Iterator<ICompoundEdge> linkEdgeIterator() {
		return this.compoundGraph.edgeIterator();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IModel#numDrawingElements()
	 */
	@Override
	public int numDrawingElements() {
		return this.compoundGraph.numElements();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IModel#numDrawingNodes()
	 */
	@Override
	public int numDrawingNodes() {
		return this.compoundGraph.numNodes();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IModel#numShapeNodes()
	 */
	@Override
	public int numShapeNodes() {
		int retVal = 0;
		Iterator<ICompoundNode> shapeNodeIterator = this.shapeNodeIterator();
		while(shapeNodeIterator.hasNext()){
			shapeNodeIterator.next();
			retVal++;
		}
		return retVal;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IModel#numLabelNodes()
	 */
	@Override
	public int numLabelNodes() {
		int retVal = 0;
		Iterator<ICompoundNode> labelNodeIterator = this.labelNodeIterator();
		while(labelNodeIterator.hasNext()){
			labelNodeIterator.next();
			retVal++;
		}
		return retVal;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IModel#numLinkEdges()
	 */
	@Override
	public int numLinkEdges() {
		return this.compoundGraph.numEdges();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IModel#drawingNodeIterator()
	 */
	@Override
	public Iterator<ICompoundNode> drawingNodeIterator() {
		return this.compoundGraph.nodeIterator();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IModel#getLabelForProperty(org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationProperty)
	 */
	@Override
	public ILabelAttribute getLabelForProperty(final IAnnotationProperty annotationProperty) {
		ICanvasElementAttribute attrib = (ICanvasElementAttribute)annotationProperty.getOwner();
		ICompoundGraphElement graphElement = attrib.getCurrentElement();
		Iterator<ICompoundNode> childIter = new SubModelFacade(graphElement.getChildCompoundGraph()).labelIterator();
		ILabelAttribute retVal = null;
		while(childIter.hasNext() && retVal == null){
			ILabelAttribute nodeAtt = (ILabelAttribute)childIter.next().getAttribute();
			if(nodeAtt.getProperty().equals(annotationProperty)){
				retVal = nodeAtt;
			}
		}
		return retVal;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IModel#hasLabelForProperty(org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationProperty)
	 */
	@Override
	public boolean hasLabelForProperty(IAnnotationProperty annotationProperty) {
		return getLabelForProperty(annotationProperty) != null;
	}
}
