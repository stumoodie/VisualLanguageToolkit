/*
Copyright 2009-2011, Court of the University of Edinburgh
*/

package org.pathwayeditor.businessobjects.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.pathwayeditor.businessobjects.drawingprimitives.ICanvasElementAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.IZOrderState;

/**
 * ZOrderState
 *
 * @author Stuart Moodie
 *
 */
public class ZOrderState implements IZOrderState {
	private final List<ICanvasElementAttribute> zorderedAtts;
	
	public ZOrderState(LinkedList<ICanvasElementAttribute> zordering) {
		this.zorderedAtts = new ArrayList<ICanvasElementAttribute>(zordering);
	}

	/**
	 * Gets the ZOrdered attributes, which is immutable.
	 * @return the immutabe list
	 */
	List<ICanvasElementAttribute> getZorderedAttribs() {
		return Collections.unmodifiableList(this.zorderedAtts);
	}

}
