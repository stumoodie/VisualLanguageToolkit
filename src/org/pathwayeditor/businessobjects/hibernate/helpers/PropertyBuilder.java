/**
 * 
 */
package org.pathwayeditor.businessobjects.hibernate.helpers;

import org.pathwayeditor.businessobjects.drawingprimitives.properties.IHtmlAnnotationProperty;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IHtmlPropertyDefinition;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IListAnnotationProperty;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IListPropertyDefinition;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.INumberAnnotationProperty;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.INumberPropertyDefinition;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPlainTextAnnotationProperty;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPlainTextPropertyDefinition;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyBuilder;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibCanvas;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibListProperty;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibNumberProperty;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibProperty;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibRichTextProperty;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibTextProperty;

/**
 * @author smoodie
 *
 */
public class PropertyBuilder implements IPropertyBuilder {
	private final HibCanvas canvas;
	
	public PropertyBuilder(HibCanvas canvas){
		this.canvas = canvas;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.hibernate.pojos.IPropertyVisitor#createHtmlProperty(org.pathwayeditor.businessobjects.drawingprimitives.properties.IHtmlPropertyDefinition)
	 */
	public IHtmlAnnotationProperty createHtmlProperty(IHtmlPropertyDefinition propDefn) {
		IHtmlAnnotationProperty anHTMLProperty = new HibRichTextProperty(canvas, this.canvas.getAttributeSerialCounter().nextIndex(), propDefn);
		canvas.getProperties().add((HibProperty) anHTMLProperty) ;
		return anHTMLProperty ;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.hibernate.pojos.IPropertyVisitor#createPlainTextProperty(org.pathwayeditor.businessobjects.drawingprimitives.properties.IPlainTextPropertyDefinition)
	 */
	public IPlainTextAnnotationProperty createPlainTextProperty(IPlainTextPropertyDefinition propDefn) {
		IPlainTextAnnotationProperty aTextProperty = new HibTextProperty(canvas, this.canvas.getAttributeSerialCounter().nextIndex(), propDefn); 
		canvas.getProperties().add((HibProperty) aTextProperty) ;
		return aTextProperty ;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyBuilder#copyHtmlProperty(org.pathwayeditor.businessobjects.drawingprimitives.properties.IHtmlPropertyDefinition)
	 */
	public IHtmlAnnotationProperty copyHtmlProperty(IHtmlAnnotationProperty other) {
		return new HibRichTextProperty(canvas, this.canvas.getAttributeSerialCounter().nextIndex(), (HibRichTextProperty)other);
	}


	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyBuilder#copyNumberProperty(org.pathwayeditor.businessobjects.drawingprimitives.properties.INumberPropertyDefinition)
	 */
	public INumberAnnotationProperty copyNumberProperty(INumberAnnotationProperty other) {
		return new HibNumberProperty(canvas, this.canvas.getAttributeSerialCounter().nextIndex(), (HibNumberProperty)other);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyBuilder#copyPlainTextProperty(org.pathwayeditor.businessobjects.drawingprimitives.properties.IPlainTextPropertyDefinition)
	 */
	public IPlainTextAnnotationProperty copyPlainTextProperty(IPlainTextAnnotationProperty other) {
		return new HibTextProperty(canvas, this.canvas.getAttributeSerialCounter().nextIndex(), (HibTextProperty)other);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyBuilder#createNumberProperty(org.pathwayeditor.businessobjects.drawingprimitives.properties.INumberPropertyDefinition)
	 */
	public INumberAnnotationProperty createNumberProperty(INumberPropertyDefinition propDefn) {
		INumberAnnotationProperty aNumberProperty =  new HibNumberProperty(canvas, this.canvas.getAttributeSerialCounter().nextIndex(), propDefn); 
		canvas.getProperties().add((HibProperty) aNumberProperty);
		return aNumberProperty ;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyBuilder#copyListProperty(org.pathwayeditor.businessobjects.drawingprimitives.properties.IListAnnotationProperty)
	 */
	public IListAnnotationProperty copyListProperty(IListAnnotationProperty other) {
		return new HibListProperty(canvas, this.canvas.getAttributeSerialCounter().nextIndex(), (HibListProperty)other);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyBuilder#createListProperty(org.pathwayeditor.businessobjects.drawingprimitives.properties.IListPropertyDefinition)
	 */
	public IListAnnotationProperty createListProperty(IListPropertyDefinition propDefn) {
		IListAnnotationProperty aListProperty =  new HibListProperty(canvas, this.canvas.getAttributeSerialCounter().nextIndex(), propDefn); 
		canvas.getProperties().add((HibProperty) aListProperty) ;
		return aListProperty ;
	}
	
}
