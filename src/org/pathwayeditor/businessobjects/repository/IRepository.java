package org.pathwayeditor.businessobjects.repository;


/**
 * The repository that holds all the folders and maps (including the map diagrams) used in EPE. It is essentially
 * a storage location and would usually correspond to a physical storage location. In is envisaged that in the future
 * EPE may have multiple repositories corresponding to other physical locations (for example on central servers) of pathway
 * maps. At present one would expect only one repository to be present per schema, but there may be reasons to store multiple repositories
 *  in a schema (at least temporarily) that we haven't thought of yet.
 *  
 *  The repository holds repository items (folders and maps) that are identified uniquely within the repository by an inode. This is
 *  analogous to the UNIX file system It also has the important benefit that the natural key (and therefore hashCode and equals) of
 *  maps and folders does not vary if they are moved into different sub-folders or renamed etc.
 *        
 * @author smoodie
 *
 */
public interface IRepository {

	/**
	 * The name of the repository. Must be unique within an EPE application.
	 * @return The name cannot be null, NOR can it be an empty string.
	 */
	String getName();
	
	/**
	 * A description of the repository.
	 * @return The description cannot be null, but can be an empty string.
	 */
	String getDescription();
	
	/**
	 * Get the root folder of the repository.
	 * @return The root folder. Cannot be null.
	 */
	IRootFolder getRootFolder(); 

	/**
	 * The build number of the underlying schema used to store the repository. Required to enable compatibolity checks between the
	 * business objects and the underlying schema.
	 * @return the schema build number.
	 */
	int getSchemaBuildNum();
	
	/**
	 * Uses the standard equals contract. In addition the business key of the object is <code>name</code> so 2 repositories of the
	 * same name are equal. Hashcode should also be implemented to enforce this key.  
	 * @param other The other object to compare.
	 * @return true if the objects are identical of have the same name, false otherwise.
	 */
	boolean equals(Object other);
}
