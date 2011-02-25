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

import java.util.HashMap;
import java.util.Map;

import org.jmock.Mockery;

/**
 * @author Stuart Moodie
 *
 */
public abstract class CommonTestFixture {
	private final Mockery mockery;
	private final String prefix;
	private final Map<String, IObjectBuilder> builderMap;
	
	public CommonTestFixture(Mockery mockery, String prefix){
		this.mockery = mockery;
		this.prefix = prefix;
		this.builderMap = new HashMap<String, IObjectBuilder>();
	}
	

	protected Mockery getMockery(){
		return this.mockery;
	}
	
	protected abstract IObjectBuilder[] getElementBuilders();

	protected void initialise(){
		for(IObjectBuilder builder : this.getElementBuilders()){
			builderMap.put(builder.getElementId(), builder);
		}
	}

	/**
	 * @param shapeNode1Id
	 * @return
	 */
	protected String createMockGraphNodeName(String shapeNode1Id) {
		StringBuilder buf = new StringBuilder(createMockName(shapeNode1Id));
		buf.append("GraphNode");
		return buf.toString();
	}


	/**
	 * @param shapeNode1Id
	 * @return
	 */
	protected String createMockLinkEdgeFactoryName(String shapeNode1Id) {
		StringBuilder buf = new StringBuilder(createMockName(shapeNode1Id));
		buf.append("LinkEdgeFactory");
		return buf.toString();
	}


	/**
	 * @param shapeNode1Id
	 * @return
	 */
	protected String createMockShapeNodeFactoryName(String shapeNode1Id) {
		StringBuilder buf = new StringBuilder(createMockName(shapeNode1Id));
		buf.append("ShapeNodeFactory");
		return buf.toString();
	}


	protected String createMockSubModelName(String shapeNode1Id) {
		StringBuilder buf = new StringBuilder(createMockName(shapeNode1Id));
		buf.append("SubModel");
		return buf.toString();
	}

	protected String createMockName(String name){
		StringBuilder buf = new StringBuilder();
		if(!prefix.isEmpty()){
			buf.append(prefix);
			buf.append(Character.toUpperCase(name.charAt(0)));
			buf.append(name, 1, name.length());
		}
		else{
			buf.append(name);
		}
		return buf.toString();
	}

	public void buildFixture(){
		for(String elementId : this.getElementCreationOrder()){
			this.builderMap.get(elementId).create();
		}
		for(String elementId : this.getElementCreationOrder()){
			this.builderMap.get(elementId).buildDependencies();
		}
	}

	public final <T> void redefineBuilder(String id, IObjectConstructor<T> constructor){
		@SuppressWarnings("unchecked")
		IRedefinableBuilder<T> builder = (IRedefinableBuilder<T>)this.builderMap.get(id);
		builder.setOverridingConstructor(constructor);
	}

	public final <T> T getObject(String id) {
		@SuppressWarnings("unchecked")
		IRedefinableBuilder<T> builder = (IRedefinableBuilder<T>)this.builderMap.get(id);
		return builder.getCreatedObject();
	}
	
	protected abstract String[] getElementCreationOrder();

}
