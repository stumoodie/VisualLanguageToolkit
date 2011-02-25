/*
Copyright 2009-2011, Court of the University of Edinburgh
*/

package org.pathwayeditor.testfixture;

/**
 * @author Stuart Moodie
 *
 */
public interface IObjectConstructor<T> {

	T create();

	boolean build();

}
