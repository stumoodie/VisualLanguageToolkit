/**
 * 
 */
package org.pathwayeditor.businessobjects.hibernate.pojos;

/**
 * @author nhanlon
 *
 */
public class HibRootObjectNode extends HibCompoundNode{

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

}
