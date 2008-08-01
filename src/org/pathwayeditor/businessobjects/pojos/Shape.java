/**
 * 
 */
package org.pathwayeditor.businessobjects.pojos;

import java.util.Iterator;
import java.util.Set;

import org.pathwayeditor.businessobjects.drawingprimitives.ICanvas;
import org.pathwayeditor.businessobjects.drawingprimitives.ILabel;
import org.pathwayeditor.businessobjects.drawingprimitives.ILink;
import org.pathwayeditor.businessobjects.drawingprimitives.IShape;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeModel;
import org.pathwayeditor.businessobjects.drawingprimitives.IZOrderedObject;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.IPrimitiveShape;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LineStyle;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.Location;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.Size;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationProperty;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibShape;
import org.pathwayeditor.businessobjects.typedefn.IPropertyDefinition;
import org.pathwayeditor.businessobjects.typedefn.IShapeObjectType;

import uk.ed.inf.graph.compound.impl.CompoundNode;

/**
 * @author smoodie
 *
 */
public class Shape implements IShape {
	private final HibShape hibShape;
	private CompoundNode node;
	private final IShapeObjectType objectType;
	private final ShapeModel shapeModel;
	
	public Shape(IShapeObjectType objectType, HibShape hibShape){
		this.hibShape = hibShape;
		this.objectType = objectType;
		this.shapeModel = new ShapeModel(this.node.getChildCigraph());
	}
	
	public Shape(IShapeObjectType objectType){
		this.hibShape = new HibShape();
		this.objectType = objectType;
		this.shapeModel = new ShapeModel(this.node.getChildCigraph());
	}
	
	void setNode(CompoundNode node){
		this.node = node;
	}
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShape#childShapeIterator()
	 */
	public Iterator<IShape> childShapeIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShape#getDescription()
	 */
	public String getDescription() {
		return this.hibShape.getDescription();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShape#getDetailedDescription()
	 */
	public String getDetailedDescription() {
		return this.hibShape.getDetailedDescription();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShape#getFillColour()
	 */
	public RGB getFillColour() {
		return new RGB(this.hibShape.getFillRed(), this.hibShape.getFillGreen(), this.hibShape.getFillBlue());
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShape#getLineColour()
	 */
	public RGB getLineColour() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShape#getLineStyle()
	 */
	public LineStyle getLineStyle() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShape#getLineWidth()
	 */
	public int getLineWidth() {
		return this.getLineWidth();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShape#getLocation()
	 */
	public Location getLocation() {
		return new Location(this.hibShape.getXPosition(), this.hibShape.getYPosition());
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShape#getName()
	 */
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShape#getNumChildren()
	 */
	public int getNumChildren() {
		Iterator<CompoundNode> iter = this.node.childIterator();
		int cntr = 0;
		while(iter.hasNext()){
			CompoundNode node = iter.next();
			if(node.getColourHandler().getColour() instanceof IShape){
				cntr++;
			}
		}
		return cntr;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShape#getNumInLinks()
	 */
	public int getNumInLinks() {
		return this.node.getInDegree();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShape#getNumLabels()
	 */
	public int getNumLabels() {
		Iterator<CompoundNode> iter = this.node.childIterator();
		int cntr = 0;
		while(iter.hasNext()){
			CompoundNode node = iter.next();
			if(node.getColourHandler().getColour() instanceof ILabel){
				cntr++;
			}
		}
		return cntr;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShape#getNumLinks()
	 */
	public int getNumLinks() {
		return this.node.getDegree();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShape#getNumOutLinks()
	 */
	public int getNumOutLinks() {
		return this.node.getOutDegree();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShape#getObjectType()
	 */
	public IShapeObjectType getObjectType() {
		return this.objectType;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShape#getParent()
	 */
	public IShape getParent() {
		return (Shape)this.node.getParent().getColourHandler().getColour();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShape#getPrimitiveShape()
	 */
	public IPrimitiveShape getPrimitiveShape() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShape#getProperty(java.lang.String)
	 */
	public IAnnotationProperty getProperty(String propertyName) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShape#getShapeModel()
	 */
	public IShapeModel getShapeModel() {
		return this.shapeModel;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShape#getSize()
	 */
	public Size getSize() {
		return new Size(this.hibShape.getWidth(), this.hibShape.getHeight());
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShape#getUrl()
	 */
	public String getUrl() {
		return this.hibShape.getUrl();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShape#hasProperty(java.lang.String)
	 */
	public boolean hasProperty(String propertyName) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShape#inLinkIterator()
	 */
	public Iterator<ILink> inLinkIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShape#labelIterator()
	 */
	public Iterator<ILabel> labelIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShape#linkIterator()
	 */
	public Iterator<ILink> linkIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShape#outLinkIterator()
	 */
	public Iterator<ILink> outLinkIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShape#setDescription(java.lang.String)
	 */
	public void setDescription(String description) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShape#setDetailedDescription(java.lang.String)
	 */
	public void setDetailedDescription(String detailedDescription) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShape#setFillColour(org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB)
	 */
	public void setFillColour(RGB fillColour) {
		this.hibShape.setFillRed(fillColour.getRed());
		this.hibShape.setFillGreen(fillColour.getGreen());
		this.hibShape.setFillBlue(fillColour.getBlue());
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShape#setLineColour(org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB)
	 */
	public void setLineColour(RGB lineColour) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShape#setLineStyle(org.pathwayeditor.businessobjects.drawingprimitives.attributes.LineStyle)
	 */
	public void setLineStyle(LineStyle lineStyle) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShape#setLineWidth(int)
	 */
	public void setLineWidth(int lineWidth) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShape#setLocation(org.pathwayeditor.businessobjects.drawingprimitives.attributes.Location)
	 */
	public void setLocation(Location newLocation) {
		this.hibShape.setXPosition(newLocation.getX());
		this.hibShape.setYPosition(newLocation.getY());
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShape#setName(java.lang.String)
	 */
	public void setName(String name) {
		this.hibShape.setName(name);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShape#setPrimitiveShape(org.pathwayeditor.businessobjects.drawingprimitives.attributes.IPrimitiveShape)
	 */
	public void setPrimitiveShape(IPrimitiveShape primitiveShape) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShape#setSize(org.pathwayeditor.businessobjects.drawingprimitives.attributes.Size)
	 */
	public void setSize(Size size) {
		this.hibShape.setWidth(size.getWidth());
		this.hibShape.setHeight(size.getHeight());
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShape#setUrl(java.lang.String)
	 */
	public void setUrl(String url) {
		this.hibShape.setUrl(url);
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
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotatedObject#propertyIterator()
	 */
	public Set<IAnnotationProperty> propertyIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	HibShape getHibShape(){
		return this.hibShape;
	}
	
	CompoundNode getNode(){
		return this.node;
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
