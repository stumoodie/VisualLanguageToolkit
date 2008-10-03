package org.pathwayeditor.businessobjects.notationsubsystem;

import java.util.Iterator;

import org.pathwayeditor.businessobjects.typedefn.ILinkObjectType;
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
	 * Returns an object type defining the map attribute defaults and syntax rules for shapes that can be
	 * placed on it.
	 * @return An instance of the root map object. This cannot be null and the same object will always be
	 * returned for a given instance of this interface.
	 */
	IRootObjectType getRootMapObjectType();

	/**
	 * @param name
	 * @return
	 */
	IShapeObjectType getShapeObjectType(String name);

}
