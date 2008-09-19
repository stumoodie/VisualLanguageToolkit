package org.pathwayeditor.businessobjects.typedefn;

import org.pathwayeditor.businessobjects.contextadapter.IContext;

/**
 * @author smoodie
 *
 */
public interface IObjectType {

	IContext getContext();
	
	String getTypeName();
}
