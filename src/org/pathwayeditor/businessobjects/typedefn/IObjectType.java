package org.pathwayeditor.businessobjects.typedefn;

import org.pathwayeditor.businessobjects.notationservice.IContext;

/**
 * @author smoodie
 *
 */
public interface IObjectType {

	IContext getContext();
	
	String getTypeName();
}
