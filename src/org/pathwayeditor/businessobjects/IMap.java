package org.pathwayeditor.businessobjects;

import org.pathwayeditor.businessobjects.typedefn.IContextAdapter;

public interface IMap {
	IFolder getOwner();

	IContextAdapter getContext();

}
