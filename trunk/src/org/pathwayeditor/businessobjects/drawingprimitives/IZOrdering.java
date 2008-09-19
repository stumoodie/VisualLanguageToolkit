package org.pathwayeditor.businessobjects.drawingprimitives;

import java.util.Iterator;


public interface IZOrdering {

	Iterator<IZOrderedObject> getZOrderedIterator();
	void shiftOneForward(IZOrderedObject shape);
	void shiftOneBackwards(IZOrderedObject shape);
	void shiftToFront(IZOrderedObject shape);
	void shiftToBack(IZOrderedObject shape);
	
}
