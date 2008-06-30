/**
 * 
 */
package org.pathwayeditor.businessobjects.pojos;

import org.pathwayeditor.businessobjects.hibernate.pojos.HibObjectType;
import org.pathwayeditor.businessobjects.typedefn.ILinkObjectType;

/**
 * @author smoodie
 *
 */
public class ObjectTypeMappingFactory implements ISyntaxMappingFactory {
	private static ObjectTypeMappingFactory anInstance = null;
	
	public static ObjectTypeMappingFactory getInstance() {
		if(anInstance == null){
			anInstance = new ObjectTypeMappingFactory();
		}
		return anInstance;
	}
	
	private ObjectTypeMappingFactory(){
		
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.pojos.IObjectTypeMappingFactory#createHibLinkObjectType(org.pathwayeditor.businessobjects.typedefn.ILinkObjectType)
	 */
	public HibObjectType createHibLinkObjectType(ILinkObjectType objectType) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.pojos.IObjectTypeMappingFactory#getLinkObjectType(org.pathwayeditor.businessobjects.hibernate.pojos.HibObjectType)
	 */
	public ILinkObjectType getLinkObjectType(HibObjectType objectType) {
		// TODO Auto-generated method stub
		return null;
	}

}
