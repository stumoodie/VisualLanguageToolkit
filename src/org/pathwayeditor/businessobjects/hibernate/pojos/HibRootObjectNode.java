/**
 * 
 */
package org.pathwayeditor.businessobjects.hibernate.pojos;

import java.util.Iterator;

import org.pathwayeditor.businessobjects.drawingprimitives.IChildModel;
import org.pathwayeditor.businessobjects.drawingprimitives.IRootObjectNode;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeAttribute;

/**
 * @author nhanlon
 *
 */
public class HibRootObjectNode extends HibCompoundNode implements IRootObjectNode {

	/**
	 * @param model
	 * @param rootNode
	 * @param nodeIdxIdx
	 */
	public HibRootObjectNode(HibModel model, HibCompoundNode rootNode, int nodeIdxIdx) {
		super(model,rootNode,nodeIdxIdx);
	}

	/**
	 * 
	 */
	public HibRootObjectNode() {
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IRootObjectNode#childShapeIterator()
	 */
	public Iterator<IShapeAttribute> childShapeIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IRootObjectNode#getNumShapes()
	 */
	public int getNumShapes() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IRootObjectNode#getShapeModel()
	 */
	public IChildModel getShapeModel() {
		// TODO Auto-generated method stub
		return null;
	}

}
