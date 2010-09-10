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
package org.pathwayeditor.businessobjects.impl;

import java.util.Iterator;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.pathwayeditor.businessobjects.drawingprimitives.ICanvasElementAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.ICanvasElementAttributeVisitor;
import org.pathwayeditor.businessobjects.drawingprimitives.ILabelAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.ILabelAttributeFactory;
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkAttributeFactory;
import org.pathwayeditor.businessobjects.drawingprimitives.IRootAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeAttributeFactory;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.CanvasAttributeChangeListenerHelper;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.CanvasAttributePropertyChange;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.ICanvasAttributeChangeListener;
import org.pathwayeditor.businessobjects.typedefn.IRootObjectType;
import org.pathwayeditor.figure.geometry.Dimension;
import org.pathwayeditor.figure.geometry.Envelope;
import org.pathwayeditor.figure.geometry.Point;

import uk.ac.ed.inf.graph.compound.IElementAttributeFactory;
import uk.ac.ed.inf.graph.compound.IRootCompoundNode;
import uk.ac.ed.inf.graph.util.IFilterCriteria;
import uk.ac.ed.inf.graph.util.IndexCounter;
import uk.ac.ed.inf.graph.util.impl.FilteredIterator;

/**
 * @author smoodie
 *
 */
public class RootAttribute extends CanvasAttribute implements IRootAttribute {
	private static final int DEFAULT_BGD_GREEN = 255;
	private static final int DEFAULT_BGD_BLUE = 255;
	private static final int DEFAULT_BGD_RED = 255;
	private static final int MIN_NAME_LEN = 1;
	private static final Pattern NAME_REGEXP = Pattern.compile("(\\w.*\\w)|(\\w)");
	public static final RGB DEFAULT_BACKGROUND_COLOUR = new RGB(DEFAULT_BGD_RED, DEFAULT_BGD_GREEN, DEFAULT_BGD_BLUE);
	
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

	public static final Point INITIAL_POS = new Point(-0.5 * Double.MAX_VALUE, -0.5 *Double.MAX_VALUE);
	public static final Dimension INITIAL_SIZE = new Dimension(Double.MAX_VALUE, Double.MAX_VALUE);
	public static final int ROOT_IDX = 0;
	private final IRootObjectType objectType;
	private final CanvasAttributeChangeListenerHelper canvasAttributeChangeListenerHelper = new CanvasAttributeChangeListenerHelper(this);
	private RGB backgroundColour = DEFAULT_BACKGROUND_COLOUR;
	private String name;
	private final IndexCounter creationSerialCounter;
	private final SortedSet<ICanvasElementAttribute> canvasElementAttributes = new TreeSet<ICanvasElementAttribute>();
	private final BoundsHelper boundsDelegate = new BoundsHelper(new Envelope(INITIAL_POS, INITIAL_SIZE), canvasAttributeChangeListenerHelper);

	public RootAttribute(String name, IRootObjectType objectType) {
		super(ROOT_IDX);
		this.name = name;
		this.objectType = objectType;
		this.creationSerialCounter = new IndexCounter(ROOT_IDX);
	}

	public RootAttribute(RootAttribute otherAttribute) {
		super(ROOT_IDX);
		this.objectType = otherAttribute.getObjectType();
		this.backgroundColour = otherAttribute.backgroundColour;
		this.name = otherAttribute.name;
		this.creationSerialCounter = new IndexCounter(ROOT_IDX);
		this.boundsDelegate.setBounds(otherAttribute.getBounds());
	}

	@Override
	public String getName() {
		return this.name;
	}
	
	@Override
	public void setName(String name){
		if(!isValidName(name)) throw new IllegalArgumentException("Invalid name: " + name);
		
		if(!this.name.equals(name)){
			String oldValue = this.name;
			this.name = name;
			canvasAttributeChangeListenerHelper.notifyPropertyChange(CanvasAttributePropertyChange.NAME, oldValue, this.name);
		}
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICanvasAttribute#getObjectType()
	 */
	@Override
	public IRootObjectType getObjectType() {
		return this.objectType;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IDrawingNodeAttribute#getBounds()
	 */
	@Override
	public Envelope getBounds() {
		return this.boundsDelegate.getBounds();
	}

	@Override
	public RGB getBackgroundColour() {
		return this.backgroundColour;
	}

	@Override
	public void setBackgroundColour(RGB backgroundColour) {
		if ( backgroundColour == null)
			throw new IllegalArgumentException ( "BackgroundColor cannot be null") ;

		RGB oldBackgroundColour = this.backgroundColour;
		this.backgroundColour = backgroundColour;
		this.canvasAttributeChangeListenerHelper.notifyPropertyChange(CanvasAttributePropertyChange.FILL_COLOUR, oldBackgroundColour, this.backgroundColour);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IDrawingNodeAttribute#setBounds(org.pathwayeditor.businessobjects.drawingprimitives.attributes.Bounds)
	 */
	@Override
	public void setBounds(Envelope newBounds) {
		this.boundsDelegate.setBounds(newBounds);
	}

	@Override
	public final void addChangeListener(ICanvasAttributeChangeListener listener) {
		this.canvasAttributeChangeListenerHelper.addChangeListener(listener);
	}

	@Override
	public final List<ICanvasAttributeChangeListener> getChangeListeners() {
		return this.canvasAttributeChangeListenerHelper.getChangeListeners();
	}

	@Override
	public final void removeChangeListener(ICanvasAttributeChangeListener listener) {
		this.canvasAttributeChangeListenerHelper.removeChangeListener(listener);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IDrawingNodeAttribute#resize(org.pathwayeditor.figure.geometry.Point, org.pathwayeditor.figure.geometry.Dimension)
	 */
	@Override
	public void resize(Point locationDelta, Dimension sizeDelta) {
		// do nothing
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IDrawingNodeAttribute#translate(org.pathwayeditor.figure.geometry.Point)
	 */
	@Override
	public void translate(Point delta) {
		// do nothing
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
		ILabelAttribute retVal = (ILabelAttribute)findAttribute(this.labelAttributeIterator(), attributeSerial);
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
		ILinkAttribute retVal = (ILinkAttribute)findAttribute(this.linkAttributeIterator(), attributeSerial);
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
		IShapeAttribute retVal = (IShapeAttribute)findAttribute(this.shapeAttributeIterator(), attributeSerial);
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

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICanvasAttribute#getRootAttribute()
	 */
	@Override
	public IRootAttribute getRootAttribute() {
		return this;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICanvasElementAttribute#visit(org.pathwayeditor.businessobjects.drawingprimitives.ICanvasElementAttributeVisitor)
	 */
	@Override
	public void visit(ICanvasElementAttributeVisitor visitor) {
		visitor.visitRoot(this);
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
	 * @see uk.ac.ed.inf.graph.compound.IElementAttribute#elementAttributeCopyFactory()
	 */
	@Override
	public IElementAttributeFactory elementAttributeCopyFactory() {
		throw new UnsupportedOperationException("The root attribute should never be copied in this way");
	}

	/* (non-Javadoc)
	 * @see uk.ac.ed.inf.graph.compound.IElementAttribute#elementAttributeMoveFactory()
	 */
	@Override
	public IElementAttributeFactory elementAttributeMoveFactory() {
		throw new UnsupportedOperationException("The root attribute should never be moved in this way");
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IRootAttribute#getCreationSerialCounter()
	 */
	@Override
	public IndexCounter getCreationSerialCounter() {
		return this.creationSerialCounter;
	}

	@Override
	public IRootCompoundNode getCurrentElement(){
		return (IRootCompoundNode)super.getCurrentElement();
	}
}
