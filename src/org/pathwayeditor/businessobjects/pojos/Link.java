/**
 * 
 */
package org.pathwayeditor.businessobjects.pojos;

import java.util.Iterator;
import java.util.Set;

import org.pathwayeditor.businessobjects.drawingprimitives.ICanvas;
import org.pathwayeditor.businessobjects.drawingprimitives.ILink;
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkEndDecoration;
import org.pathwayeditor.businessobjects.drawingprimitives.IShape;
import org.pathwayeditor.businessobjects.drawingprimitives.IZOrderedObject;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.ConnectionRouter;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.IBendPoint;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationProperty;
import org.pathwayeditor.businessobjects.typedefn.ILinkObjectType;
import org.pathwayeditor.businessobjects.typedefn.IPropertyDefinition;

import uk.ed.inf.graph.compound.archetypal.ArchetypalCompoundEdge;

/**
 * @author smoodie
 *
 */
public class Link extends ArchetypalCompoundEdge implements ILink {
	private ILinkObjectType linkObjectType;
	
	public Link(ILinkObjectType linkObjectType, ShapeModel owningSubgraph, int index, Shape outNode, Shape inNode){
		super(owningSubgraph, index, outNode, inNode);
		this.linkObjectType = linkObjectType;
	}
	
	public Link(ShapeModel owningSubgraph, int index, Shape outNode, Shape inNode, Link otherLink){
		super(owningSubgraph, index, outNode, inNode);
		this.linkObjectType = otherLink.linkObjectType;
	}
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILink#addBendPoint(org.pathwayeditor.businessobjects.drawingprimitives.attributes.IBendPoint)
	 */
	public void addBendPoint(IBendPoint newBendPoint) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILink#bendPointIterator()
	 */
	public Iterator<IBendPoint> bendPointIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILink#canChangeSource(org.pathwayeditor.businessobjects.drawingprimitives.IShape)
	 */
	public boolean canChangeSource(IShape newShape) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILink#canChangeTarget(org.pathwayeditor.businessobjects.drawingprimitives.IShape)
	 */
	public boolean canChangeTarget(IShape newShape) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILink#changeSource(org.pathwayeditor.businessobjects.drawingprimitives.IShape)
	 */
	public void changeSource(IShape newShape) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILink#changeTarget(org.pathwayeditor.businessobjects.drawingprimitives.IShape)
	 */
	public void changeTarget(IShape newShape) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILink#containsBendPoint(org.pathwayeditor.businessobjects.drawingprimitives.attributes.IBendPoint)
	 */
	public boolean containsBendPoint(IBendPoint bendPoint) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILink#getCanvas()
	 */
	public ICanvas getCanvas() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILink#getCreationSerial()
	 */
	public int getCreationSerial() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILink#getLinkSourceDecoration()
	 */
	public ILinkEndDecoration getLinkSourceDecoration() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILink#getLinkTargetDecoration()
	 */
	public ILinkEndDecoration getLinkTargetDecoration() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILink#getObjectType()
	 */
	public ILinkObjectType getObjectType() {
		return this.linkObjectType;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILink#getRouter()
	 */
	public ConnectionRouter getRouter() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILink#getSourceShape()
	 */
	public IShape getSourceShape() {
		return (Shape)super.getConnectedNodes().getOutNode();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILink#getTargetShape()
	 */
	public IShape getTargetShape() {
		return (Shape)super.getConnectedNodes().getInNode();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILink#numBendPoints()
	 */
	public int numBendPoints() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILink#removeBendPoint(org.pathwayeditor.businessobjects.drawingprimitives.attributes.IBendPoint)
	 */
	public void removeBendPoint(IBendPoint bendPoint) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILink#setRouter(org.pathwayeditor.businessobjects.drawingprimitives.attributes.ConnectionRouter)
	 */
	public void setRouter(ConnectionRouter router) {
		// TODO Auto-generated method stub

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

}
