package org.pathwayeditor.businessobjects;

import java.util.Iterator;

public interface IZOrdering {

	Iterator<IShape> getZOrderedIterator();
	void shiftOneForward(IShape shape);
	void shiftOneBackwards(IShape shape);
	void shiftToFront(IShape shape);
	void shiftToBack(IShape shape);
	
}
