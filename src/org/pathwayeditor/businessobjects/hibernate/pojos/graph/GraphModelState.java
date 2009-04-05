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
package org.pathwayeditor.businessobjects.hibernate.pojos.graph;

import java.util.Date;

import org.pathwayeditor.businessobjects.drawingprimitives.IGraphMomento;
import org.pathwayeditor.businessobjects.drawingprimitives.IModel;

import uk.ed.inf.bitstring.IBitString;
import uk.ed.inf.graph.basic.IBasicGraph;
import uk.ed.inf.graph.compound.base.BaseCompoundEdge;
import uk.ed.inf.graph.compound.base.BaseCompoundNode;
import uk.ed.inf.graph.state.IGraphState;

/**
 * @author smoodie
 *
 */
public class GraphModelState implements IGraphMomento, IGraphState<BaseCompoundNode, BaseCompoundEdge> {
	private final IModel model;
	private final IGraphState<BaseCompoundNode, BaseCompoundEdge> state;
	private final Date creationDate;
	private final int versionNum;

	public GraphModelState(IModel model, int versionNum, IGraphState<BaseCompoundNode, BaseCompoundEdge> state){
		this.state = state;
		this.model = model;
		this.creationDate = new Date();
		this.versionNum = versionNum;
	}
	
	public IBitString getEdgeStates() {
		return this.state.getEdgeStates();
	}

	public IBasicGraph<BaseCompoundNode, BaseCompoundEdge> getGraph() {
		return this.state.getGraph();
	}

	public IBitString getNodeStates() {
		return this.state.getNodeStates();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IGraphMomento#getCanvas()
	 */
	public IModel getModel() {
		return this.model;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IGraphMomento#getCreationDate()
	 */
	public Date getCreationDate() {
		return new Date(this.creationDate.getTime());
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IGraphMomento#getVersionNum()
	 */
	public int getVersionNum() {
		return this.versionNum;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((this.model == null) ? 0 : this.model.hashCode());
		result = prime * result + this.versionNum;
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof GraphModelState))
			return false;
		GraphModelState other = (GraphModelState) obj;
		if (this.model == null) {
			if (other.model != null)
				return false;
		} else if (!this.model.equals(other.model))
			return false;
		if (this.versionNum != other.versionNum)
			return false;
		return true;
	}

}
