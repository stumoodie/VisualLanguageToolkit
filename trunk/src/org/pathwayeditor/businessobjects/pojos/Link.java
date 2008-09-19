/**
 * 
 */
package org.pathwayeditor.businessobjects.pojos;

import java.util.Iterator;
import java.util.Set;

import org.pathwayeditor.businessobjects.drawingprimitives.ICanvas;
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkEndDecorator;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.IZOrderedObject;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.ConnectionRouter;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.IBendPoint;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LineStyle;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationProperty;
import org.pathwayeditor.businessobjects.typedefn.ILinkObjectType;
import org.pathwayeditor.businessobjects.typedefn.IPropertyDefinition;

import uk.ed.inf.graph.compound.archetypal.ArchetypalCompoundEdge;

/**
 * @author smoodie
 *
 */
public class Link extends ArchetypalCompoundEdge implements ILinkAttribute {
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
	public boolean canChangeSource(IShapeAttribute newShape) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILink#canChangeTarget(org.pathwayeditor.businessobjects.drawingprimitives.IShape)
	 */
	public boolean canChangeTarget(IShapeAttribute newShape) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILink#changeSource(org.pathwayeditor.businessobjects.drawingprimitives.IShape)
	 */
	public void changeSource(IShapeAttribute newShape) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILink#changeTarget(org.pathwayeditor.businessobjects.drawingprimitives.IShape)
	 */
	public void changeTarget(IShapeAttribute newShape) {
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
	public ILinkEndDecorator getLinkSourceDecoration() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILink#getLinkTargetDecoration()
	 */
	public ILinkEndDecorator getLinkTargetDecoration() {
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
	public IShapeAttribute getSourceShape() {
		return (Shape)super.getConnectedNodes().getOutNode();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILink#getTargetShape()
	 */
	public IShapeAttribute getTargetShape() {
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

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILinkAttribute#getLineColor()
	 */
	public RGB getLineColor() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILinkAttribute#getLineStyle()
	 */
	public LineStyle getLineStyle() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILinkAttribute#setLineColor(org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB)
	 */
	public void setLineColor(RGB newColor) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILinkAttribute#getUrl()
	 */
	public String getUrl() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILinkAttribute#setUrl(java.lang.String)
	 */
	public void setUrl(String url) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILinkAttribute#addLinkProperty(java.lang.String, org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationProperty)
	 */
	public void addLinkProperty(String name, IAnnotationProperty toAdd) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILinkAttribute#setLineStyle(org.pathwayeditor.businessobjects.drawingprimitives.attributes.LineStyle)
	 */
	public void setLineStyle(LineStyle style) {
		// TODO Auto-generated method stub
		
	}

}
