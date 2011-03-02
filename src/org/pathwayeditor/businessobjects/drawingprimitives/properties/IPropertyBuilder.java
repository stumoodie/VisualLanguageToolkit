/*
Copyright 2009-2011, Court of the University of Edinburgh
*/
/**
 * 
 */
package org.pathwayeditor.businessobjects.drawingprimitives.properties;


/**
 * IPropertyBuilder is an interface that defines a Visitor (see the Visitor Design Pattern) that is designed specifically to support
 * the creation of new subtypes of <code>IAnnotationProperty</code> and to copy existing instances. It should be used in conjunction with methods in
 * {@link IPropertyDefinition}.
 * 
 * @author Stuart Moodie
 *
 */
public interface IPropertyBuilder {

	/**
	 * The owner of objects that are to be created.
	 * @return the owner of the properties being built, which cannot be null.
	 */
	IAnnotatedObject getOwner();
	
	/**
	 * Creates a new plain text annotation property using the specified property definition. 
	 * @param propDefn the property definition to use for the new annotation property.
	 * @return the newly created annotation property.
	 */
	IPlainTextAnnotationProperty createPlainTextProperty(IPlainTextPropertyDefinition propDefn);

	/**
	 * Creates a new number annotation property using the specified property definition. 
	 * @param propDefn the property definition to use for the new annotation property.
	 * @return the newly created annotation property.
	 */
	INumberAnnotationProperty createNumberProperty(INumberPropertyDefinition propDefn);

	/**
	 * Creates a new integer annotation property using the specified property definition. 
	 * @param propDefn the property definition to use for the new annotation property.
	 * @return the newly created annotation property.
	 */
	IIntegerAnnotationProperty createIntegerProperty(IIntegerPropertyDefinition propDefn);

	/**
	 * Creates a new boolean annotation property using the specified property definition. 
	 * @param propDefn the property definition to use for the new annotation property.
	 * @return the newly created annotation property.
	 */
	IBooleanAnnotationProperty createBooleanProperty(IBooleanPropertyDefinition propDefn);

	/**
	 * Creates a new list annotation property using the specified property definition. 
	 * @param propDefn the property definition to use for the new annotation property.
	 * @return the newly created annotation property.
	 */
	IListAnnotationProperty createListProperty(IListPropertyDefinition propDefn);

	/**
	 * Creates a new plain text annotation property that is a copy of another property. 
	 * @param other the other property to use as the basis for the copy, which cannot be null.
	 * @return the newly created copy.
	 */
	IPlainTextAnnotationProperty copyPlainTextProperty(IPlainTextAnnotationProperty other);

	/**
	 * Creates a new number annotation property that is a copy of another property. 
	 * @param other the other property to use as the basis for the copy, which cannot be null.
	 * @return the newly created copy.
	 */
	INumberAnnotationProperty copyNumberProperty(INumberAnnotationProperty other);

	/**
	 * Creates a new integer annotation property that is a copy of another property. 
	 * @param other the other property to use as the basis for the copy, which cannot be null.
	 * @return the newly created copy.
	 */
	IIntegerAnnotationProperty copyIntegerProperty(IIntegerAnnotationProperty other);

	/**
	 * Creates a new boolean annotation property that is a copy of another property. 
	 * @param other the other property to use as the basis for the copy, which cannot be null.
	 * @return the newly created copy.
	 */
	IBooleanAnnotationProperty copyBooleanProperty(IBooleanAnnotationProperty other);

	/**
	 * Creates a new list annotation property that is a copy of another property. 
	 * @param other the other property to use as the basis for the copy, which cannot be null.
	 * @return the newly created copy.
	 */
	IListAnnotationProperty copyListProperty(IListAnnotationProperty other);
}
