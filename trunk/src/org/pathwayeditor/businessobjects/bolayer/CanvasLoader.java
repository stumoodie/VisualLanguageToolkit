/**
 * 
 */
package org.pathwayeditor.businessobjects.bolayer;

import java.util.Iterator;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.pathwayeditor.businessobjects.drawingprimitives.ICanvas;
import org.pathwayeditor.businessobjects.drawingprimitives.IDrawingNode;
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkEdge;
import org.pathwayeditor.businessobjects.hibernate.helpers.IHibNotationFactory;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibCanvas;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibLabelAttribute;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibLinkAttribute;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibLinkEdge;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibModel;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibRootNode;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibShapeAttribute;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationSubsystem;
import org.pathwayeditor.businessobjects.repository.IMap;
import org.pathwayeditor.businessobjects.typedefn.ILinkObjectType;
import org.pathwayeditor.businessobjects.typedefn.IShapeObjectType;

/**
 * @author smoodie
 *
 */
public class CanvasLoader implements ICanvasLoader {
	private final SessionFactory fact;
	private final INotationSubsystemPool subsystemPool;
	private IMap owningMap = null;
	private ICanvas loadedCanvas = null;
	private IHibNotationFactory hibNotationFactory;
	private final INotationSubsystem defaultNotationSubsystem;
	
	public CanvasLoader(SessionFactory fact, INotationSubsystemPool subsystemPool, INotationSubsystem defaultSubsystem){
		this.fact = fact;
		this.subsystemPool = subsystemPool;
		this.defaultNotationSubsystem = defaultSubsystem;
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
		HibCanvas hibCanvas = (HibCanvas) s.createQuery(
				"from HibCanvas c  where c.repository = :repo and c.inode = :inode")
				.setEntity("repo",this.getOwningMap().getINode())
				.setInteger("inode", this.getOwningMap().getINode()).uniqueResult();
		Hibernate.initialize(hibCanvas);
		hibCanvas.setMapDiagram(this.getOwningMap());
		INotationSubsystem notationSubsystem = this.defaultNotationSubsystem;
		if(this.subsystemPool.hasNotationSubsystem(hibCanvas.getHibNotation())){
			notationSubsystem = this.subsystemPool.getSubsystem(hibCanvas.getHibNotation());
		}
//		else{
//			this.notationSubsystem = this.defaultNotationSubsystem;
//		}
		hibCanvas.setNotationSubsystem(notationSubsystem);
		HibModel loadedModel = hibCanvas.getModel();
		loadedModel.setHibNotationFactory(this.getHibNotationFactory());
		initialiseAttributes(hibCanvas);
		initialiseModel(loadedModel);
		s.getTransaction().commit();
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

	/**
	 * @param hibCanvas
	 */
	private void initLabelAttributes(HibCanvas hibCanvas) {
		for(HibLabelAttribute labelAttr : hibCanvas.getLabelAttributes()){
			Hibernate.initialize(labelAttr);
		}
	}

	/**
	 * @param hibCanvas
	 */
	private void initLinkAttributes(HibCanvas hibCanvas) {
		for(HibLinkAttribute shapeAttr : hibCanvas.getLinkAttributes()){
			Hibernate.initialize(shapeAttr);
			ILinkObjectType objectType = hibCanvas.getNotationSubsystem().getSyntaxService().getLinkObjectType(shapeAttr.getHibObjectType().getUniqueId());
			shapeAttr.setObjectType(objectType);
			Hibernate.initialize(shapeAttr.getHibLinkProperties());
		}
	}

	private void initialiseModel(HibModel model){
		Hibernate.initialize(model);
		// set the OT required by the root node
		HibRootNode hibRootNode = model.getRootNode();
		Hibernate.initialize(hibRootNode);
		hibRootNode.setObjectType(model.getCanvas().getNotationSubsystem().getSyntaxService().getRootMapObjectType());
		initNodesAndEdges(model);
	}
	
	/**
	 * @param model
	 */
	private void initNodesAndEdges(HibModel model) {
		// now go through all the nodes and edges and get them loaded from the DB
		Iterator<IDrawingNode> nodeIter = model.drawingNodeIterator();
		while(nodeIter.hasNext()){
			IDrawingNode node = nodeIter.next();
			Hibernate.initialize(node.getAttribute());
			Iterator<ILinkEdge> edgeIter = node.getSubCanvas().linkIterator();
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
	 * @see org.pathwayeditor.businessobjects.bolayer.ICanvasLoader#getHibNotationFactory()
	 */
	public IHibNotationFactory getHibNotationFactory() {
		return this.hibNotationFactory;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.bolayer.ICanvasLoader#setHibNotationFactory(org.pathwayeditor.businessobjects.hibernate.helpers.IHibNotationFactory)
	 */
	public void setHibNotationFactory(IHibNotationFactory hibNotationFactory) {
		this.hibNotationFactory = hibNotationFactory;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.bolayer.ICanvasLoader#finish()
	 */
	public void reset() {
		this.loadedCanvas = null;
		this.owningMap = null;
		this.hibNotationFactory = null;
	}
}

