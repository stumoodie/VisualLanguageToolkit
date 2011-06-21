/*
  Licensed to the Court of the University of Edinburgh (UofE) under one
  or more contributor license agreements.  See the NOTICE file
  distributed with this work for additional information
  regarding copyright ownership.  The UofE licenses this file
  to you under the Apache License, Version 2.0 (the
  "License"); you may not use this file except in compliance
  with the License.  You may obtain a copy of the License at

  http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing,
  software distributed under the License is distributed on an
  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
  KIND, either express or implied.  See the License for the
  specific language governing permissions and limitations
  under the License.
*/


package org.pathwayeditor.businessobjects.exchange;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import org.apache.log4j.Logger;
import org.pathwayeditor.businessobjects.drawingprimitives.IBendPointContainer;
import org.pathwayeditor.businessobjects.drawingprimitives.ICanvas;
import org.pathwayeditor.businessobjects.drawingprimitives.ILabelAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.ILabelAttributeFactory;
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkAttributeFactory;
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkTerminus;
import org.pathwayeditor.businessobjects.drawingprimitives.IModel;
import org.pathwayeditor.businessobjects.drawingprimitives.IRootAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeAttributeFactory;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.Colour;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LineStyle;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LinkEndDecoratorShape;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotatedObject;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationProperty;
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
import org.pathwayeditor.businessobjects.management.IModelFactory;
import org.pathwayeditor.businessobjects.management.INotationSubsystemPool;
import org.pathwayeditor.businessobjects.notationsubsystem.INotation;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationSubsystem;
import org.pathwayeditor.businessobjects.typedefn.ILabelObjectType;
import org.pathwayeditor.businessobjects.typedefn.ILinkObjectType;
import org.pathwayeditor.businessobjects.typedefn.IShapeObjectType;
import org.pathwayeditor.figure.geometry.Dimension;
import org.pathwayeditor.figure.geometry.Envelope;
import org.pathwayeditor.figure.geometry.Point;

import uk.ac.ed.inf.graph.compound.CompoundNodePair;
import uk.ac.ed.inf.graph.compound.IChildCompoundGraph;
import uk.ac.ed.inf.graph.compound.ICompoundEdge;
import uk.ac.ed.inf.graph.compound.ICompoundEdgeFactory;
import uk.ac.ed.inf.graph.compound.ICompoundGraphElement;
import uk.ac.ed.inf.graph.compound.ICompoundNode;
import uk.ac.ed.inf.graph.compound.ICompoundNodeFactory;

/**
 * @author Stuart Moodie
 *
 */
public class ModelBuilder {
	private final Logger logger = Logger.getLogger(this.getClass());
	private IModel graph = null;
	private final Canvas xmlInstance;
	private final INotationSubsystemPool notationPool;
	private INotationSubsystem notationSubsystem;
	private final Map<Integer, ICompoundNode> hibNodeMap;
	private final Map<Integer, PropertyType> xmlPropMap;
	private final Map<Integer, IAnnotationProperty> hibPropMap;
	private final Map<EndDecoratorTypeType, LinkEndDecoratorShape> endDecMapping;
	private INotation notation;
	private IModelFactory modelFactory;
	
	public ModelBuilder(Canvas xmlInstance, INotationSubsystemPool notationPool, IModelFactory modelFactory){
		this.xmlInstance = xmlInstance;
		this.notationPool = notationPool;
		this.modelFactory = modelFactory;
		this.hibNodeMap = new HashMap<Integer, ICompoundNode>();
		this.xmlPropMap = new HashMap<Integer, PropertyType>();
		this.hibPropMap = new HashMap<Integer, IAnnotationProperty>();
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
		this.notationSubsystem = notationPool.getSubsystem(this.notation);
		Model xmlModel = this.xmlInstance.getModel();
		RootNode xmlRootNode = xmlModel.getRootNode();
		RootAttribute xmlRootAttribute = xmlRootNode.getRootAttribute();
		int serialIdx = xmlRootAttribute.getCreationSerial();
		int lastSerialIdx = xmlInstance.getLastCreationSerial();
//		org.pathwayeditor.businessobjects.impl.RootAttribute rootAtt = new org.pathwayeditor.businessobjects.impl.RootAttribute(serialIdx, xmlInstance.getName(), notationSubsystem.getSyntaxService().getRootObjectType(), lastSerialIdx); 
//		this.graph = new CompoundGraph(rootAtt);
		this.modelFactory.setRootCreationSerial(serialIdx);
		this.modelFactory.setLastCreationSerial(lastSerialIdx);
		this.modelFactory.setName(xmlInstance.getName());
		this.modelFactory.setNotationSubsystem(notationSubsystem);
		this.graph = this.modelFactory.createModel();
		ICanvas iCanvas = this.graph.getRootAttribute();
		iCanvas.setBackgroundColour(createColour(this.xmlInstance.getBackground()));
		if(logger.isDebugEnabled()){
			logger.debug("Created model: " + graph + " with root=" + iCanvas);
		}
	}
	
	public void buildNotation(){
		this.notation = new XmlNotation(xmlInstance.getNotation());
	}
	
	private void buildLinkAttributes(ILinkAttribute linkAttrib, LinkAttribute xmlLinkAtt) {
		linkAttrib.setLineColour(createColour(xmlLinkAtt.getLineColour()));
		linkAttrib.setLineWidth(xmlLinkAtt.getLineWidth());
		linkAttrib.setLineStyle(createLineStyle(xmlLinkAtt.getLineStyle()));
		SortedSet<BendPoint> sortedBps = new TreeSet<BendPoint>(new Comparator<BendPoint>(){

			@Override
			public int compare(BendPoint o1, BendPoint o2) {
				return o1.getIndexPosn() < o2.getIndexPosn() ? -1 : (o1.getIndexPosn() > o2.getIndexPosn() ? 1 : 0);
			}
		});
		sortedBps.addAll(Arrays.asList(xmlLinkAtt.getBendPoint()));
		IBendPointContainer bpContainer = linkAttrib.getBendPointContainer();
		for(BendPoint xmlBendPoint : sortedBps){
			Point bp = createPoint(xmlBendPoint.getPosition());
			bpContainer.createNewBendPoint(bp);
		}
		buildAnnotationProperties(linkAttrib, xmlLinkAtt.getPropertyRef());
	}
	
	
	private ILinkTerminus defineLinkTerminus(LinkTerminusType xmlTerm, ILinkTerminus retVal){
		retVal.setEndSize(createDimension(xmlTerm.getDecoratorSize()));
		retVal.setLocation(createPoint(xmlTerm.getLocation()));
		retVal.setGap(xmlTerm.getGap());
		retVal.setEndDecoratorType(this.endDecMapping.get(xmlTerm.getEndDecoratorType()));
		return retVal;
	}

	private void defineShapeAttributes(IShapeAttribute shapeAttrib, ShapeAttribute xmlShapeAtt) {
		Envelope bounds = new Envelope(createPoint(xmlShapeAtt.getLocation()), createDimension(xmlShapeAtt.getSize()));
		shapeAttrib.setBounds(bounds);
		shapeAttrib.setFillColour(createColour(xmlShapeAtt.getFillColour()));
		shapeAttrib.setLineColour(createColour(xmlShapeAtt.getLineColour()));
		shapeAttrib.setLineWidth(xmlShapeAtt.getLineWidth());
		shapeAttrib.setLineStyle(createLineStyle(xmlShapeAtt.getLineStyle()));
		buildAnnotationProperties(shapeAttrib, xmlShapeAtt.getPropertyRef());
	}
	
	
	private void buildAnnotationProperties(IAnnotatedObject shapeAttrib, PropertyRef propIds[]){
		for(PropertyRef propId : propIds){
			final PropertyType xmlProp = this.xmlPropMap.get(propId.getId());
			IAnnotationProperty hibProp = shapeAttrib.getProperty(xmlProp.getName());
			hibProp.visit(new IAnnotationPropertyVisitor(){

				@Override
				public void visitBooleanAnnotationProperty(IBooleanAnnotationProperty prop) {
					BooleanAnnotationProperty xmlBoolProp = (BooleanAnnotationProperty)xmlProp;
					prop.setValue(xmlBoolProp.getValue());
				}

				@Override
				public void visitIntegerAnnotationProperty(IIntegerAnnotationProperty prop) {
					IntegerAnnotationProperty castXmlProp = (IntegerAnnotationProperty)xmlProp;
					prop.setValue(castXmlProp.getValue());
				}

				@Override
				public void visitListAnnotationProperty(IListAnnotationProperty prop) {
					ListAnnotationProperty castXmlProp = (ListAnnotationProperty)xmlProp;
					SortedSet<ListItem> sortedList = new TreeSet<ListItem>(new Comparator<ListItem>(){
						@Override
						public int compare(ListItem arg0, ListItem arg1) {
							return arg0.getIdx() < arg1.getIdx() ? -1 : (arg0.getIdx() > arg1.getIdx() ? 1 : 0);
						}
					});
					sortedList.addAll(Arrays.asList(castXmlProp.getListItem()));
					for(ListItem item : sortedList){
						prop.addValue(item.getValue());
					}
				}

				@Override
				public void visitNumberAnnotationProperty(INumberAnnotationProperty prop) {
					NumberAnnotationProperty castXmlProp = (NumberAnnotationProperty)xmlProp;
					prop.setValue(new BigDecimal(castXmlProp.getValue()));
				}

				@Override
				public void visitPlainTextAnnotationProperty(IPlainTextAnnotationProperty prop) {
					TextAnnotationProperty castXmlProp = (TextAnnotationProperty)xmlProp;
					prop.setValue(castXmlProp.getContent());
				}
				
			});
			hibPropMap.put(xmlProp.getId(), hibProp);
		}
	}

	private void buildRootAttribute(IRootAttribute rootAttrib, RootAttribute xmlRootAtt){
		Envelope bounds = new Envelope(xmlRootAtt.getLocation().getX(), xmlRootAtt.getLocation().getY(),
				xmlRootAtt.getSize().getWidth(), xmlRootAtt.getSize().getHeight());
		rootAttrib.setBounds(bounds);
	}
	
	public void buildModel(){
		storeAnnotationProperties();
		Model xmlModel = this.xmlInstance.getModel();
		RootNode xmlRootNode = xmlModel.getRootNode();
		RootAttribute xmlRootAttribute = xmlRootNode.getRootAttribute();
		IRootAttribute hibRootAttribute = (IRootAttribute)this.graph.getGraph().getRoot().getAttribute();
		buildRootAttribute(hibRootAttribute, xmlRootAttribute);
		buildChildShapeNodes(this.graph.getGraph().getRoot().getChildCompoundGraph(), xmlRootNode.getSubModel());
//		buildChildLinkEdges(hibRootNode.getSubModel(), xmlRootNode.getSubModel());
		buildChildLabelNodes(xmlRootNode.getSubModel());
	}
	
	
	private void buildChildLabelNodes(SubModel xmlSubModel) {
		for(LabelNode xmlNode : xmlSubModel.getLabelNode()){
			int propId = xmlNode.getLabelAttribute().getPropertyRef().getId();
			IAnnotationProperty refProp = this.hibPropMap.get(propId);
			if(refProp != null){
				createLabelNode(xmlNode, refProp);
			}
			else {
				StringBuilder buf = new StringBuilder("Cannot find object property, so ignoring. Id=");
				buf.append(propId);
				logger.warn(buf.toString());
			}
		}
		for(ShapeNode xmlNode : xmlSubModel.getShapeNode()){
			buildChildLabelNodes(xmlNode.getSubModel());
		}
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

	private void buildChildShapeNodes(IChildCompoundGraph owningSubModel, SubModel xmlSubModel){
		for(ShapeNode xmlNode : xmlSubModel.getShapeNode()){
			ICompoundNode hibShapeNode = createShapeNode(owningSubModel, xmlNode);
			buildChildShapeNodes(hibShapeNode.getChildCompoundGraph(), xmlNode.getSubModel());
		}
		for(LinkEdge xmlEdge : xmlSubModel.getLinkEdge()){
			createLinkEdge(owningSubModel, xmlEdge);
		}
	}
	
	private ICompoundNode createShapeNode(IChildCompoundGraph owningSubModel, ShapeNode xmlNode){
		int otId = xmlNode.getShapeAttribute().getObjectTypeId();
		IShapeObjectType objectType = this.notationSubsystem.getSyntaxService().getShapeObjectType(otId);
		IModel rootAtt = this.graph;
		IShapeAttributeFactory attFact = rootAtt.shapeAttributeFactory();
		attFact.setDestinationAttribute(owningSubModel.getRoot().getAttribute());
		attFact.setObjectType(objectType);
		attFact.setPreferredCreationSerial(xmlNode.getShapeAttribute().getCreationSerial());
		ICompoundNodeFactory compoundNodeFact = owningSubModel.nodeFactory();
		compoundNodeFact.setAttributeFactory(attFact);
		ICompoundNode node = compoundNodeFact.createNode();
		IShapeAttribute attribute = (IShapeAttribute)node.getAttribute();
		defineShapeAttributes(attribute, xmlNode.getShapeAttribute());
		if(logger.isDebugEnabled()){
			logger.debug("Created shape node: " + node);
		}
		this.hibNodeMap.put(xmlNode.getNodeId(), node);
		return node;
	}
	
//	private IRootAttribute getRootAttribute(){
//		return (IRootAttribute)this.graph.getRoot().getAttribute();
//	}

	private ICompoundEdge createLinkEdge(IChildCompoundGraph owningSubModel, LinkEdge xmlNode){
		int otId = xmlNode.getLinkAttribute().getObjectTypeId();
		ILinkObjectType objectType = this.notationSubsystem.getSyntaxService().getLinkObjectType(otId);
		IModel canvas = this.graph;
		ILinkAttributeFactory attFact = canvas.linkAttributeFactory();
		attFact.setDestinationAttribute(owningSubModel.getRoot().getAttribute());
		attFact.setObjectType(objectType);
		attFact.setPreferredCreationSerial(xmlNode.getLinkAttribute().getCreationSerial());
		ICompoundNode srcNode = this.hibNodeMap.get(xmlNode.getSrcNodeIdx());
		ICompoundNode tgtNode = this.hibNodeMap.get(xmlNode.getTgtNodeIdx());
		ICompoundEdgeFactory edgeFactory = owningSubModel.edgeFactory();
		edgeFactory.setPair(new CompoundNodePair(srcNode, tgtNode));
		edgeFactory.setAttributeFactory(attFact);
		ICompoundEdge node = edgeFactory.createEdge(); 
		ILinkAttribute linkAtt = (ILinkAttribute)node.getAttribute();
		defineLinkTerminus(xmlNode.getLinkAttribute().getSrcTerminus(), linkAtt.getSourceTerminus()); 
		defineLinkTerminus(xmlNode.getLinkAttribute().getTgtTerminus(), linkAtt.getTargetTerminus()); 
		buildLinkAttributes(linkAtt, xmlNode.getLinkAttribute());
		if(logger.isDebugEnabled()){
			logger.debug("Created link edge: " + node);
		}
		return node;
	}
	
	private ICompoundNode createLabelNode(LabelNode xmlNode, IAnnotationProperty refProp) {
		ICompoundGraphElement parent = refProp.getOwner().getCurrentElement();  
		IModel rootAtt = this.graph;
//		IRootAttribute rootAtt = (IRootAttribute)this.graph.getRoot().getAttribute();
		ILabelAttributeFactory labelFact = rootAtt.labelAttributeFactory();
		labelFact.setDestinationAttribute(parent.getAttribute());
		labelFact.setProperty(refProp);
		ILabelObjectType labelOt = this.notationSubsystem.getSyntaxService().getLabelObjectTypeByProperty(refProp.getDefinition());
		labelFact.setLabelObjectType(labelOt);
		labelFact.setPreferredCreationSerial(xmlNode.getLabelAttribute().getCreationSerial());
		ICompoundNodeFactory nodeFact = parent.getChildCompoundGraph().nodeFactory();
		nodeFact.setAttributeFactory(labelFact);
		ICompoundNode node = nodeFact.createNode();
		ILabelAttribute attribute = (ILabelAttribute)node.getAttribute();
		buildLabelAttributes(attribute, xmlNode.getLabelAttribute());
		if(logger.isDebugEnabled()){
			logger.debug("Created label node: " + node);
		}
		return node;
	}

	private void buildLabelAttributes(ILabelAttribute attribute, LabelAttribute xmlLabelAttribute) {
		attribute.setBackgroundColor(createColour(xmlLabelAttribute.getFillColour()));
		attribute.setForegroundColor(createColour(xmlLabelAttribute.getLineColour()));
		Envelope bounds = new Envelope(createPoint(xmlLabelAttribute.getLocation()), 
				createDimension(xmlLabelAttribute.getSize()));
		attribute.setBounds(bounds);
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

	private static Colour createColour(ColourType xmlColour) {
		int blue = xmlColour.getBlue();
		int green = xmlColour.getGreen();
		int red = xmlColour.getRed();
		int alpha = Colour.OPAQUE;
		if(xmlColour.hasAlpha()){
			alpha = xmlColour.getAlpha();
		}
		return new Colour(red, green, blue, alpha);
	}

	public IModel getGraph() {
		return this.graph;
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
