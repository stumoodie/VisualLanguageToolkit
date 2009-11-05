/*
Copyright 2009, Court of the University of Edinburgh

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License. 
*/

package org.pathwayeditor.businessobjects.exchange;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import org.pathwayeditor.businessobjects.drawingprimitives.ICanvas;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LineStyle;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LinkEndDecoratorShape;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LinkTermType;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationPropertyVisitor;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IBooleanAnnotationProperty;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IIntegerAnnotationProperty;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IListAnnotationProperty;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.INumberAnnotationProperty;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPlainTextAnnotationProperty;
import org.pathwayeditor.businessobjects.exchange.castor.BendPoint;
import org.pathwayeditor.businessobjects.exchange.castor.BooleanAnnotationProperty;
import org.pathwayeditor.businessobjects.exchange.castor.Canvas;
import org.pathwayeditor.businessobjects.exchange.castor.ColourType;
import org.pathwayeditor.businessobjects.exchange.castor.DimensionType;
import org.pathwayeditor.businessobjects.exchange.castor.IntegerAnnotationProperty;
import org.pathwayeditor.businessobjects.exchange.castor.LabelAttribute;
import org.pathwayeditor.businessobjects.exchange.castor.LabelNode;
import org.pathwayeditor.businessobjects.exchange.castor.LinkAttribute;
import org.pathwayeditor.businessobjects.exchange.castor.LinkEdge;
import org.pathwayeditor.businessobjects.exchange.castor.LinkTerminusType;
import org.pathwayeditor.businessobjects.exchange.castor.ListAnnotationProperty;
import org.pathwayeditor.businessobjects.exchange.castor.ListItem;
import org.pathwayeditor.businessobjects.exchange.castor.Model;
import org.pathwayeditor.businessobjects.exchange.castor.NumberAnnotationProperty;
import org.pathwayeditor.businessobjects.exchange.castor.PointType;
import org.pathwayeditor.businessobjects.exchange.castor.PropertyList;
import org.pathwayeditor.businessobjects.exchange.castor.PropertyRef;
import org.pathwayeditor.businessobjects.exchange.castor.PropertyType;
import org.pathwayeditor.businessobjects.exchange.castor.RootAttribute;
import org.pathwayeditor.businessobjects.exchange.castor.RootNode;
import org.pathwayeditor.businessobjects.exchange.castor.ShapeAttribute;
import org.pathwayeditor.businessobjects.exchange.castor.ShapeNode;
import org.pathwayeditor.businessobjects.exchange.castor.SubModel;
import org.pathwayeditor.businessobjects.exchange.castor.TextAnnotationProperty;
import org.pathwayeditor.businessobjects.exchange.castor.types.EndDecoratorTypeType;
import org.pathwayeditor.businessobjects.exchange.castor.types.LineStyleType;
import org.pathwayeditor.businessobjects.hibernate.helpers.IHibNotationFactory;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibAnnotatedCanvasAttribute;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibBendPoint;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibCanvas;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibLabelAttribute;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibLabelNode;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibLinkAttribute;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibLinkEdge;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibLinkTerminus;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibModel;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibProperty;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibRootAttribute;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibRootNode;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibShapeAttribute;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibShapeNode;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibSubModel;
import org.pathwayeditor.businessobjects.management.INotationSubsystemPool;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationSubsystem;
import org.pathwayeditor.businessobjects.typedefn.ILabelAttributeDefaults;
import org.pathwayeditor.businessobjects.typedefn.ILinkObjectType;
import org.pathwayeditor.businessobjects.typedefn.ILinkTerminusDefinition;
import org.pathwayeditor.businessobjects.typedefn.IRootObjectType;
import org.pathwayeditor.businessobjects.typedefn.IShapeObjectType;
import org.pathwayeditor.figure.geometry.Dimension;
import org.pathwayeditor.figure.geometry.Envelope;
import org.pathwayeditor.figure.geometry.Point;

import uk.ed.inf.graph.util.IndexCounter;

/**
 * @author smoodie
 *
 */
public class CanvasBuilder {
	private HibCanvas hibCanvas = null;
	private final Canvas xmlInstance;
	private final INotationSubsystemPool notationPool;
	private final int iNode;
	private final String repoName;
	private IHibNotationFactory notationFactory;
	private INotationSubsystem notationSubsystem;
	private final Map<Integer, HibShapeNode> hibNodeMap;
	private final Map<Integer, PropertyType> xmlPropMap;
	private final Map<Integer, HibProperty> hibPropMap;
	private final Map<EndDecoratorTypeType, LinkEndDecoratorShape> endDecMapping;
	
	public CanvasBuilder(String repoName, int iNode, Canvas xmlInstance, INotationSubsystemPool notationPool){
		this.xmlInstance = xmlInstance;
		this.notationPool = notationPool;
		this.repoName = repoName;
		this.iNode = iNode;
		this.hibNodeMap = new HashMap<Integer, HibShapeNode>();
		this.xmlPropMap = new HashMap<Integer, PropertyType>();
		this.hibPropMap = new HashMap<Integer, HibProperty>();
		this.endDecMapping = new HashMap<EndDecoratorTypeType, LinkEndDecoratorShape>();
		initEndDecMappings();
	}
	
	private void initEndDecMappings(){
		endDecMapping.put(EndDecoratorTypeType.ARROW, LinkEndDecoratorShape.ARROW);
		endDecMapping.put(EndDecoratorTypeType.BAR, LinkEndDecoratorShape.BAR);
		endDecMapping.put(EndDecoratorTypeType.DIAMOND, LinkEndDecoratorShape.DIAMOND);
		endDecMapping.put(EndDecoratorTypeType.DOUBLE_BAR, LinkEndDecoratorShape.DOUBLE_BAR);
		endDecMapping.put(EndDecoratorTypeType.DOUBLE_ARROW, LinkEndDecoratorShape.DOUBLE_ARROW);
		endDecMapping.put(EndDecoratorTypeType.EMPTY_CIRCLE, LinkEndDecoratorShape.EMPTY_CIRCLE);
		endDecMapping.put(EndDecoratorTypeType.EMPTY_DIAMOND, LinkEndDecoratorShape.EMPTY_DIAMOND);
		endDecMapping.put(EndDecoratorTypeType.EMPTY_SQUARE, LinkEndDecoratorShape.EMPTY_SQUARE);
		endDecMapping.put(EndDecoratorTypeType.EMPTY_TRIANGLE, LinkEndDecoratorShape.EMPTY_TRIANGLE);
		endDecMapping.put(EndDecoratorTypeType.NONE, LinkEndDecoratorShape.NONE);
		endDecMapping.put(EndDecoratorTypeType.SQUARE, LinkEndDecoratorShape.SQUARE);
		endDecMapping.put(EndDecoratorTypeType.TRIANGLE, LinkEndDecoratorShape.TRIANGLE);
		endDecMapping.put(EndDecoratorTypeType.TRIANGLE_BAR, LinkEndDecoratorShape.TRIANGLE_BAR);
	}
	
	public void buildCanvas(){
		this.notationSubsystem = notationPool.getSubsystem(notationFactory.getNotation());
		this.hibCanvas = new HibCanvas(repoName, iNode, this.notationFactory, notationSubsystem, xmlInstance.getName());
		this.hibCanvas.setCreationSerialCounter(new IndexCounter(this.xmlInstance.getLastCreationSerial()));
		ICanvas iCanvas = hibCanvas;
		iCanvas.setBackgroundColour(createColour(this.xmlInstance.getBackground()));
		iCanvas.setCanvasSize(createDimension(this.xmlInstance.getCanvasSize()));
		iCanvas.setGridSize(createDimension(this.xmlInstance.getGrid().getGridSize()));
		iCanvas.setGridEnabled(this.xmlInstance.getGrid().isGridOn());
		iCanvas.setSnapToGrid(xmlInstance.getGrid().isSnapToGrid());
	}
	
	public void buildNotation(){
		notationFactory = new XmlNotationFactory(xmlInstance.getNotation());
		notationFactory.initialise();
	}
	
	private void buildLinkAttributes(HibLinkAttribute linkAttrib, LinkAttribute xmlLinkAtt) {
		linkAttrib.setLineColour(createColour(xmlLinkAtt.getLineColour()));
		linkAttrib.setLineWidth(xmlLinkAtt.getLineWidth());
		linkAttrib.setLineStyle(createLineStyle(xmlLinkAtt.getLineStyle()));
		linkAttrib.setLastBendPointSerial(xmlLinkAtt.getLastBendPointIndex());
		SortedSet<BendPoint> sortedBps = new TreeSet<BendPoint>(new Comparator<BendPoint>(){

			public int compare(BendPoint o1, BendPoint o2) {
				return o1.getIndexPosn() < o2.getIndexPosn() ? -1 : (o1.getIndexPosn() > o2.getIndexPosn() ? 1 : 0);
			}
		});
		sortedBps.addAll(Arrays.asList(xmlLinkAtt.getBendPoint()));
		for(BendPoint xmlBendPoint : sortedBps){
			HibBendPoint hibBp = new HibBendPoint(linkAttrib, xmlBendPoint.getCreationSerial(), createPoint(xmlBendPoint.getPosition()));
			hibBp.setIndexPos(xmlBendPoint.getIndexPosn());
			linkAttrib.getBendPoints().add(hibBp);
		}
		buildAnnotationProperties(linkAttrib, xmlLinkAtt.getPropertyRef());
	}
	
	
	private HibLinkTerminus createLinkTerminus(LinkTerminusType xmlTerm, LinkTermType termType, ILinkTerminusDefinition defn){
		HibLinkTerminus retVal = new HibLinkTerminus(this.hibCanvas, xmlTerm.getCreationSerial(), termType, defn);
		retVal.setEndSize(createDimension(xmlTerm.getDecoratorSize()));
		retVal.setLocation(createPoint(xmlTerm.getLocation()));
		retVal.setGap(xmlTerm.getGap());
		retVal.setEndDecoratorType(this.endDecMapping.get(xmlTerm.getEndDecoratorType()));
		buildAnnotationProperties(retVal, xmlTerm.getPropertyRef());
		return retVal;
	}

	private void buildShapeAttributes(HibShapeAttribute shapeAttrib, ShapeAttribute xmlShapeAtt) {
		Envelope bounds = new Envelope(xmlShapeAtt.getLocation().getX(), xmlShapeAtt.getLocation()
				.getY(), xmlShapeAtt.getSize().getWidth(), xmlShapeAtt.getSize().getHeight());
		shapeAttrib.setBounds(bounds);
		shapeAttrib.setFillColour(createColour(xmlShapeAtt.getFillColour()));
		shapeAttrib.setLineColour(createColour(xmlShapeAtt.getLineColour()));
		shapeAttrib.setSize(createDimension(xmlShapeAtt.getSize()));
		shapeAttrib.setLocation(createPoint(xmlShapeAtt.getLocation()));
		shapeAttrib.setLineWidth(xmlShapeAtt.getLineWidth());
		shapeAttrib.setLineStyle(createLineStyle(xmlShapeAtt.getLineStyle()));
		buildAnnotationProperties(shapeAttrib, xmlShapeAtt.getPropertyRef());
	}
	
	
	private void buildAnnotationProperties(HibAnnotatedCanvasAttribute shapeAttrib, PropertyRef propIds[]){
		for(PropertyRef propId : propIds){
			final PropertyType xmlProp = this.xmlPropMap.get(propId.getId());
			HibProperty hibProp = shapeAttrib.getProperty(xmlProp.getName());
			hibProp.visit(new IAnnotationPropertyVisitor(){

				public void visitBooleanAnnotationProperty(IBooleanAnnotationProperty prop) {
					BooleanAnnotationProperty xmlBoolProp = (BooleanAnnotationProperty)xmlProp;
					prop.setValue(xmlBoolProp.getValue());
				}

				public void visitIntegerAnnotationProperty(IIntegerAnnotationProperty prop) {
					IntegerAnnotationProperty castXmlProp = (IntegerAnnotationProperty)xmlProp;
					prop.setValue(castXmlProp.getValue());
				}

				public void visitListAnnotationProperty(IListAnnotationProperty prop) {
					ListAnnotationProperty castXmlProp = (ListAnnotationProperty)xmlProp;
					SortedSet<ListItem> sortedList = new TreeSet<ListItem>(new Comparator<ListItem>(){

						public int compare(ListItem arg0, ListItem arg1) {
							return arg0.getIdx() < arg1.getIdx() ? -1 : (arg0.getIdx() > arg1.getIdx() ? 1 : 0);
						}
						
					});
					sortedList.addAll(Arrays.asList(castXmlProp.getListItem()));
					for(ListItem item : sortedList){
						prop.addValue(item.getValue());
					}
				}

				public void visitNumberAnnotationProperty(INumberAnnotationProperty prop) {
					NumberAnnotationProperty castXmlProp = (NumberAnnotationProperty)xmlProp;
					prop.setValue(castXmlProp.getValue());
				}

				public void visitPlainTextAnnotationProperty(IPlainTextAnnotationProperty prop) {
					TextAnnotationProperty castXmlProp = (TextAnnotationProperty)xmlProp;
					prop.setValue(castXmlProp.getContent());
				}
				
			});
			hibPropMap.put(xmlProp.getId(), hibProp);
		}
	}

	private void buildRootAttribute(HibRootAttribute rootAttrib, RootAttribute xmlRootAtt){
		Envelope bounds = new Envelope(xmlRootAtt.getLocation().getX(), xmlRootAtt.getLocation().getY(),
				xmlRootAtt.getSize().getWidth(), xmlRootAtt.getSize().getHeight());
		rootAttrib.setBounds(bounds);
	}
	
	public void buildModel(){
		storeAnnotationProperties();
		IRootObjectType rootObjectType = this.notationSubsystem.getSyntaxService().getRootObjectType();
		HibModel model = new HibModel(this.hibCanvas, rootObjectType, this.notationFactory);
		HibRootNode hibRootNode = model.getRootNode();
		HibRootAttribute hibRootAttribute = hibRootNode.getAttribute();
		this.hibCanvas.setGraph(model);
		Model xmlModel = this.xmlInstance.getModel();
		model.setEdgeCounter(new IndexCounter(xmlModel.getLastEdgeIndex()));
		model.setNodeCounter(new IndexCounter(xmlModel.getLastNodeIndex()));
		RootNode xmlRootNode = xmlModel.getRootNode();
		RootAttribute xmlRootAttribute = xmlRootNode.getRootAttribute();
		buildRootAttribute(hibRootAttribute, xmlRootAttribute);
		buildChildShapeNodes(hibRootNode.getSubModel(), xmlRootNode.getSubModel());
//		buildChildLinkEdges(hibRootNode.getSubModel(), xmlRootNode.getSubModel());
//		buildChildLabelNodes(hibRootNode.getSubModel(), xmlRootNode.getSubModel());
	}
	
	
	private void storeAnnotationProperties() {
		PropertyList list = this.xmlInstance.getPropertyList();
		for(BooleanAnnotationProperty xmlProp : list.getBooleanAnnotationProperty()){
			this.xmlPropMap.put(xmlProp.getId(), xmlProp);
		}
		for(IntegerAnnotationProperty xmlProp : list.getIntegerAnnotationProperty()){
			this.xmlPropMap.put(xmlProp.getId(), xmlProp);
		}
		for(NumberAnnotationProperty xmlProp : list.getNumberAnnotationProperty()){
			this.xmlPropMap.put(xmlProp.getId(), xmlProp);
		}
		for(ListAnnotationProperty xmlProp : list.getListAnnotationProperty()){
			this.xmlPropMap.put(xmlProp.getId(), xmlProp);
		}
		for(TextAnnotationProperty xmlProp : list.getTextAnnotationProperty()){
			this.xmlPropMap.put(xmlProp.getId(), xmlProp);
		}
	}

	private void buildChildShapeNodes(HibSubModel owningSubModel, SubModel xmlSubModel){
		for(ShapeNode xmlNode : xmlSubModel.getShapeNode()){
			HibShapeNode hibShapeNode = createShapeNode(owningSubModel, xmlNode);
			buildChildShapeNodes(hibShapeNode.getSubModel(), xmlNode.getSubModel());
		}
		for(LinkEdge xmlEdge : xmlSubModel.getLinkEdge()){
			createLinkEdge(owningSubModel, xmlEdge);
		}
		for(LabelNode xmlNode : xmlSubModel.getLabelNode()){
			createLabelNode(owningSubModel, xmlNode);
		}
	}
	
	private HibShapeNode createShapeNode(HibSubModel owningSubModel, ShapeNode xmlNode){
		int otId = xmlNode.getShapeAttribute().getObjectTypeId();
		IShapeObjectType objectType = this.notationSubsystem.getSyntaxService().getShapeObjectType(otId);
		HibShapeAttribute attribute = new HibShapeAttribute(this.hibCanvas, xmlNode.getShapeAttribute().getCreationSerial(),
				objectType, this.notationFactory.getObjectType(otId));
		buildShapeAttributes(attribute, xmlNode.getShapeAttribute());
		HibShapeNode node = new HibShapeNode(owningSubModel.getRootNode(), xmlNode.getNodeId(), attribute);
		this.hibNodeMap.put(xmlNode.getNodeId(), node);
		return node;
	}
	
//	private void buildChildLinkEdges(HibSubModel parentNode, SubModel xmlSubModel) {
//		for(LinkEdge xmlEdge : xmlSubModel.getLinkEdge()){
//			createLinkEdge(parentNode, xmlEdge);
//		}
//		for(ShapeNode xmlNode : xmlSubModel.getShapeNode()){
//			buildChildLinkEdges(this.hibNodeMap.get(xmlNode.getNodeId()).getSubModel(), xmlNode.getSubModel());
//		}
//	}

	private HibLinkEdge createLinkEdge(HibSubModel owningSubModel, LinkEdge xmlNode){
		int otId = xmlNode.getLinkAttribute().getObjectTypeId();
		ILinkObjectType objectType = this.notationSubsystem.getSyntaxService().getLinkObjectType(otId);
		HibLinkTerminus srcTerm = createLinkTerminus(xmlNode.getLinkAttribute().getSrcTerminus(), LinkTermType.SOURCE, objectType.getSourceTerminusDefinition()); 
		HibLinkTerminus tgtTerm = createLinkTerminus(xmlNode.getLinkAttribute().getTgtTerminus(), LinkTermType.TARGET, objectType.getTargetTerminusDefinition()); 
		HibLinkAttribute attribute = new HibLinkAttribute(this.hibCanvas, xmlNode.getLinkAttribute().getCreationSerial(),
				objectType, this.notationFactory.getObjectType(otId), srcTerm, tgtTerm);
		buildLinkAttributes(attribute, xmlNode.getLinkAttribute());
		HibShapeNode srcNode = this.hibNodeMap.get(xmlNode.getSrcNodeIdx());
		HibShapeNode tgtNode = this.hibNodeMap.get(xmlNode.getTgtNodeIdx());
		HibLinkEdge node = new HibLinkEdge(owningSubModel, xmlNode.getLinkIdx(), srcNode, tgtNode, attribute);
		return node;
	}
	
//	private void buildChildLabelNodes(HibSubModel parentNode, SubModel xmlSubModel) {
//		for(LabelNode xmlEdge : xmlSubModel.getLabelNode()){
//			createLabelNode(parentNode, xmlEdge);
//		}
//		for(ShapeNode xmlNode : xmlSubModel.getShapeNode()){
//			buildChildLabelNodes(this.hibNodeMap.get(xmlNode.getNodeId()).getSubModel(), xmlNode.getSubModel());
//		}
//	}

	private HibLabelNode createLabelNode(HibSubModel owningSubModel, LabelNode xmlNode) {
		HibProperty refProp = this.hibPropMap.get(xmlNode.getLabelAttribute().getPropertyRef().getId());
		ILabelAttributeDefaults labelDefaults = refProp.getDefinition().getLabelDefaults();
		HibLabelAttribute attribute = new HibLabelAttribute(this.hibCanvas, xmlNode.getLabelAttribute().getCreationSerial(), refProp, labelDefaults);
		buildLabelAttributes(attribute, xmlNode.getLabelAttribute());
		HibLabelNode node = new HibLabelNode(owningSubModel.getRootNode(), xmlNode.getNodeId(), attribute);
		return node;
	}

	private void buildLabelAttributes(HibLabelAttribute attribute, LabelAttribute xmlLabelAttribute) {
		attribute.setBackgroundColor(createColour(xmlLabelAttribute.getFillColour()));
		attribute.setForegroundColor(createColour(xmlLabelAttribute.getLineColour()));
		attribute.setLocation(createPoint(xmlLabelAttribute.getLocation()));
		attribute.setSize(createDimension(xmlLabelAttribute.getSize()));
		attribute.setLineStyle(createLineStyle(xmlLabelAttribute.getLineStyle()));
		attribute.setNoBorder(xmlLabelAttribute.isNoBorder());
		attribute.setNoFill(xmlLabelAttribute.isNoFill());
	}

	private static Dimension createDimension(DimensionType xmlSize) {
		return new Dimension(xmlSize.getWidth(), xmlSize.getHeight());
	}


	private static Point createPoint(PointType xmlPoint) {
		return new Point(xmlPoint.getX(), xmlPoint.getY());
	}

	private static RGB createColour(ColourType xmlColour) {
		int blue = xmlColour.getBlue();
		int green = xmlColour.getGreen();
		int red = xmlColour.getRed();
		return new RGB(red, green, blue);
	}

	public ICanvas getCanvas() {
		return this.hibCanvas;
	}
	
	private LineStyle createLineStyle(LineStyleType xmlLineStyle){
		LineStyle retVal = LineStyle.SOLID;
		if(xmlLineStyle.equals(LineStyleType.DOT)){
			retVal = LineStyle.DOT;
		}
		else if(xmlLineStyle.equals(LineStyleType.DASHED)){
			retVal = LineStyle.DASHED;
		} 
		else if(xmlLineStyle.equals(LineStyleType.DASH_DOT)){
			retVal = LineStyle.DASH_DOT;
		} 
		else if(xmlLineStyle.equals(LineStyleType.DASH_DOT_DOT)){
			retVal = LineStyle.DASH_DOT_DOT;
		} 
		return retVal;
	}
	
}
