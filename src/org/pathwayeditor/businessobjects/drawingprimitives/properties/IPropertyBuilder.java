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
package org.pathwayeditor.businessobjects.drawingprimitives.properties;


/**
 * @author smoodie
 *
 */
public interface IPropertyBuilder {

	/**
	 * The owner of objects that are to be created.
	 * @return the owner of the properties buing built, which cannot be null.
	 */
	IAnnotatedObject getOwner();
	
	IPlainTextAnnotationProperty createPlainTextProperty(IPlainTextPropertyDefinition propDefn);

	IHtmlAnnotationProperty createHtmlProperty(IHtmlPropertyDefinition propDefn);

	INumberAnnotationProperty createNumberProperty(INumberPropertyDefinition propDefn);

	IIntegerAnnotationProperty createIntegerProperty(IIntegerPropertyDefinition propDefn);

	IBooleanAnnotationProperty createBooleanProperty(IBooleanPropertyDefinition propDefn);

	IListAnnotationProperty createListProperty(IListPropertyDefinition propDefn);

	IPlainTextAnnotationProperty copyPlainTextProperty(IPlainTextAnnotationProperty other);

	IHtmlAnnotationProperty copyHtmlProperty(IHtmlAnnotationProperty other);

	INumberAnnotationProperty copyNumberProperty(INumberAnnotationProperty other);

	IIntegerAnnotationProperty copyIntegerProperty(IIntegerAnnotationProperty other);

	IBooleanAnnotationProperty copyBooleanProperty(IBooleanAnnotationProperty other);

	IListAnnotationProperty copyListProperty(IListAnnotationProperty other);
}
