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

import org.pathwayeditor.businessobjects.drawingprimitives.IAttributeFactoryFactory;
import org.pathwayeditor.businessobjects.drawingprimitives.ILabelAttributeCopyFactory;
import org.pathwayeditor.businessobjects.drawingprimitives.ILabelAttributeFactory;
import org.pathwayeditor.businessobjects.drawingprimitives.ILabelAttributeMoveFactory;
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkAttributeCopyFactory;
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkAttributeFactory;
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkAttributeMoveFactory;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeAttributeCopyFactory;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeAttributeFactory;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeAttributeMoveFactory;

import uk.ac.ed.inf.graph.util.IndexCounter;

/**
 * @author smoodie
 *
 */
public class AttributeFactoryFactory implements IAttributeFactoryFactory {
	private final IndexCounter creationSerialCounter;
	
	public AttributeFactoryFactory(IndexCounter indexCouter){
		this.creationSerialCounter = indexCouter;
	}
	
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ITypedDrawingNodeAttribute#shapeAttributeFactory()
	 */
	@Override
	public IShapeAttributeFactory shapeAttributeFactory() {
		return new ShapeAttributeFactory(this.creationSerialCounter);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ITypedDrawingNodeAttribute#linkAttributeFactory()
	 */
	@Override
	public ILinkAttributeFactory linkAttributeFactory() {
		return new LinkAttributeFactory(this.creationSerialCounter);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IRootAttribute#labelAttributeFactory()
	 */
	@Override
	public ILabelAttributeFactory labelAttributeFactory() {
		return new LabelAttributeFactory(this.creationSerialCounter);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IRootAttribute#shapeAttributeCopyFactory()
	 */
	@Override
	public IShapeAttributeCopyFactory shapeAttributeCopyFactory() {
		return new ShapeAttributeCopyFactory(this.creationSerialCounter);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IRootAttribute#linkAttributeCopyFactory()
	 */
	@Override
	public ILinkAttributeCopyFactory linkAttributeCopyFactory() {
		return new LinkAttributeCopyFactory(this.creationSerialCounter);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IRootAttribute#labelAttributeCopyFactory()
	 */
	@Override
	public ILabelAttributeCopyFactory labelAttributeCopyFactory() {
		return new LabelAttributeCopyFactory(this.creationSerialCounter);
	}


	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IAttributeFactoryFactory#shapeAttributeMoveFactory()
	 */
	@Override
	public IShapeAttributeMoveFactory shapeAttributeMoveFactory() {
		// TODO Auto-generated method stub
		return null;
	}


	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IAttributeFactoryFactory#linkAttributeMoveFactory()
	 */
	@Override
	public ILinkAttributeMoveFactory linkAttributeMoveFactory() {
		// TODO Auto-generated method stub
		return null;
	}


	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IAttributeFactoryFactory#labelAttributeMoveFactory()
	 */
	@Override
	public ILabelAttributeMoveFactory labelAttributeMoveFactory() {
		// TODO Auto-generated method stub
		return null;
	}
}
