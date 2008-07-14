/**
 * 
 */
package org.pathwayeditor.businessobjects.hibernate.pojos;

import java.util.HashSet;
import java.util.Set;

/**
 * @author smoodie
 *
 */
public class HibShapeModel {
	private Long id;
	private HibModel model = null;
	private HibCompoundNode rootNode = null;
	private HibCanvas canvas ;
	private Set<HibCompoundEdge> edges = new HashSet<HibCompoundEdge>(0);
	private Set<HibCompoundNode> nodes = new HashSet<HibCompoundNode>(0);
	
	public HibShapeModel(){
		
	}
	
	public HibShapeModel(HibModel model, HibCompoundNode rootNode){
		this.model = model;
		this.rootNode = rootNode;
	}
	
	@SuppressWarnings("unused")
	private void setId(Long id){
		this.id = id;
	}
	
	public Long getId(){
		return this.id;
	}
	
	public HibModel getModel(){
		return this.model;
	}
	
	void setModel(HibModel model){
		this.model = model;
	}
	
	public HibCanvas getCanvas() {
		return this.canvas;
	}

	public void setCanvas(HibCanvas canvas) {
		this.canvas = canvas;
	}

	public void changeModel(HibModel model){
		//TODO: implement this!
	}
	
	public HibCompoundNode getRootNode(){
		return this.rootNode;
	}
	
	void setRootNode(HibCompoundNode rootNode){
		this.rootNode = rootNode;
	}
	
	
	public void changeRootNode(HibCompoundNode rootNode){
		//TODO: implement this!
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

}
