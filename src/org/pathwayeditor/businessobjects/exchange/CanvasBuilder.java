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

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import org.pathwayeditor.businessobjects.drawingprimitives.IBendPointContainer;
import org.pathwayeditor.businessobjects.drawingprimitives.ILabelAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.ILabelAttributeFactory;
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkAttributeFactory;
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkTerminus;
import org.pathwayeditor.businessobjects.drawingprimitives.IRootAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeAttributeFactory;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LineStyle;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LinkEndDecoratorShape;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB;
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
import org.pathwayeditor.businessobjects.management.INotationSubsystemPool;
import org.pathwayeditor.businessobjects.notationsubsystem.INotation;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationSubsystem;
import org.pathwayeditor.businessobjects.typedefn.ILinkObjectType;
import org.pathwayeditor.businessobjects.typedefn.IShapeObjectType;
import org.pathwayeditor.figure.geometry.Dimension;
import org.pathwayeditor.figure.geometry.Envelope;
import org.pathwayeditor.figure.geometry.Point;

import uk.ac.ed.inf.graph.compound.CompoundNodePair;
import uk.ac.ed.inf.graph.compound.IChildCompoundGraph;
import uk.ac.ed.inf.graph.compound.ICompoundEdge;
import uk.ac.ed.inf.graph.compound.ICompoundEdgeFactory;
import uk.ac.ed.inf.graph.compound.ICompoundGraph;
import uk.ac.ed.inf.graph.compound.ICompoundNode;
import uk.ac.ed.inf.graph.compound.ICompoundNodeFactory;
import uk.ac.ed.inf.graph.compound.newimpl.CompoundGraph;

/**
 * @author smoodie
 *
 */
public class CanvasBuilder {
	private ICompoundGraph hibCanvas = null;
	private final Canvas xmlInstance;
	private final INotationSubsystemPool notationPool;
//	private final int iNode;
//	private final String repoName;
//	private IHibNotationFactory notationFactory;
	private INotationSubsystem notationSubsystem;
	private final Map<Integer, ICompoundNode> hibNodeMap;
	private final Map<Integer, PropertyType> xmlPropMap;
	private final Map<Integer, IAnnotationProperty> hibPropMap;
	private final Map<EndDecoratorTypeType, LinkEndDecoratorShape> endDecMapping;
	private INotation notation;
	
	public CanvasBuilder(Canvas xmlInstance, INotationSubsystemPool notationPool){
		this.xmlInstance = xmlInstance;
		this.notationPool = notationPool;
//		this.repoName = repoName;
//		this.iNode = iNode;
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
		this.hibCanvas = new CompoundGraph(new org.pathwayeditor.businessobjects.impl.RootAttribute(xmlInstance.getName(), notationSubsystem.getSyntaxService().getRootObjectType()));
//		this.hibCanvas.setCreationSerialCounter(new IndexCounter(this.xmlInstance.getLastCreationSerial()));
		IRootAttribute iCanvas = (IRootAttribute)hibCanvas.getRoot().getAttribute();
		iCanvas.setBackgroundColour(createColour(this.xmlInstance.getBackground()));
//		iCanvas.setBounds(createDimension(this.xmlInstance.getCanvasSize()));
//		iCanvas.setGridSize(createDimension(this.xmlInstance.getGrid().getGridSize()));
//		iCanvas.setGridEnabled(this.xmlInstance.getGrid().isGridOn());
//		iCanvas.setSnapToGrid(xmlInstance.getGrid().isSnapToGrid());
	}
	
	public void buildNotation(){
//		INotationDelegate delegate = new XmlNotationDeletgate();
//		this.notation = delegate.getNotation();
		this.notation = new XmlNotation(xmlInstance.getNotation());
//		notationFactory = new XmlNotationFactory(new XmlNotationDeletgate());
//		notationFactory.initialise();
	}
	
	private void buildLinkAttributes(ILinkAttribute linkAttrib, LinkAttribute xmlLinkAtt) {
		linkAttrib.setLineColour(createColour(xmlLinkAtt.getLineColour()));
		linkAttrib.setLineWidth(xmlLinkAtt.getLineWidth());
		linkAttrib.setLineStyle(createLineStyle(xmlLinkAtt.getLineStyle()));
//		linkAttrib.setLastBendPointSerial(xmlLinkAtt.getLastBendPointIndex());
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
//			HibBendPoint hibBp = new HibBendPoint(linkAttrib, xmlBendPoint.getCreationSerial(), createPoint(xmlBendPoint.getPosition()));
//			hibBp.setIndexPos(xmlBendPoint.getIndexPosn());
//			linkAttrib.getBendPoints().add(hibBp);
		}
		buildAnnotationProperties(linkAttrib, xmlLinkAtt.getPropertyRef());
	}
	
	
	private ILinkTerminus createLinkTerminus(LinkTerminusType xmlTerm, ILinkTerminus retVal){
//		ILinkTerminus retVal = new HibLinkTerminus(this.hibCanvas, xmlTerm.getCreationSerial(), termType, defn);
		retVal.setEndSize(createDimension(xmlTerm.getDecoratorSize()));
		retVal.setLocation(createPoint(xmlTerm.getLocation()));
		retVal.setGap(xmlTerm.getGap());
		retVal.setEndDecoratorType(this.endDecMapping.get(xmlTerm.getEndDecoratorType()));
//		buildAnnotationProperties(retVal, xmlTerm.getPropertyRef());
		return retVal;
	}

	private void buildShapeAttributes(IShapeAttribute shapeAttrib, ShapeAttribute xmlShapeAtt) {
		Envelope bounds = new Envelope(createPoint(xmlShapeAtt.getLocation()), createPoint(xmlShapeAtt.getLocation()));
		shapeAttrib.setBounds(bounds);
		shapeAttrib.setFillColour(createColour(xmlShapeAtt.getFillColour()));
		shapeAttrib.setLineColour(createColour(xmlShapeAtt.getLineColour()));
//		shapeAttribsetSize(createDimension(xmlShapeAtt.getSize()));
//		shapeAttrib.setLocation(createPoint(xmlShapeAtt.getLocation()));
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
//		IRootObjectType rootObjectType = this.notationSubsystem.getSyntaxService().getRootObjectType();
//		HibModel model = new HibModel(this.hibCanvas, rootObjectType, this.notationFactory);
//		HibRootNode hibRootNode = model.getRootNode();
//		HibRootAttribute hibRootAttribute = hibRootNode.getAttribute();
//		this.hibCanvas.setGraph(model);
		Model xmlModel = this.xmlInstance.getModel();
//		model.setEdgeCounter(new IndexCounter(xmlModel.getLastEdgeIndex()));
//		model.setNodeCounter(new IndexCounter(xmlModel.getLastNodeIndex()));
		RootNode xmlRootNode = xmlModel.getRootNode();
		RootAttribute xmlRootAttribute = xmlRootNode.getRootAttribute();
		IRootAttribute hibRootAttribute = (IRootAttribute)this.hibCanvas.getRoot().getAttribute();
		buildRootAttribute(hibRootAttribute, xmlRootAttribute);
		buildChildShapeNodes(this.hibCanvas.getRoot().getChildCompoundGraph(), xmlRootNode.getSubModel());
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

	private void buildChildShapeNodes(IChildCompoundGraph owningSubModel, SubModel xmlSubModel){
		for(ShapeNode xmlNode : xmlSubModel.getShapeNode()){
			ICompoundNode hibShapeNode = createShapeNode(owningSubModel, xmlNode);
			buildChildShapeNodes(hibShapeNode.getChildCompoundGraph(), xmlNode.getSubModel());
		}
		for(LinkEdge xmlEdge : xmlSubModel.getLinkEdge()){
			createLinkEdge(owningSubModel, xmlEdge);
		}
		for(LabelNode xmlNode : xmlSubModel.getLabelNode()){
			createLabelNode(owningSubModel, xmlNode);
		}
	}
	
	private ICompoundNode createShapeNode(IChildCompoundGraph owningSubModel, ShapeNode xmlNode){
		int otId = xmlNode.getShapeAttribute().getObjectTypeId();
		IShapeObjectType objectType = this.notationSubsystem.getSyntaxService().getShapeObjectType(otId);
		IRootAttribute rootAtt = (IRootAttribute)this.hibCanvas.getRoot().getAttribute();
		IShapeAttributeFactory attFact = rootAtt.shapeAttributeFactory();
		attFact.setDestinationAttribute(owningSubModel.getRoot().getAttribute());
		attFact.setObjectType(objectType);
		ICompoundNodeFactory compoundNodeFact = owningSubModel.nodeFactory();
//		ShapeAttribute attribute = new ShapeAttribute(this.hibCanvas, xmlNode.getShapeAttribute().getCreationSerial(),
//				objectType, this.notationFactory.getObjectType(otId));
		compoundNodeFact.setAttributeFactory(attFact);
		ICompoundNode node = compoundNodeFact.createNode();
		IShapeAttribute attribute = (IShapeAttribute)node.getAttribute();
		buildShapeAttributes(attribute, xmlNode.getShapeAttribute());
//		HibShapeNode node = new HibShapeNode(owningSubModel.getRootNode(), xmlNode.getNodeId(), attribute);
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
	
	private IRootAttribute getRootAttribute(){
		return (IRootAttribute)this.hibCanvas.getRoot().getAttribute();
	}

	private ICompoundEdge createLinkEdge(IChildCompoundGraph owningSubModel, LinkEdge xmlNode){
		int otId = xmlNode.getLinkAttribute().getObjectTypeId();
		ILinkObjectType objectType = this.notationSubsystem.getSyntaxService().getLinkObjectType(otId);
		ILinkAttributeFactory attFact = getRootAttribute().linkAttributeFactory();
		attFact.setDestinationAttribute(owningSubModel.getRoot().getAttribute());
		attFact.setObjectType(objectType);
		ICompoundNode srcNode = this.hibNodeMap.get(xmlNode.getSrcNodeIdx());
		ICompoundNode tgtNode = this.hibNodeMap.get(xmlNode.getTgtNodeIdx());
		ICompoundEdgeFactory edgeFactory = owningSubModel.edgeFactory();
		edgeFactory.setPair(new CompoundNodePair(srcNode, tgtNode));
		edgeFactory.setAttributeFactory(attFact);
		ICompoundEdge node = edgeFactory.createEdge(); 
		ILinkAttribute linkAtt = (ILinkAttribute)node.getAttribute();
		createLinkTerminus(xmlNode.getLinkAttribute().getSrcTerminus(), linkAtt.getSourceTerminus()); 
		createLinkTerminus(xmlNode.getLinkAttribute().getTgtTerminus(), linkAtt.getTargetTerminus()); 
//		HibLinkAttribute attribute = new HibLinkAttribute(this.hibCanvas, xmlNode.getLinkAttribute().getCreationSerial(),
//				objectType, this.notationFactory.getObjectType(otId), srcTerm, tgtTerm);
		buildLinkAttributes(linkAtt, xmlNode.getLinkAttribute());
//		HibShapeNode srcNode = this.hibNodeMap.get(xmlNode.getSrcNodeIdx());
//		HibShapeNode tgtNode = this.hibNodeMap.get(xmlNode.getTgtNodeIdx());
//		HibLinkEdge node = new HibLinkEdge(owningSubModel, xmlNode.getLinkIdx(), srcNode, tgtNode, attribute);
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

	private ICompoundNode createLabelNode(IChildCompoundGraph owningSubModel, LabelNode xmlNode) {
		IAnnotationProperty refProp = this.hibPropMap.get(xmlNode.getLabelAttribute().getPropertyRef().getId());
//		ILabelAttributeDefaults labelDefaults = refProp.getDefinition().getLabelDefaults();
		IRootAttribute rootAtt = (IRootAttribute)this.hibCanvas.getRoot().getAttribute();
		ILabelAttributeFactory labelFact = rootAtt.labelAttributeFactory();
		labelFact.setDestinationAttribute(owningSubModel.getRoot().getAttribute());
		labelFact.setProperty(refProp);
		ICompoundNodeFactory nodeFact = owningSubModel.nodeFactory();
		nodeFact.setAttributeFactory(labelFact);
		ICompoundNode node = nodeFact.createNode();
		ILabelAttribute attribute = (ILabelAttribute)node.getAttribute();
//		IComNode node = new HibLabelNode(owningSubModel.getRootNode(), xmlNode.getNodeId(), attribute);
//		ILabelAttribute attribute = new org.pathwayeditor.businessobjects.impl.LabelAttribute(rootAtt, xmlNode.getLabelAttribute().getCreationSerial(), refProp, labelDefaults);
		buildLabelAttributes(attribute, xmlNode.getLabelAttribute());
		return node;
	}

	private void buildLabelAttributes(ILabelAttribute attribute, LabelAttribute xmlLabelAttribute) {
		attribute.setBackgroundColor(createColour(xmlLabelAttribute.getFillColour()));
		attribute.setForegroundColor(createColour(xmlLabelAttribute.getLineColour()));
		Envelope bounds = new Envelope(createPoint(xmlLabelAttribute.getLocation()), 
				createDimension(xmlLabelAttribute.getSize()));
//		attribute.setLocation(createPoint(xmlLabelAttribute.getLocation()));
//		attribute.setSize(createDimension(xmlLabelAttribute.getSize()));
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

	private static RGB createColour(ColourType xmlColour) {
		int blue = xmlColour.getBlue();
		int green = xmlColour.getGreen();
		int red = xmlColour.getRed();
		return new RGB(red, green, blue);
	}

	public ICompoundGraph getCanvas() {
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
	
//	private class XmlNotationDeletgate implements INotationDelegate {
//
//		@Override
//		public INotation getNotation() {
//			return new XmlNotation(xmlInstance.getNotation());
//		}
//
////		@Override
////		public Iterator<IObjectInfo> objectTypeIterator() {
////			List<IObjectInfo> list = Collections.emptyList();
////			return list.iterator();
////		}
//
//		/* (non-Javadoc)
//		 * @see org.pathwayeditor.businessobjects.exchange.INotationDelegate#numObjectTypes()
//		 */
//		@Override
//		public int numObjectTypes() {
//			return 0;
//		}
//		
//	}
	
//	private class XmlNotationDeletgate implements INotationDelegate {
//		
//		/* (non-Javadoc)
//		 * @see org.pathwayeditor.businessobjects.exchange.INotationDelegate#getNotation()
//		 */
//		@Override
//		public INotation getNotation() {
//			return new XmlNotation(xmlInstance.getNotation());
//		}
//
//		/* (non-Javadoc)
//		 * @see org.pathwayeditor.businessobjects.exchange.INotationDelegate#numObjectTypes()
//		 */
//		@Override
//		public int numObjectTypes() {
//			return xmlInstance.getNotation().getObjectTypeCount();
//		}
//
//		/* (non-Javadoc)
//		 * @see org.pathwayeditor.businessobjects.exchange.INotationDelegate#objectTypeIterator()
//		 */
//		@Override
//		public Iterator<IObjectInfo> objectTypeIterator() {
//			final Iterator<ObjectType> objectTypeIter = Arrays.asList(xmlInstance.getNotation().getObjectType()).iterator();
//			return new Iterator<IObjectInfo>(){
//
//				@Override
//				public boolean hasNext() {
//					return objectTypeIter.hasNext();
//				}
//
//				@Override
//				public IObjectInfo next() {
//					final ObjectType xmlObjectType = objectTypeIter.next();
//					return new IObjectInfo(){
//
//						@Override
//						public ObjectTypeClassification getClassification() {
//							ObjectTypeClassification classn = ObjectTypeClassification.SHAPE;
//							if(xmlObjectType.getClassification().equals(ObjectTypeClassificationType.ROOT)){
//								classn = ObjectTypeClassification.ROOTOBJECT;
//							}
//							else if(xmlObjectType.getClassification().equals(ObjectTypeClassificationType.LINK)){
//								classn = ObjectTypeClassification.LINK;
//							}
//							return classn;
//						}
//
//						@Override
//						public String getDescription() {
//							return xmlObjectType.getDescription();
//						}
//
//						@Override
//						public String getName() {
//							return xmlObjectType.getName();
//						}
//
//						@Override
//						public int getUniqueId() {
//							return xmlObjectType.getUniqueId();
//						}
//						
//					};
//				}
//
//				@Override
//				public void remove() {
//					throw new UnsupportedOperationException("Removal is not supported");
//				}
//				
//			};
//		}
//		
//	}
}
