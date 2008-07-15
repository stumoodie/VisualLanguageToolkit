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
	private HibCompoundNode rootNode ;
	private Set<HibShapeModel> shapeModels = new HashSet<HibShapeModel> (0) ;
	
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

	/**
	 * Gets the business object facade to this hibernate object. If necessary this is created.
	 * @return an instance of <code>Model</code> that is backed by an instance of this class.
	 * @throws IllegalStateException if <code>getCompoundGraphBuilder() == null</code>. 
	 */
	public Model getBusinessObject() {
		if(this.compoundGraphBuilder == null) throw new IllegalStateException("Compound graph builder must be set before this operation can succeed");
		if(this.businessObject == null){
			this.businessObject = new Model(this.compoundGraphBuilder, this);
		}
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

	public Set<HibShapeModel> getShapeModels() {
		return this.shapeModels;
	}

	public void setShapeModels(Set<HibShapeModel> shapeModels) {
		this.shapeModels = shapeModels;
	}
	
	
}
