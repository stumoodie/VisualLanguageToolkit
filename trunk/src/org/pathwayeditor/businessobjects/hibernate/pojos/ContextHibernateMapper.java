/**
 * 
 */
package org.pathwayeditor.businessobjects.hibernate.pojos;

import org.pathwayeditor.businessobjects.contextadapter.IContext;
import org.pathwayeditor.businessobjects.typedefn.ILinkObjectType;
import org.pathwayeditor.businessobjects.typedefn.IObjectType;
import org.pathwayeditor.businessobjects.typedefn.IRootObjectType;
import org.pathwayeditor.businessobjects.typedefn.IShapeObjectType;

/**
 * @author smoodie
 *
 */
public class ContextHibernateMapper {
	private final IContext context;
	
	public ContextHibernateMapper(IContext context){
		this.context = context;
	}
	
	
	public IContext getContext(HibContext context){
		//TODO: implement this.
		return null;
	}
	
	public HibContext convertToHibContext(IContext context){
		HibContext retVal = new HibContext();
		retVal.setName(context.getName());
		retVal.setDescription(context.getDescription());
		retVal.setGlobalId(context.getGlobalId());
		retVal.setMajorVersion(context.getMajorVersion());
		retVal.setMinorVersion(context.getMinorVersion());
		retVal.setPatchVersion(context.getPatchVersion());
		return retVal;
	}
	
	public HibObjectType convertToHibObjectType(IObjectType objectType){
		//TODO:
		return null;
	}
	
	public IShapeObjectType getShapeObjectType(HibObjectType hibObjectType){
		//TODO:
		return null;
	}
	
	public IRootObjectType getRootObjectType(HibObjectType hibObjectType){
		//TODO:
		return null;
	}
	
	public ILinkObjectType getLinkObjectType(HibObjectType hibObjectType){
		//TODO:
		return null;
	}
	
}
