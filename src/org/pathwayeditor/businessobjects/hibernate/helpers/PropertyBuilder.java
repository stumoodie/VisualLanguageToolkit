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
package org.pathwayeditor.businessobjects.hibernate.helpers;

import org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotatedObject;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IBooleanAnnotationProperty;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IBooleanPropertyDefinition;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IIntegerAnnotationProperty;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IIntegerPropertyDefinition;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IListAnnotationProperty;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IListPropertyDefinition;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.INumberAnnotationProperty;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.INumberPropertyDefinition;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPlainTextAnnotationProperty;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPlainTextPropertyDefinition;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyBuilder;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibAnnotatedCanvasAttribute;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibBooleanProperty;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibIntegerProperty;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibListProperty;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibNumberProperty;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibTextProperty;

/**
 * @author smoodie
 *
 */
public class PropertyBuilder implements IPropertyBuilder {
	private final HibAnnotatedCanvasAttribute owner;
	
	public PropertyBuilder(HibAnnotatedCanvasAttribute owner){
		this.owner = owner;
	}
	
	
	public IAnnotatedObject getOwner() {
		return this.owner;
	}

//	/* (non-Javadoc)
//	 * @see org.pathwayeditor.businessobjects.hibernate.pojos.IPropertyVisitor#createHtmlProperty(org.pathwayeditor.businessobjects.drawingprimitives.properties.IHtmlPropertyDefinition)
//	 */
//	public IHtmlAnnotationProperty createHtmlProperty(IHtmlPropertyDefinition propDefn) {
//		IHtmlAnnotationProperty anHTMLProperty = new HibRichTextProperty(owner, propDefn);
//		return anHTMLProperty ;
//	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.hibernate.pojos.IPropertyVisitor#createPlainTextProperty(org.pathwayeditor.businessobjects.drawingprimitives.properties.IPlainTextPropertyDefinition)
	 */
	public IPlainTextAnnotationProperty createPlainTextProperty(IPlainTextPropertyDefinition propDefn) {
		IPlainTextAnnotationProperty aTextProperty = new HibTextProperty(owner,  propDefn); 
		return aTextProperty ;
	}

//	/* (non-Javadoc)
//	 * @see org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyBuilder#copyHtmlProperty(org.pathwayeditor.businessobjects.drawingprimitives.properties.IHtmlPropertyDefinition)
//	 */
//	public IHtmlAnnotationProperty copyHtmlProperty(IHtmlAnnotationProperty other) {
//		return new HibRichTextProperty(owner, (HibRichTextProperty)other);
//	}


	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyBuilder#copyNumberProperty(org.pathwayeditor.businessobjects.drawingprimitives.properties.INumberPropertyDefinition)
	 */
	public INumberAnnotationProperty copyNumberProperty(INumberAnnotationProperty other) {
		return new HibNumberProperty(owner, (HibNumberProperty)other);
	}

	public IIntegerAnnotationProperty copyIntegerProperty(IIntegerAnnotationProperty other) {
		return new HibIntegerProperty(owner, (HibIntegerProperty)other);
	}

	public IBooleanAnnotationProperty copyBooleanProperty(IBooleanAnnotationProperty other) {
		return new HibBooleanProperty(owner, (HibBooleanProperty)other);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyBuilder#copyPlainTextProperty(org.pathwayeditor.businessobjects.drawingprimitives.properties.IPlainTextPropertyDefinition)
	 */
	public IPlainTextAnnotationProperty copyPlainTextProperty(IPlainTextAnnotationProperty other) {
		return new HibTextProperty(owner, (HibTextProperty)other);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyBuilder#createNumberProperty(org.pathwayeditor.businessobjects.drawingprimitives.properties.INumberPropertyDefinition)
	 */
	public INumberAnnotationProperty createNumberProperty(INumberPropertyDefinition propDefn) {
		INumberAnnotationProperty aNumberProperty =  new HibNumberProperty(owner,  propDefn); 
		return aNumberProperty ;
	}

	public IIntegerAnnotationProperty createIntegerProperty(IIntegerPropertyDefinition propDefn) {
		IIntegerAnnotationProperty aNumberProperty =  new HibIntegerProperty(owner,  propDefn); 
		return aNumberProperty ;
	}

	public IBooleanAnnotationProperty createBooleanProperty(IBooleanPropertyDefinition propDefn) {
		IBooleanAnnotationProperty aNumberProperty =  new HibBooleanProperty(owner,  propDefn); 
		return aNumberProperty ;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyBuilder#copyListProperty(org.pathwayeditor.businessobjects.drawingprimitives.properties.IListAnnotationProperty)
	 */
	public IListAnnotationProperty copyListProperty(IListAnnotationProperty other) {
		return new HibListProperty(owner, (HibListProperty)other);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyBuilder#createListProperty(org.pathwayeditor.businessobjects.drawingprimitives.properties.IListPropertyDefinition)
	 */
	public IListAnnotationProperty createListProperty(IListPropertyDefinition propDefn) {
		IListAnnotationProperty aListProperty =  new HibListProperty(owner,  propDefn); 
		return aListProperty ;
	}
	
}
