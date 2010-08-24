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

package org.pathwayeditor.businessobjects.impl;

import java.util.Iterator;

import org.pathwayeditor.businessobjects.drawingprimitives.IDrawingElement;
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkEdge;
import org.pathwayeditor.businessobjects.drawingprimitives.IRootAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeNode;
import org.pathwayeditor.businessobjects.drawingprimitives.ITypedDrawingNode;
import org.pathwayeditor.businessobjects.drawingprimitives.ITypedDrawingNodeAttribute;

import uk.ac.ed.inf.graph.compound.ICompoundGraphElement;
import uk.ac.ed.inf.graph.compound.ICompoundNode;
import uk.ac.ed.inf.graph.compound.IRootCompoundNode;

/**
 * @author smoodie
 *
 */
public class ShapeNode extends TypedCommonNode implements IShapeNode {
	
	public ShapeNode(ICompoundNode compoundNode){
		super(compoundNode);
	}
	
	@Override
	public IDrawingElement getParentNode() {
		ICompoundGraphElement parent = getCompoundGraphElement().getParent();
		ITypedDrawingNodeAttribute att = (ITypedDrawingNodeAttribute)parent.getAttribute();
		ITypedDrawingNode retVal = null;
		if(att instanceof IShapeAttribute){
			retVal = new ShapeNode((ICompoundNode)parent);
		}
		else if(att instanceof IRootAttribute){
			retVal = new RootNode((IRootCompoundNode)parent);
		}
		else{
			throw new RuntimeException("Attribute type not recognised");
		}
		return retVal;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShapeNode#sourceLinkIterator()
	 */
	@Override
	public Iterator<ILinkEdge> sourceLinkIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShapeNode#targetLinkIterator()
	 */
	@Override
	public Iterator<ILinkEdge> targetLinkIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShapeNode#getAttribute()
	 */
	@Override
	public IShapeAttribute getAttribute() {
		return (IShapeAttribute)this.getCompoundGraphElement().getAttribute();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShapeNode#getNumSourceLinks()
	 */
	@Override
	public int getNumSourceLinks() {
		return getCompoundGraphElement().getOutDegree();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShapeNode#getNumTargetLinks()
	 */
	@Override
	public int getNumTargetLinks() {
		return getCompoundGraphElement().getInDegree();
	}

}
