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
package org.pathwayeditor.businessobjects.hibernate.pojos;

// Generated 07-May-2008 22:43:44 by Hibernate Tools 3.2.1.GA

import java.io.Serializable;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.pathwayeditor.businessobjects.drawingprimitives.ILabelSubModel;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.Alignment;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LineStyle;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.PrimitiveShapeType;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.IPropertyChangeListener;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.ListenablePropertyChangeItem;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.PropertyChange;
import org.pathwayeditor.businessobjects.hibernate.helpers.InconsistentNotationDefinitionException;
import org.pathwayeditor.businessobjects.typedefn.IObjectType;
import org.pathwayeditor.businessobjects.typedefn.IShapeAttributeDefaults;
import org.pathwayeditor.businessobjects.typedefn.IShapeObjectType;
import org.pathwayeditor.figure.figuredefn.GraphicsInstructionList;
import org.pathwayeditor.figure.geometry.Dimension;
import org.pathwayeditor.figure.geometry.Envelope;
import org.pathwayeditor.figure.geometry.IConvexHull;
import org.pathwayeditor.figure.geometry.Point;

public class HibShapeAttribute extends HibAnnotatedCanvasAttribute implements IShapeAttribute,  Serializable {
	private static final long serialVersionUID = -8557015458835029042L;
	private transient final Logger logger = Logger.getLogger(this.getClass());

	private static final Point DEFAULT_POSITION = Point.ORIGIN;
	private static final Dimension DEFAULT_SIZE = new Dimension(10,10);
	private static final String DEFAULT_NAME = "";
	private static final String DEFAULT_DESCN = "";
	private static final String DEFAULT_DETAILS = "";
	private static final String DEFAULT_URL = "";
	private static final RGB DEFAULT_FILL = RGB.WHITE;
	private static final RGB DEFAULT_LINE = RGB.BLACK;
	private static final RGB DEFAULT_TEXT = RGB.BLACK;
	private static final LineStyle DEFAULT_LINE_STYLE = LineStyle.SOLID;
	private static final int DEFAULT_LINE_WIDTH = 1;
	private static final int DEFAULT_PADDING = 0;
	private static final PrimitiveShapeType DEFAULT_SHAPE_TYPE = PrimitiveShapeType.RECTANGLE;
	private static final boolean DEFAULT_NAME_VISIBLE = true ;
	private static final Alignment DEFAULT_ALIGNMENT = Alignment.CENTER ;

	private transient Point position = DEFAULT_POSITION;
	private transient Dimension size = DEFAULT_SIZE;
	private HibObjectType hibObjectType;
	private transient IShapeObjectType shapeObjectType;
	private String name = DEFAULT_NAME;
	private String description = DEFAULT_DESCN;
	private String detailedDescription = DEFAULT_DETAILS;
	private String url = DEFAULT_URL;
	private transient RGB fillColour = DEFAULT_FILL;
	private transient RGB lineColour = DEFAULT_LINE;
	private LineStyle lineStyle = DEFAULT_LINE_STYLE;
	private double lineWidth = DEFAULT_LINE_WIDTH;
	private int padding = DEFAULT_PADDING;
	private PrimitiveShapeType shapeType = DEFAULT_SHAPE_TYPE;
	private transient HibShapeNode shapeNode;
	private transient final ListenablePropertyChangeItem listenablePropertyChangeItem;
	private boolean nameVisible = DEFAULT_NAME_VISIBLE ;
	private Alignment horizontalAlignment = DEFAULT_ALIGNMENT;
	private Alignment verticalAlignment = DEFAULT_ALIGNMENT;
	private transient RGB textColour = DEFAULT_TEXT;
	private transient IConvexHull hull = null;
	private GraphicsInstructionList figureDefn;
	

	/**
	 * Default constructor to be used only by hibernate.
	 * @deprecated use any of the other constructors to construct this class in application code.
	 */
	HibShapeAttribute() {
		super();
		this.listenablePropertyChangeItem = new ListenablePropertyChangeItem();
	}

	public HibShapeAttribute(HibCanvas hibCanvas, int creationSerial, IShapeObjectType shapeObjectType, HibObjectType hibObjectType){
		super(hibCanvas, creationSerial, shapeObjectType.getDefaultAttributes());
		this.listenablePropertyChangeItem = new ListenablePropertyChangeItem();
		this.hibObjectType = hibObjectType;
		this.shapeObjectType = shapeObjectType;
		this.populateDefaults(shapeObjectType.getDefaultAttributes());
	}
	
	public HibShapeAttribute(HibCanvas newCanvas, int newCreationSerial, HibShapeAttribute other) {
		super(newCanvas, newCreationSerial, other);
		this.listenablePropertyChangeItem = new ListenablePropertyChangeItem();
		this.position = other.position;
		this.size = other.size;
		this.hibObjectType = other.hibObjectType;
		this.name = other.name;
		this.description = other.description;
		this.detailedDescription = other.detailedDescription;
		this.url = other.url;
		this.fillColour = other.fillColour;
		this.lineColour = other.lineColour;
		this.lineStyle = other.lineStyle;
		this.lineWidth = other.lineWidth;
		this.textColour = other.textColour ;
		this.nameVisible = other.nameVisible ;
		this.horizontalAlignment = other.horizontalAlignment;
		this.verticalAlignment = other.verticalAlignment ;
		this.padding = other.padding;
		this.shapeObjectType = other.shapeObjectType;
		this.shapeType=other.shapeType;
	}
	
	
	private void populateDefaults(IShapeAttributeDefaults shapeDefaults){
		this.setDescription(shapeDefaults.getDescription());
		this.setDetailedDescription(shapeDefaults.getDetailedDescription());
		this.setFillColour(shapeDefaults.getFillColour());
		this.setSize(shapeDefaults.getSize());
		this.setLineColour(shapeDefaults.getLineColour());
		this.setLineStyle(shapeDefaults.getLineStyle());
		this.setLineWidth(shapeDefaults.getLineWidth());
		this.setName(shapeDefaults.getName());
		this.setUrl(shapeDefaults.getURL());
		this.setPrimitiveShape(shapeDefaults.getShapeType());
	}

	public double getXPosition() {
		return this.position.getX();
	}

	public void setXPosition(double XPosition) {
		this.position = this.position.newX(XPosition);
	}

	public double getYPosition() {
		return this.position.getY();
	}
	
	public void setYPosition(double YPosition) {
		this.position = this.position.newY(YPosition);
	}

	public double getWidth() {
		return this.size.getWidth();
	}
	
	public void setWidth(double width) {
		this.size = this.size.newWidth(width);
	}

	public double getHeight() {
		return this.size.getHeight();
	}

	public void setHeight(double height) {
		this.size = this.size.newHeight(height);
	}

	public IShapeObjectType getObjectType() {
		return this.shapeObjectType;
	}
	
	@Override
	public void injectObjectType(IObjectType objectType) throws InconsistentNotationDefinitionException {
		this.shapeObjectType = (IShapeObjectType)objectType;
		super.injectPropertyDefinitions(shapeObjectType.getDefaultAttributes());
	}

	public HibObjectType getHibObjectType () {
		return this.hibObjectType ;
	}

	void setHibObjectType(HibObjectType hibObjectType) {
		this.hibObjectType = hibObjectType;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		if(name == null)
			throw new IllegalArgumentException("name cannot be null");
		
		String oldName = this.name;
		this.name = name;
		this.listenablePropertyChangeItem.notifyPropertyChange(PropertyChange.NAME, oldName, this.name);
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		if(description == null)
			throw new IllegalArgumentException("description cannot be null");
		
		String oldDescription = this.description;
		this.description = description;
		this.listenablePropertyChangeItem.notifyPropertyChange(PropertyChange.DESCRIPTION, oldDescription, this.description);
	}

	public String getDetailedDescription() {
		return this.detailedDescription;
	}

	public void setDetailedDescription(String detailedDescription) {
		if(detailedDescription == null)
			throw new IllegalArgumentException("detailedDescription cannot be null");
		
		String oldDetailedDescription = this.detailedDescription;
		this.detailedDescription = detailedDescription;
		this.listenablePropertyChangeItem.notifyPropertyChange(PropertyChange.DETAILED_DESCRIPTION, oldDetailedDescription, this.detailedDescription);
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		if(url == null) throw new IllegalArgumentException("The url cannot be null");
		
		String oldUrl = this.url;
		this.url = url;
		this.listenablePropertyChangeItem.notifyPropertyChange(PropertyChange.URL, oldUrl, this.url);
	}

	public int getFillRed() {
		return this.fillColour.getRed();
	}

	public void setFillRed(int fillRed) {
		this.fillColour = this.fillColour.newRed(fillRed);
	}

	public int getFillGreen() {
		return this.fillColour.getGreen();
	}
	
	public int getTextRed() {
		return this.textColour.getRed();
	}

	public void setTextRed(int textRed) {
		this.textColour = this.textColour.newRed(textRed);
	}

	public int getTextGreen() {
		return this.textColour.getGreen();
	}
	
	public void setTextGreen(int textGreen) {
		this.textColour = this.textColour.newGreen(textGreen);
	}
	
	public int getTextBlue() {
		return this.textColour.getBlue();
	}
	
	public void setTextBlue(int textBlue) {
		this.textColour = this.textColour.newBlue(textBlue);
	}

	public void setFillGreen(int fillGreen) {
		this.fillColour = this.fillColour.newGreen(fillGreen);
	}

	public int getFillBlue() {
		return this.fillColour.getBlue();
	}

	public void setFillBlue(int fillBlue) {
		this.fillColour = fillColour.newBlue(fillBlue);
	}

	public int getLineRed() {
		return this.lineColour.getRed();
	}

	public void setLineRed(int lineRed) {
		this.lineColour = this.lineColour.newRed(lineRed);
	}

	public int getLineGreen() {
		return this.lineColour.getGreen();
	}

	public void setLineGreen(int lineGreen) {
		this.lineColour = this.lineColour.newGreen(lineGreen);
	}

	public int getLineBlue() {
		return this.lineColour.getBlue();
	}

	public void setLineBlue(int lineBlue) {
		this.lineColour = this.lineColour.newBlue(lineBlue);
	}
	
	public LineStyle getLineStyle () {
		return this.lineStyle ;
	}
	
	public Alignment getHorizontalAlignment () 
	{
		return this.horizontalAlignment ;
	}
	
	public Alignment getVerticalAlignment () 
	{
		return this.verticalAlignment ;
	}
	
	public void setHibLineStyle(LineStyle lineStyle) {
		this.lineStyle = lineStyle;
	}
	
	public void setHibHorizontalAlignment ( Alignment alignment) {
		this.horizontalAlignment = alignment ;
	}
	
	public void setHibVerticalAlignment ( Alignment alignment) {
		this.verticalAlignment = alignment ;
	}

	public double getLineWidth() {
		return this.lineWidth;
	}

	public void setLineWidth(double lineWidth) {
		if ( lineWidth < MIN_LINE_WIDTH )
			throw new IllegalArgumentException ("Line width cannot be less than " + MIN_LINE_WIDTH) ;

		double oldLineWidth = this.lineWidth;
		this.lineWidth = lineWidth;
		this.listenablePropertyChangeItem.notifyPropertyChange(PropertyChange.LINE_WIDTH, oldLineWidth, this.lineWidth);
	}

	public int getPadding() {
		return this.padding;
	}

	public void setPadding(int padding) {
		int oldPadding = padding;
		this.padding = padding;
		this.listenablePropertyChangeItem.notifyPropertyChange(PropertyChange.PADDING, oldPadding, this.padding);
	}

	void setShapeNode(HibShapeNode newNode){
		this.shapeNode = newNode;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShape#getFillColour()
	 */
	public RGB getFillColour() {
		return this.fillColour;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShape#getLineColour()
	 */
	public RGB getLineColour() {
		return this.lineColour;
	}
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShape#getTextColour()
	 */
	public RGB getTextColour() {
		return this.textColour;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShape#getLocation()
	 */
	public Point getLocation() {
		return this.position;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShape#getPrimitiveShape()
	 */
	public PrimitiveShapeType getPrimitiveShape() {
		return this.shapeType;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShape#getSize()
	 */
	public Dimension getSize() {
		return this.size;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShape#setFillColour(org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB)
	 */
	public void setFillColour(RGB fillColour) {
		if ( fillColour == null )
			throw new IllegalArgumentException ("Fill colour cannot be null") ;

		RGB oldFillColour = this.fillColour;
		this.fillColour = fillColour;
		this.listenablePropertyChangeItem.notifyPropertyChange(PropertyChange.FILL_COLOUR, oldFillColour, this.fillColour);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShape#setTextColour(org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB)
	 */
	public void setTextColour(RGB textColour) {
		if ( textColour == null )
			throw new IllegalArgumentException ("Text colour cannot be null") ;

		RGB oldTextColour = this.textColour;
		this.textColour = textColour;
		this.listenablePropertyChangeItem.notifyPropertyChange(PropertyChange.FILL_COLOUR, oldTextColour, this.textColour);
	}
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShape#setLineColour(org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB)
	 */
	public void setLineColour(RGB lineColour) {
		if ( lineColour == null )
			throw new IllegalArgumentException ("Line colour cannot be null") ;

		RGB oldLineColour = this.lineColour;
		this.lineColour = lineColour;
		this.listenablePropertyChangeItem.notifyPropertyChange(PropertyChange.LINE_COLOUR, oldLineColour, this.lineColour);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShape#setLineStyle(org.pathwayeditor.businessobjects.drawingprimitives.attributes.LineStyle)
	 */
	public void setLineStyle(LineStyle lineStyle) {
		if ( lineStyle == null )
			throw new IllegalArgumentException ( "Line style cannot be null") ;

		LineStyle oldLineStyle = this.lineStyle;
		this.lineStyle = lineStyle ;
		this.listenablePropertyChangeItem.notifyPropertyChange(PropertyChange.LINE_STYLE, oldLineStyle, this.lineStyle);
	}
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShape#setHorizontalAlignment(org.pathwayeditor.businessobjects.drawingprimitives.attributes.Alignment)
	 */
	public void setHorizontalAlignment(Alignment alignment) {
		if ( alignment == null )
			throw new IllegalArgumentException ( "Alignment cannot be null") ;

		Alignment oldHorizontalAlignment = this.horizontalAlignment;
		this.horizontalAlignment = alignment ;
		this.listenablePropertyChangeItem.notifyPropertyChange(PropertyChange.ALIGNMENT, oldHorizontalAlignment, this.horizontalAlignment);
	}
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShape#setHorizontalAlignment(org.pathwayeditor.businessobjects.drawingprimitives.attributes.Alignment)
	 */
	public void setVerticalAlignment(Alignment alignment) {
		if ( alignment == null )
			throw new IllegalArgumentException ( "Alignment cannot be null") ;

		Alignment oldVerticalAlignment = this.verticalAlignment;
		this.verticalAlignment = alignment ;
		this.listenablePropertyChangeItem.notifyPropertyChange(PropertyChange.ALIGNMENT, oldVerticalAlignment, this.verticalAlignment);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShape#setLocation(org.pathwayeditor.businessobjects.drawingprimitives.attributes.Location)
	 */
	public void setLocation(Point newLocation) {
		if ( newLocation == null )
			throw new IllegalArgumentException ("the new location cannot be null") ;

		if(!this.position.equals(newLocation)){
			Point oldLocation = this.position;
			this.position = newLocation;
			this.listenablePropertyChangeItem.notifyPropertyChange(PropertyChange.LOCATION, oldLocation, this.position);
		}
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShape#setPrimitiveShape(org.pathwayeditor.businessobjects.drawingprimitives.attributes.IPrimitiveShape)
	 */
	public void setPrimitiveShape(PrimitiveShapeType primitiveShape) {
		if(primitiveShape == null) throw new IllegalArgumentException("primitive shape cannot be null");

		PrimitiveShapeType oldPrimitiveShape = this.shapeType;
		this.shapeType = primitiveShape;
		this.listenablePropertyChangeItem.notifyPropertyChange(PropertyChange.PRIMITIVE_SHAPE_TYPE, oldPrimitiveShape, this.shapeType);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShape#setSize(org.pathwayeditor.businessobjects.drawingprimitives.attributes.Size)
	 */
	public void setSize(Dimension size) {
		if ( size == null )
			throw new IllegalArgumentException ("Size cannot be null") ;

		if(!this.size.equals(size)){
			Dimension oldSize = this.size;
			this.size = size;
			this.listenablePropertyChangeItem.notifyPropertyChange(PropertyChange.SIZE, oldSize, this.size);
		}
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.listeners.ChangeListenee#addChangeListener(org.pathwayeditor.businessobjects.drawingprimitives.listeners.IPropertyChangeListener)
	 */
	public void addChangeListener(IPropertyChangeListener listener) {
		this.listenablePropertyChangeItem.addChangeListener(listener);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.listeners.ChangeListenee#removeChangeListener(org.pathwayeditor.businessobjects.drawingprimitives.listeners.IPropertyChangeListener)
	 */
	public void removeChangeListener(IPropertyChangeListener listener) {
		this.listenablePropertyChangeItem.removeChangeListener(listener);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.listeners.ChangeListenee#listenerIterator()
	 */
	public Iterator<IPropertyChangeListener> listenerIterator() {
		return this.listenablePropertyChangeItem.listenerIterator();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShapeAttribute#getCurrentDrawingElement()
	 */
	public HibShapeNode getCurrentDrawingElement() {
		return this.shapeNode;
	}

	public boolean isValid() {
		boolean objectTypeSet = this.getObjectType() != null;
		boolean reciprocalAttributeSet = this.getCurrentDrawingElement().getAttribute() != null
				&& this.getCurrentDrawingElement().getAttribute().equals(this);
		boolean syntaxRulesCorrect = this.getCurrentDrawingElement().getParent().getObjectType() != null
			&& this.getCurrentDrawingElement().getParent().getObjectType().getParentingRules().isValidChild(this.getObjectType());
		boolean propertiesValid = super.arePropertiesValid(this.getObjectType().getDefaultAttributes());
		if (!objectTypeSet || !reciprocalAttributeSet || !syntaxRulesCorrect || !propertiesValid) {
			logger.error("Attribute invalid, may be objecttypes or incompletely formed relationship with node");
		}
		return objectTypeSet && reciprocalAttributeSet && syntaxRulesCorrect && propertiesValid;
	}
	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShapeAttribute#isNameVisible()
	 */
	public boolean isNameVisible() {
		return this.nameVisible;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShapeAttribute#setNameVisible(boolean)
	 */
	public void setNameVisible(boolean value) {
		this.nameVisible = value ;
		
		boolean oldNameVisible = this.nameVisible;
		this.nameVisible = value;
		this.listenablePropertyChangeItem.notifyPropertyChange(PropertyChange.NAME_VISIBLE, oldNameVisible, this.nameVisible);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotatedObject#getLabelSubModel()
	 */
	public ILabelSubModel getLabelSubModel() {
		return this.getCurrentDrawingElement().getSubModel();
	}
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IDrawingNodeAttribute#getBounds()
	 */
	public Envelope getBounds() {
		return new Envelope(this.position, this.size);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IDrawingNodeAttribute#setBounds(org.pathwayeditor.businessobjects.drawingprimitives.attributes.Bounds)
	 */
	public void setBounds(Envelope newBounds) {
		this.setLocation(newBounds.getOrigin());
		this.setSize(newBounds.getDimension());
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShapeAttribute#getConvexHull()
	 */
	public IConvexHull getConvexHull() {
		return this.hull;
	}
	
	public void setConvexHull(IConvexHull hull){
		this.hull = hull;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShapeAttribute#getFigureDefinition()
	 */
	public GraphicsInstructionList getFigureDefinition() {
		return this.figureDefn;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShapeAttribute#setFigureDefinition(org.pathwayeditor.figure.customfigure.GraphicsInstructionList)
	 */
	public void setFigureDefinition(GraphicsInstructionList instList) {
		this.figureDefn = instList;
	}

	public boolean areListenersEnabled() {
		return this.listenablePropertyChangeItem.areListenersEnabled();
	}

	public void setListenersEnabled(boolean enabled) {
		this.listenablePropertyChangeItem.setListenersEnabled(enabled);
	}

}
