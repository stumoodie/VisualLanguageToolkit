package org.pathwayeditor.businessobjects.hibernate.pojos;

// Generated 07-May-2008 22:43:44 by Hibernate Tools 3.2.1.GA

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.pathwayeditor.businessobjects.pojos.IBusinessObjectData;
import org.pathwayeditor.businessobjects.pojos.Shape;

/**
 * Shape generated by hbm2java
 */
public class HibShape implements IBusinessObjectData<Shape>, Serializable {
	private static final long serialVersionUID = -8557015458835029042L;

	private HibCanvas canvas;
	private Long id;
	private int creationSerial;
	private int XPosition;
	private int YPosition;
	private int width;
	private int height;
	private HibObjectType hibObjectType;
	private String name;
	private String description;
	private String detailedDescription;
	private String url;
	private int fillRed;
	private int fillGreen;
	private int fillBlue;
	private int lineRed;
	private int lineGreen;
	private int lineBlue;
	private int lineStyle;
	private int lineWidth;
	private int padding;
	private short shapeType;
	private Map<String, HibProperty> hibProperties = new HashMap<String, HibProperty>(0);

	public HibShape() {
	}

	public HibShape(HibCanvas hibCanvas, int creationSerial){
		this.canvas = hibCanvas;
		this.creationSerial = creationSerial;
	}
	
	public HibShape(HibShape other) {
		this.XPosition = other.XPosition;
		this.YPosition = other.YPosition;
		this.width = other.width;
		this.height = other.height;
		this.hibObjectType = other.hibObjectType;
		this.name = other.name;
		this.description = other.description;
		this.detailedDescription = other.detailedDescription;
		this.url = other.url;
		this.fillRed = other.fillRed;
		this.fillGreen = other.fillGreen;
		this.fillBlue = other.fillBlue;
		this.lineRed = other.lineRed;
		this.lineGreen = other.lineGreen;
		this.lineBlue = other.lineBlue;
		this.lineStyle = other.lineStyle;
		this.lineWidth = other.lineWidth;
		this.padding = other.padding;

		for (String propertyName : other.hibProperties.keySet()) {
			HibProperty otherProp = other.hibProperties.get(propertyName);
			this.hibProperties.put(propertyName, otherProp.copy(getCanvas()));
		}
	}

	public Long getId() {
		return this.id;
	}

	void setId(Long id) {
		this.id = id;
	}

	void setCreationSerial(int creationSerial){
		this.creationSerial = creationSerial;
	}
	
	public int getCreationSerial(){
		return this.creationSerial;
	}
	
	public int getXPosition() {
		return this.XPosition;
	}

	public void setXPosition(int XPosition) {
		this.XPosition = XPosition;
	}

	public int getYPosition() {
		return this.YPosition;
	}

	public void setYPosition(int YPosition) {
		this.YPosition = YPosition;
	}

	public int getWidth() {
		return this.width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return this.height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public HibObjectType getObjectType() {
		return this.hibObjectType;
	}

	void setObjectType(HibObjectType hibObjectType) {
		this.hibObjectType = hibObjectType;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDetailedDescription() {
		return this.detailedDescription;
	}

	public void setDetailedDescription(String detailedDescription) {
		this.detailedDescription = detailedDescription;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getFillRed() {
		return this.fillRed;
	}

	public void setFillRed(int fillRed) {
		this.fillRed = fillRed;
	}

	public int getFillGreen() {
		return this.fillGreen;
	}

	public void setFillGreen(int fillGreen) {
		this.fillGreen = fillGreen;
	}

	public int getFillBlue() {
		return this.fillBlue;
	}

	public void setFillBlue(int fillBlue) {
		this.fillBlue = fillBlue;
	}

	public int getLineRed() {
		return this.lineRed;
	}

	public void setLineRed(int lineRed) {
		this.lineRed = lineRed;
	}

	public int getLineGreen() {
		return this.lineGreen;
	}

	public void setLineGreen(int lineGreen) {
		this.lineGreen = lineGreen;
	}

	public int getLineBlue() {
		return this.lineBlue;
	}

	public void setLineBlue(int lineBlue) {
		this.lineBlue = lineBlue;
	}

	public int getLineStyle() {
		return this.lineStyle;
	}

	public void setLineStyle(int lineStyle) {
		this.lineStyle = lineStyle;
	}

	public int getLineWidth() {
		return this.lineWidth;
	}

	public void setLineWidth(int lineWidth) {
		this.lineWidth = lineWidth;
	}

	public int getPadding() {
		return this.padding;
	}

	public void setPadding(int padding) {
		this.padding = padding;
	}

	public short getShapeType() {
		return this.shapeType;
	}

	public void setShapeType(short shapeType) {
		this.shapeType = shapeType;
	}

	public Map<String, HibProperty> getProperties() {
		return this.hibProperties;
	}

	public void setProperties(Map<String, HibProperty> hibProperties) {
		this.hibProperties = hibProperties;
	}

	public HibCanvas getCanvas() {
		return this.canvas;
	}

	public void changeCanvas(HibCanvas newCanvas) {
		HibCanvas oldCanvas = this.canvas ;
		this.canvas = newCanvas;
		if (oldCanvas != null) {
			oldCanvas.getShapes().remove(this);
		}
		if (this.canvas != null) {
			this.canvas.getShapes().add(this);
		}
		
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.pojos.IBusinessObjectData#getBusinessObject()
	 */
	public Shape getBusinessObject() {
		// TODO Auto-generated method stub
		return null;
	}
}
