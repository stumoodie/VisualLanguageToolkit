/**
 * 
 */
package org.pathwayeditor.businessobjects.hibernate.pojos;

import java.io.Serializable;

import org.pathwayeditor.businessobjects.drawingprimitives.ICompoundGraphBuilder;
import org.pathwayeditor.businessobjects.pojos.IBusinessObjectData;
import org.pathwayeditor.businessobjects.pojos.Model;

/**
 * @author smoodie
 *
 */
public class HibModel implements IBusinessObjectData<Model>, Serializable {
	private static final long serialVersionUID = -6097842004070213053L;
	private Long id;
	private HibCanvas canvas = null;
	private Model businessObject;
	private ICompoundGraphBuilder compoundGraphBuilder;
	private HibCompoundNode rootNode ;
	
	public HibModel(){
		
	}
	
	/**
	 * Constructor to be used by business object facade.
	 * @param businessObject
	 * @param canvas
	 */
	public HibModel(Model businessObject, HibCanvas canvas){
		this.canvas = canvas;
		this.businessObject = businessObject;
	}
	
	@SuppressWarnings("unused")
	private void setId(Long id){
		this.id = id;
	}
	
	public Long getId(){
		return this.id;
	}
	
	void setCanvas(HibCanvas canvas){
		this.canvas = canvas;
	}
	
	public HibCompoundNode getRootNode() {
		return this.rootNode;
	}

	public void setRootNode(HibCompoundNode rootNode) {
		this.rootNode = rootNode;
		if(rootNode!=null)
			rootNode.setModel(this);
	}

	public void changeCanvas(HibCanvas newCanvas){
		this.canvas = newCanvas;
	}
	
	public HibCanvas getCanvas(){
		return this.canvas;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((this.canvas == null) ? 0 : this.canvas.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof HibModel))
			return false;
		final HibModel other = (HibModel) obj;
		if (this.canvas == null) {
			if (other.getCanvas() != null)
				return false;
		} else if (!this.canvas.equals(other.getCanvas()))
			return false;
		return true;
	}

	/**
	 * Gets the business object facade to this hibernate object. If necessary this is created.
	 * @return an instance of <code>Model</code> that is backed by an instance of this class.
	 * @throws IllegalStateException if <code>getCompoundGraphBuilder() == null</code>. 
	 */
	public Model getBusinessObject() {
//		if(this.compoundGraphBuilder == null) throw new IllegalStateException("Compound graph builder must be set before this operation can succeed");
//		if(this.businessObject == null){
//			this.businessObject = new Model(this.compoundGraphBuilder, this);
//		}
		return this.businessObject;
	}

	/**
	 * Sets the graph builder used to build a 
	 * @param compoundGraphBuilder
	 */
	public void setCompoundGraphBuilder(ICompoundGraphBuilder compoundGraphBuilder) {
		this.compoundGraphBuilder = compoundGraphBuilder;
	}

	public ICompoundGraphBuilder getCompoundGraphBuilder() {
		return this.compoundGraphBuilder;
	}
}
