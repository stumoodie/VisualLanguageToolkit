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
import java.util.Iterator;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.Version;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibNotation;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibObjectType;
import org.pathwayeditor.businessobjects.hibernate.pojos.ObjectTypeClassification;
import org.pathwayeditor.businessobjects.notationsubsystem.INotation;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationSubsystem;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationSyntaxService;
import org.pathwayeditor.businessobjects.typedefn.ILinkObjectType;
import org.pathwayeditor.businessobjects.typedefn.IObjectType;
import org.pathwayeditor.businessobjects.typedefn.IShapeObjectType;

/**
 * @author smoodie
 *
 */
public class HibNotationFactory implements IHibNotationFactory {
	private final INotationSubsystem notationSubsystem;
	private final SessionFactory factory;
	private HibNotation notation = null;
	private final Map<IObjectType, HibObjectType> objectTypeMapping;
	private boolean initialised = false;
	private boolean initialisationfailed = false;
	
	public HibNotationFactory(SessionFactory factory, INotationSubsystem notationSubsystem){
		if(factory == null || notationSubsystem == null) throw new IllegalArgumentException("Arguments cannot be null");
		if(notationSubsystem.isFallback()) throw new IllegalArgumentException("Cannot use a fallback notation subsystem in this factory");
		
		this.factory = factory;
		this.notationSubsystem = notationSubsystem;
		this.objectTypeMapping = new HashMap<IObjectType, HibObjectType>();
	}

	public void initialise() {
		this.initialisationfailed = false;
		this.initialised = false;
		if(!doesNotationExist()){
			storeNotation();
		}
		loadNotation();
		this.initialised = true;
	}
	
	
	/**
	 * 
	 */
	private void storeNotation() {
		Session sess = factory.getCurrentSession();
		INotation subSystemNotation = this.notationSubsystem.getNotation();
		HibNotation notation = new HibNotation(subSystemNotation.getQualifiedName(), subSystemNotation.getDisplayName(),
				subSystemNotation.getDescription(),	subSystemNotation.getVersion());
		Iterator<IObjectType> iter = this.notationSubsystem.getSyntaxService().objectTypeIterator();
		while(iter.hasNext()){
			IObjectType objectType = iter.next();
			ObjectTypeClassification otClassn = objectType instanceof IShapeObjectType ? ObjectTypeClassification.SHAPE
					: objectType instanceof ILinkObjectType ? ObjectTypeClassification.LINK : ObjectTypeClassification.ROOTOBJECT; 
			HibObjectType hibObjectType = new HibObjectType(objectType.getUniqueId(), objectType.getName(), objectType.getDescription(), otClassn); 
			notation.addObjectType(hibObjectType);
		}
		sess.save(notation);
	}

	private Query createNotationQuery(String queryName, INotation notation) {
		Session sess = factory.getCurrentSession();
		final Version version = notationSubsystem.getNotation().getVersion();
		Query qry = sess.getNamedQuery(queryName).setString("qualifiedName", notationSubsystem.getNotation().getQualifiedName())
			.setInteger("majorVersion", version.getMajorVersion())
			.setInteger("minorVersion", version.getMinorVersion())
			.setInteger("patchVersion", version.getPatchVersion());
		return qry;
	}
	
	private void loadNotation() {
		final Query qry = createNotationQuery("loadNotation", this.notationSubsystem.getNotation());
		this.notation = (HibNotation)qry.uniqueResult();
		if(validateLoadedNotation(this.notationSubsystem.getNotation(), this.notation)) {
			INotationSyntaxService syntaxService = this.notationSubsystem.getSyntaxService(); 
			for(HibObjectType hibObjectType : this.notation.getObjectTypes()){
				if(syntaxService.containsObjectType(hibObjectType.getUniqueId())) {
					IObjectType objectType = this.notationSubsystem.getSyntaxService().getObjectType(hibObjectType.getUniqueId());
					this.objectTypeMapping.put(objectType, hibObjectType);
				}
				else {
					this.initialisationfailed = true;
				}
			}
		}
		else {
			this.initialisationfailed = true;
		}
	}
	
	/**
	 * @param notation2
	 * @param notation3
	 */
	private boolean validateLoadedNotation(INotation subsystemNotation, HibNotation hibNotation) {
		return subsystemNotation.getQualifiedName().equals(hibNotation.getQualifiedName())
			&& subsystemNotation.getVersion().equals(hibNotation.getVersion());
		
	}

	private final boolean doesNotationExist(){
		INotation notation = notationSubsystem.getNotation();
		final Query qry = createNotationQuery("notationExists", notation);
		long numNotations = (Long)qry.uniqueResult();
		return numNotations > 0;
	}
	
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.hibernate.helpers.IHibNotationFactory#getNotation(org.pathwayeditor.businessobjects.notationsubsystem.INotation)
	 */
	public HibNotation getNotation() {
		return this.notation;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.hibernate.helpers.IHibNotationFactory#getObjectType(org.pathwayeditor.businessobjects.typedefn.IObjectType)
	 */
	public HibObjectType getObjectType(IObjectType objectType) {
		HibObjectType retVal = null;
		if(objectType != null) {
			retVal = this.objectTypeMapping.get(objectType);
		}
		if(retVal == null) {
			throw new IllegalArgumentException("No object type mapping exists for object type: " + objectType);
		}
		return retVal;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.hibernate.helpers.IHibNotationFactory#getNotationSubsystem()
	 */
	public INotationSubsystem getNotationSubsystem() {
		return this.notationSubsystem;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.hibernate.helpers.IHibNotationFactory#isFallback()
	 */
	public boolean isFallback() {
		return false;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.hibernate.helpers.IHibNotationFactory#containsObjectType(org.pathwayeditor.businessobjects.typedefn.IObjectType)
	 */
	public boolean containsObjectType(IObjectType objectType) {
		boolean retVal = false;
		if(objectType != null) {
			retVal = this.objectTypeMapping.containsKey(objectType);
		}
		return retVal;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.hibernate.helpers.IHibNotationFactory#hasInitialisationFailed()
	 */
	public boolean hasInitialisationFailed() {
		return this.initialisationfailed;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.hibernate.helpers.IHibNotationFactory#isInitialised()
	 */
	public boolean isInitialised() {
		return this.initialised;
	}

}
