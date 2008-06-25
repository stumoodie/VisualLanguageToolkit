package org.pathwayeditor.businessobjects.repository;

import org.pathwayeditor.businessobjects.notationservice.IContext;

public interface IMap {
	IFolder getOwner();

	IContext getContext();

}
