/**
 * 
 */
package org.pathwayeditor.businessobjects.hibernate.helpers;

import java.util.HashMap;
import java.util.Map;

import org.pathwayeditor.businessobjects.hibernate.pojos.HibNotation;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibObjectType;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationSubsystem;
import org.pathwayeditor.businessobjects.typedefn.IObjectType;

/**
 * @author smoodie
 *
 */
public class FallbackHibNotationFactory implements IHibNotationFactory {
	private final HibNotation hibNotation;
	private final Map<Integer, HibObjectType> objectTypeLookup;
	private final INotationSubsystem notationSubsystem;
	
	/**
	 * @param hibNotation
	 */
	public FallbackHibNotationFactory(INotationSubsystem subsystem, HibNotation hibNotation) {
		if(!subsystem.isFallback()) throw new IllegalArgumentException("Expect a fallback notation subsystem");
		
		this.hibNotation = hibNotation;
		this.notationSubsystem = subsystem;
		this.objectTypeLookup = new HashMap<Integer, HibObjectType>();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.hibernate.helpers.IHibNotationFactory#getNotation()
	 */
	public HibNotation getNotation() {
		return this.hibNotation;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.hibernate.helpers.IHibNotationFactory#getNotationSubsystem()
	 */
	public INotationSubsystem getNotationSubsystem() {
		return this.notationSubsystem;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.hibernate.helpers.IHibNotationFactory#getObjectType(org.pathwayeditor.businessobjects.typedefn.IObjectType)
	 */
	public HibObjectType getObjectType(IObjectType objectType) {
		HibObjectType retVal = null;
		if(objectType != null) {
			retVal = this.objectTypeLookup.get(objectType.getUniqueId());
		}
		if(retVal == null) {
			throw new IllegalArgumentException("No object type mapping exists for object type: " + objectType);
		}
		return retVal;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.hibernate.helpers.IHibNotationFactory#initialise()
	 */
	public void initialise() throws InconsistentNotationDefinitionException {
		for(HibObjectType objectType : this.hibNotation.getObjectTypes()) {
			this.objectTypeLookup.put(objectType.getUniqueId(), objectType);
		}
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
	public boolean containsObjectType(IObjectType objectType) {
		boolean retVal = false;
		if(objectType != null) {
			retVal = this.objectTypeLookup.containsKey(objectType.getUniqueId());
		}
		return retVal;
	}

}
