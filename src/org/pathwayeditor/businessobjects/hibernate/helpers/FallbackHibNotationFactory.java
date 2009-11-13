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
package org.pathwayeditor.businessobjects.hibernate.helpers;

import java.util.HashMap;
import java.util.Map;

import org.pathwayeditor.businessobjects.hibernate.pojos.HibNotation;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibObjectType;

/**
 * @author smoodie
 *
 */
public class FallbackHibNotationFactory implements IHibNotationFactory {
	private final HibNotation hibNotation;
	private final Map<Integer, HibObjectType> objectTypeLookup;
	private boolean initialised = false;
	
	/**
	 * @param hibNotation
	 */
	public FallbackHibNotationFactory(HibNotation hibNotation) {
		this.hibNotation = hibNotation;
		this.objectTypeLookup = new HashMap<Integer, HibObjectType>();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.hibernate.helpers.IHibNotationFactory#getNotation()
	 */
	public HibNotation getNotation() {
		return this.hibNotation;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.hibernate.helpers.IHibNotationFactory#getObjectType(org.pathwayeditor.businessobjects.typedefn.IObjectType)
	 */
	public HibObjectType getObjectType(int uniqueId) {
		HibObjectType retVal = null;
		retVal = this.objectTypeLookup.get(uniqueId);
		if(retVal == null) {
			throw new IllegalArgumentException("No object type mapping exists for object type uid=" + uniqueId);
		}
		return retVal;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.hibernate.helpers.IHibNotationFactory#initialise()
	 */
	public void initialise() {
		this.initialised = false;
		for(HibObjectType objectType : this.hibNotation.getObjectTypes()) {
			this.objectTypeLookup.put(objectType.getUniqueId(), objectType);
		}
		this.initialised = true;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.hibernate.helpers.IHibNotationFactory#isFallback()
	 */
	public boolean isFallback() {
		return true;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.hibernate.helpers.IHibNotationFactory#containsObjectType(org.pathwayeditor.businessobjects.typedefn.IObjectType)
	 */
	public boolean containsObjectType(int uniqueId) {
		boolean retVal = false;
		retVal = this.objectTypeLookup.containsKey(uniqueId);
		return retVal;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.hibernate.helpers.IHibNotationFactory#hasInitialisationFailed()
	 */
	public boolean hasInitialisationFailed() {
		// this can never fail in normal operation (i.e. ignoring bugs)
		return false;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.hibernate.helpers.IHibNotationFactory#isInitialised()
	 */
	public boolean isInitialised() {
		return this.initialised;
	}

}
