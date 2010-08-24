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

package org.pathwayeditor.testfixture;


/**
 * @author smoodie
 *
 */
public class GeneralBuilder<T> implements IRedefinableBuilder<T> {
	private final String elementId;
	private final IObjectConstructor<T> defaultConstructor;
	private IObjectConstructor<T> overridingConstructor;
	private T createdObject;

	public GeneralBuilder(String elementId, IObjectConstructor<T> constructor){
		this.elementId = elementId;
		this.defaultConstructor = constructor;
	}
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.testfixture.IObjectBuilder#getElementId()
	 */
	@Override
	public String getElementId() {
		return this.elementId;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.testfixture.IObjectBuilder#create()
	 */
	@Override
	public void create() {
		if(overridingConstructor == null || (createdObject = overridingConstructor.create()) == null){
			createdObject = defaultConstructor.create();
		}
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.testfixture.IObjectBuilder#buildDependencies()
	 */
	@Override
	public void buildDependencies() {
		if(overridingConstructor == null || overridingConstructor.build() == false){
			defaultConstructor.build();
		}
	}

	@Override
	public IObjectConstructor<T> getOverridingConstructor() {
		return this.overridingConstructor;
	}

	@Override
	public void setOverridingConstructor(IObjectConstructor<T> nodeConstructor) {
		this.overridingConstructor = nodeConstructor;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.testfixture.IShapeNodeBuilder#getShapeNode()
	 */
	@Override
	public T getCreatedObject() {
		return this.createdObject;
	}

}
