/**
 * 
 */
package org.pathwayeditor.businessobjects.hibernate.pojos;

import java.util.Set;

import org.pathwayeditor.businessobjects.drawingprimitives.ICanvas;
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkEdge;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeNode;
import org.pathwayeditor.businessobjects.drawingprimitives.IZOrderedObject;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationProperty;
import org.pathwayeditor.businessobjects.typedefn.IPropertyDefinition;

/**
 * @author smoodie
 *
 */
public class HibLinkEdge implements ILinkEdge {
	private Long id;
	private HibModel model ;
	private int edgeIndex ;
	private HibCompoundNode owningNode ;
	private HibCompoundNode outNode ;
	private HibCompoundNode inNode ;

	public HibLinkEdge(){
		
	}
	
	
	public HibLinkEdge(HibModel model, HibCompoundNode owningNode, int edgeIndex, HibCompoundNode outNode, HibCompoundNode inNode){
		this.model = model;
		this.edgeIndex = edgeIndex;
		this.owningNode = owningNode;
		this.outNode = outNode;
		this.inNode = inNode;
	}
	
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public HibModel getModel() {
		return this.model;
	}

	public void setModel(HibModel model) {
		this.model = model;
	}

	public int getEdgeIndex() {
		return this.edgeIndex;
	}

	public void setEdgeIndex(int nodeIndex) {
		this.edgeIndex = nodeIndex;
	}

	public HibCompoundNode getOwningNode() {
		return this.owningNode;
	}

	public void setOwningNode(HibCompoundNode owningNode) {
		this.owningNode = owningNode;
	}

	public HibCompoundNode getOutNode() {
		return this.outNode;
	}

	public void setOutNode(HibCompoundNode outNode) {
		this.outNode = outNode;
	}

	public HibCompoundNode getInNode() {
		return this.inNode;
	}

	public void setInNode(HibCompoundNode inNode) {
		this.inNode = inNode;
	}

	// TODO tests from here on
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILinkEdge#canChangeSource(org.pathwayeditor.businessobjects.drawingprimitives.IShapeNode)
	 */
	public boolean canChangeSource(IShapeNode newShape) {
		// TODO Auto-generated method stub
		return false;
	}


	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILinkEdge#canChangeTarget(org.pathwayeditor.businessobjects.drawingprimitives.IShapeNode)
	 */
	public boolean canChangeTarget(IShapeNode newShape) {
		// TODO Auto-generated method stub
		return false;
	}


	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILinkEdge#changeSource(org.pathwayeditor.businessobjects.drawingprimitives.IShapeAttribute)
	 */
	public void changeSource(IShapeAttribute newShape) {
		// TODO Auto-generated method stub
		
	}


	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILinkEdge#changeTarget(org.pathwayeditor.businessobjects.drawingprimitives.IShapeNode)
	 */
	public void changeTarget(IShapeNode newShape) {
		// TODO Auto-generated method stub
		
	}


	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILinkEdge#getLinkEdgeIndex()
	 */
	public int getLinkEdgeIndex() {
		// TODO Auto-generated method stub
		return 0;
	}


	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILinkEdge#getSourceShape()
	 */
	public IShapeNode getSourceShape() {
		// TODO Auto-generated method stub
		return null;
	}


	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILinkEdge#getTargetShape()
	 */
	public IShapeNode getTargetShape() {
		// TODO Auto-generated method stub
		return null;
	}


	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IZOrderedObject#getFirstObject()
	 */
	public IZOrderedObject getFirstObject() {
		// TODO Auto-generated method stub
		return null;
	}


	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IZOrderedObject#getLastObject()
	 */
	public IZOrderedObject getLastObject() {
		// TODO Auto-generated method stub
		return null;
	}


	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IZOrderedObject#getNextObject()
	 */
	public IZOrderedObject getNextObject() {
		// TODO Auto-generated method stub
		return null;
	}


	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IZOrderedObject#getPreviousObject()
	 */
	public IZOrderedObject getPreviousObject() {
		// TODO Auto-generated method stub
		return null;
	}


	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotatedObject#getProperty(org.pathwayeditor.businessobjects.typedefn.IPropertyDefinition)
	 */
	public IAnnotationProperty getProperty(IPropertyDefinition propDefn) {
		// TODO Auto-generated method stub
		return null;
	}


	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotatedObject#getProperty(java.lang.String)
	 */
	public IAnnotationProperty getProperty(String propName) {
		// TODO Auto-generated method stub
		return null;
	}


	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotatedObject#propertyIterator()
	 */
	public Set<IAnnotationProperty> propertyIterator() {
		// TODO Auto-generated method stub
		return null;
	}


	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICanvasObject#getCanvas()
	 */
	public ICanvas getCanvas() {
		// TODO Auto-generated method stub
		return null;
	}


	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICanvasObject#getCreationSerial()
	 */
	public int getCreationSerial() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	

}

