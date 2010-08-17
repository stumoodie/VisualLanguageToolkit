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

import org.pathwayeditor.businessobjects.drawingprimitives.IDrawingElement;
import org.pathwayeditor.businessobjects.drawingprimitives.ILabelAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.ILabelNode;
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkEdge;
import org.pathwayeditor.businessobjects.drawingprimitives.IParentingValidator;
import org.pathwayeditor.businessobjects.drawingprimitives.ITypedDrawingNode;
import org.pathwayeditor.businessobjects.typedefn.IObjectType;

import uk.ac.ed.inf.graph.compound.ICompoundNode;

/**
 * @author smoodie
 *
 */
public class LabelNode extends CommonNode implements ILabelNode {

	public LabelNode(ICompoundNode compoundNode) {
		super(compoundNode);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IDrawingNode#getParentNode()
	 */
	@Override
	public ITypedDrawingNode getParentNode() {
		return new ShapeNode((ICompoundNode)getCompoundGraphElement().getParent());
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILabelNode#getAttribute()
	 */
	@Override
	public ILabelAttribute getAttribute() {
		return (ILabelAttribute)this.getCompoundGraphElement().getAttribute();
	}

	@Override
	public final IParentingValidator getParentingValidator(){
		return new IParentingValidator(){

			@Override
			public boolean canReparentTo(ILabelNode possibleChild) {
				return false;
			}

			@Override
			public boolean canReparentTo(ITypedDrawingNode possibleChild) {
				return false;
			}

			@Override
			public boolean canReparentTo(ILinkEdge possibleChild) {
				return false;
			}

			@Override
			public boolean isValidChildOf(ILabelNode possibleParent) {
				return false;
			}

			@Override
			public boolean isValidChildOf(ITypedDrawingNode possibleParent) {
				return true;
			}

			@Override
			public boolean isValidChildOf(ILinkEdge possibleParent) {
				return true;
			}
			
		};
	}

	@Override
	public final boolean canParent(IDrawingElement possibleChild) {
			return possibleChild.getParentingValidator().isValidChildOf(this);
		}

	@Override
	public final boolean canParent(IObjectType childType) {
		return false;
	}

	@Override
	public final boolean isValidChildOf(IDrawingElement possibleParent) {
			boolean retVal = false;
			if(possibleParent != null){
				retVal = possibleParent.getParentingValidator().canReparentTo(this);
			}
			return retVal;
		}

	@Override
	public final boolean isValidChildOf(IObjectType parentType) {
		return false;
	}

}
