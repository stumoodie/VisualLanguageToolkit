/*
Copyright 2009, Court of the University of Edinburgh

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License. 
*/
/**
 * 
 */
package org.pathwayeditor.bussinessobjects.stubs;

import java.util.HashMap;
import java.util.Map;

import org.pathwayeditor.businessobjects.drawingprimitives.attributes.Version;
import org.pathwayeditor.businessobjects.hibernate.helpers.IHibNotationFactory;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibNotation;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibObjectType;
import org.pathwayeditor.businessobjects.hibernate.pojos.ObjectTypeClassification;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationSubsystem;
import org.pathwayeditor.bussinessobjects.stubs.notationsubsystem.StubNotation;
import org.pathwayeditor.bussinessobjects.stubs.notationsubsystem.StubNotationSubSystem;

/**
 * @author smoodie
 *
 */
public class StubHibNotationFactory implements IHibNotationFactory {
	private static final int ROOT_UID = 0;

	private final INotationSubsystem notationSubsystem =  new StubNotationSubSystem();
	
	private Map<Integer, HibObjectType> otLookup = new HashMap<Integer, HibObjectType>();
	
	public StubHibNotationFactory() {
		HibObjectType rootOt = new HibObjectType(ROOT_UID, "ROOT_OT", "", ObjectTypeClassification.ROOTOBJECT);
		this.otLookup.put(rootOt.getUniqueId(), rootOt);
	}
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.hibernate.helpers.IHibNotationFactory#getNotation(org.pathwayeditor.businessobjects.notationsubsystem.INotation)
	 */
	public HibNotation getNotation() {
		return new HibNotation(StubNotation.EXPECTED_QUALIFIED_NAME, StubNotation.EXPECTED_DISPLAY_NAME, StubNotation.EXPECTED_DESCRIPTION,
				new Version(StubNotation.EXPECTED_MAJOR_VERSION, StubNotation.EXPECTED_MINOR_VERSION, StubNotation.EXPECTED_PATCH_VERSION));
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.hibernate.helpers.IHibNotationFactory#getObjectType(org.pathwayeditor.businessobjects.typedefn.IObjectType)
	 */
	public HibObjectType getObjectType(int uniqueId) {
		return this.otLookup.get(uniqueId);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.hibernate.helpers.IHibNotationFactory#getNotationSubsystem()
	 */
	public INotationSubsystem getNotationSubsystem() {
		return this.notationSubsystem;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.hibernate.helpers.IHibNotationFactory#containsObjectType(org.pathwayeditor.businessobjects.typedefn.IObjectType)
	 */
	public boolean containsObjectType(int uniqueId) {
		return this.otLookup.containsKey(uniqueId);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.hibernate.helpers.IHibNotationFactory#initialise()
	 */
	public void initialise() {
		
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.hibernate.helpers.IHibNotationFactory#isFallback()
	 */
	public boolean isFallback() {
		return false;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.hibernate.helpers.IHibNotationFactory#hasInitialisationFailed()
	 */
	public boolean hasInitialisationFailed() {
		return false;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.hibernate.helpers.IHibNotationFactory#isInitialised()
	 */
	public boolean isInitialised() {
		return true;
	}

}
