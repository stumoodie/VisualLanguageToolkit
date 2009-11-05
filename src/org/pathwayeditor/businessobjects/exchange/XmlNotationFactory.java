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

package org.pathwayeditor.businessobjects.exchange;

import java.util.HashMap;
import java.util.Map;

import org.pathwayeditor.businessobjects.drawingprimitives.attributes.Version;
import org.pathwayeditor.businessobjects.exchange.castor.Notation;
import org.pathwayeditor.businessobjects.exchange.castor.ObjectType;
import org.pathwayeditor.businessobjects.exchange.castor.types.ObjectTypeClassificationType;
import org.pathwayeditor.businessobjects.hibernate.helpers.IHibNotationFactory;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibNotation;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibObjectType;
import org.pathwayeditor.businessobjects.hibernate.pojos.ObjectTypeClassification;

/**
 * @author smoodie
 *
 */
public class XmlNotationFactory implements IHibNotationFactory {

	private final Notation xmlNotation;
	private HibNotation hibNotation;
	private final Map<Integer, HibObjectType> hibObjectTypeMap;
	
	public XmlNotationFactory(Notation hibNotation){
		this.xmlNotation = hibNotation;
		this.hibObjectTypeMap = new HashMap<Integer, HibObjectType>();
	}
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.hibernate.helpers.IHibNotationFactory#containsObjectType(org.pathwayeditor.businessobjects.typedefn.IObjectType)
	 */
	public boolean containsObjectType(int uniqueId) {
		return this.hibObjectTypeMap.containsKey(uniqueId);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.hibernate.helpers.IHibNotationFactory#getNotation()
	 */
	public HibNotation getNotation() {
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.hibernate.helpers.IHibNotationFactory#getObjectType(org.pathwayeditor.businessobjects.typedefn.IObjectType)
	 */
	public HibObjectType getObjectType(int uniqueId) {
		return this.hibObjectTypeMap.get(uniqueId);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.hibernate.helpers.IHibNotationFactory#hasInitialisationFailed()
	 */
	public boolean hasInitialisationFailed() {
		return false;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.hibernate.helpers.IHibNotationFactory#initialise()
	 */
	public void initialise() {
		Version notationVersion = new Version((int)xmlNotation.getMajorVersion(), (int)xmlNotation.getMinorVersion(),
				(int)xmlNotation.getPatchVersion());
		this.hibNotation = new HibNotation(xmlNotation.getQualifiedName(), xmlNotation.getDisplayName(), xmlNotation.getDescription(), notationVersion);
		for(ObjectType xmlObjectType : this.xmlNotation.getObjectType()){
			ObjectTypeClassification classn = ObjectTypeClassification.SHAPE;
			if(xmlObjectType.getClassification().equals(ObjectTypeClassificationType.ROOT)){
				classn = ObjectTypeClassification.ROOTOBJECT;
			}
			else if(xmlObjectType.getClassification().equals(ObjectTypeClassificationType.LINK)){
				classn = ObjectTypeClassification.LINK;
			}
			HibObjectType hibObjectType = new HibObjectType((int)xmlObjectType.getUniqueId(), xmlObjectType.getName(),
					xmlObjectType.getDescription(), classn);
			this.hibNotation.addObjectType(hibObjectType);
			this.hibObjectTypeMap.put((int)xmlObjectType.getUniqueId(), hibObjectType);
		}
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.hibernate.helpers.IHibNotationFactory#isFallback()
	 */
	public boolean isFallback() {
		return false;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.hibernate.helpers.IHibNotationFactory#isInitialised()
	 */
	public boolean isInitialised() {
		return this.hibNotation != null;
	}

}
