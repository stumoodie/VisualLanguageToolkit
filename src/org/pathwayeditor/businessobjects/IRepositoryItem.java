package org.pathwayeditor.businessobjects;

import java.util.Iterator;

public interface IRepositoryItem {
	
	IRepositoryItem getOwner();
	int getNodeId();
	
	String getName();
	
	Iterator<IRepositoryItem> depthFirstIterator();
	Iterator<IRepositoryItem> breadthFirstIterator();
}
