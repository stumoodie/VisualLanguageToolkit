/**
 * 
 */
package org.pathwayeditor.businessobjects.hibernate.pojos;

/**
 * @author nhanlon
 *
 */
public class HibCompoundRootNode extends HibCompoundNode{

	/**
	 * @param model
	 * @param rootNode
	 * @param nodeIdxIdx
	 */
	public HibCompoundRootNode(HibModel model, HibCompoundNode rootNode, int nodeIdxIdx) {
		super(model,rootNode,nodeIdxIdx);
	}

	/**
	 * 
	 */
	public HibCompoundRootNode() {
	}

}
