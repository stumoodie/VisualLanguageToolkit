package org.pathwayeditor.businessobjects.hibernate.pojos;

import java.io.Serializable;

import org.pathwayeditor.businessobjects.drawingprimitives.ILabelAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.Location;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.Size;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationProperty;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyDefinition;
import org.pathwayeditor.businessobjects.typedefn.ILabelAttributeDefaults;
import org.pathwayeditor.businessobjects.typedefn.INodeObjectType;

public class HibLabelAttribute implements Serializable, ILabelAttribute {
	private static final long serialVersionUID = -2354270083525870259L;

	private static final int DEFAULT_X = 0;
	private static final int DEFAULT_Y = 0;
	private static final int DEFAULT_HEIGHT = 0;
	private static final int DEFAULT_WIDTH = 0;

	private Long id;
	private HibCanvas hibCanvas;
	private int creationSerial;
	private Location position = new Location(DEFAULT_X, DEFAULT_Y);
	private Size size = new Size(DEFAULT_WIDTH, DEFAULT_HEIGHT);
	private HibProperty visualisableProperty;
	private RGB background;
	private HibLabelNode labelNode;
	private INodeObjectType objectType;

	/**
	 * Default constructor that should only be used by hibernate.
	 * @deprecated should not be used by hibernate code, use one of the other constructors. 
	 */
	HibLabelAttribute() {
		size = new Size (0 , 0) ;
		position = new Location ( 0 , 0 ) ;
		background = new RGB ( 0 , 0 ,0 ) ;
	}

	public HibLabelAttribute(HibCanvas hibCanvas, int creationSerial, HibProperty property,	ILabelAttributeDefaults labelDefaults) {
		this.hibCanvas = hibCanvas;
		this.creationSerial = creationSerial;
		this.visualisableProperty = property;
		this.objectType = new LabelObjectType(hibCanvas.getNotationSubsystem().getSyntaxService());
		populateDefaults(labelDefaults);
	}

	private void populateDefaults(ILabelAttributeDefaults labelDefaults) {
		this.setSize(labelDefaults.getSize());
		this.setBackgroundColor(labelDefaults.getFillColour());
	}

	public Long getId() {
		return this.id;
	}

	@SuppressWarnings("unused")
	private void setId(Long id) {
		this.id = id;
	}

	public HibCanvas getCanvas() {
		return this.hibCanvas;
	}

	void setCanvas(HibCanvas hibCanvas) {
		this.hibCanvas = hibCanvas;
	}

	public void changeHibCanvas(HibCanvas canvas){
		if(this.hibCanvas != null){
			this.hibCanvas.getLabelAttributes().remove(this);
		}
		if(canvas != null){
			canvas.getLabelAttributes().add(this);
		}
		this.setCanvas(canvas);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.pathwayeditor.businessobjects.drawingprimitives.ICanvasObject#
	 * getCreationSerial()
	 */

	public int getCreationSerial() {
		return this.creationSerial;
	}

	public void setCreationSerial(int label_index) {
		this.creationSerial = label_index;
	}

	public Location getPosition() {
		return this.position;
	}

	public HibLabelNode getLabelNode() {
		return this.labelNode;
	}

	public void setLabelNode(HibLabelNode nodeId) {
		this.labelNode = nodeId;
	}

	public void setXPosition(int XPosition) {
		this.position = this.position.newX(XPosition);
	}

	public int getXPosition() {
		return this.position.getX();
	}

	public int getYPosition() {
		return this.position.getY();
	}

	public void setYPosition(int YPosition) {
		this.position = this.position.newY(YPosition);
	}

	public int getWidth() {
		return this.size.getWidth();
	}

	public void setWidth(int width) {
		this.size = this.size.newWidth(width);
	}

	public int getHeight() {
		return this.size.getHeight();
	}

	public void setHeight(int height) {
		this.size = this.size.newHeight(height);
	}

	public IAnnotationProperty getVisualisableProperty() {
		return this.visualisableProperty;
	}

	public void setVisualisableProperty(HibProperty visualisableProperty) {
		this.visualisableProperty = visualisableProperty;
	}

	public int getBackgroundRed() {
		return this.background.getRed();
	}

	public void setBackgroundRed(int backgroundRed) {
		this.background = this.background.newRed(backgroundRed);
	}

	public int getBackgroundGreen() {
		return this.background.getGreen();
	}

	public void setBackgroundGreen(int backgroundGreen) {
		this.background = this.background.newGreen(backgroundGreen);
	}

	public int getBackgroundBlue() {
		return this.background.getBlue();
	}

	public void setBackgroundBlue(int backgroundBlue) {
		this.background = this.background.newBlue(backgroundBlue);
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof HibLabelAttribute))
			return false;
		HibLabelAttribute castOther = (HibLabelAttribute) other;

		return ((this.getCanvas() == castOther.getCanvas()) || (this
				.getCanvas() != null
				&& castOther.getCanvas() != null && this.getCanvas().equals(
				castOther.getCanvas())))
				&& (this.getCreationSerial() == castOther.getCreationSerial());
	}

	public int hashCode() {
		int result = 17;
		result = 37 * result
				+ (getCanvas() == null ? 0 : this.getCanvas().hashCode());
		result = 37 * result + this.getCreationSerial();
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.pathwayeditor.businessobjects.drawingprimitives.ILabelAttribute#
	 * getLocation()
	 */
	public Location getLocation() {
		return this.position;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.pathwayeditor.businessobjects.drawingprimitives.ILabelAttribute#
	 * getProperty()
	 */
	public IAnnotationProperty getProperty() {
		return this.visualisableProperty;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.pathwayeditor.businessobjects.drawingprimitives.ILabelAttribute#getSize
	 * ()
	 */
	public Size getSize() {
		return this.size;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.pathwayeditor.businessobjects.drawingprimitives.ILabelAttribute#
	 * getBackgroundColor()
	 */
	public RGB getBackgroundColor() {
		return this.background;
	}

	public void setBackgroundColor(RGB color) {
		if (color == null)
			throw new IllegalArgumentException("Color cannot be null.");

		this.background = color;

	}
	
	public void setObjectType ( INodeObjectType nodeObjectType)
	{
		this.objectType = nodeObjectType ;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.pathwayeditor.businessobjects.drawingprimitives.ILabelAttribute#
	 * setLocation
	 * (org.pathwayeditor.businessobjects.drawingprimitives.attributes.Location)
	 */
	public void setLocation(Location location) {
		if (location == null)
			throw new IllegalArgumentException("location cannot be null.");

		this.position = location;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.pathwayeditor.businessobjects.drawingprimitives.ILabelAttribute#setSize
	 * (org.pathwayeditor.businessobjects.drawingprimitives.attributes.Size)
	 */
	public void setSize(Size size) {
		if (size == null)
			throw new IllegalArgumentException("size cannot be null.");

		this.size = size;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.pathwayeditor.businessobjects.drawingprimitives.ICanvasAttribute#
	 * getObjectType()
	 */
	public INodeObjectType getObjectType() {
		return this.objectType;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICanvasAttribute#hasProperty(org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyDefinition)
	 */
	public boolean hasProperty(IPropertyDefinition property) {
		return this.visualisableProperty.getDefinition().equals(property);
	}
}
