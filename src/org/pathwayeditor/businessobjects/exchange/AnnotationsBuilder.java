/**
 * 
 */
package org.pathwayeditor.businessobjects.exchange;

import java.util.Iterator;

import org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationProperty;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationPropertyVisitor;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IBooleanAnnotationProperty;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IHtmlAnnotationProperty;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IIntegerAnnotationProperty;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IListAnnotationProperty;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.INumberAnnotationProperty;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPlainTextAnnotationProperty;
import org.pathwayeditor.businessobjects.exchange.castor.Annotations;
import org.pathwayeditor.businessobjects.exchange.castor.BooleanAnnotationProperty;
import org.pathwayeditor.businessobjects.exchange.castor.IntegerAnnotationProperty;
import org.pathwayeditor.businessobjects.exchange.castor.NumberAnnotationProperty;
import org.pathwayeditor.businessobjects.exchange.castor.TextAnnotationProperty;

/**
 * @author smoodie
 *
 */
public class AnnotationsBuilder implements IAnnotationPropertyVisitor {
	private final Iterator<IAnnotationProperty> iter;
	private final Annotations annotations;
	
	public AnnotationsBuilder(Iterator<IAnnotationProperty> iter){
		this.iter = iter;
		this.annotations = new Annotations();
	}
	
	public void build(){
		while(iter.hasNext()){
			IAnnotationProperty prop = iter.next();
			prop.visitProperty(this);
		}
	}
	
	public Annotations getAnnotations(){
		return this.annotations;
	}
	
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationPropertyVisitor#visitBooleanProperty(org.pathwayeditor.businessobjects.drawingprimitives.properties.IBooleanAnnotationProperty)
	 */
	public void visitBooleanProperty(IBooleanAnnotationProperty property) {
		BooleanAnnotationProperty prop = new BooleanAnnotationProperty();
		prop.setName(property.getDefinition().getName());
		prop.setDisplayName(property.getDefinition().getDisplayName());
		prop.setValue(property.getValue());
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationPropertyVisitor#visitHtmlProperty(org.pathwayeditor.businessobjects.drawingprimitives.properties.IHtmlAnnotationProperty)
	 */
	public void visitHtmlProperty(IHtmlAnnotationProperty property) {
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationPropertyVisitor#visitIntegerProperty(org.pathwayeditor.businessobjects.drawingprimitives.properties.IIntegerAnnotationProperty)
	 */
	public void visitIntegerProperty(IIntegerAnnotationProperty property) {
		IntegerAnnotationProperty prop = new IntegerAnnotationProperty();
		prop.setName(property.getDefinition().getName());
		prop.setDisplayName(property.getDefinition().getDisplayName());
		prop.setValue(property.getValue());
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationPropertyVisitor#visitListProperty(org.pathwayeditor.businessobjects.drawingprimitives.properties.IListAnnotationProperty)
	 */
	public void visitListProperty(IListAnnotationProperty property) {
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationPropertyVisitor#visitNumberProperty(org.pathwayeditor.businessobjects.drawingprimitives.properties.INumberAnnotationProperty)
	 */
	public void visitNumberProperty(INumberAnnotationProperty property) {
		NumberAnnotationProperty prop = new NumberAnnotationProperty();
		prop.setName(property.getDefinition().getName());
		prop.setDisplayName(property.getDefinition().getDisplayName());
		prop.setValue(property.getValue());
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationPropertyVisitor#visitPlainTextProperty(org.pathwayeditor.businessobjects.drawingprimitives.properties.IPlainTextAnnotationProperty)
	 */
	public void visitPlainTextProperty(IPlainTextAnnotationProperty property) {
		TextAnnotationProperty prop = new TextAnnotationProperty();
		prop.setName(property.getDefinition().getName());
		prop.setDisplayName(property.getDefinition().getDisplayName());
		prop.setName(property.getValue());
	}

}
