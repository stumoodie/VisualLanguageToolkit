package org.pathwayeditor.businessobjects.repository;


public interface IRepositoryItem {
	
	IRepository getRepository();
	
	int getNodeId();
	
	String getName();
	
	IRepositoryItem getOwner();
	
}
