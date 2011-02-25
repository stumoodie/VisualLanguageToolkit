/*
Copyright 2009-2011, Court of the University of Edinburgh
*/

package org.pathwayeditor.testfixture;

/**
 * @author Stuart Moodie
 *
 */
public interface IRedefinableBuilder<T> extends IObjectBuilder {

	IObjectConstructor<T> getOverridingConstructor();

	/**
	 * @param nodeConstructor
	 */
	void setOverridingConstructor(IObjectConstructor<T> nodeConstructor);

	
	T getCreatedObject();
}
