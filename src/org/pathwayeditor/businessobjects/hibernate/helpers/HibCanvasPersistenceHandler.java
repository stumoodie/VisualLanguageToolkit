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
import org.pathwayeditor.businessobjects.drawingprimitives.IDrawingNode;
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkEdge;
import org.pathwayeditor.businessobjects.hibernate.helpers.fallbacknotation.FallbackNotationSubsystem;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibCanvas;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibCompoundNode;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibLabelAttribute;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibLinkAttribute;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibLinkEdge;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibModel;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibNotation;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibObjectType;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibRootNode;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibShapeAttribute;
import org.pathwayeditor.businessobjects.hibernate.pojos.LabelObjectType;
import org.pathwayeditor.businessobjects.management.ICanvasPersistenceHandler;
import org.pathwayeditor.businessobjects.management.INotationSubsystemPool;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationSubsystem;
import org.pathwayeditor.businessobjects.repository.IMap;
import org.pathwayeditor.businessobjects.typedefn.ILinkObjectType;
import org.pathwayeditor.businessobjects.typedefn.INodeObjectType;
import org.pathwayeditor.businessobjects.typedefn.IShapeObjectType;

/**
 * @author smoodie
 * 
 */
public class HibCanvasPersistenceHandler implements ICanvasPersistenceHandler {
	private final Logger logger = Logger.getLogger(this.getClass()); 
	private final SessionFactory fact;
	private final INotationSubsystemPool subsystemPool;
	private IMap owningMap = null;
	private ICanvas loadedCanvas = null;
//	private IAttributesForCanvasBuilder shapeAttrForCanvBuilder = new ShapeAttributesForCanvasBuilder();
//	private IAttributesForCanvasBuilder linkAtrrForCanvBuilder = new LinkAttributesForCanvasBuilder();

	// private IHibNotationFactory hibNotationFactory;
	// private INotationSubsystem defaultNotationSubsystem;

	public HibCanvasPersistenceHandler(SessionFactory fact, INotationSubsystemPool subsystemPool) {
		this.fact = fact;
		this.subsystemPool = subsystemPool;
		// this.defaultNotationSubsystem = null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pathwayeditor.businessobjects.bolayer.ICanvasLoader#setOwningMap( org.pathwayeditor.businessobjects.repository.IMap)
	 */
	public void setOwningMap(IMap owningMap) {
		this.owningMap = owningMap;
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
		this.loadedCanvas = null;
		Session s = this.fact.getCurrentSession();
		s.getTransaction().begin();
		HibCanvas hibCanvas = (HibCanvas) s.getNamedQuery("loadCanvas").setString("repo",
				this.getOwningMap().getRepository().getName()).setInteger("inode", this.getOwningMap().getINode()).uniqueResult();
		Hibernate.initialize(hibCanvas);
		hibCanvas.setMapDiagram(this.getOwningMap());
		INotationSubsystem notationSubsystem = null;
		IHibNotationFactory hibNotationFactory = null;
		if (this.subsystemPool.hasNotationSubsystem(hibCanvas.getHibNotation())) {
			notationSubsystem = this.subsystemPool.getSubsystem(hibCanvas.getHibNotation());
			hibNotationFactory = new HibNotationFactory(this.fact, notationSubsystem);
		} else {
			logger.warn("Notation subsystem: " + notationSubsystem + " was not provided by application using fallback notation subsystem instead.");
			notationSubsystem = new FallbackNotationSubsystem(hibCanvas.getHibNotation());
			hibNotationFactory = new FallbackHibNotationFactory(notationSubsystem, hibCanvas.getHibNotation());
		}
		try {
			hibNotationFactory.initialise();
		} catch (InconsistentNotationDefinitionException e) {
			logger.warn("Application and Db notations were inconsistent. Using fallback notation instead.", e);
			notationSubsystem = new FallbackNotationSubsystem(hibCanvas.getHibNotation());
			hibNotationFactory = new FallbackHibNotationFactory(notationSubsystem, hibCanvas.getHibNotation());
		}
		hibCanvas.setNotationSubsystem(notationSubsystem);
		HibModel loadedModel = hibCanvas.getModel();
		loadedModel.setHibNotationFactory(hibNotationFactory);
		initialiseNotation(hibCanvas.getHibNotation());
		initialiseAttributes(hibCanvas);
		initialiseModel(loadedModel);
		s.getTransaction().commit();
		hibCanvas.setMapDiagram(this.getOwningMap());
		this.loadedCanvas = hibCanvas;
	}
	
	
	private void initialiseNotation(HibNotation notation){
		Hibernate.initialize(notation);
		Hibernate.initialize(notation.getObjectTypes());
		for(HibObjectType objectType : notation.getObjectTypes()){
			Hibernate.initialize(objectType);
		}
	}
	
	/**
	 * @param hibCanvas
	 */
	private void initialiseAttributes(HibCanvas hibCanvas) {
		Hibernate.initialize(hibCanvas.getLinkAttributes());
		Hibernate.initialize(hibCanvas.getShapeAttributes());
		Hibernate.initialize(hibCanvas.getLabelAttributes());
		Hibernate.initialize(hibCanvas.getProperties());
		initShapeAttributes(hibCanvas);
		initLinkAttributes(hibCanvas);
		initLabelAttributes(hibCanvas);
	}

	public void initLinkAttributes(HibCanvas hibCanvas) {
		try {
			for (HibLinkAttribute linkAttr : hibCanvas.getLinkAttributes()) {
				Hibernate.initialize(linkAttr);
				ILinkObjectType objectType = hibCanvas.getNotationSubsystem().getSyntaxService().getLinkObjectType(
						linkAttr.getHibObjectType().getUniqueId());
				linkAttr.injectLinkObjectType(objectType);
				Hibernate.initialize(linkAttr.getHibLinkProperties());
				Hibernate.initialize(linkAttr.getBendPoints());
				Hibernate.initialize(linkAttr.getLinkTermini());
			}
		} catch (InconsistentNotationDefinitionException e) {
			throw new IllegalStateException(e);
		}
	}

	private void initLabelAttributes(HibCanvas hibCanvas) {
		final INodeObjectType labelObjectType = new LabelObjectType(hibCanvas.getNotationSubsystem().getSyntaxService());
		for (HibLabelAttribute labelAttr : hibCanvas.getLabelAttributes()) {
			Hibernate.initialize(labelAttr);
			labelAttr.setObjectType(labelObjectType);
			Hibernate.initialize(labelAttr.getProperty().getValue());
		}
	}

	public void initShapeAttributes(HibCanvas canvas) {
		try {
			for (HibShapeAttribute shapeAttr : canvas.getShapeAttributes()) {
				Hibernate.initialize(shapeAttr);
				IShapeObjectType objectType = canvas.getNotationSubsystem().getSyntaxService().getShapeObjectType(
						shapeAttr.getHibObjectType().getUniqueId());
				shapeAttr.injectShapeObjectType(objectType);
				Hibernate.initialize(shapeAttr.getProperties());
			}
		} catch (InconsistentNotationDefinitionException e) {
			throw new IllegalStateException(e);
		}
	}
	private void initialiseModel(HibModel model) {
		Hibernate.initialize(model);
		// set the OT required by the root node
		HibRootNode hibRootNode = model.getRootNode();
		Hibernate.initialize(hibRootNode);
		hibRootNode.setObjectType(model.getCanvas().getNotationSubsystem().getSyntaxService().getRootObjectType());
		initNodesAndEdges(model);
	}

	private void initNodesAndEdges(HibModel model) {
		// now go through all the nodes and edges and get them loaded from the
		// DB
		Iterator<IDrawingNode> nodeIter = model.drawingNodeIterator();
		while (nodeIter.hasNext()) {
			IDrawingNode node = nodeIter.next();
			Hibernate.initialize((HibCompoundNode) node);
			Hibernate.initialize(node.getAttribute());
			HibCompoundNode hibNode = (HibCompoundNode) node;
			Hibernate.initialize(hibNode.getChildren());
			Hibernate.initialize(hibNode.getParent());
			Hibernate.initialize(hibNode.getChildCompoundGraph());
			Hibernate.initialize(hibNode.getInEdges());
			Hibernate.initialize(hibNode.getOutEdges());
			Iterator<ILinkEdge> edgeIter = node.getSubModel().linkIterator();
			while (edgeIter.hasNext()) {
				HibLinkEdge link = (HibLinkEdge) edgeIter.next();
				Hibernate.initialize(link);
			}
		}
	}

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
		this.owningMap = null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pathwayeditor.businessobjects.bolayer.ICanvasLoader#synchroniseCanvas ()
	 */
	public void synchroniseCanvas() {
		Session s = this.fact.getCurrentSession();
		s.getTransaction().begin();
		s.saveOrUpdate(this.loadedCanvas);
		Iterator<IDrawingNode> nodeIterator = loadedCanvas.getModel().drawingNodeIterator();
		while (nodeIterator.hasNext()) {
			s.saveOrUpdate(nodeIterator.next());
		}
		s.getTransaction().commit();
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
		try {
			if (canvasTest == 0) {
				HibNotationFactory hibNotationFactory = new HibNotationFactory(this.fact, notationSubsystem);
				hibNotationFactory.initialise();
				hibCanvas = new HibCanvas(this.owningMap, hibNotationFactory, notationSubsystem);
				s.save(hibCanvas);
			} else {
				IllegalStateException e = new IllegalStateException("canvas already exists");  
				logger.error("cannot create canvs", e);
				throw e;
			}
			s.getTransaction().commit();
			this.loadedCanvas = hibCanvas;
		} catch (InconsistentNotationDefinitionException e) {
			s.getTransaction().rollback();
			throw new IllegalStateException(e);
		}
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

//	public void setShapeAttrForCanvBuilder(IAttributesForCanvasBuilder shapeAttrForCanvBuilder) {
//		this.shapeAttrForCanvBuilder = shapeAttrForCanvBuilder;
//	}

//	public void setLinkAtrrForCanvBuilder(IAttributesForCanvasBuilder linkAtrrForCanvBuilder) {
//		this.linkAtrrForCanvBuilder = linkAtrrForCanvBuilder;
//	}
}
