/**
 * 
 */
package org.pathwayeditor.businessobjects.hibernate.pojos.graph;

import org.pathwayeditor.businessobjects.drawingprimitives.ILabelNodeFactory;
import org.pathwayeditor.businessobjects.drawingprimitives.ISubModel;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationProperty;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibCompoundNode;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibLabelNode;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibModel;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibProperty;

import uk.ed.inf.graph.compound.base.BaseCompoundNode;
import uk.ed.inf.graph.compound.base.BaseCompoundNodeFactory;

/**
 * @author smoodie
 *
 */
public class LabelNodeFactory extends BaseCompoundNodeFactory implements ILabelNodeFactory {
	private HibProperty annotationProperty;
	private final HibCompoundNode parent;
	
	/**
	 * @param parent
	 */
	public LabelNodeFactory(HibCompoundNode parent) {
		super();
		this.parent = parent;
	}

	public void setAnnotationProperty(HibProperty annotationProperty){
		this.annotationProperty = annotationProperty;
	}
	
	/* (non-Javadoc)
	 * @see uk.ed.inf.graph.compound.base.BaseCompoundNodeFactory#newNode(uk.ed.inf.graph.compound.base.BaseCompoundNode, int)
	 */
	@Override
	protected HibCompoundNode newNode(BaseCompoundNode parent, int nodeIndex) {
		return new HibLabelNode((HibCompoundNode)parent, nodeIndex, this.annotationProperty);
	}

	/* (non-Javadoc)
	 * @see uk.ed.inf.graph.compound.base.BaseCompoundNodeFactory#getGraph()
	 */
	@Override
	public HibModel getGraph() {
		return this.parent.getModel();
	}

	/* (non-Javadoc)
	 * @see uk.ed.inf.graph.compound.base.BaseCompoundNodeFactory#getParentNode()
	 */
	@Override
	public HibCompoundNode getParentNode() {
		return this.parent;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILabelNodeFactory#createLabel()
	 */
	public HibLabelNode createLabel() {
		return (HibLabelNode)super.createNode();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILabelNodeFactory#getOwningProperty()
	 */
	public IAnnotationProperty getOwningProperty() {
		return this.annotationProperty;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILabelNodeFactory#getOwningSubCanvas()
	 */
	public ISubModel getOwningSubCanvas() {
		return this.parent.getSubCanvas();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILabelNodeFactory#isValidProperty(org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationProperty)
	 */
	public boolean isValidProperty(IAnnotationProperty annotationProperty) {
		return annotationProperty != null &&
			this.annotationProperty.getCanvas().getModel().equals(this.getGraph());
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILabelNodeFactory#setProperty(org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationProperty)
	 */
	public void setProperty(IAnnotationProperty annotationProperty) {
		this.annotationProperty = (HibProperty)annotationProperty;
	}

}
