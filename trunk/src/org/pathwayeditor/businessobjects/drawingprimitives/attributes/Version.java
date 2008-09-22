/**
 * 
 */
package org.pathwayeditor.businessobjects.drawingprimitives.attributes;

/**
 * @author smoodie
 *
 */
public class Version implements Comparable<Version> {
	public static final char DEFAULT_VERSION_SEPARATOR = '.';
	private final int major;
	private final int minor;
	private final int patch;
	private final char versionSeparator;
	
	public Version(int major, int minor, int patch){
		this.major = major;
		this.minor = minor;
		this.patch = patch;
		this.versionSeparator = DEFAULT_VERSION_SEPARATOR;
	}

	public Version(int major, int minor, int patch, char versionSeparator){
		this.major = major;
		this.minor = minor;
		this.patch = patch;
		this.versionSeparator = versionSeparator;
	}

	public int getMajorVersion() {
		return this.major;
	}

	public Version newMajorVersion(int newMajorVersion){
		return new Version(newMajorVersion, this.minor, this.patch);
	}
	
	public int getMinorVersion() {
		return this.minor;
	}

	public Version newMinorVersion(int newMinorVersion){
		return new Version(this.major, newMinorVersion, this.patch);
	}
	
	public int getPatchVersion() {
		return this.patch;
	}

	public Version newPatchVersion(int newPatchVersion){
		return new Version(this.major, this.minor, newPatchVersion);
	}
	
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
