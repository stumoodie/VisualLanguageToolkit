/**
 * 
 */
package org.pathwayeditor.businessobjects.pojos;

import java.util.Iterator;
import java.util.Set;

import org.pathwayeditor.businessobjects.drawingprimitives.ILink;
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkEndDecoration;
import org.pathwayeditor.businessobjects.drawingprimitives.IShape;
import org.pathwayeditor.businessobjects.drawingprimitives.IZOrderedObject;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.ConnectionRouter;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.IBendPoint;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationProperty;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibLink;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibObjectType;
import org.pathwayeditor.businessobjects.typedefn.ILinkObjectType;
import org.pathwayeditor.businessobjects.typedefn.IPropertyDefinition;

import uk.ed.inf.graph.compound.impl.CompoundEdge;
import uk.ed.inf.graph.compound.impl.CompoundEdgeFactory;
import uk.ed.inf.graph.compound.impl.CompoundGraph;
import uk.ed.inf.graph.compound.impl.CompoundNode;
import uk.ed.inf.graph.compound.impl.SubCompoundGraphFactory;

/**
 * @author smoodie
 *
 */
public class Link implements ILink {
//	private HibLink hibLink;
	private CompoundEdge linkEdge;
	private final ILinkObjectType objectType;
	
	public Link(Canvas canvas, ILinkObjectType objectType, Shape outNode, Shape inNode){
		this.objectType = objectType;
		HibObjectType hibObjectType = ObjectTypeMappingFactory.getInstance().createHibLinkObjectType(objectType);
		HibLink hibLink = new HibLink(canvas.getHibObject(), canvas.nextCreationSerial(), hibObjectType);
		this.linkEdge = createNewEdge(getGraph(), outNode.getNode(), inNode.getNode());
		this.linkEdge.getColourHandler().setColour(hibLink);
	}
	
	public Link(HibLink hibLink){
		this.objectType = ObjectTypeMappingFactory.getInstance().getLinkObjectType(hibLink.getObjectType());
		CompoundGraph graph = hibLink.getCanvas().getBusinessObject().getModel().getGraph();
		CompoundNode outNode = hibLink.getSourceShape().getBusinessObject().getNode();
		CompoundNode inNode = hibLink.getTargetShape().getBusinessObject().getNode();
		this.linkEdge = createNewEdge(graph, outNode, inNode);
		this.linkEdge.getColourHandler().setColour(hibLink);
	}
	
	
	private CompoundGraph getGraph(){
		return this.getCanvas().getModel().getGraph();
	}
	
	private static CompoundEdge createNewEdge(CompoundGraph graph, CompoundNode outNode, CompoundNode inNode){
		CompoundEdgeFactory fact = graph.edgeFactory();
		fact.setColourHandler(new LinkEdgeColourHandler());
		fact.setPair(outNode, inNode);
		CompoundEdge newEdge = fact.createEdge();
		return newEdge;
	}
	
	public Canvas getCanvas(){
		return this.getHibLink().getCanvas().getBusinessObject();
	}
	
	public int getCreationSerial(){
		return this.getHibLink().getLinkIndex();
	}
	
	void setEdge(CompoundEdge edge){
		this.linkEdge = edge;
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
		Shape targetShape = (Shape)this.linkEdge.getConnectedNodes().getInNode().getColourHandler().getColour();
		return this.objectType.getLinkConnectionRules().isValidTarget(newShape.getObjectType(), targetShape.getObjectType());
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILink#canChangeTarget(org.pathwayeditor.businessobjects.drawingprimitives.IShape)
	 */
	public boolean canChangeTarget(IShape newShape) {
		Shape targetShape = (Shape)this.linkEdge.getConnectedNodes().getOutNode().getColourHandler().getColour();
		return this.objectType.getLinkConnectionRules().isValidTarget(targetShape.getObjectType(), newShape.getObjectType());
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILink#changeSource(org.pathwayeditor.businessobjects.drawingprimitives.IShape)
	 */
	public void changeSource(IShape iNewShape) {
		Shape newShape = (Shape)iNewShape;
		CompoundGraph graph = this.linkEdge.getGraph();
		CompoundEdgeFactory fact = graph.edgeFactory();
		fact.setColourHandler(new LinkEdgeColourHandler());
		fact.setPair(newShape.getNode(), this.linkEdge.getConnectedNodes().getInNode());
		CompoundEdge newEdge = fact.createEdge();
		newEdge.getColourHandler().setColour(this.linkEdge.getColourHandler().getColour());
		SubCompoundGraphFactory subgraphFact = graph.subgraphFactory();
		subgraphFact.addEdge(this.linkEdge);
		graph.removeSubgraph(subgraphFact.createSubgraph());
		this.linkEdge = newEdge;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILink#changeTarget(org.pathwayeditor.businessobjects.drawingprimitives.IShape)
	 */
	public void changeTarget(IShape iNewShape) {
		Shape newShape = (Shape)iNewShape;
		CompoundGraph graph = this.linkEdge.getGraph();
		CompoundEdgeFactory fact = graph.edgeFactory();
		fact.setColourHandler(new LinkEdgeColourHandler());
		fact.setPair(this.linkEdge.getConnectedNodes().getOutNode(), newShape.getNode());
		CompoundEdge newEdge = fact.createEdge();
		newEdge.getColourHandler().setColour(this.linkEdge.getColourHandler().getColour());
		SubCompoundGraphFactory subgraphFact = graph.subgraphFactory();
		subgraphFact.addEdge(this.linkEdge);
		graph.removeSubgraph(subgraphFact.createSubgraph());
		this.linkEdge = newEdge;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILink#containsBendPoint(org.pathwayeditor.businessobjects.drawingprimitives.attributes.IBendPoint)
	 */
	public boolean containsBendPoint(IBendPoint bendPoint) {
		// TODO Auto-generated method stub
		return false;
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
		// TODO Auto-generated method stub
		return null;
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
		return (Shape)this.linkEdge.getConnectedNodes().getOutNode().getColourHandler().getColour();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILink#getTargetShape()
	 */
	public IShape getTargetShape() {
		return (Shape)this.linkEdge.getConnectedNodes().getInNode().getColourHandler().getColour();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILink#numBendPoints()
	 */
	public int numBendPoints() {
		return this.getHibLink().getBendPoints().size();
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

	CompoundEdge getEdge(){
		return this.linkEdge;
	}
	
	HibLink getHibLink(){
		return (HibLink)this.linkEdge.getColourHandler().getColour();
	}
}
