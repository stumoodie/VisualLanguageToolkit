/*
Copyright 2009-2011, Court of the University of Edinburgh
*/
/**
 * 
 */
package org.pathwayeditor.bussinessobjects.stubs.notationsubsystem;

import org.pathwayeditor.businessobjects.drawingprimitives.attributes.Version;
import org.pathwayeditor.businessobjects.notationsubsystem.INotation;

/**
 * @author Stuart Moodie
 *
 */
public class StubNotation implements INotation {
	public static final String EXPECTED_DESCRIPTION = "Stub notation description";
	public static final String EXPECTED_DISPLAY_NAME = "Stub Notation";
	public static final String EXPECTED_QUALIFIED_NAME = "org.pathwayeditor.stub.notation";
	public static final int EXPECTED_MAJOR_VERSION = 99;
	public static final int EXPECTED_MINOR_VERSION = 19;
	public static final int EXPECTED_PATCH_VERSION = 9;
	public static final Version EXPECTED_VERSION = new Version (EXPECTED_MAJOR_VERSION , EXPECTED_MINOR_VERSION , EXPECTED_PATCH_VERSION); 
	private String globalId = EXPECTED_QUALIFIED_NAME;
	private final String name = EXPECTED_DISPLAY_NAME;
	private final Version version =  EXPECTED_VERSION;
	private boolean newName;
	private String notationName;
	
	public StubNotation() {
		
	}
	/**
	 * @param notationName
	 */
	public StubNotation(String notationName) {
		newName=true;
		this.notationName=notationName;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.contextadapter.INotation#getDescription()
	 */
	@Override
	public String getDescription() {
		return EXPECTED_DESCRIPTION;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.contextadapter.INotation#getGlobalId()
	 */
	@Override
	public String getQualifiedName() {
		return EXPECTED_QUALIFIED_NAME;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.contextadapter.INotation#getName()
	 */
	@Override
	public String getDisplayName() {
		if(newName)
			return this.notationName;
		return this.name;
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(INotation o) {
		int retVal = this.getQualifiedName().compareTo(o.getQualifiedName());
		return retVal == 0 ? this.getVersion().compareTo(o.getVersion()) : retVal;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.notationsubsystem.INotation#getVersion()
	 */
	@Override
	public Version getVersion() {
		return this.version;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.globalId == null) ? 0 : this.globalId.hashCode());
		result = prime * result + ((this.version == null) ? 0 : this.version.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof INotation))
			return false;
		INotation other = (INotation) obj;
		if (this.getQualifiedName() == null) {
			if (other.getQualifiedName() != null)
				return false;
		} else if (!this.getQualifiedName().equals(other.getQualifiedName()))
			return false;
		if (this.getVersion() == null) {
			if (other.getVersion() != null)
				return false;
		} else if (!this.getVersion().equals(other.getVersion()))
			return false;
		return true;
	}

}
