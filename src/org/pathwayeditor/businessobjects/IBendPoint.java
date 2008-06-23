package org.pathwayeditor.businessobjects;

public interface IBendPoint {
	ILink getOwningLink();
	
	Location getLocation();
}
