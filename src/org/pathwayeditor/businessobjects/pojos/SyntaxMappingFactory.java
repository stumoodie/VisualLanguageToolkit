/**
 * 
 */
package org.pathwayeditor.businessobjects.pojos;

import org.pathwayeditor.businessobjects.contectadapter.IContext;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibContext;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibObjectType;
import org.pathwayeditor.businessobjects.typedefn.ILinkObjectType;
import org.pathwayeditor.businessobjects.typedefn.IObjectType;
import org.pathwayeditor.businessobjects.typedefn.IRootObjectType;
import org.pathwayeditor.businessobjects.typedefn.IShapeObjectType;


/**
 * @author smoodie
 *
 */
public class SyntaxMappingFactory implements ISyntaxMappingFactory {
	
	SyntaxMappingFactory(){
		
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.pojos.ISyntaxMappingFactory#createHibContext(org.pathwayeditor.businessobjects.notationservice.IContext)
	 */
	public HibContext createHibContext(IContext context) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.pojos.ISyntaxMappingFactory#createHibObjectType(org.pathwayeditor.businessobjects.typedefn.IObjectType)
	 */
	public HibObjectType createHibObjectType(IObjectType objectType) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.pojos.ISyntaxMappingFactory#getContext(org.pathwayeditor.businessobjects.hibernate.pojos.HibContext)
	 */
	public IContext getContext(HibContext hibContext) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.pojos.ISyntaxMappingFactory#getLinkObjectType(org.pathwayeditor.businessobjects.hibernate.pojos.HibObjectType)
	 */
	public ILinkObjectType getLinkObjectType(HibObjectType hibObjectType) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.pojos.ISyntaxMappingFactory#getRootObjectType(org.pathwayeditor.businessobjects.hibernate.pojos.HibObjectType)
	 */
	public IRootObjectType getRootObjectType(HibObjectType hibObjectType) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.pojos.ISyntaxMappingFactory#getShapeObjectType(org.pathwayeditor.businessobjects.hibernate.pojos.HibObjectType)
	 */
	public IShapeObjectType getShapeObjectType(HibObjectType hibObjectType) {
		// TODO Auto-generated method stub
		return null;
	}

}
