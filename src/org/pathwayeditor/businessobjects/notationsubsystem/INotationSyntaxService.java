package org.pathwayeditor.businessobjects.notationsubsystem;

import java.util.Iterator;

import org.pathwayeditor.businessobjects.typedefn.ILinkObjectType;
import org.pathwayeditor.businessobjects.typedefn.IObjectType;
import org.pathwayeditor.businessobjects.typedefn.IRootObjectType;
import org.pathwayeditor.businessobjects.typedefn.IShapeObjectType;

/**
 * Defines the context adapter syntax service. Implementations of this interfaces should define the visual
 * look of the glyphs in the notation and its syntactic rules. This is done by providing implementations 
 * of IShapeObjectType and ILinkObjectType and an IRootMapObject for the map.
 * <p>
 * The API provides methods for accessing the object types so created, which are then used to define new
 * map objects and the notation's syntactic rules.
 * <p>
 * The object type also controls which properties of its associated map object are editable. It also
 * defines additional annotation properties, which again may be editable and also displayed as a label 
 * associated with the map object (visualisable).
 * 
 * Typically this interface will be implemented behind an eclipse extension point and should have minimal
 * dependencies on the business object API of EPE.
 * 
 * @author smoodie
 *
 */
public interface INotationSyntaxService extends INotationService {

	/**
	 * Returns an iterator of shape object types defined by this syntax service.
	 * @return An iterator of shape object types cannot be null.
	 */
	Iterator<IShapeObjectType> shapeTypeIterator();

	/**
	 * Returns an iterator of link object types defined by this syntax service.
	 * @return An iterator of link object types cannot be null.
	 */
	Iterator<ILinkObjectType> linkTypeIterator();

	/**
	 * Returns an iterator over the object types help by this syntax service. 
	 * @return The iterator, which may iterate over an empty collection.
	 */
	Iterator<IObjectType> objectTypeIterator();

	/**
	 * Returns an object type defining the map attribute defaults and syntax rules for shapes that can be
	 * placed on it.
	 * @return An instance of the root map object. This cannot be null and the same object will always be
	 * returned for a given instance of this interface.
	 */
	IRootObjectType getRootObjectType();

	/**
	 * Tests if an object type with the given uniqueId exists in this syntax service.
	 * @param uniqueId the unique id to be tested.
	 * @return true if is contains the object type, false otherwise.
	 */
	boolean containsShapeObjectType(int uniqueId); 
	
	/**
	 * Retrieve the object type based on it's object id.
	 * @param uniqueId the unique identifier of the object type.
	 * @return the object type, which cannot be null.
	 */
	IShapeObjectType getShapeObjectType(int uniqueId);

	/**
	 * Tests if a link object type with the given uniqueId exists in this syntax service.
	 * @param uniqueId the unique id to be tested.
	 * @return true if is contains the link object type, false otherwise.
	 */
	boolean containsLinkObjectType(int uniqueId); 
	
	/**
	 * Retrieve the link object type based on it's object id.
	 * @param uniqueId the unique identifier of the object type.
	 * @return the object type, which cannot be null.
	 */
	ILinkObjectType getLinkObjectType(int uniqueId);

	/**
	 * Tests if a object type with the given uniqueId exists in this syntax service.
	 * @param uniqueId the unique id to be tested.
	 * @return true if is contains the object type, false otherwise.
	 */
	boolean containsObjectType(int uniqueId); 
	
	/**
	 * Retrieve the lobject type based on it's unique id.
	 * @param uniqueId the unique identifier of the object type.
	 * @return the object type, which cannot be null.
	 */
	IObjectType getObjectType(int uniqueId);

}
