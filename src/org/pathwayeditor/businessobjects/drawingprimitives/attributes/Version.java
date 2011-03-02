/*
Copyright 2009-2011, Court of the University of Edinburgh
*/
/**
 * 
 */
package org.pathwayeditor.businessobjects.drawingprimitives.attributes;

/**
 * Version is an immutable class that describes a version number consisting of three
 * components, the major, minor and patch versions. This class enables the comparison
 * of version numbers, testing for equivalence and a simple string representation.  
 * 
 * @author Stuart Moodie
 *
 */
public class Version implements Comparable<Version> {
	public static final char DEFAULT_VERSION_SEPARATOR = '.';
	private final int major;
	private final int minor;
	private final int patch;
	private final char versionSeparator;
	
	/**
	 * Constructor.
	 * @param major major version number.
	 * @param minor minor version number.
	 * @param patch patch version number.
	 */
	public Version(int major, int minor, int patch){
		this(major, minor, patch, DEFAULT_VERSION_SEPARATOR);
	}

	/**
	 * Constructor.
	 * @param major major version number.
	 * @param minor minor version number.
	 * @param patch patch version number.
	 * @param versionSeparator the character
	 *  used to separate the version numbers in a string representation.
	 */
	public Version(int major, int minor, int patch, char versionSeparator){
		this.major = major;
		this.minor = minor;
		this.patch = patch;
		this.versionSeparator = versionSeparator;
	}

	
	/**
	 * Gets the major version number.
	 * @return the major version number.
	 */
	public int getMajorVersion() {
		return this.major;
	}

	/**
	 * Creates a new instance with the new major version number.
	 * @param newMajorVersion the new major version number.
	 * @return the new instance with the new major version number.
	 */
	public Version newMajorVersion(int newMajorVersion){
		return new Version(newMajorVersion, this.minor, this.patch);
	}
	
	/**
	 * Gets the minor version number.
	 * @return the minor version number.
	 */
	public int getMinorVersion() {
		return this.minor;
	}

	/**
	 * Creates a new instance with the new minor version number.
	 * @param newMinorVersion the new minor version number.
	 * @return the new instance with the new minor version number.
	 */
	public Version newMinorVersion(int newMinorVersion){
		return new Version(this.major, newMinorVersion, this.patch);
	}
	
	/**
	 * Gets the patch version number.
	 * @return the patch version number.
	 */
	public int getPatchVersion() {
		return this.patch;
	}

	/**
	 * Creates a new instance with the new patch version number.
	 * @param newPatchVersion the new patch version number.
	 * @return the new instance with the new patch version number.
	 */
	public Version newPatchVersion(int newPatchVersion){
		return new Version(this.major, this.minor, newPatchVersion);
	}
	
	/**
	 * Represents the version as a string of the form: "1.2.3", where '.' is the
	 * version separator.   
	 */
	@Override
	public String toString(){
		StringBuilder builder = new StringBuilder();
		builder.append(this.major);
		builder.append(versionSeparator);
		builder.append(this.minor);
		builder.append(versionSeparator);
		builder.append(this.patch);
		return builder.toString(); 
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + this.major;
		result = prime * result + this.minor;
		result = prime * result + this.patch;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Version other = (Version) obj;
		if (this.major != other.major)
			return false;
		if (this.minor != other.minor)
			return false;
		if (this.patch != other.patch)
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Version other) {
		int retVal = 0;
		if(this.major < other.major){
			retVal = -1;
		}
		else if(this.major > other.major){
			retVal = 1;
		}
		else if(this.minor < other.minor){
			retVal = -1;
		}
		else if(this.minor > other.minor){
			retVal = 1;
		}
		else if(this.patch < other.patch){
			retVal = -1;
		}
		else if(this.patch > other.patch){
			retVal = 1;
		}
		return retVal;
	}

}
