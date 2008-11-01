/**
 * 
 */
package org.pathwayeditor.bussinessobjects.stubs;

import org.pathwayeditor.businessobjects.drawingprimitives.attributes.Version;
import org.pathwayeditor.businessobjects.hibernate.helpers.IHibNotationFactory;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibNotation;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibObjectType;
import org.pathwayeditor.businessobjects.notationsubsystem.INotation;
import org.pathwayeditor.businessobjects.typedefn.IObjectType;
import org.pathwayeditor.bussinessobjects.stubs.notationsubsystem.StubNotation;

/**
 * @author smoodie
 *
 */
public class StubHibNotationFactory implements IHibNotationFactory {

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.hibernate.helpers.IHibNotationFactory#getNotation(org.pathwayeditor.businessobjects.notationsubsystem.INotation)
	 */
	public HibNotation getNotation(INotation notation) {
		return new HibNotation(StubNotation.EXPECTED_GLOBAL_ID, StubNotation.EXPECTED_NAME, StubNotation.EXPECTED_DESCRIPTION,
				new Version(StubNotation.EXPECTED_MAJOR_VERSION, StubNotation.EXPECTED_MINOR_VERSION, StubNotation.EXPECTED_PATCH_VERSION));
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.hibernate.helpers.IHibNotationFactory#getObjectType(org.pathwayeditor.businessobjects.typedefn.IObjectType)
	 */
	public HibObjectType getObjectType(IObjectType objectType) {
		throw new UnsupportedOperationException("Not implemented");
	}

}
