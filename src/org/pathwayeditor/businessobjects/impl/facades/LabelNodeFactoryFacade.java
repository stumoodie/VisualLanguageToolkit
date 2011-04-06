/*
Copyright 2009-2011, Court of the University of Edinburgh
*/

package org.pathwayeditor.businessobjects.impl.facades;

import org.pathwayeditor.businessobjects.drawingprimitives.ICanvasElementAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.ILabelAttributeFactory;
import org.pathwayeditor.businessobjects.drawingprimitives.ILabelNode;
import org.pathwayeditor.businessobjects.drawingprimitives.ILabelNodeFactory;
import org.pathwayeditor.businessobjects.drawingprimitives.IRootAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationProperty;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationSyntaxService;
import org.pathwayeditor.businessobjects.typedefn.ILabelObjectType;

import uk.ac.ed.inf.graph.compound.ICompoundNodeFactory;

/**
 * @author Stuart Moodie
 *
 */
public class LabelNodeFactoryFacade implements ILabelNodeFactory {
	private final ICompoundNodeFactory labelCompoundNodeFactory;
	private final ILabelAttributeFactory labelAttributeFactory;
//	private IAnnotationProperty visualisableProperty;

	public LabelNodeFactoryFacade(ICompoundNodeFactory shapeCompoundNodeFactory){
		this.labelCompoundNodeFactory = shapeCompoundNodeFactory;
		IRootAttribute rootAttribute = (IRootAttribute)this.labelCompoundNodeFactory.getGraph().getRoot().getAttribute();
		labelAttributeFactory = rootAttribute.getModel().labelAttributeFactory();
		shapeCompoundNodeFactory.setAttributeFactory(labelAttributeFactory);
	}
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShapeNodeFactory#canCreateShapeNode()
	 */
	@Override
	public boolean canCreateLabelNode() {
		return this.labelCompoundNodeFactory.canCreateNode() && isValidProperty(this.labelAttributeFactory.getProperty());
	}

	@Override
	public ILabelNode createLabelNode() {
		return new LabelNodeFacade(labelCompoundNodeFactory.createNode());
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShapeNodeFactory#getGraphElementFactory()
	 */
	@Override
	public ICompoundNodeFactory getGraphElementFactory() {
		return this.labelCompoundNodeFactory;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.labelCompoundNodeFactory == null) ? 0 : this.labelCompoundNodeFactory.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof ILabelNodeFactory)) {
			return false;
		}
		ILabelNodeFactory other = (ILabelNodeFactory) obj;
		if (this.labelCompoundNodeFactory == null) {
			if (other.getGraphElementFactory() != null) {
				return false;
			}
		} else if (!this.labelCompoundNodeFactory.equals(other.getGraphElementFactory())) {
			return false;
		}
		return true;
	}


	@Override
	public String toString(){
		StringBuilder buf = new StringBuilder(this.getClass().getSimpleName());
		buf.append("(");
		buf.append("element=");
		buf.append(this.labelCompoundNodeFactory);
		buf.append(")");
		return buf.toString();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILabelNodeFactory#getOwningProperty()
	 */
	@Override
	public IAnnotationProperty getOwningProperty() {
		return labelAttributeFactory.getProperty();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILabelNodeFactory#isValidProperty(org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationProperty)
	 */
	@Override
	public boolean isValidProperty(IAnnotationProperty annotationProperty) {
		boolean retVal = false;
		if(annotationProperty != null && annotationProperty.getOwner().getCurrentElement().equals(this.labelCompoundNodeFactory.getParentNode())){
			INotationSyntaxService syntaxSubsystem = ((ICanvasElementAttribute)annotationProperty.getOwner().getCurrentElement().getAttribute()).getModel().getNotationSubsystem().getSyntaxService();
			retVal = syntaxSubsystem.isVisualisableProperty(annotationProperty.getDefinition());
		}
		return retVal;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILabelNodeFactory#setProperty(org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationProperty)
	 */
	@Override
	public void setProperty(IAnnotationProperty annotationProperty) {
		this.labelAttributeFactory.setProperty(annotationProperty);
		INotationSyntaxService syntaxSubsystem = ((ICanvasElementAttribute)annotationProperty.getOwner().getCurrentElement().getAttribute()).getModel().getNotationSubsystem().getSyntaxService();
		ILabelObjectType labelObjectType = syntaxSubsystem.getLabelObjectTypeByProperty(annotationProperty.getDefinition());
		this.labelAttributeFactory.setLabelObjectType(labelObjectType);
	}
}
