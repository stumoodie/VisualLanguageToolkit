/**
 * 
 */
package org.pathwayeditor.bussinessobjects.stubs.notationsubsystem;

import org.pathwayeditor.businessobjects.drawingprimitives.attributes.Version;
import org.pathwayeditor.businessobjects.notationsubsystem.INotation;

/**
 * @author smoodie
 *
 */
public class StubNotation implements INotation {
	public static final String EXPECTED_NAME = "StubNotation";
	public static final String EXPECTED_DESCRIPTION = "Stub notation description";
	public static final String EXPECTED_DISPLAY_NAME = "Stub Notation";
	public static final String EXPECTED_GLOBAL_ID = "17326252";
	public static final int EXPECTED_MAJOR_VERSION = 99;
	public static final int EXPECTED_MINOR_VERSION = 19;
	public static final int EXPECTED_PATCH_VERSION = 9;
	public static final String EXPECTED_VERSION_STRING = "99.19.9";
	
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.contextadapter.INotation#getDescription()
	 */
	public String getDescription() {
		return EXPECTED_DESCRIPTION;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.contextadapter.INotation#getDisplayName()
	 */
	public String getDisplayName() {
		return EXPECTED_DISPLAY_NAME;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.contextadapter.INotation#getGlobalId()
	 */
	public String getGlobalId() {
		return EXPECTED_GLOBAL_ID;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.contextadapter.INotation#getMajorVersion()
	 */
	public int getMajorVersion() {
		return EXPECTED_MAJOR_VERSION;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.contextadapter.INotation#getMinorVersion()
	 */
	public int getMinorVersion() {
		return EXPECTED_MINOR_VERSION;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.contextadapter.INotation#getName()
	 */
	public String getName() {
		return EXPECTED_NAME;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.contextadapter.INotation#getPatchVersion()
	 */
	public int getPatchVersion() {
		return EXPECTED_PATCH_VERSION;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.contextadapter.INotation#getVersionString()
	 */
	public String getVersionString() {
		return EXPECTED_VERSION_STRING;
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(INotation o) {
		throw new UnsupportedOperationException("not implemented!");
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.notationsubsystem.INotation#getVersion()
	 */
	public Version getVersion() {
		// TODO Auto-generated method stub
		return new Version (EXPECTED_MAJOR_VERSION , EXPECTED_MINOR_VERSION , EXPECTED_PATCH_VERSION ) ;
	}

}
