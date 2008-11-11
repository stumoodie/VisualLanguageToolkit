/**
 * 
 */
package org.pathwayeditor.bussinessobjects.stubs;

import org.pathwayeditor.businessobjects.drawingprimitives.attributes.Version;
import org.pathwayeditor.businessobjects.hibernate.helpers.IHibNotationFactory;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibNotation;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibObjectType;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationSubsystem;
import org.pathwayeditor.businessobjects.typedefn.IObjectType;
import org.pathwayeditor.bussinessobjects.stubs.notationsubsystem.StubNotation;
import org.pathwayeditor.bussinessobjects.stubs.notationsubsystem.StubNotationSubSystem;

/**
 * @author smoodie
 *
 */
public class StubHibNotationFactory implements IHibNotationFactory {
	private final INotationSubsystem notationSubsystem =  new StubNotationSubSystem();
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.hibernate.helpers.IHibNotationFactory#getNotation(org.pathwayeditor.businessobjects.notationsubsystem.INotation)
	 */
	public HibNotation getNotation() {
		return new HibNotation(StubNotation.EXPECTED_GLOBAL_ID, StubNotation.EXPECTED_NAME, StubNotation.EXPECTED_DESCRIPTION,
				new Version(StubNotation.EXPECTED_MAJOR_VERSION, StubNotation.EXPECTED_MINOR_VERSION, StubNotation.EXPECTED_PATCH_VERSION));
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.hibernate.helpers.IHibNotationFactory#getObjectType(org.pathwayeditor.businessobjects.typedefn.IObjectType)
	 */
	public HibObjectType getObjectType(IObjectType objectType) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented");
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.hibernate.helpers.IHibNotationFactory#getNotationSubsystem()
	 */
	public INotationSubsystem getNotationSubsystem() {
		return this.notationSubsystem;
	}

}
