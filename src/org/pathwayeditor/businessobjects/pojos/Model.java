/**
 * 
 */
package org.pathwayeditor.businessobjects.pojos;

import java.util.Iterator;

import org.pathwayeditor.businessobjects.contectadapter.IContextAdapterServiceProvider;
import org.pathwayeditor.businessobjects.drawingprimitives.ICanvas;
import org.pathwayeditor.businessobjects.drawingprimitives.ICanvasObjectSelection;
import org.pathwayeditor.businessobjects.drawingprimitives.ILabelAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.IModel;
import org.pathwayeditor.businessobjects.drawingprimitives.IModelState;
import org.pathwayeditor.businessobjects.drawingprimitives.IRootObjectNode;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeAttribute;
import org.pathwayeditor.businessobjects.typedefn.ILinkObjectType;

import uk.ed.inf.graph.compound.archetypal.ArchetypalCompoundGraph;
import uk.ed.inf.graph.compound.archetypal.ArchetypalCompoundNode;
import uk.ed.inf.graph.compound.base.BaseSubCompoundGraph;
import uk.ed.inf.graph.compound.base.BaseSubCompoundGraphFactory;

/**
 * @author smoodie
 *
 */
public class Model extends ArchetypalCompoundGraph implements IModel {
	private RootObject rootNode;
	private ModelLinkFactory modelLinkFactory; 
	private ShapeFactory shapeFactory; 

	public Model() {
		super(new CopyBuilder());
		this.modelLinkFactory = new ModelLinkFactory(this);
		this.shapeFactory = new ShapeFactory(this.getRootNode());
	}
	
	public Model(Model model){
		super(new CopyBuilder(), model);
		this.modelLinkFactory = new ModelLinkFactory(this);
	}
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IModel#canCreateLink(org.pathwayeditor.businessobjects.typedefn.ILinkObjectType, org.pathwayeditor.businessobjects.drawingprimitives.IShape, org.pathwayeditor.businessobjects.drawingprimitives.IShape)
	 */
	public boolean canCreateLink(ILinkObjectType linkObjectType, IShapeAttribute srcShape, IShapeAttribute tgtShape) {
		return linkObjectType.getLinkConnectionRules().isValidTarget(srcShape.getObjectType(), tgtShape.getObjectType());
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IModel#createCopy(org.pathwayeditor.businessobjects.drawingprimitives.ICanvas)
	 */
	public IModel createCopy(ICanvas newCanvas) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IModel#createLink(org.pathwayeditor.businessobjects.typedefn.ILinkObjectType, org.pathwayeditor.businessobjects.drawingprimitives.IShape, org.pathwayeditor.businessobjects.drawingprimitives.IShape)
	 */
	public ILinkAttribute createLink(ILinkObjectType linkObjectType, IShapeAttribute srcShape, IShapeAttribute tgtShape) {
		ModelLinkFactory fact = this.edgeFactory();
		fact.setObjectType(linkObjectType);
		fact.setPair((Shape)srcShape, (Shape)tgtShape);
		return (Link)fact.createEdge();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IModel#getContextAdapterServiceProvider()
	 */
	public IContextAdapterServiceProvider getContextAdapterServiceProvider() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IModel#getNumLabels()
	 */
	public int getNumLabels() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IModel#getNumLinks()
	 */
	public int getNumLinks() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IModel#getNumShapes()
	 */
	public int getNumShapes() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IModel#getRootObject()
	 */
	public IRootObjectNode getRootObject() {
		return (RootObject)this.getRootNode();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IModel#labelIterator()
	 */
	public Iterator<ILabelAttribute> labelIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IModel#linkIterator()
	 */
	public Iterator<ILinkAttribute> linkIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IModel#removedSubgraph(org.pathwayeditor.businessobjects.drawingprimitives.ISelectionSubgraphFactory)
	 */
	public void removeSubgraph(ICanvasObjectSelection iSubgraph) {
		BaseSubCompoundGraphFactory fact = (BaseSubCompoundGraphFactory)iSubgraph;  
		BaseSubCompoundGraph subgraph = fact.createInducedSubgraph(); 
		this.removeSubgraph(subgraph);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IModel#shapeIterator()
	 */
	public Iterator<IShapeAttribute> shapeIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see uk.ed.inf.graph.compound.IModifiableCompoundGraph#edgeFactory()
	 */
	public ModelLinkFactory edgeFactory() {
		return this.modelLinkFactory;
	}

	/* (non-Javadoc)
	 * @see uk.ed.inf.graph.basic.IModifiableGraph#nodeFactory()
	 */
	public ShapeFactory nodeFactory() {
		return this.shapeFactory;
	}

	/* (non-Javadoc)
	 * @see uk.ed.inf.graph.basic.IModifiableGraph#subgraphFactory()
	 */
	public BaseSubCompoundGraphFactory subgraphFactory() {
		// TODO Auto-generated method stub
		return null;
	}

	public GraphModelState getCurrentState(){
		return new GraphModelState(super.getCurrentState());
	}
	
	public void restoreToState(IModelState state){
		GraphModelState modelState = (GraphModelState)state;
		super.restoreState(modelState);
	}

	/* (non-Javadoc)
	 * @see uk.ed.inf.graph.compound.impl.ArchetypalCompoundGraph#copyRootNode(int, uk.ed.inf.graph.compound.impl.ArchetypalCompoundNode)
	 */
	@Override
	protected void createCopyOfRootNode(int newIndexValue, ArchetypalCompoundNode otherRootNode) {
		this.rootNode = new RootObject(this, newIndexValue, (RootObject)otherRootNode);
	}

	/* (non-Javadoc)
	 * @see uk.ed.inf.graph.compound.impl.ArchetypalCompoundGraph#getRootNode()
	 */
	@Override
	public RootObject getRootNode() {
		return this.rootNode;
	}

	/* (non-Javadoc)
	 * @see uk.ed.inf.graph.compound.impl.ArchetypalCompoundGraph#newRootNode(int)
	 */
	@Override
	protected void createNewRootNode(int indexValue) {
		this.rootNode = new RootObject(this, indexValue);
	}
}
