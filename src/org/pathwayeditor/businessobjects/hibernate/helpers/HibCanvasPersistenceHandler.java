/**
 * 
 */
package org.pathwayeditor.businessobjects.hibernate.helpers;

import java.util.Iterator;

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
	private final SessionFactory fact;
	private final INotationSubsystemPool subsystemPool;
	private IMap owningMap = null;
	private ICanvas loadedCanvas = null;
//	private IHibNotationFactory hibNotationFactory;
//	private INotationSubsystem defaultNotationSubsystem;
	
	public HibCanvasPersistenceHandler(SessionFactory fact, INotationSubsystemPool subsystemPool){
		this.fact = fact;
		this.subsystemPool = subsystemPool;
//		this.defaultNotationSubsystem = null;
	}
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.bolayer.ICanvasLoader#setOwningMap(org.pathwayeditor.businessobjects.repository.IMap)
	 */
	public void setOwningMap(IMap owningMap){
		this.owningMap = owningMap;
	}
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.bolayer.ICanvasLoader#getOwningMap()
	 */
	public IMap getOwningMap() {
		return this.owningMap;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.bolayer.ICanvasLoader#loadCanvas()
	 */
	public void loadCanvas(){
		this.loadedCanvas = null;
		Session s = this.fact.getCurrentSession();
		s.getTransaction().begin();
		HibCanvas hibCanvas = (HibCanvas) s.getNamedQuery("loadCanvas")
				.setString("repo",this.getOwningMap().getRepository().getName())
				.setInteger("inode", this.getOwningMap().getINode()).uniqueResult();
		Hibernate.initialize(hibCanvas);
		hibCanvas.setMapDiagram(this.getOwningMap());
		INotationSubsystem notationSubsystem = null;
		if(this.subsystemPool.hasNotationSubsystem(hibCanvas.getHibNotation())){
			notationSubsystem = this.subsystemPool.getSubsystem(hibCanvas.getHibNotation());
		}
		else{
			notationSubsystem = new FallbackNotationSubsystem(hibCanvas.getHibNotation());
		}
		hibCanvas.setNotationSubsystem(notationSubsystem);
		HibModel loadedModel = hibCanvas.getModel();
		initialiseAttributes(hibCanvas);
		initialiseModel(loadedModel);
		s.getTransaction().commit();
		hibCanvas.setMapDiagram(this.getOwningMap());
		this.loadedCanvas = hibCanvas;
	}
	
	/**
	 * @param hibCanvas
	 */
	private void initialiseAttributes(HibCanvas hibCanvas) {
		initShapeAttributes(hibCanvas);
		initLinkAttributes(hibCanvas);
		initLabelAttributes(hibCanvas);
	}

	private void initLabelAttributes(HibCanvas hibCanvas) {
		final INodeObjectType labelObjectType = new LabelObjectType(hibCanvas.getNotationSubsystem().getSyntaxService());
		for(HibLabelAttribute labelAttr : hibCanvas.getLabelAttributes()){
			Hibernate.initialize(labelAttr);
			labelAttr.setObjectType(labelObjectType);
			Hibernate.initialize(labelAttr.getProperty().getValue()) ;
		}
	}

	private void initLinkAttributes(HibCanvas hibCanvas) {
		for(HibLinkAttribute shapeAttr : hibCanvas.getLinkAttributes()){
			Hibernate.initialize(shapeAttr);
			ILinkObjectType objectType = hibCanvas.getNotationSubsystem().getSyntaxService().getLinkObjectType(shapeAttr.getHibObjectType().getUniqueId());
			shapeAttr.setObjectType(objectType);
			Hibernate.initialize(shapeAttr.getHibLinkProperties());
			Hibernate.initialize(shapeAttr.getBendPoints());
		}
	}

	private void initialiseModel(HibModel model){
		INotationSubsystem notationSubsystem = model.getCanvas().getNotationSubsystem();
		HibNotationFactory hibNotationFactory = new HibNotationFactory(this.fact, notationSubsystem);
		hibNotationFactory.initialise();
//		hibNotationFactory.loadNotation();
		model.setHibNotationFactory(hibNotationFactory);
		Hibernate.initialize(model);
		// set the OT required by the root node
		HibRootNode hibRootNode = model.getRootNode();
		Hibernate.initialize(hibRootNode);
		hibRootNode.setObjectType(model.getCanvas().getNotationSubsystem().getSyntaxService().getRootObjectType());
		initNodesAndEdges(model);
	}
	
	private void initNodesAndEdges(HibModel model) {
		// now go through all the nodes and edges and get them loaded from the DB
		Iterator<IDrawingNode> nodeIter = model.drawingNodeIterator();
		while(nodeIter.hasNext()){
			IDrawingNode node = nodeIter.next();
			Hibernate.initialize((HibCompoundNode)node);
			Hibernate.initialize(node.getAttribute());
			HibCompoundNode hibNode = (HibCompoundNode)node;
			Hibernate.initialize(hibNode.getChildren());
			Hibernate.initialize(hibNode.getParent());
			Hibernate.initialize(hibNode.getChildCompoundGraph());
			Hibernate.initialize(hibNode.getInEdges());
			Hibernate.initialize(hibNode.getOutEdges());
			Iterator<ILinkEdge> edgeIter = node.getSubModel().linkIterator();
			while(edgeIter.hasNext()){
				HibLinkEdge link = (HibLinkEdge)edgeIter.next();
				Hibernate.initialize(link);
			}
		}
	}

	/**
	 * 
	 */
	private void initShapeAttributes(HibCanvas canvas) {
		for(HibShapeAttribute shapeAttr : canvas.getShapeAttributes()){
			Hibernate.initialize(shapeAttr);
			IShapeObjectType objectType = canvas.getNotationSubsystem().getSyntaxService().getShapeObjectType(shapeAttr.getHibObjectType().getUniqueId());
			shapeAttr.setShapeObjectType(objectType);
			Hibernate.initialize(shapeAttr.getProperties());
		}
	}

	public ICanvas getLoadedCanvas(){
		return this.loadedCanvas;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.bolayer.ICanvasLoader#finish()
	 */
	public void reset() {
		this.loadedCanvas = null;
		this.owningMap = null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.bolayer.ICanvasLoader#synchroniseCanvas()
	 */
	public void synchroniseCanvas() {
		Session s = this.fact.getCurrentSession();
		s.getTransaction().begin();
		s.saveOrUpdate(this.loadedCanvas);
		Iterator<IDrawingNode> nodeIterator = loadedCanvas.getModel().drawingNodeIterator();
		while ( nodeIterator.hasNext())
		{
			s.saveOrUpdate(nodeIterator.next());
		}
		s.getTransaction().commit();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.management.ICanvasPersistenceHandler#createCanvas(org.pathwayeditor.businessobjects.notationsubsystem.INotationSubsystem)
	 */
	public void createCanvas(INotationSubsystem notationSubsystem) {
		this.loadedCanvas = null;
		Session s = this.fact.getCurrentSession();
		s.getTransaction().begin();
		long canvasTest = (Long) s.getNamedQuery("canvasExistsForMap")
			.setString("repo",this.getOwningMap().getRepository().getName())
			.setInteger("inode", this.getOwningMap().getINode()).uniqueResult();
		HibCanvas hibCanvas = null;
		if(canvasTest == 0){
			HibNotationFactory hibNotationFactory = new HibNotationFactory(this.fact, notationSubsystem);
			hibNotationFactory.initialise();
			hibCanvas = new HibCanvas(this.owningMap, hibNotationFactory, notationSubsystem);
			s.save(hibCanvas);
		}
		else{
			throw new IllegalStateException("canvas already exists");
		}
		s.getTransaction().commit();
		this.loadedCanvas = hibCanvas;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.management.ICanvasPersistenceHandler#doesCanvasExist()
	 */
	public boolean doesCanvasExist() {
		Session s = this.fact.getCurrentSession();
		s.getTransaction().begin();
		long canvasTest = (Long) s.getNamedQuery("canvasExistsForMap")
			.setString("repo",this.getOwningMap().getRepository().getName())
			.setInteger("inode", this.getOwningMap().getINode()).uniqueResult();
		s.getTransaction().commit();
		return canvasTest > 0;
	}
}

