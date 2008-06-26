/**
 * 
 */
package org.pathwayeditor.businessobjects.pojos;

import java.util.Iterator;

import org.pathwayeditor.businessobjects.drawingprimitives.ILink;
import org.pathwayeditor.businessobjects.drawingprimitives.IRootObject;
import org.pathwayeditor.businessobjects.drawingprimitives.IShape;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeModel;

/**
 * @author smoodie
 *
 */
public class RootObject implements IRootObject {

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IRootObject#childShapeIterator()
	 */
	public Iterator<IShape> childShapeIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IRootObject#getNumChildren()
	 */
	public int getNumChildren() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IRootObject#getNumInLinks()
	 */
	public int getNumInLinks() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IRootObject#getNumLinks()
	 */
	public int getNumLinks() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IRootObject#getNumOutLinks()
	 */
	public int getNumOutLinks() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IRootObject#getShapeModel()
	 */
	public IShapeModel getShapeModel() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IRootObject#inLinkIterator()
	 */
	public Iterator<ILink> inLinkIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IRootObject#linkIterator()
	 */
	public Iterator<ILink> linkIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IRootObject#outLinkIterator()
	 */
	public Iterator<ILink> outLinkIterator() {
		// TODO Auto-generated method stub
		return null;
	}

}
