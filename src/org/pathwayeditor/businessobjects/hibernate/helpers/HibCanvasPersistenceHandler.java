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
/**
 * 
 */
package org.pathwayeditor.businessobjects.hibernate.helpers;

import java.util.Iterator;

import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.pathwayeditor.businessobjects.drawingprimitives.ICanvas;
import org.pathwayeditor.businessobjects.drawingprimitives.ILabelAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.ILabelNode;
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkEdge;
import org.pathwayeditor.businessobjects.drawingprimitives.IModel;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeNode;
import org.pathwayeditor.businessobjects.drawingprimitives.ISubModel;
import org.pathwayeditor.businessobjects.hibernate.helpers.fallbacknotation.FallbackNotationSubsystem;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibCanvas;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibCanvasAttribute;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibLabelAttribute;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibLabelNode;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibLinkAttribute;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibLinkEdge;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibModel;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibObjectType;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibRootAttribute;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibRootNode;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibShapeAttribute;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibShapeNode;
import org.pathwayeditor.businessobjects.hibernate.pojos.LabelObjectType;
import org.pathwayeditor.businessobjects.management.ICanvasPersistenceHandler;
import org.pathwayeditor.businessobjects.management.INotationSubsystemPool;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationSubsystem;
import org.pathwayeditor.businessobjects.repository.IMap;
import org.pathwayeditor.businessobjects.typedefn.IObjectType;

import uk.ed.inf.graph.compound.base.BaseCompoundNode;
import uk.ed.inf.graph.compound.base.UnfilteredTreeIterator;

/**
 * @author smoodie
 * 
 */
public class HibCanvasPersistenceHandler implements ICanvasPersistenceHandler {
	private final Logger logger = Logger.getLogger(this.getClass());
	private final SessionFactory fact;
	private final INotationSubsystemPool subsystemPool;
	private final IMap owningMap;
	private ICanvas loadedCanvas = null;


	public HibCanvasPersistenceHandler(SessionFactory fact, INotationSubsystemPool subsystemPool, IMap map) {
		this.fact = fact;
		this.subsystemPool = subsystemPool;
		this.owningMap = map;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pathwayeditor.businessobjects.bolayer.ICanvasLoader#getOwningMap()
	 */
	public IMap getOwningMap() {
		return this.owningMap;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pathwayeditor.businessobjects.bolayer.ICanvasLoader#loadCanvas()
	 */
	public void loadCanvas() {
		try {
		this.loadedCanvas = null;
		Session s = this.fact.getCurrentSession();
		s.getTransaction().begin();
		HibCanvas hibCanvas = (HibCanvas) s.getNamedQuery("loadCanvas").setString("repo",
				this.getOwningMap().getRepository().getName()).setInteger("inode", this.getOwningMap().getINode())
				.uniqueResult();
		this.loadedCanvas = hibCanvas;
		Hibernate.initialize(hibCanvas);
		INotationSubsystem loadedNotationSubsystem = null;
		IHibNotationFactory hibNotationFactory = null;
		if (this.subsystemPool.hasNotationSubsystem(hibCanvas.getHibNotation())) {
			loadedNotationSubsystem = this.subsystemPool.getSubsystem(hibCanvas.getHibNotation());
			hibNotationFactory = new HibNotationFactory(this.fact, loadedNotationSubsystem);
		} else {
			logger.warn("Notation subsystem: " + loadedNotationSubsystem
					+ " was not provided by application using fallback notation subsystem instead.");
			loadedNotationSubsystem = new FallbackNotationSubsystem(hibCanvas.getHibNotation());
			hibNotationFactory = new FallbackHibNotationFactory(loadedNotationSubsystem, hibCanvas.getHibNotation());
		}
			hibNotationFactory.initialise();
		if(hibNotationFactory.hasInitialisationFailed()) {
			logger.warn("Application and Db notations were inconsistent. Using fallback notation instead.");
			loadedNotationSubsystem = new FallbackNotationSubsystem(hibCanvas.getHibNotation());
			hibNotationFactory = new FallbackHibNotationFactory(loadedNotationSubsystem, hibCanvas.getHibNotation());
		}
		hibCanvas.setNotationSubsystem(loadedNotationSubsystem);
		HibModel loadedModel = hibCanvas.getModel();
		loadedModel.setHibNotationFactory(hibNotationFactory);
//		initialiseNotation(hibCanvas.getHibNotation());
		initialiseCanvas(hibCanvas);
//		initialiseModel(loadedModel);
		if(!loadedModel.isValid()) {
			throw new IllegalStateException("The loaded model is invalid.");
		}
		s.getTransaction().commit();
		} catch (InconsistentNotationDefinitionException e) {
			this.fact.getCurrentSession().getTransaction().rollback();
			this.loadedCanvas = null;
			throw new IllegalStateException("The loaded model is invalid and cannot be syncronised.");
		}
	}

//	private void initialiseNotation(HibNotation notation) {
//		Hibernate.initialize(notation);
//		Hibernate.initialize(notation.getObjectTypes());
//		for (HibObjectType objectType : notation.getObjectTypes()) {
//			Hibernate.initialize(objectType);
//		}
//	}

	/**
	 * @param hibCanvas
	 */
//	private void initialiseAttributes(HibCanvas hibCanvas) {
//		Hibernate.initialize(hibCanvas.getCanvasAttributes());
////		initShapeAttributes(hibCanvas);
//		initCanvasAttributes(hibCanvas);
////		initLabelAttributes(hibCanvas);
//	}

	private void initialiseCanvas(HibCanvas hibCanvas) throws InconsistentNotationDefinitionException {
//		Hibernate.initialize(hibCanvas.getCanvasAttributes());
		HibModel model = hibCanvas.getModel();
		HibRootAttribute rootAttribute = model.getRootNode().getAttribute();
//		Hibernate.initialize(rootAttribute);
		injectObjectType(hibCanvas, rootAttribute);
		// need to do this because we need to traverse the removed nodes to make sure they behave correctly
		// if not then they will throw up a validation error.
		// FIXME: We need to purge deleted nodes and edges on closure of manager
		Iterator<BaseCompoundNode> treeIter = new UnfilteredTreeIterator(model.getRootNode());
		// skip first node which is root and which has been dealt with above
		while(treeIter.hasNext()){
			BaseCompoundNode node = treeIter.next();
			if(node instanceof HibShapeNode){
				HibShapeNode shapeNode = (HibShapeNode)node;
				visitShapeNode(hibCanvas, shapeNode);
				Iterator<HibLinkEdge> edgeIter = shapeNode.getChildCompoundGraph().allEdgesIterator();
				while(edgeIter.hasNext()){
					HibLinkEdge edge = edgeIter.next();
					visitLinkEdge(hibCanvas, edge);
				}
			}
			else if(node instanceof HibLabelNode){
				visitLabelNode(hibCanvas, (HibLabelNode)node);
			}
			else if(node instanceof HibRootNode){
				HibRootNode rootNode = (HibRootNode)node;
				Iterator<HibLinkEdge> edgeIter = rootNode.getChildCompoundGraph().allEdgesIterator();
				while(edgeIter.hasNext()){
					HibLinkEdge edge = edgeIter.next();
					visitLinkEdge(hibCanvas, edge);
				}
			}
			else{
				throw new IllegalStateException("Unknown node type");
			}
		}
//		ISubModel rootsSubmodel = model.getRootNode().getSubModel();
//		Iterator<IShapeNode> shapeIter = rootsSubmodel.shapeNodeIterator();
//		while(shapeIter.hasNext()) {
//			visitShapeNode(hibCanvas, (HibShapeNode)shapeIter.next());
//		}
//		Iterator<ILinkEdge> linkIter = rootsSubmodel.linkIterator();
//		while(linkIter.hasNext()) {
//			visitLinkEdge(hibCanvas, (HibLinkEdge)linkIter.next());
//		}
//		Iterator<ILabelNode> labelIter = rootsSubmodel.labelIterator();
//		while(labelIter.hasNext()) {
//			visitLabelNode(hibCanvas, (HibLabelNode)labelIter.next());
//		}
	}

	private void injectObjectType(HibCanvas hibCanvas, HibCanvasAttribute canvasAttribute) throws InconsistentNotationDefinitionException {
		HibObjectType hibObjectType = canvasAttribute.getHibObjectType();
		if(hibObjectType != null) {
			IObjectType objectType = hibCanvas.getNotationSubsystem().getSyntaxService().getObjectType(
					hibObjectType.getUniqueId());
			canvasAttribute.injectObjectType(objectType);
		}
		else if(canvasAttribute instanceof ILabelAttribute) {
			// insert a dummy label object type.
			canvasAttribute.injectObjectType(new LabelObjectType(hibCanvas.getNotationSubsystem().getSyntaxService()));
		}
	}

	/**
	 * @param canvas 
	 * @param next
	 * @throws InconsistentNotationDefinitionException 
	 */
	private void visitLabelNode(HibCanvas canvas, HibLabelNode labelNode) throws InconsistentNotationDefinitionException {
//		Hibernate.initialize(labelNode);
		HibLabelAttribute attribute = labelNode.getAttribute();
//		Hibernate.initialize(attribute);
		injectObjectType(canvas, attribute);
//		Hibernate.initialize(attribute.getCurrentDrawingElement());
//		HibProperty property = (HibProperty)attribute.getVisualisableProperty();
//		property.getDisplayedLabel();
	}

	/**
	 * @param canvas 
	 * @param next
	 * @throws InconsistentNotationDefinitionException 
	 */
	private void visitLinkEdge(HibCanvas canvas, HibLinkEdge linkEdge) throws InconsistentNotationDefinitionException {
//		Hibernate.initialize(linkEdge);
		HibLinkAttribute attribute = linkEdge.getAttribute();
//		Hibernate.initialize(attribute);
//		Hibernate.initialize(attribute.getCurrentDrawingElement());
//		Hibernate.initialize(attribute.getBendPoints());
//		visitProperties(attribute);
		injectObjectType(canvas, attribute);
//		linkEdge.getOutNode();
//		linkEdge.getInNode();
//		linkEdge.getOwningSubModel();
//		visitLinkTermini(canvas, attribute);
	}
	
//	private void visitLinkTermini(HibCanvas canvas, HibLinkAttribute linkAttribute) {
//		Hibernate.initialize(linkAttribute.getLinkTermini());
//		for(HibLinkTerminus term : linkAttribute.getLinkTermini()) {
//			Hibernate.initialize(term.getOwningLink());
//			visitProperties(term);
//		}
//	}
//
//	private void visitProperties(HibAnnotatedCanvasAttribute attribute) {
//		Hibernate.initialize(attribute.getProperties());
//		for(HibProperty property : attribute.getProperties()) {
//			Hibernate.initialize(property);
//			Hibernate.initialize(property.getValue());
//		}
//	}
	
	
	/**
	 * @param next
	 * @throws InconsistentNotationDefinitionException 
	 */
	private void visitShapeNode(HibCanvas canvas, HibShapeNode shapeNode) throws InconsistentNotationDefinitionException {
//		Hibernate.initialize(shapeNode);
		HibShapeAttribute attribute = shapeNode.getAttribute();
//		Hibernate.initialize(attribute.getCurrentDrawingElement());
//		visitProperties(attribute);
		injectObjectType(canvas, attribute);
		ISubModel rootsSubmodel = shapeNode.getSubModel();
		Iterator<IShapeNode> shapeIter = rootsSubmodel.shapeNodeIterator();
		while(shapeIter.hasNext()) {
			visitShapeNode(canvas, (HibShapeNode)shapeIter.next());
		}
		Iterator<ILinkEdge> linkIter = rootsSubmodel.linkIterator();
		while(linkIter.hasNext()) {
			visitLinkEdge(canvas, (HibLinkEdge)linkIter.next());
		}
		Iterator<ILabelNode> labelIter = rootsSubmodel.labelIterator();
		while(labelIter.hasNext()) {
			visitLabelNode(canvas, (HibLabelNode)labelIter.next());
		}
	}

//	private void initLabelAttributes(HibCanvas hibCanvas) {
//		final INodeObjectType labelObjectType = new LabelObjectType(hibCanvas.getNotationSubsystem().getSyntaxService());
//		for (HibLabelAttribute labelAttr : hibCanvas.getLabelAttributes()) {
//			Hibernate.initialize(labelAttr);
//			labelAttr.setObjectType(labelObjectType);
//			Hibernate.initialize(labelAttr.getProperty().getValue());
//		}
//	}

//	private void initShapeAttributes(HibCanvas canvas) {
//		try {
//			for (HibShapeAttribute shapeAttr : canvas.getShapeAttributes()) {
//				Hibernate.initialize(shapeAttr);
//				IShapeObjectType objectType = canvas.getNotationSubsystem().getSyntaxService().getShapeObjectType(
//						shapeAttr.getHibObjectType().getUniqueId());
//				shapeAttr.injectShapeObjectType(objectType);
//				Hibernate.initialize(shapeAttr.getProperties());
//			}
//		} catch (InconsistentNotationDefinitionException e) {
//			throw new IllegalStateException(e);
//		}
//	}

//	private void initialiseModel(HibModel model) {
//		Hibernate.initialize(model);
//		// set the OT required by the root node
//		HibRootNode hibRootNode = model.getRootNode();
//		Hibernate.initialize(hibRootNode);
////		hibRootNode.setObjectType(model.getCanvas().getNotationSubsystem().getSyntaxService().getRootObjectType());
//		initNodesAndEdges(model);
//	}

//	private void initNodesAndEdges(HibModel model) {
//		// now go through all the nodes and edges and get them loaded from the
//		// DB
//		Iterator<IDrawingNode> nodeIter = model.drawingNodeIterator();
//		while (nodeIter.hasNext()) {
//			IDrawingNode node = nodeIter.next();
//			Hibernate.initialize((HibCompoundNode) node);
//			Hibernate.initialize(node.getAttribute());
//			HibCompoundNode hibNode = (HibCompoundNode) node;
//			Hibernate.initialize(hibNode.getChildren());
//			Hibernate.initialize(hibNode.getHibParentNode());
//			Hibernate.initialize(hibNode.getChildCompoundGraph());
//			Hibernate.initialize(hibNode.getInEdges());
//			Hibernate.initialize(hibNode.getOutEdges());
//			Iterator<ILinkEdge> edgeIter = node.getSubModel().linkIterator();
//			while (edgeIter.hasNext()) {
//				HibLinkEdge link = (HibLinkEdge) edgeIter.next();
//				Hibernate.initialize(link);
//			}
//		}
//	}

	public ICanvas getLoadedCanvas() {
		return this.loadedCanvas;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pathwayeditor.businessobjects.bolayer.ICanvasLoader#finish()
	 */
	public void reset() {
		this.loadedCanvas = null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pathwayeditor.businessobjects.bolayer.ICanvasLoader#synchroniseCanvas ()
	 */
	public void synchroniseCanvas() {
		if(this.loadedCanvas.getModel().isValid()) {
			Session s = this.fact.getCurrentSession();
			s.getTransaction().begin();
			s.saveOrUpdate(this.getLoadedCanvas().getModel().getRootNode());
			s.saveOrUpdate(this.loadedCanvas);
//		Iterator<IDrawingNode> nodeIterator = loadedCanvas.getModel().drawingNodeIterator();
//		while (nodeIterator.hasNext()) {
//			s.saveOrUpdate(nodeIterator.next());
//		}
			s.getTransaction().commit();
		}
		else {
			throw new IllegalStateException("The loaded model is invalid and cannot be syncronised.");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pathwayeditor.businessobjects.management.ICanvasPersistenceHandler #createCanvas
	 * (org.pathwayeditor.businessobjects.notationsubsystem.INotationSubsystem)
	 */
	public void createCanvas(INotationSubsystem notationSubsystem) {
		this.loadedCanvas = null;
		Session s = this.fact.getCurrentSession();
		s.getTransaction().begin();
		long canvasTest = (Long) s.getNamedQuery("canvasExistsForMap").setString("repo",
				this.getOwningMap().getRepository().getName()).setInteger("inode", this.getOwningMap().getINode())
				.uniqueResult();
		HibCanvas hibCanvas = null;
		if (canvasTest == 0) {
			HibNotationFactory hibNotationFactory = new HibNotationFactory(this.fact, notationSubsystem);
			hibNotationFactory.initialise();
			String repoName = this.owningMap.getRepository().getName();
			int iNode = this.owningMap.getINode();
			hibCanvas = new HibCanvas(repoName, iNode, hibNotationFactory, notationSubsystem, this.getOwningMap().getName());
			s.save(hibCanvas);
			// due to the mappings we must explicitly save the root node
			// and this saves the rest of the graph tree
			s.save(hibCanvas.getModel().getRootNode());
		} else {
			IllegalStateException e = new IllegalStateException("canvas already exists");
			logger.error("cannot create canvs", e);
			throw e;
		}
		s.getTransaction().commit();
		this.loadedCanvas = hibCanvas;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pathwayeditor.businessobjects.management.ICanvasPersistenceHandler #doesCanvasExist()
	 */
	public boolean doesCanvasExist() {
		Session s = this.fact.getCurrentSession();
		s.getTransaction().begin();
		long canvasTest = (Long) s.getNamedQuery("canvasExistsForMap").setString("repo",
				this.getOwningMap().getRepository().getName()).setInteger("inode", this.getOwningMap().getINode())
				.uniqueResult();
		s.getTransaction().commit();
		return canvasTest > 0;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.management.ICanvasPersistenceHandler#deleteCanvas()
	 */
	public void deleteCanvas() {
		if(this.loadedCanvas != null) {
			Session s = this.fact.getCurrentSession();
			s.getTransaction().begin();
			IModel model = this.loadedCanvas.getModel();
			// due to the mapping we must explicitly delete the root node
			// this will remove the nodes and edges before the model is deleted.
			s.delete(model.getRootNode());
			s.delete(model);
			s.delete(this.loadedCanvas);
			s.getTransaction().commit();
			this.loadedCanvas = null;
		}
		else {
			throw new IllegalStateException("No canvas was loaded.");
		}
	}
}
