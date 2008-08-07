/**
 * 
 */
package org.pathwayeditor.businessobjects.hibernate.pojos;

import java.io.Serializable;
import java.util.Iterator;

import org.pathwayeditor.businessobjects.contectadapter.IContextAdapterServiceProvider;
import org.pathwayeditor.businessobjects.drawingprimitives.ICanvas;
import org.pathwayeditor.businessobjects.drawingprimitives.ICanvasObjectSelection;
import org.pathwayeditor.businessobjects.drawingprimitives.ILabelAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.IModel;
import org.pathwayeditor.businessobjects.drawingprimitives.IModelState;
import org.pathwayeditor.businessobjects.drawingprimitives.IRootObjectNode;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeAttribute;
import org.pathwayeditor.businessobjects.typedefn.ILinkObjectType;

/**
 * @author smoodie
 *
 */
public class HibModel implements Serializable, IModel {
	private static final long serialVersionUID = -6097842004070213053L;
	private Long id;
	private HibCanvas canvas = null;
	private HibRootObjectNode rootNode ;
	
	public HibModel(){
		
	}
	
	/**
	 * Constructor to be used by business object facade.
	 * @param businessObject
	 * @param canvas
	 */
	public HibModel(HibCanvas canvas){
		this.canvas = canvas;
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
	
	public HibRootObjectNode getRootNode() {
		return this.rootNode;
	}

	public void setRootNode(HibRootObjectNode rootNode) {
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

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IModel#canCreateLink(org.pathwayeditor.businessobjects.typedefn.ILinkObjectType, org.pathwayeditor.businessobjects.drawingprimitives.IShapeAttribute, org.pathwayeditor.businessobjects.drawingprimitives.IShapeAttribute)
	 */
	public boolean canCreateLink(ILinkObjectType linkObjectType,
			IShapeAttribute srcShape, IShapeAttribute tgtShape) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IModel#createCopy(org.pathwayeditor.businessobjects.drawingprimitives.ICanvas)
	 */
	public IModel createCopy(ICanvas newCanvas) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IModel#createLink(org.pathwayeditor.businessobjects.typedefn.ILinkObjectType, org.pathwayeditor.businessobjects.drawingprimitives.IShapeAttribute, org.pathwayeditor.businessobjects.drawingprimitives.IShapeAttribute)
	 */
	public ILinkAttribute createLink(ILinkObjectType linkObjectType,
			IShapeAttribute srcShape, IShapeAttribute tgtShape) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IModel#getContextAdapterServiceProvider()
	 */
	public IContextAdapterServiceProvider getContextAdapterServiceProvider() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IModel#getCurrentState()
	 */
	public IModelState getCurrentState() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IModel#getNumLabels()
	 */
	public int getNumLabels() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IModel#getNumLinks()
	 */
	public int getNumLinks() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IModel#getNumShapes()
	 */
	public int getNumShapes() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IModel#getRootObject()
	 */
	public IRootObjectNode getRootObject() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IModel#labelIterator()
	 */
	public Iterator<ILabelAttribute> labelIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IModel#linkIterator()
	 */
	public Iterator<ILinkAttribute> linkIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IModel#removeSubgraph(org.pathwayeditor.businessobjects.drawingprimitives.ICanvasObjectSelection)
	 */
	public void removeSubgraph(ICanvasObjectSelection selection) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IModel#restoreToState(org.pathwayeditor.businessobjects.drawingprimitives.IModelState)
	 */
	public void restoreToState(IModelState stateToRestore) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IModel#shapeIterator()
	 */
	public Iterator<IShapeAttribute> shapeIterator() {
		// TODO Auto-generated method stub
		return null;
	}
}
