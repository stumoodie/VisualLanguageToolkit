package org.pathwayeditor.businessobjects.hibernate.pojos;

import java.io.Serializable;

import org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationProperty;

/**
 * Property generated by hbm2java
 */
public abstract class HibProperty implements IAnnotationProperty, Serializable {
	private static final long serialVersionUID = -1996477907215294788L;

	private Long id = null;
	private HibCanvas hibCanvas = null;
	private int creationSerial;
	private boolean displayed;

	/**
	 * Constructor should only be used by hiberate.
	 * @deprecated Application code should not use this constructor. Use one of the other constructors instead.
	 */
	protected HibProperty() {
	}

	protected HibProperty(HibCanvas hibCanvas, int creationSerial) {
		this.hibCanvas = hibCanvas;
		this.creationSerial = creationSerial;
	}

	protected HibProperty(HibCanvas newCanvas, int newCreationSerial,	HibProperty other) {
		this.hibCanvas = newCanvas;
		this.creationSerial = newCreationSerial;
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

	@SuppressWarnings("unused")
	private void setCanvas(HibCanvas hibCanvas) {
		this.hibCanvas = hibCanvas;
	}

	public int getCreationSerial() {
		return this.creationSerial;
	}

	public final void setDisplayed(boolean displayed){
		this.displayed = displayed;
	}
	
	public final boolean isDisplayed(){
		return this.displayed;
	}
	
	@SuppressWarnings("unused")
	private void setCreationSerial(int creationSerial) {
		this.creationSerial = creationSerial;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof HibProperty))
			return false;
		HibProperty castOther = (HibProperty) other;

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

}
