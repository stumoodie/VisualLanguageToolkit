/**
 * 
 */
package org.pathwayeditor.businessobjects.hibernate.pojos;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

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
	private Set<HibCompoundEdge> edges = new HashSet<HibCompoundEdge>(0);
	private Set<HibCompoundNode> nodes = new HashSet<HibCompoundNode>(0);
	private HibCanvas canvas = null;
	private Model businessObject;
	private ICompoundGraphBuilder compoundGraphBuilder;
	
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
	
	public void changeCanvas(HibCanvas newCanvas){
		this.canvas = newCanvas;
	}
	
	public HibCanvas getCanvas(){
		return this.canvas;
	}
	
	void setEdges(Set<HibCompoundEdge> edges){
		this.edges = edges;
	}
	
	public Set<HibCompoundEdge> getEdges(){
		return this.edges;
	}
	
	public void addEdge(HibCompoundEdge node){
		//TODO: implement this!
	}

	public void removeEdge(HibCompoundEdge node){
		//TODO: implement this!
	}

	void setNodes(Set<HibCompoundNode> nodes){
		this.nodes = nodes;
	}
	
	public Set<HibCompoundNode> getNodes(){
		return this.nodes;
	}
	
	public void addNode(HibCompoundNode node){
		//TODO: implement this!
	}

	public void removeNode(HibCompoundNode node){
		//TODO: implement this!
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
		if (getClass() != obj.getClass())
			return false;
		final HibModel other = (HibModel) obj;
		if (this.canvas == null) {
			if (other.canvas != null)
				return false;
		} else if (!this.canvas.equals(other.canvas))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.pojos.IBusinessObjectData#getBusinessObject()
	 */
	public Model getBusinessObject() {
		if(this.businessObject == null){
			this.businessObject = new Model(this.compoundGraphBuilder, this);
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.pojos.ISyntaxDependentObjectProvider#setSyntaxMappingFactory(org.pathwayeditor.businessobjects.pojos.ISyntaxMappingFactory)
	 */
	public void setCompoundGraphBuilder(ICompoundGraphBuilder compoundGraphBuilder) {
		this.compoundGraphBuilder = compoundGraphBuilder;
	}
}
