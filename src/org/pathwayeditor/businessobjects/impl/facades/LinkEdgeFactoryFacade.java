/*
  Licensed to the Court of the University of Edinburgh (UofE) under one
  or more contributor license agreements.  See the NOTICE file
  distributed with this work for additional information
  regarding copyright ownership.  The UofE licenses this file
  to you under the Apache License, Version 2.0 (the
  "License"); you may not use this file except in compliance
  with the License.  You may obtain a copy of the License at

  http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing,
  software distributed under the License is distributed on an
  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
  KIND, either express or implied.  See the License for the
  specific language governing permissions and limitations
  under the License.
*/


package org.pathwayeditor.businessobjects.impl.facades;

import org.pathwayeditor.businessobjects.drawingprimitives.ILinkAttributeFactory;
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkEdge;
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkEdgeFactory;
import org.pathwayeditor.businessobjects.drawingprimitives.IRootAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeNode;
import org.pathwayeditor.businessobjects.typedefn.ILinkObjectType;

import uk.ac.ed.inf.graph.compound.CompoundNodePair;
import uk.ac.ed.inf.graph.compound.ICompoundEdgeFactory;

/**
 * 
 * 
 * @author Stuart Moodie
 *
 */

public class LinkEdgeFactoryFacade implements ILinkEdgeFactory {
	private ICompoundEdgeFactory compoundEdge;
	
	public LinkEdgeFactoryFacade(ICompoundEdgeFactory compoundEdge){
		this.compoundEdge = compoundEdge;
		IRootAttribute rootAttribute = (IRootAttribute)compoundEdge.getGraph().getRoot().getAttribute();
		this.compoundEdge.setAttributeFactory(rootAttribute.getModel().linkAttributeFactory());
	}
	
	@Override
	public boolean isValidShapeNodePair(IShapeNode source, IShapeNode target) {
		return this.compoundEdge.isValidNodePair(new CompoundNodePair(source.getGraphElement(), target.getGraphElement()));
	}
	
	@Override
	public void setShapeNodePair(IShapeNode source, IShapeNode target) {
		this.compoundEdge.setPair(new CompoundNodePair(source.getGraphElement(), target.getGraphElement()));
	}

	@Override
	public void setObjectType(ILinkObjectType objectType) {
		((ILinkAttributeFactory)this.compoundEdge.getAttributeFactory()).setObjectType(objectType);
	}

	@Override
	public ILinkObjectType getCurrentObjectType() {
		return ((ILinkAttributeFactory)this.compoundEdge.getAttributeFactory()).getObjectType();
	}

	@Override
	public boolean canCreateLink() {
		return this.compoundEdge.canCreateEdge();
	}

	@Override
	public ILinkEdge createLinkEdge() {
		return new LinkEdgeFacade(this.compoundEdge.createEdge());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.compoundEdge == null) ? 0 : this.compoundEdge.hashCode());
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
		if (!(obj instanceof ILinkEdgeFactory)) {
			return false;
		}
		ILinkEdgeFactory other = (ILinkEdgeFactory) obj;
		if (this.compoundEdge == null) {
			if (other.getGraphEdgeFactory() != null) {
				return false;
			}
		} else if (!this.compoundEdge.equals(other.getGraphEdgeFactory())) {
			return false;
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILinkEdgeFactory#getGraphEdgeFactory()
	 */
	@Override
	public ICompoundEdgeFactory getGraphEdgeFactory() {
		return this.compoundEdge;
	}


	@Override
	public String toString(){
		StringBuilder buf = new StringBuilder(this.getClass().getSimpleName());
		buf.append("(");
		buf.append("element=");
		buf.append(this.compoundEdge);
		buf.append(")");
		return buf.toString();
	}
}
