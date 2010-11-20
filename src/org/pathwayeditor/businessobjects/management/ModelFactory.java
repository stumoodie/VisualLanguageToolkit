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

package org.pathwayeditor.businessobjects.management;

import org.pathwayeditor.businessobjects.drawingprimitives.IModel;
import org.pathwayeditor.businessobjects.impl.Model;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationSubsystem;

/**
 * @author smoodie
 *
 */
public class ModelFactory implements IModelFactory {

	private INotationSubsystem rootObjectType;
	private String name;
	private int lastSerialIdx;
	private int serialIdx;

	public ModelFactory(){
		this.serialIdx = 0;
		this.lastSerialIdx = 0;
	}
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.management.IModelFactory#setRootCreationSerial(int)
	 */
	@Override
	public void setRootCreationSerial(int serialIdx) {
		this.serialIdx = serialIdx;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.management.IModelFactory#setLastCreationSerial(int)
	 */
	@Override
	public void setLastCreationSerial(int lastSerialIdx) {
		this.lastSerialIdx = lastSerialIdx;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.management.IModelFactory#setName(java.lang.String, org.pathwayeditor.businessobjects.typedefn.IRootObjectType)
	 */
	@Override
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public void setNotationSubsystem(INotationSubsystem notationSubsystem){
		this.rootObjectType = notationSubsystem;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.management.IModelFactory#createModel()
	 */
	@Override
	public IModel createModel() {
		return new Model(this.name, this.rootObjectType, this.serialIdx, this.lastSerialIdx);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.management.IModelFactory#getRootCreationSerial()
	 */
	@Override
	public int getRootCreationSerial() {
		return this.serialIdx;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.management.IModelFactory#getLastCreationSerial()
	 */
	@Override
	public int getLastCreationSerial() {
		return this.lastSerialIdx;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.management.IModelFactory#getName()
	 */
	@Override
	public String getName() {
		return this.name;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.management.IModelFactory#getRootObjectType()
	 */
	@Override
	public INotationSubsystem getNotationSubsystem() {
		return this.rootObjectType;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.management.IModelFactory#canCreateModel()
	 */
	@Override
	public boolean canCreateModel() {
		return this.rootObjectType != null && this.name != null && !this.name.isEmpty();
	}

}
