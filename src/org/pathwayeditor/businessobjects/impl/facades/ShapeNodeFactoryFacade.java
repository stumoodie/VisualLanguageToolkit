/*
Copyright 2009-2011, Court of the University of Edinburgh
*/

package org.pathwayeditor.businessobjects.impl.facades;

import org.pathwayeditor.businessobjects.drawingprimitives.IRootAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeAttributeFactory;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeNode;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeNodeFactory;
import org.pathwayeditor.businessobjects.typedefn.IShapeObjectType;

import uk.ac.ed.inf.graph.compound.ICompoundNodeFactory;

/**
 * @author Stuart Moodie
 *
 */
public class ShapeNodeFactoryFacade implements IShapeNodeFactory {
	private final ICompoundNodeFactory shapeCompoundNodeFactory;

	public ShapeNodeFactoryFacade(ICompoundNodeFactory shapeCompoundNodeFactory){
		this.shapeCompoundNodeFactory = shapeCompoundNodeFactory;
		IRootAttribute rootAttribute = (IRootAttribute)this.shapeCompoundNodeFactory.getGraph().getRoot().getAttribute();
		IShapeAttributeFactory shapeAttributeFactory = rootAttribute.getModel().shapeAttributeFactory();
		shapeCompoundNodeFactory.setAttributeFactory(shapeAttributeFactory);
	}
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShapeNodeFactory#setObjectType(org.pathwayeditor.businessobjects.typedefn.IShapeObjectType)
	 */
	@Override
	public void setObjectType(IShapeObjectType shapeObjectType) {
		((IShapeAttributeFactory)this.shapeCompoundNodeFactory.getAttributeFactory()).setObjectType(shapeObjectType);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShapeNodeFactory#getCurrentObjectType()
	 */
	@Override
	public IShapeObjectType getCurrentObjectType() {
		return ((IShapeAttributeFactory)this.shapeCompoundNodeFactory.getAttributeFactory()).getObjectType();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShapeNodeFactory#canCreateShapeNode()
	 */
	@Override
	public boolean canCreateShapeNode() {
		return this.shapeCompoundNodeFactory.canCreateNode();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShapeNodeFactory#createShapeNode()
	 */
	@Override
	public IShapeNode createShapeNode() {
		return new ShapeNodeFacade(shapeCompoundNodeFactory.createNode());
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShapeNodeFactory#getGraphElementFactory()
	 */
	@Override
	public ICompoundNodeFactory getGraphElementFactory() {
		return this.shapeCompoundNodeFactory;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.shapeCompoundNodeFactory == null) ? 0 : this.shapeCompoundNodeFactory.hashCode());
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
		if (!(obj instanceof IShapeNodeFactory)) {
			return false;
		}
		IShapeNodeFactory other = (IShapeNodeFactory) obj;
		if (this.shapeCompoundNodeFactory == null) {
			if (other.getGraphElementFactory() != null) {
				return false;
			}
		} else if (!this.shapeCompoundNodeFactory.equals(other.getGraphElementFactory())) {
			return false;
		}
		return true;
	}


	@Override
	public String toString(){
		StringBuilder buf = new StringBuilder(this.getClass().getSimpleName());
		buf.append("(");
		buf.append("element=");
		buf.append(this.shapeCompoundNodeFactory);
		buf.append(")");
		return buf.toString();
	}
}
