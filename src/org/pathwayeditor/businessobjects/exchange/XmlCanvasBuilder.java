/**
 * 
 */
package org.pathwayeditor.businessobjects.exchange;


import java.io.IOException;
import java.io.Writer;
import java.util.Iterator;

import org.exolab.castor.xml.MarshalException;
import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.ValidationException;
import org.exolab.castor.xml.XMLContext;
import org.pathwayeditor.businessobjects.drawingprimitives.ICanvas;
import org.pathwayeditor.businessobjects.drawingprimitives.ILabelAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.ILabelNode;
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkEdge;
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkTerminus;
import org.pathwayeditor.businessobjects.drawingprimitives.IModel;
import org.pathwayeditor.businessobjects.drawingprimitives.IRootAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeNode;
import org.pathwayeditor.businessobjects.drawingprimitives.ISubModel;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LinkTermType;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationProperty;
import org.pathwayeditor.businessobjects.exchange.castor.Annotations;
import org.pathwayeditor.businessobjects.exchange.castor.Background;
import org.pathwayeditor.businessobjects.exchange.castor.Canvas;
import org.pathwayeditor.businessobjects.exchange.castor.CanvasSize;
import org.pathwayeditor.businessobjects.exchange.castor.ChildModel;
import org.pathwayeditor.businessobjects.exchange.castor.ColourType;
import org.pathwayeditor.businessobjects.exchange.castor.CompoundNodeType;
import org.pathwayeditor.businessobjects.exchange.castor.DrawingNodeLocation;
import org.pathwayeditor.businessobjects.exchange.castor.FillColour;
import org.pathwayeditor.businessobjects.exchange.castor.Grid;
import org.pathwayeditor.businessobjects.exchange.castor.GridSize;
import org.pathwayeditor.businessobjects.exchange.castor.LabelAttribute;
import org.pathwayeditor.businessobjects.exchange.castor.LabelNode;
import org.pathwayeditor.businessobjects.exchange.castor.LinkAttribute;
import org.pathwayeditor.businessobjects.exchange.castor.LinkEdge;
import org.pathwayeditor.businessobjects.exchange.castor.LinkLineColour;
import org.pathwayeditor.businessobjects.exchange.castor.LinkTerminus;
import org.pathwayeditor.businessobjects.exchange.castor.Location;
import org.pathwayeditor.businessobjects.exchange.castor.Model;
import org.pathwayeditor.businessobjects.exchange.castor.Notation;
import org.pathwayeditor.businessobjects.exchange.castor.ObjectType;
import org.pathwayeditor.businessobjects.exchange.castor.PointType;
import org.pathwayeditor.businessobjects.exchange.castor.RootAttribute;
import org.pathwayeditor.businessobjects.exchange.castor.RootNode;
import org.pathwayeditor.businessobjects.exchange.castor.ShapeAttribute;
import org.pathwayeditor.businessobjects.exchange.castor.ShapeLineColour;
import org.pathwayeditor.businessobjects.exchange.castor.ShapeNode;
import org.pathwayeditor.businessobjects.exchange.castor.Size;
import org.pathwayeditor.businessobjects.exchange.castor.types.LinkTerminusTypeType;
import org.pathwayeditor.businessobjects.notationsubsystem.INotation;
import org.pathwayeditor.businessobjects.typedefn.IObjectType;
import org.pathwayeditor.figure.geometry.Dimension;
import org.pathwayeditor.figure.geometry.Point;

/**
 * @author smoodie
 *
 */
public class XmlCanvasBuilder {
	private Canvas xmlCanvas;
	private ICanvas dbCanvas;
	
	public XmlCanvasBuilder(){
	}
	
	public void setCanvas(ICanvas dbCanvas){
		this.dbCanvas = dbCanvas;
	}
	
	public Canvas getCanvas(){
		return this.xmlCanvas;
	}


	public void buildCanvas(){
		if(this.dbCanvas == null) throw new IllegalStateException("No canvas has been set");
		try{
			xmlCanvas = new Canvas();
			setCanvasGraphicalProperties();
			setNotation();
			addCanvasAttributes();
			createModel();
		}
		catch(RuntimeException e){
			xmlCanvas = null;
			throw new RuntimeException("A bug was detected during xml schema generation", e);
		}
	}
	
	public void write(Writer writer) throws IOException{
		XMLContext ctx = new XMLContext();
		Marshaller m = ctx.createMarshaller();
		m.setSchemaLocation("http://www.pathwayeditor.org/Exchange file:/Users/smoodie/sfworkspace/BusinessObjects/schema/Canvas.xsd");
		m.setNamespaceMapping("epx", "http://www.pathwayeditor.org/Exchange");
		m.setMarshalAsDocument(true);
		m.setWriter(writer);
		try {
			m.marshal(xmlCanvas);
		} catch (MarshalException e) {
			throw new RuntimeException("A bug was detected during xml schema generation", e);
		} catch (ValidationException e) {
			throw new RuntimeException("A bug was detected during xml schema generation", e);
		}
	}
	
	private void setCanvasGraphicalProperties(){
		Background bgrd = new Background();
		bgrd.setBlue(dbCanvas.getBackgroundColour().getBlue());
		bgrd.setRed(dbCanvas.getBackgroundColour().getRed());
		bgrd.setGreen(dbCanvas.getBackgroundColour().getGreen());
		xmlCanvas.setBackground(bgrd);
		Grid grid = new Grid();
		grid.setGridOn(dbCanvas.isGridEnabled());
		grid.setSnapToGrid(dbCanvas.isSnapToGridOn());
		GridSize gridSize = new GridSize();
		gridSize.setHeight(dbCanvas.getCanvasSize().getHeight());
		gridSize.setWidth(dbCanvas.getCanvasSize().getWidth());
		grid.setGridSize(gridSize);
		xmlCanvas.setGrid(grid);
		CanvasSize canvasSize = new CanvasSize();
		canvasSize.setHeight(dbCanvas.getCanvasSize().getHeight());
		canvasSize.setWidth(dbCanvas.getCanvasSize().getWidth());
		xmlCanvas.setCanvasSize(canvasSize);
	}
	
	private void setNotation(){
		Notation notation = new Notation();
		INotation dbNotation = dbCanvas.getNotationSubsystem().getNotation();
		notation.setQualifiedName(dbNotation.getQualifiedName());
		notation.setMajorVersion(dbNotation.getVersion().getMajorVersion());
		notation.setMinorVersion(dbNotation.getVersion().getMinorVersion());
		notation.setPatchVersion(dbNotation.getVersion().getPatchVersion());
		xmlCanvas.setNotation(notation);
	}
	
	private void addCanvasAttributes(){
		addRootAttribute();
		addShapeAttributes();
		addLabelAttributes();
		addLinkAttributes();
		addLinkTermini();
	}
	
	private void addRootAttribute(){
		IRootAttribute rootAttribute = this.dbCanvas.getModel().getRootNode().getAttribute();
		RootAttribute xmlRootAtt = new RootAttribute();
		xmlRootAtt.setObjectType(createObjectType(rootAttribute.getObjectType()));
		xmlRootAtt.setCreationSerial(rootAttribute.getCreationSerial());
		xmlRootAtt.setDrawingNodeLocation(createLocation(new DrawingNodeLocation(), rootAttribute.getLocation()));
		xmlRootAtt.setSize(createSize(rootAttribute.getSize()));
		xmlRootAtt.setAnnotations(new Annotations());
		xmlRootAtt.setObjectType(createObjectType(rootAttribute.getObjectType()));
		this.xmlCanvas.setRootAttribute(xmlRootAtt);
	}
	
	private static Size createSize(Dimension size) {
		Size retVal = new Size();
		retVal.setHeight(size.getHeight());
		retVal.setWidth(size.getWidth());
		return retVal;
	}

	private static ObjectType createObjectType(IObjectType ot){
		ObjectType retVal = new ObjectType();
		retVal.setUniqueId(ot.getUniqueId());
		return retVal;
	}
	
	private static Annotations createAnnotations(Iterator<IAnnotationProperty> propIter){
		AnnotationsBuilder builder = new AnnotationsBuilder(propIter);
		builder.build();
		return builder.getAnnotations();
	}
	
	private void addShapeAttributes(){
		Iterator<IShapeAttribute> iter = this.dbCanvas.shapeAttributeIterator();
		while(iter.hasNext()){
			IShapeAttribute attrib = iter.next();
			ShapeAttribute xmlAttrib = new ShapeAttribute();
			xmlAttrib.setCreationSerial(attrib.getCreationSerial());
			xmlAttrib.setDrawingNodeLocation(createLocation(new DrawingNodeLocation(),
					attrib.getLocation()));
			xmlAttrib.setSize(createSize(attrib.getSize()));
			xmlAttrib.setObjectType(createObjectType(attrib.getObjectType()));
			xmlAttrib.setAnnotations(createAnnotations(attrib.propertyIterator()));
			xmlAttrib.setFillColour(setColour(new FillColour(), attrib.getFillColour()));
			xmlAttrib.setShapeLineColour(setColour(new ShapeLineColour(), attrib.getLineColour()));
			this.xmlCanvas.addShapeAttribute(xmlAttrib);
		}
	}
	
	private static <C extends ColourType> C setColour(C xmlColour, RGB rgb) {
		xmlColour.setBlue(rgb.getBlue());
		xmlColour.setGreen(rgb.getGreen());
		xmlColour.setRed(rgb.getRed());
		return xmlColour;
	}

	private void addLabelAttributes(){
		Iterator<ILabelAttribute> iter = this.dbCanvas.labelAttributeIterator();
		while(iter.hasNext()){
			ILabelAttribute attrib = iter.next();
			LabelAttribute xmlAttrib = new LabelAttribute();
			xmlAttrib.setCreationSerial(attrib.getCreationSerial());
			xmlAttrib.setDrawingNodeLocation(createLocation(new DrawingNodeLocation(),
					attrib.getLocation()));
			xmlAttrib.setSize(createSize(attrib.getSize()));
			xmlAttrib.setDrawingNodeLocation(createLocation(new DrawingNodeLocation(),
					attrib.getLocation()));
			xmlAttrib.setAnnotations(new Annotations());
			this.xmlCanvas.addLabelAttribute(xmlAttrib);
		}
	}
	
	private void addLinkAttributes(){
		Iterator<ILinkAttribute> iter = this.dbCanvas.linkAttributeIterator();
		while(iter.hasNext()){
			ILinkAttribute attrib = iter.next();
			LinkAttribute xmlAttrib = new LinkAttribute();
			xmlAttrib.setCreationSerial(attrib.getCreationSerial());
			xmlAttrib.setObjectType(createObjectType(attrib.getObjectType()));
			xmlAttrib.setAnnotations(createAnnotations(attrib.propertyIterator()));
			xmlAttrib.setLinkLineColour(setColour(new LinkLineColour(), attrib.getLineColor()));
			xmlAttrib.setSrcTermSerial(attrib.getSourceTerminus().getCreationSerial());
			xmlAttrib.setTgtTermSerial(attrib.getTargetTerminus().getCreationSerial());
			this.xmlCanvas.addLinkAttribute(xmlAttrib);
		}
	}
	
	private void addLinkTermini(){
		Iterator<ILinkTerminus> iter = this.dbCanvas.linkTerminusIterator();
		while(iter.hasNext()){
			ILinkTerminus attrib = iter.next();
			LinkTerminus xmlAttrib = new LinkTerminus();
			xmlAttrib.setCreationSerial(attrib.getCreationSerial());
			xmlAttrib.setAnnotations(createAnnotations(attrib.propertyIterator()));
			Location locn = new Location();
			locn.setX(attrib.getLocation().getX());
			locn.setY(attrib.getLocation().getY());
			xmlAttrib.setLocation(locn);
			xmlAttrib.setTerminusType(createLinkTermType(attrib));
			this.xmlCanvas.addLinkTerminus(xmlAttrib);
		}
	}
	
	private LinkTerminusTypeType createLinkTermType(ILinkTerminus terminus){
		LinkTerminusTypeType retVal = LinkTerminusTypeType.S;
		if(terminus.getLinkTermType().equals(LinkTermType.TARGET)){
			retVal = LinkTerminusTypeType.T;
		}
		return retVal;
	}
	
	private static <T extends PointType> T createLocation(T location, Point p){
		location.setX(p.getX());
		location.setY(p.getY());
		return location;
	}
	
	private void createModel(){
		IModel dbModel = dbCanvas.getModel();
		Model model = new Model();
		xmlCanvas.setModel(model);
		RootNode rootNode = new RootNode();
		rootNode.setNodeId(dbModel.getRootNode().getIndex());
		model.setRootNode(rootNode);
		ChildModel childModel = new ChildModel();
		rootNode.setChildModel(childModel);
		addSubmodel(rootNode, dbModel.getRootNode().getSubModel());
	}
	
	private void addSubmodel(CompoundNodeType parentNode, ISubModel submodel){
		addShapeSubmodel(parentNode, submodel);
		addLabelSubmodel(parentNode, submodel);
		addLinkSubmodel(parentNode, submodel);
	}
	
	private void addShapeSubmodel(CompoundNodeType parentNode, ISubModel submodel){
		ChildModel xmlChildModel = parentNode.getChildModel();
		Iterator<IShapeNode> iter = submodel.shapeNodeIterator();
		while(iter.hasNext()){
			IShapeNode node = iter.next();
			ShapeNode xmlNode = createShapeNode(node);
			xmlChildModel.addShapeNode(xmlNode);
			addSubmodel(xmlNode, node.getSubModel());
		}
	}
	
	private void addLabelSubmodel(CompoundNodeType parentNode, ISubModel submodel){
		ChildModel xmlChildModel = parentNode.getChildModel();
		Iterator<ILabelNode> iter = submodel.labelIterator();
		while(iter.hasNext()){
			ILabelNode node = iter.next();
			LabelNode xmlNode = createLabelNode(node);
			xmlChildModel.addLabelNode(xmlNode);
			addSubmodel(xmlNode, node.getSubModel());
		}
	}
	
	private void addLinkSubmodel(CompoundNodeType parentNode, ISubModel submodel){
		ChildModel xmlChildModel = parentNode.getChildModel();
		Iterator<ILinkEdge> iter = submodel.linkIterator();
		while(iter.hasNext()){
			ILinkEdge node = iter.next();
			LinkEdge xmlNode = createLinkEdge(node);
			xmlChildModel.addLinkEdge(xmlNode);
		}
	}
	
	private LinkEdge createLinkEdge(ILinkEdge linkEdge){
		LinkEdge xmlLinkEdge = new LinkEdge();
		xmlLinkEdge.setLinkIdx(linkEdge.getIndex());
		xmlLinkEdge.setSrcNodeIdx(linkEdge.getSourceShape().getIndex());
		xmlLinkEdge.setTgtNodeIdx(linkEdge.getTargetShape().getIndex());
		xmlLinkEdge.setLinkAttributeSerial(linkEdge.getAttribute().getCreationSerial());
		return xmlLinkEdge;
	}
	
	private LabelNode createLabelNode(ILabelNode labelNode){
		LabelNode xmlNode = new LabelNode();
		ChildModel childModel = new ChildModel();
		xmlNode.setChildModel(childModel);
		xmlNode.setNodeId(labelNode.getIndex());
		xmlNode.setLabelAttributeSerial(labelNode.getAttribute().getCreationSerial());
		return xmlNode;
	}
	
	private ShapeNode createShapeNode(IShapeNode shapeNode){
		ShapeNode xmlNode = new ShapeNode();
		ChildModel childModel = new ChildModel();
		xmlNode.setChildModel(childModel);
		xmlNode.setNodeId(shapeNode.getIndex());
		xmlNode.setShapeAttributeSerial(shapeNode.getAttribute().getCreationSerial());
		return xmlNode;
	}
}
