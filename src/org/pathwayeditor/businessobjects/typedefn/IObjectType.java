package org.pathwayeditor.businessobjects.typedefn;

import org.pathwayeditor.businessobjects.contectadapter.IContext;

/**
 * @author smoodie
 *
 */
public interface IObjectType {

	IContext getContext();
	
	String getTypeName();
}
