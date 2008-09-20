package org.pathwayeditor.businessobjects.hibernate.pojos;

import java.io.Serializable;

import org.pathwayeditor.businessobjects.drawingprimitives.ICanvas;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPlainTextAnnotationProperty;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPlainTextPropertyDefinition;

/**
 * TextProperty generated by hbm2java
 */
public class HibTextProperty extends HibProperty implements Serializable,
		IPlainTextAnnotationProperty {
	private static final long serialVersionUID = 2408749022194227955L;

	private String textValue;
	private IPlainTextPropertyDefinition propDefn;

	HibTextProperty() {
	}

	public HibTextProperty(ICanvas hibCanvas, int creationSerial, IPlainTextPropertyDefinition defn) {
		super(hibCanvas, creationSerial);
		this.propDefn = defn;
		this.textValue = defn.getDefaultValue();
	}

	public HibTextProperty(ICanvas newCanvas, int newCreationSerial, HibTextProperty other) {
		super(newCanvas, newCreationSerial, other);
		this.propDefn = other.propDefn;
		this.textValue = other.textValue;
	}

	public String getTextValue() {
		return this.textValue;
	}

	public void setTextValue(String textValue) {
		if (textValue == null)
			throw new IllegalArgumentException("Text value cannot be null.");

		this.textValue = textValue;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.pathwayeditor.businessobjects.drawingprimitives.properties.
	 * IPlainTextAnnotationProperty#getDefinition()
	 */
	public IPlainTextPropertyDefinition getDefinition() {
		return this.propDefn;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.pathwayeditor.businessobjects.drawingprimitives.properties.
	 * IAnnotationProperty#getValue()
	 */
	public Object getValue() {
		return textValue;
	}
}
