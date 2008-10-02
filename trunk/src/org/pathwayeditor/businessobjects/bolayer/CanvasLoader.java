/**
 * 
 */
package org.pathwayeditor.businessobjects.bolayer;

import java.util.Iterator;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.pathwayeditor.businessobjects.contextadapter.INotationSubsystem;
import org.pathwayeditor.businessobjects.drawingprimitives.ICanvas;
import org.pathwayeditor.businessobjects.drawingprimitives.IDrawingNode;
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkEdge;
import org.pathwayeditor.businessobjects.hibernate.helpers.IHibNotationFactory;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibCanvas;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibLinkEdge;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibModel;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibRootNode;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibShapeAttribute;
import org.pathwayeditor.businessobjects.repository.IMap;
import org.pathwayeditor.businessobjects.typedefn.IShapeObjectType;

/**
 * @author smoodie
 *
 */
public class CanvasLoader {
	private final SessionFactory fact;
	private IMap owningMap = null;
	private INotationSubsystem notationSubsystem = null;
	private ICanvas loadedCanvas = null;
	private IHibNotationFactory hibNotationFactory;
	
	public CanvasLoader(SessionFactory fact){
		this.fact = fact;
	}
	
	public void setOwningMap(IMap owningMap){
		this.owningMap = owningMap;
	}
	
	public IMap getOwningMap() {
		return this.owningMap;
	}

	public INotationSubsystem getNotationSubsystem() {
		return this.notationSubsystem;
	}

	public void setNotationSubsystem(INotationSubsystem syntaxService) {
		this.notationSubsystem = syntaxService;
	}

	public void loadCanvas(){
		this.loadedCanvas = null;
		Session s = this.fact.getCurrentSession();
		s.getTransaction().begin();
		HibCanvas hibCanvas = (HibCanvas) s.createQuery(
				"from HibCanvas c  where c.repository = :repo and c.inode = :inode")
				.setEntity("repo",this.getOwningMap().getINode())
				.setInteger("inode", this.getOwningMap().getINode()).uniqueResult();
		hibCanvas.setMapDiagram(this.getOwningMap());
		hibCanvas.setNotationSubsystem(notationSubsystem);
		HibModel loadedModel = hibCanvas.getModel();
		loadedModel.setHibNotationFactory(this.getHibNotationFactory());
		initialiseModel(loadedModel);
		initialiseAttributes(hibCanvas);
		s.getTransaction().commit();
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
		// TODO Auto-generated method stub
		
	}

	/**
	 * @param hibCanvas
	 */
	private void initLinkAttributes(HibCanvas hibCanvas) {
		// TODO Auto-generated method stub
		
	}

	private void initialiseModel(HibModel model){
		initNodesAndEdges(model);
	}
	
	/**
	 * @param model
	 */
	private void initNodesAndEdges(HibModel model) {
		// set the OT required by the root node
		HibRootNode hibRootNode = model.getRootNode();
		hibRootNode.setObjectType(this.notationSubsystem.getSyntaxService().getRootMapObjectType());
		// now go through the nodes edges and get them loaded from the DB
		Iterator<IDrawingNode> nodeIter = model.drawingNodeIterator();
		while(nodeIter.hasNext()){
			IDrawingNode node = nodeIter.next();
			node.getAttribute();
			Iterator<ILinkEdge> edgeIter = node.getSubCanvas().linkIterator();
			while(edgeIter.hasNext()){
				HibLinkEdge link = (HibLinkEdge)edgeIter.next();
				link.getAttribute();
			}
		}
	}

	/**
	 * 
	 */
	private void initShapeAttributes(HibCanvas canvas) {
		for(HibShapeAttribute shapeAttr : canvas.getShapeAttributes()){
			IShapeObjectType objectType = this.notationSubsystem.getSyntaxService().getShapeObjectType(shapeAttr.getHibObjectType().getName());
			shapeAttr.setShapeObjectType(objectType);
		}
		
	}

	ICanvas getLoadedCanvas(){
		return this.loadedCanvas;
	}

	public IHibNotationFactory getHibNotationFactory() {
		return this.hibNotationFactory;
	}

	public void setHibNotationFactory(IHibNotationFactory hibNotationFactory) {
		this.hibNotationFactory = hibNotationFactory;
	}
}

