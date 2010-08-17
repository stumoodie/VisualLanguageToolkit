/**
 * 
 */
package org.pathwayeditor.businessobjects.exchange;


import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.exolab.castor.xml.MarshalException;
import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.ValidationException;
import org.exolab.castor.xml.XMLContext;
import org.pathwayeditor.businessobjects.drawingprimitives.IBendPoint;
import org.pathwayeditor.businessobjects.drawingprimitives.ICanvas;
import org.pathwayeditor.businessobjects.drawingprimitives.ILabelAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.ILabelNode;
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkEdge;
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkTerminus;
import org.pathwayeditor.businessobjects.drawingprimitives.IModel;
import org.pathwayeditor.businessobjects.drawingprimitives.IRootAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.IRootNode;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeNode;
import org.pathwayeditor.businessobjects.drawingprimitives.ISubModel;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LineStyle;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LinkEndDecoratorShape;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationProperty;
import org.pathwayeditor.businessobjects.exchange.castor.Background;
import org.pathwayeditor.businessobjects.exchange.castor.BendPoint;
import org.pathwayeditor.businessobjects.exchange.castor.Canvas;
import org.pathwayeditor.businessobjects.exchange.castor.CanvasSize;
import org.pathwayeditor.businessobjects.exchange.castor.ColourType;
import org.pathwayeditor.businessobjects.exchange.castor.DecoratorSize;
import org.pathwayeditor.businessobjects.exchange.castor.DimensionType;
import org.pathwayeditor.businessobjects.exchange.castor.FillColour;
import org.pathwayeditor.businessobjects.exchange.castor.Grid;
import org.pathwayeditor.businessobjects.exchange.castor.GridSize;
import org.pathwayeditor.businessobjects.exchange.castor.LabelAttribute;
import org.pathwayeditor.businessobjects.exchange.castor.LabelNode;
import org.pathwayeditor.businessobjects.exchange.castor.LineColour;
import org.pathwayeditor.businessobjects.exchange.castor.LinkAttribute;
import org.pathwayeditor.businessobjects.exchange.castor.LinkEdge;
import org.pathwayeditor.businessobjects.exchange.castor.LinkTerminusType;
import org.pathwayeditor.businessobjects.exchange.castor.Location;
import org.pathwayeditor.businessobjects.exchange.castor.Model;
import org.pathwayeditor.businessobjects.exchange.castor.Notation;
import org.pathwayeditor.businessobjects.exchange.castor.ObjectType;
import org.pathwayeditor.businessobjects.exchange.castor.PointType;
import org.pathwayeditor.businessobjects.exchange.castor.Position;
import org.pathwayeditor.businessobjects.exchange.castor.PropertyRef;
import org.pathwayeditor.businessobjects.exchange.castor.RootAttribute;
import org.pathwayeditor.businessobjects.exchange.castor.RootNode;
import org.pathwayeditor.businessobjects.exchange.castor.ShapeAttribute;
import org.pathwayeditor.businessobjects.exchange.castor.ShapeNode;
import org.pathwayeditor.businessobjects.exchange.castor.Size;
import org.pathwayeditor.businessobjects.exchange.castor.SrcTerminus;
import org.pathwayeditor.businessobjects.exchange.castor.SubModel;
import org.pathwayeditor.businessobjects.exchange.castor.TgtTerminus;
import org.pathwayeditor.businessobjects.exchange.castor.types.EndDecoratorTypeType;
import org.pathwayeditor.businessobjects.exchange.castor.types.LineStyleType;
import org.pathwayeditor.businessobjects.exchange.castor.types.ObjectTypeClassificationType;
import org.pathwayeditor.businessobjects.notationsubsystem.INotation;
import org.pathwayeditor.businessobjects.typedefn.ILinkObjectType;
import org.pathwayeditor.businessobjects.typedefn.IObjectType;
import org.pathwayeditor.businessobjects.typedefn.IRootObjectType;
import org.pathwayeditor.figure.geometry.Dimension;
import org.pathwayeditor.figure.geometry.Point;

/**
 * @author smoodie
 *
 */
public class CanvasMarshaller {
	private Canvas xmlCanvas;
	private ICanvas dbCanvas;
	private final Map<IAnnotationProperty, PropertyRef> propMap;
	private final AnnotationsBuilder builder;
//	private final Map<ILabelAttribute, LabelNode> skippedLabels;
	private Map<LinkEndDecoratorShape, EndDecoratorTypeType> endDecMapping;
	
	public CanvasMarshaller(){
		this.propMap = new HashMap<IAnnotationProperty,PropertyRef>();
		this.builder = new AnnotationsBuilder();
//		this.skippedLabels = new HashMap<ILabelAttribute, LabelNode>();
		this.endDecMapping = new HashMap<LinkEndDecoratorShape, EndDecoratorTypeType>();
		initEndDecMappings();
	}
	
	private void initEndDecMappings(){
		endDecMapping.put(LinkEndDecoratorShape.ARROW, EndDecoratorTypeType.ARROW);
		endDecMapping.put(LinkEndDecoratorShape.BAR, EndDecoratorTypeType.BAR);
		endDecMapping.put(LinkEndDecoratorShape.DIAMOND, EndDecoratorTypeType.DIAMOND);
		endDecMapping.put(LinkEndDecoratorShape.DOUBLE_BAR, EndDecoratorTypeType.DOUBLE_BAR);
		endDecMapping.put(LinkEndDecoratorShape.DOUBLE_ARROW, EndDecoratorTypeType.DOUBLE_ARROW);
		endDecMapping.put(LinkEndDecoratorShape.EMPTY_CIRCLE, EndDecoratorTypeType.EMPTY_CIRCLE);
		endDecMapping.put(LinkEndDecoratorShape.EMPTY_DIAMOND, EndDecoratorTypeType.EMPTY_DIAMOND);
		endDecMapping.put(LinkEndDecoratorShape.EMPTY_SQUARE, EndDecoratorTypeType.EMPTY_SQUARE);
		endDecMapping.put(LinkEndDecoratorShape.EMPTY_TRIANGLE, EndDecoratorTypeType.EMPTY_TRIANGLE);
		endDecMapping.put(LinkEndDecoratorShape.NONE, EndDecoratorTypeType.NONE);
		endDecMapping.put(LinkEndDecoratorShape.SQUARE, EndDecoratorTypeType.SQUARE);
		endDecMapping.put(LinkEndDecoratorShape.TRIANGLE, EndDecoratorTypeType.TRIANGLE);
		endDecMapping.put(LinkEndDecoratorShape.TRIANGLE_BAR, EndDecoratorTypeType.TRIANGLE_BAR);
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
//			addCanvasAttributes();
			createModel();
			xmlCanvas.setPropertyList(builder.getAnnotations());
//			buildLabels();
		}
		catch(RuntimeException e){
			xmlCanvas = null;
			throw new RuntimeException("A bug was detected during xml schema generation", e);
		}
	}
	
	public void write(Writer writer) throws IOException{
		XMLContext ctx = new XMLContext();
		Marshaller m = ctx.createMarshaller();
		m.setSchemaLocation("http://www.pathwayeditor.org/Exchange http://www.pathwayeditor.org/Exchange/Canvas.xsd");
		m.setNamespaceMapping("epx", "http://www.pathwayeditor.org/Exchange");
		m.setMarshalAsDocument(true);
		m.setValidation(true);
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
		xmlCanvas.setName(dbCanvas.getName());
		xmlCanvas.setLastCreationSerial(dbCanvas.getCreationSerialCounter().getLastIndex());
	}
	
	private void setNotation(){
		Notation notation = new Notation();
		INotation dbNotation = dbCanvas.getNotationSubsystem().getNotation();
		notation.setQualifiedName(dbNotation.getQualifiedName());
		notation.setMajorVersion(dbNotation.getVersion().getMajorVersion());
		notation.setMinorVersion(dbNotation.getVersion().getMinorVersion());
		notation.setPatchVersion(dbNotation.getVersion().getPatchVersion());
		notation.setDisplayName(dbNotation.getDisplayName());
		notation.setDescription(dbNotation.getDescription());
		xmlCanvas.setNotation(notation);
		Iterator<IObjectType> iter = dbCanvas.getNotationSubsystem().getSyntaxService().objectTypeIterator();
		while(iter.hasNext()){
			IObjectType objectType = iter.next();
			ObjectType xmlObjectType = createObjectType(objectType);
			notation.addObjectType(xmlObjectType);
		}
	}
	
//	private void addCanvasAttributes(){
//		addRootAttribute();
//		addShapeAttributes();
//		addLabelAttributes();
//		addLinkAttributes();
//		addLinkTermini();
//	}
	
	private RootAttribute addRootAttribute(IRootAttribute rootAttribute){
		RootAttribute xmlRootAtt = new RootAttribute();
		xmlRootAtt.setObjectTypeId(rootAttribute.getObjectType().getUniqueId());
		xmlRootAtt.setCreationSerial(rootAttribute.getCreationSerial());
		xmlRootAtt.setLocation(createLocation(new Location(), rootAttribute.getLocation()));
		xmlRootAtt.setSize(createSize(new Size(), rootAttribute.getSize()));
		return xmlRootAtt;
	}
	
	private static <T extends DimensionType> T createSize(T xmlSize, Dimension size) {
		xmlSize.setHeight(size.getHeight());
		xmlSize.setWidth(size.getWidth());
		return xmlSize;
	}

	private static ObjectType createObjectType(IObjectType ot){
		ObjectType retVal = new ObjectType();
		retVal.setUniqueId(ot.getUniqueId());
		retVal.setDescription(ot.getDescription());
		retVal.setName(ot.getName());
		ObjectTypeClassificationType classnType = ObjectTypeClassificationType.SHAPE;
		if(ot instanceof ILinkObjectType){
			classnType = ObjectTypeClassificationType.LINK;
		}
		else if(ot instanceof IRootObjectType){
			classnType = ObjectTypeClassificationType.ROOT;
		}
		retVal.setClassification(classnType);
		return retVal;
	}
	
	private PropertyRef[] createAnnotations(Iterator<IAnnotationProperty> propIter){
		List<PropertyRef> retVal = new LinkedList<PropertyRef>();
		while(propIter.hasNext()){
			IAnnotationProperty prop = propIter.next();
			int id = builder.createProperty(prop);
			PropertyRef ref = new PropertyRef();
			ref.setId(id);
			retVal.add(ref);
			this.propMap.put(prop, ref);
		}
		return retVal.toArray(new PropertyRef[0]);
	}
	
	private ShapeAttribute createShapeAttribute(IShapeAttribute attrib) {
		ShapeAttribute xmlAttrib = new ShapeAttribute();
		xmlAttrib.setCreationSerial(attrib.getCreationSerial());
		xmlAttrib.setLocation(createLocation(new Location(), attrib.getLocation()));
		xmlAttrib.setSize(createSize(new Size(), attrib.getSize()));
		xmlAttrib.setObjectTypeId(attrib.getObjectType().getUniqueId());
		xmlAttrib.setFillColour(setColour(new FillColour(), attrib.getFillColour()));
		xmlAttrib.setLineColour(setColour(new LineColour(), attrib.getLineColour()));
		xmlAttrib.setLineWidth(attrib.getLineWidth());
		xmlAttrib.setLineStyle(createLineStyle(attrib.getLineStyle()));
		xmlAttrib.setFigureDefinition(attrib.getShapeDefinition());
		xmlAttrib.setPropertyRef(createAnnotations(attrib.propertyIterator()));
		return xmlAttrib;
	}
	
	private static <C extends ColourType> C setColour(C xmlColour, RGB rgb) {
		xmlColour.setBlue(rgb.getBlue());
		xmlColour.setGreen(rgb.getGreen());
		xmlColour.setRed(rgb.getRed());
		return xmlColour;
	}

	private LabelAttribute createLabelAttribute(ILabelAttribute attrib) {
		LabelAttribute xmlAttrib = new LabelAttribute();
		xmlAttrib.setCreationSerial(attrib.getCreationSerial());
		xmlAttrib.setLocation(createLocation(new Location(), attrib.getLocation()));
		xmlAttrib.setFillColour(setColour(new FillColour(), attrib.getBackgroundColor()));
		xmlAttrib.setLineColour(setColour(new LineColour(), attrib.getForegroundColor()));
		xmlAttrib.setLineStyle(createLineStyle(attrib.getLineStyle()));
		xmlAttrib.setLineWidth(attrib.getLineWidth());
		xmlAttrib.setNoBorder(attrib.hasNoBorder());
		xmlAttrib.setNoFill(attrib.hasNoFill());
		xmlAttrib.setSize(createSize(new Size(), attrib.getSize()));
		xmlAttrib.setLocation(createLocation(new Location(), attrib.getLocation()));
		xmlAttrib.setPropertyRef(this.propMap.get(attrib.getProperty()));
		return xmlAttrib;
	}
	
	private LinkAttribute createLinkAttribute(ILinkAttribute attrib) {
		LinkAttribute xmlAttrib = new LinkAttribute();
		xmlAttrib.setCreationSerial(attrib.getCreationSerial());
		xmlAttrib.setObjectTypeId(attrib.getObjectType().getUniqueId());
		xmlAttrib.setLineStyle(createLineStyle(attrib.getLineStyle()));
		xmlAttrib.setLineWidth(attrib.getLineWidth());
		xmlAttrib.setLineColour(setColour(new LineColour(), attrib.getLineColour()));
		xmlAttrib.setLastBendPointIndex(attrib.getLastBendPointSerial());
		addBendPoints(attrib, xmlAttrib);
		xmlAttrib.setPropertyRef(createAnnotations(attrib.propertyIterator()));
		SrcTerminus srcTerminus = new SrcTerminus();
		addLinkTermini(attrib.getSourceTerminus(), srcTerminus);
		xmlAttrib.setSrcTerminus(srcTerminus);
		TgtTerminus tgtTerminus = new TgtTerminus();
		addLinkTermini(attrib.getTargetTerminus(), tgtTerminus);
		xmlAttrib.setTgtTerminus(tgtTerminus);
		return xmlAttrib;
	}
	
	private void addBendPoints(ILinkAttribute attrib, LinkAttribute xmlAttrib) {
		 Iterator<IBendPoint> iter = attrib.bendPointIterator();
		 while(iter.hasNext()){
			IBendPoint hibBendPoint = iter.next();
			BendPoint xmlBp = new BendPoint();
			xmlBp.setCreationSerial(hibBendPoint.getCreationSerial());
			xmlBp.setIndexPosn(hibBendPoint.getIndexPos());
			xmlBp.setPosition(createLocation(new Position(), hibBendPoint.getLocation()));
			xmlAttrib.addBendPoint(xmlBp);
		}
	}

	private void addLinkTermini(ILinkTerminus attrib, LinkTerminusType xmlAttrib){
		xmlAttrib.setCreationSerial(attrib.getCreationSerial());
		xmlAttrib.setPropertyRef(createAnnotations(attrib.propertyIterator()));
		xmlAttrib.setLocation(createLocation(new Location(), attrib.getLocation()));
		xmlAttrib.setDecoratorSize(createSize(new DecoratorSize(), attrib.getEndSize()));
		xmlAttrib.setGap(attrib.getGap());
		xmlAttrib.setEndDecoratorType(this.endDecMapping.get(attrib.getEndDecoratorType()));
	}
	
	private LineStyleType createLineStyle(LineStyle xmlLineStyle){
		LineStyleType retVal = LineStyleType.SOLID;
		if(xmlLineStyle.equals(LineStyle.DOT)){
			retVal = LineStyleType.DOT;
		}
		else if(xmlLineStyle.equals(LineStyle.DASHED)){
			retVal = LineStyleType.DASHED;
		} 
		else if(xmlLineStyle.equals(LineStyle.DASH_DOT)){
			retVal = LineStyleType.DASH_DOT;
		} 
		else if(xmlLineStyle.equals(LineStyle.DASH_DOT_DOT)){
			retVal = LineStyleType.DASH_DOT_DOT;
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
		model.setLastEdgeIndex(dbModel.getEdgeCounter().getLastIndex());
		model.setLastNodeIndex(dbModel.getNodeCounter().getLastIndex());
		xmlCanvas.setModel(model);
		IRootNode dbRootNode = dbModel.getRootNode();
		RootNode rootNode = createRootNode(dbRootNode); 
		model.setRootNode(rootNode);
		SubModel submodel = new SubModel();
		submodel.addRootNode(rootNode);
		rootNode.setSubModel(submodel);
		addSubmodel(submodel, dbRootNode.getSubModel());
		this.xmlCanvas.setModel(model);
	}
	
	private RootNode createRootNode(IRootNode dbRootNode){
		RootNode rootNode = new RootNode();
		rootNode.setNodeId(dbRootNode.getIndex());
		RootAttribute rootAttribute = addRootAttribute(dbRootNode.getAttribute());
		rootNode.setRootAttribute(rootAttribute);
		SubModel childModel = new SubModel();
		rootNode.setSubModel(childModel);
		return rootNode;
	}
	
	private void addSubmodel(SubModel xmlSubmodel, ISubModel submodel){
		Iterator<IShapeNode> shapeIter = submodel.shapeNodeIterator();
		while(shapeIter.hasNext()){
			IShapeNode node = shapeIter.next();
			ShapeNode xmlNode = createShapeNode(node);
			xmlSubmodel.addShapeNode(xmlNode);
			addSubmodel(xmlNode.getSubModel(), node.getSubModel());
		}
		Iterator<ILinkEdge> linkIter = submodel.linkIterator();
		while(linkIter.hasNext()){
			ILinkEdge edge = linkIter.next();
			LinkEdge xmlNode = createLinkEdge(edge);
			xmlSubmodel.addLinkEdge(xmlNode);
		}
		Iterator<ILabelNode> labelIter = submodel.labelIterator();
		while(labelIter.hasNext()){
			ILabelNode node = labelIter.next();
			LabelNode xmlNode = createLabelNode(node);
			xmlSubmodel.addLabelNode(xmlNode);
		}
	}
	
//	private void addShapeSubmodel(SubModel xmlSubmodel, IShapeNode shapeNode){
//	}
	
//	private void addLabelSubmodel(SubModel xmlSubmodel, ISubModel submodel){
//		Iterator<ILabelNode> iter = submodel.labelIterator();
//		while(iter.hasNext()){
//			ILabelNode node = iter.next();
//			LabelNode xmlNode = createLabelNode(node);
//			xmlSubmodel.addLabelNode(xmlNode);
//			addSubmodel(xmlNode.getSubModel(), node.getSubModel());
//		}
//	}
	
//	private void addLinkSubmodel(SubModel xmlSubmodel, ILinkEdge node){
//	}
	
	private LinkEdge createLinkEdge(ILinkEdge linkEdge){
		LinkEdge xmlLinkEdge = new LinkEdge();
		xmlLinkEdge.setLinkIdx(linkEdge.getIndex());
		xmlLinkEdge.setSrcNodeIdx(linkEdge.getSourceShape().getIndex());
		xmlLinkEdge.setTgtNodeIdx(linkEdge.getTargetShape().getIndex());
		xmlLinkEdge.setLinkAttribute(createLinkAttribute(linkEdge.getAttribute()));
		return xmlLinkEdge;
	}
	
//	private void buildLabels(){
//		for(ILabelAttribute labelAttribute : this.skippedLabels.keySet()){
//			LabelAttribute xmlLabelAtt = createLabelAttribute(labelAttribute);
//			this.skippedLabels.get(labelAttribute).setLabelAttribute(xmlLabelAtt);
//		}
//	}
	
	private LabelNode createLabelNode(ILabelNode labelNode){
		LabelNode xmlNode = new LabelNode();
		SubModel childModel = new SubModel();
		xmlNode.setSubModel(childModel);
		xmlNode.setNodeId(labelNode.getIndex());
//		this.skippedLabels.put(labelNode.getAttribute(), xmlNode);
		LabelAttribute labelAttribute = createLabelAttribute(labelNode.getAttribute());
		xmlNode.setLabelAttribute(labelAttribute);
		return xmlNode;
	}
	
	private ShapeNode createShapeNode(IShapeNode shapeNode){
		ShapeNode xmlNode = new ShapeNode();
		SubModel childModel = new SubModel();
		xmlNode.setSubModel(childModel);
		xmlNode.setNodeId(shapeNode.getIndex());
		ShapeAttribute attribute = createShapeAttribute(shapeNode.getAttribute());
		xmlNode.setShapeAttribute(attribute);
		return xmlNode;
	}
}
