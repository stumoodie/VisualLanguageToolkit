/*
Copyright 2009-2011, Court of the University of Edinburgh
*/

package org.pathwayeditor.testfixture;

/**
 * @author Stuart Moodie
 *
 */
public interface IObjectBuilder {
	
	String getElementId();
	
	void create();
	
	void buildDependencies();

}
